grammar tutorials:dc ;

terminal IntLit_t /[0-9]+/ ; 

terminal Plus_t   '+' ;
terminal Star_t   '*' ;
terminal Dash_t   '-' ;
terminal Slash_t  '/' ;
terminal LParen_t '(' ;
terminal RParen_t ')' ;

-- white space
ignore terminal WhiteSpace_t /[\t\n\ ]+/  ;

-- line comment
ignore terminal LineComment_P  /[\/][\/].*/  ;

