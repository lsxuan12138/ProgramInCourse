#include <stdio.h>
#include <stdlib.h>
struct date_rec
  {
    int day ;
    int month ;
    int year ;
  } ;

void input_date(struct date_rec *current_date);
void increment_date(struct date_rec *current_date);
void output_date(struct date_rec *current_date);

int dayofmonth[2][12]={{31,28,31,30,31,30,31,31,30,31,30,31},{31,29,31,30,31,30,31,31,30,31,30,31}};

int main()
{
    printf("�����뵱ǰ���ڣ��� �� �գ���");
    struct date_rec dt1;
    input_date(&dt1);
    increment_date(&dt1);
    output_date(&dt1);
    return 0;
}
void input_date(struct date_rec *current_date)
{
    scanf("%d%d%d",&current_date->year,&current_date->month,&current_date->day);
}
void increment_date(struct date_rec *current_date)
{
    if((current_date->year%4==0&&current_date->year%100!=0)||current_date->year%400==0)
    {
        if(current_date->month==12&&current_date->day==31)
        {
            current_date->month=1;
            current_date->day=1;
        }
        else if(current_date->day+1>dayofmonth[1][current_date->month-1])
            {
                current_date->month++;
                current_date->day=1;
            }else
                {
                    current_date->day++;
                }

    }
    else
    {
        if(current_date->month==12&&current_date->day==31)
        {
            current_date->month=1;
            current_date->day=1;
        }
        else if(current_date->day+1>dayofmonth[0][current_date->month-1])
            {
                current_date->month++;
                current_date->day=1;
            }else
                {
                    current_date->day++;
                }


    }
}
void output_date(struct date_rec *current_date)
{
    printf("��ǰ���ڣ�%d��%d��%d�գ�",current_date->year,current_date->month,current_date->day);
}
