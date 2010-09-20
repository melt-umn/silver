grammar silver:translation:java:type;

import silver:definition:type;

-- The Java type corresponding to the Silver Type
synthesized attribute transType :: String;

-- Whether this type may be supplied with inherited attributes.
-- Used only to determine if the maps should be created. (NOT TYPE CHECKING)
synthesized attribute mayBeSuppliedInhAttrs :: Boolean;

attribute transType occurs on TypeExp;

aspect production defaultTypeExp
top::TypeExp ::=
{
  top.transType = error("INTERNAL ERROR: Some type forgot to define its Java transType.");
  top.mayBeSuppliedInhAttrs = false;
}

aspect production varTypeExp
top::TypeExp ::= tv::TyVar
{
  top.transType = "Object";
}

aspect production skolemTypeExp
top::TypeExp ::= tv::TyVar
{
  top.transType = "Object";
}

aspect production intTypeExp
top::TypeExp ::=
{
  top.transType = "Integer";
}

aspect production boolTypeExp
top::TypeExp ::=
{
  top.transType = "Boolean";
}

aspect production floatTypeExp
top::TypeExp ::=
{
  top.transType = "Float";
}

aspect production stringTypeExp
top::TypeExp ::=
{
  top.transType = "common.StringCatter";
}

aspect production nonterminalTypeExp
top::TypeExp ::= fn::String params::[TypeExp]
{
  -- untightened version would be "common.Node"
  top.transType = makeNTClassName(fn);
  top.mayBeSuppliedInhAttrs = true;
}

aspect production terminalTypeExp
top::TypeExp ::= fn::String
{
  top.transType = "common.TerminalRecord";
}

aspect production decoratedTypeExp
top::TypeExp ::= te::TypeExp
{
  -- TODO: this should probably be a generic.  e.g. "DecoratedNode<something>"
  top.transType = "common.DecoratedNode";
}

aspect production functionTypeExp
top::TypeExp ::= out::TypeExp params::[TypeExp]
{
  top.transType = "java.lang.reflect.Constructor";
}

aspect production productionTypeExp
top::TypeExp ::= out::TypeExp params::[TypeExp]
{
  top.transType = "java.lang.reflect.Constructor";
}


