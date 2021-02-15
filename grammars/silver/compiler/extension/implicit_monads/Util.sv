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


--imports silver:compiler:extension:list;


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
  return case ty.baseType of
         | nonterminalType(name, _, _) ->
           (name == "silver:core:Maybe") ||
           (name == "silver:core:Either") ||
           (name == "silver:core:List") ||
           (name == "silver:core:IOMonad") ||
           (name == "silver:core:State")
         | decoratedType(t, _) -> isMonad(t)
         | _ -> false
         end;
}

function dropDecorated
Type ::= ty::Type
{
  return case ty of
         | decoratedType(t, _) -> t
         | listType(t) -> listType(t)
         | t -> t
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

{-this checks two types are the same monad, (assuming they are monads)
  though not necessarily the same monadic type (see discussion above)-}
function monadsMatch
Pair<Boolean Substitution> ::= ty1::Type ty2::Type subst::Substitution
{
  return case ty1, ty2 of
         | nonterminalType(name1, k1, _), nonterminalType(name2, k2, _) ->
           pair(name1 == name2 && k1 == k2 , subst)
         | appType(c1, a1), appType(c2, a2) -> tyMatch(c1, c2, subst)
         | listType(_), listType(_) -> pair(true, subst)
         | decoratedType(t, _), _ -> monadsMatch(t, ty2, subst)
         | _, decoratedType(t, _) -> monadsMatch(ty1, t, subst)
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


function tyMatch
Pair<Boolean Substitution> ::= t1::Type t2::Type subst::Substitution
{
  local tycheck::TypeCheck = check(t1, t2);
  tycheck.downSubst = subst;
  return pair(!tycheck.typeerror, tycheck.upSubst);
}


function monadInnerType
Type ::= mty::Type
{
  return case mty of
         | appType(c, a) -> a
         | listType(ty) -> ty
         | decoratedType(t, _) -> monadInnerType(t)
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
         | listType(_) -> listType(newInner)
         | appType(c, _) -> appType(c, newInner)
         | decoratedType(t, _) -> monadOfType(t, newInner)
         | _ -> error("Tried to take a monad out of a non-monadic type to apply")
         end;
}


--Print out the monad nicely rather than filled in with some other type
function monadToString
String ::= ty::Type
{
  return case ty of
         | listType(_) ->
           "[a]"
         | appType(nonterminalType("silver:core:Maybe", _, _), _) ->
           "Maybe<a>"
         | appType(appType(nonterminalType("silver:core:Either", _, _), p), _) ->
           "Either<" ++ prettyType(p) ++ " a>"
         | appType(nonterminalType("silver:core:IOMonad", _, _), _) ->
           "IOMonad<a>"
         | appType(appType(nonterminalType("silver:core:state", _, _), p), _) ->
           "State<" ++ prettyType(p) ++ " a>"
         | decoratedType(t, _) -> monadToString(t)
         | _ -> error("Tried to get monadToString for a non-monadic type")
         end;
}


{-find the name of the bind/return for a given monad to use to build
  the rewritten term-}
function monadBind
Expr ::= ty::Type l::Location
{
  return case ty.baseType of
         | nonterminalType("silver:core:Maybe", _, _) ->
           baseExpr(qNameId(name("bindMaybe", l), location=l), location=l)
         | nonterminalType("silver:core:Either", _, _) ->
           baseExpr(qNameId(name("bindEither", l), location=l), location=l)
         | nonterminalType("silver:core:IOMonad", _, _) ->
           baseExpr(qNameId(name("bindIO", l), location=l), location=l)
         | nonterminalType("silver:core:State", _, _) ->
           baseExpr(qNameId(name("bindState", l), location=l), location=l)
         | listType(_) ->
           baseExpr(qNameId(name("bindList", l), location=l), location=l)
         | decoratedType(t, _) -> monadBind(t, l)
         | _ -> error("Tried to get the bind for a non-monadic type at " ++ l.unparse)
         end;
}
function monadReturn
Expr ::= ty::Type l::Location
{
  return case decorate ty.baseType with {boundVariables = ty.freeVariables;} of
         | nonterminalType("silver:core:Maybe", _, _) ->
           baseExpr(qNameId(name("returnMaybe", l), location=l), location=l)
         | nonterminalType("silver:core:Either", _, _) ->
           baseExpr(qNameId(name("returnEither", l), location=l), location=l)
         | nonterminalType("silver:core:IOMonad", _, _) ->
           baseExpr(qNameId(name("returnIO", l), location=l), location=l)
         | nonterminalType("silver:core:State", _, _) ->
           baseExpr(qNameId(name("returnState", l), location=l), location=l)
         | listType(_) ->
           baseExpr(qNameId(name("returnList", l), location=l), location=l)
         | decoratedType(t, _) -> monadReturn(t, l)
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
  return
    case ty of
    | appType(appType(nonterminalType("silver:core:Either", _, _), a), b) ->
           case a of
           | stringType() -> right(Silver_Expr { silver:core:failEither($Expr{string}) })
           | intType() -> right(Silver_Expr { silver:core:failEither($Expr{int}) })
           | floatType() -> right(Silver_Expr { silver:core:failEither($Expr{float}) })
           | boolType() -> right(Silver_Expr { silver:core:failEither($Expr{bool}) })
           | listType(_) -> right(Silver_Expr { silver:core:failEither($Expr{list}) })
           | nonterminalType("silver:core:Unit", _, _) ->
             right(Silver_Expr { silver:core:failEither($Expr{unit}) })
           | _ -> left("Tried to get monadFail for too complex or generic an " ++
                       "argument type for Either (type " ++ prettyType(a) ++ "given; " ++
                       "must be int, float, bool, list, or unit)")
           end
           --baseExpr(qNameId(name("failEither", l), location=l), location=l)
   | _ -> case ty.baseType of
         | nonterminalType("silver:core:Maybe", _, _) ->
           right(Silver_Expr { silver:core:failMaybe($Expr{string}) })
           --baseExpr(qNameId(name("failMaybe", l), location=l), location=l)
         | nonterminalType("silver:core:IOMonad", _, _) ->
           left("Fail undefined for IOMonad")
           --error("Fail undefined for IOMonad")
         | nonterminalType("silver:core:State", _, _) ->
           left("Fail undefined for State monad")
           --error("Fail undefined for State monad")
         | listType(_) ->
           right(Silver_Expr { silver:core:failList($Expr{string}) })
           --baseExpr(qNameId(name("failList", l), location=l), location=l)
         | decoratedType(t, _) -> monadFail(t, l)
         | _ ->
           error("Tried to get the fail for a non-monadic type at " ++ l.unparse)
         end
    end;
}


function monadPlus
Either<String Expr> ::= ty::Type l::Location
{
  return case ty.baseType of
         | nonterminalType("silver:core:Maybe", _, _) ->
           right(baseExpr(qNameId(name("mplusMaybe", l), location=l), location=l))
         | nonterminalType("silver:core:Either", _, _) ->
           right(baseExpr(qNameId(name("mplusEither", l), location=l), location=l))
         | nonterminalType("silver:core:IOMonad", _, _) ->
           left("MPlus undefined for IOMonad")
         | nonterminalType("silver:core:State", _, _) ->
           left("MPlus undefined for State monad")
         | listType(_) ->
           right(baseExpr(qNameId(name("mplusList", l), location=l), location=l))
         | decoratedType(t, _) -> monadPlus(t, l)
         | _ ->
           error("Tried to get MPlus for a non-monadic type at " ++ l.unparse)
         end;
}
function monadZero
Either<String Expr> ::= ty::Type l::Location
{
  return
    case ty of
    | appType(appType(nonterminalType("silver:core:Either", _, _), a), b) ->
           case a of
           | stringType() -> right(Silver_Expr{ silver:core:left("mzero") })
           | intType() -> right(Silver_Expr{ silver:core:left(0) })
           | floatType() -> right(Silver_Expr{ silver:core:left(0.0) })
           | listType(_) -> right(Silver_Expr{ silver:core:left([]) })
           | _ ->
             left("Cannot get MZero for Either with too complex or too generic argument type (" ++
                   prettyType(ty) ++ ")")
           end
    | _ -> case ty.baseType of
         | nonterminalType("silver:core:Maybe", _, _) ->
           right(Silver_Expr { silver:silver:core:nothing() })
         | nonterminalType("silver:core:IOMonad", _, _) ->
           left("MZero undefined for IOMonad")
         | nonterminalType("silver:core:State", _, _) ->
           left("MZero undefined for State monad")
         | listType(_) ->
           right(Silver_Expr { [] })
         | decoratedType(t, _) -> monadZero(t, l)
         | _ ->
           error("Tried to get MZero for a non-monadic type at " ++ l.unparse)
         end
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

