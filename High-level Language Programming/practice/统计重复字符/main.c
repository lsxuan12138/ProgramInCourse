#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#define N 80
int CountRepeatStr(char str[], int *tag);


int main()
{
    printf("Input a string:\n");
    char str[N];
    gets(str);
    int ret,tag,*ptag;
    ptag=&tag;
    ret=CountRepeatStr(str,ptag);
    printf("%c:%d\n",str[tag],ret);
    return 0;
}
//�������ܣ�ͳ���ַ����������ظ����������ַ������ظ��Ĵ���

//����������strָ���ͳ�Ƶ��ַ�����ָ���β�tag�����ظ��ַ������ֵ��±�λ��

//��������ֵ�������ַ��ظ��Ĵ���

int CountRepeatStr(char str[], int *tag)
{
    int max=0,count=0;
    for(int i=0;i<strlen(str);i++){
        if(str[i]==str[i+1]){
            count++;
        }
        else{
            count=0;
        }
        if(count>max){
            *tag=str[i+1];
        }
    }
    return max;
}
