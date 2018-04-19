grammar silver:extension:treegen;

imports silver:definition:core;
imports silver:definition:env;
imports silver:definition:concrete_syntax;
imports silver:definition:type;
imports silver:definition:type:syntax;
imports silver:extension:convenience;
imports silver:extension:list;
imports silver:modification:ffi;

imports silver:modification:collection;


--- conventional suffix for function names e.g. "checkEq" ++ t.idNameForGenArb
synthesized attribute idNameForGenArb :: String occurs on Type;

aspect production varType
top::Type ::= tv::TyVar
{
  top.idNameForGenArb = "VAR";
}
aspect production skolemType
top::Type ::= tv::TyVar
{
  top.idNameForGenArb = "SKOLEM";
}
aspect production errorType
top::Type ::= 
{
  top.idNameForGenArb = "ERROR";
}
aspect production intType
top::Type ::=
{
  top.idNameForGenArb = "Integer";
}
aspect production boolType
top::Type ::=
{
  top.idNameForGenArb = "Boolean";
}
aspect production floatType
top::Type ::=
{
  top.idNameForGenArb = "Float";
}
aspect production stringType
top::Type ::=
{
  top.idNameForGenArb = "String";
}
aspect production nonterminalType
top::Type ::= fn::String params::[Type]
{
  -- ignore parameters, we don't support them for now
  top.idNameForGenArb = substring(lastIndexOf(":", fn) + 1, length(fn), fn);
}
aspect production terminalType
top::Type ::= fn::String
{
  top.idNameForGenArb = substring(lastIndexOf(":", fn) + 1, length(fn), fn);
}
aspect production decoratedType
top::Type ::= te::Type
{
  top.idNameForGenArb = "Decorated" ++ te.idNameForGenArb;
}
aspect production ntOrDecType
top::Type ::= nt::Type  hidden::Type
{
  -- err, shouldn't happen?
  top.idNameForGenArb = "WTFnrOrDecTypeExpr";
}
aspect production functionType
top::Type ::= out::Type params::[Type] namedParams::[NamedArgType]
{
  -- err, shouldn't happen?
  top.idNameForGenArb = "FUNCTION";
}
aspect production listType
top::Type ::= el::Type
{
  top.idNameForGenArb = "List" ++ el.idNameForGenArb;
}
aspect production foreignType
top::Type ::= fn::String params::[Type]
{
  -- err, shouldn't happen?
  top.idNameForGenArb = "FOREIGN";
}


