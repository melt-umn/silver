grammar flow;

synthesized attribute errors1::Boolean;
synthesized attribute errors2::Boolean;

nonterminal UDExpr with env1, env2, errors1, errors2;

production overloadThing
top::UDExpr ::= e::UDExpr
{
  e.env1 = top.env1;
  forwards to dispatchThing(e);
}

production dispatchThing
top::UDExpr ::= e::Decorated! UDExpr with {env1}
{
  undecorates to overloadThing(e);
  -- Accesses on unique decorated children should not give flow errors
  top.errors1 = e.errors1;
  top.errors2 = !null(e.env1);
}

wrongFlowCode "Production 'dispatchThing2' has a unique reference in its signature but no 'undecorates to'." {
production dispatchThing2
top::UDExpr ::= e::Decorated! UDExpr with {env1}
{
  top.errors1 = e.errors1;
  top.errors2 = !null(e.env1);
}
}

function getEnv2FromUniqueRef
[String] ::= e::Expr
{
  e.env2 = [];
  -- Accesses on unique decorated local should not give flow errors
  local e2::Decorated! Expr with {env2} = e;
  return e2.env2;
}

wrongFlowCode "Multiple unique references taken to e in production flow:overloadThing2 (reference has type Decorated! flow:UDExpr with {flow:env1})." {
production overloadThing2
top::UDExpr ::= e::UDExpr
{
  local otherRef::Decorated! UDExpr with {env1} = e;
  e.env1 = top.env1;
  forwards to dispatchThing(e);
}
}

warnCode "Attribute env2 with an equation on e is not in the unique reference taken" {
aspect production overloadThing
top::UDExpr ::= e::UDExpr
{
  e.env2 = [];
}
}

wrongFlowCode "Multiple unique references taken to e2 in production flow:overloadThing (reference has type Decorated! flow:UDExpr with {flow:env1})." {
aspect production overloadThing
top::UDExpr ::= e::UDExpr
{
  local e2::UDExpr = e;
  e2.env1 = [];
  e2.env2 = [];
  local ref1::Decorated! UDExpr with {env1} = e2;
  local ref2::Decorated! UDExpr with {env1, env2} = e2;
}
}

wrongCode "Unique reference of type Decorated! flow:UDExpr with {} does not contain all attributes in the reference set of e's type Decorated! flow:UDExpr with {flow:env1}" {
aspect production dispatchThing
top::UDExpr ::= e::Decorated! UDExpr with {env1}
{
  local otherRef::Decorated! UDExpr with {} = e;
}
}

function thingWithBoundedRefArg
{env1, env2} subset i, i subset {env1} =>  -- Uninhabited, but shouldn't give an error
Decorated! UDExpr with {env1} ::= e::Decorated! UDExpr with i
{
  return e;
}

wrongCode "Cannot take a unique reference to e of type Decorated! flow:UDExpr with i, as the reference set is not bounded" {
function thingWithUnboundedRefArg
{env1, env2} subset i => Decorated! UDExpr with {env1} ::= e::Decorated! UDExpr with i
{
  return e;
}
}

warnCode "Duplicate equation for env2 on e in production flow:alreadyDec" {
function alreadyDec
() ::= e::Decorated! UDExpr with {env2}
{
  e.env2 = [];
  return ();
}
}

warnCode "Attribute env1 with an equation on e is not in the unique reference taken" {
function eqnNotInRef
Decorated! UDExpr with {env2} ::= e::UDExpr
{
  e.env1 = [];
  e.env2 = [];
  return e;
}
}

wrongCode "Unique reference of type Decorated! flow:UDExpr with {} does not contain all attributes in the reference set of e's type Decorated! flow:UDExpr with {flow:env1}" {
aspect production dispatchThing
top::UDExpr ::= e::Decorated! UDExpr with {env1}
{
  local otherRef::Decorated! UDExpr with {} = e;
}
}
