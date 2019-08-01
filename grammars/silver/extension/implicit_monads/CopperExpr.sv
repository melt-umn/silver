grammar silver:extension:implicit_monads;

aspect production actionChildReference
top::Expr ::= q::Decorated QName
{
  top.merrors := [];
  top.mtyperep = q.lookupValue.typerep;
  top.monadRewritten = baseExpr(new(q), location=top.location);
}

aspect production pluckTerminalReference
top::Expr ::= q::Decorated QName
{
  top.merrors := [];
  top.mtyperep = terminalIdType();
  top.monadRewritten = baseExpr(new(q), location=top.location);
}

aspect production terminalIdReference
top::Expr ::= q::Decorated QName
{
  top.merrors := [];
  top.mtyperep = terminalIdType();
  top.monadRewritten = baseExpr(new(q), location=top.location);
}

aspect production parserAttributeReference
top::Expr ::= q::Decorated QName
{
  top.merrors := [];
  top.mtyperep = q.lookupValue.typerep;
  top.monadRewritten = baseExpr(new(q), location=top.location);
}

aspect production termAttrValueReference
top::Expr ::= q::Decorated QName
{
  top.merrors := [];
  top.mtyperep = q.lookupValue.typerep;
  --this is just a Silver name (e.g. lexeme), not a user-defined name,
  --   so it should be fine to leave it decorated
  top.monadRewritten = termAttrValueReference(q, location=top.location);
}
