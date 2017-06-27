grammar lexerDominate;

imports test:term_b;

exports host;

parser extendedParser :: Root {
    host;
    lexerDominate;
} 

lexer class A dominates B_t;