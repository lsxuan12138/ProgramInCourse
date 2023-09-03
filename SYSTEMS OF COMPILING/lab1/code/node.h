#ifndef ENUM_H
#define ENUM_H

typedef enum nodeType {
    TOKEN_INT,
    TOKEN_FLOAT,
    TOKEN_ID,
    TOKEN_TYPE,
    TOKEN_OTHER,
    NOT_A_TOKEN
} NodeType;
#endif

#ifndef NODE_H
#define NODE_H
#include <stdio.h>
#include <stdlib.h>
#include <stdarg.h>
#include <string.h>

#define TRUE 1
#define FALSE 0

typedef struct node {
    //node in which line
    int lineNo;
    //node type
    NodeType type;
    //node name
    char* name;
    //node value
    char* val;
    //first child node
    struct node* child;
    //brother node
    struct node* next;
} Node;


static Node* newNode(int lineNo, NodeType type, char* name, int argc,...) {
    Node* curNode = NULL;
    size_t nameLength = strlen(name) + 1;

    curNode = (Node*)malloc(sizeof(Node));


    curNode->name = (char*)malloc(sizeof(char) * nameLength);
    curNode->lineNo = lineNo;
    curNode->type = type;
    strncpy(curNode->name, name, nameLength);

    va_list vaList;
    va_start(vaList, argc);

    Node* tempNode = va_arg(vaList, Node*);

    curNode->child = tempNode;

    for (int i = 1; i < argc; i++) {
        tempNode->next = va_arg(vaList, Node*);
        if (tempNode->next != NULL) {
            tempNode = tempNode->next;
        }
    }

    va_end(vaList);
    return curNode;
}

static Node* newTokenNode(int lineNo, NodeType type, char* tokenName,char* tokenText) {
    Node* tokenNode = (Node*)malloc(sizeof(Node));
    size_t nameLength = strlen(tokenName) + 1;
    size_t textLength = strlen(tokenText) + 1;

    tokenNode->lineNo = lineNo;
    tokenNode->type = type;

    tokenNode->name = (char*)malloc(sizeof(char) * nameLength);
    tokenNode->val = (char*)malloc(sizeof(char) * textLength);

    strncpy(tokenNode->name, tokenName, nameLength);
    strncpy(tokenNode->val, tokenText, textLength);

    tokenNode->child = NULL;
    tokenNode->next = NULL;

    return tokenNode;
}
static void delNode(Node* node) {
    if (node == NULL) return;
    while (node->child != NULL) {
        Node* temp = node->child;
        node->child = node->child->next;
        delNode(temp);
    }
    free(node->name);
    free(node->val);
    node->name = NULL;
    node->val = NULL;
    free(node);
    node = NULL;
}
static void printTreeInfo(Node* curNode, int height) {
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

static void saveTreeInfo(Node* curNode, int height,FILE* out) {
    if (curNode == NULL) {
        return;
    }

    for (int i = 0; i < height; i++) {
        fprintf(out,"%s","  ");
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
    fprintf(out,"%s","\n");
    saveTreeInfo(curNode->child, height + 1,out);
    saveTreeInfo(curNode->next, height,out);
}
#endif
