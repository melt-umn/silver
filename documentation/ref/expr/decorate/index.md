---
layout: sv_wiki
title: Decoration
---

## decorate

See [Decorated vs Undecorated]({{ "/concepts/decorated-vs-undecorated/" | prepend: site.sv_wiki_base }}) for an explanation of what _`Decorated`_ means.

The following syntax will decorate an undecorated tree:

```
decorate <Expr> with { ( <name> = <Expr> ; )* }
```

where _`Expr`_ is expected to be undecorated.  Each _`name`_ should
be an inherited attribute that occurs on the type of value that _`Expr`_ is.

> _**Example:**_
```
decorate expr with { env = [pair("x", 1), pair("y", 2)]; }
```
> will decorate an undecorated expression with an environment binding two variables.
```
decorate folder with { input = ["Hello", "world"]; }.output
```
> will decorate a value called _`folder`_ with the inherited attribute _`input`_, then demand the synthesized attribute _`output`_ from the resulting decorated node.


The inverse operation is [new]({{ "/ref/expr/new/" | prepend: site.sv_wiki_base }}).
