#include <stdio.h>
#include <stdlib.h>

int main()
{
    printf("Input n:\n");
    int n;
    scanf("%d",&n);
    for(int i=1;i<=n;i++){
        printf("%4d",i);
    }
    printf("\n");
    for(int i=1;i<=n;i++){
        printf("   -");
    }
    printf("\n");
    for(int i=1;i<=n;i++){
        for(int j=n-i;j<=n-2;j++){
            printf("    ");
        }
        for(int j=i;j<=n;j++){
            printf("%4d",j*i);
        }
        printf("\n");
    }
    return 0;
}
