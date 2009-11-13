grammar tutorials:expr:terminals ;


-- arithmetic
terminal IntLit_t /[0-9]+/ ; 

terminal Plus_t   '+'  operator precedence = 10, association = left ;
terminal Star_t   '*'  operator precedence = 12, association = left ;

terminal Dash_t   '-'  operator precedence = 10, association = left ;
terminal Slash_t  '/'  operator precedence = 12, association = left ;

terminal LParen_t '(' ;
terminal RParen_t ')' ;

-- white space
ignore terminal WhiteSpace_t /[\t\n\ ]+/  ;

-- line comments
ignore terminal LineComment_P  /[\/][\/].*/  ;

-- let expressions
terminal Let_t 'let' dominates { Id_t } ;
terminal In_t  'in'  dominates { Id_t } ;
terminal End_t 'end' dominates { Id_t } ;
terminal Id_t  /[a-zA-Z]+/ ;
terminal Equals_t '=' ;
