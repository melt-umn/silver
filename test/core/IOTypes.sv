grammar core;

synthesized attribute io :: IO;
synthesized attribute stringList :: StringList;
synthesized attribute sValue :: String;
synthesized attribute bValue :: Boolean;
synthesized attribute iValue :: Integer;

nonterminal IOBoolean;
attribute io occurs on IOBoolean;
attribute bValue occurs on IOBoolean;

nonterminal IOInteger;
attribute io occurs on IOInteger;
attribute iValue occurs on IOInteger;

nonterminal IOString;
attribute io occurs on IOString;
attribute sValue occurs on IOString;

nonterminal IOStringList;
attribute io occurs on IOStringList;
attribute stringList occurs on IOStringList;


abstract production ioBoolean
top::IOBoolean ::= i::IO b::Boolean
{
  top.io = i;
  top.bValue = b;
}

abstract production ioString
top::IOString ::= i::IO s::String
{
  top.io = i;
  top.sValue = s;
}

abstract production ioInteger
top::IOInteger ::= i::IO int::Integer
{
  top.io = i;
  top.iValue = int;
}

abstract production ioStringList
top::IOStringList ::= i::IO sl::StringList
{
  top.io = i;
  top.stringList = sl;
}
