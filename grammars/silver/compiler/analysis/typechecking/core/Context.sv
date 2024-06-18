grammar silver:compiler:analysis:typechecking:core;

import silver:compiler:definition:flow:env only getFlowTypeSpecFor, flowEnv;
import silver:compiler:analysis:warnings:flow only inhDepsForSynOnType;
import silver:util:treeset as set;

inherited attribute contextLoc::Location occurs on Contexts, Context;
inherited attribute contextSource::String occurs on Contexts, Context;
monoid attribute contextErrors::[Message] occurs on Contexts, Context;
monoid attribute contextSpecialization::Substitution with emptySubst(), composeSubst occurs on Contexts, Context;
propagate contextLoc, contextSource, contextErrors, contextSpecialization on Contexts;
propagate contextSpecialization on Context;

flowtype contextErrors {contextLoc, contextSource, env, frame, grammarName, compiledGrammars, config} on Context;

aspect production instContext
top::Context ::= cls::String t::Type
{
  requiredContexts.contextLoc = top.contextLoc;
  requiredContexts.contextSource = s"the instance for ${prettyContext(new(top))}, arising from ${top.contextSource}";
  
  -- Duplicates are checked at the instance declaration
  top.contextErrors :=
    -- Check for ambiguous type variables.
    -- Since we've already computed the final substitution, if t has any,
    -- they could unify with something more specific in instance resolution here,
    -- and unify with something else in solving another instance later on.
    if !null(t.freeFlexibleVars)
    then map(
      \ tv::TyVar -> err(top.contextLoc, s"Ambiguous type variable ${findAbbrevFor(tv, top.freeVariables)} (arising from ${top.contextSource}) prevents the constraint ${prettyContext(new(top))} from being solved."),
      t.freeFlexibleVars)
    else if null(top.resolved)
    then [err(top.contextLoc, s"Could not find an instance for ${prettyContext(new(top))} (arising from ${top.contextSource})")]
    else requiredContexts.contextErrors;
}

aspect production inhOccursContext
top::Context ::= attr::String args::[Type] atty::Type ntty::Type
{
  requiredContexts.contextLoc = top.contextLoc;
  requiredContexts.contextSource = s"the instance for ${prettyContext(new(top))}, arising from ${top.contextSource}";
  
  top.contextErrors :=
    -- Check for ambiguous type variables.
    -- Since we've already computed the final substitution, if ntty has any,
    -- they could unify with something more specific in instance resolution here,
    -- and unify with something else in solving another instance later on.
    if !null(ntty.freeFlexibleVars)
    then map(
      \ tv::TyVar -> err(top.contextLoc, s"Ambiguous type variable ${findAbbrevFor(tv, top.freeVariables)} (arising from ${top.contextSource}) prevents the constraint ${prettyContext(new(top))} from being solved."),
      ntty.freeFlexibleVars)
    -- atty should never have free type variables if ntty does not, except in case of errors elsewhere.
    else {-if !null(atty.freeFlexibleVars)
    then error(s"got atty with free vars")
    else-} if ntty.isDecorated
    then [err(top.contextLoc, s"Could not find an instance for ${prettyContext(new(top))}; an undecorated type is expected here (arising from ${top.contextSource})")]
    else if null(top.resolvedOccurs)
    then [err(top.contextLoc, s"Could not find an instance for ${prettyContext(new(top))} (arising from ${top.contextSource})")]
    else requiredContexts.contextErrors;
}

aspect production synOccursContext
top::Context ::= attr::String args::[Type] atty::Type inhs::Type ntty::Type
{
  requiredContexts.contextLoc = top.contextLoc;
  requiredContexts.contextSource = s"the instance for ${prettyContext(new(top))}, arising from ${top.contextSource}";
  
  top.contextErrors :=
    -- Check for ambiguous type variables.
    -- Since we've already computed the final substitution, if t has any,
    -- they could unify with something more specific in instance resolution here,
    -- and unify with something else in solving another instance later on.
    if !null(ntty.freeFlexibleVars) || (!ntty.isNonterminal && !null(inhs.freeFlexibleVars))
    then map(
      \ tv::TyVar -> err(top.contextLoc, s"Ambiguous type variable ${findAbbrevFor(tv, top.freeVariables)} (arising from ${top.contextSource}) prevents the constraint ${prettyContext(new(top))} from being solved."),
      ntty.freeFlexibleVars ++ inhs.freeFlexibleVars)
    -- Give a more helpful error message when there are flexible type vars in inhs but not in ntty,
    -- when we might be able to resolve the ambiguity via flow types.
    -- TODO: This no longer seems to be possible, since we are implicitly specializing ref sets.  Confirm this.
    else if !null(inhs.freeFlexibleVars)
    then map(
      \ tv::TyVar -> err(
        top.contextLoc,
        s"Ambiguous type variable ${findAbbrevFor(tv, top.freeVariables)} (arising from ${top.contextSource}) prevents the constraint ${prettyContext(new(top))} from being solved. Note: this ambiguity might be resolved by specifying an explicit flowtype for ${attr} on ${ntty.typeName}"),
      inhs.freeFlexibleVars)
    -- atty should never have free type variables if ntty does not, except in case of errors elsewhere.
    else {-if !null(atty.freeFlexibleVars)
    then error(s"got atty with free vars")
    else-} if null(top.resolvedOccurs)
    then [err(top.contextLoc, s"Could not find an instance for ${prettyContext(new(top))} (arising from ${top.contextSource})")]
    else requiredContexts.contextErrors;

  top.contextSpecialization <-
    -- If the nonterminal type is known but the flow type inh set is unspecialized,
    -- specialize it to the specified flow type of the attribute on the nonterminal.
    -- This is a bit of a hack, since we don't properly support functional dependencies.
    if null(ntty.freeFlexibleVars) && !null(inhs.freeFlexibleVars) && ntty.isNonterminal
    then
      case lookup(attr, getFlowTypeSpecFor(ntty.typeName, top.flowEnv)) of
      | just((specInhs, _)) -> unify(new(inhs), inhSetType(sort(specInhs)))
      | _ -> emptySubst()
      end
    else emptySubst();
}

aspect production annoOccursContext
top::Context ::= attr::String args::[Type] atty::Type ntty::Type
{
  requiredContexts.contextLoc = top.contextLoc;
  requiredContexts.contextSource = s"the instance for ${prettyContext(new(top))}, arising from ${top.contextSource}";
  
  top.contextErrors :=
    -- Check for ambiguous type variables.
    -- Since we've already computed the final substitution, if t has any,
    -- they could unify with something more specific in instance resolution here,
    -- and unify with something else in solving another instance later on.
    if !null(ntty.freeFlexibleVars)
    then map(
      \ tv::TyVar -> err(top.contextLoc, s"Ambiguous type variable ${findAbbrevFor(tv, top.freeVariables)} (arising from ${top.contextSource}) prevents the constraint ${prettyContext(new(top))} from being solved."),
      ntty.freeFlexibleVars)
    else if null(top.resolvedOccurs)
    then [err(top.contextLoc, s"Could not find an instance for ${prettyContext(new(top))} (arising from ${top.contextSource})")]
    else requiredContexts.contextErrors;
}

aspect production typeableContext
top::Context ::= t::Type
{
  requiredContexts.contextLoc = top.contextLoc;
  requiredContexts.contextSource = s"the instance for ${prettyContext(new(top))}, arising from ${top.contextSource}";

  -- Note that ambiguous type variables are permitted here,
  -- since they can be consistently type-checked at runtime.
  top.contextErrors :=
    if !t.isTypeable && null(top.resolved)
    then [err(top.contextLoc, s"Could not find an instance for ${prettyContext(new(top))} (arising from ${top.contextSource})")]
    else requiredContexts.contextErrors;
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
      \ tv::TyVar -> err(top.contextLoc, s"Ambiguous type variable ${findAbbrevFor(tv, top.freeVariables)} (arising from ${top.contextSource}) prevents the constraint ${prettyContext(new(top))} from being solved."),
      i1.freeFlexibleVars ++ i2.freeFlexibleVars)
    else
      case getMaxInhSetMembers([], new(i1), top.env), getMinInhSetMembers([], new(i2), top.env) of
      | (just(inhs1), _), (inhs2, _) when all(map(contains(_, inhs2), inhs1)) -> []
      | (_, tvs1), (_, tvs2) when any(map(contains(_, tvs2), tvs1)) -> []
      | _, _ -> [err(top.contextLoc, s"${prettyTypeWith(new(i1), top.freeVariables)} is not a subset of ${prettyTypeWith(new(i2), top.freeVariables)} (arising from ${top.contextSource})")]
      end;
}

aspect production typeErrorContext
top::Context ::= msg::String
{
  top.contextErrors := [err(top.contextLoc, msg ++ " (arising from " ++ top.contextSource ++ ")")];
}
