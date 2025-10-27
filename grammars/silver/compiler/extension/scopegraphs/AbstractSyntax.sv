grammar silver:compiler:extension:scopegraphs;

--

nonterminal Scope;

abstract production absScopeAssertion
top::Scope ::=
{}

abstract production absScopeAssertionDatum
top::Scope ::= e::Expr
{}