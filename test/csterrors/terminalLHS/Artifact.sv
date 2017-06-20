grammar terminalLHS;

exports host;

terminal A_t 'A';

concrete production terminalLHS
top::A_t ::=
{
}

parser extendedParser :: Root {
    host;
    terminalLHS;
} 