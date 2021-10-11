grammar silver:compiler:extension:convenienceaspects;

import silver:compiler:modification:let_fix;

@{-
  - From a list of Patterns, makes a PatternList with the right list-shaped productions.
  - @param l the list of patterns to modify
  - @param defaultLoc the location to provide for the patternListNil construct (which needs a location).
  - @return A patternList List-Like Nonterminal instance.
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




@{-
  - @param l The next patternList element to extract out patterns from.
  - @param accum The accumulating list of patterns.
  - @return A list containing the patterns
  From a PatternList, makes a proper list of the patterns in them.
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


@{-
  - Extracts out the subpatterns of productions in a patternList, but in a way that doesn't demand attributes.
  - @param pl The patternList List-like construct we're extracting subpatterns from.
  - @return A patternList List-like construct containing only the subpatterns of the list that was provided.
  - @warning Note that the subpatterns being extracted here are only applications of productions.
-}
function extractSubPatternListsFromProdPatterns
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


@{-
  - Takes in a regular list of Expr, turns them into an instance of the Exprs production.
  - @param l A list of Expr's.
  - @param defaultLoc The default location to provide for the ExprsEmpty production (nil-like construct).
  - @return A combined Exprs List-like construct made from the Expr's in the input list.
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


@{-
  - Takes in a regular list of MatchRule, turns them into an instance of the MRuleList production.
  - @param l A list of match rules.
  - @return A MRuleList list-like construct from the list of match rules.
-}
function makeMRuleListFromListMatchRules
MRuleList ::= l::[MatchRule]
{
  return foldrLastElem(
   \leftelem::MatchRule accum::MRuleList -> mRuleList_cons(leftelem,'|',accum, location=leftelem.location),
   \a::MatchRule -> mRuleList_one(a,location=a.location),
   l);

}

@{-
  - Given a MRuleList element, transforms it into a regular list of MatchRules
  - @param l A List-like construct MRuleList instance.
  - @param accum An accumulated list of MatchRule's
  - @return A regular list of MatchRule's
-}
function collectMatchRulesfromMRuleList
[MatchRule] ::= l::MRuleList accum::[MatchRule]
{
  return case l of
  | mRuleList_one(m) -> m::accum
  | mRuleList_cons(h,_,t) -> collectMatchRulesfromMRuleList(t,(h :: accum))
  end;
}

@{-
  - This function goes into a production pattern (if it is one), extracts out the sub pattern for that production, and generates names for each element of that sub pattern.
  - e.g Given `silver_matchRule {foo(bar(3,x),y) -> y+1 }` where `foo`,`bar` are productions, it returns `[_gen1,_gen2]`  (where the numbers are generated from genInt)
  - @param mr An instance of MatchRule
  - @param loc A default location to provide for when we use the patternList_nil production.
  - @return A list of names where each name corresponds to an argument to the production subpattern.
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


@{-
  - This function goes into a production pattern (if it is one), extracts out the sub pattern for that production, and generates wildcard patterns for each param, giving back a
   production pattern with the appropriate number of wildcards.
  - @param mr An instance of MatchRule
  - @param loc A default location to provide for when we use the patternList_nil production.
  - @return A wildcard matchRule with the same number of params as the production pattern.
-}
function makeWildcardsFromMatchRule
PatternList ::= mr::MatchRule loc::Location
{
  local patList::PatternList =
    case mr of
    | matchRule_c(patternList_one(prodAppPattern(_,_,pl,_)),_,_) -> pl
    | matchRuleWhen_c(patternList_one(prodAppPattern(_,_,pl,_)),_,_,_,_) -> pl
    | matchRuleWhenMatches_c(patternList_one(prodAppPattern(_,_,pl,_)),_,_,_,_,_,_) -> pl
    | _ -> patternList_nil(location=loc)
    end;

  -- local prodName::QName =
  --   case mr of
  --   | matchRule_c(patternList_one(prodAppPattern(name,_,_,_)),_,_) -> name
  --   | matchRuleWhen_c(patternList_one(prodAppPattern(name,_,_,_)),_,_,_,_) -> name
  --   | matchRuleWhenMatches_c(patternList_one(prodAppPattern(name,_,_,_)),_,_,_,_,_,_) -> name
  --   | _ -> error("This shouldnt be seen by the user.")
  --   end;

  local patListOut::PatternList =
    (makePatternListfromListofPatterns(
      map(\pat::Pattern ->
        wildcPattern('_', location=loc),
        -- name("__generated_" ++ toString(genInt()), loc),
        collectPatternsFromPatternList(patList,[])),
        loc));

  return patListOut;

}


@{-
  - This function takes in a name and location and returns a concrete definition LHS element that is the result of applying the concrete definition production to them.
  - @param name The name being defined.
  - @param loc the location of the definition.
  - @return a concrete definition LHS element that uses the name and location provided.
-}
function makeDefinitionLHSFromName
DefLHS ::= name::Name loc::Location
{
  return concreteDefLHS(qNameId(name,location=loc), location=loc);
}

function makeLetExprForTopRenaming
Expr ::= newName::Name aspectLHS::Decorated ConvAspectLHS e::Expr loc::Location
{
    return letp(
      assignExpr(
        newName,
        '::',
        aspectLHS.aspectType,
        '=',
        baseExpr(qNameId(aspectLHS.aspectName,location=loc),location=loc),
        location=loc),
      e,
      location=loc);

}


function makeWildCardExprFromPatternList
Expr ::= patList::PatternList aspectLHS::Decorated ConvAspectLHS e::Expr loc::Location
{
  return case patList of
  | patternList_one(wildcPattern(_)) -> e
  | patternList_more(wildcPattern(_),_,_) -> e
  | patternList_one(varPattern(name)) -> makeLetExprForTopRenaming(name, aspectLHS, e, loc)
  | patternList_more(varPattern(name),_,_) -> makeLetExprForTopRenaming(name, aspectLHS, e, loc)
  | _ -> errorExpr([],location=loc)
  end;
}






--TODO: Add wildcard expression parameter
-- Add function to generate production patterns with wildcards in every arg (should need only length)
-- Use that function, plus the wildcard expression param to add an extra case to the param list.
-- Might even be able to just shove that param as another matchrule into the matchrule list without even dealing with passing the args in,
-- in this case making a rule like so: matchRule_c(patternList_one(prodAppPattern(name,_,_,_)),_,e), and shoving that
-- in before we pass the RuleList in.


@{-
  - @param rules A list of matchrules that represents a grouping of match rules with similar patterns.
  - @param aspectLHS a convenience aspect LHS expression that contains the name and type of the term that our generated aspect production returns.
  - @param aspectAttr The aspect attribute we're generating productions for
  - @param eqKind The operator that assigns or binds to the attribute
  - @param location The location where the aspect pattern is defined
  - @param env A decorated environment for looking up production types.
  - @return A pair of a single AgDcl that defines the aspect production we're generating, and a list of warnings or errors that came from generating the AgDcl.
-}
function extractAspectAgDclFromRuleList
Pair<AGDcl [Message]> ::= rules::[MatchRule] aspectLHS::Decorated ConvAspectLHS aspectAttr::QNameAttrOccur  eqKind::ConvenienceAspectEquationKind location::Location env::Decorated Env
{

  local lookupProdInputTypes::([Type] ::= String) = \prodName::String ->
      case (getValueDcl(prodName,env)) of
      | [] -> []
      | dcl:: _ ->
        let dcl :: Decorated DclInfo with {givenNonterminalType} =
          decorate dcl with {givenNonterminalType = error("Not actually needed");}
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
        extractSubPatternListsFromProdPatterns(pl),
        arrow,
        e,
        location=location)
      | matchRuleWhen_c(pl,whenKWD,cond,arrow,e) ->
        matchRuleWhen_c(
          extractSubPatternListsFromProdPatterns(pl),
          whenKWD,
          cond,
          arrow,
          e,
          location=location)
      | matchRuleWhenMatches_c(pl,whenKWD,cond,matches,p,arrow,e) ->
        matchRuleWhenMatches_c(
          extractSubPatternListsFromProdPatterns(pl),
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
  -- can use the list of patterns there against the parameters)
  local makeParamsCaseExpr::(Expr ::= [Expr] [MatchRule]) =
    \paramsCaseSubExpr::[Expr] mRules::[MatchRule] ->
      caseExpr_c(
        'case',
        makeExprsFromExprList(paramsCaseSubExpr,location),
        'of',
        terminal(Opt_Vbar_t, "|"),
        makeMRuleListFromListMatchRules(transformPatternMatchRule(mRules)),
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
              makeLetExprForTopRenaming(name, aspectLHS, e, head(rules).location),
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
              makeLetExprForTopRenaming(name, aspectLHS, e, head(rules).location),
              head(rules).location)}}
      },
      [])
    | _ ->
      pair(
        emptyAGDcl(location=location),
        [err(location,"Patterns in aspect convenience syntax should be productions,wildcards, or varpatterns only")])
    end;
}



@{-
  - Compares patterns, if they're both production patterns, compares production name otherwise compares the kind of pattern, (varname or wildcard, mostly).
  - As a note, patterns with kinds other than varPattern,Wildcard, or prodAppPattern compare favorably with eachother even if they dont have the same kind, as this function is intended to sort patterns for convenience aspect purposes.
  - @param l first pattern we're comparing
  - @param r second pattern we're comparing
  - @return boolean telling us if two production patterns have the same name, otherwise compares the kind of pattern.
-}
function eqProdNamePattern
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


@{-
  - Extracts out the head pattern from the given PatternList.
  - @param pList a PatternList construct
  - @return The head pattern from the given PatternList
  - @warning throws an error if the pattern list is nil.
-}
function extractHeadPatternFromPatternList
Pattern ::= pList::PatternList
{
  return case pList of
  | patternList_one(patHead) -> patHead
  | patternList_more(patHead,_,_) -> patHead
  | patternList_snoc(ps,_,_) -> extractHeadPatternFromPatternList(ps)
  | patternList_nil() -> error("No head pattern in patternList_nil()")
  end;
}



@{-
  - Extracts out the head pattern from the given matchRule.
  - @param mRule a MatchRule construct
  - @return The head pattern from the given MatchRule
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


@{-
  - Compares the head pattern of two match rules, but without demanding attributes
  - Modeled after comparison used for AbstractMatchRules
  - @param l first match rule
  - @param r second match rule
  - @return Boolean telling us if the head pattern of two match rules uses the same production (or are equivalent in terms of being a wildcard or varpattern).
-}
function eqHeadPatternMatchRule
Boolean ::= l::MatchRule r::MatchRule
{
  -- The reason this isn't done with attributes is that demanding attributes
  -- (from silver core lang elements) has caused infinite loops and mwda errors
  -- for me (due to me working with concrete syntax elements, I'm presuming), so I've been
  -- avoiding demanding attributes as much as possible.
  return
    eqProdNamePattern(
      extractHeadPatternFromMatchRule(l),
      extractHeadPatternFromMatchRule(r));
}


@{-
  - @param mRule a MatchRule construct
  - @return Boolean indicating whether its a wildcard match rule
  - Given a MatchRule, tells you if its a "wildcard" match rule.
  - varpatterns aren't called wildcards, but they match everything just the same so they return true here.
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



@{-
  - Gives back a single AgDcl defining all the aspect productions according to the parameters given.
  - This is the abstract production for convenience aspects.
  - It's generally advised if you intend to use convenience aspects to use them as concrete syntax (using the concrete production starting with `aspect <attr> on ...` )
  - @param attr The attribute for which you'd like to define aspect productions for.
  - @param aspectLHS a convenience aspect LHS expression that contains the name and type of the term that our generated aspect production returns.
  - @param eqKind The operator that assigns or binds to the attribute
  - @param ml The Match Rules that define what aspects we'd like to generate.
-}
abstract production convenienceAspects
top::AGDcl ::= attr::QNameAttrOccur aspectLHS::Decorated ConvAspectLHS eqKind::ConvenienceAspectEquationKind ml::MRuleList
{
  top.defs := [];
  top.moduleNames := [];
  top.occursDefs := [];

  top.unparse = "aspect " ++ attr.unparse ++ " on " ++ aspectLHS.unparse ++ " " ++ eqKind.unparse ++ " of |" ++ ml.unparse ++ " end";


  -- local makeLetExprForTopRenaming::(Expr ::= Name Expr Location) = \newName::Name e::Expr loc::Location ->
  --   letp(
  --     assignExpr(
  --       newName,
  --       '::',
  --       aspectLHS.aspectType,
  --       '=',
  --       baseExpr(qNameId(aspectLHS.aspectName,location=loc),location=loc),
  --       location=loc),
  --     e,
  --     location=loc);

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
  local groupedMRules::[[MatchRule]] = groupBy(eqHeadPatternMatchRule, mListUpToFirstWildcard);

  -- adds a wildcard to each non-wildcard section if available.
  local groupedMRulesWithExtraWildcards::[[MatchRule]] =
    case mListWildcardAndAfter of
    | [] -> groupedMRules
    | matchRule_c(patList,_,e) :: _ -> map(
      \mList::[MatchRule] ->
        case mList of
        | matchRule_c(patternList_one(prodAppPattern(name,leftparen,patternList,rightparen)),arrow,_) :: _ ->
          let wildCardPatternList :: PatternList = makeWildcardsFromMatchRule(head(mList),(head(mListWildcardAndAfter)).location)
          in
            let expr :: Expr = makeWildCardExprFromPatternList(patList, aspectLHS, e, (head(mListWildcardAndAfter)).location)
            in
            mList ++ [matchRule_c(patternList_one(prodAppPattern(name,leftparen,wildCardPatternList,rightparen, location=(head(mListWildcardAndAfter)).location),location=(head(mListWildcardAndAfter)).location),arrow,expr,location=(head(mListWildcardAndAfter)).location)]
            end
          end
        | matchRule_c(patternList_more(prodAppPattern(name,_,wildCardPatternList,_),_,_),arrow,_) :: _ ->
          let wildCardPatternList :: PatternList = makeWildcardsFromMatchRule(head(mList),(head(mListWildcardAndAfter)).location)
          in
            let expr :: Expr = makeWildCardExprFromPatternList(patList, aspectLHS, e, (head(mListWildcardAndAfter)).location)
            in
            mList ++ [matchRule_c(
              patternList_one(
                prodAppPattern(
                  name,
                  '(',
                  wildCardPatternList,
                  ')',
                  location=(head(mListWildcardAndAfter)).location),
                location=(head(mListWildcardAndAfter)).location),
              arrow,
              expr,
              location=(head(mListWildcardAndAfter)).location)]
            end
          end
        | otherwise -> otherwise
        end,
        groupedMRules)
    | firstRule::rest -> groupedMRules
    end;


  local groupExtractResults::[Pair<AGDcl [Message]>] = map(
    extractAspectAgDclFromRuleList(_,aspectLHS,attr,eqKind,top.location, top.env),
    groupedMRulesWithExtraWildcards);

  local groupExtractErrors::[Message] = foldr(append, [], (map(snd(_), groupExtractResults)));



  local combinedAspectProds::[AGDcl] = map(fst(_),groupExtractResults);

  local combinedAspectDcls::AGDcls = foldr(
   consAGDcls(_,_,location=top.location),
   nilAGDcls(location=top.location),
   combinedAspectProds);


  local fwrd::AGDcl = makeAppendAGDclOfAGDcls(combinedAspectDcls);


  top.errors <- if null(mListAfterWildcard)
                -- This means that nothing is past the wildcard pattern, which is good.
                then groupExtractErrors
                -- Something _is_ past the wildcard pattern
                else [wrn(((head(mListAfterWildcard)).location),"This pattern and the ones that follow are being ignored.")]
                  ++ groupExtractErrors;

  top.errors := filter(
    \message::Message ->
      -- Note: If you see this error unexpectedly that might mean the string for this error has changed.
      case message of
      | err(l, "Pattern has overlapping cases!") when l == top.location -> false
      | _ -> true
      end,
      fwrd.errors);

  forwards to unsafeTracePrint(fwrd,fwrd.unparse);

  -- forwards to makeAppendAGDclOfAGDcls(combinedAspectDcls);
}
