grammar silver:definition:core;

{--
 - Context for ProductionStmt blocks. (Function, production, other...)
 -}
autocopy attribute blockContext :: BlockContext occurs on ProductionBody, ProductionStmts, ProductionStmt, ForwardInhs, ForwardInh, DefLHS, -- ProductionBody.sv
                                                          Expr, Exprs, ExprInh, ExprInhs; -- Expr.sv


nonterminal BlockContext with permitReturn, permitForward, permitProductionAttributes, lazyApplication;

synthesized attribute permitReturn :: Boolean;
synthesized attribute permitForward :: Boolean;
synthesized attribute permitProductionAttributes :: Boolean;
synthesized attribute lazyApplication :: Boolean;

abstract production defaultContext
top::BlockContext ::=
{
  top.permitReturn = false;
  top.permitForward = false;
  top.permitProductionAttributes = true;
  top.lazyApplication = true;
}

abstract production functionContext
top::BlockContext ::=
{
  top.permitReturn = true;
  forwards to defaultContext();
}

abstract production productionContext
top::BlockContext ::=
{
  top.permitForward = true;
  forwards to defaultContext();
}

