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

/*Ϊһ��ż��Ѱ����������������������֮�͵��ڸ�ż����
��������������ͨ���β�ָ�봫����������
*/
void fun(int a, int *b, int *c)
{
    int i, j, d, y;
    for (i = 3; i <= a / 2; i = i + 2)
    {
        y = 1;
        for (j = 2; j <= sqrt((double)i); j++)//�ж�i�Ƿ�Ϊ����
        {
            if (i % j == 0)
            {
                y = 0;
            }
        }
        if (y == 1)
        {
            d = a - i;
            for (j = 2; j <= sqrt((double)d); j++)//�ж�a-i�Ƿ�Ϊ����
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
