grammar silver:compiler:extension:nanopass;

attribute includeTrans occurs on
  Expr, Exprs, ExprInhs, ExprInh, ExprLHSExpr, AppExprs, AppExpr, AnnoAppExprs, AnnoExpr;
propagate includeTrans on
  Expr, Exprs, ExprInhs, ExprInh, ExprLHSExpr, AppExprs, AppExpr, AnnoAppExprs
excluding
  errorReference, childReference, lhsReference, localReference, nondecLocalReference, forwardReference,
  lexicalLocalReference, lambdaParamReference, shortFunParamReference,
  productionReference, functionReference, classMemberReference, globalValueReference,
  actionChildReference, pluckTerminalReference, terminalIdReference, lexerClassReference, parserAttributeReference, termAttrValueReference,
  errorApplication, functionInvocation, partialApplication, dispatchApplication,
  annoUpdatePositionalErrorApplication, annoUpdateInvocation, annoUpdatePartialApplication,
  errorAccessHandler, terminalAccessHandler, synDecoratedAccessHandler, inhDecoratedAccessHandler,
  transDecoratedAccessHandler, annoAccessHandler, synDataAccessHandler, inhUndecoratedAccessErrorHandler,
  transUndecoratedAccessErrorHandler, unknownDclAccessHandler;

aspect includeTrans on top::Expr of
| errorReference(_, q) -> \ _ -> baseExpr(^q)
| childReference(q) -> \ _ -> baseExpr(^q)
| lhsReference(q) -> \ _ -> baseExpr(^q)
| localReference(q) -> \ _ -> baseExpr(^q)
| nondecLocalReference(q) -> \ _ -> baseExpr(^q)
| forwardReference(q) -> \ _ -> baseExpr(^q)
| lexicalLocalReference(q, _, _) -> \ _ -> baseExpr(^q)
| lambdaParamReference(q) -> \ _ -> baseExpr(^q)
| shortFunParamReference(q) -> \ _ -> baseExpr(^q)
| actionChildReference(q) -> \ _ -> baseExpr(^q)
| pluckTerminalReference(q) -> \ _ -> baseExpr(^q)
| productionReference(q) -> \ _ ->
  baseExpr(qName(qualifyIfDiffGrammar(top.grammarName, q.lookupValue.fullName)))
| functionReference(q) -> \ _ ->
  baseExpr(qName(qualifyIfDiffGrammar(top.grammarName, q.lookupValue.fullName)))
| classMemberReference(q) -> \ _ ->
  baseExpr(qName(qualifyIfDiffGrammar(top.grammarName, q.lookupValue.fullName)))
| globalValueReference(q) -> \ _ ->
  baseExpr(qName(qualifyIfDiffGrammar(top.grammarName, q.lookupValue.fullName)))
| terminalIdReference(q) -> \ _ ->
  baseExpr(qName(qualifyIfDiffGrammar(top.grammarName, q.lookupValue.fullName)))
| lexerClassReference(q) -> \ _ ->
  baseExpr(qName(qualifyIfDiffGrammar(top.grammarName, q.lookupValue.fullName)))
| parserAttributeReference(q) -> \ _ ->
  baseExpr(qName(qualifyIfDiffGrammar(top.grammarName, q.lookupValue.fullName)))
| termAttrValueReference(q) -> \ _ ->
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
| terminalAccessHandler(e, q) -> \ tr::Decorated TransformStmts ->
  access(e.includeTrans(tr), '.', q.includeTrans(tr))
| synDecoratedAccessHandler(e, q) -> \ tr::Decorated TransformStmts ->
  access(e.includeTrans(tr), '.', q.includeTrans(tr))
| inhDecoratedAccessHandler(e, q) -> \ tr::Decorated TransformStmts ->
  access(e.includeTrans(tr), '.', q.includeTrans(tr))
| transDecoratedAccessHandler(e, q) -> \ tr::Decorated TransformStmts ->
  access(e.includeTrans(tr), '.', q.includeTrans(tr))
| annoAccessHandler(e, q) -> \ tr::Decorated TransformStmts ->
  access(e.includeTrans(tr), '.', q.includeTrans(tr))
| synDataAccessHandler(e, q) -> \ tr::Decorated TransformStmts ->
  access(e.includeTrans(tr), '.', q.includeTrans(tr))
| inhUndecoratedAccessErrorHandler(e, q) -> \ tr::Decorated TransformStmts ->
  access(e.includeTrans(tr), '.', q.includeTrans(tr))
| transUndecoratedAccessErrorHandler(e, q) -> \ tr::Decorated TransformStmts ->
  access(e.includeTrans(tr), '.', q.includeTrans(tr))
| unknownDclAccessHandler(e, q) -> \ tr::Decorated TransformStmts ->
  access(e.includeTrans(tr), '.', q.includeTrans(tr))
end;

aspect production annoExpr
top::AnnoExpr ::= qn::QName '=' e::AppExpr
{
  top.includeTrans = \ tr::Decorated TransformStmts -> annoExpr(
    qName(qualifyIfDiffGrammar(top.grammarName, qn.lookupAttribute.fullName)),
    '=', e.includeTrans(tr));
}
