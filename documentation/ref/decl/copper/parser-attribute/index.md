---
layout: sv_wiki
title: Parser attribute declarations
---


```
parser attribute indentStack :: [Integer] action {
  indentStack = [];
};

parser attribute seenTypes :: [String] action {
  seenTypes = [];
};
```

## Syntax

Parser attributes are mutable values available in imperative action blocks during parsing. They are declared as:

`parser` `attribute` _indentifier_ `::` _type_ `action` `{` _initialization action_ `}`;

The initialization block is run before the parser begins. Different parser attributes will initialize in no specific order.

## Examples

### C

C has [a well known parsing problem](http://calculist.blogspot.com/2009/02/c-typedef-parsing-problem.html), for which the canonical solution is to simply remember all declared typedef names, and use this list during parsing to decide if a lexeme is a type name or identifier name.

Parser attributes would be used to remember a list of seen type names, and then [disambiguation functions]({{ "/ref/decl/copper/disambiguate/" | prepend: site.silver_base }}) can be used during parsing to pick.
