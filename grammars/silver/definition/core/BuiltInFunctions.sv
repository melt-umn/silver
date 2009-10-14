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


concrete production indexOfFunction
top::Expr ::= 'indexOf' '(' e1::Expr ',' e2::Expr ')'
{
  top.pp = "indexOf(" ++ e1.pp ++ ", " ++ e2.pp ++ ")";
  top.location = loc(top.file, $1.line, $2.column);

  top.errors := e1.errors ++ e2.errors;
  top.warnings := [];
  top.typerep = integerTypeRep();
}

concrete production subStringFunction
top::Expr ::= 'substring' '(' e1::Expr ',' e2::Expr ',' e3::Expr ')'
{
  top.pp = "substring(" ++ e1.pp ++ ", " ++ e2.pp ++ ", " ++ e3.pp ++ ")";
  top.location = loc(top.file, $1.line, $2.column);

  top.errors := e1.errors ++ e2.errors ++ e3.errors;
  top.warnings := [];
  top.typerep = stringTypeRep();
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

concrete production isDigitFunction
top::Expr ::= 'isDigit' '(' e::Expr ')'
{
  top.pp = "isDigit(" ++ e.pp ++ ")";
  top.location = loc(top.file, $1.line, $2.column);

  top.errors := e.errors;
  top.warnings := [];
  top.typerep = booleanTypeRep();
}

concrete production isAlphaFunction
top::Expr ::= 'isAlpha' '(' e::Expr ')'
{
  top.pp = "isAlpha(" ++ e.pp ++ ")";
  top.location = loc(top.file, $1.line, $2.column);

  top.errors := e.errors;
  top.warnings := [];
  top.typerep = booleanTypeRep();
}

concrete production isSpaceFunction
top::Expr ::= 'isSpace' '(' e::Expr ')'
{
  top.pp = "isSpace(" ++ e.pp ++ ")";
  top.location = loc(top.file, $1.line, $2.column);

  top.errors := e.errors;
  top.warnings := [];
  top.typerep = booleanTypeRep();
}

concrete production isLowerFunction
top::Expr ::= 'isLower' '(' e::Expr ')'
{
  top.pp = "isLower(" ++ e.pp ++ ")";
  top.location = loc(top.file, $1.line, $2.column);

  top.errors := e.errors;
  top.warnings := [];
  top.typerep = booleanTypeRep();
}

concrete production isUpperFunction
top::Expr ::= 'isUpper' '(' e::Expr ')'
{
  top.pp = "isUpper(" ++ e.pp ++ ")";
  top.location = loc(top.file, $1.line, $2.column);

  top.errors := e.errors;
  top.warnings := [];
  top.typerep = booleanTypeRep();
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

concrete production terminalFunction2
top::Expr ::= 'terminal' '(' t::Type ',' e1::Expr ',' e2::Expr ',' e3::Expr ')'
{
  top.pp = "terminal(" ++ t.pp ++ ", " ++ e1.pp ++ ", " ++ e2.pp ++ ", " ++ e3.pp ++ ")";
  top.location = loc(top.file, $1.line, $2.column);

  top.errors := t.errors ++ e1.errors ++ e2.errors ++ e3.errors;
  top.warnings := [];
  top.typerep = t.typerep;
}
