grammar silver:compiler:analysis:typechecking:core;

inherited attribute contextLoc::Location occurs on Contexts, Context;
inherited attribute contextSource::String occurs on Contexts, Context;
monoid attribute contextErrors::[Message] occurs on Contexts, Context;
attribute downSubst, upSubst occurs on Contexts, Context;
propagate contextLoc, contextSource, contextErrors, downSubst, upSubst on Contexts;

aspect production instContext
top::Context ::= cls::String t::Type
{
  requiredContexts.contextLoc = top.contextLoc;
  requiredContexts.contextSource = s"the instance for ${prettyContext(top)}, arising from ${top.contextSource}";
  
  -- Check for ambiguous type variables
  local ambTyVars::[TyVar] =
    removeAll(resolvedTypeScheme.typerep.freeVariables, map(fst, resolvedSubst.substList));
  
  -- Duplicates are checked at the instance declaration
  top.contextErrors :=
    if null(top.resolved)
    then [err(top.contextLoc, s"Could not find an instance for ${prettyContext(top)} (arising from ${top.contextSource})")]
    else if !null(ambTyVars)
    then map(
      \ tv::TyVar -> err(top.contextLoc, s"Ambiguous type variable ${findAbbrevFor(tv, top.freeVariables)} (arising from ${top.contextSource}) prevents the constraint ${prettyContext(top)} from being solved."),
      ambTyVars)
    else requiredContexts.contextErrors;

  production substT::Type = performSubstitution(t, top.downSubst);
  top.upSubst =
    case substT of
    -- Only refine to the *undecorated* type based on instances, since if left
    -- unrefined the type will be treated as decorated, anyway (and leaving it
    -- unrefined will result in less confusing behavior.)
    | ntOrDecType(nt, _) when !null(getInstanceDcl(cls, nt, top.env)) && null(getInstanceDcl(cls, decoratedType(nt), top.env)) ->
      composeSubst(top.downSubst, substT.unifyInstanceNonterminal)
    | _ -> top.downSubst
    end;
}
