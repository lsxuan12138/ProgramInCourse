//#include <stdio.h>
#include <stdlib.h>

/* run this program using the console pauser or add your own getch, system("pause") or input loop */

/*******�� �� ͷ �� ��*******/
#include <stdio.h>		//��׼������������⣨printf��scanf��
#include <conio.h>  	//Ϊ�˶�ȡ�����
#include <windows.h>	//����dos���棨��ȡ����̨������λ�á�����������ɫ��
#include <math.h>   	//pow����
#include <time.h>
/*******��  ��  ȫ  ��  ��  ��*******/
int step=0;				//��ִ�в���
int score=0;				//�洢��Ϸ����
long int Time;				//��Ϸ����ʱ��
int BOX[4][4]={0,0,0,0, 	//��Ϸ�е�16������
               0,0,0,0,
               0,0,0,0,
               0,0,0,0};
HANDLE hOut;				//����̨���

/*******��  ��  ��  ��*******/
void gotoxy(int x, int y); 		//��ȡ��Ļ���λ��
int color(int c);               	//����������ɫ
int	TextColors(int i);				//���������޸���ɫ
void drawTheGameBox(); 			//������Ϸ����
int *add(int item[]);   			//�ϲ�����
int ifMove(int item[]); 			//�ж��Ƿ��ܹ��ƶ�����,����1�ܶ���0���ܶ�
void Gameplay();    				//��ʼ��Ϸ
void Replay();			       		//������Ϸ
int if2n(int x);         			//�ж�x�Ƿ���2��n�η�
//�ж��Ƿ��ܹ����ƣ��������ƣ������е������������2��n�η������򷵻�1�������ƶ����򷵻�0
int ifup();
//�ж��Ƿ��ܹ����ƣ��������ƣ������е������������2��n�η������򷵻�1�������ƶ����򷵻�0
int ifdown();
//�ж��Ƿ��ܹ����ƣ��������ƣ������е������������2��n�η������򷵻�1�������ƶ����򷵻�0
int ifleft();
//�ж��Ƿ��ܹ����ƣ��������ƣ������е������������2��n�η������򷵻�1�������ƶ����򷵻�0
int ifright();
int BOXmax();						//�������������
int Gamefaile();					//�ж��Ƿ�ʧ��
int Gamewin();						//�ж��Ƿ�ʤ��
int keyboardControl(int key);   	//���̿����ƶ�
void close();                   	//�ر���Ϸ
void title();                   	//���Ʊ��⣨2048�ַ�����
void choice();                  	//ѡ���
void Rabbit();						//�����ַ���
void regulation();              	//��Ϸ�������
void explation();               	//����˵��

/**
 * ��ȡ��Ļ���λ��
 */
void gotoxy(int x, int y)
{
    COORD c;
    c.X = x;
    c.Y = y;
    SetConsoleCursorPosition(GetStdHandle(STD_OUTPUT_HANDLE), c);     //��λ���λ��
}

/**
 * ������ɫ����
 */
int color(int c)
{
	SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), c);        //����������ɫ
	return 0;
}

/**
 * ���ñ���
 */
void title()
{
	color(11);          						//ǳ����ɫ
    gotoxy(19,2);
    printf("������   ��������   ��  ��     ��������"); 	//���2048�ַ���
    gotoxy(19,3);
    printf("    ��   ��    ��   ��  ��     ��    ��");
    gotoxy(19,4);
    printf("������   ��    ��   ��  ��     ��������");
    gotoxy(19,5);
    printf("��       ��    ��   ��������   ��    ��");
    gotoxy(19,6);
    printf("������   ��������       ��     ��������");
}

/**
 * �˵�ѡ��
 */
void choice()
{
	int n;
    int i,j = 1;
    gotoxy(32,8) ;
    color(13);
    printf("    2 0 4 8");
	color(14);          					//��ɫ�߿�
    for (i = 9; i <= 20; i++)   			//������±߿�===
	{
		for (j = 15; j <= 60; j++)  	//������ұ߿�||
		{
			gotoxy(j, i);
			if (i == 9 || i == 20) printf("=");
			else if (j == 15 || j == 59) printf("||");
		}
	}

	color(12);                              //��ɫ����
	gotoxy(25, 12);                        //������ʾλ��
	printf("1.��ʼ��Ϸ");
	gotoxy(40, 12);
	printf("2.��Ϸ����");
	gotoxy(25, 16);
	printf("3.����˵��");
	gotoxy(40, 16);
	printf("4.�˳�");
	gotoxy(21,22);
	color(5);                             //����ɫ����
	printf("��ѡ��[1 2 3 4]:[ ]\b\b");
    scanf("%d", &n);    					 //����ѡ��
    switch (n)                            //��֧ѡ��
    {
    	case 1:                          //��������"1"
    		Gameplay();
        	break;
    	case 2:                          //��������"2"
    		regulation();
        	break;
    	case 3:							//��������"3"
			explation();
        	break;
    	case 4:                          //��������"4"
       	 	exit(0);    					//�˳���Ϸ
        	break;
    }
}

/**
 * ����С����
 */
void Rabbit()
{
	color(3);
	gotoxy(64,10);
	printf("��-��  ��-��");		//��ӡ���Ӷ���
	gotoxy(64,11);
	printf("�� ��  �� ��");
	gotoxy(64,12);
	printf("�� ��  �� ��");
	gotoxy(63,13);
	printf("_��");					//��ӡ������
	gotoxy(74,13);
	printf("��_");
	gotoxy(62,14);
	printf("��");
	gotoxy(76,14);
	printf("��");
	gotoxy(62,15);
	printf("��            ��");
	gotoxy(62,16);
	printf("��");
	gotoxy(76,16);
	printf("��");
	gotoxy(62,17);
	printf("��            ��");	//��ӡ������
	gotoxy(62,18);
	printf("�����ש����ש���");	//��ӡ�°�
	gotoxy(64,19);
	printf("����    �ǩ�");		//��ӡ�첲
	gotoxy(62,20);
	printf("-�ީߩ��ߩߩ�-");
	color(5);
	gotoxy(67,13);
	printf("`~�]~`");				//��ӡ����
	color(4);
	gotoxy(67,14);
	printf("��   ��");				//��ӡ�۾�
	color(13);
	gotoxy(65,15);
	printf("��");					//��ӡ����
	gotoxy(74,15);
	printf("��");
	color(12);
	gotoxy(69,17);
	printf("��");					//��ӡ���
}
/**
 * ���������������
 */
int main() {

    title();            //��ӡ����
    Rabbit();           //��ӡ�ַ���
    choice();           //�˵�ѡ��
}

/**
 * ������Ϸ����4��4������
 */
void drawTheGameBox()
{
	int i,j,k;
	color(14);       			//��ɫ
	for(j = 2;j <= 22;j += 5)                                  //��ӡ���̱߿�
	{
		gotoxy(15,j);
		for(k = 1;k<42;k++)
		{
			printf("-");	//��ӡ���̵ĺ���߿�
		}
		printf("\n");
	}
	for (i = 3;i < 7;i ++)
	{
		gotoxy(15,i);		//9���ո�
		printf("|         |         |         |         | ");	 //��ӡ���̵�����߿�
	}
	for (i = 8;i<12;i++)
	{
		gotoxy(15,i);
		printf("|         |         |         |         | ");
	}
	for (i = 13;i<17;i++)
	{
		gotoxy(15,i);
		printf("|         |         |         |         | ");	 //��ӡ���̵�����߿�
	}
	for (i = 8;i<12;i++)
	{
		gotoxy(15,i);
		printf("|         |         |         |         | ");
	}
	for (i = 13;i<17;i++)
	{
		gotoxy(15,i);
		printf("|         |         |         |         | ");
	}
	for (i = 18;i<22;i++)
	{
		gotoxy(15,i);
		printf("|         |         |         |         | ");
	}
	gotoxy(16,1);   								//��Ļ����λ��
	color(11);      								//��ǳ��ɫ
	printf("��Ϸ����: %d",score);

	color(13);      								//��ɫ
	gotoxy(42,1);   								//��Ļ����λ��
	printf("ִ�в���: %d\n",step);

    gotoxy(44,23);
	color(10);                                  	//��ɫ
	printf("����ʱ��%lld s",time(NULL) - Time);   //�����Ϸ����ʱ��

}

/**
 * ���������޸���ɫ
 */
int TextColors(int number)
{
	switch (number)         		//�����г���2n��0<n<12����������ʾΪ��ͬ��ɫ
	{   //����1~15����ͬ��������ɫ������15��ʾ���ֱ���ɫ
		case 2:						//����2
			return color(12);		//��ʾɫ��Ϊ12����ɫ����ɫ
			break;
		case 4: 					//����4
			return color(11); 		//��ʾɫ��Ϊ11����ɫ������ɫ
			break;
		case 8:
			return color(10);
			break;
		case 16:
			return color(14);
			break;
		case 32:
			return color(6);
			break;
		case 64:
			return color(5);
			break;
		case 128:
			return color(4);
			break;
		case 256:
			return color(3);
			break;
		case 512:
			return color(2);
			break;
		case 1024:
			return color(9);
			break;
		case 2048:
			return color(7);
			break;
		default:
			break;
	}
	return 0;
}

/**
 * �ϲ�����
 */
int* add(int item[])
{
    int i = 0, j = 0;
    int tep[4] = {0, 0, 0, 0}, tmp[4] = {0, 0, 0, 0};
    for(i = 0; i < 4; i ++)
	{
        if(item[i] != 0)					//������������������
		{
            tep[j ++] = item[i];
        }
    }
	//���������ڵ���ͬ����������
    for(i = 0; i < 4; i ++)
	{
        if(tep[i] == tep[i + 1])			//�������������ͬ����ϲ�
		{
            tep[i] *= 2;        			//һ�������е����ַ�������һ��Ϊ��
            tep[i + 1] = 0;
            score=score+tep[i];			//�ӷ֣��ӵķ���Ϊ����������*2
        }
    }
    j = 0;
    for(i = 0; i < 4; i ++)
	{
        if(tep[i] != 0)
		{
            tmp[j ++] = tep[i];
        }
    }
    return (tmp);   				//tmpΪָ������ã�*&ָ�뱾��ɱ�
}

/**
 * �ж��ܷ��ƶ�����,����1�ܶ�������0���ܶ�
 */
int ifMove(int item[])
{
    int i = 0;
    for(i = 0; i < 3; i ++)
	{
        //�������������λ��������ͬ�����ƶ�������1
        if(item[i] != 0 && item[i] == item[i + 1])
        {
        	return 1;
		}
        //�������������λ�ϣ�һ���ǿո��ӣ�һ���������֣�Ҳ���ƶ�������1
        if(item[i] == 0 && item[i + 1] != 0)
        {
        	return 1;
		}
    }
    return 0;       //���ܺϲ�������0
}

/**
 * �ж�x�Ƿ���2��n�η�,��2��n�η�������1�����򷵻�0
 */
int if2n(int x)
{
	int flag = 0;
	int n;
	int N = 1;
	for(n = 1;n <= 11;n++)		//2��11�η���2048����ϷĿ���Ǵﵽ2048
	{
		if(x == pow(2,n))       	//����2��n�η�
		{
			flag = 1;
			if(n>N)
				N = n;
			return flag;
		}
	}
	return flag;
}

 /**
 * �ж��Ƿ��ܹ����ƣ��������ƣ��򷵻�1�������ƶ����򷵻�0
 */
int ifup()
{
	int i,j;
	int flag = 0;           //�����־������ֻ��0��1
	for(j = 0;j < 4;j ++)
		for(i = 0;i < 3;i ++)
		{
            //��������������������2��n�η�����������ĸ�����������
			if((if2n(BOX[i][j] + BOX[i+1][j]) == 1) && BOX[i+1][j])
			{
				flag = 1;    //��������
			}
		}
		return flag;        //����1��ʾ�������ƣ�����0��ʾ��������
}

 /**
 * �ж��Ƿ��ܹ����ƣ��������ƣ��򷵻�1�������ƶ����򷵻�0  ������1�������������������2��n�η���2��
����ĸ�����������
 */
int ifdown()
{
	int i,j;
	int flag = 0;
	for(j = 0;j < 4;j ++)
		for(i = 3;i > 0;i --)
		{
            //��������������������2��n�η�����������ĸ�����������
			if((if2n(BOX[i][j] + BOX[i-1][j]) == 1) && BOX[i-1][j])
			{
				flag = 1;       //��������
			}
		}
		return flag;            //����1��ʾ�������ƣ�����0��ʾ��������
}

 /**
 * �ж��Ƿ��ܹ����ƣ��������ƣ��򷵻�1�������ƶ����򷵻�0  ������1�������������������2��n�η���2��
����ĸ�����������
 */
int ifleft()
{
	int i,j;
	int flag = 0;
	for(i = 0;i < 4;i ++)
		for(j = 0;j < 3;j ++)
		{
            //��������������������2��n�η�����������ĸ���������
			if((if2n(BOX[i][j] + BOX[i][j+1]) == 1) && BOX[i][j+1])
			{
				flag = 1;          //��������
			}
		}
		return flag;              //����1��ʾ�������ƣ�����0��ʾ��������
}

 /**
 * �ж��Ƿ��ܹ����ƣ��������ƣ��򷵻�1�������ƶ����򷵻�0   ������1�������������������2��n�η���2��
����ĸ�����������
 */
int ifright()
{
	int i,j;
	int flag = 0;
	for(i = 0;i < 4;i ++)
		for(j = 3;j > 0;j --)
		{
            //��������������������2��n�η�����������ĸ�����������
			if((if2n(BOX[i][j] + BOX[i][j-1]) == 1) && BOX[i][j-1])
			{
				flag = 1;            //��������
			}
		}
		return flag;                 //����1��ʾ�������ƣ�����0��ʾ��������
}
/**
 * �������������
 */
int BOXmax()
{
	int max = BOX[0][0];       	 	//��ʼ��BOX����
	int i,j;
	for(i = 0;i < 4;i ++)      		//������������
	{
		for(j = 0;j < 4;j ++)
		{
			if(BOX[i][j] > max) 		//�������������ֵ����max��ֵ
			{
				max = BOX[i][j];    //�������е�ֵ��ֵ��max�������ҳ������е������
			}
		}
}
	return max;                     //����max��ֵ��Ҳ���ǵ�ǰ�����е����ֵ
}

/**
 * �ж��Ƿ�ʤ��
 */
int Gamewin()
{
	int flag = 0;
	if(BOXmax() == 2048)            //��������е����ֵΪ2048���ﵽĿ�꣬����Ϸʤ��
	{
		system("cls");
		gotoxy(1,6);
		color(2);   				//����ɫ
        //���ʤ��win���ַ���
    	printf("     ��             ��             ��       ����������       ��          ��  \n");
    	gotoxy(1,7);
    	printf("      ��           ����           ��            ��           �� ��       ��  \n");
    	gotoxy(1,8);
        printf("       ��         ��  ��         ��             ��           ��  ��      ��  \n");
        gotoxy(1,9);
		printf("        ��       ��    ��       ��              ��           ��   ��     ��   \n");
		gotoxy(1,10);
        printf("         ��     ��      ��     ��               ��           ��    ��    ��  \n");
        gotoxy(1,11);
        printf("          ��   ��        ��   ��                ��           ��     ��   �� \n");
        gotoxy(1,12);
		printf("           �� ��          �� ��                 ��           ��      ��  ��    \n");
		gotoxy(1,13);
		printf("            ����          ����                  ��           ��       �� ��  \n");
		gotoxy(1,14);
		printf("             ��            ��               ����������       ��          ��   \n");
		gotoxy(35,17);
		color(13);
		printf("ʤ�����������������");
		flag = 1;
	}
	 return flag;                //flag��ֵĬ����0������1���ʾ��Ϸ�ɹ�
}

/**
 * �ж��Ƿ�ʧ�ܣ���������������
 */
int Gamefaile()
{
	int flag = 0;
	int max;
    //���������Ҷ������ƶ�ʱ����Ϸʧ��
	if(ifup() + ifdown() + ifleft() + ifright() == 0)
	{
		system("cls");
		gotoxy(34,3);
		color(14);
		printf("�ϲ�����������ǣ�");
		gotoxy(52,3);
		color(11);
		max = BOXmax();
		printf("%d",max);
		gotoxy(19,6);
		color(4);   //����ɫ
    	printf("     ����������    ��         ��    ����     \n");  //���end�ַ���
    	gotoxy(19,7);
    	printf("     ��            ����       ��    ��  ��   \n");
    	gotoxy(19,8);
        printf("     ��            �� ��      ��    ��    ��   \n");
        gotoxy(19,9);
		printf("     ��            ��  ��     ��    ��     ��  \n");
		gotoxy(19,10);
        printf("     ��������      ��   ��    ��    ��      ��  \n");
        gotoxy(19,11);
        printf("     ��            ��    ��   ��    ��     ��  \n");
        gotoxy(19,12);
		printf("     ��            ��     ��  ��    ��    ��   \n");
		gotoxy(19,13);
		printf("     ��            ��      �� ��    ��  ��  \n");
		gotoxy(19,14);
		printf("     ����������    ��       ����    ����     \n");
		gotoxy(34,17);
		color(13);
		printf("�޷��ƶ�����Ϸʧ�ܣ�");                             //��ʾ����
		flag = 1;
	}
   	return flag;                //flag��ֵĬ����0������1���ʾ��Ϸʧ��
}

/**
 * ���̿����ƶ�
 */
int keyboardControl(int key) {
    int i = 0, j = 0;
    int change = 0;
    int *p;
    int tp[4] = {0, 0, 0, 0};
    switch(key)							//LEFT = 75, UP = 72, RIGHT = 77, DOWN = 80
	{
    	case 72: 							// up�����ϼ�
 			j = 0;
        	for(i = 0; i < 4; i++)
			{
            	tp[0] = BOX[0][i];      //��һ�����Ƶ��м����
            	tp[1] = BOX[1][i];
            	tp[2] = BOX[2][i];
            	tp[3] = BOX[3][i];
            	p = add(tp);            	//��úϲ�֮�����ֵ
            	//�ж��Ƿ�����ƶ��������ƶ������³���һ�����֣������ƶ��������ˣ�Ҳ�������������
           	 	if(!ifMove(tp))
				{
					j++;        			//�����ƶ�
				}
           	 	BOX[0][i] = p[0];       	//�Ѵ���õ��м�����ƻ���
            	BOX[1][i] = p[1];
            	BOX[2][i] = p[2];
            	BOX[3][i] = p[3];
        	}
        	return j != 4;              	//��j������4ʱ������ִ��up����
    	case 80: 							// down�����¼�
			j  = 0;
        	for(i = 0; i < 4; i++)
			{
            	tp[0] = BOX[3][i];
            	tp[1] = BOX[2][i];
            	tp[2] = BOX[1][i];
            	tp[3] = BOX[0][i];
            	p = add(tp);
           	 	if(!ifMove(tp))
				{
					j++;
				}
            	BOX[3][i] = p[0];
            	BOX[2][i] = p[1];
            	BOX[1][i] = p[2];
           	 BOX[0][i] = p[3];
       	 	}
        	return j != 4;
    	case 75:							// left�������
			j = 0;
        	for(i = 0; i < 4; i++)
			{
            	tp[0] = BOX[i][0];
            	tp[1] = BOX[i][1];
            	tp[2] = BOX[i][2];
            	tp[3] = BOX[i][3];
            	p = add(tp);
          		if(!ifMove(tp))
				{
					j++;
				};
            	BOX[i][0] = p[0];
            	BOX[i][1] = p[1];
            	BOX[i][2] = p[2];
            	BOX[i][3] = p[3];
        	}
        	return j != 4;
    	case 77: 										// right�����Ҽ�
			j = 0;
        	for(i = 0; i < 4; i++)
			{
        	    tp[0] = BOX[i][3];
            	tp[1] = BOX[i][2];
           	 	tp[2] = BOX[i][1];
            	tp[3] = BOX[i][0];
            	p = add(tp);
				if(!ifMove(tp))
				{
					j++;
				}
            	BOX[i][3] = p[0];
           	 	BOX[i][2] = p[1];
           	 	BOX[i][1] = p[2];
            	BOX[i][0] = p[3];
        	}
        	return j != 4;
		case 27:										//��Esc
			gotoxy(20,23);
			color(12);
			printf("ȷ���˳���Ϸô? (y/n)");
			char c = getch();           				//��ü�������
			if(c == 'y' ||c == 'Y')     			//���������Ǵ�д����Сд��y
			{
				exit(0);                				//�˳���Ϸ
			}
			if(c == 'n'||c == 'N')      			//���������Ǵ�д����Сд��n
			{
				gotoxy(20,23);
				printf("                        ");   	//������Ϸ
			}
			break;

    }
    return 0;
}

/**
 * ��ʼ��Ϸ
 */
void Gameplay()
{
	system("cls");      			//����
	int i = 0, j = 0;
	Time = time(NULL);				//ȡ��ǰʱ��Ϊ��ʼʱ��
    drawTheGameBox();               //������Ϸ����
    int a,b;                        //BOX[][]����ĺ�������
    srand(time(NULL)); 				//������������ӣ���ʼ�������
	do
	{
    	a = rand()%4;               //���4*4�����е����λ��
    	b = rand()%4;
	}while(BOX[a][b]!=0);           //һֱ��������û�пո�
	if(rand() % 4 == 0)      		//2��4��������ڿո�(�ʼ�����������ϵ�2��4)
	{
		BOX[a][b] = 4;
    }
	else
	{
		BOX[a][b] = 2;
    }

    for(i = 0; i < 4; i ++)         //������������
	{
        for(j = 0; j < 4; j ++)
		{
            if(BOX[i][j] == 0)      //����������п�λ���ͼ�����ȥ
            {
            	continue;
			}
//            gotoxy(17 + j * 8 + 3 + (j * 2), i * 4 + (i + 5));  //����������ʾλ��
			gotoxy(15 + j * 10 + 5, 2 + i * 5 + 3);  //����������ʾλ��
            int c = BOX[i][j];  //���������BOX[i][j]�ϵ�����
            TextColors(c);  //�������ӵ���ɫ����ͬ������ʾ��ͬ��ɫ
            printf("%d", c);    //��ӡ����
        }
    }

    while(1) {
        while (kbhit())            			//kbhit()��鵱ǰ�Ƿ��м������룬�з���1�����򷵻�0
        {
            if(!keyboardControl(getch()))  //������µİ���������keyboardControl()�����ж���ģ���û�з�Ӧ��һֱ�����¶���İ���Ϊֹ
            {
				continue;
			}
            drawTheGameBox();       		//��������
            for(i = 0; i < 4; i ++)         //ѭ������4*4������
			{
                for(j = 0; j < 4; j ++)
				{
                    if(BOX[i][j] == 0)      //����������п�λ�����һֱ���а���
                    {
						continue;
					}
					gotoxy(15 + j * 10 + 5, 2 + i * 5 + 3);//�ϲ���������ֵ�λ��
                    int c = BOX[i][j];
            		TextColors(c);
            		printf("%d", c);
                }
            }
			do{
    			a = rand()%4;
    			b = rand()%4;           //������λ��
			}while(BOX[a][b]!=0);
    		if(rand() % 4 == 0)       //2��4��������ڿո�(���з�������ϲ�֮���ڿհ״�����)
			{
				BOX[a][b] = 4;          //���λ��������Ϊ4
    		} else {
				BOX[a][b] = 2;          //���λ��������Ϊ2
    		}
    		step++;                 //���мƲ�
//            gotoxy(17 + b * 8 + 3 + (b * 2), a * 4 + (a + 5));//�ϲ���������ֵ�λ��
			gotoxy(15 +  b * 10 + 5, 2 + a * 5 + 3);     //������ֵ�2��4
			int c = BOX[a][b];
		    TextColors(c);
            printf("%d", c);
	    }
	 	if(Gamefaile()+Gamewin() != 0)  //ֻҪGamefaile()����Gamewin()����һ����������1��Ҳ���ǳɹ�����ʧ�ܶ���������������
	    {
	    	int n;
	    	gotoxy(20,20);
			color(12);
			printf("��Ҫ������һ��-------1");
			gotoxy(45,20);
			printf("�����ˣ��˳���-------2\n");
			gotoxy(43,21);
			color(11);
			scanf("%d", &n);
    		switch (n)
    		{
    			case 1:
        			Replay();         //������Ϸ����
        			break;
    			case 2:
        			close();    		//�ر���Ϸ����
        			break;
    		}
		}
	}
}

/**
 * ������Ϸ
 */
void Replay()
{
	system("cls");                  	//����
	score = 0,step = 0;                 //��������������
	memset(BOX,0,16*sizeof(int));      //��ʼ��BOX����
	Gameplay();                         	//��ʼ��Ϸ
}

/**
 * ��Ϸ�������
 */
void regulation()
{
	int i,j = 1;
    system("cls");
    color(13);
    gotoxy(34,3);
    printf("��Ϸ����");
    color(2);
    for (i = 6; i <= 18; i++)   		 //������±߿�===
	{
		for (j = 15; j <= 70; j++)  //������ұ߿�||
		{
			gotoxy(j, i);
			if (i == 6 || i == 18) printf("=");
			else if (j == 15 || j == 69) printf("||");
		}
	}
    color(3);
    gotoxy(18,7);
    printf("tip1: ��ҿ���ͨ���� �� �� ����������ƶ�����");
    color(10);
    gotoxy(18,9);
    printf("tip2: ��ESC�˳���Ϸ");
    color(14);
    gotoxy(18,11);
    printf("tip3: ���ѡ��ķ�����,������ͬ��������ϲ�");
    color(11);
    gotoxy(18,13);
    printf("tip4: ÿ�ƶ�һ������λ�������һ��2��4");
    color(4);
    gotoxy(18,15);
    printf("tip5: ���̱������������޷�������Ч�ƶ�����Ϸʧ��");
    color(5);
    gotoxy(18,17);
    printf("tip6: �����ϳ���2048����Ϸʤ��");
    getch();                	//����������ػ�ӭ����
    system("cls");
    main();
}

/**
 * ����˵��
 */
void explation()
{
	gotoxy(20,22);
	color(13);
	printf("�١��������������������������Ϸ������");
	gotoxy(20, 24);
	printf("�ڡ�ESC���˳���Ϸ");
	getch();                //����������ػ�ӭ����
	system("cls");
	main();
}
void close()
{
    exit(0);
}
