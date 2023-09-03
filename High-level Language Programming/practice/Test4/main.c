/*#include <stdio.h>
#include<math.h>
int main()
{
    int num[10];
    int value1=0,value2=0;
    int m=18;
    t1://±êºÅ
    for(;m<=22;m++){
        value1=pow(m,3);
        for(int i=0;i<=3;i++){
            num[i]=value1%10;
            value1/=10;
            printf("%d",i);
        }
        value2=pow(m,4);
        for(int i=4;i<=9;i++){
            num[i]=value2%10;
            value2/=10;
            printf("%d",i);
        }

        for(int i=0;i<=9;i++){
            for(int j=i+1;j<=9;j++){
                if(num[i]==num[j]){
                    goto t1;
                }
            }
        }
        printf("age=%d\n",m);
    }
   return 0;
}*/
#include<stdio.h>
int main(){

    int age=1;
    int san=0;
    int si=0;
    int sum=0;
    while(age>0)
    {
        san=age*age*age;
        si=age*age*age*age;
        int t1,t2,t3,t4;
        int f1,f2,f3,f4,f5,f6;

        t1=san/1000;
        t2=(san-t1*1000)/100;
        t3=(san-t1*1000-t2*100)/10;
        t4=san-t1*1000-t2*100-t3*10;

        f1=si/100000;
        f2=(si-f1*100000)/10000;
        f3=(si-f1*100000-f2*10000)/1000;
        f4=(si-f1*100000-f2*10000-f3*1000)/100;
        f5=(si-f1*100000-f2*10000-f3*1000-f4*100)/10;
        f6=si-f1*100000-f2*10000-f3*1000-f4*100-f5*10;

        sum=t1+t2+t3+t4+f1+f2+f3+f4+f5+f6;
        if(sum==45){
            printf("%d",age);
            break;
        }else{
        age++;
        }
    }
    return 0;
}
