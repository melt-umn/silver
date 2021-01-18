grammar silver:compiler:extension:doc:core;

nonterminal DocDclInfo with id, loc, path;

synthesized attribute id :: String;
synthesized attribute path :: String;

abstract production docDclInfo
top::DocDclInfo ::= typ::String id::String loc::Location path::String
{
  top.id = id;
  top.loc = loc;
  top.path = path;
}
