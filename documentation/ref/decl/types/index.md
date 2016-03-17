---
layout: sv_wiki
title: Type declarations
---


```
type EnvMap = TreeMap<String  Decorated Decl>;

type Set<a> = [a];
```

## Syntax

Transparent type aliases can be declared as follows:

`type` _Identifier_ `<` _type variable list_ `>` `=` _type_ `;`

Please note these are aliases, not actual new types. For that see [nonterminal declarations]({{ "/ref/decl/nonterminals/" | prepend: site.sv_wiki_base }}).

## FAQ

### Is there a newtype equivalent?

Not yet, but someday.
