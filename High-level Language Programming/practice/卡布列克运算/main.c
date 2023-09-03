#include <stdio.h>
#include <stdlib.h>

void Sort(int a[],int n);
void kbuliek(int a);
int count=0;
int main()
{
    printf("Enter number:");
    int num;
    scanf("%d",&num);
    kbuliek(num);
    //kbuliek(6174);
    return 0;
}
void kbuliek(int a)
{
    int numofa[4];
    for(int i=0;i<4;i++){
        numofa[i]=a%10;
        a/=10;
    }
    Sort(numofa,4);
    int m=0,n=0;
    for(int i=0;i<4;i++){
        m*=10;
        m+=numofa[i];
    }
    for(int i=0;i<4;i++){
        n*=10;
        n+=numofa[3-i];
    }
    if(m-n==6174){
        printf(" [%d]:%d-%d=%d\n",++count,m,n,m-n);
    }
    else{
        printf(" [%d]:%d-%d=%d\n",++count,m,n,m-n);
        kbuliek(m-n);
    }
}
void Sort(int a[],int n)
{
    for(int i=0;i<n-1;i++){
        int k=i;
        for(int j=i+1;j<n;j++){
            if(a[j]>a[k])k=j;
        }
        if(k!=i){
            int temp=a[k];
            a[k]=a[i];
            a[i]=temp;
        }
    }
}
