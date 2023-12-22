grammar silver:compiler:definition:env;

import silver:util:treemap as rtm; -- good performance (mostly due to strictness!)

{--
 - The abstraction for maps throughout the Silver compiler.
 -}
type EnvTree<a> = rtm:Map<String a>;

{--
 - Look up function for maps.
 -}
fun searchEnvTree [a] ::= search::String et::EnvTree<a> = rtm:lookup(search, et);

{--
 - Standard environment constructor for a map.
 - Obey's EnvItem's rules for what names should appear for each item.
 -}
fun buildTree EnvTree<a> ::= eis::[EnvItem<a>] = directBuildTree(flatMap((.envContribs), eis));

{--
 - Arbitrary environment constructor for a map.
 -}
fun directBuildTree EnvTree<a> ::= eis::[Pair<String a>] = rtm:add(eis, rtm:empty());

fun emptyEnvTree EnvTree<a> ::= = directBuildTree([]);

fun appendEnvTree EnvTree<a> ::= e1::EnvTree<a> e2::EnvTree<a> = rtm:add(rtm:toList(e1), e2);

fun consEnvTree EnvTree<a> ::= eis::[EnvItem<a>] et::EnvTree<a> =
  rtm:add(flatMap((.envContribs), eis), et);
