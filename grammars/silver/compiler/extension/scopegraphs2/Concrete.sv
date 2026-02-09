grammar silver:compiler:extension:scopegraphs2;

--

terminal NewScope_t 'new' lexer classes {KEYWORD, RESERVED};
terminal NewScopeArrow_t '->';

terminal Scope_t 'scope' lexer classes {KEYWORD};
terminal Graph_t 'graph' lexer classes {KEYWORD};

terminal ScopeEdge_t 'edge' lexer classes {KEYWORD}; 

terminal EdgeLeft_t '-[';
terminal EdgeRight_t ']->';

--

concrete production graphSpec_c
top::AGDcl ::= 'scope' 'graph' ident::IdUpper_t 'with' '{' qns::FlowSpecInhs '}' ';'
{ forwards to graphSpec(ident.lexeme, @qns); }

--

concrete production mkScopeNoData_c
top::ProductionStmt ::= 'new' ident::IdLower_t '::' sg::IdUpper_t ';'
{ forwards to mkScopeNoData(ident.lexeme, sg); }

concrete production mkScopeWithData_c
top::ProductionStmt ::= 'new' ident::IdLower_t '::' sg::IdUpper_t scopeLab::IdLower_t '->' datum::Expr ';'
{ forwards to mkScopeWithData(ident.lexeme, sg, scopeLab.lexeme, @datum); }

--

concrete production edgeSpecNoType_c
top::AGDcl ::= 'edge' '-[' label::IdLower_t ']->' ';'
{ forwards to edgeSpecNoType(label.lexeme); }

concrete production edgeSpecWithType_c
top::AGDcl ::= 'edge' '-[' label::IdLower_t ']->' te::TypeExpr ';'
{ forwards to edgeSpecWithType(label.lexeme, @te); }