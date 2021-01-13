grammar silver:compiler:extension:convenienceaspects;

concrete production convenienceAspects_c
top::AGDcl ::= 'aspect' attr::QName 'on' ty::TypeExpr 'of' Opt_Vbar_t ml::MRuleList 'end' ';'
{
  top.defs := [];
  ml.aspectAttr = attr;
  ml.aspectTy = ty;
  forwards to makeAppendAGDclOfAGDcls(ml.aspectDcls);
}

autocopy attribute aspectAttr::QName occurs on MRuleList, MatchRule, PatternList, Pattern;
autocopy attribute aspectTy::TypeExpr occurs on MRuleList, MatchRule, PatternList, Pattern;

synthesized attribute aspectDcls::AGDcls occurs on MRuleList;
flowtype aspectDcls {aspectTy, aspectAttr} on MRuleList;

aspect production mRuleList_one
top::MRuleList ::= m::MatchRule
{
  top.aspectDcls = consAGDcls(m.aspectDcl, nilAGDcls(location=top.location),
                              location=top.location);
}

aspect production mRuleList_cons
top::MRuleList ::= h::MatchRule '|' t::MRuleList
{
  top.aspectDcls = consAGDcls(h.aspectDcl, t.aspectDcls, location=top.location);
}

synthesized attribute aspectDcl::AGDcl occurs on MatchRule;
flowtype aspectDcl {aspectTy, aspectAttr} on MatchRule;

aspect production matchRule_c
top::MatchRule ::= pt::PatternList _ e::Expr
{
  top.aspectDcl = case pt.patternList of
  | [pat] -> pat.makeAspect(e)
  end;
}

synthesized attribute makeAspect::(AGDcl ::= Expr) occurs on Pattern;
flowtype makeAspect {aspectTy, aspectAttr} on Pattern;

function genIntReally -- zzz
Integer ::= a
{ return genInt(); }

aspect production prodAppPattern_named
top::Pattern ::= prod::QName '(' ps::PatternList ',' nps::NamedPatternList ')'
{
  local prodParamTypes::[Type] =
    case prod.lookupValue.typeScheme.typerep of
    | functionType(_, paramTypes, _) -> paramTypes
    | _ -> error("invalid production type")
    end;
  local prodParamsList::[AspectRHSElem] =
    map(\ty::Type -> aspectRHSElemFull(name("__generated_" ++ toString(genIntReally(ty)), top.location), ty, location=top.location),
    prodParamTypes);
  local prodParamNames::[QName] =
    map((\asp::AspectRHSElem -> case asp of
                                | aspectRHSElemFull(n,_) -> qNameId(n, location=top.location)
                                | _ -> qNameId(name("ThisNameShouldntComeUp",top.location), location=top.location)
                                end),
            prodParamsList);
  local paramCaseSubExpr::Exprs = foldr(
    \param1::QName accum::Exprs ->
      exprsCons(
        baseExpr(param1,location=top.location),
        ',',
        accum,
        location=top.location),
    exprsEmpty(location=top.location),
    prodParamNames);
  local prodParams::AspectRHS = foldr(
    aspectRHSElemCons(_, _, location=top.location),
    aspectRHSElemNil(location=top.location),
    prodParamsList);

  top.makeAspect = \rhsExpr::Expr ->
      Silver_AGDcl {
        aspect production $QName{prod}
        top::$TypeExpr{top.aspectTy} ::= $AspectRHS{prodParams}
        { top.$QName{top.aspectAttr} = $Expr{caseExpr_c('case',
                                                        paramCaseSubExpr,
                                                        'of',
                                                        terminal(Opt_Vbar_t, "|"),
                                                        mRuleList_one(
                                                            matchRule_c(ps,
                                                                        '->',
                                                                        rhsExpr,
                                                                        location=top.location),
                                                            location=top.location),
                                                        'end',
                                                        location=top.location)}; }
      };
}

aspect production wildcPattern
top::Pattern ::= '_'
{
  top.makeAspect = \rhsExpr::Expr ->
    Silver_AGDcl {
      aspect default production
      top::$TypeExpr{top.aspectTy} ::=
      { top.$QName{top.aspectAttr} = $Expr{rhsExpr}; }
    };
}
