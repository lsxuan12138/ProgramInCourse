#include <stdio.h>
#include <stdlib.h>
int isLeap(int year);

const int dayofmonth[2][12]={{31,28,31,30,31,30,31,31,30,31,30,31},{31,29,31,30,31,30,31,31,30,31,30,31}};

int main()
{
    int day, month ,year,talDay=0,ret;
    ret=scanf("%4d-%2d-%2d",&year,&month,&day);
    if(ret!=3||year<1990){
        printf("Invalid input.");
        exit(0);
    }
    if(isLeap(year)){
        if(dayofmonth[1][month-1]<day){
            printf("Invalid input.");
            exit(0);
        }
    }else{
        if(dayofmonth[0][month-1]<day){
            printf("Invalid input.");
            exit(0);
        }
    }

    for(int i=1990;i<year;i++){
        if(isLeap(i)){
            talDay+=366;
        }else{
            talDay+=365;
        }
    }
    if(isLeap(year)){
        for(int i=0;i<month-1;i++){
            talDay+=dayofmonth[1][i];
        }
    }else{
        for(int i=0;i<month-1;i++){
            talDay+=dayofmonth[0][i];
        }
    }
    talDay+=day;
    if((talDay-1)%5>3){
        printf("He is having a rest.");
    }else{
        printf("He is working.");
    }

    return 0;
}
int isLeap(int year)
{
    if((year%4==0&&year%100!=0)||year%400==0){
        return 1;
    }
    return 0;
}
