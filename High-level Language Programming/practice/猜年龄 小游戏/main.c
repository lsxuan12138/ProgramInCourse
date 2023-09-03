#include <stdio.h>
#include <stdlib.h>
int dayofmonth[2][12]={{31,28,31,30,31,30,31,31,30,31,30,31},{31,29,31,30,31,30,31,31,30,31,30,31}};
int isLeap(int year);
int main()
{
    printf("Please input your busiest day.\n");
    int n;
    scanf("%d",&n);
    if(n<1||n>7) goto ERROR;
    n*=2;
    printf("Please input your gender.\n");
    char gender;
    scanf(" %c",&gender);
    if(gender=='m')n+=4;
    else if (gender=='f') n+=8;
        else goto ERROR;
    n*=50;
    printf("Please input your birthday.\n");
    int year,month,day;
    scanf("%4d-%2d-%2d",&year,&month,&day);
    if(month<1||month>12||day<1) goto ERROR;
    if(isLeap(year)){
        if (day>dayofmonth[1][month-1]){
            goto ERROR;
        }
    }
    else{
        if (day>dayofmonth[0][month-1]){
            goto ERROR;
        }
    }
    if (month<=6)n+=2002;
    else n+=3002;
    n-=year;
    n%=100;
    n+=10;
    printf("Your age is:%d\n",n);
    return 0;
    ERROR:
        printf("Invalid input.\n");
    return 0;
}
int isLeap(int year)
{
    if((year%4==0&&year%100!=0)||year%400==0)
        return 1;
    return 0;
}
