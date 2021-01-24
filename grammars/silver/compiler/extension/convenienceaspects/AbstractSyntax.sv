grammar silver:compiler:extension:convenienceaspects;



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
  | patternList_more(p,_,ps) -> collectPatternsFromPatternList(ps, p::accum)
  | patternList_nil() -> []
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


function extractAspectAgDclFromRuleList
Pair<AGDcl [Message]> ::= rules::[MatchRule] aspectLHS::ConvAspectLHS aspectAttr::QNameAttrOccur  eqKind::ConvenienceAspectEquationKind location::Location env::Decorated Env
{

  local lookupProdInputTypes::([Type] ::= String) = \prodName::String ->
    if (!null(getValueDclInScope(prodName,env))
       && head(getValueDclInScope(prodName,env)).typeScheme.typerep.isApplicable)
       then
         head(getValueDclInScope(prodName,env)).typeScheme.typerep.inputTypes
    -- Replace with call to function lookup that uses strings.
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
    | matchRule_c(patternList_one(prodAppPattern(name,_,_,_)),_,e) :: _
    ->
    -- I wish let bindings were stable...
    pair(
      makeAspectProduction(
        makeParamsCaseExpr(
            makeParamCaseSubExpr(makeQNamesFromNames(head(rules).aspectProdParamsList)),
            rules),
        name,
        makeProdParams(
            makeProdParamsList(
              head(rules).aspectProdParamsList,
              lookupProdInputTypes(name.name)))),
      [])
    | matchRule_c(patternList_more(prodAppPattern(name,_,_,_),_,_),_,e) :: _
    ->
    -- I wish let bindings were stable...
    pair(
      makeAspectProduction(
        makeParamsCaseExpr(
            makeParamCaseSubExpr(makeQNamesFromNames(head(rules).aspectProdParamsList)),
            rules),
        name,
        makeProdParams(
            makeProdParamsList(
              head(rules).aspectProdParamsList,
              lookupProdInputTypes(name.name)))),
      [])
    | matchRule_c(patternList_one(wildcPattern(_)),_,e) :: _ ->
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
    | matchRule_c(patternList_more(wildcPattern(_),_,_),_,e) :: _ ->
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
    -- | [matchRule(varPattern(name)::_,_,e)] ->
    --   pair(
    --     Silver_AGDcl {
    --       aspect default production
    --       $Name{aspectLHS.aspectName}::$TypeExpr{aspectLHS.aspectType} ::=
    --       { $ProductionStmt{eqKind.makeAspectEquation(
    --           defTop(
    --             aspectLHS.aspectName,
    --             head(rules).location),
    --           aspectAttr,
    --           e,
    --           head(rules).location)}}
    --   },
    --   [])
    -- varPattern(name,...) -> let top = Name in <expr>
    | _ ->
      pair(
        error("Patterns in aspect convenience syntax should be productions or wildcards only"),
        [err(location,"Patterns in aspect convenience syntax should be productions or wildcards only")])

    end;
}

synthesized attribute aspectProdParamsList::[Name] occurs on MatchRule;

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
  | leftPatt ->
    case r of
    | prodAppPattern_named(_,_,_,_,_,_) -> false
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

abstract production convenienceAspects
top::AGDcl ::= attr::QNameAttrOccur aspectLHS::ConvAspectLHS eqKind::ConvenienceAspectEquationKind ml::MRuleList
{
  top.defs := [];
  top.moduleNames := [];

  --check for wildcard varpattern nonsense

  -- local groupedMRules::[[MatchRule]] =
  --   map(\rules::[AbstractMatchRule] -> map(
  --     \rule_::AbstractMatchRule -> decorate rule_ with { env = top.env; }, rules),
  --       groupMRules(ml.matchRuleList));

  local groupedMRules::[[MatchRule]] = groupBy(eqPatternMatchRule, collectMatchRulesfromMRuleList(ml,[]));

  local groupExtractResults::[Pair<AGDcl [Message]>] = map(
    extractAspectAgDclFromRuleList(_,aspectLHS,attr,eqKind,top.location, top.env),
    groupedMRules);

  -- top.errors := foldr(append, [], (map(snd(_), groupExtractResults)));

  local combinedAspectProds::[AGDcl] = map(fst(_),groupExtractResults);

  local combinedAspectDcls::AGDcls = foldr(
   consAGDcls(_,_,location=top.location),
   nilAGDcls(location=top.location),
   combinedAspectProds);

  local fwrd::AGDcl = makeAppendAGDclOfAGDcls(combinedAspectDcls);
  forwards to makeAppendAGDclOfAGDcls(combinedAspectDcls);

}



aspect production matchRule_c
top::MatchRule ::= pt::PatternList '->' e::Expr
{
  top.aspectProdParamsList = case pt of
    | patternList_one(prodAppPattern_named(_, _, ps,_,_,_)) ->
      map(\pat::Pattern ->
        name("__generated_" ++ toString(genInt()), top.location),
        collectPatternsFromPatternList(pt,[]))
    | patternList_more(prodAppPattern_named(_, _, ps,_,_,_),_,_) ->
      map(\pat::Pattern ->
        name("__generated_" ++ toString(genInt()), top.location),
        collectPatternsFromPatternList(pt,[]))
    | _ -> []
    end;

}

aspect production matchRuleWhen_c
top::MatchRule ::= pt::PatternList 'when' cond::Expr '->' e::Expr
{
  top.aspectProdParamsList = case pt of
    | patternList_one(prodAppPattern_named(_, _, ps,_,_,_)) ->
      map(\pat::Pattern ->
        name("__generated_" ++ toString(genInt()), top.location),
        collectPatternsFromPatternList(pt,[]))
    | patternList_more(prodAppPattern_named(_, _, ps,_,_,_),_,_) ->
      map(\pat::Pattern ->
        name("__generated_" ++ toString(genInt()), top.location),
        collectPatternsFromPatternList(pt,[]))
    | _ -> []
    end;

}

aspect default production
top::MatchRule ::=
{
  top.aspectProdParamsList = [];
}
