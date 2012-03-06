grammar silver:definition:env;

{--
 - Just an abstraction for representing one scope.
 -}
nonterminal EnvScope with envTrees;

{--
 - Our EnvTree abstraction is immutable, so we have a list here
 - to accomodate any situation where we might need to add to an
 - existing scope, rather than create a new one.
 -}
inherited attribute envTrees :: [EnvTree<Decorated DclInfo>];

abstract production i_envScope_dummy_record
top::EnvScope ::=
{
}

function emptyEnvScope
Decorated EnvScope ::=
{
  return decorate i_envScope_dummy_record() with {envTrees = [];};
}

function oneEnvScope
Decorated EnvScope ::= eis::EnvTree<Decorated DclInfo>
{
  return decorate i_envScope_dummy_record() with {envTrees = [eis];};
}

function appendEnvScope
Decorated EnvScope ::= l::Decorated EnvScope r::Decorated EnvScope
{
  return decorate i_envScope_dummy_record() with {envTrees = l.envTrees ++ r.envTrees;};
}

function consEnvScope
Decorated EnvScope ::= l::EnvTree<Decorated DclInfo> r::Decorated EnvScope
{
  return decorate i_envScope_dummy_record() with {envTrees = l :: r.envTrees;};
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

