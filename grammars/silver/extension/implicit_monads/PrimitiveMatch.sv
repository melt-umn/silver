grammar silver:extension:implicit_monads;

attribute mtyperep, merrors, patternType, monadRewritten<PrimPatterns>,
          returnFun, returnify<PrimPatterns> occurs on PrimPatterns;
attribute mtyperep, merrors, patternType, monadRewritten<PrimPattern>,
          returnFun, returnify<PrimPattern> occurs on PrimPattern;

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
{-  top.mtyperep = if isMonad(e.mtyperep) && !isMonad(pr.patternType)
                 then if isMonad(pr.mtyperep)
                      then pr.mtyperep
                      else if isMonad(f.mtyperep)
                           then f.mtyperep
                           else monadOfType(e.mtyperep, pr.mtyperep)
                 else if isMonad(pr.mtyperep)
                      then pr.mtyperep
                      else f.mtyperep;-}
  top.mtyperep = if isMonad(e.mtyperep) && !isMonad(pr.patternType)
                 then if isMonad(f.mtyperep)
                      then f.mtyperep
                      else if isMonad(pr.mtyperep)
                           then pr.mtyperep
                           else if isMonad(t.typerep)
                                then monadOfType(t.typerep, pr.mtyperep)
                                else monadOfType(e.mtyperep, pr.mtyperep)
                 else if isMonad(pr.mtyperep)
                      then pr.mtyperep
                      else if isMonad(t.typerep)
                           then monadOfType(t.typerep, pr.mtyperep)
                           else f.mtyperep;

  top.merrors := e.merrors ++ pr.merrors ++ f.merrors;

  --check the type coming up with the type that's supposed to be
  --   coming out, which should, for case expressions (since nobody
  --   uses just this), be just a variable(?)
  local attribute errCheck1::TypeCheck; errCheck1.finalSubst = top.upSubst;
  errCheck1 = if isMonad(pr.mtyperep)
              then if isMonad(f.mtyperep)
                   then check(pr.mtyperep, f.mtyperep)
                   else check(monadInnerType(pr.mtyperep), f.mtyperep)
              else if isMonad(f.mtyperep)
                   then check(pr.mtyperep, monadInnerType(f.mtyperep))
                   else check(pr.mtyperep, f.mtyperep);

  errCheck1.downSubst = f.upSubst;

  local freshname::String = "__sv_bindingInAMatchExpression_" ++ toString(genInt());
  local eBind::Expr = monadBind(e.mtyperep, top.location);
  local eInnerType::TypeExpr = typerepTypeExpr(monadInnerType(e.mtyperep), location=bogusLoc());
  local binde_lambdaparams::ProductionRHS =
        productionRHSCons(productionRHSElem(name(freshname, bogusLoc()), '::',
                                            eInnerType, location=bogusLoc()),
                          productionRHSNil(location=bogusLoc()), location=bogusLoc());
  local outty::TypeExpr = typerepTypeExpr(top.mtyperep, location=bogusLoc());
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
                                                                             freshname),
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
                                                                             freshname),
                                                                       location=bogusLoc()),
                                                              outty, pr.monadRewritten,
                                                              Silver_Expr {
                                                                $Expr{monadReturn(e.mtyperep, bogusLoc())}
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
  prReturnify.returnFun = monadReturn(e.mtyperep, bogusLoc());
  prReturnify.grammarName = top.grammarName;
  prReturnify.env = top.env;
  prReturnify.config = top.config;
  local bind_e_returnify_pr::Expr =
    applicationExpr(eBind,
                    '(',
                    snocAppExprs(oneAppExprs(presentAppExpr(e.monadRewritten, location=bogusLoc()),
                                             location=bogusLoc()),
                                 ',',
                                 presentAppExpr(
                                   lambdap(binde_lambdaparams,
                                           matchPrimitiveReal(baseExpr(qName(bogusLoc(),
                                                                             freshname),
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
                                                                             freshname),
                                                                       location=bogusLoc()),
                                                              outty, prReturnify.returnify,
                                                              Silver_Expr {
                                                                $Expr{monadReturn(e.mtyperep, bogusLoc())}
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
                         $Expr{monadReturn(pr.mtyperep, bogusLoc())}($Expr{f.monadRewritten})
                       },
                       location=top.location);
  --returnify pr from f's type
  local ret_pr_from_f::PrimPatterns = pr.monadRewritten;
  ret_pr_from_f.returnFun = monadReturn(f.mtyperep, bogusLoc());
  ret_pr_from_f.grammarName = top.grammarName;
  ret_pr_from_f.env = top.env;
  ret_pr_from_f.config = top.config;
  local returnify_pr::Expr = matchPrimitiveReal(e.monadRewritten, outty, ret_pr_from_f.returnify,
                                                f.monadRewritten, location=top.location);
  --just use monadRewritten
  local just_rewrite::Expr = matchPrimitiveReal(e.monadRewritten, outty, pr.monadRewritten,
                                                f.monadRewritten, location=top.location);
  --pick the right rewriting
  --top.monadRewritten
  local foo::Expr    = if isMonad(e.mtyperep) && !isMonad(pr.patternType)
                       then if isMonad(pr.mtyperep)
                            then if isMonad(f.mtyperep)
                                 then justBind_e
                                 else bind_e_return_f
                            else if isMonad(f.mtyperep)
                                 then bind_e_returnify_pr
                                 else bind_e_returnify_pr_return_f
                       else if isMonad(pr.mtyperep)
                            then if isMonad(f.mtyperep)
                                 then just_rewrite
                                 else return_f
                            else if isMonad(f.mtyperep)
                                 then returnify_pr
                                 else just_rewrite;
  top.monadRewritten = foo; {-unsafeTrace(foo,
                     print("Type of rewrite:  " ++
                      (if isMonad(e.mtyperep) && !isMonad(pr.patternType)
                       then if isMonad(pr.mtyperep)
                            then if isMonad(f.mtyperep)
                                 then "just bind e"
                                 else "bind e return f"
                            else if isMonad(f.mtyperep)
                                 then "bind_e_returnify_pr"
                                 else "bind_e_returnify_pr_return_f"
                       else if isMonad(pr.mtyperep)
                            then if isMonad(f.mtyperep)
                                 then "just_rewrite"
                                 else "return_f"
                            else if isMonad(f.mtyperep)
                                 then "returnify_pr"
                                 else "just_rewrite") ++ "; " ++ e.unparse ++ "; e type: " ++ prettyType(e.mtyperep) ++ "; patt type: " ++ prettyType(pr.patternType) ++ "; return type: " ++ prettyType(pr.mtyperep) ++ "; fail type: " ++ prettyType(f.mtyperep) ++ "; " ++ top.location.unparse ++ "\n" ++ "Environment Search Result:  " ++
                 --(if null(getValueDcl("bindMaybe", top.env)) then "nothing" else "found something") ++
      let x::DclInfo = head(getValueDcl("bindMaybe", top.env)) in x.sourceGrammar ++ ", " ++ x.fullName end ++
     "; Environment type search result:  " ++ (if null(getTypeDcl("bindMaybe", top.env)) then "nothing" else "found something") ++ "\n\n", unsafeIO()));
-}
}

aspect production onePattern
top::PrimPatterns ::= p::PrimPattern
{
  top.merrors := p.merrors;

  top.mtyperep = p.mtyperep;
  top.patternType = p.patternType;

  p.returnFun = top.returnFun;
  top.returnify = onePattern(p.returnify, location=top.location);
  top.monadRewritten = onePattern(p.monadRewritten, location=top.location);
}
aspect production consPattern
top::PrimPatterns ::= p::PrimPattern vbar::Vbar_kwd ps::PrimPatterns
{
  top.merrors := p.merrors ++ ps.merrors;

  errCheck1.downSubst = ps.upSubst;
  errCheck1.finalSubst = errCheck1.upSubst;
  local errCheck1::TypeCheck = if isMonad(p.mtyperep)
                               then if isMonad(ps.mtyperep)
                                    then check(p.mtyperep, ps.mtyperep)
                                    else check(monadInnerType(p.mtyperep), ps.mtyperep)
                               else if isMonad(ps.mtyperep)
                                    then check(p.mtyperep, monadInnerType(ps.mtyperep))
                                    else check(p.mtyperep, ps.mtyperep);
  top.merrors <-
    if errCheck1.typeerror
    then [err(top.location,
          --TODO this message should really be specialized based on what is and isn't monadic
              "pattern expression should have type " ++ errCheck1.leftpp ++
              " or a monad of this; instead it has type " ++ errCheck1.rightpp)]
    else [];

  top.mtyperep = if isMonad(p.mtyperep)
                 then if isMonad(ps.mtyperep)
                      then ps.mtyperep
                      else p.mtyperep
                 else ps.mtyperep;
  top.patternType = p.patternType; --go with the "earlier" type--mismatch handled by merrors

  p.returnFun = top.returnFun;
  ps.returnFun = top.returnFun;
  top.returnify = consPattern(p.returnify, terminal(Vbar_kwd, "|"), ps.returnify, location=top.location);

  --when both are monads or both aren't, so we don't need to change anything
  local basicRewritten::PrimPatterns = consPattern(p.monadRewritten, terminal(Vbar_kwd, "|"), ps.monadRewritten,
                                                   location=top.location);
  --when the current clause is a monad but the rest aren't, wrap all of them in Return()
  local psReturnify::PrimPatterns = ps.monadRewritten;
  psReturnify.returnFun = monadReturn(p.mtyperep, bogusLoc());
  psReturnify.env = top.env;
  psReturnify.config = top.config;
  psReturnify.grammarName = top.grammarName;
  local returnifyRewritten::PrimPatterns = consPattern(p.monadRewritten, terminal(Vbar_kwd, "|"),
                                                       psReturnify.returnify,
                                                       location=top.location);
  --when the current clause is not a monad but the rest are, wrap the current one in Return()
  local pReturnify::PrimPattern = p.monadRewritten;
  pReturnify.returnFun = monadReturn(ps.mtyperep, bogusLoc());
  pReturnify.grammarName = top.grammarName;
  pReturnify.config = top.config;
  pReturnify.env = top.env;
  local returnRewritten::PrimPatterns = consPattern(pReturnify.returnify, terminal(Vbar_kwd, "|"),
                                                    ps.monadRewritten,
                                                    location=top.location);
  top.monadRewritten = if isMonad(p.mtyperep)
                       then if isMonad(ps.mtyperep)
                            then basicRewritten     --both monads
                            else returnifyRewritten --current monad, rest not
                       else if isMonad(ps.mtyperep)
                            then returnRewritten    --rest monad, current not
                            else basicRewritten;    --neither monads
}

aspect production prodPattern
top::PrimPattern ::= qn::QName '(' ns::VarBinders ')' arr::Arrow_kwd e::Expr
{
}
aspect production prodPatternNormal
top::PrimPattern ::= qn::Decorated QName  ns::VarBinders  e::Expr
{
  top.merrors := e.merrors;

  top.mtyperep = e.mtyperep;
  -- Turns the existential variables existential
  local prod_type :: Type =
    skolemizeProductionType(qn.lookupValue.typerep);
  top.patternType = prod_type.outputType;

  top.returnify = prodPatternNormal(qn, ns,
                                    Silver_Expr { $Expr{top.returnFun}($Expr{e}) },
                                    location=top.location);
  top.monadRewritten = prodPatternNormal(qn, ns, e.monadRewritten, location=top.location);
}

aspect production prodPatternGadt
top::PrimPattern ::= qn::Decorated QName  ns::VarBinders  e::Expr
{
  top.merrors := e.merrors;

  top.mtyperep = e.mtyperep;
  local prod_type :: Type =
    fullySkolemizeProductionType(qn.lookupValue.typerep); -- that says FULLY. See the comments on that function.
  top.patternType = prod_type.outputType;

  top.returnify = prodPatternGadt(qn, ns,
                                  Silver_Expr { $Expr{top.returnFun}($Expr{e}) },
                                  location=top.location);
  top.monadRewritten = prodPatternGadt(qn, ns, e.monadRewritten, location=top.location);
}


aspect production integerPattern
top::PrimPattern ::= i::Int_t arr::Arrow_kwd e::Expr
{
  top.merrors := e.merrors;

  top.mtyperep = e.mtyperep;
  top.patternType = intType();

  top.returnify = integerPattern(i, terminal(Arrow_kwd, "->"),
                                 Silver_Expr { $Expr{top.returnFun}($Expr{e}) },
                                 location=top.location);
  top.monadRewritten = integerPattern(i, terminal(Arrow_kwd, "->"), e.monadRewritten, location=top.location);
}
aspect production floatPattern
top::PrimPattern ::= f::Float_t arr::Arrow_kwd e::Expr
{
  top.merrors := e.merrors;
  
  top.mtyperep = e.mtyperep;
  top.patternType = floatType();

  top.returnify = floatPattern(f, terminal(Arrow_kwd, "->"),
                               Silver_Expr { $Expr{top.returnFun}($Expr{e}) },
                               location=top.location);
  top.monadRewritten = floatPattern(f, terminal(Arrow_kwd, "->"), e.monadRewritten, location=top.location);
}
aspect production stringPattern
top::PrimPattern ::= i::String_t arr::Arrow_kwd e::Expr
{
  top.merrors := e.merrors;
  
  top.mtyperep = e.mtyperep;
  top.patternType = stringType();

  top.returnify = stringPattern(i, terminal(Arrow_kwd, "->"),
                                Silver_Expr { $Expr{top.returnFun}($Expr{e}) },
                                location=top.location);
  top.monadRewritten = stringPattern(i, terminal(Arrow_kwd, "->"), e.monadRewritten, location=top.location);
}
aspect production booleanPattern
top::PrimPattern ::= i::String arr::Arrow_kwd e::Expr
{
  top.merrors := e.merrors;

  top.mtyperep = e.mtyperep;
  top.patternType = stringType();

  top.returnify = booleanPattern(i, terminal(Arrow_kwd, "->"),
                                 Silver_Expr { $Expr{top.returnFun}($Expr{e}) },
                                 location=top.location);
  top.monadRewritten = booleanPattern(i, terminal(Arrow_kwd, "->"), e.monadRewritten, location=top.location);
}
aspect production nilPattern
top::PrimPattern ::= e::Expr
{
  top.merrors := e.merrors;

  top.mtyperep = e.mtyperep;
  local attribute thisListType::Type = listType(freshType());
  top.patternType = thisListType;

  top.returnify = nilPattern(Silver_Expr { $Expr{top.returnFun}($Expr{e}) },
                             location=top.location);
  top.monadRewritten = nilPattern(e.monadRewritten, location=top.location);
}
aspect production conslstPattern
top::PrimPattern ::= h::Name t::Name e::Expr
{
  top.merrors := e.merrors;

  top.mtyperep = e.mtyperep;
  local elemType :: Type = freshType();
  top.patternType = listType(elemType);

  top.returnify = conslstPattern(h, t, Silver_Expr { $Expr{top.returnFun}($Expr{e}) },
                                 location=top.location);
  top.monadRewritten = conslstPattern(h, t, e.monadRewritten, location=top.location);
}


