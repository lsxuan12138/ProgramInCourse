#include <stdio.h>
/*
 算法思路：1将 n-1个盘子先放到c座位上
          2.将a座上剩下的一个盘移动到b盘上
          3、将n-1个盘从c座移动到b座上
*/
//函数声明
int count=1;
void move(int n,char x,char y);
void hannuo(int n,char one ,char two,char three);

int main()
{
     int n;
     printf("Please enter the number of discs:");
     scanf("%d",&n);
     hannuo(n,'a','c','b');
     printf("\tTotal:%d\n",count-1);
     return 0;
}
void hannuo(int n,char one ,char two,char three)
{
    if(n==1)move(n,one, three); //递归截止条件
    else{
        hannuo(n-1,one ,three,two);//将 n-1个盘子先放到c座位上
        move(n,one,three);//将a座上地剩下的一个盘移动到b盘上
        hannuo(n-1,two,one,three);//将n-1个盘从c座移动到b座上
    }
}
void move(int n,char x,char y)
{
     printf("%2d-(%2d):%c==>%c\n",count,n,x,y);
     count++;
}
