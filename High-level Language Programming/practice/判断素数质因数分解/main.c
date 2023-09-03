#include <stdio.h>
#include <stdlib.h>
#define N 10
int IsPrime(int x);
int main()
{
    printf("Input m:");
    int m;
    scanf("%d",&m);
    if(IsPrime(m)){//判断m是否为素数
        printf("It is a prime number\n");
    }
    else{
        int i,k=m,j=0,num[N]={0};
        for(i=2;i<m/2;i++){
            while(IsPrime(i)&&k%i==0){
                num[j]=i;//将所有因数存入数组
                j++;
                k/=i;
            }
        }
        printf("%d = ",m);
        for(i=0;num[i+1]!=0;i++){//输出因数
            printf("%d * ",num[i]);
        }
        printf("%d",num[i]);//输出最后一个因数
    }
    return 0;
}
//函数功能：判断x是否是素数，若函数返回0，则表示不是素数，
//          若返回1，则代表是素数
int IsPrime(int x)
{
    if(x==1)return 0;
    else{
        for(int i=x/2;i>1;i--){
            if(x%i==0){
                return 0;
            }
        }
    }
    return 1;
}
