#include <stdio.h>
#include <stdlib.h>

float y1(float x);
float y2(float x);
float fuc (float (*f)(float ),float a,float b);

int main()
{
    printf("y1=%f\ny2=%f\n",fuc(y1,0,1),fuc(y2,0,3));
    return 0;
}
float fuc (float (*f)(float ),float a,float b)
{
    float sum=0;
    float t=(b-a)/100;
    for(float i=a;i<=b-t;i+=t)
    {
        float s=(f(i)+f(i+t))*t/2;
        sum+=s;
    }
    return sum;
}
float y1(float x)
{
    return 1+x*x;
}
float y2 (float x)
{
    return x/(1+x*x);
}
