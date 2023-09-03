/*形如2^i-1的素数，称为梅森尼数。*/
#include <stdio.h>
#include <stdlib.h>
int IsPrime(double x);
int main()
{
    printf("Input n:\n");
    int n;
    scanf("%d",&n);
    int count=0;
    double x=1;
    for(int i=2;i<=n;i++)
    {
        x=(x+1)*2-1;
        //printf("%f",x);
        if(IsPrime(x)){
            printf("2^%d-1=%.0f\n",i,x);
            count++;
        }
    }
    printf("count=%d\n",count);
    return 0;
}
int IsPrime(double x)
{
    int find=1;
    for(int i=2;i<=x/2;i++)
    {
        if(x/i == (long long)(x/i)){
            find=0;
        }
    }
    return find;
}
