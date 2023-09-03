#include <stdio.h>
#include <stdlib.h>
#define N 100
int isExist (int num[],int x,int index,int n,int k);

int main()
{
    int n;
    int num[N]={0};
    scanf("%d",&n);
    //int num[n]={0};
    //int j=0;
    for(int i=0;i<n;i++){
        scanf("%d",&num[i]);
    }
    int k=0;
    for(int i=0;i<n;i++){
        if(isExist(num,num[i],i,n,k)){
            k++;
            for(int j=i;j<n-k;j++){
                num[j]=num[j+1];
            }
            i--;
        }
    }
    for(int i=0;i<n-k;i++){
        printf("%d ",num[i]);
    }

    return 0;
}
//判断num中下标为index的元素后是否含有x
int isExist (int num[],int x,int index,int n,int k)
{
    for(int i=index+1;i<n-k;i++){
        if(num[i]==x){return 1;}
    }
    return 0;
}
