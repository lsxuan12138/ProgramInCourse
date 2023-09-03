#include <stdio.h>
#include <stdlib.h>

int main()
{
    int total=23;
    printf("这里是23根火柴游戏！！\n");
    printf("注意：最大移动火柴数目为三根\n");
    int n, k;
    do{
        printf("请输入您移动的火柴数目：\n");
        do{
            scanf("%d",&n);
            if(n>3){
                printf("对不起！您输入了不合适的数目，请重新输入！\n");
            }
        }while(n>3);
        printf("您移动的火柴数目为：%d\n",n);
        total-=n;
        printf("您移动后剩下的火柴数目为：%d\n",total);
        if(total==0){
            printf("对不起！您输了！\n");
            break;
        }
        if(total==3){
            k=2;
        }
        else if(total==2){
                k=2;
            }
            else if(total==1){
                    k=1;
                }
                else{
                    k=total%3+1;
                }
        printf("计算机移动的火柴数目为：%d\n",k);
        total-=k;
        printf("计算机移动后剩下的火柴数目为：%d\n",total);
        if(total==0){
            printf("恭喜您！您赢了！\n");
            break;
        }
    }while(total!=0);

    return 0;
}
