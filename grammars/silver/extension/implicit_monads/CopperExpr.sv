grammar silver:extension:implicit_monads;

{-
  I honestly have no idea if these can occur in a production.  I kind of doubt
  it with them being Copper expressions.
-}

aspect production actionChildReference
top::Expr ::= q::Decorated QName
{
  top.merrors := [];
  top.mUpSubst = top.mDownSubst;
  top.mtyperep = q.lookupValue.typerep;
  top.monadicNames = if top.monadicallyUsed
                     then [baseExpr(new(q), location=top.location)]
                     else [];
  top.monadRewritten = baseExpr(new(q), location=top.location);
}

aspect production pluckTerminalReference
top::Expr ::= q::Decorated QName
{
  top.merrors := [];
  top.mUpSubst = top.mDownSubst;
  top.mtyperep = terminalIdType();
  top.monadicNames = if top.monadicallyUsed
                     then [baseExpr(new(q), location=top.location)]
                     else [];
  top.monadRewritten = baseExpr(new(q), location=top.location);
}

aspect production terminalIdReference
top::Expr ::= q::Decorated QName
{
  top.merrors := [];
  top.mUpSubst = top.mDownSubst;
  top.mtyperep = terminalIdType();
  top.monadicNames = if top.monadicallyUsed
                     then [baseExpr(new(q), location=top.location)]
                     else [];
  top.monadRewritten = baseExpr(new(q), location=top.location);
}

aspect production parserAttributeReference
top::Expr ::= q::Decorated QName
{
  top.merrors := [];
  top.mUpSubst = top.mDownSubst;
  top.mtyperep = q.lookupValue.typerep;
  top.monadicNames = if top.monadicallyUsed
                     then [baseExpr(new(q), location=top.location)]
                     else [];
  top.monadRewritten = baseExpr(new(q), location=top.location);
}

aspect production termAttrValueReference
top::Expr ::= q::Decorated QName
{
  top.merrors := [];
  top.mUpSubst = top.mDownSubst;
  top.mtyperep = q.lookupValue.typerep;
  top.monadicNames = if top.monadicallyUsed
                     then [baseExpr(new(q), location=top.location)]
                     else [];
  --this is just a Silver name (e.g. lexeme), not a user-defined name,
  --   so it should be fine to leave it decorated
  top.monadRewritten = termAttrValueReference(q, location=top.location);
}
