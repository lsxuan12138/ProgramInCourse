#include <stdio.h>
#include <stdlib.h>

int N;
void Transpose(int a[][N], int n);
void InputMatrix(int a[][N], int n);
void PrintMatrix(int a[][N], int n);

int main()
{
    printf("Input n:\n");
    int n;
    scanf("%d",&n);
    N=n;
    int matrix[n][n];
    printf("Input %d*%d matrix:\n",n,n);
    InputMatrix( matrix,  n);
    Transpose( matrix,  n);
    printf("The transposed matrix is:\n");
    PrintMatrix( matrix,  n);
    return 0;
}
void InputMatrix(int a[][N], int n)
{
    for(int i=0;i<n;i++)
    {
        for(int j=0;j<n;j++)
        {
            scanf("%d",&a[i][j]);
        }
    }

}
void Transpose(int a[][N], int n)
{
    int temp;
    for(int i=0;i<n;i++)
    {
        for(int j=i+1;j<n;j++ )
        {
            temp=a[i][j];
            a[i][j]=a[j][i];
            a[j][i]=temp;
        }
    }
}
void PrintMatrix(int a[][N], int n)
{
    for(int i=0;i<n;i++)
    {
        for(int j=0;j<n;j++)
        {
            printf("%d\t",a[i][j]);
        }
        printf("\n");
    }
}
