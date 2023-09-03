#include <stdio.h>
#include <stdlib.h>

typedef struct yuangong
{
    char name[21];
    int jiXiao;//平均月绩效
    int pingYi;//民主评议成绩
    char isGood;//是否优秀员工
    char isStranger;//是否外地员工
    char isAttend;//是否年度满勤
    int hongBao;
}worker;
int main()
{
    int n;
    scanf("%d",&n);//员工数
    worker workers[n],*pworkers[n];
    for(int i=0;i<n;i++)
    {
        pworkers[i]=&workers[i];
    }
    for(int i=0;i<n;i++)
    {
        scanf("%s %d %d %c %c %c",pworkers[i]->name,&(pworkers[i]->jiXiao),&(pworkers[i]->pingYi),&(pworkers[i]->isGood),&(pworkers[i]->isStranger),&(pworkers[i]->isAttend));
    }
    for(int i=0;i<n;i++)
    {
        pworkers[i]->hongBao=0;
        if(pworkers[i]->jiXiao>80&&pworkers[i]->isAttend=='Y'){pworkers[i]->hongBao+=8000;}
        if(pworkers[i]->jiXiao>85&&pworkers[i]->pingYi>80){pworkers[i]->hongBao+=4000;}
        if(pworkers[i]->jiXiao>90){pworkers[i]->hongBao+=2000;}
        if(pworkers[i]->jiXiao>85&&pworkers[i]->isStranger=='Y'){pworkers[i]->hongBao+=1000;}
        if(pworkers[i]->pingYi>80&&pworkers[i]->isGood=='Y'){pworkers[i]->hongBao+=850;}
    }
    worker *pmax=pworkers[0];
    for(int i=1;i<n;i++)
    {
        if(pworkers[i]->hongBao>pmax->hongBao)
        {
            pmax=pworkers[i];
        }
    }
    printf("%s\n",pmax->name);
    printf("%d\n",pmax->hongBao);
    int sum=0;
    for(int i=0;i<n;i++)
    {
        sum+=pworkers[i]->hongBao;
    }
    printf("%d\n",sum);
    return 0;
}
