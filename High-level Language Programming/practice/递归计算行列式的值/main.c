#include <math.h>
#include <stdio.h>
#define CONST 1e-6
#define SIZE 20
void InputMatrix(double a[][SIZE], int n);
double DeterminantValue(double a[][SIZE], int n);
void SubMatrix(double a[][SIZE], double b[][SIZE], int n, int row, int col);
void PrintMatrix(double a[][SIZE], int n);
int main(void)//计算n阶行列式的值
{
    double a[SIZE][SIZE];
    int n;
    double result=0;
    printf("Please enter matrix size n(1<=n<%d):", SIZE);
    scanf("%d", &n);
    printf("Please input matrix line by line:\n");
    InputMatrix(a, n);
    printf("matrix a:\n");
    PrintMatrix(a, n);
    printf("\n");
    result = DeterminantValue(a, n);
    printf("result = %f\n", result);
    return 0;
}

//  函数功能： 输入一个n×n矩阵的元素
void InputMatrix(double a[][SIZE], int n)
{
    for(int i=0;i<n;i++)
    {
        for(int j=0;j<n;j++)
        {
            scanf("%lf",&a[i][j]);
        }
    }
}

//  函数功能： 计算n×n矩阵的行列式的值
double DeterminantValue(double a[][SIZE], int n)
{
    if(n==1)
    {
        return a[0][0];
    }
    else if(n==2)
        {
            return a[0][0]*a[1][1]-a[0][1]*a[1][0];
        }
        else
            {
                double b[n-1][n-1],result=0,ret;
                for(int j=0;j<n;j++)
                {
                    SubMatrix(a,b,n,0,j);
                    printf("Submatrix:\n");
                    PrintMatrix(b,n-1);
                    ret=DeterminantValue(b,n-1);
                    printf("DValue of the Submatrix is %6.1f\n",ret);
                    result+=a[0][j]*pow(-1,j)*ret;
                }
                return result;
            }
}

//  函数功能： 计算n×n矩阵a中第row行col列元素的(n-1)×(n-1)子矩阵b
void SubMatrix(double a[][SIZE], double b[][SIZE], int n, int row, int col)
{
    int i1=0,j1=0;
    for(int i=0;i<n;i++)
    {
        j1=0;
        if(i==row)continue;
        for(int j=0;j<n;j++)
        {
            if(j==col){continue;}
            b[i1][j1]=a[i][j];
            j1++;
        }
        i1++;
    }
}

//  函数功能： 输出一个n×n矩阵的元素
void PrintMatrix(double a[][SIZE], int n)
{
    for(int i=0;i<n;i++)
    {
        for(int j=0;j<n;j++)
        {
            printf("%6.1f\t",a[i][j]);
        }
        printf("\n");
    }
}
