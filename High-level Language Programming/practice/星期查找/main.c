#include <stdio.h>
#include <stdlib.h>
#include <string.h>

char weekDay[7][10]={"Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};
int main()
{
    printf("Please enter a string:\n");
    char str[10];
    gets(str);
    for(int i=0;i<7;i++){
        if(strcmp(weekDay[i],str)==0){
            printf("%s is %d\n",str,i);
            exit(0);
        }
    }
    printf("Not found!\n");
    return 0;
}
