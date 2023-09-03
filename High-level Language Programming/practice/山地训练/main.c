#include <stdio.h>
#include <stdlib.h>
#include <string.h>
long Fun(long M, long T, long U, long F, long D, char str[]);

int main()
{
    long M,  T,  U,  F,  D;
    printf( "Input M,T,U,F,D:");
    scanf("%ld%ld%ld%ld%ld",&M,&T,&U,&F,&D);
    printf( "Input conditions of road:");
    char str[T];
    scanf("%s",str);
    long num=Fun(M,  T,  U,  F,  D, str);
    printf( "num=%ld\n",num);
    return 0;
}
//��������ʱM����T��·�ε�����£�����������ܵ�·������
//
//         ������M,T,U,F,D�ֱ������ʱ��·�������Լ����¡�ƽ�ء����µĺ�ʱ
//
//              ����str��������ɽ·��·��״��
//
//         ����ֵ�������ܵ�·����

long Fun(long M, long T, long U, long F, long D, char str[])
{
    long road[3]={0},time=0;
    for(int i=0;i<T;i++){
        switch(str[i]){
        case 'u':
            road[0]++;
            break;
        case 'f':
            road[1]++;
            break;
        case 'd':
            road[2]++;
        }
        time=(U+D)*(road[0]+road[2])+2*F*road[1];
        if(time>=M) return i;
    }
    return 0;
}
