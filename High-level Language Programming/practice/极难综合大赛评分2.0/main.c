#include <stdio.h>
#include <math.h>

typedef struct Athlete
{
    int order;
    float score;
}ATHLETE;
typedef struct judge
{
    double score;
}JUDGE;

void InputScore (float *scores,int n,int m,ATHLETE Athletes[],int i);
float FindMax (float *scores,int m,int i);
float FindMin (float *scores,int m,int i);
void CaculateScore(ATHLETE Athletes[],float *scores,int i,int m,float max,float min);
void SortAthlete(int n,ATHLETE *pAth[]);
void PrintfScoreOfAthletes(int n,ATHLETE *pAth[]);
void CaculateJudgeScore(int m,int n,float *scores,JUDGE judges[],ATHLETE *pAth[]);
void SortJudge(int m,JUDGE *pj[]);
void PrintfScoreOfJudge(int m,JUDGE *pj[]);

int main()
{
    printf("How many Athletes?");
    int n;//选手数
    scanf("%d",&n);
    ATHLETE Athletes[n];
    printf("How many judges?");
    int m;//评委数
    scanf("%d",&m);
    float scores[n][m];
    printf("Scores of Athletes:\n");
    for(int i=1;i<=n;i++) {
        InputScore(scores, n, m, Athletes,i);
        printf("Delete a maximum score:%.1f\n",FindMax(scores,m,i));
        printf("Delete a minimum score:%.1f\n",FindMin(scores,m,i));
        CaculateScore(Athletes,scores,i,m,FindMax(scores,m,i),FindMin(scores,m,i));
        printf("The final score of Athlete %d is %.3f\n",Athletes[i-1].order,Athletes[i-1].score);
    }
    printf("Order of Athletes:\n");
    printf("order\tfinal score\tnumber code\n");
    ATHLETE *pAth[n];
    for(int i=0;i<n;i++)//初始化
    {
        pAth[i]=&Athletes[i];
    }
    SortAthlete(n,pAth);
    PrintfScoreOfAthletes( n,pAth);
    for(int i=0;i<n;i++)//再次初始化
    {
        pAth[i]=&Athletes[i];
    }
    printf("Order of judges:\n");
    JUDGE judges[m];
    CaculateJudgeScore(m,n,scores,judges,pAth);
    JUDGE *pj[m];
    for(int i=0;i<m;i++)
    {
        pj[i]=&judges[i];
    }
    SortJudge( m, pj);
    printf("order\tfinal score\tnumber code\n");
    PrintfScoreOfJudge( m, pj);
    printf("Over!Thank you!\n");
    return 0;
}
void InputScore (float *scores,int n,int m,ATHLETE Athletes[],int i)
{

        printf("\nAthlete %d is playing.", i);
        printf("\nPlease enter his number code:");
        scanf("%d", &Athletes[i - 1].order);
        for (int j = 1; j <= m; j++)
        {
            printf("Judge %d gives score:", j);
            scanf("%f", &scores[(i - 1) * m + j - 1]);
        }

}
float FindMax (float *scores,int m,int i)
{

    float max=scores[i-1];
    for(int j=1;j<=m;j++)
    {
        if(max<scores[(i - 1) * m + j - 1])
        {
            max=scores[(i - 1) * m + j - 1];
        }
    }
    return max;
}
float FindMin (float *scores,int m,int i)
{

    float min=scores[i-1];
    for(int j=1;j<=m;j++)
    {
        if(min>scores[(i - 1) * m + j - 1])
        {
            min=scores[(i - 1) * m + j - 1];
        }
    }
    return min;
}
void CaculateScore(ATHLETE Athletes[],float *scores,int i,int m,float max,float min)
{
    Athletes[i-1].score=0;
    for(int j=0;j<m;j++)
    {
        Athletes[i-1].score+=scores[(i-1)*m+j];
    }
    Athletes[i-1].score-=max;
    Athletes[i-1].score-=min;
    Athletes[i-1].score/=(m-2);
}
void SortAthlete(int n,ATHLETE *pAth[])
{
    for(int i=0;i<n;i++)
    {
        ATHLETE *p;
        int k=i;
        for(int j=i+1;j<n;j++)
        {
            if(pAth[k]->score<pAth[j]->score)k=j;
        }
        if(k!=i)
        {
            p=pAth[k];
            pAth[k]=pAth[i];
            pAth[i]=p;
        }
    }
}
void PrintfScoreOfAthletes(int n,ATHLETE *pAth[])
{
    for(int i=0;i<n;i++)
    {
        printf("%5d\t%11.3f\t%6d\n",i+1,pAth[i]->score,pAth[i]->order);
    }
}
void CaculateJudgeScore(int m,int n,float *scores,JUDGE judges[],ATHLETE *pAth[])
{
    for(int i=0;i<m;i++)
    {   double sum=0;
        for(int j=0;j<n;j++)
        {
            sum+=pow((scores[j*m+i]-pAth[j]->score),2);
        }
        judges[i].score=10-sqrt(sum/n);
    }
}
void SortJudge(int m,JUDGE *pj[])
{
    for(int i=0;i<m;i++)
    {
        JUDGE *p;
        int k=i;
        for(int j=i+1;j<m;j++)
        {
            if(pj[k]->score<pj[j]->score)k=j;
        }
        if(k!=i)
        {
            p=pj[k];
            pj[k]=pj[i];
            pj[i]=p;
        }
    }
}
void PrintfScoreOfJudge(int m,JUDGE *pj[])
{
    for(int i=0;i<m;i++)
    {
        printf("%5d\t%11.3f\t%6d\n",i+1,pj[i]->score,i+1);
    }
}
