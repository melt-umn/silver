grammar silver:compiler:extension:convenienceaspects;

import silver:compiler:modification:let_fix;


{-
From a list of Patterns, makes a PatternList with the right list-shaped productions.
-}
function makePatternListfromListofPatterns
PatternList ::= l::[Pattern] defaultLoc::Location
{
  return
    foldr(
      \next::Pattern accum::PatternList -> patternList_more(next, ',', accum, location=next.location),
      patternList_nil(location=defaultLoc),
      l);
}


{-
From a PatternList, makes a "regular" list of the patterns in them.
-}
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

{-
Extracts out the subpatterns of productions in a patternList, but in a way
that doesn't demand attributes.
-}
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

{-
A foldr function that requires the list to be nonEmpty.
It takes in a function a->b->b to combine two elements as is usual, but also takes
in a function a->b, and applies that function to the last element.
-}
function foldrLastElem
b ::= f::(b ::= a b)  i::(b ::= a) l::[a]
{
  return case l of
  | [elem] -> i(elem)
  | h::t -> f(h, foldrLastElem(f,i,t))
  | [] -> error("You can't call foldrLastElem with an empty list")
  end;
}

{-
Takes in a regular list of Expr, turns them into an instance of the Exprs production.
-}
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


{-
Takes in a regular list of MatchRule, turns them into an instance of the MRuleList production.
-}
function makeMRuleListFromListMatchRules
MRuleList ::= l::[MatchRule] loc::Location
{
  return foldrLastElem(
   \leftelem::MatchRule accum::MRuleList -> mRuleList_cons(leftelem,'|',accum, location=leftelem.location),
   \a::MatchRule -> mRuleList_one(a,location=a.location),
   l);

}

{-
Given a MRuleList element, transforms it into a regular list of MatchRules
-}
function collectMatchRulesfromMRuleList
[MatchRule] ::= l::MRuleList accum::[MatchRule]
{
  return case l of
  | mRuleList_one(m) -> m::accum
  | mRuleList_cons(h,_,t) -> collectMatchRulesfromMRuleList(t,(h :: accum))
  end;
}

{-
This function goes into a production pattern (if it is one), extracts out the sub pattern
for that production, and generates names for each element of that sub pattern.
e.g Given silver_matchRule {foo(bar(3,x),y) -> y+1 } where foo,bar are productions,
   it returns [_gen1,_gen2] (where the numbers are generated from genInt)
-}
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


{-
This function takes in a name and location and returns a concrete definition LHS
element.
-}
function makeDefinitionLHSFromName
DefLHS ::= name::Name loc::Location
{
  return concreteDefLHS(qNameId(name,location=loc), location=loc);
}





{-
This function takes in a list of MatchRules which should represent a grouping of
match rules with similar patterns (rules), an aspectLHS (the expression that
usually goes like this in an aspect production top::<Type-of-production> ...),
the aspect attribute we're generating productions for (aspectAttr), the operator
that assigns or binds to the attribute (eqKind), a location for where all this
is defined, and an environment for looking up production types.

From all this, it returns a single AgDcl that defines the aspect production we're generating, paired with
a list of warnings or errors it generated.
-}
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

  local makeAspectRHSElemListFromNameAndTypeLists::([AspectRHSElem] ::= [Name] [Type]) =
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

  -- This function makes the case expression that we insert into
  -- the aspect production, which is generated from the original pattern
  -- match statement we had, with the production names taken out (so we
  -- can use the list of patterns there against the paramters)
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

  -- This function makes our aspect production from the Expression, QName, and AspectRHS
  -- We've generated elsewhere.
  local makeAspectProduction::(AGDcl ::= Expr QName AspectRHS) =
    \paramsCaseExpr::Expr prod::QName prodParams::AspectRHS ->
      Silver_AGDcl {
        aspect production $QName{prod}
        $Name{aspectLHS.aspectName}::$TypeExpr{aspectLHS.aspectType} ::= $AspectRHS{prodParams}
          { $ProductionStmt{
              eqKind.makeAspectEquation(
                makeDefinitionLHSFromName(
                  aspectLHS.aspectName,
                  head(rules).location),
                aspectAttr,
                paramsCaseExpr,
                paramsCaseExpr.location)}}
      };

  -- Makes the let expression for varpatterns, allowing one to specify a var pattern
  -- and have top be renamed to that name in the default production.
  local makeLetExprForTopRenaming::(Expr ::= Name Expr Location) = \newName::Name e::Expr loc::Location ->
    letp(
      assignExpr(
        newName,
        '::',
        aspectLHS.aspectType,
        '=',
        baseExpr(qNameId(aspectLHS.aspectName,location=loc),location=loc),
        location=loc),
      e,
      location=loc);


  return case rules of
    | matchRule_c(patternList_one(prodAppPattern(name,_,_,_)),_,e) :: _
    ->
    -- Handling for production patterns
    let paramNames :: [Name] = makeGeneratedNamesFromMatchRule(head(rules),location)
    in
    pair(
      makeAspectProduction(
        makeParamsCaseExpr(
            makeBaseExprFromQNames(makeQNamesFromNames(paramNames)),
            rules),
        name,
        makeAspectRHSFromParamsList(
            makeAspectRHSElemListFromNameAndTypeLists(
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
            makeAspectRHSElemListFromNameAndTypeLists(
              paramNames,
              lookupProdInputTypes(name.name)))),
      [])
    end
    -- Handling for wildcard patterns
    | matchRule_c(patternList_one(wildcPattern(_)),_,e) :: _ ->
      pair(
        Silver_AGDcl {
          aspect default production
          $Name{aspectLHS.aspectName}::$TypeExpr{aspectLHS.aspectType} ::=
          { $ProductionStmt{eqKind.makeAspectEquation(
              makeDefinitionLHSFromName(
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
              makeDefinitionLHSFromName(
                aspectLHS.aspectName,
                head(rules).location),
              aspectAttr,
              e,
              head(rules).location)}}
      },
      [])
      -- Handling for varpatterns
    | matchRule_c(patternList_one(varPattern(name)),_,e) :: _ ->
      pair(
        Silver_AGDcl {
          aspect default production
          $Name{aspectLHS.aspectName}::$TypeExpr{aspectLHS.aspectType} ::=
          { $ProductionStmt{eqKind.makeAspectEquation(
              makeDefinitionLHSFromName(
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
              makeDefinitionLHSFromName(
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



{-
Compares patterns, if they're
both production patterns, compares production name
otherwise compares the kind of pattern, (varname or wildcard, mostly).
-}
function eqKindPattern
Boolean ::= l::Pattern r::Pattern
{
  return case l,r of
  | prodAppPattern_named(nameL,_,_,_,_,_),prodAppPattern_named(nameR,_,_,_,_,_) ->
    nameR.name == nameL.name
  | wildcPattern(_),wildcPattern(_) -> true
  | varPattern(_),varPattern(_) -> true
  | prodAppPattern_named(_,_,_,_,_,_),_ -> false
  | wildcPattern(_),_ -> false
  | varPattern(_),_ -> false
  -- other patterns compare favorably with eachother, for our purposes they're all the
  -- same "kind" as not being a varPattern,wildcard,or prod.
  | _,_ -> true
  end;
}


{-
Extracts out the head pattern from the given PatternList.
-}
function extractHeadPatternFromPatternList
Pattern ::= pList::PatternList
{
  return case pList of
  | patternList_one(patHead) -> patHead
  | patternList_more(patHead,_,_) -> patHead
  | patternList_snoc(ps,_,_) -> extractHeadPatternFromPatternList(ps)
  end;
}



{-
Extracts out the head pattern from the given matchRule.
-}
function extractHeadPatternFromMatchRule
Pattern ::= mRule::MatchRule
{
  return case mRule of
  | matchRule_c(ps,_,_) -> extractHeadPatternFromPatternList(ps)
  | matchRuleWhen_c(ps,_,_,_,_) -> extractHeadPatternFromPatternList(ps)
  | matchRuleWhenMatches_c(ps,_,_,_,_,_,_) -> extractHeadPatternFromPatternList(ps)
  end;
}


{-
Compares the head pattern of two match rules, but without demanding attributes
Modeled after comparison used for AbstractMatchRules
-}
function eqPatternMatchRule
Boolean ::= l::MatchRule r::MatchRule
{
  -- The reason this isn't done with attributes is that demanding attributes
  -- (from silver core lang elements) has caused infinite loops and mwda errors
  -- for me (due to me working with concrete syntax elements, I'm presuming), so I've been
  -- avoiding demanding attributes as much as possible.
  return
    eqKindPattern(
      extractHeadPatternFromMatchRule(l),
      extractHeadPatternFromMatchRule(r));
}


{-
Given a MatchRule, tells you if its a "wildcard" match rule.
varpatterns aren't called wildcards, but they match everything just the same.
-}
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


{-
Abstract production for convenience aspects. It's generally advised if you
intend to use convenience aspects to use them as concrete syntax (using the
concrete production starting with 'aspect' <attr> on ... )
-}
abstract production convenienceAspects
top::AGDcl ::= attr::QNameAttrOccur aspectLHS::ConvAspectLHS eqKind::ConvenienceAspectEquationKind ml::MRuleList
{
  top.defs := [];
  top.moduleNames := [];
  top.occursDefs := [];

  top.unparse = "aspect " ++ attr.unparse ++ " on " ++ aspectLHS.unparse ++ " " ++ eqKind.unparse ++ " of |" ++ ml.unparse ++ " end";

  -- Everything past the first wildcard (or varpattern) gets dropped before grouping of match patterns
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

  -- groups MatchRules by their kind, which for our purposes is production name, wildcard, or varpattern.
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

  forwards to makeAppendAGDclOfAGDcls(combinedAspectDcls);
}
