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
  top.merrors := la.merrors ++ e.merrors;
  -- TODO do some error checking for the monads all matching

  la.mDownSubst = top.mDownSubst;
  e.mDownSubst = la.mUpSubst;
  top.mUpSubst = e.mUpSubst;

  top.mtyperep = if isMonad(e.mtyperep) || null(la.bindInList)
                 then e.mtyperep
                 else case la.monadUsed of
                      | just(ty) -> monadOfType(ty, e.mtyperep)
                      end;

  --top.monadRewritten = letp(la.monadRewritten, e.monadRewritten, location=top.location);

  local mreturn::Expr = case la.monadUsed of
                        | just(ty) -> monadReturn(ty, top.location)
                        end;
  local mbind::Expr = case la.monadUsed of
                      | just(ty) -> monadBind(ty, top.location)
                      end;

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
  local rewrittenLets::Expr =
     letp(la.fixedAssigns,
          if isMonad(e.mtyperep) || null(la.bindInList)
          then e.monadRewritten
          else Silver_Expr { $Expr{mreturn}($Expr{e.monadRewritten}) },
          location=top.location);
  top.monadRewritten =
         foldr(\x::Pair<Name TypeExpr> y::Expr ->
                 Silver_Expr {
                  $Expr{mbind}
                   ($Expr{baseExpr(qName(top.location, x.fst.name), location=top.location)},
                    $Expr{lambdap(productionRHSCons(productionRHSElem(x.fst, '::', x.snd,
                                                  location=top.location),
                                    productionRHSNil(location=top.location),
                                    location=top.location), y, location=top.location)}) },
                  rewrittenLets, la.bindInList);
}


synthesized attribute fixedAssigns::AssignExpr occurs on AssignExpr;
synthesized attribute bindInList::[Pair<Name TypeExpr>] occurs on AssignExpr;
--if bindInList is not empty, monadUsed must be just(ty) where ty is a monad type
synthesized attribute monadUsed::Maybe<Type> occurs on AssignExpr;

attribute merrors, monadRewritten<AssignExpr>, mDownSubst, mUpSubst occurs on AssignExpr;

aspect production appendAssignExpr
top::AssignExpr ::= a1::AssignExpr a2::AssignExpr
{
  top.merrors := a1.merrors ++ a2.merrors;
  a1.mDownSubst = top.mDownSubst;
  a2.mDownSubst = a1.mUpSubst;
  top.mUpSubst = monadCheck.snd;

  top.bindInList = a1.bindInList ++ a2.bindInList;

  local monadCheck::Pair<Boolean Substitution> =
     case a1.monadUsed, a2.monadUsed of
     | just(ty1), just(ty2) -> monadsMatch(ty1, ty2, a2.mUpSubst)
     | _, _ -> pair(true, a2.mUpSubst)
     end;
  top.monadUsed = case a1.monadUsed, a2.monadUsed of
                  | _, just(ty) -> just(ty)
                  | t, _ -> t
                  end;

  top.fixedAssigns = appendAssignExpr(a1.fixedAssigns, a2.fixedAssigns, location=top.location);
  top.monadRewritten = appendAssignExpr(a1.monadRewritten, a2.monadRewritten, location=top.location);
}

aspect production assignExpr
top::AssignExpr ::= id::Name '::' t::TypeExpr '=' e::Expr
{
  top.merrors := e.merrors;
  local errCheck::TypeCheck = if isMonad(e.mtyperep)
                              then if isMonad(t.typerep)
                                   then check(t.typerep, e.mtyperep)
                                   else check(t.typerep, monadInnerType(e.mtyperep))
                              else check(t.typerep, e.mtyperep);
  e.mDownSubst = top.mDownSubst;
  errCheck.downSubst = e.mUpSubst;
  top.mUpSubst = errCheck.upSubst;

  top.monadUsed = if isMonad(e.mtyperep) && !isMonad(t.typerep)
                  then just(e.mtyperep)
                  else nothing();

  top.bindInList = if isMonad(e.mtyperep) && !isMonad(t.typerep)
                   then [pair(id, t)]
                   else [];

  top.fixedAssigns = if isMonad(e.mtyperep) && !isMonad(t.typerep)
                     --use t.typerep to get typechecking when we create the ultimate monadRewritten
                     then assignExpr(id, '::', typerepTypeExpr(monadOfType(e.mtyperep, t.typerep),
                                                               location=top.location),
                                     '=', e.monadRewritten, location=top.location)
                     else assignExpr(id, '::', t, '=', e.monadRewritten, location=top.location);
  top.monadRewritten = assignExpr(id, '::', t, '=', e.monadRewritten, location=top.location);
}




aspect production lexicalLocalReference
top::Expr ::= q::Decorated QName  fi::ExprVertexInfo  fd::[FlowVertex]
{
  top.merrors := [];
  top.mUpSubst = top.mDownSubst;
  top.mtyperep = q.lookupValue.typerep;
  top.monadRewritten = baseExpr(new(q), location=top.location);
}
