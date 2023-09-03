#include <stdio.h>
#include <stdlib.h>
#define N 81
int main()
{
    char str[N];
    int num[26]={0};
    gets(str);
    for(int i=0;str[i]!='\0';i++){
        if(str[i]>='a'&&str[i]<='z'){
            num[str[i]-'a']++;
        }
    }
    for(int i=0;i<26;i++){
        if(num[i]!=0){
            printf("%c=%d\n",'a'+i,num[i]);
        }
    }
    return 0;
}
