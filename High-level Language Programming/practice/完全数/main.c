#include <stdio.h>
#include <stdlib.h>
int IsPerfect(int x);
int main()
{
    printf("Input m:\n");
    int m;
    scanf("%d",&m);
    if(IsPerfect(m))
    {
        printf("%d is a perfect number\n",m);
    }
    else
    {
        printf("%d is not a perfect number\n",m);
    }
    return 0;
}
//�ж���ȫ���ĺ���������������0�����������ȫ����
//������1�����������ȫ����
int IsPerfect(int x)
{
    int sum=0;
    for(int i=1;i<=(x/2);i++)
    {
        if(x%i==0)
        {
            sum+=i;
        }
    }
    if(sum==x)
    {
        return 1;
    }
    else
    {
        return 0;
    }
}
