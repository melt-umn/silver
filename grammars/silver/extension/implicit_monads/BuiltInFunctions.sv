grammar silver:extension:implicit_monads;


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

aspect production reifyFunctionLiteral
top::Expr ::= 'reify'
{
  top.merrors := [];
  top.mUpSubst = top.mDownSubst;
  top.mtyperep =
    functionType(nonterminalType("core:Either", [stringType(), varType(freshTyVar())]), [nonterminalType("core:reflect:AST", [])], []);
  top.monadicNames = [];
  top.monadRewritten = reifyFunctionLiteral('reify', location=top.location);
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

{-
  TODO I don't know what these are supposed to be, really, but I suppose they should be bound in if they're monads?
-}
aspect production terminalConstructor
top::Expr ::= 'terminal' '(' t::TypeExpr ',' es::Expr ',' el::Expr ')'
{
  top.merrors := es.merrors ++ el.merrors;
  top.mUpSubst = top.mDownSubst;
  top.mtyperep = t.typerep;
  top.monadicNames = [];
  top.monadRewritten = error("terminalConstructor monadRewritten not defined");
}


--------------------------------------------------------------------------------
-- Deprecated variants of built-in functions


aspect production toIntFunction
top::Expr ::= 'toInt' '(' e::Expr ')'
{
--  forwards to toIntegerFunction('toInteger', '(', e, ')', location=top.location);
}

aspect production terminalConstructorTemporaryDispatcher
top::Expr ::= 'terminal' '(' t::TypeExpr ',' es::Expr ',' el::Expr ')'
{
--  forwards to
--    if el.typerep.isTerminal
--    then terminalFunctionInherited($1, $2, t, $4, es, $6, el, $8, location=top.location)
--    else terminalConstructor($1, $2, t, $4, es, $6, el, $8, location=top.location);
}

aspect production terminalFunction
top::Expr ::= 'terminal' '(' t::TypeExpr ',' e::Expr ')'
{
--  forwards to terminalConstructor($1, $2, t, $4, e, ',', bogus, $6, location=top.location);
}

aspect production terminalFunctionLineCol
top::Expr ::= 'terminal' '(' t::TypeExpr ',' e1::Expr ',' e2::Expr ',' e3::Expr ')'
{
--  forwards to terminalConstructor($1, $2, t, $4, e1, $6,
--    mkStrFunctionInvocation($10.location, "core:loc", [
--      stringConst(terminal(String_t, "\"??\""), location=$10.location),
--      e2, e3, bogus, bogus, bogus, bogus
--    ]), $10, location=top.location);
}

