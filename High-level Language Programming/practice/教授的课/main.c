#include <stdio.h>
#include <stdlib.h>
//�������ܣ���������a�м�¼��ѧ������ʱ��ȷ��
//�γ��Ƿ�ȡ����ȡ���򷵻�1�����򷵻�0
int IsCancel(int a[], int n, int k);

int main()
{
    int n,k;
    printf("Input n,k:\n");
    scanf("%d,%d",&n,&k);
    int time[n];
    for(int i=0;i<n;i++)
        {
            scanf("%d",&time[i]);
        }
    printf("%s",IsCancel(time,n,k)?"YES":"NO");
    return 0;
}
//�������ܣ���������a�м�¼��ѧ������ʱ��ȷ��
//�γ��Ƿ�ȡ����ȡ���򷵻�1�����򷵻�0
int IsCancel(int a[], int n, int k)
{
    int peopleIsLate=0;
    for(int i=0;i<n;i++)
        {
            if(a[i]<=0)
                {
                    peopleIsLate++;
                }
        }
    if(peopleIsLate<k)
        {
            return 1;
        }
    else
        {
            return 0;
        }
}
