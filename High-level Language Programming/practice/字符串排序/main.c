#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#define N 10
void Input(char str[][80],int n);
void Sort(char str[][80],int n);
void Print(char str[][80],int n);
//char *ptr[N];
int main()
{
    char str[N][80];
    //char *ptr[N];
    Input(str,N);
    Sort(str,N);
    Print(str,N);
    return 0;
}
void Input(char str[][80],int n)
{
    for(int i=0;i<n;i++)
    {
        gets(str[i]);
    }
}
void Sort(char str[][80],int n)
{
    char temp[80];
    for(int i=0;i<n-1;i++)
    {
        int k=i;
        for(int j=i+1;j<n;j++)
        {
            if(strcmp(str[k],str[j])>0)k=j;
        }
        if(k!=i)
        {
            strcpy(temp,str[i]);
            strcpy(str[i],str[k]);
            strcpy(str[k],temp);
        }
    }
}
void Print(char str[][80],int n)
{
    for(int i=0;i<n;i++)
    {
        printf("%s\n",str[i]);
    }
}
