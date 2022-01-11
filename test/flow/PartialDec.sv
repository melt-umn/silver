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

warnCode "Partially decorated reference of type Decorated flow:PDExpr with only {} does not contain all attributes in the reference set of e's type Decorated flow:PDExpr with only {flow:env1}" {
aspect production dispatchThing
top::PDExpr ::= e::Decorated PDExpr with only {env1}
{
  local otherRef::Decorated PDExpr with only {} = e;
}
}

function thingWithBoundedRefArg
{env1, env2} subset i, i subset {env1} =>  -- Uninhabited, but shouldn't give an error
Decorated PDExpr with only {env1} ::= e::Decorated PDExpr with only i
{
  return e;
}

warnCode "Cannot take a partially decorated reference to e of type Decorated flow:PDExpr with only i, as the reference set is not bounded" {
function thingWithUnboundedRefArg
{env1, env2} subset i => Decorated PDExpr with only {env1} ::= e::Decorated PDExpr with only i
{
  return e;
}
}
