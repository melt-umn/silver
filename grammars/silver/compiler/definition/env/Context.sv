grammar silver:compiler:definition:env;

-- Context lookup/resolution stuff lives here

attribute env occurs on Context;

-- This mostly exists as a convenient way to perform multiple env-dependant operations
-- on a list of contexts without re-decorating them and repeating context resolution.
nonterminal Contexts with env, freeVariables, boundVariables;
abstract production consContext
top::Contexts ::= h::Context t::Contexts
{ top.freeVariables = setUnionTyVars(h.freeVariables, t.freeVariables); }
abstract production nilContext
top::Contexts ::=
{ top.freeVariables = []; }

global foldContexts::(Contexts ::= [Context]) = foldr(consContext, nilContext(), _);

synthesized attribute contextSuperDcl::(DclInfo ::= DclInfo String Location) occurs on Context;  -- Instances from context's superclasses
synthesized attribute contextMemberDcl::(DclInfo ::= [TyVar] String Location) occurs on Context; -- Instances from a context on a class member
synthesized attribute contextClassName::Maybe<String> occurs on Context;

synthesized attribute resolved::[DclInfo] occurs on Context;

aspect production instContext
top::Context ::= cls::String t::Type
{
  top.contextSuperDcl = instSuperDcl(cls, _, sourceGrammar=_, sourceLocation=_);
  top.contextMemberDcl = instConstraintDcl(cls, t, _, sourceGrammar=_, sourceLocation=_); -- Could be a different kind of def, but these are essentially the same as regular instance constraints
  top.contextClassName = just(cls);
  
  -- Here possibly-decorated types that are still unspecialized at this point
  -- are specialized as decorated.  Why?  Instance resolution happens after
  -- final types have been computed, and the default is to be decorated with nothing,
  -- so we can't allow this to match an instance for the undecorated type.
  production decT::Type =
    case t of
    | ntOrDecType(nt, inhs, _) -> decoratedType(nt, inhs)
    | _ -> t
    end;

  -- Somewhat inefficient, since we try unifying with all the instances of the class.
  -- But occurs-on lookup works this way too and isn't too bad?
  -- TODO: This does unification twice, once for filtering and once when we find
  -- the instance.  Probably unavoidable?
  local matching::[DclInfo] =
    filter(
      \ d::DclInfo -> !unifyDirectional(d.typeScheme.typerep, decT).failure && !d.typeScheme.typerep.isError,
      searchEnvTree(cls, top.env.instTree));
  top.resolved =
    removeAllBy(
      \ d1::DclInfo d2::DclInfo -> isMoreSpecific(d1.typeScheme.typerep, d2.typeScheme.typerep),
      matching, matching);

  production resolvedDcl::DclInfo = head(top.resolved);
  production resolvedTypeScheme::PolyType = resolvedDcl.typeScheme;
  production resolvedSubst::Substitution = unifyDirectional(resolvedTypeScheme.typerep, decT);
  production requiredContexts::Contexts =
    foldContexts(map(performContextRenaming(_, resolvedSubst), resolvedTypeScheme.contexts));
  requiredContexts.env = top.env;
}

aspect production inhOccursContext
top::Context ::= attr::String args::[Type] atty::Type ntty::Type
{
  top.contextSuperDcl = occursSuperDcl(attr, atty, _, sourceGrammar=_, sourceLocation=_);
  top.contextMemberDcl = occursInstConstraintDcl(attr, ntty, atty, _, sourceGrammar=_, sourceLocation=_);
  top.contextClassName = nothing();
  
  top.resolved = getOccursDcl(attr, ntty.typeName, top.env);
  production resolvedDcl::DclInfo = head(top.resolved);
  production resolvedTypeScheme::PolyType = resolvedDcl.typeScheme;
  production resolvedSubst::Substitution = unifyDirectional(resolvedTypeScheme.typerep, atty);
  production requiredContexts::Contexts =
    foldContexts(map(performContextRenaming(_, resolvedSubst), resolvedTypeScheme.contexts));
  requiredContexts.env = top.env;
}

aspect production synOccursContext
top::Context ::= attr::String args::[Type] atty::Type inhs::Type ntty::Type
{
  top.contextSuperDcl = occursSuperDcl(attr, atty, _, sourceGrammar=_, sourceLocation=_);
  top.contextMemberDcl = occursInstConstraintDcl(attr, ntty, atty, _, sourceGrammar=_, sourceLocation=_);
  top.contextClassName = nothing();

  top.resolved = getOccursDcl(attr, ntty.typeName, top.env);
  production resolvedDcl::DclInfo = head(top.resolved);
  production resolvedTypeScheme::PolyType = resolvedDcl.typeScheme;
  production resolvedSubst::Substitution = unifyDirectional(resolvedTypeScheme.typerep, atty);
  production requiredContexts::Contexts =
    foldContexts(map(performContextRenaming(_, resolvedSubst), resolvedTypeScheme.contexts));
  requiredContexts.env = top.env;
}

aspect production typeableContext
top::Context ::= t::Type
{
  top.contextSuperDcl = typeableSuperDcl(_, sourceGrammar=_, sourceLocation=_);
  top.contextMemberDcl = typeableInstConstraintDcl(t, _, sourceGrammar=_, sourceLocation=_); -- Could be a different kind of def, but these are essentially the same as regular instance constraints
  top.contextClassName = nothing();

  top.resolved =
    filter(
      \ d::DclInfo -> !unifyDirectional(d.typeScheme.typerep, t).failure && !d.typeScheme.typerep.isError,
      searchEnvTree("typeable", top.env.instTree));

  production resolvedDcl::DclInfo = head(top.resolved); -- resolvedDcl.typeScheme should not bind any type variables!
  production requiredContexts::Contexts =
    foldContexts(
      if null(top.resolved)
      then map(compose(typeableContext, skolemType), t.freeSkolemVars)
      else resolvedDcl.typeScheme.contexts);
  requiredContexts.env = top.env;
}

synthesized attribute isTypeable::Boolean occurs on Type;
aspect default production
top::Type ::=
{ top.isTypeable = true; }
aspect production skolemType
top::Type ::= _
{ top.isTypeable = false; }

aspect production inhSubsetContext
top::Context ::= i1::Type i2::Type
{
  top.contextSuperDcl = error("subset can't appear as superclass");
  top.contextMemberDcl = inhSubsetInstConstraintDcl(i1, i2, _, sourceGrammar=_, sourceLocation=_); -- Could be a different kind of def, but these are essentially the same as regular instance constraints
  top.contextClassName = nothing();

  top.resolved =
    filter(
      \ d::DclInfo ->
        !unifyDirectional(d.typeScheme.monoType, i1).failure && !d.typeScheme.monoType.isError &&
        !unifyDirectional(d.typerep2, i2).failure && !d.typerep2.isError,
      searchEnvTree("subset", top.env.instTree));
}

-- Invariant: This should be called when a and b are unifyable
function isMoreSpecific
Boolean ::= a::Type b::Type
{
  return
    case a, b of
    | varType(_), varType(_) -> false
    | _, varType(_) -> true
    | appType(c1, a1), appType(c2, a2) ->
      (isMoreSpecific(c1, c2) || isMoreSpecific(a1, a2)) && !(isMoreSpecific(c2, c1) || isMoreSpecific(a2, a1))
    | decoratedType(t1, i1), decoratedType(t2, i2) ->
      (isMoreSpecific(t1, t2) || isMoreSpecific(i1, i2)) && !(isMoreSpecific(t2, t1) || isMoreSpecific(i2, i1))
    | _, _ -> false
    end;
}
