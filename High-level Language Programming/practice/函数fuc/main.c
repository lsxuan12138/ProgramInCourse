#include <stdio.h>
#include <stdlib.h>

double fuc(int m,int n);
long Fact(int n);

int main()
{
    printf("������m,n��ֵ( m>n )��\n");
    int m,n;
    scanf("%d %d",&m,&n);
    printf("n��֮��Ϊ��%lf\n",fuc(m,n));
    return 0;
}
double fuc(int m,int n){
    double p=Fact(m)/(Fact(n)*Fact(m-n));
    return p;
}
long Fact(int n){
    long result=1;
    for(int i=2;i<=n;i++){
        result*=i;
    }
    return result;
}
