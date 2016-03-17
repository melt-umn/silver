---
layout: sv_wiki
title: Lists
---


```
1 :: 2 :: 3 :: 4 :: []
[1, 2, 3, 4]
cons(1, cons(2, cons(3, cons(4, nil()))))
[1, 2] ++ [3, 4]
```

## Syntax

The list type constructor is `[a]` where '`a`' is a type.

The empty list is can be written two ways:
```
[]
nil()
```

The cons operator as well:
```
<Expr :: a> :: <Expr :: [a]>
cons(<Expr :: a>, <Expr :: [a]>)
```

The append operator (`++`) is usable on lists, as is '`length`':
```
<Expr :: [a]> ++ <Expr :: [a]>
length(<Expr :: [a]>)
```

Finally, there are three more function that operate on lists:
```
null( <Expr :: [a]> )
head( <Expr :: [a]> )
tail( <Expr :: [a]> )
```

_`null`_ returns true if and only if the list is empty (i.e. nil).  _`head`_ returns the first element of the list, while _`tail`_ returns the remainder of the list, after the first element.

> _**Note:**_
> If _`head`_ or _`tail`_ are called on an empty list, an uncatchable error is raised.  **In nearly every case, pattern matching should be used instead of these functions.**

## The Colon Confusion Concern

Yes, _`::`_ is both the type operator and the cons operator in Silver.  Here's a helpful guide:

| Language | Scope resolution operator | Has type operator | list cons operator | Access operator |
|:---------|:--------------------------|:------------------|:-------------------|:----------------|
| Silver   | **:**                     | **::**            | **::**             | **.**           |
| Haskell  | **.**                     | **::**            | **:**              | _n/a_           |
| Ocaml, Scala, F# | **.**                     | **:**             | **::**             | **.**           |

It wasn't confusing enough that Haskell uses _`::`_ as the type operator and _`:`_ as the cons operator, while ML uses the exact oppose. We hope that our strategy of using one operator for both will sweep the world, though another language will inevitably use only _`:`_, just to annoy everyone in classic Bieber-like fashion. The parser is lonely, please give it some syntax errors to play with.

Alright, seriously now.  It'd be nice if we could eliminate this overlap, but we really wanted to keep the access operator and the scope resolution operator syntactically distinct.

> Consider `Foo.Bar.Baz` in Java, and that each of those names could be a package, a class, a field, or.... the only option to make sense of it is to go left-right looking up each name in turn. `Foo:Bar.Baz` is the grammar `Foo`, a value `Bar`, and the attribute `Baz`, no question.

> This becomes dramatically more important when you realize that attributes can be scoped, too.  Consider `foo:bar.foo:baz`.

Unfortunately, no other operator really seems appropriate for any of these cases.  Fortunately, there is no ambiguity in Silver, because arbitrary expressions don't need to be given explicit type annotations. For now.


---


## map
> Applies the function _`f`_ to each element of the list _`l`_, and returns the resulting list.
```
function map
[b] ::= f::Function(b ::= a)  l::[a]
```

> _**Example:**_
```
map(null, [[],[1],[]])
```
> will produce the list _`[true, false, true]`_.

## foldr
> Starting with the initial value _`i`_, the function _`f`_ is applied to the current intermediate value and the next element of the list, and the last intermediate value is returned as the result.  If the list is empty, the initial value is also the result.
```
function foldr
b ::= f::Function(b ::= a b)  i::b  l::[a]
```

> _**Example:**_
```
foldr(stringConcat, "He", ["llo", ", ", "World!"])
```
> will produce the string _`"Hello, World!"`_.

## filter
> The function _`f`_ is applied to each element list _`lst`_, and the element is included in the final list only if the function returns true. The order of elements is preserved.
```
function filter
[a] ::= f::Function(Boolean ::= a) lst::[a]
```

> _**Example:**_
```
function isSilverFile
Boolean ::= s::String
{ return endsWith(".sv", s); }

silverFiles = filter(isSilverFile, directoryContents);
```
> will assign to _`silverFiles`_ only those files that end in _`.sv`_.

## containsBy
> Returns true if an only if the element _`elem`_ is in the list _`lst`_, as determined by the equality function _`eq`_.
```
function containsBy
Boolean ::= eq::Function(Boolean ::= a a)  elem::a  lst::[a]
```

> _**Example:**_
```
containsBy(stringEq, "ABC", ["A", "B", "C"])
```
> will return _`false`_.

## last
> Returns the last element of the list. Ensure that the list is not empty, or it will raise an error (as _`head`_ does on an empty list.)
```
function last
a ::= lst::[a]
```

> _**Example:**_
```
last(["A", "B", "C"])
```
> will return _`"C"`_.

## drop / dropWhile
> Returns the list remaining after _`number`_ elements have been removed from the beginning.  If the list does not have that many elements, the empty list is returned.
> Or, returns the list remaining after the function _`f`_ returns false for the first time (including the element it returned false on.)
```
function drop
[a] ::= number::Integer lst::[a]

function dropWhile
[a] ::= f::Function(Boolean::=a) lst::[a]
```

> _**Example:**_
```
drop(2, ["A", "B", "C"])
```
> will return _`["C"]`_.

## take / takeWhile
> Returns the first _`number`_ elements of the list.  Or the whole list if there aren't that many elements in the list.
> Or, returns the list up to the first element that _`f`_ returns false on (excluding the element that returns false.)
```
function take
[a] ::= number::Integer lst::[a]

function takeWhile
[a] ::= f::Function(Boolean::=a) lst::[a]
```

> _**Example:**_
```
take(2, ["A", "B", "C"])
```
> will return _`["A","B"]`_.

## reverse
> Returns the reverse of the list.
```
function reverse
[a] ::= lst::[a]
```

> _**Example:**_
```
head(reverse(lst)) == last(lst)
```
> will always return true, or raise an error on an empty list.

## sortBy
> Sorts the list according to the comparison function _`lte`_.
```
function sortBy
[a] ::= lte::Function(Boolean ::= a a) lst::[a]
```

> _**Note:**_
> If _`lte`_ is a <= comparison, the sort will be stable and in ascending order.  If it is a >= comparison, the sort will be stable and descending.

> _**Example:**_
```
sortBy(stringLte, ["b","c","a"])
```
> will return _`["a","b","c"]`_.
