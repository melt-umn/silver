grammar silver:compiler:extension:nanopass;

attribute includeTrans occurs on
  Expr, Exprs, ExprInhs, ExprInh, ExprLHSExpr, AppExprs, AppExpr, AnnoAppExprs, AnnoExpr,
  LambdaRHS, LambdaRHSElem, AssignExpr, PrimPatterns, PrimPattern;
propagate includeTrans on
  Expr, Exprs, ExprInhs, ExprInh, ExprLHSExpr, AppExprs, AppExpr, AnnoAppExprs,
  LambdaRHS, LambdaRHSElem, AssignExpr, PrimPatterns, PrimPattern
excluding
  errorReference, childReference, lhsReference, localReference, nondecLocalReference, forwardReference,
  lexicalLocalReference, lambdaParamReference, shortFunParamReference,
  productionReference, functionReference, classMemberReference, globalValueReference,
  actionChildReference, pluckTerminalReference, terminalIdReference, lexerClassReference, parserAttributeReference, termAttrValueReference,
  errorApplication, functionInvocation, partialApplication, dispatchApplication,
  annoUpdatePositionalErrorApplication, annoUpdateInvocation, annoUpdatePartialApplication,
  errorAccessHandler, terminalAccessHandler, synDecoratedAccessHandler, inhDecoratedAccessHandler,
  transDecoratedAccessHandler, annoAccessHandler, synDataAccessHandler, inhUndecoratedAccessErrorHandler,
  transUndecoratedAccessErrorHandler, unknownDclAccessHandler,
  prodPatternNormal, prodPatternGadt;

-- All these dispatch impl prods get translated back to the dispatching prod:
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
  baseExpr(qName(unqualifyIfSameGrammar(top.grammarName, q.lookupValue.fullName)))
| functionReference(q) -> \ _ ->
  baseExpr(qName(unqualifyIfSameGrammar(top.grammarName, q.lookupValue.fullName)))
| classMemberReference(q) -> \ _ ->
  baseExpr(qName(unqualifyIfSameGrammar(top.grammarName, q.lookupValue.fullName)))
| globalValueReference(q) -> \ _ ->
  baseExpr(qName(unqualifyIfSameGrammar(top.grammarName, q.lookupValue.fullName)))
| terminalIdReference(q) -> \ _ ->
  baseExpr(qName(unqualifyIfSameGrammar(top.grammarName, q.lookupValue.fullName)))
| lexerClassReference(q) -> \ _ ->
  baseExpr(qName(unqualifyIfSameGrammar(top.grammarName, q.lookupValue.fullName)))
| parserAttributeReference(q) -> \ _ ->
  baseExpr(qName(unqualifyIfSameGrammar(top.grammarName, q.lookupValue.fullName)))
| termAttrValueReference(q) -> \ _ ->
  baseExpr(qName(unqualifyIfSameGrammar(top.grammarName, q.lookupValue.fullName)))
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
    ^qn, -- This is a QName, but it actually should be unqualified!
    '=', e.includeTrans(tr));
}

-- TODO: Translation on prim pattern extension means no case completeness check in transformed AST
aspect production prodPatternNormal
top::PrimPattern ::= @qn::QName @ns::VarBinders @e::Expr
{
  top.includeTrans = \ tr::Decorated TransformStmts ->
    prodPattern(
      qName(unqualifyIfSameGrammar(top.grammarName, qn.lookupValue.fullName)),
      '(', ^ns, ')', '->', e.includeTrans(tr));
}
aspect production prodPatternGadt
top::PrimPattern ::= @qn::QName @ns::VarBinders @e::Expr
{
  top.includeTrans = \ tr::Decorated TransformStmts ->
    prodPattern(
      qName(unqualifyIfSameGrammar(top.grammarName, qn.lookupValue.fullName)),
      '(', ^ns, ')', '->', e.includeTrans(tr));
}

monoid attribute mentionedAttrs::[String] occurs on
  Expr, Exprs, ExprInhs, ExprInh, ExprLHSExpr, AppExprs, AppExpr, AnnoAppExprs, AnnoExpr,
  LambdaRHS, LambdaRHSElem, AssignExpr, PrimPatterns, PrimPattern, QNameAttrOccur;
propagate mentionedAttrs on
  Expr, Exprs, ExprInhs, ExprInh, ExprLHSExpr, AppExprs, AppExpr, AnnoAppExprs, AnnoExpr,
  LambdaRHS, LambdaRHSElem, AssignExpr, PrimPatterns, PrimPattern;

aspect mentionedAttrs on top::QNameAttrOccur using := of
| qNameAttrOccur(at) -> if top.found && !top.attrDcl.isAnnotation then [top.attrDcl.fullName] else []
end;
