#include <stdio.h>
#include <stdlib.h>

void swap(int *p1,int *p2);
int main()
{
    printf("please input 3 number x,y,z");
    int x,y,z;
    scanf("%d,%d,%d",&x,&y,&z);
    if(x<y)
    {
        if(y>z)
        {
            if(x<=z){
                swap(&y,&z);
            }
            else{
                swap(&y,&z);
                swap(&x,&y);
            }
        }

    }
    else if(x>y)
    {
        if(y>=z)
        {
            swap(&x,&z);
        }
        else
        {
            if(x<=z)
            {
                swap(&x,&y);
            }
            else
            {
                swap(&x,&y);
                swap(&y,&z);
            }
        }
    }
    else
    {
        if(x>z)
        {
            swap(&x,&z);
        }
    }
    printf("the sorted numbers are:%d,%d,%d\n",x,y,z);
    return 0;
}
void swap(int *p1,int *p2)
{
    int t=*p1;
    *p1=*p2;
    *p2=t;
}
