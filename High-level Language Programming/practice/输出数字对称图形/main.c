#include <stdio.h>
#include <stdlib.h>

int main()
{
    printf("Enter n(1-9):\n");
    int n;
    scanf("%d",&n);
    for(int i=1;i<=n;i++){
        for(int j=1;j<i;j++){
            printf(" ");
        }
        for(int j=1;j<=2*n-2*i+1;j++){
            printf("%d",n-i+1);
        }
        printf("\n");
    }
    for(int i=n-1;i>=1;i--){
        for(int j=1;j<i;j++){
            printf(" ");
        }
        for(int j=1;j<=2*n-2*i+1;j++){
            printf("%d",n-i+1);
        }
        printf("\n");
    }
    return 0;
}
