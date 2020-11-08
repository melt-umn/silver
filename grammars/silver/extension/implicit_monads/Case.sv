grammar silver:extension:implicit_monads;

--import silver:definition:type:syntax only typerepTypeExpr;

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
                        top.expectedMonad);
  local monadInClauses::Boolean =
    foldl((\b::Boolean a::AbstractMatchRule ->
            b ||
            let ty::Type = decorate a with {env=top.env; mDownSubst=ml.mUpSubst; frame=top.frame;
                                   grammarName=top.grammarName; compiledGrammars=top.compiledGrammars;
                                   config=top.config; flowEnv=top.flowEnv;
                                   expectedMonad=top.expectedMonad;}.mtyperep
            in
              isMonad(ty) && monadsMatch(ty, top.expectedMonad, ml.mUpSubst).fst && fst(monadsMatch(ty, top.expectedMonad, top.mUpSubst))
            end),
          false,
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
  local failure::Expr = case monadFail(top.expectedMonad, top.location) of
                        | right(e) -> e
                        | left(_) -> basicFailure
                        end;
  {-
    This sets up the actual output type.  If there's a monad, the
    return type given to the case expression is M(freshtype); if not,
    the return type is just a fresh type.
  -}
  local outty::Type = if monadInExprs
                      then monadOfType(top.expectedMonad, freshType())
                      else if monadInClauses
                           then monadOfType(top.expectedMonad, freshType())
                           else case monadFail(top.expectedMonad, top.location) of
                                | right(_) -> monadOfType(top.expectedMonad, freshType())
                                | left(_) -> freshType() --absolutely nothing is a monad
                                end;
  --read the comment on the function below if you want to know what it is
  local attribute monadStuff::Pair<[Pair<Type Pair<Expr String>>] [Expr]>;
  monadStuff = monadicMatchTypesNames(es.rawExprs, ml.patternTypeList, top.env, ml.mUpSubst, top.frame,
                                      top.grammarName, top.compiledGrammars, top.config, top.flowEnv, [],
                                      top.location, 1, top.expectedMonad);

  local monadLocal::Expr =
    buildMonadicBinds(monadStuff.fst,
                      caseExpr(monadStuff.snd,
                               ml.matchRuleList, failure,
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
  top.monadRewritten = monadLocal.monadRewritten;
  top.mtyperep = monadLocal.mtyperep;
  top.mUpSubst = monadLocal.mUpSubst;

  monadLocal.monadicallyUsed = false;
  --We get the monadic names out of the expressions bound in here and the rest off the fake forward (monadLocal)
  top.monadicNames =
     foldr(\x::Pair<Expr Type> l::[Expr] ->
             let a::Decorated Expr = decorate x.fst with {env=top.env; mDownSubst=top.mDownSubst;
                                     frame=top.frame; grammarName=top.grammarName; downSubst=top.mDownSubst;
                                     finalSubst=top.mDownSubst; compiledGrammars=top.compiledGrammars;
                                     config=top.config; flowEnv=top.flowEnv;expectedMonad=top.expectedMonad;}
             in if isMonad(a.mtyperep) && monadsMatch(a.mtyperep, top.expectedMonad, top.mDownSubst).fst && !isMonad(performSubstitution(x.snd, top.mDownSubst))
                then decorate x.fst with {env=top.env; mDownSubst=top.mDownSubst;
                                     frame=top.frame; grammarName=top.grammarName; downSubst=top.mDownSubst;
                                     finalSubst=top.mDownSubst; compiledGrammars=top.compiledGrammars;
                                     config=top.config; flowEnv=top.flowEnv; monadicallyUsed=true;
                                     expectedMonad=top.expectedMonad;}.monadicNames
                else []
             end ++ l,
           monadLocal.monadicNames, zipWith(\x::Expr y::Type -> pair(x,y), es.rawExprs, ml.patternTypeList));
}
--find if any of the expressions are being matched as their inner type
--if returns (true, ty), ty will be used to find the correct Fail()
function monadicallyUsedExpr
Boolean ::= elst::[Expr] env::Decorated Env sub::Substitution f::BlockContext gn::String
  cg::EnvTree<Decorated RootSpec> c::Decorated CmdArgs fe::Decorated FlowEnv em::Type
{
  return case elst of
              | [] -> false
              | e::etl ->
                let etyp::Type = decorate e with {env=env; mDownSubst=sub; frame=f; grammarName=gn;
                                                  downSubst=sub; finalSubst=sub;
                                                  compiledGrammars=cg; config=c; flowEnv=fe;
                                                  expectedMonad=em;}.mtyperep
                in
                  fst(monadsMatch(etyp, em, sub)) ||  monadicallyUsedExpr(etl, env, sub, f, gn, cg, c, fe, em)
                end
              end;
}
--make a list of the expression types, expressions and names for binding them as
--   well as a new list of expressions for the forward to use
--use a name from names when that is not empty; when empty, use a new name
function monadicMatchTypesNames
Pair<[Pair<Type Pair<Expr String>>] [Expr]> ::=
elst::[Expr] tylst::[Type] env::Decorated Env sub::Substitution f::BlockContext gn::String
  cg::EnvTree<Decorated RootSpec> c::Decorated CmdArgs fe::Decorated FlowEnv names::[String]
  loc::Location index::Integer em::Type
{
  local attribute subcall::Pair<[Pair<Type Pair<Expr String>>] [Expr]>;
  subcall = case elst, tylst of
            | _::etl, _::ttl -> monadicMatchTypesNames(etl, ttl, env, sub, f, gn, cg, c, fe, ntail, loc, index+1, em)
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
                                            expectedMonad=em;}.mtyperep
           in
             if fst(monadsMatch(ety, em, sub))
             then pair(pair(ety, pair(e, newName)) :: subcall.fst,
                       baseExpr(qName(loc, newName), location=loc) :: subcall.snd)
             else pair(subcall.fst, e::subcall.snd)
           end
         end;
}
--take a list of things to bind and the name to use in binding them, as well as
--   a base for the binding, and create an expression with all of them bound
function buildMonadicBinds
Expr ::= bindlst::[Pair<Type Pair<Expr String>>] base::Expr loc::Location
{
  return case bindlst of
         | [] -> base
         | pair(ty,pair(e,n))::rest ->
           Silver_Expr{ $Expr{monadBind(ty, loc)}
            ($Expr{e},
             $Expr{
               lambdap(
                 productionRHSCons(productionRHSElem(name(n, loc),
                                                     '::',
                                                     typerepTypeExpr(monadInnerType(ty),
                                                                     location=loc),
                                                     location=loc),
                                   productionRHSNil(location=loc),
                                   location=loc),
                 buildMonadicBinds(rest, base, loc),
                 location=loc)})}
         end;
}

{-We need to essentially set up our own compilation here for
  monadRewritten because Ted doesn't like duplicating generated code.
  Putting the monad default fail into a let with a monad type is
  turning into a bind over the matching, so everything matching
  fails.-}
aspect production caseExpr
top::Expr ::= es::[Expr] ml::[AbstractMatchRule] failExpr::Expr retType::Type {
  local m_partMRs :: Pair<[AbstractMatchRule] [AbstractMatchRule]> =
    partition((.isVarMatchRule), ml);
  local m_varRules :: [AbstractMatchRule] = m_partMRs.fst;
  local m_prodRules :: [AbstractMatchRule] = m_partMRs.snd;
  
  {--
   - All constructors? Then do a real primitive match.
   -}
  local m_freshCurrName :: String = "__curr_match_" ++ toString(genInt());
  local m_freshCurrNameRef :: Expr =
    baseExpr(qName(top.location, m_freshCurrName), location=top.location);
  local m_allConCase :: Expr =
    -- Annoyingly, this now needs to be a let in case of annotation patterns.
    makeLet(top.location,
      m_freshCurrName, freshType(), head(es), 
      matchPrimitive(
        m_freshCurrNameRef,
        typerepTypeExpr(retType, location=top.location),
        foldPrimPatterns(
          map(allConCaseTransform(m_freshCurrNameRef, tail(es), failExpr, retType, _),
          groupMRules(m_prodRules))),
        failExpr, location=top.location));
  
  {--
   - All variables? Just push a let binding inside each branch.
   -}
  local m_allVarCase :: Expr =
    caseExpr(tail(es),
      map(bindHeadPattern(head(es), freshType(){-whatever the first expression's type is?-}, _),
        ml),
      failExpr, retType, location=top.location);
      -- A quick note about that freshType() hack: putting it here means there's ONE fresh type
      -- generated, puching it inside 'bindHeadPattern' would generate multiple fresh types.
      -- So don't try that!
  
  {--
   - Mixed con/var? Partition, and push the vars into the "fail" branch.
   -}
  local m_mixedCase :: Expr =
      caseExpr(es, m_prodRules, caseExpr(es, m_varRules, failExpr, retType, location=top.location),
        retType, location=top.location);

   local monadLocal::Expr =
       case ml of
       | matchRule([], c, e) :: _ -> buildMatchWhenConditionals(ml, failExpr) -- valid or error case
       | _ -> if null(es) then failExpr -- error case
              else if null(m_varRules) then m_allConCase
              else if null(m_prodRules) then m_allVarCase
              else m_mixedCase
       end;
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

  top.monadRewritten = monadLocal.monadRewritten;
}



--case expression that expands, using mplus, to possibly take multiple cases
concrete production mcaseExpr_c
top::Expr ::= 'case_any' es::Exprs 'of' vbar::Opt_Vbar_t ml::MRuleList 'end'
{
  top.unparse = "case_any " ++ es.unparse ++ " of " ++ ml.unparse ++ " end";

  ml.mDownSubst = top.mDownSubst;
  local monadInExprs::Boolean =
    monadicallyUsedExpr(es.rawExprs, top.env, ml.mUpSubst, top.frame,
                        top.grammarName, top.compiledGrammars, top.config, top.flowEnv,
                        top.expectedMonad);
  local monadInClauses::Boolean =
    foldl((\b::Boolean a::AbstractMatchRule ->
            b ||
            let ty::Type = decorate a with {env=top.env; mDownSubst=ml.mUpSubst; frame=top.frame;
                                   grammarName=top.grammarName; compiledGrammars=top.compiledGrammars;
                                   config=top.config; flowEnv=top.flowEnv;
                                   expectedMonad=top.expectedMonad;}.mtyperep
            in
              isMonad(ty) && monadsMatch(ty, top.expectedMonad, top.mDownSubst).fst && fst(monadsMatch(ty, top.expectedMonad, top.mUpSubst))
            end),
          false,
          ml.matchRuleList);
  local mplus::Expr = case monadPlus(top.expectedMonad, top.location) of
                      | right(e) -> e
                      end;
  local mzero::Expr = case monadZero(top.expectedMonad, top.location) of
                      | right(e) -> e
                      end;

  --new names for using lets to bind the incoming expressions
  local newNames::[String] = map(\x::Expr -> "__sv_mcase_var_" ++ toString(genInt()), es.rawExprs);
  local nameExprs::[Expr] = map(\x::String -> baseExpr(qName(top.location, x), location=top.location),
                                newNames);
  local caseExprs::[Expr] = map(\x::AbstractMatchRule ->
                                 caseExpr(nameExprs, [x], mzero, freshType(), location=top.location),
                                ml.matchRuleList);
  local mplused::Expr = foldl(\rest::Expr current::Expr -> 
                               Silver_Expr{
                                 $Expr{mplus}($Expr{rest}, $Expr{current})
                               },
                              head(caseExprs), tail(caseExprs));
  --figure out which ones need to get bound in
  local attribute monadStuff::Pair<[Pair<Type Pair<Expr String>>] [Expr]>;
  monadStuff = monadicMatchTypesNames(es.rawExprs, ml.patternTypeList, top.env, ml.mUpSubst, top.frame,
                                      top.grammarName, top.compiledGrammars, top.config, top.flowEnv,
                                      newNames, top.location, 1, top.expectedMonad);
  --bind those ones in over the mpluses
  local monadLocal::Expr = buildMonadicBinds(monadStuff.fst, mplused, top.location);
  --put lets for all the names over the top (the binds will overwrite some)
  local letBound::Expr = foldr(\p::Pair<Expr String> rest::Expr ->
                                makeLet(top.location, p.snd, freshType(), p.fst, rest),
                               monadLocal, zipWith(pair, es.rawExprs, newNames));

  forwards to case monadPlus(top.expectedMonad, top.location) of
              | right(_) -> letBound
              | left(e) -> errorExpr([err(top.location, e)], location=top.location)
              end;
}

aspect production mRuleList_one
top::MRuleList ::= m::MatchRule
{
  top.patternTypeList = m.patternTypeList;
  top.mUpSubst = top.mDownSubst;
}

aspect production mRuleList_cons
top::MRuleList ::= h::MatchRule vbar::Vbar_kwd t::MRuleList
{
  top.patternTypeList = h.patternTypeList;
  --need to unify here with t.patternTypeList so, when we reach the case, if there is a
  --   monad pattern farther down where the first one is a wildcard/variable, we'll find
  --   it and not incorrectly identify something as being used non-monadically
  top.mUpSubst = foldl(\s::Substitution p::Pair<Type Type> ->
                       decorate check(p.fst, p.snd) with {downSubst=s;}.upSubst,
                      t.mUpSubst, zipWith(pair, h.patternTypeList, t.patternTypeList));
  t.mDownSubst = top.mDownSubst;
}

aspect production matchRule_c
top::MatchRule ::= pt::PatternList arr::Arrow_kwd e::Expr
{
  top.patternTypeList = pt.patternTypeList;
}

aspect production matchRuleWhen_c
top::MatchRule ::= pt::PatternList 'when' cond::Expr arr::Arrow_kwd e::Expr
{
  top.patternTypeList = pt.patternTypeList;
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



attribute env, mDownSubst, merrors, mtyperep, frame, grammarName, compiledGrammars,
          config, flowEnv, expectedMonad occurs on AbstractMatchRule;

aspect production matchRule
top::AbstractMatchRule ::= pl::[Decorated Pattern] cond::Maybe<Pair<Expr Maybe<Pattern>>> e::Expr
{
  e.env = top.env;
  e.mDownSubst = top.mDownSubst;
  e.frame = top.frame;
  e.grammarName = top.grammarName;
  e.compiledGrammars = top.compiledGrammars;
  e.config = top.config;
  e.flowEnv = top.flowEnv;
  e.downSubst = top.mDownSubst;
  e.finalSubst = top.mDownSubst;
  e.expectedMonad = top.expectedMonad;
  top.merrors := []; --merrors from e should be picked up in primitive matching
  top.mtyperep = e.mtyperep;
}

