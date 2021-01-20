grammar silver:compiler:extension:tuple;

nonterminal ListOfTypeExprs with location, unparse, env, config, te_translation;
synthesized attribute te_translation :: TypeExpr;

aspect production appType
top::Type ::= c::Type a::Type
{
  top.tupleElems =
    case a.baseType of
    | nonterminalType("silver:core:Pair", 2, false) -> c.argTypes ++ a.argTypes
    | _ -> c.argTypes ++ [a]
    end;
}

synthesized attribute tupleElems :: [Type] occurs on Type;

abstract production tupleType
top::Type ::= ts::[Type]
{
  top.typepp = "(" ++ printTupleTypeList(ts) ++ ")";
  forwards to foldr1(\ t1::Type t2::Type -> appType(appType(nonterminalType("silver:core:Pair", 2, false), t1), t2), ts);
}

function printTupleTypeList
String ::= ts::[Type]
{
  return case ts of
  | [ty] -> ty.typepp
  | ty::tys -> ty.typepp ++ ", " ++ printTupleTypeList(tys)
  end;
}

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
  top.typerep = tupleType(tes.te_translation.typerep.tupleElems);
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