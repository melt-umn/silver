grammar silver:modification:primitivepattern;

imports silver:definition:core;
imports silver:definition:env;
imports silver:definition:type;

import silver:definition:type:syntax only typerepType, TypeExpr;
import silver:extension:patternmatching only Arrow_kwd, Vbar_kwd, ensureDecoratedExpr; -- TODO remove

import silver:translation:java:core;
import silver:translation:java:type;

-- Actually only used for lists, in this file... TODO
import silver:modification:let_fix only makeSpecialLocalBinding, lexicalLocalDef, End_kwd;
import silver:definition:flow:ast only noVertex;

import silver:extension:list; -- Oh no, this is a hack! TODO

import silver:definition:type:syntax only typerepTypeExpr; --for monad stuff
import silver:modification:lambda_fn only lambdap; --for monad stuff01

terminal Match_kwd 'match' lexer classes {KEYWORD,RESERVED}; -- temporary!!!

nonterminal PrimPatterns with 
  config, grammarName, env, compiledGrammars, frame,
  location, unparse, errors,
  downSubst, upSubst, finalSubst,
  scrutineeType, returnType, translation,
  typerep, --the returned type from the patterns
  patternType,
  monadRewritten<PrimPatterns>,
  returnFun, returnify<PrimPatterns>;
nonterminal PrimPattern with 
  config, grammarName, env, compiledGrammars, frame,
  location, unparse, errors,
  downSubst, upSubst, finalSubst,
  scrutineeType, returnType, translation,
  typerep, --the returned type from the patterns
  patternType,
  monadRewritten<PrimPattern>,
  returnFun, returnify<PrimPattern>;

autocopy attribute scrutineeType :: Type;
autocopy attribute returnType :: Type;

--returnFun is the monad's defined Return for returnify
inherited attribute returnFun::Expr;
synthesized attribute returnify<a>::a;
--type matched by patterns
synthesized attribute patternType::Type;


concrete production matchPrimitiveConcrete
top::Expr ::= 'match' e::Expr 'return' t::TypeExpr 'with' pr::PrimPatterns 'else' '->' f::Expr 'end'
{
  --top.unparse = "match " ++ e.unparse ++ " return " ++ t.unparse ++ " with " ++ pr.unparse ++ " else -> " ++ f.unparse ++ "end";
  e.downSubst = top.downSubst;
  pr.downSubst = top.downSubst;
  f.downSubst = top.downSubst;

  forwards to matchPrimitive(e, t, pr, f, location=top.location);
}
abstract production matchPrimitive
top::Expr ::= e::Expr t::TypeExpr pr::PrimPatterns f::Expr
{
  --top.unparse = "match " ++ e.unparse ++ " return " ++ t.unparse ++ " with " ++ pr.unparse ++ " else -> " ++ f.unparse ++ "end";

  e.downSubst = top.downSubst;
  forward.downSubst = e.upSubst;
  pr.downSubst = top.downSubst;
  f.downSubst = top.downSubst;
  
  -- ensureDecoratedExpr is currently wrapping 'e' in 'exprRef' which suppresses errors
  -- TODO: the use of 'exprRef' should be reviewed, given that this error slipped through...
  top.errors := e.errors ++ forward.errors;
  
  forwards to matchPrimitiveReal(ensureDecoratedExpr(e), t, pr, f, location=top.location);
}
{--
 - @param e  The value to match against (should be DECORATED if it's nonterminal type at all)
 - @param t  The RETURN TYPE, explicitly.
 - @param pr  The cases of this match expression
 - @param f  The failure expression. (if the patterns don't match, evaluate to this.)
 -}
abstract production matchPrimitiveReal
top::Expr ::= e::Expr t::TypeExpr pr::PrimPatterns f::Expr
{
  --top.unparse = "match " ++ e.unparse ++ " return " ++ t.unparse ++ " with " ++ pr.unparse ++ " else -> " ++ f.unparse ++ "end";
  {-
    This ought to be using top.finalSubst, but, somewhere unknown, the
    substitution from pr seems to be getting lost, so I'm putting this in.
  -}
  local unparseType::String = prettyType(performSubstitution(top.typerep, top.upSubst));
  top.unparse = "match " ++ e.unparse ++ " return " ++ unparseType ++ " with " ++
  pr.unparse ++ " else -> " ++ f.unparse ++ "end {-" ++
  "pr:  " ++ prettyType(pr.typerep) ++ ", " ++ prettyType(performSubstitution(pr.typerep, top.finalSubst)) ++
  ", " ++ prettyType(performSubstitution(pr.typerep, pr.upSubst)) ++
  ", " ++ prettyType(performSubstitution(pr.typerep, top.upSubst)) ++
  "; " ++
  " f:  " ++ prettyType(f.typerep) ++ ", " ++ prettyType(performSubstitution(f.typerep, top.finalSubst)) ++
  ", " ++ prettyType(performSubstitution(f.typerep, f.upSubst)) ++
  "-}\n";

  top.typerep = if isMonad(e.typerep) && !isMonad(pr.patternType)
                then if isMonad(pr.typerep)
                     then pr.typerep
                     else if isMonad(f.typerep)
                          then f.typerep
                          else monadOfType(e.typerep, pr.typerep)
                else if isMonad(pr.typerep)
                     then pr.typerep
                     else f.typerep;

  top.errors := e.errors ++ t.errors ++ pr.errors ++ f.errors;
  
  {--
   - Invariant: if we were given an undecorated expression, it should have been
   - decorated by matchPrimitive before we got here, so we should either
   - have a decorated expr, or some other type.
   -}
  local attribute scrutineeType :: Type;
  scrutineeType = performSubstitution(e.typerep, e.upSubst);

  --check the type coming up with the type that's supposed to be
  --   coming out, which should, for case expressions (since nobody
  --   uses just this), be just a variable(?)
  local attribute errCheck1::TypeCheck; errCheck1.finalSubst = top.finalSubst;
  errCheck1 = if isMonad(pr.typerep)
              then if isMonad(f.typerep)
                   then check(pr.typerep, f.typerep)
                   else check(monadInnerType(pr.typerep), f.typerep)
              else if isMonad(f.typerep)
                   then check(pr.typerep, monadInnerType(f.typerep))
                   else check(pr.typerep, f.typerep);
  top.errors <-
    if errCheck1.typeerror
    then [err(top.location, " pattern expression should have type " ++ errCheck1.rightpp ++
              " instead it has type " ++ errCheck1.leftpp)]
    else [];

  -- ordinary threading: e, pr, f, errCheck1
  e.downSubst = top.downSubst;
  pr.downSubst = e.upSubst;
  f.downSubst = pr.upSubst;
  errCheck1.downSubst = f.upSubst;
  top.upSubst = errCheck1.upSubst;

  pr.scrutineeType = if isMonad(scrutineeType) && !isMonad(pr.patternType)
                     then monadInnerType(scrutineeType)
                     else scrutineeType;
  pr.returnType = t.typerep;

  {-
    To do the binding right, we should try to find a fresh name here.
    The trick used elsewhere to pass everything as an argument to a
    function for new names won't work here because we don't have
    expressions in the clauses that we can just pull out, nor can we,
    obviously, just pass the clauses in themselves.

    To get things working fast, we'll just use an "unlikely" name for
    binding e in and say that will mostly work.
  -}
  local eBind::Expr = monadBind(e.typerep, top.location);
  local eInnerType::TypeExpr = typerepTypeExpr(monadInnerType(e.typerep), location=bogusLoc());
  local binde_lambdaparams::ProductionRHS =
        productionRHSCons(productionRHSElem(name("bindingInAMatchExpression", bogusLoc()), '::',
                                            eInnerType, location=bogusLoc()),
                          productionRHSNil(location=bogusLoc()), location=bogusLoc());
  local outty::TypeExpr = typerepTypeExpr(top.typerep, location=bogusLoc());
  --bind e, just do the rest
  local justBind_e::Expr =
    applicationExpr(eBind,
                    '(',
                    snocAppExprs(oneAppExprs(presentAppExpr(e.monadRewritten, location=bogusLoc()),
                                             location=bogusLoc()),
                                 ',',
                                 presentAppExpr(
                                   lambdap(binde_lambdaparams,
                                           matchPrimitiveReal(baseExpr(qName(bogusLoc(),
                                                                             "bindingInAMatchExpression"),
                                                                       location=bogusLoc()),
                                                              outty, pr.monadRewritten, f.monadRewritten,
                                                              location=top.location),
                                           location=bogusLoc()),
                                   location=bogusLoc()),
                                 location=bogusLoc()),
                    ')',
                    location=top.location);
  --bind e, return f based on e's type
  local bind_e_return_f::Expr =
    applicationExpr(eBind,
                    '(',
                    snocAppExprs(oneAppExprs(presentAppExpr(e.monadRewritten, location=bogusLoc()),
                                             location=bogusLoc()),
                                 ',',
                                 presentAppExpr(
                                   lambdap(binde_lambdaparams,
                                           matchPrimitiveReal(baseExpr(qName(bogusLoc(),
                                                                             "bindingInAMatchExpression"),
                                                                       location=bogusLoc()),
                                                              outty, pr.monadRewritten,
                                                              Silver_Expr {
                                                                $Expr{monadReturn(e.typerep, bogusLoc())}
                                                                 ($Expr{f})
                                                              },
                                                              location=top.location),
                                           location=bogusLoc()),
                                   location=bogusLoc()),
                                 location=bogusLoc()),
                    ')',
                    location=top.location);
  --bind e, returnify pr based on e's type
  local prReturnify::PrimPatterns = pr.monadRewritten;
  prReturnify.returnFun = monadReturn(e.typerep, bogusLoc());
  local bind_e_returnify_pr::Expr =
    applicationExpr(eBind,
                    '(',
                    snocAppExprs(oneAppExprs(presentAppExpr(e.monadRewritten, location=bogusLoc()),
                                             location=bogusLoc()),
                                 ',',
                                 presentAppExpr(
                                   lambdap(binde_lambdaparams,
                                           matchPrimitiveReal(baseExpr(qName(bogusLoc(),
                                                                             "bindingInAMatchExpression"),
                                                                       location=bogusLoc()),
                                                              outty, prReturnify.returnify,
                                                              f.monadRewritten, location=top.location),
                                           location=bogusLoc()),
                                   location=bogusLoc()),
                                 location=bogusLoc()),
                    ')',
                    location=top.location);
  --bind e, returnify pr, return f based on e's type
  local bind_e_returnify_pr_return_f::Expr =
    applicationExpr(eBind,
                    '(',
                    snocAppExprs(oneAppExprs(presentAppExpr(e.monadRewritten, location=bogusLoc()),
                                             location=bogusLoc()),
                                 ',',
                                 presentAppExpr(
                                   lambdap(binde_lambdaparams,
                                           matchPrimitiveReal(baseExpr(qName(bogusLoc(),
                                                                             "bindingInAMatchExpression"),
                                                                       location=bogusLoc()),
                                                              outty, prReturnify.returnify,
                                                              Silver_Expr {
                                                                $Expr{monadReturn(e.typerep, bogusLoc())}
                                                                 ($Expr{f.monadRewritten})
                                                              },
                                                              location=top.location),
                                           location=bogusLoc()),
                                   location=bogusLoc()),
                                 location=bogusLoc()),
                    ')',
                    location=top.location);
  --return f from pr's return type
  local return_f::Expr =
    matchPrimitiveReal(e.monadRewritten, outty, pr.monadRewritten,
                       Silver_Expr {
                         $Expr{monadReturn(pr.typerep, bogusLoc())}($Expr{f.monadRewritten})
                       },
                       location=top.location);
  --returnify pr from f's type
  local ret_pr_from_f::PrimPatterns = pr.monadRewritten;
  ret_pr_from_f.returnFun = monadReturn(f.typerep, bogusLoc());
  local returnify_pr::Expr = matchPrimitiveReal(e.monadRewritten, outty, ret_pr_from_f.returnify,
                                                f.monadRewritten, location=top.location);
  --pick the right rewriting
  top.monadRewritten = if isMonad(e.typerep) && !isMonad(pr.patternType)
                       then if isMonad(pr.typerep)
                            then if isMonad(f.typerep)
                                 then justBind_e
                                 else bind_e_return_f
                            else if isMonad(f.typerep)
                                 then bind_e_returnify_pr
                                 else bind_e_returnify_pr_return_f
                       else if isMonad(pr.typerep)
                            then if isMonad(f.typerep)
                                 then top
                                 else return_f
                            else if isMonad(f.typerep)
                                 then returnify_pr
                                 else top;


  local resultTransType :: String = performSubstitution(t.typerep, top.finalSubst).transType;
  -- It is necessary to subst on scrutineeType here for the horrible reason that the type we're matching on
  -- may not be determined until we get to the constructor list. e.g. 'case error("lol") of pair(x,_) -> x end'
  -- which is legal, but if we don't do this will result in java translation errors (as the scrutinee will be
  -- type 'a' which is Object, which doesn't have .childAsIs for 'x'.)
  local scrutineeFinalType :: Type = performSubstitution(scrutineeType, top.finalSubst);
  local scrutineeTransType :: String = scrutineeFinalType.transType;
  
  top.translation = 
    "new common.PatternLazy<" ++ scrutineeTransType ++ ", " ++ resultTransType ++ ">() { " ++
      "public final " ++ resultTransType ++ " eval(final common.DecoratedNode context, " ++ scrutineeTransType ++ " scrutineeIter) {" ++
        (if scrutineeFinalType.isDecorated
         then
          "while(true) {" ++
           "final " ++ scrutineeTransType ++ " scrutinee = scrutineeIter; " ++ -- our Lazy needs a final variable
           "final common.Node scrutineeNode = scrutinee.undecorate(); " ++
            pr.translation ++
           "if(!scrutineeIter.undecorate().hasForward()) break;" ++ 
           "scrutineeIter = scrutineeIter.forward();" ++
          "}"
         else
          "final " ++ scrutineeTransType ++ " scrutinee = scrutineeIter; " ++ -- ditto
           pr.translation) ++
        "return " ++ f.translation ++ ";" ++ 
    "}}.eval(context, (" ++ scrutineeTransType ++")" ++ e.translation ++ ")";

  top.lazyTranslation = wrapThunk(top.translation, top.frame.lazyApplication); 
  -- TODO there seems to be an opportunity here to avoid an anon class somehow...
}

concrete production onePattern
top::PrimPatterns ::= p::PrimPattern
{
  top.unparse = p.unparse;
  
  top.errors := p.errors;
  top.translation = p.translation;
  
  p.downSubst = top.downSubst;
  top.upSubst = p.upSubst;

  top.typerep = p.typerep;
  top.patternType = p.patternType;

  p.returnFun = top.returnFun;
  top.returnify = onePattern(p.returnify, location=top.location);
  top.monadRewritten = onePattern(p.monadRewritten, location=top.location);
}
concrete production consPattern
top::PrimPatterns ::= p::PrimPattern '|' ps::PrimPatterns
{
  top.unparse = p.unparse ++ " | " ++ ps.unparse;
  
  top.errors := p.errors ++ ps.errors;
  top.translation = p.translation ++ "\nelse " ++ ps.translation;

  p.downSubst = top.downSubst;
  ps.downSubst = p.upSubst;
  errCheck1.downSubst = ps.upSubst;
  errCheck2.downSubst = errCheck1.upSubst;
  top.upSubst = errCheck2.upSubst;

  local errCheck1::TypeCheck = if isMonad(p.typerep)
                               then if isMonad(ps.typerep)
                                    then check(p.typerep, ps.typerep)
                                    else check(monadInnerType(p.typerep), ps.typerep)
                               else if isMonad(ps.typerep)
                                    then check(p.typerep, monadInnerType(ps.typerep))
                                    else check(p.typerep, ps.typerep);
  top.errors <-
    if errCheck1.typeerror
    then [err(top.location,
          --TODO this message should really be specialized based on what is and isn't monadic
              "pattern expression should have type " ++ errCheck1.leftpp ++
              " or a monad of this; instead it has type " ++ errCheck1.rightpp)]
    else [];
  local errCheck2::TypeCheck = check(p.patternType, ps.patternType);
  top.errors <-
    if errCheck2.typeerror
    then [err(top.location,
              "pattern matches " ++ errCheck2.leftpp ++
              " but it should match " ++ errCheck2.rightpp)]
    else [];

  top.typerep = if isMonad(p.typerep)
                then if isMonad(ps.typerep)
                     then ps.typerep
                     else p.typerep
                else ps.typerep;
  top.patternType = p.patternType; --go with the "earlier" type--mismatch handled by errors

  p.returnFun = top.returnFun;
  ps.returnFun = top.returnFun;
  top.returnify = consPattern(p.returnify, '|', ps.returnify, location=top.location);

  --when both are monads or both aren't, so we don't need to change anything
  local basicRewritten::PrimPatterns = consPattern(p.monadRewritten, '|', ps.monadRewritten,
                                                   location=top.location);
  --when the current clause is a monad but the rest aren't, wrap all of them in Return()
  local psReturnify::PrimPatterns = ps.monadRewritten;
  psReturnify.returnFun = monadReturn(p.typerep, bogusLoc());
  local returnifyRewritten::PrimPatterns = consPattern(p.monadRewritten, '|',
                                                       psReturnify.returnify,
                                                       location=top.location);
  --when the current clause is not a monad but the rest are, wrap the current one in Return()
  local pReturnify::PrimPattern = p.monadRewritten;
  pReturnify.returnFun = monadReturn(ps.typerep, bogusLoc());
  local returnRewritten::PrimPatterns = consPattern(pReturnify.returnify, '|',
                                                    ps.monadRewritten,
                                                    location=top.location);
  top.monadRewritten = if isMonad(p.typerep)
                       then if isMonad(ps.typerep)
                            then basicRewritten     --both monads
                            else returnifyRewritten --current monad, rest not
                       else if isMonad(ps.typerep)
                            then returnRewritten    --rest monad, current not
                            else basicRewritten;    --neither monads
}

-- TODO: Long term, I'd like to switch to having a PrimRule and rename PrimPatterns PrimRules.
-- However, we cannot do this yet, because the GADT case does CRAZY things with typing.
-- (Ideally, we'd be able to do those crazy things with constraints added to the
--  context there, instead...)

concrete production prodPattern
top::PrimPattern ::= qn::QName '(' ns::VarBinders ')' '->' e::Expr
{
  top.unparse = qn.unparse ++ "(" ++ ns.unparse ++ ") -> " ++ e.unparse;
  e.downSubst = top.downSubst;

  local isGadt :: Boolean =
    case qn.lookupValue.typerep.outputType of
    -- If the lookup is successful, and it's a production type, and it 
    -- constructs a nonterminal that either:
    --  1. has a non-type-variable parameter (e.g. Expr<Boolean>)
    --  2. has fewer free variables than parameters (e.g. Eq<a a>)
    -- THEN it's a gadt.
    | nonterminalType(_, tvs) -> !isOnlyTyVars(tvs) || length(tvs) != length(setUnionTyVarsAll(map((.freeVariables), tvs)))
    | _ -> false
    end;
  
  -- The reason we do it this way is because the threading of type information
  -- around is very different, and I don't want to confuse myself while I'm writing
  -- the code. After it works, perhaps these can be merged into one non-forwarding
  -- production, once the code is understood fully.
  forwards to if isGadt
              then prodPatternGadt(qn, ns, e, location=top.location)
              else prodPatternNormal(qn, ns, e, location=top.location);
}
abstract production prodPatternNormal
top::PrimPattern ::= qn::Decorated QName  ns::VarBinders  e::Expr
{
  top.unparse = qn.unparse ++ "(" ++ ns.unparse ++ ") -> " ++ e.unparse ++
  " {- e:  " ++ prettyType(e.typerep) ++ ", " ++
  prettyType(performSubstitution(e.typerep, top.finalSubst)) ++
  ", " ++ prettyType(performSubstitution(e.typerep, e.upSubst)) ++ "-}\n";

  local chk :: [Message] =
    if null(qn.lookupValue.dcls) || ns.varBinderCount == length(prod_type.inputTypes) then []
    else [err(qn.location, qn.name ++ " has " ++ toString(length(prod_type.inputTypes)) ++ " parameters but " ++ toString(ns.varBinderCount) ++ " patterns were provided")];
  
  top.errors := qn.lookupValue.errors ++ ns.errors ++ chk ++ e.errors;

  -- Turns the existential variables existential
  local prod_type :: Type =
    skolemizeProductionType(qn.lookupValue.typerep);
  -- Note that we're going to check prod_type against top.scrutineeType shortly.
  -- This is where the type variables become unified.
  
  ns.bindingTypes = prod_type.inputTypes;
  ns.bindingIndex = 0;
  ns.bindingNames = if null(qn.lookupValue.dcls) then [] else qn.lookupValue.dcl.namedSignature.inputNames;
  ns.matchingAgainst = if null(qn.lookupValue.dcls) then nothing() else just(qn.lookupValue.dcl);
  
  local attribute errCheck1 :: TypeCheck; errCheck1.finalSubst = top.finalSubst;
  --local attribute errCheck2 :: TypeCheck; errCheck2.finalSubst = top.finalSubst;

  --errCheck1 = check(decoratedType(prod_type.outputType), top.scrutineeType);
  errCheck1 = case top.scrutineeType of
              | decoratedType(t) -> check(decoratedType(prod_type.outputType), top.scrutineeType)
              | _ -> check(prod_type.outputType, top.scrutineeType)
              end;
  top.errors <- if errCheck1.typeerror
                then [err(top.location, qn.name ++ " has type " ++ errCheck1.leftpp ++ " but we're trying to match against " ++ errCheck1.rightpp)]
                else [];

  {- Checking the return types is now handled by passing types up to the top
  errCheck2 = check(e.typerep, top.returnType);
  top.errors <- if errCheck2.typeerror
                then [err(e.location, "pattern expression should have type " ++ errCheck2.rightpp ++ " instead it has type " ++ errCheck2.leftpp)]
                else [];
  -}

  -- Thread NORMALLY! YAY!
  errCheck1.downSubst = top.downSubst;
  e.downSubst = errCheck1.upSubst;
  --errCheck2.downSubst = e.upSubst;
  top.upSubst = e.upSubst;
  
  e.env = newScopeEnv(ns.defs, top.env);
  
  top.translation = "if(scrutineeNode instanceof " ++ makeClassName(qn.lookupValue.fullName) ++
    ") { " ++ ns.translation ++ " return (" ++ performSubstitution(top.returnType, top.finalSubst).transType ++ ")" ++ e.translation ++ "; }";

  top.typerep = e.typerep;
  top.patternType = prod_type.outputType;

  top.returnify = prodPatternNormal(qn, ns,
                                    Silver_Expr { $Expr{top.returnFun}($Expr{e}) },
                                    location=top.location);
  top.monadRewritten = prodPatternNormal(qn, ns, e.monadRewritten, location=top.location);
}

abstract production prodPatternGadt
top::PrimPattern ::= qn::Decorated QName  ns::VarBinders  e::Expr
{
  top.unparse = qn.unparse ++ "(" ++ ns.unparse ++ ") -> " ++ e.unparse;
  
  local chk :: [Message] =
    if null(qn.lookupValue.dcls) || ns.varBinderCount == length(prod_type.inputTypes) then []
    else [err(qn.location, qn.name ++ " has " ++ toString(length(prod_type.inputTypes)) ++ " parameters but " ++ toString(ns.varBinderCount) ++ " patterns were provided")];
  
  top.errors := qn.lookupValue.errors ++ ns.errors ++ chk ++ e.errors;

  local prod_type :: Type =
    fullySkolemizeProductionType(qn.lookupValue.typerep); -- that says FULLY. See the comments on that function.
  
  ns.bindingTypes = prod_type.inputTypes;
  ns.bindingIndex = 0;
  ns.bindingNames = if null(qn.lookupValue.dcls) then [] else qn.lookupValue.dcl.namedSignature.inputNames;
  ns.matchingAgainst = if null(qn.lookupValue.dcls) then nothing() else just(qn.lookupValue.dcl);
  
  local attribute errCheck1 :: TypeCheck; errCheck1.finalSubst = composeSubst(errCheck1.upSubst, top.finalSubst); -- part of the
  --local attribute errCheck2 :: TypeCheck; errCheck2.finalSubst = composeSubst(errCheck2.upSubst, top.finalSubst); -- threading hack
  
  errCheck1 = check(decoratedType(prod_type.outputType), top.scrutineeType);
  top.errors <- if errCheck1.typeerror
                then [err(top.location, qn.name ++ " has type " ++ errCheck1.leftpp ++ " but we're trying to match against " ++ errCheck1.rightpp)]
                else [];

  {- Checking the return types is now handled by passing types up to the top  
  errCheck2 = check(e.typerep, top.returnType);
  top.errors <- if errCheck2.typeerror
                then [err(e.location, "pattern expression should have type " ++ errCheck2.rightpp ++ " instead it has type " ++ errCheck2.leftpp)]
                else [];
  -}

  -- For GADTs, threading gets a bit weird.
  -- TODO: we SHOULD check that the "base type" is accurate for the pattern / scrutineeType first.
  --       but for now for simplicity, we avoid that.
  -- So for now, we're just skipping over this case entirely:
  top.upSubst = top.downSubst;
  
  -- AFTER everything is done elsewhere, we come back with finalSubst, and we produce the refinement, and thread THAT through everything.
  errCheck1.downSubst = composeSubst(top.finalSubst, produceRefinement(top.scrutineeType, decoratedType(prod_type.outputType)));
  e.downSubst = errCheck1.upSubst;
  --errCheck2.downSubst = e.upSubst;
  -- Okay, now update the finalSubst....
  e.finalSubst = e.upSubst;
  -- Here ends the hack
  
  e.env = newScopeEnv(ns.defs, top.env);
  
  top.translation = "if(scrutineeNode instanceof " ++ makeClassName(qn.lookupValue.fullName) ++
    ") { " ++ ns.translation ++ " return (" ++ performSubstitution(top.returnType, top.finalSubst).transType ++ ")" ++ e.translation ++ "; }";

  top.typerep = e.typerep;
  top.patternType = prod_type.outputType;

  top.returnify = prodPatternGadt(qn, ns,
                                  Silver_Expr { $Expr{top.returnFun}($Expr{e}) },
                                  location=top.location);
  top.monadRewritten = prodPatternGadt(qn, ns, e.monadRewritten, location=top.location);
}

-- TODO: We currently provide the below for ease of translation from complex case exprs, but
-- we should really translate those to appropriate expressions, and not handle primitive types here

abstract production integerPattern
top::PrimPattern ::= i::Int_t '->' e::Expr
{
  top.unparse = i.lexeme ++ " -> " ++ e.unparse;
  
  top.errors := e.errors;

  local attribute errCheck1 :: TypeCheck; errCheck1.finalSubst = top.finalSubst;
  --local attribute errCheck2 :: TypeCheck; errCheck2.finalSubst = top.finalSubst;
  
  errCheck1 = check(intType(), top.scrutineeType);
  top.errors <- if errCheck1.typeerror
                then [err(top.location, i.lexeme ++ " is an " ++ errCheck1.leftpp ++ " but we're trying to match against " ++ errCheck1.rightpp)]
                else [];

  {- Checking the return types is now handled by passing types up to the top
  errCheck2 = check(e.typerep, top.returnType);
  top.errors <- if errCheck2.typeerror
                then [err(e.location, "pattern expression should have type " ++ errCheck2.rightpp ++ " instead it has type " ++ errCheck2.leftpp)]
                else [];
  -}

  errCheck1.downSubst = top.downSubst;
  e.downSubst = errCheck1.upSubst;
  --errCheck2.downSubst = e.upSubst;
  top.upSubst = errCheck1.upSubst;

  top.translation = "if(scrutinee == " ++ i.lexeme ++ ") { return (" ++ performSubstitution(top.returnType, top.finalSubst).transType ++ ")" ++
                         e.translation ++ "; }";

  top.typerep = e.typerep;
  top.patternType = intType();

  top.returnify = integerPattern(i, '->', 
                                 Silver_Expr { $Expr{top.returnFun}($Expr{e}) },
                                 location=top.location);
  top.monadRewritten = integerPattern(i, '->', e.monadRewritten, location=top.location);
}
abstract production floatPattern
top::PrimPattern ::= f::Float_t '->' e::Expr
{
  top.unparse = f.lexeme ++ " -> " ++ e.unparse;
  
  top.errors := e.errors;
  
  local attribute errCheck1 :: TypeCheck; errCheck1.finalSubst = top.finalSubst;
  --local attribute errCheck2 :: TypeCheck; errCheck2.finalSubst = top.finalSubst;

  errCheck1 = check(floatType(), top.scrutineeType);
  top.errors <- if errCheck1.typeerror
                then [err(top.location, f.lexeme ++ " is a " ++ errCheck1.leftpp ++ " but we're trying to match against " ++ errCheck1.rightpp)]
                else [];

  {- Checking the return types is now handled by passing types up to the top
  errCheck2 = check(e.typerep, top.returnType);
  top.errors <- if errCheck2.typeerror
                then [err(e.location, "pattern expression should have type " ++ errCheck2.rightpp ++ " instead it has type " ++ errCheck2.leftpp)]
                else [];
  -}

  errCheck1.downSubst = top.downSubst;
  e.downSubst = errCheck1.upSubst;
  --errCheck2.downSubst = e.upSubst;
  top.upSubst = e.upSubst;

  top.translation = "if(scrutinee == " ++ f.lexeme ++ ") { return (" ++ performSubstitution(top.returnType, top.finalSubst).transType ++ ")" ++
                         e.translation ++ "; }";

  top.typerep = e.typerep;
  top.patternType = floatType();

  top.returnify = floatPattern(f, '->', 
                               Silver_Expr { $Expr{top.returnFun}($Expr{e}) },
                               location=top.location);
  top.monadRewritten = floatPattern(f, '->', e.monadRewritten, location=top.location);
}
abstract production stringPattern
top::PrimPattern ::= i::String_t '->' e::Expr
{
  top.unparse = i.lexeme ++ " -> " ++ e.unparse;
  
  top.errors := e.errors;
  
  local attribute errCheck1 :: TypeCheck; errCheck1.finalSubst = top.finalSubst;
  --local attribute errCheck2 :: TypeCheck; errCheck2.finalSubst = top.finalSubst;

  errCheck1 = check(stringType(), top.scrutineeType);
  top.errors <- if errCheck1.typeerror
                then [err(top.location, i.lexeme ++ " is a " ++ errCheck1.leftpp ++ " but we're trying to match against " ++ errCheck1.rightpp)]
                else [];

  {- Checking the return types is now handled by passing types up to the top
  errCheck2 = check(e.typerep, top.returnType);
  top.errors <- if errCheck2.typeerror
                then [err(e.location, "pattern expression should have type " ++ errCheck2.rightpp ++ " instead it has type " ++ errCheck2.leftpp)]
                else [];
  -}

  errCheck1.downSubst = top.downSubst;
  e.downSubst = errCheck1.upSubst;
  --errCheck2.downSubst = e.upSubst;
  top.upSubst = e.upSubst;

  top.translation = "if(scrutinee.equals(" ++ i.lexeme ++ ")) { return (" ++ performSubstitution(top.returnType, top.finalSubst).transType ++ ")" ++
                         e.translation ++ "; }";

  top.typerep = e.typerep;
  top.patternType = stringType();

  top.returnify = stringPattern(i, '->', 
                                Silver_Expr { $Expr{top.returnFun}($Expr{e}) },
                                location=top.location);
  top.monadRewritten = stringPattern(i, '->', e.monadRewritten, location=top.location);
}
abstract production booleanPattern
top::PrimPattern ::= i::String '->' e::Expr
{
  top.unparse = i ++ " -> " ++ e.unparse;
  
  top.errors := e.errors;
  
  local attribute errCheck1 :: TypeCheck; errCheck1.finalSubst = top.finalSubst;
  --local attribute errCheck2 :: TypeCheck; errCheck2.finalSubst = top.finalSubst;

  errCheck1 = check(boolType(), top.scrutineeType);
  top.errors <- if errCheck1.typeerror
                then [err(top.location, i ++ " is a " ++ errCheck1.leftpp ++ " but we're trying to match against " ++ errCheck1.rightpp)]
                else [];

  {- Checking the return types is now handled by passing types up to the top
  errCheck2 = check(e.typerep, top.returnType);
  top.errors <- if errCheck2.typeerror
                then [err(e.location, "pattern expression should have type " ++ errCheck2.rightpp ++ " instead it has type " ++ errCheck2.leftpp)]
                else [];
  -}

  errCheck1.downSubst = top.downSubst;
  e.downSubst = errCheck1.upSubst;
  --errCheck2.downSubst = e.upSubst;
  top.upSubst = e.upSubst;

  top.translation = "if(scrutinee == " ++ i ++ ") { return (" ++ performSubstitution(top.returnType, top.finalSubst).transType ++ ")" ++
                         e.translation ++ "; }";

  top.typerep = e.typerep;
  top.patternType = stringType();

  top.returnify = booleanPattern(i, '->', 
                                 Silver_Expr { $Expr{top.returnFun}($Expr{e}) },
                                 location=top.location);
  top.monadRewritten = booleanPattern(i, '->', e.monadRewritten, location=top.location);
}
abstract production nilPattern
top::PrimPattern ::= e::Expr
{
  top.unparse = "nil() -> " ++ e.unparse;
  
  top.errors := e.errors;
  
  local attribute errCheck1 :: TypeCheck; errCheck1.finalSubst = top.finalSubst;
  --local attribute errCheck2 :: TypeCheck; errCheck2.finalSubst = top.finalSubst;

  local attribute thisListType::Type = listType(freshType());

  errCheck1 = check(thisListType, top.scrutineeType);
  top.errors <- if errCheck1.typeerror
                then [err(top.location, "nil matches lists but we're trying to match against " ++ errCheck1.rightpp)]
                else [];

  {- Checking the return types is now handled by passing types up to the top
  errCheck2 = check(e.typerep, top.returnType);
  top.errors <- if errCheck2.typeerror
                then [err(e.location, "pattern expression should have type " ++ errCheck2.rightpp ++ " instead it has type " ++ errCheck2.leftpp)]
                else [];
  -}

  errCheck1.downSubst = top.downSubst;
  e.downSubst = errCheck1.upSubst;
  --errCheck2.downSubst = e.upSubst;
  top.upSubst = e.upSubst;

  top.translation = "if(scrutinee.nil()) { return (" ++ performSubstitution(top.returnType, top.finalSubst).transType ++ ")" ++
                         e.translation ++ "; }";

  top.typerep = e.typerep;
  top.patternType = thisListType;

  top.returnify = nilPattern(Silver_Expr { $Expr{top.returnFun}($Expr{e}) },
                             location=top.location);
  top.monadRewritten = nilPattern(e.monadRewritten, location=top.location);
}
abstract production conslstPattern
top::PrimPattern ::= h::Name t::Name e::Expr
{
  top.unparse = "cons(" ++ h.unparse ++ ", " ++ t.unparse ++ ") -> " ++ e.unparse;
  
  top.errors := e.errors;

  local h_fName :: String = toString(genInt()) ++ ":" ++ h.name;
  local t_fName :: String = toString(genInt()) ++ ":" ++ t.name;
  local attribute errCheck1 :: TypeCheck; errCheck1.finalSubst = top.finalSubst;
  --local attribute errCheck2 :: TypeCheck; errCheck2.finalSubst = top.finalSubst;
  local elemType :: Type = freshType();

  errCheck1 = check(listType(elemType), top.scrutineeType);
  top.errors <- if errCheck1.typeerror
                then [err(top.location, "cons matches lists but we're trying to match against " ++ errCheck1.rightpp)]
                else [];

  {- Checking the return types is now handled by passing types up to the top
  errCheck2 = check(e.typerep, top.returnType);
  top.errors <- if errCheck2.typeerror
                then [err(e.location, "pattern expression should have type " ++ errCheck2.rightpp ++ " instead it has type " ++ errCheck2.leftpp)]
                else [];
  -}

  errCheck1.downSubst = top.downSubst;
  e.downSubst = errCheck1.upSubst;
  --errCheck2.downSubst = e.upSubst;
  top.upSubst = e.upSubst;
  
  local consdefs :: [Def] =
    [lexicalLocalDef(top.grammarName, top.location, h_fName, elemType, noVertex(), []),
     lexicalLocalDef(top.grammarName, top.location, t_fName, top.scrutineeType, noVertex(), [])];
  
  e.env = newScopeEnv(consdefs, top.env);
  
  top.translation =
    let
      elemTrans :: String = performSubstitution(elemType, top.finalSubst).transType,
      listTrans :: String = performSubstitution(top.scrutineeType, top.finalSubst).transType
    in
      "if(!scrutineeIter.nil()) {" ++
      makeSpecialLocalBinding(h_fName, s"(${elemTrans})scrutinee.head()", elemTrans) ++
      makeSpecialLocalBinding(t_fName, s"(${listTrans})scrutinee.tail()", listTrans) ++
      "return " ++ e.translation ++ "; }"
    end;

  top.typerep = e.typerep;
  top.patternType = listType(elemType);

  top.returnify = conslstPattern(h, t, Silver_Expr { $Expr{top.returnFun}($Expr{e}) },
                                 location=top.location);
  top.monadRewritten = conslstPattern(h, t, e.monadRewritten, location=top.location);
}


