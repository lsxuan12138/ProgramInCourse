#include <stdio.h>
#include <stdlib.h>
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
    for(int i=1;i<=n;i++)
    {
        printf("\nAthlete %d is playing.",i);
        printf("\nPlease enter his number code:");
        scanf("%d",&Athletes[i-1].order);
        for(int j=1;j<=m;j++)
        {
            printf("Judge %d gives score:",j);
            scanf("%f",&scores[i-1][j-1]);
        }
        float max=scores[i-1][0];
        float min=scores[i-1][0];
        for(int j=1;j<=m;j++)
        {
            if(max<scores[i-1][j-1]){
                max=scores[i-1][j-1];
            }
        }
        printf("Delete a maximum score:%.1f\n",max);
        for(int j=1;j<=m;j++)
        {
            if(min>scores[i-1][j-1]){
                min=scores[i-1][j-1];
            }
        }
        printf("Delete a minimum score:%.1f\n",min);
        Athletes[i-1].score=0;
        for(int j=0;j<m;j++)
        {
            Athletes[i-1].score+=scores[i-1][j];
        }
        Athletes[i-1].score-=max;
        Athletes[i-1].score-=min;
        Athletes[i-1].score/=(m-2);
        printf("The final score of Athlete %d is %.3f\n",Athletes[i-1].order,Athletes[i-1].score);
    }
    printf("Order of Athletes:\n");
    printf("order\tfinal score\tnumber code\n");
    ATHLETE *pAth[n];
    for(int i=0;i<n;i++)
    {
        pAth[i]=&Athletes[i];
    }
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
    for(int i=0;i<n;i++)
    {
        printf("%5d\t%11.3f\t%6d\n",i+1,pAth[i]->score,pAth[i]->order);
    }
    for(int i=0;i<n;i++)
    {
        pAth[i]=&Athletes[i];
    }
    printf("Order of judges:\n");
    JUDGE judges[m];
    for(int i=0;i<m;i++)
    {   double sum=0;
        for(int j=0;j<n;j++)
        {
            sum+=pow((scores[j][i]-pAth[j]->score),2);
        }
        judges[i].score=10-sqrt(sum/n);
    }
    JUDGE *pj[m];
    for(int i=0;i<m;i++)
    {
        pj[i]=&judges[i];
    }
    for(int i=0;i<m;i++)
    {
        JUDGE *p2;
        int k=i;
        for(int j=i+1;j<m;j++)
        {
            if(pj[k]->score<pj[j]->score)k=j;
        }
        if(k!=i)
        {
            p2=pj[k];
            pj[k]=pj[i];
            pj[i]=p2;
        }
    }
    printf("order\tfinal score\tnumber code\n");
    for(int i=0;i<m;i++)
    {
        printf("%5d\t%11.3f\t%6d\n",i+1,pj[i]->score,i+1);
    }
    printf("Over!Thank you!\n");
    return 0;
}
