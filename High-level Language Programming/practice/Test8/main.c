/*#include <stdio.h>
#include <stdlib.h>
#include <math.h>
double sin(double x);
int count=0;
int main()
{

    printf("Input x:\n");
    double x;
    scanf("%lf",&x);
    double result=sin(x);
    printf("sin(x)=%.3f,count=%d\n",result,count);
    return 0;
}
double sin(double x){
    double n=x,sum=0;
    int d=1;
    sum+=n;
    count++;
    do{
    n*=((x*x*(-1))/((d+1)*(d+2)));
    sum+=n;
    count++;
    d+=2;
    }while(fabs(n)>=10e-5);
    return sum;
}
*/
#include <stdio.h>
#include<stdlib.h>
double factorial (double n);
int main()
{
    int i=0;
    int sign=1; //控制for循环的符号
    double x; //输入值
    double nr; //储存分子
    double sin=0; //储存sin的近视值
    double dm; //储存分母
    printf("Input x:\n");
    scanf("%lf",&x);
    nr=x;
    do
    {
        dm=1+2*i;//累加项的分母
        sin=sin+(nr/factorial(dm))*sign;
        nr*=x*x;//累加项的分子
        sign=-sign;//控制正负
        i++;//累加项个数
    }while((nr/factorial(dm))>=1e-5);
    printf("sin(x)=%.3f,count=%d\n",sin,i);
    return 0;
}
//函数功能：计算阶乘
double factorial (double n)
{
    double s=1;
    int i;
    for(i=2;i<=n;i++)
    s*=i;
    return s;
}
