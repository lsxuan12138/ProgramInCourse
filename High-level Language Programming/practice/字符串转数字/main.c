#include <math.h>
#include <stdio.h>

void fun(char *s, char *t, int *k)
{
    int i,j=0;
    for (i = 0; s[i]!='\0'; i++){
        if ('0' <= s[i] && s[i] <= '9'){
            t[j++] = s[i];
            (*k)++;
        }
    }
}
int main()
{
    char str[100], tOrigin[9];
    long int n=0;
    int i, k=0;
    printf("Please enter string s:");
    scanf("%s", str);
    fun(str, tOrigin, &k);
    for (i = 0; i < k; i++)
        n += (tOrigin [k - 1 - i] - '0') * pow(10, i);
    printf("The result is: %ld", n);
}

