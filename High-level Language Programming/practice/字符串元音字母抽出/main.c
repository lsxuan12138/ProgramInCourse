#include <stdio.h>
#include <stdlib.h>
#include <string.h>
void func (char *str,char *str2);
int func2(char ch);

int main()
{
    printf("\n�����ַ�����");
    char str[40],str2[40];
    gets(str);
    func(str,str2);
    printf("%s",str);
    printf("\n�ַ����е�Ԫ����ĸ��%s",str2);
    return 0;
}
void func (char *str,char *str2)
{
    int j=0;
    for(int i=0;i<strlen(str);i++){
        if(func2(str[i])){
            str2[j++]=str[i];
        }
    }
    str2[j]='\0';
}
int func2(char ch)
{
    if(ch=='a'||ch=='e'||ch=='i'||ch=='o'||ch=='u')return 1;
    return 0;
}
