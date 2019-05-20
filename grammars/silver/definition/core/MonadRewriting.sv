grammar silver:definition:core;

{-
  I don't really know where to put this, but I'm sure Lucas will have
  an opinion on where it should go, so I'll ask him before the final
  product is done.
-}


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


synthesized attribute monadRewritten<a>::a;


function isMonad
Boolean ::= ty::Type
{
  return case ty of
         | nonterminalType(name, params) ->
           (name == "core:Maybe" && length(params) == 1) ||
           (name == "core:Either" && length(params) == 2) ||
           (name == "core:IOMonad" && length(params) == 1) ||
           (name == "core:State" && length(params) == 2) ||
           (name == "core:List" && length(params) == 1)
         | _ -> false
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
         | _ -> error("Tried to take a monad out of a non-monadic " ++
                      "type to apply")
         end;
}


{-find the name of the bind/return for a given monad to use to build
  the rewritten term-}
function monadBind
Expr ::= ty::Type l::Location
{
  return case ty of
         | nonterminalType("Maybe", _) ->
           baseExpr(qNameId(name("bindMaybe", l), location=l), location=l)
         | nonterminalType("Either", _) -> 
           baseExpr(qNameId(name("bindEither", l), location=l), location=l)
         | nonterminalType("IOMonad", _) -> 
           baseExpr(qNameId(name("bindIO", l), location=l), location=l)
         | nonterminalType("State", _) -> 
           baseExpr(qNameId(name("bindState", l), location=l), location=l)
         | nonterminalType("List", _) -> 
           baseExpr(qNameId(name("bindList", l), location=l), location=l)
         | _ -> error("Tried to get the bind for a non-monadic type")
         end;
}
function monadReturn
Expr ::= ty::Type l::Location
{
  return case ty of
         | nonterminalType("Maybe", _) ->
           baseExpr(qNameId(name("returnMaybe", l), location=l), location=l)
         | nonterminalType("Either", _) -> 
           baseExpr(qNameId(name("returnEither", l), location=l), location=l)
         | nonterminalType("IOMonad", _) -> 
           baseExpr(qNameId(name("returnIO", l), location=l), location=l)
         | nonterminalType("State", _) -> 
           baseExpr(qNameId(name("returnState", l), location=l), location=l)
         | nonterminalType("List", _) -> 
           baseExpr(qNameId(name("returnList", l), location=l), location=l)
         | _ -> error("Tried to get the return for a non-monadic type")
         end;
}
