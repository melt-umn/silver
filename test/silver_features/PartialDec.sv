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
top::PDExpr ::= e::Decorated! PDExpr with {env1}
{
  undecorates to pdOp1(e);
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
top::PDExpr ::= e::Decorated! PDExpr with {env1}
{
  undecorates to pdOp2(e);
  local e2::Decorated! PDExpr with {env1} = e;
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
top::PDExpr ::= e::Decorated! PDExpr with {env1}
{
  undecorates to pdOp3(e);
  local e2::Decorated PDExpr = decorate withEnv1(e) with {env2 = top.env2;};
  top.errors1 = e2.errors1;
  top.errors2 = e2.errors2;
}

function withEnv1
Decorated! PDExpr with {env1} ::= x::Decorated! PDExpr with {env1}
{ return x; }

production pdOp4
top::PDExpr ::= e::PDExpr
{
  e.env1 = top.env1;
  local e2::Decorated! PDExpr with {env1} = e;
  e2.env2 = top.env2;
  forwards to pdOp4Impl(e2);
}

production pdOp4Impl
top::PDExpr ::= e::Decorated! PDExpr
{
  undecorates to pdOp4(e);
  local e2::Decorated! PDExpr = e;
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

wrongCode "Cannot specialize" {
  global uniqueRefId::(Decorated! PDExpr ::= Decorated! PDExpr) = id;
}
