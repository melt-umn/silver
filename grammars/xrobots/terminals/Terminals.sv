grammar xrobots:terminals ;



ignore terminal WhiteSpace_t /[\t\n\ ]+/  ;     -- white space
ignore terminal LineComment_P  /[\/][\/].*/  ;  -- line comments

-- Operator precedence and associativity can be specified directly.
-- Higher precedence values indicate higher precedence operators.
-- Associativity can be set to "left" or "right".

terminal Semi_t    ';'  precedence = 16;
terminal Not_t    '!'   precedence = 14 ;

terminal Star_t   '*'   precedence = 12, association = left ;
terminal Slash_t  '/'   precedence = 12, association = left ;
terminal Power_t  '^'   precedence = 12, association = left ;

terminal Plus_t   '+'   precedence = 10, association = left ;
terminal Dash_t   '-'   precedence = 10, association = left ;

terminal EqEq_t   '=='  precedence =  8 ;
terminal NEQ_t    '!='  precedence =  8 ;
terminal LT_t     '<'   precedence =  8 ;
terminal LTEQ_t   '<='  precedence =  8 ;
terminal GT_t     '>'   precedence =  8 ;
terminal GTEQ_t   '>='  precedence =  8 ;

terminal And_t    '&&'  precedence =  6 ;
terminal Or_t     '||'  precedence =  6 ;

terminal Equals_t '=' ;
terminal Assign_t ':=';

-- punctuation
terminal LeftParen_t  '(' ;
terminal RightParen_t ')' ;
terminal LBrace_t     '{' ;
terminal RBrace_t     '}' ;
terminal LBracket_t   '[' ;
terminal RBracket_t   ']' ;
terminal Comma_t      ',' ;
terminal Dot_t        '.' ;
terminal Colon_t      ':' ;


-- statements
lexer class keywords ;

terminal If_t    'if'              lexer classes { keywords } ;
terminal Then_t  'then'	           lexer classes { keywords } ;  
terminal Else_t  'else'  	   lexer classes { keywords } ;
terminal While_t 'while' 	   lexer classes { keywords } ; 


terminal SetRSpeed_t 'SetRSpeed'   lexer classes { keywords } ; 
terminal SetLSpeed_t 'SetLSpeed'   lexer classes { keywords } ; 
terminal Entry_t     'Entry'   	   lexer classes { keywords } ; 
terminal Exit_t      'Exit'   	   lexer classes { keywords } ; 
terminal Under_t     'Under'   	   lexer classes { keywords } ; 
terminal Condition_t 'Condition'   lexer classes { keywords } ; 
terminal Apply_t     'Apply' 	   lexer classes { keywords } ; 
terminal ExitTo_t    'ExitTo'  	   lexer classes { keywords } ; 


-- types
terminal Int_t       'int'     lexer classes { keywords } ; 
terminal Float_t     'float'       lexer classes { keywords } ; 
terminal Boolean_t   'bool'     lexer classes { keywords } ; 
terminal Behavior_t  'Behavior'    lexer classes { keywords } ; 
terminal PBehavior_t 'pBehavior'   lexer classes { keywords } ; 


terminal Initial_t   'Initial'	   lexer classes { keywords } ; 
terminal True_t      'True'	   lexer classes { keywords } ; 
terminal False_t     'False'	   lexer classes { keywords } ; 

terminal Rand_t      'rand'	   lexer classes { keywords } ; 
terminal Sin_t       'sin'	   lexer classes { keywords } ; 
terminal Cos_t	     'cos'	   lexer classes { keywords } ; 
terminal Sqrt_t      'sqrt'	   lexer classes { keywords } ; 
terminal Pre_t       'pre'	   lexer classes { keywords } ; 



{- Alternatively, we could avoid the keywords lexer class and put the
   clause "dominates { Id_t}" on each keyword.  But the use of the
   lexer class makes our intention more clear.  
-}

-- expressions
terminal Id_t  /[a-zA-Z][a-zA-Z0-9_]*/  submits to { keywords } ;

terminal IntLit_t /[0-9]+/ ; 
terminal FloatLiteral_t   /[0-9]+\.[0-9]+/ ; 

terminal BooleanLiteral_t /(True)|(False)/ lexer classes { keywords } ;
{---

terminal IntLit_t /[0-9]+/ ; 
terminal Dot_t '.' ;
terminal Plus_t   '+' precedence = 10, association = left ;
terminal Star_t   '*' precedence = 12, association = left ;
terminal Dash_t   '-' precedence = 10, association = left ;
terminal Slash_t  '/' precedence = 12, association = left ;
terminal Power_t '^'  precedence = 12, association = left ;
terminal LParen_t '(' ;
terminal RParen_t ')' ;
terminal LBrace_t '{' ;
terminal RBrace_t '}' ;
terminal LBracket_t '[' ;
terminal RBracket_t ']' ;
terminal Comma_t ',' ;
terminal Semi_t ';' ;


-- white space
ignore terminal WhiteSpace_t /[\t\n\ ]+/  ;

-- line comments
ignore terminal LineComment_P  /[\/][\/].*/  ;

-- let expressions
--terminal Let_t 'let' dominates { Id_t } ;
--terminal In_t  'in'  dominates { Id_t } ;
--terminal End_t 'end' dominates { Id_t } ;
terminal Id_t  /[a-zA-Z]+/ ;
--terminal Float_t  'f' dominates { Id_t };
terminal Equals_t '=' ;
terminal Assign_t ':=' ;
terminal If_t 'if'dominates { Id_t };
terminal Then_t 'then' dominates { Id_t };
terminal Else_t 'else' dominates { Id_t };
terminal Rand_t 'rand' dominates { Id_t };
terminal Function_t 'function' dominates { Id_t };
terminal LeftBump_t 'LeftBump' dominates { Id_t };
terminal RightBump_t 'RightBump' dominates { Id_t };
terminal RightWheelDrop_t 'RightWheelDrop' dominates { Id_t };
terminal LeftWheelDrop_t 'LeftWheelDrop' dominates { Id_t };
terminal SetRSpeed_t 'SetRSpeed' dominates { Id_t };
terminal SetLSpeed_t 'SetLSpeed' dominates { Id_t };
terminal Wait_t 'Wait' dominates { Id_t };
terminal Behavior_t 'Behavior' dominates { Id_t };
terminal PBehavior_t 'pBehavior' dominates { Id_t };
terminal On_t 'On' dominates { Id_t };
terminal Entry_t 'Entry' dominates { Id_t };
terminal Exit_t 'Exit' dominates { Id_t };
terminal ExitTo_t 'ExitTo' dominates { Id_t };
terminal ExitBehavior_t 'ExitBehavior' dominates { Id_t };
terminal Dec_t 'Dec' dominates { Id_t };
terminal Condition_t 'Condition' dominates {Id_t };	
terminal Apply_t 'Apply' dominates { Id_t };
terminal Under_t 'Under' dominates { Id_t };
terminal Initial_t 'Initial' dominates {Id_t};
terminal False_t 'false' dominates {Id_t};
terminal True_t 'true' dominates {Id_t};
terminal Sin_t 'sin' dominates {Id_t};
terminal Cos_t 'cos' dominates {Id_t};
terminal Sqrt_t 'sqrt' dominates {Id_t};
terminal Pre_t 'pre' dominates {Id_t};


--}