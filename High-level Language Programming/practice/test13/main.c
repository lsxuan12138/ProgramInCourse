#include <stdio.h>
#include <windows.h>
#include <conio.h>
#include <time.h>
/*****�궨��******/
#define FrameX 13          //��Ϸ�������Ͻ�x������Ϊ13
#define FrameY 3           //��Ϸ�������Ͻ�y������Ϊ13
#define Frame_height 20    //��Ϸ���ڸ߶�Ϊ20
#define Frame_width 18     //��Ϸ���ڿ��Ϊ20
/*******ȫ�ֱ���**********/
int a[80][80]={0};
//�����Ϸ�����ڵ�ͼ����2��1��0�ֱ��ʾ��λ��Ϊ��Ϸ�߿򡢷��顢��ͼ������ʼ��Ϊ��ͼ��
int b[4];//���4�����ڡ����飬1��ʾ�з��飬0��ʾ�޷���
typedef struct Tetris
{
    int x;                   //���ķ���x������
    int y;                   //���ķ���y������
    int flag;                //��Ƿ������͵����
    int next;                //�����һ���������͵����
    int speed;               //�����ƶ��ٶ�
    int number;              //��������˹����ĸ���
    int score;               //��Ϸ����
    int level;               //��Ϸ�ȼ�
}TETRIS;
HANDLE hOut;                 //???����̨���
/******��������******/
int color(int c);
void gotoxy(int x,int y);         //��ȡ��Ļ���λ��
void title ();                    //��ӭ�����Ϸ��ı���
void flower();                    //�����ַ���
void welcome();                   //�˵�ѡ��ͱ߿�
void DrawGameFrame();             //������Ϸ����
void MakeTetris(TETRIS *tetris);  //��������˹����
void PrintTetris(TETRIS *tetris); //��ӡ����˹����
int ifMove(TETRIS *tetris);       //�ж��Ƿ���ƶ�
int ifDown(TETRIS *tetris);
void CleanTetris(TETRIS *tetris); //�������ۼ�
void Del_FullLine(TETRIS *tetris);//�ж��Ƿ����У����ǣ�ɾ�����еķ���
void Flag(TETRIS *tetris);        //�����������˹�����������
void GameStart();                 //��ʼ��Ϸ
void ReStart(TETRIS *tetris);     //���¿�ʼ��Ϸ
void explation();                 //����˵������
void regulation();                //����˵������
void close();                     //�˳�
int main()//������
{
    title();
    flower();
    welcome();
    return 0;
}
/**
*������ɫ����
*/
int color(int c)
{
    SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE),c);
    //SetConsoleTextAttribute���������ÿ���̨����������ɫ�ͱ�����ɫ�ĺ���
    //GetStdHandle�ǻ�����룬�����������Ļ�������ľ��������
    //GetStdHandle����ֵӦΪ��STD_OUTPUT_HANDLE����STD_INPUT_HANDLE����STD_ERROR_HANDLE��
    return 0;
}
/**
*��ȡ��Ļ���λ��
*/
void gotoxy(int x,int y)
{
    COORD pos;
    pos.X=x;
    pos.Y=y;
    SetConsoleCursorPosition(GetStdHandle(STD_OUTPUT_HANDLE),pos);
    //SetConsoleCursorPosition������λ���λ��
    //����ͨ���޸�pos.X��pos.Y���޸Ĺ��λ��
}
/**
*��ӭ�����Ϸ��ı���
*/
void title ()
{
    color(15);                    //����ɫ
    gotoxy(24,3);
    printf("Ȥ ζ �� �� ˹ �� ��\n");
    color(11);                    //����ɫ
    gotoxy(18,5);
    printf("��");
    gotoxy(18,6);
    printf("����");
    gotoxy(18,7);
    printf("��");

    color(14);                    //��ɫ
    gotoxy(26,6);
    printf("����");
    gotoxy(28,7);
    printf("����");

    color(10);                   //��ɫ
    gotoxy(36,6);
    printf("����");
    gotoxy(36,7);
    printf("����");

    color(13);                  //��ɫ
    gotoxy(45,5);
    printf("��");
    gotoxy(45,6);
    printf("��");
    gotoxy(45,7);
    printf("��");
    gotoxy(45,8);
    printf("��");

    color(12);
    gotoxy(56,6);
    printf("��");
    gotoxy(52,7);
    printf("������");
}
/**
*�����ַ���
*/
void flower()
{
    color(12);//��ɫ
    gotoxy(66,11);//�ĸ�����
    printf("(_)");

    gotoxy(64,12);
    printf("(_)");

    gotoxy(68,12);
    printf("(_)");

    gotoxy(66,13);
    printf("(_)");

    color(6);//����컨����
    gotoxy(67,12);
    printf("@");

    color(13);//��ɫ
    gotoxy(72,10);//�ĸ�����
    printf("(_)");

    gotoxy(76,10);
    printf("(_)");

    gotoxy(74,9);
    printf("(_)");

    gotoxy(74,11);
    printf("(_)");

    color(6);//����ۻ�����
    gotoxy(75,10);
    printf("@");

    gotoxy(71,12);//������仨֮�������
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

    color(10);//����ݵ�
    gotoxy(67,17);
    printf("\\\\\\\\");
    gotoxy(73,17);
    printf("//");

    color(2);
    gotoxy(67,18);
    printf("^^^^^^^^");

    color(10);
    gotoxy(67,19);
    printf(" �� �� ");
    gotoxy(68,20);
    printf("lsx");
}
/**
*�˵�
*/
void welcome()
{
    int i,j=1;                                    //�˵�ѡ��߿�
    color(14);
    for(i=9;i<=20;i++){
        for(j=15;j<=60;j++){
            gotoxy(j,i);
            if(i==9||i==20)printf("=");
            else if(j==15||j==59)printf("||");
        }
    }

    color(12);//��ɫ
    gotoxy(25,12);
    printf("1.��ʼ��Ϸ");                        //�˵�ѡ������
    gotoxy(40,12);
    printf("2.����˵��");
    gotoxy(25,17);
    printf("3.��Ϸ����");
    gotoxy(40,17);
    printf("4.�˳�");

    color(3);
    gotoxy(21,22);
    printf("��ѡ��[1 2 3 4]:[ ]\b\b");
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
*������Ϸ����
*/
void DrawGameFrame()
{
    color(11);//����ɫ//��ӡ��Ϸ����
    gotoxy(FrameX+Frame_width-5,FrameY-2);
    printf("����˹����");

    color(2);//����ɫ//��ӡ�ұ����±߿�
    gotoxy(FrameX+2*Frame_width+3,FrameY+7);
    printf("**********");
    gotoxy(FrameX+2*Frame_width+3,FrameY+13);
    printf("**********");

    color(3);//������ɫ
    gotoxy(FrameX+2*Frame_width+13,FrameY+7);
    printf("��һ���ַ��飺 ");

    color(14);//��ɫ
    gotoxy(FrameX+2*Frame_width+3,FrameY+17);
    printf("��������ת");
    gotoxy(FrameX+2*Frame_width+3,FrameY+19);
    printf("�ո���ͣ��Ϸ");
    gotoxy(FrameX+2*Frame_width+3,FrameY+15);
    printf("Esc���˳���Ϸ");

    color(12);//��ɫ
    gotoxy(FrameX,FrameY);
    printf("�X");
    gotoxy(FrameX+2*Frame_width-2,FrameY);
    printf("�[");
    gotoxy(FrameX,FrameY+Frame_height);
    printf("�^");
    gotoxy(FrameX+2*Frame_width-2,FrameY+Frame_height);
    printf("�a");
    a[FrameX][FrameY+Frame_height]=2;
    a[FrameX+2*Frame_width-2][FrameY+Frame_height]=2;
    int i;
    for(i=2;i<2*Frame_width-2;i+=2){//����Ϻ��
        gotoxy(FrameX+i,FrameY);
        printf("�T");
    }
    for(i=2;i<2*Frame_width-2;i+=2){//����Ϻ��
        gotoxy(FrameX+i,FrameY+Frame_height);
        printf("�T");
        a[FrameX+i][FrameY+Frame_height]=2;
    }
    for(i=1;i<Frame_height;i++){//���������
        gotoxy(FrameX,FrameY+i);
        printf("�U");
        a[FrameX][FrameY+i]=2;
    }
    for(i=1;i<Frame_height;i++){//���������
        gotoxy(FrameX+2*Frame_width-2,FrameY+i);
        printf("�U");
        a[FrameX+2*Frame_width-2][FrameY+i]=2;
    }
    //printf("\n");
}
/**
*��������˹����
*/
void MakeTetris(TETRIS *tetris)
{
    a[tetris->x][tetris->y]=b[0];
    switch(tetris->flag)
    {
        case 1:       //����
        {             //����
            color(10);
            a[tetris->x][tetris->y-1]=b[1];
            a[tetris->x+2][tetris->y-1]=b[2];
            a[tetris->x+2][tetris->y]=b[3];
            break;
        }
        case 2: //��������
        {
            color(13);
            a[tetris->x-2][tetris->y]=b[1];
            a[tetris->x+2][tetris->y]=b[2];
            a[tetris->x+4][tetris->y]=b[3];
            break;
        }
        case 3://��
               //��
               //��
               //��
        {
            color(13);
            a[tetris->x][tetris->y-1]=b[1];
            a[tetris->x][tetris->y-2]=b[2];
            a[tetris->x][tetris->y+1]=b[3];
            break;
        }
        case 4://������
               // ��
        {
            color(11);
            a[tetris->x-2][tetris->y]=b[1];
            a[tetris->x+2][tetris->y]=b[2];
            a[tetris->x][tetris->y+1]=b[3];
            break;
        }
        case 5://  ��
               // ����
               //  ��
        {
            color(11);
            a[tetris->x][tetris->y-1]=b[1];
            a[tetris->x][tetris->y+1]=b[2];
            a[tetris->x-2][tetris->y]=b[3];
            break;
        }
        case 6://  ��
               // ������
        {
            color(11);
            a[tetris->x][tetris->y-1]=b[1];
            a[tetris->x-2][tetris->y]=b[2];
            a[tetris->x+2][tetris->y]=b[3];
            break;
        }
        case 7:// ��
               // ����
               // ��
        {
            color(11);
            a[tetris->x][tetris->y-1]=b[1];
            a[tetris->x][tetris->y+1]=b[2];
            a[tetris->x+2][tetris->y]=b[3];
            break;
        }
        case 8:// ����
               //  ����
        {
            color(14);
            a[tetris->x][tetris->y+1]=b[1];
            a[tetris->x-2][tetris->y]=b[2];
            a[tetris->x+2][tetris->y+1]=b[3];
            break;
        }
        case 9://  ��
               // ����
               // ��
        {
            color(14);
            a[tetris->x][tetris->y-1]=b[1];
            a[tetris->x-2][tetris->y]=b[2];
            a[tetris->x-2][tetris->y+1]=b[3];
            break;
        }
        case 10://  ����
                // ����
        {
            color(14);
            a[tetris->x][tetris->y-1]=b[1];
            a[tetris->x-2][tetris->y-1]=b[2];
            a[tetris->x+2][tetris->y]=b[3];
            break;
        }
        case 11:// ��
                // ����
                //  ��
        {
            color(14);
            a[tetris->x][tetris->y+1]=b[1];
            a[tetris->x-2][tetris->y-1]=b[2];
            a[tetris->x-2][tetris->y]=b[3];
            break;
        }
        case 12:// ����
                //  ��
                //  ��
        {
            color(12);
            a[tetris->x][tetris->y-1]=b[1];
            a[tetris->x][tetris->y+1]=b[2];
            a[tetris->x-2][tetris->y-1]=b[3];
            break;
        }
        case 13://   ��
                // ������
        {
            color(12);
            a[tetris->x-2][tetris->y]=b[1];
            a[tetris->x+2][tetris->y-1]=b[2];
            a[tetris->x+2][tetris->y]=b[3];
            break;
        }
        case 14:// ��
                // ��
                // ����
        {
            color(12);
            a[tetris->x][tetris->y-1]=b[1];
            a[tetris->x][tetris->y+1]=b[2];
            a[tetris->x+2][tetris->y+1]=b[3];
            break;
        }
        case 15:// ������
                // ��
        {
            color(12);
            a[tetris->x-2][tetris->y]=b[1];
            a[tetris->x-2][tetris->y+1]=b[2];
            a[tetris->x+2][tetris->y]=b[3];
            break;
        }
        case 16:// ����
                // ��
                // ��
        {
            color(12);
            a[tetris->x][tetris->y+1]=b[1];
            a[tetris->x][tetris->y-1]=b[2];
            a[tetris->x+2][tetris->y-1]=b[3];
            break;
        }
        case 17:// ������
                //   ��
        {
            color(12);
            a[tetris->x-2][tetris->y]=b[1];
            a[tetris->x+2][tetris->y+1]=b[2];
            a[tetris->x+2][tetris->y]=b[3];
            break;
        }
        case 18://  ��
                //  ��
                // ����
        {
            color(12);
            a[tetris->x][tetris->y-1]=b[1];
            a[tetris->x][tetris->y+1]=b[2];
            a[tetris->x-2][tetris->y+1]=b[3];
            break;
        }
        case 19:// ��
                // ������
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
*��ӡ����˹����
*/
void PrintTetris(TETRIS *tetris)
{
    for(int i=0;i<4;i++){
        b[i]=1;
    }
    MakeTetris(tetris);
    for(int i=tetris->x-2;i<=tetris->x+4;i+=2){      //�����������п��ܳ��ֵ�λ��
        for(int j=tetris->y-2;j<=tetris->y+1;j++){
            if(i==tetris->x&&j==tetris->y)
            {
                gotoxy(i,j);
                printf("��");
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
                printf("��");
            }
        }
    }
    //��ӡ�˵���Ϣ
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
*�ж��Ƿ���ƶ�
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
*�ܷ����¼����ƶ�
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
*�������ۼ�
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
*�ж��Ƿ����У����ǣ�ɾ�����еķ���
*/
void Del_FullLine(TETRIS *tetris)
{
    int k,del_rows=0;
    int j;
    for(j=FrameY+Frame_height-1;j>=FrameY+1;j--){
        k=0;
        for(int i=FrameX+2;i<FrameX+2*Frame_width-2;i+=2){
            if(a[i][j]==1){
                k++;//��¼���з������
                if(k==Frame_width-2){//�ж��Ƿ�����
                    for(k=FrameX+2;k<FrameX+2*Frame_width-2;k+=2){//�������еķ���
                        a[k][j]=0;
                        gotoxy(k,j);
                        printf(" ");
                    }
                    for(k=j-1;k>FrameY;k--){//����ɾ�����Ϸ��ķ�������
                         for(int i=FrameX+2;i<FrameX+2*Frame_width-2;i+=2){
                            if(a[i][k]==1){
                                a[i][k]=0;
                                gotoxy(i,k);
                                printf(" ");
                                a[i][k+1]=1;
                                gotoxy(i,k+1);
                                printf("��");
                            }
                         }
                    }
                    j++;//�����жϱ�ɾ���Ƿ�����
                    del_rows++;//��¼��ɾ����
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
*�����������˹�����������
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
 * ��ʼ��Ϸ
 */
void GameStart()
{
    int temp1,temp2;
    TETRIS t,*tetris=&t;
    char ch;//���ڽ��ռ�������
    tetris->number=0;      //��ʼ����Ϸ
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
            if(kbhit())//�ж��Ƿ�������
            {
                ch=getch();
                if(ch==75)//���
                {
                    tetris->x-=2;
                }
                if(ch==77)//�Ҽ�
                {
                    tetris->x+=2;
                }
                if(ch==80)//�¼�
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
                if(ch==72)//�Ͻ�//����
                {
                    if(tetris->flag>=2&&tetris->flag<=3)//ֱ����
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
                    memset(a,0,6400*sizeof(int));//��ʼ������a
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
        //��Ϸ��������
        for(int i=tetris->y-2;i<tetris->y+2;i++)
        {
            if(i==FrameY)
            {
                system("cls");
                gotoxy(29,7);
                printf("   \n");
                color(12);//��ɫ
                printf("\t\t\t��������   ��     ��   ����     \n");
                printf("\t\t\t��         ����   ��   ��  ��   \n");
                printf("\t\t\t������     ��  �� ��   ��   ��  \n");
				printf("\t\t\t��         ��   ����   ��  ��   \n");
				printf("\t\t\t��������   ��     ��   ����     \n");

				color(14);
				gotoxy(17,18);
				printf("��Ҫ������һ��-------1");
				gotoxy(44,18);
				printf("�����ˣ��˳���-------2\n");

				int n;
				gotoxy(32,20);
				printf("ѡ��1/2����");
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
*���¿�ʼ��Ϸ
*/
void ReStart(TETRIS *tetris)
{
    system("cls");
    memset(a,0,6400*sizeof(int));
    DrawGameFrame();
    GameStart();
}
/**
*����˵������
*/
void explation()
{
    int i,j=1;
    system("cls");
    color(13);
    gotoxy(32,3);
    printf("����˵��");
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
    printf("tip1:��ҿ���ͨ��������������ƶ�����");
    color(10);
    gotoxy(18,9);
    printf("tip2:ͨ����ʹ������ת");
    color(14);
    gotoxy(18,11);
    printf("tip3:ͨ�������ٷ������䣨��bug��");
    color(11);
    gotoxy(18,13);
    printf("tip4:���ո����ͣ��Ϸ���ٰ�һ����Ϸ����");
    color(4);
    gotoxy(18,15);
    printf("tip5:Esc���˳���Ϸ");
    getch();
    system("cls");
    main();
}
/**
*��Ϸ����
*/
void regulation()
{
    system("cls");
    color(13);
    gotoxy(34,3);
    printf("��Ϸ����");
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
    printf("tip1: ��ͬ��״��С�������Ļ�Ϸ����£����ͨ������");
    gotoxy(22,9);
    printf("�����λ�úͷ���ʹ��������Ļ�ײ�ƴ��������");
    gotoxy(22,11);
    printf("һ������");
    color(14);
    gotoxy(16,13);
    printf("tip2: ÿ����һ�У���������100");
    color(11);
    gotoxy(16,15);
    printf("tip3: ÿ�ۼ�1000�֣�������һ���ȼ�");
    color(10);
    gotoxy(16,17);
    printf("tip4: �����ȼ���ʹ���������ٶȼӿ죬��Ϸ�ѶȼӴ�");
	getch();                //�����������������
	system("cls");
	welcome();
}
/**
*�˳�
*/
void close()
{
    exit(0);
}
