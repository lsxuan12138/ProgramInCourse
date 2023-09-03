#include <stdio.h>
#include <stdlib.h>
int Sum(int n);

int main()
{
    printf("Please input n:");
    int n;
    scanf("%d",&n);
    int ret=Sum(n);
    if(ret==-1){
        printf("data error!\n");
    }else{
        printf("sum=%d\n",ret);
    }
    return 0;
}
int Sum(int n){
    if(n<=0){
        return -1;
    }else if(n==1){
        return 1;
    }else{
        return n+Sum(n-1);
    }
}
