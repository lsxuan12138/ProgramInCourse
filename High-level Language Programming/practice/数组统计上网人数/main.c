#include <stdio.h>
#include <stdlib.h>

int main()
{
    int num[24];
    for(int i=0;i<24;i++){
        printf("��������%d:00��%d:00֮���¼���û�����",i,i+1);
        scanf("%d",&num[i]);
    }
    int sum=0;
    for(int i=0;i<24;i++){
        sum+=num[i];
    }
    int max=num[0],pmax=0,min=num[0],pmin=0;
    for(int i=0;i<24;i++){
        if(num[i]>max){
            max=num[i];
            pmax=i;
        }
        if(num[i]<min){
            min=num[i];
            pmin=i;
        }
    }
    printf("   ʱ��                 ��¼����                 ��ռ����\n");
    for(int i=0;i<24;i++){
        printf("%2d:00 - %2d:00 %15d %25.1f\n",i,i+1,num[i],(float)num[i]/sum*100);
    }
    printf("����¼����%d������%2d��00��%2d��00֮��\n",max,pmax,pmax+1);
    printf("��С��¼����%d������%2d��00��%2d��00֮��\n",min,pmin,pmin+1);
    return 0;
}
