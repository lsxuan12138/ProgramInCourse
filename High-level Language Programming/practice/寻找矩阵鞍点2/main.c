#include <stdio.h>
#include <stdlib.h>
int N;
void FindSaddlePoint(int a[][N], int m, int n);

int main()
{
    printf("\n����������");
    int m,n;
    scanf("%d",&m);
    printf("\n����������");
    scanf("%d",&n);
    int matrix[m][n];
    N=n;
    for(int i=0;i<m;i++)
    {
        printf("��%d�У�\n",i+1);
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
            printf("\n��%d�У���%d�е�%d�ǰ���\n",b,c,max);
            exit(0);
        }
    }
    printf("\n�������ް���!\n");
}
