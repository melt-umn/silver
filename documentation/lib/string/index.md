---
layout: sv_wiki
title: String operations
---


```
"[" ++ implode(", ", contents) ++ "]"

substring(0, length(s) - 1, s)
```

## String Operations

String literals are written in double quotes, and give the standard special meaning to the following escape characters:

```
\\ \r \n \t
```

There is one special operator that applies to strings, the append operator:

```
<Expr> ++ <Expr>
```

Silver will efficiently evaluate string appends, so this is the standard method of composing strings together.

In addition there is a special function for determining lengths
that works with strings:

```
length ( <Expr> )
```

## Built-in string functions

All of the following string manipulation function are in the core library. For absolutely up-to-date documentation, you can see `String.sv` in core, distributed with Silver.


**implode**
```
function implode
String ::= sep::String lst::[String]
```

> Fold a list of strings (`lst`) into one string, by interspersing a separator (`sep`).

> _**Example:**_
```
implode(", ", ["Hello", "World!"])
```

> will produce the string `"Hello, World!"`.

<br />
**explode**
```
function explode
[String] ::= sep::String str::String
```

> Split a string (`str`) into a list of strings by a separator (`sep`). If the separator is the empty string then the string is split into single character strings.

> _**Example:**_
```
explode("/", "/path/to/file")
```

> will produce the list of strings `["", "path", "to", "file"]`.

<br />
**indexOf**
```
function indexOf
Integer ::= needle::String haystack::String
```

Find the index of a needle in the haystack.  (Indices are 0-based.) Or -1 if not found.

> _**Example:**_
```
indexOf("/", "/path/file")
```

> will produce the integer 0.

```
indexOf("/", "C:\path\file")
```

> will produce the integer -1.

<br />
**lastIndexOf**
```
function lastIndexOf
Integer ::= needle::String haystack::String
```

Find the LAST index of a needle in the haystack.  (Indices are 0-based.) Or -1 if not found.

> _**Example:**_
```
lastIndexOf("/", "/path/file")
```

> will produce the integer $5$.

```
lastIndexOf("/", "C:\path\fil")
```

> will produce the integer $-1$.


**substring**
```
function substring
String ::= start::Integer endl::Integer str::String
```

Return a substring of the original.  Indices are 0-based.  The first index is inclusive, but the last index is exclusive.

> _**Example:**_
```
substring(1, 5, "/path/file")
```

> will produce the string \texttt{"path"}.

```
-- with str = "/path/file"
substring(indexOf("/", str) + 1, lastIndexOf("/", str), str)
```

> will produce the string `"path"`.


**startsWith / endsWith**
```
function startsWith
Boolean ::= pre::String s::String

function endsWith
Boolean ::= post::String s::String
```

Tests if one string is a prefix (or suffix) of another string `s`.

**isDigit / isAlpha / isSpace / isLower / isUpper**
```
function isDigit
Boolean ::= str::String
```

(All of these functions have the same signature.)

Tests if all characters of a string are digits
(or letters, whitespace, uppercase, or lowercase.)  May in practice be unicode
aware, but this behavior hasn't quite been finalized yet.
