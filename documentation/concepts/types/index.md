---
layout: sv_wiki
title: Types
---



# Types

The primitive types in Silver are:

```
String
Boolean
Integer
Float
IO
```

For more information on these primitive types, see the corresponding sections on expressions for [Booleans]({{ "ref/expr/booleans/" | prepend: site.sv_wiki_base }}), [Numeric Operations]({{ "ref/expr/numeric/" | prepend: site.sv_wiki_base }}), [Strings]({{ "lib/string/" | prepend: site.sv_wiki_base }}), and [IO]({{ "lib/io/" | prepend: site.sv_wiki_base }}).)

## Function and production types

Functions and production have types that look similar to their signatures, but with
the names removed:

```
Function ( <Type> ::= <Type> ...)
Production ( <Type> ::= <Type> ...)
```

> _**Example:**_ The following function signature:
```
function pluck
String ::= lst::String index::Integer
```
> has type
```
Function(String ::= String Integer)
```


## Lists

Lists are given a special syntax for types:

```
[ <Type> ]
```

See [Lists]({{ "lib/list/" | prepend: site.sv_wiki_base }}) for more information on lists.

> _**Example:**_ The map function would have the following signature:
```
function map
[b] ::= Function(b ::= a) [a]
```


## Nonterminals and terminals

Nonterminal declarations create a type (as do terminal declarations.) All type
names must be capitalized, as lower case names are considered type variables
(see [Type Variables](Concept_Types#Type_variables.md).)

> _**Example:**_ The following nonterminal declaration:
```
nonterminal Expr;
```
> creates the type _`Expr`_.

> _**Note:**_ Attributes with a nonterminal type are often called _`higher-order attributes`_ in the attribute grammar literature\cite{vogt89}.  For example:
```
synthesized attribute transformed :: Expr;
```
> is a "higher-order attribute."


Nonterminals additionally have a "decorated" form, whose type is simply prefixed
with the keyword _`Decorated`_.  See [Decorated vs Undecorated]({{ "concepts/decorated-vs-undecorated/" | prepend: site.sv_wiki_base }}) for more information.

> _**Example:**_
```
Expr
Decorated Expr
```
> Each of these lines is a valid, and importantly different, type.

> _**Note:**_ Attributes with a decorated nonterminal type are often called **reference attributes** in the attribute grammar literature\cite{hedin00}.  For example:
```
synthesized attribute declaration :: Decorated Dcl;
```
> is a "reference attribute."


## Type variables

Lower case type names are considered to be type variables.  Type variables are
declared in a function or production signature only (using a new type variable
name elsewhere will result in an ``undefined type" error.)

> _**Example:**_
```
function reverse
[a] ::= l::[a]   -- 'a' is a new type variable
{
  local attribute foo:[b]; -- ERROR: 'b' is not in scope
}
```

Type variables are always held abstract where they are in scope. Once something
is declared to be of type _`a`_, that cannot be refined to, for example,
_`Integer`_ as long as _`a`_ is in scope.

> _**Example:**_
```
function reverse
[a] ::= l::[a]
{
  return [1,2]; -- ERROR: 'a' is not Integer
}
```

> _**Example:**_ But, the following is perfectly okay:
```
global foo :: [Integer] = reverse([1,2,3]);
```
