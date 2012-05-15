grammar silver:extension:easyterminal;

import silver:definition:env;
import silver:definition:regex;


function getTerminalRegexDclAll
[DclInfo] ::= search::String e::Decorated Env
{
  return searchEnv(search, e.terminalTree);
}



inherited attribute terminalTree :: [Decorated EnvScope<DclInfo>] occurs on Env; -- must be kept in sync with typeTree's type!! (whether its a [] or not)

function filterAndConvertTermDcls
[Pair<String DclInfo>] ::= ei::EnvItem sofar::[Pair<String DclInfo>]
{
  return case ei.dcl of
           termDcl(_, _, fn, regex) -> pair(regex.regString, ei.dcl) :: sofar
         | _ -> sofar
         end;
}

function buildTerminalTree
EnvTree<DclInfo> ::= eis::[EnvItem]
{
  return directBuildTree(foldr(filterAndConvertTermDcls,[],eis));
}

aspect function emptyEnv
Decorated Env ::=
{
  top.terminalTree = [emptyEnvScope()];
}

aspect function toEnv
Decorated Env ::= d::Defs
{
  top.terminalTree = [oneEnvScope(buildTerminalTree(d.typeList))];
}

aspect function appendEnv
Decorated Env ::= e1::Decorated Env  e2::Decorated Env
{
  top.terminalTree = e1.terminalTree ++ e2.terminalTree;
}

aspect function newScopeEnv
Decorated Env ::= d::Defs  e::Decorated Env
{
  top.terminalTree = oneEnvScope(buildTerminalTree(d.typeList)) :: e.terminalTree;
}

