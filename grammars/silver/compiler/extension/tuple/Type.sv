grammar silver:compiler:extension:tuple;

nonterminal ListOfTypeExprs with location, unparse, env, downSubst, te_typelist, te_translation;
synthesized attribute te_translation :: TypeExpr;
synthesized attribute te_typelist :: [Type];

concrete production emptyTupleTypeExpr
top::TypeExpr ::= '(' ')'
{
  top.unparse = "()";
  forwards to Silver_TypeExpr { silver:core:Unit };
}

concrete production tupleTypeExpr
top::TypeExpr ::= '(' tes::ListOfTypeExprs ')'
{
  top.unparse = "(" ++ tes.unparse ++ ")";
  top.typerep = tupleType(tes.te_typelist);
  forwards to tes.te_translation;
}

concrete production tupleTypeExpr2
top::ListOfTypeExprs ::= te1::TypeExpr ',' te2::TypeExpr
{
  top.unparse = te1.unparse ++ "," ++ te2.unparse;
  top.te_typelist = [te1.typerep, te2.typerep];
  top.te_translation = Silver_TypeExpr {silver:core:Pair<$TypeExpr{te1} $TypeExpr{te2}>};
}

concrete production tupleTypeExprn
top::ListOfTypeExprs ::= te::TypeExpr ',' tes::ListOfTypeExprs
{
  top.unparse = te.unparse ++ "," ++ tes.unparse;
  top.te_typelist = te.typerep::tes.te_typelist;
  top.te_translation = Silver_TypeExpr {silver:core:Pair<$TypeExpr{te} $TypeExpr{tes.te_translation}>};
}