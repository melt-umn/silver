
Bugs
======================================================================
* Generation of missing clauses for components doesn't include
  component in name of relation being defined
* Generation of missing clauses for components doesn't produce enough
  arguments; need an argument for the top tree name
* Some full equations are getting output twice
* Production check needs to be put back in `functionInvocation`
* Locals with nonterminal types need to have access relations
  generated with pair types
* Locals with nonterminal types are accessed paired with a node
  instead of a node tree
* Underscore terms are apparently not actually a thing
* Some component relations are ending up split into two different
  definitions
* When generating is relations for nonterminals, we need to throw in
  the WPD relation instead


Features
======================================================================
* Failure clauses for equations need to be added
* Pattern matching needs to be added
* Unification of eqMetaterms in cleaning clauses
* Special functions for pairs need to be added (`fst`, `snd`) as well
  as accessing `fst` or `snd` on a pair
* Local attributes in functions---might require not using
  `encodedExpr` for functions, or perhaps a flag for production
  vs. function

