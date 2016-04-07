---
layout: sv_wiki
title: Lexer class declarations
---


```
lexer class IDENTIFIER;

lexer class KEYWORD dominates IDENTIFIER;

lexer class IDENTIFIER submits to KEYWORD, BUILTIN;

lexer class KEYWORD dominates Id_t;
```

## Syntax

Lexer classes are a useful tool in resolving lexical conflicts. If one DFA state can accept multiple valid tokens, it is normally an error during the lexical generator. However, if there is a lexer class relationship between those token, the token that dominates all others is selected instead as the resolution.

Lexer classes are declared as follows:

`lexer` `class` _Name_ `dominates` _lexer class or terminal list_ `submits` `to` _lexer class or terminal list_ `;`

where both the dominates and submits lists are optional.
