grammar lambda;

{- Ignore terminals define the whitespace for the grammar, as a whole.
 - We also typically include comments
 -}
ignore terminal WhiteSpace /[\t\n\ ]+/;

{- Operator precedence and associativity can be specified directly.
 - Higher values indicate higher precedence operators.
 - Associativity can be set to "left" or "right", default is none.
 -}

terminal Star   '*'   precedence = 12, association = left;
terminal Slash  '/'   precedence = 12, association = left;

terminal Plus   '+'   precedence = 10, association = left;
terminal Dash   '-'   precedence = 10, association = left;

terminal Eq     '=';

-- Punctuation

terminal Period     '.';
terminal LeftParen  '(';
terminal RightParen ')';
terminal Colon      ':';
terminal To         '->';

{- Lexer classes are used to easily describe lexer precedence.
 - We'll see its use in a moment.
 - By convention, we typically keep lexer classes in all-caps.
 -}
lexer class KEYWORDS;

-- Statements

terminal Lambda_t 'lambda'  lexer classes { KEYWORDS } ;
terminal In_t     'in'      lexer classes { KEYWORDS } ;
terminal Let_t    'let'     lexer classes { KEYWORDS } ;

-- Types

terminal Integer_t 'int' lexer classes { KEYWORDS }; 

{- Alternatively, we could avoid the keywords lexer class and put the
   clause "dominates { Id_t }" on each keyword.  But the use of the
   lexer class makes our intention more clear.  
-}

-- Expressions

{- Here we're using the 'submits to' relation to tell the lexer anything
 - in the KEYWORD class should be lexed as a keyword, not as this identifer
 -}
terminal Id_t  /[a-zA-Z][a-zA-Z0-9_]*/  submits to { KEYWORDS };

terminal IntLit_t /[0-9]+/ ;
