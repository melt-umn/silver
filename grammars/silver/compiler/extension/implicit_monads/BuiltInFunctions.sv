grammar silver:compiler:extension:implicit_monads;

aspect production terminalConstructor
top::Expr ::= 'terminal' '(' t::TypeExpr ',' es::Expr ',' el::Expr ')'
{
  es.mDownSubst = top.mDownSubst;
  el.mDownSubst = es.mUpSubst;
  top.merrors := es.merrors ++ el.merrors;
  top.mUpSubst = el.mUpSubst;
  top.mtyperep =
     if ( isMonad(es.mtyperep, top.env) && monadsMatch(es.mtyperep, top.expectedMonad, top.mUpSubst).fst ) ||
        ( isMonad(el.mtyperep, top.env) && monadsMatch(el.mtyperep, top.expectedMonad, top.mUpSubst).fst )
     then monadOfType(top.expectedMonad, t.typerep)
     else t.typerep;
  top.monadicNames = [];

  local bind::Expr = monadBind(top.location);
  local ret::Expr = monadReturn(top.location);
  local esty::TypeExpr =
              typerepTypeExpr(if isMonad(es.mtyperep, top.env) then es.mtyperep
                              else monadInnerType(es.mtyperep, top.location), location=top.location);
  local elty::TypeExpr =
              typerepTypeExpr(if isMonad(es.mtyperep, top.env) then es.mtyperep
                              else monadInnerType(es.mtyperep, top.location), location=top.location);
  local bindes::Expr =
    Silver_Expr {
      $Expr {bind}
      ($Expr {es.monadRewritten},
       (\x::$TypeExpr {esty}
         y::$TypeExpr {elty} ->
            $Expr {ret}
            (terminal($TypeExpr {t}, x, y))) (_, $Expr {el.monadRewritten}))
    };
  local bindel::Expr =
    Silver_Expr {
      $Expr {bind}
      ($Expr {el.monadRewritten},
       (\x::$TypeExpr {esty}
         y::$TypeExpr {elty} ->
            $Expr {ret}
            (terminal($TypeExpr {t}, x, y))) ($Expr {es.monadRewritten}, _))
    };
  local bindBoth::Expr =
    Silver_Expr {
      $Expr {bind}
      ($Expr {es.monadRewritten},
       (\x::$TypeExpr {elty}
         y::$TypeExpr {typerepTypeExpr(es.mtyperep, location=top.location)} ->
          $Expr {bind}
          (y,
           \z::$TypeExpr {elty} ->
            $Expr {ret}
            (terminal($TypeExpr {t}, x, z))) (_, $Expr {el.monadRewritten})))
    };
  top.monadRewritten =
      if isMonad(es.mtyperep, top.env) && monadsMatch(es.mtyperep, top.expectedMonad, top.mUpSubst).fst
      then if isMonad(el.mtyperep, top.env) && monadsMatch(el.mtyperep, top.expectedMonad, top.mUpSubst).fst
           then bindBoth
           else bindes
      else if isMonad(el.mtyperep, top.env) && monadsMatch(el.mtyperep, top.expectedMonad, top.mUpSubst).fst
           then bindel
           else top;
}

