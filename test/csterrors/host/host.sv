grammar host;

nonterminal Root;

concrete production root_c
top::Root ::=
{}

parser p :: Root {
    host;
}