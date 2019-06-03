grammar silver:extension:patternmatching;

imports silver:definition:core;
imports silver:definition:env;
imports silver:definition:type;
imports silver:modification:primitivepattern;

import silver:definition:type:syntax only typerepTypeExpr;
import silver:modification:let_fix;

import silver:modification:lambda_fn only lambdap; --for monad stuff

terminal Case_kwd 'case' lexer classes {KEYWORD,RESERVED};
terminal MCase_kwd 'mcase' lexer classes {KEYWORD, RESERVED};
terminal Of_kwd 'of' lexer classes {KEYWORD,RESERVED};
terminal Arrow_kwd '->' lexer classes {SPECOP};
terminal Vbar_kwd '|' lexer classes {SPECOP};
terminal Opt_Vbar_t /\|?/ lexer classes {SPECOP}; -- optional Coq-style vbar.

-- MR | ...
nonterminal MRuleList with location, config, unparse, env, errors, matchRuleList, matchRulePatternSize, patternTypeList, upSubst, downSubst;

-- Turns MRuleList (of MatchRules) into [AbstractMatchRule]
synthesized attribute matchRuleList :: [AbstractMatchRule];
-- Notification of the number of expressions being matched upon
autocopy attribute matchRulePatternSize :: Integer;

-- P -> E
nonterminal MatchRule with location, config, unparse, env, errors, matchRuleList, matchRulePatternSize, patternTypeList;
nonterminal AbstractMatchRule with location, headPattern, isVarMatchRule, expandHeadPattern, typerep, env, downSubst;

-- The head pattern of a match rule
synthesized attribute headPattern :: Decorated Pattern;
-- Whether the head pattern of a match rule is a variable binder or not
synthesized attribute isVarMatchRule :: Boolean;
-- Turns A(B, C), D into B, C, D in the patterns list.
synthesized attribute expandHeadPattern :: AbstractMatchRule;

-- P , ...
nonterminal PatternList with location, config, unparse, patternList, env, errors, patternTypeList;

-- Turns PatternList into [Pattern]
synthesized attribute patternList :: [Decorated Pattern];

-- List of the types of patterns in a single rule for comparing to
--    determine if we are monadically matching something
synthesized attribute patternTypeList::[Type];


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

  {-
    We just need to take the type off the first pattern to compare for
    things used monadically in matching--if this type doesn't match
    the other types, we'll have an error, so we won't output anything,
    and if this type doesn't have any structure, none of them have any
    structure, since unstructured ones only come from unstructured
    patterns.
  -}
  ml.downSubst = top.downSubst;
  local monadInExprs::Pair<Boolean Type> =
    monadicallyUsedExpr(es.rawExprs, ml.patternTypeList, top.env, ml.upSubst);
  local monadInClauses::Pair<Boolean Type> =
    foldl((\p::Pair<Boolean Type> a::AbstractMatchRule ->
            if p.fst
            then p
            else if isMonad(decorate a with {env=top.env; downSubst=ml.upSubst;}.typerep)
                 then pair(true, decorate a with {env=top.env; downSubst=ml.upSubst;}.typerep)
                 else p),
          pair(false, errorType()), --error as filler; won't be used
          ml.matchRuleList);

  local basicFailure::Expr = mkStrFunctionInvocation(top.location, "core:error",
                               [stringConst(terminal(String_t, 
                                  "\"Error: pattern match failed at " ++ top.grammarName ++
                                  " " ++ top.location.unparse ++ "\\n\""),
                                location=top.location)]);
  {-
    This will add in a Fail() for an appropriate monad (if the
    expression is well-typed) whenever we are matching against a monad
    or any clause returns a monad.  This does not cover the case where
    a monad type is expected out and the clauses are incomplete.  That
    one will still fail, but I think that will be a rare case.  We
    would need to pass down an expected type for that to work, and we
    haven't done that here.

    Inserting fails breaks down if the current monad's fail is
    expecting something other than a string, integer, float, or list,
    as we don't really have ways to come up with basic fail arguments
    for anything more complex.
  -}
  local failure::Expr = if monadInExprs.fst
                        then case monadFailArgument(monadInExprs.snd) of
                             | just(x) ->
                               Silver_Expr {
                                 $Expr{monadFail(monadInExprs.snd, top.location)}($Expr{x})
                               }
                             | nothing() -> basicFailure
                             end
                        else if monadInClauses.fst
                             then case monadFailArgument(monadInClauses.snd) of
                                  | just(x) ->
                                    Silver_Expr {
                                      $Expr{monadFail(monadInClauses.snd, top.location)}($Expr{x})
                                    }
                                  | nothing() -> basicFailure
                                  end
                             else basicFailure;

  -- TODO: this is the only use of .rawExprs. FIXME
  -- introduce the failure case here.
  {-
    forwards to 
    caseExpr(es.rawExprs, ml.matchRuleList, 
      failure,
      freshType(), location=top.location);
  -}
  --read the comment on the function below if you want to know what it is
  local attribute monadStuff::Pair<[Pair<Type Pair<Expr String>>] [Expr]>;
  monadStuff = monadicMatchTypesNames(es.rawExprs, ml.patternTypeList, top.env, ml.upSubst, 1);
  forwards to
    buildMonadicBinds(monadStuff.fst,
                      caseExpr(monadStuff.snd,
                               ml.matchRuleList, failure,
                               freshType(), location=top.location));
}
--find if any of the expressions are being matched as their inner type
--if returns (true, ty), ty will be used to find the correct Fail()
function monadicallyUsedExpr
Pair<Boolean Type> ::= elst::[Expr] tylst::[Type] env::Decorated Env sub::Substitution
{
  return case elst, tylst of
              | [], _ -> pair(false, errorType())
              | _, [] -> pair(false, errorType())
              | e::etl, t::ttl ->
                if isMonad(decorate e with {env=env; downSubst=sub;}.typerep) &&
                  !isMonad(performSubstitution(t, sub))
                then pair(true, decorate e with {env=env; downSubst=sub;}.typerep)
                else monadicallyUsedExpr(etl, ttl, env, sub)
              end;
}
--make a list of the expression types, expressions and names for binding them as
--   well as a new list of expressions for the forward to use
function monadicMatchTypesNames
Pair<[Pair<Type Pair<Expr String>>] [Expr]> ::=
elst::[Expr] tylst::[Type] env::Decorated Env sub::Substitution index::Integer
{
  local attribute subcall::Pair<[Pair<Type Pair<Expr String>>] [Expr]>;
  subcall = case elst, tylst of
            | _::etl, _::ttl -> monadicMatchTypesNames(etl, ttl, env, sub, index+1)
            end;
  local newName::String = "binding_matched_expression_in_case" ++ toString(index);
  return case elst, tylst of
         | [], _ -> pair([], [])
         | _, [] -> pair([], elst)
         | e::etl, t::ttl ->
           if isMonad(decorate e with {env=env; downSubst=sub;}.typerep) &&
             !isMonad(performSubstitution(t, sub))
           then pair(pair(decorate e with {env=env; downSubst=sub;}.typerep, pair(e, newName)) :: subcall.fst,
                     baseExpr(qName(bogusLoc(), newName), location=bogusLoc()) :: subcall.snd)
           else pair(subcall.fst, e::subcall.snd)
         end;
}
--take a list of things to bind and the name to use in binding them, as well as
--   a base for the binding, and create an expression with all of them bound
function buildMonadicBinds
Expr ::= bindlst::[Pair<Type Pair<Expr String>>] base::Expr
{
  return case bindlst of
         | [] -> base
         | pair(ty,pair(e,n))::rest ->
           Silver_Expr{ $Expr{monadBind(ty, bogusLoc())}
            ($Expr{e},
             $Expr{
               lambdap(
                 productionRHSCons(productionRHSElem(name(n, bogusLoc()),
                                                     '::',
                                                     typerepTypeExpr(monadInnerType(ty),
                                                                     location=bogusLoc()),
                                                     location=bogusLoc()),
                                   productionRHSNil(location=bogusLoc()),
                                   location=bogusLoc()),
                 buildMonadicBinds(rest, base),
                 location=bogusLoc())})}
         end;
}
--case expression that expands, using mplus, to possibly take multiple cases
concrete production mcaseExpr_c
top::Expr ::= 'mcase' es::Exprs 'of' Opt_Vbar_t ml::MRuleList 'end'
{
  top.unparse = "mcase " ++ es.unparse ++ " of " ++ ml.unparse ++ " end";

  {-
    This will fail if we don't have a monad type somewhere, even if
    the output type is expected to be a monad.  For example, if the
    expected output type is [a], we might expect mcase to map over all
    the patterns and give us all the ones that match in a list, which
    we won't do if there wasn't a list somewhere here in the first
    place.
  -}
  ml.downSubst = top.downSubst;
  local monadInExprs::Pair<Boolean Type> =
    monadicallyUsedExpr(es.rawExprs, ml.patternTypeList, top.env, ml.upSubst);
  local monadInClauses::Pair<Boolean Type> =
    foldl((\p::Pair<Boolean Type> a::AbstractMatchRule ->
            if p.fst
            then p
            else if isMonad(decorate a with {env=top.env; downSubst=ml.upSubst;}.typerep)
                 then pair(true, decorate a with {env=top.env; downSubst=ml.upSubst;}.typerep)
                 else p),
          pair(false, errorType()), --error as filler; won't be used
          ml.matchRuleList);
  local monad::Type = if monadInExprs.fst
                      then monadInExprs.snd
                      else monadInClauses.snd;
  local mplus::Expr = monadPlus(monad, bogusLoc());
  local mzero::Expr = monadZero(monad, bogusLoc());

  --new names for using lets to bind the incoming expressions
  local newNames::[String] = map(\x::Expr -> "__mcase_var_" ++ toString(genInt()), es.rawExprs);
  local nameExprs::[Expr] = map(\x::String -> baseExpr(qName(bogusLoc(), x), location=bogusLoc()),
                                newNames);
  local caseExprs::[Expr] = map(\x::AbstractMatchRule -> 
                                 caseExpr(nameExprs, [x], mzero, freshType(), location=bogusLoc()),
                                ml.matchRuleList);
  local mplused::Expr = foldl(\rest::Expr current::Expr -> 
                               Silver_Expr{
                                 $Expr{mplus}($Expr{rest}, $Expr{current})
                               },
                              head(caseExprs), tail(caseExprs));
  local letBound::Expr = foldr(\p::Pair<Expr String> rest::Expr ->
                                makeLet(bogusLoc(), p.snd, freshType(), p.fst, rest),
                               mplused, zipWith(pair, es.rawExprs, newNames));

  forwards to if isMonad(monad)
              then if canBeMCased(monad)
                   then letBound
                   else errorExpr([err(top.location, "Monad type " ++
                                   prettyType(performSubstitution(monad, top.finalSubst)) ++
                                   " cannot be used in an mcase as it does not have " ++
                                   "MPlus/MZero defined")], location=top.location)
              else errorExpr([err(top.location, "Need a monad type somewhere in " ++
                                                "an mcase, but did not find one")],
                             location=top.location);
}

abstract production caseExpr
top::Expr ::= es::[Expr] ml::[AbstractMatchRule] failExpr::Expr retType::Type
{
  --Why is unparse defined here if its point is just to fail?  Why not just let it forward anyway?
  --top.unparse = error("Internal error: pretty of intermediate data structure");

  -- 4 cases: no patterns left, all constructors, all variables, or mixed con/var.
  -- errors cases: more patterns no scrutinees, more scrutinees no patterns, no scrutinees multiple rules
  forwards to
    case ml of
    | matchRule([], e) :: _ -> e -- valid or error case
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
    -- are there multiple match rules, with no patterns left in them to distinguish between them?
    | matchRule([], e) :: _ :: _ -> [err(top.location, "Pattern has overlapping cases!")]
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
  local allConCase :: Expr =
    matchPrimitive(head(es),
      typerepTypeExpr(retType, location=top.location),
      foldPrimPatterns(
        map(allConCaseTransform(tail(es), failExpr, retType, _),
          groupMRules(prodRules))),
      failExpr, location=top.location);
  
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

  top.patternTypeList = m.patternTypeList;
  top.upSubst = top.downSubst;
}

concrete production mRuleList_cons
top::MRuleList ::= h::MatchRule '|' t::MRuleList
{
  top.unparse = h.unparse ++ " | " ++ t.unparse;
  top.errors := h.errors ++ t.errors;
  
  top.matchRuleList = h.matchRuleList ++ t.matchRuleList;

  top.patternTypeList = h.patternTypeList;
  --need to unify here with t.patternTypeList so, when we reach the case, if there is a
  --   monad pattern farther down where the first one is a wildcard/variable, we'll find
  --   it and not incorrectly identify something as being used non-monadically
  top.upSubst = foldl(\s::Substitution p::Pair<Type Type> ->
                       decorate check(p.fst, p.snd) with {downSubst=s;}.upSubst,
                      t.upSubst, zipWith(pair, h.patternTypeList, t.patternTypeList));
  t.downSubst = top.downSubst;
}

concrete production matchRule_c
top::MatchRule ::= pt::PatternList '->' e::Expr
{
  top.unparse = pt.unparse ++ " -> " ++ e.unparse;
  top.errors := pt.errors; -- e.errors is examine later, after transformation.
  
  top.errors <-
    if length(pt.patternList) == top.matchRulePatternSize then []
    else [err(pt.location, "case expression matching against " ++ toString(top.matchRulePatternSize) ++ " values, but this rule has " ++ toString(length(pt.patternList)) ++ " patterns")];

  top.matchRuleList = [matchRule(pt.patternList, e, location=top.location)];

  top.patternTypeList = pt.patternTypeList;
}

abstract production matchRule
top::AbstractMatchRule ::= pl::[Decorated Pattern] e::Expr
{
  top.headPattern = head(pl);
  -- If pl is null, and we're consulted, then we're missing patterns, pretend they're _
  top.isVarMatchRule = null(pl) || head(pl).patternIsVariable;
  -- For this, we safely know that pl is not null:
  top.expandHeadPattern = 
    matchRule(head(pl).patternSubPatternList ++ tail(pl), e, location=top.location);

  e.env = top.env;
  e.downSubst = top.downSubst;
  top.typerep = e.typerep;
}

concrete production patternList_one
top::PatternList ::= p::Pattern
{
  top.unparse = p.unparse;
  top.errors := p.errors;

  top.patternList = [p];

  top.patternTypeList = [p.patternType];
}
concrete production patternList_more
top::PatternList ::= p::Pattern ',' ps1::PatternList
{
  top.unparse = p.unparse ++ ", " ++ ps1.unparse;
  top.errors := p.errors ++ ps1.errors;

  top.patternList = p :: ps1.patternList;

  top.patternTypeList = p.patternType :: ps1.patternTypeList;
}

-- lol, dangling comma bug TODO
concrete production patternList_nil
top::PatternList ::=
{
  top.unparse = "";
  top.errors := [];

  top.patternList = [];

  top.patternTypeList = [];
}

----------------------------------------------------
-- Added Functions
----------------------------------------------------

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
 - @param restExprs  (The remaining expressions to match against in the overall complex case-expr)
 - @param failCase  (The failure expression)
 - @param retType  (The return type of the overall case-expr, and thus this)
 - @param mrs  (Match rules that all share the same head-pattern)
 -
 - @return  A primitive pattern matching the constructor, with the overall case-expr pushed down into it
 -}
function allConCaseTransform
PrimPattern ::= restExprs::[Expr]  failCase::Expr  retType::Type  mrs::[AbstractMatchRule]
{
  -- TODO: potential source of buggy error messages. We're using head(mrs) as the source of
  -- authority for the length of pattern variables to match against. But each match rule may
  -- actually have a different length (and .expandHeadPattern just applies whatever is there)
  -- This is an erroneous condition, but it means we transform into a maybe-more erroneous condition.
  local names :: [Name] = map(patternListVars, head(mrs).headPattern.patternSubPatternList);

  local subcase :: Expr =
    caseExpr(
      map(exprFromName, names) ++ restExprs,
      map((.expandHeadPattern), mrs),
      failCase, retType, location=head(mrs).location);
  -- TODO: head(mrs).location is probably not the correct thing to use here?? (generally)

  -- Maybe this one is more reasonable? We need to test examples and see what happens...
  local l :: Location = head(mrs).headPattern.location;

  return
    case head(mrs).headPattern of
    | prodAppPattern(qn,_,_,_) -> 
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
  | matchRule(headPat :: restPat, e) ->
      matchRule(restPat, 
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


