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


imports silver:extension:list;


synthesized attribute monadRewritten<a>::a;


function isMonad
Boolean ::= ty::Type
{
  return case ty of
         | nonterminalType(name, params) ->
           (name == "core:Maybe" && length(params) == 1) ||
           (name == "core:Either" && length(params) == 2) ||
           (name == "core:IOMonad" && length(params) == 1) ||
           (name == "core:State" && length(params) == 2) --||
           --(name == "listType" && length(params) == 1)
         | listType(_) -> true
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
         | listType(_), listType(_) -> pair(true, subst)
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
         | _ -> error("Tried to get the bind for a non-monadic type")
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
         | _ -> error("Tried to get the return for a non-monadic type " ++ l.filename ++ " " ++ toString(l.line) ++ ":" ++ toString(l.column))
         end;
}



aspect production compilation
top::Compilation ::= g::Grammars  _  buildGrammar::String  benv::BuildEnv
{
  top.postOps <- [genOut(grammarsForFinalTranslation)];
}

abstract production genOut
top::DriverAction ::= specs::[Decorated RootSpec]
{
  local file :: String = "monad/monad_out.sv";

  local str :: String = case head(specs) of
                        | grammarRootSpec(consGrammar(r, _), _, _, _, _) -> r.unparse
                        end;

  local str2 :: String =
  s"""
grammar monad;

function bindEither
Either<a c> ::= m::Either<a b> fn::(Either<a c> ::= b)
{
  return case m of
    left(x) -> left(x)
  | right(x) -> fn(x)
  end;
}

function returnEither
Either<a b> ::= x::b
{
  return right(x);
}

{- Need to figure out what to do with this since this is a production
abstract production bindIO
top::IOMonad<b> ::= st::IOMonad<a> fn::(IOMonad<b> ::= a)
{
  st.stateIn = top.stateIn;
  local newState::IOMonad<b> = fn(st.stateVal);
  newState.stateIn = st.stateOut;
  local stateOut::IO = newState.stateOut;
  local stateVal::b = newState.stateVal;
  
  -- Using unsafeTrace here to demand st is evaluated before evaluating fn
  top.stateOut = unsafeTrace(stateOut, st.stateOut);
  top.stateVal = unsafeTrace(stateVal, st.stateOut);
}

abstract production returnIO
top::IOMonad<a> ::= x::a
{
  top.stateOut = top.stateIn;
  top.stateVal = x;
}-}


function bindList
[b] ::= l::[a] fn::([b] ::= a)
{
  return flatMap(fn, l); --Do we need to add flatMap in?
}

function returnList
[a] ::= x::a
{
  return [x];
}


function bindMaybe
Maybe<b> ::= m::Maybe<a> fn::(Maybe<b> ::= a)
{
  return case m of
    just(x) -> fn(x)
  | nothing() -> nothing()
  end;
}

--global returnMaybe::(Maybe<a> ::= a) = just; -- TODO: Why doesn't this work?
function returnMaybe
Maybe<a> ::= x::a
{
  return just(x);
}

{- Need to figure out what to do with this since this is a production
abstract production bindState
top::State<s b> ::= st::State<s a> fn::(State<s b> ::= a)
{
  st.stateIn = top.stateIn;
  local newState::State<s b> = fn(st.stateVal);
  newState.stateIn = st.stateOut;
  top.stateOut = newState.stateOut;
  
  top.stateVal = newState.stateVal;
}

abstract production returnState
top::State<s a> ::= x::a
{
  top.stateOut = top.stateIn;
  top.stateVal = x;
}-}
--""" ++ str;

  local err :: IO =
    print("Errors while Generating Monad Out " ++ "\n", top.ioIn);

  local doWR :: IO =
    writeFile(file, str2,
      print(s"Generating Monad Out.\n", top.ioIn));

  top.io =
    if null(specs)
    then print("Did not find a grammar for which to generate Monad Out.\n", top.ioIn)
    else doWR;

  top.code = 1;
  top.order = 10;
}

