---
layout: sv_wiki
title: Code organization
---

| generated/ | src and bin will be created here by default. Basically anything you find in here can be deleted. |
|:-----------|:-------------------------------------------------------------------------------------------------|
| runtime/java/ | The Silver runtime, written in Java. Should have decent JavaDocs.                                |
| support/   | Random supporting files. Scripts, syntax highlighting stuff, etc.                                |
| test/      | Tests for silver. Not a particularly good organization below it yet.                             |
| tutorials/ | Tutorials, to be distributed with Silver.                                                        |
| grammars/core/ | The always-included silver library. Probably renamed silver:prelude, someday.                    |
| grammars/lib/ | DEPRECATED. Old place to jam random libraries.                                                   |
| grammars/silver/ | where most things should be. Also the compiler. Someday, the compiler will probably be pushed under silver:compiler |
| grammars/silver/util | silver:util is deprecated, but stuff below it isn't necessarily, and some of the grammars below it should be considered "standard library" |
| grammars/silver/testing | the testing harness stuff, both the library and the binary.                                      |
| grammars/silver/langutil | standard library stuff, but specific to language processing                                      |

| grammars/silver/definition/core | the central place to look for silver compiler stuff |
|:--------------------------------|:----------------------------------------------------|
| grammars/silver/definition/ (env|type|regex|concrete\_syntax) | Silver environment, type, regular expression syntax, and the concrete syntax for concrete syntax declarations in Silver. (Don't confuse this for Silver's concrete syntax!) |
| grammars/silver/analysis/       | typechecking is in here, that's about it            |
| grammars/silver/translation     | The translation to Java is here                     |
| grammars/silver/host            | a "host language" definition for silver.            |
| grammars/silver/extensions      | pure extensions to the silver "host language"       |
| grammars/silver/modifications   | pieces of the core Silver language that are pretty self-contained, but not true, pure extensions |
| grammars/silver/composed        | Where the main function lies                        |

## FAQ

### What's the difference between extensions and modifications?

Modifications are pieces of the core of the silver language that are relatively self-contained.  Let expressions, for example, don't have a lot of implications in the wider language, or at least, not yet.

The choice of what's in modifications, and what's elsewhere at the moment is slightly arbitrary and could use a re-design.

Extensions, in contrast, are actually real, true, pure extensions that follow all the "rules" for what a language extension should be like.  Uhh... except the list extension, right now. It's breaking those rules.

### What's the point of that separate analysis grammar?

Not much. At the moment, typechecking is _basically_ the only real things in there. And it is _kinda_ nice that the substitution-state threading stuff is hidden away there, but type checking is such a core part of the language now that it makes little sense for it to be separate.

### What's the point of translation?

In Silver's past there have been several different translations, and several parser generators supported.  We're keeping that structure, even though we're only maintaining Java and Copper.

Basically, it should be designed AS IF you could choose to use other translations and other parser generators, even if none are there. It currently fails to fully live up to that goal, because a few grammars need a little bit of refactoring on that point (e.g. some modifications don't bother to separate out the java translation, but should.)

### What goes into core and what goes into definitions and what goes into modifications and ... ?

At the moment it's a bit confused.  The language has evolved a LOT, and some refactoring is needed.

Roughly, if it's a really, hugely core part of the language (nonterminals, attributes, production, equations) it goes in core.

If it's a huge cross-cutting part of the language, but is easily self-contained, it goes as its own thing in definitions. (types, the environment code, the regex grammar)

And modifications are non-cross-cutting, self-contained things that aren't quite true extensions.

The whole organization could probably use some rethinking.
