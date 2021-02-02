grammar silver:compiler:extension:tuple;

nonterminal ListOfTypeExprs with location, unparse, te_translation;
synthesized attribute te_translation :: TypeExpr;

aspect default production
top::Type ::=
{
  top.tupleElems = [top];
}

aspect production appType
top::Type ::= c::Type a::Type
{
  top.tupleElems =
    -- c.argTypes should only have a single element
    case c.baseType of
    | nonterminalType("silver:core:Pair", 2, false) -> c.argTypes ++ a.tupleElems
    | _ -> [top]
    end;

}

synthesized attribute tupleElems :: [Type] occurs on Type;

abstract production tupleType
top::Type ::= ts::[Type]
{

  top.substituted = tupleType(map (\ t::Type -> decorate t with {substitution = top.substitution;}.substituted, ts));
  top.flatRenamed = tupleType(map (\ t::Type -> decorate t with {substitution = top.substitution;}.flatRenamed, ts));

  -- elements of ts need the boundVariables from the top because typepp attribute has a dependency on boundVariables
  top.typepp = "(" ++ implode(", ", map(prettyTypeWith(_, top.boundVariables), ts)) ++ ")";
  
  forwards to case ts of
    | [] -> nonterminalType("silver:core:Unit", 0, false)
    | [t] -> t
    | t1::t1s -> appType(appType(nonterminalType("silver:core:Pair", 2, false), t1), tupleType(t1s))
    end;

}

concrete production emptyTupleTypeExpr
top::TypeExpr ::= '(' ')'
{
  top.unparse = "()";
  top.typerep = tupleType([]);
  forwards to Silver_TypeExpr { silver:core:Unit };
}

concrete production tupleTypeExpr
top::TypeExpr ::= '(' tes::ListOfTypeExprs ')'
{
  top.unparse = "(" ++ tes.unparse ++ ")";
  top.typerep = tupleType(forward.typerep.tupleElems);
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