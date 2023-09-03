#include <stdio.h>
#include <math.h>
void fun(int a, int *b, int *c);
int main(void)
{
    int a, b, c;
    do
    {
        printf("Input a:\n");
        scanf("%d", &a);
    }
    while (a % 2!=0);
    int *pb=&b,*pc=&c;
    fun(a, pb, pc);
    printf("%d = %d + %d\n", a, b, c);
}

/*为一个偶数寻找两个素数，这两个素数之和等于该偶数，
并将这两个素数通过形参指针传回主函数。
*/
void fun(int a, int *b, int *c)
{
    int i, j, d, y;
    for (i = 3; i <= a / 2; i = i + 2)
    {
        y = 1;
        for (j = 2; j <= sqrt((double)i); j++)//判断i是否为素数
        {
            if (i % j == 0)
            {
                y = 0;
            }
        }
        if (y == 1)
        {
            d = a - i;
            for (j = 2; j <= sqrt((double)d); j++)//判断a-i是否为素数
            {
                if (d % j == 0)
                    y = 0;
            }
            if (y == 1)
            {
                *b = i;
                *c = d;
            }
        }
    }

}
