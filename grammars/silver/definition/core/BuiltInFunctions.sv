grammar silver:definition:core;

--import silver:analysis:typechecking:core;

concrete production lengthFunction
top::Expr ::= 'length' '(' e::Expr ')'
{
  top.pp = "length(" ++ e.pp ++ ")";

  top.typerep = intTypeExp();
  
  top.errors := e.errors ++ forward.errors;

  forwards to performSubstitution(e.typerep, e.upSubst).lengthDispatcher(e, top.location);
}

abstract production errorLength
top::Expr ::= e::Decorated Expr
{
  top.pp = "length(" ++ e.pp ++ ")";

  top.typerep = intTypeExp();

  top.errors := [err(e.location, "Operand to length is not compatible. It is of type " ++ prettyType(performSubstitution(e.typerep, top.finalSubst)))];
}

abstract production stringLength
top::Expr ::= e::Decorated Expr
{
  top.pp = "length(" ++ e.pp ++ ")";

  top.typerep = intTypeExp();

  top.errors := [];
}

concrete production toIntFunction
top::Expr ::= 'toInt' '(' e::Expr ')'
{
  top.pp = "toInt(" ++ e.pp ++ ")";

  top.errors := e.errors;
  top.typerep = intTypeExp();
}

concrete production toFloatFunction
top::Expr ::= 'toFloat' '(' e::Expr ')'
{
  top.pp = "toFloat(" ++ e.pp ++ ")";

  top.errors := e.errors;
  top.typerep = floatTypeExp();
}

concrete production toStringFunction
top::Expr ::= 'toString' '(' e::Expr ')'
{
  top.pp = "toString(" ++ e.pp ++ ")";

  top.errors := e.errors;
  top.typerep = stringTypeExp();
}

concrete production newFunction
top::Expr ::= 'new' '(' e::Expr ')'
{
  top.pp = "new(" ++ e.pp ++ ")";

  top.errors := e.errors;
  top.typerep = performSubstitution(e.typerep, top.upSubst).decoratedType;
}

abstract production terminalConstructor
top::Expr ::= 'terminal' '(' t::Type ',' es::Expr ',' el::Expr ')'
{
  top.pp = "terminal(" ++ t.pp ++ ", " ++ es.pp ++ ", " ++ el.pp ++ ")";

  top.errors := t.errors ++ es.errors ++ el.errors;
  top.typerep = t.typerep;
}

-- OLD DEPRECATED VERSIONS

concrete production terminalConstructorTemporaryDispatcher
top::Expr ::= 'terminal' '(' t::Type ',' es::Expr ',' el::Expr ')'
{
  top.pp = "terminal(" ++ t.pp ++ ", " ++ es.pp ++ ", " ++ el.pp ++ ")";
  -- This is a temporary compatibility hack. It's really nasty. Remove as soon as possible. TODO
  
  -- We're being stupidly simple here.
  el.downSubst = emptySubst();
  
  forwards to
    if el.typerep.isTerminal
    then terminalFunctionInherited($1, $2, t, $4, es, $6, el, $8, location=top.location)
    else terminalConstructor($1, $2, t, $4, es, $6, el, $8, location=top.location);
}


concrete production terminalFunction
top::Expr ::= 'terminal' '(' t::Type ',' e::Expr ')'
{
  -- let's temporarily not say anything about this one...
  --top.errors <- [wrn(t.location, "terminal(type,lexeme) is deprecated. Use terminal(type,lexeme,location) instead.")];
  forwards to terminalConstructor($1, $2, t, $4, e, ',',
    mkStrFunctionInvocation($6.location, "core:loc", [
      stringConst(terminal(String_t, "\"??\""), location=$6.location),
      intConst(terminal(Int_t, "-1"), location=$6.location),
      intConst(terminal(Int_t, "-1"), location=$6.location),
      intConst(terminal(Int_t, "-1"), location=$6.location),
      intConst(terminal(Int_t, "-1"), location=$6.location),
      intConst(terminal(Int_t, "-1"), location=$6.location),
      intConst(terminal(Int_t, "-1"), location=$6.location)
    ]), $6, location=top.location);
}

concrete production terminalFunctionLineCol
top::Expr ::= 'terminal' '(' t::Type ',' e1::Expr ',' e2::Expr ',' e3::Expr ')'
{
  top.errors <- [wrn(t.location, "terminal(type,lexeme,line,column) is deprecated. Use terminal(type,lexeme,location) instead.")];
  forwards to terminalConstructor($1, $2, t, $4, e1, $6,
    mkStrFunctionInvocation($10.location, "core:loc", [
      stringConst(terminal(String_t, "\"??\""), location=$10.location),
      e2,
      e3,
      intConst(terminal(Int_t, "-1"), location=$10.location),
      intConst(terminal(Int_t, "-1"), location=$10.location),
      intConst(terminal(Int_t, "-1"), location=$10.location),
      intConst(terminal(Int_t, "-1"), location=$10.location)
    ]), $10, location=top.location);
}

abstract production terminalFunctionInherited
top::Expr ::= 'terminal' '(' t::Type ',' e1::Expr ',' e2::Expr ')'
{
  top.errors <- [wrn(t.location, "terminal(type,lexeme,terminal) is deprecated. Please just add '.location' on the terminal to use terminal(type,lexeme,location)")];
  forwards to terminalConstructor($1, $2, t, $4, e1, $6, access(e2, '.', qNameAttrOccur(qName(forward.location, "location"), location=top.location), location=top.location), $8, location=top.location);
}

