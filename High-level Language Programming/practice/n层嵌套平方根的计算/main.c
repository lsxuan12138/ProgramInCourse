#include <stdio.h>
#include <stdlib.h>
#include <math.h>
double Y(double x, int n);

int main()
{
    printf("Please input x and n:");
    double x;
    int n;
    scanf("%lf,%d",&x,&n);
    printf("Result=%.2f\n",Y(x,n));
    return 0;
}
double Y(double x, int n){
    if(n==1){
        return sqrt(x);
    }else if(n==0){
        return 0;
    }else{
        return sqrt(x+Y(x,n-1));
    }
}
