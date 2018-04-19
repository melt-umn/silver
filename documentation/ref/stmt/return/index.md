---
layout: sv_wiki
title: Return
menu_weight: 40.0
---

Be sure to see [function]({{ "/ref/decl/functions/" | prepend: site.sv_wiki_base }}) declarations.

```
function stringEq
Boolean ::= s1::String  s2::String
{
  return s1 == s2;
}
```

## Semantics

Each function should have exactly one `return` statement within its body, with the usual meaning.
