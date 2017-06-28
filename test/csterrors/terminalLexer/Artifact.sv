grammar terminalLexer;

imports test:lexer_b;

exports host;

parser extendedParser :: Root {
    host;
    terminalLexer;
} 

terminal A_t 'A' lexer classes {B};