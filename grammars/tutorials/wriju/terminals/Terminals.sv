grammar tutorials:wriju:terminals;

-- expressions
terminal Id_t  /[a-zA-Z]+/ ;

terminal IntLit_t /[0-9]+/ ; 

-- white space
ignore terminal WhiteSpace_t /[\t\n\ ]+/  ;

-- line comments
ignore terminal LineComment_P  /[\/][\/].*/  ;
