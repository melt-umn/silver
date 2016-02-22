---
layout: sv_wiki
title: Pairs
---


```
local attribute symbolDef :: Pair<String Integer>;
symbolDef = pair("a", 3);
```

# Pair
Pairs are also provided as a standard data structure in core.  Pairs are the
first data structure that is completely unspecial--that is, it's an ordinary nonterminal with no special language support.

The pair type is written _`Pair<a b>`_ where _`a`_ and _`b`_ are
types.

Pairs are constructed using the _`pair`_ constructor:

```
abstract production pair
top::Pair<a b> ::= f::a  s::b
```

> _**Example:**_
```
local attribute priorityError :: Pair<Integer String>;
priorityError = pair(3, "OH NO!");
```


The elements are accessed using the _`fst`_ and _`snd`_ attributes.

> _**Example:**_
```
if priorityError.fst > 2
then print("Error: " ++ priorityError.snd, ioin)
else print("No serious errors.", ioin)
```

Up to date information about this data structure can be found in _`core/Pair.sv`_.

> _**Note:**_
> In future versions of Silver, we may add syntactic sugar for pairs as _`(3, "OH NO!")`_. And similarly for types.  However, this is not present yet.
