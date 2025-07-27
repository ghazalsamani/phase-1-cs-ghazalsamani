grammar SimpleLang;

options { visitor=true; }

program    : statement* EOF;

statement
    : varDecl
    | assignment
    | ifStmt
    | whileStmt
    ;

varDecl   : 'int' ID ';' ;
assignment: ID '=' expr ';' ;
ifStmt    : 'if' '(' expr ')' block ( 'else' block )? ;
whileStmt : 'while' '(' expr ')' block ;
block     : '{' statement* '}' ;

expr
    : expr op=('*'|'/') expr
    | expr op=('+'|'-') expr
    | '(' expr ')'
    | INT
    | ID
    ;

ID : [a-zA-Z_][a-zA-Z_0-9]*;
INT: [0-9]+;
WS : [ \t\r\n]+ -> skip;
