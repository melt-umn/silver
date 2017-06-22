grammar prodMod;

exports host;

imports test:term_b;

nonterminal NT;
terminal A_t 'A';

-- precedence currently just has a syntax parse
-- error if you put not-an-integer after precedence. 
-- this doesnt seem like the best error message.
--
-- This creates two cstErrors, one for B_t being unknown
-- to the operator and one to B_t being unknown to the layout.
-- (the usage of operator and layout here is effectively meaningless,
--  dont base correct usage off of how they are used here)
concrete production missingModifiers
top::NT ::= 'A' 'A'
operator = B_t, layout {B_t}, precedence = 2
{
}

parser extendedParser :: Root {
    host;
    prodMod;
} 