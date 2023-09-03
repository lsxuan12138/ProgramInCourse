#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#define N 80
int SearchString(char s[], char d[]);

int main()
{
    printf("Input a string:");
    char s1[N],s2[N];
    gets(s1);
    printf("Input another string:");
    gets(s2);
    int ret;
    //printf("%d",strlen(s2));
    ret=SearchString(s1,s2);
    if(ret==-1){
        printf("Not found!\n");
    }
    else{
        printf("Searching results:%d\n",ret);
    }
    return 0;
}
//函数功能：在字符数组s中查找子串d，返回d在s中首次出现的位置，若找不到，则返回-1。
int SearchString(char s[], char d[])
{
    int i=0;
    for(int j=0;j<strlen(s);j++){
        if(d[i]==s[j]){
            i++;
        }else{
            i=0;
        }
        if(i==strlen(d)){
            return j-i+2;
        }
    }
    return -1;
}
