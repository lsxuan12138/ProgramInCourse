#include <stdio.h>
#include <stdlib.h>
#include <conio.h>
#include <string.h>
#include <time.h>
#include <windows.h>
#include <pthread.h>

#define UI_WXAJ "无效按键！\r"

#define GAMESETMAPMENUMAX 1
#define MAPL 9

#define DATAFILENAME "PROJECTDAT.DAT"
#define DATAUSERNAME "USERDAT.DAT"

#define B_BACK 'Q'
#define B_SET 'E'
#define B_QUIT 'P'
#define B_UP  'W'
#define B_LEFT  'A'
#define B_RIGHT  'D'
#define B_DOWN  'S'
#define B_0  '0'
#define B_1  '1'
#define B_2  '2'
#define B_3  '3'
#define B_4  '4'
#define B_5  '5'
#define B_6  '6'
#define B_7  '7'
#define B_8  '8'
#define B_9  '9'
#define B_CONFIRM 'F'
#define B_TIP  '.'
#define B_SHOWANS  'Z'
#define B_RESTART 'R'

//生成答案、挖空、展示、移动、填充、判断胜利


//结构定义——————————————————
typedef struct TABLENAME {
    char name[101];
    struct TABLENAME *next;
} NAME;

typedef struct info {
    int x;
    int y;
    int map[MAPL][MAPL];
    int ansmap[MAPL][MAPL];

} INFO;

//公用全局变量与函数声明——————————————————

int SHOWANS=0;
int MAP_DIFF=3;

//用户登录
char USER_NAME[100]="\0";
int USER_COINS=0;
int prethreturn=0;

//首页
int menumax=3;
char menu[][100]={
        ">>        设置 Options     <<",
        ">>    开始数独 Start       <<",
        ">>        退出 Exit        <<"};

FILE* ff;

void clearscreen();
void showmenu();
void printerr(int option, char* text);
void dooption(int option);
void proexit(int id);
void errexit(int a);
int loginreset(NAME *usernamehead);
int login(NAME *usernamehead);

//数独
void sdmain();
int sdput(INFO* map, int number);
int sdcreate(INFO* map);
int sdisvalid(INFO* map,int x,int y);
int sdreset(INFO* map);
int sdiswin(INFO* map);
int sdshowans(INFO* map);
void sdtitle();
int sdscreen(INFO* map);
int sdmove(INFO* map,int dx,int dy);
int sdifexist(INFO* map,int xlow,int xhigh,int ylow,int yhigh,int n,int x,int y);

//代码区——————————————————————————————

///通用函数——————————————————————

//通用标题头
void title0()
{
    clearscreen();
    printf("———————————————————\n");
    printf("             数独\n Ver.0.0.1 2020-12-11\n");
    printf("———————————————————\n");
}

//用户标题头
void title()
{
    title0();
    if(ff==NULL)
    {
        printf("打开配置文件失败或游客模式，设置无法保存。\n");
    }else{
        printf("金币数：%d\n",USER_COINS);
        printf("欢迎您，");puts(USER_NAME);
    }
    printf("———————————————————\n");
}

//保存设置并退出
void proexit(int id)
{
    if(ff!=NULL)
    {
        printf("正在保存信息...\n");
        fclose(ff);
        char tmp[300]="\0";
        strcat(strcat(strcat(tmp,USER_NAME),"_"),DATAFILENAME);
        ff=fopen(tmp,"w");
        if(ff==NULL) {printf("保存用户信息失败\n");}
        else{
        fprintf(ff,"COINS:%d\n",USER_COINS);
        fprintf(ff,"MAP_DIFF:%d\n",MAP_DIFF);
        fclose(ff);
        printf("保存用户信息完毕\n");}
    }
    exit(id);
}

//通用清屏
void clearscreen()
{
    system("cls");
}

//通用：打印错误项（白底高亮）
void printerr (int option, char* text)
{
    char tmp[300]={"\033[7m"};
    switch(option)
    {
    case 0:

        strcat(tmp,text);
        strcat(tmp,"\033[0m\n");
        printf("%s",tmp);
        break;
    }

}

//通用：输出带颜色文本
void printcolor (int mode,int front,int back, char* text)
{
    printf("\033[%d;%d;%dm%s\033[0m",mode,30+front,40+back,text);
}


//通用：错误退出
void errexit(int a)
{
    printf("内存不足，申请空间失败，程序即将退出。\n");
    proexit(a);
}


///设置函数————————————————

//设置：刷新屏幕
void mapsetscreen(int option)
{
    title();
    printf("游戏设置\n");
    printf("请开大写锁定，%c键退出，%c键返回\n%c/%c键上下选择，%c可进行更改\n更改将在下次生成时生效\n",B_QUIT,B_BACK,B_UP,B_DOWN,B_CONFIRM);
    printf("——————————\n");
    for(int i=0;i<GAMESETMAPMENUMAX;i++)
    {
        switch(i)
        {
        case 0:
            printf("难度(1-10)：%d    ",MAP_DIFF);
            break;
        }
        if(option==i) printerr(0,"<<<");
        else printf("\n");
    }


}

//设置：应用设置
void domapset(int option)
{
    int input=0;
    mapsetscreen(option);
    printf("\n\n——————————————\n");
    switch(option)
    {
    case 0:
        while(input<1||input>10)
        {
            printf("请输入欲设置的难度(1-10)：");
            scanf("%d",&input);
            fflush(stdin);
        }
        MAP_DIFF=input;
        break;
    default:
        return;
    }
    mapsetscreen(option);
}

//设置：主函数
void gamesetmap()
{
    int doid=0;
    int option=0;
    mapsetscreen(0);
    while(1)
    {
        doid = (int) getch();

        switch(doid)
        {
        case B_UP:
            option--;
            if(option<0) option=0;
            mapsetscreen(option);
            break;

        case B_DOWN:
            option++;
            if(option>=GAMESETMAPMENUMAX) option=GAMESETMAPMENUMAX-1;
            mapsetscreen(option);
            break;

        case B_CONFIRM:
            domapset(option);
            break;

        case B_QUIT:
            proexit(0);
            break;

        case B_BACK:
            return;
            break;

        default:
            mapsetscreen(option);
            printerr(0,UI_WXAJ);
        }
    }
    return;
}

///首页函数——————————————————————

//首页：显示菜单，高亮参数项
void showmenu(int option)
{
    /**
    格式：\033[显示方式;前景色;背景色m
    说明：
    前景色            背景色           颜色
    ---------------------------------------
    30                40              黑色
    31                41              红色
    32                42              绿色
    33                43              黃色
    34                44              蓝色
    35                45              紫红色
    36                46              青蓝色
    37                47              白色
    显示方式           意义
    -------------------------
    0                终端默认设置
    1                高亮显示
    4                使用下划线
    5                闪烁
    7                反白显示
    8                不可见
	printf("\n\033[1;32;40m在交互输入模式下可见为绿色加粗字体\033[0m\n\n");
	printf("\033[1;31;40m在交互输入模式下可见为红色加粗字体\033[0m\n\n");
	printf("\033[7m在交互输入模式下可见为白底黑字\033[0m\n\n");
	printf("\033[4m在交互输入模式下可见为下划线\033[0m\n\n");
	printf("\033[0;30;41m在交互输入模式下可见为红底黑字\033[0m\n\n");
**/
    title();
    printf("操作方法：请开大写锁定，%c键退出，%c键确认，%c/%c键上下选择\n",B_BACK,B_CONFIRM,B_UP,B_DOWN);
    printf("———————————————————\n");
    for(int i=0;i<menumax;i++)
    {
        if(i==option)
        {
            printf("     ");
            printerr(0,menu[i]);
        }else
        {
            printf("     %s\n",menu[i]);
        }
    }


}


//首页：选中选项操作
void dooption(int option)
{
    switch(option)
    {
    case 1:
        sdmain();
        showmenu(option);
        break;
    case 2:
        proexit(0);
        break;
    case 0:
        gamesetmap();
        showmenu(option);
        break;
    default:
        showmenu(0);
        printerr(0,UI_WXAJ);
        return;
    }
}

//程序：主函数
int main()
{
    int doid=0;
    int option=0;
    NAME usernamehead;
    printf("加载中...");
    if(loginreset(&usernamehead)==1)
    {
        login(&usernamehead);
    }
    showmenu(option);
    while(1)
    {
        doid = (int) getch();
        switch(doid)
        {
        case B_UP:
            option--;
            if(option<0) option=0;
            showmenu(option);
            break;

        case B_DOWN:
            option++;
            if(option>=menumax) option=menumax-1;
            showmenu(option);
            break;

        case '\n':
        case B_CONFIRM:
            dooption(option);
            break;

        case B_BACK:
        case B_QUIT:
            proexit(0);
            break;

        default:
            showmenu(option);
            printerr(0,UI_WXAJ);
        }
    }
    return 0;
}

///登录函数——————————

//登录：初始化
int loginreset(NAME *usernamehead)
{
    clearscreen();
    FILE* fileuser;
    NAME *usernamen;
    fileuser=fopen(DATAUSERNAME,"r");

    strcpy(usernamehead->name,"添加用户");
    usernamehead->next=malloc(sizeof(NAME));
    usernamen=usernamehead->next;
    strcpy(usernamen->name,"游客");
    usernamen->next=NULL;
    if(fileuser==NULL)
    {
        fileuser=fopen(DATAUSERNAME,"w");
        if(fileuser==NULL)
        {
            printf("打开配置文件失败！请检查%s文件是否被占用，继续将导致用户信息无法保存，输入Y继续，其他键退出",DATAUSERNAME);
            if(getch()!='Y')    exit(0);
            else return 0;
        }


    }else{

        int i=2;
        char tmp[102]="\0";
        fgets(tmp,100,fileuser);
        int j=0;
        for(;tmp[j]!='\n'&&j<99;j++)   ;
        tmp[j]='\0';
        while(tmp[0]!='\0')
        {
            usernamen->next=malloc(sizeof(NAME));
            usernamen=usernamen->next;
            strcpy(usernamen->name,tmp);
            usernamen->next=NULL;

            i++;
            tmp[0]='\0';
            fgets(tmp,100,fileuser);
            j=0;
            for(;tmp[j]!='\n'&&j<99;j++)   ;
            tmp[j]='\0';

        }
    }
    fclose(fileuser);
    return 1;
}


//登录：主函数
int login(NAME *usernamehead)
{
    int inc=-1;
    int opt=1;
    int max=0;
    NAME *usernamen=NULL;
    while(USER_NAME[0]=='\0')
    {
        int i=0;
        max=0;
        title0();
        printf("操作方法：请开大写锁定，%c键退出，%c键确认，%c/%c键上下选择，%c键删除用户\n",B_QUIT,B_CONFIRM,B_UP,B_DOWN,B_SHOWANS);
        printf("—————————————\n");
        printf("欢迎您！请选择您的用户名！\n");
         printf("—————————————\n");
        usernamen=usernamehead;
        while(1)
        {
            if(usernamen==NULL) break;
            if(i==opt) {char tmpa[300]="\0";puts(strcat(strcat(tmpa,usernamen->name),"  <<<"));}
            else puts(usernamen->name);

            i++;
            if(usernamen->next==NULL) break;
            else usernamen=usernamen->next;
            //if(i>1)fgets(username[i],100,fileuser);
        }
        max=i-1;
        inc=getch();
        switch(inc)
        {
        case B_DOWN:
            if(opt<max)
            opt++;
            break;
        case B_UP:
            if(opt>0)
            opt--;
            break;
        case B_SHOWANS:
            if(opt==0 || opt==1)    break;
            else
            {
                    i=0;
                    usernamen=usernamehead;
                    while(1)
                    {
                        if(usernamen==NULL) break;
                        if(i==opt-1)
                        {
                            printf("————————————\n");
                            //printf("\n");
                            char tmp[300]="\0";
                            strcat(strcat(strcat(tmp,"确实要删除用户:"),usernamen->next->name),"吗？该用户的所有信息将被清空。输入Y确认，其他键取消\n");
                            puts(tmp);
                            if(getch()=='Y')
                            {
                                NAME *TMN=usernamen->next;
                                if(usernamen->next->next==NULL)
                                {
                                    usernamen->next=NULL;
                                }
                                else {
                                    usernamen->next=usernamen->next->next;
                                }
                                free(TMN);
                                usernamen=usernamehead;
                                int k=0;
                                FILE *filea=fopen(DATAUSERNAME,"w");
                                while(1)
                                {
                                    if(usernamen==NULL) break;
                                    if(k>1) {fputs(usernamen->name,filea);fputs("\n",filea);}
                                    usernamen=usernamen->next;
                                    k++;
                                }
                                fclose(filea);
                            }
                            opt=0;
                            break;
                        }
                        else usernamen=usernamen->next;
                        i++;
                    }
            }
            break;

        case B_QUIT:
            exit(0);
            break;
        case B_CONFIRM:
            if(opt==0)
            {
                    printf("————————————\n");
                    printf("请输入您要创建的用户名（不超过100个字符）\n");
                    usernamen->next=malloc(sizeof(NAME));
                    if(usernamen->next==NULL){errexit(0); break;}
                    usernamen=usernamen->next;
                    usernamen->next=NULL;
                    fgets(usernamen->name,100,stdin);
                    FILE *A=fopen(DATAUSERNAME,"a+");
                    fputs(usernamen->name,A);
                    fclose(A);
                    int j=0;
                    for(;usernamen->name[j]!='\n'&&j<99;j++)   ;
                    usernamen->name[j]='\0';
                    break;

                //新建用户
            }else if(opt==1){//游客
                return 0;
            }else{
                //
                i=0;
                usernamen=usernamehead;
                while(1)
                {
                    if(usernamen==NULL) break;
                    if(i==opt)
                        {
                            strcpy(USER_NAME,usernamen->name);
                            break;
                        }
                    else usernamen=usernamen->next;
                    i++;
            //if(i>1)fgets(username[i],100,fileuser);
                }

                break;
            }
        }
    }

    char tmp[200]="\0";
    strcat(strcat(strcat(tmp,USER_NAME),"_"),DATAFILENAME);
    ff=fopen(tmp,"r"); //wt+
    if(ff==NULL)
    {
        ff=fopen(tmp,"w");
        if(ff==NULL)
        {
            char a[300]= {'\0'};
            strcat(strcat(strcat(a," 打开配置文件失败！请检查"),tmp),"文件是否被占用，继续将导致用户信息无法导入，输入Y继续，其他键退出");
            printf("%s",a);
            if(getch()!='Y')
                return 0;
        }else{

        }

    }else{
        fscanf(ff,"COINS:%d\n",&USER_COINS);
        fscanf(ff,"MAP_DIFF:%d\n",&MAP_DIFF);
        //printf("MAP_DIFF:%d\n",coins);
        //if(getch()!='Y')
          //  return 0;

    }
    return 1;
}

void sdmain()
{
    int doid=0;
    INFO map;
    int retu=0;

    start:
    srand(time(NULL));
    sdtitle();
    map.x=0;
    map.y=0;
    SHOWANS=0;
    if(sdcreate(&map)!=0)
    {
        printf("\n生成错误，输入Y重试，其他键返回。");
        if(getch()!='Y') return;
        else goto start;
        return;
    }
    sdreset(&map);
    sdscreen(&map);
    fflush(stdin);
    retu=0;

    while(1)
    {
        doid = (int) getch();
        printf("%c %d %d\r",doid,map.x,map.y);
        if(retu==0)
        {

        switch(doid)
        {
        case B_UP:
            sdmove(&map,-1,0);
            break;
        case B_LEFT:
            sdmove(&map,0,-1);
            break;
        case B_DOWN:
            sdmove(&map,1,0);
            break;
        case B_RIGHT:
            sdmove(&map,0,1);
            break;

        case B_0:
        case B_1:
        case B_2:
        case B_3:
        case B_4:
        case B_5:
        case B_6:
        case B_7:
        case B_8:
        case B_9:
            sdput(&map,doid-'0');
            retu=sdiswin(&map);
            sdscreen(&map);
            if(retu==1){
                printf("你胜利了。\n");
                USER_COINS+=MAP_DIFF*3;
                printf("输入Y重新开始，其他键返回。");
                if(getch()!='Y') return;
                else goto start;
            }
            break;
        default:
            goto elseid;
        }

        }else
        {

            elseid:
            switch(doid)
        {
        case B_BACK:
            return;

        case B_SET:
            gamesetmap();
            sdscreen(&map);
            fflush(stdin);
            break;

        case B_RESTART:
            goto start;
            break;
        case B_SHOWANS:
            SHOWANS=!SHOWANS;
            if(SHOWANS)
            {
                USER_COINS-=3;
                sdshowans(&map);
            }else{
                sdscreen(&map);
            }
            retu=0;
            break;
        case B_QUIT:
            proexit(0);
            break;
        default:
            printerr(0,UI_WXAJ);
        }
        }

    }
    return;
}
//移动选择项
int sdmove(INFO* map,int dx,int dy)
{
    if(map->x+dx<0 || map->y+dy<0 || map->y+dy>=MAPL || map->x+dx>=MAPL) return 0;

    map->x+=dx;
    map->y+=dy;
    SHOWANS=0;
    sdscreen(map);
    return 0;
}


//生成完整数独与答案
int sdcreate(INFO* map)
{
    int a[9][9]={{1,4,9,8,3,6,7,5,2},{5,7,6,2,4,1,9,3,8},{2,3,8,5,7,9,1,6,4},{7,2,4,3,6,8,5,9,1},{6,8,3,9,1,5,4,2,7},{9,5,1,4,2,7,3,8,6},{3,6,2,7,9,4,8,1,5},{4,1,5,6,8,3,2,7,9},{8,9,7,1,5,2,6,4,3}};
    int num[9]={1,2,3,4,5,6,7,8,9};
    for( int i=0;i<9;i++)
    {
        int r=rand()%(9-i)+i;
        int t=num[i];
        num[i]=num[r];
        num[r]=t;
    }
    for(int i=0;i<9;i++)
    {
        for(int j=0;j<9;j++)
        {
            map->ansmap[i][j]=num[a[i][j]-1];
        }
    }
//    for(int i=0;i<9;i++)
//    {
//        for(int j=0;j<9;j++)
//        printf("(%d)",map->ansmap[i][j]);
//        printf("\n");
//    }
    return 0;
}

//生成游戏，挖空
int sdreset(INFO* map){
    int r;
    for(int i=0;i<MAPL;i++)
    {
        for(int j=0;j<MAPL;j++)
        {
            r=rand()%(11-MAP_DIFF);
            if(r==0)
            {
                map->map[i][j]=0;
            }
            else
            {
                map->map[i][j]=map->ansmap[i][j];
            }
        }
    }
}


//显示答案
int sdshowans(INFO* map){
    sdtitle();
    SHOWANS=1;
    for(int i=0;i<MAPL;i++){
        for(int j=0;j<MAPL;j++){
            if(map->x==i && j==map->y) printf("[");
            else printf(" ");

            char a[2]={"0\0"};
            if( map->map[i][j]<10 && map->map[i][j]>0 ){a[0]=map->ansmap[i][j]+'0'; printcolor(0,3,0,a);}
            else printf("%d",map->ansmap[i][j]);

            if(map->x==i && j==map->y) printf("]");
            else printf(" ");
            if(j==2 || j==5) printf("|");
        }
        printf("\n");
        if(i==2 || i==5) printf("---------+---------+---------\n");
    }
}

//数独通用标题
void sdtitle(){
    title();
    printf("数独\n%c%c%c%c键控制选择\n数字键填入，%c键删除\n",B_UP,B_LEFT,B_DOWN,B_RIGHT,B_0);
    printf("请开大写锁定，%c键退出，%c键返回，%c键重新开始\n按%c键可设置难度，%c键可显示/隐藏答案（消耗3金币）\n",B_QUIT,B_BACK,B_RESTART,B_SET,B_SHOWANS);
    printf("————————————————\n");
}

//刷新显示
int sdscreen(INFO* map){
    sdtitle();
    SHOWANS=0;
    for(int i=0;i<MAPL;i++){
        for(int j=0;j<MAPL;j++){
            if(map->x==i && j==map->y) printf("[");
            else printf(" ");

            char a[2]={"0\0"};
            if( map->map[i][j]<10 && map->map[i][j]>0 ){a[0]=map->map[i][j]+'0'; printcolor(0,3,0,a);}
            else if( map->map[i][j]>10 && map->map[i][j]<20 ){a[0]=map->map[i][j]-10+'0'; printcolor(0,7,0,a);}
            else if( map->map[i][j]>20 && map->map[i][j]<30 ){a[0]=map->map[i][j]-20+'0'; printcolor(0,1,0,a);}
            else {printf(" ");} ////!!!!!!!!!!!!!!!!!!!!!!!!!!

            if(map->x==i && j==map->y) printf("]");
            else printf(" ");
            if(j==2 || j==5) printf("|");
        }
        printf("\n");
        if(i==2 || i==5) printf("---------+---------+---------\n");
    }
}
//填入数字
int sdput(INFO* map, int number){
    int t=map->map[map->x][map->y];
    if(t < 10 && t > 0 ) return 0;
    if(t < 30 && t >= 10 || t == 0 ) map->map[map->x][map->y] = number+10;
}

//判断胜利
int sdiswin(INFO* map)
{
    if(map->map[map->x][map->y]/10==0) return 0;
    if(sdisvalid(map,map->x,map->y)==0){return 0;}
    for(int i=0;i<MAPL;i++)
    {
        for(int j=0;j<MAPL;j++)
        {
            if(map->map[i][j]%10==0)return 0;
            if(map->map[i][j]>10&&map->map[i][j]<30)
            {
                sdisvalid(map,i,j);
            }
        }
    }


    return 1;
}
int sdisvalid(INFO* map,int x,int y){

    for(int i=0;i<MAPL;i++)
    {
        if(i==x)continue;
        if(map->map[i][y]%10==map->map[x][y]%10)
        {
            if(map->map[i][y]/10==0) continue;
            map->map[x][y]=map->map[x][y]%10+20;return 0;
        }
    }

    for(int j=0;j<MAPL;j++)
    {
        if(j==y)continue;
        if(map->map[x][j]%10==map->map[x][y]%10)
        {
            if(map->map[x][j]/10==0) continue;
            map->map[x][y]=map->map[x][y]%10+20;return 0;
        }
    }
    int xlow, xhigh, ylow, yhigh;
    xlow=x/3*3;xhigh=xlow+2;ylow=y/3*3;yhigh=ylow+2;

    if(sdifexist(map,xlow,xhigh,ylow,yhigh,map->map[x][y],x,y)){
           map->map[x][y]=map->map[x][y]%10+20;return 0;
    }
    if(map->map[x][y]/10==2 && map->map[x][y]!=20){
        map->map[x][y]-=10;
    }
    return 1;
}
int sdifexist(INFO* map,int xlow,int xhigh,int ylow,int yhigh,int n,int x,int y)
{
    for(int i=xlow;i<=xhigh;i++)
    {

        for(int j=ylow;j<=yhigh;j++)
        {
            if(x==i&&y==j)continue;
            if(map->map[i][j]%10==n%10)return 1;
        }
    }
    return 0;
}

