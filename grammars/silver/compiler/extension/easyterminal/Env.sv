grammar silver:compiler:extension:easyterminal;

import silver:compiler:definition:env;

function getTerminalRegexDclAll
[TypeDclInfo] ::= search::String e::Env
{
  return searchEnv(search, e.terminalTree);
}

synthesized attribute terminalTree :: [EnvTree<TypeDclInfo>] occurs on Env; -- must be kept in sync with typeTree's type!! (whether its a [] or not)

function filterAndConvertTermDcls
[Pair<String TypeDclInfo>] ::= ei::EnvItem<TypeDclInfo> sofar::[Pair<String TypeDclInfo>]
{
  return case ei.dcl of
         | termDcl(fn, _, just(en), _) -> (en, ei.dcl) :: sofar
         | _ -> sofar
         end;
}

function buildTerminalTree
EnvTree<TypeDclInfo> ::= eis::[EnvItem<TypeDclInfo>]
{
  return directBuildTree(foldr(filterAndConvertTermDcls,[],eis));
}

aspect production emptyEnv
top::Env ::=
{
  top.terminalTree = [emptyEnvTree()];
}

aspect production appendEnv
top::Env ::= e1::Env  e2::Env
{
  top.terminalTree = e1.terminalTree ++ e2.terminalTree;
}

aspect production newScopeEnv
top::Env ::= _  e::Env
{
  top.terminalTree = buildTerminalTree(d.typeList) :: e.terminalTree;
}

aspect production occursEnv
top::Env ::= _  e::Env
{
  top.terminalTree = e.terminalTree;
}

