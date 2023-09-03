#include <stdio.h>
#include <stdlib.h>
typedef struct stu
{
    int no;
    int mt;
    int en;
    int ph;
    int sum;
    int aver;
    char morethan90;
}STU;
int main()
{
    int n=5;
    STU stus[n];
    for(int i=0;i<n;i++){
            printf("Enter No. and score as: MT EN PH\n");
        scanf("%d%d%d%d",&stus[i].no,&stus[i].mt,&stus[i].en,&stus[i].ph);
    }
    for(int i=0;i<n;i++){
        stus[i].sum=stus[i].mt+stus[i].en+stus[i].ph;
        stus[i].aver=stus[i].sum/3;
        stus[i].morethan90=stus[i].aver>90?'Y':'N';
    }
    printf("NO\tMT\tEN\tPH\tSUM\tV\t>90\n"  );
    printf("------------------------------------------------------\n");
    for(int i=0;i<n;i++){
        printf("%d\t%d\t%d\t%d\t%d\t%d\t%c\n",stus[i].no,stus[i].mt,stus[i].en,stus[i].ph,stus[i].sum,stus[i].aver, stus[i].morethan90);
    }
    return 0;
}
