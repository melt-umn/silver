
This extension implements monadification for implicit monads.  We do
this by rewriting the expressions in implicit equations.



# Equations

We have both marked equations (e.g. `implicit t.a = e;`) and unmarked
equations (e.g. `t.a = e;`) for attributes.  The marked equations are
primarily for consistency with the paper, and thus should probably be
left even though they aren't necessary anymore.



# Expressions

Some general notes on expression rewriting, which address common but
perhaps non-obvious elements of the rewriting:

Expressions in implicit equations rewrite based on typing.  We end up
having similar attributes (declared in `Util.sv`) as normal typing,
but we need our own because the equations need to take into account
the implicit monads which might be present when deciding whether an
expression is typable, or close enough to typable to rewrite.

Most error checking is left to the rewritten version, with our
checking only caring about whether something is a monad or not.  This
allows us to reduce the duplication of typechecking between this
extension and the host language, as well as making our job easier.

Many expressions rewrite by using lambda functions to assign new names
to expressions.  The main reason we do this is to keep the variable in
a bind from capturing the same variable in another subexpression.  For
example, `e1 + (x * 2)`, not using a function to change the name of
`x * 2` can give us `bind(e1, \ x::Integer -> pure(x + x * 2))`, where
using a function for renaming can give us
```
bind(e1, (\ x::Integer y::Integer -> pure(x + y))(_, x * 2))
```
where we don't have variable capture.  A secondary reason for doing
this in some places is that it lets us rewrite constructed subparts of
the new expression with the names bound, while not needing to rewrite
the whole thing.  For example, in `case_any`, we construct and rewrite
new `case` expressions in lambda functions so they have all the names
bound, then join them with `mplus`, then put them in a function to
bind the names we want.

Throughout our expression rewriting, we check whether a monadic
expression is decorated, wrapping it in `new()` before binding it in
if it is.  Silver does not appreciate a decorated expression as the
first argument to `bind`, and thus we need to check it and undecorate
it with `new()` if it is decorated.

