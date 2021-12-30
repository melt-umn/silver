
Bugs
======================================================================
* There might be a bug with new associated attrs for host
  nonterminals.  If a host attr is newly associated with a host NT, we
  need to add clauses to its definition for existing prods which are
  esentially empty so the equation relation holds vacuously.  I'm not
  sure this will happen currently.


Features
======================================================================
* Pattern matching needs to be added
* Local attributes in functions---might require not using
  `encodedExpr` for functions, or perhaps a flag for production
  vs. function
* Consider unifying away function calls with the same arguments.
  Doing this completely might require multiple passes, since
  unification might make arguments equal that were not equal before.

