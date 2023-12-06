grammar silver:compiler:extension:scopegraphs;

nonterminal Query;

synthesized attribute results::[Decorated Scope] occurs on Query;

abstract production mkQuery
top::Query ::= 
  s::Decorated Scope
  for::String
  r::LabelRegex
  --o::Ordering TODO
{
  --top.results = query_lexstar_var ([s], for);
  top.results = r.f(for, [s]);
}

synthesized attribute f :: ([Decorated Scope] ::= String [Decorated Scope]);

nonterminal LabelRegex with f;

abstract production alt
top::LabelRegex ::= r1::LabelRegex r2::LabelRegex
{

}

abstract production seq
top::LabelRegex ::= r1::LabelRegex r2::LabelRegex
{
  top.f = 
    \lookup::String scopes::[Decorated Scope] -> 
      r2.f (lookup, r1.f (lookup, scopes));
}

abstract production star
top::LabelRegex ::= r1::LabelRegex
{
  top.f = 
    \lookup::String scopes::[Decorated Scope] -> [];
}

abstract production opt
top::LabelRegex ::= r1::LabelRegex
{
  top.f = 
    \lookup::String scopes::[Decorated Scope] -> [];
}

abstract production lbl
top::LabelRegex ::= l1::Label
{
  top.f = 
    \lookup::String scopes::[Decorated Scope] ->
      case l1 of
        var_label () -> concat (map ((.var_edges), scopes))
      | lex_label () -> concat (map ((.lex_edges), scopes))
      end;
}

nonterminal Label;

abstract production var_label
top::Label ::=
{

}

abstract production lex_label
top::Label ::=
{

}

{-
aspect production abs:epsilon
top::abs:Regex ::=
{
  
}

aspect production abs:alt
top::abs:Regex ::= r1::abs:Regex r2::abs:Regex
{
}

aspect production abs:seq
top::abs:Regex ::= r1::abs:Regex r2::abs:Regex
{
}

aspect production abs:star
top::abs:Regex ::= r::abs:Regex
{
}

aspect production abs:opt
top::abs:Regex ::= r::abs:Regex
{
}
-}
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