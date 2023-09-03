#include <stdio.h>
int Check(int line,int list);
void print();
void eight_queen(int line);
int x=1;

int Queenes[8]={0};

int main() {
    printf("The possible configuration of 8 queens are:\n");
    //���û��ݺ���������0��ʾ�����̵ĵ�һ�п�ʼ�ж�
    eight_queen(0);

    return 0;
}

void eight_queen(int line){
    //��������Ϊ0-7��
    for (int list=0; list<8; list++) {
        //���ڹ̶������У�����Ƿ��֮ǰ�Ļʺ�λ�ó�ͻ
        if (Check(line, list)) {
            //����ͻ������Ϊ�±������λ�ü�¼����
            Queenes[line]=list;
            //������һ��Ҳ����ͻ��֤��Ϊһ����ȷ�İڷ�
            if (line==7) {
                //�������ڷ�
                print();
                //ÿ�γɹ�����Ҫ�������ع�Ϊ0
                Queenes[line]=0;
                return;
            }
            //�����ж���һ���ʺ�İڷ����ݹ�
            eight_queen(line+1);
            //���ܳɹ�ʧ�ܣ���λ�ö�Ҫ���¹�0���Ա��ظ�ʹ�á�
            Queenes[line]=0;
        }
    }
}
int Check(int line,int list){
    //��������֮ǰ��������
    for (int index=0; index<line; index++) {
        //����ȡ��ǰ�����лʺ�����λ�õ�������
        int data=Queenes[index];
        //�����ͬһ�У���λ�ò��ܷ�
        if (list==data) {
            return 0;
        }
        //�����ǰλ�õ�б�Ϸ��лʺ���һ��б���ϣ�Ҳ����
        if ((index+data)==(line+list)) {
            return 0;
        }
        //�����ǰλ�õ�б�·��лʺ���һ��б���ϣ�Ҳ����
        if ((index-data)==(line-list)) {
            return 0;
        }
    }
    //���������������ǣ���ǰλ�þͿ��ԷŻʺ�
    return 1;
}
//������
void print()
{
    for (int line = 0; line < 8; line++)
    {
        int list;
        for (list = 0; list < Queenes[line]; list++)
            printf("0");
        printf("#");
        for (list = Queenes[line] + 1; list < 8; list++){
            printf("0");
        }
        printf("\n");
    }
    printf("================\n");
//    printf("  [%2d]:",x);
//    x++;
//    for (int line = 0; line < 8; line++)
//    {
//        printf(" %d",Queenes[line]+1);
//    }
//    if(x%3==1){
//        printf("\n");
//    }
}

