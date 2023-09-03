#include<stdio.h>
#include<math.h>
int main(){
    printf("Input capital, year:");
    double capital=0,rate=0,deposit=0;
    int n;
    scanf("%lf,%d",&capital,&n);
    printf("Compound interest (Y/N)?");
    char i;
    scanf(" %c",&i);
    switch(n){
    case 1:
        rate=0.0225;
        break;
    case 2:
        rate=0.0243;
        break;
    case 3:
        rate=0.0270;
        break;
    case 5:
        rate=0.0288;
        break;
    case 8:
        rate=0.03;
        break;
    default:
        printf("Error year!\n");
        return 0;
    }
    if(i=='Y'||i=='y'){
        deposit=capital*pow((1+rate),n);
    }else if(i=='N'||i=='n'){
        deposit=capital*(1+rate*n);
    }else{
        printf("Error year!\n");
        return 0;
    }
    /*switch(i){
    case 89:case 89+32:
        deposit=capital*pow((1+rate),n);
        break;
    case 78:case 78+32:
        deposit=capital*(1+rate*n);
        break;
    default:
        printf("Error year!\n");
        return 0;
    }*/
    printf("rate = %.4f, deposit = %.4f\n",rate,deposit);
    return 0;
}
/*int main(void)
{
    double cap=0,dep=0;
    int y=0;
    char mode='0';
    double rate[9]={-1,0.0225,0.0243,0.0270,-1,0.0288,-1,-1,0.0300};
    printf("Input capital, year:");
    scanf("%lf,%d",&cap,&y);
    printf("Compound interest (Y/N)?");
    scanf(" %c",&mode);
    if(y>8 || y<1 || rate[y]==-1.0)
    {
        printf("Error year!\n");
        return 0;
    }


    if(mode=='Y' || mode=='y')
    {
            dep=cap*pow(1+rate[y],y);
    }else if(mode=='N' || mode=='n')
    {
            dep=cap*(1+rate[y]*y);
    }else{printf("Error year!\n");return 0;}

    printf("rate = %.4f, deposit = %.4f\n",rate[y],dep);

    return 0;
}*/
