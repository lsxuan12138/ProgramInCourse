#ifndef NODE_H
#define NODE_H


#include <assert.h>
#include <stdarg.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include "enum.h"

#define TRUE 1
#define FALSE 0


// node type declared
typedef struct node {
    int lineNo;  //  node in which line
    NodeType type;  // node type
    char* name;     //  node name
    char* val;      //  node value
    struct node* child;  //  non-terminals node first child node
    struct node* next;   //  non-terminals node next brother node

} Node;

typedef unsigned boolean;
typedef Node* pNode;

static char* newString(char* src) {
    if (src == NULL) return NULL;
    size_t length = strlen(src) + 1;
    char* p = (char*)malloc(sizeof(char) * length);
    assert(p != NULL);
    strncpy(p, src, length);
    return p;
}

static pNode newNode(int lineNo, NodeType type, char* name, int argc,...) {
    pNode curNode = NULL;

    curNode = (pNode)malloc(sizeof(Node));

    assert(curNode != NULL);

    curNode->lineNo = lineNo;
    curNode->type = type;
    curNode->name = newString(name);
    curNode->val = NULL;

    va_list vaList;
    va_start(vaList, argc);

    pNode tempNode = va_arg(vaList, pNode);

    curNode->child = tempNode;
    curNode->next =NULL;
    if(argc==1)tempNode->next=NULL;
    for (int i = 1; i < argc; i++) {
        tempNode->next = va_arg(vaList, pNode);
        if (tempNode->next != NULL) {
            tempNode = tempNode->next;
        }
    }

    va_end(vaList);
    return curNode;
}

static pNode newTokenNode(int lineNo, NodeType type, char* tokenName,
                                 char* tokenText) {
    pNode tokenNode = (pNode)malloc(sizeof(Node));

    assert(tokenNode != NULL);

    tokenNode->lineNo = lineNo;
    tokenNode->type = type;

    tokenNode->name = newString(tokenName);
    tokenNode->val = newString(tokenText);

    tokenNode->child = NULL;
    tokenNode->next = NULL;

    return tokenNode;
}

static void delNode(pNode* node) {
    if (node == NULL) return;
    pNode p = *node;
    while (p->child != NULL) {
        pNode temp = p->child;
        p->child = p->child->next;
        delNode(&temp);
    }
    free(p->name);
    free(p->val);
    free(p);
    p = NULL;
}

static void printTreeInfo(pNode curNode, int height) {
    if (curNode == NULL) {
        return;
    }

    for (int i = 0; i < height; i++) {
        printf("  ");
    }
    printf("%s", curNode->name);
    if (curNode->type == NOT_A_TOKEN) {
        printf(" (%d)", curNode->lineNo);
    } else if (curNode->type == TOKEN_TYPE || curNode->type == TOKEN_ID ||
               curNode->type == TOKEN_INT) {
        printf(": %s", curNode->val);
    } else if (curNode->type == TOKEN_FLOAT) {
        printf(": %lf", atof(curNode->val));
    }
    printf("\n");
    printTreeInfo(curNode->child, height + 1);
    printTreeInfo(curNode->next, height);
}
static void saveTreeInfo(pNode curNode, int height,FILE* out) {
    if (curNode == NULL) {
        return;
    }

    for (int i = 0; i < height; i++) {
        fprintf(out,"  ");
    }
    fprintf(out,"%s", curNode->name);
    if (curNode->type == NOT_A_TOKEN) {
        fprintf(out," (%d)", curNode->lineNo);
    } else if (curNode->type == TOKEN_TYPE || curNode->type == TOKEN_ID ||
               curNode->type == TOKEN_INT) {
        fprintf(out,": %s", curNode->val);
    } else if (curNode->type == TOKEN_FLOAT) {
        fprintf(out,": %lf", atof(curNode->val));
    }
    fprintf(out,"\n");
    saveTreeInfo(curNode->child, height + 1,out);
    saveTreeInfo(curNode->next, height,out);
}
#endif
