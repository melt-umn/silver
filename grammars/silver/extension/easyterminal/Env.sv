grammar silver:extension:easyterminal;

import silver:definition:env;
import silver:definition:regex;

-- TODO BUG FIXME: This looks up terminals via their regular expressions, but
-- that confuses single quoted and non-single quoted regexs!
-- i.e. 'hi' will happily match /hi/ and '(' will match /[\(]/, but this should
-- probably not be the case!

-- The correct solution would be to only look up via their "single quotes name"
-- which for '' terminals is the bit in '', while for // terminals, it can be specified if desired

function getTerminalRegexDclAll
[DclInfo] ::= search::String e::Decorated Env
{
  return searchEnv(search, e.terminalTree);
}



synthesized attribute terminalTree :: [EnvScope<DclInfo>] occurs on Env; -- must be kept in sync with typeTree's type!! (whether its a [] or not)

function filterAndConvertTermDcls
[Pair<String DclInfo>] ::= ei::EnvItem sofar::[Pair<String DclInfo>]
{
  return case ei.dcl of
         | termDcl(_, _, fn, regex) -> pair(regex.regString, ei.dcl) :: sofar
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
  top.terminalTree = [emptyEnvScope()];
}

aspect production i_appendEnv
top::Env ::= e1::Decorated Env  e2::Decorated Env
{
  top.terminalTree = e1.terminalTree ++ e2.terminalTree;
}

aspect production i_newScopeEnv
top::Env ::= d::Defs  e::Decorated Env
{
  top.terminalTree = oneEnvScope(buildTerminalTree(d.typeList)) :: e.terminalTree;
}

aspect production i_occursEnv
top::Env ::= _  e::Decorated Env
{
  top.terminalTree = e.terminalTree;
}

