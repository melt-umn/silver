---
layout: sv_wiki
title: Attribute declarations
---


```
synthesized attribute pp :: Document;

inherited attribute env<a> :: Map<String a>;
```

## Syntax

Attribute declarations note whether the attribute is `synthesized` or `inherited`, give a name for the attribute, and the type of the attribute.  Attributes may also be parameterized:

(`synthesized` | `inherited`) `attribute` _identifier_ `::` _type_ `;`

(`synthesized` | `inherited`) `attribute` _identifier_ `<` _type variable list_ `>` `::` _type_ `;`

All type variables that appear in the type must be declared in the parameter list.

For an explanation of the role of attributes, see [the section on attribute grammars](Concept_AttributeGrammars.md).  A deeply related syntax is the [occurs-on declaration]({{ "/ref/decl/occurs/" | prepend: site.silver_base }}).

## Autocopy attributes

For language processing, nearly all `inherited` attributes are better described as `autocopy` attributes:

`autocopy` `attribute` _identifier_ `::` _type_ `;`

Autocopy attributes behavior is simple: unless an attribute definition in a production body gives a different rule, the attribute is simply copied from the parent to all children the attribute also occurs on.

Note that currently, autocopy attributes are not permitted to be parameterized. This restriction may or may not be lifted in the future.

## Collection attributes

Attributes that may have their value influenced by aspects are called collection attributes, and are declared by giving the attribute an associated _composition operator_:

```
synthesized attribute errors :: [Message] with ++;
```

This operator must be either `++` (for lists or strings), `||`, `&&`, or any user-defined function of type `Function(a ::= a a)`.  See [collection attributes]({{ "/concepts/collections/" | prepend: site.silver_base }}).

## Convenience extensions

Attributes declarations and occurs-on declarations can be merged:

```
synthesized attribute pp :: String occurs on Expr, Stmt;
```

However, this should not be used in any circumstance where the nonterminal and the occurs-on declarations can be merged instead. (See the [nonterminal]({{ "/ref/decl/nonterminals/" | prepend: site.silver_base }}) documentation for that syntax.) For more reasons that just the stylistic: this syntax is more inflexible for parameterized attributes.

> _**Example**_: To demonstrate the inflexibility, the following code will raise an error:
```
 synthesized attribute ast<a> :: a occurs on ConcreteExpr;
```
> Because there is no place where we are able to describe what the type parameter of the `ast` attribute should be on `ConcreteExpr`.
