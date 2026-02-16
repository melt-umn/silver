grammar silver:compiler:extension:scopegraphs2;

--

terminal NewScope_t 'newScope' lexer classes {KEYWORD, RESERVED};
terminal NewScopeArrow_t '->';

terminal Scope_t 'scope' lexer classes {KEYWORD};
terminal Graph_t 'graph' lexer classes {KEYWORD};

terminal ScopeEdge_t 'edge' lexer classes {KEYWORD}; 

terminal EdgeLeft_t '-[';
terminal EdgeRight_t ']->';

--

concrete production graphSpec_c
top::AGDcl ::= 'scope' 'graph' ident::IdUpper_t 'with' names::LabelNames';'
{ forwards to graphSpec(ident.lexeme, ^names); }

--

nonterminal LabelNames;

concrete production labelNamesOne_c
top::LabelNames ::= lab::IdLower_t
{ forwards to labelNamesOne(lab.lexeme); }

concrete production labelNamesCons_c
top::LabelNames ::= lab::IdLower_t ',' ns::LabelNames
{ forwards to labelNamesCons(lab.lexeme, ^ns); }

--

concrete production mkScopeNoData_c
top::ProductionStmt ::= 'newScope' ident::IdLower_t '::' sg::IdUpper_t ';'
{ forwards to mkScopeNoData(ident.lexeme, sg); }

concrete production mkScopeWithData_c
top::ProductionStmt ::= 'newScope' ident::IdLower_t '::' sg::IdUpper_t scopeLab::IdLower_t '->' datum::Expr ';'
{ forwards to mkScopeWithData(ident.lexeme, sg, scopeLab.lexeme, @datum); }

--

concrete production edgeSpecNoType_c
top::AGDcl ::= 'edge' '-[' label::IdLower_t ']->' ';'
{ forwards to edgeSpecNoType(label.lexeme); }

concrete production edgeSpecWithType_c
top::AGDcl ::= 'edge' '-[' label::IdLower_t ']->' te::TypeExpr ';'
{ forwards to edgeSpecWithType(label.lexeme, @te); }

--

concrete production edgeAssertionLocal_c
top::ProductionStmt ::= a::Name '-[' lab::IdLower_t ']->' tgt::Expr ';'
{ forwards to edgeAssertionLocal(qNameId(^a), lab.lexeme, ^tgt); }

concrete production edgeAssertionInh_c
top::ProductionStmt ::= dl::DefLHS '.' attr::QNameAttrOccur '-[' lab::IdLower_t ']->' tgt::Expr ';'
{ forwards to edgeAssertionInh(^dl, ^attr, lab.lexeme, ^tgt); }

--

concrete production scopeAttribute_c
top::AGDcl ::= 'scope' 'attribute' sg::IdUpper_t ':' lab::IdLower_t ident::IdLower_t ';'
{
  forwards to scopeAttribute(sg, lab.lexeme, ident.lexeme);
}