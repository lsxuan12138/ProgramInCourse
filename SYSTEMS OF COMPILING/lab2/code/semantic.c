#include "semantic.h"


TableItem symbolTable;
int unNamedStructNum;

// Global function
void traverseTree(pNode node) {
    if (node == NULL) return;

    if (!strcmp(node->name, "ExtDef")) ExtDef(node);

    traverseTree(node->child);
    traverseTree(node->next);
}

// 获取树的高度
int height(pAvlNode node) {
    if (node == NULL)
        return 0;
    return node->height;
}

// 获取最大值
int max(int a, int b) {
    return (a > b) ? a : b;
}

// 左旋转子树
pAvlNode leftRotate(pAvlNode x) {
    pAvlNode y = x->right;
    pAvlNode T2 = y->left;

    y->left = x;
    x->right = T2;

    x->height = max(height(x->left), height(x->right)) + 1;
    y->height = max(height(y->left), height(y->right)) + 1;

    return y;
}

// 右旋转子树
pAvlNode rightRotate(pAvlNode y) {
    pAvlNode x = y->left;
    pAvlNode T2 = x->right;

    x->right = y;
    y->left = T2;

    y->height = max(height(y->left), height(y->right)) + 1;
    x->height = max(height(x->left), height(x->right)) + 1;

    return x;
}

// 平衡因子
int getBalanceFactor(pAvlNode N) {
    if (N == NULL)
        return 0;
    int l = height(N->left);
    int r = height(N->right);
    return l - r;
}

// 插入操作
pAvlNode insert(pAvlNode node, pAvlNode data) {
    // 如果节点为空，直接插入新节点
    if (node == NULL)
        return (data);

    if (strcmp(data->name, node->name) < 0)
        node->left = insert(node->left, data);
    else if (strcmp(data->name, node->name) > 0)
        node->right = insert(node->right, data);
    else
        return node;

    // 更新结点的高度
    node->height = 1 + max(height(node->left), height(node->right));

    // 获取平衡因子
    int balanceFactor = getBalanceFactor(node);

    // 左旋转的情况 LL
    if (balanceFactor > 1 && strcmp(data->name, node->left->name) < 0)
        return rightRotate(node);

    // 右旋转的情况 RR
    if (balanceFactor < -1 && strcmp(data->name, node->right->name) > 0)
        return leftRotate(node);

    // LR 情况
    if (balanceFactor > 1 && strcmp(data->name, node->left->name) > 0) {
        node->left = leftRotate(node->left);
        return rightRotate(node);
    }

    // RL 情况
    if (balanceFactor < -1 && strcmp(data->name, node->right->name) < 0) {
        node->right = rightRotate(node->right);
        return leftRotate(node);
    }

    return node;
}

/*查找结点*/
pAvlNode search(pAvlNode tree, char *name) {
    if (tree == NULL || strcmp(tree->name, name) == 0)
        return tree;
    else if (strcmp(name, tree->name) > 0)
        return search(tree->right, name);
    else
        return search(tree->left, name);
    return NULL;
}

void delAvlNode(pAvlNode node) {
    free(node->name);
    deleteType(node->type);
}

pAvlNode newAvlNode(char *name,boolean isField) {
    pAvlNode node = (pAvlNode) malloc(sizeof(AVLNode));
    node->name = newString(name);
    node->type = NULL;
    node->next = NULL;
    node->isField = isField;
    node->height = 0;
    node->left = NULL;
    node->right = NULL;
    return node;
}
void delTable(pAvlNode root) {
    if (root == NULL)return;
    delTable(root->left);
    delTable(root->right);

    delAvlNode(root);
}

void printTree(pAvlNode root) {
    if (root == NULL)return;
    printf("Node %s\n", root->name);
    printf("Node addr: %p\n", root);
    printf("lChild addr: %p\n", root->left);
    printf("rChild addr: %p\n", root->right);
    printf("next addr: %p\n", root->next);
    printType(root->type);
    printTree(root->left);
    printTree(root->right);
}

void initTable() {
    if (symbolTable)deleteTable();
    symbolTable = NULL;
    unNamedStructNum = 0;
}

void printTable() {
    printTree(symbolTable);
}

void deleteTable() {
    delTable(symbolTable);
    symbolTable = NULL;
    unNamedStructNum = 0;
}

boolean findConflict(pAvlNode table, char *name) {
    pAvlNode node = search(table, name);
    return node != NULL;
}

boolean isStructDef(TableItem src) {
    if (src == NULL) return FALSE;
    if (src->type->kind != STRUCTURE) return FALSE;
    if (src->type->u.structure.structName) return FALSE;
    if(src->isField)return FALSE;
    return TRUE;
}

// Type functions
pType newType(Kind kind, int argc, ...) {
    pType p = (pType) malloc(sizeof(Type));
    assert(p != NULL);
    p->kind = kind;
    va_list vaList;
    assert(kind == BASIC || kind == ARRAY || kind == STRUCTURE ||
           kind == FUNCTION);
    switch (kind) {
        case BASIC:
            assert(argc == 1);
            va_start(vaList, argc);
            p->u.basic = va_arg(vaList, BasicType);
            break;
        case ARRAY:
            assert(argc == 2);
            va_start(vaList, argc);
            p->u.array.elem = va_arg(vaList, pType);
            p->u.array.size = va_arg(vaList, int);
            break;
        case STRUCTURE:
            assert(argc == 1);
            va_start(vaList, argc);
            p->u.structure.structName = va_arg(vaList, char*);
            break;
        case FUNCTION:
            assert(argc == 2);
            va_start(vaList, argc);
            p->u.function.argc = va_arg(vaList, int);
            p->u.function.returnType = va_arg(vaList, pType);
            break;
    }
    va_end(vaList);
    return p;
}

pType copyType(pType src) {
    if (src == NULL) return NULL;
    pType p = (pType) malloc(sizeof(Type));
    assert(p != NULL);
    p->kind = src->kind;
    assert(p->kind == BASIC || p->kind == ARRAY || p->kind == STRUCTURE ||
           p->kind == FUNCTION);
    switch (p->kind) {
        case BASIC:
            p->u.basic = src->u.basic;
            break;
        case ARRAY:
            p->u.array.elem = copyType(src->u.array.elem);
            p->u.array.size = src->u.array.size;
            break;
        case STRUCTURE:
            p->u.structure.structName = newString(src->u.structure.structName);
            break;
        case FUNCTION:
            p->u.function.argc = src->u.function.argc;
            p->u.function.returnType = copyType(src->u.function.returnType);
            break;
    }

    return p;
}

void deleteType(pType type) {
    if (type == NULL)return;
    assert(type->kind == BASIC || type->kind == ARRAY ||
           type->kind == STRUCTURE || type->kind == FUNCTION);
    switch (type->kind) {
        case BASIC:
            break;
        case ARRAY:
            deleteType(type->u.array.elem);
            type->u.array.elem = NULL;
            break;
        case STRUCTURE:
            if (type->u.structure.structName)
                free(type->u.structure.structName);
            type->u.structure.structName = NULL;
            break;
        case FUNCTION:
            deleteType(type->u.function.returnType);
            type->u.function.returnType = NULL;
            break;
    }
    free(type);
}

boolean checkType(pType type1, pType type2) {
    if (type1 == NULL || type2 == NULL) return TRUE;
    if (type1->kind == FUNCTION || type2->kind == FUNCTION) return FALSE;
    if (type1->kind != type2->kind)
        return FALSE;
    else {
        assert(type1->kind == BASIC || type1->kind == ARRAY ||
               type1->kind == STRUCTURE);
        switch (type1->kind) {
            case BASIC:
                return type1->u.basic == type2->u.basic;
            case ARRAY:
                return checkType(type1->u.array.elem, type2->u.array.elem);
            case STRUCTURE:
                return !strcmp(type1->u.structure.structName,
                               type2->u.structure.structName);
            case FUNCTION:
                break;
        }
    }
}

void printType(pType type) {
    if (type == NULL) {
        printf("type is NULL.\n");
    } else {
        printf("type kind: %d\n", type->kind);
        switch (type->kind) {
            case BASIC:
                printf("type basic: %d\n", type->u.basic);
                break;
            case ARRAY:
                printf("array size: %d\n", type->u.array.size);
                printType(type->u.array.elem);
                break;
            case STRUCTURE:
                if (!type->u.structure.structName)
                    printf("struct name is NULL\n");
                else {
                    printf("struct name is %s\n", type->u.structure.structName);
                }
                break;
            case FUNCTION:
                printf("function argc is %d\n", type->u.function.argc);
                printf("function args:\n");
                printf("function return type:\n");
                printType(type->u.function.returnType);
                break;
        }
    }
}

// ExtDef -> Specifier ExtDecList SEMI 变量声明
//         | Specifier SEMI 结构体声明
//         | Specifier FunDec CompSt 函数声明
void ExtDef(pNode node) {
    assert(node != NULL);

    pType specifierType = Specifier(node->child);
    char *name = node->child->next->name;

    // ExtDef -> Specifier ExtDecList SEMI
    if (!strcmp(name, "ExtDecList")) {
        ExtDecList(node->child->next, specifierType);
    }
        // ExtDef -> Specifier FunDec CompSt
    else if (!strcmp(name, "FunDec")) {
        FunDec(node->child->next, specifierType);
        CompSt(node->child->next->next, specifierType);
    }
    if (specifierType) deleteType(specifierType);
    specifierType = NULL;
    // Specifier SEMI
    // this situation has no meaning or is struct define(have been processed in Specifier())
}

//全局变量
// ExtDecList -> VarDec 变量名
//             | VarDec COMMA ExtDecList 一行变量名
void ExtDecList(pNode node, pType specifier) {
    assert(node != NULL);

    pNode temp = node;
    while (temp) {
        TableItem item = VarDec(temp->child, specifier);
        if (findConflict(symbolTable, item->name)) {
            char msg[100] = {0};
            sprintf(msg, "Redefined variable \"%s\".", item->name);
            pError(REDEF_VAR, temp->lineNo, msg);
            delAvlNode(item);
        } else {
            symbolTable = insert(symbolTable, item);
        }
        if (temp->child->next) {
            temp = temp->next->next->child;
        } else {
            break;
        }
    }
}

// Specifier -> TYPE 基础类型
//            | StructSpecifier 结构体类型
pType Specifier(pNode node) {
    assert(node != NULL);

    pNode t = node->child;
    // Specifier -> TYPE
    if (!strcmp(t->name, "TYPE")) {
        if (!strcmp(t->val, "float")) {
            return newType(BASIC, 1, FLOAT_TYPE);
        } else {
            return newType(BASIC, 1, INT_TYPE);
        }
    }
        // Specifier -> StructSpecifier
    else {
        return StructSpecifier(t);
    }
}

// StructSpecifier -> STRUCT OptTag LC DefList RC 结构体定义
//                  | STRUCT Tag 结构体引用
pType StructSpecifier(pNode node) {
    assert(node != NULL);

    // OptTag -> ID | e
    // Tag -> ID
    pType type = NULL;
    pNode t = node->child->next;
    // StructSpecifier->STRUCT OptTag LC DefList RC
    if (strcmp(t->name, "Tag") != 0) {
        TableItem structItem = newAvlNode("",FALSE);
        structItem->type = newType(STRUCTURE, 1, NULL);
        if (!strcmp(t->name, "OptTag")) {
            structItem->name = newString(t->child->val);
            //structItem->type->u.structure.structName = newString(t->child->val);
            t = t->next;
        }
            // unnamed struct
        else {
            unNamedStructNum++;
            char structName[20] = {0};
            sprintf(structName, "%d", unNamedStructNum);
            structItem->name = newString(structName);
        }
        //进入结构体了，注意，报错信息会有不同！
        if (!strcmp(t->next->name, "DefList")) {
            DefList(t->next, structItem);
        }

        if (findConflict(symbolTable, structItem->name)) {
            char msg[100] = {0};
            sprintf(msg, "Duplicated name \"%s\".", structItem->name);
            pError(DUPLICATED_NAME, node->lineNo, msg);
            delAvlNode(structItem);
        } else {
            type = newType(STRUCTURE, 1, newString(structItem->name));
            if (!strcmp(node->child->next->name, "OptTag")) {
                symbolTable = insert(symbolTable, structItem);
            }
                // OptTag -> e
            else {
                delAvlNode(structItem);
            }
        }

        //出结构体
    }
        // StructSpecifier->STRUCT Tag
    else {
        TableItem structItem = search(symbolTable, t->child->val);
        if (structItem == NULL || !isStructDef(structItem)) {
            char msg[100] = {0};
            sprintf(msg, "Undefined structure \"%s\".", t->child->val);
            pError(UNDEF_STRUCT, node->lineNo, msg);
        } else
            type = newType(STRUCTURE, 1, newString(structItem->name));
    }
    return type;
}

//一个变量名,不检测冲突
// VarDec -> ID 标识符
//         | VarDec LB INT RB 数组
TableItem VarDec(pNode node, pType specifier) {
    assert(node != NULL);

    pNode id = node;
    // get ID
    while (id->child) id = id->child;
    TableItem p = newAvlNode(id->val,FALSE);
    // VarDec -> ID
    if (!strcmp(node->child->name, "ID")) {
        p->type = copyType(specifier);
    }
        // VarDec -> VarDec LB INT RB
    else {
        pNode varDec = node->child;
        pType temp = specifier;
        while (varDec->next) {
            p->type = newType(ARRAY, 2, copyType(temp), atoi(varDec->next->next->val));
            temp = p->type;
            varDec = varDec->child;
        }
    }
    return p;
}

// FunDec -> ID LP VarList RP 有参函数
//         | ID LP RP 无参函数
//pType returnType为函数返回类型
void FunDec(pNode node, pType returnType) {
    assert(node != NULL);

    TableItem p = newAvlNode(node->child->val,FALSE);
    p->type = newType(FUNCTION, 2, 0, copyType(returnType));

    // FunDec -> ID LP VarList RP
    if (!strcmp(node->child->next->next->name, "VarList")) {
        //参数列表会直接放到p后面
        VarList(node->child->next->next, p);
    }

    // FunDec -> ID LP RP don't need process

    // check redefine
    if (findConflict(symbolTable, p->name)) {
        char msg[100] = {0};
        sprintf(msg, "Redefined function \"%s\".", p->name);
        pError(REDEF_FUNC, node->lineNo, msg);
        delAvlNode(p);
        p = NULL;
    } else {
        symbolTable = insert(symbolTable, p);
    }
}

//参数列表
// VarList -> ParamDec COMMA VarList  参数列表
//          | ParamDec 一个参数
void VarList(pNode node, TableItem func) {
    assert(node != NULL);

    int argc = 0;
    pNode temp = node->child;
    TableItem cur = NULL;

    // VarList -> ParamDec
    TableItem paramDec = ParamDec(temp);
    //func->field->type->u.function.argv = copyFieldList(paramDec);
    func->next = paramDec;
    cur = func->next;
    argc++;

    // VarList -> ParamDec COMMA VarList
    while (temp->next) {
        temp = temp->next->next->child;
        paramDec = ParamDec(temp);
        if (paramDec) {
            cur->next = paramDec;
            cur = cur->next;
            argc++;
        }
    }

    func->type->u.function.argc = argc;
}

// ParamDec -> Specifier VarDec 一个参数（检测冲突）
TableItem ParamDec(pNode node) {
    assert(node != NULL);

    pType specifierType = Specifier(node->child);
    TableItem p = VarDec(node->child->next, specifierType);
    if (specifierType) deleteType(specifierType);
    specifierType = NULL;
    if (findConflict(symbolTable, p->name)) {
        char msg[100] = {0};
        sprintf(msg, "Redefined variable \"%s\".", p->name);
        pError(REDEF_VAR, node->lineNo, msg);
        delAvlNode(p);
        return NULL;
    } else {
        symbolTable = insert(symbolTable, p);
        return p;
    }
}

//语句块或函数体
// CompSt -> LC DefList StmtList RC
//pType returnType为函数返回类型
void CompSt(pNode node, pType returnType) {
    assert(node != NULL);

    pNode temp = node->child->next;
    if (!strcmp(temp->name, "DefList")) {
        DefList(temp, NULL);
        temp = temp->next;
    }
    if (!strcmp(temp->name, "StmtList")) {
        StmtList(temp, returnType);
    }
}

//执行语句列表
// StmtList -> Stmt StmtList
//           | e
//pType returnType为函数返回类型
void StmtList(pNode node, pType returnType) {
    while (node) {
        Stmt(node->child, returnType);
        node = node->child->next;
    }
}

//执行语句
// Stmt -> Exp SEMI 表达式
//       | CompSt 语句块
//       | RETURN Exp SEMI 返回语句
//       | IF LP Exp RP Stmt 分支语句
//       | IF LP Exp RP Stmt ELSE Stmt
//       | WHILE LP Exp RP Stmt 循环语句
//pType returnType为函数返回类型
void Stmt(pNode node, pType returnType) {
    assert(node != NULL);

    pType expType = NULL;
    // Stmt -> Exp SEMI
    if (!strcmp(node->child->name, "Exp")) {
        expType = Exp(node->child);
    }
        // Stmt -> CompSt
    else if (!strcmp(node->child->name, "CompSt")) {
        CompSt(node->child, returnType);
    }
        // Stmt -> RETURN Exp SEMI
    else if (!strcmp(node->child->name, "RETURN")) {
        expType = Exp(node->child->next);

        // check return type
        if (!checkType(returnType, expType))
            pError(TYPE_MISMATCH_RETURN, node->lineNo,
                   "Type mismatched for return.");
    }
        // Stmt -> IF LP Exp RP Stmt
    else if (!strcmp(node->child->name, "IF")) {
        pNode stmt = node->child->next->next->next->next;
        expType = Exp(node->child->next->next);
        Stmt(stmt, returnType);
        // Stmt -> IF LP Exp RP Stmt ELSE Stmt
        if (stmt->next != NULL) Stmt(stmt->next->next, returnType);
    }
        // Stmt -> WHILE LP Exp RP Stmt
    else if (!strcmp(node->child->name, "WHILE")) {
        expType = Exp(node->child->next->next);
        Stmt(node->child->next->next->next->next, returnType);
    }

    if (expType) {
        deleteType(expType);
        expType = NULL;
    }
}

//变量声明列表（本地变量或者结构体域）
// DefList -> Def DefList
//          | e
//pItem structInfo为NULL表示不在结构体内
void DefList(pNode node, TableItem structInfo) {
    while (node) {
        Def(node->child, structInfo);
        node = node->child->next;
    }
}

//变量声明（本地变量或者结构体域）
// Def -> Specifier DecList SEMI
//pItem structInfo为NULL表示不在结构体内
void Def(pNode node, TableItem structInfo) {
    assert(node != NULL);

    pType decType = Specifier(node->child);

    DecList(node->child->next, decType, structInfo);
    if (decType) {
        deleteType(decType);
        decType = NULL;
    }
}

//变量名列表（本地变量或者结构体域）
// DecList -> Dec 一个变量名
//          | Dec COMMA DecList
//pItem structInfo为NULL表示不在结构体内
void DecList(pNode node, pType specifier, TableItem structInfo) {
    assert(node != NULL);

    pNode temp = node;
    while (temp) {
        Dec(temp->child, specifier, structInfo);
        if (temp->child->next)
            temp = temp->child->next->next;
        else
            break;
    }
}

//变量名（本地变量或者结构体域）
// Dec -> VarDec 未初始化
//      | VarDec ASSIGNOP Exp 已初始化
//pItem structInfo为NULL表示不在结构体内
void Dec(pNode node, pType specifier, TableItem structInfo) {
    assert(node != NULL);

    // Dec -> VarDec
    if (node->child->next == NULL) {
        if (structInfo != NULL) {
            // 结构体内，将VarDec返回的Item中的filedList
            // Copy判断是否重定义，无错则到结构体链表尾 记得delete掉Item
            TableItem decItem = VarDec(node->child, specifier);
            decItem->isField = TRUE;
            TableItem last = structInfo->next;
            if (findConflict(symbolTable, decItem->name)) {
                char msg[100] = {0};
                sprintf(msg, "Redefined field \"%s\".", decItem->name);
                pError(REDEF_FEILD, node->lineNo, msg);
                delAvlNode(decItem);
                return;
            } else {
                symbolTable = insert(symbolTable, decItem);
                if (last == NULL) {
                    structInfo->next = decItem;
                } else {
                    while (last->next) {
                        last = last->next;
                    }
                    last->next = decItem;
                }
            }
        } else {
            // 非结构体内，判断返回的item有无冲突，无冲突放入表中，有冲突报错delete
            TableItem decItem = VarDec(node->child, specifier);
            if (findConflict(symbolTable, decItem->name)) {
                //出现冲突，报错
                char msg[100] = {0};
                sprintf(msg, "Redefined variable \"%s\".",
                        decItem->name);
                pError(REDEF_VAR, node->lineNo, msg);
                delAvlNode(decItem);
            } else {
                symbolTable = insert(symbolTable, decItem);
            }
        }
    }
        // Dec -> VarDec ASSIGNOP Exp
    else {
        if (structInfo != NULL) {
            // 结构体内不能赋值，报错
            pError(REDEF_FEILD, node->lineNo,
                   "Illegal initialize variable in struct.");
        } else {
            // 判断赋值类型是否相符
            //如果成功，注册该符号
            TableItem decItem = VarDec(node->child, specifier);
            pType expType = Exp(node->child->next->next);
            if (findConflict(symbolTable, decItem->name)) {
                //出现冲突，报错
                char msg[100] = {0};
                sprintf(msg, "Redefined variable \"%s\".",
                        decItem->name);
                pError(REDEF_VAR, node->lineNo, msg);
                delAvlNode(decItem);
            }
            if (!checkType(decItem->type, expType)) {
                //类型不相符
                //报错
                pError(TYPE_MISMATCH_ASSIGN, node->lineNo,
                       "Type mismatched for assignment.");
                delAvlNode(decItem);
            }
            if (decItem->type && decItem->type->kind == ARRAY) {
                //报错，对非basic类型赋值
                pError(TYPE_MISMATCH_ASSIGN, node->lineNo,
                       "Illegal initialize variable.");
                delAvlNode(decItem);
            } else {
                symbolTable = insert(symbolTable, decItem);
            }
            // exp不出意外应该返回一个无用的type，删除
            if (expType) {
                deleteType(expType);
                expType = NULL;
            }
        }
    }
}

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
pType Exp(pNode node) {
    assert(node != NULL);

    pNode t = node->child;
    //二值运算
    // Exp -> Exp ASSIGNOP Exp 赋值
//      | Exp AND Exp 与
//      | Exp OR Exp 或
//      | Exp RELOP Exp 布尔
//      | Exp PLUS Exp 加
//      | Exp MINUS Exp 减
//      | Exp STAR Exp 乘
//      | Exp DIV Exp 除
//      | Exp LB Exp RB 数组[]
//      | Exp DOT ID 结构体中域的引用
    if (!strcmp(t->name, "Exp")) {
        // 基本数学运算符
        if (strcmp(t->next->name, "LB") != 0 && strcmp(t->next->name, "DOT") != 0) {
            pType p1 = Exp(t);
            pType p2 = Exp(t->next->next);
            pType type = NULL;

            // Exp -> Exp ASSIGNOP Exp
            if (!strcmp(t->next->name, "ASSIGNOP")) {
                //检查左值
                pNode tChild = t->child;

                if (!strcmp(tChild->name, "FLOAT") ||
                    !strcmp(tChild->name, "INT")) {
                    //报错，左值
                    pError(LEFT_VAR_ASSIGN, t->lineNo,
                           "The left-hand side of an assignment must be "
                           "available.");

                } else if (!strcmp(tChild->name, "ID") ||
                           !strcmp(tChild->next->name, "LB") ||
                           !strcmp(tChild->next->name, "DOT")) {
                    if (!checkType(p1, p2)) {
                        //报错，类型不匹配
                        pError(TYPE_MISMATCH_ASSIGN, t->lineNo,
                               "Type mismatched for assignment.");
                    } else
                        type = copyType(p1);
                } else {
                    //报错，左值
                    pError(LEFT_VAR_ASSIGN, t->lineNo,
                           "The left-hand side of an assignment must be "
                           "available.");
                }

            }
                // Exp -> Exp AND Exp
                //      | Exp OR Exp
                //      | Exp RELOP Exp
                //      | Exp PLUS Exp
                //      | Exp MINUS Exp
                //      | Exp STAR Exp
                //      | Exp DIV Exp
            else {
                if (p1 && p2 && (p1->kind == ARRAY || p2->kind == ARRAY)) {
                    //报错，数组，结构体运算
                    pError(TYPE_MISMATCH_OP, t->lineNo,
                           "Type mismatched for operands.");
                } else if (!checkType(p1, p2)) {
                    //报错，类型不匹配
                    pError(TYPE_MISMATCH_OP, t->lineNo,
                           "Type mismatched for operands.");
                } else {
                    if (p1 && p2) {
                        type = copyType(p1);
                    }
                }
            }

            if (p1) {
                deleteType(p1);
                p1 = NULL;
            }
            if (p2) {
                deleteType(p2);
                p2 = NULL;
            }
            return type;
        }
            // 数组和结构体访问
        else {
            // Exp -> Exp LB Exp RB
            if (!strcmp(t->next->name, "LB")) {
                //数组
                pType p1 = Exp(t);
                pType p2 = Exp(t->next->next);
                pType type = NULL;

                if (!p1) {
                    // 第一个exp为null，上层报错，这里不用再管
                } else if (p1 && p1->kind != ARRAY) {
                    //报错，非数组使用[]运算符
                    char msg[100] = {0};
                    sprintf(msg, "\"%s\" is not an array.", t->child->val);
                    pError(NOT_A_ARRAY, t->lineNo, msg);
                } else if (!p2 || p2->kind != BASIC ||
                           p2->u.basic != INT_TYPE) {
                    //报错，不用int索引[]
                    char msg[100] = {0};
                    sprintf(msg, "\"%s\" is not an integer.",
                            t->next->next->child->val);
                    pError(NOT_A_INT, t->lineNo, msg);
                } else {
                    type = copyType(p1->u.array.elem);
                }
                if (p1) {
                    deleteType(p1);
                    p1 = NULL;
                }
                if (p2) {
                    deleteType(p2);
                    p2 = NULL;
                }
                return type;
            }
                // Exp -> Exp DOT ID
            else {
                pType p1 = Exp(t);
                pType type = NULL;
                if (!p1 || p1->kind != STRUCTURE ||
                    !p1->u.structure.structName) {
                    //报错，对非结构体使用.运算符
                    pError(ILLEGAL_USE_DOT, t->lineNo, "Illegal use of \".\".");
                    if (p1) deleteType(p1);
                    p1 = NULL;
                } else {
                    pNode ref_id = t->next->next;
                    TableItem structInfo = search(symbolTable, p1->u.structure.structName);
                    if(structInfo->isField){
                        char msg[100] = {0};
                        sprintf(msg, "Undefined variable \"%s\".", ref_id->val);
                        pError(UNDEF_VAR, t->lineNo, msg);
                    }
                    TableItem structField = structInfo->next;
                    while (structField != NULL) {
                        if (!strcmp(structField->name, ref_id->val)) {
                            break;
                        }
                        structField = structField->next;
                    }
                    if (structField == NULL) {
                        //报错，没有可以匹配的域名
                        char msg[100] = {0};
                        sprintf(msg,"Non-existent field \"%s\"",ref_id->val);
                        pError(NONEXISTFIELD,t->lineNo,msg);
                    } else {
                        type = copyType(structField->type);
                    }
                }
                if (p1) {
                    deleteType(p1);
                    p1 = NULL;
                }
                return type;
            }
        }
    }
        //单目运算符
        // Exp -> MINUS Exp
        //      | NOT Exp
    else if (!strcmp(t->name, "MINUS") || !strcmp(t->name, "NOT")) {
        pType p1 = Exp(t->next);
        pType type = NULL;
        if (!p1 || p1->kind != BASIC) {
            //报错，数组，结构体运算
            printf("Error type %d at Line %d: %s.\n", 7, t->lineNo,
                   "TYPE_MISMATCH_OP");
        } else {
            type = copyType(p1);
        }
        if (p1) {
            deleteType(p1);
            p1 = NULL;
        }
        return type;
    } else if (!strcmp(t->name, "LP")) {
        return Exp(t->next);
    }
        // Exp -> ID LP Args RP
        //		| ID LP RP
    else if (!strcmp(t->name, "ID") && t->next) {
        TableItem funcInfo = search(symbolTable, t->val);

        // function not find
        if (funcInfo == NULL) {
            char msg[100] = {0};
            sprintf(msg, "Undefined function \"%s\".", t->val);
            pError(UNDEF_FUNC, node->lineNo, msg);
            return NULL;
        } else if(funcInfo->isField){
            char msg[100] = {0};
            sprintf(msg, "Undefined variable \"%s\".", t->val);
            pError(UNDEF_VAR, t->lineNo, msg);
        }else if (funcInfo->type->kind != FUNCTION) {
            char msg[100] = {0};
            sprintf(msg, "\"%s\" is not a function.", t->val);
            pError(NOT_A_FUNC, node->lineNo, msg);
            return NULL;
        }
            // Exp -> ID LP Args RP
        else if (!strcmp(t->next->next->name, "Args")) {
            Args(t->next->next, funcInfo);
            return copyType(funcInfo->type->u.function.returnType);
        }
            // Exp -> ID LP RP
        else {
            if (funcInfo->type->u.function.argc != 0) {
                char msg[100] = {0};
                sprintf(msg,
                        "too few arguments to function \"%s\", except %d args.",
                        funcInfo->name,
                        funcInfo->type->u.function.argc);
                pError(FUNC_AGRC_MISMATCH, node->lineNo, msg);
            }
            return copyType(funcInfo->type->u.function.returnType);
        }
    }
        // Exp -> ID
    else if (!strcmp(t->name, "ID")) {
        TableItem tp = search(symbolTable, t->val);

        if (tp == NULL || isStructDef(tp)||tp->isField) {
            char msg[100] = {0};
            sprintf(msg, "Undefined variable \"%s\".", t->val);
            pError(UNDEF_VAR, t->lineNo, msg);
            return NULL;
        } else {
            // good
            return copyType(tp->type);
        }
    } else {
        // Exp -> FLOAT
        if (!strcmp(t->name, "FLOAT")) {
            return newType(BASIC, 1, FLOAT_TYPE);
        }
            // Exp -> INT
        else {
            return newType(BASIC, 1, INT_TYPE);
        }
    }
}

//实参列表（一个及以上）
// Args -> Exp COMMA Args
//       | Exp
void Args(pNode node, TableItem funcInfo) {
    assert(node != NULL);

    pNode temp = node;
    TableItem arg = funcInfo->next;
    while (temp) {
        if (arg == NULL) {
            char msg[100] = {0};
            sprintf(
                    msg, "too many arguments to function \"%s\", except %d args.",
                    funcInfo->name, funcInfo->type->u.function.argc);
            pError(FUNC_AGRC_MISMATCH, node->lineNo, msg);
            break;
        }
        pType realType = Exp(temp->child);
        if (!checkType(realType, arg->type)) {
            char msg[100] = {0};
            sprintf(msg, "Function \"%s\" is not applicable for arguments.",
                    funcInfo->name);
            pError(FUNC_AGRC_MISMATCH, node->lineNo, msg);
            if (realType) {
                deleteType(realType);
                realType = NULL;
            }
            return;
        }
        if (realType) {
            deleteType(realType);
            realType = NULL;
        }

        arg = arg->next;
        if (temp->child->next) {
            temp = temp->child->next->next;
        } else {
            break;
        }
    }
    if (arg != NULL) {
        char msg[100] = {0};
        sprintf(msg, "too few arguments to function \"%s\", except %d args.",
                funcInfo->name, funcInfo->type->u.function.argc);
        pError(FUNC_AGRC_MISMATCH, node->lineNo, msg);
    }
}