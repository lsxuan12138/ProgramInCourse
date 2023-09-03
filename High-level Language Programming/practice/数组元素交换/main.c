#include <stdio.h>
#include <stdlib.h>
#define N 5
void SwapArray(int *a,int *b,int n);

int main()
{
    int a[N]={1,2,3,4,5};
    int b[N]={10,20,30,40,50};
    SwapArray(a,b,N);
    for(int i=0;i<N;i++){
        printf("a[%d]=%2d, ",i,a[i]);
    }
    for(int i=0;i<N;i++){
        printf("b[%d]=%2d, ",i,b[i]);
    }
    return 0;
}
void SwapArray(int *a,int *b,int n){
    for(int i=0;i<n;i++){
        int temp=*a;
        *a=*b;
        *b=temp;
        a++;
        b++;
    }
}
