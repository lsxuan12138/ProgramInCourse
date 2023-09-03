#include  <stdio.h>
#include  <string.h>
#define   MAX_LEN  10
#define   N         150
void SortString(char *ptr[], int n);
int main()
{
    int    i, n;
    char   *pStr[N];
    printf("How many countries?\n");
    scanf("%d",&n);
    getchar();
    char name[N][MAX_LEN];
    for(int i=0;i<n;i++)
    {
        pStr[i]=&name[i][0];
    }
    printf("Input their names:\n");
    for (i=0; i<n; i++)
    {
        gets(pStr[i]);
    }
    SortString(pStr, n);
    printf("Sorted results:\n");
    for (i=0; i<n; i++)
    {
        puts(pStr[i]);
    }
}

void SortString(char *ptr[], int n)
{
    int    i, j;
    char *temp;
    for (i=0; i<n-1; i++)
    {
        for (j = i+1; j<n; j++)
        {
            if (strcmp(ptr[j], ptr[i])<0)
            {
                temp = ptr[i];
                ptr[i] = ptr[j];
                ptr[j] = temp;
            }
        }
    }
}
