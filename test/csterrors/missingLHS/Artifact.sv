grammar missingLHS;

exports host;

imports test:nonterm_b;

-- This causes a cstError because this grammar does not
-- have access to B, but does import B. 
concrete production missingLHS
top::B ::=
{
}

parser extendedParser :: Root {
    host;
    missingLHS;
} 