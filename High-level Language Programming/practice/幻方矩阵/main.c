#include <stdio.h>
#include <stdlib.h>
#define N 5

int main()
{
    int a[N][N];
    for(int i=0;i<N;i++)
    {
        for(int j=0;j<N;j++)
        {
            scanf("%d",&a[i][j]);
        }
    }
    int sum[2*N+2]={0,0,0,0,0,0,0,0,0,0,0,0};

    for(int i=0;i<N;i++)
    {
        for(int j=0;j<N;j++)
        {
           sum[i]+=a[i][j];
        }
    }
    for(int i=0;i<N;i++)
    {
        for(int j=0;j<N;j++)
        {
           sum[i+N]+=a[j][i];
        }
    }
    for(int i=0;i<N;i++)
    {
        sum[2*N]+=a[i][i];
    }
    for(int i=0;i<N;i++)
    {
        sum[2*N+1]+=a[i][4-i];
    }
    for(int i=0;i<2*N+2-1;i++)
    {
        if(sum[i]!=sum[i+1])
        {
            goto Print;
        }
    }
    printf("It is a magic square!\n");
Print:
    for(int i=0;i<N;i++)
    {
        for(int j=0;j<N;j++)
        {
            printf("%4d",a[i][j]);
        }
        printf("\n");
    }
    return 0;
}
