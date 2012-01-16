grammar silver:translation:java:type;

import silver:definition:type;
import silver:translation:java:core only makeNTClassName;

-- The Java type corresponding to the Silver Type
synthesized attribute transType :: String;
-- Java has crappy syntax for some things.
-- If we want to statically refer to the class of this type, we cannot use
-- the <> part of the type!! e.g. "Foo<Bar>.class" is illegal, should be "Foo.class"
synthesized attribute transClassType :: String;

attribute transType, transClassType occurs on TypeExp;

aspect production defaultTypeExp
top::TypeExp ::=
{
  top.transType = error("INTERNAL ERROR: Some type forgot to define its Java transType.");
  top.transClassType = error("INTERNAL ERROR: Some type forgot to define its Java transClassType.");
}

aspect production varTypeExp
top::TypeExp ::= tv::TyVar
{
  top.transType = "Object";
  top.transClassType = "Object";
}

aspect production skolemTypeExp
top::TypeExp ::= tv::TyVar
{
  top.transType = "Object";
  top.transClassType = "Object";
}

aspect production intTypeExp
top::TypeExp ::=
{
  top.transType = "Integer";
  top.transClassType = "Integer";
}

aspect production boolTypeExp
top::TypeExp ::=
{
  top.transType = "Boolean";
  top.transClassType = "Boolean";
}

aspect production floatTypeExp
top::TypeExp ::=
{
  top.transType = "Float";
  top.transClassType = "Float";
}

aspect production stringTypeExp
top::TypeExp ::=
{
  top.transType = "common.StringCatter";
  top.transClassType = "common.StringCatter";
}

aspect production nonterminalTypeExp
top::TypeExp ::= fn::String params::[TypeExp]
{
  -- untightened version would be "common.Node", but we prefer the generated
  -- class, e.g. silver.definition.core.NExpr
  top.transType = makeNTClassName(fn);
  top.transClassType = top.transType;
}

aspect production terminalTypeExp
top::TypeExp ::= fn::String
{
  top.transType = "common.TerminalRecord";
  top.transClassType = "common.TerminalRecord";
}

aspect production decoratedTypeExp
top::TypeExp ::= te::TypeExp
{
  -- TODO: this should probably be a generic.  e.g. "DecoratedNode<something>"
  top.transType = "common.DecoratedNode";
  top.transClassType = "common.DecoratedNode";
}

aspect production functionTypeExp
top::TypeExp ::= out::TypeExp params::[TypeExp]
{
  top.transType = "common.NodeFactory<" ++ out.transType ++ ">";
  top.transClassType = "common.NodeFactory";
}

