#include <stdio.h>
#include "syscall.h"

void ProcessFuction(const char* name)
{
    printf("Process %s started\n", name);

    sleep(1);

    printf("Process %s exited\n", name);
}

int main(int argc, char **argv)
{
    puts("Main process started");

    clone(ProcessFuction, 0, 0, "Child 1");
    clone(ProcessFuction, 0, 0, "Child 2");

    sleep(2);

    puts("Main process exited");

    return 0;
}
