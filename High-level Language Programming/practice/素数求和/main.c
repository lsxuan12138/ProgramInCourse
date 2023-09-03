#include <stdio.h>
#include <stdlib.h>
int IsPrime(int x);

int main()
{
    printf("Input n:");
    int n;
    scanf("%d",&n);
    int sum=0;
    for(int i=1;i<=n;i++){
        if(IsPrime(i)==1){
            sum+=i;
        }
    }
    printf("sum=%d\n",sum);
    return 0;
}
//�������ܣ��ж�x�Ƿ�������������������0�����ʾ����������������1�������������
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
