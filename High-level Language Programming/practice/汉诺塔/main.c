#include <stdio.h>
/*
 �㷨˼·��1�� n-1�������ȷŵ�c��λ��
          2.��a����ʣ�µ�һ�����ƶ���b����
          3����n-1���̴�c���ƶ���b����
*/
//��������
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
    if(n==1)move(n,one, three); //�ݹ��ֹ����
    else{
        hannuo(n-1,one ,three,two);//�� n-1�������ȷŵ�c��λ��
        move(n,one,three);//��a���ϵ�ʣ�µ�һ�����ƶ���b����
        hannuo(n-1,two,one,three);//��n-1���̴�c���ƶ���b����
    }
}
void move(int n,char x,char y)
{
     printf("%2d-(%2d):%c==>%c\n",count,n,x,y);
     count++;
}
