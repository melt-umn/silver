
concrete production do_c
top::Expr ::= 'do' '(' bindFn::QName ',' returnFn::QName ')' '{' body::DoBodyStmts '}'
{
  top.pp = s"do (${bindFn.pp}, ${returnFn.pp}) {${body.pp}}";

  -- TODO: quit reporting errors about undefined bind and lookup functions repeatedly
  top.errors <- bindFn.lookupValue.errors ++ returnFn.lookupValue.errors;
  --top.errors <- body.errors; 
  
  body.bindFn = bindFn;
  body.returnFn = returnFn;
  
  forwards to body.transform;
}

terminal DoId_t /[a-zA-Z][A-Za-z0-9\_]*/ lexer classes {IDENTIFIER};

autocopy attribute bindFn::QName;
autocopy attribute returnFn::QName;
synthesized attribute transform::Expr;

nonterminal DoBodyStmts with location, pp, transform, bindFn, returnFn;

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
      nominalType(
        qNameTypeId(terminal(IdUpper_t, "UnitT"), location=top.location),
        botlNone(location=top.location),
        location=top.location),
      '<-', h.transform, ';', t,
      location=top.location);
}

concrete production oneDoBodyStmt
top::DoBodyStmts ::= h::DoBodyStmt
{
  top.pp = s"${h.pp}";
  top.transform = h.transform;
}

nonterminal DoBodyStmt with location, pp, transform, bindFn, returnFn;

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
  top.transform =
    applicationExpr(
      baseExpr(top.returnFn, location=top.location), '(',
      oneAppExprs(presentAppExpr(e, location=top.location), location=top.location),
      ')',
      location=top.location);
}

concrete production condDoBody
top::DoBodyStmt ::= 'if' cond::Expr 'then' th::DoBodyStmt NoElse_t
{
  top.pp = s"if ${cond.pp} then ${th.pp}";
  forwards to
    condDoBodyElse(
      'if', cond,
      'then', th,
      'else',
      returnDoBody(
        'return',
        applicationEmpty(
          baseExpr(qName(top.location, "unit"), location=top.location), '(', ')',
          location=top.location),
        ';',
        location=top.location),
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