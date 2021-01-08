grammar silver:compiler:analysis:typechecking:core;

inherited attribute contextLoc::Location occurs on Contexts, Context;
inherited attribute contextSource::String occurs on Contexts, Context;
monoid attribute contextErrors::[Message] with [], ++ occurs on Contexts, Context;
propagate contextLoc, contextSource, contextErrors on Contexts;

aspect production instContext
top::Context ::= cls::String t::Type
{
  requiredContexts.contextLoc = top.contextLoc;
  requiredContexts.contextSource = s"the instance for ${prettyContext(top)}, arising from ${top.contextSource}";
  
  -- Duplicates are checked at the instance declaration
  top.contextErrors :=
    if null(top.resolved)
    then [err(top.contextLoc, s"Could not find an instance for ${prettyContext(top)} (arising from ${top.contextSource})")]
    else requiredContexts.contextErrors ++
      -- Check for ambiguous type variables
      map(
        \ tv::TyVar -> err(top.contextLoc, s"Ambiguous type variable ${findAbbrevFor(tv, top.freeVariables)} (arising from ${top.contextSource}) prevents the constraint ${prettyContext(top)} from being solved."),
        removeAllBy(tyVarEqual, resolvedTypeScheme.typerep.freeVariables, map(fst, resolvedSubst.substList)));
}
