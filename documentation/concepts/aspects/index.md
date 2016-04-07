---
layout: sv_wiki
title: Aspect productions
---

# Aspect Productions

Aspect productions provide a means for writing attribute definitions
for a production outside of the definition of that production.  Their
form is similar to abstract and concrete productions in which the
_`abstract`_ or _`concrete`_ modifier is replaced by the
_`aspect`_ modifier; that form is
```
aspect production <name> <prod-sig> { <prod-body> } 
```
The name in the aspect production specification must match the name of
a concrete or abstract production that is imported or defined in the
same module as the aspect production.  We refer to this concrete or
abstract production declaration and definition as the _original_
production in the discussion below.  The production signature in the
aspect must specify the same production type as the original
production.  However, the names of left and right hand side elements
may differ from those in the original production.

Aspect productions can refer to attribute values defined in the
original production or in other aspects on that production.  The only
restriction, naturally, is that the visibility of these other
attributes follows the regular scoping rules of Silver and thus one
may need to import the appropriate grammar module to access an
attribute defined in the original production or in another aspect.

Local attributes defined in an aspect are only visible in that aspect
and, thus, aspects cannot see local attributes defined in the original
production or in other aspects of the production.

The semantics of an aspect are as if the declarations and definitions
in the aspect were included in the original production
(modulo any renaming that occurred in the aspect and renaming needed to
avoid name clashes from any aspect-introduced local attributes).
However, since Silver supports separate compilation of grammar
modules we do not weave all the aspects together at compile-time. Type
checking and code generation for grammar modules, and any aspect
productions they include, are done independently with
only a bit of overhead at run-time to accommodate this.


> _**Example:**_ In the tutorial grammar _`dc`_ a collection of aspect productions are given in the file _`BetterPP.sv`_.  These aspects provide definitions for new attributes (also declared in the same file) that compute a better version of the pretty print attribute _`pp`_ defined in _`AbstractSyntax.sv`_ that does not include the sometime unnecessary parenthesis that are used in _`pp`_.

> An aspect for the abstract production _`add`_ is shown below:
```
aspect production add
sum::Expr ::= l::Expr r::Expr
{
 sum.bpp = if wrapInParens ( sum.enclosingOpPrecedence, 1,
                             sum.leftOrRight, "left" )
           then "(" ++ l.bpp ++ " + " ++ r.bpp ++ ")" 
           else l.bpp ++ " + " ++ r.bpp ;

 l.enclosingOpPrecedence = 1 ;
 r.enclosingOpPrecedence = 1 ;
 l.leftOrRight = "left" ;
 r.leftOrRight = "right" ;
}
```
> Other than the use of "_`aspect`_" instead of "_`abstract`_" this production looks like any other production definition.  It defines a synthesized attribute (_`bpp`_) and two inherited attributes (_`enclosingOpPrecedence`_ and _`leftOrRight`_).  Since the constructs declared in _`AbstractSyntax.sv`_ are visible in this file, this aspect could use the value of the _`pp`_ attribute defined on in the original _`add`_ production in the file _`AbstractSyntax.sv`_, though it makes little sense to do so in this example.

> Although this aspect uses the same names for the left and right hand side elements (_`sum`_, _`l`_, and _`r`_) as used in the original production we could have changed, for example, _`sum`_ to _`s`_ throughout and it would have the same effect.
