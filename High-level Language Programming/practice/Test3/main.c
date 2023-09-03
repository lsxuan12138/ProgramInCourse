#include<stdio.h>
int main(void){
    printf("Input a decimal and press Enter:");
    int n;
    scanf("%d",&n);
    printf("\n");
    for(int i=1;i<=n;i++){
        for(int j=n-i-1;j>=0;j--){
            printf("%c",' ');
        }
        for(int j=1;j<=2*i-1;j++){
            printf("%c",'*');
        }
        printf("\n");
    }
    return 0;
}
