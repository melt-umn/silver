grammar silver:translation:java:type;

import silver:definition:type;
import silver:translation:java:core only makeNTClassName, makeTerminalName;

-- The Java type corresponding to the Silver Type
synthesized attribute transType :: String;
-- Java has crappy syntax for some things.
-- If we want to statically refer to the class of this type, we cannot use
-- the <> part of the type!! e.g. "Foo<Bar>.class" is illegal, should be "Foo.class"
synthesized attribute transClassType :: String;

attribute transType, transClassType occurs on Type;

aspect production varType
top::Type ::= tv::TyVar
{
  top.transType = "Object";
  top.transClassType = "Object";
}

aspect production skolemType
top::Type ::= tv::TyVar
{
  top.transType = "Object";
  top.transClassType = "Object";
}

aspect production intType
top::Type ::=
{
  top.transType = "Integer";
  top.transClassType = "Integer";
}

aspect production boolType
top::Type ::=
{
  top.transType = "Boolean";
  top.transClassType = "Boolean";
}

aspect production floatType
top::Type ::=
{
  top.transType = "Float";
  top.transClassType = "Float";
}

aspect production stringType
top::Type ::=
{
  top.transType = "common.StringCatter";
  top.transClassType = "common.StringCatter";
}

aspect production nonterminalType
top::Type ::= fn::String params::[Type]
{
  -- untightened version would be "common.Node", but we prefer the generated
  -- class, e.g. silver.definition.core.NExpr
  top.transType = makeNTClassName(fn);
  top.transClassType = top.transType;
}

aspect production terminalType
top::Type ::= fn::String
{
  top.transType = makeTerminalName(fn);
  top.transClassType = makeTerminalName(fn);
}

aspect production decoratedType
top::Type ::= te::Type
{
  -- TODO: this should probably be a generic.  e.g. "DecoratedNode<something>"
  top.transType = "common.DecoratedNode";
  top.transClassType = "common.DecoratedNode";
}

aspect production functionType
top::Type ::= out::Type params::[Type] namedParams::[NamedArgType]
{
  top.transType = "common.NodeFactory<" ++ out.transType ++ ">";
  top.transClassType = "common.NodeFactory";
}

