grammar silver:compiler:extension:scopegraphs;

--

terminal Scope_t 'scope' lexer classes {KEYWORD, RESERVED};

terminal Arrow_t '->';

terminal EdgeLeft_t '-[';
terminal EdgeRight_t ']->';

--

concrete production scopeAssertionNoDatum
top::ProductionStmt ::= 'scope' '<' inhs::TypeExpr '>' a::Name ';'
{ forwards to absScopeAssertion(^inhs, ^a, errorExpr([]), false); }

concrete production scopeAssertionDatum
top::ProductionStmt ::= 'scope' '<' inhs::TypeExpr '>' a::Name 
                                '->' e::Expr ';'
{ forwards to absScopeAssertion(^inhs, ^a, ^e, true); }

--

{-concrete production edgeAssertionDot
top::ProductionStmt ::= d::DefLHS '.' attr::QNameAttrOccur '-[' lab::IdLower_t ']->' e::Expr ';'
{ forwards to absEdgeAssertionDot(^d, ^attr, lab.lexeme, ^e); }

concrete production edgeAssertionLocal
top::ProductionStmt ::= d::DefLHS '-[' lab::IdLower_t ']->' e::Expr ';'
{ forwards to absEdgeAssertionLocal(^d, lab.lexeme, ^e); }-}

concrete production edgeAssertionLocal
top::ProductionStmt ::= n::QName '-[' lab::IdLower_t ']->' e::Expr ';'
{ forwards to absEdgeAssertionLocal(^n, lab.lexeme, ^e); }
