#include <stdio.h>
#include <stdlib.h>
#define N 10
int IsPrime(int x);
int main()
{
    printf("Input m:");
    int m;
    scanf("%d",&m);
    if(IsPrime(m)){//�ж�m�Ƿ�Ϊ����
        printf("It is a prime number\n");
    }
    else{
        int i,k=m,j=0,num[N]={0};
        for(i=2;i<m/2;i++){
            while(IsPrime(i)&&k%i==0){
                num[j]=i;//������������������
                j++;
                k/=i;
            }
        }
        printf("%d = ",m);
        for(i=0;num[i+1]!=0;i++){//�������
            printf("%d * ",num[i]);
        }
        printf("%d",num[i]);//������һ������
    }
    return 0;
}
//�������ܣ��ж�x�Ƿ�������������������0�����ʾ����������
//          ������1�������������
int IsPrime(int x)
{
    if(x==1)return 0;
    else{
        for(int i=x/2;i>1;i--){
            if(x%i==0){
                return 0;
            }
        }
    }
    return 1;
}
