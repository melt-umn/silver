---
layout: sv_wiki
title: Other functions
---



## toString / toInt / toFloat

```
toString ( <Expr> )
toInt ( <Expr> )
toFloat ( <Expr> )
```

_`toString`_, as the name implies, converts its argument to a string. It
can be applied to _`Integer`_s and _`Float`_s. It currently cannot be
applied to _`Boolean`_s or any other types.

_`toInt`_ and _`toFloat`_ work similarly, and on the same three types
only. _`toInt`_ will truncate floats (the same behavior as casting in C.)

> _**Note:**_ We're really sorry about the asymmetry of _`toInt`_ being abbreviated, while none of the others are.  That parser was feeling lonely again, and it has a gun.

> _**Example**_
```
toString(1)
```
> will return the string _`"1"`_.
```
toInt(1.5)
```
> will return the integer _`1`_.

## error

```
error ( <Expr> )
```

_`error`_ takes a string argument, and returns any type necessary. It should
be used to produce unexpected errors, as they immediately terminate the program
when evaluated.  Do not use this to report semantic errors in an AST; use it
for internal compiler errors.

> _**Example:**_
```
if null(something)
then error("unexpected empty list!")
else dosomething(head(something))
```
