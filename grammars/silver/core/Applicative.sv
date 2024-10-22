grammar silver:core;

@{-
Applicative functors extend Apply with a means to lift a value of type a into an f<a>;
thus together with the apply operation, it is possible to lift a function of artibrary arity to
work on values wrapped in the type constructor f.

Instances should satisfy the following:

Identity
  ap(pure(id), x) = x
Composition
  ap(ap(ap(pure(compose), f), g), h) = ap(ap(f, g), h)
Homomorphism
  ap(pure(f), pure(x)) = pure(f(x))
Interchange
  ap(u, pure(y)) = ap(pure(\f -> f(y)), u)
-}
class Apply f => Applicative f {
  pure :: (f<a> ::= a);
}

@{-
  - Prefer `map` to this function. However, it can be useful to get a `Functor`
  - instance for free, given an existing `Applicative` instance.
  -}
fun liftA1 Applicative f => f<b> ::= f::(b ::= a)  x::f<a> = ap(pure(f), x);

fun when_ Applicative f => f<Unit> ::= cond::Boolean  body::f<Unit> =
  if cond then body else pure(unit());

fun unless Applicative f => f<Unit> ::= cond::Boolean  body::f<Unit> =
  if cond then pure(unit()) else body;

-- These should be factored out into a Traversable type class, eventually.
fun traverseA Applicative m => m<[b]> ::= f::(m<b> ::= a) lst::[a] =
  foldr(lift2(cons, _, _), pure([]), map(f, lst));

fun traverse_ Applicative m => m<()> ::= f::(m<()> ::= a) lst::[a] =
  foldr(applySecond, pure(()), map(f, lst));

fun sequence Applicative m => m<[a]> ::= lst::[m<a>] = foldr(lift2(cons, _, _), pure([]), lst);
