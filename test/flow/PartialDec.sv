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
top::PDExpr ::= e::Decorated! PDExpr with {env1}
{
  -- Accesses on unique decorated children should not give flow errors
  top.errors1 = e.errors1;
  top.errors2 = !null(e.env1);
}

function getEnv2FromPartialRef
[String] ::= e::Expr
{
  e.env2 = [];
  -- Accesses on unique decorated local should not give flow errors
  local e2::Decorated! Expr with {env2} = e;
  return e2.env2;
}
{-
warnCode "Multiple unique references taken to e in production flow:overloadThing2 (reference has type Decorated! flow:PDExpr with {flow:env1})." {
production overloadThing2
top::PDExpr ::= e::PDExpr
{
  local otherRef::Decorated! PDExpr with {env1} = e;
  e.env1 = top.env1;
  forwards to dispatchThing(e);
}
}
-}
warnCode "Attribute env2 with an equation on e is not in the unique reference taken" {
aspect production overloadThing
top::PDExpr ::= e::PDExpr
{
  e.env2 = [];
}
}
{-
warnCode "Multiple unique references taken to e2 in production flow:overloadThing (reference has type Decorated! flow:PDExpr with {flow:env1})." {
aspect production overloadThing
top::PDExpr ::= e::PDExpr
{
  local e2::PDExpr = e;
  e2.env1 = [];
  e2.env2 = [];
  local ref1::Decorated! PDExpr with {env1} = e2;
  local ref2::Decorated! PDExpr with {env1, env2} = e2;
}
}
-}
warnCode "Unique reference of type Decorated! flow:PDExpr with {} does not contain all attributes in the reference set of e's type Decorated! flow:PDExpr with {flow:env1}" {
aspect production dispatchThing
top::PDExpr ::= e::Decorated! PDExpr with {env1}
{
  local otherRef::Decorated! PDExpr with {} = e;
}
}

function thingWithBoundedRefArg
{env1, env2} subset i, i subset {env1} =>  -- Uninhabited, but shouldn't give an error
Decorated! PDExpr with {env1} ::= e::Decorated! PDExpr with i
{
  return e;
}

warnCode "Cannot take a unique reference to e of type Decorated! flow:PDExpr with i, as the reference set is not bounded" {
function thingWithUnboundedRefArg
{env1, env2} subset i => Decorated! PDExpr with {env1} ::= e::Decorated! PDExpr with i
{
  return e;
}
}

warnCode "Duplicate equation for env2 on e in production flow:alreadyDec" {
function alreadyDec
() ::= e::Decorated! PDExpr with {env2}
{
  e.env2 = [];
  return ();
}
}

warnCode "Attribute env1 with an equation on e is not in the unique reference taken" {
function eqnNotInRef
Decorated! PDExpr with {env2} ::= e::PDExpr
{
  e.env1 = [];
  e.env2 = [];
  return e;
}
}

warnCode "Unique reference of type Decorated! flow:PDExpr with {} does not contain all attributes in the reference set of e's type Decorated! flow:PDExpr with {flow:env1}" {
aspect production dispatchThing
top::PDExpr ::= e::Decorated! PDExpr with {env1}
{
  local otherRef::Decorated! PDExpr with {} = e;
}
}
