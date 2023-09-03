#include <stdio.h>
#include <stdlib.h>
#define N 10
void SortInt(int a[],int n);

int main()
{
    printf("Input 10 numbers:\n");
    int srcnum[N],desnum[N];
    for(int i=0;i<N;i++)
    {
        scanf("%d",&srcnum[i]);
    }
    SortInt(srcnum,N);
    int j=0;
    for(int i=0;i<N;i++)
    {
        if(srcnum[i]%2!=0)
        {
            desnum[j++]=srcnum[i];
        }
    }
    for(int i=0;i<N;i++)
    {
        if(srcnum[i]%2==0)
        {
            desnum[j++]=srcnum[i];
        }
    }
    printf("Output: ");
    for(int i=0;i<N-1;i++)
    {
        printf("%d,",desnum[i]);
    }
    printf("%d\n",desnum[N-1]);
    return 0;
}
void SortInt(int a[],int n)
{
    for(int i=0;i<n-1;i++)
    {
        int k=i;
        for(int j=i+1;j<n;j++)
        {
            if(a[k]>a[j])k=j;
        }
        if(k!=i)
        {
            int temp=a[k];
            a[k]=a[i];
            a[i]=temp;
        }
    }
}
