grammar silver:compiler:extension:nanopass;

attribute isIncluded occurs on TypeExpr;
aspect isIncluded on top::TypeExpr of
| nominalTypeExpr(n) -> isTypeIncluded(_, n.lookupType.fullName)
| aliasAppTypeExpr(n, _) -> isTypeIncluded(_, n.lookupType.fullName)
| typeAppTypeExpr(ty, _) -> ty.isIncluded
| refTypeExpr(_, ty, _, _) -> ty.isIncluded
| refDefaultTypeExpr(_, ty) -> ty.isIncluded
| _ -> tt
end;

attribute includeTrans occurs on
  TypeExpr, Signature, SignatureLHS, TypeExprs, BracketedTypeExprs, BracketedOptTypeExprs, NamedTypeExprs,
  ConstraintList, Constraint;
propagate includeTrans on
  TypeExpr, Signature, SignatureLHS, TypeExprs, BracketedTypeExprs, BracketedOptTypeExprs, NamedTypeExprs,
  ConstraintList, Constraint
  excluding nominalTypeExpr, aliasAppTypeExpr;
aspect includeTrans on top::TypeExpr of
| nominalTypeExpr(n) -> \ tr::Decorated TransformStmts ->
  nominalTypeExpr(qName(unqualifyIfSameGrammar(top.grammarName, n.lookupType.fullName)).qNameType)
| aliasAppTypeExpr(n, tl) -> \ tr::Decorated TransformStmts ->
  appTypeExpr(
    nominalTypeExpr(qName(unqualifyIfSameGrammar(top.grammarName, n.lookupType.fullName)).qNameType),
    tl.includeTrans(tr))
end;

-- Replicate the type expression, fully qualifying names to refer to the same type as in the same pass.
functor attribute prevTypeTrans occurs on
  TypeExpr, Signature, SignatureLHS, TypeExprs, BracketedTypeExprs, BracketedOptTypeExprs, NamedTypeExprs;
propagate prevTypeTrans on
  TypeExpr, Signature, SignatureLHS, TypeExprs, BracketedTypeExprs, BracketedOptTypeExprs, NamedTypeExprs
  excluding nominalTypeExpr, aliasAppTypeExpr;

aspect prevTypeTrans on top::TypeExpr of
| nominalTypeExpr(n) ->
  nominalTypeExpr(qName(n.lookupType.fullName).qNameType)
| aliasAppTypeExpr(n, tl) ->
  appTypeExpr(nominalTypeExpr(qName(n.lookupType.fullName).qNameType), tl.prevTypeTrans)
end;

-- Annoyingly, Type appears in the AST in few places.
-- It would be nice to get rid of all this:
attribute includeTrans occurs on
  Type, Contexts, Context, NamedSignatureElements, NamedSignatureElement, NamedSignature;
propagate includeTrans on
  Type, Contexts, Context, NamedSignatureElements, NamedSignatureElement
  excluding nonterminalType, terminalType;

aspect includeTrans on top::Type of
| nonterminalType(fn, ks, d, t) -> \ tr::Decorated TransformStmts ->
  nonterminalType(
    if grammarNameOf(fn) == tr.includedGrammarName
    then tr.grammarName ++ ":" ++ shortNameOf(fn)
    else fn,
    ks, d, t)
| terminalType(fn) -> \ tr::Decorated TransformStmts ->
  terminalType(
    if grammarNameOf(fn) == tr.includedGrammarName
    then tr.grammarName ++ ":" ++ shortNameOf(fn)
    else fn)
end;

-- Can appear under AST via dispatchType:
aspect production namedSignature
top::NamedSignature ::= fn::String ctxs::Contexts ie::NamedSignatureElements oe::NamedSignatureElement np::NamedSignatureElements
{
  top.includeTrans = \ tr::Decorated TransformStmts ->
    namedSignature(
      if grammarNameOf(fn) == tr.includedGrammarName
      then tr.grammarName ++ ":" ++ shortNameOf(fn)
      else fn,
      ctxs.includeTrans(tr),
      ie.includeTrans(tr),
      oe.includeTrans(tr),
      np.includeTrans(tr));
}
aspect production globalSignature
top::NamedSignature ::= fn::String ctxs::Contexts ty::Type
{
  top.includeTrans = error("includeTrans should not be demanded on globalSignature");
}