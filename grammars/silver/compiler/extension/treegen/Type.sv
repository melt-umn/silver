grammar silver:compiler:extension:treegen;

imports silver:compiler:definition:core;
imports silver:compiler:definition:env;
imports silver:compiler:definition:concrete_syntax;
imports silver:compiler:definition:type;
imports silver:compiler:definition:type:syntax;
imports silver:compiler:extension:convenience;
imports silver:compiler:extension:list;
imports silver:compiler:modification:ffi;

imports silver:compiler:modification:collection;


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
aspect production appType
top::Type ::= c::Type a::Type
{
  -- ignore parameters, we don't support them for now
  top.idNameForGenArb = c.idNameForGenArb;
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
aspect production terminalIdType
top::Type ::=
{
  top.idNameForGenArb = "TerminalId";
}
aspect production nonterminalType
top::Type ::= fn::String _ _
{
  top.idNameForGenArb = substring(lastIndexOf(":", fn) + 1, length(fn), fn);
}
aspect production terminalType
top::Type ::= fn::String
{
  top.idNameForGenArb = substring(lastIndexOf(":", fn) + 1, length(fn), fn);
}
aspect production decoratedType
top::Type ::= te::Type _
{
  top.idNameForGenArb = "Decorated" ++ te.idNameForGenArb;
}
aspect production inhSetType
top::Type ::= _
{
  -- err, shouldn't happen?
  top.idNameForGenArb = "INH_SET";
}
aspect production ntOrDecType
top::Type ::= _ _ _
{
  -- err, shouldn't happen?
  top.idNameForGenArb = "WTFnrOrDecTypeExpr";
}
aspect production functionType
top::Type ::= _ _
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
top::Type ::= fn::String  transType::String  params::[Type]
{
  -- err, shouldn't happen?
  top.idNameForGenArb = "FOREIGN";
}


