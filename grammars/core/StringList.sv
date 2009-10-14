grammar core;

synthesized attribute empty :: Boolean;
synthesized attribute sh    :: String;
synthesized attribute st    :: StringList;

nonterminal StringList;
attribute sh occurs on StringList;
attribute st occurs on StringList;
attribute empty occurs on StringList;

abstract production emptyString
top::StringList ::= 
{
  forwards to empty_string();
}

abstract production empty_string
top::StringList ::= 
{
  top.empty = true;
  top.sh = error("Head not defined on an empty core string list");
  top.st = error("Tail not defined on an empty core string list");
}


abstract production consString
top::StringList ::= h::String t::StringList
{
  forwards to cons_string(h, t);
}

abstract production cons_string
top::StringList ::= h::String t::StringList
{
  top.empty = false;
  top.sh = h;
  top.st = t'';
}
