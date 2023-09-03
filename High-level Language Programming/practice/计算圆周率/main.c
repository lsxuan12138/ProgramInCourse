#include  <stdio.h>
int main()
{
    double term, result=1;
    int n;

    for (n=2;n<=100; n+=2)
    {
        term = (double)(n * n) / (( n - 1) * ( n + 1));
        result *= term;
    }
    result*=2.0;
    printf("result=%f\n",result);
}
