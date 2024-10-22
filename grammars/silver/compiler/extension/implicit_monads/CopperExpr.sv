grammar silver:compiler:extension:implicit_monads;

{-
  I honestly have no idea if these can occur in a production.  I kind of doubt
  it with them being Copper expressions.
-}

aspect production actionChildReference
top::Expr ::= @q::QName
{
  top.merrors := [];
  top.mUpSubst = top.mDownSubst;
  top.mtyperep = q.lookupValue.typeScheme.monoType;
  top.monadicNames = if top.monadicallyUsed
                     then [baseExpr(^q)]
                     else [];
  top.monadRewritten = baseExpr(^q);
}

aspect production pluckTerminalReference
top::Expr ::= @q::QName
{
  top.merrors := [];
  top.mUpSubst = top.mDownSubst;
  top.mtyperep = terminalIdType();
  top.monadicNames = if top.monadicallyUsed
                     then [baseExpr(^q)]
                     else [];
  top.monadRewritten = baseExpr(^q);
}

aspect production terminalIdReference
top::Expr ::= @q::QName
{
  top.merrors := [];
  top.mUpSubst = top.mDownSubst;
  top.mtyperep = terminalIdType();
  top.monadicNames = if top.monadicallyUsed
                     then [baseExpr(^q)]
                     else [];
  top.monadRewritten = baseExpr(^q);
}

aspect production parserAttributeReference
top::Expr ::= @q::QName
{
  top.merrors := [];
  top.mUpSubst = top.mDownSubst;
  top.mtyperep = q.lookupValue.typeScheme.monoType;
  top.monadicNames = if top.monadicallyUsed
                     then [baseExpr(^q)]
                     else [];
  top.monadRewritten = baseExpr(^q);
}

aspect production termAttrValueReference
top::Expr ::= @q::QName
{
  top.merrors := [];
  top.mUpSubst = top.mDownSubst;
  top.mtyperep = q.lookupValue.typeScheme.monoType;
  top.monadicNames = if top.monadicallyUsed
                     then [baseExpr(^q)]
                     else [];
  top.monadRewritten = baseExpr(^q);
}
