#include <stdio.h>
#include <stdlib.h>
int isPinFang(int x);
int main()
{   int abc,xyz;
    int a,b,c,x,y,z;
    int ax,by,cz;
    printf("The possible perfect squares combinations are:\n");
    for(int i=10;i<=31;i++){
        for(int j=10;j<=31;j++){
            abc=i*i;
            xyz=j*j;
            a=abc/100;
            b=(abc-a*100)/10;
            c=abc%10;
            x=xyz/100;
            y=(xyz-x*100)/10;
            z=xyz%10;
            ax=a*10+x;
            by=b*10+y;
            cz=c*10+z;
            if(isPinFang(ax)&&isPinFang(by)&&isPinFang(cz)){
                printf("%d and %d\n",abc,xyz);
            }
        }
    }
    return 0;
}
int isPinFang(int x)
{
    int nums[]={16,25,36,49,64,81};
    for(int i=0;i<6;i++){
        if(x==nums[i]){
            return 1;
        }
    }
    return 0;
}
