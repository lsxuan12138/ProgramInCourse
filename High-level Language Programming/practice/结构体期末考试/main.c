#include <stdio.h>
#include <stdlib.h>
#define N 40
typedef struct stu
{
    long id;
    int math;
    int english;
    int physics;
    int total;
    float aver;

}STU;
void InputInfo(STU stus[N],int n);
void DealInfo(STU stus[N],int n);
void PrintInfo(STU stus[N],int n);
int main()
{
    printf("Input the total number of the students(n<40):");
    int n;
    scanf("%d",&n);
    STU stus[N];
    InputInfo(stus,n);
    printf("Counting Result:\n");
    DealInfo(stus,n);
    PrintInfo(stus,n);
    int sumofmt=0,sumofen=0,sumofph=0;
    for(int i=0;i<n;i++){
        sumofmt+=stus[i].math;
        sumofen+=stus[i].english;
        sumofph+=stus[i].physics;
    }
    printf("SumofCourse \t%4d\t%4d\t%4d\t",sumofmt,sumofen,sumofph);
    printf("\nAverofCourse\t%4.1f\t%4.1f\t%4.1f\t",(float)sumofmt/n,(float)sumofen/n,(float)sumofph/n);
    return 0;
}
void InputInfo(STU stus[N],int n)
{
    printf("Input student¡¯s ID and score as: MT  EN  PH:\n");
    for(int i=0;i<n;i++){
        scanf("%ld%d%d%d",&(stus[i].id),&(stus[i].math),&(stus[i].english),&(stus[i].physics));
    }
}
void DealInfo(STU stus[N],int n)
{
    for(int i=0;i<n;i++){
        stus[i].total=stus[i].math+stus[i].english+stus[i].physics;
        stus[i].aver=(float)stus[i].total/3;
    }
}
void PrintInfo(STU stus[N],int n)
{
    printf("Student¡¯s ID\t  MT \t  EN \t  PH \t SUM \t AVER\n");
    for(int i=0;i<n;i++){
        printf("%12ld\t%4d\t%4d\t%4d\t%4d\t%5.1f\n",stus[i].id,stus[i].math,stus[i].english,stus[i].physics,stus[i].total,stus[i].aver);
    }
}
