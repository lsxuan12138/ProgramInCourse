#include <stdio.h>
#include <stdlib.h>
#include <math.h>

int main()
{
    int ret;
    short n;
    do{
    printf("n=");
    ret=scanf("%hd",&n);
    if (ret !=1){
        while(getchar()!='\n');
    }
    }while(ret!=1||n<0);
    int num[16]={0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
    for(int i=15;i>=0;i--){
        if(n/(int)pow(2,i)==1){
            n%=(int)pow(2,i);
            num[i]=1;
        }
    }
    printf("the binary number is ");
    for(int i=15;i>=0;i--){
        printf("%d",num[i]);
    }
    return 0;
}
