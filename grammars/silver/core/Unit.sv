grammar silver:core;

data nonterminal Unit;
derive Eq, Ord on Unit;

abstract production unit
top::Unit ::=
{}