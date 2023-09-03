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
    }while(m > n && printf("n must be not smaller than m! Input again!\n"));   //��֤m<=n
    sum = PrimeSum(m,n);
    printf("sum of prime numbers:%d",sum);
    return 0;
}
/*ʵ���û�����һ�����������������ĺϷ��Խ��м�飬������벻�ɹ�(���磺�����ַ���)��
���������Ϸ������Ǵ���1�������������������룬ֱ��������ȷΪֹ��*/
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
/*�ж���Ȼ��x�Ƿ�Ϊ���������x�������򷵻�1��������������0��*/
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
/*���m��n֮�������������������ǵĺ͡�*/
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
