#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#define N 101
int main()
{
    char str[N];
    printf("请输入一个字符串，长度小于等于100：");
    gets(str);
    char *ptr=str;
    printf("该字符串的内存编码为： ");
    for(int i=0;i<strlen(str);i++){
        printf("%x ", (unsigned char)str[i] & 0xff);
        ptr++;
    }
    return 0;
}
