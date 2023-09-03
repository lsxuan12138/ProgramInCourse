#include <stdio.h>
#include <stdlib.h>
#define N 10
int Search(int a[], int n, int x);
int main()
{
    printf("Input 10 numbers:\n");
    int num[N];
    for(int i=0;i<N;i++){
        scanf("%d",&num[i]);
    }
    printf("Input x:\n");
    int x;
    scanf("%d",&x);
    int ret=Search(num,N,x);
    if(ret==-1){
        printf("Not found!\n");
    }
    else{
        printf("Subscript of x is %d\n",ret);
    }
    return 0;
}
int Search(int a[], int n, int x)
{
    for(int i=0;i<n;i++){
        if(x==a[i]){return i;}
    }
    return -1;
}
