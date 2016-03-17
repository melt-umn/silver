---
layout: sv_wiki
title: Maybe
---


```
n.errors := case n.lookupName of
            | nothing() -> ["error!"]
            | just(_) -> []
            end;
```

# Maybe

Silver does not have 'null' values, unlike languages such as Java or
C++, as these are a constant source of errors.  Instead,
if one wishes for a value to potentially be absent, that must be reflected in its
type.  The _`Maybe`_ type accomplishes this.

The type is written _`Maybe<a>`_ where _`a`_ is a type.

The constructors of this type are:
```
abstract production just
top::Maybe<a> ::= v::a

abstract production nothing
top::Maybe<a> ::=
```

where _`nothing`_ represents null, and _`just`_ is used otherwise.

Information is extracted from a Maybe using two attributes.  _`isJust`_
determines whether there is information present or not.  _`fromJust`_ returns
the value wrapped up inside the Maybe, assuming it is a just.  (Otherwise, an
unrecoverable error occurs, similar to asking for the head of nil.)

> _**Example:**_
```
function fromMaybe
a ::= otherwise::a ifJust::Maybe<a>
{
  return if ifJust.isJust
         then ifJust.fromJust
         else otherwise;
}
```
> This function will return the wrapped value from a Maybe if it is present, and if not, will return the default value specified.

The above example is actually a standard function for operating on Maybes.
