#include <stdio.h>
#include <stdlib.h>
int fuc(long num);
int main()
{
    printf("Enter a number :");
    long number;
    scanf("%ld",&number);
    int n=fuc(number);
    int num[n];
    for(int i=0;i<n;i++)
    {
        num[i]=number%10;
        number/=10;
    }
    int timesofnum[10]={0};
    for(int i=0;i<n;i++)
    {
        timesofnum[num[i]]++;
    }
    for(int i=0;i<10;i++)
    {
        if(timesofnum[i]>=2)
        {
            printf("Repeated digit\n\n");
            exit(0);
        }
    }
    printf("No Repeated digit\n\n");
    return 0;
}
int fuc(long num)
{
    int n=0;
    while(num>=1)
    {
        num/=10;
        n++;
    }
    return n;
}
