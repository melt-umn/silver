grammar silver:translation:java:type;

import silver:definition:type;
import silver:translation:java:core only makeNTClassName, makeTerminalName;

-- The Java type corresponding to the Silver Type
synthesized attribute transType :: String;
-- Java has crappy syntax for some things.
-- If we want to statically refer to the class of this type, we cannot use
-- the <> part of the type!! e.g. "Foo<Bar>.class" is illegal, should be "Foo.class"
synthesized attribute transClassType :: String;
-- The runtime representation of a type, used for reification
synthesized attribute transTypeRep :: String;

attribute transType, transClassType, transTypeRep occurs on Type;

aspect production varType
top::Type ::= tv::TyVar
{
  top.transType = "Object";
  top.transClassType = "Object";
  top.transTypeRep = s"typeVar_${toString(tv.extractTyVarRep)}";
}

aspect production skolemType
top::Type ::= tv::TyVar
{
  top.transType = "Object";
  top.transClassType = "Object";
  top.transTypeRep = s"typeVar_${toString(tv.extractTyVarRep)}";
}

aspect production intType
top::Type ::=
{
  top.transType = "Integer";
  top.transClassType = "Integer";
  top.transTypeRep = "new common.BaseTypeRep(\"Integer\")";
}

aspect production boolType
top::Type ::=
{
  top.transType = "Boolean";
  top.transClassType = "Boolean";
  top.transTypeRep = "new common.BaseTypeRep(\"Boolean\")";
}

aspect production floatType
top::Type ::=
{
  top.transType = "Float";
  top.transClassType = "Float";
  top.transTypeRep = "new common.BaseTypeRep(\"Float\")";
}

aspect production stringType
top::Type ::=
{
  top.transType = "common.StringCatter";
  top.transClassType = "common.StringCatter";
  top.transTypeRep = "new common.BaseTypeRep(\"String\")";
}

aspect production nonterminalType
top::Type ::= fn::String params::[Type]
{
  -- untightened version would be "common.Node", but we prefer the generated
  -- class, e.g. silver.definition.core.NExpr
  top.transType = makeNTClassName(fn);
  top.transClassType = top.transType;
  top.transTypeRep =
    s"new common.BaseTypeRep(\"${fn}\", new common.TypeRep[] {${implode(", ", map((.transTypeRep), params))}})";
}

aspect production terminalType
top::Type ::= fn::String
{
  top.transType = makeTerminalName(fn);
  top.transClassType = makeTerminalName(fn);
  top.transTypeRep = s"new common.BaseTypeRep(\"${fn}\")";
}

aspect production decoratedType
top::Type ::= te::Type
{
  -- TODO: this should probably be a generic.  e.g. "DecoratedNode<something>"
  top.transType = "common.DecoratedNode";
  top.transClassType = "common.DecoratedNode";
  top.transTypeRep =
    case te of
      nonterminalType(fn, params) ->
        s"new common.BaseTypeRep(\"Decorated ${fn}\", new common.TypeRep[] {${implode(", ", map((.transTypeRep), params))}})"
    | _ -> error("Found decoratedType that does not wrap nonterminalType!")
    end;
}

aspect production functionType
top::Type ::= out::Type params::[Type] namedParams::[NamedArgType]
{
  top.transType = "common.NodeFactory<" ++ out.transType ++ ">";
  top.transClassType = "common.NodeFactory";
  top.transTypeRep =
    s"new common.FunctionTypeRep(${out.transTypeRep}, " ++
      s"new common.TypeRep[] {${implode(", ", map((.transTypeRep), params))}}, " ++
      s"new String[] {${implode(", ", map(\ nat::NamedArgType -> s"\"${nat.argName}\"", namedParams))}}, " ++
      s"new common.TypeRep[] {${implode(", ", map((.transTypeRep), map((.argType), namedParams)))}})";
}

