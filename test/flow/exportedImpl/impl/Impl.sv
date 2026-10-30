grammar flow:exportedImpl:impl;

imports flow:exportedImpl;

warnCode "Access of synthesized attribute xiTy on a requires missing inherited attribute(s) flow:exportedImpl:xiCtx" {
production xiImpl implements XIOp
top::XIExpr ::= @a::XIExpr
{
  top.xiTy = a.xiTy;
}
}
