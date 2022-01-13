grammar silver_features;

synthesized attribute errors1::Boolean;
synthesized attribute errors2::Boolean;

nonterminal PDExpr with env1, env2, errors1, errors2;

production pdVar
top::PDExpr ::= n::String
{
  top.errors1 = !contains(n, top.env1);
  top.errors2 = top.errors1 || !contains(n, top.env2);
}

production pdOp1
top::PDExpr ::= e::PDExpr
{
  e.env1 = top.env1;
  forwards to pdOp1Impl(e);
}

production pdOp1Impl
top::PDExpr ::= e::PartiallyDecorated PDExpr with {env1}
{
  e.env2 = top.env2;
  top.errors1 = e.errors1;
  top.errors2 = e.errors2;
}

production pdOp2
top::PDExpr ::= e::PDExpr
{
  e.env1 = top.env1;
  forwards to pdOp2Impl(e);
}

production pdOp2Impl
top::PDExpr ::= e::PartiallyDecorated PDExpr with {env1}
{
  local e2::PartiallyDecorated PDExpr with {env1} = e;
  e2.env2 = top.env2;
  top.errors1 = e2.errors1;
  top.errors2 = e2.errors2;
}

production pdOp3
top::PDExpr ::= e::PDExpr
{
  --forwards to pdOp3Impl(decorate e with {env1 = top.env1;});  -- TODO
  e.env1 = top.env1;
  forwards to pdOp3Impl(e);
}

production pdOp3Impl
top::PDExpr ::= e::PartiallyDecorated PDExpr with {env1}
{
  local e2::Decorated PDExpr = decorate e with {env2 = top.env2;};
  top.errors1 = e2.errors1;
  top.errors2 = e2.errors2;
}

production pdOp4
top::PDExpr ::= e::PDExpr
{
  e.env1 = top.env1;
  e.env2 = top.env2;
  forwards to pdOp4Impl(e);
}

production pdOp4Impl
top::PDExpr ::= e::PartiallyDecorated PDExpr
{
  local e2::PartiallyDecorated PDExpr = e;
  top.errors1 = e2.errors1;
  top.errors2 = e2.errors2;
}

global pdTerm::PDExpr = pdOp1(pdOp2(pdOp3(pdOp4(pdVar("foo")))));
equalityTest(decorate pdTerm with { env1 = ["foo"]; env2 = ["foo"]; }.errors1, false, Boolean, silver_tests);
equalityTest(decorate pdTerm with { env1 = ["foo"]; env2 = ["foo"]; }.errors2, false, Boolean, silver_tests);
equalityTest(decorate pdTerm with { env1 = ["foo"]; env2 = []; }.errors1, false, Boolean, silver_tests);
equalityTest(decorate pdTerm with { env1 = ["foo"]; env2 = []; }.errors2, true, Boolean, silver_tests);
equalityTest(decorate pdTerm with { env1 = []; env2 = ["foo"]; }.errors1, true, Boolean, silver_tests);
equalityTest(decorate pdTerm with { env1 = []; env2 = ["foo"]; }.errors2, true, Boolean, silver_tests);
