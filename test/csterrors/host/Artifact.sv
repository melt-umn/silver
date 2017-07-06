grammar host;

-- Host is not erroneous, but is 
-- used by other grammars in this package 
-- to demonstrate erroneous extension grammars

nonterminal Root;

concrete production root_c
top::Root ::=
{}

parser p :: Root {
    host;
}