#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#define N 10
void DealStr(char *str,int n);

int main()
{
    char str[N];
    gets(str);
    DealStr(str,N);
    printf("%s",str);
    return 0;
}
void DealStr(char *str,int n)
{
    char *ptr=str,*temp=str,temp1;
    for(int i=0;i<n;i++)
    {
        if(*str>*temp){
            temp=str;
            temp1= *str;
        }
        str++;
    }

    for(ptr=temp;ptr>str;ptr--)
    {
        *ptr=*(ptr-1);
    }
    *str=temp1;
}
