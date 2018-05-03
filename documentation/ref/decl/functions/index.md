---
layout: sv_wiki
title: Function declarations
---


```
function orElse
Maybe<a> ::= l::Maybe<a> r::Maybe<a>
{
  return if l.isJust then l else r;
}

function performSubstitution
TypeExp ::= te::TypeExp s::Substitution
{
  te.substitution = s;
  return te.substituted;
}
```

## Syntax

Functions declarations give a name for the function, and a signature in the same format as [productions]({{ "/ref/decl/productions/" | prepend: site.silver_base }}), except that the left-hand side is not named. Functions must also contain a [return statement]({{ "/ref/stmt/return/" | prepend: site.silver_base }}).

## Semantics

Like productions' children, parameters may be given inherited attributes, and are considered implicitly decorated. Unlike productions, there is no parent for autocopy attributes to come from. Passing undecorated types to a function, accessing an attribute, and getting errors about missing inherited attribute equations is a common mistake for new Silver programmers. A simplistic fix is often to simply pass the parameter as already `Decorated`.

All parameters are passed lazily. There is currently no strict annotation.

## Aspects

```
aspect function driver
_ ::= _ _ _
{
  tasks <- [emitDocumentation(ast)];
}
```

Aspect functions are also possible, by prefixing the declaration with `aspect`. Aspect functions are forbidden from using `return`, their purpose is only to influence the value of "collection production attributes" (which is not only a mouthful, but misnamed inside a function, too!)

Aspecting functions is discouraged. Future changes may deprecate this feature.

## FAQ

### Why is the syntax so verbose?

Partly because it's mimicking the production syntax, and partly because it can be: functions aren't as important in Silver because most of the heavy lifting should be done by attributes on productions.

However, there is a future flaw with the current signature syntax. When Silver begins to support type classes (it doesn't yet, but probably will) there is no natural way to write the type class constraints, as there is in Haskell.  This may force a redesign of how signatures are written.
