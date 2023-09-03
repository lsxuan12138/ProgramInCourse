#include <stdio.h>
#include <math.h>
int main()
{
    int n = 1, count = 1;
    double x;
    double sum, term;
    printf("Input x:");
    scanf("%lf", &x);
    sum = x;
    term = x;
    do
    {
        term = -term * x * x / ((n + 1) * (n + 2));
        sum += term;
        n += 2;
        count++;
    }
    while (fabs(term) >= 1e-5);
    printf("sin(x) = %lf, count = %d\n", sum, count);
}
