#include <stdio.h>
int main(void)
{
    int a[10],b[10];
    for(int i=0;i<10;i++){
        scanf("%d",&a[i]);
    }
    for(int j=1;j<=10;j++){
        b[j]=a[j-1]*a[j];
    }
    for(int i=0;i<10;i++){
        printf("%d",b[i]);
    }
    return 0;
}
