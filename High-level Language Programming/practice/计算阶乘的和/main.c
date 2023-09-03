#include <stdio.h>
#include <stdlib.h>
int Judge(int n);
long Fact(int n);
int main()
{
    int sum=0;
    for(int i=100;i<=999;i++){
        if(Judge(i)==1){
            sum+=i;
        }
    }
    printf("%d\n",sum);
    return 0;
}
int Judge(int n)//判断是否n为三位阶乘和数
{
    int a,b,c;
    a=n/100;
    b=(n/10)%10;
    c=n%10;
    if(n==Fact(a)+Fact(b)+Fact(c))
    {
        return 1;
    }else{
        return -1;
    }

}
long Fact(int n)//计算阶乘
{
    long int result=1;
    if(n==0)
    {
        return 1;
    }else
    {
        for(int i=1;i<=n;i++)
        {
            result*=i;
        }
    return result;
    }
}
