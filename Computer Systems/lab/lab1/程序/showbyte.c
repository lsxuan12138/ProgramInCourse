#include <stdio.h>
#include <assert.h>

int main()
{
    char filePointer[100];
    if(NULL==gets(filePointer))assert("!!!!!!!!!!!");
    FILE *file= fopen(filePointer,"r");
    if(file==NULL)assert("???????????????");
    char c;
    int count = 0;
    unsigned char line[16];
    while ((c=getc(file))!=EOF && c!=255){
        line[count++]=c;
        if(count==16){
            count=0;
            for(int i=0;i<=15;i++){
                if(line[i]=='\n'){
                    printf(" \\n");
                    continue;
                }
                printf("  %c",line[i]);
            }
            printf("\n");
            for(int i=0;i<=15;i++){
                printf(" %2x",line[i]);

            }
            printf("\n");
        }

    }
    if(count!=16){
        for(int i=0;i<count;i++){
            if(line[i]=='\n'){
                printf(" \\n");
                continue;
            }
            printf("  %c",line[i]);
        }
        printf("\n");
        for(int i=0;i<count;i++){
            printf(" %2x",line[i]);

        }
        printf("\n");
    }

    fclose(file);
    return 0;
}