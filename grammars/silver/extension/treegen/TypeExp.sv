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



synthesized attribute idNameForGenArb :: String occurs on TypeExp;

aspect production varTypeExp
top::TypeExp ::= tv::TyVar
{
  top.idNameForGenArb = "VAR";
}
aspect production skolemTypeExp
top::TypeExp ::= tv::TyVar
{
  top.idNameForGenArb = "SKOLEM";
}
aspect production intTypeExp
top::TypeExp ::=
{
  top.idNameForGenArb = "Integer";
}
aspect production boolTypeExp
top::TypeExp ::=
{
  top.idNameForGenArb = "Boolean";
}
aspect production floatTypeExp
top::TypeExp ::=
{
  top.idNameForGenArb = "Float";
}
aspect production stringTypeExp
top::TypeExp ::=
{
  top.idNameForGenArb = "String";
}
aspect production nonterminalTypeExp
top::TypeExp ::= fn::String params::[TypeExp]
{
  -- ignore parameters, we don't support them for now
  top.idNameForGenArb = substring(lastIndexOf(":", fn) + 1, length(fn), fn);
}
aspect production terminalTypeExp
top::TypeExp ::= fn::String
{
  top.idNameForGenArb = substring(lastIndexOf(":", fn) + 1, length(fn), fn);
}
aspect production decoratedTypeExp
top::TypeExp ::= te::TypeExp
{
  top.idNameForGenArb = "Decorated" ++ te.idNameForGenArb;
}
aspect production ntOrDecTypeExp
top::TypeExp ::= nt::TypeExp  hidden::TypeExp
{
  -- err, shouldn't happen?
  top.idNameForGenArb = "WTFnrOrDecTypeExpr";
}
aspect production functionTypeExp
top::TypeExp ::= out::TypeExp params::[TypeExp] namedParams::[NamedArgType]
{
  -- err, shouldn't happen?
  top.idNameForGenArb = "FUNCTION";
}
aspect production listTypeExp
top::TypeExp ::= el::TypeExp
{
  top.idNameForGenArb = "List" ++ el.idNameForGenArb;
}
aspect production foreignTypeExp
top::TypeExp ::= fn::String params::[TypeExp]
{
  -- err, shouldn't happen?
  top.idNameForGenArb = "FOREIGN";
}


