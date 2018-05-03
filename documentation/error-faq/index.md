---
layout: sv_wiki
title: Error FAQ
menu_title: Understanding Errors
menu_weight: 80.0
---

# Compile-time

**error: duplicate equation for attribute _a_**

There are two definitions of the value of the attribute on the same production. Both definitions should emit this error message, so you should be able to find them.

If the attribute is a collection, perhaps one should change from ':=' to '<-' (see Concept\_Collections).

# Runtime

Stack trace errors commonly look like this:

```
(DN.307): While evaling syn 'silver:analysis:typechecking:core:upSubst' in 'silver:definition:core:presentAppExpr' (1b842264, Command.sv:98:30)
(DN.307): While evaling syn 'silver:analysis:typechecking:core:upSubst' in 'silver:analysis:typechecking:core:check' (53df4528)
(silver.definition.type.PunifyCheck in PunifyCheck.java:116): Error while evaluating function silver:definition:type:unifyCheck
```

There are a few things to note about how to read this output:

  * They begin with something in parenthesis. For things in the Silver Runtime, this is shortened to an abbreviation (like DN.307). For things coming from generated code, there is a full package and class name, followed by a file name and line number.
  * Each production is identified by the `System.identityHashCode` value of its decorated node, and (if present at all) the location annotation attached to that node. The identity allows spotting the same node in the stack trace, and the location is occasionally helpful when tracking down bugs with your program: often it's easier to diagnose a problem when you know what bit of syntax lead to it.
  * **Begin reading stack traces at the bottom**: that's often where the only relevant bit is.
  * Silver stack traces do NOT show thunk evaluation, which means they can occasionally be misleading. The underlying (large!) Java stack traces will be printed instead if you set the SILVER\_TRACE environment variable to 1.

**Inherited attribute _a_ not provided to _p1_ by _p2_**

Unfortunately this error provides little clue about how _p2_ appeared in its parent node. It could be a child, a local, or an "anonymous" decorate site using the `decorate foo with {...}` syntax. Or, one may have written `x.foo` on an undecorated value `x`, which implicitly creates a decorated node (with no inherited attributes!) on which to evaluated `foo`.

If the cause is not obvious, begin by tracking down which nonterminal the production _p2_ belongs to, and then track down **all** aspects of the production _p1_. Look for anywhere a value of that appropriate type might be accessed.

**While evaling inh _a_ for forward in _p_**

**While evaling syn via forward in _p_**

Neither of these tracing message indicate which attribute, as that's a bit redundant. Look below them until forwarding ends and you should see something like:

```
(DN.313): While evaling syn via forward in 'silver:definition:core:application' (5a659a05, Command.sv:98:30)
(DN.313): While evaling syn via forward in 'silver:definition:core:functionApplication' (217bd515, Command.sv:98:30)
(DN.307): While evaling syn 'silver:analysis:typechecking:core:upSubst' in 'silver:definition:core:functionInvocation' (19a9b1e9, Command.sv:98:30)
```

Here, we're accessing `upSubst` from an `application` which forwarded to `functionApplication` which forwarded to `functionInvocation`, where there was an equation answering.
