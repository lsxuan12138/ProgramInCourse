#include <stdio.h>
#include <stdlib.h>
#define M 2
#define K 3
#define N 2

int main()
{
    int a[M][K];
    int b[K][N];
    int c[M][N];
    printf("Input:a[%d][%d]",M,K);
    for(int i=0;i<M;i++)
    {
        for(int j=0;j<K;j++)
        {
            scanf("%d",&a[i][j]);
        }
    }
    printf("Input:b[%d][%d]",K,N);
    for(int i=0;i<K;i++)
    {
        for(int j=0;j<N;j++)
        {
            scanf("%d",&b[i][j]);
        }
    }
    for(int i=0;i<M;i++)
    {
        for(int j=0;j<N;j++)
        {
            c[i][j]=0;
            for(int k=0;k<K;k++)
            {
                c[i][j]+=a[i][k]*b[k][j];
            }
        }
    }
    printf("array A=\n");
    for(int i=0;i<M;i++)
    {
        for(int j=0;j<K;j++)
        {
            printf("\t%d",a[i][j]);
        }
        printf("\n");
    }
    printf("array B=\n");
    for(int i=0;i<K;i++)
    {
        for(int j=0;j<N;j++)
        {
            printf("\t%d",b[i][j]);
        }
        printf("\n");
    }
    printf("array C=\n");
    for(int i=0;i<M;i++)
    {
        for(int j=0;j<N;j++)
        {
            printf("\t%d",c[i][j]);
        }
        printf("\n");
    }
    return 0;
}
