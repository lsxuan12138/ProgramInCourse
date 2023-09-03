#ifndef INTER_H
#define INTER_H
#include "node.h"
#include "semantic.h"

typedef struct operand* pOperand;
typedef struct interCode* pInterCode;
typedef struct interCodes* pInterCodes;
typedef struct arg* pArg;
typedef struct argList* pArgList;
typedef struct interCodeList* pInterCodeList;

typedef struct operand {
    int count;
    OperandKind kind;
    union {
        long value;
        char* name;
    } u;

} Operand;

typedef struct interCode {
    InterCodeKind kind;
    union {
        struct {
            pOperand op;
        } oneOp;
        struct {
            pOperand right, left;
        } assign;
        struct {
            pOperand result, op1, op2;
        } binOp;
        struct {
            pOperand x, relop, y, z;
        } ifGoto;
        struct {
            pOperand op;
            int size;
        } dec;
    } u;
} InterCode;

typedef struct interCodes {
    pInterCode code;
    pInterCodes prev, next;
} InterCodes;

typedef struct arg {
    pOperand op;
    pArg next;
} Arg;

typedef struct argList {
    pArg head;
} ArgList;

typedef struct interCodeList {
    pInterCodes head;
    pInterCodes cur;
    int tempVarNum;
    int labelNum;
} InterCodeList;

extern boolean interError;


// Operand func
pOperand newOperand(OperandKind kind,int argc ,...);
void deleteOperand(pOperand p);
void setOperand(pOperand p, OperandKind kind, void* val);
void printOp(FILE* fp, pOperand op);

// InterCode func
pInterCode newInterCode(InterCodeKind kind, int argc,...);
void deleteInterCode(pInterCode p);
void printInterCode(FILE* fp);

// InterCodes func
pInterCodes newInterCodes(pInterCode code);
void deleteInterCodes(pInterCodes p);

// Arg and ArgList func
pArg newArg(pOperand op);
pArgList newArgList();
void deleteArg(pArg p);
void deleteArgList(pArgList p);
void addArg(pArgList argList, pArg arg);

// InterCodeList func
void initInterCodeList();
pInterCodeList newInterCodeList();

void deleteInterCodeList();
void addInterCode(pInterCodeList interCodeList, pInterCodes newCode);

// traverse func
pOperand newTemp();
pOperand newLabel();
int getSize(pType type);
void genInterCodes(pNode node);
void genInterCode(InterCodeKind kind, int argc,...);
void translateExp(pNode node, pOperand place);
void translateArgs(pNode node, pArgList argList);
void translateCond(pNode node, pOperand labelTrue, pOperand labelFalse);
void translateVarDec(pNode node, pOperand place);
void translateDec(pNode node);
void translateDecList(pNode node);
void translateDef(pNode node);
void translateDefList(pNode node);
void translateCompSt(pNode node);
void translateStmt(pNode node);
void translateStmtList(pNode node);
void translateFunDec(pNode node);
void translateExtDef(pNode node);
void translateExtDefList(pNode node);
#endif