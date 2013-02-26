grammar silver:definition:core;

--import silver:analysis:typechecking:core;

concrete production lengthFunction
top::Expr ::= 'length' '(' e::Expr ')'
{
  top.pp = "length(" ++ e.pp ++ ")";
  top.location = $1.location;

  top.typerep = intTypeExp();
  
  top.errors := e.errors ++ forward.errors;

  forwards to performSubstitution(e.typerep, e.upSubst).lengthDispatcher(e);
}

abstract production errorLength
top::Expr ::= e::Decorated Expr
{
  top.pp = "length(" ++ e.pp ++ ")";
  top.location = e.location;

  top.typerep = intTypeExp();

  top.errors := [err(e.location, "Operand to length is not compatible. It is of type " ++ prettyType(performSubstitution(e.typerep, top.finalSubst)))];
}

abstract production stringLength
top::Expr ::= e::Decorated Expr
{
  top.pp = "length(" ++ e.pp ++ ")";
  top.location = e.location;

  top.typerep = intTypeExp();

  top.errors := [];
}

concrete production toIntFunction
top::Expr ::= 'toInt' '(' e::Expr ')'
{
  top.pp = "toInt(" ++ e.pp ++ ")";
  top.location = $1.location;

  top.errors := e.errors;
  top.typerep = intTypeExp();
}

concrete production toFloatFunction
top::Expr ::= 'toFloat' '(' e::Expr ')'
{
  top.pp = "toFloat(" ++ e.pp ++ ")";
  top.location = $1.location;

  top.errors := e.errors;
  top.typerep = floatTypeExp();
}

concrete production toStringFunction
top::Expr ::= 'toString' '(' e::Expr ')'
{
  top.pp = "toString(" ++ e.pp ++ ")";
  top.location = $1.location;

  top.errors := e.errors;
  top.typerep = stringTypeExp();
}

concrete production newFunction
top::Expr ::= 'new' '(' e::Expr ')'
{
  top.pp = "new(" ++ e.pp ++ ")";
  top.location = $1.location;

  top.errors := e.errors;
  top.typerep = performSubstitution(e.typerep, top.upSubst).decoratedType;
}

abstract production terminalConstructor
top::Expr ::= 'terminal' '(' t::Type ',' es::Expr ',' el::Expr ')'
{
  top.pp = "terminal(" ++ t.pp ++ ", " ++ es.pp ++ ", " ++ el.pp ++ ")";
  top.location = $1.location;

  top.errors := t.errors ++ es.errors ++ el.errors;
  top.typerep = t.typerep;
}

-- OLD DEPRECATED VERSIONS

concrete production terminalConstructorTemporaryDispatcher
top::Expr ::= 'terminal' '(' t::Type ',' es::Expr ',' el::Expr ')'
{
  -- This is a temporary compatibility hack. It's really nasty. Remove as soon as possible. TODO
  
  -- We're being stupidly simple here.
  el.downSubst = emptySubst();
  
  forwards to
    if el.typerep.isTerminal
    then terminalFunctionInherited($1, $2, t, $4, es, $6, el, $8)
    else terminalConstructor($1, $2, t, $4, es, $6, el, $8);
}


concrete production terminalFunction
top::Expr ::= 'terminal' '(' t::Type ',' e::Expr ')'
{
  --temporarily, don't complain. Yet.
  --top.errors <- [wrn(t.location, "terminal(type,lexeme) is deprecated. Use terminal(type,lexeme,location) instead.")];
  forwards to terminalConstructor($1, $2, t, $4, e, ',',
    applicationExpr(baseExpr(qName(forward.location, "core:loc")), '(',
      foldl(snocAppExprs(_, ',', _), emptyAppExprs(forward.location), map(presentAppExpr, [
        stringConst(terminal(String_t, "\"??\"")),
        intConst(terminal(Int_t, "-1")),
        intConst(terminal(Int_t, "-1")),
        intConst(terminal(Int_t, "-1")),
        intConst(terminal(Int_t, "-1")),
        intConst(terminal(Int_t, "-1")),
        intConst(terminal(Int_t, "-1"))
      ])), ')'), $6);
}

concrete production terminalFunctionLineCol
top::Expr ::= 'terminal' '(' t::Type ',' e1::Expr ',' e2::Expr ',' e3::Expr ')'
{
  --temporarily, don't complain. Yet.
  --top.errors <- [wrn(t.location, "terminal(type,lexeme) is deprecated. Use terminal(type,lexeme,location) instead.")];
  forwards to terminalConstructor($1, $2, t, $4, e1, $6,
    applicationExpr(baseExpr(qName(forward.location, "core:loc")), '(',
      foldl(snocAppExprs(_, ',', _), emptyAppExprs(forward.location), map(presentAppExpr, [
        stringConst(terminal(String_t, "\"??\"")),
        e2,
        e3,
        intConst(terminal(Int_t, "-1")),
        intConst(terminal(Int_t, "-1")),
        intConst(terminal(Int_t, "-1")),
        intConst(terminal(Int_t, "-1"))
      ])), ')'), $10);
}

abstract production terminalFunctionInherited
top::Expr ::= 'terminal' '(' t::Type ',' e1::Expr ',' e2::Expr ')'
{
  top.errors <- [wrn(t.location, "terminal(type,lexeme,terminal) is deprecated. Please just add '.location' on the terminal to use terminal(type,lexeme,location)")];
  forwards to terminalConstructor($1, $2, t, $4, e1, $6, access(e2, '.', qNameAttrOccur(qName(forward.location, "location"))), $8);
}

