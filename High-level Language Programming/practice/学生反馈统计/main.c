#include <stdio.h>
#include <stdlib.h>
#define N 40
int Mean(int answer[], int n);
int Median(int answer[], int n);
int Mode(int answer[], int n);
void DataSort(int a[], int n);
int main()
{
    printf("Input the feedbacks of 40 students:\n");
    int feedbacks[N]={0};
    for(int i=0;i<N;i++)
    {
        scanf("%d",&feedbacks[i]);
    }
    DataSort(feedbacks,N);
    printf("Mean value = %d\n",Mean(feedbacks,N));
    printf("Median value = %d\n",Median(feedbacks,N));
    printf("Mode value = %d\n",Mode(feedbacks,N));
    return 0;
}
int Mean(int answer[], int n)
{
    int sum=0;
    for(int i=0;i<n;i++)
    {
        sum+=answer[i];
    }
    return sum/n;
}
int Median(int answer[], int n)
{
    if(n%2!=0)
    {
        return answer[(n-1)/2];
    }
    else
    {
        return (answer[n/2]+answer[n/2-1])/2;
    }
}
int Mode(int answer[], int n)
{
    int times=1,max=0,maxofindex=0;
    for(int i=0;i<n;i++)
    {
        if(answer[i]==answer[i+1]){
            times++;
        }
        else
        {
            if(max<times){
                max=times;
                maxofindex=i;
            }
            times=1;
        }
    }
    return answer[maxofindex];
}
void DataSort(int a[], int n)
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
