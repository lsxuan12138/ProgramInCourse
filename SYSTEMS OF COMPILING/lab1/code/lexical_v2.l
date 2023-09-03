INT			0|([1-9][0-9]+)
FLOAT		([1-9][0-9]*\.[0-9]+)|(0\.[0-9]+)
ID			[_a-zA-Z][_a-zA-Z0-9]{0,31}
RELOP		(>=?)|(<=?)|(==)|(!=)
%%
int|float	{printf("TYPE\n");}
struct	{printf("STRUCT\n");}
return	{printf("RETURN\n");}
if	{printf("IF\n");}
else	{printf("ELSE\n");}
while	{printf("WHILE\n");}
{ID}	{printf("ID\n");}
{INT}	{printf("INT\n");}
{FLOAT}	{printf("FLOAT\n");}
";"	{printf("SEMI\n");}
","	{printf("COMMA\n");}
{RELOP}	{printf("RELOP\n");}
"="	{printf("ASSIGNOP\n");}
"+"	{printf("PLUS\n");}
"-"	{printf("MINUS\n");}
"*"	{printf("STAR\n");}
"/"	{printf("DIV\n");}
"&&"	{printf("AND\n");}
"||"	{printf("OR\n");}
"."	{printf("DOT\n");}
"!"	{printf("NOT\n");}
"("	{printf("LP\n");}
")"	{printf("RP\n");}
"["	{printf("LB\n");}
"]"	{printf("RB\n");}
"{"	{printf("LC\n");}
"}"	{printf("RC\n");}
[ \t]+		{;}
\n|\r		{ yylineno++; }

[0-9]+{ID}	{printf("Error type A at Line %d: Illegal ID \"%s\".\n", yylineno, yytext); }
"."[0-9]+	{printf("Error type A at Line %d: Illegal floating point number \"%s\".\n", yylineno, yytext); }
[0-9]+"."	{printf("Error type A at Line %d: Illegal floating point number \"%s\".\n", yylineno, yytext); }

.	{
	printf("Error type A at Line %d: Mysterious characters \'%s\'\n",yylineno,yytext);
}
%%

