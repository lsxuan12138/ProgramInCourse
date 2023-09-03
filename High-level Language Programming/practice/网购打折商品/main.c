#include <stdio.h>
#include <stdlib.h>

int main()
{
    printf("Input payment:");
    float pay, rate=1;
    scanf("%f",&pay);
    float c=pay;
    if(c<100){
        rate=1;
    }else if(c>=100&&c<200){
        rate=0.95;
    }else if(c<500){
        rate =0.92;
    }else if(c-1000<0){
        rate=0.9;
    }else{
        rate=0.85;
    }
    float price=pay*rate;
    printf("price = %.1f\n",price);
    return 0;
}
