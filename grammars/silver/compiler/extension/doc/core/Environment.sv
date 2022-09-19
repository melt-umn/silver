grammar silver:compiler:extension:doc:core;

nonterminal DocDclInfo with sourceGrammar, sourceLocation;

synthesized attribute scopedName::String occurs on DocDclInfo;

abstract production docDclInfo
top::DocDclInfo ::= id::String
{
  top.scopedName = top.sourceGrammar ++ ":" ++ id;
}
