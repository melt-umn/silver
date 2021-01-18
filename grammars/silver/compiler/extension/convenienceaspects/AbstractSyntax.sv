grammar silver:compiler:extension:convenienceaspects;


function extractAspectAgDclFromRuleList
Pair<AGDcl [Message]> ::= rules::[Decorated AbstractMatchRule] aspectLHS::ConvAspectLHS aspectAttr::QNameAttrOccur  eqKind::ConvenienceAspectEquationKind location::Location
{

  local makeProdParamTypes::([Type] ::= Decorated QName) = \prod::Decorated QName ->
    if prod.lookupValue.typeScheme.typerep.isApplicable then
      prod.lookupValue.typeScheme.typerep.inputTypes
    -- Productions that aren't in scope, and names that
    -- aren't productions will be caught later.
    else [];

  local makeProdParamsList::([AspectRHSElem] ::= [Name] [Type]) =
    zipWith(aspectRHSElemFull(_, _, location=location), _, _);

  local makeProdParams::(AspectRHS ::= [AspectRHSElem] ) = foldr(
    aspectRHSElemCons(_, _, location=location),
    aspectRHSElemNil(location=location),
    _);

  local makeQNamesFromNames::([QName] ::= [Name]) = map(qNameId(_, location=location),_);

  local makeParamCaseSubExpr::([Expr] ::= [QName]) = map(baseExpr(_,location=location),_);

  local transformPatternMatchRule::([AbstractMatchRule]::=[Decorated AbstractMatchRule]) =
    map((\mRule::Decorated AbstractMatchRule -> case mRule of
      | matchRule(pl,cond,e) -> matchRule(
        (foldr(append, [], (map(\pat::Decorated Pattern -> pat.patternSubPatternList, pl)))),
        cond,
        e,
        location=location)
      end),
      _);

  local makeParamsCaseExpr::(Expr ::= [Expr] [Decorated AbstractMatchRule]) =
    \paramsCaseSubExpr::[Expr] mRules::[Decorated AbstractMatchRule] ->
      caseExpr(paramsCaseSubExpr, transformPatternMatchRule(mRules),
        mkStrFunctionInvocation(location, "silver:core:error",
            [stringConst(terminal(String_t,
            "\"Error: pattern match failed at " ++ head(mRules).headPattern.location.unparse ++ "\\n\""), location=head(mRules).headPattern.location)]),
            freshType(), location=head(mRules).headPattern.location);

  local makeAspect::(AGDcl ::= Expr QName AspectRHS) =
    \paramsCaseExpr::Expr prod::QName prodParams::AspectRHS ->
      Silver_AGDcl {
        aspect production $QName{prod}
        $Name{aspectLHS.aspectName}::$TypeExpr{aspectLHS.aspectType} ::= $AspectRHS{prodParams}
          { $ProductionStmt{eqKind.makeAspectEquation(
              defTop(
                aspectLHS.aspectName,
                head(rules).location),
              aspectAttr,
              paramsCaseExpr,
              paramsCaseExpr.location)}}
      };

  local defTop::(DefLHS ::= Name Location) = \name::Name loc::Location ->
    concreteDefLHS(qNameId(name,location=loc), location=loc);

  return case rules of
    | matchRule(prodAppPattern(name,_,_,_)::_, cond,e) :: _ ->
    -- I wish let bindings were stable...
    pair(
      makeAspect(
        makeParamsCaseExpr(
            makeParamCaseSubExpr(makeQNamesFromNames(head(rules).aspectProdParamsList)),
            rules),
        name,
        makeProdParams(
            makeProdParamsList(
              head(rules).aspectProdParamsList,
              makeProdParamTypes(name)))),
      [])
    | [matchRule(wildcPattern(_)::_,_,e)] ->
      pair(
        Silver_AGDcl {
          aspect default production
          $Name{aspectLHS.aspectName}::$TypeExpr{aspectLHS.aspectType} ::=
          { $ProductionStmt{eqKind.makeAspectEquation(
              defTop(
                aspectLHS.aspectName,
                head(rules).location),
              aspectAttr,
              e,
              head(rules).location)}}
      },
      [])
    | matchRule(wildcPattern(_)::_,_,e) :: _ ->
      pair(
        Silver_AGDcl {
          aspect default production
          $Name{aspectLHS.aspectName}::$TypeExpr{aspectLHS.aspectType} ::=
          { $ProductionStmt{eqKind.makeAspectEquation(
              defTop(
                aspectLHS.aspectName,
                head(rules).location),
              aspectAttr,
              e,
              head(rules).location)}}
        },
        [wrn(head(rules).location, "wildcard patterns after this one are dead code.")])
    | _ ->
      pair(
        error("Patterns in aspect convenience syntax should be productions or wildcards only"),
        [err(location,"Patterns in aspect convenience syntax should be productions or wildcards only")])

    end;
}

synthesized attribute aspectProdParamsList::[Name] occurs on AbstractMatchRule;

abstract production convenienceAspects
top::AGDcl ::= attr::QNameAttrOccur aspectLHS::ConvAspectLHS eqKind::ConvenienceAspectEquationKind ml::MRuleList
{
  top.defs := [];

  local groupedMRules::[[Decorated AbstractMatchRule]] =
    map(\rules::[AbstractMatchRule] -> map(
      \rule_::AbstractMatchRule -> decorate rule_ with { env = top.env; }, rules),
        groupMRules(ml.matchRuleList));

  local groupExtractResults::[Pair<AGDcl [Message]>] = map(
    extractAspectAgDclFromRuleList(_,aspectLHS,attr,eqKind,top.location),
    groupedMRules);

  top.errors <- foldr(append, [], (map(snd(_), groupExtractResults)));

  local combinedAspectProds::[AGDcl] = map(fst(_),groupExtractResults);

  local combinedAspectDcls::AGDcls = foldr(
   consAGDcls(_,_,location=top.location),
   nilAGDcls(location=top.location),
   combinedAspectProds);

  forwards to makeAppendAGDclOfAGDcls(combinedAspectDcls);

}


aspect production matchRule
top::AbstractMatchRule ::= pl::[Decorated Pattern] cond::Maybe<Pair<Expr Maybe<Pattern>>> e::Expr
{
  top.aspectProdParamsList = case pl of
    | prodAppPattern_named(prod, _, ps,_,_,_) :: _ ->
      map(\pat::Decorated Pattern ->
        name("__generated_" ++ toString(genInt()), top.location),
        ps.patternList)
    | _ -> []
    end;

}

aspect default production
top::AbstractMatchRule ::=
{
  top.aspectProdParamsList = [];
}
