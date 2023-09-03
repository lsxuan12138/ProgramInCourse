#include <stdio.h>
#include <stdlib.h>
#include <math.h>

unsigned int InputNumber(char ch);
int IsPrime(unsigned int n);
int PrimeSum(unsigned int m, unsigned int n);

int main()
{
    int m = 0,n = 0,sum = 0;
    do
    {
        m = InputNumber('m');
        n = InputNumber('n');
    }while(m > n && printf("n must be not smaller than m! Input again!\n"));   //保证m<=n
    sum = PrimeSum(m,n);
    printf("sum of prime numbers:%d",sum);
    return 0;
}
/*实现用户输入一个正整数，并对数的合法性进行检查，如果读入不成功(例如：输入字符串)，
或者数不合法即不是大于1的正整数，则重新输入，直到输入正确为止。*/
unsigned int InputNumber(char ch)
{
    int x,ret;
    do{
        printf("Please input the number %c(>1):",ch);
        ret=scanf("%d",&x);
        if(x<=1||ret!=1){
            printf("The input must be an integer larger than 1!\n");
            while(getchar()!='\n');
        }
    }while(x<=1||ret!=1);
    return x;
}
/*判断自然数x是否为素数，如果x是素数则返回1，不是素数返回0。*/
int IsPrime(unsigned int n)
{
    for(int i=2;i<=sqrt(n);i++)
    {
        if(n%i==0)
        {
            return 0;
        }
    }
    return 1;
}
/*输出m到n之间所有素数并返回它们的和。*/
int PrimeSum(unsigned int m, unsigned int n)
{
    int sum=0;
    for(int i=m;i<=n;i++)
    {
        if(IsPrime(i)){
            printf("%d",i);
            sum+=i;
            printf("\n");
        }
    }
    return sum;
}
