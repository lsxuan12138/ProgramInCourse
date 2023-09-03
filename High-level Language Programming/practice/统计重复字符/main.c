#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#define N 80
int CountRepeatStr(char str[], int *tag);


int main()
{
    printf("Input a string:\n");
    char str[N];
    gets(str);
    int ret,tag,*ptag;
    ptag=&tag;
    ret=CountRepeatStr(str,ptag);
    printf("%c:%d\n",str[tag],ret);
    return 0;
}
//函数功能：统计字符串中连续重复次数最多的字符及其重复的次数

//函数参数：str指向待统计的字符串，指针形参tag返回重复字符最后出现的下标位置

//函数返回值：返回字符重复的次数

int CountRepeatStr(char str[], int *tag)
{
    int max=0,count=0;
    for(int i=0;i<strlen(str);i++){
        if(str[i]==str[i+1]){
            count++;
        }
        else{
            count=0;
        }
        if(count>max){
            *tag=str[i+1];
        }
    }
    return max;
}
