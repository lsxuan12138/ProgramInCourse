#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
int IsNumIn(char word[]);
int IsSpcIn(char word[]);
void Trim(char oldWord[], char newWord[]);
void Seg(char words[], char wArray[][100] );
int main()
{
    char str[80];
    char newstr[80];
    char newstr2[4][100];
    gets(str);
    if(IsNumIn(str)){
        printf("error");
        exit(0);
    }
    else{
        Trim(str,newstr);
        if(!IsSpcIn(newstr)){
            printf("%s",newstr);
        }
        else{
            Seg(newstr,newstr2);
            for(int i=0;i<4;i++){
                printf("%s",newstr2[i]);
            }
        }
    }
    return 0;
}
//�������Ƿ��������
int IsNumIn(char word[])
{
    for(int i=0;i<strlen(word);i++){
        if(isalpha(word[i])){
            return 1;
        }
    }
    return 0;
}
//�������Ƿ�����ո�
int IsSpcIn(char word[])
{
    for(int i=0;i<strlen(word);i++){
        if(isspace(word[i])){
            return 1;
        }
    }
    return 0;
}
//ȥ�����ʵ�ǰ��ո�tab���ͻ��з�
void Trim(char oldWord[], char newWord[])
{
    int i=0,j=0;
    if(isspace(oldWord[i])){
        i++;
    }
    for(;!(isspace(oldWord[i])&&isspace(oldWord[i+1]));i++){
        newWord[j++]=oldWord[i];
    }
    newWord[j]='\0';
}
//�����ڲ��пո񣬷ֽ�ɶ������
//���赥���ڲ�ֻ��һ���ո�û�����������ո���������
void Seg(char words[], char wArray[][100] )
{
    int i=0,j=0,k=0;
    for(;i<strlen(words);i++){
        wArray[j][k++]=words[i];
        if(isspace(words[i])){
            wArray[j][k]='\0';
            i++;
            j++;
            k=0;
        }
    }
}
