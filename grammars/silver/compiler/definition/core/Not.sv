grammar silver:compiler:definition:core;

abstract production not
top::Expr ::= '!'  e::Expr
{ forwards to notOp('!', e, location=top.location); }
