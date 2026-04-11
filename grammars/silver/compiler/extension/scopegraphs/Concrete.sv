grammar silver:compiler:extension:scopegraphs;

--

terminal NewScope_t 'newScope' lexer classes {KEYWORD, RESERVED};
terminal NewScopeArrow_t '->';

terminal Exists_t 'existsScope' lexer classes {KEYWORD, RESERVED};

terminal Scope_t 'scope' lexer classes {KEYWORD};
--terminal ScopeGraph_t 'scopegraph' lexer classes {KEYWORD};

terminal Labels_t 'labels' lexer classes {KEYWORD};

terminal ScopeEdge_t 'edge' lexer classes {KEYWORD}; 

terminal Query_t 'query' lexer classes {KEYWORD, RESERVED};

terminal EdgeLeft_t '-[';
terminal EdgeRight_t ']->';

--

-- no named SGs
--concrete production graphSpec_c
--top::AGDcl ::= 'scope' ident::IdUpper_t 'labels' names::LabelNames 'as' labsId::IdUpper_t ';'
--{ forwards to graphSpec(just(ident.lexeme), ^names, labsId.lexeme); }

concrete production graphDefaultSpec_c
top::AGDcl ::= 'scope' 'labels' names::LabelNames 'as' labsId::IdUpper_t ';'
{ forwards to graphSpec({-nothing(),-} ^names, labsId.lexeme); } -- no named SGs

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
top::AGDcl ::= 'scope' 'attribute' ident::IdLower_t '::' sg::IdUpper_t ';'
{ forwards to scopeAttribute(qName(ident.lexeme), just(sg.lexeme), ident.location); }

concrete production scopeAttributeDefault_c
top::AGDcl ::= 'scope' 'attribute' ident::IdLower_t ';'
{ forwards to scopeAttribute(qName(ident.lexeme), nothing(), ident.location); }

-- no named SGs
--concrete production scopeAttributeConvenience_c
--top::AGDcl ::= 'scope' 'attribute' ident::IdLower_t '::' sg::IdUpper_t 'occurs' 'on' qs::QNames ';'
--{
--  forwards to appendAGDcl(
--    scopeAttribute(qName(ident.lexeme), just(sg.lexeme), ident.location),
--    makeOccursDclsHelp(qNameWithTL(qName(ident.lexeme), botlNone()), qs.qnames)
--  );
--}

concrete production scopeAttributeConvenienceDefault_c
top::AGDcl ::= 'scope' 'attribute' ident::IdLower_t 'occurs' 'on' qs::QNames ';'
{ 
  forwards to appendAGDcl(
    scopeAttribute(qName(ident.lexeme), nothing(), ident.location),
    makeOccursDclsHelp(qNameWithTL(qName(ident.lexeme), botlNone()), qs.qnames)
  );
}

--

-- no named SGs
--concrete production existsScope_c
--top::ProductionStmt ::= 'existsScope' ident::IdLower_t '::' sg::IdUpper_t ';'
--{ forwards to scopeExists(ident.lexeme, just(sg.lexeme)); }

concrete production existsScopeDefault_c
top::ProductionStmt ::= 'existsScope' ident::Name ';'
{ forwards to scopeExists(^ident, nothing()); }

--

--concrete production mkScope_c
--top::ProductionStmt ::= 'newScope' ident::IdLower_t '::' sg::IdUpper_t d::SGDatum ';'
--{ forwards to mkScope(ident.lexeme, just(sg.lexeme), d.datumExpr); }

concrete production mkScopeLocal_c
top::ProductionStmt ::= 'newScope' ident::Name d::SGDatum ';'
{ forwards to mkScopeLocal(qName(ident.name), nothing(), d.datumExpr); }

-- no named SGs
--concrete production mkScopeInherited_c
--top::ProductionStmt ::= 'newScope' dl::DefLHS '.' attr::QNameAttrOccur '::' sg::IdUpper_t d::SGDatum ';'
--{ forwards to mkScopeInherited(^dl, ^attr, just(sg.lexeme), d.datumExpr); }

concrete production mkScopeInheritedDefault_c
top::ProductionStmt ::= 'newScope' lhsqn::QName '.' attrqn::QName d::SGDatum ';'
{ forwards to mkScopeInherited(^lhsqn, ^attrqn, nothing(), d.datumExpr); }

--

nonterminal SGDatum;

synthesized attribute datumExpr::Expr occurs on SGDatum;

concrete production sgDatum_c
top::SGDatum ::= '->' datum::Expr
{ top.datumExpr = ^datum; }

concrete production sgDatumNone_c
top::SGDatum ::=
{ top.datumExpr = Silver_Expr{ datumDefault() }; }

--

concrete production edgeAssertionLocal_c
top::ProductionStmt ::= a::Name '-[' lab::IdLower_t ']->' tgt::Expr ';'
{ forwards to edgeAssertionLocal(qNameId(^a), lab.lexeme, ^tgt); }

concrete production edgeAssertionInh_c
top::ProductionStmt ::= dl::DefLHS '.' attr::QNameAttrOccur '-[' lab::IdLower_t ']->' tgt::Expr ';'
{ forwards to edgeAssertionInh(^dl, ^attr, lab.lexeme, ^tgt); }

--

concrete production reachableQuery_c
top::Expr ::= 'query' '(' rx::SGRegex_c ',' pred::Expr ',' s::Expr ')'
{ 
  forwards to Silver_Expr{
    reachableQuery(
      $Expr{rx.toExpr},
      $Expr{^pred},
      $Expr{^s}
    )
  };
}
