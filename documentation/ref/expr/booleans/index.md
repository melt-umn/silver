---
layout: sv_wiki
title: Boolean expressions
menu_title: Boolean
menu_weight: 20
---


```
if first.isJust then first else second

c && (!a || !b) == c && !(a && b)
```

## Boolean operations

`true` and `false` (all lower case) are the values of type `Boolean`. `if` expressions have the following form:

```
if <Expr::Boolean> then <Expr::a> else <Expr::a>
```

The condition must be of type `Boolean`. There is no implicit conversion to `Boolean` from, for example, `Integer`.  The types of the `then` and `else` branches must be the same.

Every `if` must have an `else` branch, there is no `if-then`.  The `else` branch will extend as far as possible.

> _**Example:**_ To clarify by what "as far as possible" means, the following code:
```
 if condition
 then foo
 else bar ++ if condition2
             then foo2
             else bar2 ++ more
```
> will parse as
```
 if (condition)
 then (foo)
 else (bar ++ if (condition2)
              then (foo2)
              else (bar2 ++ more))
```


## Logical operators

The standard C-style boolean operators are present:

```
<Expr::Boolean> && <Expr::Boolean>

<Expr::Boolean> || <Expr::Boolean>

! <Expr::Boolean>
```

Again, the operands must be of type `Boolean`, there are no implicit
conversions.  The not operator binds more tightly than the and operator, which binds more tightly than the or operator, as you would expect.  These operators DO short-circuit evaluation.
