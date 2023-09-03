#include <stdio.h>
#include <stdlib.h>

typedef struct stack
{
    int data;
    struct stack *next;
} STACK;
STACK *head, *pr;
int nodeNum = 0;                            /* ��ջ�ڵ����Ĵ��� */
STACK *CreateNode(int num);
STACK *PushStack(int num);
int PopStack(void);
int main()
{
    int pushNum[5] = {111, 222, 333, 444, 555}, popNum[5], i;
    for (i = 0; i < 5; i++)
    {
        PushStack(pushNum[i]);
        printf("Push %dth Data:%d\n", i + 1, pushNum[i]);
    }
    for (i = 0; i < 5; i++)
    {
        popNum[i] = PopStack();
        printf("Pop %dth Data:%d\n", 5 - i, popNum[i]);
    }
    return 0;
}
/* �������ܣ�����һ���µĽڵ㣬��Ϊ�ýڵ㸳��ֵ������ָ���µĽڵ��ָ�� */
STACK *CreateNode(int num)
{
    STACK *p;
    p = (STACK *)malloc(sizeof(STACK));
    if(p==NULL)
    {
    printf("No enoughmemory!\n");
    exit(0);
    }
    p->next = NULL; //Ϊ�½��Ľڵ�ָ���򸳿�ָ��
    p->data = num; //Ϊ�½��Ľڵ���������ֵ
    return p;
}
/*  �������ܣ������ͱ���num��ֵѹ���ջ������ָ�������½ڵ��ָ�� */
STACK *PushStack(int num)
{
    if (nodeNum == 0) //���Ϊ�׽ڵ㣬��ô�ͱ����ýڵ��ַ��head��
    {
    head = CreateNode(num);
    pr = head;
    nodeNum++; //��ջ�ڵ����Ĵ���+1
    }
    else //��������׽ڵ㣬��ô�ͽ��½��ڵ���������Ľ�β��
    {
    pr->next = CreateNode(num);
    pr = pr->next;
    nodeNum++; //��ջ�ڵ����Ĵ���+1
    }
    return pr;
}
/*  �������ܣ�����ǰջ�������ݵ�����ջ�����شӶ�ջ�е��������� */
int PopStack(void)
{
    STACK *p = head;
    int result;
    for (;;)
    {
        if (p->next == NULL) //�������һ���ڵ�/
        {
        break;
        }
        else
        {
        pr = p; //��¼���һ���ڵ��ǰһ���ڵ�ĵ�ַ
        p = p->next;
        nodeNum--; //��ջ�ڵ����Ĵ���-1
        }
    }
    pr->next = NULL; //��ĩ�ڵ��ǰһ���ڵ��ó�ĩ�ڵ�
    result = p->data;
    free(p);
    return result;
}
