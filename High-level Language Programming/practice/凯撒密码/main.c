#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#define N 100
void Caesar(char c[]);

int main()
{
    printf("Input a string:");
    char str[N];
    gets(str);
    Caesar(str);
    puts(str);
    return 0;
}
void Caesar(char c[])
{
    for(int i=0;i<strlen(c);i++){
        c[i] +=3;
        if(c[i]>'z'){
            c[i]-=26;
        }
    }
}
