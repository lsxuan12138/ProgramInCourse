#include <stdio.h>
#include <stdlib.h>
#include <string.h>
int main()
{
    printf("Input the number of electorates:");
    int n;
    scanf("%d",&n);
    int num[3] = {0};
    char name[5];
    for(int i=1;i<=n;i++){
        printf("Input vote %d:",i);
        scanf("%s",name);
        if(strcmp(name,"tom")==0){num[0]++;}
        if(strcmp(name,"jack")==0){num[1]++;}
        if(strcmp(name,"rose")==0){num[2]++;}
    }
    printf("Election results:\n");
    printf("%s:%d\n","tom",num[0]);
    printf("%s:%d\n","jack",num[1]);
    printf("%s:%d\n","rose",num[2]);
    if(num[0]>num[1]&&num[0]>num[2]){printf("%s wins\n","tom");}
    if(num[1]>num[2]&&num[1]>num[0]){printf("%s wins\n","jack");}
    if(num[2]>num[1]&&num[2]>num[0]){printf("%s wins\n","rose");}
    return 0;
}
