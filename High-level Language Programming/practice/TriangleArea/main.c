#include <stdio.h>
#include <stdlib.h>
#include <math.h>

int main(void)
{
    float a,b,c,s,area;
    printf("Input a,b,c:");
    scanf("%f %f %f",&a,&b,&c);
    if(a+b<c||a+c<b||b+c<a){
        printf("The data does not form a triangle \n");
        exit(0);
    }
    s=0.5*(a+b+c);
    area =(float)sqrt(s*(s-a)*(s-b)*(s-c));
    printf("area=%.2f\n",area);
    return 0;
}
