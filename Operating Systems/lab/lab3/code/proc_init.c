/*
 * Copyright (c) 2013-2019 Huawei Technologies Co., Ltd. All rights reserved.
 * Copyright (c) 2020-2021 Huawei Device Co., Ltd. All rights reserved.
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

#include "proc_fs.h"
#include "internal.h"
#ifdef LOSCFG_INOTIFY
#include "fs/inotify.h"
#endif
#include "stdio.h"
#include "sys/mount.h"
#include "sys/stat.h"
#include "los_init.h"

#ifdef LOSCFG_FS_PROC

#define PROCFS_MOUNT_POINT  "/proc"
#define PROCFS_MOUNT_POINT_SIZE (sizeof(PROCFS_MOUNT_POINT) - 1)

extern struct ProcDirEntry g_procRootDirEntry;

void ProcFsInit(void)
{
    int ret;

    ret = mkdir(PROCFS_MOUNT_POINT, PROCFS_DEFAULT_MODE);
    if (ret < 0) {
        PRINT_ERR("failed to mkdir %s, errno = %d\n", PROCFS_MOUNT_POINT, get_errno());
        return;
    }

    ret = mount(NULL, PROCFS_MOUNT_POINT, "procfs", 0, NULL);
    if (ret) {
        PRINT_ERR("mount procfs err %d\n", ret);
        return;
    }

    ProcMountsInit();
#if defined(LOSCFG_SHELL_CMD_DEBUG) && defined(LOSCFG_KERNEL_VM)
    ProcVmmInit();
#endif
    ProcProcessInit();
    ProcTracInit();
    ProcUptimeInit();
    ProcFsCacheInit();
    ProcFdInit();
#ifdef LOSCFG_PROCESS_LIMITS
    ProcLimitsInit();
#endif
#ifdef LOSCFG_KERNEL_PM
    ProcPmInit();
#endif
#ifdef LOSCFG_INOTIFY
    ProcInotifyInit();
#endif
    ProcFsMemInfoInit();
    ProcFileSysInit();

    ProcFsSysInit();
}

LOS_MODULE_INIT(ProcFsInit, LOS_INIT_LEVEL_KMOD_EXTENDED);

#endif
