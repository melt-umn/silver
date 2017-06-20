grammar prodMod;

exports host;

imports test:term_b;

nonterminal NT1;
nonterminal NT2;
nonterminal NT3;
terminal A_t 'A';

-- precedence currently just has a syntax parse
-- error if you put not-an-integer after precedence. 
-- this doesnt seem like the best error message.
concrete production missingModifiers
top::NT1 ::= 'A' 'A'
operator = B_t, layout {B_t}, precedence = 2
{
}

--Todo: split off
concrete production missingLHS
top::B_t ::=
{
}

--Todo: split off
concrete production terminalLHS
top::A_t ::=
{
}

-- Does not cause CST error. 
--concrete production duplicateProduction
--top::NT3 ::=
--{
--}


parser extendedParser :: Root {
    host;
    prodMod;
} 