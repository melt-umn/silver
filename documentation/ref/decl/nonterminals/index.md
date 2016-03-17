---
layout: sv_wiki
title: Nonterminal declarations
---


```
nonterminal Expression;

nonterminal List<a>;
```

## Syntax

Nonterminals are declared using the keyword `nonterminal` followed by a name, and an optional type parameter list.

`nonterminal` _Identifier_ ; <br />
`nonterminal` _Identifier_ < _type variable list_ > ;

The identifier, like all type names in Silver, must start with a capital letter.  The type variables must be lower case.

## Convenience extension

An extension allows a nonterminal declaration to include a comma-separated list of attribute occurrences using the `with` keyword.  For example:

```
nonterminal Expression with pp, env, errors;

nonterminal Pair<a b> with fst<a>, snd<b>;
```

The use of this extension is highly encouraged.

## Analogy to data types

Nonterminals in Silver are somewhat similar to datatypes in other functional languages. The major initially noticeable difference is that Silver does not require a fixed list of constructors (and we use different names, so we call them [productions]({{ "/ref/decl/productions/" | prepend: site.sv_wiki_base }}).)

Similarly, nonterminals are somewhat like abstract class declarations in other object-oriented languages. The major initially noticeable difference is the lack of a fixed list of (virtual) methods (and in Silver they're called [attributes]({{ "/ref/decl/attributes/" | prepend: site.sv_wiki_base }}) and must be declared to [occur]({{ "/ref/decl/occurs/" | prepend: site.sv_wiki_base }}) on the nonterminal.)

## Concrete Syntax

Nonterminal declarations are used for all data representation in Silver, including concrete syntax.  Nothing specific to the nonterminal designates whether it is a "concrete syntax nonterminal" or not.  The only distinction is whether or not any `concrete` productions are declared for a nonterminal.  If not, then it's not considered part of the concrete syntax.

## Closed nonterminals

These should be used sparingly for representing languages.

Fundamentally, there is a choice in how we should achieve composable extensions: should we permits new semantics openly, and require new syntax to be defined in terms of semantically equivalent syntax? (i.e. forwarding) For nearly all AST types, the answer is yes. But for some, and for many programming tasks, the answer is the opposite: we should permits new syntax openly, and require any new semantics be defined in terms of existing semantics.

Closed nonterminals allow the latter. Beginners should typically **only use them for concrete syntax** at first. (As concrete syntax typically just constructs an abstract syntax, and so we do not need open semantic extensibility.)

The only practical effect of closed nonterminals is how it affects the [modular well-definedness analysis]({{ "/concepts/modular-well-definedness/" | prepend: site.sv_wiki_base }}).
