#include <stdio.h>
#include <stdlib.h>
#include <math.h>

int main()
{
    for(int i=0;i<=9;i++){
        printf("%7d",i);
    }
    printf("\n");
    for(int i=0;i<=9;i++){
        printf("%d",i);
        for(int j=0;j<=9;j++){
            printf("%7.3f",sqrt(i*10+j));
        }
        printf("\n");
    }
    return 0;
}
