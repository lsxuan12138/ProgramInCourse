#include <stdio.h>
#include "node.h"


extern Node* root;
extern int yylineno;

extern int yyparse (void);
extern void yyrestart ( FILE *input_file  );

unsigned lexError = FALSE;
unsigned synError = FALSE;

int main(int argc, char** argv){
	if(argc <= 1||argc>3)return 1;
	FILE *f = fopen(argv[1],"r");
	if(!f){
		perror(argv[1]);
		return 1;
	}
	yyrestart(f);
	yyparse();

	if (!lexError && !synError) {
		printTreeInfo(root, 0);
		if(argc==3){
			FILE *out = fopen(argv[2],"w");
			saveTreeInfo(root, 0,out);
			fclose(out);
		}
	}
	
	delNode(root);
	return 0;
}
