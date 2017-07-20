grammar silver:definition:env;

-- Just to clarify:
-- call prettyType to pretty print the type.
-- get typeName to find out what nonterminal a NT or DNT is
-- call unparseType to put something into an interface file

attribute unparse, typeName occurs on Type;

synthesized attribute typeName :: String;

aspect default production
top::Type ::=
{
  top.typeName = ""; -- We actually put a value here, since it's possible for us to request typeName of nonsensical things.
}

aspect production varType
top::Type ::= tv::TyVar
{
  top.unparse = findAbbrevFor(tv, top.boundVariables);
}

aspect production skolemType
top::Type ::= tv::TyVar
{
  top.unparse = findAbbrevFor(tv, top.boundVariables);
}

aspect production intType
top::Type ::=
{
  top.unparse = "int";
}

aspect production boolType
top::Type ::=
{
  top.unparse = "bool" ;
}

aspect production floatType
top::Type ::=
{
  top.unparse = "float" ;
}

aspect production stringType
top::Type ::=
{
  top.unparse = "string" ;
}

aspect production nonterminalType
top::Type ::= fn::String params::[Type]
{
  top.unparse = "nt('" ++ fn ++ "', " ++ unparseTypes(params, top.boundVariables) ++ ")"; -- TODO todo WHAT?! why must my comments suck
  top.typeName = fn;
}

aspect production terminalType
top::Type ::= fn::String
{
  top.unparse = "term('" ++ fn ++ "')";
  top.typeName = fn;
}

aspect production decoratedType
top::Type ::= te::Type
{
  top.unparse = "decorated(" ++ te.unparse ++ ")" ;
  top.typeName = te.typeName;
}

aspect production functionType
top::Type ::= out::Type params::[Type] namedParams::[NamedArgType]
{
  top.unparse = "fun(" ++ unparseTypes(params, top.boundVariables) ++ ", " ++ out.unparse ++ unparseNameArgTypes(namedParams, top.boundVariables) ++ ")"  ;
}

function unparseNameArgTypes
String ::= np::[NamedArgType]  bv::[TyVar]
{
  return case np of
         | [] -> ""
         | namedArgType(s, t) :: rest -> ";" ++ quoteString(s) ++ "=" ++ decorate t with {boundVariables = bv;}.unparse ++ unparseNameArgTypes(rest, bv)
         end;
}

