
Bugs
======================================================================
* Make it output acceptable Abella code for relations:  `varTerm` is
  not being replaced by a `nameTerm`, which I think might be done in
  `RootSpec.sv`; can check where by looking at `synAttrEqInfo`
  processing
* Remove inh attrs from local equations
* Identify children of prods and locals not getting inh attrs defined
  on them and add an empty definition so the child eq relation is
  defined
* Add child inh attr equivalent of PC theorems:
  `inh attr eq -> child inh attr`


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

