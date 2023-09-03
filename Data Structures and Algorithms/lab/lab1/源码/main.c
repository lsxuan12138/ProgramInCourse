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
int main()//主方法
{
    printf("请以(系数1,幂次1)(系数2,幂次2)...#形式输入多项式\n");
    printf("例如(3,2)(4,4)(5,3)#\n");
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

    printf("输出两式相加结果");
    Node* re = add(poly1,poly2);
    printPolynomial(re);
    printf("\n");
    printf("输出两式相减结果");
    Node* re2 = sub(poly1,poly2);
    printPolynomial(re2);
    printf("\n");
    printf("输出两式相乘结果");
    Node* re3 = mul(poly1,poly2);
    printPolynomial(re3);
    printf("\n");
    printf("输出两式相除结果");
    divd(poly1,poly2);
    //printf("\n%lf\n",getValue(2.00,poly1));
    freePoly(poly1);
    freePoly(poly2);
    return 0;
}
/**
 * 创造节点
 * @param n 多项式中一项的系数
 * @param e 多项式中一项的幂次
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
 * 插入节点
 * @param head 多项式头节点
 * @param node 要插入的节点
 * @return 若插入的节点幂次比头节点大返回头节点，否则返回NULL
 */
Node* insertNode(Node* head,Node* node){
    if(head!=NULL&&node==NULL)printf("Error\n");

    //printf("%dx^(%d)insertNode\n",node->n,node->e);
    //如果插入的节点幂次比头节点大，将新节点变为头节点，并返回
    if(node->e > head->e){
        node->next=head;
        //printf("headchange\n");
        return node;
    }
    int flag = 0;
    Node* temp1 = head;
    Node* temp2 = head->next;
    while(temp1->next != NULL){
        //当插入的节点幂次在前后两个节点之间时，插入
        if(temp1->e >= node->e&&temp2->e < node->e){
            temp1->next=node;
            node->next=temp2;
            flag=1;//将标志位 置一
            //printf("insertSuccess\n");
            return NULL;
        }
        temp1 = temp1->next;
        if(temp1->next==NULL)continue;
        temp2 = temp1->next;
    }
    //若为0，表示未插入，即未找到位置，将其插入到链表尾端
    if(flag==0){
        temp1->next=node;
        //printf("tailChange\n");
        return NULL;
    }
}
/**
 * 根据字符串创建多项式
 * @param str
 * @return str对应的多项式
 */
Node* createPolynomial(char* str){
    Node* head = NULL;
    int i=0;
    int t=0,n=0,e=0;
    while(str[i]!='#'){//读到#字符时停止
        if(str[i]=='(');//读到（时，说明此为一项的开始
        if(isdigit(str[i])){//读到数字时，处理数字
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
            //创建节点
            Node* temp = createNode(n,e);
            //若头节点为空，将创建的节点当作头节点
            if(head==NULL){head=temp;}
            else{
                //插入节点，；若返回值不为NULL说明头节点改变
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
 * 打印多项式
 * @param head 头节点
 */
void printPolynomial(Node* head){
    //排除头节点为空的情况
    if(head==NULL){
        printf("Error\n");
        return;
    }
    //打印头节点中值
    printf("%lfx^(%d)",head->n,head->e);
    Node* node = head->next;
    while(node!=NULL){
        //若系数为0，不打印
        if(node->n==0) {
            node=node->next;
            continue;
        }
        //若系数小于0
        if(node->n<0){
            printf("%lfx^(%d)",node->n,node->e);
        }else{
            //若系数大于0，在式子前加一个‘+’
            printf("+%lfx^(%d)",node->n,node->e);
        }
        node=node->next;
    }
}
/**
 * 取得多项式在x点的值
 * @param x
 * @param poly 多项式
 * @return 值
 */
double getValue(double x,Node* poly){
    if(poly==NULL){
        printf("Error\n");
    }
    Node* node = poly;
    double value = 0.0;
    while(node!=NULL){
        //遍历相加
        value+=((node->n)*pow(x,node->e));
        node = node->next;
    }
    return value;
}
/**
 * 释放内存
 * @param poly
 */
void freePoly(Node* poly){
    if(poly==NULL)return;
    //若只有一项，直接释放头节点
    if(poly->next==NULL){free(poly);return;}
    //遍历释放所有内存
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
 * 复制多项式
 * @param poly
 * @return 复制得到的多项式头节点
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
 * 多项式加法
 * @param poly1
 * @param poly2
 * @return 两式相加的结果
 */
Node* add(Node* poly1,Node* poly2){
    if(poly1==NULL||poly2==NULL){
        printf("Error\n");
        return NULL;
    }
    Node *p1=poly1,*p2=poly2;
    Node* result = NULL;
    //当两式都未到达头节点
    while(p1!=NULL&&p2!=NULL){
        //若两个头节点幂次不相等
        //向结果追加幂次大的一项
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
            //若幂次相等相加并追加到结果
            if(p1->n+p2->n==0){
                //若系数相加为0，跳过
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
    //若多项式1为到尾节点，将后面全部追加到result上
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
 * 对象时相减
 * @param poly1 被减数
 * @param poly2 减数
 * @return 差
 */
Node* sub(Node* poly1,Node* poly2){
    Node *p=NULL;
    Node *p2 = poly2;
    while(p2!=NULL){
        //将每一项系数取相反数
        Node *temp = createNode(-(p2->n),p2->e);
        if(p==NULL)p=temp;
        else insertNode(p,temp);
        p2=p2->next;
    }
    //将被减数 和 减数的相反数 相加
    Node* result = add(poly1,p);
    freePoly(p);
    return result;
}
/**
 * 多项式乘法
 * @param poly1
 * @param poly2
 * @return 积
 */
Node* mul(Node* poly1,Node* poly2){
    Node* p = poly1;
    Node* p2 = poly2;
    Node* result = createNode(0,0);
    //int flag = 0;
    while (p!=NULL){
        //遍历多项式1的每一项
        //将其与式2相乘得到result2
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
        //将result2与result相加
        result=add(result,result2);
        freePoly(result2);
        //继续计算式1下一项与式2的积
        p=p->next;
        p2=poly2;
    }
    //printPolynomial(result);
    return result;

}
/**
 * 多项式除法
 * @param poly1 被除数
 * @param poly2 除数
 * @return 商
 */
Node* divd(Node* poly1,Node* poly2){
    Node* result =NULL;
    Node* p1 = poly1;
    Node* p2 = poly2;
    int flag = 0;
    //当p1的最高幂次小于p2的时，跳出循环，剩余的即为余项
    while (p1->e >= p2->e){
        //计算p1的最高幂次项小于p2的最高幂次项的商
        Node *temp= createNode(p1->n/p2->n,p1->e-p2->e);
        //向result的中追加
        if(result==NULL)result=temp;
        else{
            Node *temp2= insertNode(result,temp);
            if(temp2!=NULL)result=temp;
        }
        //p1=sub(p1,mul(p2,temp));
        //计算除去temp后的余项，并赋值给p1
        Node* temp2=mul(p2,temp);
        //以temp3临时保存，一边释放多余占用空间
        Node* temp3 = p1;
        p1=sub(p1,temp2);
        if(flag){
            freePoly(temp3);
        }
        flag=1;
        freePoly(temp2);
    }
    //打印商
    printf("积为：");
    printPolynomial(result);
    printf("\n");
    //打印余数
    printf("余项为：");
    printPolynomial(p1);
    return result;
}
