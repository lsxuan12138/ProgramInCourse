#include <stdio.h>
#include <assert.h>

 long fibonacci(int n){
    long fn=1,fn1=1,temp;
    for(int i=2;i<=n;i++){
        temp=fn+fn1;
        fn=fn1;
        fn1=temp;
    }

    return fn;
}

int main()
{
    int n=100;
    printf("%.8f\n", ((double)fibonacci(n))/ fibonacci(n+1));
    return 0;
}
