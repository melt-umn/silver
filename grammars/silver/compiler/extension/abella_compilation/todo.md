
Bugs
======================================================================
* Identify children of prods and locals not getting inh attrs defined
  on them and add an empty definition so the child eq relation is
  defined
* Add child inh attr equivalent of PC theorems:
  `inh attr eq -> child inh attr`
  Also remove the PC theorems for inh attrs, maybe, since they won't
  be needed.


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

