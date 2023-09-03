#include <stdio.h>
#include <stdlib.h>
#define N 30
typedef struct student
{
    long id;
    float score;
}STU;

void InputRecord (STU stus[N],int n);
void SumScores(STU stus[N],int n);
void PrintInf(STU *pstus[N],int n);
void DescendingSortBbyScore(STU *pstus[N],STU stus[N],int n);
void AscendingSortBbyNumber(STU *pstus[N],STU stus[N],int n);
void SearchByNumber(STU stus[N],int n);
void StatisticAnalysis(STU stus[N],int n);

int main()
{
    printf("Input student number(n<30):\n");
    int n;
    scanf("%d",&n);
    STU stus[N];
    STU *pstus[N];
    for(int i=0;i<n;i++){
        pstus[i]=&stus[i];
    }
    int choice;
    do{
        printf("Management for Students' scores\n");
        printf("1.Input record\n");
        printf("2.Caculate total and average score of course\n");
        printf("3.Sort in descending order by score\n");
        printf("4.Sort in ascending order by number\n");
        printf("5.Search by number\n");
        printf("6.Statistic analysis\n");
        printf("7.List record\n");
        printf("0.Exit\n");
        printf("Please Input your choice:\n");
        scanf("%d",&choice);
        switch(choice)
        {
        case 0:
            printf("End of program!\n");
            break;
        case 1:
            InputRecord(stus,n);
            break;
        case 2:
            SumScores(stus,n);
            break;
        case 3:
            DescendingSortBbyScore(pstus,stus, n);
            break;
        case 4:
            AscendingSortBbyNumber(pstus,stus, n);
            break;
        case 5:
            SearchByNumber(stus, n);
            break;
        case 6:
            StatisticAnalysis(stus, n);
            break;
        case 7:
            PrintInf(pstus,n);
            break;
        default:
            printf("Input error!\n");
        }
    }while(choice!=0);
    return 0;
}
void InputRecord (STU stus[N],int n)
{
    printf("Input student's ID, name and score:\n");
    for(int i=0;i<n;i++){
        scanf("%ld%f",&(stus[i].id),&(stus[i].score));
    }
}
void SumScores(STU stus[N],int n)
{
    float sum=0;
    for(int i=0;i<n;i++){
        sum+=(stus[i].score);
    }
    printf("sum=%.0f,aver=%.2f\n",sum,sum/n);
}
void PrintInf(STU *pstus[N],int n)
{
    for(int i=0;i<n;i++){
        printf("%ld\t%.0f\n",pstus[i]->id,pstus[i]->score);
    }
}
void DescendingSortBbyScore(STU *pstus[N],STU stus[N],int n)
{
    int k,i,j;
    for(i=0;i<n;i++){
        k=i;
        for(j=i+1;j<n;j++){
            if(pstus[j]->score>pstus[k]->score){
                k=j;
            }
        }
        if(k!=i){
            STU *p=pstus[i];
            pstus[i]=pstus[k];
            pstus[k]=p;
        }
    }
    printf("Sort in descending order by score:\n");
    PrintInf(pstus,n);
    for(int i=0;i<n;i++){
        pstus[i]=&stus[i];
    }
}
void AscendingSortBbyNumber(STU *pstus[N],STU stus[N],int n)
{
    int k,i,j;
    for(i=0;i<n;i++){
        k=i;
        for(j=i+1;j<n;j++){
            if(pstus[j]->id<pstus[k]->id){
                k=j;
            }
        }
        if(k!=i){
            STU *p=pstus[i];
            pstus[i]=pstus[k];
            pstus[k]=p;
        }
    }
    printf("Sort in ascending order by number:\n");
    PrintInf(pstus,n);
    for(int i=0;i<n;i++){
        pstus[i]=&stus[i];
    }
}
void SearchByNumber(STU stus[N],int n)
{
    long num;
    printf("Input the number you want to search:\n");
    scanf("%ld",&num);
    for(int i=0;i<n;i++){
        if(num==stus[i].id){
            printf("%ld\t%.0f\n",num,stus[i].score);
            return;
        }
    }
    printf("Not found!\n");
}
void StatisticAnalysis(STU stus[N],int n)
{
    int level[6]={0};
    for(int i=0;i<n;i++){
        if(stus[i].score<60)level[0]++;
        else if(stus[i].score<70)level[1]++;
            else if(stus[i].score<80)level[2]++;
                else if(stus[i].score<90)level[3]++;
                    else if(stus[i].score<100)level[4]++;
                        else level[5]++;
    }
    printf("<60\t%d\t%.2f%%\n",level[0],(float)level[0]/n*100);
    printf("%d-%d\t%d\t%.2f%%\n",60,69,level[1],(float)level[1]/n*100);
    printf("%d-%d\t%d\t%.2f%%\n",70,79,level[2],(float)level[2]/n*100);
    printf("%d-%d\t%d\t%.2f%%\n",80,89,level[3],(float)level[3]/n*100);
    printf("%d-%d\t%d\t%.2f%%\n",90,99,level[4],(float)level[4]/n*100);
    printf("%d\t%d\t%.2f%%\n",100,level[5],(float)level[5]/n*100);
}
