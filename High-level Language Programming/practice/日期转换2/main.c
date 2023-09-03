#include <stdio.h>
#include <stdlib.h>
int  DayofYear(int year, int month, int day);

int main()
{
    printf("Please enter year, month, day:");
    int year, month, day;
    scanf("%d,%d,%d",&year,&month,&day);
    int yearDay=DayofYear(year, month, day);
    printf("yearDay = %d\n",yearDay);
    return 0;
}
/* �������ܣ�  �Ը�����ĳ��ĳ��ĳ�գ�����������һ��ĵڼ���
����������  ���ͱ���year��month��day���ֱ�����ꡢ�¡���
��������ֵ����һ��ĵڼ��� */
int  DayofYear(int year, int month, int day)
{
    int yearDay=0;
    int dayofmonth[2][12]={{31,28,31,30,31,30,31,31,30,31,30,31},{31,29,31,30,31,30,31,31,30,31,30,31}};
    if((year%4==0&&year%100!=0)||year%400==0)
    {
        for(int i=0;i<month;i++)
        {
            yearDay+=dayofmonth[1][i];
        }
    }
    else
    {
        for(int i=0;i<month;i++)
        {
            yearDay+=dayofmonth[0][i];
        }
    }
    yearDay+=day;
    return yearDay;
}
/*#include <stdio.h>
int DayofYear(int year, int month, int day);
int dayTab[2][13] =
{
    {0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31},
    {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31}
};

int main(void)
{
    int year, month, day;
    printf("Please enter year, month, day:\n");
    scanf("%d,%d,%d", &year, &month, &day);
    printf("yearDay = %d\n" ,DayofYear(year, month, day));
}
int DayofYear(int year, int month, int day)
{
    int i, leap,yearDay=0;
    leap = ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0)?0:1;
    for (i = 0; i < month;i++)
    {
        yearDay+=dayTab[leap][i];
    }
    yearDay+=day;
    return yearDay;
}
*/
