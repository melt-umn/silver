grammar flow;

nonterminal PDExpr with env1, env2;

production overloadThing
top::PDExpr ::= e::PDExpr
{
  e.env1 = top.env1;
  forwards to dispatchThing(e);
}

production dispatchThing
top::PDExpr ::= e::Decorated PDExpr with only {env1}
{
}

aspect production overloadThing
top::PDExpr ::= e::PDExpr
{
  local otherRef::Decorated PDExpr with only {env1} = e;  -- OK since the ref set is the same
}

warnCode "Attribute env2 with an equation on e is not in the partially decorated reference taken" {
aspect production overloadThing
top::PDExpr ::= e::PDExpr
{
  e.env2 = [];
}
}

warnCode "Conflicting partially decorated references to e2" {
aspect production overloadThing
top::PDExpr ::= e::PDExpr
{
  local e2::PDExpr = e;
  e2.env1 = [];
  e2.env2 = [];
  local ref1::Decorated PDExpr with only {env1} = e2;
  local ref2::Decorated PDExpr with only {env1, env2} = e2;
}
}
