grammar dc ;

{- Regular expressions associated with a terminal are written after
   the terminal name between forward slashes. -}
terminal IntLit_t /[0-9]+/ ; 

{- Regular expressions that are constant strings may be represented
   using single quotes. -}
terminal Plus_t   '+' ; 
terminal Dash_t   '-' ; 
terminal Star_t   '*' ; 
terminal Slash_t  '/' ; 
terminal Hat_t    '^' ;
 
terminal LParen_t '(' ;
terminal RParen_t ')' ;

{- 'ignore' terminals are recognized by the scanner but then discarded
   and not returned to the parser.  This is useful for specifying the
   white-space that may appear between lexical constructs and for
   comments.  
-}

ignore terminal WhiteSpace_t /[\t\n\ ]+/  ;
ignore terminal LineComment_P  /[\/][\/].*/  ;

