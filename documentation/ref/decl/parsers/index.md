---
layout: sv_wiki
title: Parser function declarations
---


```
parser parse :: Root {
  grammar:one;
  grammar:two;
  some:other:grammar;
}
```

## Syntax

Parser declarations have three parts: a name for the newly created parse function, the starting nonterminal, and a list of grammar to include in what's sent to the parser generator.

`parser` _parser name_ `::` _start nonterminal_ `{` _grammar list_ `;` `}`

The resulting parse function has type:
```
Function(ParseResult<StartNT> ::= String String)
```
e.g., for the parser declaration at the start of the page:
```
Function(ParseResult<Root> ::= String String)
```

Where `ParseResult` is a data structure indicating either `parseSuccess` or `parseFailure` along with the errors or syntax tree result.  The two parameters are (1) the string to parse and (2) the filename to indicate is being parsed. (e.g. for parse errors, or for the `filename` terminal attribute.)

All concrete syntax in the listed grammars in included in what's sent to the parser generator, including those grammars they export.

## FAQ

### Can't I parse a file directly?

Not yet.

### How do I control the layout accepted before/after the root nonterminal?

Silver currently just makes all 'ignore terminals' be the layout before/after the starting nonterminal. Eventually there will be syntax to control this, just like there is on productions.
