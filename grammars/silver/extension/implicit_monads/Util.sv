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


synthesized attribute monadRewritten<a>::a;
synthesized attribute merrors::[Message] with ++;
synthesized attribute mtyperep::Type;
autocopy attribute mDownSubst::Substitution;
synthesized attribute mUpSubst::Substitution;


function isMonad
Boolean ::= ty::Type
{
  return case ty of
         | nonterminalType(name, params) ->
           (name == "core:Maybe" && length(params) == 1) ||
           (name == "core:Either" && length(params) == 2) ||
           (name == "core:IOMonad" && length(params) == 1) ||
           (name == "core:State" && length(params) == 2)
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


function monadToString
String ::= ty::Type
{
  return case ty of
         | nonterminalType("core:Maybe", _) ->
           "Maybe<a>"
         | nonterminalType("core:Either", [p, a]) ->
           "Either<" ++ prettyType(p) ++ " a>"
         | nonterminalType("core:IOMonad", _) ->
           "IOMonad<a>"
         | nonterminalType("core:State", [p, a]) ->
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
         | nonterminalType("core:IOMonad", _) ->
           baseExpr(qNameId(name("bindIO", l), location=l), location=l)
         | nonterminalType("core:State", _) ->
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
  return case ty of
         | nonterminalType("core:Maybe", _) ->
           baseExpr(qNameId(name("returnMaybe", l), location=l), location=l)
         | nonterminalType("core:Either", _) ->
           baseExpr(qNameId(name("returnEither", l), location=l), location=l)
         | nonterminalType("core:IOMonad", _) ->
           baseExpr(qNameId(name("returnIO", l), location=l), location=l)
         | nonterminalType("core:State", _) ->
           baseExpr(qNameId(name("returnState", l), location=l), location=l)
         | listType(_) ->
           baseExpr(qNameId(name("returnList", l), location=l), location=l)
         | decoratedType(t) -> monadReturn(t, l)
         | _ -> error("Tried to get the return for a non-monadic type at " ++ l.unparse)
         end;
}
function monadFailExists
Boolean ::= ty::Type
{
  return case ty of
         | nonterminalType("core:Maybe", _) -> true
         | nonterminalType("core:Either", _) -> true
         | nonterminalType("core:IOMonad", _) -> false
         | nonterminalType("core:State", _) -> false
         | listType(_) -> true
         | decoratedType(t) -> monadFailExists(t)
         | _ -> false
         end;
}
{-
  TODO monadFailExists, monadFail, and monadFailArgument should combine into a
  single function return type Maybe<Expr> that returns nothing() if there is no
  fail and just(fail(arg)) if the fail is defined.  This is not happening now
  due to time constraints.
-}
function monadFail
Expr ::= ty::Type l::Location
{
  return case ty of
         | nonterminalType("core:Maybe", _) ->
           baseExpr(qNameId(name("failMaybe", l), location=l), location=l)
         | nonterminalType("core:Either", _) ->
           baseExpr(qNameId(name("failEither", l), location=l), location=l)
         | nonterminalType("core:IOMonad", _) ->
           error("Fail undefined for IOMonad")
         | nonterminalType("core:State", _) ->
           error("Fail undefined for State monad")
         | listType(_) ->
           baseExpr(qNameId(name("failList", l), location=l), location=l)
         | decoratedType(t) -> monadFail(t, l)
         | _ ->
           error("Tried to get the fail for a non-monadic type at " ++ l.unparse)
         end;
}
--come up with a "generic" argument for the call to Fail() if it is one of
--   a small set of basic types
function monadFailArgument
Maybe<Expr> ::= ty::Type l::Location
{
  local string::Expr =
     stringConst(terminal(String_t,
             "\"automatically-inserted fail at " ++ l.unparse ++ "\""),
             location=l);
  local int::Expr = Silver_Expr { 0 };
  local float::Expr = Silver_Expr { 0.0 };
  local bool::Expr = Silver_Expr { false };
  local list::Expr = Silver_Expr { [] };
  return case ty of
         | nonterminalType("core:Maybe", _) -> just(string)
         | nonterminalType("core:Either", [a, b]) ->
           case a of
           | stringType() -> just(string)
           | intType() -> just(int)
           | floatType() -> just(float)
           | boolType() -> just(bool)
           | listType(_) -> just(list)
           | _ -> nothing()
           end
         | listType(_) -> just(string)
         | _ -> nothing()
         end;
}

{-
  TODO these should probably return Maybe<Expr> rather than erroring on monads
  for which it's undefined, then let the places calling them create errors to
  go in merrors
-}
function monadPlus
Expr ::= ty::Type l::Location
{
  return case ty of
         | nonterminalType("core:Maybe", _) ->
           baseExpr(qNameId(name("mplusMaybe", l), location=l), location=l)
         | nonterminalType("core:Either", _) ->
           baseExpr(qNameId(name("mplusEither", l), location=l), location=l)
         | nonterminalType("core:IOMonad", _) ->
           error("MPlus undefined for IOMonad")
         | nonterminalType("core:State", _) ->
           error("MPlus undefined for State monad")
         | listType(_) ->
           baseExpr(qNameId(name("mplusList", l), location=l), location=l)
         | decoratedType(t) -> monadPlus(t, l)
         | _ ->
           error("Tried to get MPlus for a non-monadic type at " ++ l.unparse)
         end;
}
function monadZero
Expr ::= ty::Type l::Location
{
  return case ty of
         | nonterminalType("core:Maybe", _) ->
           Silver_Expr { nothing() }
         | nonterminalType("core:Either", [a, b]) ->
           case a of
           | stringType() -> Silver_Expr{ $Expr{monadFail(ty, l)}("mzero") }
           | intType() -> Silver_Expr{ $Expr{monadFail(ty, l)}(0) }
           | floatType() -> Silver_Expr{ $Expr{monadFail(ty, l)}(0.0) }
           | listType(_) -> Silver_Expr{ $Expr{monadFail(ty, l)}([]) }
           | _ ->
             error("Tried to get MZero for Either with too complex or too generic argument type (" ++
                   prettyType(ty) ++ ") at " ++ l.unparse)
           end
         | nonterminalType("core:IOMonad", _) ->
           error("MZero undefined for IOMonad")
         | nonterminalType("core:State", _) ->
           error("MZero undefined for State monad")
         | listType(_) ->
           Silver_Expr { [] }
         | decoratedType(t) -> monadZero(t, l)
         | _ ->
           error("Tried to get MZero for a non-monadic type at " ++ l.unparse)
         end;
}

--We can't do mcase when we don't have mplus/mzero defined, so check if something
--   of a given type can be expanded with these
function canBeMCased
Boolean ::= mty::Type
{
  return case mty of
         | nonterminalType("core:Maybe", _) -> true
         | nonterminalType("core:Either", _) -> true
         | listType(_) -> true
         | decoratedType(t) -> canBeMCased(t)
         | _ -> false
         end;
}

