grammar silver:compiler:extension:implicit_monads;

--import silver:compiler:definition:type:syntax only typerepTypeExpr;

terminal MCase_kwd 'case_any' lexer classes {KEYWORD, RESERVED};


synthesized attribute patternType::Type occurs on Pattern;
synthesized attribute patternTypeList::[Type] occurs on PatternList;

attribute patternTypeList, mUpSubst, mDownSubst occurs on MRuleList;
attribute patternTypeList occurs on MatchRule;


aspect production caseExpr_c
top::Expr ::= 'case' es::Exprs 'of' vbar::Opt_Vbar_t ml::MRuleList 'end'
{
  ml.mDownSubst = top.mDownSubst;
  local monadInExprs::Boolean =
    monadicallyUsedExpr(es.rawExprs, top.env, ml.mUpSubst, top.frame,
                        top.grammarName, top.compiledGrammars, top.config, top.flowEnv,
                        top.expectedMonad, false, top.originRules);
  local monadInClauses::Boolean =
    foldl((\b::Boolean a::AbstractMatchRule ->
            b ||
            let ty::Type = decorate a with {mDownSubst=ml.mUpSubst; temp_env=top.env; temp_frame=top.frame;
                                   temp_grammarName=top.grammarName; temp_compiledGrammars=top.compiledGrammars;
                                   temp_config=top.config; temp_flowEnv=top.flowEnv;
                                   temp_finalSubst=ml.mUpSubst; temp_downSubst=ml.mUpSubst;
                                   expectedMonad=top.expectedMonad;}.mtyperep
            in
              isMonad(ty, top.env) &&
              monadsMatch(ty, top.expectedMonad, ml.mUpSubst).fst && fst(monadsMatch(ty, top.expectedMonad, ml.mUpSubst))
            end),
          false,
          ml.matchRuleList);

  local basicFailure::Expr = mkStrFunctionInvocation(top.location, "silver:core:error",
                               [stringConst(terminal(String_t, 
                                  "\"Error: pattern match failed at " ++ top.grammarName ++
                                  " " ++ top.location.unparse ++ "\\n\""),
                                location=top.location)]);
  {-
    This will add in a Fail() for the appropriate monad (if the
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
  local failure::Expr =
        if isMonadFail(top.expectedMonad, top.env)
        then monadFail(top.location)
        else basicFailure;
  {-
    This sets up the actual output type.  If there's a monad, the
    return type given to the case expression is M(freshtype); if not,
    the return type is just a fresh type.
  -}
  local outty::Type =
        if monadInExprs
        then monadOfType(top.expectedMonad, freshType())
        else if monadInClauses
             then monadOfType(top.expectedMonad, freshType())
             else if isMonadFail(top.expectedMonad, top.env)
                  then monadOfType(top.expectedMonad, freshType())
                  else freshType(); --absolutely nothing is a monad
  --read the comment on the function below if you want to know what it is
  local attribute monadStuff::([(Type, Expr, String)], [Expr]);
  monadStuff = monadicMatchTypesNames(es.rawExprs, ml.patternTypeList, top.env, ml.mUpSubst, top.frame,
                                      top.grammarName, top.compiledGrammars, top.config, top.flowEnv, [],
                                      top.location, 1, top.expectedMonad, top.isRoot, top.originRules);

  {-
    We rewrite by creating a function which does the actual case
    matching, which we do monad rewriting on.  We then pass the actual
    things to match on into the function as arguments after the
    rewriting.
  -}
  local monadLocal::Expr =
        buildMultiLambda(map(\ p::Pair<Type Pair<Expr String>> ->
                         case p of
                         | pair(ty, pair(e, n)) -> pair(n, dropDecorated(ty))
                         end, monadStuff.fst), monadLocalBody, top.location);
  local monadLocalBody::Expr =
    buildMonadicBinds(monadStuff.1,
                      caseExpr(monadStuff.snd,
                               ml.matchRuleList, !isMonadFail(top.expectedMonad, top.env), failure,
                               outty, location=top.location), top.location);
  monadLocal.mDownSubst = ml.mUpSubst;
  monadLocal.frame = top.frame;
  monadLocal.grammarName = top.grammarName;
  monadLocal.compiledGrammars = top.compiledGrammars;
  monadLocal.config = top.config;
  monadLocal.env = top.env;
  monadLocal.flowEnv = top.flowEnv;
  monadLocal.downSubst = ml.mUpSubst;
  monadLocal.finalSubst = top.finalSubst;
  monadLocal.expectedMonad = top.expectedMonad;
  monadLocal.originRules = top.originRules;
  monadLocal.isRoot = false;
  top.monadRewritten =
      buildApplication(monadLocal.monadRewritten,
                       map(\ p::Pair<Type Pair<Expr String>> ->
                             case p of
                             | pair(ty, pair(e, n)) -> e
                             end,
                           monadStuff.fst), top.location);
  top.mtyperep = monadLocal.mtyperep.outputType;
  top.mUpSubst = monadLocal.mUpSubst;

  monadLocal.monadicallyUsed = false;
  --We get the monadic names out of the expressions bound in here and the rest off the fake forward (monadLocal)
  top.monadicNames =
     foldr(\x::Pair<Expr Type> l::[Expr] ->
             let a::Decorated Expr with {env, mDownSubst, frame, grammarName, downSubst, finalSubst, compiledGrammars, config, flowEnv, expectedMonad, isRoot, originRules} =
                decorate x.fst with {env=top.env; mDownSubst=top.mDownSubst;
                                     frame=top.frame; grammarName=top.grammarName; downSubst=top.mDownSubst;
                                     finalSubst=top.mDownSubst; compiledGrammars=top.compiledGrammars;
                                     config=top.config; flowEnv=top.flowEnv;expectedMonad=top.expectedMonad;
                                     isRoot=top.isRoot; originRules=top.originRules;}
             in if isMonad(a.mtyperep, top.env) && monadsMatch(a.mtyperep, top.expectedMonad, top.mDownSubst).fst &&
                   !isMonad(performSubstitution(x.snd, top.mDownSubst), top.env)
                then decorate x.fst with {env=top.env; mDownSubst=top.mDownSubst;
                                     frame=top.frame; grammarName=top.grammarName; downSubst=top.mDownSubst;
                                     finalSubst=top.mDownSubst; compiledGrammars=top.compiledGrammars;
                                     config=top.config; flowEnv=top.flowEnv; monadicallyUsed=true;
                                     expectedMonad=top.expectedMonad; isRoot=top.isRoot; originRules=top.originRules;}.monadicNames
                else []
             end ++ l,
           monadLocal.monadicNames, zipWith(\x::Expr y::Type -> pair(x,y), es.rawExprs, ml.patternTypeList));
}
--find if any of the expressions are being matched as their inner type
--if returns (true, ty), ty will be used to find the correct Fail()
function monadicallyUsedExpr
Boolean ::= elst::[Expr] env::Decorated Env sub::Substitution f::BlockContext gn::String
  cg::EnvTree<Decorated RootSpec> c::Decorated CmdArgs fe::Decorated FlowEnv em::Type
  iR::Boolean oR::[Decorated Expr]
{
  return case elst of
              | [] -> false
              | e::etl ->
                let etyp::Type = decorate e with {env=env; mDownSubst=sub; frame=f; grammarName=gn;
                                                  downSubst=sub; finalSubst=sub;
                                                  compiledGrammars=cg; config=c; flowEnv=fe;
                                                  expectedMonad=em; isRoot=iR; originRules=oR;}.mtyperep
                in
                  fst(monadsMatch(etyp, em, sub)) ||  monadicallyUsedExpr(etl, env, sub, f, gn, cg, c, fe, em, iR, oR)
                end
              end;
}
--make a list of the expression types, expressions and names for binding them as
--   well as a new list of expressions for the forward to use
--use a name from names when that is not empty; when empty, use a new name
function monadicMatchTypesNames
([(Type, (Expr, String))], [Expr]) ::=
  elst::[Expr] tylst::[Type] env::Decorated Env sub::Substitution f::BlockContext gn::String
  cg::EnvTree<Decorated RootSpec> c::Decorated CmdArgs fe::Decorated FlowEnv names::[String]
  loc::Location index::Integer em::Type iR::Boolean oR::[Decorated Expr]
{
  local attribute subcall::([(Type, Expr, String)], [Expr]);
  subcall = case elst, tylst of
            | _::etl, _::ttl -> monadicMatchTypesNames(etl, ttl, env, sub, f, gn, cg, c, fe, ntail, loc, index+1, em, iR, oR)
            | [], [] -> error("Should not access subcall in monadicMatchTypesNames with empty lists")
            | _, _ -> error("Both lists in monadicMatchTypesNames must be the same length")
            end;
  local ntail::[String] = if null(names) then [] else tail(names);
  local newName::String = if null(names)
                          then "__sv_expression_in_case" ++ toString(index) ++ "_" ++ toString(genInt())
                          else head(names);
  return case elst, tylst of
         | [], _ -> pair([], [])
         | _, [] -> pair([], elst)
         | e::etl, t::ttl ->
           let ety::Type = decorate e with {env=env; mDownSubst=sub; frame=f; grammarName=gn;
                                            downSubst=sub; finalSubst=sub;
                                            compiledGrammars=cg; config=c; flowEnv=fe;
                                            expectedMonad=em; isRoot=iR; originRules=oR;}.mtyperep
           in
             if isMonad(ety, env) && fst(monadsMatch(ety, em, sub))
             then ((ety, e, newName) :: subcall.1, 
                   baseExpr(qName(loc, newName), location=loc) :: subcall.2)
             else (subcall.1, e::subcall.2)
           end
         end;
}
--take a list of things to bind and the name to use in binding them, as well as
--   a base for the binding, and create an expression with all of them bound
function buildMonadicBinds
Expr ::= bindlst::[(Type, Expr, String)] base::Expr loc::Location
{
  return case bindlst of
         | [] -> base
         | pair(ty, pair(e, n))::rest ->
           buildApplication(monadBind(loc),
                [baseExpr(qNameId(name(n, loc), location=loc), location=loc),
                 buildLambda(n, monadInnerType(ty, loc), buildMonadicBinds(rest, base, loc), loc)],
               loc)
         end;
}

{-We need to essentially set up our own compilation here for
  monadRewritten because Ted doesn't like duplicating generated code.
  Putting the monad default fail into a let with a monad type is
  turning into a bind over the matching, so everything matching
  fails.-}
aspect production caseExpr
top::Expr ::= es::[Expr] ml::[AbstractMatchRule] complete::Boolean failExpr::Expr retType::Type {
  local monadLocal::Expr = result.fst;
  monadLocal.mDownSubst = top.mDownSubst;
  monadLocal.frame = top.frame;
  monadLocal.grammarName = top.grammarName;
  monadLocal.compiledGrammars = top.compiledGrammars;
  monadLocal.config = top.config;
  monadLocal.env = top.env;
  monadLocal.flowEnv = top.flowEnv;
  monadLocal.downSubst = top.mDownSubst;
  monadLocal.finalSubst = top.finalSubst;
  monadLocal.expectedMonad = top.expectedMonad;
  monadLocal.originRules = top.originRules;
  monadLocal.isRoot = top.isRoot;

  top.monadRewritten = monadLocal.monadRewritten;
  local result::Pair<Expr [Message]> = monadCompileCaseExpr(es, ml, failExpr, retType, top.location);

  top.merrors <- result.snd;
}


{-
  We need to compile the case expression for monad rewriting to take
  advantage of primitive matching's monad rewriting for patterns, but
  without ending up rewriting any lets which will turn out poorly with
  rewriting.  For example, the standard compilation involving
  rewriting to "let __fail = <expr> in <actual match>", which will not
  work for us if our failure expression has a monadic type, as it
  often does.

  This is essentially the standard compilation, but without inserting
  failure lets.
-}
function monadCompileCaseExpr
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
          map(monadAllConCaseTransform(freshCurrNameRef, tail(es), failExpr, retType, _),
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
        monadCompileCaseExpr(
             tail(es),
             map(bindHeadPattern(head(es), freshType(), _),
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
  local mixedCase :: Pair<Expr [Message]> = monadBuildMixedCaseMatches(es, ml, failExpr, retType, loc);

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
}
{-
  Turn matching on all constructors into primitive matching.
-}
function monadAllConCaseTransform
Pair<PrimPattern [Message]> ::= currExpr::Expr restExprs::[Expr]  failCase::Expr  retType::Type  mrs::[AbstractMatchRule]
{
  -- TODO: potential source of buggy error messages. We're using head(mrs) as the source of
  -- authority for the length of pattern variables to match against. But each match rule may
  -- actually have a different length (and .expandHeadPattern just applies whatever is there)
  -- This is an erroneous condition, but it means we transform into a maybe-more erroneous condition.
  local names :: [Name] = map(patternListVars, head(mrs).headPattern.patternSubPatternList);

  local subcase :: Expr = subCaseCompile.fst;
  local subCaseCompile::Pair<Expr [Message]> =
    monadCompileCaseExpr(
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
        pair(prodPattern(qn, '(', convStringsToVarBinders(names, l), ')', terminal(Arrow_kwd, "->", l), subcase, location=l), subCaseCompile.snd)
    | intPattern(it) -> pair(integerPattern(it, terminal(Arrow_kwd, "->", l), subcase, location=l), subCaseCompile.snd)
    | fltPattern(it) -> pair(floatPattern(it, terminal(Arrow_kwd, "->", l), subcase, location=l), subCaseCompile.snd)
    | strPattern(it) -> pair(stringPattern(it, terminal(Arrow_kwd, "->", l), subcase, location=l), subCaseCompile.snd)
    | truePattern(_) -> pair(booleanPattern("true", terminal(Arrow_kwd, "->", l), subcase, location=l), subCaseCompile.snd)
    | falsePattern(_) -> pair(booleanPattern("false", terminal(Arrow_kwd, "->", l), subcase, location=l), subCaseCompile.snd)
    | nilListPattern(_,_) -> pair(nilPattern(subcase, location=l), subCaseCompile.snd)
    | consListPattern(h,_,t) -> pair(conslstPattern(head(names), head(tail(names)), subcase, location=l), subCaseCompile.snd)
    | _ -> error("Can only have constructor patterns in monadAllConCaseTransform")
    end;
}
{-
  Build a matching for patterns of a mixed type, but by passing the
  rest of the match as the failure expression directly rather than
  using a let to bind it there.
-}
function monadBuildMixedCaseMatches
Pair<Expr [Message]> ::= es::[Expr] ml::[AbstractMatchRule] failExpr::Expr retType::Type loc::Location
{
  local freshFailName :: String = "__fail_" ++ toString(genInt());
  return if null(ml)
         then pair(failExpr, [])
         else let segments::Pair<[AbstractMatchRule] [AbstractMatchRule]> =
                            initialSegmentPatternType(ml)
              in
                let pairMixed::Pair<Expr [Message]> =
                    monadBuildMixedCaseMatches(es, segments.snd, failExpr, retType, loc)
                in
                  --the failure here is the expression for the other matches
                  let pairCompiled::Pair<Expr [Message]> =
                      monadCompileCaseExpr(es, segments.fst, pairMixed.fst, retType, loc)
                  in
                    pair(pairCompiled.fst, pairMixed.snd ++ pairCompiled.snd)
                  end
                end
              end;
}



--case expression that expands, using mplus, to possibly take multiple cases
concrete production mcaseExpr_c
top::Expr ::= 'case_any' es::Exprs 'of' vbar::Opt_Vbar_t ml::MRuleList 'end'
{
  top.unparse = "case_any " ++ es.unparse ++ " of " ++ ml.unparse ++ " end";

  top.merrors := [];
  top.merrors <- if isMonadPlus_instance then [] else [err(top.location, notMonadPlus)];

  ml.mDownSubst = top.mDownSubst;
  local monadInExprs::Boolean =
        monadicallyUsedExpr(es.rawExprs, top.env, ml.mUpSubst, top.frame,
                            top.grammarName, top.compiledGrammars, top.config, top.flowEnv,
                            top.expectedMonad, false, top.originRules);

  local mplus::Expr = monadPlus(top.location);
  local mzero::Expr = monadZero(top.location);

  local isMonadPlus_instance::Boolean = isMonadPlus(top.expectedMonad, top.env);
  local notMonadPlus::String =
        monadToString(top.expectedMonad) ++ " is not an instance of " ++
        "MonadPlus and cannot be used with case_any";

  --we need fresh names for the expressions being matched on, which we will use to only evaluate them once
  local newNames::[String] = map(\ x::Expr -> "__sv_mcase_var_" ++ toString(genInt()), es.rawExprs);
  local params::[Pair<String Type>] = zipWith(pair, newNames, ml.patternTypeList);
  local nameExprs::[Expr] = map(\x::String -> baseExpr(qName(top.location, x), location=top.location),
                                newNames);

  --Build a separate case expression for each match rule with mzero as the failure
  local caseExprs::[Expr] =
        map(\ x::AbstractMatchRule ->
             caseExpr(nameExprs, [x], false, mzero, top.mtyperep, location=top.location),
            ml.matchRuleList);
  --Rewrite the case expressions, wrapped in lambdas to provide the names
  local rewrittenCaseExprs::[Expr] =
         map(\ x::Expr ->
               decorate buildMultiLambda(params, x, top.location) with
                 {mDownSubst = top.mDownSubst;
                  frame = top.frame;
                  grammarName = top.grammarName;
                  compiledGrammars = top.compiledGrammars;
                  config = top.config;
                  env = top.env;
                  flowEnv = top.flowEnv;
                  downSubst = top.mDownSubst;
                  finalSubst = top.mDownSubst;
                  expectedMonad = top.expectedMonad;
                  isRoot = top.isRoot;
                  originRules = top.originRules;
                 }.monadRewritten,
             caseExprs);
  --Take the rewritten functions and apply them to the names to get expressions of a monad type
  local appliedCaseExprs::[Expr] =
        map(\ x::Expr -> buildApplication(x, nameExprs, top.location),
            rewrittenCaseExprs);
  --In some cases, they might not return monadic types, so we need to check and add return
  local typecheckedCaseExprs::[Expr] =
        map(\ x::Expr ->
              let ty::Type = decorate x with
                             {flowEnv = top.flowEnv; env = top.env; config=top.config;
                              compiledGrammars=top.compiledGrammars; grammarName=top.grammarName;
                              frame=top.frame; downSubst=top.mDownSubst; finalSubst=top.mDownSubst;
                              isRoot=top.isRoot; originRules=top.originRules;
                             }.typerep
              in
                if isMonad(ty, top.env) && monadsMatch(ty, top.expectedMonad, top.mDownSubst).fst
                then x
                else buildApplication(monadReturn(top.location), [x], top.location)
              end,
            appliedCaseExprs);
  --Mplus the rewritten-and-applied case expressions together
  local mplused::Expr = foldl(\rest::Expr current::Expr -> 
                               Silver_Expr{
                                 $Expr{mplus}($Expr{rest}, $Expr{current})
                               },
                              head(typecheckedCaseExprs), tail(typecheckedCaseExprs));
  --Use bind and lambdas to change the names of everything being matched on to only evaluate it once
  local applied::Expr =
        mcaseBindsApps(es.rawExprs, newNames, top.location, mplused,
                       top.env, ml.mUpSubst, top.frame, top.grammarName,
                       top.compiledGrammars, top.config, top.flowEnv,
                       top.expectedMonad, top.isRoot, top.originRules);

  top.monadRewritten = applied;
  top.mUpSubst = ml.mUpSubst;
  top.mtyperep = monadOfType(top.expectedMonad, freshType());

  forwards to if isMonadPlus_instance
              then applied
              else errorExpr([err(top.location, notMonadPlus)], location=top.location);
}

{-
  We walk down exprs and names to either bind head(exprs) using
  head(names) for it if it is monadic, otherwise simply applying it to
  a lambda to change the name.
-}
function mcaseBindsApps
Expr ::= exprs::[Expr] names::[String] loc::Location base::Expr
         env::Decorated Env sub::Substitution f::BlockContext
         gn::String cg::EnvTree<Decorated RootSpec> c::Decorated CmdArgs
         fe::Decorated FlowEnv em::Type iR::Boolean oR::[Decorated Expr]
{
  local subcall::Expr =
        mcaseBindsApps(tail(exprs), tail(names), loc, base,
                       env, sub, f, gn, cg, c, fe, em, iR, oR);
  return
     if null(exprs)
     then base
     else let ety::Type = decorate head(exprs) with
                             {env=env; mDownSubst=sub; frame=f; grammarName=gn;
                              downSubst=sub; finalSubst=sub;
                              compiledGrammars=cg; config=c; flowEnv=fe;
                              expectedMonad=em; originRules = oR;
                              isRoot = iR;}.mtyperep
           in
             if isMonad(ety, env) && fst(monadsMatch(ety, em, sub))
             then buildApplication(
                    monadBind(loc),
                              [if isDecorated(ety)
                               then mkStrFunctionInvocation(loc, "silver:core:new", [head(exprs)])
                               else head(exprs),
                               buildLambda(head(names), monadInnerType(ety, loc), subcall, loc)],
                              loc)
             else buildApplication(buildLambda(head(names), dropDecorated(ety), subcall, loc),
                                   [head(exprs)], loc)
           end;
}



--There are several thing we need for mtyperep on e which don't occur on match rules
--Therefore we need to pass them here
inherited attribute temp_flowEnv::Decorated FlowEnv;
inherited attribute temp_env::Decorated Env;
inherited attribute temp_config::Decorated CmdArgs;
inherited attribute temp_compiledGrammars::EnvTree<Decorated RootSpec>;
inherited attribute temp_grammarName::String;
inherited attribute temp_frame::BlockContext;
inherited attribute temp_finalSubst::Substitution;
inherited attribute temp_downSubst::Substitution;
attribute temp_flowEnv, temp_compiledGrammars, temp_grammarName occurs on MatchRule, MRuleList;
attribute mDownSubst occurs on MatchRule;


aspect production mRuleList_one
top::MRuleList ::= m::MatchRule
{
  m.temp_compiledGrammars = top.temp_compiledGrammars;
  m.temp_flowEnv = top.temp_flowEnv;
  m.temp_grammarName = top.temp_grammarName;
  m.mDownSubst = top.mDownSubst;

  top.patternTypeList = m.patternTypeList;
  top.mUpSubst = top.mDownSubst;
}

aspect production mRuleList_cons
top::MRuleList ::= h::MatchRule vbar::Vbar_kwd t::MRuleList
{
  h.temp_compiledGrammars = top.temp_compiledGrammars;
  h.temp_flowEnv = top.temp_flowEnv;
  h.temp_grammarName = top.temp_grammarName;
  h.mDownSubst = top.mDownSubst;

  t.temp_compiledGrammars = top.temp_compiledGrammars;
  t.temp_flowEnv = top.temp_flowEnv;
  t.temp_grammarName = top.temp_grammarName;
  t.mDownSubst = top.mDownSubst;

  top.patternTypeList = h.patternTypeList;
  --need to unify here with t.patternTypeList so, when we reach the case, if there is a
  --   monad pattern farther down where the first one is a wildcard/variable, we'll find
  --   it and not incorrectly identify something as being used non-monadically
  top.mUpSubst = foldl(\s::Substitution p::Pair<Type Type> ->
                       decorate check(p.fst, p.snd) with {downSubst=s;}.upSubst,
                      t.mUpSubst, zipWith(pair, h.patternTypeList, t.patternTypeList));
}

aspect production matchRule_c
top::MatchRule ::= pt::PatternList arr::Arrow_kwd e::Expr
{
  local ne::Expr = e;
  ne.flowEnv = top.temp_flowEnv;
  ne.env = top.env;
  ne.config = top.config;
  ne.compiledGrammars = top.temp_compiledGrammars;
  ne.grammarName = top.temp_grammarName;
  ne.frame = top.frame;
  ne.finalSubst = top.mDownSubst;
  ne.downSubst = top.mDownSubst;
  ne.originRules = [];
  ne.isRoot = false;

  top.patternTypeList = pt.patternTypeList;

  top.notExplicitAttributes := ne.notExplicitAttributes;
}

aspect production matchRuleWhen_c
top::MatchRule ::= pt::PatternList 'when' cond::Expr arr::Arrow_kwd e::Expr
{
  local ncond::Expr = cond;
  ncond.flowEnv = top.temp_flowEnv;
  ncond.env = top.env;
  ncond.config = top.config;
  ncond.compiledGrammars = top.temp_compiledGrammars;
  ncond.grammarName = top.temp_grammarName;
  ncond.frame = top.frame;
  ncond.finalSubst = top.mDownSubst;
  ncond.downSubst = top.mDownSubst;
  ncond.originRules = [];
  ncond.isRoot = false;
  local ne::Expr = e;
  ne.flowEnv = top.temp_flowEnv;
  ne.env = top.env;
  ne.config = top.config;
  ne.compiledGrammars = top.temp_compiledGrammars;
  ne.grammarName = top.temp_grammarName;
  ne.frame = top.frame;
  ne.finalSubst = top.mDownSubst;
  ne.downSubst = top.mDownSubst;
  ne.originRules = [];
  ne.isRoot = false;

  top.patternTypeList = pt.patternTypeList;

  top.notExplicitAttributes := ncond.notExplicitAttributes ++ ne.notExplicitAttributes;
}

aspect production matchRuleWhenMatches_c
top::MatchRule ::= pt::PatternList 'when' cond::Expr 'matches' p::Pattern arr::Arrow_kwd e::Expr
{
  local ncond::Expr = cond;
  ncond.flowEnv = top.temp_flowEnv;
  ncond.env = top.env;
  ncond.config = top.config;
  ncond.compiledGrammars = top.temp_compiledGrammars;
  ncond.grammarName = top.temp_grammarName;
  ncond.frame = top.frame;
  ncond.finalSubst = top.mDownSubst;
  ncond.downSubst = top.mDownSubst;
  ncond.originRules = [];
  ncond.isRoot = false;
  local ne::Expr = e;
  ne.flowEnv = top.temp_flowEnv;
  ne.env = top.env;
  ne.config = top.config;
  ne.compiledGrammars = top.temp_compiledGrammars;
  ne.grammarName = top.temp_grammarName;
  ne.frame = top.frame;
  ne.finalSubst = top.mDownSubst;
  ne.downSubst = top.mDownSubst;
  ne.originRules = [];
  ne.isRoot = false;

  top.patternTypeList = pt.patternTypeList;

  top.notExplicitAttributes := ncond.notExplicitAttributes ++ ne.notExplicitAttributes;
}

aspect production patternList_one
top::PatternList ::= p::Pattern
{
--  top.errors := p.errors;

  top.patternTypeList = [p.patternType];
}
aspect production patternList_more
top::PatternList ::= p::Pattern ',' ps1::PatternList
{
--  top.errors := p.errors ++ ps1.errors;

  top.patternTypeList = p.patternType :: ps1.patternTypeList;
}

aspect production patternList_nil
top::PatternList ::=
{
--  top.errors := [];

  top.patternTypeList = [];
}



attribute temp_flowEnv, temp_env, temp_config, temp_compiledGrammars, temp_grammarName,
          temp_frame, temp_finalSubst, temp_downSubst occurs on AbstractMatchRule;

attribute mDownSubst, merrors, mtyperep, expectedMonad occurs on AbstractMatchRule;

aspect production matchRule
top::AbstractMatchRule ::= pl::[Decorated Pattern] cond::Maybe<(Expr, Maybe<Pattern>)> e::Expr
{
  local ne::Expr = e;
  ne.flowEnv = top.temp_flowEnv;
  ne.env = top.temp_env;
  ne.config = top.temp_config;
  ne.compiledGrammars = top.temp_compiledGrammars;
  ne.grammarName = top.temp_grammarName;
  ne.frame = top.temp_frame;
  ne.finalSubst = top.temp_finalSubst;
  ne.downSubst = top.temp_downSubst;
  ne.originRules = [];
  ne.isRoot = false;

  ne.mDownSubst = top.mDownSubst;
  ne.expectedMonad = top.expectedMonad;
  top.merrors := []; --merrors from e should be picked up in primitive matching
  top.mtyperep = ne.mtyperep;

  top.notExplicitAttributes := ne.notExplicitAttributes;
}

