grammar lexerSubmit;

imports test:term_b;

exports host;

parser extendedParser :: Root {
    host;
    lexerSubmit;
} 

lexer class A submits to B_t;