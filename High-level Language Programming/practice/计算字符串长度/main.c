#include <stdio.h>
#include <stdlib.h>
#define N 81
int Mystrlen(char str[]);
int main()
{
    printf("Enter a string:");
    char str[N];
    gets(str);
    int len=Mystrlen(str);
    printf("The length of the string is:%d\n",len);
    return 0;
}
int Mystrlen(char str[])
{
    int n=0;
    while(str[n]!='\0'){
        n++;
    }
    return n;
}
