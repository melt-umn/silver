---
layout: sv_wiki
title: Collections
---

# Collection Attributes

Attributes can optionally be specified as being _collection_ attributes.  Such attributes allow aspects to contribute values that are used to compute the final value of the attribute.  All attributes (synthesized, inherited, local, and production) can be collection attributes by indicating an operation to be used to combine the contributed values of the attribute.  Such attribute declarations have the form

```
<attr-kind> attribute <short-name> :: <type> with <op> ;
```

where _`<attr-kind>`_ is **synthesized**, **inherited**, **local**, or **production** and _`<op>`_ is a Silver operator or (possibly, user defined) function with the type (_`<type>`_, _`<type>`_) `->` _`<type>`_).  This operator or function combines contributed values of the attribute; it is not unlike the function passed to a _fold_ function in functional programming.

Collection attributes must use the two collection assignment operators _`:=`_ and _`<-`_; the use of the regular assignment operator _`=`_ is not allowed. The first assignment operator, _`:=`_, is used to assign a base value to the collection attribute.  This value is similar to the initial or ```zero'' value passed to a fold function. The second assignment operator, _``<-`_, contributes additional values to the final value of the attribute.  These values are combined with, or folded into, the base value and other contributed values using the operator.   Such contributions are often written in aspect productions.  Because the order in which values from different contributions are combined in not specified the operator should be commutative._

> _**Example:**_ In the tutorial grammar _`simple:abstractsyntax`_, the file _`Expr.sv`_ contains the declaration of the attribute _`errors`_, which is declared as a collection attribute as follows:

```
synthesized attribute errors :: [String] with ++;
```

> The errors attribute is a list of string values whose various contributions and base value are combined using the list-concatenation operator _`++`_.

> In the same file, the _`add`_ production defines the base value to be the combination of the errors attribute on its two children.

```
abstract production add 
e::Expr ::= l::Expr r::Expr 
{
  e.errors := l.errors ++ r.errors ;
}
```

> In the file _`TypeChecking.sv`_ an aspect on _`add`_ checks that the type on each child is numeric by calling the helper function _`isNumeric`_.

```
aspect production add
e::Expr ::= l::Expr r::Expr 
{
  e.errors <- (if isNumeric(l.type) then [ ]
               else [ "Expression \"" ++ l.pp ++ 
                      "\" must be of type Integer or Float.\n" ]) ++
              (if isNumeric(r.type) then [ ]
               else [ "Expression \"" ++ r.pp ++ 
                      "\" must be of type Integer or Float.\n" ]);
}
```

> The final value of _`errors`_ on a tree node constructed by the _`add`_ production will contain any errors defined on its children (including type errors computed by aspects on the productions defining the children) and any type errors computed in the aspect above.
