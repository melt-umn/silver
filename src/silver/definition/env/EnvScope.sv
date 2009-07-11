grammar silver:definition:env;

--		API		--
----------------------------------

--getDclsScope (String, Decorated EnvScope) -> [Decorated EnvItem]
--getDclsScopeAll (Decorated EnvScope) -> [Decorated EnvItem]
--searchEnvScope (String, EnvSearch, Decorated EnvScope) -> [Decorated EnvItem]
--searchEnvScopeAll (EnvSearch, Decorated EnvScope) -> [Decorated EnvItem]


nonterminal EnvScope with unparse, envTrees;

synthesized attribute envTrees :: [Decorated EnvTree] ;

-- Productions for constructing an EnvTree
function emptyEnvScope
Decorated EnvScope ::=
{
  return decorate i_emptyEnvScope() with {};
}

abstract production i_emptyEnvScope
et::EnvScope ::= 
{
  et.unparse = "";
  et.envTrees = [::Decorated EnvTree];
}

function oneEnvScope
Decorated EnvScope ::= eis::Decorated EnvTree
{
  return decorate i_oneEnvScope(eis) with {};
}

abstract production i_oneEnvScope
et::EnvScope ::= eis::Decorated EnvTree
{
  et.unparse = unparseTrees(et.envTrees);
  et.envTrees = [eis] ;
}

function appendEnvScope
Decorated EnvScope ::= l::Decorated EnvScope r::Decorated EnvScope
{
  return decorate i_appendEnvScope(l, r) with {};
}
abstract production i_appendEnvScope
et::EnvScope ::=  l::Decorated EnvScope r::Decorated EnvScope
{
  et.unparse = unparseTrees(et.envTrees);
  et.envTrees = l.envTrees ++ r.envTrees;
}


function consEnvScope
Decorated EnvScope ::= l::Decorated EnvTree r::Decorated EnvScope
{
  return decorate i_consEnvScope(l, r) with {};
}
abstract production i_consEnvScope
et::EnvScope ::= l::Decorated EnvTree  r::Decorated EnvScope
{
 et.unparse = unparseTrees(et.envTrees);
 et.envTrees = [l] ++ r.envTrees;
}

function getDclsScope
[Decorated EnvItem] ::= search::String e::Decorated EnvScope
{
  return getDclsScopeHelp(search, e.envTrees);
}

function getDclsScopeHelp
[Decorated EnvItem] ::= search::String e::[Decorated EnvTree]
{
  return if null(e) then [::Decorated EnvItem] else getDclsTree(search, head(e)) ++ getDclsScopeHelp(search, tail(e));
}

function getDclsScopeAll
[Decorated EnvItem] ::= e::Decorated EnvScope
{
  return getDclsScopeAllHelp(e.envTrees);
}

function getDclsScopeAllHelp
[Decorated EnvItem] ::= e::[Decorated EnvTree]
{
  return if null(e) then [::Decorated EnvItem] else getDclsTreeAll(head(e)) ++ getDclsScopeAllHelp(tail(e));
}

function searchEnvScope
[Decorated EnvItem] ::= search::String s::EnvSearch e::Decorated EnvScope
{
  return searchEnvScopeHelp(search, s, e.envTrees);
}

function searchEnvScopeHelp
[Decorated EnvItem] ::= search::String s::EnvSearch e::[Decorated EnvTree]
{
  return if null(e)
         then [::Decorated EnvItem]    
         else searchEnvTree(search, s, head(e)) ++ searchEnvScopeHelp(search, s, tail(e));
}

function searchEnvScopeAll
[Decorated EnvItem] ::= s::EnvSearch e::Decorated EnvScope
{
  return searchEnvScopeAllHelp(s, e.envTrees);
}

function searchEnvScopeAllHelp
[Decorated EnvItem] ::= s::EnvSearch e::[Decorated EnvTree]
{
  return if null(e)
         then [::Decorated EnvItem]    
         else searchEnvTreeAll(s, head(e)) ++ searchEnvScopeAllHelp(s, tail(e));
}

function unparseScopes
String ::= s::[Decorated EnvScope]{
  return "[" ++ unparseScopesHelp(s) ++ "]";
}

function unparseScopesHelp
String ::= s::[Decorated EnvScope]{
  return if null(s) then "" else (head(s).unparse ++ if null(tail(s)) then "" else (", " ++ unparseScopesHelp(tail(s))));
}

