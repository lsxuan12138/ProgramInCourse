
#include <stdio.h>

int main(){
    FILE* ff=fopen("1.c","r");
    FILE* ff2=fopen("2.c","w");

    char a[1000]={'1'};
    fgets(a,1000,ff);
    for(int i=1;a[0]!='\0' ; i++){
        fprintf(ff2,"%3d\t",i);
        fputs(a,ff2);
        if (fgets(a,1000,ff)==NULL)break;
    }
    fclose(ff);
    fclose(ff2);
    return 0;
}
