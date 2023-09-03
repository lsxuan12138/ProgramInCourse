#include <stdio.h>
#include <stdlib.h>
void Seperate(int a[], int n);
int main()
{
    printf("Input n:");
    int n;
    scanf("%d",&n);
    int a[n];
    printf("Input numbers:");
    for(int i=0;i<n;i++){
        scanf("%d",&a[i]);
    }
    Seperate(a,n);
    return 0;
}
void Seperate(int a[], int n)
{
    int j=0;
    for(int i=0;i<n;i++){
        if(a[i]%2==1){
            if(j==0){
                printf("%d",a[i]);
                j=1;
            }
            else{
                printf(",%d",a[i]);
            }
        }
    }
    printf("\n");
    j=0;
    for(int i=0;i<n;i++){
        if(a[i]%2==0){
            if(j==0){
                printf("%d",a[i]);
                j=1;
            }
            else{
                printf(",%d",a[i]);
            }
        }
    }
}
