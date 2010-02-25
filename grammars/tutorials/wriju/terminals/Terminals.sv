grammar tutorials:wriju:terminals;

-- expressions
terminal Id_t  		/[a-zA-Z]+/ ;

terminal IntLit_t /[0-9]+/ ; 

-- white space
ignore terminal WhiteSpace_t /[\t\n\ ]+/  ;

-- line comments
ignore terminal LineComment_P  /[\/][\/].*/  ;

-- math
terminal Equals_t 	'=' ;
terminal Add_t 			'+' precedence = 10, association = left ;
terminal Subtract_t '-'	precedence = 10, association = left ;
terminal Multiply_t '*' precedence = 12, association = left ;
terminal Divide_t 	'/' precedence = 12, association = left ;

-- punctuation
terminal Semi_t   					';'	;
terminal BraceOpen_t 				'{'	;
terminal BraceClose_t 			'}'	;
terminal ParenthesisOpen_t	'('	;
terminal ParenthesisClose_t	')' ;

-- keywords
terminal Function_t		'function' 	dominates {Id_t} ; --dominates means precedence over
terminal While_t			'while'			dominates {Id_t} ;
