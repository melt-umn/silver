---
layout: sv_wiki
title: Numeric expressions
menu_title: Numeric
menu_weight: 10
---


```
25.0 + -2.0 * 3.14159

toInt(100.0 / toFloat(3))
```

## Numerical Operations

Integer literals like `5` will parse as type `Integer`, and are not
implicitly promoted to `Float`, if present where a `Float` is expected.  Floating point literals must have a dot, like `5.0`.

The basic arithmetic operations on `Integer`s and `Float`s are:

```
<Expr> + <Expr>

<Expr> - <Expr>

- <Expr>

<Expr> * <Expr>

<Expr> / <Expr>
```

Multiply and divide will bind more tightly than addition and subtraction, as you would expect from the standard order of operations.

The operands to each must have the same type.  There is no implicit promotion of `Integer` to `Float` or vice versa.  The operators will
result in the same type as its inputs.  Division of two integers will result in an integer, with the remainder truncated (as is normal in, for example, C.)

> _**Example:**_ The following code:
```
5 / 2
```
> is an integer division, and will evaluate to the `Integer` 2. Whereas:
```
5.0 / 2.0
```
> is a floating point division, and will evaluate to the `Float` 2.5.
