#include <stdio.h>
#include <stdlib.h>
int Gcd(int a, int b);
//·ÖÊı»¯¼ò
int main()
{
    printf("Input m,n:");
    int m,n;
    scanf("%d,%d",&m,&n);
    int ret=Gcd(m,n);
    if(ret==-1){
        printf("Input error!");
    }else{
        m/=ret;
        n/=ret;
        printf("%d/%d\n",m,n);
    }
    return 0;
}
int Gcd(int a, int b)
{
    if(a<1||a>10000||b<1||b>10000){
        return -1;
    }else{
        if(a>b){
            for(int i=b/2;i>=1;i--){
                if(a%i==0&&b%i==0){
                    return i;
                }
            }
        }else if(a<b){
            for(int i=a/2;i>=1;i--){
                if(a%i==0&&b%i==0){
                    return i;
                }
            }
        }else{
            return a;
        }
    }
    return -1;
}
