
Bugs
======================================================================
* There might be a bug with new associated attrs for host
  nonterminals.  If a host attr is newly associated with a host NT, we
  need to add clauses to its definition for existing prods which are
  esentially empty so the equation relation holds vacuously.  I'm not
  sure this will happen currently.
  + This is a bit trickier of a situation than I thought.  Suppose we
    have the host grammar `H` introducing nonterminal `Nt` and
    inherited attribute `i` where `i` does not occur on `Nt`.  We add
    extensions `A` and `B` where both add new productions building
    `Nt` and setting `i` on a child.  This makes `i` an associated
    attribute for `Nt` in both.  Both extensions need to introduce
    encoded relations for this new attribute, but they need to be
    consistent.  Two options:
    - Both extensions introduce the same full relation.  It will have
      the same name and the same type, which will silently cause an
      error in the background in the theorem prover if both `A` and
      `B` are imported together in a further grammar, but the user
      will never know unless Abella somehow changes (theoretically
      possible, but unlikely with how we are using it).  Both
      extensions also introduce a component relation claiming to be
      from the host which says all the host productions can do
      whatever they want.  This should also silently cause an error in
      the background if both extensions are imported together in the
      future, but the two relations should be identical, so it
      shouldn't have an actual semantic effect.  Finally, both
      extensions introduce component relations for the productions
      they introduce.  This way the shared things are consistent and
      the relations are fully defined.  Note this approach will also
      require adding the appropriate primary component theorems, which
      might cause more silent errors when we try to add the same
      theorem a second time.
    - A grammar introduces all the full relations and component
      relations for any inherited attribute and nonterminal
      combination, even if it isn't actually set.  This means `H` will
      introduce the full relation for associating `i` with `Nt` and a
      component relation for all its productions where none of them
      are constrained.  Then `A` and `B` just add their component
      relations for this associated attr and everything is covered.
      If they introduce any new inherited attributes, they would also
      create the basics of any associated-attribute relations for them
      on any host nonterminals, and the same for any new nonterminals
      with existing inherited attributes, as well as the combinations
      of new inherited attrs and new nonterminals.  This should ensure
      everything is filled in, albeit with a lot of extra relations
      which are never used.  It might interfere with telling what
      attributes occur where, but I'm not sure (that could be fixed
      with another information-passing constant like we use for
      inherited attributes).


Features
======================================================================
* Pattern matching needs to be added
* Local attributes in functions---might require not using
  `encodedExpr` for functions, or perhaps a flag for production
  vs. function
* Consider unifying away function calls with the same arguments.
  Doing this completely might require multiple passes, since
  unification might make arguments equal that were not equal before.

