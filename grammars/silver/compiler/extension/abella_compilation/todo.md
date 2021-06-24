
Bugs
======================================================================
* Locals with nonterminal types need to have access relations
  generated with pair types
* Locals with nonterminal types are accessed paired with a node
  instead of a node tree
* When generating is relations for nonterminals, we need to throw in
  the WPD relation instead


Features
======================================================================
* Failure clauses for equations need to be added
* Pattern matching needs to be added
* Unification of eqMetaterms in cleaning clauses
* Unification/general cleaning for function clauses
* Special functions for pairs need to be added (`fst`, `snd`) as well
  as accessing `.fst` or `.snd` on a pair
* Local attributes in functions---might require not using
  `encodedExpr` for functions, or perhaps a flag for production
  vs. function

