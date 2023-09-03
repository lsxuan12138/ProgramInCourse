#include <stdio.h>
long Fib(int n,int *pc);
int main()
{
    int count = 0;
    int *pc=&count;
    int n, i, x;
    printf("Input n:");
    scanf("%d", &n);
    for (i = 0; i <= n; i++)
    {
        count =0;
        x = Fib(i,pc);
        printf("Fib(%d)=%d, count=%d\n", i, x, count);
    }
    return 0;
}

long Fib(int n,int *pc)
{
    (*pc)++;
    if (n == 0)   return 0;
    if (n == 1)   return 1;
    return (Fib(n - 1,pc) + Fib(n - 2,pc));
}
