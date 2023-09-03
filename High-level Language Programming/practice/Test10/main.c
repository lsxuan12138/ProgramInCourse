#include <stdio.h>

int main()
{
    int i;
    char ch;
    float f;
    printf("Please input:\n");
    scanf("%d %c%f", &i, &ch, &f);
    printf("The input integer is : %d \nThe input character is : %c\n", i, ch);
    printf("The input float is : %f", f);
}
