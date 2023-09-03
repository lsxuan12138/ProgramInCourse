#include <stdio.h>
#include <stdlib.h>

int main()
{
    int num[24];
    for(int i=0;i<24;i++){
        printf("请输入在%d:00和%d:00之间登录的用户数：",i,i+1);
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
    printf("   时间                 登录人数                 所占比例\n");
    for(int i=0;i<24;i++){
        printf("%2d:00 - %2d:00 %15d %25.1f\n",i,i+1,num[i],(float)num[i]/sum*100);
    }
    printf("最大登录人数%d发生在%2d：00到%2d：00之间\n",max,pmax,pmax+1);
    printf("最小登录人数%d发生在%2d：00到%2d：00之间\n",min,pmin,pmin+1);
    return 0;
}
