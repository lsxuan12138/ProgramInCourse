#include <stdio.h>
int MinCommonMultiple(int a, int b);

int main()
{
    int a, b, x;
    printf("Input a,b:");
    scanf("%d,%d",&a,&b);
    x=MinCommonMultiple(a,b);
    printf("MinCommonMultiple = %d\n", x);
}
int MinCommonMultiple(int a, int b)
{
    int i;
    for (i=a; i<=a*b; i++)
    {
        if (i%a==0 && i%b==0)
            return i;
    }
    return 0;
}
