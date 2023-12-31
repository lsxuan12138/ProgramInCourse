/*
 * Copyright (c) 2013-2019 Huawei Technologies Co., Ltd. All rights reserved.
 * Copyright (c) 2020-2022 Huawei Device Co., Ltd. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this list of
 *    conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice, this list
 *    of conditions and the following disclaimer in the documentation and/or other materials
 *    provided with the distribution.
 *
 * 3. Neither the name of the copyright holder nor the names of its contributors may be used
 *    to endorse or promote products derived from this software without specific prior written
 *    permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS;
 * OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR
 * OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF
 * ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

#include "stdlib.h"
#include "los_config.h"
#include "los_exc.h"
#include "los_memstat_pri.h"
#include "los_sem_pri.h"
#include "los_seq_buf.h"
#include "los_task_pri.h"
#ifdef LOSCFG_PID_CONTAINER
#include "los_pid_container.h"
#endif
#ifdef LOSCFG_SHELL
#include "shcmd.h"
#include "shell.h"
#endif
#ifdef LOSCFG_KERNEL_CPUP
#include "los_cpup_pri.h"
#endif
#ifdef LOSCFG_SAVE_EXCINFO
#include "los_excinfo_pri.h"
#endif
#include "los_process_pri.h"
#include "los_vm_dump.h"
#ifdef LOSCFG_FS_VFS
#include "fs/file.h"
#endif
#include "los_sched_pri.h"
#include "los_swtmr_pri.h"

#define OS_PROCESS_MEM_INFO 0x2U
#define OS_PROCESS_INFO_LEN          (g_processMaxNum * (sizeof(LosProcessCB)))
#define OS_PROCESS_GROUP_INFO_LEN    (g_processMaxNum * sizeof(UINT32))
#define OS_PROCESS_UID_INFO_LEN      (g_processMaxNum * sizeof(UINT32))
#define OS_PROCESS_MEM_ALL_INFO_LEN  (g_processMaxNum * PROCESS_MEMINFO_LEN)
#ifdef LOSCFG_KERNEL_CPUP
#define OS_PROCESS_CPUP_LEN           (g_processMaxNum * sizeof(CPUP_INFO_S))
#define OS_PROCESS_AND_TASK_CPUP_LEN  ((g_processMaxNum + g_taskMaxNum) * sizeof(CPUP_INFO_S))
#define OS_PROCESS_CPUP_ALLINFO_LEN   (OS_PROCESS_AND_TASK_CPUP_LEN * 3)
STATIC VOID TaskCpupInfoBaseGet(UINTPTR base, const CPUP_INFO_S **, const CPUP_INFO_S **, const CPUP_INFO_S **);
STATIC VOID ProcessCpupInfoBaseGet(UINTPTR base, const CPUP_INFO_S **, const CPUP_INFO_S **, const CPUP_INFO_S **);
#else
#define OS_PROCESS_CPUP_ALLINFO_LEN 0
#endif
#define OS_PROCESS_ALL_INFO_LEN (g_processMaxNum * (sizeof(LosProcessCB) + sizeof(UINT32)) + \
    OS_PROCESS_CPUP_ALLINFO_LEN + OS_PROCESS_UID_INFO_LEN)

#define OS_INVALID_SEM_ID         0xFFFFFFFF
#define OS_TASK_WATER_LINE_SIZE   (g_taskMaxNum * sizeof(UINT32))
#define OS_TASK_INFO_LEN          (g_taskMaxNum * sizeof(LosTaskCB))
#define OS_TASK_SCHED_INFO_LEN    (g_taskMaxNum * sizeof(SchedParam))
#define OS_TASK_ALL_INFO_LEN      (g_taskMaxNum * (sizeof(LosTaskCB) + sizeof(UINT32) + sizeof(SchedParam)))

#undef SHOW
#ifdef LOSCFG_FS_VFS
#if defined(LOSCFG_BLACKBOX) && defined(LOSCFG_SAVE_EXCINFO)
#define SaveExcInfo(arg, ...) WriteExcInfoToBuf(arg, ##__VA_ARGS__)
#else
#define SaveExcInfo(arg, ...)
#endif
#define SHOW(arg...) do {                                    \
    if (seqBuf != NULL) {                                    \
        (void)LosBufPrintf((struct SeqBuf *)seqBuf, ##arg);  \
    } else {                                                 \
        PRINTK(arg);                                         \
    }                                                        \
    SaveExcInfo(arg);                                        \
} while (0)
#else
#define SHOW(arg...) PRINTK(arg)
#endif

#define VM_INDEX  PROCESS_VM_INDEX
#define SM_INDEX  PROCESS_SM_INDEX
#define PM_INDEX  PROCESS_PM_INDEX
#define CPUP_MULT LOS_CPUP_PRECISION_MULT

STATIC UINT8 *ConvertProcessModeToString(UINT16 mode)
{
    if (mode == OS_KERNEL_MODE) {
        return (UINT8 *)"kernel";
    } else if (mode == OS_USER_MODE) {
        return (UINT8 *)"user";
    }

    return (UINT8 *)"ERROR";
}

STATIC UINT8 *ConvertSchedPolicyToString(UINT16 policy)
{
    if (policy == LOS_SCHED_RR) {
        return (UINT8 *)"RR";
    } else if (policy == LOS_SCHED_FIFO) {
        return (UINT8 *)"FIFO";
    } else if (policy == LOS_SCHED_EDF) {
        return (UINT8 *)"EDF";
    } else if (policy == LOS_SCHED_IDLE) {
        return (UINT8 *)"IDLE";
    }

    return (UINT8 *)"ERROR";
}

STATIC UINT8 *ConvertProcessStatusToString(UINT16 status)
{
    if (status & OS_PROCESS_STATUS_ZOMBIES) {
        return (UINT8 *)"Zombies";
    } else if (status & OS_PROCESS_STATUS_INIT) {
        return (UINT8 *)"Init";
    } else if (status & OS_PROCESS_STATUS_RUNNING) {
        return (UINT8 *)"Running";
    } else if (status & OS_PROCESS_STATUS_READY) {
        return (UINT8 *)"Ready";
    }
    return (UINT8 *)"Pending";
}

STATIC VOID ProcessInfoTitle(VOID *seqBuf, UINT16 flag)
{
    SHOW("\r\n  PID  PPID PGID   UID   Mode  Status Policy Priority MTID TTotal");
    if (flag & OS_PROCESS_INFO_ALL) {
        if (flag & OS_PROCESS_MEM_INFO) {
            SHOW(" VirtualMem ShareMem PhysicalMem");
        }
#ifdef LOSCFG_KERNEL_CPUP
        SHOW(" CPUUSE CPUUSE10s CPUUSE1s");
#endif /* LOSCFG_KERNEL_CPUP */
    } else {
#ifdef LOSCFG_KERNEL_CPUP
        SHOW(" CPUUSE10s");
#endif /* LOSCFG_KERNEL_CPUP */
    }
    SHOW(" PName\n");
}

STATIC VOID AllProcessDataShow(const LosProcessCB *pcbArray, const SchedParam *param,
                               UINTPTR cpupInfo, VOID *seqBuf, UINT16 flag)
{
    const INT32 *group = (const INT32 *)((UINTPTR)pcbArray + OS_PROCESS_INFO_LEN);
    const INT32 *user = (const INT32 *)((UINTPTR)group + OS_PROCESS_GROUP_INFO_LEN);
    const UINT32 *memArray = (const UINT32 *)((UINTPTR)pcbArray + OS_PROCESS_ALL_INFO_LEN);
#ifdef LOSCFG_KERNEL_CPUP
    const CPUP_INFO_S *cpupAll = NULL;
    const CPUP_INFO_S *cpup10s = NULL;
    const CPUP_INFO_S *cpup1s = NULL;
    ProcessCpupInfoBaseGet(cpupInfo, &cpupAll, &cpup10s, &cpup1s);
#else
    (VOID)cpupInfo;
#endif

    for (UINT32 pid = 1; pid < g_processMaxNum; ++pid) {
        const LosProcessCB *processCB = pcbArray + pid;
        if (OsProcessIsUnused(processCB)) {
            continue;
        }
#ifdef LOSCFG_PID_CONTAINER
        UINT32 level = GetCurrPidLevel();
        if (level > PID_CONTAINER_ROOT_LEVEL){
            UINT32 ret = IsInSamePidContainer((LosProcessCB *)processCB);
            if (ret != LOS_OK) {
                continue;
            }
            LosProcessCB *parentProcessCB = OS_PCB_FROM_PID(processCB->parentProcessID);
            SHOW("%5u%6d%5d%6d%7s%8s%7s%9u%5u%7u", processCB->vProcessID.numbers[level].number, parentProcessCB->vProcessID.numbers[level].number, group[pid], user[pid],
                 ConvertProcessModeToString(processCB->processMode), ConvertProcessStatusToString(processCB->processStatus),
                 ConvertSchedPolicyToString(LOS_SCHED_RR), param[processCB->threadGroupID].basePrio,
                 processCB->threadGroupID, processCB->threadNumber);
        }
        else {
            SHOW("%5u%6d%5d%6d%7s%8s%7s%9u%5u%7u", pid, (INT32)processCB->parentProcessID, group[pid], user[pid],
                ConvertProcessModeToString(processCB->processMode), ConvertProcessStatusToString(processCB->processStatus),
                ConvertSchedPolicyToString(LOS_SCHED_RR), param[processCB->threadGroupID].basePrio,
                processCB->threadGroupID, processCB->threadNumber);

        }
#else
        SHOW("%5u%6d%5d%6d%7s%8s%7s%9u%5u%7u", pid, (INT32)processCB->parentProcessID, group[pid], user[pid],
             ConvertProcessModeToString(processCB->processMode), ConvertProcessStatusToString(processCB->processStatus),
             ConvertSchedPolicyToString(LOS_SCHED_RR), param[processCB->threadGroupID].basePrio,
             processCB->threadGroupID, processCB->threadNumber);
#endif

        if (flag & OS_PROCESS_INFO_ALL) {
            if (flag & OS_PROCESS_MEM_INFO) {
                const UINT32 *memUsage = &memArray[pid * PROCESS_VM_INDEX_MAX];
                SHOW("%#11x%#9x%#12x", memUsage[VM_INDEX], memUsage[SM_INDEX], memUsage[PM_INDEX]);
            }
#ifdef LOSCFG_KERNEL_CPUP
            SHOW("%4u.%-2u%7u.%-2u%6u.%-2u ", cpupAll[pid].usage / CPUP_MULT, cpupAll[pid].usage % CPUP_MULT,
                 cpup10s[pid].usage / CPUP_MULT, cpup10s[pid].usage % CPUP_MULT,
                 cpup1s[pid].usage / CPUP_MULT, cpup1s[pid].usage % CPUP_MULT);
#endif /* LOSCFG_KERNEL_CPUP */
        } else {
#ifdef LOSCFG_KERNEL_CPUP
            SHOW("%7u.%-2u ", cpup10s[pid].usage / CPUP_MULT, cpup10s[pid].usage % CPUP_MULT);
#endif /* LOSCFG_KERNEL_CPUP */
        }
        SHOW("%-32s\n", processCB->processName);
    }
}

#ifdef LOSCFG_KERNEL_VM
STATIC VOID ProcessMemUsageGet(LosProcessCB *pcbArray)
{
    UINT32 intSave, memUsed;
    UINT32 *memArray = (UINT32 *)((UINTPTR)pcbArray + OS_PROCESS_ALL_INFO_LEN);

    for (UINT32 pid = 0; pid < g_processMaxNum; ++pid) {
        const LosProcessCB *processCB = g_processCBArray + pid;
        UINT32 *proMemUsage = &memArray[pid * PROCESS_VM_INDEX_MAX];

        SCHEDULER_LOCK(intSave);
        if (OsProcessIsUnused(processCB)) {
            SCHEDULER_UNLOCK(intSave);
            pcbArray[pid].processStatus = OS_PROCESS_FLAG_UNUSED;
            continue;
        }

        LosVmSpace *vmSpace  = processCB->vmSpace;
        SCHEDULER_UNLOCK(intSave);

        /* Process memory usage statistics, idle task defaults to 0 */
        if (pid == OsGetIdleProcessID()) {
            proMemUsage[VM_INDEX] = 0;
            proMemUsage[SM_INDEX] = 0;
            proMemUsage[PM_INDEX] = 0;
        } else if (vmSpace == LOS_GetKVmSpace()) {
            (VOID)OsShellCmdProcessPmUsage(vmSpace, &proMemUsage[SM_INDEX], &proMemUsage[PM_INDEX]);
            proMemUsage[VM_INDEX] = proMemUsage[PM_INDEX];
        } else {
            memUsed = OsShellCmdProcessVmUsage(vmSpace);
            if (memUsed == 0) {
                pcbArray[pid].processStatus = OS_PROCESS_FLAG_UNUSED;
                continue;
            }
            proMemUsage[VM_INDEX] = memUsed;
            memUsed = OsShellCmdProcessPmUsage(vmSpace, &proMemUsage[SM_INDEX], &proMemUsage[PM_INDEX]);
            if (memUsed == 0) {
                pcbArray[pid].processStatus = OS_PROCESS_FLAG_UNUSED;
            }
        }
    }
}
#endif

#define OS_TASK_STATUS_MASK 0x00FF
STATIC VOID ProcessInfoGet(LosProcessCB **pcbArray, LosTaskCB **tcbArray, SchedParam **schedParam)
{
    INT32 *group = (INT32 *)((UINTPTR)*pcbArray + OS_PROCESS_INFO_LEN);
    INT32 *user = (INT32 *)((UINTPTR)group + OS_PROCESS_GROUP_INFO_LEN);
    SchedParam *param = (SchedParam *)((UINTPTR)*tcbArray + OS_TASK_INFO_LEN);
    *schedParam = param;

    for (UINT32 tid = 0; tid < g_taskMaxNum; tid++) {
        const LosTaskCB *taskCB = *tcbArray + tid;
        if (OsTaskIsUnused(taskCB)) {
            continue;
        }

        LosProcessCB *processCB = *pcbArray + taskCB->processID;
        if (!OsProcessIsDead(processCB) && !OsProcessIsInit(processCB)) {
            processCB->processStatus |= (taskCB->taskStatus & OS_TASK_STATUS_MASK);
        }
        taskCB->ops->schedParamGet(taskCB, &param[tid]);
    }

    for (UINT32 pid = 0; pid < g_processMaxNum; ++pid) {
        const LosProcessCB *processCB = *pcbArray + pid;
        if (OsProcessIsUnused(processCB)) {
            continue;
        }

        if (processCB->group != NULL) {
            group[processCB->processID] = processCB->group->groupID;
        } else {
            group[processCB->processID] = -1;
        }

#ifdef LOSCFG_SECURITY_CAPABILITY
        if (processCB->user != NULL) {
            user[processCB->processID] = processCB->user->userID;
        } else {
            user[processCB->processID] = -1;
        }
#else
        user[processCB->processID] = 0;
#endif
    }
}

STATIC VOID ProcessInfoShow(const LosProcessCB *pcbArray, const SchedParam *param,
                            UINTPTR cpupInfo, VOID *seqBuf, UINT16 flag)
{
#ifdef LOSCFG_KERNEL_CPUP
    const CPUP_INFO_S *cpupAll = NULL;
    const CPUP_INFO_S *cpup10s = NULL;
    const CPUP_INFO_S *cpup1s = NULL;
    ProcessCpupInfoBaseGet(cpupInfo, &cpupAll, &cpup10s, &cpup1s);
    UINT32 pid = OsGetIdleProcessID();
    UINT32 sysUsage = LOS_CPUP_PRECISION - cpupAll[pid].usage;

    SHOW("\n  allCpu(%%): %4u.%02u sys, %4u.%02u idle\n", sysUsage / CPUP_MULT, sysUsage % CPUP_MULT,
         cpupAll[pid].usage / CPUP_MULT, cpupAll[pid].usage % CPUP_MULT);
#endif

    ProcessInfoTitle(seqBuf, flag);
    AllProcessDataShow(pcbArray, param, cpupInfo, seqBuf, flag);
}

UINT8 *ConvertTaskStatusToString(UINT16 taskStatus)
{
    if (taskStatus & OS_TASK_STATUS_INIT) {
        return (UINT8 *)"Init";
    } else if (taskStatus & OS_TASK_STATUS_RUNNING) {
        return (UINT8 *)"Running";
    } else if (taskStatus & OS_TASK_STATUS_READY) {
        return (UINT8 *)"Ready";
    } else if (taskStatus & OS_TASK_STATUS_FROZEN) {
        return (UINT8 *)"Frozen";
    } else if (taskStatus & OS_TASK_STATUS_SUSPENDED) {
        return (UINT8 *)"Suspended";
    } else if (taskStatus & OS_TASK_STATUS_DELAY) {
        return (UINT8 *)"Delay";
    } else if (taskStatus & OS_TASK_STATUS_PEND_TIME) {
        return (UINT8 *)"PendTime";
    } else if (taskStatus & OS_TASK_STATUS_PENDING) {
        return (UINT8 *)"Pending";
    } else if (taskStatus & OS_TASK_STATUS_EXIT) {
        return (UINT8 *)"Exit";
    }

    return (UINT8 *)"Invalid";
}

STATIC VOID TaskWaterLineGet(UINTPTR waterLineBase, LosTaskCB *tcbArray)
{
    UINT32 intSave;
    UINT32 *taskWaterLine = (UINT32 *)waterLineBase;

    for (UINT32 tid = 0; tid < g_taskMaxNum; ++tid) {
        const LosTaskCB *taskCB = g_taskCBArray + tid;
        SCHEDULER_LOCK(intSave);
        if (OsTaskIsUnused(taskCB)) {
            SCHEDULER_UNLOCK(intSave);
            tcbArray[tid].taskStatus = OS_TASK_STATUS_UNUSED;
            continue;
        }

        (VOID)OsStackWaterLineGet((const UINTPTR *)((UINTPTR)taskCB->topOfStack + taskCB->stackSize),
                                  (const UINTPTR *)taskCB->topOfStack, &taskWaterLine[taskCB->taskID]);
        SCHEDULER_UNLOCK(intSave);
    }
}

#ifdef LOSCFG_SHELL_CMD_DEBUG
#define OS_PEND_REASON_MAX_LEN 20

STATIC CHAR *CheckTaskWaitFlag(const LosTaskCB *taskCB, UINTPTR *lockID)
{
    *lockID = taskCB->waitID;
    switch (taskCB->waitFlag) {
        case OS_TASK_WAIT_PROCESS:
            return "Child";
        case OS_TASK_WAIT_GID:
            return "PGroup";
        case OS_TASK_WAIT_ANYPROCESS:
            return "AnyChild";
        case OS_TASK_WAIT_SEM:
            return "Semaphore";
        case OS_TASK_WAIT_QUEUE:
            return "Queue";
        case OS_TASK_WAIT_JOIN:
            return "Join";
        case OS_TASK_WAIT_SIGNAL:
            return "Signal";
        case OS_TASK_WAIT_LITEIPC:
            return "LiteIPC";
        case OS_TASK_WAIT_MUTEX:
            return "Mutex";
        case OS_TASK_WAIT_EVENT:
            return "Event";
        case OS_TASK_WAIT_FUTEX:
            return "Futex";
        case OS_TASK_WAIT_COMPLETE:
            return "Complete";
        default:
            break;
    }

    return NULL;
}

STATIC VOID TaskPendingReasonInfoGet(const LosTaskCB *taskCB, CHAR *pendReason, UINT32 maxLen, UINTPTR *lockID)
{
    CHAR *reason = NULL;

    if (!(taskCB->taskStatus & OS_TASK_STATUS_PENDING)) {
        reason = (CHAR *)ConvertTaskStatusToString(taskCB->taskStatus);
        goto EXIT;
    }

    reason = CheckTaskWaitFlag(taskCB, lockID);
    if (reason == NULL) {
        reason = "Others";
    }

    if (taskCB->taskMux != NULL) {
        *lockID = (UINTPTR)taskCB->taskMux;
        LosTaskCB *owner = ((LosMux *)taskCB->taskMux)->owner;
        if (owner != NULL) {
            if (snprintf_s(pendReason, maxLen, maxLen - 1, "Mutex-%u", owner->taskID) == EOK) {
                return;
            }
        }
    }

EXIT:
    if (strcpy_s(pendReason, maxLen, reason) != EOK) {
        PRINT_ERR("Get pend reason copy failed !\n");
    }
}
#endif

STATIC VOID TaskInfoTitle(VOID *seqBuf, UINT16 flag)
{
    SHOW("\r\n  TID  PID");
#ifdef LOSCFG_KERNEL_SMP
    SHOW(" Affi CPU");
#endif
    SHOW("    Status Policy Priority StackSize WaterLine");
    if (flag & OS_PROCESS_INFO_ALL) {
#ifdef LOSCFG_KERNEL_CPUP
        SHOW(" CPUUSE CPUUSE10s CPUUSE1s");
#endif /* LOSCFG_KERNEL_CPUP */
#ifdef LOSCFG_SHELL_CMD_DEBUG
        SHOW("   StackPoint  TopOfStack PendReason     LockID");
#endif
    } else {
#ifdef LOSCFG_KERNEL_CPUP
        SHOW("  CPUUSE10s ");
#endif /* LOSCFG_KERNEL_CPUP */
    }
    SHOW("  TaskName\n");
}

STATIC VOID AllTaskInfoDataShow(const LosTaskCB *allTaskArray, UINTPTR cpupInfo, VOID *seqBuf, UINT16 flag)
{
    const SchedParam *param = (const SchedParam *)((UINTPTR)allTaskArray + OS_TASK_INFO_LEN);
    const UINT32 *waterLine = (const UINT32 *)((UINTPTR)allTaskArray + OS_TASK_INFO_LEN + OS_TASK_SCHED_INFO_LEN);
#ifdef LOSCFG_KERNEL_CPUP
    const CPUP_INFO_S *cpupAll = NULL;
    const CPUP_INFO_S *cpup10s = NULL;
    const CPUP_INFO_S *cpup1s = NULL;
    TaskCpupInfoBaseGet(cpupInfo, &cpupAll, &cpup10s, &cpup1s);
#else
    (VOID)cpupInfo;
#endif
    for (UINT32 pid = 1; pid < g_processMaxNum; ++pid) {
        for (UINT32 tid = 0; tid < g_taskMaxNum; ++tid) {
            const LosTaskCB *taskCB = allTaskArray + tid;
            if (OsTaskIsUnused(taskCB) || (taskCB->processID != pid)) {
                continue;
            }
#ifdef LOSCFG_SHELL_CMD_DEBUG
            UINTPTR lockID = 0;
            CHAR pendReason[OS_PEND_REASON_MAX_LEN] = { 0 };
#endif
#ifdef LOSCFG_PID_CONTAINER
            UINT32 level = GetCurrPidLevel();
            if (level > PID_CONTAINER_ROOT_LEVEL){
                LosProcessCB *processCB = OS_PCB_FROM_PID(taskCB->processID);
                UINT32 ret = IsInSamePidContainer(processCB);
                if (ret != LOS_OK) {
                    continue;
                }
                SHOW(" %4u%5u", tid, processCB->vProcessID.numbers[level].number);
            }
            else {
                SHOW(" %4u%5u", tid, taskCB->processID);
            }
#else
            SHOW(" %4u%5u", tid, taskCB->processID);
#endif
#ifdef LOSCFG_KERNEL_SMP
            SHOW("%#5x%4d ", taskCB->cpuAffiMask, (INT16)(taskCB->currCpu));
#endif
            SHOW("%9s%7s%9u%#10x%#10x", ConvertTaskStatusToString(taskCB->taskStatus),
                 ConvertSchedPolicyToString(param[tid].policy), param[tid].priority, taskCB->stackSize, waterLine[tid]);
            if (flag & OS_PROCESS_INFO_ALL) {
#ifdef LOSCFG_KERNEL_CPUP
                SHOW("%4u.%-2u%7u.%-2u%6u.%-2u ", cpupAll[tid].usage / CPUP_MULT, cpupAll[tid].usage % CPUP_MULT,
                     cpup10s[tid].usage / CPUP_MULT, cpup10s[tid].usage % CPUP_MULT,
                     cpup1s[tid].usage / CPUP_MULT, cpup1s[tid].usage % CPUP_MULT);
#endif /* LOSCFG_KERNEL_CPUP */
#ifdef LOSCFG_SHELL_CMD_DEBUG
                TaskPendingReasonInfoGet(taskCB, pendReason, OS_PEND_REASON_MAX_LEN, &lockID);
                SHOW("%#12x%#12x%11s%#11x", taskCB->stackPointer, taskCB->topOfStack, pendReason, lockID);
#endif
            } else {
#ifdef LOSCFG_KERNEL_CPUP
                SHOW("%8u.%-2u ", cpup10s[tid].usage / CPUP_MULT, cpup10s[tid].usage % CPUP_MULT);
#endif /* LOSCFG_KERNEL_CPUP */
            }
            SHOW("  %-32s\n", taskCB->taskName);
        }
    }
}

STATIC VOID TaskInfoData(const LosTaskCB *allTaskArray, UINTPTR cpupInfo, VOID *seqBuf, UINT16 flag)
{
    TaskInfoTitle(seqBuf, flag);
    AllTaskInfoDataShow(allTaskArray, cpupInfo, seqBuf, flag);
}

#ifdef LOSCFG_KERNEL_CPUP
STATIC VOID TaskCpupInfoBaseGet(UINTPTR base, const CPUP_INFO_S **cpupAll,
                                const CPUP_INFO_S **cpup10s, const CPUP_INFO_S **cpup1s)
{
    UINTPTR processCpupAll = base + OS_PROCESS_UID_INFO_LEN;
    *cpupAll = (CPUP_INFO_S *)(processCpupAll + OS_PROCESS_CPUP_LEN);
    UINTPTR processCpup10s = processCpupAll + OS_PROCESS_AND_TASK_CPUP_LEN;
    *cpup10s = (CPUP_INFO_S *)(processCpup10s + OS_PROCESS_CPUP_LEN);
    UINTPTR processCpup1s = processCpup10s + OS_PROCESS_AND_TASK_CPUP_LEN;
    *cpup1s = (CPUP_INFO_S *)(processCpup1s + OS_PROCESS_CPUP_LEN);
}

STATIC VOID ProcessCpupInfoBaseGet(UINTPTR base, const CPUP_INFO_S **cpupAll,
                                   const CPUP_INFO_S **cpup10s, const CPUP_INFO_S **cpup1s)
{
    *cpupAll = (CPUP_INFO_S *)(base + OS_PROCESS_UID_INFO_LEN);
    *cpup10s = (CPUP_INFO_S *)((UINTPTR)*cpupAll + OS_PROCESS_AND_TASK_CPUP_LEN);
    *cpup1s = (CPUP_INFO_S *)((UINTPTR)*cpup10s + OS_PROCESS_AND_TASK_CPUP_LEN);
}

STATIC VOID TaskCpupInfoGet(UINTPTR base)
{
    UINT32 intSave;

    CPUP_INFO_S *processCpupAll = (CPUP_INFO_S *)(base + OS_PROCESS_UID_INFO_LEN);
    CPUP_INFO_S *processCpup10s = (CPUP_INFO_S *)((UINTPTR)processCpupAll + OS_PROCESS_AND_TASK_CPUP_LEN);
    CPUP_INFO_S *processCpup1s = (CPUP_INFO_S *)((UINTPTR)processCpup10s + OS_PROCESS_AND_TASK_CPUP_LEN);

    SCHEDULER_LOCK(intSave);
    (VOID)OsGetAllProcessAndTaskCpuUsageUnsafe(CPUP_ALL_TIME, processCpupAll, OS_PROCESS_AND_TASK_CPUP_LEN);
    SCHEDULER_UNLOCK(intSave);

    SCHEDULER_LOCK(intSave);
    (VOID)OsGetAllProcessAndTaskCpuUsageUnsafe(CPUP_LAST_TEN_SECONDS, processCpup10s, OS_PROCESS_AND_TASK_CPUP_LEN);
    SCHEDULER_UNLOCK(intSave);

    SCHEDULER_LOCK(intSave);
    (VOID)OsGetAllProcessAndTaskCpuUsageUnsafe(CPUP_LAST_ONE_SECONDS, processCpup1s, OS_PROCESS_AND_TASK_CPUP_LEN);
    SCHEDULER_UNLOCK(intSave);
}
#endif

/*
 * | pcb | group | user | task and process cpup | process mem | tcb | sched param | task water line |
 */
STATIC VOID ProcessAndTaskInfoGet(LosProcessCB **pcbArray, LosTaskCB **tcbArray,
                                  SchedParam **schedParam, UINTPTR *cpupInfo, UINT16 flag)
{
    UINT32 intSave;
    UINT32 processInfoLen = OS_PROCESS_ALL_INFO_LEN;

#ifdef LOSCFG_KERNEL_VM
    if (flag & OS_PROCESS_MEM_INFO) {
        processInfoLen += OS_PROCESS_MEM_ALL_INFO_LEN;
    }
#endif

    SCHEDULER_LOCK(intSave);
    (VOID)memcpy_s(*pcbArray, OS_PROCESS_INFO_LEN, g_processCBArray, OS_PROCESS_INFO_LEN);
    *tcbArray = (LosTaskCB *)((UINTPTR)*pcbArray + processInfoLen);
    (VOID)memcpy_s(*tcbArray, OS_TASK_INFO_LEN, g_taskCBArray, OS_TASK_INFO_LEN);

    ProcessInfoGet(pcbArray, tcbArray, schedParam);
    SCHEDULER_UNLOCK(intSave);

#ifdef LOSCFG_KERNEL_CPUP
    *cpupInfo = (UINTPTR)*pcbArray + OS_PROCESS_INFO_LEN + OS_PROCESS_GROUP_INFO_LEN;
    TaskCpupInfoGet(*cpupInfo);
#endif

#ifdef LOSCFG_KERNEL_VM
    if (flag & OS_PROCESS_MEM_INFO) {
        ProcessMemUsageGet(*pcbArray);
    }
#endif

    TaskWaterLineGet((UINTPTR)*tcbArray + OS_TASK_INFO_LEN + OS_TASK_SCHED_INFO_LEN, *tcbArray);
}

LITE_OS_SEC_TEXT_MINOR UINT32 OsShellCmdTskInfoGet(UINT32 taskID, VOID *seqBuf, UINT16 flag)
{
    UINT32 size;
    LosProcessCB *pcbArray = NULL;
    LosTaskCB *tcbArray = NULL;
    SchedParam *schedParam = NULL;
    UINTPTR cpupInfo = 0;

    if (taskID == OS_ALL_TASK_MASK) {
        if (flag & OS_PROCESS_MEM_INFO) {
            size = OS_PROCESS_ALL_INFO_LEN + OS_PROCESS_MEM_ALL_INFO_LEN + OS_TASK_ALL_INFO_LEN;
        } else {
            size = OS_PROCESS_ALL_INFO_LEN + OS_TASK_ALL_INFO_LEN;
        }
        pcbArray = (LosProcessCB *)LOS_MemAlloc(m_aucSysMem1, size);
        if (pcbArray == NULL) {
            PRINT_ERR("Memory is not enough to save task info!\n");
            return LOS_NOK;
        }
        (VOID)memset_s(pcbArray, size, 0, size);
        ProcessAndTaskInfoGet(&pcbArray, &tcbArray, &schedParam, &cpupInfo, flag);
        ProcessInfoShow(pcbArray, schedParam, cpupInfo, seqBuf, flag);
        TaskInfoData(tcbArray, cpupInfo, seqBuf, flag);

        (VOID)LOS_MemFree(m_aucSysMem1, pcbArray);
    }

    return LOS_OK;
}

LITE_OS_SEC_TEXT_MINOR UINT32 OsShellCmdDumpTask(INT32 argc, const CHAR **argv)
{
    UINT32 flag = 0;
#ifdef LOSCFG_KERNEL_VM
    flag |= OS_PROCESS_MEM_INFO;
#endif

    if (argc >= 2) { /* 2: The task shell name restricts the parameters */
        goto TASK_HELP;
    }

    if (argc == 1) {
        if (strcmp("-a", argv[0]) == 0) {
            flag |= OS_PROCESS_INFO_ALL;
#ifdef LOSCFG_SCHED_DEBUG
#ifdef LOSCFG_SCHED_TICK_DEBUG
        } else if (strcmp("-i", argv[0]) == 0) {
            if (!OsShellShowTickResponse()) {
                return LOS_OK;
            }
            goto TASK_HELP;
#endif
        } else if (strcmp("-t", argv[0]) == 0) {
            if (!OsShellShowSchedStatistics()) {
                return LOS_OK;
            }
            goto TASK_HELP;
#endif
        } else {
            goto TASK_HELP;
        }
    }

    return OsShellCmdTskInfoGet(OS_ALL_TASK_MASK, NULL, flag);

TASK_HELP:
    PRINTK("Unknown option: %s\n", argv[0]);
    PRINTK("Usage:\n");
    PRINTK(" task    --- Basic information about all created processes.\n");
    PRINTK(" task -a --- Complete information about all created processes.\n");
    return LOS_NOK;
}

#ifdef LOSCFG_SHELL
SHELLCMD_ENTRY(task_shellcmd, CMD_TYPE_EX, "task", 1, (CmdCallBackFunc)OsShellCmdDumpTask);
#endif

