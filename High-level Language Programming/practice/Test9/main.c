#include <stdio.h>
#include <stdlib.h>
unsigned int ComputeAge(unsigned int n);

int main()
{
    printf("The 5th person's age is %d\n",ComputeAge(5));
    return 0;
}
unsigned int ComputeAge(unsigned int n){
    if(n==1){
        return 10;
    }else{
        return 2+ComputeAge(n-1);
    }
}
