---
layout: sv_wiki
title: Relational expressions
menu_title: Relational
menu_weight: 30
---

# Comparison operators

All comparison operators bind more tightly than logical operators.

The following comparison operators work on types `Integer`,
`Float`, and `String`:

```
<Expr> < <Expr>

<Expr> <= <Expr>

<Expr> > <Expr>

<Expr> >= <Expr>

<Expr> == <Expr>

<Expr> != <Expr>
```

String comparison is lexicographical, and does pay attention to case. Equality and inequality will also work with `Boolean`s, but the other comparison operators will not.
