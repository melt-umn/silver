
Bugs
======================================================================
* It's possible that Silver constructs could be given two underscores
  in their names; however, I am using this as a separator/joiner for
  encoded names of things everywhere.  This should change, and must
  change in both the extension encoding and the theorem prover.
* There might be a bug with new associated attrs for host
  nonterminals.  If a host attr is newly associated with a host NT, we
  need to add clauses to its definition for existing prods which are
  esentially empty so the equation relation holds vacuously.  I'm not
  sure this will happen currently.
* Look into why the function relations in `imp:host` aren't being
  defined mutually---it happens to be safe here, but all the functions
  are being defined mutually in `imp:security` and they generally need
  to be, so I don't know why they aren't there.


Features
======================================================================
* Pattern matching needs to be added
* Local attributes in functions---might require not using
  `encodedExpr` for functions, or perhaps a flag for production
  vs. function
* Consider unifying away function calls with the same arguments.
  Doing this completely might require multiple passes, since
  unification might make arguments equal that were not equal before.

