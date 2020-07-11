grammar silver:extension:implicit_monads;

--import silver:definition:flow:ast only ExprVertexInfo, FlowVertex;

--- Concrete Syntax for lets
--------------------------------------------------------------------------------

{-
  To have a let turn into a bind, it needs to have the form
      let x::T = e in b  where e::M<T>
  This is easier than guessing whether it should be bound in if the declared
  type is also M<T>.
-}


aspect production letp
top::Expr ::= la::AssignExpr  e::Expr
{
  top.merrors := la.merrors ++ ne.merrors;

  --We needed to provide our own environment.
  local ne::Expr = e;
  ne.config = top.config;
  ne.grammarName = top.grammarName;
  ne.compiledGrammars = top.compiledGrammars;
  ne.flowEnv = top.flowEnv;
  ne.frame = top.frame;
  ne.downSubst = top.mDownSubst;
  ne.finalSubst = top.mUpSubst;
  ne.env = newScopeEnv(la.mdefs, top.env);
  ne.expectedMonad = top.expectedMonad;

  la.mDownSubst = top.mDownSubst;
  ne.mDownSubst = la.mUpSubst;
  top.mUpSubst = ne.mUpSubst;

  e.expectedMonad = top.expectedMonad;

  top.mtyperep = if null(la.bindInList) || fst(monadsMatch(ne.mtyperep, top.expectedMonad, top.mUpSubst))
                 then ne.mtyperep
                 else monadOfType(top.expectedMonad, ne.mtyperep);

  --I'm not entirely sure if this should be false.  It might be that it should
  --be based on top.monadicallyUsed and whether other things become binds or
  --something.
  ne.monadicallyUsed = false;
  top.monadicNames = la.monadicNames ++ ne.monadicNames;

  local mreturn::Expr = monadReturn(top.expectedMonad, top.location);
  local mbind::Expr = monadBind(top.expectedMonad, top.location);

  {-
    Our rewriting here binds in anything after the let to keep names from
    interfering with each other.  For example, if we have
        let a::Ta = ea; b::Tb = eb; c::Tc = ec in d
    where a and c are monadic, we rewrite to
        let a::M<Ta> = ea; b::Tb = eb; c::M<Tc> = ec in a >>= \a::Ta. c >>= \c::Tc. d
    This ensures our names do not interfere with prevous ones.  For example, ec
    might reference an a that existed before the let, so we need to bind all the
    expressions to names at once in a let; after that, we are free to use the
    names to create binds.
  -}
  top.monadRewritten =
     letp(la.fixedAssigns,
          boundIn,
          location=top.location);
  local inside::Expr = if isMonad(e.mtyperep) || null(la.bindInList)
                       then e.monadRewritten
                       else Silver_Expr { $Expr{mreturn}($Expr{e.monadRewritten}) };
  local boundIn::Expr =
         foldr(\x::Pair<Name TypeExpr> y::Expr ->
                 Silver_Expr {
                  $Expr{mbind}
                   ($Expr{baseExpr(qName(top.location, x.fst.name), location=top.location)},
                    $Expr{lambdap(productionRHSCons(productionRHSElem(x.fst, '::', x.snd,
                                                  location=top.location),
                                    productionRHSNil(location=top.location),
                                    location=top.location), y, location=top.location)}) },
                  inside, la.bindInList);
}


synthesized attribute fixedAssigns::AssignExpr occurs on AssignExpr;
synthesized attribute bindInList::[Pair<Name TypeExpr>] occurs on AssignExpr;
--definitions, but ones that won't cause errors with monad type mismatches in let definitions
synthesized attribute mdefs::[Def] occurs on AssignExpr;

attribute merrors, mDownSubst, mUpSubst, monadicNames, expectedMonad occurs on AssignExpr;

aspect production appendAssignExpr
top::AssignExpr ::= a1::AssignExpr a2::AssignExpr
{
  top.merrors := a1.merrors ++ a2.merrors;

  a1.mDownSubst = top.mDownSubst;
  a2.mDownSubst = a1.mUpSubst;
  top.mUpSubst = a2.mUpSubst;

  a1.expectedMonad = top.expectedMonad;
  a2.expectedMonad = top.expectedMonad;

  top.monadicNames = a1.monadicNames ++ a2.monadicNames;

  top.mdefs = a1.mdefs ++ a2.mdefs;

  top.bindInList = a1.bindInList ++ a2.bindInList;

  top.fixedAssigns = appendAssignExpr(a1.fixedAssigns, a2.fixedAssigns, location=top.location);
}

aspect production assignExpr
top::AssignExpr ::= id::Name '::' t::TypeExpr '=' e::Expr
{
  top.merrors := e.merrors;
  top.merrors <- if isMonad(t.typerep) && fst(monadsMatch(top.expectedMonad, t.typerep, top.mDownSubst))
                 then [err(top.location, "Let bindings may not use a monad type")]
                 else [];
  local errCheck::TypeCheck = if isMonad(e.mtyperep)
                              then if isMonad(t.typerep)
                                   then check(t.typerep, e.mtyperep)
                                   else check(t.typerep, monadInnerType(e.mtyperep))
                              else check(t.typerep, e.mtyperep);
  e.mDownSubst = top.mDownSubst;
  errCheck.downSubst = e.mUpSubst;
  top.mUpSubst = errCheck.upSubst;

  --I'm not entirely sure about this vs. false--it should only matter if we are
  --directly redefining a name (doing x::T=y), which would be weird for a person
  --to write (?), and redfining it monadically.  This would happen if the person
  --was putting in a let after let insertion failed, but then this should be the
  --only place where the name occurs, so it wouldn't affect anything then either.
  e.monadicallyUsed = isMonad(e.mtyperep) && !isMonad(t.typerep);
  top.monadicNames = e.monadicNames;

  e.expectedMonad = top.expectedMonad;

  top.mdefs = [lexicalLocalDef(top.grammarName, id.location, fName,
                               performSubstitution(t.typerep, top.mUpSubst),
                               e.flowVertexInfo, e.flowDeps)];

  top.bindInList = if isMonad(e.mtyperep) && fst(monadsMatch(e.mtyperep, top.expectedMonad, top.mUpSubst))
                   then [pair(id, t)]
                   else [];

  top.fixedAssigns = if isMonad(e.mtyperep) && fst(monadsMatch(e.mtyperep, top.expectedMonad, top.mUpSubst))
                     --use t.typerep to get typechecking when we create the ultimate monadRewritten
                     then assignExpr(id, '::', typerepTypeExpr(monadOfType(e.mtyperep, t.typerep),
                                                               location=top.location),
                                     '=', e.monadRewritten, location=top.location)
                     else assignExpr(id, '::', t, '=', e.monadRewritten, location=top.location);
}




aspect production lexicalLocalReference
top::Expr ::= q::Decorated QName  fi::ExprVertexInfo  fd::[FlowVertex]
{
  top.merrors := [];
  top.mUpSubst = top.mDownSubst;
  top.mtyperep = q.lookupValue.typerep;
  top.monadicNames = if top.monadicallyUsed
                     then [baseExpr(new(q), location=top.location)]
                     else [];
  top.monadRewritten = baseExpr(new(q), location=top.location);
}
