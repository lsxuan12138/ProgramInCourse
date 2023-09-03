#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#define   MAX_LEN  10                		/* 字符串最大长度 */
#define   STU_NUM 30                       /* 最多的学生人数 */
#define   COURSE_NUM 6                     /* 最多的考试科目数 */

int   Menu(void);
void  ReadScore(long num[], char name[][MAX_LEN],float score[][COURSE_NUM], int n, int m);
void AverSumofEveryStudent(float score[][COURSE_NUM], int n, int m,float  sum[STU_NUM], float aver[STU_NUM]);
void AverSumofEveryCourse(float score[][COURSE_NUM], int n, int m);
void  SortbyScore(long num[], char name[][MAX_LEN],float score[][COURSE_NUM], float  sum[], float aver[],int n, int m, int (*compare)(float a, float b));
int   Ascending(float a, float b);
int   Descending(float a, float b);
void  SwapFloat(float *x, float *y);
void  SwapLong(long *x, long *y);
void  SwapChar(char x[], char y[]);
void  AsSortbyNum(long num[], char name[][MAX_LEN],float score[][COURSE_NUM], float  sum[], float aver[],int n, int m);
void  SortbyName(long num[], char name[][MAX_LEN],float score[][COURSE_NUM], float  sum[], float aver[],int n, int m);
void  SearchbyNum(long num[], char name[][MAX_LEN],float score[][COURSE_NUM], float  sum[], float aver[],int n, int m);
void  SearchbyName(long num[], char name[][MAX_LEN],float score[][COURSE_NUM], float  sum[], float aver[],int n, int m);
void  StatisticAnalysis(float score[][COURSE_NUM], int n, int m);
void  PrintScore(long num[], char name[][MAX_LEN],float score[][COURSE_NUM], float  sum[], float aver[],int n, int m) ;

int main (void)
{
    int n,m;
    printf("Input student number(n<30):\n");
    scanf("%d",&n);
    long num[n];
    char name[n][MAX_LEN];
    float score[n][COURSE_NUM];
    float  sum[n];
    float aver[n];
    int choice=0xffffff;
    while((choice)!=0)
    {
        choice=Menu();
        switch(choice)
        {
        case 1:
            printf("Input course number(m<=6):\n");
            scanf("%d",&m);
            ReadScore(num,name,score,n,m);
            break;
        case 2:
            AverSumofEveryCourse(score,n,m);
            break;
        case 3:
            AverSumofEveryStudent(score,n,m,sum,aver);
            break;
        case 4:
            SortbyScore(num,name,score,sum,aver,n,m,Descending);
            printf("Sort in descending order by score:\n");
            PrintScore(num,name,score,sum,aver,n,m);
            break;
        case 5:
            SortbyScore(num,name,score,sum,aver,n,m,Ascending);
            printf("Sort in ascending order by score:\n");
            PrintScore(num,name,score,sum,aver,n,m);
            break;
        case 6:
            AsSortbyNum(num,name,score,sum,aver,n,m);
            printf("Sort in ascending order by number:\n");
            PrintScore(num,name,score,sum,aver,n,m);
            break;
        case 7:
            SortbyName(num,name,score,sum,aver,n,m);
            printf("Sort in dictionary order by name:\n");
            PrintScore(num,name,score,sum,aver,n,m);
            break;
        case 8:
            SearchbyNum(num,name,score,sum,aver,n,m);
            break;
        case 9:
            SearchbyName(num,name,score,sum,aver,n,m);
            break;
        case 10:
            StatisticAnalysis(score,n,m);
            break;
        case 11:
            PrintScore(num,name,score,sum,aver,n,m);
        case 0:
            printf("End of program!");
            break;
        default:
            printf("Input error!\n");
        }
    }

}
int   Menu(void)
{
    int n;
    printf("Management for Students' scores\n1.Input record\n2.Caculate total and average score of every course\n3.Caculate total and average score of every student\n4.Sort in descending order by score\n5.Sort in ascending order by score\n6.Sort in ascending order by number\n7.Sort in dictionary order by name\n8.Search by number\n9.Search by name\n10.Statistic analysis\n11.List record\n0.Exit\nPlease Input your choice:\n");
    scanf("%d",&n);
    return n;
}
void  ReadScore(long num[], char name[][MAX_LEN],float score[][COURSE_NUM], int n, int m)
{
    printf("Input student's ID, name and score:\n");
    for(int i=0;i<n;i++){
        scanf("%ld%s",&num[i],name[i]);
        for(int j=0;j<m;j++){
            scanf("%f",&score[i][j]);
        }
    }
}
void AverSumofEveryStudent(float score[][COURSE_NUM], int n, int m,float  sum[STU_NUM], float aver[STU_NUM])
{
    for(int i=0;i<n;i++){
        sum[i]=0;
        for(int j=0;j<m;j++){
            sum[i]+=score[i][j];
        }
        aver[i]=sum[i]/m;
        printf("student %d:sum=%.0f,aver=%.0f\n",i+1,sum[i],aver[i]);
    }
}

void AverSumofEveryCourse(float score[][COURSE_NUM], int n, int m)
{
    for(int i=0;i<m;i++){
        float sum=0;
        for(int j=0;j<n;j++){
            sum+=score[j][i];
        }
        float aver=sum/n;
        printf("course %d:sum=%.0f,aver=%.0f\n",i+1,sum,aver);
    }
}
void  SortbyScore(long num[], char name[][MAX_LEN],float score[][COURSE_NUM], float  sum[], float aver[],int n, int m, int (*compare)(float a, float b))
{
    for(int i=0;i<n-1;i++){
        int k=i;
        for(int j=k+1;j<n;j++){
            if(compare(aver[j],aver[k])){k=j;}
        }
        if(k!=i){
            SwapLong(&num[i],&num[k]);
            SwapChar(name[i],name[k]);
            for(int p=0;p<m;p++){
                SwapFloat(&score[i][p],&score[k][p]);
            }
            SwapFloat(&sum[i],&sum[k]);
            SwapFloat(&aver[i],&aver[k]);
        }
    }

}
int   Ascending(float a, float b)
{
    if(a<b){return 1;}
    return 0;
}
int   Descending(float a, float b)
{
    if(a>b){return 1;}
    return 0;
}
void  SwapFloat(float *x, float *y)
{
    float temp=*x;
    *x=*y;
    *y=temp;
}

void  SwapLong(long *x, long *y)
{
    long temp=*x;
    *x=*y;
    *y=temp;
}
void  SwapChar(char x[], char y[])
{
    char temp[MAX_LEN];
    strcpy(temp,x);
    strcpy(x,y);
    strcpy(y,temp);
}
void  AsSortbyNum(long num[], char name[][MAX_LEN],float score[][COURSE_NUM], float  sum[], float aver[],int n, int m)
{
    for(int i=0;i<n-1;i++){
        int k=i;
        for(int j=i+1;j<n;j++){
            if(num[j]<num[k])k=j;
        }
        if(k!=i){
            SwapLong(&num[i],&num[k]);
            SwapChar(name[i],name[k]);
            for(int p=0;p<m;p++){
                SwapFloat(&score[i][p],&score[k][p]);
            }
            SwapFloat(&sum[i],&sum[k]);
            SwapFloat(&aver[i],&aver[k]);
        }
    }
}
void  SortbyName(long num[], char name[][MAX_LEN],float score[][COURSE_NUM], float  sum[], float aver[],int n, int m)
{
    for(int i=0;i<n-1;i++){
        int k=i;
        for(int j=k+1;j<n;j++){
            if(strcmp(name[j],name[k])<0)k=j;
        }
        if(k!=i){
            SwapLong(&num[i],&num[k]);
            SwapChar(name[i],name[k]);
            for(int p=0;p<m;p++){
                SwapFloat(&score[i][p],&score[k][p]);
            }
            SwapFloat(&sum[i],&sum[k]);
            SwapFloat(&aver[i],&aver[k]);
        }
    }
}

void  SearchbyNum(long num[], char name[][MAX_LEN],float score[][COURSE_NUM], float  sum[], float aver[],int n, int m)
{
    printf("Input the number you want to search:\n");
    long num1;
    scanf("%ld",&num1);
    for(int i=0;i<n;i++){
        if(num1==num[i]){
            printf("%ld\t%s\t",num[i],name[i]);
            for(int j=0;j<m;j++){
                printf("%.0f\t",score[i][j]);
            }
            printf("%.0f\t%.0f\n",sum[i],aver[i]);
            return;
        }
    }
    printf("Not found!\n");
}
void  SearchbyName(long num[], char name[][MAX_LEN],float score[][COURSE_NUM], float  sum[], float aver[],int n, int m)
{
    printf("Input the name you want to search:\n");
    char temp[MAX_LEN];
    while(getchar()!='\n');
    scanf("%s",temp);
    for(int i=0;i<n;i++){
        if(strcmp(temp,name[i])==0){
            printf("%ld\t%s\t",num[i],name[i]);
            for(int j=0;j<m;j++){
                printf("%.0f\t",score[i][j]);
            }
            printf("%.0f\t%.0f\n",sum[i],aver[i]);
            return;
        }
    }
    printf("Not found!\n");
}
void  StatisticAnalysis(float score[][COURSE_NUM], int n, int m)
{
    for (int j=0;j<m;j++){
        printf( "For course %d:\n",j+1);
        int level[6]={0,0,0,0,0,0};
        for(int i=0;i<n;i++){
            if(score[i][j]<60)level[0]++;
            else if(score[i][j]<70)level[1]++;
                else if(score[i][j]<80)level[2]++;
                    else if(score[i][j]<90)level[3]++;
                        else if(score[i][j]<100)level[4]++;
                            else level[5]++;
        }
        printf("<60\t%d\t%.2f%%\n",level[0],(float)level[0]/n*100);
        printf("%d-%d\t%d\t%.2f%%\n",60,69,level[1],(float)level[1]/n*100);
        printf("%d-%d\t%d\t%.2f%%\n",70,79,level[2],(float)level[2]/n*100);
        printf("%d-%d\t%d\t%.2f%%\n",80,89,level[3],(float)level[3]/n*100);
        printf("%d-%d\t%d\t%.2f%%\n",90,99,level[4],(float)level[4]/n*100);
        printf("%d\t%d\t%.2f%%\n",100,level[5],(float)level[5]/n*100);
        }
}
void  PrintScore(long num[], char name[][MAX_LEN],float score[][COURSE_NUM], float  sum[], float aver[],int n, int m)
{
    for(int i=0;i<n;i++){
        printf("%ld\t%s\t",num[i],name[i]);
        for(int j=0;j<m;j++){
            printf("%.0f\t",score[i][j]);
        }
        printf("%.0f\t%.0f\n",sum[i],aver[i]);
    }
}
