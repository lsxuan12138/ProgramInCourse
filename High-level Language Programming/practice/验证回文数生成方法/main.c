#include <stdio.h>
#include <stdlib.h>
int isHuiWen(long num);
int main()
{
    printf("please enter a number optionaly:");
    long num,num2;
    int count=1;
    scanf("%ld",&num);
    printf("The generation process of palindrome:\n");
    BIAOJI:
    num2=0;
    long temp=num;
    while(temp>0){
        num2=num2*10+temp%10;
        temp/=10;
    }
    printf("   [%d]: %ld+%ld=%ld\n",count++,num,num2,num+num2);
    if(isHuiWen(num+num2)){
        printf("Here we reached the aim at last !\n");
    }
    else{
        num=num+num2;
        goto BIAOJI;
    }
    //printf("%ld",num2);
    return 0;
}
int isHuiWen(long num)
{
    long temp=num,num2=0;
    while(temp>0){
        num2=num2*10+temp%10;
        temp/=10;
    }
    if(num==num2){
        return 1;
    }
    return 0;
}
