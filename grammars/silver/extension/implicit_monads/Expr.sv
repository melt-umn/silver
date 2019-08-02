grammar silver:extension:implicit_monads;

attribute monadRewritten<Expr>, merrors, mtyperep occurs on Expr;


aspect default production
top::Expr ::=
{
  top.merrors := [];
  --top.monadRewritten = error("Attribute monadRewritten must be defined on all productions");
  --top.mtyperep = errorType();
}


aspect production errorExpr
top::Expr ::= e::[Message]
{
  top.merrors := e;
  top.mtyperep = errorType();
  top.monadRewritten = errorExpr(e, location=top.location);
}

aspect production errorReference
top::Expr ::= msg::[Message]  q::Decorated QName
{
  top.merrors := msg;
  top.mtyperep = errorType();
  top.monadRewritten = errorReference(msg, q, location=top.location);
}

aspect production childReference
top::Expr ::= q::Decorated QName
{
  top.merrors := [];
  top.mtyperep = if q.lookupValue.typerep.isDecorable
                 then ntOrDecType(q.lookupValue.typerep, freshType())
                 else q.lookupValue.typerep;
  top.monadRewritten = baseExpr(new(q), location=top.location);
}

aspect production lhsReference
top::Expr ::= q::Decorated QName
{
  top.merrors := [];
  -- An LHS is *always* a decorable (nonterminal) type.
  top.mtyperep = ntOrDecType(q.lookupValue.typerep, freshType());
  top.monadRewritten = baseExpr(new(q), location=top.location);
}

aspect production localReference
top::Expr ::= q::Decorated QName
{
  top.merrors := [];
  top.mtyperep = if q.lookupValue.typerep.isDecorable
                 then ntOrDecType(q.lookupValue.typerep, freshType())
                 else q.lookupValue.typerep;
  top.monadRewritten = baseExpr(new(q), location=top.location);
}

aspect production forwardReference
top::Expr ::= q::Decorated QName
{
  top.merrors := [];
  -- An LHS (and thus, forward) is *always* a decorable (nonterminal) type.
  top.mtyperep = ntOrDecType(q.lookupValue.typerep, freshType());
  top.monadRewritten = baseExpr(new(q), location=top.location);
}

aspect production productionReference
top::Expr ::= q::Decorated QName
{
  top.merrors := [];
  top.mtyperep = freshenCompletely(q.lookupValue.typerep);
  top.monadRewritten = baseExpr(new(q), location=top.location);
}

aspect production functionReference
top::Expr ::= q::Decorated QName
{
  top.merrors := [];
  top.mtyperep = freshenCompletely(q.lookupValue.typerep);
  top.monadRewritten = baseExpr(new(q), location=top.location);
}

aspect production globalValueReference
top::Expr ::= q::Decorated QName
{
  top.merrors := [];
  top.mtyperep = freshenCompletely(q.lookupValue.typerep);
  top.monadRewritten = baseExpr(new(q), location=top.location);
}


aspect production functionInvocation
top::Expr ::= e::Decorated Expr es::Decorated AppExprs anns::Decorated AnnoAppExprs
{
  top.merrors := e.merrors ++ es.merrors;
  local mty::Type = head(es.monadTypesLocations).fst;
  --need to check that all our monads match
  top.merrors <- if null(es.monadTypesLocations) ||
                   foldr(\x::Pair<Type Integer> b::Boolean -> b && monadsMatch(mty, x.fst, e.upSubst).fst, 
                         true, tail(es.monadTypesLocations))
                then []
                else [err(top.location,
                      "All monad types used monadically in a function application must match")];
  --need to check it is compatible with the function return type
  top.merrors <- if isMonad(ety.outputType)
                then if null(es.monadTypesLocations)
                     then []
                     else if monadsMatch(ety.outputType, mty, e.upSubst).fst
                          then []
                          else [err(top.location,
                                    "Return type of function is a monad which doesn't " ++
                                     "match the monads used for arguments")]
                else [];

  local ety :: Type = e.mtyperep; --performSubstitution(e.mtyperep, e.upSubst);

  --needs to change based on whether there are monads or not
  top.mtyperep = --unsafeTrace(
                 if null(es.monadTypesLocations)
                 then ety.outputType
                 else if isMonad(ety.outputType)
                      then ety.outputType
                      else monadOfType(head(es.monadTypesLocations).fst, ety.outputType);--,
  --print("Function Invocation:  f: " ++ e.unparse ++ "; output type: " ++ prettyType(ety.outputType) ++ "; " ++
  --                   "full type: " ++ prettyType(ety) ++ "; " ++
  --                   (if null(es.monadTypesLocations)
  --                    then "no monadic arguments"
  --                    else "monadic arguments") ++ "; " ++ top.location.unparse ++ "\n", unsafeIO()));

  --whether we need to wrap the ultimate function call in monadRewritten in a Return
  local wrapReturn::Boolean = !isMonad(ety.outputType) && !null(es.monadTypesLocations);

  {-
    Monad translation creates a lambda to apply to all the arguments
    plus the function (to get fresh names for everything), then
    creates a body that binds all the monadic arguments into the final
    function application.

    For example, if we have
       fun(a, b, c, d)
    where a and d are monadic, then we translate into
       (\a1 a2 a3 a4 f. a1 >>= (\a1. a4 >>= (\a4. f(a1, a2, a3, a4))))(a, b, c, d, fun)
    Reusing ai in the bind for the ith argument simplifies doing the
    application inside all the binds.
  -}
  --TODO also needs to deal with the case where the function is a monad
  local lambda_fun::Expr = buildMonadApplicationLambda(es.realTypes, es.monadTypesLocations, ety, wrapReturn);
  local expanded_args::AppExprs = snocAppExprs(new(es), ',', presentAppExpr(new(e), location=bogusLoc()),
                                               location=bogusLoc());
  --haven't done monadRewritten on annotated ones, so ignore them
  top.monadRewritten = if null(es.monadTypesLocations)
                       then applicationExpr(e.monadRewritten, '(', es.monadRewritten, ')', location=bogusLoc())
                       else
                         case anns of
                         | emptyAnnoAppExprs() ->
                           applicationExpr(lambda_fun, '(', expanded_args, ')', location=bogusLoc())
                         | _ -> 
                           error("Monad Rewriting not defined with annotated " ++
                                 "expressions in a function application")
                         end;
}
--build the lambda to apply to all the original arguments plus the function
--we're going to assume this is only called if monadTysLocs is non-empty
function buildMonadApplicationLambda
Expr ::= realtys::[Type] monadTysLocs::[Pair<Type Integer>] funType::Type wrapReturn::Boolean
{
  local funargs::AppExprs = buildFunArgs(length(realtys));
  local params::ProductionRHS = buildMonadApplicationParams(realtys, 1, funType);
  local body::Expr = buildMonadApplicationBody(monadTysLocs, funargs, head(monadTysLocs).fst, wrapReturn);
  return lambdap(params, body, location=bogusLoc());
}
--build the parameters for the lambda applied to all the original arguments plus the function
function buildMonadApplicationParams
ProductionRHS ::= realtys::[Type] currentLoc::Integer funType::Type
{
  return if null(realtys)
         then productionRHSCons(productionRHSElem(name("f", bogusLoc()),
                                                  '::',
                                                  typerepTypeExpr(funType, location=bogusLoc()),
                                                  location=bogusLoc()),
                                productionRHSNil(location=bogusLoc()),
                                location=bogusLoc())
         else productionRHSCons(productionRHSElem(name("a"++toString(currentLoc), bogusLoc()),
                                                  '::',
                                                  --typerepTypeExpr(head(realtys), location=bogusLoc()),
                                                  typerepTypeExpr(dropDecorated(head(realtys)), location=bogusLoc()),
                                                  location=bogusLoc()),
                                buildMonadApplicationParams(tail(realtys), currentLoc+1, funType),
                                location=bogusLoc());
}
--build the arguments for the application inside all the binds
function buildFunArgs
AppExprs ::= currentIndex::Integer
{
  return if currentIndex == 0
         then emptyAppExprs(location=bogusLoc())
         else snocAppExprs(buildFunArgs(currentIndex - 1), ',',
                           presentAppExpr(baseExpr(qName(bogusLoc(),
                                                         "a"++toString(currentIndex)),
                                                   location=bogusLoc()),
                                          location=bogusLoc()), location=bogusLoc());
}
--build the body of the lambda which includes all the binds
function buildMonadApplicationBody
Expr ::= monadTysLocs::[Pair<Type Integer>] funargs::AppExprs monadType::Type wrapReturn::Boolean
{
  local sub::Expr = buildMonadApplicationBody(tail(monadTysLocs), funargs, monadType, wrapReturn);
  local argty::Type = head(monadTysLocs).fst;
  local bind::Expr = monadBind(argty, bogusLoc());
  local binding::ProductionRHS = productionRHSCons(productionRHSElem(name("a"++toString(head(monadTysLocs).snd),
                                                                          bogusLoc()),
                                                                     '::', 
                                                                     typerepTypeExpr(monadInnerType(argty),
                                                                                     location=bogusLoc()),
                                                                     location=bogusLoc()),
                                                   productionRHSNil(location=bogusLoc()),
                                                   location=bogusLoc());
  local bindargs::AppExprs = snocAppExprs(
                             oneAppExprs(presentAppExpr(
                                            baseExpr(qName(bogusLoc(),"a"++toString(head(monadTysLocs).snd)),
                                                     location=bogusLoc()),
                                            location=bogusLoc()),
                                         location=bogusLoc()),
                             ',',
                              presentAppExpr(lambdap(binding, sub, location=bogusLoc()),
                                             location=bogusLoc()),
                              location=bogusLoc());

  local step::Expr = applicationExpr(bind, '(', bindargs, ')', location=bogusLoc());

  --the function is always going to be bound into the name "f", so we hard code that here
  local baseapp::Expr = applicationExpr(baseExpr(qName(bogusLoc(), "f"), location=bogusLoc()),
                                        '(', funargs, ')', location=bogusLoc());
  local funapp::Expr = if wrapReturn
                       then Silver_Expr { $Expr {monadReturn(monadType, bogusLoc())}($Expr {baseapp}) }
                       else baseapp;

  return if null(monadTysLocs)
         then funapp
         else step;
}
aspect production partialApplication
top::Expr ::= e::Decorated Expr es::Decorated AppExprs anns::Decorated AnnoAppExprs
{
  top.merrors := e.merrors ++ es.merrors;

  local ety :: Type = performSubstitution(e.mtyperep, e.upSubst);

  top.mtyperep = functionType(ety.outputType, es.missingTypereps ++ anns.partialAnnoTypereps, anns.missingAnnotations);
  top.monadRewritten = error("monadRewritten not defined on partial applications, but should be in the future");
}

aspect production errorApplication
top::Expr ::= e::Decorated Expr es::AppExprs anns::AnnoAppExprs
{
  top.merrors := e.merrors ++
    (if e.typerep.isError then [] else  
    [err(top.location, e.unparse ++ " has type " ++ prettyType(performSubstitution(e.mtyperep, e.upSubst)) ++
      " and cannot be invoked as a function.")]) ++
    es.errors ++ anns.errors;

  top.mtyperep = errorType();
  top.monadRewritten = application(new(e), '(', es, ',', anns, ')', location=top.location);
}

aspect production attributeSection
top::Expr ::= '(' '.' q::QName ')'
{
  top.merrors := [];
  top.mtyperep = functionType(freshenCompletely(q.lookupAttribute.typerep), [freshType()], []);
  top.monadRewritten = attributeSection('(', '.', q, ')', location=top.location);
}

aspect production forwardAccess
top::Expr ::= e::Expr '.' 'forward'
{
  top.merrors := e.errors;
  top.mtyperep = e.mtyperep;
  top.monadRewritten = forwardAccess(e.monadRewritten, '.', 'forward', location=top.location);
}

aspect production access
top::Expr ::= e::Expr '.' q::QNameAttrOccur
{
  top.merrors := e.merrors ++ forward.merrors;
}

aspect production errorAccessHandler
top::Expr ::= e::Decorated Expr  q::Decorated QNameAttrOccur
{
  top.mtyperep = errorType();
  top.merrors := [];
  top.monadRewritten = access(new(e), '.', new(q), location=top.location);
}

aspect production annoAccessHandler
top::Expr ::= e::Decorated Expr  q::Decorated QNameAttrOccur
{
  top.mtyperep = q.typerep;
  top.merrors := [];
  top.monadRewritten = access(e.monadRewritten, '.', new(q), location=top.location);
}

aspect production terminalAccessHandler
top::Expr ::= e::Decorated Expr  q::Decorated QNameAttrOccur
{
  top.merrors := [];

  top.mtyperep =
    if q.name == "lexeme" || q.name == "filename"
    then stringType()
    else if q.name == "line" || q.name == "column"
    then intType()
    else if q.name == "location"
    then nonterminalType("core:Location", [])
    else errorType();

  top.monadRewritten = access(e.monadRewritten, '.', new(q), location=top.location);
}

aspect production synDecoratedAccessHandler
top::Expr ::= e::Decorated Expr  q::Decorated QNameAttrOccur
{
  top.mtyperep = q.typerep;
  top.merrors := [];
  top.monadRewritten = access(e.monadRewritten, '.', new(q), location=top.location);
}

aspect production inhDecoratedAccessHandler
top::Expr ::= e::Decorated Expr  q::Decorated QNameAttrOccur
{
  top.mtyperep = q.typerep;
  top.merrors := [];
  top.monadRewritten = access(e.monadRewritten, '.', new(q), location=top.location);
}

aspect production errorDecoratedAccessHandler
top::Expr ::= e::Decorated Expr  q::Decorated QNameAttrOccur
{
  top.merrors := [];
  top.mtyperep = errorType();
  top.monadRewritten = access(e.monadRewritten, '.', new(q), location=top.location);
}


aspect production decorateExprWith
top::Expr ::= 'decorate' e::Expr 'with' '{' inh::ExprInhs '}'
{
  top.mtyperep = decoratedType(performSubstitution(e.mtyperep, e.upSubst)); -- .decoratedForm?
  top.merrors := e.merrors;
  top.monadRewritten = decorateExprWith('decorate', e.monadRewritten, 'with',
                                        '{', inh, '}', location=top.location);
}


aspect production trueConst
top::Expr ::= 'true'
{
  top.mtyperep = boolType();
  top.merrors := [];
  top.monadRewritten = trueConst('true', location=top.location);
}

aspect production falseConst
top::Expr ::= 'false'
{
  top.mtyperep = boolType();
  top.merrors := [];
  top.monadRewritten = falseConst('false', location=top.location);
}

aspect production and
top::Expr ::= e1::Expr '&&' e2::Expr
{
  top.merrors := e1.merrors ++ e2.merrors;

  top.mtyperep = if isMonad(e1.mtyperep)
                then e1.mtyperep --assume it will be well-typed
                else if isMonad(e2.mtyperep)
                     then e2.mtyperep
                     else boolType();

  --we assume both have the same monad, so we only need one return
  --e1 >>= ( (\x::Bool y::M(Bool). y >>= (\z::Bool. Return(x && z))) (_, e2) )
  local bindBoth::Expr =
    Silver_Expr {
      $Expr {monadBind(e1.mtyperep, top.location)}
      ($Expr {e1.monadRewritten},
       (\ x::Boolean y::Boolean ->
        $Expr {monadBind(e1.mtyperep, top.location)}
        (y,
         \ z::Boolean ->
          $Expr {monadReturn(e1.mtyperep, top.location)}
          (x && z)))(_, $Expr {e2.monadRewritten}))
    };
  --e1 >>= ( (\x::Bool y::Bool. Return(x && y))(_, e2) )
  local bind1::Expr =
    Silver_Expr {
      $Expr {monadBind(e1.mtyperep, top.location)}
      ($Expr {e1.monadRewritten},
       (\ x::Boolean y::Boolean -> 
          $Expr {monadReturn(e1.mtyperep, top.location)}
         (x && y))(_, $Expr {e2.monadRewritten}))
    };
  --e2 >>= ( (\x::Bool y::Bool. Return(x && y))(e1, _) )
  local bind2::Expr =
    Silver_Expr {
      $Expr {monadBind(e2.mtyperep, top.location)}
      ($Expr {e2.monadRewritten},
       (\ x::Boolean y::Boolean -> 
          $Expr {monadReturn(e2.mtyperep, top.location)}
         (x && y))($Expr {e1.monadRewritten}, _))
    };
  top.monadRewritten = if isMonad(e1.mtyperep)
                       then if isMonad(e2.mtyperep)
                            then bindBoth
                            else bind1
                       else if isMonad(e2.mtyperep)
                            then bind2
                            else and(e1.monadRewritten, '&&', e2.monadRewritten, location=top.location);
}

aspect production or
top::Expr ::= e1::Expr '||' e2::Expr
{
  top.merrors := e1.merrors ++ e2.merrors;

  top.mtyperep = if isMonad(e1.mtyperep)
                then e1.mtyperep --assume it will be well-typed
                else if isMonad(e2.mtyperep)
                     then e2.mtyperep
                     else boolType();

  --we assume both have the same monad, so we only need one return
  --e1 >>= ( (\x::Bool y::M(Bool). y >>= (\z::Bool. Return(x || z))) (_, e2) )
  local bindBoth::Expr =
    Silver_Expr {
      $Expr {monadBind(e1.mtyperep, top.location)}
      ($Expr {e1.monadRewritten},
       (\ x::Boolean y::Boolean ->
        $Expr {monadBind(e1.mtyperep, top.location)}
        (y,
         \ z::Boolean ->
          $Expr {monadReturn(e1.mtyperep, top.location)}
          (x || z)))(_, $Expr {e2.monadRewritten}))
    };
  --e1 >>= ( (\x::Bool y::Bool. Return(x || y))(_, e2) )
  local bind1::Expr =
    Silver_Expr {
      $Expr {monadBind(e1.mtyperep, top.location)}
      ($Expr {e1.monadRewritten},
       (\ x::Boolean y::Boolean -> 
          $Expr {monadReturn(e1.mtyperep, top.location)}
         (x || y))(_, $Expr {e2.monadRewritten}))
    };
  --e2 >>= ( (\x::Bool y::Bool. Return(x || y))(e1, _) )
  local bind2::Expr =
    Silver_Expr {
      $Expr {monadBind(e2.mtyperep, top.location)}
      ($Expr {e2.monadRewritten},
       (\ x::Boolean y::Boolean -> 
          $Expr {monadReturn(e2.mtyperep, top.location)}
         (x || y))($Expr {e1.monadRewritten}, _))
    };
  top.monadRewritten = if isMonad(e1.mtyperep)
                       then if isMonad(e2.mtyperep)
                            then bindBoth
                            else bind1
                       else if isMonad(e2.mtyperep)
                            then bind2
                            else or(e1.monadRewritten, '||', e2.monadRewritten, location=top.location);
}

aspect production not
top::Expr ::= '!' e::Expr
{
  top.merrors := e.merrors;

  top.mtyperep = e.mtyperep; --assume it will be well-typed

  top.monadRewritten =
    if isMonad(e.mtyperep)
    then Silver_Expr {
           $Expr {monadBind(e.mtyperep, top.location)}
            ($Expr {e.monadRewritten},
             \x::Boolean -> 
              $Expr {monadReturn(e.mtyperep, top.location)}(!x))
         }
    else not('!', e.monadRewritten, location=top.location);
}

aspect production gt
top::Expr ::= e1::Expr '>' e2::Expr
{
  top.merrors := e1.merrors ++ e2.merrors;

  top.mtyperep = if isMonad(e1.mtyperep)
                then monadOfType(e1.mtyperep, boolType())
                else if isMonad(e2.mtyperep)
                     then monadOfType(e2.mtyperep, boolType())
                     else boolType();

  --we assume both have the same monad, so we only need one return
  --e1 >>= ( (\x y -> y >>= \z -> Return(x > z))(_, e2) )
  local bindBoth::Expr =
    Silver_Expr {
      $Expr {monadBind(e2.mtyperep, top.location)}
      ($Expr {e1.monadRewritten},
       (\x::$TypeExpr {typerepTypeExpr(monadInnerType(e2.mtyperep), location=top.location)}
         y::$TypeExpr {typerepTypeExpr(e2.mtyperep, location=top.location)} ->
          $Expr {monadBind(e2.mtyperep, top.location)}
          (y,
           \z::$TypeExpr {typerepTypeExpr(monadInnerType(e2.mtyperep), location=top.location)} ->
            $Expr {monadReturn(e2.mtyperep, top.location)}
            (x > z))) (_, $Expr {e2.monadRewritten}))
    };
  --e1 >>= ( (\x y -> Return(x > y))(_, e2) )
  local bind1::Expr =
    Silver_Expr {
      $Expr {monadBind(e1.mtyperep, top.location)}
      ($Expr {e1.monadRewritten},
       (\x::$TypeExpr {typerepTypeExpr(monadInnerType(e1.mtyperep), location=top.location)}
         y::$TypeExpr {typerepTypeExpr(e2.mtyperep, location=top.location)} ->
        $Expr {monadReturn(e1.mtyperep, top.location)}
        (x > y))(_, $Expr {e2.monadRewritten}))
    };
  --e2 >>= ( (\x y -> Return(x > y))(e1, _) )
  local bind2::Expr =
    Silver_Expr {
      $Expr {monadBind(e2.mtyperep, top.location)}
      ($Expr {e2.monadRewritten},
       (\x::$TypeExpr {typerepTypeExpr(e1.mtyperep, location=top.location)}
         y::$TypeExpr {typerepTypeExpr(monadInnerType(e2.mtyperep), location=top.location)} ->
        $Expr {monadReturn(e2.mtyperep, top.location)}
        (x > y))($Expr {e1.monadRewritten}, _))
    };
  top.monadRewritten = if isMonad(e1.mtyperep)
                       then if isMonad(e2.mtyperep)
                            then bindBoth
                            else bind1
                       else if isMonad(e2.mtyperep)
                            then bind2
                            else gt(e1.monadRewritten, '>', e2.monadRewritten, location=top.location);
}

aspect production lt
top::Expr ::= e1::Expr '<' e2::Expr
{
  top.merrors := e1.merrors ++ e2.merrors;

  top.mtyperep = if isMonad(e1.mtyperep)
                then monadOfType(e1.mtyperep, boolType())
                else if isMonad(e2.mtyperep)
                     then monadOfType(e2.mtyperep, boolType())
                     else boolType();

  --we assume both have the same monad, so we only need one return
  --e1 >>= ( (\x y -> y >>= \z -> Return(x < z))(_, e2) )
  local bindBoth::Expr =
    Silver_Expr {
      $Expr {monadBind(e2.mtyperep, top.location)}
      ($Expr {e1.monadRewritten},
       (\x::$TypeExpr {typerepTypeExpr(monadInnerType(e2.mtyperep), location=top.location)}
         y::$TypeExpr {typerepTypeExpr(e2.mtyperep, location=top.location)} ->
          $Expr {monadBind(e2.mtyperep, top.location)}
          (y,
           \z::$TypeExpr {typerepTypeExpr(monadInnerType(e2.mtyperep), location=top.location)} ->
            $Expr {monadReturn(e2.mtyperep, top.location)}
            (x < z))) (_, $Expr {e2.monadRewritten}))
    };
  --e1 >>= ( (\x y -> Return(x < y))(_, e2) )
  local bind1::Expr =
    Silver_Expr {
      $Expr {monadBind(e1.mtyperep, top.location)}
      ($Expr {e1.monadRewritten},
       (\x::$TypeExpr {typerepTypeExpr(monadInnerType(e1.mtyperep), location=top.location)}
         y::$TypeExpr {typerepTypeExpr(e2.mtyperep, location=top.location)} ->
        $Expr {monadReturn(e1.mtyperep, top.location)}
        (x < y))(_, $Expr {e2.monadRewritten}))
    };
  --e2 >>= ( (\x y -> Return(x < y))(e1, _) )
  local bind2::Expr =
    Silver_Expr {
      $Expr {monadBind(e2.mtyperep, top.location)}
      ($Expr {e2.monadRewritten},
       (\x::$TypeExpr {typerepTypeExpr(e1.mtyperep, location=top.location)}
         y::$TypeExpr {typerepTypeExpr(monadInnerType(e2.mtyperep), location=top.location)} ->
        $Expr {monadReturn(e2.mtyperep, top.location)}
        (x < y))($Expr {e1.monadRewritten}, _))
    };
  top.monadRewritten = if isMonad(e1.mtyperep)
                       then if isMonad(e2.mtyperep)
                            then bindBoth
                            else bind1
                       else if isMonad(e2.mtyperep)
                            then bind2
                            else lt(e1.monadRewritten, '<', e2.monadRewritten, location=top.location);
}

aspect production gteq
top::Expr ::= e1::Expr '>=' e2::Expr
{
  top.merrors := e1.merrors ++ e2.merrors;

  top.mtyperep = if isMonad(e1.mtyperep)
                then monadOfType(e1.mtyperep, boolType())
                else if isMonad(e2.mtyperep)
                     then monadOfType(e2.mtyperep, boolType())
                     else boolType();

  --we assume both have the same monad, so we only need one return
  --e1 >>= ( (\x y -> y >>= \z -> Return(x >= z))(_, e2) )
  local bindBoth::Expr =
    Silver_Expr {
      $Expr {monadBind(e2.mtyperep, top.location)}
      ($Expr {e1.monadRewritten},
       (\x::$TypeExpr {typerepTypeExpr(monadInnerType(e2.mtyperep), location=top.location)}
         y::$TypeExpr {typerepTypeExpr(e2.mtyperep, location=top.location)} ->
          $Expr {monadBind(e2.mtyperep, top.location)}
          (y,
           \z::$TypeExpr {typerepTypeExpr(monadInnerType(e2.mtyperep), location=top.location)} ->
            $Expr {monadReturn(e2.mtyperep, top.location)}
            (x >= z))) (_, $Expr {e2.monadRewritten}))
    };
  --e1 >>= ( (\x y -> Return(x >= y))(_, e2) )
  local bind1::Expr =
    Silver_Expr {
      $Expr {monadBind(e1.mtyperep, top.location)}
      ($Expr {e1.monadRewritten},
       (\x::$TypeExpr {typerepTypeExpr(monadInnerType(e1.mtyperep), location=top.location)}
         y::$TypeExpr {typerepTypeExpr(e2.mtyperep, location=top.location)} ->
        $Expr {monadReturn(e1.mtyperep, top.location)}
        (x >= y))(_, $Expr {e2.monadRewritten}))
    };
  --e2 >>= ( (\x y -> Return(x >= y))(e1, _) )
  local bind2::Expr =
    Silver_Expr {
      $Expr {monadBind(e2.mtyperep, top.location)}
      ($Expr {e2.monadRewritten},
       (\x::$TypeExpr {typerepTypeExpr(e1.mtyperep, location=top.location)}
         y::$TypeExpr {typerepTypeExpr(monadInnerType(e2.mtyperep), location=top.location)} ->
        $Expr {monadReturn(e2.mtyperep, top.location)}
        (x >= y))($Expr {e1.monadRewritten}, _))
    };
  top.monadRewritten = if isMonad(e1.mtyperep)
                       then if isMonad(e2.mtyperep)
                            then bindBoth
                            else bind1
                       else if isMonad(e2.mtyperep)
                            then bind2
                            else gteq(e1.monadRewritten, '>=', e2.monadRewritten, location=top.location);
}

aspect production lteq
top::Expr ::= e1::Expr '<=' e2::Expr
{
  top.merrors := e1.merrors ++ e2.merrors;

  top.mtyperep = if isMonad(e1.mtyperep)
                then monadOfType(e1.mtyperep, boolType())
                else if isMonad(e2.mtyperep)
                     then monadOfType(e2.mtyperep, boolType())
                     else boolType();

  --we assume both have the same monad, so we only need one return
  --e1 >>= ( (\x y -> y >>= \z -> Return(x <= z))(_, e2) )
  local bindBoth::Expr =
    Silver_Expr {
      $Expr {monadBind(e2.mtyperep, top.location)}
      ($Expr {e1.monadRewritten},
       (\x::$TypeExpr {typerepTypeExpr(monadInnerType(e2.mtyperep), location=top.location)}
         y::$TypeExpr {typerepTypeExpr(e2.mtyperep, location=top.location)} ->
          $Expr {monadBind(e2.mtyperep, top.location)}
          (y,
           \z::$TypeExpr {typerepTypeExpr(monadInnerType(e2.mtyperep), location=top.location)} ->
            $Expr {monadReturn(e2.mtyperep, top.location)}
            (x <= z))) (_, $Expr {e2.monadRewritten}))
    };
  --e1 >>= ( (\x y -> Return(x <= y))(_, e2) )
  local bind1::Expr =
    Silver_Expr {
      $Expr {monadBind(e1.mtyperep, top.location)}
      ($Expr {e1.monadRewritten},
       (\x::$TypeExpr {typerepTypeExpr(monadInnerType(e1.mtyperep), location=top.location)}
         y::$TypeExpr {typerepTypeExpr(e2.mtyperep, location=top.location)} ->
        $Expr {monadReturn(e1.mtyperep, top.location)}
        (x <= y))(_, $Expr {e2.monadRewritten}))
    };
  --e2 >>= ( (\x y -> Return(x <= y))(e1, _) )
  local bind2::Expr =
    Silver_Expr {
      $Expr {monadBind(e2.mtyperep, top.location)}
      ($Expr {e2.monadRewritten},
       (\x::$TypeExpr {typerepTypeExpr(e1.mtyperep, location=top.location)}
         y::$TypeExpr {typerepTypeExpr(monadInnerType(e2.mtyperep), location=top.location)} ->
        $Expr {monadReturn(e2.mtyperep, top.location)}
        (x <= y))($Expr {e1.monadRewritten}, _))
    };
  top.monadRewritten = if isMonad(e1.mtyperep)
                       then if isMonad(e2.mtyperep)
                            then bindBoth
                            else bind1
                       else if isMonad(e2.mtyperep)
                            then bind2
                            else lteq(e1.monadRewritten, '<=', e2.monadRewritten, location=top.location);
}

aspect production eqeq
top::Expr ::= e1::Expr '==' e2::Expr
{
  top.merrors := e1.merrors ++ e2.merrors;

  top.mtyperep = if isMonad(e1.mtyperep)
                then monadOfType(e1.mtyperep, boolType())
                else if isMonad(e2.mtyperep)
                     then monadOfType(e2.mtyperep, boolType())
                     else boolType();

  --we assume both have the same monad, so we only need one return
  --e1 >>= ( (\x y -> y >>= \z -> Return(x == z))(_, e2) )
  local bindBoth::Expr =
    Silver_Expr {
      $Expr {monadBind(e2.mtyperep, top.location)}
      ($Expr {e1.monadRewritten},
       (\x::$TypeExpr {typerepTypeExpr(monadInnerType(e2.mtyperep), location=top.location)}
         y::$TypeExpr {typerepTypeExpr(e2.mtyperep, location=top.location)} ->
          $Expr {monadBind(e2.mtyperep, top.location)}
          (y,
           \z::$TypeExpr {typerepTypeExpr(monadInnerType(e2.mtyperep), location=top.location)} ->
            $Expr {monadReturn(e2.mtyperep, top.location)}
            (x == z))) (_, $Expr {e2.monadRewritten}))
    };
  --e1 >>= ( (\x y -> Return(x == y))(_, e2) )
  local bind1::Expr =
    Silver_Expr {
      $Expr {monadBind(e1.mtyperep, top.location)}
      ($Expr {e1.monadRewritten},
       (\x::$TypeExpr {typerepTypeExpr(monadInnerType(e1.mtyperep), location=top.location)}
         y::$TypeExpr {typerepTypeExpr(e2.mtyperep, location=top.location)} ->
        $Expr {monadReturn(e1.mtyperep, top.location)}
        (x == y))(_, $Expr {e2.monadRewritten}))
    };
  --e2 >>= ( (\x y -> Return(x == y))(e1, _) )
  local bind2::Expr =
    Silver_Expr {
      $Expr {monadBind(e2.mtyperep, top.location)}
      ($Expr {e2.monadRewritten},
       (\x::$TypeExpr {typerepTypeExpr(e1.mtyperep, location=top.location)}
         y::$TypeExpr {typerepTypeExpr(monadInnerType(e2.mtyperep), location=top.location)} ->
        $Expr {monadReturn(e2.mtyperep, top.location)}
        (x == y))($Expr {e1.monadRewritten}, _))
    };
  top.monadRewritten = if isMonad(e1.mtyperep)
                       then if isMonad(e2.mtyperep)
                            then bindBoth
                            else bind1
                       else if isMonad(e2.mtyperep)
                            then bind2
                            else eqeq(e1.monadRewritten, '==', e2.monadRewritten, location=top.location);
}

aspect production neq
top::Expr ::= e1::Expr '!=' e2::Expr
{
  top.merrors := e1.merrors ++ e2.merrors;

  top.mtyperep = if isMonad(e1.mtyperep)
                then monadOfType(e1.mtyperep, boolType())
                else if isMonad(e2.mtyperep)
                     then monadOfType(e2.mtyperep, boolType())
                     else boolType();

  --we assume both have the same monad, so we only need one return
  --e1 >>= ( (\x y -> y >>= \z -> Return(x != z))(_, e2) )
  local bindBoth::Expr =
    Silver_Expr {
      $Expr {monadBind(e2.mtyperep, top.location)}
      ($Expr {e1.monadRewritten},
       (\x::$TypeExpr {typerepTypeExpr(monadInnerType(e2.mtyperep), location=top.location)}
         y::$TypeExpr {typerepTypeExpr(e2.mtyperep, location=top.location)} ->
          $Expr {monadBind(e2.mtyperep, top.location)}
          (y,
           \z::$TypeExpr {typerepTypeExpr(monadInnerType(e2.mtyperep), location=top.location)} ->
            $Expr {monadReturn(e2.mtyperep, top.location)}
            (x != z))) (_, $Expr {e2.monadRewritten}))
    };
  --e1 >>= ( (\x y -> Return(x != y))(_, e2) )
  local bind1::Expr =
    Silver_Expr {
      $Expr {monadBind(e1.mtyperep, top.location)}
      ($Expr {e1.monadRewritten},
       (\x::$TypeExpr {typerepTypeExpr(monadInnerType(e1.mtyperep), location=top.location)}
         y::$TypeExpr {typerepTypeExpr(e2.mtyperep, location=top.location)} ->
        $Expr {monadReturn(e1.mtyperep, top.location)}
        (x != y))(_, $Expr {e2.monadRewritten}))
    };
  --e2 >>= ( (\x y -> Return(x != y))(e1, _) )
  local bind2::Expr =
    Silver_Expr {
      $Expr {monadBind(e2.mtyperep, top.location)}
      ($Expr {e2.monadRewritten},
       (\x::$TypeExpr {typerepTypeExpr(e1.mtyperep, location=top.location)}
         y::$TypeExpr {typerepTypeExpr(monadInnerType(e2.mtyperep), location=top.location)} ->
        $Expr {monadReturn(e2.mtyperep, top.location)}
        (x != y))($Expr {e1.monadRewritten}, _))
    };
  top.monadRewritten = if isMonad(e1.mtyperep)
                       then if isMonad(e2.mtyperep)
                            then bindBoth
                            else bind1
                       else if isMonad(e2.mtyperep)
                            then bind2
                            else neq(e1.monadRewritten, '!=', e2.monadRewritten, location=top.location);
}

concrete production ifThen
top::Expr ::= 'if' e1::Expr 'then' e2::Expr 'end' --this is easier than anything else to do
{
  top.unparse = "if " ++ e1.unparse  ++ " then " ++ e2.unparse ++ " end";
  e1.downSubst = top.downSubst;
  e2.downSubst = e1.upSubst;
  top.upSubst = e2.upSubst;

  local bind::Expr = if isMonad(e1.mtyperep)
                     then monadFail(e1.mtyperep, bogusLoc())
                     else monadFail(e2.mtyperep, bogusLoc());
  local attribute arg::Maybe<Expr>;
  arg = case monadFailArgument(e1.mtyperep, top.location),
             monadFailArgument(e2.mtyperep, top.location) of
        | just(x), _ -> just(x)
        | _, just(x) -> just(x)
        | _, _ -> nothing()
        end;
  local fail::Expr = Silver_Expr {
                       $Expr{bind}($Expr{case arg of | just(a) -> a end})
                     };

  forwards to if isMonad(e1.mtyperep) || isMonad(e2.mtyperep)
              then case arg of
                   | just(_) -> ifThenElse('if', e1, 'then', e2, 'else', fail, location=top.location)
                   | nothing() -> 
                     errorExpr([err(top.location, "The monad used in an if-then " ++
                                "must be able to have a Fail argument generated (the " ++
                                "argument must be Integer, Float, String, or List), which" ++
                                " is not true for " ++
                                prettyType(performSubstitution(if isMonad(e1.mtyperep)
                                                               then e1.mtyperep
                                                               else e2.mtyperep, top.upSubst)))],
                                location=top.location)
                   end
              else errorExpr([err(top.location, "One of the expressions in " ++
                              "an if-then has to have a monad type--instead, have " ++
                              prettyType(performSubstitution(e1.mtyperep, top.upSubst)) ++
                              " and " ++ prettyType(performSubstitution(e2.mtyperep, top.upSubst)))],
                              location=top.location);
}

aspect production ifThenElse
top::Expr ::= 'if' e1::Expr 'then' e2::Expr 'else' e3::Expr
{
  top.merrors := e1.merrors ++ e2.merrors ++ e3.merrors;
  top.mtyperep = if isMonad(e1.mtyperep)
                then if isMonad(e2.mtyperep)
                     then e2.mtyperep
                     else if isMonad(e3.mtyperep)
                          then e3.mtyperep
                          else monadOfType(e1.mtyperep, e3.mtyperep)
                else if isMonad(e2.mtyperep)
                     then e2.mtyperep
                     else e3.mtyperep;

  --To deal with the case where one type or the other might be "generic" (e.g. Maybe<a>),
  --   we want to do substitution on the types before putting them into the monadRewritten
  local e2Type::Type = performSubstitution(e2.mtyperep, top.upSubst);
  local e3Type::Type = performSubstitution(e3.mtyperep, top.upSubst);
  --We assume that if e2 or e3 are monads, they are the same as e1 if that is a
  --   monad and we don't allow monads to become nested.
  local cMonad::Expr =
    Silver_Expr {
      $Expr {monadBind(e1.mtyperep, top.location)}
      ($Expr {e1.monadRewritten},
       (\c::Boolean
         x::$TypeExpr {typerepTypeExpr(dropDecorated(e2Type), location=top.location)}
         y::$TypeExpr {typerepTypeExpr(dropDecorated(e3Type), location=top.location)} ->
         if c
         then $Expr { if isMonad(e2.mtyperep)
                      then Silver_Expr {x}
                      else Silver_Expr {$Expr {monadReturn(e1.mtyperep, top.location)}(x)} }
         else $Expr { if isMonad(e3.mtyperep)
                      then Silver_Expr {y}
                      else Silver_Expr {$Expr {monadReturn(e1.mtyperep, top.location)}(y)} })
       (_, $Expr {e2.monadRewritten}, $Expr {e3.monadRewritten}))
    };
  local cBool::Expr =
    Silver_Expr {
      if $Expr {e1.monadRewritten}
      then $Expr {if isMonad(e2.mtyperep)
                  then e2.monadRewritten
                  else if isMonad(e3.mtyperep)
                       then Silver_Expr { $Expr {monadReturn(e3.mtyperep, top.location)}($Expr {e2.monadRewritten}) }
                       else e2.monadRewritten}
      else $Expr {if isMonad(e3.mtyperep)
                  then e3.monadRewritten
                  else if isMonad(e2.mtyperep)
                       then Silver_Expr { $Expr {monadReturn(e2.mtyperep, top.location)}($Expr {e3.monadRewritten}) }
                       else e3.monadRewritten}
    };
  top.monadRewritten = if isMonad(e1.mtyperep)
                       then cMonad
                       else cBool;
} 

aspect production intConst
top::Expr ::= i::Int_t
{
  top.merrors := [];
  top.mtyperep = intType();
  top.monadRewritten = intConst(i, location=top.location);
}

aspect production floatConst
top::Expr ::= f::Float_t
{
  top.merrors := [];
  top.mtyperep = floatType();
  top.monadRewritten = floatConst(f, location=top.location);
} 

aspect production plus
top::Expr ::= e1::Expr '+' e2::Expr
{
  top.merrors := e1.merrors ++ e2.merrors;

  top.mtyperep = if isMonad(e1.mtyperep)
                then e1.mtyperep
                else e2.mtyperep;

  --we assume both have the same monad, so we only need one return
  --e1 >>= ( (\x y -> y >>= \z -> Return(x + z))(_, e2) )
  local bindBoth::Expr =
    Silver_Expr {
      $Expr {monadBind(e2.mtyperep, top.location)}
      ($Expr {e1.monadRewritten},
       (\x::$TypeExpr {typerepTypeExpr(monadInnerType(e2.mtyperep), location=top.location)}
         y::$TypeExpr {typerepTypeExpr(e2.mtyperep, location=top.location)} ->
          $Expr {monadBind(e2.mtyperep, top.location)}
          (y,
           \z::$TypeExpr {typerepTypeExpr(monadInnerType(e2.mtyperep), location=top.location)} ->
            $Expr {monadReturn(e2.mtyperep, top.location)}
            (x + z))) (_, $Expr {e2.monadRewritten}))
    };
  --e1 >>= ( (\x y -> Return(x + y))(_, e2) )
  local bind1::Expr =
    Silver_Expr {
      $Expr {monadBind(e1.mtyperep, top.location)}
      ($Expr {e1.monadRewritten},
       (\x::$TypeExpr {typerepTypeExpr(monadInnerType(e1.mtyperep), location=top.location)}
         y::$TypeExpr {typerepTypeExpr(e2.mtyperep, location=top.location)} ->
        $Expr {monadReturn(e1.mtyperep, top.location)}
        (x + y))(_, $Expr {e2.monadRewritten}))
    };
  --e2 >>= ( (\x y -> Return(x + y))(e1, _) )
  local bind2::Expr =
    Silver_Expr {
      $Expr {monadBind(e2.mtyperep, top.location)}
      ($Expr {e2.monadRewritten},
       (\x::$TypeExpr {typerepTypeExpr(e1.mtyperep, location=top.location)}
         y::$TypeExpr {typerepTypeExpr(monadInnerType(e2.mtyperep), location=top.location)} ->
        $Expr {monadReturn(e2.mtyperep, top.location)}
        (x + y))($Expr {e1.monadRewritten}, _))
    };
  top.monadRewritten = if isMonad(e1.mtyperep)
                       then if isMonad(e2.mtyperep)
                            then bindBoth
                            else bind1
                       else if isMonad(e2.mtyperep)
                            then bind2
                            else plus(e1.monadRewritten, '+', e2.monadRewritten, location=top.location);
}

aspect production minus
top::Expr ::= e1::Expr '-' e2::Expr
{
  top.merrors := e1.merrors ++ e2.merrors;

  top.mtyperep = if isMonad(e1.mtyperep)
                then e1.mtyperep
                else e2.mtyperep;

  --we assume both have the same monad, so we only need one return
  --e1 >>= ( (\x y -> y >>= \z -> Return(x - z))(_, e2) )
  local bindBoth::Expr =
    Silver_Expr {
      $Expr {monadBind(e2.mtyperep, top.location)}
      ($Expr {e1.monadRewritten},
       (\x::$TypeExpr {typerepTypeExpr(monadInnerType(e2.mtyperep), location=top.location)}
         y::$TypeExpr {typerepTypeExpr(e2.mtyperep, location=top.location)} ->
          $Expr {monadBind(e2.mtyperep, top.location)}
          (y,
           \z::$TypeExpr {typerepTypeExpr(monadInnerType(e2.mtyperep), location=top.location)} ->
            $Expr {monadReturn(e2.mtyperep, top.location)}
            (x - z))) (_, $Expr {e2.monadRewritten}))
    };
  --e1 >>= ( (\x y -> Return(x - y))(_, e2) )
  local bind1::Expr =
    Silver_Expr {
      $Expr {monadBind(e1.mtyperep, top.location)}
      ($Expr {e1.monadRewritten},
       (\x::$TypeExpr {typerepTypeExpr(monadInnerType(e1.mtyperep), location=top.location)}
         y::$TypeExpr {typerepTypeExpr(e2.mtyperep, location=top.location)} ->
        $Expr {monadReturn(e1.mtyperep, top.location)}
        (x - y))(_, $Expr {e2.monadRewritten}))
    };
  --e2 >>= ( (\x y -> Return(x - y))(e1, _) )
  local bind2::Expr =
    Silver_Expr {
      $Expr {monadBind(e2.mtyperep, top.location)}
      ($Expr {e2.monadRewritten},
       (\x::$TypeExpr {typerepTypeExpr(e1.mtyperep, location=top.location)}
         y::$TypeExpr {typerepTypeExpr(monadInnerType(e2.mtyperep), location=top.location)} ->
        $Expr {monadReturn(e2.mtyperep, top.location)}
        (x - y))($Expr {e1.monadRewritten}, _))
    };
  top.monadRewritten = if isMonad(e1.mtyperep)
                       then if isMonad(e2.mtyperep)
                            then bindBoth
                            else bind1
                       else if isMonad(e2.mtyperep)
                            then bind2
                            else minus(e1.monadRewritten, '-', e2.monadRewritten, location=top.location);
}

aspect production multiply
top::Expr ::= e1::Expr '*' e2::Expr
{
  top.merrors := e1.merrors ++ e2.merrors;

  top.mtyperep = if isMonad(e1.mtyperep)
                then e1.mtyperep
                else e2.mtyperep;

  --we assume both have the same monad, so we only need one return
  --e1 >>= ( (\x y -> y >>= \z -> Return(x * z))(_, e2) )
  local bindBoth::Expr =
    Silver_Expr {
      $Expr {monadBind(e2.mtyperep, top.location)}
      ($Expr {e1.monadRewritten},
       (\x::$TypeExpr {typerepTypeExpr(monadInnerType(e2.mtyperep), location=top.location)}
         y::$TypeExpr {typerepTypeExpr(e2.mtyperep, location=top.location)} ->
          $Expr {monadBind(e2.mtyperep, top.location)}
          (y,
           \z::$TypeExpr {typerepTypeExpr(monadInnerType(e2.mtyperep), location=top.location)} ->
            $Expr {monadReturn(e2.mtyperep, top.location)}
            (x * z))) (_, $Expr {e2.monadRewritten}))
    };
  --e1 >>= ( (\x y -> Return(x * y))(_, e2) )
  local bind1::Expr =
    Silver_Expr {
      $Expr {monadBind(e1.mtyperep, top.location)}
      ($Expr {e1.monadRewritten},
       (\x::$TypeExpr {typerepTypeExpr(monadInnerType(e1.mtyperep), location=top.location)}
         y::$TypeExpr {typerepTypeExpr(e2.mtyperep, location=top.location)} ->
        $Expr {monadReturn(e1.mtyperep, top.location)}
        (x * y))(_, $Expr {e2.monadRewritten}))
    };
  --e2 >>= ( (\x y -> Return(x * y))(e1, _) )
  local bind2::Expr =
    Silver_Expr {
      $Expr {monadBind(e2.mtyperep, top.location)}
      ($Expr {e2.monadRewritten},
       (\x::$TypeExpr {typerepTypeExpr(e1.mtyperep, location=top.location)}
         y::$TypeExpr {typerepTypeExpr(monadInnerType(e2.mtyperep), location=top.location)} ->
        $Expr {monadReturn(e2.mtyperep, top.location)}
        (x * y))($Expr {e1.monadRewritten}, _))
    };
  top.monadRewritten = if isMonad(e1.mtyperep)
                       then if isMonad(e2.mtyperep)
                            then bindBoth
                            else bind1
                       else if isMonad(e2.mtyperep)
                            then bind2
                            else multiply(e1.monadRewritten, '*', e2.monadRewritten, location=top.location);
}

aspect production divide
top::Expr ::= e1::Expr '/' e2::Expr
{
  top.merrors := e1.merrors ++ e2.merrors;

  top.mtyperep = if isMonad(e1.mtyperep)
                then e1.mtyperep
                else e2.mtyperep;

  --we assume both have the same monad, so we only need one return
  --e1 >>= ( (\x y -> y >>= \z -> Return(x / z))(_, e2) )
  local bindBoth::Expr =
    Silver_Expr {
      $Expr {monadBind(e2.mtyperep, top.location)}
      ($Expr {e1.monadRewritten},
       (\x::$TypeExpr {typerepTypeExpr(monadInnerType(e2.mtyperep), location=top.location)}
         y::$TypeExpr {typerepTypeExpr(e2.mtyperep, location=top.location)} ->
          $Expr {monadBind(e2.mtyperep, top.location)}
          (y,
           \z::$TypeExpr {typerepTypeExpr(monadInnerType(e2.mtyperep), location=top.location)} ->
            $Expr {monadReturn(e2.mtyperep, top.location)}
            (x / z))) (_, $Expr {e2.monadRewritten}))
    };
  --e1 >>= ( (\x y -> Return(x / y))(_, e2) )
  local bind1::Expr =
    Silver_Expr {
      $Expr {monadBind(e1.mtyperep, top.location)}
      ($Expr {e1.monadRewritten},
       (\x::$TypeExpr {typerepTypeExpr(monadInnerType(e1.mtyperep), location=top.location)}
         y::$TypeExpr {typerepTypeExpr(e2.mtyperep, location=top.location)} ->
        $Expr {monadReturn(e1.mtyperep, top.location)}
        (x / y))(_, $Expr {e2.monadRewritten}))
    };
  --e2 >>= ( (\x y -> Return(x / y))(e1, _) )
  local bind2::Expr =
    Silver_Expr {
      $Expr {monadBind(e2.mtyperep, top.location)}
      ($Expr {e2.monadRewritten},
       (\x::$TypeExpr {typerepTypeExpr(e1.mtyperep, location=top.location)}
         y::$TypeExpr {typerepTypeExpr(monadInnerType(e2.mtyperep), location=top.location)} ->
        $Expr {monadReturn(e2.mtyperep, top.location)}
        (x / y))($Expr {e1.monadRewritten}, _))
    };
  top.monadRewritten = if isMonad(e1.mtyperep)
                       then if isMonad(e2.mtyperep)
                            then bindBoth
                            else bind1
                       else if isMonad(e2.mtyperep)
                            then bind2
                            else divide(e1.monadRewritten, '/', e2.monadRewritten, location=top.location);
}

aspect production modulus
top::Expr ::= e1::Expr '%' e2::Expr
{
  top.merrors := e1.merrors ++ e2.merrors;

  top.mtyperep = if isMonad(e1.mtyperep)
                then e1.mtyperep
                else e2.mtyperep;

  --we assume both have the same monad, so we only need one return
  --e1 >>= ( (\x y -> y >>= \z -> Return(x % z))(_, e2) )
  local bindBoth::Expr =
    Silver_Expr {
      $Expr {monadBind(e2.mtyperep, top.location)}
      ($Expr {e1.monadRewritten},
       (\x::$TypeExpr {typerepTypeExpr(monadInnerType(e2.mtyperep), location=top.location)}
         y::$TypeExpr {typerepTypeExpr(e2.mtyperep, location=top.location)} ->
          $Expr {monadBind(e2.mtyperep, top.location)}
          (y,
           \z::$TypeExpr {typerepTypeExpr(monadInnerType(e2.mtyperep), location=top.location)} ->
            $Expr {monadReturn(e2.mtyperep, top.location)}
            (x % z))) (_, $Expr {e2.monadRewritten}))
    };
  --e1 >>= ( (\x y -> Return(x % y))(_, e2) )
  local bind1::Expr =
    Silver_Expr {
      $Expr {monadBind(e1.mtyperep, top.location)}
      ($Expr {e1.monadRewritten},
       (\x::$TypeExpr {typerepTypeExpr(monadInnerType(e1.mtyperep), location=top.location)}
         y::$TypeExpr {typerepTypeExpr(e2.mtyperep, location=top.location)} ->
        $Expr {monadReturn(e1.mtyperep, top.location)}
        (x % y))(_, $Expr {e2.monadRewritten}))
    };
  --e2 >>= ( (\x y -> Return(x % y))(e1, _) )
  local bind2::Expr =
    Silver_Expr {
      $Expr {monadBind(e2.mtyperep, top.location)}
      ($Expr {e2.monadRewritten},
       (\x::$TypeExpr {typerepTypeExpr(e1.mtyperep, location=top.location)}
         y::$TypeExpr {typerepTypeExpr(monadInnerType(e2.mtyperep), location=top.location)} ->
        $Expr {monadReturn(e2.mtyperep, top.location)}
        (x % y))($Expr {e1.monadRewritten}, _))
    };
  top.monadRewritten =  if isMonad(e1.mtyperep)
                       then if isMonad(e2.mtyperep)
                            then bindBoth
                            else bind1
                       else if isMonad(e2.mtyperep)
                            then bind2
                            else modulus(e1.monadRewritten, '%', e2.monadRewritten, location=top.location);
}

aspect production neg
top::Expr ::= '-' e::Expr
{
  top.merrors := e.merrors;

  top.mtyperep = e.mtyperep;

  top.monadRewritten =
    if isMonad(e.mtyperep)
    then Silver_Expr {
           $Expr {monadBind(e.mtyperep, top.location)}
            ($Expr {e.monadRewritten},
             \x::$TypeExpr {typerepTypeExpr(monadInnerType(e.mtyperep), location=top.location)} ->
              $Expr {monadReturn(e.mtyperep, top.location)}(-x))
         }
    else neg('-', e.monadRewritten, location=top.location);
}

aspect production stringConst
top::Expr ::= s::String_t
{
  top.merrors := [];
  top.mtyperep = stringType();

  top.monadRewritten = stringConst(s, location=top.location);
}

aspect production plusPlus
top::Expr ::= e1::Expr '++' e2::Expr
{
{-  local result_type :: Type = if isMonad(e1.mtyperep)
                              then performSubstitution(e1.mtyperep, errCheck1.upSubst)
                              else performSubstitution(e2.mtyperep, errCheck1.upSubst);

  -- Moved from 'analysis:typechecking' because we want to use this stuff here now
  local attribute errCheck1 :: TypeCheck; errCheck1.finalSubst = top.upSubst;

  e1.downSubst = top.downSubst;
  e2.downSubst = e1.upSubst;
  errCheck1.downSubst = e2.upSubst;
  forward.downSubst = monadMatchAndSubst.snd;
  -- upSubst defined via forward :D
  local attribute monadMatchAndSubst::Pair<Boolean Substitution>;
  monadMatchAndSubst = if isMonad(e1.mtyperep) && isMonad(e2.mtyperep)
                       then monadsMatch(e1.mtyperep, e2.mtyperep, errCheck1.upSubst)
                       else pair(true, errCheck1.upSubst);

  errCheck1 = check(if isMonad(e1.mtyperep)
                    then monadInnerType(e1.mtyperep)
                    else e1.mtyperep,
                    if isMonad(e2.mtyperep)
                    then monadInnerType(e2.mtyperep)
                    else e2.mtyperep);
  local errors::[Message] = (if errCheck1.typeerror
                             then [err(top.location, "Operands to ++ must be the same concatenable type or monads of the same type. Instead they are " ++ errCheck1.leftpp ++ " and " ++ errCheck1.rightpp)]
                             else []) ++
                            if monadMatchAndSubst.fst
                            then []
                            else [err(top.location, "Two monad operands to ++ must be the same monad.  Instead they are " ++ errCheck1.leftpp ++ " and " ++ errCheck1.rightpp)];

  forwards to
    -- if the types disagree, forward to an error production instead.
    if errCheck1.typeerror
    then errorExpr(errors, location=top.location)
    else if isMonad(top.mtyperep)
         then monadInnerType(top.mtyperep).appendDispatcher(e1, e2, top.location)
         else top.mtyperep.appendDispatcher(e1, e2, top.location);
-}
}

aspect production stringPlusPlus
top::Expr ::= e1::Decorated Expr   e2::Decorated Expr
{
  top.merrors := [];

  --we assume both types will be compatible (any other case would have
  --   been caught in plusPlus dispatching to this)
  top.mtyperep = if isMonad(e1.mtyperep)
                then e1.mtyperep
                else e2.mtyperep;
  top.monadRewritten = plusPlus(new(e1), '++', new(e2), location=top.location);
}

aspect production errorPlusPlus
top::Expr ::= e1::Decorated Expr e2::Decorated Expr
{
  local result_type :: Type = performSubstitution(e1.mtyperep, top.downSubst);

  top.merrors :=
    if result_type.isError then []
    else [err(e1.location, prettyType(result_type) ++ " is not a concatenable type.")];
  top.mtyperep = errorType();

  top.monadRewritten = plusPlus(new(e1), '++', new(e2), location=top.location);
}



synthesized attribute monadTypesLocations::[Pair<Type Integer>] occurs on AppExpr, AppExprs;
synthesized attribute realTypes::[Type] occurs on AppExpr, AppExprs;
attribute monadRewritten<AppExpr>, merrors occurs on AppExpr;
attribute monadRewritten<AppExprs>, merrors occurs on AppExprs;

-- These are the "new" Exprs syntax. This allows missing (_) arguments, to indicate partial application.
aspect production missingAppExpr
top::AppExpr ::= '_'
{
  top.merrors := [];
  top.monadRewritten = missingAppExpr('_', location=top.location);
  top.realTypes = [];
  top.monadTypesLocations = [];
}
aspect production presentAppExpr
top::AppExpr ::= e::Expr
{
  top.merrors := e.merrors;

  top.realTypes = [e.mtyperep];
  top.monadTypesLocations = if isMonadic
                            then [pair(e.mtyperep, top.appExprIndex+1)] --not sure if that's the right index
                            else [];

  --these have an 'a' at the end of their names because of a bug where local names are not local to their grammars
  local attribute errCheck1a :: TypeCheck; errCheck1a.finalSubst = top.upSubst;
  local attribute errCheck2a :: TypeCheck; errCheck2a.finalSubst = top.upSubst;

  errCheck1a.downSubst = e.upSubst;
  errCheck2a.downSubst = e.upSubst;
  --TODO:  Do I want to be able to use upSubst with monad stuff?
  --top.upSubst = if isMonadic
  --              then errCheck2a.upSubst
  --              else errCheck1a.upSubst;
  --determine whether it appears that this is supposed to take
  --   advantage of implicit monads based on types matching the
  --   expected and being monads
  local isMonadic::Boolean = if errCheck1a.typeerror
                             then if isMonad(e.mtyperep)
                                  then true
                                  else false
                             else false;

  errCheck1a = check(e.mtyperep, top.appExprTyperep);
  errCheck2a = check(monadInnerType(e.mtyperep), top.appExprTyperep);
  top.merrors <-
    if isMonadic
    then if !errCheck2a.typeerror
         then []
         else [err(top.location, "Argument " ++ toString(top.appExprIndex+1) ++ " of function '" ++
                top.appExprApplied ++ "' expected " ++ errCheck1a.rightpp ++
                " or a monad of " ++ errCheck1a.rightpp ++
                " but argument is of type " ++ errCheck1a.leftpp)]
    else
      if !errCheck1a.typeerror
      then []
      else [err(top.location, "Argument " ++ toString(top.appExprIndex+1) ++ " of function '" ++
                top.appExprApplied ++ "' expected " ++ errCheck1a.rightpp ++
                " or a monad of " ++ errCheck1a.rightpp ++
                " but argument is of type " ++ errCheck1a.leftpp)];

  top.monadRewritten = presentAppExpr(e.monadRewritten, location=top.location);
}

aspect production snocAppExprs
top::AppExprs ::= es::AppExprs ',' e::AppExpr
{
  top.merrors := es.merrors ++ e.merrors;

  top.realTypes = es.realTypes ++ e.realTypes;

  top.monadTypesLocations = es.monadTypesLocations ++ e.monadTypesLocations;

  top.monadRewritten = snocAppExprs(es.monadRewritten, ',', e.monadRewritten, location=top.location);
}
aspect production oneAppExprs
top::AppExprs ::= e::AppExpr
{
  top.merrors := if null(top.appExprTypereps)
                 then [err(top.location, "Too many arguments provided to function '" ++ top.appExprApplied ++ "'")]
                 else if length(top.appExprTypereps) > 1
                 then [err(top.location, "Too few arguments provided to function '" ++ top.appExprApplied ++ "'")]
                 else [];
  top.merrors <- e.merrors;

  top.realTypes = e.realTypes;

  top.monadTypesLocations = e.monadTypesLocations;

  top.monadRewritten = oneAppExprs(e.monadRewritten, location=top.location);
}
aspect production emptyAppExprs
top::AppExprs ::=
{
  -- Assumption: We only get here when we're looking at ()
  -- i.e. we can't ever have 'too many' provided error
  top.merrors := if null(top.appExprTypereps) then []
                 else [err(top.location, "Too few arguments provided to function '" ++ top.appExprApplied ++ "'")];

  top.realTypes = [];

  top.monadTypesLocations = [];

  top.monadRewritten = emptyAppExprs(location=top.location);
}


aspect production exprRef
top::Expr ::= e::Decorated Expr
{
  top.merrors := e.merrors;
  top.mtyperep = e.mtyperep;
  top.monadRewritten = e.monadRewritten;
}
