grammar silver:compiler:extension:convenienceaspects;
import silver:core;


function extractAgDclFromRuleList
AGDcl ::= rules::[AbstractMatchRule] aspectTy::TypeExpr aspectAttr::QName location::Location
{

  local makeProdParamTypes::([Type] ::= QName) = \prod::QName ->
    case prod.lookupValue.typeScheme.typerep of
    | functionType(_, paramTypes, _) -> paramTypes
    | _ -> error("invalid production type")
    end;
  -- local makeProdParamsList::([AspectRHSElem] ::= [Type]) = \prodParamTypes::[Type] ->
  --   map(\ty::Type -> aspectRHSElemFull(name("__generated_" ++ toString(genIntReally(ty)), location), ty, location=location),
  --   prodParamTypes);
  local makeProdParamsList::([AspectRHSElem] ::= [Name] [Type]) =
    \prodParamNames::[Name] prodParamTypes::[Type] ->
      zipWith(aspectRHSElemFull(_, _, location=location), prodParamNames, prodParamTypes);
  local makeProdParams::(AspectRHS ::= [AspectRHSElem] ) = \prodParamsList::[AspectRHSElem] ->
    foldr(aspectRHSElemCons(_, _, location=location),
          aspectRHSElemNil(location=location),
          prodParamsList);
  local makeQNamesFromNames::([QName] ::= [Name]) = map(qNameId(_, location=location),_);
  -- local makeProdParamNames::([QName] ::= [AspectRHSElem]) = \prodParamsList::[AspectRHSElem] ->
  --   map((\asp::AspectRHSElem -> case asp of
  --                               | aspectRHSElemFull(n,_) -> qNameId(n, location=location)
  --                               | _ -> error("Somehow an element other than an AspectRHSElem is in this list, which shouldn't happen")
  --                               end),
  --           prodParamsList);
  local makeParamCaseSubExpr::([Expr] ::= [QName]) = \prodParamNames::[QName] ->
    map(baseExpr(_,location=location),prodParamNames);
  local makeParamsCaseExpr::(Expr ::= [Expr] [AbstractMatchRule]) =
    \paramsCaseSubExpr::[Expr] mRules::[AbstractMatchRule] ->
      caseExpr(paramsCaseSubExpr, mRules,
        mkStrFunctionInvocation(location, "silver:core:error",
            [stringConst(terminal(String_t,
            "\"Error: pattern match failed at " ++ location.unparse ++ "\\n\""), location=location)]),
            freshType(), location=location);
  local makeAspect::(AGDcl ::= Expr QName AspectRHS) =
    \paramsCaseExpr::Expr prod::QName prodParams::AspectRHS ->
      Silver_AGDcl {
        aspect production $QName{prod}
        top::$TypeExpr{aspectTy} ::= $AspectRHS{prodParams}
        { top.$QName{aspectAttr} = $Expr{paramsCaseExpr}; }
      };
  return case head(rules) of
    | matchRule(prodAppPattern(name,_,_,_)::_, cond,e) ->
    -- I wish let bindings were stable
    makeAspect(
      makeParamsCaseExpr(
        makeParamCaseSubExpr(
          makeQNamesFromNames(head(rules).aspectProdParamsList)), rules),
      name,
      makeProdParams(makeProdParamsList(head(rules).aspectProdParamsList, makeProdParamTypes(name))))
    -- makeAspect(
    --   makeParamsCaseExpr(
    --     makeParamCaseSubExpr(
    --       makeProdParamNames(
    --         makeProdParamsList(
    --           makeProdParamTypes(name)))),
    --     rules),
    --     name
    --     )
    end;
}

concrete production convenienceAspects_c
top::AGDcl ::= 'aspect' attr::QName 'on' ty::TypeExpr 'of' Opt_Vbar_t ml::MRuleList 'end' ';'
{
  top.defs := [];
  ml.aspectAttr = attr;
  ml.aspectTy = ty;

  -- local extractAgDclFromRuleList::(AGDcl ::= [AbstractMatchRule]) =
  --   \rules::[AbstractMatchRule] ->
  --     case rules of
  --     | [matchRule(prodAppPattern(name,_,_,_),)] :: rest ->
  --       foldr( w )


  local groupedMRules::[[AbstractMatchRule]] = groupMRules(ml.matchRuleList);
  local fwrd::AGDcl = makeAppendAGDclOfAGDcls(ml.aspectDcls);

  forwards to unsafeTrace(fwrd, print(hackUnparse(groupedMRules) ++ "\n\n",unsafeIO()));
}



aspect production matchRule
top::AbstractMatchRule ::= pl::[Decorated Pattern] cond::Maybe<Pair<Expr Maybe<Pattern>>> e::Expr
{
  top.aspectProdParamsList = case pl of
    | prodAppPattern_named(prod, _, ps,_,_,_) :: _ ->
      map(\pat::Decorated Pattern ->
        name("__generated_" ++ toString(genIntReally(pat)), top.location),
        ps.patternList)
    | _ -> []
    end;

}


--TODO: Do this, but generate warning on <-,
-- Error("This is subject to change.")
-- aspect foo on bar with := of ... end
-- Defaults to =
-- aspect foo on bar of ... end

autocopy attribute aspectAttr::QName occurs on MRuleList, MatchRule, PatternList, Pattern;
autocopy attribute aspectTy::TypeExpr occurs on MRuleList, MatchRule, PatternList, Pattern;

synthesized attribute aspectDcls::AGDcls occurs on MRuleList;
synthesized attribute aspectProdParamsList::[Name] occurs on AbstractMatchRule;

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

aspect production matchRule_c
top::MatchRule ::= pt::PatternList _ e::Expr
{
  top.aspectDcl = case pt.patternList of
  | [pat] -> pat.makeAspect(e)
  end;
}

synthesized attribute makeAspect::(AGDcl ::= Expr) occurs on Pattern;

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
