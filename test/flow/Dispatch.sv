grammar flow;

synthesized attribute errors1::Boolean;
synthesized attribute errors2::Boolean;

nonterminal UDExpr with env1, env2, errors1, errors2;

production overloadThing
top::UDExpr ::= e::UDExpr
{
  e.env1 = top.env1;
  e.env2 = [];
  forwards to dispatchThing(e);
}

production dispatchThing
top::UDExpr ::= @e::UDExpr
{
  top.errors1 = e.errors1;
  top.errors2 = !null(e.env1);
}

wrongFlowCode "Tree e in production flow:overloadThing2 is shared in multiple places" {
production overloadThing2
top::UDExpr ::= e::UDExpr
{
  local otherRef::UDExpr = @e;
  e.env1 = top.env1;
  forwards to dispatchThing(e);
}
}

wrongFlowCode "Tree e in production flow:dispatchThing is shared in multiple places" {
aspect production dispatchThing
top::UDExpr ::= @e::UDExpr
{
  local otherRef::UDExpr = @e;
  local otherRef2::UDExpr = @e;
}
}

warnCode "Duplicate equation for env2 on e in production flow:alreadyDec" {
production alreadyDec
top::UDExpr ::= @e::UDExpr
{
  e.env2 = [];
}
}

wrongFlowCode "Unique reference to flow:uniqueReturn:e taken outside of a unique context. The return of flow:uniqueReturn is not a unique context as this function has no unique parameters." {
function uniqueReturn
UDExpr ::= e::UDExpr
{
  e.env1 = [];
  return dispatchThing(e);
}
}
