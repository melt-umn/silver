grammar silver:compiler:definition:core;

{--
 - The standard terminal constructor. This is *abstract* because 3-arg
 - syntax is ambiguous without type-based disambiguation with a legacy syntax.
 - See below in this file.
 -}
abstract production terminalConstructor
top::Expr ::= 'terminal' '(' t::TypeExpr ',' es::Expr ',' el::Expr ')'
{
  top.unparse = "terminal(" ++ t.unparse ++ ", " ++ es.unparse ++ ", " ++ el.unparse ++ ")";

  top.typerep = t.typerep;

  es.isRoot = false;
  el.isRoot = false;
}


--------------------------------------------------------------------------------
-- Deprecated variants of built-in functions

{--
 - Three-argument `terminal` is either:
 - `terminal(type,lexeme,location)` or a legacy syntax of
 - `terminal(type,lexeme,inherited_terminal)`.
 -
 - We should remove the "inherited terminal" variant, eventually.
 -}
concrete production terminalConstructorTemporaryDispatcher
top::Expr ::= 'terminal' '(' t::TypeExpr ',' es::Expr ',' el::Expr ')'
{
  top.unparse = "terminal(" ++ t.unparse ++ ", " ++ es.unparse ++ ", " ++ el.unparse ++ ")";

  -- A hack, so we can get `typerep`. Should be always fine, since explicitly stated
  -- types won't actually use `downSubst`.
  el.downSubst = emptySubst();

  forwards to
    if el.typerep.isTerminal
    then terminalFunctionInherited($1, $2, t, $4, es, $6, el, $8, location=top.location)
    else terminalConstructor($1, $2, t, $4, es, $6, el, $8, location=top.location);
}

concrete production terminalFunction
top::Expr ::= 'terminal' '(' t::TypeExpr ',' e::Expr ')'
{
  -- So, *maybe* this is deprecated? But let's not complain for now, because
  -- it's too widely used.

  --top.errors <- [wrn(t.location, "terminal(type,lexeme) is deprecated. Use terminal(type,lexeme,location) instead.")];

  local bogus :: Expr =
    mkStrFunctionInvocation($6.location, "silver:core:bogusLoc", []);

  forwards to terminalConstructor($1, $2, t, $4, e, ',', bogus, $6, location=top.location);
}

concrete production terminalFunctionLineCol
top::Expr ::= 'terminal' '(' t::TypeExpr ',' e1::Expr ',' e2::Expr ',' e3::Expr ')'
{
  top.errors <-
    [wrn(t.location, "terminal(type,lexeme,line,column) is deprecated. Use terminal(type,lexeme,location) instead.")];

  local bogus :: Expr =
    intConst(terminal(Int_t, "-1"), location=$10.location);

  forwards to terminalConstructor($1, $2, t, $4, e1, $6,
    mkStrFunctionInvocation($10.location, "silver:core:loc", [
      stringConst(terminal(String_t, "\"??\""), location=$10.location),
      e2, e3, bogus, bogus, bogus, bogus
    ]), $10, location=top.location);
}

{--
 - Legacy syntax should be removed, eventually.
 -}
abstract production terminalFunctionInherited
top::Expr ::= 'terminal' '(' t::TypeExpr ',' e1::Expr ',' e2::Expr ')'
{
  top.errors <-
    [wrn(t.location, "terminal(type,lexeme,terminal) is deprecated. Please just add '.location' on the terminal to use terminal(type,lexeme,location)")];

  local loc_access :: Expr =
    access(e2, '.', qNameAttrOccur(qName($8.location, "location"), location=top.location), location=top.location);

  forwards to terminalConstructor($1, $2, t, $4, e1, $6, loc_access, $8, location=top.location);
}

