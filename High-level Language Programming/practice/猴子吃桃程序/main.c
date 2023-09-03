#include <stdio.h>
#include <stdlib.h>
int Monkey(int n);

int main()
{
    printf("Input days n:");
    int n;
    scanf("%d",&n);
    int x=Monkey(n);
    printf("x=%d\n",x);

    return 0;
}
int Monkey(int n){
    if(n==1){
        return 1;
    }else{
        return (Monkey(n-1)+1)*2;
    }
}
