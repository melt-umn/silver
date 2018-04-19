---
layout: sv_wiki
title: Modular well-definedness
---

## Running the analysis

Run silver using the `--clean` and `--warn-all` flags.

```
silver --clean --warn-all my:project:grammar
```

Note that `--clean` is not, strictly speaking, necessary, but the build system does not currently dirty enough grammars by default, and you may unexpectedly miss warnings from grammars it decides do not need to be re-analyzed.

Running the analysis will approximately double the Silver part of compile times... so perhaps a 40% increase over all.  This will be fixed eventually.  It's just naively implemented at the moment.

### Getting old code to satisfy the analysis

If you're overwhelmed by warnings, you can fix them in the following order to keep things more sensible:

```
--warn-orphaned --warn-fwd
--warn-eqdef
--warn-missing-syn
--warn-missing-inh
```

That is, fix the first, then add the next line to your compiler flags, etc.  (We may actually build this ordering into the analysis eventually...)  Always include `--clean`, of course.

The first line of warnings should be independent of each other. The next two flags enable warnings that could appear / disappear as earlier types of problems are discovered.  The last warnings flag is a sort-of global analysis, so it can have oddly non-local effects (or get spammy) if you still have earlier warnings in the code.

## Understanding the analysis in three easy steps

  1. A fundamental notion of the analysis is "**exported by**."  We consider the defining features of a declaration to be those things you always must have in the environment if you have that declaration in the environment.
  1. The expression problem must be solved: only production **exported by** a nonterminal can be non-forwarding, everything else is considered an extension (and thus, must forward to solve the expression problem, e.g. ensure semantics from extension A are defined on new syntax from independent extension B.)
  1. Synthesized attributes are "sorta" methods, and inherited attributes are "sorta" just method parameters. AG notation is wonderful in that this is inferred, but the analysis essentially dictates that whatever inherited dependencies that appear in equations that are **exported by** an attribute occurrence defines the set of inherited attributes usable by that attribute.  In essence, errors about "exceeding the flow type" can be read as "this method does not have that parameter."

## Interpreting the warnings

In the following explanations we'll say X "is exported by" Y, if Y appears in a grammar that is exported (or the same) grammar that X appears in.

### Orphaned occurs

Occurs declarations must be exported by EITHER the attribute declaration or the nonterminal declaration.

Most likely, the declaration should be moved to one of those grammars.

### Orphaned equation

Equations (that is, `lhs.pp = "hi";` inside a production block) must be exported by either the occurs declaration (that is, `attribute pp occurs on Expr`) or by the production declaration (aspects do not count, of course.)

Most likely, the equations should be moved to one of those grammars.

### Orphaned production

Productions should either by exported by their associated nonterminal declaration, or they should forward.

Most likely, the production should forward, or be moved into the "host language" that is declaring the nonterminal.

Or...

#### Other options

Nonterminals that are purely concrete syntax, and not abstract, that only have, say an `ast` attribute on them can be declared closed (`closed nonterminal Expr_c;`) and then the production does not need to forward.  This only really applies to concrete syntax (until you know what you're doing!) because it prevents any new attributes from occurring on it (which is fine when all you need is `ast`.)

If the production does NOT have anything it can validly forward to, then either it is NOT a valid extension (according to Silver's notion of extension) or the host language must be changed somehow.  Optional components of a host language can be permitted by adding an optional export to the host language. (That is, host will add `option some:modification;`.)

### Missing synthesized equation

Productions that do not forward must have equations specifying the value of every attribute that occurs on its associated nonterminal.  Simply add the equation. It's missing.

### Missing inherited equation

This is slightly trickier.  Silver will not require that all inherited attributes be supplied, as this is not possible in a modular way.  Instead, it requires inherited equations be present that it suspects are necessary to evaluated (for example) synthesized attributes that are demanded.

Generally, the solution to these warnings is to add an equation for the inherited attribute.  However, in some cases, the real solution may be to determine why Silver believes it needs an inherited attribute that you are certain it does not. This can be somewhat tricky, as the culprit could be anywhere.  There does exist a `--dump-flow-graph` flag that produces a dot file, however this is typically too large to actually visualize.  It might be useful to grep, however.

## Tracking down flow problems

Flow type errors have an annoying tendency to be non-local.

Invoking silver with the `--dump-flow-graph` flag can be helpful in trying to determine what's going on. This generates two files. `flow-types.dot` has flow type information for nonterminals, and `flow-deps-transitive.dot` has flow graph information for productions, with `flow-deps-direct.dot` containing only immediate dependencies (i.e. unsolved graphs.)  This latter might be useful occasionally in trying to track down the "culprits" causing unwanted dependencies.

I recommend trying to grep through these to find the information you want. For example, if you're just trying to debug the flow type for forward equations, you can use:

```
egrep "(\{|\}|\"[A-Za-z:_0-9]*/forward\" ->)" flow-deps.dot
```

If you want to find all lines that are deps of synthesized equations only, us:

```
egrep -v ".*/.*/.*->" flow-deps.dot
```
