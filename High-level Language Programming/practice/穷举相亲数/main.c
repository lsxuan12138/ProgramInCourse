#include <stdio.h>
#include <stdlib.h>
#include <math.h>
void Print(int a,int b, int* num1, int *num2,int count1,int count2);

int divideNum(int num,int* divNum,int* count);

int isPrime (int x);
int main()
{
    int num1[100]={0},num2[100]={0};
    int sum1=0,sum2=0;
    int count1=0,count2=0;
    int cou=1;
    for(;cou<10000;cou++){
        if(isPrime(cou))continue;
        sum1=divideNum(cou,num1,&count1);
        sum2=divideNum(sum1,num2,&count2);
        if(cou==sum2 && sum1!=sum2){
            Print(cou,sum1,num1,num2,count1,count2);
            cou=sum1;
        }
    }
    return 0;
}

int divideNum(int num,int* divNum,int* count)
{
    int sum=0;
    *count=1;
    divNum[0]=1;
    sum=1;
    int a=sqrt(num);
    for(int i=2;i<=a;i++){
        if(num%i==0){
            sum=sum+i;
            if(num/i==i)
            ;
            else
            {
                sum+=num/i;
                divNum[(*count)++]=num/i;
            }
            divNum[(*count)++]=i;
        }
    }
    return sum;
}

void Print(int a,int b, int* num1, int *num2,int count1,int count2)
{
    printf("相亲数：%d,%d\n",a,b);
    printf("%d 的真因数之和为：%d",a,1);
    int i=2;
    for(;i<count1;i+=2){
        printf("+%d",num1[i]);
    }
    for(i-=1;i>0;i-=2){
        printf("+%d",num1[i]);
    }
    printf("=%d\n",b);
    printf("%d 的真因数之和为：%d",b,1);

    for(i=2;i<count2;i+=2){
        printf("+%d",num2[i]);
    }
    for(i-=1;i>0;i-=2){
        printf("+%d",num1[i]);
    }
    printf("=%d\n",a);
}
int isPrime (int x)
{
    for(int i=2;i<sqrt(x);i++){
        if(x%i==0){
            return 0;
        }
    }
    return 1;
}
//#include <stdio.h>
//#include <stdlib.h>
//int num1[30]={0},num2[30]={0},count1=0,count2=0;//用来存储真因数及其个数
//int num3[20]={0},count3=0;
//int isXiangQin(int a);
//void Print(int a,int b);
//int isExist(int a);
//int main()
//{
//    for(int i=1;i<10000;i++){
//        if(isExist(i))continue;
//        int ret=isXiangQin(i);
//        if(ret>0&&ret!=i){Print(i,ret);}
//    }
//    return 0;
//}
//int isXiangQin(int a)
//{
//    int sum1=0,sum2=0;
//    for(int i=1;i<=a/2;i++){
//        if(a%i==0){
//            sum1+=i;
//            num1[count1++]=i;
//        }
//    }
//    for(int i=1;i<=sum1/2;i++){
//        if(sum1%i==0){
//            sum2+=i;
//            num2[count2++]=i;
//        }
//    }
//    if(sum2!=a){
//        count2=0;
//        count1=0;
//        return 0;
//    }
//    num3[count3]=a;
//    num3[count3+1]=sum1;
//    count3+=2;
//    return sum1;
//}
//void Print(int a,int b)
//{
//    printf("相亲数：%d,%d\n",a,b);
//    printf("%d 的真因数之和为：%d",a,1);
//    int k=count1;
//    for(int i=1;i<k;i++){
//        printf("+%d",num1[i]);
//    }
//    count1=0;
//    printf("=%d\n",b);
//    printf("%d 的真因数之和为：%d",b,1);
//    k=count2;
//    for(int i=1;i<k;i++){
//        printf("+%d",num2[i]);
//    }
//    count2=0;
//    printf("=%d\n",a);
//}
//int isExist(int a)
//{
//    for(int i=0;i<count3;i++){
//        if(num3[i]==a){
//            return 1;
//        }
//    }
//    return 0;
//}
//#include <stdio.h>
//#include <stdlib.h>
//int num1[30]={0},num2[30]={0},count1=0,count2=0;//用来存储真因数及其个数
//int isXiangQin(int a,int b);
//void Print(int a,int b);
//int main()
//{
//    for(int i=1;i<10000;i++){
//        for(int j=i+1;j<10000;j++){
//            //printf("%d,%d\n",i,j);
//            if(isXiangQin(i,j)){Print(i,j);}
//        }
//    }
//    return 0;
//}
//int isXiangQin(int a,int b)
//{
//    int sum1=0,sum2=0;
//    for(int i=1;i<=a/2;i++){
//        if(a%i==0){
//            sum1+=i;
//            num1[count1++]=i;
//        }
//    }
//    if(sum1!=b){
//        count1=0;
//        return 0;
//    }
//    for(int i=1;i<=b/2;i++){
//        if(b%i==0){
//            sum2+=i;
//            num2[count2++]=i;
//        }
//    }
//    if(sum2!=a){
//        count2=0;
//        return 0;
//    }
//    return 1;
//}
//void Print(int a,int b)
//{
//    printf("相亲数：%d,%d\n",a,b);
//    printf("%d 的真因数之和为：%d",a,1);
//    int k=count1;
//    for(int i=1;i<k;i++){
//        printf("+%d",num1[i]);
//    }
//    count1=0;
//    printf("=%d\n",b);
//    printf("%d 的真因数之和为：%d",b,1);
//    k=count2;
//    for(int i=1;i<k;i++){
//        printf("+%d",num2[i]);
//    }
//    count2=0;
//    printf("=%d\n",a);
//}
