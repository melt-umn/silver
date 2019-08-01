grammar silver:extension:implicit_monads;


{-
  TODO:  If the typerep is a list, which is a monad, we should probably just take the length of that
-}
aspect production lengthFunction
top::Expr ::= 'length' '(' e::Expr ')'
{
  top.merrors := e.merrors;
  top.mtyperep = if isMonad(e.mtyperep)
                 then monadOfType(e.mtyperep, intType())
                 else intType();
  top.monadRewritten = if isMonad(e.mtyperep)
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
  top.merrors := e.merrors;
  top.mtyperep = intType();
  top.monadRewritten = lengthFunction('length', '(', new(e), ')', location=top.location);
}

aspect production stringLength
top::Expr ::= e::Decorated Expr
{
  top.merrors := e.merrors;
  top.mtyperep = if isMonad(e.mtyperep)
                 then monadOfType(e.mtyperep, intType())
                 else intType();
  top.monadRewritten = if isMonad(e.mtyperep)
                       then Silver_Expr {
                              $Expr {monadBind(e.mtyperep, top.location)}
                              ($Expr {e.monadRewritten},
                              \x::$TypeExpr {typerepTypeExpr(monadInnerType(e.mtyperep), location=top.location)} ->
                                  $Expr {monadReturn(e.mtyperep, top.location)} (length(x)))
                            }
                       else lengthFunction('length', '(', e.monadRewritten, ')', location=top.location);
}

aspect production toIntegerFunction
top::Expr ::= 'toInteger' '(' e::Expr ')'
{
  top.merrors := e.merrors;
  top.mtyperep = if isMonad(e.mtyperep)
                 then monadOfType(e.mtyperep, intType())
                 else intType();
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
  top.mtyperep = if isMonad(e.mtyperep)
                 then monadOfType(e.mtyperep, boolType())
                 else boolType();
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
  top.mtyperep = if isMonad(e.mtyperep)
                 then monadOfType(e.mtyperep, floatType())
                 else floatType();
  top.monadRewritten = if isMonad(e.mtyperep)
                       then Silver_Expr {
                              $Expr {monadBind(e.mtyperep, top.location)}
                              ($Expr {e.monadRewritten},
                              \x::$TypeExpr {typerepTypeExpr(monadInnerType(e.mtyperep), location=top.location)} ->
                                  $Expr {monadReturn(e.mtyperep, top.location)} (toFloat(x)))
                            }
                       else toFloatFunction('toFloat', '(', e.monadRewritten, ')', location=top.location);
}

{-
  TODO-Should this have special behavior for any monads?  Depends on whether it "works" on any of them.  Same with the above functions, I guess.
-}
aspect production toStringFunction
top::Expr ::= 'toString' '(' e::Expr ')'
{
  top.merrors := e.merrors;
  top.mtyperep = if isMonad(e.mtyperep)
                 then monadOfType(e.mtyperep, stringType())
                 else stringType();
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
  top.mtyperep =
    functionType(nonterminalType("core:Either", [stringType(), varType(freshTyVar())]), [nonterminalType("core:reflect:AST", [])], []);
  top.monadRewritten = reifyFunctionLiteral('reify', location=top.location);
}

aspect production newFunction
top::Expr ::= 'new' '(' e::Expr ')'
{
  top.merrors := e.merrors;
  top.mtyperep = e.mtyperep;
  top.monadRewritten = newFunction('new', '(', e.monadRewritten, ')', location=top.location);
}

{-
  TODO I don't know what these are supposed to be, really, but I suppose they should be bound in if they're monads?
-}
aspect production terminalConstructor
top::Expr ::= 'terminal' '(' t::TypeExpr ',' es::Expr ',' el::Expr ')'
{
  top.merrors := es.merrors ++ el.merrors;
  top.mtyperep = t.typerep;
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

