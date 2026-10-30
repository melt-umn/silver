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
top::UDExpr ::= @e::UDExpr
{
  e.env2 = top.env2;
  top.errors1 = e.errors1;
  top.errors2 = e.errors2;
}

-- These work, but have flow errors since we don't do reverse sharing through locals:

production udOp2
top::UDExpr ::= e::UDExpr
{
  e.env1 = top.env1;
  forwards to udOp2Impl(e);
}

production udOp2Impl
top::UDExpr ::= @e::UDExpr
{
  local e2::UDExpr = @e;
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
top::UDExpr ::= @e::UDExpr
{
  --local e2::Decorated UDExpr = decorate @e with {env2 = top.env2;};  -- TODO
  local e1::UDExpr = @e;
  e1.env2 = top.env2;
  local e2::UDExpr = @e1;
  top.errors1 = e2.errors1;
  top.errors2 = e2.errors2;
}

production udOp4
top::UDExpr ::= e::UDExpr
{
  e.env1 = top.env1;
  local e2::UDExpr = @e;
  e2.env2 = top.env2;
  forwards to udOp4Impl(e2);
}

production udOp4Impl
top::UDExpr ::= @e::UDExpr
{
  local e2::UDExpr = @e;
  top.errors1 = e2.errors1;
  top.errors2 = e2.errors2;
}

production udOp5
top::UDExpr ::= e::UDExpr
{
  e.env1 = top.env1;
  top.errors1 = e.errors1;
  forwards to udOp5Impl(e);
}

production udOp5Impl
top::UDExpr ::= @e::UDExpr
{
  e.env2 = forwardParent.env2;
  top.errors1 = forwardParent.errors1;
  top.errors2 = e.errors2;
}

global udTerm::UDExpr = udOp1(udOp2(udOp3(udOp4(udOp5(udVar("foo"))))));
equalityTest(decorate udTerm with { env1 = ["foo"]; env2 = ["foo"]; }.errors1, false, Boolean, silver_tests);
equalityTest(decorate udTerm with { env1 = ["foo"]; env2 = ["foo"]; }.errors2, false, Boolean, silver_tests);
equalityTest(decorate udTerm with { env1 = ["foo"]; env2 = []; }.errors1, false, Boolean, silver_tests);
equalityTest(decorate udTerm with { env1 = ["foo"]; env2 = []; }.errors2, true, Boolean, silver_tests);
equalityTest(decorate udTerm with { env1 = []; env2 = ["foo"]; }.errors1, true, Boolean, silver_tests);
equalityTest(decorate udTerm with { env1 = []; env2 = ["foo"]; }.errors2, true, Boolean, silver_tests);

-- A tree and its translation attribute can be shared in mutually exclusive branches of the same equation.
inherited attribute shEnv::String;
synthesized attribute shOut::String;

nonterminal ShY with shEnv, shOut;
production shY
top::ShY ::= n::String
{ top.shOut = n ++ "(" ++ top.shEnv ++ ")"; }

translation attribute shA::ShY;
nonterminal ShX with shEnv, shOut, shA;
production shX
top::ShX ::= n::String
{
  top.shOut = n ++ "(" ++ top.shEnv ++ ")";
  top.shA = shY(n ++ ".a");
}

nonterminal ShW with shOut;
production shWX
top::ShW ::= c::ShX
{
  c.shEnv = "wX";
  c.shA.shEnv = "wX.a";
  top.shOut = "wX[" ++ c.shOut ++ ", " ++ c.shA.shOut ++ "]";
}
-- A site that itself shares its child's translation attribute.
production shWXShareA
top::ShW ::= c::ShX
{
  c.shEnv = "wXShareA";
  local a::ShW = shWY(@c.shA);
  top.shOut = "wXShareA[" ++ c.shOut ++ ", " ++ a.shOut ++ "]";
}
production shWY
top::ShW ::= c::ShY
{
  c.shEnv = "wY";
  top.shOut = "wY[" ++ c.shOut ++ "]";
}

production shExclIf
top::ShW ::= b::Boolean x::ShX
{
  local y::ShW = if b then shWX(@x) else shWY(@x.shA);
  top.shOut = y.shOut;
}
production shExclCase
top::ShW ::= b::Boolean x::ShX
{
  local y::ShW = case b of true -> shWX(@x) | false -> shWY(@x.shA) end;
  top.shOut = y.shOut;
}
production shExclShareA
top::ShW ::= b::Boolean x::ShX
{
  local y::ShW = if b then shWXShareA(@x) else shWY(@x.shA);
  top.shOut = y.shOut;
}
production shExclFwd
top::ShW ::= b::Boolean x::ShX
{
  forwards to if b then shWX(@x) else shWY(@x.shA);
}

equalityTest(shExclIf(true, shX("x")).shOut, "wX[x(wX), x.a(wX.a)]", String, silver_tests);
equalityTest(shExclIf(false, shX("x")).shOut, "wY[x.a(wY)]", String, silver_tests);
equalityTest(shExclCase(true, shX("x")).shOut, "wX[x(wX), x.a(wX.a)]", String, silver_tests);
equalityTest(shExclCase(false, shX("x")).shOut, "wY[x.a(wY)]", String, silver_tests);
equalityTest(shExclShareA(true, shX("x")).shOut, "wXShareA[x(wXShareA), wY[x.a(wY)]]", String, silver_tests);
equalityTest(shExclShareA(false, shX("x")).shOut, "wY[x.a(wY)]", String, silver_tests);
equalityTest(shExclFwd(true, shX("x")).shOut, "wX[x(wX), x.a(wX.a)]", String, silver_tests);
equalityTest(shExclFwd(false, shX("x")).shOut, "wY[x.a(wY)]", String, silver_tests);

-- An application that a tree is shared into through a let binding is unique, as one it is shared into directly:
-- undecorating it gives back the tree undecorated, rather than its decoration at the site.
nonterminal ShV with shEnv, shOut;
production shV
top::ShV ::= c::ShX
{
  c.shEnv = top.shEnv;
  top.shOut = c.shOut;
}
production shLetUnique
top::ShW ::= x::ShX
{
  local v::ShV = let y::ShX = @x in shV(y) end;
  v.shEnv = "first";
  top.shOut = v.shOut ++ ", " ++ (decorate ^v with { shEnv = "second"; }).shOut;
}
production shDirectUnique
top::ShW ::= x::ShX
{
  local v::ShV = shV(@x);
  v.shEnv = "first";
  top.shOut = v.shOut ++ ", " ++ (decorate ^v with { shEnv = "second"; }).shOut;
}

equalityTest(shDirectUnique(shX("x")).shOut, "x(first), x(second)", String, silver_tests);
equalityTest(shLetUnique(shX("x")).shOut, "x(first), x(second)", String, silver_tests);
