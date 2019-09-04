---
layout: sv_wiki
title: Functor attributes
---

Note: this extension is a work in progress!  

```
synthesized attribute foo<a> occurs on Expr;

abstract production add
top::Expr ::= e1::Expr e2::Expr
{ propagate foo; }

foo('keyword')
```

## Concept
"Functor attributes" are essentially a method of performing transformations on trees.  They work by copying the current tree at every node, except for a few which may be overridden manually.  There are a number of uses for
functor attrbutes, such as generating the host tree produced after forwarding, or performing a transformation such as lifting generated decls to the top level.  

## Syntax
Currently, the only support for functor attributes is in the form of a propagate production statment, written as
```propagage foo, bar, baz```
This simply generates the equations for the enclosing production.  The generated equations simply call the
constructor for the enclosing production, with the result of accessing the attribute on each child as the
parameters.  If the attribute does not occur on a child, or a child is not an undecorated nonterminal, then the
child is used directly.  In addition, any annotations occuring on the parent nonterminal are provided with the same
values as on the parent.  

## Future work
In the future, a way of performing the propagate globally may be provided.  In addition, a new attribute declaration
for functor attributes may be introduced, to remove the need for a generic type parameter.  
