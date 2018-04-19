---
layout: sv_wiki
title: Annotation declarations
---


```
annotation origin :: Origin;
```

## What are annotations?

Recall the distinction between [decorated and undecorated
types](../../../concepts/decorated-vs-undecorated). Typically, attributes are
computed on decorated nodes: to obtain a decorated node, an
undecorated one is supplied with inherited attributes. This makes the
synthesized attributes on that node computable. 

Annotations are very different from attributes. They are values that are supplied to create undecorated nodes -- they are far more similar to children than attributes.  Unlike children, however, they are not something you would supply inherited attributes to, and they appear uniformly on all productions for a nonterminal.

## Declaration syntax

Identical to attributes, but using `annotation` instead of `synthesized attribute` or the like.

```
annotation foo<a> :: a;
annotation origin :: Origin;
annotation location :: Location;
```

## Occurs syntax

Identical to attributes, but using `annotation` instead of `attribute`.

```
annotation origin occurs on Expr;

nonterminal Expr with location, foo<String>;
```

## Access syntax

Identical to attributes, but notably requires the undecorated value. Decorated values will be implicitly undecorated to access the annotation.

```
top.location
```

## Application syntax

Annotations are supplied via named arguments when a node is created. The syntax looks as follows:

```
and(l.ast, r.ast, location=lhs.location)


```

The named arguments **must** come after the ordered arguments.

## Implicit location

In the standard library there is an `location` annotation that the parser has special understanding of, and will automatically populate concrete syntax nodes with this location during parsing.

This should make obtaining location must easier.

## Feature wishlist

Issue 32 (on Google Code) tracks some desired features to make annotations easier to use.

## Acknowledgements

The idea for annotations was shamelessly stolen from Rascal. Yoink!
