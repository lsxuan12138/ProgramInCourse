#include <stdio.h>
#include <stdlib.h>

int main()
{
    printf("请输入人数：");
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
    printf("%d个人中最后留下的是第%d号",n,k);
    return 0;
}
/*有n个人围成一圈，顺序排号。从第一人开始报数（从1到3报数），
凡报到3的人退出圈子，问最后留下的是原来第几号的那位。
程序的运行示例如下：
请输入人数：30
30个人中最后留下的是第29号。*/
