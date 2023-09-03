#include <stdio.h>

int main()//ËãĞÄÌø´ÎÊı
{
    int birth,today,count=0;
    long unsigned int beats=0;
    printf("Input your birth year:");
    scanf("%d",&birth);
    printf("Input this year:");
    scanf("%d",&today);
    for(int i=birth;i<today;i++){
        if((i%4==0&&i%100!=0)||i%400==0){
            count++;
        }
    }
    beats=(365*(today-birth)+count)*24*60*75;
    printf("The heart beats in your life: %lu",beats);
    return 0;
}
/*#include <stdio.h>
int main()
{
	int x, y, count = 0, year, day;
	unsigned long beat;
	printf("Input your birth year:");
	scanf("%d", &x);
	printf("Input this year:");
	scanf("%d", &y);
	year = y - x;
	for (x;x<y;x++)
	{
		if ((x % 4 == 0 && x % 100 != 0) || x % 400 == 0)
		{
			count++;
		}
	}
	day = year * 365 + count;
	beat = day * 24 * 60 * 75;
	printf("The heart beats in your life: %lu", beat);
	return 0;
}*/
