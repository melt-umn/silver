grammar terminalDominate;

imports test:term_b;
exports host;

parser extendedParser :: Root {
    host;
    terminalDominate;
} 

terminal A_t 'A' dominates {B_t};