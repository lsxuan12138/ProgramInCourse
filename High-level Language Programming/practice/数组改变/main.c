//�Ӽ�������һ������ΪN��NΪ10�����������飬��������
//��С�����Ԫ���ƶ��������ǰ�ˣ��������Ԫ���ƶ�����
//��ĺ�ˣ��������Ԫ�����������м䣬ǰ�����Ҫ�ֱ�
//��������ԭ�����Ⱥ�˳��
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
    int b=0;//���
    for(int i=0;i<n;i++){//����������ÿһ����
        if(a[i]<0){//�ҵ���ֵ
            int temp=a[b];//����a[b]��ֵ
            a[b]=a[i];//���ҵ��ĸ�ֵ�ŵ���ǵ�λ��
            for(int j=i;j>b+1;j--){//����ֵa[i]ǰ ���λa[b]��ÿһ��ֵ����ƶ�
                a[j]=a[j-1];
            }
            a[b+1]=temp;//��a[b]��ֵ����a[b+1]��λ
            b++;//��a[b]ֵ�ı䣬���ֵ��1�������һ��λ��
        }
    }
    for(int i=0;i<n;i++){//�ı�0��λ��//����һ��forͬ��
        if(a[i]==0){
            int temp=a[b];
            a[b]=a[i];
            for(int j=i;j>b+1;j--){
                a[j]=a[j-1];
            }
            a[b+1]=temp;
            b++;
        }
    }//��ֵ��0�źú���ֵ�Զ��ź�
}
