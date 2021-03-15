grammar silver:compiler:definition:env;

import silver:util:treemap as rtm; -- good performance (mostly due to strictness!)

{--
 - The abstraction for maps throughout the Silver compiler.
 -}
type EnvTree<a> = rtm:Map<String a>;

{--
 - Look up function for maps.
 -}
function searchEnvTree
[a] ::= search::String et::EnvTree<a>
{
  return rtm:lookup(search, et);
}

{--
 - Standard environment constructor for a map.
 - Obey's EnvItem's rules for what names should appear for each item.
 -}
function buildTree
EnvTree<DclInfo> ::= eis::[EnvItem]
{
  return directBuildTree(flatMap((.envContribs), eis));
}

{--
 - Arbitrary environment constructor for a map.
 -}
function directBuildTree
EnvTree<a> ::= eis::[Pair<String a>]
{
  return rtm:add(eis, rtm:empty());
}

function emptyEnvTree
EnvTree<a> ::=
{
  return directBuildTree([]);
}

function appendEnvTree
EnvTree<a> ::= e1::EnvTree<a> e2::EnvTree<a>
{
  return rtm:add(rtm:toList(e1), e2);
}

function consEnvTree
EnvTree<DclInfo> ::= eis::[EnvItem] et::EnvTree<DclInfo>
{
  return rtm:add(flatMap((.envContribs), eis), et);
}
