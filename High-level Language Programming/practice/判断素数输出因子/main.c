#include <stdio.h>
#include <stdlib.h>
int IsPrime(int x);
int main()
{
    printf("Please enter a number:");
    int x;
    scanf("%d",&x);
    if(x<2){
        printf("It is not a prime number.No divisor!\n");
    }
    else{
        if(IsPrime(x)){
            printf("It is a prime number.No divisor!\n");
        }
        else{
            for(int i=2;i<x;i++){
                if(x%i==0){
                    printf("%d\n",i);
                }
            }
        }
    }

    return 0;
}
//函数功能：判断x是否是素数，若函数返回0，则表示不是素数，若返回1，则代表是素数
int IsPrime(int x)
{
    if(x==1)return 0;
    else{
        for(int i=2;i<=x/2;i++){
            if(x%i==0){
                return 0;
            }
        }
    }
    return 1;
}
