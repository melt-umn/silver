grammar silver:core;

--TODO: {Alt, Plus, Alternative, MonadZero, MonadPlus, MonadFail}

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
