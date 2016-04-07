---
layout: sv_wiki
title: Pretty printing
---



# Required Code for Use
The following import statements are required for using the pretty print extension.

```
imports silver:langutil;
imports silver:langutil:pp;
```

To declare a pretty print attribute on a nonterminal, attach it using a 'with' clause. For example, to add a pretty print attribute to a nonterminal called Root, add 'with pp' to the declaration of Root.

```
nonterminal Root with pp;
```

# Show
To return the string equivalent of a attribute, use the function show with the width of the string and the pp attribute itself.

```
abstract production root
r::Root ::= e::Expr
{
  local attribute s::String;
  s = show(100,r.pp);

  ...
}
```

# PP Constructors
The examples below show the document function and the strings they represent.
Ex:
```
pp_constructor(params) = "string equivalent"
```


## Literals
  * ` text("foo") = "foo" `

## Punctuation
  * ` space() = " " `
  * ` semi() = ";" `
  * ` comma() = "," `
  * ` braces(foo) = "{" ++ foo ++ "}" `
  * ` parens(foo) = "(" ++ foo ++ ")" `
  * ` brackets(foo) = "[" ++ foo ++ "]" `
  * ` notext() = "" `

In general, these should NOT be preferred over just using that punctuation directly! These are useful only in situations where it make sense and it keeps things simpler.

For example, `implode(comma(), list)` is okay, but as soon as you need a space, it's better to just write `implode(text(", "), list)` instead. Whatever you do, never write `implode(cat(comma(), space()), list)` because that's just horrible.

## Concatenation
  * ` cat(foo, bar) = foo ++ bar `
  * ` concat([ text("foo"), text("bar"), text("test")]) = "foobartest" ` Folds a list of Documents together using cat
  * ` implode(text("|"), [text("foo"), text("bar"), text("test")]) = "foo|bar|test" ` Places a string between each element of a list of strings.
  * ` terminate(text("|"), [text("foo"), text("bar"), text("test")]) = "foo|bar|test|" ` Similar to implode. Places a string between each element of a list of strings AND at the end.

## Grouping, newlines, and indentation
  * `group(inner)` creates a new _indentation group_ for `inner`. That is, every occurrence of `line()` inside `inner` that isn't itself contained by an intervening `group` will either ALL be horizontal (spaces) or all be vertical (newlines.) Every Document begins with an initial, implicit, outer group that is automatically vertical.
  * `line()` is a **potential** newline point, or a space if the group it belongs to decides to be horizontal instead. Note that in addition to becoming a newline, it will also emit a number of space equal to the current indentation level.
  * `nest(depth, inner)` increases the current indentation level for all `line()`s in `inner`. Note that it's for **all** of them, `nest` does not just affect the current group or anything like that.

## Common pattern helpers
  * `groupnest(depth, inner)` performs both a group and a nest operation at once.
  * `nestlines(depth, inner)` gets right an extremely common pattern that's slightly tricky to do. This construct nests `inner` and puts a newline before and after it. The before newline is inside the nest, but the after newline is OUTSIDE the nest. This is the correct behavior for, for example, nesting statements.
  * `softbreak()` is equivalent to `group(line())`. That is, it's a potential place for a newline, but can be taken without committing to turning anything else into newlines versus spaces.
  * `box(inner)` is equivalent to a `nest` but sets the indentation level to whatever the current indentation is. This makes it easier to line things up vertically at an unknown resulting indentation level.

## Two pieces of advice

  * Do not use `group` at all until you get to, say, expressions. Creating the option to place, for example, statements horizontally is usually a very bad idea.
  * Use `nestlines` because it gets indentation right.

As an example, to correct indent something like the braces after an "if statement" you would write:

` braces(nestlines(2, implode(line(), stmts))) `


This would produce outputs like:

```
if(expr) {    <--- this opening brace is where the above starts
  stmt1;      <--- this is why the first newline is *inside* the nest
  stmt2;      <--- the 'implode' does NOT put the newline here, nestlines does.
}             <--- the last newline is *outside* the nest, and followed by }
```


if(expr) {    <--- this opening brace is where the above starts
  stmt1;      <--- this is why the first newline is *inside* the nest
  stmt2;      <--- the 'implode' does NOT put the newline here, nestlines does.
}             <--- the last newline is *outside* the nest, and followed by }
}}}```
