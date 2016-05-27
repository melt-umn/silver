---
layout: sv_wiki
title: Equations
menu_weight: 10.0
---


```
lhs.syn = ... definition of synthesized attribute;
rhs.inh = ... definition of inherited attribute;
local.inh = ... similar;
```

# Attribute Definitions (or Equations)

Attribute definitions specify the values of attributes as some
expression over the values of attributes on neighboring nodes in the
syntax tree. In a production, definitions for synthesized attributes
of the left-hand side nonterminal and for inherited attributes on the
right-hand side nonterminals (as well as locals) may be specified.
These have the form

```
<short-name> . <name> = <expression> ;
```

### Example: 

Consider the following definitions of synthesized attributes `pp`
and `value` that decorate the `Expr` nonterminal in the grammar
`tutorials:dc`.  These are located in the `AbstractSyntax.sv` file
in that grammar. 
```
abstract production add
sum::Expr ::= l::Expr r::Expr
{
 sum.pp = "(" ++ l.pp ++ " + " ++ r.pp ++ ")";
 sum.value = l.value + r.value ;
}
```

Here we see that the string-valued `pp` attribute on the `sum` node is
computed by concatenating string literals and the value of the `pp`
attribute on the right hand side nonterminals.  Similarly, for
`value`, which is  computed by adding the values of that same
attribute on the child nodes. 

As an example of inherited attributes definitions consider the following example from the file `Stmt.sv` in the `tutorials:simple:abstractsyntax` grammar.
```
s::Stmt ::= s1::Stmt s2::Stmt 
{
  s1.env = s.env ; 
  s2.env = appendEnv(s1.defs, s.env) ;
  s.defs = appendEnv (s1.defs, s2.defs) ;
}
```

This production defines two statements (which may be compound
statements) in sequence. Here we see that the environment attribute
`env` on child `s1` is copied down to this child from its parent
`s`. For `s2`, the environment is computed from the definitions on
`s1` and the environment on `s`.  If `s1` is a variable declaration,
then that declaration information is stored in `s1.defs` and will thus
now be available to `s2`.  This follows the normal scoping rules found
in most imperative languages. 

Silver supports many types and operations over those types to write 
expressions:

+ [Numeric expressions](../../expr/numeric)
+ [Boolean expressions](../../expr/booleans), 
+ [comparison expressions](../../expr/comparisons) 
+ [string operations and built-in functions](../../../lib/string)
+ [other built-in functions](../../lib/other/)

are discussed within this Wiki.
