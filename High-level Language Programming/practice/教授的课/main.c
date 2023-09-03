#include <stdio.h>
#include <stdlib.h>
//函数功能：根据数组a中记录的学生到达时间确定
//课程是否被取消，取消则返回1，否则返回0
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
//函数功能：根据数组a中记录的学生到达时间确定
//课程是否被取消，取消则返回1，否则返回0
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
