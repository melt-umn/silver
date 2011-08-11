grammar simple:terminals;

{- Ignore terminals define the whitespace for the grammar, as a whole.
 - We also typically include comments
 -}
ignore terminal WhiteSpace /[\t\n\ ]+/;
ignore terminal LineComment /\/\/.*/;

-- Operator precedence and associativity can be specified directly.
-- Higher values indicate higher precedence operators.
-- Associativity can be set to "left" or "right", default is none.

terminal Not    '!'   precedence = 14;

terminal Star   '*'   precedence = 12, association = left;
terminal Slash  '/'   precedence = 12, association = left;

terminal Plus   '+'   precedence = 10, association = left;
terminal Dash   '-'   precedence = 10, association = left;

terminal EqEq   '=='  precedence =  8;
terminal NEq    '!='  precedence =  8;
terminal Lt     '<'   precedence =  8;
terminal LtEq   '<='  precedence =  8;
terminal Gt     '>'   precedence =  8;
terminal GtEq   '>='  precedence =  8;

terminal And    '&&'  precedence =  6;
terminal Or     '||'  precedence =  6;

terminal Eq     '=';

-- Punctuation

terminal LeftParen  '(';
terminal RightParen ')';
terminal LeftCurly  '{';
terminal RightCurly '}';
terminal Semicolon  ';';
terminal Comma      ',';

{- Lexer classes are used to easily describe lexer precedence.
 - We'll see its use in a moment.
 - By convention, we typically keep lexer classes in all-caps.
 -}
lexer class KEYWORDS;

-- Statements

terminal If    'if'    lexer classes { KEYWORDS }; 
terminal Else  'else'  lexer classes { KEYWORDS };
terminal While 'while' lexer classes { KEYWORDS }; 
terminal Print 'print' lexer classes { KEYWORDS }; 
terminal For   'for'   lexer classes { KEYWORDS } ;
terminal To    'to'    lexer classes { KEYWORDS } ;

terminal Main  'main'  lexer classes { KEYWORDS }; 

-- Types

terminal Integer_t 'Integer' lexer classes { KEYWORDS }; 
terminal Float_t   'Float'   lexer classes { KEYWORDS }; 
terminal Boolean_t 'Boolean' lexer classes { KEYWORDS }; 

{- Alternatively, we could avoid the keywords lexer class and put the
   clause "dominates { Id_t}" on each keyword.  But the use of the
   lexer class makes our intention more clear.  
-}

-- Expressions

{- Here we're using the 'submits to' relation to tell the lexer anything
 - in the KEYWORD class should be lexed as a keyword, not as this identifer
 -}
terminal Id  /[a-zA-Z][a-zA-Z0-9_]*/  submits to { KEYWORDS };

terminal IntegerLiteral /[0-9]+/; 
terminal FloatLiteral   /[0-9]+\.[0-9]+/; 
terminal BooleanLiteral /(True)|(False)/ lexer classes { KEYWORDS };

terminal StringLiteral /\"([^\n\"\\]|\\\"|\\\\|\\n|\\r|\\t)*\"/;


