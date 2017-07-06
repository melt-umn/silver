grammar startMissing;

exports host;

imports test:term_b;
imports test:nonterm_b;

-- Non-CST error undeclared type
--parser p :: C_t {
--} 

-- If the below cases are gone, "unable to find start symbol"
parser p2 :: B {
}

-- currently good error message:
-- Lookup error with parameter term_b:B_t of production startMissing:start2
--concrete production start2
--top::Root ::= B_t 
--{
--}

-- currently garbage error message:
-- as above... startMissing:P_startMissingstartMissing1_sv_18_0
--                             ^ grammar   ^ file          ^line,col
--concrete productions top::Expr_c
--| B_t
--{
--}

parser extendedParser :: Root {
    host;
    startMissing;
} 