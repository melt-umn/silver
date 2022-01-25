grammar silver:compiler:definition:env;

-- Context lookup/resolution stuff lives here

attribute env occurs on Context;

-- This mostly exists as a convenient way to perform multiple env-dependant operations
-- on a list of contexts without re-decorating them and repeating context resolution.
nonterminal Contexts with env, contexts, freeVariables, boundVariables;
abstract production consContext
top::Contexts ::= h::Context t::Contexts
{
  top.contexts = h :: t.contexts;
  top.freeVariables = setUnionTyVars(h.freeVariables, t.freeVariables);
}
abstract production nilContext
top::Contexts ::=
{
  top.contexts = [];
  top.freeVariables = [];
}

global foldContexts::(Contexts ::= [Context]) = foldr(consContext, nilContext(), _);

synthesized attribute contextSuperDefs::([Def] ::= InstDclInfo String Location) occurs on Context;  -- Instances from context's superclasses
synthesized attribute contextMemberDefs::([Def] ::= [TyVar] String Location) occurs on Context; -- Instances from a context on a class member
synthesized attribute contextSigDefs::([Def] ::= NamedSignature String Location) occurs on Context;  -- Instances from a context in an aspect signature
synthesized attribute contextSuperOccursDefs::([OccursDclInfo] ::= InstDclInfo String Location) occurs on Context;  -- Attribute occurences from context's superclasses
synthesized attribute contextMemberOccursDefs::([OccursDclInfo] ::= [TyVar] String Location) occurs on Context; -- Attribute occurences from a context on a class member
synthesized attribute contextSigOccursDefs::([OccursDclInfo] ::= NamedSignature String Location) occurs on Context;  -- Attribute occurences from a context in an aspect signature
synthesized attribute contextClassName::Maybe<String> occurs on Context;

synthesized attribute resolved::[InstDclInfo] occurs on Context;
synthesized attribute resolvedOccurs::[OccursDclInfo] occurs on Context;

monoid attribute isTypeError::Boolean with false, || occurs on Contexts, Context;
propagate isTypeError on Contexts, Context;

aspect default production
top::Context ::=
{
  top.resolved = [];
  top.resolvedOccurs = [];
}

aspect production instContext
top::Context ::= cls::String t::Type
{
  top.contextSuperDefs = \ d::InstDclInfo g::String l::Location ->
    [tcInstDef(instSuperDcl(cls, d, sourceGrammar=g, sourceLocation=l))];
  top.contextMemberDefs = \ tvs::[TyVar] g::String l::Location ->
    [tcInstDef(instConstraintDcl(cls, t, tvs, sourceGrammar=g, sourceLocation=l))]; -- Could be a different kind of def, but these are essentially the same as regular instance constraints
  top.contextSigDefs = \ ns::NamedSignature g::String l::Location ->
    [tcInstDef(sigConstraintDcl(cls, t, ns, sourceGrammar=g, sourceLocation=l))];
  top.contextSuperOccursDefs = \ InstDclInfo String Location -> [];
  top.contextMemberOccursDefs = \ [TyVar] String Location -> [];
  top.contextSigOccursDefs = \ NamedSignature String Location -> [];
  top.contextClassName = just(cls);
  
  -- Here possibly-decorated types that are still unspecialized at this point
  -- are specialized as decorated.  Why?  Instance resolution happens after
  -- final types have been computed, and the default is to be decorated,
  -- so we can't allow this to match an instance for the undecorated type.
  production decT::Type = t.defaultSpecialization;

  -- Somewhat inefficient, since we try unifying with all the instances of the class.
  -- But occurs-on lookup works this way too and isn't too bad?
  -- TODO: This does unification twice, once for filtering and once when we find
  -- the instance.  Probably unavoidable?
  local matching::[InstDclInfo] =
    filter(
      \ d::InstDclInfo -> !unifyDirectional(d.typeScheme.typerep, decT).failure && !d.typeScheme.typerep.isError,
      searchEnvTree(cls, top.env.instTree));
  top.resolved =
    removeAllBy(
      \ d1::InstDclInfo d2::InstDclInfo -> isMoreSpecific(d1.typeScheme.typerep, d2.typeScheme.typerep),
      matching, matching);

  production resolvedDcl::InstDclInfo = head(top.resolved);
  production resolvedTypeScheme::PolyType = resolvedDcl.typeScheme;
  production resolvedSubst::Substitution = unifyDirectional(resolvedTypeScheme.typerep, decT);
  production requiredContexts::Contexts =
    foldContexts(map(performContextRenaming(_, resolvedSubst), resolvedTypeScheme.contexts));
  requiredContexts.env = top.env;
}

aspect production inhOccursContext
top::Context ::= attr::String args::[Type] atty::Type ntty::Type
{
  top.contextSuperDefs = \ InstDclInfo String Location -> [];
  top.contextMemberDefs = \ [TyVar] String Location -> [];
  top.contextSigDefs = \ NamedSignature String Location -> [];
  top.contextSuperOccursDefs = \ d::InstDclInfo g::String l::Location ->
    [occursSuperDcl(attr, atty, d, sourceGrammar=g, sourceLocation=l)];
  top.contextMemberOccursDefs = \ tvs::[TyVar] g::String l::Location ->
    [occursInstConstraintDcl(attr, ntty, atty, tvs, sourceGrammar=g, sourceLocation=l)];
  top.contextSigOccursDefs = \ ns::NamedSignature g::String l::Location ->
    [occursSigConstraintDcl(attr, ntty, atty, ns, sourceGrammar=g, sourceLocation=l)];
  top.contextClassName = nothing();
  
  top.resolvedOccurs = getOccursDcl(attr, ntty.typeName, top.env);
  production resolvedDcl::OccursDclInfo = head(top.resolvedOccurs);
  resolvedDcl.givenNonterminalType = ntty;
  production resolvedTypeScheme::PolyType = resolvedDcl.typeScheme;
  production resolvedSubst::Substitution = unifyDirectional(resolvedTypeScheme.typerep, atty);
  production requiredContexts::Contexts =
    foldContexts(map(performContextRenaming(_, resolvedSubst), resolvedTypeScheme.contexts));
  requiredContexts.env = top.env;
}

aspect production synOccursContext
top::Context ::= attr::String args::[Type] atty::Type inhs::Type ntty::Type
{
  top.contextSuperDefs = \ InstDclInfo String Location -> [];
  top.contextMemberDefs = \ [TyVar] String Location -> [];
  top.contextSigDefs = \ NamedSignature String Location -> [];
  top.contextSuperOccursDefs = \ d::InstDclInfo g::String l::Location ->
    [occursSuperDcl(attr, atty, d, sourceGrammar=g, sourceLocation=l)];
  top.contextMemberOccursDefs = \ tvs::[TyVar] g::String l::Location ->
    [occursInstConstraintDcl(attr, ntty, atty, tvs, sourceGrammar=g, sourceLocation=l)];
  top.contextSigOccursDefs = \ ns::NamedSignature g::String l::Location ->
    [occursSigConstraintDcl(attr, ntty, atty, ns, sourceGrammar=g, sourceLocation=l)];
  top.contextClassName = nothing();

  top.resolvedOccurs = getOccursDcl(attr, ntty.typeName, top.env);
  production resolvedDcl::OccursDclInfo = head(top.resolvedOccurs);
  resolvedDcl.givenNonterminalType = ntty;
  production resolvedTypeScheme::PolyType = resolvedDcl.typeScheme;
  production resolvedSubst::Substitution = unifyDirectional(resolvedTypeScheme.typerep, atty);
  production requiredContexts::Contexts =
    foldContexts(map(performContextRenaming(_, resolvedSubst), resolvedTypeScheme.contexts));
  requiredContexts.env = top.env;
}

aspect production annoOccursContext
top::Context ::= attr::String args::[Type] atty::Type ntty::Type
{
  top.contextSuperDefs = \ InstDclInfo String Location -> [];
  top.contextMemberDefs = \ [TyVar] String Location -> [];
  top.contextSigDefs = \ NamedSignature String Location -> [];
  top.contextSuperOccursDefs = \ d::InstDclInfo g::String l::Location ->
    [annoSuperDcl(attr, atty, d, sourceGrammar=g, sourceLocation=l)];
  top.contextMemberOccursDefs = \ tvs::[TyVar] g::String l::Location ->
    [annoInstConstraintDcl(attr, ntty, atty, tvs, sourceGrammar=g, sourceLocation=l)];
  top.contextSigOccursDefs = \ ns::NamedSignature g::String l::Location ->
    [annoSigConstraintDcl(attr, ntty, atty, ns, sourceGrammar=g, sourceLocation=l)];
  top.contextClassName = nothing();
  
  top.resolvedOccurs = getOccursDcl(attr, ntty.typeName, top.env);
  production resolvedDcl::OccursDclInfo = head(top.resolvedOccurs);
  resolvedDcl.givenNonterminalType = ntty;
  production resolvedTypeScheme::PolyType = resolvedDcl.typeScheme;
  production resolvedSubst::Substitution = unifyDirectional(resolvedTypeScheme.typerep, atty);
  production requiredContexts::Contexts =
    foldContexts(map(performContextRenaming(_, resolvedSubst), resolvedTypeScheme.contexts));
  requiredContexts.env = top.env;
}

aspect production typeableContext
top::Context ::= t::Type
{
  top.contextSuperDefs = \ d::InstDclInfo g::String l::Location ->
    [tcInstDef(typeableSuperDcl(d, sourceGrammar=g, sourceLocation=l))];
  top.contextMemberDefs = \ tvs::[TyVar] g::String l::Location ->
    [tcInstDef(typeableInstConstraintDcl(t, tvs, sourceGrammar=g, sourceLocation=l))]; -- Could be a different kind of def, but these are essentially the same as regular instance constraints
  top.contextSigDefs = \ ns::NamedSignature g::String l::Location ->
    [tcInstDef(typeableSigConstraintDcl(t, ns, sourceGrammar=g, sourceLocation=l))];
  top.contextSuperOccursDefs = \ InstDclInfo String Location -> [];
  top.contextMemberOccursDefs = \ [TyVar] String Location -> [];
  top.contextSigOccursDefs = \ NamedSignature String Location -> [];
  top.contextClassName = nothing();

  top.resolved =
    filter(
      \ d::InstDclInfo -> !unifyDirectional(d.typeScheme.typerep, t).failure && !d.typeScheme.typerep.isError,
      searchEnvTree("typeable", top.env.instTree));

  production resolvedDcl::InstDclInfo = head(top.resolved); -- resolvedDcl.typeScheme should not bind any type variables!
  production requiredContexts::Contexts =
    foldContexts(
      if null(top.resolved)
      then map(compose(typeableContext, skolemType), t.freeSkolemVars)
      else resolvedDcl.typeScheme.contexts);
  requiredContexts.env = top.env;
}

synthesized attribute isTypeable::Boolean occurs on Type;
aspect isTypeable on Type of
| skolemType(_) -> false
| _ -> true
end;

aspect production inhSubsetContext
top::Context ::= i1::Type i2::Type
{
  top.contextSuperDefs = error("subset can't appear as superclass");
  top.contextMemberDefs = \ tvs::[TyVar] g::String l::Location ->
    [tcInstDef(inhSubsetInstConstraintDcl(i1, i2, tvs, sourceGrammar=g, sourceLocation=l))]; -- Could be a different kind of def, but these are essentially the same as regular instance constraints
  top.contextSigDefs = \ ns::NamedSignature g::String l::Location ->
    [tcInstDef(inhSubsetSigConstraintDcl(i1, i2, ns, sourceGrammar=g, sourceLocation=l))];
  top.contextSuperOccursDefs = \ InstDclInfo String Location -> [];
  top.contextMemberOccursDefs = \ [TyVar] String Location -> [];
  top.contextSigOccursDefs = \ NamedSignature String Location -> [];
  top.contextClassName = nothing();

  top.resolved =
    filter(
      \ d::InstDclInfo ->
        !unifyDirectional(d.typeScheme.monoType, i1).failure && !d.typeScheme.monoType.isError &&
        !unifyDirectional(d.typerep2, i2).failure && !d.typerep2.isError,
      searchEnvTree("subset", top.env.instTree));
}

aspect production typeErrorContext
top::Context ::= msg::String
{
  top.contextSuperDefs = \ InstDclInfo String Location -> [];
  top.contextMemberDefs = \ [TyVar] String Location -> [];
  top.contextSigDefs = \ NamedSignature String Location -> [];
  top.contextSuperOccursDefs = \ d::InstDclInfo g::String l::Location -> [];
  top.contextMemberOccursDefs = \ tvs::[TyVar] g::String l::Location -> [];
  top.contextSigOccursDefs = \ ns::NamedSignature g::String l::Location -> [];
  top.contextClassName = nothing();
  top.resolved = [];
  top.isTypeError <- true;
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
    | partiallyDecoratedType(t1, i1), partiallyDecoratedType(t2, i2) ->
      (isMoreSpecific(t1, t2) || isMoreSpecific(i1, i2)) && !(isMoreSpecific(t2, t1) || isMoreSpecific(i2, i1))
    | _, _ -> false
    end;
}
