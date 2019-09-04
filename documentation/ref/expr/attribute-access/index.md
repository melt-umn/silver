---
layout: sv_wiki
title: Attribute access
---


```
pair(1,2).fst == 1

parse(str, filename).parseResult.ast.errors
```

## Attribute access

Attributes are accessed from a decorated node (see [decorated vs undecorated]({{ "/concepts/decorated-vs-undecorated/" | prepend: site.sv_wiki_base }})) using the following syntax:

_Expr_ `.` _identifier_

where _identifier_ is the name of the attribute to access.  The type of _Expr_ should be decorated. As a short hand, however, Silver will implicitly decorate (with no inherited attributes) if an undecorated expression is provided. This is often useful for data structures, where no inherited attributes are present.

## Attribute sections

```
map((.typerep), exprs)
```

A feature introduced with Silver 0.4 is _attribute sections_, named after operator _sections_ in Haskell (e.g. `(3+)`.)

Attribute sections are a notation for getting a function that does nothing but retrieve a specific attribute from its argument. The syntax is:

`(.` _identifier_ `)`

i.e. an attribute access without an expression, but enclosed in parentheses.

Currently, attribute sections have a number of limitations:

  * The attribute must be synthesized
  * The attribute must not be parameterized

Eventually, these restrictions will be lifted.
