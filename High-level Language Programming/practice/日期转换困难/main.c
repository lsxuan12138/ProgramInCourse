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
/* �������ܣ�  �Ը�����ĳ��ĳ��ĳ�գ�����������һ��ĵڼ���
����������  ���ͱ���year��month��day���ֱ�����ꡢ�¡���
��������ֵ����һ��ĵڼ��� */
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
/* �������ܣ�    �Ը�����ĳһ��ĵڼ��죬����������һ��ĵڼ��µڼ���
������ڲ��������ͱ���year���洢��
                 ���ͱ���yearDay���洢��һ��ĵڼ���
�������ڲ���������ָ��pMonth��ָ��洢��һ��ڼ��µ����ͱ���
                 ����ָ��pDay��ָ��洢�ڼ��յ����ͱ���
��������ֵ��  �� */
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
