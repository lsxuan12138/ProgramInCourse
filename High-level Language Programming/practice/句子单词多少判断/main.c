#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>
#include <string.h>

int main()
{
    printf("������һ���ַ���");
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
    printf("�ܹ���%d�����ʣ�ƽ������Ϊ%d\n",numofword,numofalpha/numofword);
    return 0;
}
