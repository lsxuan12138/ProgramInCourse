#include <stdio.h>
#include <stdlib.h>

#define N 10

int main()
{
    char name[N];
    printf("ÇëÊäÈëÄãµÄĞÕÃû£º");
    gets(name);
    for(int i=0;name[i]!='\000';i++){
        printf("%c",name[i]);
        printf(" ");
    }
    return 0;
}

