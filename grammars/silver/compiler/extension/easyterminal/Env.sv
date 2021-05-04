grammar silver:compiler:extension:easyterminal;

import silver:compiler:definition:env;

function getTerminalRegexDclAll
[DclInfo] ::= search::String e::Decorated Env
{
  return searchEnv(search, e.terminalTree);
}

synthesized attribute terminalTree :: [EnvTree<DclInfo>] occurs on Env; -- must be kept in sync with typeTree's type!! (whether its a [] or not)

function filterAndConvertTermDcls
[Pair<String DclInfo>] ::= ei::EnvItem sofar::[Pair<String DclInfo>]
{
  return case ei.dcl of
         | termDcl(fn, _, just(en)) -> pair(en, ei.dcl) :: sofar
         | _ -> sofar
         end;
}

function buildTerminalTree
EnvTree<DclInfo> ::= eis::[EnvItem]
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

