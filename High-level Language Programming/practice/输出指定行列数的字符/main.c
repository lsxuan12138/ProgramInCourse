#include <stdio.h>
void Chline(char ch, int column, int row);

int main(void){
    printf("input a char:\n");
    char ch;
    scanf("%c",&ch);
    printf("input column and row:\n");
    int column,row;
    scanf("%d%d",&column,&row);
    Chline(ch,column,row);
    return 0;
}
void Chline(char ch, int column, int row){
    for(int i=1;i<=row;i++){
        for(int j=1;j<=column;j++){
            printf("%c",ch);
        }
        printf("\n");
    }
}
