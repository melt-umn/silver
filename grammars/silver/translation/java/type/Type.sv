grammar silver:translation:java:type;

imports silver:definition:type;
imports silver:translation:java:core;

-- The Java type corresponding to the Silver Type
synthesized attribute transType :: String;
-- Java has crappy syntax for some things.
-- If we want to statically refer to the class of this type, we cannot use
-- the <> part of the type!! e.g. "Foo<Bar>.class" is illegal, should be "Foo.class"
synthesized attribute transClassType :: String;
-- The runtime representation of a type, used for reification
synthesized attribute transTypeRep :: String;
-- The runtime representation of a type, where all skolems are replaced with flexible vars, used for reification
synthesized attribute transFreshTypeRep :: String;
-- A valid Java identifier, unique to the type
synthesized attribute transTypeName :: String;

attribute transType, transClassType, transTypeRep, transFreshTypeRep, transTypeName occurs on Type;

aspect production varType
top::Type ::= tv::TyVar
{
  top.transType = "Object";
  top.transClassType = "Object";
  top.transTypeRep = s"freshTypeVar_${toString(tv.extractTyVarRep)}";
  top.transFreshTypeRep = top.transTypeRep;
  top.transTypeName = "a";
}

aspect production skolemType
top::Type ::= tv::TyVar
{
  top.transType = "Object";
  top.transClassType = "Object";
  top.transTypeRep = s"new common.BaseTypeRep(\"b${toString(tv.extractTyVarRep)}\")";
  top.transFreshTypeRep = s"freshTypeVar_${toString(tv.extractTyVarRep)}";
  top.transTypeName = "a";
}

aspect production errorType
top::Type ::=
{
  local oops :: String = error("Attempting to translate in presence of errors");
  top.transType = oops;
  top.transClassType = oops;
  top.transTypeRep = oops;
  top.transFreshTypeRep = oops;
  top.transTypeName = oops;
}

aspect production intType
top::Type ::=
{
  top.transType = "Integer";
  top.transClassType = "Integer";
  top.transTypeRep = "new common.BaseTypeRep(\"Integer\")";
  top.transFreshTypeRep = top.transTypeRep;
  top.transTypeName = "Integer";
}

aspect production boolType
top::Type ::=
{
  top.transType = "Boolean";
  top.transClassType = "Boolean";
  top.transTypeRep = "new common.BaseTypeRep(\"Boolean\")";
  top.transFreshTypeRep = top.transTypeRep;
  top.transTypeName = "Boolean";
}

aspect production floatType
top::Type ::=
{
  top.transType = "Float";
  top.transClassType = "Float";
  top.transTypeRep = "new common.BaseTypeRep(\"Float\")";
  top.transFreshTypeRep = top.transTypeRep;
  top.transTypeName = "Float";
}

aspect production stringType
top::Type ::=
{
  top.transType = "common.StringCatter";
  top.transClassType = "common.StringCatter";
  top.transTypeRep = "new common.BaseTypeRep(\"String\")";
  top.transFreshTypeRep = top.transTypeRep;
  top.transTypeName = "String";
}

aspect production terminalIdType
top::Type ::=
{
  top.transType = "Integer";
  top.transClassType = "Integer";
  top.transTypeRep = "new common.BaseTypeRep(\"TerminalId\")";
  top.transFreshTypeRep = top.transTypeRep;
  top.transTypeName = "TerminalId";
}

aspect production nonterminalType
top::Type ::= fn::String params::[Type]
{
  -- untightened version would be "common.Node", but we prefer the generated
  -- class, e.g. silver.definition.core.NExpr
  top.transType = makeNTName(fn);
  top.transClassType = top.transType;
  top.transTypeRep =
    s"new common.BaseTypeRep(\"${fn}\", new common.TypeRep[] {${implode(", ", map((.transTypeRep), params))}})";
  top.transFreshTypeRep =
    s"new common.BaseTypeRep(\"${fn}\", new common.TypeRep[] {${implode(", ", map((.transFreshTypeRep), params))}})";
  top.transTypeName = substitute(":", "_", fn) ++ "_" ++ implode("_", map((.transTypeName), params));
}

aspect production terminalType
top::Type ::= fn::String
{
  top.transType = makeTerminalName(fn);
  top.transClassType = makeTerminalName(fn);
  top.transTypeRep = s"new common.BaseTypeRep(\"${fn}\")";
  top.transFreshTypeRep = top.transTypeRep;
  top.transTypeName = substitute(":", "_", fn);
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
  top.transFreshTypeRep =
    case te of
      nonterminalType(fn, params) ->
        s"new common.BaseTypeRep(\"Decorated ${fn}\", new common.TypeRep[] {${implode(", ", map((.transFreshTypeRep), params))}})"
    | _ -> error("Found decoratedType that does not wrap nonterminalType!")
    end;
  top.transTypeName = "Decorated_" ++ te.transTypeName;
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
  top.transFreshTypeRep =
    s"new common.FunctionTypeRep(${out.transFreshTypeRep}, " ++
      s"new common.TypeRep[] {${implode(", ", map((.transFreshTypeRep), params))}}, " ++
      s"new String[] {${implode(", ", map(\ nat::NamedArgType -> s"\"${nat.argName}\"", namedParams))}}, " ++
      s"new common.TypeRep[] {${implode(", ", map((.transFreshTypeRep), map((.argType), namedParams)))}})";
  top.transTypeName = "Fn_" ++ out.transTypeName ++ "_from_" ++ implode("_", map((.transTypeName), params)) ++ implode("_", map((.transTypeName), namedParams));
}

attribute transTypeName occurs on NamedArgType;

aspect production namedArgType
top::NamedArgType ::= s::String  ty::Type
{
  top.transTypeName = s ++ "_" ++ ty.transTypeName;
}
