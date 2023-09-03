#include <stdio.h>
#include <stdlib.h>

int main()
{
    printf("Enter length:");
    int n;
    scanf("%d",&n);
    for(int i=1;i<n;i++){
        printf(" ");
    }
    for(int i=1;i<=n;i++){
        printf("%c",'*');
    }
    printf("\n");
    for(int i=1;i<n;i++){
        for(int j=1;j<=n-i-1;j++){
            printf(" ");
        }
        printf("%c",'*');
        for(int j=1;j<=n+2*i-2;j++){
            printf(" ");
        }
        printf("%c",'*');
        printf("\n");
    }
    for(int i=n-2;i>=1;i--){
        for(int j=1;j<=n-i-1;j++){
            printf(" ");
        }
        printf("%c",'*');
        for(int j=1;j<=n+2*i-2;j++){
            printf(" ");
        }
        printf("%c",'*');
        printf("\n");
    }
    for(int i=1;i<n;i++){
        printf(" ");
    }
    for(int i=1;i<=n;i++){
        printf("%c",'*');
    }
    return 0;
}
