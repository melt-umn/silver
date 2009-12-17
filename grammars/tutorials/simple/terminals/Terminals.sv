grammar tutorials:simple:terminals ;

-- expressions
terminal Id_t  /[a-zA-Z]+/ ;

terminal IntLit_t /[0-9]+/ ; 

terminal Plus_t   '+'   precedence = 10, association = left ;
terminal Star_t   '*'   precedence = 12, association = left ;

terminal Dash_t   '-'   precedence = 10, association = left ;
terminal Slash_t  '/'   precedence = 12, association = left ;

terminal LParen_t '(' ;
terminal RParen_t ')' ;


-- punctuation
terminal Equals_t '=' ;
terminal Semi_t   ';' ;
terminal Comma_t  ',' ;

-- statements
terminal If_t    'if'     dominates { Id_t } ;
terminal Else_t  'else'   dominates { Id_t } ;



-- declarations
terminal Func_t    'func'     dominates { Id_t } ;



-- white space
ignore terminal WhiteSpace_t /[\t\n\ ]+/  ;

-- line comments
ignore terminal LineComment_P  /[\/][\/].*/  ;

