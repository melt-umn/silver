grammar silver:compiler:extension:convenienceaspects;

import silver:compiler:modification:let_fix;

function makePatternListfromListofPatterns
PatternList ::= l::[Pattern] defaultLoc::Location
{
  return
    foldr(
      \next::Pattern accum::PatternList -> patternList_more(next, ',', accum, location=next.location),
      patternList_nil(location=defaultLoc),
      l);
}


function collectPatternsFromPatternList
[Pattern] ::= l::PatternList accum::[Pattern]
{
  return case l of
  | patternList_one(p) -> p::accum
  | patternList_snoc(ps,_,p) -> collectPatternsFromPatternList(ps, p::accum)
  | patternList_more(p,_,ps) -> [p] ++ collectPatternsFromPatternList(ps, accum)
  | patternList_nil() -> accum
  end;
}

function extractSubPatternListsFromPatterns
PatternList ::= pl::PatternList
{
  return makePatternListfromListofPatterns(
    foldr(
      append,
      [],
      (map(
        \pat::Pattern -> case pat of
        | prodAppPattern(_,_,ps,_) -> collectPatternsFromPatternList(ps,[])
        | _ -> []
        end,
        collectPatternsFromPatternList(pl,[])))),
    pl.location);
}

function foldrLastElem
b ::= f::(b ::= a b)  i::(b ::= a)  l::[a]
{
  return case l of
  | [elem] -> i(elem)
  | h::t -> f(h, foldrLastElem(f,i,t))
  | [] -> error("You can't call foldrLastElem with an empty list")
  end;
}

function makeExprsFromExprList
Exprs ::= l::[Expr] defaultLoc::Location
{
  return
    if null(l) then exprsEmpty(location=defaultLoc)
    else
      foldrLastElem(
        \leftelem::Expr accum::Exprs -> exprsCons(leftelem,',',accum,location=leftelem.location),
        \elem::Expr -> exprsSingle(elem,location=elem.location),
        l);

}


function makeMRuleListFromListMatchRules
MRuleList ::= l::[MatchRule] loc::Location
{
  return foldrLastElem(
   \leftelem::MatchRule accum::MRuleList -> mRuleList_cons(leftelem,'|',accum, location=leftelem.location),
   \a::MatchRule -> mRuleList_one(a,location=a.location),
   l);

}


function makeGeneratedNamesFromMatchRule
[Name] ::= mr::MatchRule loc::Location
{
  local patList::PatternList =
    case mr of
    | matchRule_c(patternList_one(prodAppPattern(_,_,pl,_)),_,_) -> pl
    | matchRuleWhen_c(patternList_one(prodAppPattern(_,_,pl,_)),_,_,_,_) -> pl
    | matchRuleWhenMatches_c(patternList_one(prodAppPattern(_,_,pl,_)),_,_,_,_,_,_) -> pl
    | _ -> patternList_nil(location=loc)
    end;

  return
    map(\pat::Pattern ->
      name("__generated_" ++ toString(genInt()), loc),
      collectPatternsFromPatternList(patList,[]));

}


function extractAspectAgDclFromRuleList
Pair<AGDcl [Message]> ::= rules::[MatchRule] aspectLHS::ConvAspectLHS aspectAttr::QNameAttrOccur  eqKind::ConvenienceAspectEquationKind location::Location env::Decorated Env
{

  local lookupProdInputTypes::([Type] ::= String) = \prodName::String ->
      case (getValueDclInScope(prodName,env)) of
      | [] -> []
      | dcl:: _ ->
        let dcl :: Decorated DclInfo = decorate dcl with {givenNonterminalType = error("Not actually needed");}
        in
          if dcl.typeScheme.typerep.isApplicable
          then dcl.typeScheme.typerep.inputTypes
          -- Productions that aren't in scope, and names that
          -- aren't productions will be caught later in the primitive match.
          else []
        end
      end;

  local makeAspectRHSElemListFromNameAndTypeList::([AspectRHSElem] ::= [Name] [Type]) =
    zipWith(aspectRHSElemFull(_, _, location=location), _, _);

  local makeAspectRHSFromParamsList::(AspectRHS ::= [AspectRHSElem] ) = foldr(
    aspectRHSElemCons(_, _, location=location),
    aspectRHSElemNil(location=location),
    _);

  local makeQNamesFromNames::([QName] ::= [Name]) = map(qNameId(_, location=location),_);

  local makeBaseExprFromQNames::([Expr] ::= [QName]) = map(baseExpr(_,location=location),_);

  -- Transforms it to extract a subpattern and bring it up as the
  -- main pattern.
  local transformPatternMatchRule::([MatchRule]::=[MatchRule]) =
    map((\mRule::MatchRule -> case mRule of
      | matchRule_c(pl,arrow,e) -> matchRule_c(
        extractSubPatternListsFromPatterns(pl),
        arrow,
        e,
        location=location)
      | matchRuleWhen_c(pl,whenKWD,cond,arrow,e) ->
        matchRuleWhen_c(
          extractSubPatternListsFromPatterns(pl),
          whenKWD,
          cond,
          arrow,
          e,
          location=location)
      | matchRuleWhenMatches_c(pl,whenKWD,cond,matches,p,arrow,e) ->
        matchRuleWhenMatches_c(
          extractSubPatternListsFromPatterns(pl),
          whenKWD,
          cond,
          matches,
          p,
          arrow,
          e,
          location=location)
      end),
      _);

  local makeParamsCaseExpr::(Expr ::= [Expr] [MatchRule]) =
    \paramsCaseSubExpr::[Expr] mRules::[MatchRule] ->
      caseExpr_c(
        'case',
        makeExprsFromExprList(paramsCaseSubExpr,location),
        'of',
        terminal(Opt_Vbar_t, "|"),
        makeMRuleListFromListMatchRules(transformPatternMatchRule(mRules), location),
        'end',
        location=location);

  local makeAspectProduction::(AGDcl ::= Expr QName AspectRHS) =
    \paramsCaseExpr::Expr prod::QName prodParams::AspectRHS ->
      Silver_AGDcl {
        aspect production $QName{prod}
        $Name{aspectLHS.aspectName}::$TypeExpr{aspectLHS.aspectType} ::= $AspectRHS{prodParams}
          { $ProductionStmt{eqKind.makeAspectEquation(
              makeTopDefinitionLHS(
                aspectLHS.aspectName,
                head(rules).location),
              aspectAttr,
              paramsCaseExpr,
              paramsCaseExpr.location)}}
      };

  local makeTopDefinitionLHS::(DefLHS ::= Name Location) = \name::Name loc::Location ->
    concreteDefLHS(qNameId(name,location=loc), location=loc);

  local makeLetExprForTopRenaming::(Expr ::= Name Expr Location) = \newName::Name e::Expr loc::Location ->
    (letp(assignExpr(newName,'::',aspectLHS.aspectType,'=', baseExpr(qNameId(aspectLHS.aspectName,location=loc),location=loc),location=loc), e,location=loc));


  return case rules of
    | matchRule_c(patternList_one(prodAppPattern(name,_,_,_)),_,e) :: _
    ->
    let paramNames :: [Name] = makeGeneratedNamesFromMatchRule(head(rules),location)
    in
    pair(
      makeAspectProduction(
        makeParamsCaseExpr(
            makeBaseExprFromQNames(makeQNamesFromNames(paramNames)),
            rules),
        name,
        makeAspectRHSFromParamsList(
            makeAspectRHSElemListFromNameAndTypeList(
              paramNames,
              lookupProdInputTypes(name.name)))),
      [])
    end
    | matchRule_c(patternList_more(prodAppPattern(name,_,_,_),_,_),_,e) :: _
    ->
    let paramNames :: [Name] = makeGeneratedNamesFromMatchRule(head(rules),location)
    in
    pair(
      makeAspectProduction(
        makeParamsCaseExpr(
            makeBaseExprFromQNames(makeQNamesFromNames(paramNames)),
            rules),
        name,
        makeAspectRHSFromParamsList(
            makeAspectRHSElemListFromNameAndTypeList(
              paramNames,
              lookupProdInputTypes(name.name)))),
      [])
    end
    | matchRule_c(patternList_one(wildcPattern(_)),_,e) :: _ ->
      pair(
        Silver_AGDcl {
          aspect default production
          $Name{aspectLHS.aspectName}::$TypeExpr{aspectLHS.aspectType} ::=
          { $ProductionStmt{eqKind.makeAspectEquation(
              makeTopDefinitionLHS(
                aspectLHS.aspectName,
                head(rules).location),
              aspectAttr,
              e,
              head(rules).location)}}
      },
      [])
    | matchRule_c(patternList_more(wildcPattern(_),_,_),_,e) :: _ ->
      pair(
        Silver_AGDcl {
          aspect default production
          $Name{aspectLHS.aspectName}::$TypeExpr{aspectLHS.aspectType} ::=
          { $ProductionStmt{eqKind.makeAspectEquation(
              makeTopDefinitionLHS(
                aspectLHS.aspectName,
                head(rules).location),
              aspectAttr,
              e,
              head(rules).location)}}
      },
      [])
    | matchRule_c(patternList_one(varPattern(name)),_,e) :: _ ->
      pair(
        Silver_AGDcl {
          aspect default production
          $Name{aspectLHS.aspectName}::$TypeExpr{aspectLHS.aspectType} ::=
          { $ProductionStmt{eqKind.makeAspectEquation(
              makeTopDefinitionLHS(
                aspectLHS.aspectName,
                head(rules).location),
              aspectAttr,
              makeLetExprForTopRenaming(name, e, head(rules).location),
              head(rules).location)}}
      },
      [])
    | matchRule_c(patternList_more(varPattern(name),_,_),_,e) :: _ ->
      pair(
        Silver_AGDcl {
          aspect default production
          $Name{aspectLHS.aspectName}::$TypeExpr{aspectLHS.aspectType} ::=
          { $ProductionStmt{eqKind.makeAspectEquation(
              makeTopDefinitionLHS(
                aspectLHS.aspectName,
                head(rules).location),
              aspectAttr,
              makeLetExprForTopRenaming(name, e, head(rules).location),
              head(rules).location)}}
      },
      [])
    | _ ->
      pair(
        error("Patterns in aspect convenience syntax should be productions,wildcards, or varpatterns only"),
        [err(location,"Patterns in aspect convenience syntax should be productions,wildcards, or varpatterns only")])
    end;
}


function collectMatchRulesfromMRuleList
[MatchRule] ::= l::MRuleList accum::[MatchRule]
{
  return case l of
  | mRuleList_one(m) -> m::accum
  | mRuleList_cons(h,_,t) -> collectMatchRulesfromMRuleList(t,(h :: accum))
  end;
}

-- Compares patterns, if they're
-- both production patterns, compares production name
-- otherwise compares the kind of pattern, (varname, wildcard, etc)
function eqKindPattern
Boolean ::= l::Pattern r::Pattern
{
  return case l of
  | prodAppPattern_named(nameL,_,_,_,_,_) ->
    case r of
    | prodAppPattern_named(nameR,_,_,_,_,_) ->
      nameR.name == nameL.name
    | _ -> false
    end
  | wildcPattern(_) ->
    case r of
    | wildcPattern(_) -> true
    | _ -> false
    end
  | leftPatt ->
    case r of
    | prodAppPattern_named(_,_,_,_,_,_) -> false
    | wildcPattern(_) -> false
    | _ -> true
    end
  end;

}

-- Compares the head pattern of two match rules
-- Modeled after comparison used for AbstractMatchRules
function eqPatternMatchRule
Boolean ::= l::MatchRule r::MatchRule
{
  -- TODO: when tuple patterns land redo this.
  return case l of
  | matchRule_c(patternList_one(patternHeadL),_,_) ->
    case r of
    | matchRule_c(patternList_one(patternHeadR),_,_) ->
      eqKindPattern(patternHeadL,patternHeadR)
    | matchRule_c(patternList_more(patternHeadR,_,_),_,_) ->
      eqKindPattern(patternHeadL,patternHeadR)
    | matchRuleWhen_c(patternList_one(patternHeadR),_,_,_,_) ->
      eqKindPattern(patternHeadL,patternHeadR)
    | matchRuleWhen_c(patternList_more(patternHeadR,_,_),_,_,_,_) ->
      eqKindPattern(patternHeadL,patternHeadR)
    | matchRuleWhenMatches_c(patternList_one(patternHeadR),_,_,_,_,_,_) ->
      eqKindPattern(patternHeadL,patternHeadR)
    | matchRuleWhenMatches_c(patternList_more(patternHeadR,_,_),_,_,_,_,_,_) ->
      eqKindPattern(patternHeadL,patternHeadR)
      end
  | matchRule_c(patternList_more(patternHeadL,_,_),_,_) ->
    case r of
    | matchRule_c(patternList_one(patternHeadR),_,_) ->
      eqKindPattern(patternHeadL,patternHeadR)
    | matchRule_c(patternList_more(patternHeadR,_,_),_,_) ->
      eqKindPattern(patternHeadL,patternHeadR)
    | matchRuleWhen_c(patternList_one(patternHeadR),_,_,_,_) ->
      eqKindPattern(patternHeadL,patternHeadR)
    | matchRuleWhen_c(patternList_more(patternHeadR,_,_),_,_,_,_) ->
      eqKindPattern(patternHeadL,patternHeadR)
    | matchRuleWhenMatches_c(patternList_one(patternHeadR),_,_,_,_,_,_) ->
      eqKindPattern(patternHeadL,patternHeadR)
    | matchRuleWhenMatches_c(patternList_more(patternHeadR,_,_),_,_,_,_,_,_) ->
      eqKindPattern(patternHeadL,patternHeadR)
      end
  | matchRuleWhen_c(patternList_one(patternHeadL),_,_,_,_) ->
    case r of
    | matchRule_c(patternList_one(patternHeadR),_,_) ->
      eqKindPattern(patternHeadL,patternHeadR)
    | matchRule_c(patternList_more(patternHeadR,_,_),_,_) ->
      eqKindPattern(patternHeadL,patternHeadR)
    | matchRuleWhen_c(patternList_one(patternHeadR),_,_,_,_) ->
      eqKindPattern(patternHeadL,patternHeadR)
    | matchRuleWhen_c(patternList_more(patternHeadR,_,_),_,_,_,_) ->
      eqKindPattern(patternHeadL,patternHeadR)
    | matchRuleWhenMatches_c(patternList_one(patternHeadR),_,_,_,_,_,_) ->
      eqKindPattern(patternHeadL,patternHeadR)
    | matchRuleWhenMatches_c(patternList_more(patternHeadR,_,_),_,_,_,_,_,_) ->
      eqKindPattern(patternHeadL,patternHeadR)
      end
  | matchRuleWhen_c(patternList_more(patternHeadL,_,_),_,_,_,_) ->
    case r of
    | matchRule_c(patternList_one(patternHeadR),_,_) ->
      eqKindPattern(patternHeadL,patternHeadR)
    | matchRule_c(patternList_more(patternHeadR,_,_),_,_) ->
      eqKindPattern(patternHeadL,patternHeadR)
    | matchRuleWhen_c(patternList_one(patternHeadR),_,_,_,_) ->
      eqKindPattern(patternHeadL,patternHeadR)
    | matchRuleWhen_c(patternList_more(patternHeadR,_,_),_,_,_,_) ->
      eqKindPattern(patternHeadL,patternHeadR)
    | matchRuleWhenMatches_c(patternList_one(patternHeadR),_,_,_,_,_,_) ->
      eqKindPattern(patternHeadL,patternHeadR)
    | matchRuleWhenMatches_c(patternList_more(patternHeadR,_,_),_,_,_,_,_,_) ->
      eqKindPattern(patternHeadL,patternHeadR)
      end
  | matchRuleWhenMatches_c(patternList_one(patternHeadL),_,_,_,_,_,_) ->
    case r of
    | matchRule_c(patternList_one(patternHeadR),_,_) ->
      eqKindPattern(patternHeadL,patternHeadR)
    | matchRule_c(patternList_more(patternHeadR,_,_),_,_) ->
      eqKindPattern(patternHeadL,patternHeadR)
    | matchRuleWhen_c(patternList_one(patternHeadR),_,_,_,_) ->
      eqKindPattern(patternHeadL,patternHeadR)
    | matchRuleWhen_c(patternList_more(patternHeadR,_,_),_,_,_,_) ->
      eqKindPattern(patternHeadL,patternHeadR)
    | matchRuleWhenMatches_c(patternList_one(patternHeadR),_,_,_,_,_,_) ->
      eqKindPattern(patternHeadL,patternHeadR)
    | matchRuleWhenMatches_c(patternList_more(patternHeadR,_,_),_,_,_,_,_,_) ->
      eqKindPattern(patternHeadL,patternHeadR)
      end
  | matchRuleWhenMatches_c(patternList_more(patternHeadL,_,_),_,_,_,_,_,_) ->
    case r of
    | matchRule_c(patternList_one(patternHeadR),_,_) ->
      eqKindPattern(patternHeadL,patternHeadR)
    | matchRule_c(patternList_more(patternHeadR,_,_),_,_) ->
      eqKindPattern(patternHeadL,patternHeadR)
    | matchRuleWhen_c(patternList_one(patternHeadR),_,_,_,_) ->
      eqKindPattern(patternHeadL,patternHeadR)
    | matchRuleWhen_c(patternList_more(patternHeadR,_,_),_,_,_,_) ->
      eqKindPattern(patternHeadL,patternHeadR)
    | matchRuleWhenMatches_c(patternList_one(patternHeadR),_,_,_,_,_,_) ->
      eqKindPattern(patternHeadL,patternHeadR)
    | matchRuleWhenMatches_c(patternList_more(patternHeadR,_,_),_,_,_,_,_,_) ->
      eqKindPattern(patternHeadL,patternHeadR)
      end
  | _ -> false
  end;

}

function isWildCardMatchRule
Boolean ::= mRule::MatchRule
{ return
      case mRule of
      | matchRule_c(patternList_one(wildcPattern(_)),_,_) -> true
      | matchRule_c(patternList_more(wildcPattern(_),_,_),_,e) -> true
      | matchRule_c(patternList_snoc(patternList_one(wildcPattern(_)),_,_),_,e) -> true
      | matchRule_c(patternList_one(varPattern(_)),_,_) -> true
      | matchRule_c(patternList_more(varPattern(_),_,_),_,e) -> true
      | matchRule_c(patternList_snoc(patternList_one(varPattern(_)),_,_),_,e) -> true
      | _ -> false
      end;
}


abstract production convenienceAspects
top::AGDcl ::= attr::QNameAttrOccur aspectLHS::ConvAspectLHS eqKind::ConvenienceAspectEquationKind ml::MRuleList
{
  top.defs := [];
  top.moduleNames := [];
  top.occursDefs := [];

  top.unparse = "aspect " ++ attr.unparse ++ " on " ++ aspectLHS.unparse ++ " " ++ eqKind.unparse ++ " of |" ++ ml.unparse ++ " end";
  --Everything past the first wildcard gets dropped before grouping of match patterns
  local mList::[MatchRule] = reverse(collectMatchRulesfromMRuleList(ml,[]));
  local mListUpToFirstWildcard::[MatchRule] =
    foldr(\next::MatchRule accum::[MatchRule] ->
      if !isWildCardMatchRule(next) then next::accum else [next],
      [],
      mList);
  local mListWildcardAndAfter::[MatchRule] =
    dropWhile(\mRule::MatchRule -> !isWildCardMatchRule(mRule),mList);
  local mListAfterWildcard::[MatchRule] =
    if null(mListWildcardAndAfter) then [] else tail(mListWildcardAndAfter);

  local groupedMRules::[[MatchRule]] = groupBy(eqPatternMatchRule, mListUpToFirstWildcard);

  local groupExtractResults::[Pair<AGDcl [Message]>] = map(
    extractAspectAgDclFromRuleList(_,aspectLHS,attr,eqKind,top.location, top.env),
    groupedMRules);

  local groupExtractErrors::[Message] = foldr(append, [], (map(snd(_), groupExtractResults)));


  top.errors <- if null(mListAfterWildcard)
                -- This means that nothing is past the wildcard pattern, which is good.
                then groupExtractErrors
                -- Something _is_ past the wildcard pattern
                else [wrn(((head(mListAfterWildcard)).location),"This pattern and the ones that follow are being ignored.")]
                  ++ groupExtractErrors;

  local combinedAspectProds::[AGDcl] = map(fst(_),groupExtractResults);

  local combinedAspectDcls::AGDcls = foldr(
   consAGDcls(_,_,location=top.location),
   nilAGDcls(location=top.location),
   combinedAspectProds);

  local fwrd::AGDcl = makeAppendAGDclOfAGDcls(combinedAspectDcls);
  forwards to fwrd;
}
