grammar terminalSubmitLexer;

imports test:lexer_b;

exports host;

parser extendedParser :: Root {
    host;
    terminalSubmitLexer;
} 

terminal A_t 'A' submits to {B};