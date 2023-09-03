#include "inter.h"

boolean interError = FALSE;
pInterCodeList globalInterCodeList;

// Operand func
pOperand newOperand(OperandKind kind, int argc, ...) {
    pOperand p = (pOperand) malloc(sizeof(Operand));
    assert(p != NULL);
    p->kind = kind;
    p->count = 0;
    va_list vaList;
    assert(argc == 1);
    va_start(vaList, argc);
    switch (kind) {
        case OP_CONSTANT:
            p->u.value = va_arg(vaList, int);
            break;
        case OP_VARIABLE:
        case OP_ADDRESS:
        case OP_LABEL:
        case OP_FUNCTION:
        case OP_RELOP:
            p->u.name = va_arg(vaList, char*);
            break;
    }

    return p;
}

void addOperandRef(pOperand p) {
    assert(p != NULL);
    p->count++;
}

void setOperand(pOperand p, OperandKind kind, void *val) {
    assert(p != NULL);
    p->kind = kind;
    switch (kind) {
        case OP_CONSTANT:
            p->u.value = (long) val;
            break;
        case OP_VARIABLE:
        case OP_ADDRESS:
        case OP_LABEL:
        case OP_FUNCTION:
        case OP_RELOP:
            if (p->u.name) free(p->u.name);
            p->u.name = (char *) val;
            break;
    }
}

void deleteOperand(pOperand p) {
    if (p == NULL) return;
    if (p->count > 1) {
        p->count--;
        return;
    }
    switch (p->kind) {
        case OP_CONSTANT:
            break;
        case OP_VARIABLE:
        case OP_ADDRESS:
        case OP_LABEL:
        case OP_FUNCTION:
        case OP_RELOP:
            if (p->u.name) {
                free(p->u.name);
                p->u.name = NULL;
            }
            break;
    }
    free(p);
}

void printOp(FILE *fp, pOperand op) {
    assert(op != NULL);
    if (fp == NULL) {
        switch (op->kind) {
            case OP_CONSTANT:
                printf("#%ld", op->u.value);
                break;
            case OP_VARIABLE:
            case OP_ADDRESS:
            case OP_LABEL:
            case OP_FUNCTION:
            case OP_RELOP:
                printf("%s", op->u.name);
                break;
        }
    } else {
        switch (op->kind) {
            case OP_CONSTANT:
                fprintf(fp, "#%ld", op->u.value);
                break;
            case OP_VARIABLE:
            case OP_ADDRESS:
            case OP_LABEL:
            case OP_FUNCTION:
            case OP_RELOP:
                fprintf(fp, "%s", op->u.name);
                break;
        }
    }
}

// InterCode func
pInterCode newInterCode(InterCodeKind kind, int argc, ...) {
    pInterCode p = (pInterCode) malloc(sizeof(InterCode));
    assert(p != NULL);
    p->kind = kind;
    va_list vaList;
    switch (kind) {
        case IR_LABEL:
        case IR_FUNCTION:
        case IR_GOTO:
        case IR_RETURN:
        case IR_ARG:
        case IR_PARAM:
        case IR_READ:
        case IR_WRITE:
            assert(argc == 1);
            va_start(vaList, argc);
            p->u.oneOp.op = va_arg(vaList, pOperand);
            addOperandRef(p->u.oneOp.op);
            break;
        case IR_ASSIGN:
        case IR_GET_ADDR:
        case IR_READ_ADDR:
        case IR_WRITE_ADDR:
        case IR_CALL:
            assert(argc == 2);
            va_start(vaList, argc);
            p->u.assign.left = va_arg(vaList, pOperand);
            p->u.assign.right = va_arg(vaList, pOperand);
            addOperandRef(p->u.assign.left);
            addOperandRef(p->u.assign.right);
            break;
        case IR_ADD:
        case IR_SUB:
        case IR_MUL:
        case IR_DIV:
            assert(argc == 3);
            va_start(vaList, argc);
            p->u.binOp.result = va_arg(vaList, pOperand);
            p->u.binOp.op1 = va_arg(vaList, pOperand);
            p->u.binOp.op2 = va_arg(vaList, pOperand);
            addOperandRef(p->u.binOp.result);
            addOperandRef(p->u.binOp.op1);
            addOperandRef(p->u.binOp.op2);
            break;
        case IR_DEC:
            assert(argc == 2);
            va_start(vaList, argc);
            p->u.dec.op = va_arg(vaList, pOperand);
            addOperandRef(p->u.dec.op);
            p->u.dec.size = va_arg(vaList, int);
            break;
        case IR_IF_GOTO:
            assert(argc == 4);
            va_start(vaList, argc);
            p->u.ifGoto.x = va_arg(vaList, pOperand);
            p->u.ifGoto.relop = va_arg(vaList, pOperand);
            p->u.ifGoto.y = va_arg(vaList, pOperand);
            p->u.ifGoto.z = va_arg(vaList, pOperand);
            addOperandRef(p->u.ifGoto.x);
            addOperandRef(p->u.ifGoto.relop);
            addOperandRef(p->u.ifGoto.y);
            addOperandRef(p->u.ifGoto.z);
    }
    return p;
}

void deleteInterCode(pInterCode p) {
    assert(p != NULL);
    switch (p->kind) {
        case IR_LABEL:
        case IR_FUNCTION:
        case IR_GOTO:
        case IR_RETURN:
        case IR_ARG:
        case IR_PARAM:
        case IR_READ:
        case IR_WRITE:
            deleteOperand(p->u.oneOp.op);
            p->u.oneOp.op = NULL;
            break;
        case IR_ASSIGN:
        case IR_GET_ADDR:
        case IR_READ_ADDR:
        case IR_WRITE_ADDR:
        case IR_CALL:
            deleteOperand(p->u.assign.left);
            deleteOperand(p->u.assign.right);
            p->u.assign.left = NULL;
            p->u.assign.right = NULL;
            break;
        case IR_ADD:
        case IR_SUB:
        case IR_MUL:
        case IR_DIV:
            deleteOperand(p->u.binOp.result);
            deleteOperand(p->u.binOp.op1);
            deleteOperand(p->u.binOp.op2);
            p->u.binOp.result = NULL;
            p->u.binOp.op1 = NULL;
            p->u.binOp.op2 = NULL;
            break;
        case IR_DEC:
            deleteOperand(p->u.dec.op);
            p->u.dec.op = NULL;
            break;
        case IR_IF_GOTO:
            deleteOperand(p->u.ifGoto.x);
            deleteOperand(p->u.ifGoto.relop);
            deleteOperand(p->u.ifGoto.y);
            deleteOperand(p->u.ifGoto.z);
            p->u.ifGoto.x = NULL;
            p->u.ifGoto.relop = NULL;
            p->u.ifGoto.y = NULL;
            p->u.ifGoto.z = NULL;
    }
    free(p);
}

void printInterCode(FILE *fp) {
    pInterCodeList interCodeList = globalInterCodeList;
    for (pInterCodes cur = interCodeList->head; cur != NULL; cur = cur->next) {
        if (fp == NULL) {
            switch (cur->code->kind) {
                case IR_LABEL:
                    printf("LABEL ");
                    printOp(fp, cur->code->u.oneOp.op);
                    printf(" :");
                    break;
                case IR_FUNCTION:
                    printf("FUNCTION ");
                    printOp(fp, cur->code->u.oneOp.op);
                    printf(" :");
                    break;
                case IR_ASSIGN:
                    printOp(fp, cur->code->u.assign.left);
                    printf(" := ");
                    printOp(fp, cur->code->u.assign.right);
                    break;
                case IR_ADD:
                    printOp(fp, cur->code->u.binOp.result);
                    printf(" := ");
                    printOp(fp, cur->code->u.binOp.op1);
                    printf(" + ");
                    printOp(fp, cur->code->u.binOp.op2);
                    break;
                case IR_SUB:
                    printOp(fp, cur->code->u.binOp.result);
                    printf(" := ");
                    printOp(fp, cur->code->u.binOp.op1);
                    printf(" - ");
                    printOp(fp, cur->code->u.binOp.op2);
                    break;
                case IR_MUL:
                    printOp(fp, cur->code->u.binOp.result);
                    printf(" := ");
                    printOp(fp, cur->code->u.binOp.op1);
                    printf(" * ");
                    printOp(fp, cur->code->u.binOp.op2);
                    break;
                case IR_DIV:
                    printOp(fp, cur->code->u.binOp.result);
                    printf(" := ");
                    printOp(fp, cur->code->u.binOp.op1);
                    printf(" / ");
                    printOp(fp, cur->code->u.binOp.op2);
                    break;
                case IR_GET_ADDR:
                    printOp(fp, cur->code->u.assign.left);
                    printf(" := &");
                    printOp(fp, cur->code->u.assign.right);
                    break;
                case IR_READ_ADDR:
                    printOp(fp, cur->code->u.assign.left);
                    printf(" := *");
                    printOp(fp, cur->code->u.assign.right);
                    break;
                case IR_WRITE_ADDR:
                    printf("*");
                    printOp(fp, cur->code->u.assign.left);
                    printf(" := ");
                    printOp(fp, cur->code->u.assign.right);
                    break;
                case IR_GOTO:
                    printf("GOTO ");
                    printOp(fp, cur->code->u.oneOp.op);
                    break;
                case IR_IF_GOTO:
                    printf("IF ");
                    printOp(fp, cur->code->u.ifGoto.x);
                    printf(" ");
                    printOp(fp, cur->code->u.ifGoto.relop);
                    printf(" ");
                    printOp(fp, cur->code->u.ifGoto.y);
                    printf(" GOTO ");
                    printOp(fp, cur->code->u.ifGoto.z);
                    break;
                case IR_RETURN:
                    printf("RETURN ");
                    printOp(fp, cur->code->u.oneOp.op);
                    break;
                case IR_DEC:
                    printf("DEC ");
                    printOp(fp, cur->code->u.dec.op);
                    printf(" ");
                    printf("%d", cur->code->u.dec.size);
                    break;
                case IR_ARG:
                    printf("ARG ");
                    printOp(fp, cur->code->u.oneOp.op);
                    break;
                case IR_CALL:
                    printOp(fp, cur->code->u.assign.left);
                    printf(" := CALL ");
                    printOp(fp, cur->code->u.assign.right);
                    break;
                case IR_PARAM:
                    printf("PARAM ");
                    printOp(fp, cur->code->u.oneOp.op);
                    break;
                case IR_READ:
                    printf("READ ");
                    printOp(fp, cur->code->u.oneOp.op);
                    break;
                case IR_WRITE:
                    printf("WRITE ");
                    printOp(fp, cur->code->u.oneOp.op);
                    break;
            }
            printf("\n");
        } else {
            switch (cur->code->kind) {
                case IR_LABEL:
                    fprintf(fp, "LABEL ");
                    printOp(fp, cur->code->u.oneOp.op);
                    fprintf(fp, " :");
                    break;
                case IR_FUNCTION:
                    fprintf(fp, "FUNCTION ");
                    printOp(fp, cur->code->u.oneOp.op);
                    fprintf(fp, " :");
                    break;
                case IR_ASSIGN:
                    printOp(fp, cur->code->u.assign.left);
                    fprintf(fp, " := ");
                    printOp(fp, cur->code->u.assign.right);
                    break;
                case IR_ADD:
                    printOp(fp, cur->code->u.binOp.result);
                    fprintf(fp, " := ");
                    printOp(fp, cur->code->u.binOp.op1);
                    fprintf(fp, " + ");
                    printOp(fp, cur->code->u.binOp.op2);
                    break;
                case IR_SUB:
                    printOp(fp, cur->code->u.binOp.result);
                    fprintf(fp, " := ");
                    printOp(fp, cur->code->u.binOp.op1);
                    fprintf(fp, " - ");
                    printOp(fp, cur->code->u.binOp.op2);
                    break;
                case IR_MUL:
                    printOp(fp, cur->code->u.binOp.result);
                    fprintf(fp, " := ");
                    printOp(fp, cur->code->u.binOp.op1);
                    fprintf(fp, " * ");
                    printOp(fp, cur->code->u.binOp.op2);
                    break;
                case IR_DIV:
                    printOp(fp, cur->code->u.binOp.result);
                    fprintf(fp, " := ");
                    printOp(fp, cur->code->u.binOp.op1);
                    fprintf(fp, " / ");
                    printOp(fp, cur->code->u.binOp.op2);
                    break;
                case IR_GET_ADDR:
                    printOp(fp, cur->code->u.assign.left);
                    fprintf(fp, " := &");
                    printOp(fp, cur->code->u.assign.right);
                    break;
                case IR_READ_ADDR:
                    printOp(fp, cur->code->u.assign.left);
                    fprintf(fp, " := *");
                    printOp(fp, cur->code->u.assign.right);
                    break;
                case IR_WRITE_ADDR:
                    fprintf(fp, "*");
                    printOp(fp, cur->code->u.assign.left);
                    fprintf(fp, " := ");
                    printOp(fp, cur->code->u.assign.right);
                    break;
                case IR_GOTO:
                    fprintf(fp, "GOTO ");
                    printOp(fp, cur->code->u.oneOp.op);
                    break;
                case IR_IF_GOTO:
                    fprintf(fp, "IF ");
                    printOp(fp, cur->code->u.ifGoto.x);
                    fprintf(fp, " ");
                    printOp(fp, cur->code->u.ifGoto.relop);
                    fprintf(fp, " ");
                    printOp(fp, cur->code->u.ifGoto.y);
                    fprintf(fp, " GOTO ");
                    printOp(fp, cur->code->u.ifGoto.z);
                    break;
                case IR_RETURN:
                    fprintf(fp, "RETURN ");
                    printOp(fp, cur->code->u.oneOp.op);
                    break;
                case IR_DEC:
                    fprintf(fp, "DEC ");
                    printOp(fp, cur->code->u.dec.op);
                    fprintf(fp, " ");
                    fprintf(fp, "%d", cur->code->u.dec.size);
                    break;
                case IR_ARG:
                    fprintf(fp, "ARG ");
                    printOp(fp, cur->code->u.oneOp.op);
                    break;
                case IR_CALL:
                    printOp(fp, cur->code->u.assign.left);
                    fprintf(fp, " := CALL ");
                    printOp(fp, cur->code->u.assign.right);
                    break;
                case IR_PARAM:
                    fprintf(fp, "PARAM ");
                    printOp(fp, cur->code->u.oneOp.op);
                    break;
                case IR_READ:
                    fprintf(fp, "READ ");
                    printOp(fp, cur->code->u.oneOp.op);
                    break;
                case IR_WRITE:
                    fprintf(fp, "WRITE ");
                    printOp(fp, cur->code->u.oneOp.op);
                    break;
            }
            fprintf(fp, "\n");
        }
    }
}

// InterCodes func
pInterCodes newInterCodes(pInterCode code) {
    pInterCodes p = (pInterCodes) malloc(sizeof(InterCodes));
    assert(p != NULL);
    p->code = code;
    p->prev = NULL;
    p->next = NULL;
    return p;
}

void deleteInterCodes(pInterCodes p) {
    assert(p != NULL);
    deleteInterCode(p->code);
    p->code = NULL;
    free(p);
}

// Arg and ArgList func
pArg newArg(pOperand op) {
    pArg p = (pArg) malloc(sizeof(Arg));
    assert(p != NULL);
    p->op = op;
    addOperandRef(op);
    p->next = NULL;
    return p;
}

pArgList newArgList() {
    pArgList p = (pArgList) malloc(sizeof(ArgList));
    assert(p != NULL);
    p->head = NULL;
    return p;
}

void deleteArg(pArg p) {
    assert(p != NULL);
    deleteOperand(p->op);
    free(p);
}

void deleteArgList(pArgList p) {
    assert(p != NULL);
    pArg q = p->head;
    while (q) {
        pArg temp = q;
        q = q->next;
        deleteArg(temp);
    }
    free(p);
}

void addArg(pArgList argList, pArg arg) {
    if (argList->head == NULL) {
        argList->head = arg;
    } else {
        arg->next = argList->head;
        argList->head = arg;
        // argList->cur->next = arg;
        // argList->cur = arg;
    }
}


// InterCodeList func
pInterCodeList newInterCodeList() {
    pInterCodeList p = (pInterCodeList) malloc(sizeof(InterCodeList));
    p->head = NULL;
    p->cur = NULL;
    p->tempVarNum = 1;
    p->labelNum = 1;
    return p;
}

void initInterCodeList() {
    globalInterCodeList = newInterCodeList();
}

void deleteInterCodeList() {
    assert(globalInterCodeList != NULL);
    pInterCodes q = globalInterCodeList->head;
    while (q) {
        pInterCodes temp = q;
        q = q->next;
        deleteInterCodes(temp);
    }
    free(globalInterCodeList);
    globalInterCodeList = NULL;
}

void addInterCode(pInterCodeList interCodeList, pInterCodes newCode) {
    if (interCodeList->head == NULL) {
        interCodeList->head = newCode;
        interCodeList->cur = newCode;
    } else {
        interCodeList->cur->next = newCode;
        newCode->prev = interCodeList->cur;
        interCodeList->cur = newCode;
    }
}

// traverse func
pOperand newTemp() {
    char tName[10] = {0};
    sprintf(tName, "t%d", globalInterCodeList->tempVarNum);
    globalInterCodeList->tempVarNum++;
    pOperand temp = newOperand(OP_VARIABLE, 1, newString(tName));
    return temp;
}

pOperand newLabel() {
    char lName[10] = {0};
    sprintf(lName, "label%d", globalInterCodeList->labelNum);
    globalInterCodeList->labelNum++;
    pOperand temp = newOperand(OP_LABEL, 1, newString(lName));
    return temp;
}

int getSize(pType type) {
    if (type == NULL)
        return 0;
    else if (type->kind == BASIC)
        return 4;
    else if (type->kind == ARRAY)
        return type->u.array.size * getSize(type->u.array.elem);
    else if (type->kind == STRUCTURE) {
        int size = 0;
        TableItem temp = searchTableItem(type->u.structure.structName);
        temp = temp->next;
        while (temp) {
            size += getSize(temp->type);
            temp = temp->next;
        }
        return size;
    }
    return 0;
}

void genInterCodes(pNode node) {
    if (node == NULL) return;
    if (!strcmp(node->name, "ExtDefList"))
        translateExtDefList(node);
    else {
        genInterCodes(node->child);
        genInterCodes(node->next);
    }
}

void genInterCode(InterCodeKind kind, int argc, ...) {
    va_list vaList;
    pOperand temp = NULL;
    pOperand result = NULL, op1 = NULL, op2 = NULL, relop = NULL;
    int size = 0;
    pInterCodes newCode = NULL;
    switch (kind) {
        case IR_LABEL:
        case IR_FUNCTION:
        case IR_GOTO:
        case IR_RETURN:
        case IR_ARG:
        case IR_PARAM:
        case IR_READ:
        case IR_WRITE:
            assert(argc == 1);
            va_start(vaList, argc);
            op1 = va_arg(vaList, pOperand);
            if (op1->kind == OP_ADDRESS) {
                temp = newTemp();
                genInterCode(IR_READ_ADDR, 2, temp, op1);
                op1 = temp;
            }
            newCode = newInterCodes(newInterCode(kind, 1, op1));
            addInterCode(globalInterCodeList, newCode);
            break;
        case IR_ASSIGN:
        case IR_GET_ADDR:
        case IR_READ_ADDR:
        case IR_WRITE_ADDR:
        case IR_CALL:
            assert(argc == 2);
            va_start(vaList, argc);
            op1 = va_arg(vaList, pOperand);
            op2 = va_arg(vaList, pOperand);
            if (kind == IR_ASSIGN
                && (op1->kind == OP_ADDRESS || op2->kind == OP_ADDRESS)) {
                if (op1->kind == OP_ADDRESS && op2->kind != OP_ADDRESS) {
                    genInterCode(IR_WRITE_ADDR, 2, op1, op2);
                } else if (op2->kind == OP_ADDRESS && op1->kind != OP_ADDRESS) {
                    genInterCode(IR_READ_ADDR, 2, op1, op2);
                } else {
                    temp = newTemp();
                    genInterCode(IR_READ_ADDR, 2, temp, op2);
                    genInterCode(IR_WRITE_ADDR, 2, op1, temp);
                }
            } else {
                newCode = newInterCodes(newInterCode(kind, 2, op1, op2));
                addInterCode(globalInterCodeList, newCode);
            }
            break;
        case IR_ADD:
        case IR_SUB:
        case IR_MUL:
        case IR_DIV:
            assert(argc == 3);
            va_start(vaList, argc);
            result = va_arg(vaList, pOperand);
            op1 = va_arg(vaList, pOperand);
            op2 = va_arg(vaList, pOperand);
            if (op1->kind == OP_ADDRESS) {
                temp = newTemp();
                genInterCode(IR_READ_ADDR, 2, temp, op1);
                op1 = temp;
            }
            if (op2->kind == OP_ADDRESS) {
                temp = newTemp();
                genInterCode(IR_READ_ADDR, 2, temp, op2);
                op2 = temp;
            }
            newCode = newInterCodes(newInterCode(kind, 3, result, op1, op2));
            addInterCode(globalInterCodeList, newCode);
            break;
        case IR_DEC:
            assert(argc == 2);
            va_start(vaList, argc);
            op1 = va_arg(vaList, pOperand);
            size = va_arg(vaList, int);
            newCode = newInterCodes(newInterCode(kind, 2, op1, size));
            addInterCode(globalInterCodeList, newCode);
            break;
        case IR_IF_GOTO:
            assert(argc == 4);
            va_start(vaList, argc);
            result = va_arg(vaList, pOperand);
            relop = va_arg(vaList, pOperand);
            op1 = va_arg(vaList, pOperand);
            op2 = va_arg(vaList, pOperand);
            newCode =
                    newInterCodes(newInterCode(kind, 4, result, relop, op1, op2));
            addInterCode(globalInterCodeList, newCode);
            break;
    }
}

// ExtDefList -> ExtDef ExtDefList
//             | e
void translateExtDefList(pNode node) {
    // ExtDefList -> ExtDef ExtDefList
    //             | e
    while (node) {
        translateExtDef(node->child);
        node = node->child->next;
    }
}

// ExtDef -> Specifier ExtDecList SEMI
//         | Specifier SEMI
//         | Specifier FunDec CompSt
void translateExtDef(pNode node) {
    assert(node != NULL);
    if (interError) return;

    // 因为没有全局变量使用，
    // ExtDecList不涉及中间代码生成，类型声明也不涉及，所以只需要处理FunDec和CompSt
    // ExtDef -> Specifier FunDec CompSt
    if (!strcmp(node->child->next->name, "FunDec")) {
        translateFunDec(node->child->next);
        translateCompSt(node->child->next->next);
    }
}

// FunDec -> ID LP VarList RP
//         | ID LP RP
void translateFunDec(pNode node) {
    assert(node != NULL);
    if (interError) return;

    genInterCode(IR_FUNCTION, 1,
                 newOperand(OP_FUNCTION, 1, newString(node->child->val)));

    TableItem funcItem = searchTableItem(node->child->val);
    TableItem temp = funcItem->next;
    while (temp) {
        genInterCode(IR_PARAM, 1, newOperand(OP_VARIABLE, 1, newString(temp->name)));
        temp = temp->next;
    }
}

// CompSt -> LC DefList StmtList RC
void translateCompSt(pNode node) {
    assert(node != NULL);
    if (interError) return;

    pNode temp = node->child->next;
    if (!strcmp(temp->name, "DefList")) {
        translateDefList(temp);
        temp = temp->next;
    }
    if (!strcmp(temp->name, "StmtList")) {
        translateStmtList(temp);
    }
}

// DefList -> Def DefList
//          | e
void translateDefList(pNode node) {
    if (interError) return;

    while (node) {
        translateDef(node->child);
        node = node->child->next;
    }
}

// Def -> Specifier DecList SEMI
void translateDef(pNode node) {
    assert(node != NULL);
    if (interError) return;

    translateDecList(node->child->next);
}

// DecList -> Dec
//          | Dec COMMA DecList
void translateDecList(pNode node) {
    assert(node != NULL);
    if (interError) return;

    pNode temp = node;
    while (temp) {
        translateDec(temp->child);
        if (temp->child->next)
            temp = temp->child->next->next;
        else
            break;
    }
}

// Dec -> VarDec
//      | VarDec ASSIGNOP Exp
void translateDec(pNode node) {
    assert(node != NULL);
    if (interError) return;

    // Dec -> VarDec
    if (node->child->next == NULL) {
        translateVarDec(node->child, NULL);
    }
        // Dec -> VarDec ASSIGNOP Exp
    else {
        pOperand t1 = newTemp();
        translateVarDec(node->child, t1);
        pOperand t2 = newTemp();
        translateExp(node->child->next->next, t2);
        genInterCode(IR_ASSIGN, 2, t1, t2);
    }
}

// VarDec -> ID
//         | VarDec LB INT RB
void translateVarDec(pNode node, pOperand place) {
    assert(node != NULL);
    if (interError) return;

    if (!strcmp(node->child->name, "ID")) {
        TableItem temp = searchTableItem(node->child->val);
        pType type = temp->type;
        if (type->kind == BASIC) {
            if (place) {
                globalInterCodeList->tempVarNum--;
                setOperand(place, OP_VARIABLE,
                           (void *) newString(temp->name));
            }
        } else if (type->kind == ARRAY) {
            // 不需要完成高维数组情况
            if (type->u.array.elem->kind == ARRAY) {
                interError = TRUE;
                printf("Cannot translate: Code contains variables of "
                       "multi-dimensional array type or parameters of array "
                       "type.\n");
                return;
            } else {
                genInterCode(
                        IR_DEC, 2,
                        newOperand(OP_VARIABLE, 1, newString(temp->name)),
                        getSize(type));
            }
        } else if (type->kind == STRUCTURE) {
            interError = TRUE;
            printf("The code contains variables or parameters of the struct type.\n");
            return;
        }
    } else {
        translateVarDec(node->child, place);
    }
}

// StmtList -> Stmt StmtList
//           | e
void translateStmtList(pNode node) {
    if (interError) return;

    while (node) {
        translateStmt(node->child);
        node = node->child->next;
    }
}

// Stmt -> Exp SEMI
//       | CompSt
//       | RETURN Exp SEMI
//       | IF LP Exp RP Stmt
//       | IF LP Exp RP Stmt ELSE Stmt
//       | WHILE LP Exp RP Stmt
void translateStmt(pNode node) {
    assert(node != NULL);
    if (interError) return;

    // Stmt -> Exp SEMI
    if (!strcmp(node->child->name, "Exp")) {
        translateExp(node->child, NULL);
    }
        // Stmt -> CompSt
    else if (!strcmp(node->child->name, "CompSt")) {
        translateCompSt(node->child);
    }
        // Stmt -> RETURN Exp SEMI
    else if (!strcmp(node->child->name, "RETURN")) {
        pOperand t1 = newTemp();
        translateExp(node->child->next, t1);
        genInterCode(IR_RETURN, 1, t1);
    }
        // Stmt -> IF LP Exp RP Stmt
    else if (!strcmp(node->child->name, "IF")) {
        pNode exp = node->child->next->next;
        pNode stmt = exp->next->next;
        pOperand label1 = newLabel();
        pOperand label2 = newLabel();

        translateCond(exp, label1, label2);
        genInterCode(IR_LABEL, 1, label1);
        translateStmt(stmt);
        if (stmt->next == NULL) {
            genInterCode(IR_LABEL, 1, label2);
        }
            // Stmt -> IF LP Exp RP Stmt ELSE Stmt
        else {
            pOperand label3 = newLabel();
            genInterCode(IR_GOTO, 1, label3);
            genInterCode(IR_LABEL, 1, label2);
            translateStmt(stmt->next->next);
            genInterCode(IR_LABEL, 1, label3);
        }

    }
        // Stmt -> WHILE LP Exp RP Stmt
    else if (!strcmp(node->child->name, "WHILE")) {
        pOperand label1 = newLabel();
        pOperand label2 = newLabel();
        pOperand label3 = newLabel();

        genInterCode(IR_LABEL, 1, label1);
        translateCond(node->child->next->next, label2, label3);
        genInterCode(IR_LABEL, 1, label2);
        translateStmt(node->child->next->next->next->next);
        genInterCode(IR_GOTO, 1, label1);
        genInterCode(IR_LABEL, 1, label3);
    }
}
// Exp -> Exp ASSIGNOP Exp
//      | Exp AND Exp
//      | Exp OR Exp
//      | Exp RELOP Exp
//      | Exp PLUS Exp
//      | Exp MINUS Exp
//      | Exp STAR Exp
//      | Exp DIV Exp

//      | MINUS Exp
//      | NOT Exp
//      | ID LP Args RP
//      | ID LP RP
//      | Exp LB Exp RB
//      | Exp DOT ID
//      | ID
//      | INT
//      | FLOAT
void translateExp(pNode node, pOperand place) {
    assert(node != NULL);
    if (interError) return;

    // Exp -> LP Exp RP
    if (!strcmp(node->child->name, "LP"))
        translateExp(node->child->next, place);

    else if (!strcmp(node->child->name, "Exp") ||
             !strcmp(node->child->name, "NOT")) {
        // 条件表达式 和 基本表达式
        if (strcmp(node->child->next->name, "LB") != 0 &&
            strcmp(node->child->next->name, "DOT") != 0) {
            // Exp -> Exp AND Exp
            //      | Exp OR Exp
            //      | Exp RELOP Exp
            //      | NOT Exp
            if (!strcmp(node->child->next->name, "AND") ||
                !strcmp(node->child->next->name, "OR") ||
                !strcmp(node->child->next->name, "RELOP") ||
                !strcmp(node->child->name, "NOT")) {
                pOperand label1 = newLabel();
                pOperand label2 = newLabel();
                pOperand true_num = newOperand(OP_CONSTANT, 1, 1);
                pOperand false_num = newOperand(OP_CONSTANT, 1, 0);
                genInterCode(IR_ASSIGN, 2, place, false_num);
                translateCond(node, label1, label2);
                genInterCode(IR_LABEL, 1, label1);
                genInterCode(IR_ASSIGN, 2, place, true_num);
            } else {
                // Exp -> Exp ASSIGNOP Exp
                if (!strcmp(node->child->next->name, "ASSIGNOP")) {
                    pOperand t2 = newTemp();
                    translateExp(node->child->next->next, t2);
                    pOperand t1 = newTemp();
                    translateExp(node->child, t1);
                    genInterCode(IR_ASSIGN, 2, t1, t2);
                } else {
                    pOperand t1 = newTemp();
                    translateExp(node->child, t1);
                    pOperand t2 = newTemp();
                    translateExp(node->child->next->next, t2);
                    // Exp -> Exp PLUS Exp
                    if (!strcmp(node->child->next->name, "PLUS")) {
                        genInterCode(IR_ADD, 3, place, t1, t2);
                    }
                        // Exp -> Exp MINUS Exp
                    else if (!strcmp(node->child->next->name, "MINUS")) {
                        genInterCode(IR_SUB, 3, place, t1, t2);
                    }
                        // Exp -> Exp STAR Exp
                    else if (!strcmp(node->child->next->name, "STAR")) {
                        genInterCode(IR_MUL, 3, place, t1, t2);
                    }
                        // Exp -> Exp DIV Exp
                    else if (!strcmp(node->child->next->name, "DIV")) {
                        genInterCode(IR_DIV, 3, place, t1, t2);
                    }
                }
            }

        }
            // 数组和结构体访问
        else {
            // Exp -> Exp LB Exp RB
            if (!strcmp(node->child->next->name, "LB")) {
                //数组
                if (node->child->child->next &&
                    !strcmp(node->child->child->next->name, "LB")) {
                    //多维数组，报错
                    interError = TRUE;
                    printf(
                            "Cannot translate: Code containsvariables of "
                            "multi-dimensional array type or parameters of array "
                            "type.\n");
                    return;
                } else {
                    pOperand idx = newTemp();
                    translateExp(node->child->next->next, idx);
                    pOperand base = newTemp();
                    translateExp(node->child, base);

                    pOperand width;
                    pOperand offset = newTemp();
                    pOperand target;
                    // 根据假设，Exp1只会展开为 Exp DOT ID 或 ID
                    // 我们让前一种情况吧ID作为name回填进place返回到这里的base处，在语义分析时将结构体变量也填进表（因为假设无重名），这样两种情况都可以查表得到。
                    TableItem item = searchTableItem(base->u.name);
                    assert(item->type->kind == ARRAY);
                    width = newOperand(
                            OP_CONSTANT, 1, getSize(item->type->u.array.elem));
                    genInterCode(IR_MUL, 3, offset, idx, width);
                    // 如果是ID[Exp],
                    // 则需要对ID取址，如果前面是结构体内访问，则会返回一个地址类型，不需要再取址
                    if (base->kind == OP_VARIABLE) {
                        // printf("非结构体数组访问\n");
                        target = newTemp();
                        genInterCode(IR_GET_ADDR, 2, target, base);
                    } else {
                        interError = TRUE;
                        printf("The code contains variables or parameters of the struct type.\n");
                        return;
                        // printf("结构体数组访问\n");
                    }
                    genInterCode(IR_ADD, 3, place, target, offset);
                    place->kind = OP_ADDRESS;
                }
            }
                // Exp -> Exp DOT ID
            else {
                //结构体
                interError = TRUE;
                printf("The code contains variables or parameters of the struct type.\n");
                return;
            }
        }
    }
        //单目运算符
        // Exp -> MINUS Exp
    else if (!strcmp(node->child->name, "MINUS")) {
        pOperand t1 = newTemp();
        translateExp(node->child->next, t1);
        pOperand zero = newOperand(OP_CONSTANT, 1, 0);
        genInterCode(IR_SUB, 3, place, zero, t1);
    }
        // Exp -> ID LP Args RP
        //		| ID LP RP
    else if (!strcmp(node->child->name, "ID") && node->child->next) {
        pOperand funcTemp =
                newOperand(OP_FUNCTION, 1, newString(node->child->val));
        // Exp -> ID LP Args RP
        if (!strcmp(node->child->next->next->name, "Args")) {
            pArgList argList = newArgList();
            translateArgs(node->child->next->next, argList);
            if (!strcmp(node->child->val, "write")) {
                genInterCode(IR_WRITE, 1, argList->head->op);
            } else {
                pArg argTemp = argList->head;
                while (argTemp) {
                    if (argTemp->op == OP_VARIABLE) {
                        TableItem item =
                                searchTableItem(argTemp->op->u.name);
                        // 结构体
                        if (item && item->type->kind == STRUCTURE) {
                            interError = TRUE;
                            printf("The code contains variables or parameters of the struct type.\n");
                            return;
                        }
                    }
                        // 一般参数直接传值
                    else {
                        genInterCode(IR_ARG, 1, argTemp->op);
                    }
                    argTemp = argTemp->next;
                }
                if (place) {
                    genInterCode(IR_CALL, 2, place, funcTemp);
                } else {
                    pOperand temp = newTemp();
                    genInterCode(IR_CALL, 2, temp, funcTemp);
                }
            }
        }
            // Exp -> ID LP RP
        else {
            if (!strcmp(node->child->val, "read")) {
                genInterCode(IR_READ, 1, place);
            } else {
                if (place) {
                    genInterCode(IR_CALL, 2, place, funcTemp);
                } else {
                    pOperand temp = newTemp();
                    genInterCode(IR_CALL, 2, temp, funcTemp);
                }
            }
        }
    }
        // Exp -> ID
    else if (!strcmp(node->child->name, "ID")) {
        TableItem item = searchTableItem(node->child->val);
        // 根据讲义，因为结构体不允许赋值，结构体做形参时是传址的方式
        globalInterCodeList->tempVarNum--;
        if (item->isArg && item->type->kind == STRUCTURE) {
//            setOperand(place, OP_ADDRESS, (void *) newString(node->child->val));
            // place->isAddr = TRUE;
            interError = TRUE;
            printf("The code contains variables or parameters of the struct type.\n");
            return;
        }
            // 非结构体参数情况都当做变量处理
        else {
            setOperand(place, OP_VARIABLE, (void *) newString(node->child->val));
        }

    } else {
        // // Exp -> FLOAT
        // 无浮点数常数

        // Exp -> INT
        globalInterCodeList->tempVarNum--;
        setOperand(place, OP_CONSTANT, (void *) (long) atoi(node->child->val));
    }
}
// Exp -> Exp AND Exp
//      | Exp OR Exp
//      | Exp RELOP Exp
//      | NOT Exp

void translateCond(pNode node, pOperand labelTrue, pOperand labelFalse) {
    assert(node != NULL);
    if (interError) return;

    // Exp -> NOT Exp
    if (!strcmp(node->child->name, "NOT")) {
        translateCond(node->child->next, labelFalse, labelTrue);
    }
        // Exp -> Exp RELOP Exp
    else if (!strcmp(node->child->next->name, "RELOP")) {
        pOperand t1 = newTemp();
        pOperand t2 = newTemp();
        translateExp(node->child, t1);
        translateExp(node->child->next->next, t2);

        pOperand relop =
                newOperand(OP_RELOP, 1, newString(node->child->next->val));

        if (t1->kind == OP_ADDRESS) {
            pOperand temp = newTemp();
            genInterCode(IR_READ_ADDR, 2, temp, t1);
            t1 = temp;
        }
        if (t2->kind == OP_ADDRESS) {
            pOperand temp = newTemp();
            genInterCode(IR_READ_ADDR, 2, temp, t2);
            t2 = temp;
        }
        genInterCode(IR_IF_GOTO, 4, t1, relop, t2, labelTrue);
        genInterCode(IR_GOTO, 1, labelFalse);
    }
        // Exp -> Exp AND Exp
    else if (!strcmp(node->child->next->name, "AND")) {
        pOperand label1 = newLabel();
        translateCond(node->child, label1, labelFalse);
        genInterCode(IR_LABEL, 1, label1);
        translateCond(node->child->next->next, labelTrue, labelFalse);
    }
        // Exp -> Exp OR Exp
    else if (!strcmp(node->child->next->name, "OR")) {
        pOperand label1 = newLabel();
        translateCond(node->child, labelTrue, label1);
        genInterCode(IR_LABEL, 1, label1);
        translateCond(node->child->next->next, labelTrue, labelFalse);
    }
        // other cases
    else {
        pOperand t1 = newTemp();
        translateExp(node, t1);
        pOperand t2 = newOperand(OP_CONSTANT, 1, 0);
        pOperand relop = newOperand(OP_RELOP, 1, newString("!="));

        if (t1->kind == OP_ADDRESS) {
            pOperand temp = newTemp();
            genInterCode(IR_READ_ADDR, 2, temp, t1);
            t1 = temp;
        }
        genInterCode(IR_IF_GOTO, 4, t1, relop, t2, labelTrue);
        genInterCode(IR_GOTO, 1, labelFalse);
    }
}

// Args -> Exp COMMA Args
//       | Exp
void translateArgs(pNode node, pArgList argList) {
    assert(node != NULL);
    assert(argList != NULL);
    if (interError) return;

    // Args -> Exp
    pArg temp = newArg(newTemp());
    translateExp(node->child, temp->op);

    if (temp->op->kind == OP_VARIABLE) {
        TableItem item = searchTableItem(temp->op->u.name);
        if (item && item->type->kind == ARRAY) {
            interError = TRUE;
            printf("Cannot translate: Code containsvariables of "
                   "multi-dimensional array type or parameters of array "
                   "type.\n");
            return;
        }
    }
    addArg(argList, temp);

    // Args -> Exp COMMA Args
    if (node->child->next != NULL) {
        translateArgs(node->child->next->next, argList);
    }
}

