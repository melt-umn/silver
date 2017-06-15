grammar rhs;

exports host;

imports test:term_b;

nonterminal A;

concrete production rhsMissing
top::A ::= B_t
{
}

-- Does not give concrete error
--concrete production rhsIsProduction
--top::A ::= rhsMissing
--{
--}

parser extendedParser :: Root {
    host;
    rhs;
} 
