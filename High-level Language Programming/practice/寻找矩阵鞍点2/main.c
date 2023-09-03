#include <stdio.h>
#include <stdlib.h>
int N;
void FindSaddlePoint(int a[][N], int m, int n);

int main()
{
    printf("\n输入行数：");
    int m,n;
    scanf("%d",&m);
    printf("\n输入列数：");
    scanf("%d",&n);
    int matrix[m][n];
    N=n;
    for(int i=0;i<m;i++)
    {
        printf("第%d行？\n",i+1);
        for(int j=0;j<n;j++)
        {
            scanf("%d",&matrix[i][j]);
        }
    }
    for(int i=0;i<m;i++)
    {
        for(int j=0;j<n;j++)
        {
            printf("%5d",matrix[i][j]);
        }
        printf("\n");
    }
    FindSaddlePoint(matrix,m,n);
    return 0;
}
void FindSaddlePoint(int a[][N], int m, int n)
{
    int max,min,b,c;
    for(int i=0;i<m;i++)//遍历每行
    {   max=a[i][0];
        for(int j=0;j<n;j++)//找到每行中最大的数
        {
            if(a[i][j]>max)
            {
                max=a[i][j];
                b=i;
                c=j;
            }
        }
        min=a[0][c];
        for(int k=0;k<m;k++)//找到最大数所在列最小的数
        {
            if(min>a[k][c])
            {
                min=a[k][c];
            }
        }
        if(max==min)//判断两者是否相等
        {
            printf("\n第%d行，第%d列的%d是鞍点\n",b,c,max);
            exit(0);
        }
    }
    printf("\n矩阵中无鞍点!\n");
}
