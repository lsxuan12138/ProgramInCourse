#include <stdio.h>
#include <stdlib.h>
void Insertx(int x,int num[],int n);
int main()
{
    printf("Input array size:\n");
    int n;
    scanf("%d",&n);
    printf("Input array:\n");
    int num[n+1];
    for(int i=0;i<n;i++){
        scanf("%d",&num[i]);
    }
    int x;
    printf("Input x:\n");
    scanf("%d",&x);
    Insertx(x,num,n);
    printf("After insert %d:\n",x);
    for(int i=0;i<n+1;i++){
        printf("%4d",num[i]);
    }
    return 0;
}
void Insertx(int x,int num[],int n)
{
    int low=0,high=n-1,middle=(n-1)/2;
    while(!(num[middle]<x&&num[middle+1]>x)){
        if(num[middle+1]<x){
            low=middle;
            middle=(low+high)/2;
        }
        else if(num[middle]>x){
                high=middle;
                middle=(low+high)/2;
            }
    }
    for(int i=n;i>middle+1;i--){
        num[i]=num[i-1];
    }
    num[middle+1]=x;

}
