#include <stdio.h>
#include <stdlib.h>
#define SIZE 5
int main()
{
    int a[SIZE]={0};
    int *p,*q;
    int temp;
    int i=0,x;
    int * p_Low, *p_Mid, *p_High;
    p = a;
    do{
        scanf("%d",p+i);
        i++;
    }while(i<SIZE);
    for (p=a;p<(a+SIZE);p++){
        for (q=(p+1);q<(a+SIZE);q++){
            if(*q<*p){
                temp = *q;
                *q = *p;
                *p = temp;
            }
        }
    }
    printf("\n");
    for(p=a;p<(a+SIZE);p++){
        printf("%d ", *p);
    }
    p_Low = &a[0];
    p_High = &a[SIZE-1];
    scanf("%d",&x);
    while (p_Low<=p_High){
        p_Mid = (p_High- p_Low)/2+p_Low;
        if (x>*p_Mid){
            p_Low = p_Mid +1;
        }else if (*p_Mid >x){
            p_High = p_Mid -1;
        }else{
            printf("\n%d",(int )(p_Mid-a));
            break;
        }
   }
    return 0;
}
