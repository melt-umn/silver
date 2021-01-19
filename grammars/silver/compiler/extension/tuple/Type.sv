grammar silver:compiler:extension:tuple;

nonterminal ListOfTypeExprs with location, unparse, te_translation;
synthesized attribute te_translation :: TypeExpr;

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
  top.typerep = tupleType(tes.typerep);
  forwards to tes.te_translation;
}

concrete production tupleTypeExpr2
top::ListOfTypeExprs ::= te1::TypeExpr ',' te2::TypeExpr
{
  top.unparse = te1.unparse ++ "," ++ te2.unparse;
  top.te_translation = Silver_TypeExpr {silver:core:Pair<$TypeExpr{te1} $TypeExpr{te2}>};
}

concrete production tupleTypeExprn
top::ListOfTypeExprs ::= te::TypeExpr ',' tes::ListOfTypeExprs
{
  top.unparse = te.unparse ++ "," ++ tes.unparse;
  top.te_translation = Silver_TypeExpr {silver:core:Pair<$TypeExpr{te} $TypeExpr{tes.te_translation}>};
}