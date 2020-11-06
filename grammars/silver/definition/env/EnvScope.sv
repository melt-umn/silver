grammar silver:definition:env;

{--
 - Just an abstraction for representing one scope.
 -}
nonterminal EnvScope<a> with envTrees<a>;

{--
 - Our EnvTree abstraction is immutable, so we have a list here
 - to accomodate any situation where we might need to add to an
 - existing scope, rather than create a new one.
 -}
synthesized attribute envTrees<a> :: [EnvTree<a>];

abstract production envScope
top::EnvScope<a> ::= trees::[EnvTree<a>]
{
  top.envTrees = trees;
}


function emptyEnvScope
EnvScope<a> ::=
{
  return envScope([]);
}

function oneEnvScope
EnvScope<a> ::= eis::EnvTree<a>
{
  return envScope([eis]);
}

function appendEnvScope
EnvScope<a> ::= l::EnvScope<a> r::EnvScope<a>
{
  return envScope(l.envTrees ++ r.envTrees);
}

function consEnvScope
EnvScope<a> ::= l::EnvTree<a> r::EnvScope<a>
{
  return envScope(l :: r.envTrees);
}

function searchEnvScope
[a] ::= search::String e::EnvScope<a>
{
  return flatMap(searchEnvTree(search, _), e.envTrees);
}

