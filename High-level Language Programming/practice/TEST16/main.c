#include <stdio.h>
#include <stdlib.h>
#include <conio.h>
#include <string.h>
#include <time.h>
#include <windows.h>
#include <pthread.h>

#define UI_WXAJ "��Ч������\r"

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

//���ɴ𰸡��ڿա�չʾ���ƶ�����䡢�ж�ʤ��


//�ṹ���塪����������������������������������
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

//����ȫ�ֱ����뺯������������������������������������������

int SHOWANS=0;
int MAP_DIFF=3;

//�û���¼
char USER_NAME[100]="\0";
int USER_COINS=0;
int prethreturn=0;

//��ҳ
int menumax=3;
char menu[][100]={
        ">>        ���� Options     <<",
        ">>    ��ʼ���� Start       <<",
        ">>        �˳� Exit        <<"};

FILE* ff;

void clearscreen();
void showmenu();
void printerr(int option, char* text);
void dooption(int option);
void proexit(int id);
void errexit(int a);
int loginreset(NAME *usernamehead);
int login(NAME *usernamehead);

//����
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

//������������������������������������������������������������������

///ͨ�ú�����������������������������������������������

//ͨ�ñ���ͷ
void title0()
{
    clearscreen();
    printf("��������������������������������������\n");
    printf("             ����\n Ver.0.0.1 2020-12-11\n");
    printf("��������������������������������������\n");
}

//�û�����ͷ
void title()
{
    title0();
    if(ff==NULL)
    {
        printf("�������ļ�ʧ�ܻ��ο�ģʽ�������޷����档\n");
    }else{
        printf("�������%d\n",USER_COINS);
        printf("��ӭ����");puts(USER_NAME);
    }
    printf("��������������������������������������\n");
}

//�������ò��˳�
void proexit(int id)
{
    if(ff!=NULL)
    {
        printf("���ڱ�����Ϣ...\n");
        fclose(ff);
        char tmp[300]="\0";
        strcat(strcat(strcat(tmp,USER_NAME),"_"),DATAFILENAME);
        ff=fopen(tmp,"w");
        if(ff==NULL) {printf("�����û���Ϣʧ��\n");}
        else{
        fprintf(ff,"COINS:%d\n",USER_COINS);
        fprintf(ff,"MAP_DIFF:%d\n",MAP_DIFF);
        fclose(ff);
        printf("�����û���Ϣ���\n");}
    }
    exit(id);
}

//ͨ������
void clearscreen()
{
    system("cls");
}

//ͨ�ã���ӡ������׵׸�����
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

//ͨ�ã��������ɫ�ı�
void printcolor (int mode,int front,int back, char* text)
{
    printf("\033[%d;%d;%dm%s\033[0m",mode,30+front,40+back,text);
}


//ͨ�ã������˳�
void errexit(int a)
{
    printf("�ڴ治�㣬����ռ�ʧ�ܣ����򼴽��˳���\n");
    proexit(a);
}


///���ú�����������������������������������

//���ã�ˢ����Ļ
void mapsetscreen(int option)
{
    title();
    printf("��Ϸ����\n");
    printf("�뿪��д������%c���˳���%c������\n%c/%c������ѡ��%c�ɽ��и���\n���Ľ����´�����ʱ��Ч\n",B_QUIT,B_BACK,B_UP,B_DOWN,B_CONFIRM);
    printf("��������������������\n");
    for(int i=0;i<GAMESETMAPMENUMAX;i++)
    {
        switch(i)
        {
        case 0:
            printf("�Ѷ�(1-10)��%d    ",MAP_DIFF);
            break;
        }
        if(option==i) printerr(0,"<<<");
        else printf("\n");
    }


}

//���ã�Ӧ������
void domapset(int option)
{
    int input=0;
    mapsetscreen(option);
    printf("\n\n����������������������������\n");
    switch(option)
    {
    case 0:
        while(input<1||input>10)
        {
            printf("�����������õ��Ѷ�(1-10)��");
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

//���ã�������
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

///��ҳ������������������������������������������������

//��ҳ����ʾ�˵�������������
void showmenu(int option)
{
    /**
    ��ʽ��\033[��ʾ��ʽ;ǰ��ɫ;����ɫm
    ˵����
    ǰ��ɫ            ����ɫ           ��ɫ
    ---------------------------------------
    30                40              ��ɫ
    31                41              ��ɫ
    32                42              ��ɫ
    33                43              �Sɫ
    34                44              ��ɫ
    35                45              �Ϻ�ɫ
    36                46              ����ɫ
    37                47              ��ɫ
    ��ʾ��ʽ           ����
    -------------------------
    0                �ն�Ĭ������
    1                ������ʾ
    4                ʹ���»���
    5                ��˸
    7                ������ʾ
    8                ���ɼ�
	printf("\n\033[1;32;40m�ڽ�������ģʽ�¿ɼ�Ϊ��ɫ�Ӵ�����\033[0m\n\n");
	printf("\033[1;31;40m�ڽ�������ģʽ�¿ɼ�Ϊ��ɫ�Ӵ�����\033[0m\n\n");
	printf("\033[7m�ڽ�������ģʽ�¿ɼ�Ϊ�׵׺���\033[0m\n\n");
	printf("\033[4m�ڽ�������ģʽ�¿ɼ�Ϊ�»���\033[0m\n\n");
	printf("\033[0;30;41m�ڽ�������ģʽ�¿ɼ�Ϊ��׺���\033[0m\n\n");
**/
    title();
    printf("�����������뿪��д������%c���˳���%c��ȷ�ϣ�%c/%c������ѡ��\n",B_BACK,B_CONFIRM,B_UP,B_DOWN);
    printf("��������������������������������������\n");
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


//��ҳ��ѡ��ѡ�����
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

//����������
int main()
{
    int doid=0;
    int option=0;
    NAME usernamehead;
    printf("������...");
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

///��¼������������������������

//��¼����ʼ��
int loginreset(NAME *usernamehead)
{
    clearscreen();
    FILE* fileuser;
    NAME *usernamen;
    fileuser=fopen(DATAUSERNAME,"r");

    strcpy(usernamehead->name,"����û�");
    usernamehead->next=malloc(sizeof(NAME));
    usernamen=usernamehead->next;
    strcpy(usernamen->name,"�ο�");
    usernamen->next=NULL;
    if(fileuser==NULL)
    {
        fileuser=fopen(DATAUSERNAME,"w");
        if(fileuser==NULL)
        {
            printf("�������ļ�ʧ�ܣ�����%s�ļ��Ƿ�ռ�ã������������û���Ϣ�޷����棬����Y�������������˳�",DATAUSERNAME);
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


//��¼��������
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
        printf("�����������뿪��д������%c���˳���%c��ȷ�ϣ�%c/%c������ѡ��%c��ɾ���û�\n",B_QUIT,B_CONFIRM,B_UP,B_DOWN,B_SHOWANS);
        printf("��������������������������\n");
        printf("��ӭ������ѡ�������û�����\n");
         printf("��������������������������\n");
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
                            printf("������������������������\n");
                            //printf("\n");
                            char tmp[300]="\0";
                            strcat(strcat(strcat(tmp,"ȷʵҪɾ���û�:"),usernamen->next->name),"�𣿸��û���������Ϣ������ա�����Yȷ�ϣ�������ȡ��\n");
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
                    printf("������������������������\n");
                    printf("��������Ҫ�������û�����������100���ַ���\n");
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

                //�½��û�
            }else if(opt==1){//�ο�
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
            strcat(strcat(strcat(a," �������ļ�ʧ�ܣ�����"),tmp),"�ļ��Ƿ�ռ�ã������������û���Ϣ�޷����룬����Y�������������˳�");
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
        printf("\n���ɴ�������Y���ԣ����������ء�");
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
                printf("��ʤ���ˡ�\n");
                USER_COINS+=MAP_DIFF*3;
                printf("����Y���¿�ʼ�����������ء�");
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
//�ƶ�ѡ����
int sdmove(INFO* map,int dx,int dy)
{
    if(map->x+dx<0 || map->y+dy<0 || map->y+dy>=MAPL || map->x+dx>=MAPL) return 0;

    map->x+=dx;
    map->y+=dy;
    SHOWANS=0;
    sdscreen(map);
    return 0;
}


//���������������
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

//������Ϸ���ڿ�
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


//��ʾ��
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

//����ͨ�ñ���
void sdtitle(){
    title();
    printf("����\n%c%c%c%c������ѡ��\n���ּ����룬%c��ɾ��\n",B_UP,B_LEFT,B_DOWN,B_RIGHT,B_0);
    printf("�뿪��д������%c���˳���%c�����أ�%c�����¿�ʼ\n��%c���������Ѷȣ�%c������ʾ/���ش𰸣�����3��ң�\n",B_QUIT,B_BACK,B_RESTART,B_SET,B_SHOWANS);
    printf("��������������������������������\n");
}

//ˢ����ʾ
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
//��������
int sdput(INFO* map, int number){
    int t=map->map[map->x][map->y];
    if(t < 10 && t > 0 ) return 0;
    if(t < 30 && t >= 10 || t == 0 ) map->map[map->x][map->y] = number+10;
}

//�ж�ʤ��
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

