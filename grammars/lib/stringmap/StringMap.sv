grammar lib:stringmap;

deprecated "StringMap is inefficient and no longer recommended: use silver:util:treemap instead.";

-- hide the representation of StringMap, as we someday plan to make this a tree
nonterminal StringMap<a> with bindings<a>;

synthesized attribute bindings<a> :: [ Pair<String a> ] ;


abstract production emptyStringMap
e::StringMap<a> ::= 
{
  e.bindings = [ ] ;
}

abstract production consStringMap
e::StringMap<a> ::= name::String v::a env::StringMap<a>
{
  e.bindings = cons( pair(name,v), env.bindings ) ;
}

abstract production appendStringMap
e::StringMap<a> ::= e1::StringMap<a> e2::StringMap<a>
{
  e.bindings = e1.bindings ++ e2.bindings ;
}

function lookupStringMap
Maybe<a> ::= name::String e::StringMap<a>
{
  return lookup(name,e.bindings);
}

-- This should, perhaps, not be here...
function lookupAll
[a] ::= s::String m::[Pair<String a>]
{
  return if null(m)
         then []
         else if head(m).fst == s
         then head(m).snd :: lookupAll(s, tail(m))
         else lookupAll(s, tail(m));
}
function lookup
Maybe<a> ::= s::String m::[Pair<String a>]
{
  local all :: [a] = lookupAll(s,m);
  return if null(all) then nothing() else just(head(all));
}


