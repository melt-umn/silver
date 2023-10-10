grammar silver:core;

synthesized attribute fromJust<a> :: a;
synthesized attribute isJust :: Boolean;

data Maybe<a>
  = just a
  | nothing
  with fromJust<a>, isJust;
derive Eq, Ord on Maybe;

aspect fromJust on Maybe<a> of
| just(v) -> v
| nothing() -> error("fromJust accessed on a Maybe that was actually nothing!")
end;

aspect isJust on Maybe<a> of
| just(_) -> true
| nothing() -> false
end;

instance Functor Maybe {
  map = \ f::(b ::= a) m::Maybe<a> ->
    case m of
    | just(x)   -> just(f(x))
    | nothing() -> nothing()
    end;
}

instance Apply Maybe {
  ap = \ mf::Maybe<(b ::= a)> m::Maybe<a> ->
    case mf of
    | just(f)   -> map(f, m)
    | nothing() -> nothing()
    end;
}

instance Applicative Maybe {
  pure = just;
}

instance Bind Maybe {
  bind = \ m::Maybe<a> fn::(Maybe<b> ::= a) ->
    case m of
    | just(x) -> fn(x)
    | nothing() -> nothing()
    end;
}

instance Monad Maybe {}

instance MonadFail Maybe {
  fail = \ _ -> nothing();
}

instance Alt Maybe {
  alt = orElse;
}

instance Plus Maybe {
  empty = nothing();
}

instance Alternative Maybe {}

instance MonadZero Maybe {}
instance MonadPlus Maybe {}

function mfixMaybe
Maybe<a> ::= f::(Maybe<a> ::= a)
{
  local x::Maybe<a> = f(x.fromJust);
  return x;
}

instance MonadFix Maybe {
  mfix = mfixMaybe;
}

@{-
 - Monad transformer corresponding to Maybe.
 - 
 - @param m The monad type to be transformed
 - @param a The optional result type
 -}
data nonterminal MaybeT<(m :: * -> *) a> with run<m<Maybe<a>>>;
abstract production maybeT
top::MaybeT<m a> ::= x::m<Maybe<a>>
{
  top.run = x;
}

@{--
 - Transform the computation inside a MaybeT.
 -}
function mapMaybeT
MaybeT<n b> ::= f::(n<Maybe<b>> ::= m<Maybe<a>>) x::MaybeT<m a>
{
  return maybeT(f(x.run));
}

instance Functor m => Functor MaybeT<m _> {
  map = \ f::(b ::= a) x::MaybeT<m a> -> mapMaybeT(map(map(f, _), _), x); 
}

instance Monad m => Apply MaybeT<m _> {
  ap = \ mf::MaybeT<m (b ::= a)> mx::MaybeT<m a> -> maybeT(
    do {
      maybeF::Maybe<(b ::= a)> <- mf.run;
      case maybeF of
      | nothing() -> pure(nothing())
      | just(f) -> do {
          maybeX::Maybe<a> <- mx.run;
          case maybeX of
          | nothing() -> pure(nothing())
          | just(x) -> pure(just(f(x)))
          end;
        }
      end;
    }); 
}

instance Monad m => Applicative MaybeT<m _> {
  pure = compose(maybeT, compose(pure, just));
}

instance Monad m => Bind MaybeT<m _> {
  bind = \ x::MaybeT<m a> f::(MaybeT<m b> ::= a) -> maybeT(
    do {
      maybeVal :: Maybe<a> <- x.run;
      case maybeVal of
      | nothing() -> pure(nothing())
      | just(val) -> f(val).run
      end;
    });
}

instance Monad m => Monad MaybeT<m _> {}

instance Monad m => MonadFail MaybeT<m _> {
  fail = \ _ -> maybeT(pure(nothing()));
}

instance Monad m => Alt MaybeT<m _> {
  alt = \ x::MaybeT<m a> y::MaybeT<m a> -> maybeT(
    do {
      v :: Maybe<a> <- x.run;
      case v of
      | nothing() -> y.run
      | just(_) -> pure(v)
      end;
    });
}

instance Monad m => Plus MaybeT<m _> {
  empty = maybeT(pure(nothing()));
}

instance Monad m => Alternative MaybeT<m _> {}

instance Monad m => MonadZero MaybeT<m _> {}
instance Monad m => MonadPlus MaybeT<m _> {}

instance MonadTrans MaybeT {
  lift = \ x::m<a> -> maybeT(map(just, x));
}

--------------------------------------------------------------------------------

@{--
 - The corresponding fold for Maybes.
 -
 - @param otherwise  The element to return if 'ifJust' is 'nothing'
 - @param ifJust  The maybe value to scrutinize
 - @return  Either the contents of the Maybe (if 'just'), or the otherwise element.
 -}
function fromMaybe
a ::= otherwise::a ifJust::Maybe<a>
{
  return if ifJust.isJust then ifJust.fromJust else otherwise;
}

@{--
 - Selects the first existing element, favoring the left.
 -
 - @param l  The first element
 - @param r  The second element
 - @return  A wrapped element, if any, favoring 'l'
 -}
function orElse
Maybe<a> ::= l::Maybe<a> r::Maybe<a>
{
  return if l.isJust then l else r;
}

@{--
  - The eliminator for Maybe. Runs ifJust on the wrapped value if there is one,
  - otherwise returns ifNothing.
  -}
function mapOrElse
b ::= ifNothing::b  ifJust::(b ::= a)  value::Maybe<a>
{
  return
    case value of
    | just(x)   -> ifJust(x)
    | nothing() -> ifNothing
    end;
}

@{--
 - Maybe cons a value to a list, or not.
 -
 - @param h  If a value, the value to cons onto the list.
 - @param t  The list to amend, if there's a value
 - @return  The list, possibly with a new value at its head.
 -}
function consMaybe
[a] ::= h::Maybe<a>  t::[a]
{
  return if h.isJust then h.fromJust :: t else t;
}

@{--
 - Turn a list of possible values into a list of values, skipping over
 - any 'nothing's.
 -
 - @param l  A list of optional values
 - @return  The list with all absent values removed, and present values unwrapped.
 -}
function catMaybes
[a] ::= l::[Maybe<a>]
{
  return foldr(consMaybe, [], l);
}

@{--
 - Finds the first value matching a predicate.
 -}
function find
Maybe<a> ::= f::(Boolean ::= a) l::[a]
{
  return if null(l) then
    nothing()
  else if f(head(l)) then
    just(head(l))
  else
    find(f, tail(l));
}
