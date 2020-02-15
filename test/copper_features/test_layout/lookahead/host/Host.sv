grammar copper_features:test_layout:lookahead:host;

ignore terminal WhiteSpace_t /[\t\r\n ]+/;

terminal Plus_t '+' association=left, precedence=0;
terminal Mod_t '%' association=left, precedence=1;

terminal LParen_t '(';
terminal RParen_t ')';
terminal LCurly_t '{';
terminal RCurly_t '}';
terminal Semi_t ';';

terminal Id_t /[a-zA-Z]+/;

nonterminal Stmt;
concrete productions top::Stmt
| Expr ';' {}
| '{' Stmt '}' {}

nonterminal Expr;
concrete productions top::Expr
| '(' Expr ')' {}
| Expr '+' Expr {}
| Expr '%' Expr {}
| Id_c '(' ')' {}
| Id_c {}

nonterminal Id_c;
concrete productions top::Id_c
| Id_t {}
