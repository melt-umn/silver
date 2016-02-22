---
layout: sv_wiki
title: Let
---

**Let syntax is not yet finalized, we currently discourage its use.**

Usually, you can use [locals]({{ "/ref/stmt/locals/" | prepend: site.sv_wiki_base }}) instead.

```
let
  foo :: String = "hello",
  bar :: Integer = 42
in
  foo ++ " " ++ bar
end
```

## Syntax

Subject to change.

**TODO**: current syntax is inconsistent with the rest of silver generally. We should probably use `let { foo :: String = e; bar :: Type = f; } in Expr` to be consistent. However, perhaps that syntax (e.g. `decorate`) should also be changed to something else. Because both of these options are ugly and annoying.

## Semantics

Subject to change.

**TODO**: **BUG**: Currently, the scope for lets is that each individual declaration in a let block is **unaware** of each other. Their environment is the same as is given to the let block as a whole.

This isn't even remotely what you'd expect. The least of the reasonable expectations would be that each declaration is visible to all later ones. The behavior we should support is that these are all simultaneously defined (i.e. a `letrec`.)

This is not currently done due to irritating issues with the Java translation of the blocks. So currently they're a bit broken.

(The issue is that these are currently implemented as locals captured by Java closures (anon classes). However, there's no way to do the mutually recursive definition while still also being `final` and thus capturable. So we need fundamentally change how these work to fix this problem.)
