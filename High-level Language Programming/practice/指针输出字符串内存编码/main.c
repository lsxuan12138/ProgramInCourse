#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#define N 101
int main()
{
    char str[N];
    printf("������һ���ַ���������С�ڵ���100��");
    gets(str);
    char *ptr=str;
    printf("���ַ������ڴ����Ϊ�� ");
    for(int i=0;i<strlen(str);i++){
        printf("%x ", (unsigned char)str[i] & 0xff);
        ptr++;
    }
    return 0;
}
