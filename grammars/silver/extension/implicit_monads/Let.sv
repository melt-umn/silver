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

  top.mtyperep = e.mtyperep;

  top.monadRewritten = letp(la.monadRewritten, e.monadRewritten, location=top.location);

  local mreturn::Expr = case la.monadUsed of
                        | just(ty) -> monadReturn(ty, top.location)
                        end;
  local mbind::Expr = case la.monadUsed of
                      | just(ty) -> monadBind(ty, top.location)
                      end;

  --because of the semantics of lets where the names can't be used in other
  --definitions in the same let, it doesn't matter whether we bind over the let
  --or under the let, nor does it matter that we might change the order
  --The order matters because they might be redefining names, so this needs to be rethought
  local keepLets::Expr =
     if null(la.keepLetList)
     then e.monadRewritten
     else letp(foldr(\x::AssignExpr y::AssignExpr -> appendAssignExpr(x, y, location=top.location),
                     head(la.keepLetList), tail(la.keepLetList)),
               e.monadRewritten, location=top.location);
  local makeBinds::Expr =
     if null(la.bindInList)
     then keepLets
     else foldr(\x::Pair<Name Pair<TypeExpr Expr>> y::Expr ->
                 Silver_Expr {
                  $Expr{mbind}
                   ($Expr{x.snd.snd},
                    $Expr{lambdap(productionRHSCons(productionRHSElem(x.fst, '::', x.snd.fst,
                                                  location=top.location),
                                    productionRHSNil(location=top.location),
                                    location=top.location), y, location=top.location)}) },
                  if isMonad(e.mtyperep) then keepLets else Silver_Expr{$Expr{mreturn}($Expr{keepLets})},
                  la.bindInList);
}


synthesized attribute bindInList::[Pair<Name Pair<TypeExpr Expr>>] occurs on AssignExpr;
synthesized attribute keepLetList::[AssignExpr] occurs on AssignExpr;
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
  top.keepLetList = a1.keepLetList ++ a2.keepLetList;

  local monadCheck::Pair<Boolean Substitution> =
     case a1.monadUsed, a2.monadUsed of
     | just(ty1), just(ty2) -> monadsMatch(ty1, ty2, a2.mUpSubst)
     | _, _ -> pair(true, a2.mUpSubst)
     end;
  top.monadUsed = case a1.monadUsed, a2.monadUsed of
                  | _, just(ty) -> just(ty)
                  | t, _ -> t
                  end;

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

  --note that these two lists ought to be disjoint
  top.bindInList = if isMonad(e.mtyperep) && !isMonad(t.typerep)
                   then [pair(id, pair(t, e.monadRewritten))]
                   else [];
  top.keepLetList = if isMonad(e.mtyperep) && !isMonad(t.typerep)
                    then []
                    else [assignExpr(id, '::', t, '=', e.monadRewritten, location=top.location)];

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
