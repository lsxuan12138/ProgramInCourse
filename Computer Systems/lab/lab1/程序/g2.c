#include <stdio.h>
#include <assert.h>

long fibonacci(int n){
    if(n<=0)assert("!!!!!!!!!!!!!");
    if(n==1||n==2)return 1L;
    else{
        return fibonacci(n-1)+ fibonacci(n-2) ;
    }
}
int main()
{
    int n=100;
    printf("%.8f\n", ((double)fibonacci(n))/ fibonacci(n+1));
    return 0;
}
