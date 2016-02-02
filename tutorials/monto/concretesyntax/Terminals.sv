grammar monto:concretesyntax;


terminal IntConst_t /[0-9]+/;

-- Operators
lexer class Operator;

terminal Assign_t '=' lexer classes {Operator};

-- Association and Precedence
terminal Add_t '+' association = left, precedence = 1, lexer classes {Operator};
terminal Sub_t '-' association = left, precedence = 1, lexer classes {Operator};
terminal Mul_t '*' association = left, precedence = 2, lexer classes {Operator};
terminal Div_t '/' association = left, precedence = 2, lexer classes {Operator};

-- Parenthesis
lexer class Punctuation;

terminal LParen_t '(' lexer classes {Punctuation};
terminal RParen_t ')' lexer classes {Punctuation};

-- White space and comments
lexer class Whitespace;

-- Ignore terminals
ignore terminal LineComment
  /[\-][\-].*/ 
  lexer classes {Whitespace};

ignore terminal Spaces_t 
  /[\t\ ]+/ 
  lexer classes {Whitespace};

ignore terminal NewLine_t /\n/ 
  lexer classes {Whitespace};
