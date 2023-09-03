#include <stdio.h>
#include <stdlib.h>
#define N 10
int CountRepeatNum(int count[], int n);

int main()
{
    printf("Input n:\n");
    long n;
    int i;
    scanf("%ld",&n);
    int count[N];
    for(i=0;n>1;i++)
    {
        count[i]=n%10;
        n/=10;
    }
    int ret=CountRepeatNum(count,i);
    if(ret!=-1)
    {
        printf("Repeated digit!\n");
    }
    else
    {
        printf( "No repeated digit!\n");
    }
    return 0;
}
/*若有重复数字，则该函数返回重复出现的数字；否则返回-1.*/
int CountRepeatNum(int count[], int n)
{
    int temp=0;
    for(int i=0;i<n;i++){
        temp=count[i];
        for(int j=i+1;j<n;j++)
        {
            if(temp==count[j])
            {
                return temp;
            }
        }
        //if(i){}printf("count[%d]=%d",i,count[i]);
    }
    return -1;
}
