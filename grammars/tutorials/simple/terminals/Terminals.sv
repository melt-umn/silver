grammar tutorials:simple:terminals ;

ignore terminal WhiteSpace_t /[\t\n\ ]+/  ;     -- white space
ignore terminal LineComment_P  /[\/][\/].*/  ;  -- line comments

-- Operator precedence and associativity can be specified directly.
-- Higher precedence values indicate higher precedence operators.
-- Associativity can be set to "left" or "right".

terminal Not_t    '!'   precedence = 14 ;

terminal Star_t   '*'   precedence = 12, association = left ;
terminal Slash_t  '/'   precedence = 12, association = left ;

terminal Plus_t   '+'   precedence = 10, association = left ;
terminal Dash_t   '-'   precedence = 10, association = left ;

terminal EQEQ_t   '=='  precedence =  8 ;
terminal NEQ_t    '!='  precedence =  8 ;
terminal LT_t     '<'   precedence =  8 ;
terminal LTEQ_t   '<='  precedence =  8 ;
terminal GT_t     '>'   precedence =  8 ;
terminal GTEQ_t   '>='  precedence =  8 ;

terminal And_t    '&&'  precedence =  6 ;
terminal Or_t     '||'  precedence =  6 ;

terminal Equals_t '=' ;

-- punctuation
terminal LeftParen_t  '(' ;
terminal RightParen_t ')' ;
terminal LeftCurly_t  '{' ;
terminal RightCurly_t '}' ;
terminal Semi_t       ';' ;
terminal Comma_t      ',' ;

-- statements
lexer class keywords ;

terminal If_t    'if'     lexer classes { keywords } ; 
terminal Else_t  'else'   lexer classes { keywords } ; 
terminal While_t 'while'  lexer classes { keywords } ; 
terminal Print_t 'print'  lexer classes { keywords } ; 

terminal Main_t  'main'   lexer classes { keywords } ; 


-- types
terminal Integer_t  'Integer'   lexer classes { keywords } ; 
terminal Float_t    'Float'     lexer classes { keywords } ; 
terminal Boolean_t  'Boolean'   lexer classes { keywords } ; 
{- Alternatively, we could avoid the keywords lexer class and put the
   clause "dominates { Id_t}" on each keyword.  But the use of the
   lexer class makes our intention more clear.  
-}

-- expressions
terminal Id_t  /[a-zA-Z][a-zA-Z0-9_]*/  submits to { keywords } ;

terminal IntegerLiteral_t /[0-9]+/ ; 
terminal FloatLiteral_t   /[0-9]+\.[0-9]+/ ; 

terminal BooleanLiteral_t /(True)|(False)/ lexer classes { keywords } ;

terminal StringLiteral_t 
         /[\"]([^\"\\]|[\\][\"]|[\\][\\]|[\\]n|[\\]r|[\\]t)*[\"]/;



