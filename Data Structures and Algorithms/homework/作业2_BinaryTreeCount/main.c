#include <stdio.h>
#include <stdlib.h>
int getTreeNum(int n);
int main()
{
    printf("%d\n",getTreeNum(4));
    return 0;
}
int getTreeNum(int n){
    if (n==0)return 1;
    if (n==1)return 1;
    int num = 0;
    for(int i=0;i<=n-1;i++){
        num+=getTreeNum(i)*getTreeNum(n-1-i);
    }
    return num;
}
