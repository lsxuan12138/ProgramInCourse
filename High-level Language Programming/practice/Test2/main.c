/*
�����������������С�����m��n��20��m��n��0��������������·���ʽ������������i1,i2,��,in��ʹ��i1+i2+��+in=m����i1��i2�ݡ���in�����磺
��n=4��m=8ʱ�����õ�����5�����У�
5 1 1 1  4 2 1 1  3 3 1 1  3 2 2 1  2 2 2 2
**�����ʽҪ��"%d" ��ʾ��Ϣ��"Please enter requried terms (<=10):"
                               "                             their sum:"
**�����ʽҪ��"There are following possible series:\n" "[%d]:" "%d"
��������ʾ����
Please enter requried terms (<=10): 4 8
                            their sum:There are following possible series:
[1]:5111
[2]:4211
[3]:3311
[4]:3221
[5]:2222*/
#define NUM 10
#include <stdio.h>
int i[NUM];
int main()
{
    int sum, n,total,k,flag,count=0;
    printf("Please enter requried terms (<=10):");
    scanf("%d",&n);
    printf("                             their sum:");
    scanf("%d",&total);
    sum=0;
    k=n;
    i[n]=1;
    printf("There are following possible series:\n");
    while(1){
        if(sum+i[k]<total){
            if(k<=1){
                i[1]=total-sum;
                flag=1;
            }
            else{
                sum+=i[k];
                k--;
                i[k]=i[k+1];
                continue;
            }
        }
        else if(sum+i[k]>total||k!=1){
                ++k;
                sum-=i[k];
                flag=0;
            }
            else{
                flag=1;
            }
        if(flag){
            printf("[%d]:",++count);
            for(flag=1;flag<=n;++flag){
                printf("%d",i[flag]);
            }
            printf("\n");
        }
        k++;
        if(k>n){
            break;
        }
        sum-=i[k];
        i[k]++;
    }
}
