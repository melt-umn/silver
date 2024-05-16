grammar silver:compiler:extension:implicit_monads;

attribute mtyperep, merrors, patternType, monadRewritten<PrimPatterns>,
          mDownSubst, mUpSubst, monadicNames, expectedMonad,
          returnFun, returnify<PrimPatterns> occurs on PrimPatterns;
attribute mtyperep, merrors, patternType, monadRewritten<PrimPattern>,
          mDownSubst, mUpSubst, monadicNames, expectedMonad,
          returnFun, returnify<PrimPattern> occurs on PrimPattern;
propagate expectedMonad on PrimPatterns, PrimPattern;

--returnFun is the monad's defined Return for returnify
inherited attribute returnFun::Expr;
synthesized attribute returnify<a>::a;
--type matched by patterns; provided by Case.sv
--synthesized attribute patternType::Type;


aspect production matchPrimitiveConcrete
top::Expr ::= 'match' e::Expr 'return' t::TypeExpr 'with' pr::PrimPatterns 'else' arr::Arrow_kwd f::Expr 'end'
{
}
aspect production matchPrimitive
top::Expr ::= e::Expr t::TypeExpr pr::PrimPatterns f::Expr
{
}

aspect production matchPrimitiveReal
top::Expr ::= e::Expr t::TypeExpr pr::PrimPatterns f::Expr
{
  --if e is the implicit monad
  local eIsMonadic::Boolean = isMonad(e.mtyperep, top.env) && monadsMatch(e.mtyperep, top.expectedMonad, top.mDownSubst).fst;
  --if the pattern type is the implicit monad
  local prPattIsMonadic::Boolean = isMonad(pr.patternType, top.env) && monadsMatch(pr.patternType, top.expectedMonad, top.mDownSubst).fst;
  --the return type of the patterns is the implicit monad
  local prRetIsMonadic::Boolean = isMonad(pr.mtyperep, top.env) && monadsMatch(pr.mtyperep, top.expectedMonad, top.mDownSubst).fst;
  --the type of f is the implicit monad
  local fIsMonadic::Boolean = isMonad(f.mtyperep, top.env) && monadsMatch(f.mtyperep, top.expectedMonad, top.mDownSubst).fst;
  --the type t is the implicit monad
  local tIsMonadic::Boolean = isMonad(t.typerep, top.env) && monadsMatch(t.typerep, top.expectedMonad, top.mDownSubst).fst;

  top.mtyperep = if eIsMonadic
                 then if tIsMonadic
                      then t.typerep
                      else monadOfType(t.typerep, top.expectedMonad)
                 else if prRetIsMonadic
                      then pr.mtyperep
                      else if tIsMonadic
                           then t.typerep
                           else f.mtyperep;

  top.merrors := e.merrors ++ pr.merrors ++ f.merrors;
  top.merrors <- if prPattIsMonadic
                 then [errFromOrigin(top, "Cannot match on implicit monadic type " ++ prettyType(pr.patternType))]
                 else [];

  e.mDownSubst = top.mDownSubst;
  pr.mDownSubst = e.mUpSubst;
  f.mDownSubst = pr.mUpSubst;
  top.mUpSubst = f.mUpSubst;

  e.monadicallyUsed = eIsMonadic;
  f.monadicallyUsed = false;
  top.monadicNames = e.monadicNames ++ pr.monadicNames ++ f.monadicNames;

  local freshname::String = "__sv_bindingInAMatchExpression_" ++ toString(genInt());
  nondecorated local eBind::Expr = monadBind();
  nondecorated local eInnerType::TypeExpr = typerepTypeExpr(monadInnerType(e.mtyperep));
  nondecorated local binde_lambdaparams::LambdaRHS =
    lambdaRHSCons(lambdaRHSElemIdTy(name(freshname), '::',
                                        eInnerType),
                      lambdaRHSNil());
  --Since we sometimes need to just use pure() over the top of everything to get a
  --   monad out, we use a fresh type rather than the top.mtyperep
  nondecorated local outty::TypeExpr = typerepTypeExpr(freshType());

  {-We need to make sure that, if we are matching on a decorable type,
    it is decorated.  We need to check both whether the type is
    decorable, or, in case that is a variable because we do as little
    typechecking as possible, whether the pattern type is decorable
    (and to avoid double-decoration, we check it isn't decorated
    already).-}
  local eMTyDecorable::Boolean =
        if eIsMonadic
        then isDecorable(performSubstitution(monadInnerType(e.mtyperep), e.mUpSubst), top.env) ||
             (!performSubstitution(monadInnerType(e.mtyperep), e.mUpSubst).isDecorated && isDecorable(pr.patternType, top.env))
        else isDecorable(performSubstitution(e.mtyperep, e.mUpSubst), top.env) ||
             (!performSubstitution(e.mtyperep, e.mUpSubst).isDecorated && isDecorable(pr.patternType, top.env));
  nondecorated local decName::Expr =
    if eMTyDecorable
    then decorateExprWithEmpty('decorate', baseExpr(qName(freshname)),
                               'with', '{', '}')
    else baseExpr(qName(freshname));
  nondecorated local decE::Expr =
    if eMTyDecorable
    then decorateExprWithEmpty('decorate', e.monadRewritten, 'with', '{', '}')
    else e.monadRewritten;

  --bind e, just do the rest
  nondecorated local justBind_e::Expr =
    buildApplication(eBind,
                     [e.monadRewritten, lambdap(binde_lambdaparams,
                                           matchPrimitiveReal(decName,
                                                              outty, pr.monadRewritten, f.monadRewritten))]);
  --bind e, return f based on e's type
  nondecorated local bind_e_return_f::Expr =
    buildApplication(eBind,
                     [e.monadRewritten, lambdap(binde_lambdaparams,
                                           matchPrimitiveReal(decName,
                                                              outty, pr.monadRewritten,
                                                              buildApplication(monadReturn(),
                                                                               [f.monadRewritten])))]);
  --bind e, returnify pr based on e's type
  local prReturnify::PrimPatterns = pr.monadRewritten;
  prReturnify.returnFun = monadReturn();
  prReturnify.grammarName = top.grammarName;
  prReturnify.env = top.env;
  prReturnify.config = top.config;
  nondecorated local bind_e_returnify_pr::Expr =
    buildApplication(eBind,
                     [e.monadRewritten, lambdap(binde_lambdaparams,
                                           matchPrimitiveReal(decName,
                                                              outty, prReturnify.returnify,
                                                              f.monadRewritten))]);
  --bind e, returnify pr, return f based on e's type
  nondecorated local bind_e_returnify_pr_return_f::Expr =
    buildApplication(eBind,
                     [e.monadRewritten, lambdap(binde_lambdaparams,
                                           matchPrimitiveReal(decName,
                                                              outty, prReturnify.returnify,
                                                              buildApplication(monadReturn(),
                                                                 [f.monadRewritten])))]);
  --return f from pr's return type
  nondecorated local return_f::Expr =
    matchPrimitiveReal(decE, outty, pr.monadRewritten,
                       buildApplication(monadReturn(), [f.monadRewritten]));
  --returnify pr from f's type
  local ret_pr_from_f::PrimPatterns = pr.monadRewritten;
  ret_pr_from_f.returnFun = monadReturn();
  ret_pr_from_f.grammarName = top.grammarName;
  ret_pr_from_f.env = top.env;
  ret_pr_from_f.config = top.config;
  nondecorated local returnify_pr::Expr =
    matchPrimitiveReal(decE, outty, ret_pr_from_f.returnify,
                       f.monadRewritten);
  --just use monadRewritten
  nondecorated local just_rewrite::Expr =
    matchPrimitiveReal(decE, outty, pr.monadRewritten,
                       f.monadRewritten);
  --t is monadic and nothing else is, so return over whole thing
  nondecorated local return_whole_thing::Expr =
    buildApplication(monadReturn(),
                                 [matchPrimitiveReal(decE, outty, pr.monadRewritten,
                                                     f.monadRewritten)]);
  --pick the right rewriting
  nondecorated local mRw::Expr =
    if eIsMonadic
    then if prRetIsMonadic
         then if fIsMonadic
              then justBind_e
              else bind_e_return_f
         else if fIsMonadic
              then bind_e_returnify_pr
              else bind_e_returnify_pr_return_f
    else if prRetIsMonadic
         then if fIsMonadic
              then just_rewrite
              else return_f
         else if fIsMonadic
              then returnify_pr
              else if tIsMonadic
                   then return_whole_thing
                   else if tIsMonadic --and nothing else is, so we need to add return
                        then Silver_Expr {$Expr {monadReturn()}($Expr {just_rewrite})}
                        else just_rewrite;
  top.monadRewritten = mRw;
}

aspect production onePattern
top::PrimPatterns ::= p::PrimPattern
{
  top.merrors := p.merrors;

  propagate mDownSubst, mUpSubst;

  top.mtyperep = p.mtyperep;
  top.patternType = p.patternType;

  top.monadicNames = p.monadicNames;

  p.returnFun = top.returnFun;
  top.returnify = onePattern(p.returnify);
  top.monadRewritten = onePattern(p.monadRewritten);
}
aspect production consPattern
top::PrimPatterns ::= p::PrimPattern vbar::Vbar_kwd ps::PrimPatterns
{
  top.merrors := p.merrors ++ ps.merrors;

  top.monadicNames = p.monadicNames ++ ps.monadicNames;

  p.mDownSubst = top.mDownSubst;
  ps.mDownSubst = p.mUpSubst;
  top.mUpSubst = ps.mUpSubst;

  top.mtyperep = if isMonad(p.mtyperep, top.env) && monadsMatch(p.mtyperep, top.expectedMonad, top.mDownSubst).fst
                 then if isMonad(ps.mtyperep, top.env) && monadsMatch(ps.mtyperep, top.expectedMonad, top.mDownSubst).fst
                      then ps.mtyperep
                      else p.mtyperep
                 else ps.mtyperep;
  top.patternType = p.patternType; --go with the "earlier" type--mismatch handled by merrors

  p.returnFun = top.returnFun;
  ps.returnFun = top.returnFun;
  top.returnify = consPattern(p.returnify, terminal(Vbar_kwd, "|"), ps.returnify);

  --when both are monads or both aren't, so we don't need to change anything
  nondecorated local basicRewritten::PrimPatterns =
    consPattern(p.monadRewritten, terminal(Vbar_kwd, "|"), ps.monadRewritten);
  --when the current clause is a monad but the rest aren't, wrap all of them in Return()
  local psReturnify::PrimPatterns = ps.monadRewritten;
  psReturnify.returnFun = monadReturn();
  psReturnify.env = top.env;
  psReturnify.config = top.config;
  psReturnify.grammarName = top.grammarName;
  nondecorated local returnifyRewritten::PrimPatterns =
    consPattern(p.monadRewritten, terminal(Vbar_kwd, "|"),
                psReturnify.returnify);
  --when the current clause is not a monad but the rest are, wrap the current one in Return()
  local pReturnify::PrimPattern = p.monadRewritten;
  pReturnify.returnFun = monadReturn();
  pReturnify.grammarName = top.grammarName;
  pReturnify.config = top.config;
  pReturnify.env = top.env;
  nondecorated local returnRewritten::PrimPatterns =
    consPattern(pReturnify.returnify, terminal(Vbar_kwd, "|"),
                ps.monadRewritten);
  top.monadRewritten = if isMonad(p.mtyperep, top.env) && monadsMatch(p.mtyperep, top.expectedMonad, top.mDownSubst).fst
                       then if isMonad(ps.mtyperep, top.env) && monadsMatch(ps.mtyperep, top.expectedMonad, top.mDownSubst).fst
                            then basicRewritten     --both monads
                            else returnifyRewritten --current monad, rest not
                       else if isMonad(ps.mtyperep, top.env) && monadsMatch(ps.mtyperep, top.expectedMonad, top.mDownSubst).fst
                            then returnRewritten    --rest monad, current not
                            else basicRewritten;    --neither monads
}

aspect production prodPattern
top::PrimPattern ::= qn::QName '(' ns::VarBinders ')' arr::Arrow_kwd e::Expr
{
  local ne::Expr = e;
  ne.env = e.env;
  ne.frame = top.frame;
  ne.compiledGrammars = top.compiledGrammars;
  ne.grammarName = top.grammarName;
  ne.config = top.config;
  ne.flowEnv = top.flowEnv;
  ne.decSiteVertexInfo = nothing();
  ne.alwaysDecorated = false;
  ne.isRoot = false;

  ne.finalSubst = top.finalSubst;
  ne.downSubst = top.mDownSubst;
  ne.mDownSubst = top.mDownSubst;
  top.mUpSubst = ne.mUpSubst;

  ne.expectedMonad = top.expectedMonad;

  ne.monadicallyUsed = false;
  top.monadicNames = ne.monadicNames;
}
aspect production prodPatternNormal
top::PrimPattern ::= qn::Decorated QName  ns::VarBinders  e::Expr
{
  top.merrors := e.merrors;
  propagate mDownSubst, mUpSubst;

  e.monadicallyUsed = false;
  top.monadicNames = e.monadicNames;

  top.mtyperep = e.mtyperep;
  top.patternType = prod_type.outputType;

  top.returnify = prodPatternNormal(qn, ns,
                                    Silver_Expr { $Expr{top.returnFun}($Expr{e}) });
  top.monadRewritten = prodPatternNormal(qn, ns, e.monadRewritten);
}

aspect production prodPatternGadt
top::PrimPattern ::= qn::Decorated QName  ns::VarBinders  e::Expr
{
  top.merrors := e.merrors;
  propagate mDownSubst, mUpSubst;

  e.monadicallyUsed = false;
  top.monadicNames = e.monadicNames;

  top.mtyperep = e.mtyperep;
  top.patternType = prod_type.outputType;

  top.returnify = prodPatternGadt(qn, ns,
                                  Silver_Expr { $Expr{top.returnFun}($Expr{e}) });
  top.monadRewritten = prodPatternGadt(qn, ns, e.monadRewritten);
}


aspect production integerPattern
top::PrimPattern ::= i::Int_t arr::Arrow_kwd e::Expr
{
  top.merrors := e.merrors;
  e.mDownSubst = top.mDownSubst;
  top.mUpSubst = e.mUpSubst;

  e.monadicallyUsed = false;
  top.monadicNames = e.monadicNames;

  top.mtyperep = e.mtyperep;
  top.patternType = intType();

  top.returnify = integerPattern(i, terminal(Arrow_kwd, "->"),
                                 Silver_Expr { $Expr{top.returnFun}($Expr{e}) });
  top.monadRewritten = integerPattern(i, terminal(Arrow_kwd, "->"), e.monadRewritten);
}
aspect production floatPattern
top::PrimPattern ::= f::Float_t arr::Arrow_kwd e::Expr
{
  top.merrors := e.merrors;
  propagate mDownSubst, mUpSubst;

  e.monadicallyUsed = false;
  top.monadicNames = e.monadicNames;
  
  top.mtyperep = e.mtyperep;
  top.patternType = floatType();

  top.returnify = floatPattern(f, terminal(Arrow_kwd, "->"),
                               Silver_Expr { $Expr{top.returnFun}($Expr{e}) });
  top.monadRewritten = floatPattern(f, terminal(Arrow_kwd, "->"), e.monadRewritten);
}
aspect production stringPattern
top::PrimPattern ::= i::String_t arr::Arrow_kwd e::Expr
{
  top.merrors := e.merrors;
  propagate mDownSubst, mUpSubst;

  e.monadicallyUsed = false;
  top.monadicNames = e.monadicNames;
  
  top.mtyperep = e.mtyperep;
  top.patternType = stringType();

  top.returnify = stringPattern(i, terminal(Arrow_kwd, "->"),
                                Silver_Expr { $Expr{top.returnFun}($Expr{e}) });
  top.monadRewritten = stringPattern(i, terminal(Arrow_kwd, "->"), e.monadRewritten);
}
aspect production booleanPattern
top::PrimPattern ::= i::String arr::Arrow_kwd e::Expr
{
  top.merrors := e.merrors;
  propagate mDownSubst, mUpSubst;

  e.monadicallyUsed = false;
  top.monadicNames = e.monadicNames;

  top.mtyperep = e.mtyperep;
  top.patternType = stringType();

  top.returnify = booleanPattern(i, terminal(Arrow_kwd, "->"),
                                 Silver_Expr { $Expr{top.returnFun}($Expr{e}) });
  top.monadRewritten = booleanPattern(i, terminal(Arrow_kwd, "->"), e.monadRewritten);
}
aspect production nilPattern
top::PrimPattern ::= e::Expr
{
  top.merrors := e.merrors;
  propagate mDownSubst, mUpSubst;

  e.monadicallyUsed = false;
  top.monadicNames = e.monadicNames;

  top.mtyperep = e.mtyperep;
  nondecorated local attribute thisListType::Type = listType(freshType());
  top.patternType = thisListType;

  top.returnify = nilPattern(Silver_Expr { $Expr{top.returnFun}($Expr{e}) });
  top.monadRewritten = nilPattern(e.monadRewritten);
}
aspect production conslstPattern
top::PrimPattern ::= h::Name t::Name e::Expr
{
  top.merrors := e.merrors;
  propagate mDownSubst, mUpSubst;

  e.monadicallyUsed = false;
  top.monadicNames = e.monadicNames;

  top.mtyperep = e.mtyperep;
  nondecorated local elemType::Type = freshType();
  top.patternType = listType(elemType);

  top.returnify = conslstPattern(h, t, Silver_Expr { $Expr{top.returnFun}($Expr{e}) });
  top.monadRewritten = conslstPattern(h, t, e.monadRewritten);
}


