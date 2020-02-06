grammar silver:extension:patternmatching;

imports silver:definition:core;
imports silver:definition:env;
imports silver:definition:type;
imports silver:modification:primitivepattern;

import silver:definition:type:syntax only typerepTypeExpr;
import silver:modification:let_fix;

terminal Case_kwd 'case' lexer classes {KEYWORD,RESERVED};
terminal Of_kwd 'of' lexer classes {KEYWORD,RESERVED};
terminal Arrow_kwd '->' lexer classes {SPECOP};
terminal Vbar_kwd '|' lexer classes {SPECOP};
terminal Opt_Vbar_t /\|?/ lexer classes {SPECOP}; -- optional Coq-style vbar.
terminal When_kwd 'when' lexer classes {KEYWORD,RESERVED};

-- MR | ...
nonterminal MRuleList with location, config, unparse, env, errors, matchRuleList, matchRulePatternSize;

-- Turns MRuleList (of MatchRules) into [AbstractMatchRule]
synthesized attribute matchRuleList :: [AbstractMatchRule];
-- Notification of the number of expressions being matched upon
autocopy attribute matchRulePatternSize :: Integer;

-- P -> E
nonterminal MatchRule with location, config, unparse, env, errors, matchRuleList, matchRulePatternSize;
nonterminal AbstractMatchRule with location, headPattern, isVarMatchRule, expandHeadPattern;

-- The head pattern of a match rule
synthesized attribute headPattern :: Decorated Pattern;
-- Whether the head pattern of a match rule is a variable binder or not
synthesized attribute isVarMatchRule :: Boolean;
-- Turns A(B, C), D into B, C, D in the patterns list, with a list of named patterns to include.
synthesized attribute expandHeadPattern :: (AbstractMatchRule ::= [String]);

-- P , ...
nonterminal PatternList with location, config, unparse, patternList, env, errors;

-- Turns PatternList into [Pattern]
synthesized attribute patternList :: [Decorated Pattern];


{- NOTE ON ERRORS: #HACK2012
 -
 - All of the real error checking should be done in PrimitiveMatch.sv on the
 - more primitive form of pattern matching. BUT, there are a few
 - kinds of errors that the pattern matching compiler will OBSCURE
 - and so we must check for them here.
 -
 - ANY error on MRuleList, MatchRule, PatternList, or Pattern should
 - be accompanied by a comment explaining why it's there, and not on
 - primitive match.
 -}


concrete production caseExpr_c
top::Expr ::= 'case' es::Exprs 'of' Opt_Vbar_t ml::MRuleList 'end'
{
  top.unparse = "case " ++ es.unparse ++ " of " ++ ml.unparse ++ " end";

  ml.matchRulePatternSize = length(es.rawExprs);
  top.errors <- ml.errors;
  
  -- TODO: this is the only use of .rawExprs. FIXME
  -- introduce the failure case here.
  forwards to 
    caseExpr(es.rawExprs, ml.matchRuleList, 
      mkStrFunctionInvocation(top.location, "core:error",
        [stringConst(terminal(String_t, 
          "\"Error: pattern match failed at " ++ top.grammarName ++ " " ++ top.location.unparse ++ "\\n\""), location=top.location)]),
      freshType(), location=top.location);
}

abstract production caseExpr
top::Expr ::= es::[Expr] ml::[AbstractMatchRule] failExpr::Expr retType::Type
{
  top.unparse = error("Internal error: pretty of intermediate data structure");

  -- 4 cases: no patterns left, all constructors, all variables, or mixed con/var.
  -- errors cases: more patterns no scrutinees, more scrutinees no patterns, no scrutinees multiple rules
  forwards to
    case ml of
    | matchRule([], c, e) :: _ -> buildMatchWhenConditionals(ml, failExpr) -- valid or error case
    | _ -> if null(es) then failExpr -- error case
           else if null(varRules) then allConCase
           else if null(prodRules) then allVarCase
           else mixedCase
    end;
  -- TODO: BUG: we're using the left of patterns in the first match rule as a guide here
  -- which means we run into serious problems if not all match rules agree on the length
  -- of the pattern list. We don't report some errors related to not having enough
  -- variable binders
  
  top.errors <-
    case ml of
    -- are there multiple match rules, with no patterns/conditions left in them to distinguish between them?
    | matchRule([], _, e) :: _ :: _ ->
      if areUselessPatterns(ml)
      then [err(top.location, "Pattern has overlapping cases!")]
      else []
    | _ -> []
    end;
       
--  top.errors <- unsafeTrace([], 
--     print(top.unparse ++ "\n\n", unsafeIO()));

  local partMRs :: Pair<[AbstractMatchRule] [AbstractMatchRule]> =
    partition((.isVarMatchRule), ml);
  local varRules :: [AbstractMatchRule] = partMRs.fst;
  local prodRules :: [AbstractMatchRule] = partMRs.snd;
  
  {--
   - All constructors? Then do a real primitive match.
   -}
  local freshCurrName :: String = "__curr_match_" ++ toString(genInt());
  local freshCurrNameRef :: Expr =
    baseExpr(qName(top.location, freshCurrName), location=top.location);
  local allConCase :: Expr =
    -- Annoyingly, this now needs to be a let in case of annotation patterns.
    makeLet(top.location,
      freshCurrName, freshType(), head(es), 
      matchPrimitive(
        freshCurrNameRef,
        typerepTypeExpr(retType, location=top.location),
        foldPrimPatterns(
          map(allConCaseTransform(freshCurrNameRef, tail(es), failExpr, retType, _),
          groupMRules(prodRules))),
        failExpr, location=top.location));
  
  {--
   - All variables? Just push a let binding inside each branch.
   -}
  local allVarCase :: Expr =
    caseExpr(tail(es),
      map(bindHeadPattern(head(es), freshType(){-whatever the first expression's type is?-}, _),
        ml),
      failExpr, retType, location=top.location);
      -- A quick note about that freshType() hack: putting it here means there's ONE fresh type
      -- generated, puching it inside 'bindHeadPattern' would generate multiple fresh types.
      -- So don't try that!
  
  {--
   - Mixed con/var? Partition, and push the vars into the "fail" branch.
   - Use a let for it, to avoid code duplication!
   -}
  local freshFailName :: String = "__fail_" ++ toString(genInt());
  local mixedCase :: Expr =
    makeLet(top.location,
      freshFailName, retType, caseExpr(es, varRules, failExpr, retType, location=top.location),
      caseExpr(es, prodRules, baseExpr(qName(top.location, freshFailName), location=top.location),
        retType, location=top.location));
}

concrete production mRuleList_one
top::MRuleList ::= m::MatchRule
{
  top.unparse = m.unparse;
  top.errors := m.errors;  

  top.matchRuleList = m.matchRuleList;
}

concrete production mRuleList_cons
top::MRuleList ::= h::MatchRule '|' t::MRuleList
{
  top.unparse = h.unparse ++ " | " ++ t.unparse;
  top.errors := h.errors ++ t.errors;
  
  top.matchRuleList = h.matchRuleList ++ t.matchRuleList;
}

concrete production matchRule_c
top::MatchRule ::= pt::PatternList '->' e::Expr
{
  top.unparse = pt.unparse ++ " -> " ++ e.unparse;
  top.errors := pt.errors; -- e.errors is examined later, after transformation.
  
  top.errors <-
    if length(pt.patternList) == top.matchRulePatternSize then []
    else [err(pt.location, "case expression matching against " ++ toString(top.matchRulePatternSize) ++ " values, but this rule has " ++ toString(length(pt.patternList)) ++ " patterns")];

  top.matchRuleList = [matchRule(pt.patternList, nothing(), e, location=top.location)];
}

concrete production matchRuleWhen_c
top::MatchRule ::= pt::PatternList 'when' cond::Expr '->' e::Expr
{
  top.unparse = pt.unparse ++ " when " ++ cond.unparse ++ " -> " ++ e.unparse;
  top.errors := pt.errors; -- e.errors is examined later, after transformation, as is cond.errors
  
  top.errors <-
    if length(pt.patternList) == top.matchRulePatternSize then []
    else [err(pt.location, "case expression matching against " ++ toString(top.matchRulePatternSize) ++ " values, but this rule has " ++ toString(length(pt.patternList)) ++ " patterns")];

  top.matchRuleList = [matchRule(pt.patternList, just(cond), e, location=top.location)];
}

abstract production matchRule
top::AbstractMatchRule ::= pl::[Decorated Pattern] cond::Maybe<Expr> e::Expr
{
  top.headPattern = head(pl);
  -- If pl is null, and we're consulted, then we're missing patterns, pretend they're _
  top.isVarMatchRule = null(pl) || head(pl).patternIsVariable;
  -- For this, we safely know that pl is not null:
  top.expandHeadPattern = 
    \ named::[String] ->
      matchRule(
        head(pl).patternSubPatternList ++
        map(
          \ n::String ->
            fromMaybe(
              decorate wildcPattern('_', location=top.location)
                with { config=head(pl).config; env=head(pl).env; },
              lookupBy(stringEq, n, head(pl).patternNamedSubPatternList)),
          named) ++
        tail(pl),
        cond, e, location=top.location);
}

concrete production patternList_one
top::PatternList ::= p::Pattern
{
  top.unparse = p.unparse;
  top.errors := p.errors;

  top.patternList = [p];
}
concrete production patternList_snoc
top::PatternList ::= ps::PatternList ',' p::Pattern 
{
  top.unparse = ps.unparse ++ ", " ++ p.unparse;
  
  forwards to appendPatternList(ps, patternList_one(p, location=p.location));
}
abstract production patternList_more
top::PatternList ::= p::Pattern ',' ps1::PatternList
{
  top.unparse = p.unparse ++ ", " ++ ps1.unparse;
  top.errors := p.errors ++ ps1.errors;

  top.patternList = p :: ps1.patternList;
}

-- lol, dangling comma bug TODO
concrete production patternList_nil
top::PatternList ::=
{
  top.unparse = "";
  top.errors := [];

  top.patternList = [];
}

----------------------------------------------------
-- Added Functions
----------------------------------------------------
function appendPatternList
PatternList ::= p1::PatternList p2::PatternList
{
  return
    case p1 of
    | patternList_more(h, _, t) ->
      patternList_more(h, ',', appendPatternList(t, p2), location=p1.location)
    | patternList_one(h) ->
      patternList_more(h, ',', p2, location=p1.location)
    | patternList_nil() -> p2
    end;
}

function patternListVars
Name ::= p::Decorated Pattern
{
  local n :: String =
    case p of
    | varPattern(pvn) -> "__sv_pv_" ++ toString(genInt()) ++ "_" ++ pvn.name
    | h -> "__sv_tmp_pv_" ++ toString(genInt())
    end;
  return name(n, p.location);
}
function convStringsToVarBinders
VarBinders ::= s::[Name] l::Location
{
  return if null(s) then nilVarBinder(location=l)
         else if null(tail(s)) then oneVarBinder(varVarBinder(head(s), location=head(s).location), location=l)
         else consVarBinder(varVarBinder(head(s), location=head(s).location), ',', convStringsToVarBinders(tail(s), l), location=l);
}
function exprFromName
Expr ::= n::Name
{
  return baseExpr(qNameId(n, location=n.location), location=n.location);
}

{--
 - Takes a set of matchrules that all match against the SAME CONSTRUCTOR and pushes
 - a complex case-expr within a primitive pattern that matches this constructor.
 -
 - @param currExpr  (The current expression to match against in the overall complex case-expr)
 - @param restExprs  (The remaining expressions to match against in the overall complex case-expr)
 - @param failCase  (The failure expression)
 - @param retType  (The return type of the overall case-expr, and thus this)
 - @param mrs  (Match rules that all share the same head-pattern)
 -
 - @return  A primitive pattern matching the constructor, with the overall case-expr pushed down into it
 -}
function allConCaseTransform
PrimPattern ::= currExpr::Expr restExprs::[Expr]  failCase::Expr  retType::Type  mrs::[AbstractMatchRule]
{
  -- TODO: potential source of buggy error messages. We're using head(mrs) as the source of
  -- authority for the length of pattern variables to match against. But each match rule may
  -- actually have a different length (and .expandHeadPattern just applies whatever is there)
  -- This is an erroneous condition, but it means we transform into a maybe-more erroneous condition.
  local names :: [Name] = map(patternListVars, head(mrs).headPattern.patternSubPatternList);

  local subcase :: Expr =
    caseExpr(
      map(exprFromName, names) ++ annoAccesses ++ restExprs,
      map(\ mr::AbstractMatchRule -> mr.expandHeadPattern(annos), mrs),
      failCase, retType, location=head(mrs).location);
  -- TODO: head(mrs).location is probably not the correct thing to use here?? (generally)

  local annos :: [String] =
    nubBy(stringEq, map(fst, flatMap((.patternNamedSubPatternList), map((.headPattern), mrs))));
  local annoAccesses :: [Expr] =
    map(\ n::String -> access(currExpr, '.', qNameAttrOccur(qName(l, n), location=l), location=l), annos);
  
  -- Maybe this one is more reasonable? We need to test examples and see what happens...
  local l :: Location = head(mrs).headPattern.location;

  return
    case head(mrs).headPattern of
    | prodAppPattern_named(qn,_,_,_,_,_) -> 
        prodPattern(qn, '(', convStringsToVarBinders(names, l), ')', '->', subcase, location=l)
    | intPattern(it) -> integerPattern(it, '->', subcase, location=l)
    | fltPattern(it) -> floatPattern(it, '->', subcase, location=l)
    | strPattern(it) -> stringPattern(it, '->', subcase, location=l)
    | truePattern(_) -> booleanPattern("true", '->', subcase, location=l)
    | falsePattern(_) -> booleanPattern("false", '->', subcase, location=l)
    | nilListPattern(_,_) -> nilPattern(subcase, location=l)
    | consListPattern(h,_,t) -> conslstPattern(head(names), head(tail(names)), subcase, location=l)
    end;
}

function foldPrimPatterns
PrimPatterns ::= l::[PrimPattern]
{
  return if null(tail(l)) then onePattern(head(l), location=head(l).location)
         else consPattern(head(l), '|', foldPrimPatterns(tail(l)), location=head(l).location);
}

{--
 - Remove the first pattern from the rule, and put a let binding of it into
 - the expression.
 -
 - Would like to make this an attribute instead of a function, but
 - (a) we don't have lambdas yet, and the attr would need to be a function value
 - (b) we don't have a nice way of applying to all element of a list of functions
 -     e.g. right now we 'map(this(x, y, _), list)'
 -}
function bindHeadPattern
AbstractMatchRule ::= headExpr::Expr  headType::Type  rule::AbstractMatchRule
{
  -- If it's '_' we do nothing, otherwise, bind away!
  return case rule of
  | matchRule(headPat :: restPat, cond, e) ->
      matchRule(restPat,
        case headPat.patternVariableName, cond of
        | just(pvn), just(c) -> just(makeLet(rule.location, pvn, headType, headExpr, c))
        | _, _ -> cond
        end,
        case headPat.patternVariableName of
        | just(pvn) -> makeLet(rule.location, pvn, headType, headExpr, e)
        | nothing() -> e
        end, location=rule.location)
  end;
}

function makeLet
Expr ::= l::Location s::String t::Type e::Expr o::Expr
{
  return letp(
    assignExpr(
      name(s, l), '::', typerepTypeExpr(t, location=l), '=', e, location=l),
    o, location=l);
}

function ensureDecoratedExpr
Expr ::= e::Decorated Expr
{
  local et :: Type = performSubstitution(e.typerep, e.upSubst);

  return if et.isDecorable
         then decorateExprWithEmpty('decorate', exprRef(e, location=e.location), 'with', '{', '}', location=e.location)
         else exprRef(e, location=e.location);
}

function mruleEqForGrouping
Boolean ::= a::AbstractMatchRule b::AbstractMatchRule
{
  return a.headPattern.patternSortKey == b.headPattern.patternSortKey;
}
function mruleLTEForSorting
Boolean ::= a::AbstractMatchRule b::AbstractMatchRule
{
  return a.headPattern.patternSortKey <= b.headPattern.patternSortKey;
}
{--
 - Given a list of match rules, examine the "head pattern" of each.
 - Sort and group by the key of this head pattern.
 -
 - i.e. [cons, nil, cons] becomes [[cons, cons], [nil]] (where 'cons' is the key of the head pattern)
 -}
function groupMRules
[[AbstractMatchRule]] ::= l::[AbstractMatchRule]
{
  return groupBy(mruleEqForGrouping, sortBy(mruleLTEForSorting, l));
}

{--
 - Given a list of match rules, which are presumed to match empty
 - patterns (this is not checked), turn them into nested
 - conditionals.
 -}
function buildMatchWhenConditionals
Expr ::= ml::[AbstractMatchRule] failExpr::Expr
{
  return
    case ml of
    | matchRule(_, just(c), e) :: tl ->
      Silver_Expr {
        if $Expr {c}
        then $Expr {e}
        else $Expr {buildMatchWhenConditionals(tl, failExpr)}
      }
    | matchRule(_, nothing(), e) :: tl -> e
    | [] -> failExpr
    end;
}

{--
 - Check whether there are patterns that overlap in a list of match
 - rules which are presumed to match empty patterns (this is not
 - checked here).
 -
 - An answer of true definitively means there are useless patterns.
 - An answer of false means there may be, but it would require
 - analysis of the conditions on the patterns to determine whether
 - they are actually useless.  We do not do that.
 -}
function areUselessPatterns
Boolean ::= ml::[AbstractMatchRule]
{
  return
    case ml of
    | matchRule(_, just(_), _) :: tl ->
      areUselessPatterns(tl)
    | matchRule(_, nothing(), _) :: _ :: _ -> true
    | matchRule(_, nothing(), _) :: [] -> false
    | [] -> false
    end;
}


