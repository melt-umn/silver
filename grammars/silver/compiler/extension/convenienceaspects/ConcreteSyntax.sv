grammar silver:compiler:extension:convenienceaspects;
import silver:core;
import silver:compiler:modification:collection;
import silver:compiler:extension:constructparser;


function extractAgDclFromRuleList
Pair<AGDcl [Message]> ::= rules::[Decorated AbstractMatchRule] aspectTy::TypeExpr aspectAttr::QNameAttrOccur genDef::(ProductionStmt ::= DefLHS QNameAttrOccur Expr Location) location::Location
{

  local makeProdParamTypes::([Type] ::= Decorated QName) = \prod::Decorated QName ->
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
  local transformPatternMatchRule::([AbstractMatchRule]::=[Decorated AbstractMatchRule]) =
    \mRuleList::[Decorated AbstractMatchRule] ->
      map((\mRule::Decorated AbstractMatchRule -> case mRule of
         | matchRule(pl,cond,e) -> matchRule(
           (foldr(append, [], (map(\pat::Decorated Pattern -> pat.patternSubPatternList, pl)) ) ),
           cond,
           e,
           location=location)
         | _ -> error("This error indicates possible productions for AbstractMatchRule have expanded.")
         end),
         mRuleList);
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
        top::$TypeExpr{aspectTy} ::= $AspectRHS{prodParams}
        { $ProductionStmt{genDef(defTop,aspectAttr,paramsCaseExpr, paramsCaseExpr.location)}}
      };
  local defTop::DefLHS = concreteDefLHS(qNameId(name("top",location), location=location), location=location);
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
          top::$TypeExpr{aspectTy} ::=
          { $ProductionStmt{genDef(defTop,aspectAttr,e,head(rules).location)}}
      },
      [])
    | matchRule(wildcPattern(_)::_,_,e) :: _ ->
      pair(
        Silver_AGDcl {
          aspect default production
          top::$TypeExpr{aspectTy} ::=
          { $ProductionStmt{genDef(defTop,aspectAttr,e,head(rules).location)}}
        },
        [wrn(location, "wildcard patterns after this one are dead code.")])
    | _ ->
      pair(
        error("Patterns in aspect convenience syntax should be productions or wildcards only"),
        [err(location,"Patterns in aspect convenience syntax should be productions or wildcards only")])

    end;
}

synthesized attribute aspectProdParamsList::[Name] occurs on AbstractMatchRule;
synthesized attribute genAspectAttrDef::(ProductionStmt ::= DefLHS QNameAttrOccur Expr Location) occurs on AGDcl;

abstract production convenienceAspects
top::AGDcl ::= attr::QNameAttrOccur ty::TypeExpr ml::MRuleList
{
  top.defs := [];

  -- TODO: Lookup whether attribute is a collection attribute, and error out accordingly
  -- local extractAgDclFromRuleList::(AGDcl ::= [AbstractMatchRule]) =
  --   \rules::[AbstractMatchRule] ->
  --     case rules of
  --     | [matchRule(prodAppPattern(name,_,_,_),)] :: rest ->
  --       foldr( w )


  local groupedMRules::[[Decorated AbstractMatchRule]] =
    map(\rules::[AbstractMatchRule] -> map(\rule_::AbstractMatchRule -> decorate rule_ with { env = top.env; grammarName = top.grammarName; }, rules),
        groupMRules(ml.matchRuleList));
  local groupExtractResults::[Pair<AGDcl [Message]>] = map(extractAgDclFromRuleList(_,ty,attr,top.genAspectAttrDef,top.location), groupedMRules);
  local combinedAspectProds::[AGDcl] = map(fst(_),groupExtractResults);
  local combinedAspectDcls::AGDcls = foldr(
   consAGDcls(_,_,location=top.location),
   nilAGDcls(location=top.location),
   combinedAspectProds);
  local fwrd::AGDcl = makeAppendAGDclOfAGDcls(combinedAspectDcls);

  -- forwards to fwrd ;
  forwards to unsafeTraceDump(fwrd);

}

concrete productions top::AGDcl
| 'aspect' attr::QNameAttrOccur 'on' ty::TypeExpr 'using' '=' 'of' Opt_Vbar_t ml::MRuleList 'end' ';'
{
  top.genAspectAttrDef = attributeDef(_,'.',_,'=',_,';',location=_);
  forwards to convenienceAspects(attr,ty,ml,location=top.location);

}
| 'aspect' attr::QNameAttrOccur 'on' ty::TypeExpr 'using' ':=' 'of' Opt_Vbar_t ml::MRuleList 'end' ';'
{
  top.genAspectAttrDef = attrContainsBase(_,'.',_,':=',_,';',location=_);
  forwards to convenienceAspects(attr,ty,ml,location=top.location);
}
| 'aspect' attr::QNameAttrOccur 'on' ty::TypeExpr 'using' '<-' 'of' Opt_Vbar_t ml::MRuleList 'end' ';'
{
  top.genAspectAttrDef = attrContainsAppend(_,'.',_,'<-',_,';', location=_);
  forwards to convenienceAspects(attr,ty,ml,location=top.location);
}
| 'aspect' attr::QNameAttrOccur 'on' ty::TypeExpr 'of' Opt_Vbar_t ml::MRuleList 'end' ';'
{
  top.genAspectAttrDef = attributeDef(_,'.',_,'=',_,';',location=_);
  forwards to convenienceAspects(attr,ty,ml,location=top.location);
}

aspect default production
top::AGDcl ::=
{
  top.genAspectAttrDef = error("Attribute should not be used in this context.");
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

aspect default production
top::AbstractMatchRule ::=
{
  top.aspectProdParamsList = [];
}


-- autocopy attribute aspectAttr::QNameAttrOccur occurs on MRuleList, MatchRule, PatternList, Pattern;
-- autocopy attribute aspectTy::TypeExpr occurs on MRuleList, MatchRule, PatternList, Pattern;

-- synthesized attribute aspectDcls::AGDcls occurs on MRuleList;

-- aspect production mRuleList_one
-- top::MRuleList ::= m::MatchRule
-- {
--   top.aspectDcls = consAGDcls(m.aspectDcl, nilAGDcls(location=top.location),
--                               location=top.location);
-- }

-- aspect production mRuleList_cons
-- top::MRuleList ::= h::MatchRule '|' t::MRuleList
-- {
--   top.aspectDcls = consAGDcls(h.aspectDcl, t.aspectDcls, location=top.location);
-- }


-- synthesized attribute aspectDcl::AGDcl occurs on MatchRule;

-- aspect production matchRule_c
-- top::MatchRule ::= pt::PatternList _ e::Expr
-- {
--   top.aspectDcl = case pt.patternList of
--   | [pat] -> pat.makeAspect(e)
--   end;
-- }

-- synthesized attribute makeAspect::(AGDcl ::= Expr) occurs on Pattern;

function genIntReally -- zzz
Integer ::= a
{ return genInt(); }


-- aspect production prodAppPattern_named
-- top::Pattern ::= prod::QName '(' ps::PatternList ',' nps::NamedPatternList ')'
-- {
--   local prodParamTypes::[Type] =
--     case prod.lookupValue.typeScheme.typerep of
--     | functionType(_, paramTypes, _) -> paramTypes
--     | _ -> error("invalid production type")
--     end;
--   local prodParamsList::[AspectRHSElem] =
--     map(\ty::Type -> aspectRHSElemFull(name("__generated_" ++ toString(genIntReally(ty)), top.location), ty, location=top.location),
--     prodParamTypes);
--   local prodParamNames::[QName] =
--     map((\asp::AspectRHSElem -> case asp of
--                                 | aspectRHSElemFull(n,_) -> qNameId(n, location=top.location)
--                                 | _ -> qNameId(name("ThisNameShouldntComeUp",top.location), location=top.location)
--                                 end),
--             prodParamsList);
--   local paramCaseSubExpr::Exprs = foldr(
--     \param1::QName accum::Exprs ->
--       exprsCons(
--         baseExpr(param1,location=top.location),
--         ',',
--         accum,
--         location=top.location),
--     exprsEmpty(location=top.location),
--     prodParamNames);
--   local prodParams::AspectRHS = foldr(
--     aspectRHSElemCons(_, _, location=top.location),
--     aspectRHSElemNil(location=top.location),
--     prodParamsList);

--   top.makeAspect = \rhsExpr::Expr ->
--       Silver_AGDcl {
--         aspect production $QName{prod}
--         top::$TypeExpr{top.aspectTy} ::= $AspectRHS{prodParams}
--         { top.$QNameAttrOccur{top.aspectAttr} = $Expr{caseExpr_c('case',
--                                                         paramCaseSubExpr,
--                                                         'of',
--                                                         terminal(Opt_Vbar_t, "|"),
--                                                         mRuleList_one(
--                                                             matchRule_c(ps,
--                                                                         '->',
--                                                                         rhsExpr,
--                                                                         location=top.location),
--                                                             location=top.location),
--                                                         'end',
--                                                         location=top.location)}; }
--       };
-- }

-- aspect production wildcPattern
-- top::Pattern ::= '_'
-- {
--   top.makeAspect = \rhsExpr::Expr ->
--     Silver_AGDcl {
--       aspect default production
--       top::$TypeExpr{top.aspectTy} ::=
--       { top.$QNameAttrOccur{top.aspectAttr} = $Expr{rhsExpr}; }
--     };
-- }
