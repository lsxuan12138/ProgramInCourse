#include <stdio.h>
#include <stdlib.h>

int main()
{
    printf("Input a 4 digits number\n");
    int n;
    scanf("%d",&n);
    int num[4];
    for(int i=0;i<4;i++){
        num[3-i]=n%10;
        n/=10;
    }
    for(int i=0;i<4;i++){
        num[3-i]+=5;
        num[3-i]%=10;
    }
    int temp=num[0];
    num[0]=num[3];
    num[3]=temp;
    temp=num[1];
    num[1]=num[2];
    num[2]=temp;
    for(int i=0;i<4;i++){
        printf("%d",num[i]);
    }
    return 0;
}
