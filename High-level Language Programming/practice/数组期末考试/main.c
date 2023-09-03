#include <stdio.h>
#include <stdlib.h>
#define N 40

typedef struct stu{
    long id;
    int math;
    int english;
    int physics;
    int sum;
    float aver;
}STU;
int main()
{
    printf("Input the total number of the students(n<40):");
    int n;
    scanf("%d",&n);
    STU stus[n];
    printf("Input student¡¯s ID and score as: MT  EN  PH:\n");
    for(int i=0;i<n;i++){
        scanf("%ld",&(stus[i].id));
        scanf("%d",&(stus[i].math));
        scanf("%d",&(stus[i].english));
        scanf("%d",&(stus[i].physics));
    }
    printf("Counting Result:\n");
    printf("Student¡¯s ID\t  MT \t  EN \t  PH \t SUM \t AVER\n");
    for(int i=0;i<n;i++){
        stus[i].sum=stus[i].math+stus[i].english+stus[i].physics;
        stus[i].aver=(float)stus[i].sum/3;
    }
    for(int i=0;i<n;i++){
        printf("%12ld\t%4d\t%4d\t%4d\t%4d\t%5.1f\n",stus[i].id,stus[i].math,stus[i].english,stus[i].physics,stus[i].sum,stus[i].aver);
    }
    int sumOfMT=0,sumOfEN=0,sumOfPH=0;
    for(int i=0;i<n;i++){
        sumOfMT+=stus[i].math;
        sumOfEN+=stus[i].english;
        sumOfPH+=stus[i].physics;
    }
    printf("SumofCourse \t%4d\t%4d\t%4d\t",sumOfMT,sumOfEN,sumOfPH);
    float averOfMT=(float)sumOfMT/n,averOfEN=(float)sumOfEN/n,averOfPH=(float)sumOfPH/n;
    printf("\nAverofCourse\t%4.1f\t%4.1f\t%4.1f\t",averOfMT,averOfEN,averOfPH);
    return 0;
}
