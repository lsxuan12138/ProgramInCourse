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

        for(int j=1;j<=n;j++){
            printf("%4d",i*j);
        }
        printf("\n");
    }
    return 0;
}
