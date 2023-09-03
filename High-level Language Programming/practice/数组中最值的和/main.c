#include <stdio.h>
#include <stdlib.h>

int main()
{
    int num[10];
    for(int i=0;i<=9;i++){
        scanf("%d",&num[i]);
    }
    int max=num[0],min=num[0];
    for(int i=1;i<=9;i++){
        if(max<num[i]){
            max=num[i];
        }
    }
    for(int i=1;i<=9;i++){
        if(min>num[i]){
            min=num[i];
        }
    }
    int sum=max+min;
    printf("sum=%3d",sum);
    return 0;
}
