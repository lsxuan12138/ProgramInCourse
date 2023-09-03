#include <stdio.h>
#include <stdlib.h>
#include <string.h>
typedef struct winner
{
    char name[20];
    int finalScore;
    int classScore;
    char work;
    char west;
    int paper;
    int scholarship;
} WIN;
int main()
{
    printf("Input n:");
    int n;
    scanf("%d",&n);
    WIN winners[n];
    for(int i=0;i<n;i++){
        printf("Input name:");
        scanf("%s",winners[i].name);
        printf("Input final score:");
        scanf("%d",&winners[i].finalScore);
        printf("Input class score:");
        scanf("%d",&winners[i].classScore);
        printf("Class cadre or not?(Y/N):");
        scanf(" %c",&winners[i].work);
        printf("Students from the West or not?(Y/N):");
        scanf(" %c",&winners[i].west);
        printf("Input the number of published papers:");
        scanf("%d",&winners[i].paper);
        winners[i].scholarship=0;
        if(winners[i].finalScore>80&&winners[i].paper>=1){winners[i].scholarship+=8000;}
        if(winners[i].finalScore>85&&winners[i].classScore>80){winners[i].scholarship+=4000;}
        if(winners[i].finalScore>90){winners[i].scholarship+=2000;}
        if(winners[i].finalScore>85&&winners[i].west=='Y'){winners[i].scholarship+=1000;}
        if(winners[i].classScore>80&&winners[i].work=='Y'){winners[i].scholarship+=850;}
        printf("name:%s,scholarship:%d\n",winners[i].name,winners[i].scholarship);
    }
    int max=0;
    char *p;

    for(int i=0;i<n;i++){
        if(winners[i].scholarship>max){
            max=winners[i].scholarship;
            p=winners[i].name;
        }
    }
    printf("%s get the highest scholarship %d\n",p,max);
    return 0;
}
