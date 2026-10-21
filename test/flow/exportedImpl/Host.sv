grammar flow:exportedImpl;

-- The grammar of a dispatch signature exports the grammar of an implementation, and applies the implementation by
-- name.  The implementation is checked against what this application in the host language supplies.
exports flow:exportedImpl:impl;

inherited attribute xiEnv::String;
inherited attribute xiCtx::String;
synthesized attribute xiTy::String;
nonterminal XIExpr with xiEnv, xiCtx, xiTy;
flowtype XIExpr = decorate {xiEnv, xiCtx}, forward {xiEnv, xiCtx}, xiTy {xiEnv, xiCtx};

dispatch XIOp = XIExpr ::= @a::XIExpr;

production xiLeaf
top::XIExpr ::=
{
  top.xiTy = top.xiEnv ++ top.xiCtx;
}

-- Supplies only xiEnv
production xiSite
top::XIExpr ::= x::XIExpr
{
  x.xiEnv = top.xiEnv;
  forwards to xiImpl(x);
}
