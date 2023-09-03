#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#define N 80
int SearchString(char s[], char d[]);
int main()
{
    char a[N],b[N];
    printf("Input the first string:");
    gets(a);
    printf("Input the second string:");
    gets(b);
    if(SearchString(a,b)){
        printf("Yes\n");
    }
    else{
        printf("No\n");
    }
    return 0;
}
//判断d[]是否被是s[]包含
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
            return 1;
        }
    }
    return 0;
}
