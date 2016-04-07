---
layout: sv_wiki
title: Abstract productions
---


```
abstract production plus
e::Expr ::= e1::Expr  e2::Expr
{
}
```

This page covers abstract productions. See also [concrete productions]({{ "/ref/decl/productions/concrete/" | prepend: site.sv_wiki_base }}), [aspect productions]({{ "/ref/decl/productions/aspect/" | prepend: site.sv_wiki_base }}), [default productions]({{ "/ref/decl/productions/default/" | prepend: site.sv_wiki_base }}).

## Syntax

Abstract production declarations introduce a new constructor for a nonterminal type. Following the name of the production is the _production signature_ and then within braces, a set of _production statements_.

Any type variables not bound by the nonterminal type are universally quantified. (And since productions are value constructors, this makes them existential types within the body, or when pattern matching.)

The number of children in the RHS can be zero.  Additionally, names can be omitted from any child that may be irrelevant (such as some terminals in concrete productions.) In this case, just the type is written instead.

Aspects are permitted to omit referencing children using an underscore (`_`).

## Analogy to data types

Production declarations are similar to constructors in an algebraic data type. Or to a final child class of an abstract base class (the nonterminal) in an object oriented language.
