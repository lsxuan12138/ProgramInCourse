#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#define MAPL 9
typedef struct info {
    int x;
    int y;
    int map[MAPL][MAPL];
    //int map[MAPL][MAPL];
    int ansmap[MAPL][MAPL];

} INFO;
int MAP_DIFF=3;
int sdiswin(INFO* map);
int ifexist(INFO* map,int xlow,int xhigh,int ylow,int yhigh,int n);
int main()
{
    INFO i;
    sdcreate(&i);
    return 0;
}

//生成完整数独与答案
int sdcreate(INFO* map)
{
    int a[9][9]={{1,4,9,8,3,6,7,5,2},{5,7,6,2,4,1,9,3,8},{2,3,8,5,7,9,1,6,4},{7,2,4,3,6,8,5,9,1},{6,8,3,9,1,5,4,2,7},{9,5,1,4,2,7,3,8,6},{3,6,2,7,9,4,8,1,5},{4,1,5,6,8,3,2,7,9},{8,9,7,1,5,2,6,4,3}};
    int num[9]={1,2,3,4,5,6,7,8,9};
    srand(time(NULL));
    for( int i=0;i<9;i++)
    {
        int r=rand()%(9-i)+i;
        int t=num[i];
        num[i]=num[r];
        num[r]=t;
    }
    for(int i=0;i<9;i++)
    {
        for(int j=0;j<9;j++)
        {
            map->ansmap[i][j]=num[a[i][j]-1];
        }
    }
    for(int i=0;i<9;i++)
    {
        for(int j=0;j<9;j++)
        printf("(%d)",map->ansmap[i][j]);
        printf("\n");
    }
}
//判断胜利//胜利返回1否则0
int sdiswin(INFO* map)
{
    int x=map->x,y=map->y;
    for(int i=0;i<x;i++)
    {
        if(map->map[i][y]==map->map[x][y])
        {
            map->map[x][y]=map->map[x][y]%10+20;return 0;
        }
    }
    for(int i=x+1;i<MAPL;i++)
    {
        if(map->map[i][y]==map->map[x][y])
        {
            map->map[x][y]=map->map[x][y]%10+20;return 0;
        }
    }
    for(int j=0;j<y;j++)
    {
        if(map->map[x][j]==map->map[x][y])
        {
            map->map[x][y]=map->map[x][y]%10+20;return 0;
        }
    }
    for(int j=y+1;j<MAPL;j++)
    {
        if(map->map[x][j]==map->map[x][y]){
            map->map[x][y]=map->map[x][y]%10+20;return 0;
        }
    }
    int xlow, xhigh, ylow, yhigh;
    xlow=x/3*3;xhigh=xlow+3;ylow=y/3*3;yhigh=ylow+2;
//    if(x<=2&&y<=2){xlow=0; xhigh=2; ylow=0; yhigh=2;}
//    if((x>2&&x<=5)&&(y<=2)){xlow=3; xhigh=5; ylow=0; yhigh=2;}
//    if((x>5)&&(y<=2)){xlow=6; xhigh=8; ylow=0; yhigh=2;}
//    if((x<=2)&&(y>2&&y<=5)){xlow=0; xhigh=2; ylow=3; yhigh=5;}
//    if((x>2&&x<=5)&&(y>2&&y<=5)){xlow=3; xhigh=5; ylow=3; yhigh=5;}
//    if((x>5)&&(y>2&&y<=5)){xlow=6; xhigh=8; ylow=3; yhigh=5;}
//    if(x<=2&&y>5){xlow=0; xhigh=2; ylow=6; yhigh=8;}
//    if((x>2&&x<=5)&&(y>5)){xlow=3; xhigh=5; ylow=6; yhigh=8;}
//    if((x>5)&&(y>5)){xlow=6; xhigh=8; ylow=6; yhigh=8;}
    if(ifexist(map,xlow,xhigh,ylow,yhigh,map->map[x][y])){
           map->map[x][y]=map->map[x][y]%10+20;return 0;
        }
}
int ifexist(INFO* map,int xlow,int xhigh,int ylow,int yhigh,int n)
{
    for(int i=xlow;i<=xhigh;i++)
    {

        for(int j=ylow;j<=yhigh;j++)
        {
            if(map->x==i&&map->y==j)continue;
            if(map->map[i][j]==n)return 1;
        }
    }
    return 0;
}
