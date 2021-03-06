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
  
  -- Duplicates are checked at the instance declaration
  top.contextErrors :=
    -- Check for ambiguous type variables.
    -- Since we've already computed the final substitution, if t has any,
    -- they could unify with something more specific in instance resolution here,
    -- and unify with something else in solving another instance later on.
    if !null(t.freeFlexibleVars)
    then map(
      \ tv::TyVar -> err(top.contextLoc, s"Ambiguous type variable ${findAbbrevFor(tv, top.freeVariables)} (arising from ${top.contextSource}) prevents the constraint ${prettyContext(top)} from being solved."),
      t.freeFlexibleVars)
    else if null(top.resolved)
    then [err(top.contextLoc, s"Could not find an instance for ${prettyContext(top)} (arising from ${top.contextSource})")]
    else requiredContexts.contextErrors;

  production substT::Type = performSubstitution(t, top.downSubst);
  top.upSubst =
    case substT of
    -- Only refine to the *undecorated* type based on instances, since if left
    -- unrefined the type will be treated as decorated, anyway (and leaving it
    -- unrefined will result in less confusing behavior.)
    | ntOrDecType(nt, inhs, _) when !null(getInstanceDcl(cls, nt, top.env)) && null(getInstanceDcl(cls, decoratedType(nt, inhs), top.env)) ->
      composeSubst(top.downSubst, substT.unifyInstanceNonterminal)
    | _ -> top.downSubst
    end;
}

aspect production typeableContext
top::Context ::= t::Type
{
  requiredContexts.contextLoc = top.contextLoc;
  requiredContexts.contextSource = s"the instance for ${prettyContext(top)}, arising from ${top.contextSource}";

  -- Note that ambiguous type variables are permitted here,
  -- since they can be consistently type-checked at runtime.
  top.contextErrors :=
    if !t.isTypeable && null(top.resolved)
    then [err(top.contextLoc, s"Could not find an instance for ${prettyContext(top)} (arising from ${top.contextSource})")]
    else requiredContexts.contextErrors;

  top.upSubst = top.downSubst; -- No effect on decoratedness
}

aspect production inhSubsetContext
top::Context ::= i1::Type i2::Type
{
  top.contextErrors :=
    -- Check for ambiguous type variables.
    -- Since we've already computed the final substitution, if i1 or i2 has any,
    -- they could unify with something more specific in instance resolution here,
    -- and unify with something else in solving another instance later on.
    if !null(i1.freeFlexibleVars ++ i2.freeFlexibleVars)
    then map(
      \ tv::TyVar -> err(top.contextLoc, s"Ambiguous type variable ${findAbbrevFor(tv, top.freeVariables)} (arising from ${top.contextSource}) prevents the constraint ${prettyContext(top)} from being solved."),
      i1.freeFlexibleVars ++ i2.freeFlexibleVars)
    else
      case getMaxInhSetMembers([], i1, top.env), getMinInhSetMembers([], i2, top.env) of
      | (just(inhs1), _), (inhs2, _) when all(map(contains(_, inhs2), inhs1)) -> []
      | (_, tvs1), (_, tvs2) when any(map(contains(_, tvs2), tvs1)) -> []
      | _, _ -> [err(top.contextLoc, s"${prettyTypeWith(i1, top.freeVariables)} is not a subset of ${prettyTypeWith(i2, top.freeVariables)} (arising from ${top.contextSource})")]
      end;

  top.upSubst = top.downSubst; -- No effect on decoratedness
}
