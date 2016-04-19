
concrete production do_c
top::Expr ::= 'do' '(' bindFn::QName ',' returnFn::QName ')' '{' body::DoBodyStmts '}'
{
  top.pp = s"do (${bindFn.pp}, ${returnFn.pp}) {${body.pp}}";
  
  local bindLookup::Decorated QNameLookup = bindFn.lookupValue;
  local returnLookup::Decorated QNameLookup = returnFn.lookupValue;

  -- TODO: Raise errors once if bind/return have the wrong types
  -- We may need typeclasses for this to be possible?  
  local localErrors::[Message] = bindLookup.errors ++ returnLookup.errors;
  
  body.bindFn = bindFn;
  body.returnFn = returnFn;
  body.isFinalVal = true;
  
  forwards to
    if !null(localErrors)
    then errorExpr(localErrors, location=top.location)
    else body.transform;
}

autocopy attribute bindFn::QName;
autocopy attribute returnFn::QName;

autocopy attribute isFinalVal::Boolean;

synthesized attribute transform::Expr;

nonterminal DoBodyStmts with location, pp, transform, bindFn, returnFn, isFinalVal;

concrete production bindExprDoBodyStmts
top::DoBodyStmts ::= n::MName '::' t::Type '<-' e::Expr ';' rest::DoBodyStmts
{
  top.pp = s"${n.pp}::${t.pp} <- ${e.pp}; ${rest.pp}";
  top.transform =
    bindExpr(nameFromMName(n), t, e, rest.transform, top.bindFn, location=top.location);
}

concrete production letExprDoBodyStmts
top::DoBodyStmts ::= n::MName '::' t::Type '=' e::Expr ';' rest::DoBodyStmts
{
  top.pp = s"${n.pp}::${t.pp} = ${e.pp}; ${rest.pp}";
  
  -- TODO: move this to AbstractSyntax for consistancy?
  top.transform =
    letp(
      assignExpr(nameFromMName(n), '::', t, '=', e, location=top.location),
      rest.transform,
    location=top.location);
}

concrete production consDoBodyStmt
top::DoBodyStmts ::= h::DoBodyStmt t::DoBodyStmts
{
  top.pp = s"${h.pp} ${t.pp}";
  forwards to
    bindExprDoBodyStmts(
      mName("_", top.location), '::',
      typerepType(freshType(), location=top.location),
      '<-', h.transform, ';', t,
      location=top.location);
  
  h.isFinalVal = false;
}

concrete production oneDoBodyStmt
top::DoBodyStmts ::= h::DoBodyStmt
{
  top.pp = s"${h.pp}";
  top.transform = h.transform;
}

nonterminal DoBodyStmt with location, pp, transform, bindFn, returnFn, isFinalVal;

concrete production doBodyBlock
top::DoBodyStmt ::= '{' body::DoBodyStmts '}'
{
  top.pp = s"{${body.pp}}";
  top.transform = body.transform;
}

concrete production exprDoBody
top::DoBodyStmt ::= e::Expr ';'
{
  top.pp = s"${e.pp};";
  --top.errors := e.errors;
  top.transform = e;
}

concrete production returnDoBody
top::DoBodyStmt ::= 'return' e::Expr ';'
{
  top.pp = s"return ${e.pp};";
  top.transform = returnExpr(e, top.isFinalVal, top.returnFn, location=top.location);
}

-- This could forward to returnDoBody, but not doing that so raising a warning on non-unit return
-- is simpler
abstract production returnUnitDoBody
top::DoBodyStmt ::= 
{
  top.pp = s"return unit();";
  top.transform = returnUnitExpr(top.returnFn, location=top.location);
}

concrete production condDoBody
top::DoBodyStmt ::= 'if' cond::Expr 'then' th::DoBodyStmt NoElse_t
{
  top.pp = s"if ${cond.pp} then ${th.pp}";
  forwards to
    condDoBodyElse(
      'if', cond,
      'then', th,
      'else', returnUnitDoBody(location=top.location),
      location=top.location);
}

concrete production condDoBodyElse
top::DoBodyStmt ::= 'if' cond::Expr 'then' th::DoBodyStmt 'else' el::DoBodyStmt
{
  top.pp = s"if ${cond.pp} then ${th.pp} else ${el.pp}";
  top.transform =
    ifThenElse(
      'if', cond,
      'then', th.transform,
      'else', el.transform,
      location=top.location);
}