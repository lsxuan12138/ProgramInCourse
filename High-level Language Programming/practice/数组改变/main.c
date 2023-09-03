//从键盘输入一个长度为N（N为10）的整型数组，而后将数组
//中小于零的元素移动到数组的前端，大于零的元素移动到数
//组的后端，等于零的元素留在数组中间，前、后端要分别保
//持数组中原来的先后顺序。
#include <stdio.h>
#define N 10
void Rearrange(int a[], int n);

int main(void)
{
    int num[N],n;
    printf("Input %d integer number\n",N);
    for(int i=0;i<N;i++){
        scanf("%d",&n);
        num[i]=n;
    }
    Rearrange(num,N);
    for(int i=0;i<N;i++){
        printf("%5d",num[i]);
    }
    return 0;
}
void Rearrange(int a[], int n)
{
    int b=0;//标记
    for(int i=0;i<n;i++){//遍历数组中每一个数
        if(a[i]<0){//找到负值
            int temp=a[b];//储存a[b]的值
            a[b]=a[i];//将找到的负值放到标记的位置
            for(int j=i;j>b+1;j--){//将负值a[i]前 标记位a[b]后每一个值向后移动
                a[j]=a[j-1];
            }
            a[b+1]=temp;//将a[b]的值放入a[b+1]空位
            b++;//若a[b]值改变，标记值加1，标记下一个位置
        }
    }
    for(int i=0;i<n;i++){//改变0的位置//与上一个for同理
        if(a[i]==0){
            int temp=a[b];
            a[b]=a[i];
            for(int j=i;j>b+1;j--){
                a[j]=a[j-1];
            }
            a[b+1]=temp;
            b++;
        }
    }//负值和0排好后，正值自动放好
}
