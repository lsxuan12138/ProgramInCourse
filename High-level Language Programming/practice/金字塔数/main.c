#include <stdio.h>
#include <stdlib.h>

int main()
{
    printf("请输入树的层数：");
    int n;
    scanf("%d",&n);
    for(int i=1;i<=n;i++){//层数
        for(int j=1;j<=i+1;j++){//输出一层
            for(int k=1;k<=n+1-j;k++){//输出空格
                printf(" ");
            }
            for(int k=1;k<=2*j-1;k++){
                printf("*");
            }
            printf("\n");
        }
    }
    for(int i=1;i<=n;i++){
        printf(" ");
    }
    printf("|");
    printf("\n");
    for(int i=1;i<=n;i++){
        printf("=");
    }
    printf("%c",'V');
    for(int i=1;i<=n;i++){
        printf("=");
    }
    return 0;
}
