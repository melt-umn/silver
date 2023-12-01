grammar silver:compiler:extension:scopegraphs;

terminal Scope_kwd 'scope' lexer classes {KEYWORD};
terminal With_kwd 'with' lexer classes {KEYWORD};

concrete production mkScopeNoDatum_c
top::Expr ::= 
  'scope' 'with'
  var_edges :: Expr
  lex_edges :: Expr
{
  forwards to Silver_Expr { mkScopeFull (nothing(), var_edges, lex_edges) };
}

{-
concrete production mkScopeDatumScope_c
top::Expr ::= 
  'scope' 'with'
  var_edges :: EdgeTargetList
  lex_edges :: EdgeTargetList
  'datum'
  str::IdLower_t ':' q::QName
{
  forwards to Silver_Expr { mkScopeFull (just(datum), var_edges, lex_edges) };
}

concrete production mkScopeDatumType_c
top::Expr ::= 
  'scope' 'with'
  var_edges :: EdgeTargetList
  lex_edges :: EdgeTargetList
  'datum'
  str::IdLower_t ':' t::TypeExpr
{
  forwards to Silver_Expr { mkScopeFull (just(datum), var_edges, lex_edges) };
}
-}

{-
nonterminal EdgeTargetList_c;

synthesized attribute asLst :: [Scope] occurs on EdgeTargetList_c;

concrete production edgeTargetCons
top::EdgeTargetList_c ::= 
  q::QName
  t::EdgeTargetList_c
{

  forwards to if null(q.lookupValue.dcls)
              then edgeTargetError()
              else edgeTargetConsScope (
                let res::Expr = q.lookupValue.dcl.refDispatcher(q) in
                  case res of
                    mkScopeNoDatum_c (_, _, ve, le) -> 
                  | mkScopeDatumScope_c (_, _, ve, le, _, str, _, q) ->
                  | mkScopeDatumType_c (_, _, ve, le, _, str, _, t) -> 
                  | _ -> edgeTargetError()
                  end
                end
              ); --q.lookupValue.dcl.refDispatcher(q);

  top.asLst = s :: t.asLst;

}

concrete production edgeTargetNil
top::EdgeTargetList_c ::=
{

  top.asLst = [];

}

abstract production edgeTargetError
top::EdgeTargetList_c ::=
{}-}