grammar silver:definition:core;

--import silver:analysis:typechecking:core;

concrete production lengthFunction
top::Expr ::= 'length' '(' e::Expr ')'
{
  top.pp = "length(" ++ e.pp ++ ")";
  top.location = loc(top.file, $1.line, $2.column);

  top.typerep = intTypeExp();

  forwards to performSubstitution(e.typerep, e.upSubst).lengthDispatcher(e);
}

abstract production errorLength
top::Expr ::= e::Decorated Expr
{
  top.pp = "length(" ++ e.pp ++ ")";
  top.location = e.location;

  top.typerep = intTypeExp();

  top.errors := [err(e.location, "Operand to length is not compatible. It is of type " ++ prettyType(performSubstitution(e.typerep, top.finalSubst)))] ++ e.errors;
}

abstract production stringLength
top::Expr ::= e::Decorated Expr
{
  top.pp = "length(" ++ e.pp ++ ")";
  top.location = e.location;

  top.typerep = intTypeExp();

  top.errors := e.errors;
}

concrete production toIntFunction
top::Expr ::= 'toInt' '(' e::Expr ')'
{
  top.pp = "toInt(" ++ e.pp ++ ")";
  top.location = loc(top.file, $1.line, $2.column);

  top.errors := e.errors;
  top.typerep = intTypeExp();
}

concrete production toFloatFunction
top::Expr ::= 'toFloat' '(' e::Expr ')'
{
  top.pp = "toFloat(" ++ e.pp ++ ")";
  top.location = loc(top.file, $1.line, $2.column);

  top.errors := e.errors;
  top.typerep = floatTypeExp();
}

concrete production toStringFunction
top::Expr ::= 'toString' '(' e::Expr ')'
{
  top.pp = "toString(" ++ e.pp ++ ")";
  top.location = loc(top.file, $1.line, $2.column);

  top.errors := e.errors;
  top.typerep = stringTypeExp();
}

concrete production newFunction
top::Expr ::= 'new' '(' e::Expr ')'
{
  top.pp = "new(" ++ e.pp ++ ")";
  top.location = loc(top.file, $1.line, $2.column);

  top.errors := e.errors;
  top.typerep = performSubstitution(e.typerep, top.upSubst).decoratedType;
}

concrete production terminalFunction
top::Expr ::= 'terminal' '(' t::Type ',' e::Expr ')'
{
  top.pp = "terminal(" ++ t.pp ++ ", " ++ e.pp ++ ")";
  top.location = loc(top.file, $1.line, $2.column);

  top.errors := t.errors ++ e.errors;
  top.typerep = t.typerep;
}

concrete production terminalFunctionLineCol
top::Expr ::= 'terminal' '(' t::Type ',' e1::Expr ',' e2::Expr ',' e3::Expr ')'
{
  top.pp = "terminal(" ++ t.pp ++ ", " ++ e1.pp ++ ", " ++ e2.pp ++ ", " ++ e3.pp ++ ")";
  top.location = loc(top.file, $1.line, $2.column);

  top.errors := t.errors ++ e1.errors ++ e2.errors ++ e3.errors;
  top.typerep = t.typerep;
}

concrete production terminalFunctionInherited
top::Expr ::= 'terminal' '(' t::Type ',' e1::Expr ',' e2::Expr ')'
{
  top.pp = "terminal(" ++ t.pp ++ ", " ++ e1.pp ++ "," ++ e2.pp ++ ")";
  top.location = loc(top.file, $1.line, $2.column);

  top.errors := t.errors ++ e1.errors ++ e2.errors;
  top.typerep = t.typerep;
}
