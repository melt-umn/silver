grammar silver:definition:env;

import silver:util:raw:treemap as rtm; -- good performance (mostly due to strictness!)
--import silver:util:fixedmap as fm; -- decent performance
--import silver:util:treemap as tm; -- poor performance

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
EnvTree<Decorated DclInfo> ::= eis::[EnvItem]
{
  return directBuildTree( explodeEnvItems(eis) );
}

{--
 - Arbitrary environment constructor for a map.
 -}
function directBuildTree
EnvTree<a> ::= eis::[Pair<String a>]
{
  return rtm:add(eis, rtm:empty(compareString));
}

----

{--
 - Helper for buildTree.
 -}
function explodeEnvItems
[Pair<String Decorated DclInfo>] ::= eis::[EnvItem]
{
  return if null(eis) then [] else
            head(eis).envContribs ++ explodeEnvItems(tail(eis));
}

