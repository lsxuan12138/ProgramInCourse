#include <stdio.h>
#include <stdlib.h>
int dayofmonth[2][12]={{31,28,31,30,31,30,31,31,30,31,30,31},{31,29,31,30,31,30,31,31,30,31,30,31}};

int  DayofYear(int year, int month, int day);
void  MonthDay(int year, int yearDay, int *pMonth, int *pDay);
int isLeap(int year);

int main()
{
    int year,month,day,yearDay;
    printf("1. year/month/day -> yearDay\n");
    printf("2. yearDay -> year/month/day\n");
    printf("3. Exit\n");
    printf("Please enter your choice:");
    int n;
    scanf("%d",&n);
    switch(n)
    {
    case 1:
        printf("Please enter year, month, day:");
        scanf("%d,%d,%d",&year,&month,&day);
        printf("yearDay = %d\n",DayofYear(year,month,day));
        break;
    case 2:
        printf("Please enter year, yearDay:");
        scanf("%d,%d",&year,&yearDay);
        MonthDay(year,yearDay,&month,&day);
        printf("month = %d,day = %d\n",month,day);
        break;
    default:
        exit(0);
    }
    return 0;
}
/* 函数功能：  对给定的某年某月某日，计算它是这一年的第几天
函数参数：  整型变量year、month、day，分别代表年、月、日
函数返回值：这一年的第几天 */
int  DayofYear(int year, int month, int day)
{
    int dayOfYear=0;
    if(isLeap(year))
    {
        for(int i=0;i<month-1;i++){
            dayOfYear+=dayofmonth[1][i];
        }
    }else{
        for(int i=0;i<month-1;i++){
            dayOfYear+=dayofmonth[0][i];
        }
    }
    dayOfYear+=day;
    return dayOfYear;
}
/* 函数功能：    对给定的某一年的第几天，计算它是这一年的第几月第几日
函数入口参数：整型变量year，存储年
                 整型变量yearDay，存储这一年的第几天
函数出口参数：整型指针pMonth，指向存储这一年第几月的整型变量
                 整型指针pDay，指向存储第几日的整型变量
函数返回值：  无 */
void  MonthDay(int year, int yearDay, int *pMonth, int *pDay)
{
    if(isLeap(year)){
        for(int i=0;yearDay>=dayofmonth[1][i];i++,*pMonth=i+1){
            yearDay-=dayofmonth[1][i];
        }
        *pDay=yearDay;
    }else{
        for(int i=0;yearDay>=dayofmonth[0][i];i++,*pMonth=i+1){
            yearDay-=dayofmonth[0][i];
        }
        *pDay=yearDay;
    }
}
int isLeap(int year)
{
    if((year%4==0&&year%100!=0)||year%400==0)
    {
        return 1;
    }
    return 0;
}
