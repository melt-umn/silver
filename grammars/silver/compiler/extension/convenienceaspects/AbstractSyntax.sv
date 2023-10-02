grammar silver:compiler:extension:convenienceaspects;

import silver:compiler:modification:let_fix;

@{-
  - From a list of Patterns, makes a PatternList with the right list-shaped productions.
  - @param l the list of patterns to modify
  - @return A patternList List-Like Nonterminal instance.
  -}
function makePatternListFromListofPatterns
PatternList ::= l::[Pattern]
{
  return
    foldr(
      \next::Pattern accum::PatternList -> patternList_more(next, ',', accum),
      patternList_nil(),
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
  return makePatternListFromListofPatterns(
    foldr(
      append,
      [],
      (map(
        \pat::Pattern -> case pat of
        | prodAppPattern(_,_,ps,_) -> collectPatternsFromPatternList(ps,[])
        | _ -> []
        end,
        collectPatternsFromPatternList(pl,[])))));
}


@{-
  - Takes in a regular list of Expr, turns them into an instance of the Exprs production.
  - @param l A list of Expr's.
  - @return A combined Exprs List-like construct made from the Expr's in the input list.
-}
function makeExprsFromExprList
Exprs ::= l::[Expr]
{
  return
    if null(l) then exprsEmpty()
    else
      foldrLastElem(
        \leftelem::Expr accum::Exprs -> exprsCons(leftelem,',',accum),
        \elem::Expr -> exprsSingle(elem),
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
   \leftelem::MatchRule accum::MRuleList -> mRuleList_cons(leftelem,'|',accum),
   \a::MatchRule -> mRuleList_one(a),
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
  - @return A list of names where each name corresponds to an argument to the production subpattern.
-}
function makeGeneratedNamesFromMatchRule
[Name] ::= mr::MatchRule
{
  local patList::PatternList =
    case mr of
    | matchRule_c(patternList_one(prodAppPattern(_,_,pl,_)),_,_) -> pl
    | matchRuleWhen_c(patternList_one(prodAppPattern(_,_,pl,_)),_,_,_,_) -> pl
    | matchRuleWhenMatches_c(patternList_one(prodAppPattern(_,_,pl,_)),_,_,_,_,_,_) -> pl
    | _ -> patternList_nil()
    end;

  return
    map(\pat::Pattern ->
      name("__generated_" ++ toString(genInt())),
      collectPatternsFromPatternList(patList,[]));

}


@{-
  - This function goes into a production pattern (if it is one), extracts out the sub pattern for that production, and generates wildcard patterns for each param, giving back a
   production pattern with the appropriate number of wildcards.
  - @param mr An instance of MatchRule
  - @return A wildcard matchRule with the same number of params as the production pattern.
-}
function makeWildcardsFromMatchRule
PatternList ::= mr::MatchRule
{
  local patList::PatternList =
    case mr of
    | matchRule_c(patternList_one(prodAppPattern(_,_,pl,_)),_,_) -> pl
    | matchRuleWhen_c(patternList_one(prodAppPattern(_,_,pl,_)),_,_,_,_) -> pl
    | matchRuleWhenMatches_c(patternList_one(prodAppPattern(_,_,pl,_)),_,_,_,_,_,_) -> pl
    | _ -> patternList_nil()
    end;

  return makePatternListFromListofPatterns(
    map(\pat::Pattern ->
      wildcPattern('_'),
      collectPatternsFromPatternList(patList,[])));
}


@{-
  - This function takes in a name and returns a concrete definition LHS element that is the result of applying the concrete definition production to them.
  - @param name The name being defined.
  - @return a concrete definition LHS element that uses the name provided.
-}
function makeDefinitionLHSFromName
DefLHS ::= name::Name
{
  return concreteDefLHS(qNameId(name));
}

@{-
  - This function takes in a name, aspectRHS, and expr, returning a let expression that binds the name we provide to the name of
  - the "top" term in our aspect production. This allows one to define convenience aspects that make use of variable named wildcards, and
  - have those names be usable in our convenience aspect definition to access other attributes of the top level term.
  - @param newName The name being defined.
  - @param aspectLHS a convenience aspect LHS expression that contains the name and type of the term that our generated aspect production returns.
  - @param e The expression that uses the name we're defining and will be surrounded by let.
  - @return A Let expr that binds the name we provide to the "top" term in our aspect production with let, and surrounds the expression we gave.
-}
function makeLetExprForTopRenaming
Expr ::= newName::Name aspectLHS::Decorated ConvAspectLHS e::Expr
{
    return letp(
      assignExpr(
        newName,
        '::',
        aspectLHS.aspectType,
        '=',
        baseExpr(qNameId(aspectLHS.aspectName))),
      e);

}


@{- @hide
  - @param patList A list of matchrules that represents a grouping of match rules with similar patterns.
  - @param aspectLHS a convenience aspect LHS expression that contains the name and type of the term that our generated aspect production returns.
  - @param e The Expr on the other side of the arrow of the match rule
  - @return An expression from the wildcard matchrule we can use in making convenience aspects.
-}
function makeWildcardExprFromPatternList
Expr ::= patList::PatternList aspectLHS::Decorated ConvAspectLHS e::Expr
{
  return case patList of
  | patternList_one(wildcPattern(_)) -> e
  | patternList_more(wildcPattern(_),_,_) -> e
  | patternList_one(varPattern(name)) -> makeLetExprForTopRenaming(name, aspectLHS, e)
  | patternList_more(varPattern(name),_,_) -> makeLetExprForTopRenaming(name, aspectLHS, e)
  | _ -> errorExpr([])
  end;
}

@{-
  - @param rules A list of matchrules that represents a grouping of match rules with similar patterns.
  - @param aspectLHS a convenience aspect LHS expression that contains the name and type of the term that our generated aspect production returns.
  - @param aspectAttr The aspect attribute we're generating productions for
  - @param eqKind The operator that assigns or binds to the attribute
  - @param env A Environment for looking up production types.
  - @return A pair of a single AgDcl that defines the aspect production we're generating, and a list of warnings or errors that came from generating the AgDcl.
-}
function extractAspectAgDclFromRuleList
Pair<AGDcl [Message]> ::= rules::[MatchRule] aspectLHS::Decorated ConvAspectLHS aspectAttr::QNameAttrOccur  eqKind::ConvenienceAspectEquationKind env::Env
{
  attachNote if null(rules) then logicalLocationFromOrigin(aspectLHS) else logicalLocationFromOrigin(head(rules));

  local lookupProdInputTypes::([Type] ::= String) = \prodName::String ->
      case (getValueDcl(prodName,env)) of
      -- Productions that aren't in scope, and names that
      -- aren't productions will be caught later in the primitive match.
      | [] -> []
      | dcl:: _ ->
          if dcl.typeScheme.typerep.isApplicable
          then dcl.typeScheme.typerep.inputTypes
          else []
      end;

  local makeAspectRHSElemListFromNameAndTypeLists::([AspectRHSElem] ::= [Name] [Type]) =
    zipWith(aspectRHSElemFull, _, _);

  local makeAspectRHSFromParamsList::(AspectRHS ::= [AspectRHSElem] ) =
    foldr(aspectRHSElemCons, aspectRHSElemNil(), _);

  local makeQNamesFromNames::([QName] ::= [Name]) = map(qNameId,_);

  local makeBaseExprFromQNames::([Expr] ::= [QName]) = map(baseExpr,_);

  -- Transforms it to extract a subpattern and bring it up as the
  -- main pattern.
  local transformPatternMatchRule::([MatchRule]::=[MatchRule]) =
    map((\mRule::MatchRule -> case mRule of
      | matchRule_c(pl,arrow,e) -> matchRule_c(
        extractSubPatternListsFromProdPatterns(pl),
        arrow, e)
      | matchRuleWhen_c(pl,whenKWD,cond,arrow,e) ->
        matchRuleWhen_c(
          extractSubPatternListsFromProdPatterns(pl),
          whenKWD, cond, arrow, e)
      | matchRuleWhenMatches_c(pl,whenKWD,cond,matches,p,arrow,e) ->
        matchRuleWhenMatches_c(
          extractSubPatternListsFromProdPatterns(pl),
          whenKWD, cond, matches, p, arrow, e)
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
        makeExprsFromExprList(paramsCaseSubExpr),
        'of',
        terminal(Opt_Vbar_t, "|"),
        makeMRuleListFromListMatchRules(transformPatternMatchRule(mRules)),
        'end');

  -- This function makes our aspect production from the Expression, QName, and AspectRHS
  -- We've generated elsewhere.
  local makeAspectProduction::(AGDcl ::= Expr QName AspectRHS) =
    \paramsCaseExpr::Expr prod::QName prodParams::AspectRHS ->
      Silver_AGDcl {
        aspect production $QName{prod}
        $Name{aspectLHS.aspectName}::$TypeExpr{aspectLHS.aspectType} ::= $AspectRHS{prodParams}
          { $ProductionStmt{
              eqKind.makeAspectEquation(
                makeDefinitionLHSFromName(aspectLHS.aspectName),
                aspectAttr,
                paramsCaseExpr)}}
      };


  return case rules of
    | matchRule_c(patternList_one(prodAppPattern(name,_,_,_)),_,e) :: _
    ->
    -- Handling for production patterns
    let paramNames :: [Name] = makeGeneratedNamesFromMatchRule(head(rules))
    in
    (
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
    let paramNames :: [Name] = makeGeneratedNamesFromMatchRule(head(rules))
    in
    (
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
      (
        Silver_AGDcl {
          aspect default production
          $Name{aspectLHS.aspectName}::$TypeExpr{aspectLHS.aspectType} ::=
          { $ProductionStmt{eqKind.makeAspectEquation(
              makeDefinitionLHSFromName(aspectLHS.aspectName),
              aspectAttr,
              e)}}
      },
      [])
    | matchRule_c(patternList_more(wildcPattern(_),_,_),_,e) :: _ ->
      (
        Silver_AGDcl {
          aspect default production
          $Name{aspectLHS.aspectName}::$TypeExpr{aspectLHS.aspectType} ::=
          { $ProductionStmt{eqKind.makeAspectEquation(
              makeDefinitionLHSFromName(aspectLHS.aspectName),
              aspectAttr,
              e)}}
      },
      [])
      -- Handling for varpatterns
    | matchRule_c(patternList_one(varPattern(name)),_,e) :: _ ->
      (
        Silver_AGDcl {
          aspect default production
          $Name{aspectLHS.aspectName}::$TypeExpr{aspectLHS.aspectType} ::=
          { $ProductionStmt{eqKind.makeAspectEquation(
              makeDefinitionLHSFromName(aspectLHS.aspectName),
              aspectAttr,
              makeLetExprForTopRenaming(name, aspectLHS, e))}}
      },
      [])
    | matchRule_c(patternList_more(varPattern(name),_,_),_,e) :: _ ->
      (
        Silver_AGDcl {
          aspect default production
          $Name{aspectLHS.aspectName}::$TypeExpr{aspectLHS.aspectType} ::=
          { $ProductionStmt{eqKind.makeAspectEquation(
              makeDefinitionLHSFromName(
                aspectLHS.aspectName),
              aspectAttr,
              makeLetExprForTopRenaming(name, aspectLHS, e))}}
      },
      [])
    | _ ->
      (
        emptyAGDcl(),
        [errFromOrigin(ambientOrigin(),"Patterns in aspect convenience syntax should be productions,wildcards, or varpatterns only")])
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
function isWildcardMatchRule
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
  top.moduleNames := [];
  top.unparse = "aspect " ++ attr.unparse ++ " on " ++ aspectLHS.unparse ++ " " ++ eqKind.unparse ++ " of |" ++ ml.unparse ++ " end";


  -- Everything past the first wildcard (or varpattern) gets dropped before grouping of match patterns
  local mList::[MatchRule] = reverse(collectMatchRulesfromMRuleList(ml,[]));
  local mListUpToFirstWildcard::[MatchRule] =
    foldr(\next::MatchRule accum::[MatchRule] ->
      if !isWildcardMatchRule(next) then next::accum else [next],
      [],
      mList);
  local mListWildcardAndAfter::[MatchRule] =
    dropWhile(\mRule::MatchRule -> !isWildcardMatchRule(mRule),mList);
  local mListAfterWildcard::[MatchRule] =
    if null(mListWildcardAndAfter) then [] else tail(mListWildcardAndAfter);

  -- groups MatchRules by their kind, which for our purposes is production name, wildcard, or varpattern.
  local groupedMRules::[[MatchRule]] = groupBy(eqHeadPatternMatchRule, mListUpToFirstWildcard);

  -- adds a wildcard to each non-wildcard section if available.
  local groupedMRulesWithExtraWildcards::[[MatchRule]] =
    case mListWildcardAndAfter of
    | [] -> groupedMRules
    | firstRule :: _ when firstRule matches matchRule_c(patList,_,e) -> map(
      \mList::[MatchRule] -> attachNote logicalLocationFromOrigin(firstRule) on
          case mList of
          | matchRule_c(patternList_one(prodAppPattern(name,leftparen,patternList,rightparen)),arrow,_) :: _ ->
            let wildcardPatternList :: PatternList = makeWildcardsFromMatchRule(head(mList))
            in
              let expr :: Expr = makeWildcardExprFromPatternList(patList, aspectLHS, e)
              in
              mList ++ [matchRule_c(patternList_one(prodAppPattern(name,leftparen,wildcardPatternList,rightparen)),arrow,expr)]
              end
            end
          | matchRule_c(patternList_more(prodAppPattern(name,_,wildcardPatternList,_),_,_),arrow,_) :: _ ->
            let wildcardPatternList :: PatternList = makeWildcardsFromMatchRule(head(mList))
            in
              let expr :: Expr = makeWildcardExprFromPatternList(patList, aspectLHS, e)
              in
              mList ++ [matchRule_c(patternList_one(prodAppPattern(name, '(', wildcardPatternList, ')')), arrow, expr)]
              end
            end
          | otherwise -> otherwise
          end
        end,
        groupedMRules)
    | firstRule::rest -> groupedMRules
    end;


  local groupExtractResults::[Pair<AGDcl [Message]>] = map(
    extractAspectAgDclFromRuleList(_,aspectLHS,attr,eqKind,top.env),
    groupedMRulesWithExtraWildcards);

  local groupExtractErrors::[Message] = foldr(append, [], (map(snd(_), groupExtractResults)));



  local combinedAspectProds::[AGDcl] = map(fst(_),groupExtractResults);

  local combinedAspectDcls::AGDcls = foldr(
   consAGDcls(_,_),
   nilAGDcls(),
   combinedAspectProds);


  top.errors <- if null(mListAfterWildcard)
                -- This means that nothing is past the wildcard pattern, which is good.
                then groupExtractErrors
                -- Something _is_ past the wildcard pattern
                else [wrnFromOrigin(head(mListAfterWildcard),"This pattern and the ones that follow are being ignored.")]
                  ++ groupExtractErrors;

  -- Errors are filtered out here in a move we call in the business "an infelicity"
  -- The errors here arise from inserting the first wildcard pattern we find into
  -- the non-default aspect productions we generate. This means that we get semantics
  -- of not having incomplete cases in some productions but because we can't check case completeness from
  -- The convenience aspects side it produces more errors about overlapping cases, causing us to filter here.
  -- If pattern matching and case completeness change this approach might no longer be necessary.
  top.errors := filter(
    \message::Message ->
      -- Note: If you see this error unexpectedly that might mean the string for this error has changed.
      case message of
      | err(l, "Pattern has overlapping cases!")
        when contains(l, map(getParsedOriginLocationOrFallback, mList)) -> false
      | _ -> true
      end,
      forward.errors);

  forwards to makeAppendAGDclOfAGDcls(combinedAspectDcls);
}
