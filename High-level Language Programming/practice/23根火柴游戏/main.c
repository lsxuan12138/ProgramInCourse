#include <stdio.h>
#include <stdlib.h>

int main()
{
    int total=23;
    printf("������23�������Ϸ����\n");
    printf("ע�⣺����ƶ������ĿΪ����\n");
    int n, k;
    do{
        printf("���������ƶ��Ļ����Ŀ��\n");
        do{
            scanf("%d",&n);
            if(n>3){
                printf("�Բ����������˲����ʵ���Ŀ�����������룡\n");
            }
        }while(n>3);
        printf("���ƶ��Ļ����ĿΪ��%d\n",n);
        total-=n;
        printf("���ƶ���ʣ�µĻ����ĿΪ��%d\n",total);
        if(total==0){
            printf("�Բ��������ˣ�\n");
            break;
        }
        if(total==3){
            k=2;
        }
        else if(total==2){
                k=2;
            }
            else if(total==1){
                    k=1;
                }
                else{
                    k=total%3+1;
                }
        printf("������ƶ��Ļ����ĿΪ��%d\n",k);
        total-=k;
        printf("������ƶ���ʣ�µĻ����ĿΪ��%d\n",total);
        if(total==0){
            printf("��ϲ������Ӯ�ˣ�\n");
            break;
        }
    }while(total!=0);

    return 0;
}
