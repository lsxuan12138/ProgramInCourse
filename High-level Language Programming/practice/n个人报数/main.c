#include <stdio.h>
#include <stdlib.h>

int main()
{
    printf("������������");
    int n;
    scanf("%d",&n);
    int no[n];
    for(int i=0;i<n;i++)
    {
        no[i]=i+1;
    }
    int x=0,t=0;
    int *arry=no,*arry_end=no+n,*p=no;
    while(t<n-1)
    {
        if(p!=arry_end&&*p!=0)
        {
            x++;
        }
        if(x==3)
        {
            x=0;
            *p=0;
            t++;
        }
        p++;
        if(p==arry_end)
        {
            p=arry;
        }
    }
    int k;
    for(int i=0;i<n;i++)
    {
        if(no[i]!=0)
        {
            k=no[i];
            break;
        }
    }
    printf("%d������������µ��ǵ�%d��",n,k);
    return 0;
}
/*��n����Χ��һȦ��˳���źš��ӵ�һ�˿�ʼ��������1��3��������
������3�����˳�Ȧ�ӣ���������µ���ԭ���ڼ��ŵ���λ��
���������ʾ�����£�
������������30
30������������µ��ǵ�29�š�*/
