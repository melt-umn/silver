grammar silver:definition:core;
import silver:definition:env;

concrete production lengthFunction
top::Expr ::= 'length' '(' e::Expr ')'
{
  top.pp = "length(" ++ e.pp ++ ")";
  top.location = loc(top.file, $1.line, $2.column);

  production attribute handlers :: [Expr] with ++;
  handlers := [];

  forwards to if null(handlers) then unknownLength(e) else head(handlers);
}

aspect production lengthFunction
top::Expr ::= 'length' '(' e::Expr ')'
{
  handlers <- if e.typerep.typeName == "String"
	      then [stringLength(e)]
	      else [];
}

abstract production unknownLength
top::Expr ::= e::Decorated Expr
{
  top.errors := e.errors;
  top.warnings := [];
  top.typerep = topTypeRep();
}

abstract production stringLength
top::Expr ::= e::Decorated Expr
{
  top.errors := e.errors;
  top.warnings := [];
  top.typerep = integerTypeRep();
}

concrete production errorFunction
top::Expr ::= 'error' '(' e::Expr ')'
{
  top.pp = "error(" ++ e.pp ++ ")";
  top.location = loc(top.file, $1.line, $2.column);

  top.errors := e.errors;
  top.warnings := [];
  top.typerep = topTypeRep();
}

concrete production toIntFunction
top::Expr ::= 'toInt' '(' e::Expr ')'
{
  top.pp = "toInt(" ++ e.pp ++ ")";
  top.location = loc(top.file, $1.line, $2.column);

  top.errors := e.errors;
  top.warnings := [];
  top.typerep = integerTypeRep();
}

concrete production toFloatFunction
top::Expr ::= 'toFloat' '(' e::Expr ')'
{
  top.pp = "toFloat(" ++ e.pp ++ ")";
  top.location = loc(top.file, $1.line, $2.column);

  top.errors := e.errors;
  top.warnings := [];
  top.typerep = floatTypeRep();
}

concrete production toStringFunction
top::Expr ::= 'toString' '(' e::Expr ')'
{
  top.pp = "toString(" ++ e.pp ++ ")";
  top.location = loc(top.file, $1.line, $2.column);

  top.errors := e.errors;
  top.warnings := [];
  top.typerep = stringTypeRep();
}

concrete production newFunction
top::Expr ::= 'new' '(' e::Expr ')'
{
  top.pp = "new(" ++ e.pp ++ ")";
  top.location = loc(top.file, $1.line, $2.column);

  top.errors := e.errors;
  top.warnings := [];
  top.typerep = e.typerep.decoratedType ;

  e.expected = expected_decorated();
}

concrete production terminalFunction
top::Expr ::= 'terminal' '(' t::Type ',' e::Expr ')'
{
  top.pp = "terminal(" ++ t.pp ++ ", " ++ e.pp ++ ")";
  top.location = loc(top.file, $1.line, $2.column);

  top.errors := t.errors ++ e.errors;
  top.warnings := [];
  top.typerep = t.typerep;
}

concrete production terminalFunctionLineCol
top::Expr ::= 'terminal' '(' t::Type ',' e1::Expr ',' e2::Expr ',' e3::Expr ')'
{
  top.pp = "terminal(" ++ t.pp ++ ", " ++ e1.pp ++ ", " ++ e2.pp ++ ", " ++ e3.pp ++ ")";
  top.location = loc(top.file, $1.line, $2.column);

  top.errors := t.errors ++ e1.errors ++ e2.errors ++ e3.errors;
  top.warnings := [];
  top.typerep = t.typerep;
}

concrete production terminalFunctionInherited
top::Expr ::= 'terminal' '(' t::Type ',' e1::Expr ',' e2::Expr ')'
{
  top.pp = "terminal(" ++ t.pp ++ ", " ++ e1.pp ++ "," ++ e2.pp ++ ")";
  top.location = loc(top.file, $1.line, $2.column);

  top.errors := t.errors ++ e1.errors ++ e2.errors;
  top.warnings := [];
  top.typerep = t.typerep;
}
