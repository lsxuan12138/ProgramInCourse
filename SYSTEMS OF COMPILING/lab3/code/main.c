#include "semantic.h"
#include "inter.h"
#include "syntax.tab.h"

extern pNode root;

extern int yylineno;
extern int yyparse();
extern void yyrestart(FILE*);

unsigned lexError = FALSE;
unsigned synError = FALSE;

int main(int argc, char** argv) {
    if(argc <= 1||argc>3)return 1;
    FILE* fr = fopen(argv[1], "r");
    if (!fr) {
        perror(argv[1]);
        return 1;
    }
    FILE* fw = NULL;
    if(argc==3){
    fw = fopen(argv[2], "wt+");
        if (!fw) {
            perror(argv[2]);
            return 1;
        }
    }
    
    yyrestart(fr);
    yyparse();
    if (!lexError && !synError) {
        //printTreeInfo(root, 0);
        initTable();
        traverseTree(root);
        //printTable();
        initInterCodeList();
        genInterCodes(root);
        if (!interError) {
            //printInterCode(NULL);
            printInterCode(fw);
            deleteInterCodeList();
        }
        deleteTable();
    }
    delNode(&root);
    return 0;
}
