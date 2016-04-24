---
layout: sv_wiki
title: Style guide and conventions
menu_title: Style Guide
menu_weight: 90.0
---

Silver is rather distinct from many other programming languages, so some pointers on style are probably useful.

The best external point of reference may be the [Scala style guide](http://docs.scala-lang.org/style/), though obviously huge swaths of it are irrelevant, and huge parts are missing.

## Indentation

For this part in particular, read the Scala guide.  The short version:

  * Two-space indentation. Tabs bad!
  * No hard 80 column limit, but you should pay attention to it!
  * If wrapping an expression using a binary operator (like ++) put the line break AFTER the operator, not before.
  * If wrapping function calls, or complex expressions, try to put line breaks earlier so that the "unit of indent" is kept small (2!) instead of very large.

```
  lhs.attr = 
    case lhs.context of
    | something(a, b) -> 
        if something_is_okay
        then workOn(a) ++ [i, j, k] ++
             workOn(b)
        else []
    | _ -> []
    end;
```

## Declaration formatting

**Production and function** declarations should always:

  * Have a newline after the identifier
  * Put the whole signature on a line or lines to itself
  * Give the opening/closing brace its own line.
  * Consider using double spaces between rhs elements in the signature, and no spaces between identifier and type.

```
function trippend
String ::= a::String  b::String  c::String
{
  return a ++ b ++ c;
}
```

**Nonterminal** declarations should make use of `with` clauses to declare attribute occurrences.  (Obviously, when extensions are introducing new attributes, they are unable to modify the host nonterminal's declaration. In that case, standalone attribute occurrence declarations are fine.)  Consider placing the newline after `with` if there is a long series of occurrences.

Attributes in the with list should be ordered: "standard" inherited attributes first, "standard" synthesized attributes second, "nonstandard" attributes third.  Be sure that any new ("nonstandard") inherited attributes are distinctive, somehow. (e.g. they are very, very good candidates for being explicitly mentioned in comments/documentation.)

```
nonterminal ProductionBody with
  grammarName, file, env, pp, location, defs, errors,
  signature, productionAttributes;
```

**Attribute** declarations should:

  * Put spaces around the `::` here.
  * Only use the `occurs on` syntax if the attribute is being declared to occur on a nonterminal is a different grammar.

```
nonterminal Pair<a b> with fst<a>, snd<b>;

synthesized attribute fst<a> :: a;
synthesized attribute snd<a> :: a;
```

**Global and local** (or "production") attributes should:

  * Also include space around the `::`.
  * Strongly consider putting a newline after the `=` sign, if the line may need wrapping.

```
global value :: String =
  "Some potentially long expression that\n" ++
  "may need wrapping.\n";
```

## Expression formatting

**If-then-else** should either be purely horizontal, or vertical with all three keywords lining up.

One exception is the common case of _error checking_, where there is an obvious trivial case.  In that case, the `then` branch can be on the same line, and check should ensure everything is okay.

```
  lhs.simple = if easy then abc else ijk;
  lhs.harder =
    if somethingDifficult
    then complexExpression
    else differentExpression;

  lhs.errors <-
    if everythingIsOkay then []
    else [err(here, "Everything is not okay?!")];
```

**Pattern matching** expressions should:

  * Always use the optional first vertical bar, for consistency, easy refactoring.
  * Line up '`case`' vertical bars and '`end`' vertically.
  * Any indentation of a case expressions should be 4 spaces in from the vertical bar (i.e. 2 spaces from the beginning of the pattern)

```
  local foo :: Type =
    case bar of
    | barBorEtum(a, b, c) -> a
    | borBarEtum(a, b, c) ->
        if a.everythingIsOkay then b else c
    | borEtumBar(a, b, c) -> c
    end;
```

## Naming conventions

  * Types are required to start with a capital letter.
  * Lexer classes should be capitalized.
  * Everything else should start with a lower case letter.
  * camelCase things, rather than use underscores
  * Try to avoid prefixing/suffixing in names.
    * e.g. name identifier terminals `Identifier` rather than `Identifier_t`.
    * e.g. name productions `ifThenElse` rather than `exprIfThenElse`
    * If it's unavoidable, be consistent!
    * Okay for things like `consStatement` and `nilStatement`, where you're writing down the abstract syntax for a list of statements. (We just don't have the language features to avoid this.)  In this case, always **prefix** nil/cons/etc.
    * When dealing with concrete and abstract syntax productions with the same name:
      * Import the abstract syntax to the concrete syntax using '`as ast`', and make sure nothing depends on the concrete syntax. This way, having duplicate names isn't an issue.
      * Always give preference to giving the abstract the "nicer" name if one must be mangled.
      * Consider not giving the concrete production a name at all!


## File structure

There is no dictated organization of files within a grammar. However, these might be useful rules of thumb:

  * Try to keep files limited to a particular nonterminal that it's named after.  e.g. `Statements.sv` might define the `Statement` and `StatementSeq` nonterminals. But not `Declaration` and certainly not `Expression`.
  * Commonly, grammar-wide module statements (e.g. `imports` with an s) are in a file that is either named after the grammar, or simply name `Project.sv`.  These should definitely not appear in more than one file in a grammar!
  * File structure should generally go: nonterminals, attributes, productions, functions. There are a multitude of reasons and situations to do otherwise, but this is a good rule of thumb.
    * Files with more than one nonterminal, for example, might repeat the "nonterminal, attributes, productions" sequence for each nonterminal.
    * Declaring a nonterminal and its productions in the middle of a sequence of productions for another nonterminal should be considered a code smell. Either push it to afterwards, or make it another file in the grammar.
  * Generally, a production declared in a grammar should not be aspected in that same grammar.  The closest I have yet to see where this might be a good idea is the definition of type representations (where there's a lot of complex, careful, core machinery and other stuff besides), and even there I'm not sure.
