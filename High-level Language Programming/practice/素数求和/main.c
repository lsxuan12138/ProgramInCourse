#include <stdio.h>
#include <stdlib.h>
int IsPrime(int x);

int main()
{
    printf("Input n:");
    int n;
    scanf("%d",&n);
    int sum=0;
    for(int i=1;i<=n;i++){
        if(IsPrime(i)==1){
            sum+=i;
        }
    }
    printf("sum=%d\n",sum);
    return 0;
}
//函数功能：判断x是否是素数，若函数返回0，则表示不是素数，若返回1，则代表是素数
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
