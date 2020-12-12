grammar silver:definition:env;

-- Context lookup/resolution stuff lives here

synthesized attribute contextSuperDef::(Def ::= String Location String) occurs on Context;

attribute env occurs on Context;
synthesized attribute resolved::[DclInfo] occurs on Context;

aspect production instContext
top::Context ::= cls::String t::Type
{
  top.contextSuperDef = instSuperConstraintDef(_, _, cls, _, t);
  
  -- Somewhat inefficient, since we try unifying with all the instances of the class.
  -- But occurs-on lookup works this way too and isn't too bad?
  -- TODO: This does unification twice, once for filtering and once when we find
  -- the instance.  Probably unavoidable?
  top.resolved =
    filter(
      \ d::DclInfo -> !unifyDirectional(d.typeScheme.typerep, t).failure,
      searchEnvScope(cls, top.env.instTree));

  production resolvedTypeScheme::PolyType = head(top.resolved).typeScheme;
  production requiredContexts::[Context] =
    map(performContextRenaming(_, unifyDirectional(resolvedTypeScheme.typerep, t)), resolvedTypeScheme.contexts);
}
