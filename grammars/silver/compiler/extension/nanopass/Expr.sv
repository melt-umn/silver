grammar silver:compiler:extension:nanopass;

attribute includeTrans occurs on
  Expr, Exprs, ExprInhs, ExprInh, ExprLHSExpr, AppExprs, AppExpr, AnnoAppExprs, AnnoExpr;
propagate includeTrans on
  Expr, Exprs, ExprInhs, ExprInh, ExprLHSExpr, AppExprs, AppExpr, AnnoAppExprs
excluding
  childReference, lhsReference, localReference, forwardReference,
  productionReference, functionReference, classMemberReference, globalValueReference,
  errorApplication, functionInvocation, partialApplication, dispatchApplication,
  annoUpdatePositionalErrorApplication, annoUpdateInvocation, annoUpdatePartialApplication,
  errorAccessHandler, terminalAccessHandler, synDecoratedAccessHandler, inhDecoratedAccessHandler,
  transDecoratedAccessHandler, annoAccessHandler, synDataAccessHandler, inhUndecoratedAccessErrorHandler,
  transUndecoratedAccessErrorHandler, unknownDclAccessHandler;

aspect includeTrans on top::Expr of
| childReference(q) -> \ _ -> baseExpr(^q)
| lhsReference(q) -> \ _ -> baseExpr(^q)
| localReference(q) -> \ _ -> baseExpr(^q)
| forwardReference(q) -> \ _ -> baseExpr(^q)
| productionReference(q) -> \ _ ->
  baseExpr(qName(qualifyIfDiffGrammar(top.grammarName, q.lookupValue.fullName)))
| functionReference(q) -> \ _ ->
  baseExpr(qName(qualifyIfDiffGrammar(top.grammarName, q.lookupValue.fullName)))
| classMemberReference(q) -> \ _ ->
  baseExpr(qName(qualifyIfDiffGrammar(top.grammarName, q.lookupValue.fullName)))
| globalValueReference(q) -> \ _ ->
  baseExpr(qName(qualifyIfDiffGrammar(top.grammarName, q.lookupValue.fullName)))
| errorApplication(e, es, anns) -> \ tr::Decorated TransformStmts ->
  application(e.includeTrans(tr), '(', es.includeTrans(tr), ',', anns.includeTrans(tr), ')')
| functionInvocation(e, es, anns) -> \ tr::Decorated TransformStmts ->
  application(e.includeTrans(tr), '(', es.includeTrans(tr), ',', anns.includeTrans(tr), ')')
| partialApplication(e, es, anns) -> \ tr::Decorated TransformStmts ->
  application(e.includeTrans(tr), '(', es.includeTrans(tr), ',', anns.includeTrans(tr), ')')
| dispatchApplication(e, es, anns) -> \ tr::Decorated TransformStmts ->
  application(e.includeTrans(tr), '(', es.includeTrans(tr), ',', anns.includeTrans(tr), ')')
| annoUpdatePositionalErrorApplication(e, es, anns) -> \ tr::Decorated TransformStmts ->
  application(e.includeTrans(tr), '(', es.includeTrans(tr), ',', anns.includeTrans(tr), ')')
| annoUpdateInvocation(e, es, anns) -> \ tr::Decorated TransformStmts ->
  application(e.includeTrans(tr), '(', es.includeTrans(tr), ',', anns.includeTrans(tr), ')')
| annoUpdatePartialApplication(e, es, anns) -> \ tr::Decorated TransformStmts ->
  application(e.includeTrans(tr), '(', es.includeTrans(tr), ',', anns.includeTrans(tr), ')')
| errorAccessHandler(e, q) -> \ tr::Decorated TransformStmts ->
  access(e.includeTrans(tr), '.', q.includeTrans(tr))
end;

aspect production annoExpr
top::AnnoExpr ::= qn::QName '=' e::AppExpr
{
  top.includeTrans = \ tr::Decorated TransformStmts -> annoExpr(
    qName(qualifyIfDiffGrammar(top.grammarName, qn.lookupAttribute.fullName)),
    '=', e.includeTrans(tr));
}

-- TODO: Annotated attributes
