---
layout: sv_wiki
title: Pattern matching
---


```
case lhs, rhs of
| just(l), just(r) -> l ++ r
| just(l), nothing() -> l
| nothing(), just(r) -> r
| nothing(), nothing() -> []
end
```

# Pattern Matching

Silver supports pattern matching on decorated nonterminal types. (It will also
pattern match on undecorated nonterminal types, but implicitly "decorates" them with no inherited attributes.)

The expression has the following syntax:

```
case <Expr> of
  | <Pattern> -> <Expr>
( | <Pattern> -> <Expr> )*
end
```

Patterns must be constructors of the appropriate types, the wildcard _`_`_,
or a new name that will be bound to the value that appears in that position.

> _**Example:**_
```
local attribute val :: Maybe<Pair<String String>>;
-- ...
case val of
| just(pair(a, _)) -> "Key is " ++ a
| nothing() -> "Key not present"
end
```
> produces a string value based on the value of _`val`_.

## TODO

This page needs expanding and many of the old notes are now out of date.

  * Looks-through-forwards
  * Decoration
  * Noting the types that can be matched on (int,string,list, any nonterminal type) and the syntax
  * mention GADTs?
  * Compilation behavior (left-right, rather that top-bottom, maybe note this as known bug)
