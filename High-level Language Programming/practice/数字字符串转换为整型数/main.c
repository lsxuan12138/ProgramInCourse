#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>
#include <math.h>
int Myatoi(char str[]);
int main()
{
    printf("Input a string:");
    char str[8];
    scanf("%7s",str);
    //int result=Myatoi(str);
    //printf("%d\n",result);
    return 0;
}
//形参数组str[]对应用户输入的字符串，函数返回值为转换后的整型数。
int Myatoi(char str[])
{
    int num[8]={0};
    int i,j=0;
    for(i=0;i<8;i++){
        if(isdigit(str[i])){
            num[j]=str[i]-48;
            j++;
        }
    }
    int result=0,k=--j;
    for(;j>=0;j--){
        result+=(num[j]*pow(10,k-j));
    }
    return result;
}

