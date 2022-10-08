grammar silver:core;

synthesized attribute fromLeft<a> :: a;
synthesized attribute fromRight<a> :: a;
synthesized attribute isLeft :: Boolean;
synthesized attribute isRight :: Boolean;

@{--
 - The basic sum type, counterpart to Pair.
 -
 - Occasionally used as a poor-quality "result or error" type.
 - By convention, the error type is the FIRST type, and the 
 - expected return value is the second.
 - e.g. `Either<String Tree>`
 -
 - Inspect it's state with isLeft::Boolean and isRight::Boolean, and
 - access it's state with fromLeft::a, fromRight::b (which panic if incorrect)
 -}
nonterminal Either<a b> with fromLeft<a>, fromRight<b>, isLeft, isRight;

@{-
  - Left case for Either.
  -}
abstract production left
top::Either<a b> ::= value::a
{
  top.fromLeft = value;
  top.fromRight = error("fromRight accessed on a Either that was actually left!");
  top.isLeft = true;
  top.isRight = false;
}

@{-
  - Right case for Either.
  -}
abstract production right
top::Either<a b> ::= value::b
{
  top.fromLeft = error("fromRight accessed on a Either that was actually left!");
  top.fromRight = value;
  top.isLeft = false;
  top.isRight = true;
}

instance Functor Either<a _> {
  map = \ f::(c ::= b) e::Either<a b> ->
    case e of
    | right(x) -> right(f(x))
    | left(x)  -> left(x)
    end;
}

instance Apply Either<a _> {
  ap = \ ef::Either<a (c ::= b)> e::Either<a b> ->
    case ef of
    | left(x)  -> left(x)
    | right(f) -> map(f, e)
    end;
}

instance Applicative Either<a _> {
  pure = right;
}

instance Bind Either<a _> {
  bind = \ e::Either<a b> fn::(Either<a c> ::= b) ->
    case e of
    | left(x) -> left(x)
    | right(x) -> fn(x)
    end;
}

instance Monad Either<a _> {}

instance MonadFail Either<String _> {
  fail = left;
}

function mfixEither
Either<a b> ::= f::(Either<a b> ::= b)
{
  local x::Either<a b> = f(x.fromRight);
  return x;
}

instance MonadFix Either<a _> {
  mfix = mfixEither;
}

instance Alt Either<a _> {
  alt = \ e1::Either<a b> e2::Either<a b> ->
    case e1, e2 of
    | right(x), _ -> right(x)
    | _, right(x) -> right(x)
    -- If they're both left, arbitrarily take the first one
    | _, _ -> e1
    end;
}

@{-
 - Monad transformer corresponding to Either.
 - Note that the type arguments are in this order because Silver type
 - constructors are curried and must be partially applied in order, and we need to have
 - instance Functor MaybeT<e m _> (and friends) and instance MonadTrans EitherT<e _ _>.
 - 
 - @param e The "error" result type, corresponding to the left constructor
 - @param m The monad type to be transformed
 - @param a The "success" result type, corresponding to the right constructor
 -}
nonterminal EitherT<e (m :: * -> *) a> with run<m<Either<e a>>>;
abstract production eitherT
top::EitherT<e m a> ::= x::m<Either<e a>>
{
  top.run = x;
}

@{--
 - Transform the computation inside an EitherT.
 -}
function mapEitherT
EitherT<f n b> ::= f::(n<Either<f b>> ::= m<Either<e a>>) x::EitherT<e m a>
{
  return eitherT(f(x.run));
}

instance Functor m => Functor EitherT<e m _> {
  map = \ f::(b ::= a) x::EitherT<e m a> -> mapEitherT(map(map(f, _), _), x);
}

instance Monad m => Apply EitherT<e m _> {
  ap = \ mf::EitherT<e m (b ::= a)> mx::EitherT<e m a> -> eitherT(
    do {
      fRes::Either<e (b ::= a)> <- mf.run;
      case fRes of
      | left(y) -> pure(left(y))
      | right(f) -> do {
          xRes::Either<e a> <- mx.run;
          case xRes of
          | left(y) -> pure(left(y))
          | right(x) -> pure(right(f(x)))
          end;
        }
      end;
    });
}

@{--
 - Return an "error" result. This is analagous to left for Either,
 - as pure is analagous to right for Either.
 -}
global throwError::Applicative m => (EitherT<e m a> ::= e) = compose(eitherT, compose(pure, left));

instance Monad m => Applicative EitherT<e m _> {
  pure = compose(eitherT, compose(pure, right));
}

instance Monad m => Bind EitherT<e m _> {
  bind = \ x::EitherT<e m a> f::(EitherT<e m b> ::= a) -> eitherT(
    do {
      res :: Either<e a> <- x.run;
      case res of
      | left(y) -> pure(left(y))
      | right(val) -> f(val).run
      end;
    });
}

instance Monad m => Monad EitherT<e m _> {}

instance Monad m => MonadFail EitherT<String m _> {
  fail = \ msg::String -> eitherT(pure(left(msg)));
}

instance Monad m => Alt EitherT<e m _> {
  alt = \ x::EitherT<e m a> y::EitherT<e m a> -> eitherT(
    do {
      v :: Either<e a> <- x.run;
      case v of
      | left(_) -> y.run
      | right(_) -> pure(v)
      end;
    });
}

instance MonadTrans EitherT<e _ _> {
  lift = \ x::m<a> -> eitherT(map(right, x));
}

@{--
 - Order preserving partitioning of a list of eithers into a pair
 - of lists of the two different results.
 -}
function partitionEithers
Pair<[a] [b]> ::= l::[Either<a b>]
{
  local recurse :: Pair<[a] [b]> = partitionEithers(tail(l));
  
  return case l of
  | [] -> pair([], [])
  | left(a) :: _ -> pair(a :: recurse.fst, recurse.snd)
  | right(b) :: _ -> pair(recurse.fst, b :: recurse.snd)
  end;
}

@{--
 - Returns the left value, or the default if there is no left value.
 - 
 - @param e The either being discriminated
 - @param o The fallback value
 -}
function fromLeft
a ::= e::Either<a b> o::a
{
  return case e of
  | left(a) -> a
  | right(_) -> o
  end;
}

@{--
 - Returns the right value, or the default if there is no right value.
 - 
 - @param e The either being discriminated
 - @param o The fallback value
 -}
function fromRight
b ::= e::Either<a b> o::b
{
  return case e of
  | left(_) -> o
  | right(b) -> b
  end;
}

