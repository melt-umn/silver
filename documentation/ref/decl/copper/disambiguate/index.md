---
layout: sv_wiki
title: Disambiguation directives
menu_title: Disambiguation directives
menu_weight: 10.0
---


```
disambiguate TypeName_t, Ident_t {
  pluck if containsBy(stringEq, lexeme, seenTypeNames)
        then TypeName_t
        else Ident_t;
}

disambiguate Slash_t, RegexChar_t {
  pluck Slash_t;
}
```

## Syntax

Whereas a dominates/submits relation resolves a lexical ambiguity at the time of the lexer generation, a disambiguation function allows the ambiguity to proceed until runtime, where it must be resolved at the time the token is lexed.

The syntax of the declaration is:

`disambiguate` _terminal list_ `{` _action block_ `}`

Within the action block must be a `pluck` statement, indicating which terminal is the correct interpretation.

## Uses

### C

See [Reference\_ParserAttribute]({{ "/ref/decl/copper/parser-attribute/" | prepend: site.silver_base }}).

### Non-reserved words

A dominates/submits relation reserves the words. Once '`if`' dominates '`Id_t`', there can be no identifier with lexeme '`if`'.  With a disambiguation function, however, it can instead be made so that only those places where both are valid can an identifier not take on the "reserved" word.  Wherever the reserved word is not valid, the identifier can.

Typically this is not desirable for keywords, as it's not always apparent where both terminals are valid. But, it is desirable in some cases, as in the example at the top, where `/` is reserved as indicating the end of a regular expression.
