grammar silver:compiler:definition:env;

-- Context lookup/resolution stuff lives here

attribute env occurs on Context;

-- This mostly exists as a convenient way to perform multiple env-dependant operations
-- on a list of contexts without re-decorating them and repeating context resolution.
nonterminal Contexts with env, freeVariables;
abstract production consContext
top::Contexts ::= h::Context t::Contexts
{ top.freeVariables = setUnionTyVars(h.freeVariables, t.freeVariables); }
abstract production nilContext
top::Contexts ::=
{ top.freeVariables = []; }

global foldContexts::(Contexts ::= [Context]) = foldr(consContext, nilContext(), _);

synthesized attribute contextSuperDef::(Def ::= String Location DclInfo) occurs on Context;  -- Instances from context's superclasses
synthesized attribute contextMemberDef::(Def ::= String Location) occurs on Context; -- Instances from a context on a class member
synthesized attribute contextClassName::Maybe<String> occurs on Context;

synthesized attribute resolved::[DclInfo] occurs on Context;

aspect production instContext
top::Context ::= cls::String t::Type
{
  top.contextSuperDef = instSuperDef(_, _, cls, _);
  top.contextMemberDef = instConstraintDef(_, _, cls, t); -- Could be a different kind of def, but these are essentially the same as regular instance constraints
  top.contextClassName = just(cls);
  
  -- Here possibly-decorated types that are still unspecialized at this point
  -- are specialized as decorated.  Why?  Instance resolution happens after
  -- final types have been computed, and the default is to be decorated,
  -- so we can't allow this to match an instance for the undecorated type.
  production decT::Type =
    case t of
    | ntOrDecType(nt, _) -> decoratedType([], nt)
    | _ -> t
    end;

  -- Somewhat inefficient, since we try unifying with all the instances of the class.
  -- But occurs-on lookup works this way too and isn't too bad?
  -- TODO: This does unification twice, once for filtering and once when we find
  -- the instance.  Probably unavoidable?
  local matching::[DclInfo] =
    filter(
      \ d::DclInfo -> !unifyDirectional(d.typeScheme.typerep, decT).failure && !d.typeScheme.typerep.isError,
      searchEnvScope(cls, top.env.instTree));
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


aspect production typeableContext
top::Context ::= t::Type
{
  top.contextSuperDef = typeableSuperDef(_, _, _);
  top.contextMemberDef = typeableInstConstraintDef(_, _, t); -- Could be a different kind of def, but these are essentially the same as regular instance constraints
  top.contextClassName = nothing();

  top.resolved =
    if t.isTypeable
    then [typeableDcl(t, sourceGrammar="silver:core", sourceLocation=txtLoc("<builtin>"))]
    else
      filter(
        \ d::DclInfo -> !unifyDirectional(d.typeScheme.typerep, t).failure && !d.typeScheme.typerep.isError,
        searchEnvScope("typeable", top.env.instTree));

  production resolvedDcl::DclInfo = head(top.resolved); -- resolvedDcl.typeScheme should not bind any type variables!
  production requiredContexts::Contexts = foldContexts(resolvedDcl.typeScheme.contexts);
  requiredContexts.env = top.env;
}

synthesized attribute isTypeable::Boolean occurs on Type;
aspect default production
top::Type ::=
{ top.isTypeable = true; }
aspect production skolemType
top::Type ::= _
{ top.isTypeable = false; }

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
    | decoratedType(_, t1), decoratedType(_, t2) -> isMoreSpecific(t1, t2)
    | _, _ -> false
    end;
}
