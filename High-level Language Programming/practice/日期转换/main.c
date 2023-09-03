#include <stdio.h>
#include <stdlib.h>
void  MonthDay(int year, int yearDay, int *pMonth, int *pDay);

int main(void)
{
    printf("Please enter year, yearDay:");
    int year, yearDay;
    scanf("%d,%d",&year,&yearDay);
    int month=1,day=1;
    int *pMonth=&month,*pDay=&day;
    MonthDay(year,yearDay,pMonth,pDay);
    printf("month = %d, day = %d\n",*pMonth,*pDay);
    return 0;
}
/* 函数功能：    对给定的某一年的第几天，计算它是这一年的第几月第几日
函数入口参数：整型变量year，存储年
                 整型变量yearDay，存储这一年的第几天
函数出口参数：整型指针pMonth，指向存储这一年第几月的整型变量
                 整型指针pDay，指向存储第几日的整型变量
函数返回值：  无 */
void  MonthDay(int year, int yearDay, int *pMonth, int *pDay){
    int month[2][13]={{0,31,28,31,30,31,30,31,31,30,31,30,31},
    {0,31,29,31,30,31,30,31,31,30,31,30,31}};
    int month1[13];
    if((year%4==0||year%100!=0)&&year%400==0){
        for(int i=0;i<=12;i++){
            month1[i]=month[1][i];
        }
    }else{
        for(int i=0;i<=12;i++){
            month1[i]=month[0][i];
        }
    }
    int month2=1;
    while(yearDay>month1[month2]){
        yearDay-=month1[month2];
        month2++;
    }
    *pMonth=month2;
    *pDay=yearDay;
}
