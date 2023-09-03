#include "proc_fs.h"
#include "los_sched_pri.h"
#include "los_task_pri.h"

static int TracProcFill(struct SeqBuf *m, void *v)
{
    (void)v;
    (void)m;

    if (g_schedTracesSize == 0)
    {
        PRINTK("No scheduling trace\n");
        return 0;
    }

    PRINTK("%-20s%-15s%-8s%-8s%-20s\n", "Time", "TaskStatus", "TID", "PID", "Position");

    bool initial = true;
    for (uint32_t i = g_schedTracesSize < LOSCFG_SCHED_TRACE_LIMIT
                          ? 0
                          : g_nextSchedTraceIndex;
         i != g_nextSchedTraceIndex || initial;
         i = (i + 1) % LOSCFG_SCHED_TRACE_LIMIT)
    {
        PRINTK("%-20llu%-15s%-8u%-8u%-20s\n",
               g_schedTraces[i].time,
               ConvertTaskStatusToString(g_schedTraces[i].taskStatus),
               g_schedTraces[i].tid,
               g_schedTraces[i].pid,
               g_schedTraces[i].position);

        initial = false;
    }

    return 0;
}

static const struct ProcFileOperations PROCESS_PROC_FOPS = {
    .read = TracProcFill,
};

void ProcTracInit(void)
{
    struct ProcDirEntry *pde = CreateProcEntry("trac", 0, NULL);
    if (pde == NULL)
    {
        PRINT_ERR("create /proc/trac error!\n");
        return;
    }

    pde->procFileOps = &PROCESS_PROC_FOPS;
}