grammar silver:compiler:extension:scopegraphs;

nonterminal Query;

synthesized attribute results::[Decorated Scope] occurs on Query;

abstract production mkQuery
top::Query ::= 
  s::Decorated Scope
  for::String
  r::Regex
  --o::Ordering TODO
{
  --top.results = query_lexstar_var ([s], for);
  top.results = [];
}

aspect production epsilon
top::Regex ::=
{
  
}

aspect production alt
top::Regex ::= r1::Regex r2::Regex
{
}

aspect production seq
top::Regex ::= r1::Regex r2::Regex
{
}

aspect production star
top::Regex ::= r::Regex
{
}

aspect production opt
top::Regex ::= r::Regex
{
}

{-
function query_lexstar_var
[Decorated Scope] ::= ss::[Decorated Scope] id::String
{
  local var_scopes::[Decorated Scope] = concat (map ((.var_edges), ss));
  local lex_scopes::[Decorated Scope] = concat (map ((.lex_edges), ss));
  return filter (id_eq(id, _), var_scopes) ++ query_lexstar_var (lex_scopes, id);
}


function id_eq
Boolean ::= lookFor::String scope::Decorated Scope
{
  return case scope.datum of
           just (datumType (id, t)) -> id == lookFor
         | _ -> false
         end;
}
-}