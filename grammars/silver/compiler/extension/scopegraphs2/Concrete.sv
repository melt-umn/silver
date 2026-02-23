grammar silver:compiler:extension:scopegraphs2;

--

terminal NewScope_t 'newScope' lexer classes {KEYWORD, RESERVED};
terminal NewScopeArrow_t '->';

terminal Exists_t 'exists' lexer classes {KEYWORD, RESERVED};
terminal Scope_t 'scope' lexer classes {KEYWORD};
terminal ScopeGraph_t 'scopegraph' lexer classes {KEYWORD};
terminal Labels_t 'labels' lexer classes {KEYWORD};

terminal ScopeEdge_t 'edge' lexer classes {KEYWORD}; 

terminal EdgeLeft_t '-[';
terminal EdgeRight_t ']->';

--

concrete production graphSpec_c
top::AGDcl ::= 'scopegraph' ident::IdUpper_t 'labels' names::LabelNames';'
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

concrete production scopeAttribute_c
top::AGDcl ::= 'scope' 'attribute' sg::IdUpper_t ':' ident::IdLower_t ';'
{ forwards to scopeAttribute(sg.lexeme, qName(ident.lexeme), ident.location); }

--

concrete production existsScope_c
top::ProductionStmt ::= 'exists' 'scope' sg::IdUpper_t ':' ident::IdLower_t ';'
{ forwards to existsScope(sg.lexeme, ident.lexeme); }

--

concrete production mkScope_c
top::ProductionStmt ::= 'newScope' ident::IdLower_t '::' sg::IdUpper_t '->' datum::Expr ';'
{ forwards to mkScope(ident.lexeme, sg.lexeme, ^datum); }

concrete production mkScopeUndec_c
top::ProductionStmt ::= 'newScope' dl::DefLHS '.' attr::QNameAttrOccur '::' sg::IdUpper_t '->' datum::Expr ';'
{ forwards to mkScopeUndec(^dl, ^attr, sg, ^datum); }


--

concrete production edgeAssertionLocal_c
top::ProductionStmt ::= a::Name '-[' lab::IdLower_t ']->' tgt::Expr ';'
{ forwards to edgeAssertionLocal(qNameId(^a), lab.lexeme, ^tgt); }

concrete production edgeAssertionInh_c
top::ProductionStmt ::= dl::DefLHS '.' attr::QNameAttrOccur '-[' lab::IdLower_t ']->' tgt::Expr ';'
{ forwards to edgeAssertionInh(^dl, ^attr, lab.lexeme, ^tgt); }
