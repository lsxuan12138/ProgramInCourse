#include <stdio.h>
#include <stdlib.h>
#include <string.h>
int main()
{
    char str[10];
    int len=0;
    do{
        printf("请输入一个字符串(长度为[3..5]个字符)：");
        scanf("%s",str);
        len=strlen(str);
    }while(len<3||len>5);
    printf("你输入的字符串为：%s",str);
    return 0;
}
