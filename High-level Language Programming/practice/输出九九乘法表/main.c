#include <stdio.h>
#include <stdlib.h>

int main()//下三角九九乘法表
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
        for(int j=1;j<=i;j++){
            printf("%4d",i*j);
        }
        for(int j=i+1;j<=n;j++){
            printf("    ");
        }
        printf("\n");
    }
    return 0;
}
