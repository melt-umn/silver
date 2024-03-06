grammar silver:compiler:translation:java:type;

imports silver:compiler:definition:type;
imports silver:compiler:translation:java:core;

-- The Java type corresponding to the Silver Type
synthesized attribute transType :: String;
-- The *covariant* Java generic type argument corresponding to the Silver Type
-- e.g. ? extends Object instead of Object
synthesized attribute transCovariantType :: String;
-- Java has crappy syntax for some things.
-- If we want to statically refer to the class of this type, we cannot use
-- the <> part of the type!! e.g. "Foo<Bar>.class" is illegal, should be "Foo.class"
synthesized attribute transClassType :: String;
-- An environment mapping skolem constants to their runtime representation translations
inherited attribute skolemTypeReps :: [(TyVar, String)];
-- The runtime representation of a type, where all skolems are replaced with their provided representations, used for reification
synthesized attribute transTypeRep :: String;
-- A valid Java identifier, unique to the type
synthesized attribute transTypeName :: String;

function transFreshTypeRep
String ::= t::Type
{
  t.skolemTypeReps = map(\ tv::TyVar -> (tv, s"freshTypeVar_${toString(tv.varId)}"), t.freeSkolemVars);
  return t.transTypeRep;
}

function transTypeRepWith
String ::= t::Type skolemTypeReps::[(TyVar, String)]
{
  t.skolemTypeReps = skolemTypeReps;
  return t.transTypeRep;
}

function transTypeName
String ::= te::Type
{
  te.boundVariables = te.freeVariables;
  return te.transTypeName;
}

function transTypeNameWith
String ::= te::Type tvs::[TyVar]
{
  te.boundVariables = tvs;
  return te.transTypeName;
}

attribute transType, transCovariantType, transClassType, transTypeRep, skolemTypeReps, transTypeName occurs on Type;
propagate skolemTypeReps on Type;

aspect default production
top::Type ::=
{
  top.transType = top.transClassType;
  top.transCovariantType = top.transType;
}

aspect production varType
top::Type ::= tv::TyVar
{
  top.transCovariantType = "? extends Object";
  top.transClassType = "Object";
  top.transTypeRep = s"freshTypeVar_${toString(tv.varId)}";
  top.transTypeName = "a" ++ toString(positionOf(tv, top.boundVariables));
}

aspect production skolemType
top::Type ::= tv::TyVar
{
  top.transCovariantType = "? extends Object";
  top.transClassType = "Object";
  top.transTypeRep = lookup(tv, top.skolemTypeReps).fromJust;
  top.transTypeName = "a" ++ toString(positionOf(tv, top.boundVariables));
}

aspect production appType
top::Type ::= c::Type a::Type
{
  top.transType =
    case c.baseType of
    | functionType(_, _) -> "common.NodeFactory<" ++ top.outputType.transCovariantType ++ ">"
    | _ -> c.transType
    end;
  top.transCovariantType =
    case c.baseType of
    | functionType(_, _) -> "? extends common.NodeFactory<" ++ top.outputType.transCovariantType ++ ">"
    | _ -> c.transCovariantType
    end;
  top.transClassType = c.transClassType;
  top.transTypeRep = s"new common.AppTypeRep(${c.transTypeRep}, ${a.transTypeRep})";
  top.transTypeName = c.transTypeName ++ "_" ++ a.transTypeName;
}

aspect production errorType
top::Type ::=
{
  local oops :: String = error("Attempting to translate in presence of errors");
  top.transClassType = oops;
  top.transTypeRep = oops;
  top.transTypeName = oops;
}

aspect production intType
top::Type ::=
{
  top.transClassType = "Integer";
  top.transTypeRep = "new common.BaseTypeRep(\"Integer\")";
  top.transTypeName = "Integer";
}

aspect production boolType
top::Type ::=
{
  top.transClassType = "Boolean";
  top.transTypeRep = "new common.BaseTypeRep(\"Boolean\")";
  top.transTypeName = "Boolean";
}

aspect production floatType
top::Type ::=
{
  top.transClassType = "Float";
  top.transTypeRep = "new common.BaseTypeRep(\"Float\")";
  top.transTypeName = "Float";
}

aspect production stringType
top::Type ::=
{
  top.transClassType = "common.StringCatter";
  top.transTypeRep = "new common.BaseTypeRep(\"String\")";
  top.transTypeName = "String";
}

aspect production terminalIdType
top::Type ::=
{
  top.transClassType = "Integer";
  top.transTypeRep = "new common.BaseTypeRep(\"TerminalId\")";
  top.transTypeName = "TerminalId";
}

aspect production nonterminalType
top::Type ::= fn::String _ _ _
{
  -- untightened version would be "common.Node", but we prefer the generated
  -- class, e.g. silver.definition.core.NExpr
  top.transClassType = makeNTName(fn);
  top.transTypeRep = s"new common.BaseTypeRep(\"${fn}\")";
  top.transTypeName = substitute(":", "_", fn);
}

aspect production terminalType
top::Type ::= fn::String
{
  top.transClassType = makeTerminalName(fn);
  top.transTypeRep = s"new common.BaseTypeRep(\"${fn}\")";
  top.transTypeName = substitute(":", "_", fn);
}

aspect production inhSetType
top::Type ::= inhs::[String]
{
  top.transClassType = error("Demanded translation of InhSet type");
  top.transTypeRep = error("Demanded TypeRep translation of InhSet type");
  top.transTypeName = substitute(":", "_", implode("_", inhs));
}

aspect production decoratedType
top::Type ::= te::Type i::Type
{
  -- TODO: this should probably be a generic.  e.g. "DecoratedNode<something>"
  top.transType = "common.DecoratedNode";
  top.transClassType = "common.DecoratedNode";
  top.transTypeRep = s"new common.DecoratedTypeRep(${te.transTypeRep})";
  top.transTypeName = "Decorated_" ++ te.transTypeName;
}

aspect production uniqueDecoratedType
top::Type ::= te::Type i::Type
{
  -- TODO: this should probably be a generic.  e.g. "DecoratedNode<something>"
  top.transType = "common.DecoratedNode";
  top.transClassType = "common.DecoratedNode";
  top.transTypeRep = s"new common.DecoratedTypeRep(${te.transTypeRep})";
  top.transTypeName = "Decorated_" ++ te.transTypeName;
}

aspect production functionType
top::Type ::= params::Integer namedParams::[String]
{
  top.transClassType = "common.NodeFactory";
  top.transTypeRep =
    s"new common.FunctionTypeRep(${toString(params)}, new String[] {${implode(", ", map(\ n::String -> s"\"${n}\"", namedParams))}})";
  top.transTypeName = "Fn_" ++ toString(params) ++ "_" ++ implode("_", namedParams);
}

aspect production dispatchType
top::Type ::= fn::String
{
  top.transClassType = "common.NodeFactory";
  top.transTypeRep = s"new common.BaseTypeRep(\"${fn}\")";
  top.transTypeName = substitute(":", "_", fn);
}
