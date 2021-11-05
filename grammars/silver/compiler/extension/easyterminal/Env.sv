grammar silver:compiler:extension:easyterminal;

import silver:compiler:definition:env;

function getTerminalRegexDclAll
[TypeDclInfo] ::= search::String e::Decorated Env
{
  return searchEnv(search, e.terminalTree);
}

synthesized attribute terminalTree :: [EnvTree<TypeDclInfo>] occurs on Env; -- must be kept in sync with typeTree's type!! (whether its a [] or not)

function filterAndConvertTermDcls
[Pair<String TypeDclInfo>] ::= ei::EnvItem<TypeDclInfo> sofar::[Pair<String TypeDclInfo>]
{
  return case ei.dcl of
         | termDcl(fn, _, just(en)) -> pair(en, ei.dcl) :: sofar
         | _ -> sofar
         end;
}

function buildTerminalTree
EnvTree<TypeDclInfo> ::= eis::[EnvItem<TypeDclInfo>]
{
  return directBuildTree(foldr(filterAndConvertTermDcls,[],eis));
}

aspect production i_emptyEnv
top::Env ::=
{
  top.terminalTree = [emptyEnvTree()];
}

aspect production i_appendEnv
top::Env ::= e1::Decorated Env  e2::Decorated Env
{
  top.terminalTree = e1.terminalTree ++ e2.terminalTree;
}

aspect production i_newScopeEnv
top::Env ::= d::Defs  e::Decorated Env
{
  top.terminalTree = buildTerminalTree(d.typeList) :: e.terminalTree;
}

aspect production i_occursEnv
top::Env ::= _  e::Decorated Env
{
  top.terminalTree = e.terminalTree;
}

