grammar silver:definition:env;

-- searchEnvScope   [Decorated DclInfo] ::= search::String e::Decorated EnvScope
-- collapseEnvScope [Decorated DclInfo] ::= e::Decorated EnvScope

-- emptyEnvScope    Decorated EnvScope ::=
-- oneEnvScope      Decorated EnvScope ::= eis::EnvTree
-- appendEnvScope   Decorated EnvScope ::= l::Decorated EnvScope r::Decorated EnvScope
-- consEnvScope     Decorated EnvScope ::= l::EnvTree r::Decorated EnvScope

nonterminal EnvScope with envTrees;

synthesized attribute envTrees :: [EnvTree] ;

function emptyEnvScope
Decorated EnvScope ::=
{
  return decorate i_emptyEnvScope() with {};
}
abstract production i_emptyEnvScope
et::EnvScope ::= 
{
  et.envTrees = [];
}

function oneEnvScope
Decorated EnvScope ::= eis::EnvTree
{
  return decorate i_oneEnvScope(eis) with {};
}
abstract production i_oneEnvScope
et::EnvScope ::= eis::EnvTree
{
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
  et.envTrees = l.envTrees ++ r.envTrees;
}

function consEnvScope
Decorated EnvScope ::= l::EnvTree r::Decorated EnvScope
{
  return decorate i_consEnvScope(l, r) with {};
}
abstract production i_consEnvScope
et::EnvScope ::= l::EnvTree  r::Decorated EnvScope
{
 et.envTrees = l :: r.envTrees;
}

function searchEnvScope
[Decorated DclInfo] ::= search::String e::Decorated EnvScope
{
  return searchEnvScopeHelp(search, e.envTrees);
}
function searchEnvScopeHelp
[Decorated DclInfo] ::= search::String e::[EnvTree]
{
  return if null(e)
         then [ ]
         else searchEnvTree(search, head(e)) ++ searchEnvScopeHelp(search, tail(e));
}

function collapseEnvScope
[Decorated DclInfo] ::= e::Decorated EnvScope
{
  return collapseEnvScopeHelp(e.envTrees);
}
function collapseEnvScopeHelp
[Decorated DclInfo] ::= e::[EnvTree]
{
  return if null(e)
         then []
         else collapseEnvTree(head(e)) ++ collapseEnvScopeHelp(tail(e));
}

