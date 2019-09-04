---
layout: sv_wiki
title: Terminal expressions
---


```
-- earlier:
terminal Identifier /[A-Za-z]/;

abstract production foo
e::Expr ::= f::'foo'
{
  -- each of these lines does the same thing:
  forwards to id(terminal(Identifier, "foo")); -- except here line & column are -1
  forwards to id(terminal(Identifier, "foo", f));
  forwards to id(terminal(Identifier, "foo", f.line, f.column));
}
```

# Terminals

The _`terminal`_ special form is the constructor for terminals:

```
terminal ( <Type> , <Expr :: String> )
terminal ( <Type> , <Expr :: String> , <Expr :: Integer> , <Expr :: Integer> )
terminal ( <Type> , <Expr :: String> , <Expr> )
```

The first parameter is the type of the terminal to create. The second parameter is the lexeme of the terminal to create.  Silver makes no restriction that the lexeme must match the regular expression the terminal was declared with.

In the second form, the line and column values for the terminal are provided. In the first form, these attributes will be given the value -1. In the third form, the terminal simply obtains these attributes from another terminal (of any type, not only the same type of terminal.)

## Easy terminal extension

Terminals declared using single quotes, rather than a regular expression, may be constructed quickly in the same manner:

```
... foo('foo') ... -- constructs the foo production above, and a terminal 'foo' to give as its child
```

## Terminal attributes

There are four terminal attributes:

```
<Expr> . lexeme
<Expr> . filename
<Expr> . line
<Expr> . column
```

Both _`lexeme`_ and _`filename`_ are _`String`_s, while _`line`_ and _`column`_ are _`Integer`_s.  The file name comes from the second parameter passed to the parse function. (See \ref{sec:copper:parser}.)
