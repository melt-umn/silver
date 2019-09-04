---
layout: sv_wiki
title: Equations
---


```
lhs.syn = ... definition of synthesized attribute;
rhs.inh = ... definition of inherited attribute;
local.inh = ... similar;
```

# Attribute Definitions (or Equations)

Attribute definitions specify the values of attributes as some
expression over the values of attributes on neighboring nodes in the
syntax tree. In a production, definitions for synthesized attributes of the left-hand side nonterminal and for inherited attributes on the right-hand side nonterminals (as well as locals) may be specified.  These have the form
```
<short-name> . <name> = <expression> ;
```
> _**Example:**_ Consider the following definitions of synthesized attributes _`pp`_ and _`value`_ that decorate the _`Expr`_ nonterminal in the grammar _`tutorials:dc`_.  These are located in the _`AbstractSyntax.sv`_ file in that grammar.
```
abstract production add
sum::Expr ::= l::Expr r::Expr
{
 sum.pp = "(" ++ l.pp ++ " + " ++ r.pp ++ ")";
 sum.value = l.value + r.value ;
}
```
> Here we see that the string-valued _`pp`_ attribute on the _`sum`_ node is computed by concatenating string literals and the value of the _`pp`_ attribute on the right hand side nonterminals.  Similarly, for _`value`_, which is  computed by adding the values of that same attribute on the child nodes.

> As an example of inherited attributes definitions consider the following example from the file _`Stmt.sv`_ in the _`tutorials:simple:abstractsyntax`_ grammar.
```
s::Stmt ::= s1::Stmt s2::Stmt 
{
  s1.env = s.env ; 
  s2.env = appendEnv(s1.defs, s.env) ;
  s.defs = appendEnv (s1.defs, s2.defs) ;
}
```
> This production defines two statements (which may be compound statements) in sequence. Here we see that the environment attribute _`env`_ on child _`s1`_ is copied down to this child from its parent _`s`_. For _`s2`_, the environment is computed from the definitions on _`s1`_ and the environment on _`s`_.  If _`s1`_ is a variable declaration, then that declaration information is stored in _`s1.defs`_ and will thus now be available to _`s2`_.  This follows the normal scoping rules found in most imperative languages.

Silver supports many types and operations over those types to write
expressions. [Numeric expressions]({{ "/ref/expr/numeric/" | prepend: site.sv_wiki_base }}), [boolean expressions]({{ "/ref/expr/booleans/" | prepend: site.sv_wiki_base }}), [comparison expressions]({{ "/ref/expr/comparisons/" | prepend: site.sv_wiki_base }}), [string operations and built-in functions]({{ "/lib/string/" | prepend: site.sv_wiki_base }}), and [other built-in functions]({{ "/lib/other/" | prepend: site.sv_wiki_base }}) are discussed within this Wiki.
