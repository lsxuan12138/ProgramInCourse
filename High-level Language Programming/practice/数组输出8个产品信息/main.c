#include <stdio.h>
#include <stdlib.h>

int main()
{
    float fixed[]   =  { 11.31, 12.12, 13.67, 11.91, 12.30, 11.8, 11.00, 12.00 } ;
    float variable[] = { 1.12, 1.13, 3.14, 1.35, 2.20, 1.28, 1.00, 2.10 } ;
    printf("�������Ʒ�ţ�1-8����");
    int num;
    scanf("%d",&num);
    printf("��������ֲ�Ʒ������������");
    int n;
    scanf("%d",&n);
    printf("�������Ϊ%d������Ϊ%d�Ĳ�Ʒ���ܳɱ�Ϊ%.2f\n",num,n,fixed[num-1]+n*variable[num-1]);
    return 0;
}
