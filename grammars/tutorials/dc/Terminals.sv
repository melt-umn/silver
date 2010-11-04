grammar tutorials:dc ;

-- Between forward slashes is a regular expression:

terminal IntLit_t /[0-9]+/ ; 

-- Inside single quotes is a string literal:

-- Note that we're not specifying associativity or precedence in this example,
-- but if we were, an example of that syntax is commented out:

terminal Plus_t   '+' ; -- precedence = 5, association = left;
terminal Dash_t   '-' ; -- precedence = 5, association = left;
terminal Star_t   '*' ; -- precedence = 10, association = left;
terminal Slash_t  '/' ; -- precedence = 10, association = left;
terminal LParen_t '(' ;
terminal RParen_t ')' ;

-- 'ignore' terminals are permitted to exist between any tokens in productions.

ignore terminal WhiteSpace_t /[\t\n\ ]+/  ;

ignore terminal LineComment_P  /[\/][\/].*/  ;

