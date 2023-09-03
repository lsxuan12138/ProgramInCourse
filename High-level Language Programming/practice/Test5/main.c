#include <stdio.h>
#include <stdlib.h>

int main()
{
    printf("Please input a capital:\n");
    char ch;
    scanf("%c",&ch);
    for(int i=0;i<=ch-'A';i++){
        for(int j=0;j<=ch-'A'-i;j++){
            printf(" ");
        }
        for(int j='A';j<='A'+i;j++){
            printf("%c",j);
        }
        for(int j='A'+i-1;j>='A';j--){
            printf("%c",j);
        }
        printf("\n");
    }
    return 0;

}
