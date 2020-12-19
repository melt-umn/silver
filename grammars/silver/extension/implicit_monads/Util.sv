grammar silver:extension:implicit_monads;



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


--imports silver:extension:list;


autocopy attribute expectedMonad::Type;
synthesized attribute monadRewritten<a>::a;
synthesized attribute merrors::[Message] with ++;
synthesized attribute mtyperep::Type;

-- TODO: There's lots of places where we can't automatically propagate these because
-- the host downSubst/upSubst attributes are mixed in too. 
threaded attribute mDownSubst, mUpSubst::Substitution;


function isMonad
Boolean ::= ty::Type
{
  return case ty of
         | nonterminalType(name, params) ->
           (name == "core:Maybe" && length(params) == 1) ||
           (name == "core:Either" && length(params) == 2) ||
           (name == "core:monad:IOMonad" && length(params) == 1) ||
           (name == "core:monad:State" && length(params) == 2)
         | listType(_) -> true
         | decoratedType(t) -> isMonad(t)
         | _ -> false
         end;
}


{-
  Since we're translating before doing most error checking, we want to
  avoid translating if we have an error type to make errors easier to
  trace back to their original location, so we need a way to check for
  that.
-}
function isError
Boolean ::= ty::Type
{
  return case ty of
         | errorType() -> true
         | _ -> false
         end;
}

function dropDecorated
Type ::= ty::Type
{
  return case ty of
         | decoratedType(t) -> t
         | listType(t) -> listType(t)
         | t -> t
         end;
}

function isDecorated
Boolean ::= ty::Type
{
  return case ty of
         | decoratedType(t) -> true
         | t -> false
         end;
}


{-this checks two types are the same monad, (assuming they are monads)
  though not necessarily the same monadic type (see discussion above)-}
function monadsMatch
Pair<Boolean Substitution> ::= ty1::Type ty2::Type subst::Substitution
{
  return case ty1, ty2 of
         | nonterminalType(name1, params1), nonterminalType(name2, params2) ->
           if name1 == name2 && length(params1) == length(params2)
           then tyListMatch(init(params1), init(params2), subst)
           else pair(false, subst)
         | listType(_), listType(_) -> pair(true, subst)
         | decoratedType(t), _ -> monadsMatch(t, ty2, subst)
         | _, decoratedType(t) -> monadsMatch(ty1, t, subst)
         | _, _ -> pair(false, subst)
         end;
}


{-This is the easiest way to get case_all translation working.  We
  would be better off getting the error checking to occur prior to
  rewriting so these functions don't show up.-}
function acceptableMonadFunction
Boolean ::= f::Decorated Expr
{
  return case f of
         | functionReference(qNameId(name)) ->
           case name.name of
           | "mplusMaybe" -> true
           | "mplusEither" -> true
           | "mplusList" -> true
           | _ -> false
           end
         | _ -> false
         end;
}


{-this assumes the lists have the same length-}
function tyListMatch
Pair<Boolean Substitution> ::= tl1::[Type] tl2::[Type] subst::Substitution
{
  local tycheck::TypeCheck = check(head(tl1), head(tl2));
  tycheck.downSubst = subst;
  return if length(tl1) == 0
         then pair(true, subst)
         else if tycheck.typeerror
              then pair(false, subst)
              else tyListMatch(tail(tl1), tail(tl2), tycheck.upSubst);
}


function monadInnerType
Type ::= mty::Type
{
  return case mty of
         | nonterminalType(name1, params1) ->
           last(params1)
         | listType(ty) -> ty
         | decoratedType(t) -> monadInnerType(t)
         | _ -> error("The monadInnerType function should only be called " ++
                      "once a type has been verified to be a monad")
         end;
}


{-take the monad of mty and replace its inner type with the given type
  to make a new monadic type-}
function monadOfType
Type ::= mty::Type newInner::Type
{
  return case mty of
         | nonterminalType(name, params) ->
           nonterminalType(name, append(init(params), [newInner]))
         | listType(_) -> listType(newInner)
         | decoratedType(t) -> monadOfType(t, newInner)
         | _ -> error("Tried to take a monad out of a non-monadic type to apply")
         end;
}


--Print out the monad nicely rather than filled in with some other type
function monadToString
String ::= ty::Type
{
  return case ty of
         | nonterminalType("core:Maybe", _) ->
           "Maybe<a>"
         | nonterminalType("core:Either", [p, a]) ->
           "Either<" ++ prettyType(p) ++ " a>"
         | nonterminalType("core:monad:IOMonad", _) ->
           "IOMonad<a>"
         | nonterminalType("core:monad:State", [p, a]) ->
           "State<" ++ prettyType(p) ++ " a>"
         | listType(_) ->
           "[a]"
         | decoratedType(t) -> monadToString(t)
         | _ -> error("Tried to get monadToString for a non-monadic type")
         end;
}


{-find the name of the bind/return for a given monad to use to build
  the rewritten term-}
function monadBind
Expr ::= ty::Type l::Location
{
  return case ty of
         | nonterminalType("core:Maybe", _) ->
           baseExpr(qNameId(name("bindMaybe", l), location=l), location=l)
         | nonterminalType("core:Either", _) ->
           baseExpr(qNameId(name("bindEither", l), location=l), location=l)
         | nonterminalType("core:monad:IOMonad", _) ->
           baseExpr(qNameId(name("bindIO", l), location=l), location=l)
         | nonterminalType("core:monad:State", _) ->
           baseExpr(qNameId(name("bindState", l), location=l), location=l)
         | listType(_) ->
           baseExpr(qNameId(name("bindList", l), location=l), location=l)
         | decoratedType(t) -> monadBind(t, l)
         | _ -> error("Tried to get the bind for a non-monadic type at " ++ l.unparse)
         end;
}
function monadReturn
Expr ::= ty::Type l::Location
{
  return case decorate ty with {boundVariables = ty.freeVariables;} of
         | nonterminalType("core:Maybe", _) ->
           baseExpr(qNameId(name("returnMaybe", l), location=l), location=l)
         | nonterminalType("core:Either", _) ->
           baseExpr(qNameId(name("returnEither", l), location=l), location=l)
         | nonterminalType("core:monad:IOMonad", _) ->
           baseExpr(qNameId(name("returnIO", l), location=l), location=l)
         | nonterminalType("core:monad:State", _) ->
           baseExpr(qNameId(name("returnState", l), location=l), location=l)
         | listType(_) ->
           baseExpr(qNameId(name("returnList", l), location=l), location=l)
         | decoratedType(t) -> monadReturn(t, l)
         | _ -> error("Tried to get the return for a non-monadic type (" ++ prettyType(ty) ++ ") at " ++ l.unparse)
         end;
}

--Return right of an expression suitable for monad fail for the given type if
--   it exists or left of an error message if it fails
function monadFail
Either<String Expr> ::= ty::Type l::Location
{
  local string::Expr =
     stringConst(terminal(String_t,
             "\"automatically-inserted fail at " ++ l.unparse ++ "\""),
             location=l);
  local int::Expr = Silver_Expr { 0 };
  local float::Expr = Silver_Expr { 0.0 };
  local bool::Expr = Silver_Expr { false };
  local list::Expr = Silver_Expr { [] };
  local unit::Expr = Silver_Expr { unit() };
  return case ty of
         | nonterminalType("core:Maybe", _) ->
           right(Silver_Expr { core:monad:failMaybe($Expr{string}) })
           --baseExpr(qNameId(name("failMaybe", l), location=l), location=l)
         | nonterminalType("core:Either", [a, b]) ->
           case a of
           | stringType() -> right(Silver_Expr { core:monad:failEither($Expr{string}) })
           | intType() -> right(Silver_Expr { core:monad:failEither($Expr{int}) })
           | floatType() -> right(Silver_Expr { core:monad:failEither($Expr{float}) })
           | boolType() -> right(Silver_Expr { core:monad:failEither($Expr{bool}) })
           | listType(_) -> right(Silver_Expr { core:monad:failEither($Expr{list}) })
           | nonterminalType("core:Unit", _) ->
             right(Silver_Expr { core:monad:failEither($Expr{unit}) })
           | _ -> left("Tried to get monadFail for too complex or generic an " ++
                       "argument type for Either (type " ++ prettyType(a) ++ "given; " ++
                       "must be int, float, bool, list, or unit)")
           end
           --baseExpr(qNameId(name("failEither", l), location=l), location=l)
         | nonterminalType("core:monad:IOMonad", _) ->
           left("Fail undefined for IOMonad")
           --error("Fail undefined for IOMonad")
         | nonterminalType("core:monad:State", _) ->
           left("Fail undefined for State monad")
           --error("Fail undefined for State monad")
         | listType(_) ->
           right(Silver_Expr { core:monad:failList($Expr{string}) })
           --baseExpr(qNameId(name("failList", l), location=l), location=l)
         | decoratedType(t) -> monadFail(t, l)
         | _ ->
           error("Tried to get the fail for a non-monadic type at " ++ l.unparse)
         end;
}


function monadPlus
Either<String Expr> ::= ty::Type l::Location
{
  return case ty of
         | nonterminalType("core:Maybe", _) ->
           right(baseExpr(qNameId(name("mplusMaybe", l), location=l), location=l))
         | nonterminalType("core:Either", _) ->
           right(baseExpr(qNameId(name("mplusEither", l), location=l), location=l))
         | nonterminalType("core:monad:IOMonad", _) ->
           left("MPlus undefined for IOMonad")
         | nonterminalType("core:monad:State", _) ->
           left("MPlus undefined for State monad")
         | listType(_) ->
           right(baseExpr(qNameId(name("mplusList", l), location=l), location=l))
         | decoratedType(t) -> monadPlus(t, l)
         | _ ->
           error("Tried to get MPlus for a non-monadic type at " ++ l.unparse)
         end;
}
function monadZero
Either<String Expr> ::= ty::Type l::Location
{
  return case ty of
         | nonterminalType("core:Maybe", _) ->
           right(Silver_Expr { core:monad:nothing() })
         | nonterminalType("core:Either", [a, b]) ->
           case a of
           | stringType() -> right(Silver_Expr{ core:monad:left("mzero") })
           | intType() -> right(Silver_Expr{ core:monad:left(0) })
           | floatType() -> right(Silver_Expr{ core:monad:left(0.0) })
           | listType(_) -> right(Silver_Expr{ core:monad:left([]) })
           | _ ->
             left("Cannot get MZero for Either with too complex or too generic argument type (" ++
                   prettyType(ty) ++ ")")
           end
         | nonterminalType("core:monad:IOMonad", _) ->
           left("MZero undefined for IOMonad")
         | nonterminalType("core:monad:State", _) ->
           left("MZero undefined for State monad")
         | listType(_) ->
           right(Silver_Expr { [] })
         | decoratedType(t) -> monadZero(t, l)
         | _ ->
           error("Tried to get MZero for a non-monadic type at " ++ l.unparse)
         end;
}





{-
  Some functions to build common structures to make rewriting easier.
  By using these instead of Silver_Expr {...}, we can get actual locations where errors occur.
-}

function buildApplication
Expr ::= fun::Expr args::[Expr] loc::Location
{
  return applicationExpr(fun, '(', buildApplicationReverseArgs(reverse(args), loc), ')', location=loc);
}

--because the AST is set up as a snoc list, we build the arguments in reverse
--e.g. [a,b,c] gives application arguments (c, b, a)
function buildApplicationReverseArgs
AppExprs ::= args::[Expr] loc::Location
{
  return case args of
         | [] -> emptyAppExprs(location=loc)
         | hd::tl ->
           snocAppExprs(buildApplicationReverseArgs(tl, loc), ',',
                        presentAppExpr(hd, location=loc), location=loc)
         end;
}



function buildLambda
Expr ::= n::String ty::Type body::Expr loc::Location
{
  -- \ n::ty -> body
  return lambdap(
           productionRHSCons(productionRHSElem(name(n, loc),
                                               '::',
                                               typerepTypeExpr(ty, location=loc),
                                               location=loc),
                             productionRHSNil(location=loc),
                             location=loc),
           body,
           location=loc);
}

