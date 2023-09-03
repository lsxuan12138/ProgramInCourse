#include <stdio.h>
#include <windows.h>
#include <conio.h>
#include <time.h>
/*****宏定义******/
#define FrameX 13          //游戏窗口左上角x轴坐标为13
#define FrameY 3           //游戏窗口左上角y轴坐标为13
#define Frame_height 20    //游戏窗口高度为20
#define Frame_width 18     //游戏窗口宽度为20
/*******全局变量**********/
int a[80][80]={0};
//标记游戏界面内的图案：2，1，0分别表示该位置为游戏边框、方块、无图案：初始化为无图案
int b[4];//标记4个”口“方块，1表示有方块，0表示无方块
typedef struct Tetris
{
    int x;                   //中心方块x轴坐标
    int y;                   //中心方块y轴坐标
    int flag;                //标记方块类型的序号
    int next;                //标记下一个方块类型的序号
    int speed;               //方块移动速度
    int number;              //产生俄罗斯方块的个数
    int score;               //游戏分数
    int level;               //游戏等级
}TETRIS;
HANDLE hOut;                 //???控制台句柄
/******函数声明******/
int color(int c);
void gotoxy(int x,int y);         //获取屏幕光标位置
void title ();                    //欢迎界面上方的标题
void flower();                    //绘制字符花
void welcome();                   //菜单选项和边框
void DrawGameFrame();             //绘制游戏窗口
void MakeTetris(TETRIS *tetris);  //制作俄罗斯方块
void PrintTetris(TETRIS *tetris); //打印俄罗斯方块
int ifMove(TETRIS *tetris);       //判断是否可移动
int ifDown(TETRIS *tetris);
void CleanTetris(TETRIS *tetris); //清除方块痕迹
void Del_FullLine(TETRIS *tetris);//判断是否满行，若是，删除满行的方块
void Flag(TETRIS *tetris);        //随机产生俄罗斯方块类型序号
void GameStart();                 //开始游戏
void ReStart(TETRIS *tetris);     //重新开始游戏
void explation();                 //按键说明界面
void regulation();                //规则说明界面
void close();                     //退出
int main()//主函数
{
    title();
    flower();
    welcome();
    return 0;
}
/**
*文字颜色函数
*/
int color(int c)
{
    SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE),c);
    //SetConsoleTextAttribute是用来设置控制台窗口字体颜色和背景颜色的函数
    //GetStdHandle是获得输入，输出或错误的屏幕缓冲区的句柄？？？
    //GetStdHandle参数值应为（STD_OUTPUT_HANDLE）（STD_INPUT_HANDLE）（STD_ERROR_HANDLE）
    return 0;
}
/**
*获取屏幕光标位置
*/
void gotoxy(int x,int y)
{
    COORD pos;
    pos.X=x;
    pos.Y=y;
    SetConsoleCursorPosition(GetStdHandle(STD_OUTPUT_HANDLE),pos);
    //SetConsoleCursorPosition用来定位光标位置
    //可以通过修改pos.X和pos.Y来修改光标位置
}
/**
*欢迎界面上方的标题
*/
void title ()
{
    color(15);                    //亮白色
    gotoxy(24,3);
    printf("趣 味 俄 罗 斯 方 块\n");
    color(11);                    //亮蓝色
    gotoxy(18,5);
    printf("■");
    gotoxy(18,6);
    printf("■■");
    gotoxy(18,7);
    printf("■");

    color(14);                    //黄色
    gotoxy(26,6);
    printf("■■");
    gotoxy(28,7);
    printf("■■");

    color(10);                   //绿色
    gotoxy(36,6);
    printf("■■");
    gotoxy(36,7);
    printf("■■");

    color(13);                  //粉色
    gotoxy(45,5);
    printf("■");
    gotoxy(45,6);
    printf("■");
    gotoxy(45,7);
    printf("■");
    gotoxy(45,8);
    printf("■");

    color(12);
    gotoxy(56,6);
    printf("■");
    gotoxy(52,7);
    printf("■■■");
}
/**
*绘制字符花
*/
void flower()
{
    color(12);//红色
    gotoxy(66,11);//四个花瓣
    printf("(_)");

    gotoxy(64,12);
    printf("(_)");

    gotoxy(68,12);
    printf("(_)");

    gotoxy(66,13);
    printf("(_)");

    color(6);//输出红花花蕊
    gotoxy(67,12);
    printf("@");

    color(13);//粉色
    gotoxy(72,10);//四个花瓣
    printf("(_)");

    gotoxy(76,10);
    printf("(_)");

    gotoxy(74,9);
    printf("(_)");

    gotoxy(74,11);
    printf("(_)");

    color(6);//输出粉花花蕊
    gotoxy(75,10);
    printf("@");

    gotoxy(71,12);//输出两朵花之间的连接
    printf("|");
    gotoxy(72,11);
    printf("/");
    gotoxy(70,13);
    printf("\\/");
    gotoxy(70,14);
    printf("`|/");
    gotoxy(70,15);
    printf("\\|");
    gotoxy(70,16);
    printf("| /");
    gotoxy(70,17);
    printf("|");

    color(10);//输出草地
    gotoxy(67,17);
    printf("\\\\\\\\");
    gotoxy(73,17);
    printf("//");

    color(2);
    gotoxy(67,18);
    printf("^^^^^^^^");

    color(10);
    gotoxy(67,19);
    printf(" 作 者 ");
    gotoxy(68,20);
    printf("lsx");
}
/**
*菜单
*/
void welcome()
{
    int i,j=1;                                    //菜单选项边框
    color(14);
    for(i=9;i<=20;i++){
        for(j=15;j<=60;j++){
            gotoxy(j,i);
            if(i==9||i==20)printf("=");
            else if(j==15||j==59)printf("||");
        }
    }

    color(12);//红色
    gotoxy(25,12);
    printf("1.开始游戏");                        //菜单选项文字
    gotoxy(40,12);
    printf("2.按键说明");
    gotoxy(25,17);
    printf("3.游戏规则");
    gotoxy(40,17);
    printf("4.退出");

    color(3);
    gotoxy(21,22);
    printf("请选择[1 2 3 4]:[ ]\b\b");
    color(14);
    int n;
    scanf("%d",&n);
    switch(n)
    {
        case 1:
            system("cls");
            DrawGameFrame();
            GameStart();
            break;
        case 2:
            explation();
            break;
        case 3:
            regulation();
            break;
        case 4:
            close();
            break;
    }
}
/**
*绘制游戏窗口
*/
void DrawGameFrame()
{
    color(11);//亮蓝色//打印游戏名称
    gotoxy(FrameX+Frame_width-5,FrameY-2);
    printf("俄罗斯方块");

    color(2);//深绿色//打印右边上下边框
    gotoxy(FrameX+2*Frame_width+3,FrameY+7);
    printf("**********");
    gotoxy(FrameX+2*Frame_width+3,FrameY+13);
    printf("**********");

    color(3);//深蓝绿色
    gotoxy(FrameX+2*Frame_width+13,FrameY+7);
    printf("下一出现方块： ");

    color(14);//黄色
    gotoxy(FrameX+2*Frame_width+3,FrameY+17);
    printf("↑键：旋转");
    gotoxy(FrameX+2*Frame_width+3,FrameY+19);
    printf("空格：暂停游戏");
    gotoxy(FrameX+2*Frame_width+3,FrameY+15);
    printf("Esc：退出游戏");

    color(12);//红色
    gotoxy(FrameX,FrameY);
    printf("╔");
    gotoxy(FrameX+2*Frame_width-2,FrameY);
    printf("╗");
    gotoxy(FrameX,FrameY+Frame_height);
    printf("╚");
    gotoxy(FrameX+2*Frame_width-2,FrameY+Frame_height);
    printf("╝");
    a[FrameX][FrameY+Frame_height]=2;
    a[FrameX+2*Frame_width-2][FrameY+Frame_height]=2;
    int i;
    for(i=2;i<2*Frame_width-2;i+=2){//输出上横框
        gotoxy(FrameX+i,FrameY);
        printf("═");
    }
    for(i=2;i<2*Frame_width-2;i+=2){//输出上横框
        gotoxy(FrameX+i,FrameY+Frame_height);
        printf("═");
        a[FrameX+i][FrameY+Frame_height]=2;
    }
    for(i=1;i<Frame_height;i++){//输出左竖框
        gotoxy(FrameX,FrameY+i);
        printf("║");
        a[FrameX][FrameY+i]=2;
    }
    for(i=1;i<Frame_height;i++){//输出右竖框
        gotoxy(FrameX+2*Frame_width-2,FrameY+i);
        printf("║");
        a[FrameX+2*Frame_width-2][FrameY+i]=2;
    }
    //printf("\n");
}
/**
*制作俄罗斯方块
*/
void MakeTetris(TETRIS *tetris)
{
    a[tetris->x][tetris->y]=b[0];
    switch(tetris->flag)
    {
        case 1:       //■■
        {             //■■
            color(10);
            a[tetris->x][tetris->y-1]=b[1];
            a[tetris->x+2][tetris->y-1]=b[2];
            a[tetris->x+2][tetris->y]=b[3];
            break;
        }
        case 2: //■■■■
        {
            color(13);
            a[tetris->x-2][tetris->y]=b[1];
            a[tetris->x+2][tetris->y]=b[2];
            a[tetris->x+4][tetris->y]=b[3];
            break;
        }
        case 3://■
               //■
               //■
               //■
        {
            color(13);
            a[tetris->x][tetris->y-1]=b[1];
            a[tetris->x][tetris->y-2]=b[2];
            a[tetris->x][tetris->y+1]=b[3];
            break;
        }
        case 4://■■■
               // ■
        {
            color(11);
            a[tetris->x-2][tetris->y]=b[1];
            a[tetris->x+2][tetris->y]=b[2];
            a[tetris->x][tetris->y+1]=b[3];
            break;
        }
        case 5://  ■
               // ■■
               //  ■
        {
            color(11);
            a[tetris->x][tetris->y-1]=b[1];
            a[tetris->x][tetris->y+1]=b[2];
            a[tetris->x-2][tetris->y]=b[3];
            break;
        }
        case 6://  ■
               // ■■■
        {
            color(11);
            a[tetris->x][tetris->y-1]=b[1];
            a[tetris->x-2][tetris->y]=b[2];
            a[tetris->x+2][tetris->y]=b[3];
            break;
        }
        case 7:// ■
               // ■■
               // ■
        {
            color(11);
            a[tetris->x][tetris->y-1]=b[1];
            a[tetris->x][tetris->y+1]=b[2];
            a[tetris->x+2][tetris->y]=b[3];
            break;
        }
        case 8:// ■■
               //  ■■
        {
            color(14);
            a[tetris->x][tetris->y+1]=b[1];
            a[tetris->x-2][tetris->y]=b[2];
            a[tetris->x+2][tetris->y+1]=b[3];
            break;
        }
        case 9://  ■
               // ■■
               // ■
        {
            color(14);
            a[tetris->x][tetris->y-1]=b[1];
            a[tetris->x-2][tetris->y]=b[2];
            a[tetris->x-2][tetris->y+1]=b[3];
            break;
        }
        case 10://  ■■
                // ■■
        {
            color(14);
            a[tetris->x][tetris->y-1]=b[1];
            a[tetris->x-2][tetris->y-1]=b[2];
            a[tetris->x+2][tetris->y]=b[3];
            break;
        }
        case 11:// ■
                // ■■
                //  ■
        {
            color(14);
            a[tetris->x][tetris->y+1]=b[1];
            a[tetris->x-2][tetris->y-1]=b[2];
            a[tetris->x-2][tetris->y]=b[3];
            break;
        }
        case 12:// ■■
                //  ■
                //  ■
        {
            color(12);
            a[tetris->x][tetris->y-1]=b[1];
            a[tetris->x][tetris->y+1]=b[2];
            a[tetris->x-2][tetris->y-1]=b[3];
            break;
        }
        case 13://   ■
                // ■■■
        {
            color(12);
            a[tetris->x-2][tetris->y]=b[1];
            a[tetris->x+2][tetris->y-1]=b[2];
            a[tetris->x+2][tetris->y]=b[3];
            break;
        }
        case 14:// ■
                // ■
                // ■■
        {
            color(12);
            a[tetris->x][tetris->y-1]=b[1];
            a[tetris->x][tetris->y+1]=b[2];
            a[tetris->x+2][tetris->y+1]=b[3];
            break;
        }
        case 15:// ■■■
                // ■
        {
            color(12);
            a[tetris->x-2][tetris->y]=b[1];
            a[tetris->x-2][tetris->y+1]=b[2];
            a[tetris->x+2][tetris->y]=b[3];
            break;
        }
        case 16:// ■■
                // ■
                // ■
        {
            color(12);
            a[tetris->x][tetris->y+1]=b[1];
            a[tetris->x][tetris->y-1]=b[2];
            a[tetris->x+2][tetris->y-1]=b[3];
            break;
        }
        case 17:// ■■■
                //   ■
        {
            color(12);
            a[tetris->x-2][tetris->y]=b[1];
            a[tetris->x+2][tetris->y+1]=b[2];
            a[tetris->x+2][tetris->y]=b[3];
            break;
        }
        case 18://  ■
                //  ■
                // ■■
        {
            color(12);
            a[tetris->x][tetris->y-1]=b[1];
            a[tetris->x][tetris->y+1]=b[2];
            a[tetris->x-2][tetris->y+1]=b[3];
            break;
        }
        case 19:// ■
                // ■■■
        {
            color(12);
            a[tetris->x-2][tetris->y]=b[1];
            a[tetris->x-2][tetris->y-1]=b[2];
            a[tetris->x+2][tetris->y]=b[3];
            break;
        }
    }
}
/**
*打印俄罗斯方块
*/
void PrintTetris(TETRIS *tetris)
{
    for(int i=0;i<4;i++){
        b[i]=1;
    }
    MakeTetris(tetris);
    for(int i=tetris->x-2;i<=tetris->x+4;i+=2){      //遍历方块所有可能出现的位置
        for(int j=tetris->y-2;j<=tetris->y+1;j++){
            if(i==tetris->x&&j==tetris->y)
            {
                gotoxy(i,j);
                printf("■");
            }
            if(
                (tetris->flag==1&&((i==tetris->x&&j==tetris->y-1)||
                (i==tetris->x+2&&j==tetris->y-1)||
                (i==tetris->x+2&&j==tetris->y)))||
               (tetris->flag==2&&((i==tetris->x-2&&j==tetris->y)||
                (i==tetris->x+2&&j==tetris->y)||
                (i==tetris->x+4&&j==tetris->y)))||
               (tetris->flag==3&&((i==tetris->x&&j==tetris->y-1)||
                (i==tetris->x&&j==tetris->y-2)||
                (i==tetris->x&&j==tetris->y+1)))||
               (tetris->flag==4&&((i==tetris->x-2&&j==tetris->y)||
                (i==tetris->x+2&&j==tetris->y)||
                (i==tetris->x&&j==tetris->y+1)))||
               (tetris->flag==5&&((i==tetris->x&&j==tetris->y-1)||
                (i==tetris->x&&j==tetris->y+1)||
                (i==tetris->x-2&&j==tetris->y)))||
               (tetris->flag==6&&((i==tetris->x&&j==tetris->y-1)||
                (i==tetris->x-2&&j==tetris->y)||
                (i==tetris->x+2&&j==tetris->y)))||
               (tetris->flag==7&&((i==tetris->x&&j==tetris->y-1)||
                (i==tetris->x&&j==tetris->y+1)||
                (i==tetris->x+2&&j==tetris->y)))||
               (tetris->flag==8&&((i==tetris->x&&j==tetris->y+1)||
                (i==tetris->x-2&&j==tetris->y)||
                (i==tetris->x+2&&j==tetris->y+1)))||
               (tetris->flag==9&&((i==tetris->x&&j==tetris->y-1)||
                (i==tetris->x-2&&j==tetris->y)||
                (i==tetris->x-2&&j==tetris->y+1)))||
               (tetris->flag==10&&((i==tetris->x&&j==tetris->y-1)||
                (i==tetris->x-2&&j==tetris->y-1)||
                (i==tetris->x+2&&j==tetris->y)))||
               (tetris->flag==11&&((i==tetris->x&&j==tetris->y+1)||
                (i==tetris->x-2&&j==tetris->y-1)||
                (i==tetris->x-2&&j==tetris->y)))||
               (tetris->flag==12&&((i==tetris->x&&j==tetris->y-1)||
                (i==tetris->x&&j==tetris->y+1)||
                (i==tetris->x-2&&j==tetris->y-1)))||
               (tetris->flag==13&&((i==tetris->x-2&&j==tetris->y)||
                (i==tetris->x+2&&j==tetris->y-1)||
                (i==tetris->x+2&&j==tetris->y)))||
               (tetris->flag==14&&((i==tetris->x&&j==tetris->y-1)||
                (i==tetris->x&&j==tetris->y+1)||
                (i==tetris->x+2&&j==tetris->y+1)))||
               (tetris->flag==15&&((i==tetris->x-2&&j==tetris->y)||
                (i==tetris->x-2&&j==tetris->y+1)||
                (i==tetris->x+2&&j==tetris->y)))||
               (tetris->flag==16&&((i==tetris->x&&j==tetris->y+1)||
                (i==tetris->x&&j==tetris->y-1)||
                (i==tetris->x+2&&j==tetris->y-1)))||
               (tetris->flag==17&&((i==tetris->x-2&&j==tetris->y)||
                (i==tetris->x+2&&j==tetris->y+1)||
                (i==tetris->x+2&&j==tetris->y)))||
               (tetris->flag==18&&((i==tetris->x&&j==tetris->y-1)||
                (i==tetris->x&&j==tetris->y+1)||
                (i==tetris->x-2&&j==tetris->y+1)))||
               (tetris->flag==19&&((i==tetris->x-2&&j==tetris->y)||
                (i==tetris->x-2&&j==tetris->y-1)||
                (i==tetris->x+2&&j==tetris->y)))
            )
            {
                gotoxy(i,j);
                printf("■");
            }
        }
    }
    //打印菜单信息
    color(4);
    gotoxy(FrameX+2*Frame_width+3,FrameY+1);
    printf("level : ");
    color(12);
    printf(" %d",tetris->level);

    color(4);
    gotoxy(FrameX+2*Frame_width+3,FrameY+3);
    printf("score : ");
    color(12);
    printf(" %d",tetris->score);

    color(4);
    gotoxy(FrameX+2*Frame_width+3,FrameY+5);
    printf("speed : ");
    color(12);
    printf(" %dms",tetris->speed);
}
/**
*判断是否可移动
*/
int ifMove(TETRIS *tetris)
{
    if(a[tetris->x][tetris->y]!=0){return 0;}
    else
    {
        if(
           (tetris->flag==1&&(a[tetris->x][tetris->y-1]==0&&
            a[tetris->x+2][tetris->y-1]==0&&
            a[tetris->x+2][tetris->y]==0))||
           (tetris->flag==2&&(a[tetris->x-2][tetris->y]==0&&
            a[tetris->x+2][tetris->y]==0&&
            a[tetris->x+4][tetris->y]==0))||
           (tetris->flag==3&&(a[tetris->x][tetris->y-1]==0&&
            a[tetris->x][tetris->y-2]==0&&
            a[tetris->x][tetris->y+1]==0))||
           (tetris->flag==4&&(a[tetris->x-2][tetris->y]==0&&
            a[tetris->x+2][tetris->y]==0&&
            a[tetris->x][tetris->y+1]==0))||
           (tetris->flag==5&&(a[tetris->x][tetris->y-1]==0&&
            a[tetris->x][tetris->y+1]==0&&
            a[tetris->x-2][tetris->y]==0))||
           (tetris->flag==6&&(a[tetris->x][tetris->y-1]==0&&
            a[tetris->x-2][tetris->y]==0&&
            a[tetris->x+2][tetris->y]==0))||
           (tetris->flag==7&&(a[tetris->x][tetris->y-1]==0&&
            a[tetris->x][tetris->y+1]==0&&
            a[tetris->x+2][tetris->y]==0))||
           (tetris->flag==8&&(a[tetris->x][tetris->y+1]==0&&
            a[tetris->x-2][tetris->y]==0&&
            a[tetris->x+2][tetris->y+1]==0))||
           (tetris->flag==9&&(a[tetris->x][tetris->y-1]==0&&
            a[tetris->x-2][tetris->y]==0&&
            a[tetris->x-2][tetris->y+1]==0))||
           (tetris->flag==10&&(a[tetris->x][tetris->y-1]==0&&
            a[tetris->x-2][tetris->y-1]==0&&
            a[tetris->x+2][tetris->y]==0))||
           (tetris->flag==11&&(a[tetris->x][tetris->y+1]==0&&
            a[tetris->x-2][tetris->y-1]==0&&
            a[tetris->x-2][tetris->y]==0))||
           (tetris->flag==12&&(a[tetris->x][tetris->y-1]==0&&
            a[tetris->x][tetris->y+1]==0&&
            a[tetris->x-2][tetris->y-1]==0))||
           (tetris->flag==13&&(a[tetris->x-2][tetris->y]==0&&
            a[tetris->x+2][tetris->y-1]==0&&
            a[tetris->x+2][tetris->y]==0))||
           (tetris->flag==14&&(a[tetris->x][tetris->y-1]==0&&
            a[tetris->x][tetris->y+1]==0&&
            a[tetris->x+2][tetris->y+1]==0))||
           (tetris->flag==15&&(a[tetris->x-2][tetris->y]==0&&
            a[tetris->x-2][tetris->y+1]==0&&
            a[tetris->x+2][tetris->y]==0))||
           (tetris->flag==16&&(a[tetris->x][tetris->y+1]==0&&
            a[tetris->x][tetris->y-1]==0&&
            a[tetris->x+2][tetris->y-1]==0))||
           (tetris->flag==17&&(a[tetris->x-2][tetris->y]==0&&
            a[tetris->x+2][tetris->y+1]==0&&
            a[tetris->x+2][tetris->y]==0))||
           (tetris->flag==18&&(a[tetris->x][tetris->y-1]==0&&
            a[tetris->x][tetris->y+1]==0&&
            a[tetris->x-2][tetris->y+1]==0))||
           (tetris->flag==19&&(a[tetris->x-2][tetris->y]==0&&
            a[tetris->x-2][tetris->y-1]==0&&
            a[tetris->x+2][tetris->y]==0))
           )
        {
            return 1;
        }
    }
    return 0;
}
/**
*能否向下加速移动
*/
int ifDown(TETRIS *tetris)
{
    if(a[tetris->x][tetris->y+1]!=0){return 0;}
    else
    {
        if(
           (tetris->flag==1&&(a[tetris->x][tetris->y]==0&&
            a[tetris->x+2][tetris->y]==0&&
            a[tetris->x+2][tetris->y+1]==0))||
           (tetris->flag==2&&(a[tetris->x-2][tetris->y+1]==0&&
            a[tetris->x+2][tetris->y+1]==0&&
            a[tetris->x+4][tetris->y+1]==0))||
           (tetris->flag==3&&(a[tetris->x][tetris->y]==0&&
            a[tetris->x][tetris->y-1]==0&&
            a[tetris->x][tetris->y+2]==0))||
           (tetris->flag==4&&(a[tetris->x-2][tetris->y+1]==0&&
            a[tetris->x+2][tetris->y+1]==0&&
            a[tetris->x][tetris->y+2]==0))||
           (tetris->flag==5&&(a[tetris->x][tetris->y]==0&&
            a[tetris->x][tetris->y+2]==0&&
            a[tetris->x-2][tetris->y+1]==0))||
           (tetris->flag==6&&(a[tetris->x][tetris->y]==0&&
            a[tetris->x-2][tetris->y+1]==0&&
            a[tetris->x+2][tetris->y+1]==0))||
           (tetris->flag==7&&(a[tetris->x][tetris->y]==0&&
            a[tetris->x][tetris->y+2]==0&&
            a[tetris->x+2][tetris->y+1]==0))||
           (tetris->flag==8&&(a[tetris->x][tetris->y+2]==0&&
            a[tetris->x-2][tetris->y+1]==0&&
            a[tetris->x+2][tetris->y+2]==0))||
           (tetris->flag==9&&(a[tetris->x][tetris->y]==0&&
            a[tetris->x-2][tetris->y+1]==0&&
            a[tetris->x-2][tetris->y+2]==0))||
           (tetris->flag==10&&(a[tetris->x][tetris->y]==0&&
            a[tetris->x-2][tetris->y]==0&&
            a[tetris->x+2][tetris->y+1]==0))||
           (tetris->flag==11&&(a[tetris->x][tetris->y+2]==0&&
            a[tetris->x-2][tetris->y]==0&&
            a[tetris->x-2][tetris->y+1]==0))||
           (tetris->flag==12&&(a[tetris->x][tetris->y]==0&&
            a[tetris->x][tetris->y+2]==0&&
            a[tetris->x-2][tetris->y]==0))||
           (tetris->flag==13&&(a[tetris->x-2][tetris->y+1]==0&&
            a[tetris->x+2][tetris->y]==0&&
            a[tetris->x+2][tetris->y+1]==0))||
           (tetris->flag==14&&(a[tetris->x][tetris->y]==0&&
            a[tetris->x][tetris->y+2]==0&&
            a[tetris->x+2][tetris->y+2]==0))||
           (tetris->flag==15&&(a[tetris->x-2][tetris->y+1]==0&&
            a[tetris->x-2][tetris->y+2]==0&&
            a[tetris->x+2][tetris->y+1]==0))||
           (tetris->flag==16&&(a[tetris->x][tetris->y+2]==0&&
            a[tetris->x][tetris->y]==0&&
            a[tetris->x+2][tetris->y]==0))||
           (tetris->flag==17&&(a[tetris->x-2][tetris->y+1]==0&&
            a[tetris->x+2][tetris->y+2]==0&&
            a[tetris->x+2][tetris->y+1]==0))||
           (tetris->flag==18&&(a[tetris->x][tetris->y]==0&&
            a[tetris->x][tetris->y+2]==0&&
            a[tetris->x-2][tetris->y+2]==0))||
           (tetris->flag==19&&(a[tetris->x-2][tetris->y+1]==0&&
            a[tetris->x-2][tetris->y]==0&&
            a[tetris->x+2][tetris->y+1]==0))
           )
        {
            return 1;
        }
    }
    return 0;
}
/**
*清除方块痕迹
*/
void CleanTetris(TETRIS *tetris)
{
    for(int i=0;i<4;i++){
        b[i]=0;
    }
    MakeTetris(tetris);
    for(int i=tetris->x-2;i<=tetris->x+4;i+=2){
        for(int j=tetris->y-2;j<=tetris->y+1;j++){
            if(a[i][j]==0&&j>FrameY){
                gotoxy(i,j);
                printf("  ");
            }
        }
    }
}
/**
*判断是否满行，若是，删除满行的方块
*/
void Del_FullLine(TETRIS *tetris)
{
    int k,del_rows=0;
    int j;
    for(j=FrameY+Frame_height-1;j>=FrameY+1;j--){
        k=0;
        for(int i=FrameX+2;i<FrameX+2*Frame_width-2;i+=2){
            if(a[i][j]==1){
                k++;//记录此行方块个数
                if(k==Frame_width-2){//判断是否满行
                    for(k=FrameX+2;k<FrameX+2*Frame_width-2;k+=2){//消除满行的方块
                        a[k][j]=0;
                        gotoxy(k,j);
                        printf(" ");
                    }
                    for(k=j-1;k>FrameY;k--){//将被删除行上方的方块下移
                         for(int i=FrameX+2;i<FrameX+2*Frame_width-2;i+=2){
                            if(a[i][k]==1){
                                a[i][k]=0;
                                gotoxy(i,k);
                                printf(" ");
                                a[i][k+1]=1;
                                gotoxy(i,k+1);
                                printf("■");
                            }
                         }
                    }
                    j++;//重新判断被删行是否满行
                    del_rows++;//记录被删行数
                }
            }
        }
    }
    tetris->score+=100*del_rows;
    if(del_rows>0&&(tetris->score%1000==0||tetris->score/1000>tetris->level-1)){
        tetris->speed-=20;
        tetris->level++;
    }
}
/**
*随机产生俄罗斯方块类型序号
*/
void Flag(TETRIS *tetris)
{
    tetris->number ++;
    srand(time(NULL));
    if(tetris->number==1){
        tetris->flag=rand()%19+1;
    }
    tetris->next=rand()%19+1;
}
/**
 * 开始游戏
 */
void GameStart()
{
    int temp1,temp2;
    TETRIS t,*tetris=&t;
    char ch;//用于接收键盘输入
    tetris->number=0;      //初始化游戏
    tetris->speed=300;
    tetris->score=0;
    tetris->level=1;
    int temp;
    while(1)
    {
        Flag(tetris);
        temp=tetris->flag;
        tetris->x=FrameX+2*Frame_width+6;
        tetris->y=FrameY+10;
        tetris->flag=tetris->next;
        PrintTetris(tetris);
        tetris->x=FrameX+Frame_width;
        tetris->y=FrameY-1;
        tetris->flag=temp;
        while(1)
        {
        label:
            PrintTetris(tetris);
            Sleep(tetris->speed);
            CleanTetris(tetris);
            temp1=tetris->x;
            temp2=tetris->flag;
            if(kbhit())//判断是否有输入
            {
                ch=getch();
                if(ch==75)//左键
                {
                    tetris->x-=2;
                }
                if(ch==77)//右键
                {
                    tetris->x+=2;
                }
                if(ch==80)//下键
                {
                    if(ifDown(tetris))
                    {
                        tetris->y+=2;

                    }
                    else
                    {
                        tetris->y=FrameY+Frame_height-2;
                    }
                }
                if(ch==72)//上建//变形
                {
                    if(tetris->flag>=2&&tetris->flag<=3)//直线型
                    {
                        tetris->flag++;
                        tetris->flag%=2;
                        tetris->flag+=2;
                    }
                    if(tetris->flag>=4&&tetris->flag<=7)
                    {
                        tetris->flag++;
                        tetris->flag%=4;
                        tetris->flag+=4;
                    }
                    if(tetris->flag>=8&&tetris->flag<=11)
                    {
                        tetris->flag++;
                        tetris->flag%=4;
                        tetris->flag+=8;
                    }
                    if(tetris->flag>=12&&tetris->flag<=15)
                    {
                        tetris->flag++;
                        tetris->flag%=4;
                        tetris->flag+=12;
                    }
                    if(tetris->flag>=16&&tetris->flag<=19)
                    {
                        tetris->flag++;
                        tetris->flag%=4;
                        tetris->flag+=16;
                    }
                }
                if(ch==32)
                {
                    PrintTetris(tetris);
                    while(1)
                    {
                        if(kbhit())
                        {
                            ch=getch();
                            if(ch==32)
                            {
                                goto label;
                            }
                        }
                    }
                }
                if(ch==27)//Esc
                {
                    system("cls");
                    memset(a,0,6400*sizeof(int));//初始化数组a
                    welcome();
                }
                if(!ifMove(tetris))
                {
                    tetris->x=temp1;
                    tetris->flag=temp2;
                }
                else
                {
                    goto label;
                }
            }
            tetris->y++;
            if(!ifMove(tetris))
            {
                tetris->y--;
                PrintTetris(tetris);
                Del_FullLine(tetris);
                break;
            }
        }
        //游戏结束条件
        for(int i=tetris->y-2;i<tetris->y+2;i++)
        {
            if(i==FrameY)
            {
                system("cls");
                gotoxy(29,7);
                printf("   \n");
                color(12);//红色
                printf("\t\t\t■■■■   ■     ■   ■■     \n");
                printf("\t\t\t■         ■■   ■   ■  ■   \n");
                printf("\t\t\t■■■     ■  ■ ■   ■   ■  \n");
				printf("\t\t\t■         ■   ■■   ■  ■   \n");
				printf("\t\t\t■■■■   ■     ■   ■■     \n");

				color(14);
				gotoxy(17,18);
				printf("我要重新玩一局-------1");
				gotoxy(44,18);
				printf("不玩了，退出吧-------2\n");

				int n;
				gotoxy(32,20);
				printf("选择【1/2】：");
				color(11);
				scanf("%d",&n);
				switch(n)
				{
                    case 1:
                        system("cls");
                        ReStart(tetris);
                        break;
                    case 2:
                        exit(0);
                        break;
				}
            }
        }
        tetris->flag=tetris->next;
        tetris->x=FrameX+2*Frame_width+6;
        tetris->y=FrameY+10;
        CleanTetris(tetris);
    }
}
/**
*重新开始游戏
*/
void ReStart(TETRIS *tetris)
{
    system("cls");
    memset(a,0,6400*sizeof(int));
    DrawGameFrame();
    GameStart();
}
/**
*按键说明界面
*/
void explation()
{
    int i,j=1;
    system("cls");
    color(13);
    gotoxy(32,3);
    printf("按键说明");
    color(2);
    for(i=6;i<=16;i++)
    {
        for(j=15;j<=60;j++)
        {
            gotoxy(j,i);
            if(i==6||i==16)printf("=");
            else if(j==15||j==59)printf("||");
        }
    }
    color(3);
    gotoxy(18,7);
    printf("tip1:玩家可以通过←→方向键来移动方块");
    color(10);
    gotoxy(18,9);
    printf("tip2:通过↑使方块旋转");
    color(14);
    gotoxy(18,11);
    printf("tip3:通过↓加速方块下落（有bug）");
    color(11);
    gotoxy(18,13);
    printf("tip4:按空格键暂停游戏，再按一次游戏继续");
    color(4);
    gotoxy(18,15);
    printf("tip5:Esc键退出游戏");
    getch();
    system("cls");
    main();
}
/**
*游戏规则
*/
void regulation()
{
    system("cls");
    color(13);
    gotoxy(34,3);
    printf("游戏规则");
    color(2);
    for(int i=6;i<=18;i++)
    {
        for(int j=12;j<=70;j++)
        {
            gotoxy(j,i);
            if(i==6||i==18)printf("=");
            else if(j==12||j==69)printf("||");
        }
    }
    color(12);
    gotoxy(16,7);
    printf("tip1: 不同形状的小方块从屏幕上方落下，玩家通过调整");
    gotoxy(22,9);
    printf("方块的位置和方向，使他们在屏幕底部拼出完整的");
    gotoxy(22,11);
    printf("一条或几条");
    color(14);
    gotoxy(16,13);
    printf("tip2: 每消除一行，积分增加100");
    color(11);
    gotoxy(16,15);
    printf("tip3: 每累计1000分，会提升一个等级");
    color(10);
    gotoxy(16,17);
    printf("tip4: 提升等级会使方块下落速度加快，游戏难度加大");
	getch();                //按任意键返回主界面
	system("cls");
	welcome();
}
/**
*退出
*/
void close()
{
    exit(0);
}
