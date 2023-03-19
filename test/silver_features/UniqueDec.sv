grammar silver_features;

synthesized attribute errors1::Boolean;
synthesized attribute errors2::Boolean;

nonterminal UDExpr with env1, env2, errors1, errors2;

production udVar
top::UDExpr ::= n::String
{
  top.errors1 = !contains(n, top.env1);
  top.errors2 = top.errors1 || !contains(n, top.env2);
}

production udOp1
top::UDExpr ::= e::UDExpr
{
  e.env1 = top.env1;
  forwards to udOp1Impl(e);
}

production udOp1Impl
top::UDExpr ::= e::Decorated! UDExpr with {env1}
{
  undecorates to udOp1(e);
  e.env2 = top.env2;
  top.errors1 = e.errors1;
  top.errors2 = e.errors2;
}

production udOp2
top::UDExpr ::= e::UDExpr
{
  e.env1 = top.env1;
  forwards to udOp2Impl(e);
}

production udOp2Impl
top::UDExpr ::= e::Decorated! UDExpr with {env1}
{
  undecorates to udOp2(e);
  local e2::Decorated! UDExpr with {env1} = e;
  e2.env2 = top.env2;
  top.errors1 = e2.errors1;
  top.errors2 = e2.errors2;
}

production udOp3
top::UDExpr ::= e::UDExpr
{
  --forwards to udOp3Impl(decorate e with {env1 = top.env1;});  -- TODO
  e.env1 = top.env1;
  forwards to udOp3Impl(e);
}

production udOp3Impl
top::UDExpr ::= e::Decorated! UDExpr with {env1}
{
  undecorates to udOp3(e);
  local e2::Decorated UDExpr = decorate withEnv1(e) with {env2 = top.env2;};
  top.errors1 = e2.errors1;
  top.errors2 = e2.errors2;
}

function withEnv1
Decorated! UDExpr with {env1} ::= x::Decorated! UDExpr with {env1}
{ return x; }

production udOp4
top::UDExpr ::= e::UDExpr
{
  e.env1 = top.env1;
  local e2::Decorated! UDExpr with {env1} = e;
  e2.env2 = top.env2;
  forwards to udOp4Impl(e2);
}

production udOp4Impl
top::UDExpr ::= e::Decorated! UDExpr
{
  undecorates to udOp4(e);
  local e2::Decorated! UDExpr = e;
  top.errors1 = e2.errors1;
  top.errors2 = e2.errors2;
}

global udTerm::UDExpr = udOp1(udOp2(udOp3(udOp4(udVar("foo")))));
equalityTest(decorate udTerm with { env1 = ["foo"]; env2 = ["foo"]; }.errors1, false, Boolean, silver_tests);
equalityTest(decorate udTerm with { env1 = ["foo"]; env2 = ["foo"]; }.errors2, false, Boolean, silver_tests);
equalityTest(decorate udTerm with { env1 = ["foo"]; env2 = []; }.errors1, false, Boolean, silver_tests);
equalityTest(decorate udTerm with { env1 = ["foo"]; env2 = []; }.errors2, true, Boolean, silver_tests);
equalityTest(decorate udTerm with { env1 = []; env2 = ["foo"]; }.errors1, true, Boolean, silver_tests);
equalityTest(decorate udTerm with { env1 = []; env2 = ["foo"]; }.errors2, true, Boolean, silver_tests);

wrongCode "Cannot specialize" {
  global uniqueRefId::(Decorated! UDExpr ::= Decorated! UDExpr) = id;
}
