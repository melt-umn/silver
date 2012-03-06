grammar silver:definition:env;

-- searchEnvScope   [Decorated DclInfo] ::= search::String e::Decorated EnvScope
-- collapseEnvScope [Decorated DclInfo] ::= e::Decorated EnvScope

-- emptyEnvScope    Decorated EnvScope ::=
-- oneEnvScope      Decorated EnvScope ::= eis::EnvTree<Decorated DclInfo>
-- appendEnvScope   Decorated EnvScope ::= l::Decorated EnvScope r::Decorated EnvScope
-- consEnvScope     Decorated EnvScope ::= l::EnvTree<Decorated DclInfo> r::Decorated EnvScope

nonterminal EnvScope with envTrees;

synthesized attribute envTrees :: [EnvTree<Decorated DclInfo>];

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
Decorated EnvScope ::= eis::EnvTree<Decorated DclInfo>
{
  return decorate i_oneEnvScope(eis) with {};
}
abstract production i_oneEnvScope
et::EnvScope ::= eis::EnvTree<Decorated DclInfo>
{
  et.envTrees = [eis];
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
Decorated EnvScope ::= l::EnvTree<Decorated DclInfo> r::Decorated EnvScope
{
  return decorate i_consEnvScope(l, r) with {};
}
abstract production i_consEnvScope
et::EnvScope ::= l::EnvTree<Decorated DclInfo>  r::Decorated EnvScope
{
 et.envTrees = l :: r.envTrees;
}

function searchEnvScope
[Decorated DclInfo] ::= search::String e::Decorated EnvScope
{
  return searchEnvScopeHelp(search, e.envTrees);
}
function searchEnvScopeHelp
[Decorated DclInfo] ::= search::String e::[EnvTree<Decorated DclInfo>]
{
  return if null(e)
         then [ ]
         else searchEnvTree(search, head(e)) ++ searchEnvScopeHelp(search, tail(e));
}

