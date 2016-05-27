---
layout: sv_wiki
title: Forwarding equations
menu_weight: 20.0
---

# Forwarding

Forwarding is a mechanism for providing default values for attributes that we introduced to attribute grammars that is especially useful for defining language extensions.

A production can specify a semantically equivalent tree that it will provide default values for any attributes that are not explicitly defined on that production.  Thus, the type of the forwarded-to tree must be the same as the nonterminal on the left-hand side of the production.

Any inherited attributes assigned to the forwarding-tree are automatically copied to the forwarded-to tree.

> _**Example:**_ An example will help to clarify.  Consider the definition of a greater-than-or-equal operator _`>=`_ found in the _`Expr.sv`_ file of the Simple tutorial's abstract syntax.
```
abstract production gte 
e::Expr ::= l::Expr r::Expr 
{
  e.pp = "(" ++  l.pp ++ " >= " ++ r.pp ++ ")" ;
  forwards to not(lt(l,r)) ;
}
```
> This productions defines the value of its _`pp`_ attribute but does not have a definition of the attribute _`c_code`_ to define its translation to C code.  When a node in the syntax tree constructed by the _`gte`_ production is queried for the value of its _`pp`_ attribute it will compute it based on the explicit definition.  If that node is queried for the value of its _`c_code`_ attribute however it will "forward" that query to the tree _`not(lt(l,r)`_.  This tree is the semantic equivalent of the _`gte`_ tree ( `(l >= r) = -(l < r)` ).

> If inherited attributes, for example _`env`_ are used in the computation of _`c_code`_, then those attributes must be available on the forwards to tree.  This is accomplished automatically as any inherited attribute that decorates node _`e`_ is copied to the forwards to node.

A production can specify the values of inherited attributes for the forwarded to tree.  To do so, the forwards clause is augmented with attribute definitions and has the following form
```
forwards to <expression> with { ( <name> = <expression> ; )* } ;
```

## Common forwarding patterns

The primary purpose of forwarding is to enable safe extension of a host language. Forwarding permits one extension to safely add new attributes to the host language, while another extension adds new productions. This is accomplished by simple evaluating the attribute on the forwarded-to tree for those new productions.

But, forwarding is often used in a few other common ways:

### Single dispatch via forwarding

Single-dispatch based on types, for example, is easy to implement using forwarding. Types can be given an attribute like the following:

```
synthesized attribute lengthDispatcher :: Production(Expr ::= Decorated Expr);
```

Using the inheritance method of the previous section, the default type can set this attribute to an error production, and relevant types (such as strings and lists) can override that with a production specialized to their types.

Then the syntax itself can be implemented as follows:

```
abstract production lengthFunction
top::Expr ::= arg::Expr
{
  forwards to arg.type.lengthDispatcher(arg);
}
```

> _**Note:**_ Although it is unlikely that calls to length would be nested too deeply, see [Decorated vs Undecorated Examples](Concept_DecVsUndec#Examples.md) for an explanation for why the production attribute has a decorated child.

### Forwarding to a higher order attribute

Complex extensions will often forward to a higher order attribute.  For example,

```
synthesized attribute hostTranslation :: Expr;

abstract production extensionMagic
top::Expr ::= 'magic' m::ComplexNewSyntax
{
  forwards to m.hostTranslation;
}
```

Here, the "semantically equivalent" tree is computed based on the code.

## TODO

Part of this page should be on the forwarding concept page, and this trimmed down to just reference material.
