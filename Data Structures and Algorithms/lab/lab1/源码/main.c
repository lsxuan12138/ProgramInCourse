#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>
#include <string.h>
#include <math.h>
#define N 100
typedef struct node{
    double n;
    int e;
    struct Node* next;
} Node;

Node* createNode(double n,int e);
Node* insertNode(Node* head,Node* node);
Node* createPolynomial(char* str);
void printPolynomial(Node* head);
Node* copyPloy(Node *poly);
double getValue(double x,Node* poly);
Node* add(Node* poly1,Node* poly2);
Node* sub(Node* poly1,Node* poly2);
Node* mul(Node* poly1,Node* poly2);
Node* divd(Node* poly1,Node* poly2);
void freePoly(Node* poly);
int main()//������
{
    printf("����(ϵ��1,�ݴ�1)(ϵ��2,�ݴ�2)...#��ʽ�������ʽ\n");
    printf("����(3,2)(4,4)(5,3)#\n");
    char str[N] = {};
    scanf("%s",str);
    printf("%s\n",str);
    Node* poly1=createPolynomial(str);
    printPolynomial(poly1);
    printf("\n");
    scanf("%s",str);
    printf("%s\n",str);
    Node* poly2=createPolynomial(str);
    printPolynomial(poly2);
    printf("\n");

    printf("�����ʽ��ӽ��");
    Node* re = add(poly1,poly2);
    printPolynomial(re);
    printf("\n");
    printf("�����ʽ������");
    Node* re2 = sub(poly1,poly2);
    printPolynomial(re2);
    printf("\n");
    printf("�����ʽ��˽��");
    Node* re3 = mul(poly1,poly2);
    printPolynomial(re3);
    printf("\n");
    printf("�����ʽ������");
    divd(poly1,poly2);
    //printf("\n%lf\n",getValue(2.00,poly1));
    freePoly(poly1);
    freePoly(poly2);
    return 0;
}
/**
 * ����ڵ�
 * @param n ����ʽ��һ���ϵ��
 * @param e ����ʽ��һ����ݴ�
 * @return
 */
Node* createNode(double n,int e){
    Node* p = (Node*)malloc(sizeof(Node));
    if(p==NULL)printf("Error\n");
    p->n=n;
    p->e=e;
    p->next=NULL;
    //printf("%lfx^(%d)CreateNode\n",p->n,p->e);
    return p;
}
/**
 * ����ڵ�
 * @param head ����ʽͷ�ڵ�
 * @param node Ҫ����Ľڵ�
 * @return ������Ľڵ��ݴα�ͷ�ڵ�󷵻�ͷ�ڵ㣬���򷵻�NULL
 */
Node* insertNode(Node* head,Node* node){
    if(head!=NULL&&node==NULL)printf("Error\n");

    //printf("%dx^(%d)insertNode\n",node->n,node->e);
    //�������Ľڵ��ݴα�ͷ�ڵ�󣬽��½ڵ��Ϊͷ�ڵ㣬������
    if(node->e > head->e){
        node->next=head;
        //printf("headchange\n");
        return node;
    }
    int flag = 0;
    Node* temp1 = head;
    Node* temp2 = head->next;
    while(temp1->next != NULL){
        //������Ľڵ��ݴ���ǰ�������ڵ�֮��ʱ������
        if(temp1->e >= node->e&&temp2->e < node->e){
            temp1->next=node;
            node->next=temp2;
            flag=1;//����־λ ��һ
            //printf("insertSuccess\n");
            return NULL;
        }
        temp1 = temp1->next;
        if(temp1->next==NULL)continue;
        temp2 = temp1->next;
    }
    //��Ϊ0����ʾδ���룬��δ�ҵ�λ�ã�������뵽����β��
    if(flag==0){
        temp1->next=node;
        //printf("tailChange\n");
        return NULL;
    }
}
/**
 * �����ַ�����������ʽ
 * @param str
 * @return str��Ӧ�Ķ���ʽ
 */
Node* createPolynomial(char* str){
    Node* head = NULL;
    int i=0;
    int t=0,n=0,e=0;
    while(str[i]!='#'){//����#�ַ�ʱֹͣ
        if(str[i]=='(');//������ʱ��˵����Ϊһ��Ŀ�ʼ
        if(isdigit(str[i])){//��������ʱ����������
            t*=10;
            t+=(str[i]-48);
        }
        if(str[i]==','){
            n=t;
            t=0;
        }
        if(str[i]==')'){
            e=t;
            t=0;
            //�����ڵ�
            Node* temp = createNode(n,e);
            //��ͷ�ڵ�Ϊ�գ��������Ľڵ㵱��ͷ�ڵ�
            if(head==NULL){head=temp;}
            else{
                //����ڵ㣬��������ֵ��ΪNULL˵��ͷ�ڵ�ı�
                Node* temp2 = NULL;
                if((temp2=insertNode(head,temp))!=NULL){
                    head=temp2;
                }
            }
        }
        i++;
    }
    return head;
}
/**
 * ��ӡ����ʽ
 * @param head ͷ�ڵ�
 */
void printPolynomial(Node* head){
    //�ų�ͷ�ڵ�Ϊ�յ����
    if(head==NULL){
        printf("Error\n");
        return;
    }
    //��ӡͷ�ڵ���ֵ
    printf("%lfx^(%d)",head->n,head->e);
    Node* node = head->next;
    while(node!=NULL){
        //��ϵ��Ϊ0������ӡ
        if(node->n==0) {
            node=node->next;
            continue;
        }
        //��ϵ��С��0
        if(node->n<0){
            printf("%lfx^(%d)",node->n,node->e);
        }else{
            //��ϵ������0����ʽ��ǰ��һ����+��
            printf("+%lfx^(%d)",node->n,node->e);
        }
        node=node->next;
    }
}
/**
 * ȡ�ö���ʽ��x���ֵ
 * @param x
 * @param poly ����ʽ
 * @return ֵ
 */
double getValue(double x,Node* poly){
    if(poly==NULL){
        printf("Error\n");
    }
    Node* node = poly;
    double value = 0.0;
    while(node!=NULL){
        //�������
        value+=((node->n)*pow(x,node->e));
        node = node->next;
    }
    return value;
}
/**
 * �ͷ��ڴ�
 * @param poly
 */
void freePoly(Node* poly){
    if(poly==NULL)return;
    //��ֻ��һ�ֱ���ͷ�ͷ�ڵ�
    if(poly->next==NULL){free(poly);return;}
    //�����ͷ������ڴ�
    Node* pre =poly;
    Node* ne = poly->next;
    do{
        //printf("NodeFree%dx^(%d)\n",pre->n,pre->e);
        free(pre);
        pre=ne;
        if(ne!=NULL)ne=ne->next;
    }while(pre!=NULL);
}
/**
 * ���ƶ���ʽ
 * @param poly
 * @return ���Ƶõ��Ķ���ʽͷ�ڵ�
 */
Node* copyPloy(Node *poly){
    Node* p = poly;
    Node* p2=NULL;
    Node* result = NULL;
    while (p!=NULL){
        Node* temp= createNode(p->n,p->e);
        if(result==NULL)result=temp;
        else{
            insertNode(result,temp);
        }
        p=p->next;
    }
}
/**
 * ����ʽ�ӷ�
 * @param poly1
 * @param poly2
 * @return ��ʽ��ӵĽ��
 */
Node* add(Node* poly1,Node* poly2){
    if(poly1==NULL||poly2==NULL){
        printf("Error\n");
        return NULL;
    }
    Node *p1=poly1,*p2=poly2;
    Node* result = NULL;
    //����ʽ��δ����ͷ�ڵ�
    while(p1!=NULL&&p2!=NULL){
        //������ͷ�ڵ��ݴβ����
        //����׷���ݴδ��һ��
        if(p1->e>p2->e){
            if(p1->n==0){
                if(p1!=NULL)p1=p1->next;
                continue;
            }
            Node* temp = createNode(p1->n,p1->e);
            if(result==NULL)result=temp;
            else insertNode(result,temp);
            if(p1!=NULL)p1=p1->next;
        }else if(p1->e<p2->e){
            if(p2->n==0){
                if(p2!=NULL)p2=p2->next;
                continue;
            }
            Node* temp = createNode(p2->n,p2->e);
            if(result==NULL)result=temp;
            else insertNode(result,temp);
            if(p2!=NULL)p2=p2->next;
        }else if(p1->e==p2->e){
            //���ݴ������Ӳ�׷�ӵ����
            if(p1->n+p2->n==0){
                //��ϵ�����Ϊ0������
                if(p1!=NULL)p1=p1->next;
                if(p2!=NULL)p2=p2->next;
                continue;
            }
            Node* temp = createNode(p1->n+p2->n,p1->e);
            if(result==NULL)result=temp;
            else insertNode(result,temp);
            if(p1!=NULL)p1=p1->next;
            if(p2!=NULL)p2=p2->next;//(2,3)(3,5)(2,1)#//(2,4)(3,5)(2,0)#
        }
    }
    //������ʽ1Ϊ��β�ڵ㣬������ȫ��׷�ӵ�result��
    while(p1!=NULL){
        if(p1->n==0){
            if(p1!=NULL)p1=p1->next;
            continue;
        }
        Node* temp = createNode(p1->n,p1->e);
        if(result==NULL)result=temp;
        else insertNode(result,temp);
        if(p1!=NULL)p1=p1->next;
    }
    while(p2!=NULL){
        if(p2->n==0){
            if(p2!=NULL)p2=p2->next;
            continue;
        }
        Node* temp = createNode(p2->n,p2->e);
        if(result==NULL)result=temp;
        else insertNode(result,temp);
        if(p2!=NULL)p2=p2->next;
    }
    return result;
}
/**
 * ����ʱ���
 * @param poly1 ������
 * @param poly2 ����
 * @return ��
 */
Node* sub(Node* poly1,Node* poly2){
    Node *p=NULL;
    Node *p2 = poly2;
    while(p2!=NULL){
        //��ÿһ��ϵ��ȡ�෴��
        Node *temp = createNode(-(p2->n),p2->e);
        if(p==NULL)p=temp;
        else insertNode(p,temp);
        p2=p2->next;
    }
    //�������� �� �������෴�� ���
    Node* result = add(poly1,p);
    freePoly(p);
    return result;
}
/**
 * ����ʽ�˷�
 * @param poly1
 * @param poly2
 * @return ��
 */
Node* mul(Node* poly1,Node* poly2){
    Node* p = poly1;
    Node* p2 = poly2;
    Node* result = createNode(0,0);
    //int flag = 0;
    while (p!=NULL){
        //��������ʽ1��ÿһ��
        //������ʽ2��˵õ�result2
        Node* result2 = NULL;
        while (p2!=NULL){
            Node* temp = createNode(p->n*p2->n,p->e+p2->e);
            if(result2==NULL)result2=temp;
            else{
                Node* temp2 = insertNode(result2,temp);
                if(temp2!=NULL)result2 =temp2;
            }

            p2=p2->next;
        }
        //��result2��result���
        result=add(result,result2);
        freePoly(result2);
        //��������ʽ1��һ����ʽ2�Ļ�
        p=p->next;
        p2=poly2;
    }
    //printPolynomial(result);
    return result;

}
/**
 * ����ʽ����
 * @param poly1 ������
 * @param poly2 ����
 * @return ��
 */
Node* divd(Node* poly1,Node* poly2){
    Node* result =NULL;
    Node* p1 = poly1;
    Node* p2 = poly2;
    int flag = 0;
    //��p1������ݴ�С��p2��ʱ������ѭ����ʣ��ļ�Ϊ����
    while (p1->e >= p2->e){
        //����p1������ݴ���С��p2������ݴ������
        Node *temp= createNode(p1->n/p2->n,p1->e-p2->e);
        //��result����׷��
        if(result==NULL)result=temp;
        else{
            Node *temp2= insertNode(result,temp);
            if(temp2!=NULL)result=temp;
        }
        //p1=sub(p1,mul(p2,temp));
        //�����ȥtemp����������ֵ��p1
        Node* temp2=mul(p2,temp);
        //��temp3��ʱ���棬һ���ͷŶ���ռ�ÿռ�
        Node* temp3 = p1;
        p1=sub(p1,temp2);
        if(flag){
            freePoly(temp3);
        }
        flag=1;
        freePoly(temp2);
    }
    //��ӡ��
    printf("��Ϊ��");
    printPolynomial(result);
    printf("\n");
    //��ӡ����
    printf("����Ϊ��");
    printPolynomial(p1);
    return result;
}
