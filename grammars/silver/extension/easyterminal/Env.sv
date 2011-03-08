grammar silver:extension:easyterminal;

import silver:definition:env;
import silver:definition:regex;


function getTerminalRegexDclAll
[Decorated DclInfo] ::= search::String e::Decorated Env
{
  return searchEnvScope(search, e.terminalTree);
}



synthesized attribute terminalTree :: Decorated EnvScope occurs on Env; -- must be kept in sync with typeTree's type!! (whether its a [] or not)

function filterAndConvertTermDcls
[Decorated EnvItem] ::= ei::Decorated EnvItem sofar::[Decorated EnvItem]
{
  return case ei.dcl of
           termDcl(_, _, fn, regex) -> renamedEnvItem(regex.regString, ei.dcl) :: sofar
         | _ -> sofar
         end;
}

function buildTerminalTree
Decorated EnvTree ::= eis::[Decorated EnvItem]
{
  return buildTreeFromCollected(collectEnvItems(sortEnvItems(foldr(filterAndConvertTermDcls,[],eis))));
}

aspect production i_emptyEnv 
top::Env ::= 
{
  top.terminalTree = emptyEnvScope();
}

aspect production i_toEnv
top::Env ::= d::Defs
{
  top.terminalTree = oneEnvScope(buildTerminalTree(d.typeList));
}

aspect production i_appendEnv
top::Env ::= e1::Decorated Env  e2::Decorated Env
{
  top.terminalTree = appendEnvScope(e1.terminalTree, e2.terminalTree);
}

aspect production i_newScopeEnv
top::Env ::= d::Defs  e::Decorated Env
{
  top.terminalTree = consEnvScope(buildTerminalTree(d.typeList), e.terminalTree);
}

