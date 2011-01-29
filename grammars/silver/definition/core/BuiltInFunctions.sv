grammar silver:definition:core;

concrete production lengthFunction
top::Expr ::= 'length' '(' e::Expr ')'
{
  top.pp = "length(" ++ e.pp ++ ")";
  top.location = loc(top.file, $1.line, $2.column);

  production attribute handlers :: [Expr] with ++;
  handlers := if !unify(e.typerep, stringTypeExp()).failure
	      then [stringLength(e)]
	      else [];

  e.expected = expected_default();

  -- TODO: bug, the handler doesn't have the location available for error handling anymore!
  forwards to if null(handlers) then unknownLength(e) else head(handlers);
}

abstract production unknownLength
top::Expr ::= e::Decorated Expr
{
  top.location = e.location;
  top.errors := e.errors;
  top.typerep = errorType();
}

abstract production stringLength
top::Expr ::= e::Decorated Expr
{
  top.errors := e.errors;
  top.typerep = intTypeExp();
}

concrete production toIntFunction
top::Expr ::= 'toInt' '(' e::Expr ')'
{
  top.pp = "toInt(" ++ e.pp ++ ")";
  top.location = loc(top.file, $1.line, $2.column);

  top.errors := e.errors;
  top.typerep = intTypeExp();

  e.expected = expected_default();
}

concrete production toFloatFunction
top::Expr ::= 'toFloat' '(' e::Expr ')'
{
  top.pp = "toFloat(" ++ e.pp ++ ")";
  top.location = loc(top.file, $1.line, $2.column);

  top.errors := e.errors;
  top.typerep = floatTypeExp();

  e.expected = expected_default();
}

concrete production toStringFunction
top::Expr ::= 'toString' '(' e::Expr ')'
{
  top.pp = "toString(" ++ e.pp ++ ")";
  top.location = loc(top.file, $1.line, $2.column);

  top.errors := e.errors;
  top.typerep = stringTypeExp();

  e.expected = expected_default();
}

concrete production newFunction
top::Expr ::= 'new' '(' e::Expr ')'
{
  top.pp = "new(" ++ e.pp ++ ")";
  top.location = loc(top.file, $1.line, $2.column);

  top.errors := e.errors;
  top.typerep = e.typerep.decoratedType;

  e.expected = case top.expected of
                 expected_type(nonterminalTypeExp(f,p)) -> expected_type(decoratedTypeExp(nonterminalTypeExp(f,p)))
               | _ -> expected_decorated()
               end;
}

concrete production terminalFunction
top::Expr ::= 'terminal' '(' t::Type ',' e::Expr ')'
{
  top.pp = "terminal(" ++ t.pp ++ ", " ++ e.pp ++ ")";
  top.location = loc(top.file, $1.line, $2.column);

  top.errors := t.errors ++ e.errors;
  top.typerep = t.typerep;

  e.expected = expected_type(stringTypeExp());
}

concrete production terminalFunctionLineCol
top::Expr ::= 'terminal' '(' t::Type ',' e1::Expr ',' e2::Expr ',' e3::Expr ')'
{
  top.pp = "terminal(" ++ t.pp ++ ", " ++ e1.pp ++ ", " ++ e2.pp ++ ", " ++ e3.pp ++ ")";
  top.location = loc(top.file, $1.line, $2.column);

  top.errors := t.errors ++ e1.errors ++ e2.errors ++ e3.errors;
  top.typerep = t.typerep;

  e1.expected = expected_type(stringTypeExp());
  e2.expected = expected_type(intTypeExp());
  e3.expected = expected_type(intTypeExp());
}

concrete production terminalFunctionInherited
top::Expr ::= 'terminal' '(' t::Type ',' e1::Expr ',' e2::Expr ')'
{
  top.pp = "terminal(" ++ t.pp ++ ", " ++ e1.pp ++ "," ++ e2.pp ++ ")";
  top.location = loc(top.file, $1.line, $2.column);

  top.errors := t.errors ++ e1.errors ++ e2.errors;
  top.typerep = t.typerep;

  e1.expected = expected_type(stringTypeExp());
  e2.expected = expected_default();
}
