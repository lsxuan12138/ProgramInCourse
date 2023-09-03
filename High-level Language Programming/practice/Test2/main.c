/*
满足特异条件的数列。输入m和n（20≥m≥n≥0），求出满足以下方程式的正整数数列i1,i2,…,in，使得i1+i2+…+in=m，且i1≥i2≥…≥in。例如：
当n=4，m=8时，将得到如下5个数列：
5 1 1 1  4 2 1 1  3 3 1 1  3 2 2 1  2 2 2 2
**输入格式要求："%d" 提示信息："Please enter requried terms (<=10):"
                               "                             their sum:"
**输出格式要求："There are following possible series:\n" "[%d]:" "%d"
程序运行示例：
Please enter requried terms (<=10): 4 8
                            their sum:There are following possible series:
[1]:5111
[2]:4211
[3]:3311
[4]:3221
[5]:2222*/
#define NUM 10
#include <stdio.h>
int i[NUM];
int main()
{
    int sum, n,total,k,flag,count=0;
    printf("Please enter requried terms (<=10):");
    scanf("%d",&n);
    printf("                             their sum:");
    scanf("%d",&total);
    sum=0;
    k=n;
    i[n]=1;
    printf("There are following possible series:\n");
    while(1){
        if(sum+i[k]<total){
            if(k<=1){
                i[1]=total-sum;
                flag=1;
            }
            else{
                sum+=i[k];
                k--;
                i[k]=i[k+1];
                continue;
            }
        }
        else if(sum+i[k]>total||k!=1){
                ++k;
                sum-=i[k];
                flag=0;
            }
            else{
                flag=1;
            }
        if(flag){
            printf("[%d]:",++count);
            for(flag=1;flag<=n;++flag){
                printf("%d",i[flag]);
            }
            printf("\n");
        }
        k++;
        if(k>n){
            break;
        }
        sum-=i[k];
        i[k]++;
    }
}
