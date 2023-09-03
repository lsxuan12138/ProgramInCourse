#include <stdio.h>
#include <stdlib.h>
int Func(int n);
int main()
{
    printf("Input n:");
    int n;
    scanf("%d",&n);
    if(n<1000||n>1000000){
        printf("Input error!");
    }else{
        if(Func(n)==-1);
        else{
            printf("%d",Func(n));
        }
    }
    return 0;
}
//函数功能：计算n的所有约数中最大的三位数
int Func(int n){
    int i;
    for(i=999;i>=100;i--){
        if(n%i==0){
            return i;
        }
    }
    return -1;

}
