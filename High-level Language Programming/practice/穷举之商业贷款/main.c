#include <stdio.h>
#include <stdlib.h>
#include <math.h>
int main()
{
    printf("����������:loan\n");
    int loan;
    scanf("%d",&loan);
    printf("������������:rate\n");
    double rate;
    scanf("%lf",&rate);
    float money;
    printf("��������\t�»����\n");
    for(int i=5;i<=30;i++){
        int month=i*12;
        money=loan*rate*pow((1+rate),month)/(pow((1+rate),month)-1);
        printf("%d\t\t%.0f\n",i,money);
    }
    return 0;
}
