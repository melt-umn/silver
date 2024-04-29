grammar flow;

synthesized attribute errors1::Boolean;
synthesized attribute errors2::Boolean;

nonterminal UDExpr with env1, env2, errors1, errors2;

production directOverloadThing
top::UDExpr ::= e::UDExpr
{
  e.env1 = top.env1;
  top.errors2 = e.errors2;
  forwards to shareThing(e);
}

production indirectOverloadThing
top::UDExpr ::= e::UDExpr
{
  e.env1 = top.env1;
  top.errors2 = e.errors2;
  local prod::DispatchOp = if e.errors1 then dispatchThing1 else dispatchThing2;
  forwards to prod(e);
}

production shareThing
top::UDExpr ::= @e::UDExpr
{
  e.env2 = top.env2;
  top.errors1 = e.errors1;
  top.errors2 = !null(e.env1);
}

dispatch DispatchOp = UDExpr ::= @e1::UDExpr;

production dispatchThing1 implements DispatchOp
top::UDExpr ::= @e::UDExpr
{
  e.env2 = top.env2;
  top.errors1 = e.errors1;
  top.errors2 = !null(e.env1);
}

production dispatchThing2 implements DispatchOp
top::UDExpr ::= @e::UDExpr
{
  e.env2 = top.env1;
  top.errors1 = e.errors1;
  top.errors2 = !null(e.env1);
}

production dispatchThing3 implements DispatchOp
top::UDExpr ::= @e::UDExpr i::Integer b::Boolean
{
  top.errors1 = b;
  top.errors2 = i > 0;
}

global dt3::DispatchOp = dispatchThing3(3, false);

production dispatchThing4 implements DispatchOp
top::UDExpr ::= @e::UDExpr
{
  forwards to dispatchThing3(e, 42, true);
}

wrongFlowCode "Tree e in production flow:overloadThing2 is shared in multiple places" {
production overloadThing2
top::UDExpr ::= e::UDExpr
{
  local otherRef::UDExpr = @e;
  e.env1 = top.env1;
  forwards to shareThing(e);
}
}

wrongFlowCode "Tree e in production flow:shareThing2 is shared in multiple places" {
production shareThing2
top::UDExpr ::= @e::UDExpr
{
  local otherRef::UDExpr = @e;
  local otherRef2::UDExpr = @e;
}
}

warnCode "Production shareThing has shared children in its signature, and can only be applied in the root position of a forward or forward production attribute equation" {
function dispatchFunction
UDExpr ::= e::UDExpr
{
  e.env1 = [];
  return shareThing(e);
}
}
