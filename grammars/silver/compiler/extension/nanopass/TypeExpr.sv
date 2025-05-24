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
  nominalTypeExpr(qName(qualifyIfDiffGrammar(top.grammarName, n.lookupType.fullName)).qNameType)
| aliasAppTypeExpr(n, tl) -> \ tr::Decorated TransformStmts ->
  appTypeExpr(
    nominalTypeExpr(qName(qualifyIfDiffGrammar(top.grammarName, n.lookupType.fullName)).qNameType),
    tl.includeTrans(tr))
end;
