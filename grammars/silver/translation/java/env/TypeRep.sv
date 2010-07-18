grammar silver:translation:java:env;

import silver:definition:env;

synthesized attribute transType :: String;
attribute transType occurs on TypeRep;

aspect production i_integerTypeRep
top::TypeRep ::= 
{
  top.transType = "Integer";
}

aspect production i_floatTypeRep
top::TypeRep ::= 
{
  top.transType = "Float";
}


aspect production i_stringTypeRep
top::TypeRep ::= 
{
  top.transType = "common.StringCatter";
}

aspect production i_booleanTypeRep
top::TypeRep ::= 
{
  top.transType = "Boolean";
}

aspect production i_termTypeRep
top::TypeRep ::= n::String
{
  top.transType = "common.TerminalRecord";
}

aspect production i_ntTypeRep
top::TypeRep ::= n::String
{
  -- todo: we might be able to tighten this up, now that we have a nonterminal class
  -- that productions inherit from.  Is there any gain?  We might find translation
  -- errors easier that way...
  top.transType = "common.Node";
}

aspect production i_refTypeRep
top::TypeRep ::= t::Decorated TypeRep
{
  top.transType = "common.DecoratedNode";
}

aspect production i_prodTypeRep
top::TypeRep ::= it::[Decorated TypeRep] ot::Decorated TypeRep
{
  top.transType = "java.lang.reflect.Constructor";
}

aspect production i_funTypeRep
top::TypeRep ::= it::[Decorated TypeRep] ot::Decorated TypeRep
{
  top.transType = "java.lang.reflect.Constructor";
}

aspect production i_defaultTypeRep
top::TypeRep ::= 
{
  top.transType = "Object";
}
