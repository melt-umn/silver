grammar silver:compiler:extension:implicit_monads;


{-
  TODO:  If the typerep is a list, which is a monad, we should probably just take the length of that
-}
aspect production lengthFunction
top::Expr ::= 'length' '(' e::Expr ')'
{
  local isList::Boolean = case e.mtyperep of
                          | listType(_) -> true
                          | _ -> false
                          end;
  top.merrors := e.merrors;
  e.mDownSubst = top.mDownSubst;
  top.mUpSubst = e.mUpSubst;
  top.mtyperep = if !isList && isMonad(e.mtyperep)
                 then monadOfType(e.mtyperep, intType())
                 else intType();

  e.monadicallyUsed = !isList && isMonad(e.mtyperep);
  top.monadicNames = e.monadicNames;

  top.monadRewritten = if !isList && isMonad(e.mtyperep)
                       then Silver_Expr {
                              $Expr {monadBind(e.mtyperep, top.location)}
                              ($Expr {e.monadRewritten},
                              \x::$TypeExpr {typerepTypeExpr(monadInnerType(e.mtyperep), location=top.location)} ->
                                  $Expr {monadReturn(e.mtyperep, top.location)} (length(x)))
                            }
                       else lengthFunction('length', '(', e.monadRewritten, ')', location=top.location);
}

aspect production errorLength
top::Expr ::= e::Decorated Expr
{
  local ne::Expr = new(e);
  ne.mDownSubst = top.mDownSubst;
  ne.env = top.env;
  ne.flowEnv = top.flowEnv;
  ne.config = top.config;
  ne.compiledGrammars = top.compiledGrammars;
  ne.grammarName = top.grammarName;
  ne.frame = top.frame;
  ne.finalSubst = top.finalSubst;
  ne.downSubst = top.downSubst;
  ne.expectedMonad = top.expectedMonad;
  top.merrors := ne.merrors;
  top.mUpSubst = ne.mUpSubst;
  top.mtyperep = intType();
  top.monadicNames = [];
  top.monadRewritten = lengthFunction('length', '(', new(e), ')', location=top.location);
}

aspect production stringLength
top::Expr ::= e::Decorated Expr
{
  local ne::Expr = new(e);
  ne.mDownSubst = top.mDownSubst;
  ne.env = top.env;
  ne.flowEnv = top.flowEnv;
  ne.config = top.config;
  ne.compiledGrammars = top.compiledGrammars;
  ne.grammarName = top.grammarName;
  ne.frame = top.frame;
  ne.finalSubst = top.finalSubst;
  ne.downSubst = top.downSubst;
  ne.expectedMonad = top.expectedMonad;
  top.merrors := ne.merrors;
  top.mUpSubst = ne.mUpSubst;
  top.mtyperep = if isMonad(ne.mtyperep)
                 then monadOfType(ne.mtyperep, intType())
                 else intType();
  ne.monadicallyUsed = isMonad(ne.mtyperep);
  top.monadicNames = ne.monadicNames;
  top.monadRewritten =
     if isMonad(ne.mtyperep)
     then Silver_Expr {
            $Expr {monadBind(ne.mtyperep, top.location)}
             ($Expr {ne.monadRewritten},
               \x::$TypeExpr {typerepTypeExpr(monadInnerType(ne.mtyperep), location=top.location)} ->
                     $Expr {monadReturn(ne.mtyperep, top.location)} (length(x)))
                   }
     else lengthFunction('length', '(', ne.monadRewritten, ')', location=top.location);
}

aspect production toIntegerFunction
top::Expr ::= 'toInteger' '(' e::Expr ')'
{
  top.merrors := e.merrors;
  e.mDownSubst = top.mDownSubst;
  top.mUpSubst = e.mUpSubst;
  top.mtyperep = if isMonad(e.mtyperep)
                 then monadOfType(e.mtyperep, intType())
                 else intType();
  e.monadicallyUsed = isMonad(e.mtyperep);
  top.monadicNames = e.monadicNames;
  top.monadRewritten = if isMonad(e.mtyperep)
                       then Silver_Expr {
                              $Expr {monadBind(e.mtyperep, top.location)}
                              ($Expr {e.monadRewritten},
                              \x::$TypeExpr {typerepTypeExpr(monadInnerType(e.mtyperep), location=top.location)} ->
                                  $Expr {monadReturn(e.mtyperep, top.location)} (toInteger(x)))
                            }
                       else toIntegerFunction('toInteger', '(', e.monadRewritten, ')', location=top.location);
}

aspect production toBooleanFunction
top::Expr ::= 'toBoolean' '(' e::Expr ')'
{
  top.merrors := e.merrors;
  e.mDownSubst = top.mDownSubst;
  top.mUpSubst = e.mUpSubst;
  top.mtyperep = if isMonad(e.mtyperep)
                 then monadOfType(e.mtyperep, boolType())
                 else boolType();
  e.monadicallyUsed = isMonad(e.mtyperep);
  top.monadicNames = e.monadicNames;
  top.monadRewritten = if isMonad(e.mtyperep)
                       then Silver_Expr {
                              $Expr {monadBind(e.mtyperep, top.location)}
                              ($Expr {e.monadRewritten},
                              \x::$TypeExpr {typerepTypeExpr(monadInnerType(e.mtyperep), location=top.location)} ->
                                  $Expr {monadReturn(e.mtyperep, top.location)} (toBoolean(x)))
                            }
                       else toBooleanFunction('toBoolean', '(', e.monadRewritten, ')', location=top.location);
}

aspect production toFloatFunction
top::Expr ::= 'toFloat' '(' e::Expr ')'
{
  top.merrors := e.merrors;
  e.mDownSubst = top.mDownSubst;
  top.mUpSubst = e.mUpSubst;
  top.mtyperep = if isMonad(e.mtyperep)
                 then monadOfType(e.mtyperep, floatType())
                 else floatType();
  e.monadicallyUsed = isMonad(e.mtyperep);
  top.monadicNames = e.monadicNames;
  top.monadRewritten = if isMonad(e.mtyperep)
                       then Silver_Expr {
                              $Expr {monadBind(e.mtyperep, top.location)}
                              ($Expr {e.monadRewritten},
                              \x::$TypeExpr {typerepTypeExpr(monadInnerType(e.mtyperep), location=top.location)} ->
                                  $Expr {monadReturn(e.mtyperep, top.location)} (toFloat(x)))
                            }
                       else toFloatFunction('toFloat', '(', e.monadRewritten, ')', location=top.location);
}

aspect production toStringFunction
top::Expr ::= 'toString' '(' e::Expr ')'
{
  top.merrors := e.merrors;
  e.mDownSubst = top.mDownSubst;
  top.mUpSubst = e.mUpSubst;
  top.mtyperep = if isMonad(e.mtyperep)
                 then monadOfType(e.mtyperep, stringType())
                 else stringType();
  e.monadicallyUsed = isMonad(e.mtyperep);
  top.monadicNames = e.monadicNames;
  top.monadRewritten = if isMonad(e.mtyperep)
                       then Silver_Expr {
                              $Expr {monadBind(e.mtyperep, top.location)}
                              ($Expr {e.monadRewritten},
                              \x::$TypeExpr {typerepTypeExpr(monadInnerType(e.mtyperep), location=top.location)} ->
                                  $Expr {monadReturn(e.mtyperep, top.location)} (toString(x)))
                            }
                       else toStringFunction('toString', '(', e.monadRewritten, ')', location=top.location);
}

aspect production newFunction
top::Expr ::= 'new' '(' e::Expr ')'
{
  top.merrors := e.merrors;
  e.mDownSubst = top.mDownSubst;
  top.mUpSubst = e.mUpSubst;
  top.mtyperep = e.mtyperep;
  e.monadicallyUsed = false;
  top.monadicNames = e.monadicNames;
  top.monadRewritten = newFunction('new', '(', e.monadRewritten, ')', location=top.location);
}


aspect production terminalConstructor
top::Expr ::= 'terminal' '(' t::TypeExpr ',' es::Expr ',' el::Expr ')'
{
  es.mDownSubst = top.mDownSubst;
  el.mDownSubst = es.mUpSubst;
  top.merrors := es.merrors ++ el.merrors;
  top.mUpSubst = el.mUpSubst;
  top.mtyperep =
     if ( isMonad(es.mtyperep) && monadsMatch(es.mtyperep, top.expectedMonad, top.mUpSubst).fst ) ||
        ( isMonad(el.mtyperep) && monadsMatch(el.mtyperep, top.expectedMonad, top.mUpSubst).fst )
     then monadOfType(top.expectedMonad, t.typerep)
     else t.typerep;
  top.monadicNames = [];

  local bind::Expr = monadBind(top.expectedMonad, top.location);
  local ret::Expr = monadReturn(top.expectedMonad, top.location);
  local esty::TypeExpr =
              typerepTypeExpr(if isMonad(es.mtyperep) then es.mtyperep
                              else monadInnerType(es.mtyperep), location=top.location);
  local elty::TypeExpr =
              typerepTypeExpr(if isMonad(es.mtyperep) then es.mtyperep
                              else monadInnerType(es.mtyperep), location=top.location);
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
      if isMonad(es.mtyperep) && monadsMatch(es.mtyperep, top.expectedMonad, top.mUpSubst).fst
      then if isMonad(el.mtyperep) && monadsMatch(el.mtyperep, top.expectedMonad, top.mUpSubst).fst
           then bindBoth
           else bindes
      else if isMonad(el.mtyperep) && monadsMatch(el.mtyperep, top.expectedMonad, top.mUpSubst).fst
           then bindel
           else top;
}

