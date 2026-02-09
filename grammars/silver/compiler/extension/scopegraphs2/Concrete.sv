grammar silver:compiler:extension:scopegraphs2;

--

terminal Scope_t 'scope' lexer classes {KEYWORD};
terminal Graph_t 'graph' lexer classes {KEYWORD};

terminal ScopeEdge_t 'edge' lexer classes {KEYWORD}; 

terminal EdgeLeft_t '-[';
terminal EdgeRight_t ']->';

--

concrete production graphSpec_c
top::AGDcl ::= 'scope' 'graph' ident::IdUpper_t 'with' qns::QNames ';'
{ forwards to graphSpec(ident.lexeme, @qns); }

--

concrete production edgeSpecNoType_c
top::AGDcl ::= 'edge' '-[' label::IdLower_t ']->' ';'
{ forwards to edgeSpecNoType(label.lexeme); }

concrete production edgeSpecWithType_c
top::AGDcl ::= 'edge' '-[' label::IdLower_t ']->' te::TypeExpr ';'
{ forwards to edgeSpecWithType(label.lexeme, @te); }