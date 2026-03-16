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

concrete production graphSpec_c
top::AGDcl ::= 'scope' ident::IdUpper_t 'labels' names::LabelNames 'as' labsId::IdUpper_t ';'
{ forwards to graphSpec(just(ident.lexeme), ^names, labsId.lexeme); }

concrete production graphDefaultSpec_c
top::AGDcl ::= 'scope' 'labels' names::LabelNames 'as' labsId::IdUpper_t ';'
{ forwards to graphSpec(nothing(), ^names, labsId.lexeme); }

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

concrete production scopeAttributeConvenience_c
top::AGDcl ::= 'scope' 'attribute' ident::IdLower_t '::' sg::IdUpper_t 'occurs' 'on' qs::QNames ';'
{
  forwards to appendAGDcl(
    scopeAttribute(qName(ident.lexeme), just(sg.lexeme), ident.location),
    makeOccursDclsHelp(qNameWithTL(qName(ident.lexeme), botlNone()), qs.qnames)
  );
}

concrete production scopeAttributeConvenienceDefault_c
top::AGDcl ::= 'scope' 'attribute' ident::IdLower_t 'occurs' 'on' qs::QNames ';'
{ 
  forwards to appendAGDcl(
    scopeAttribute(qName(ident.lexeme), nothing(), ident.location),
    makeOccursDclsHelp(qNameWithTL(qName(ident.lexeme), botlNone()), qs.qnames)
  );
}

{-
concrete production attributeDclSynMultiple
top::AGDcl ::= 'synthesized' 'attribute' a::Name tl::BracketedOptTypeExprs '::' te::TypeExpr 'occurs' 'on' qs::QNames ';'
{
  top.unparse = "synthesized attribute " ++ a.name ++ tl.unparse ++ " :: " ++ te.unparse ++ " occurs on " ++ qs.unparse ++ ";" ;
  forwards to appendAGDcl(
    attributeDclSyn($1, $2, @a, @tl, $5, @te, $10),
    makeOccursDclsHelp(qNameWithTL(qNameId(^a), ^tl), qs.qnames));
}
-}

--

concrete production existsScope_c
top::ProductionStmt ::= 'existsScope' ident::IdLower_t '::' sg::IdUpper_t ';'
{ forwards to scopeExists(ident.lexeme, just(sg.lexeme)); }

concrete production existsScopeDefault_c
top::ProductionStmt ::= 'existsScope' ident::IdLower_t ';'
{ forwards to scopeExists(ident.lexeme, nothing()); }

--

concrete production mkScope_c
top::ProductionStmt ::= 'newScope' ident::IdLower_t '::' sg::IdUpper_t d::SGDatum ';'
{ forwards to mkScope(ident.lexeme, just(sg.lexeme), d.datumExprOpt); }

concrete production mkScopeDefault_c
top::ProductionStmt ::= 'newScope' ident::IdLower_t d::SGDatum ';'
{ forwards to mkScope(ident.lexeme, nothing(), d.datumExprOpt); }

concrete production mkScopeUndec_c
top::ProductionStmt ::= 'newScope' dl::DefLHS '.' attr::QNameAttrOccur '::' sg::IdUpper_t d::SGDatum ';'
{ forwards to mkScopeUndec(^dl, ^attr, just(sg.lexeme), d.datumExprOpt); }

concrete production mkScopeUndecDefault_c
top::ProductionStmt ::= 'newScope' dl::DefLHS '.' attr::QNameAttrOccur d::SGDatum ';'
{ forwards to mkScopeUndec(^dl, ^attr, nothing(), d.datumExprOpt); }

--

nonterminal SGDatum;

synthesized attribute datumExprOpt::Maybe<Expr> occurs on SGDatum;

concrete production sgDatum_c
top::SGDatum ::= '->' datum::Expr
{
  top.datumExprOpt = just(^datum);
}

concrete production sgDatumNone_c
top::SGDatum ::=
{
  top.datumExprOpt = nothing();
}

--

concrete production edgeAssertionLocal_c
top::ProductionStmt ::= a::Name '-[' lab::IdLower_t ']->' tgt::Expr ';'
{ forwards to edgeAssertionLocal(qNameId(^a), lab.lexeme, ^tgt); }

concrete production edgeAssertionInh_c
top::ProductionStmt ::= dl::DefLHS '.' attr::QNameAttrOccur '-[' lab::IdLower_t ']->' tgt::Expr ';'
{ forwards to edgeAssertionInh(^dl, ^attr, lab.lexeme, ^tgt); }

--

concrete production visibleQuery_c
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
