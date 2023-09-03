#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>
#include <string.h>

int main()
{
    printf("请输入一行字符：");
    char sentence[50];
    gets(sentence);
    //printf("%lu",strlen(sentence));
    int numofword=0,numofalpha=0;
    char *p=sentence;
    while(*p!='\000'){
        while(isspace(*p)){
            p++;
        }
        while(isalpha(*p)){
            p++;
            numofalpha++;
        }
        numofword++;
    }
    printf("总共有%d个单词，平均长度为%d\n",numofword,numofalpha/numofword);
    return 0;
}
