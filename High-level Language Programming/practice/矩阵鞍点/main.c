#include <stdio.h>
#include <stdlib.h>
int N;
void FindSaddlePoint(int a[][N], int m, int n);

int main()
{
    printf("Input m,n:\n");
    int m,n;
    scanf("%d,%d",&m,&n);
    int matrix[m][n];
    N=n;
    printf("Input matrix:\n");
    for(int i=0;i<m;i++)
    {
        for(int j=0;j<n;j++)
        {
            scanf("%d",&matrix[i][j]);
        }
    }
    FindSaddlePoint(matrix,m,n);
    return 0;
}
void FindSaddlePoint(int a[][N], int m, int n)
{
    int max,min,b,c;
    for(int i=0;i<m;i++)//����ÿ��
    {   max=a[i][0];
        for(int j=0;j<n;j++)//�ҵ�ÿ����������
        {
            if(a[i][j]>max)
            {
                max=a[i][j];
                b=i;
                c=j;
            }
        }
        min=a[0][c];
        for(int k=0;k<m;k++)//�ҵ��������������С����
        {
            if(min>a[k][c])
            {
                min=a[k][c];
            }
        }
        if(max==min)//�ж������Ƿ����
        {
            printf("a[%d][%d] is %d\n",b,c,max);
            exit(0);
        }
    }
    printf("No saddle point!\n");
}
