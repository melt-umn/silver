grammar silver:compiler:extension:patternmatching;

imports silver:util:treeset as ts;

imports silver:compiler:definition:core;
imports silver:compiler:definition:env;
imports silver:compiler:definition:type;
imports silver:compiler:modification:primitivepattern;
imports silver:compiler:extension:list;

--Get mwdaWrn production for completeness analysis
import silver:compiler:analysis:warnings:flow;
--Get getNonforwardingProds to check all are covered
import silver:compiler:definition:flow:env only getNonforwardingProds;

import silver:compiler:definition:type:syntax only typerepTypeExpr;
import silver:compiler:modification:let_fix;

terminal Case_kwd 'case' lexer classes {KEYWORD,RESERVED};
terminal Of_kwd 'of' lexer classes {KEYWORD,RESERVED};
terminal Arrow_kwd '->' lexer classes {SPECOP};
terminal Vbar_kwd '|' lexer classes {SPECOP};
terminal Opt_Vbar_t /\|?/ lexer classes {SPECOP}; -- optional Coq-style vbar.
terminal When_kwd 'when' lexer classes {KEYWORD,RESERVED};
terminal Matches_kwd 'matches' lexer classes {KEYWORD};

-- MR | ...
nonterminal MRuleList with location, config, unparse, env, frame, errors, freeVars, matchRuleList, matchRulePatternSize;
propagate errors, freeVars on MRuleList;

-- Turns MRuleList (of MatchRules) into [AbstractMatchRule]
synthesized attribute matchRuleList :: [AbstractMatchRule];
-- Notification of the number of expressions being matched upon
autocopy attribute matchRulePatternSize :: Integer;

-- P -> E
nonterminal MatchRule with location, config, unparse, env, frame, errors, freeVars, matchRuleList, matchRulePatternSize;
nonterminal AbstractMatchRule with location, unparse, headPattern, isVarMatchRule, expandHeadPattern, hasCondition;

-- The head pattern of a match rule
synthesized attribute headPattern :: Decorated Pattern;
-- Whether the head pattern of a match rule is a variable binder or not
synthesized attribute isVarMatchRule :: Boolean;
-- Turns A(B, C), D into B, C, D in the patterns list, with a list of named patterns to include.
synthesized attribute expandHeadPattern :: (AbstractMatchRule ::= [String]);
-- For completeness checking, we need to know if we have a condition
synthesized attribute hasCondition::Boolean;

-- P , ...
nonterminal PatternList with location, config, unparse, patternList, env, frame, errors, patternVars, patternVarEnv;
propagate errors on PatternList;

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
  propagate freeVars;

  ml.matchRulePatternSize = length(es.rawExprs);
  top.errors <- ml.errors;
  
  -- TODO: this is the only use of .rawExprs. FIXME
  -- introduce the failure case here.
  forwards to 
    caseExpr(es.rawExprs, ml.matchRuleList, true,
      mkStrFunctionInvocation(top.location, "silver:core:error",
        [stringConst(terminal(String_t, 
          "\"Error: pattern match failed at " ++ top.grammarName ++ " " ++ top.location.unparse ++ "\\n\""), location=top.location)]),
      freshType(), location=top.location);
}


abstract production caseExpr
top::Expr ::= es::[Expr] ml::[AbstractMatchRule] complete::Boolean failExpr::Expr retType::Type
{
  top.unparse =
    "(case " ++ implode(", ", map((.unparse), es)) ++ " of " ++ 
    implode(" | ", map((.unparse), ml)) ++ " | _ -> " ++ failExpr.unparse ++
    " end :: " ++ prettyType(retType) ++ ")";

  {-Checking Pattern Completeness

    We want to check if a set of patterns covers all possible cases.
    For this, we need to consider closed and non-closed nonterminals
    separately.  We will NOT count match rules with conditions as
    contributing to completeness, as we will assume conditions are
    actually conditional, and the rule with a condition will sometimes
    match and sometimes not.
  -}
  local conditionlessRules::[AbstractMatchRule] =
        partition((.hasCondition), ml).snd;
  local conditionlessPatterns::[[Decorated Pattern]] =
        map(\ x::AbstractMatchRule ->
              case x of
              | matchRule(plst, _, _) -> plst
              end, conditionlessRules);
  local completenessCounterExample::Maybe<[Pattern]> =
        checkCompleteness(conditionlessPatterns, top.env, top.flowEnv);

  top.errors <-
      case completenessCounterExample of
      | just(lst) when complete ->
        [mwdaWrn(top.location,
                 "This pattern-matching is not exhaustive.  Here is an example of a " ++
                   "case that is not matched:  " ++ implode(", ", map((.unparse), lst)),
                 top.config.runMwda)]
      | _ -> []
      end;

  {-
    With the addition of completeness checking, we cannot
    incrementally forward through a series of caseExpr, compiling
    toward primitive matching as we go.  That would lead to a lot of
    false incompletes.  Instead, we compile it in a function.  This
    function also checks for overlapping cases in patterns (best
    detected through compilation), hence the [Message] part of the
    return value.
  -}
  local fwdResult::Pair<Expr [Message]> = compileCaseExpr(es, ml, failExpr, retType, top.location);
  top.errors <- fwdResult.snd;
  forwards to fwdResult.fst;
}


--Get the initial segment of the match rules which all have the same
--pattern type (constructor or var) and the rest of the rules
function initialSegmentPatternType
Pair<[AbstractMatchRule] [AbstractMatchRule]> ::= lst::[AbstractMatchRule]
{
  return case lst of
           --this probably shouldn't be called with an empty list, but catch it anyway
         | [] -> pair([], [])
         | [mr] -> pair([mr], [])
         | mr1::mr2::rest ->
           if mr1.isVarMatchRule == mr2.isVarMatchRule
           then --both have the same type of pattern
              let sub::Pair<[AbstractMatchRule] [AbstractMatchRule]> = initialSegmentPatternType(mr2::rest)
              in pair(mr1::sub.fst, sub.snd) end
           else --the first has a different type of pattern than the second
              pair([mr1], mr2::rest)
         end;
}

--Compile a case expression `case es of ml` down into primitive matches
--Also check for overlapping patterns, which show up by this compilation
function compileCaseExpr
Pair<Expr [Message]> ::= es::[Expr] ml::[AbstractMatchRule] failExpr::Expr retType::Type loc::Location
{
  local errors::[Message] =
    case ml of
    -- are there multiple match rules, with no patterns/conditions left in them to distinguish between them?
    | matchRule([], _, e) :: _ :: _ ->
      if areUselessPatterns(ml)
      then [err(loc, "Pattern has overlapping cases!")]
      else []
    | _ -> []
    end;

  local partMRs :: Pair<[AbstractMatchRule] [AbstractMatchRule]> =
    partition((.isVarMatchRule), ml);
  local varRules :: [AbstractMatchRule] = partMRs.fst;
  local prodRules :: [AbstractMatchRule] = partMRs.snd;

  {--
   - All constructors? Then do a real primitive match.
   -}
  local freshCurrName :: String = "__curr_match_" ++ toString(genInt());
  local freshCurrNameRef :: Expr =
    baseExpr(qName(loc, freshCurrName), location=loc);
  local allConCase :: Pair<Expr [Message]> =
      let constructorGroups::[[AbstractMatchRule]] = groupMRules(prodRules) in
      let mappedPatternsErrs::[Pair<PrimPattern [Message]>] =
          map(allConCaseTransform(freshCurrNameRef, tail(es), failExpr, retType, _),
              constructorGroups) in
      let primPatterns::[PrimPattern] =
          map(\ p::Pair<PrimPattern [Message]> -> p.fst, mappedPatternsErrs) in
      let errs::[Message] =
          foldr(\ p::Pair<PrimPattern [Message]> l::[Message] -> p.snd ++ l,
                [], mappedPatternsErrs) in
        pair(
          -- Annoyingly, this now needs to be a let in case of annotation patterns.
          makeLet(loc,
            freshCurrName, freshType(), head(es), 
            matchPrimitive(
              freshCurrNameRef,
              typerepTypeExpr(retType, location=loc),
              foldPrimPatterns(primPatterns),
              failExpr, location=loc)),
          errors ++ errs)
      end end end end;

  {--
   - All variables? Just push a let binding inside each branch.
   -}
  local allVarCase :: Pair<Expr [Message]> =
     let p::Pair<Expr [Message]> =
        compileCaseExpr(
             tail(es),
             map(bindHeadPattern(head(es), freshType(){-whatever the first expression's type is?-}, _),
                 ml),
             failExpr, retType, loc)
             -- A quick note about that freshType() hack: putting it here means there's ONE fresh type
             -- generated, puching it inside 'bindHeadPattern' would generate multiple fresh types.
             -- So don't try that!
     in pair(p.fst, errors ++ p.snd) end;

  {--
    - Mixed con/var? Partition into segments and build nested case expressions
    - The whole segment partitioning is done in a function rather than grabbing the initial segment
      and forwarding to do the rest of the segments (another workable option) for efficiency
   -}
  local mixedCase :: Pair<Expr [Message]> = buildMixedCaseMatches(es, ml, failExpr, retType, loc);

  -- 4 cases: no patterns left, all constructors, all variables, or mixed con/var.
  -- errors cases: more patterns no scrutinees, more scrutinees no patterns, no scrutinees multiple rules
  return
    case ml of
    | matchRule([], c, e) :: _ -> pair(buildMatchWhenConditionals(ml, failExpr), -- valid or error case
                                       errors)
    -- No match rules, only possible through abstract syntax
    | [] -> pair(Silver_Expr { let res :: $TypeExpr{typerepTypeExpr(retType, location=loc)} = $Expr{failExpr} in res end }, [])
    | _ -> if null(es) then pair(failExpr, []) -- error case
           else if null(varRules) then allConCase
           else if null(prodRules) then allVarCase
           else mixedCase
    end;
  -- TODO: BUG: we're using the left of patterns in the first match rule as a guide here
  -- which means we run into serious problems if not all match rules agree on the length
  -- of the pattern list. We don't report some errors related to not having enough
  -- variable binders
}

{-
  Build the correct match expression when we are mixing constructor
  and variable patterns for the first match.  We do this by
  partitioning the list into segments of only constructor or variable
  patterns in order, then putting each segment into its own match.
-}
function buildMixedCaseMatches
Pair<Expr [Message]> ::= es::[Expr] ml::[AbstractMatchRule] failExpr::Expr retType::Type loc::Location
{
  local freshFailName :: String = "__fail_" ++ toString(genInt());
  return if null(ml)
         then pair(failExpr, [])
         else let segments::Pair<[AbstractMatchRule] [AbstractMatchRule]> =
                            initialSegmentPatternType(ml)
              in
                case buildMixedCaseMatches(es, segments.snd, failExpr, retType, loc),
                     compileCaseExpr(es, segments.fst, baseExpr(qName(loc, freshFailName), location=loc),
                                     retType, loc) of
                | pair(bmexpr, bmerrs), pair(ccexpr, ccerrs) ->
                  pair(makeLet(loc, freshFailName, retType, bmexpr, ccexpr),
                       bmerrs ++ ccerrs)
                end
              end;
}


{-
  We check completeness with a function because we want to recursively
  check, which we cannot do with attributes alone.

  We need the environment to look up any nonterminal matches and see
  if the nonterminal is closed or not.

  We return nothing() if the patterns are complete, and just(plst) if
  plst is an example of a missing pattern set (matching over multiple
  values at once).
-}
function checkCompleteness
Maybe<[Pattern]> ::= lst::[[Decorated Pattern]] env::Decorated Env
                     flowEnv::Decorated FlowEnv
{
  local pattGroups::([[Decorated Pattern]], [[Decorated Pattern]]) =
        partition(\ plst::[Decorated Pattern] -> head(plst).patternIsVariable, lst);
  local varGroup::[[Decorated Pattern]] = pattGroups.1;
  local consGroup::[[Decorated Pattern]] = pattGroups.2;

  local allPattLens::[Integer] = map(\ x::[Decorated Pattern] -> length(x), lst);
  local allSameLen::Boolean =
        if null(lst)
        then true
        else all(map(\ x::Integer -> x == head(allPattLens), allPattLens));
  local numPatts::Integer =
        if null(lst) || !allSameLen
        then 0
        else head(allPattLens);

  --We delegate checking based on the kind of pattern which is first in the list.
  local conPatts::[Decorated Pattern] = map(head, consGroup);
  local isPrimPatts::Boolean =
        foldr(\ a::Decorated Pattern b::Boolean -> b || a.isPrimitivePattern,
              false, conPatts);
  local isBoolPatts::Boolean =
        foldr(\ a::Decorated Pattern b::Boolean -> b || a.isBoolPattern,
              false, conPatts);
  local isListPatts::Boolean =
        foldr(\ a::Decorated Pattern b::Boolean -> b || a.isListPattern,
              false, conPatts);

  {-
    Each checking function checks completeness of the first patterns
    in the sets, which are of the appropriate type, including checking
    for variable patterns.  If the first patterns are complete, it
    then handles grouping the rest of the sets of patterns and
    checking if they are complete as well.  Correctly handling
    grouping is the reason we need to have a separate function for
    each kind of pattern.
  -}
  local boolComp::Maybe<[Pattern]> = checkBooleanCompleteness(consGroup, varGroup, env, flowEnv);
  local listComp::Maybe<[Pattern]> = checkListCompleteness(consGroup, varGroup, env, flowEnv);
  local ntComp::Maybe<[Pattern]> = checkNonterminalCompleteness(consGroup, varGroup, env, flowEnv);
  local primComp::Maybe<[Pattern]> = checkPrimitiveCompleteness(consGroup, varGroup, env, flowEnv);

  return
     if numPatts == 0 || !allSameLen
     then nothing()
     else if isBoolPatts
          then boolComp
          else if isListPatts
               then listComp
               else if isPrimPatts
                    then primComp
                    else if length(consGroup) > 0
                         then ntComp
                         else --If we somehow end up with all vars to start, just check the rest
                              case checkCompleteness(map(tail, lst), env, flowEnv) of
                              | nothing() -> nothing()
                              | just(plst) -> just(wildcPattern('_', location=bogusLoc())::plst)
                              end;
}

{-
  We need blanks for (1) output when a production is missing and (2)
  for expanding a variable pattern list to be the same length as an
  expanded list from a head pattern with subpatterns.
-}
function generateWildcards
[Pattern] ::= n::Integer
{
  return repeat(wildcPattern('_', location=bogusLoc()), n);
}

{-
  Sometimes we need to decorate a list of wildcard patterns for the
  type system to pass as an expanded list for completeness checking to
  replace a variable.  We should never need to access anything on
  these, so empty decoration is fine.
-}
function decoratePattList
[Decorated Pattern] ::= lst::[Pattern]
{
  return map(\ p::Pattern -> decorate p with {}, lst);
}

--Group sets of patterns by the first pattern in each set
--The core groupBy function doesn't work because it groups contiguous
--   sets, and the patterns might not be contiguous.
function groupAllPattsByHead
[[[Decorated Pattern]]] ::= pattLists::[[Decorated Pattern]]
{
  return
     if null(pattLists)
     then []
     else case groupAllPattsByHeadHelp(head(pattLists), tail(pattLists)) of
          | pair(thisGroup, others) ->
            (head(pattLists)::thisGroup)::groupAllPattsByHead(others)
          end;
}
function groupAllPattsByHeadHelp
Pair<[[Decorated Pattern]] [[Decorated Pattern]]> ::=
    item::[Decorated Pattern] rest::[[Decorated Pattern]]
{
  return
     case rest of
     | [] -> pair([], [])
     | h::t -> case groupAllPattsByHeadHelp(item, t) of
               | pair(grp, rst) ->
                 if head(item).patternSortKey == head(h).patternSortKey
                 then pair(h::grp, rst)
                 else pair(grp, h::rst)
               end
     end;
}

--This checks the primitive patterns all have the same type and generates an
--   example of a primitive value which is not covered by the given patterns
function generatePrimitiveMissingPattern
Maybe<Pattern> ::= patts::[Decorated Pattern]
{
  local ints::[Integer] =
        foldr(\ p::Decorated Pattern l::[Integer] ->
                case p of
                | intPattern(int_t) -> toInteger(int_t.lexeme)::l
                | _ -> l
                end, [], patts);
  local flts::[Float] =
        foldr(\ p::Decorated Pattern l::[Float] ->
                case p of
                | fltPattern(flt_t) -> toFloat(flt_t.lexeme)::l
                | _ -> l
                end, [], patts);
  local strs::[String] =
        foldr(\ p::Decorated Pattern l::[String] ->
                case p of              --remove quotation marks
                | strPattern(str_t) -> substring(1, length(str_t.lexeme) - 1, str_t.lexeme)::l
                | _ -> l
                end, [], patts);
  return case ints, flts, strs of
         | _::_, [], [] -> just(generateMissingIntegerPattern(ints, 0))
         | [], _::_, [] -> just(generateMissingFloatPattern(flts, 0.0))
         | [], [], _::_ -> just(generateMissingStringPattern(strs, ""))
         | _, _, _ -> nothing() --type error, so don't generate a completeness error
         end;
}

function generateMissingIntegerPattern
Pattern ::= lst::[Integer] initial::Integer
{
  return if containsBy(\ a::Integer b::Integer -> a == b, initial, lst)
         then generateMissingIntegerPattern(lst, initial + 1)
         else intPattern(terminal(Int_t, toString(initial), bogusLoc()), location=bogusLoc());
}
function generateMissingFloatPattern
Pattern ::= lst::[Float] initial::Float
{
  return if containsBy(\ a::Float b::Float -> a == b, initial, lst)
         then generateMissingFloatPattern(lst, initial + 1.0)
         else fltPattern(terminal(Float_t, toString(initial), bogusLoc()), location=bogusLoc());
}
function generateMissingStringPattern
Pattern ::= lst::[String] initial::String
{
  return if containsBy(\ a::String b::String -> a == b, initial, lst)
         then generateMissingStringPattern(lst, initial ++ "*")
         else strPattern(terminal(String_t, "\"" ++ initial ++ "\"", bogusLoc()), location=bogusLoc());
}

--First pattern in each set in conPatts is a primitive
--The first match can only be completed by a variable
function checkPrimitiveCompleteness
Maybe<[Pattern]> ::= conPatts::[[Decorated Pattern]] varPatts::[[Decorated Pattern]]
                     env::Decorated Env flowEnv::Decorated FlowEnv
{
  local firstPatts::[Decorated Pattern] = map(head, conPatts);
  local firstPattMissng::Maybe<Pattern> =
        generatePrimitiveMissingPattern(firstPatts);
  local numPatts::Integer = length(head(conPatts));

  local grouped::[ [[Decorated Pattern]] ] = groupAllPattsByHead(conPatts);
  local subcallCons::Maybe<[Pattern]> =
        foldr(\ patts::[[Decorated Pattern]] rest::Maybe<[Pattern]> ->
                case rest of
                | nothing() ->
                  case checkCompleteness(map(tail, patts) ++ map(tail, varPatts),
                                         env, flowEnv) of
                  | just(plst) -> just(new(head(head(patts)))::plst)
                  | nothing() -> nothing()
                  end
                | just(plst) -> just(plst)
                end,
              nothing(), grouped);
  local subcall::Maybe<[Pattern]> =
        case subcallCons of
        | just(plst) -> just(plst)
        | nothing() ->
          case checkCompleteness(map(tail, varPatts), env, flowEnv) of
          | just(plst) -> just(wildcPattern('_', location=bogusLoc())::plst)
          | nothing() -> nothing()
          end
        end;

  return if length(varPatts) > 0
         then subcall
         else case firstPattMissng of
              | just(p) -> just(p::generateWildcards(numPatts - 1))
              | nothing() -> nothing() --only possible if there is a typing error
              end;
}

--First pattern in each set in consPatts is a Boolean pattern
--The first match can be completed by a variable or patterns for true and false
function checkBooleanCompleteness
Maybe<[Pattern]> ::= conPatts::[[Decorated Pattern]] varPatts::[[Decorated Pattern]]
                     env::Decorated Env flowEnv::Decorated FlowEnv
{
  --create groups for true and false
  local grouped::([[Decorated Pattern]], [[Decorated Pattern]]) =
        partition(\ plst::[Decorated Pattern] -> head(plst).patternSortKey == "true", conPatts);
  local numPatts::Integer = length(head(conPatts));

  local foundTrue::Boolean = length(grouped.1) > 0;
  local foundFalse::Boolean = length(grouped.2) > 0;
  local foundVar::Boolean = length(varPatts) > 0;

  --Check the completeness of the rest of the patterns where 'true' or a variable was first
  local trueSubcall::Maybe<[Pattern]> =
        checkCompleteness(map(\ plst::[Decorated Pattern] -> tail(plst), grouped.1) ++
                          map(\ plst::[Decorated Pattern] -> tail(plst), varPatts),
                          env, flowEnv);
  --Check the completeness of the rest of the patterns where 'false' or a variable was first
  local falseSubcall::Maybe<[Pattern]> =
        checkCompleteness(map(\ plst::[Decorated Pattern] -> tail(plst), grouped.2) ++
                          map(\ plst::[Decorated Pattern] -> tail(plst), varPatts),
                          env, flowEnv);
  --What's missing from subcalls, including the first pattern
  local subcallResult::Maybe<[Pattern]> =
        case trueSubcall of
        | just(lst) -> just(truePattern('true', location=bogusLoc())::lst)
        | nothing() ->
          case falseSubcall of
          | just(lst) -> just(falsePattern('false', location=bogusLoc())::lst)
          | nothing() ->
            --If completed by vars, need to check vars case is complete as well
            if foundTrue && foundFalse
            then nothing()
            else checkCompleteness(varPatts, env, flowEnv)
          end
        end;

  return if foundVar
         then subcallResult
         else if foundTrue
              then if foundFalse
                   then subcallResult
                   else just(falsePattern('false', location=bogusLoc())::generateWildcards(numPatts - 1))
              else just(truePattern('true', location=bogusLoc())::generateWildcards(numPatts - 1));
}

--First pattern in each set in consPatts is a list pattern
--The first match can be completed by a variable or patterns for cons and nil
function checkListCompleteness
Maybe<[Pattern]> ::= conPatts::[[Decorated Pattern]] varPatts::[[Decorated Pattern]]
                     env::Decorated Env flowEnv::Decorated FlowEnv
{
  --create groups for nil and cons
  local grouped::([[Decorated Pattern]], [[Decorated Pattern]]) =
        partition(\ plst::[Decorated Pattern] -> head(plst).patternSortKey == "silver:core:nil", conPatts);
  local numPatts::Integer = length(head(conPatts));

  local foundNil::Boolean = length(grouped.1) > 0;
  local foundCons::Boolean = length(grouped.2) > 0;
  local foundVar::Boolean = length(varPatts) > 0;

  --Check the completeness of the rest of the patterns where 'nil' or a variable was first
  local nilSubcall::Maybe<[Pattern]> =
        checkCompleteness(map(\ plst::[Decorated Pattern] -> tail(plst), grouped.1) ++
                          map(\ plst::[Decorated Pattern] -> tail(plst), varPatts),
                          env, flowEnv);
  --Check the completeness of the rest of the patterns where 'cons' or a variable was first
  local consSubcall::Maybe<[Pattern]> =
        --We check the subpatterns for the head and tail of the list and the overall tail together
        checkCompleteness(map(\ plst::[Decorated Pattern] ->
                                head(plst).patternSubPatternList ++ tail(plst), grouped.2) ++
                          map(\ plst::[Decorated Pattern] ->
                                decoratePattList(generateWildcards(2)) ++ tail(plst), varPatts),
                          env, flowEnv);
  --What's missing from subcalls, including the first pattern
  local subcallResult::Maybe<[Pattern]> =
        case nilSubcall of
        | just(lst) -> just(nilListPattern('[', ']', location=bogusLoc())::lst)
        | nothing() ->
          case consSubcall of
          | just(hd::tl::lst) ->
            just(consListPattern(hd, '::', tl, location=bogusLoc())::lst)
          | just(_) -> error("List must include patterns for at least head and tail")
          | nothing() ->
            --If completed by vars, need to check vars case is complete as well
            if foundNil && foundCons
            then nothing()
            else checkCompleteness(varPatts, env, flowEnv)
          end
        end;

  return if foundVar
         then subcallResult
         else if foundNil
              then if foundCons
                   then subcallResult
                   else just(consListPattern(wildcPattern('_', location=bogusLoc()), '::',
                                             wildcPattern('_', location=bogusLoc()), location=bogusLoc())::
                             generateWildcards(numPatts - 1))
              else just(nilListPattern('[', ']', location=bogusLoc())::generateWildcards(numPatts - 1));
}

--First pattern in each set in consPatts is a nonterminal pattern
--The first match can be completed by a variable or, for a non-closed nonterminal,
--   by having a pattern for each non-forwarding production
function checkNonterminalCompleteness
Maybe<[Pattern]> ::= conPatts::[[Decorated Pattern]] varPatts::[[Decorated Pattern]]
                     env::Decorated Env flowEnv::Decorated FlowEnv
{
  local numPatts::Integer = length(head(conPatts));

  --All the patterns ought to have the same type.
  local builtTypes::[String] =
        nubBy(\ a::String b::String -> a == b, map((.patternTypeName), map(head, conPatts)));
  local builtType::String = head(builtTypes);

  --Test whether all the required productions are represented in the productions
  local requiredProds::[String] = getNonforwardingProds(builtType, flowEnv);
  local groupedPatts::[ [[Decorated Pattern]] ] =
        groupAllPattsByHead(conPatts);
  local constructorReps::[Decorated Pattern] =
        map(\ plst::[[Decorated Pattern]] -> head(head(plst)), groupedPatts);
  local allRepresented::Maybe<Pattern> =
        checkAllProdsRepresented(constructorReps, requiredProds, env);

  local hasVar::Boolean = length(varPatts) > 0 && length(head(varPatts)) > 0;
  local isVarCompleted::Boolean = hasVar && allRepresented.isJust;

  --Test whether the children and rests of the list are complete, but only for required productions
  --We don't care whether other productions are complete because they forward to required productions
  local reqGroupedPatts::[ [[Decorated Pattern]] ] =
        filter(\ plst::[[Decorated Pattern]] ->
                 contains(head(head(plst)).patternSortKey, requiredProds), groupedPatts);
  local groupsWithVars::[ [[Decorated Pattern]] ] =
        map(\ plst::[[Decorated Pattern]] -> plst ++ varPatts, reqGroupedPatts);
  local subcallResult::Maybe<[Pattern]> =
        case checkAllProdGroupsComplete(reqGroupedPatts, varPatts, env, flowEnv) of
        | just(plst) -> just(plst)
        | nothing() ->
          --check if it was var completed, and, if so, if the var patterns are complete
          if isVarCompleted
          then checkCompleteness(map(tail, varPatts), env, flowEnv)
          else nothing()
        end;

  --To find out if the nonterminal is closed or not, we need to look it up
  local nt::QName = qName(bogusLoc(), builtType);
  nt.env = env;
  local isClosed::Boolean =
        case nt.lookupType.dcls of
        | ntDcl(_, _, closed, _) :: _ -> closed
        | _ -> false -- default, if the lookup fails
        end;

  -- TODO:  named argument patterns are not handled yet
  return
     --If we are building multiple types or have unknown productions,
     --   we have a type error, so don't check completeness
     if length(builtTypes) != 1
     then nothing()
     else if isClosed && !hasVar
               --This is a hack to pass up a message about closed nonterminals as a pattern
          then just(varPattern(name("<default case for closed nonterminal>", bogusLoc()),
                               location=bogusLoc())::generateWildcards(numPatts - 1))
          else if hasVar
               then subcallResult
               else case allRepresented of
                    | just(p) -> just(p::generateWildcards(numPatts - 1))
                    | nothing() -> subcallResult
                    end;
}

--Check every set of patterns in conGrps is complete, when varPatts is added to it
function checkAllProdGroupsComplete
Maybe<[Pattern]> ::= conGrps::[ [[Decorated Pattern]] ] varPatts::[[Decorated Pattern]]
                     env::Decorated Env flowEnv::Decorated FlowEnv
{
  local hdProdPatt::Decorated Pattern = head(head(head(conGrps)));
  local numChildren::Integer = length(hdProdPatt.patternSubPatternList);

  --Check the completeness of the children of the current production, joined with the rest of the pattern list
  local expandedVars::[[Decorated Pattern]] =
        --We need to drop the head and add wildcards for the number of children
        map(\ plst::[Decorated Pattern] ->
            decoratePattList(generateWildcards(numChildren)) ++ tail(plst), varPatts);
  local grpWithVars::[[Decorated Pattern]] =
        map(\ plst::[Decorated Pattern] ->
              head(plst).patternSubPatternList ++ tail(plst), head(conGrps)) ++
        expandedVars;
  local hdComplete::Maybe<[Pattern]> = checkCompleteness(grpWithVars, env, flowEnv);

  return
     case conGrps of
     | [] -> nothing()
     | _::rest ->
       case hdComplete, hdProdPatt of
       | just(plst), prodAppPattern_named(qname, _, _, _, _, _) ->
         just(prodAppPattern(qname, '(', buildPatternList(take(numChildren, plst), bogusLoc()),
                             ')', location=bogusLoc())::drop(numChildren, plst))
       | just(_), _ -> error("Should not have anything but prodAppPattern_named here")
       | nothing(), _ -> checkAllProdGroupsComplete(rest, varPatts, env, flowEnv)
       end
     end;
}

--check that all required productions are present
function checkAllProdsRepresented
Maybe<Pattern> ::= givenPatts::[Decorated Pattern] requiredProds::[String] env::Decorated Env
{
  {-
    We walk down through requiredProds rather than pattGroups.  We
    only care that everything in requiredProds is covered; we don't
    care about anything else which shows up in pattGroups
    (e.g. forwarding productions).
  -}
  local firstProdName::String = head(requiredProds);
  local firstProdQName::QName = qName(bogusLoc(), firstProdName);
  local pattFound::Boolean =
        foldr(\ p::Decorated Pattern b::Boolean ->
                b || p.patternSortKey == firstProdName,
              false, givenPatts);

  firstProdQName.env = env;
  local firstProdNumArgs::Integer = firstProdQName.lookupValue.typeScheme.typerep.arity;
  local wildcards::PatternList =
        buildPatternList(repeat(wildcPattern('_', location=bogusLoc()), firstProdNumArgs), bogusLoc());

  return
     case requiredProds of
     | [] -> nothing()
     | _::rest ->
       if pattFound
       then checkAllProdsRepresented(givenPatts, rest, env)
       else just(prodAppPattern(firstProdQName, '(', wildcards, ')',
                 location=bogusLoc()))
     end;
}




--Match Rules
concrete production mRuleList_one
top::MRuleList ::= m::MatchRule
{
  top.unparse = m.unparse;

  top.matchRuleList = m.matchRuleList;
}

concrete production mRuleList_cons
top::MRuleList ::= h::MatchRule '|' t::MRuleList
{
  top.unparse = h.unparse ++ " | " ++ t.unparse;
  
  top.matchRuleList = h.matchRuleList ++ t.matchRuleList;
}

concrete production matchRule_c
top::MatchRule ::= pt::PatternList '->' e::Expr
{
  top.unparse = pt.unparse ++ " -> " ++ e.unparse;
  top.errors := pt.errors; -- e.errors is examined later, after transformation.
  top.freeVars := ts:removeAll(pt.patternVars, e.freeVars);
  
  top.errors <-
    if length(pt.patternList) == top.matchRulePatternSize then []
    else [err(pt.location, "case expression matching against " ++ toString(top.matchRulePatternSize) ++ " values, but this rule has " ++ toString(length(pt.patternList)) ++ " patterns")];

  pt.patternVarEnv = [];

  top.matchRuleList = [matchRule(pt.patternList, nothing(), e, location=top.location)];
}

concrete production matchRuleWhen_c
top::MatchRule ::= pt::PatternList 'when' cond::Expr '->' e::Expr
{
  top.unparse = pt.unparse ++ " when " ++ cond.unparse ++ " -> " ++ e.unparse;
  top.errors := pt.errors; -- e.errors is examined later, after transformation, as is cond.errors
  top.freeVars := ts:removeAll(pt.patternVars, cond.freeVars ++ e.freeVars);
  
  top.errors <-
    if length(pt.patternList) == top.matchRulePatternSize then []
    else [err(pt.location, "case expression matching against " ++ toString(top.matchRulePatternSize) ++ " values, but this rule has " ++ toString(length(pt.patternList)) ++ " patterns")];

  pt.patternVarEnv = [];

  top.matchRuleList = [matchRule(pt.patternList, just(pair(cond, nothing())), e, location=top.location)];
}

concrete production matchRuleWhenMatches_c
top::MatchRule ::= pt::PatternList 'when' cond::Expr 'matches' p::Pattern '->' e::Expr
{
  top.unparse = pt.unparse ++ " when " ++ cond.unparse ++ " matches " ++ p.unparse ++ " -> " ++ e.unparse;
  top.errors := pt.errors; -- e.errors is examined later, after transformation, as is cond.errors
  top.freeVars := ts:removeAll(pt.patternVars, cond.freeVars ++ ts:removeAll(p.patternVars, e.freeVars));
  
  top.errors <-
    if length(pt.patternList) == top.matchRulePatternSize then []
    else [err(pt.location, "case expression matching against " ++ toString(top.matchRulePatternSize) ++ " values, but this rule has " ++ toString(length(pt.patternList)) ++ " patterns")];

  pt.patternVarEnv = [];
  p.patternVarEnv = pt.patternVars;

  top.matchRuleList = [matchRule(pt.patternList, just(pair(cond, just(p))), e, location=top.location)];
}

abstract production matchRule
top::AbstractMatchRule ::= pl::[Decorated Pattern] cond::Maybe<Pair<Expr Maybe<Pattern>>> e::Expr
{
  top.unparse =
    implode(", ", map((.unparse), pl)) ++
    case cond of
    | just(pair(c, just(p))) -> " when " ++ c.unparse ++ " matches " ++ p.unparse
    | just(pair(c, nothing())) -> " when " ++ c.unparse
    | nothing() -> ""
    end ++
    " -> " ++ e.unparse;
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
                with { config=head(pl).config; env=head(pl).env; patternVarEnv = []; },
              lookup(n, head(pl).patternNamedSubPatternList)),
          named) ++
        tail(pl),
        cond, e, location=top.location);

  top.hasCondition = cond.isJust;
}

concrete production patternList_one
top::PatternList ::= p::Pattern
{
  top.unparse = p.unparse;

  top.patternVars = p.patternVars;
  p.patternVarEnv = top.patternVarEnv;
  
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
  top.unparse = p.unparse ++ (if ps1.unparse == "" then "" else ", " ++ ps1.unparse);

  top.patternVars = p.patternVars ++ ps1.patternVars;
  ps1.patternVarEnv = p.patternVarEnv ++ p.patternVars;
  
  top.patternList = p :: ps1.patternList;
}

-- lol, dangling comma bug TODO
concrete production patternList_nil
top::PatternList ::=
{
  top.unparse = "";

  top.patternVars = [];
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
 - @return  A primitive pattern matching the constructor, with the overall case-expr pushed down into it and compiled, along with any errors for overlapping patterns.
 -}
function allConCaseTransform
Pair<PrimPattern [Message]> ::= currExpr::Expr restExprs::[Expr]  failCase::Expr  retType::Type  mrs::[AbstractMatchRule]
{
  -- TODO: potential source of buggy error messages. We're using head(mrs) as the source of
  -- authority for the length of pattern variables to match against. But each match rule may
  -- actually have a different length (and .expandHeadPattern just applies whatever is there)
  -- This is an erroneous condition, but it means we transform into a maybe-more erroneous condition.
  local names :: [Name] = map(patternListVars, head(mrs).headPattern.patternSubPatternList);

  local subcase :: Expr = subCaseCompile.fst;
  local subCaseCompile::Pair<Expr [Message]> =
    compileCaseExpr(
      map(exprFromName, names) ++ annoAccesses ++ restExprs,
      map(\ mr::AbstractMatchRule -> mr.expandHeadPattern(annos), mrs),
      failCase, retType, head(mrs).location);
  -- TODO: head(mrs).location is probably not the correct thing to use here?? (generally)

  local annos :: [String] =
    nub(map(fst, flatMap((.patternNamedSubPatternList), map((.headPattern), mrs))));
  local annoAccesses :: [Expr] =
    map(\ n::String -> access(currExpr, '.', qNameAttrOccur(qName(l, n), location=l), location=l), annos);
  
  -- Maybe this one is more reasonable? We need to test examples and see what happens...
  local l :: Location = head(mrs).headPattern.location;

  return
    case head(mrs).headPattern of
    | prodAppPattern_named(qn,_,_,_,_,_) -> 
        pair(prodPattern(qn, '(', convStringsToVarBinders(names, l), ')', '->', subcase, location=l), subCaseCompile.snd)
    | intPattern(it) -> pair(integerPattern(it, '->', subcase, location=l), subCaseCompile.snd)
    | fltPattern(it) -> pair(floatPattern(it, '->', subcase, location=l), subCaseCompile.snd)
    | strPattern(it) -> pair(stringPattern(it, '->', subcase, location=l), subCaseCompile.snd)
    | truePattern(_) -> pair(booleanPattern("true", '->', subcase, location=l), subCaseCompile.snd)
    | falsePattern(_) -> pair(booleanPattern("false", '->', subcase, location=l), subCaseCompile.snd)
    | nilListPattern(_,_) -> pair(nilPattern(subcase, location=l), subCaseCompile.snd)
    | consListPattern(h,_,t) -> pair(conslstPattern(head(names), head(tail(names)), subcase, location=l), subCaseCompile.snd)
    | _ -> error("Can only have constructor patterns in allConCaseTransform")
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
AbstractMatchRule ::= headExpr::Expr  headType::Type  absRule::AbstractMatchRule
{
  -- If it's '_' we do nothing, otherwise, bind away!
  return case absRule of
  | matchRule(headPat :: restPat, cond, e) ->
    case headPat.patternVariableName of
    | just(pvn) ->
      matchRule(
        restPat,
        case cond of
        | just(pair(c, p)) -> just(pair(makeLet(absRule.location, pvn, headType, headExpr, c), p))
        | nothing() -> nothing()
        end,
        makeLet(absRule.location, pvn, headType, headExpr, e),
        location=absRule.location)
    | nothing() -> matchRule(restPat, cond, e, location=absRule.location)
    end
  | r -> r -- Don't crash when we see a rule with too few patterns (should be an error)
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

instance Eq AbstractMatchRule {
  eq = \ a::AbstractMatchRule b::AbstractMatchRule ->
    a.headPattern.patternSortKey == b.headPattern.patternSortKey;
}
instance Ord AbstractMatchRule {
  lte = \ a::AbstractMatchRule b::AbstractMatchRule ->
    a.headPattern.patternSortKey <= b.headPattern.patternSortKey;
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
  return group(sort(l));
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
    | matchRule(_, just(pair(c, nothing())), e) :: tl ->
      Silver_Expr {
        if $Expr{c}
        then $Expr{e}
        else $Expr{buildMatchWhenConditionals(tl, failExpr)}
      }
    | matchRule(_, just(pair(c, just(p))), e) :: tl ->
      Silver_Expr {
        case $Expr{c} of
        | $Pattern{p} -> $Expr{e}
        | _ -> $Expr{buildMatchWhenConditionals(tl, failExpr)}
        end
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


