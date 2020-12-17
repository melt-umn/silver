grammar silver:definition:env;

-- Context lookup/resolution stuff lives here

attribute env occurs on Context;

-- This mostly exists as a convenient way to perform multiple env-dependant operations
-- on a list of contexts without re-decorating them and repeating context resolution.
nonterminal Contexts with env;
abstract production consContext
top::Contexts ::= h::Context t::Contexts
{}
abstract production nilContext
top::Contexts ::=
{}

global foldContexts::(Contexts ::= [Context]) = foldr(consContext, nilContext(), _);

synthesized attribute contextSuperDef::(Def ::= String Location DclInfo) occurs on Context;
synthesized attribute contextClassName::Maybe<String> occurs on Context;

synthesized attribute resolved::[DclInfo] occurs on Context;

aspect production instContext
top::Context ::= cls::String t::Type
{
  top.contextSuperDef = instSuperDef(_, _, cls, _, t);
  top.contextClassName = just(cls);
  
  -- Somewhat inefficient, since we try unifying with all the instances of the class.
  -- But occurs-on lookup works this way too and isn't too bad?
  -- TODO: This does unification twice, once for filtering and once when we find
  -- the instance.  Probably unavoidable?
  top.resolved =
    nubBy(
      \ d1::DclInfo d2::DclInfo -> isMoreSpecific(d1.typeScheme.typerep, d2.typeScheme.typerep),
      filter(
        \ d::DclInfo -> !unifyDirectional(d.typeScheme.typerep, t).failure,
        searchEnvScope(cls, top.env.instTree)));

  production resolvedDcl::DclInfo = head(top.resolved);
  production resolvedTypeScheme::PolyType = resolvedDcl.typeScheme;
  production requiredContexts::Contexts =
    foldContexts(map(performContextRenaming(_, unifyDirectional(resolvedTypeScheme.typerep, t)), resolvedTypeScheme.contexts));
  requiredContexts.env = top.env;
}

-- Invariant: This should be called when a and b are unifyable
function isMoreSpecific
Boolean ::= a::Type b::Type
{
  return
    case a, b of
    | varType(_), varType(_) -> false
    | varType(_), _ -> true
    | nonterminalType(fn1, params1), nonterminalType(fn2, params2) when fn1 == fn2 ->
      any(zipWith(isMoreSpecific, params1, params2)) && !any(zipWith(isMoreSpecific, params2, params1))
    | decoratedType(t1), decoratedType(t2) -> isMoreSpecific(t1, t2)
    | functionType(out1, params1, _), functionType(out2, params2, _) -> -- TODO: Ignoring named args for now
      any(zipWith(isMoreSpecific, out1 :: params1, out2 :: params2)) && !any(zipWith(isMoreSpecific, out2 :: params2, out1 :: params1))
    | _, _ -> false
    end;
}
