grammar flow:ext;

warnCode "Inherited override equation for flow:env2 has excess dependencies" {
production extDispatchInhExceedsHost implements DispatchOp2
top::UDExpr ::= e::UDExpr
{
  e.env2 = if e.errors1 then [] else top.env1;

  local prod::DispatchOp2 = doimpl1;
  forwards to prod(@e);
}
}

warnCode "Synthesized override equation for flow:errors1 has excess dependencies" {
production extDispatchSynExceedsHost implements DispatchOp2
top::UDExpr ::= e::UDExpr
{
  top.errors1 = !null(e.env2);

  local prod::DispatchOp2 = doimpl1;
  forwards to prod(@e);
}
}
