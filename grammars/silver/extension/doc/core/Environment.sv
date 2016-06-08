grammar silver:extension:doc:core;

nonterminal DocDclInfo with id, file, path;

synthesized attribute id :: String;
synthesized attribute path :: String;

abstract production functionDocDclInfo
top::DocDclInfo ::= id::String file::String
{
  top.id = id;
  top.file = file;
  top.path = "";
}

abstract production functionDocDclInfoP
top::DocDclInfo ::= id::String file::String path::String
{
  top.id = id;
  top.file = file;
  top.path = path;
}

abstract production productionDocDclInfo
top::DocDclInfo ::= id::String file::String
{
  top.id = id;
  top.file = file;
  top.path = "";
}

abstract production productionDocDclInfoP
top::DocDclInfo ::= id::String file::String path::String
{
  top.id = id;
  top.file = file;
  top.path = path;
}

