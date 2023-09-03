#include <stdio.h>
#include "syscall.h"

void test_syscall(const char *name, char *name_buf, int name_buf_size)
{
    int name_size = strlen(name);
    int copied_size = 0;

    if ((copied_size = iam(name)) != name_size)
    {
        printf("iam() errco: %d was returned, excepted %d\n", copied_size, name_size);
        return;
    }
    if ((copied_size = whoami(name_buf, name_buf_size)) == -1)
    {
        printf("whoami() errco: insufficent buffer size\n");
        return;
    }
    printf("%s\n", name_buf);
}
int main(int argc, char **argv)
{
    printf("\n************************************************\n");
    printf("\n\t\tHello OHOS!\n");
    printf("\n************************************************\n\n");

    printf("-----------------test-------------test-----------------------\n");
    const int name_buf_size = 50;
    char name_buf[name_buf_size];

    test_syscall("lsx", name_buf, name_buf_size);

    test_syscall("12345678901234567890123", name_buf, name_buf_size);
    test_syscall("123456789012345678901234", name_buf, name_buf_size);

    test_syscall("12345678901234567890123", name_buf, 24);
    test_syscall("12345678901234567890123", name_buf, 23);

    return 0;
}
// int main(int argc, char **argv)
// {
//     printf("\n************************************************\n");
//     printf("\n\t\tHello OHOS!\n");
//     printf("\n************************************************\n\n");

//     return 0;
// }