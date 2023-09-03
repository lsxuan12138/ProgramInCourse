#include <stdio.h>
#include <stdlib.h>

int main()
{
    typedef struct plural
    {
        int a;
        int b;
    }Fushu;
    Fushu za={3,4};
    Fushu zb={5,6};
    Fushu za2={10,20};
    Fushu zb2={30,40};
    printf("(%d+%di)*(%d+%di)=" "(%d+%di)\n",za.a,za.b,zb.a,zb.b,(za.a*zb.a-za.b*zb.b),za.a*zb.b+za.b*zb.a);
    printf("(%d+%di)*(%d+%di)=" "(%d+%di)\n",za2.a,za2.b,zb2.a,zb2.b,(za2.a*zb2.a-za2.b*zb2.b),za2.a*zb2.b+za2.b*zb2.a);
    return 0;
}
