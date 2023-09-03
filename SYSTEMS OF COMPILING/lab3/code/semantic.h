#ifndef SEMANTIC_H
#define SEMANTIC_H

#include "node.h"

typedef struct type *pType;
typedef struct AVLNode *pAvlNode;
typedef pAvlNode TableItem;

//类型（基本类型，数组，结构体，函数）
typedef struct type {
    SymbolKind kind;
    union {
        // 基本类型
        BasicType basic;
        // 数组类型信息包括元素类型与数组大小构成
        struct {
            pType elem;
            int size;
        } array;
        // 结构体类型
        struct {
            char *structName;
        } structure;
        //函数类型
        struct {
            //参数数量
            int argc;
            //返回类型
            pType returnType;
        } function;
    } u;
} Type;
//平衡二叉树
typedef struct AVLNode {
    //名字
    char *name;
    //类型
    pType type;
    //函数参数列表，或者结构体域列表
    struct AVLNode *next;
    boolean isArg;
    boolean isField;
    int height;
    //左子树
    struct AVLNode *left;
    //右子树
    struct AVLNode *right;
} AVLNode;


// Type functions
pType newType(SymbolKind kind, int argc, ...);

pType copyType(pType src);

void deleteType(pType type);

boolean checkType(pType type1, pType type2);

void printType(pType type);

// Global functions

static inline void pError(ErrorType type, int line, char *msg) {
    printf("Error type %d at Line %d: %s\n", type, line, msg);
}

void traverseTree(pNode node);

void initTable();
void printTable();
void deleteTable();
pAvlNode  searchTableItem(char *name);

// Generate symbol table functions

// ExtDef -> Specifier ExtDecList SEMI 变量声明
//         | Specifier SEMI 结构体声明
//         | Specifier FunDec CompSt 函数声明
void ExtDef(pNode node);

//全局变量
// ExtDecList -> VarDec 变量名
//             | VarDec COMMA ExtDecList 一行变量名
void ExtDecList(pNode node, pType specifier);

// Specifier -> TYPE 基础类型
//            | StructSpecifier 结构体类型
pType Specifier(pNode node);

// StructSpecifier -> STRUCT OptTag LC DefList RC 结构体定义
//                  | STRUCT Tag 结构体引用
pType StructSpecifier(pNode node);

//一个变量名
// VarDec -> ID 标识符
//         | VarDec LB INT RB 数组
TableItem VarDec(pNode node, pType specifier);

// FunDec -> ID LP VarList RP 有参函数
//         | ID LP RP 无参函数
//pType returnType为函数返回类型
void FunDec(pNode node, pType returnType);

//参数列表
// VarList -> ParamDec COMMA VarList  参数列表
//          | ParamDec 一个参数
void VarList(pNode node, TableItem func);

// ParamDec -> Specifier VarDec 一个参数
TableItem ParamDec(pNode node);

//语句块或函数体
// CompSt -> LC DefList StmtList RC
//pType returnType为函数返回类型
void CompSt(pNode node, pType returnType);

//执行语句列表
// StmtList -> Stmt StmtList
//           | e
//pType returnType为函数返回类型
void StmtList(pNode node, pType returnType);

//执行语句
// Stmt -> Exp SEMI 表达式
//       | CompSt 语句块
//       | RETURN Exp SEMI 返回语句
//       | IF LP Exp RP Stmt 分支语句
//       | IF LP Exp RP Stmt ELSE Stmt
//       | WHILE LP Exp RP Stmt 循环语句
//pType returnType为函数返回类型
void Stmt(pNode node, pType returnType);

//变量声明列表（本地变量或者结构体域）
// DefList -> Def DefList
//          | e
//pItem structInfo为NULL表示不在结构体内
void DefList(pNode node, TableItem structInfo);

//变量声明（本地变量或者结构体域）
// Def -> Specifier DecList SEMI
//pItem structInfo为NULL表示不在结构体内
void Def(pNode node, TableItem structInfo);

//变量名列表（本地变量或者结构体域）
// DecList -> Dec 一个变量名
//          | Dec COMMA DecList
//pItem structInfo为NULL表示不在结构体内
void DecList(pNode node, pType specifier, TableItem structInfo);

//变量名（本地变量或者结构体域）
// Dec -> VarDec 未初始化
//      | VarDec ASSIGNOP Exp 已初始化
//pItem structInfo为NULL表示不在结构体内
void Dec(pNode node, pType specifier, TableItem structInfo);

//表达式
// Exp -> Exp ASSIGNOP Exp 赋值
//      | Exp AND Exp 与
//      | Exp OR Exp 或
//      | Exp RELOP Exp 布尔
//      | Exp PLUS Exp 加
//      | Exp MINUS Exp 减
//      | Exp STAR Exp 乘
//      | Exp DIV Exp 除
//      | LP Exp RP 括号
//      | MINUS Exp 取反
//      | NOT Exp 取非
//      | ID LP Args RP 函数调用
//      | ID LP RP 加括号
//      | Exp LB Exp RB 数组[]
//      | Exp DOT ID 结构体中域的引用
//      | ID 标识符
//      | INT 整数
//      | FLOAT 浮点数
pType Exp(pNode node);

//实参列表（一个及以上）
// Args -> Exp COMMA Args
//       | Exp
void Args(pNode node, TableItem funcInfo);


#endif