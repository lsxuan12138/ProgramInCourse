#include <stdio.h>
#include <stdlib.h>

int main()
{
    int n=9;
    for(int i=n;i>=0;i--){
        for(int j=1;j<n-i+1;j++){
            printf(" ");
        }
        for(int j=n-i+1;j<=n;j++){
            printf("%d",j);
        }
        for(int j=n-1;j>=n-i+1;j--){
            printf("%d",j);
        }
        printf("\n");
    }
    return 0;
}
