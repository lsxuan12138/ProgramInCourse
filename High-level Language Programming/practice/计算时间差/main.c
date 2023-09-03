#include <stdio.h>
#include <stdlib.h>
#include <math.h>
typedef struct clock

{

    int hour;

    int minute;

    int second;

} CLOCK;
 CLOCK CalculateTime(CLOCK t1, CLOCK t2);
int main()
{
    CLOCK t1,t2,t3;
    printf("Input time one:(hour，minute):");
    scanf("%d,%d",&t1.hour,&t1.minute);
    printf("Input time two: (hour，minute):");
    scanf("%d,%d",&t2.hour,&t2.minute);
    t3=CalculateTime(t1,t2);
    printf("%dhour,%dminute\n",t3.hour,t3.minute);
    return 0;
}
//函数功能：计算并返回两个时间t1和t2之间的差
 CLOCK CalculateTime(CLOCK t1, CLOCK t2)
 {
     CLOCK t3;
     int time1=t1.hour*60+t1.minute;
     int time2=t2.hour*60+t2.minute;
     int time3=(int)fabs(time1-time2);
     t3.hour=time3/60;
     t3.minute=time3%60;
     return t3;
 }
