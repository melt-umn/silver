
Bugs
======================================================================


Features
======================================================================
* Pattern matching needs to be added
* Forwarding needs to be added as a special attribute
  + Also requires copy equations for all inherited attributes and
    undefined synthesized attributes
* Local attributes in functions---might require not using
  `encodedExpr` for functions, or perhaps a flag for production
  vs. function
* Consider unifying away function calls with the same arguments.
  Doing this completely might require multiple passes, since
  unification might make arguments equal that were not equal before.

