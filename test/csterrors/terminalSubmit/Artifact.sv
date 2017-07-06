grammar terminalSubmit;

imports test:term_b;

exports host;

parser extendedParser :: Root {
    host;
    terminalSubmit;
} 

terminal A_t 'A' submits to {B_t};