grammar silver:compiler:analysis:typechecking:core;

import silver:compiler:definition:flow:env only getFlowTypeSpecFor, flowEnv;
import silver:compiler:analysis:warnings:flow only inhDepsForSynOnType;
import silver:util:treeset as set;

inherited attribute contextLoc::Location occurs on Contexts, Context;
inherited attribute contextSource::String occurs on Contexts, Context;
monoid attribute contextErrors::[Message] occurs on Contexts, Context;
attribute downSubst, upSubst occurs on Contexts, Context;
propagate contextLoc, contextSource, contextErrors, downSubst, upSubst on Contexts;

flowtype contextErrors {contextLoc, contextSource, env, frame, grammarName, compiledGrammars, config} on Context;

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
    -- If there is an instance for the undecorated type, and all instances for
    -- the decorated type are typeError instances (or there are none),
    -- then specialize to the undecorated type, since the decorated type will
    -- always give a type error.
    | ntOrDecType(nt, _, _) when
        !null(getInstanceDcl(cls, nt, top.env)) &&
        all(map((.isTypeError), getInstanceDcl(cls, substT.defaultSpecialization, top.env))) ->
      composeSubst(top.downSubst, substT.unifyInstanceNonterminal)
    | _ -> top.downSubst
    end;
}

aspect production inhOccursContext
top::Context ::= attr::String args::[Type] atty::Type ntty::Type
{
  requiredContexts.contextLoc = top.contextLoc;
  requiredContexts.contextSource = s"the instance for ${prettyContext(top)}, arising from ${top.contextSource}";
  
  top.contextErrors :=
    -- Check for ambiguous type variables.
    -- Since we've already computed the final substitution, if ntty has any,
    -- they could unify with something more specific in instance resolution here,
    -- and unify with something else in solving another instance later on.
    if !null(ntty.freeFlexibleVars)
    then map(
      \ tv::TyVar -> err(top.contextLoc, s"Ambiguous type variable ${findAbbrevFor(tv, top.freeVariables)} (arising from ${top.contextSource}) prevents the constraint ${prettyContext(top)} from being solved."),
      ntty.freeFlexibleVars)
    -- atty should never have free type variables if ntty does not, except in case of errors elsewhere.
    else {-if !null(atty.freeFlexibleVars)
    then error(s"got atty with free vars")
    else-} if ntty.isDecorated
    then [err(top.contextLoc, s"Could not find an instance for ${prettyContext(top)}; an undecorated type is expected here (arising from ${top.contextSource})")]
    else if null(top.resolvedOccurs)
    then [err(top.contextLoc, s"Could not find an instance for ${prettyContext(top)} (arising from ${top.contextSource})")]
    else requiredContexts.contextErrors;

  -- Not refining based on occurs-on constraints for now, since the appropriate inference should happen on the associated decoration
  top.upSubst = top.downSubst;
}

aspect production synOccursContext
top::Context ::= attr::String args::[Type] atty::Type inhs::Type ntty::Type
{
  requiredContexts.contextLoc = top.contextLoc;
  requiredContexts.contextSource = s"the instance for ${prettyContext(top)}, arising from ${top.contextSource}";
  
  top.contextErrors :=
    -- Check for ambiguous type variables.
    -- Since we've already computed the final substitution, if t has any,
    -- they could unify with something more specific in instance resolution here,
    -- and unify with something else in solving another instance later on.
    if !null(ntty.freeFlexibleVars) || (!ntty.isNonterminal && !null(inhs.freeFlexibleVars))
    then map(
      \ tv::TyVar -> err(top.contextLoc, s"Ambiguous type variable ${findAbbrevFor(tv, top.freeVariables)} (arising from ${top.contextSource}) prevents the constraint ${prettyContext(top)} from being solved."),
      ntty.freeFlexibleVars ++ inhs.freeFlexibleVars)
    -- Give a more helpful error message when there are flexible type vars in inhs but not in ntty,
    -- when we might be able to resolve the ambiguity via flow types.
    else if !null(inhs.freeFlexibleVars)
    then map(
      \ tv::TyVar -> err(
        top.contextLoc,
        s"Ambiguous type variable ${findAbbrevFor(tv, top.freeVariables)} (arising from ${top.contextSource}) prevents the constraint ${prettyContext(top)} from being solved. Note: this ambiguity might be resolved by specifying an explicit flowtype for ${attr} on ${ntty.typeName}"),
      inhs.freeFlexibleVars)
    -- atty should never have free type variables if ntty does not, except in case of errors elsewhere.
    else {-if !null(atty.freeFlexibleVars)
    then error(s"got atty with free vars")
    else-} if null(top.resolvedOccurs)
    then [err(top.contextLoc, s"Could not find an instance for ${prettyContext(top)} (arising from ${top.contextSource})")]
    else requiredContexts.contextErrors;

  production substNtty::Type = performSubstitution(ntty, top.downSubst);
  production substInhs::Type = performSubstitution(inhs, top.downSubst);
  top.upSubst =
    -- If the nonterminal type is known but the flow type inh set is unspecialized,
    -- specialize it to the specified flow type of the attribute on the nonterminal.
    -- This is a bit of a hack, since we don't properly support functional dependencies.
    if null(substNtty.freeFlexibleVars) && !null(substInhs.freeFlexibleVars) && substNtty.isNonterminal
    then
      case lookup(attr, getFlowTypeSpecFor(substNtty.typeName, top.flowEnv)) of
      | just((specInhs, _)) -> composeSubst(top.downSubst, unify(substInhs, inhSetType(sort(specInhs))))
      | _ -> top.downSubst
      end
    else top.downSubst;
}

aspect production annoOccursContext
top::Context ::= attr::String args::[Type] atty::Type ntty::Type
{
  requiredContexts.contextLoc = top.contextLoc;
  requiredContexts.contextSource = s"the instance for ${prettyContext(top)}, arising from ${top.contextSource}";
  
  top.contextErrors :=
    -- Check for ambiguous type variables.
    -- Since we've already computed the final substitution, if t has any,
    -- they could unify with something more specific in instance resolution here,
    -- and unify with something else in solving another instance later on.
    if !null(ntty.freeFlexibleVars)
    then map(
      \ tv::TyVar -> err(top.contextLoc, s"Ambiguous type variable ${findAbbrevFor(tv, top.freeVariables)} (arising from ${top.contextSource}) prevents the constraint ${prettyContext(top)} from being solved."),
      ntty.freeFlexibleVars)
    else if null(top.resolvedOccurs)
    then [err(top.contextLoc, s"Could not find an instance for ${prettyContext(top)} (arising from ${top.contextSource})")]
    else requiredContexts.contextErrors;

  -- Not refining based on occurs-on constraints for now, since the appropriate inference should happen on the associated decoration
  top.upSubst = top.downSubst;
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

aspect production typeErrorContext
top::Context ::= msg::String
{
  top.contextErrors := [err(top.contextLoc, msg ++ " (arising from " ++ top.contextSource ++ ")")];

  top.upSubst = top.downSubst; -- No effect on decoratedness
}
