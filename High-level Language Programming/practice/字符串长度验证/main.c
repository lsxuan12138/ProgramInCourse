#include <stdio.h>
#include <stdlib.h>
#include <string.h>
int main()
{
    char str[10];
    int len=0;
    do{
        printf("������һ���ַ���(����Ϊ[3..5]���ַ�)��");
        scanf("%s",str);
        len=strlen(str);
    }while(len<3||len>5);
    printf("��������ַ���Ϊ��%s",str);
    return 0;
}
