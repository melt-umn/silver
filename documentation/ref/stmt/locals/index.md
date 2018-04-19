---
layout: sv_wiki
title: Local equations
menu_weight: 30.0
---


```
abstract production id
lhs::Expr ::= n::String
{
  local refs :: [Decorated Dcl] =
    lookupName(n, lhs.env);
}
```

## Syntax

The preferred form of local declarations is shown above, as one production statement.

Production attributes (local visible to all aspects, not just the current production body) can be declared in the same way, except using `production` instead of `local`.

## Legacy syntax

The old form of local declaration is two-part. First, the local is declared:
```
local attribute <name> :: <type>;
```
The name of the attribute and its type (following the has-type symbol
**::**) is similar to the name/type specifications in production
signatures. This attribute can be referenced inside the production body. The
separate definition of its value has the form
```
<name> = <expression>;
```

## Higher-order attributes

If the local attribute is a higher order attribute (its type is a
nonterminal), then inherited attributes can be set on it just like
they are set for the nonterminal children on the right-hand side of
the production.

```
{
  local chain :: IOChain = print("hello, world");
  chain.inputIOToken = ioin;
}
```

## Collection attributes

Currently, production attributes can be [collections]({{ "/concepts/collections/" | prepend: site.sv_wiki_base }}), but the legacy syntax must be used.

```
{
  production attribute contribs :: [Stmt] with ++;
  contribs := [];
}
```
