
Bugs
======================================================================
* Identify children of prods and locals not getting inh attrs defined
  on them and add an empty definition so the child eq relation is
  defined
  + For the next time I start rethink this:  Yes, I need these.  We
    need to restrict it to setting it to no value, since that is the
    semantics of Silver and sometimes we want to show things don't
    exist.
* Add child inh attr equivalent of PC theorems:
  `inh attr eq -> child inh attr`
  Also remove the PC theorems for inh attrs, maybe, since they won't
  be needed.
* It might be generating strange names for the names used in the
  Abella relation if you access an attribute by a fully-qualified
  name.  For example, `t.gr:am:mar:attr` might be generating an access
  of a value variable named `Gr$*$am$*$mar$*$attr`.  Since the user
  sees this and can't enter it in the prover, this is not good.
  + If this is happening, use the `nameToShortName` function on the
    attribute name for generating a name in the encoding of the
    access.


Features
======================================================================
* Pattern matching needs to be added
* Forwarding needs to be added as a special attribute
  + Also requires copy equations for undefined synthesized attributes
* Add production attributes, which are supported by the new scheme for
  inherited attributes.
* Local attributes in functions---might require not using
  `encodedExpr` for functions, or perhaps a flag for production
  vs. function
* Consider unifying away function calls with the same arguments.
  Doing this completely might require multiple passes, since
  unification might make arguments equal that were not equal before.

