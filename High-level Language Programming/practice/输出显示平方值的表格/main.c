#include <stdio.h>
#include <stdlib.h>

int main()
{
    printf("This program prints a table of squares.\n");
    printf("Enter number of entries in table:\n");
    int n;
    scanf("%d",&n);
    for(int i=1;i<=n;i++){
        printf("%10d%10d\n",i,i*i);
    }
    return 0;
}
