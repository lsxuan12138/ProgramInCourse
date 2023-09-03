#include <stdio.h>
#include <stdlib.h>
char weekDay[7][10]={"sunday","monday","tuesday","wednesday","thursday","friday","saturday"};

int main()
{
    printf("please input the first letter of someday:\n");
    char let1;
    scanf(" %c",&let1);
    int count=0;//记录首字母与let1相同数量
    char *p;
    for(int i=0;i<7;i++){
        if(weekDay[i][0]==let1||weekDay[i][0]-32==let1){
            count++;
            p=weekDay[i];
        }
    }
    if(count==0){
        printf("data error\n");
    }
    else if(count==1){
        printf("%s\n",p);
        }
        else{
            char let2;
            printf("please input second letter:\n");
            scanf(" %c",&let2);
            count=0;
            for(int i=0;i<7;i++){
                if(weekDay[i][1]==let2||weekDay[i][1]-32==let2){
                    p=weekDay[i];
                    count++;
                }
            }
            if(count==0){
                printf("data error\n");
                exit(0);
            }
            printf("%s\n",p);
        }
    return 0;
}
