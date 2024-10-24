grammar silver:compiler:extension:implicit_monads;



{-
  EXPLANATION OF OUR VIEW OF A MONAD

  We assume our monads have their "inner type" as the last parameter
  to their constructor (e.g. Either<String Type> is a monad over Type,
  not String).

  For two monad types to be the same, all their parameters must be the
  same.  For two monads to be the same, their non-monad parameters
  must be the same.  For example, Either<String Type> and
  Either<String Expr> have the same monad (Either<String a>) but are
  not the same type.  Either<String Type> and Either<Type Type> do not
  have the same monad.
-}


--The monad of the attribute the equation for which we are rewriting
inherited attribute expectedMonad::Type;
--The rewritten version of the current expression, exprs, etc.
synthesized attribute monadRewritten<a>::a;
--Errors encountered in rewriting
synthesized attribute merrors::[Message] with ++;
--The type in the expression based on implicit monads
--We can't just use the normal typerep because it depends on the implicit monads
synthesized attribute mtyperep::Type;


-- TODO: There's lots of places where we can't automatically propagate these because
-- the host downSubst/upSubst attributes are mixed in too. 
threaded attribute mDownSubst, mUpSubst::Substitution;


fun isMonad Boolean ::= ty::Type env::Env =
  case dropDecorated(ty) of
  | appType(t, _) -> length(getInstanceDcl("silver:core:Monad", ^t, env)) > 0
  | t -> length(getInstanceDcl("silver:core:Monad", t, env)) > 0
  end;

fun isMonadPlus Boolean ::= ty::Type env::Env =
  case dropDecorated(ty) of
  | appType(t, _) -> length(getInstanceDcl("silver:core:MonadPlus", ^t, env)) > 0
  | t -> length(getInstanceDcl("silver:core:MonadPlus", t, env)) > 0
  end;

fun isMonadFail Boolean ::= ty::Type env::Env =
  case dropDecorated(ty) of
  | appType(t, _) -> length(getInstanceDcl("silver:core:MonadFail", ^t, env)) > 0
  | t -> length(getInstanceDcl("silver:core:MonadFail", t, env)) > 0
  end;


function dropDecorated
Type ::= ty::Type
{
  return if ty.isDecorated then ty.decoratedType else ^ty;
}


{-This checks two types are the same monad, (assuming they are monads)
  though not necessarily the same monadic type (see discussion above)-}
fun monadsMatch Pair<Boolean Substitution> ::= ty1::Type ty2::Type subst::Substitution =
  case dropDecorated(ty1), dropDecorated(ty2) of
  | appType(c1, _), appType(c2, _) ->
    tyMatch(^c1, ^c2, subst)
  | _, _ -> (false, subst)
  end;


{-This is the easiest way to get case_any translation working.  We
  would be better off getting the error checking to occur prior to
  rewriting so these functions don't show up.

  We also need to use this because we occasionally need to use new to
  drop the decoration from the type of things we're passing into
  binds.-}
fun acceptableMonadFunction Boolean ::= f::Decorated Expr with {forward} =
  case f of
  | functionReference(qNameId(name)) ->
    name.name == "silver:core:alt"
  | _ -> false
  end;


function tyMatch
Pair<Boolean Substitution> ::= t1::Type t2::Type subst::Substitution
{
  local tycheck::TypeCheck = check(@t1, @t2);
  tycheck.downSubst = subst;
  return (!tycheck.typeerror, tycheck.upSubst);
}


function monadInnerType
Type ::= mty::Type
{
  local loc::Location = getParsedOriginLocationOrFallback(ambientOrigin());
  return case dropDecorated(^mty) of
         | appType(c, a) -> ^a
         | _ -> error("The monadInnerType function should only be called " ++
                      "once a type has been verified to be a monad (" ++
                      prettyType(^mty) ++ " at " ++  loc.unparse ++ ")")
         end;
}


{-take the monad of mty and replace its inner type with the given type
  to make a new monadic type-}
fun monadOfType Type ::= mty::Type newInner::Type =
  case dropDecorated(mty) of
  | appType(c, _) -> appType(^c, newInner)
  | _ -> error("Tried to take a monad out of a non-monadic type (" ++ prettyType(mty) ++ ") to apply")
  end;


--Print out the monad nicely rather than filled in with some other type
fun monadToString String ::= ty::Type =
  case dropDecorated(ty) of
  | appType(c, _) ->
    --We use nonterminalType to get it to show just an underscore
    --e.g. this gives us Maybe<_>, Either<String _>
    prettyType(appType(^c, nonterminalType("_", [], false, false)))
  | _ -> error("Tried to get monadToString for a non-monadic type (" ++ prettyType(ty) ++ ")")
  end;


{-find the name of the bind/return for a given monad to use to build
  the rewritten term-}
fun monadBind Expr ::= = baseExpr(qNameId(name("silver:core:bind")));
fun monadReturn Expr ::= = baseExpr(qNameId(name("silver:core:pure")));

--We want to produce a value, not a function, so we apply it to an argument
function monadFail
Expr ::=
{
  local loc::Location = getParsedOriginLocationOrFallback(ambientOrigin());
  return
     buildApplication
       (baseExpr(qNameId(name("silver:core:fail"))),
        [stringConst(terminal(String_t, "\"Automatically-inserted fail at " ++
                                           loc.unparse ++ "\""))]);
}


fun monadPlus Expr ::= = baseExpr(qNameId(name("silver:core:alt")));
fun monadZero Expr ::= = baseExpr(qNameId(name("silver:core:empty")));





{-
  Some functions to build common structures to make rewriting easier.
  By using these instead of Silver_Expr {...}, we can get actual locations where errors occur.
  These can also be easier to read because we don't have all the "$Expr"s and "Silver_Expr"s around.
-}

fun buildApplication Expr ::= fun::Expr args::[Expr] =
  applicationExpr(fun, '(', buildApplicationReverseArgs(reverse(args)), ')');

--because the AST is set up as a snoc list, we build the arguments in reverse
--e.g. [a,b,c] gives application arguments (c, b, a)
fun buildApplicationReverseArgs AppExprs ::= args::[Expr] =
  case args of
  | [] -> emptyAppExprs()
  | hd::tl ->
    snocAppExprs(buildApplicationReverseArgs(tl), ',',
                 presentAppExpr(hd))
  end;



fun buildLambda Expr ::= n::String ty::Type body::Expr =
  lambdap(
    lambdaRHSCons(lambdaRHSElemIdTy(name(n),
                                        '::',
                                        typerepTypeExpr(ty)),
                      lambdaRHSNil()),
    body);


function buildMultiLambda
Expr ::= names::[Pair<String Type>] body::Expr
{
  nondecorated local sig::LambdaRHS =
    foldr(\ pr::Pair<String Type> p::LambdaRHS ->
            case pr of
            | (n, ty) ->
              lambdaRHSCons(lambdaRHSElemIdTy(name(n), '::',
                                   typerepTypeExpr(ty)),
                                p)
            end,
          lambdaRHSNil(), names);
  return lambdap(sig, ^body);
}

