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
inherited attribute envTrees<a> :: [EnvTree<a>];

abstract production i_envScope_dummy_record
top::EnvScope<a> ::=
{
}

function emptyEnvScope
Decorated EnvScope<a> ::=
{
  return decorate i_envScope_dummy_record() with {envTrees = [];};
}

function oneEnvScope
Decorated EnvScope<a> ::= eis::EnvTree<a>
{
  return decorate i_envScope_dummy_record() with {envTrees = [eis];};
}

function appendEnvScope
Decorated EnvScope<a> ::= l::Decorated EnvScope<a> r::Decorated EnvScope<a>
{
  return decorate i_envScope_dummy_record() with {envTrees = l.envTrees ++ r.envTrees;};
}

function consEnvScope
Decorated EnvScope<a> ::= l::EnvTree<a> r::Decorated EnvScope<a>
{
  return decorate i_envScope_dummy_record() with {envTrees = l :: r.envTrees;};
}

function searchEnvScope
[a] ::= search::String e::Decorated EnvScope<a>
{
  return searchEnvScopeHelp(search, e.envTrees);
}
function searchEnvScopeHelp
[a] ::= search::String e::[EnvTree<a>]
{
  return if null(e)
         then []
         else searchEnvTree(search, head(e)) ++ searchEnvScopeHelp(search, tail(e));
}

