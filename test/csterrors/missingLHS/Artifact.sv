grammar missingLHS;

exports host;

imports test:term_b;

concrete production missingLHS
top::B_t ::=
{
}

parser extendedParser :: Root {
    host;
    missingLHS;
} 