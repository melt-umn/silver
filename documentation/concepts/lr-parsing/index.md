---
layout: sv_wiki
title: LR parsing
menu_title: LR Parsing
---

# Shortest ever guide to LR parsing

LR parsers recognize syntax by essentially turning it into reverse polish notation, with the 'production rules' being the operators, and everything else just data.

For example,

`1 + 2 * 3 / 4`

in a grammar with the appropriate productions MUL, DIV, ADD with the usual precedence rules gets recognized as:

`1 + 2 * 3 MUL / 4 DIV ADD`

equivalent to the usual reverse polish:

`1 2 3 * 4 / +`

whereas without precedence this might be incorrectly read as:

`1 + 2 ADD * 3 MUL / 4 DIV`

which is equivalent to the usual reverse polish:

`1 2 + 3 * 4 /`

# Conflicts

  * **Shift** means "move on to the next token."
  * **Reduce** means "recognize this production."

Silver's parser generator, Copper, will report Shift/Reduce and Reduce/Reduce conflicts. The error message generated on the command line may not be the best.

The quickest route to understanding the problem is to open the `Parser_*.copperdump.html` file, and going to the parser state (in the "LALR DFA") an error is reported in, and take a look at what the parser's view of the world is.

# Fixing errors

Unfortunately, beyond the scope of this document. (TODO: Someone suggest links to elsewhere for discussion and examples, perhaps?)

Generally, the structure of the grammar must be changed somehow to recognize the same language, but in a manner the parser is capable of understanding.

# How precedence works

This is a brief explanation of how the precedence rules work, since this isn't always explained even to people who understand and are capable of writing LALR grammars.

There are two forms of precedence:

  * Terminal (operator) precedence: for resolving shift/reduce conflicts.
  * Production precedence: for resolving reduce/reduce conflicts.

Precedence is only used to resolve conflicts when (1) it's not a conflict due to insufficient lookahead, but rather due to an actual ambiguity in the grammar AND (2) exactly one behavior should always be preferred.  If either of these two are not the case, then it must be resolved through changing the grammar.

## Operator precedence

To resolve shift/reduce conflicts, we must be able to relate the production and the terminal somehow. We accomplish this by using the shift terminal, and mapping every production to an _operator terminal_.

By default, every production reports the _last_ terminal that appears in the production rule as its operator terminal. If it has no terminals, it has no operator terminal. An operator terminal can be assigned manually in silver:

```
concrete production whatever
top::Expr ::= Expr '-' '>' Expr
operator=Some_Terminal_t
{
}
```

This will make the 'whatever' production use `Some_Terminal_t` instead of '>' as it's operator terminal.

### Operator precedence resolution

After mapping each production to an operator terminal, we then consult the precedence of each terminal (the shift terminal, and the production operator terminal.)

This consists of two parts:

  1. The precedence number.  Higher means binds more tightly (e.g. MUL has 2, ADD has 1)
  1. The association: left/right/non-assoc, if any.

This is just a very convenient way of ranking terminals. First, by precedence number, second by association (if precedence is equal.) The winner is the action the parser takes.

If there is no winner, a shift/reduce conflict is reported.

The "non-assoc" case is special: it resolves the ambiguity in favor of raising a parse error when that syntax is encountered. No winner. The shift and reduce actions are both removed.

An example, consider our earlier example,

`1 + 2 *`

The parser has shifted `1 + 2` and `*` is the next symbol in the input, we are in a state where we can either shift `*` or reduce (emit ADD). Since `*` has a higher precedence, shifting wins.

`1 + 2 * 3 /`

We're looking at the `/` symbol, shift or reduce. Here, our reduce option is to emit MUL, which has operator `*` which has the same precedence as `/`. But, they're left associative, so the decision is to reduce.

## Production precedence

Reduce/Reduce conflicts simply require relating productions to other productions. Each production can optionally be assigned a precedence number. The highest number wins.

There is no interaction or relationship between production precedence and operator precedence at all. They're entirely orthogonal.

```
concrete production alwaysUsed
top::Expr ::= Ident_t
precedence=2
{
}

concrete production neverUsed
top::Expr ::= Ident_t
precedence=1
{
}
```

Usually reduce/reduce conflicts are more interesting than this example. (TODO: pick a simple interesting one?)  Typically, however, it's often better to resolve RR conflicts by changing the grammar to more accurately express the language structure rather than ad-hoc sprinkling of these precedences.

# Philosophy

Why LR parsing? There are lots of alternatives, [PEG](http://en.wikipedia.org/wiki/Parsing_expression_grammar)s and GLR in particular. Answering this question requires context, though.

  * For general purpose programming, you probably wouldn't use LR. PEGs and parser combinators seem the most appropriate solution there. The challenge that tools primarily face here is not technological, but social: just getting programmers to not use more damned ad-hoc regex and string hacks.
  * For ultra-high quality production compilers, the choice _seems_ to be hand-written parsers. The justifications commonly cited are primarily error reporting and error recovery, and this approach manages to be practical only because the syntax doesn't change very often. However, it's unclear that this isn't a solvable problem. The greater reason these seem to be hand-written is that C++ cannot be parsed any other way. (And once you've hand-written a parser for C++, you're not far from the rest of the C family.) The developers of javac, to take a non-C family example, seem to have fallen on hand-written parsing only by necessity: they didn't have a (good enough, fast enough) parser generator, so they wrote it by hand, and now they're not sure they have an accurate grammar, but would consider transitioning to a generated parser if they did. ([some discussion here](http://mail.openjdk.java.net/pipermail/compiler-dev/2012-February/thread.html#4014))
  * For language processing experts building tools for themselves, generalized parsing DOES seem to be the way to go, in many cases! The tricky thing about GLR is that you get back an ambiguous parse forest, but this is no problem for grammar engineers to handle (it's only an obstacle for the rest of us!)  The nice thing about GLR is that it allows one to simply dump a grammar into the tool, and then immediately obtain parse forests, that you can then hack into sensibility and use them for your immediate purposes.
  * For **language extension** purposes (and note that we're talking specifically about programmers being able to pull in and use language extensions, not about grammar engineers being able to extend languages -- that's covered in the case above) the major reason for choosing LR parsing that it can give guarantees about composition of many extensions together with their host language. This is the reason for our choice of Copper in particular as our parser and scanner generator.
  * For everyone else, however, there doesn't seem to be a good reason not to use LR, and in fact that's what you see in practice: Ruby, PHP, and Python, for example. However, many of these have to resort to a few ugly hacks to work out. More on that later.


For Silver, we're less focused on general purpose programmers or even grammar hackers, and more focused on compilers, and language extension in particular. Here, LR has a few obvious advantages:

  * The parsers generated are unambiguous, no unexpected surprises that break things for users.
  * They're semantically sound - PEGs lack unordered choice, and this can cause surprises (and makes grammar definition and composition difficult: in what order?)
  * Context-aware scanning, and Copper's modular determinism analysis permit language extension while retaining these properties. Generalized parsing, even if the grammar is unambiguous to start with, can't guarantee it stays that way after being composed with extensions.
  * And of course, as a relatively minor thing, they're quite efficient.
