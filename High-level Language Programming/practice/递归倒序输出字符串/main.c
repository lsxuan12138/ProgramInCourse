#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#define N 10
void Print(char s[],char *p);

int main()
{
    printf("input your string:\n");
    char str[N],*p;
    scanf("%s",str);
    p=str;
    Print(str,p);
    return 0;
}
void Print(char s[],char *p)
{
    if(strlen(s)==1){
        printf("%c",s[0]);
        s[0]='\0';
        if(s>p){
            Print(s-1,p);
        }
    }
    else Print(s+1,p);

}
/*
#include <stdio.h>
#include<string.h>
void reverse(char s[])
{
    int len;

    len = strlen(s);
    if (len == 1)
    {
        printf("%c", s[0]);
    }
    else
    {
        reverse(s + 1);
        printf("%c", s[0]);
    }
}

int main()
{
    char s[100];

    printf("input your string:\n");
    scanf("%s", s);
    reverse(s);
}
*/
