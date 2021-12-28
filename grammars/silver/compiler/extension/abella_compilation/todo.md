
Bugs
======================================================================
* Component theorems are not being generated for host attributes on
  host nonterminals for extension productions (should have names like
  `$imp$*$host$*$env__$nt_imp$*$host$*$C__$prod_imp$*$host$*$noop`),
  and not being generated for new attrs on existing prods


Features
======================================================================
* Pattern matching needs to be added
* Local attributes in functions---might require not using
  `encodedExpr` for functions, or perhaps a flag for production
  vs. function
* Consider unifying away function calls with the same arguments.
  Doing this completely might require multiple passes, since
  unification might make arguments equal that were not equal before.

