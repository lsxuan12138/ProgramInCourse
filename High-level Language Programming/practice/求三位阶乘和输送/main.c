#include <stdio.h>
#include <stdlib.h>
long Fact(int n);

int main()
{
    int a,b,c;
    for (int i=100;i<=999;i++)
    {
        a=i/100;
        b=(i/10)%10;
        c=i%10;
        if(i==Fact(a)+Fact(b)+Fact(c)){
            printf("%d",i);
        }
    }
    return 0;
}
long Fact(int n)
{
    if(n==0)
    {
        return 1;
    }
    long p=1;
    for(int i=2;i<=n;i++)
    {
        p*=i;
    }
    return p;
}
