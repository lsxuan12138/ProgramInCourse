#ifndef SYSCALL_WHO_C
#define SYSCALL_WHO_C
#include <errno.h>
#include <stddef.h>

//name的长度不能超过23个字符
#define MAX_NAME_LEN 23
static char saved_name[MAX_NAME_LEN + 1];
static size_t saved_name_len = 0;

int SysIam(const char *name)
{
    size_t name_len = 0;
    while (name[name_len] != '\0')
    {
        name_len++;
    }
    //name_len equals to strlen(name), not include '\0'
    if (name_len > MAX_NAME_LEN)
        return -EINVAL;
    //copy chars, include '\0'
    for (size_t i = 0; i <= name_len; i++)
    {
        saved_name[i] = name[i];
    }
    saved_name_len = name_len;

    return name_len;
}
int SysWhoAmI(char *name, unsigned int size)
{
    if (saved_name_len + 1 > size)
        return -EINVAL;
    //copy chars, include '\0'
    for (size_t i = 0; i <= saved_name_len; i++)
    {
        name[i] = saved_name[i];
    }

    return saved_name_len;
}
#endif