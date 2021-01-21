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

