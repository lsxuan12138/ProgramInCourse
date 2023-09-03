%{
	#include"node.h"
	#include "syntax.tab.h"

	extern unsigned lexError;
	int lines = 0;
	int yycolumn = 1;

	#define YY_USER_ACTION \
		yylloc.first_line=yylloc.last_line=yylineno; \
		yylloc.first_column=yycolumn; \
		yylloc.last_column=yycolumn+yyleng-1; \
		yycolumn+=yyleng;
%}

%option yylineno

INT			0|([1-9][0-9]*)
FLOAT		([1-9][0-9]*\.[0-9]+)|(0\.[0-9]+)
ID			[_a-zA-Z][_a-zA-Z0-9]{0,31}
RELOP		(>=?)|(<=?)|(==)|(!=)
%%
int|float	{ yylval.node = newTokenNode(yylineno, TOKEN_TYPE, "TYPE", yytext); return TYPE; }
struct		{ yylval.node = newTokenNode(yylineno, TOKEN_OTHER, "STRUCT", yytext); return STRUCT; }
return		{ yylval.node = newTokenNode(yylineno, TOKEN_OTHER, "RETURN", yytext); return RETURN; }
if			{ yylval.node = newTokenNode(yylineno, TOKEN_OTHER, "IF", yytext); return IF;}
else		{ yylval.node = newTokenNode(yylineno, TOKEN_OTHER, "ELSE", yytext); return ELSE; }
while		{ yylval.node = newTokenNode(yylineno, TOKEN_OTHER, "WHILE", yytext); return WHILE; }


{ID}		{ yylval.node = newTokenNode(yylineno, TOKEN_ID, "ID", yytext); return ID;}
{INT}		{ yylval.node = newTokenNode(yylineno, TOKEN_INT, "INT", yytext); return INT;}
{FLOAT}		{ yylval.node = newTokenNode(yylineno, TOKEN_FLOAT, "FLOAT", yytext); return FLOAT;}

";"			{ yylval.node = newTokenNode(yylineno, TOKEN_OTHER, "SEMI", yytext); return SEMI; }
","			{ yylval.node = newTokenNode(yylineno, TOKEN_OTHER, "COMMA", yytext); return COMMA; }
{RELOP}		{ yylval.node = newTokenNode(yylineno, TOKEN_OTHER, "RELOP", yytext); return RELOP; }
"="			{ yylval.node = newTokenNode(yylineno, TOKEN_OTHER, "ASSIGNOP", yytext); return ASSIGNOP; }
"+"			{ yylval.node = newTokenNode(yylineno, TOKEN_OTHER, "PLUS", yytext); return PLUS; }
"-"			{ yylval.node = newTokenNode(yylineno, TOKEN_OTHER, "MINUS", yytext); return MINUS; }
"*"			{ yylval.node = newTokenNode(yylineno, TOKEN_OTHER, "STAR", yytext); return STAR; }
"/"			{ yylval.node = newTokenNode(yylineno, TOKEN_OTHER, "DIV", yytext); return DIV; }
"&&"		{ yylval.node = newTokenNode(yylineno, TOKEN_OTHER, "AND", yytext); return AND; }
"||"		{ yylval.node = newTokenNode(yylineno, TOKEN_OTHER, "OR", yytext); return OR; }
"."			{ yylval.node = newTokenNode(yylineno, TOKEN_OTHER, "DOT", yytext); return DOT; }
"!"			{ yylval.node = newTokenNode(yylineno, TOKEN_OTHER, "NOT", yytext); return NOT; }
"("			{ yylval.node = newTokenNode(yylineno, TOKEN_OTHER, "LP", yytext); return LP; }
")"			{ yylval.node = newTokenNode(yylineno, TOKEN_OTHER, "RP", yytext); return RP; }
"["			{ yylval.node = newTokenNode(yylineno, TOKEN_OTHER, "LB", yytext); return LB; }
"]"			{ yylval.node = newTokenNode(yylineno, TOKEN_OTHER, "RB", yytext); return RB; }
"{"			{ yylval.node = newTokenNode(yylineno, TOKEN_OTHER, "LC", yytext); return LC; }
"}"			{ yylval.node = newTokenNode(yylineno, TOKEN_OTHER, "RC", yytext); return RC; }
[ \t]+		{;}
\n|\r		{ yycolumn = 1; }

[0-9]+{ID}	{ lexError = TRUE; printf("Error type A at Line %d: Illegal ID \"%s\".\n", yylineno, yytext); }
"."[0-9]+	{ lexError = TRUE; printf("Error type A at Line %d: Illegal floating point number \"%s\".\n", yylineno, yytext); }
[0-9]+"."	{ lexError = TRUE; printf("Error type A at Line %d: Illegal floating point number \"%s\".\n", yylineno, yytext); }

.	{
	lexError = TRUE;
	printf("Error type A at Line %d: Mysterious characters \'%s\'\n",yylineno,yytext);
}
%%

