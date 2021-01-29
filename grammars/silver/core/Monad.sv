grammar silver:core;

{-
Monads support both lifting functions/values of arbitrary arity and sequential compostion.

Instances should satisfy the following:

Left Identity
  bind(pure(x), f) = f(x)
Right Identity
  bind(x, pure) = x
-}
class Applicative m, Bind m => Monad m {}

-- Prefer `fmap` to this function. However, it can be useful to get a `Functor`
-- instance for free, given an existing `Monad` instance.
function liftM1
Monad m => m<b> ::= f::(b ::= a)  x::m<a>
{ return bind(x, \x::a -> pure(f(x))); }

-- Prefer `ap` to this function. However, it can be useful to get an
-- `Applicative` instance for free, given an existing `Monad` instance.
function apM
Monad m => m<b> ::= f::m<(b ::= a)>  x::m<a>
{ return bind(f, \f::(b ::= a) -> bind(x, \x::a -> pure(f(x)))); }

function whenM
Monad m => m<Unit> ::= cond::m<Boolean>  body::m<Unit>
{ return bind(cond, when_(_, body)); }

function unlessM
Monad m => m<Unit> ::= cond::m<Boolean>  body::m<Unit>
{ return bind(cond, unless(_, body)); }

{-
Monads that support failure with an error message.

Instances should satisfy the following:

Annihilation
  bind(empty, f) = empty
-}
class Monad m => MonadFail m {
  fail :: (m<a> ::= String);
}

instance MonadFail [] {
  fail = \ String -> [];
}

instance MonadFail Maybe {
  fail = \ String -> nothing();
}

instance MonadFail Either<String _> {
  fail = left;
}

{-
The MonadZero type class has no members of its own; it just specifies that the type has both Monad and Alternative instances.

Instances should satisfy the following:

Annihilation
  bind(fail(s), f) = fail(s)
-}
class Monad m, Alternative m => MonadZero m {}

instance MonadZero [] {}
instance MonadZero Maybe {}

{-
The MonadZero type class has no members of its own; it just extends MonadZero with an additional law.

Instances should satisfy the following:

Distributivity
  bind(alt(x, y), f) = alt(bind(x, f), bind(y, f))
-}
class Monad m, Alternative m => MonadPlus m {}

instance MonadPlus [] {}
instance MonadPlus Maybe {}
