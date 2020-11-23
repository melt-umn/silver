
The grammar in this directory implements typing and evaluation for the
simply-typed lambda calculus augmented with Boolean operations.  This
is done by taking advantage of implicit monads, which allow us to use
monadic expressions as if they were not monadic in attribute
equations.


To compile the grammar, run
```
./silver-compile
```
This will produce `stlc.jar`.


There are two ways to run examples:
- Examples can be run with
  ```
  java -jar stlc.jar <STLC Expression>
  ```
  replacing `<STLC Expression>` with the expression one wishes to type
  and evaluate.
- Examples may also be run with
  ```
  ./run
  ```
  which will enter a REPL.  Expressions may be typed at the prompt.
  To exit, enter a blank line.


The following example expressions show the syntax of the language:
- `lambda f:Bool -> Bool. f` : a function taking an argument of type
  `Bool -> Bool` named `f`
- `true && false` : conjunction of the constants `true` and `false`
- `true || true` : disjunction of the constant `true` with itself
- `(lambda x:Bool. x) true` : a function applied to the constant
  `true`
Associativity and precedence are as expected.

