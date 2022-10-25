grammar flow;

synthesized attribute errors1::Boolean;
synthesized attribute errors2::Boolean;

nonterminal PDExpr with env1, env2, errors1, errors2;

production overloadThing
top::PDExpr ::= e::PDExpr
{
  e.env1 = top.env1;
  forwards to dispatchThing(e);
}

production dispatchThing
top::PDExpr ::= e::PartiallyDecorated PDExpr with {env1}
{
  -- Accesses on partially decorated children should not give flow errors
  top.errors1 = e.errors1;
  top.errors2 = !null(e.env1);
}

function getEnv2FromPartialRef
[String] ::= e::Expr
{
  e.env2 = [];
  -- Accesses on partially decorated local should not give flow errors
  local e2::PartiallyDecorated Expr with {env2} = e;
  return e2.env2;
}
{-
warnCode "Multiple partially decorated references taken to e in production flow:overloadThing2 (reference has type PartiallyDecorated flow:PDExpr with {flow:env1})." {
production overloadThing2
top::PDExpr ::= e::PDExpr
{
  local otherRef::PartiallyDecorated PDExpr with {env1} = e;
  e.env1 = top.env1;
  forwards to dispatchThing(e);
}
}
-}
warnCode "Attribute env2 with an equation on e is not in the partially decorated reference taken" {
aspect production overloadThing
top::PDExpr ::= e::PDExpr
{
  e.env2 = [];
}
}
{-
warnCode "Multiple partially decorated references taken to e2 in production flow:overloadThing (reference has type PartiallyDecorated flow:PDExpr with {flow:env1})." {
aspect production overloadThing
top::PDExpr ::= e::PDExpr
{
  local e2::PDExpr = e;
  e2.env1 = [];
  e2.env2 = [];
  local ref1::PartiallyDecorated PDExpr with {env1} = e2;
  local ref2::PartiallyDecorated PDExpr with {env1, env2} = e2;
}
}
-}
warnCode "Partially decorated reference of type PartiallyDecorated flow:PDExpr with {} does not contain all attributes in the reference set of e's type PartiallyDecorated flow:PDExpr with {flow:env1}" {
aspect production dispatchThing
top::PDExpr ::= e::PartiallyDecorated PDExpr with {env1}
{
  local otherRef::PartiallyDecorated PDExpr with {} = e;
}
}

function thingWithBoundedRefArg
{env1, env2} subset i, i subset {env1} =>  -- Uninhabited, but shouldn't give an error
PartiallyDecorated PDExpr with {env1} ::= e::PartiallyDecorated PDExpr with i
{
  return e;
}

warnCode "Cannot take a partially decorated reference to e of type PartiallyDecorated flow:PDExpr with i, as the reference set is not bounded" {
function thingWithUnboundedRefArg
{env1, env2} subset i => PartiallyDecorated PDExpr with {env1} ::= e::PartiallyDecorated PDExpr with i
{
  return e;
}
}

warnCode "Duplicate equation for env2 on e in production flow:alreadyDec" {
function alreadyDec
() ::= e::PartiallyDecorated PDExpr with {env2}
{
  e.env2 = [];
  return ();
}
}

warnCode "Attribute env1 with an equation on e is not in the partially decorated reference taken" {
function eqnNotInRef
PartiallyDecorated PDExpr with {env2} ::= e::PDExpr
{
  e.env1 = [];
  e.env2 = [];
  return e;
}
}

warnCode "Partially decorated reference of type PartiallyDecorated flow:PDExpr with {} does not contain all attributes in the reference set of e's type PartiallyDecorated flow:PDExpr with {flow:env1}" {
aspect production dispatchThing
top::PDExpr ::= e::PartiallyDecorated PDExpr with {env1}
{
  local otherRef::PartiallyDecorated PDExpr with {} = e;
}
}
