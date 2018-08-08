
concrete production do_c
top::Expr ::= 'do' '(' bindFn::QName ',' returnFn::QName ')' '{' body::DoBodyStmts '}'
{
  top.unparse = s"do (${bindFn.unparse}, ${returnFn.unparse}) {${body.unparse}}";
  
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

nonterminal DoBodyStmts with location, unparse, transform, bindFn, returnFn, isFinalVal;

concrete production bindExprDoBodyStmts
top::DoBodyStmts ::= n::MName '::' t::TypeExpr '<-' e::Expr ';' rest::DoBodyStmts
{
  top.unparse = s"${n.unparse}::${t.unparse} <- ${e.unparse}; ${rest.unparse}";
  top.transform =
    bindExpr(nameFromMName(n), t, e, rest.transform, top.bindFn, location=top.location);
}

concrete production letExprDoBodyStmts
top::DoBodyStmts ::= n::MName '::' t::TypeExpr '=' e::Expr ';' rest::DoBodyStmts
{
  top.unparse = s"${n.unparse}::${t.unparse} = ${e.unparse}; ${rest.unparse}";
  
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
  top.unparse = s"${h.unparse} ${t.unparse}";
  forwards to
    bindExprDoBodyStmts(
      mName("_", top.location), '::',
      typerepTypeExpr(freshType(), location=top.location),
      '<-', h.transform, ';', t,
      location=top.location);
  
  h.isFinalVal = false;
}

concrete production oneDoBodyStmt
top::DoBodyStmts ::= h::DoBodyStmt
{
  top.unparse = s"${h.unparse}";
  top.transform = h.transform;
}

nonterminal DoBodyStmt with location, unparse, transform, bindFn, returnFn, isFinalVal;

concrete production doBodyBlock
top::DoBodyStmt ::= '{' body::DoBodyStmts '}'
{
  top.unparse = s"{${body.unparse}}";
  top.transform = body.transform;
}

concrete production exprDoBody
top::DoBodyStmt ::= e::Expr ';'
{
  top.unparse = s"${e.unparse};";
  --top.errors := e.errors;
  top.transform = e;
}

concrete production returnDoBody
top::DoBodyStmt ::= 'return' e::Expr ';'
{
  top.unparse = s"return ${e.unparse};";
  top.transform = returnExpr(e, top.isFinalVal, top.returnFn, location=top.location);
}

-- This could forward to returnDoBody, but not doing that so raising a warning on non-unit return
-- is simpler
abstract production returnUnitDoBody
top::DoBodyStmt ::= 
{
  top.unparse = s"return unit();";
  top.transform = returnUnitExpr(top.returnFn, location=top.location);
}

concrete production condDoBody
top::DoBodyStmt ::= 'if' cond::Expr 'then' th::DoBodyStmt NoElse_t
{
  top.unparse = s"if ${cond.unparse} then ${th.unparse}";
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
  top.unparse = s"if ${cond.unparse} then ${th.unparse} else ${el.unparse}";
  top.transform =
    ifThenElse(
      'if', cond,
      'then', th.transform,
      'else', el.transform,
      location=top.location);
}