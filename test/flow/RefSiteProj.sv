grammar flow;

nonterminal RSExpr with env1, env2, errors1, errors2;
flowtype RSExpr = errors1 {env1}, errors2 {env1, env2};

production copy1
top::RSExpr ::= e::RSExpr
{
  e.env1 = top.env1;

  top.errors1 = e.errors1;
  top.errors2 = false;
}

production copy12
top::RSExpr ::= e::RSExpr
{
  e.env1 = top.env1;
  e.env2 = top.env2;

  top.errors1 = e.errors1;
  top.errors2 = e.errors2;
}

production copy12From1
top::RSExpr ::= e::RSExpr
{
  e.env1 = top.env1;
  e.env2 = top.env1;

  top.errors1 = e.errors1;
  top.errors2 = e.errors2;
}

production copy12From2
top::RSExpr ::= e::RSExpr
{
  e.env1 = top.env2;
  e.env2 = top.env2;

  top.errors1 = false;
  top.errors2 = e.errors2;
}

production base
top::RSExpr ::=
{
  top.errors1 = null(top.env1);
  top.errors2 = null(top.env2);
}

production proj1
top::RSExpr ::= e::RSExpr
{
  top.errors1 = e.errors1;
  forwards to copy1(@e);
}

warnCode "missing remote equation" {
production proj2Missing
top::RSExpr ::= e::RSExpr
{
  top.errors2 = e.errors2;
  forwards to copy1(@e);
}
}

production proj12
top::RSExpr ::= e::RSExpr
{
  top.errors2 = e.errors2;
  forwards to copy12(@e);
}

warnCode "missing remote equation" {
production projNestedMissing
top::RSExpr ::= e::RSExpr
{
  top.errors2 = e.errors2;
  forwards to copy12(copy1(copy12(@e)));
}
}

production projNestedLocals
top::RSExpr ::= e::RSExpr
{
  local e1::RSExpr = @e;
  local e2::RSExpr = copy12(@e1);
  local e3::RSExpr = copy12(copy1(@e2));

  e3.env1 = top.env1;
  e3.env2 = top.env2;
  top.errors1 = e.errors1;
  top.errors2 = false;
}

warnCode "missing remote equation" {
production projNestedLocalsMissing
top::RSExpr ::= e::RSExpr
{
  local e1::RSExpr = @e;
  local e2::RSExpr = copy12(@e1);
  local e3::RSExpr = copy12(copy1(@e2));

  e3.env1 = top.env1;
  e3.env2 = top.env2;
  top.errors1 = e.errors1;
  top.errors2 = e.errors2;
}
}

production projNestedLocalsFwrd
top::RSExpr ::= e::RSExpr
{
  local e1::RSExpr = @e;
  local e2::RSExpr = copy12(@e1);
  local e3::RSExpr = copy12(copy1(@e2));

  e2.env1 = "a" :: top.env1;
  forwards to @e3;
}

production proj1AvoidMissing
top::RSExpr ::= e::RSExpr
{
  top.errors2 = e.errors2;
  forwards to copy1(copy12From1(@e));
}

production incrementalDec
top::RSExpr ::= e::RSExpr
{
  e.env1 = top.env1;
  local e1::Decorated! RSExpr with {env1} = e;
  e1.env2 = top.env2;

  top.errors1 = e1.errors1;
  top.errors2 = e.errors2;
}

warnCode "equation errors1 exceeds flow type with dependencies on flow:env2" {
production remoteExceeds
top::RSExpr ::= e::RSExpr
{
  local e1::RSExpr = copy12(copy12From2(copy12(@e)));
  e1.env1 = top.env1;
  e1.env2 = top.env2;

  top.errors1 = e.errors1;
  top.errors2 = e.errors2;
}
}

production remoteExceedsOverride
top::RSExpr ::= e::RSExpr
{
  e.env1 = top.env1;

  local e1::RSExpr = copy12(copy12From2(copy12(@e)));
  e1.env1 = top.env1;
  e1.env2 = top.env2;

  top.errors1 = e.errors1;
  top.errors2 = e.errors2;
}

warnCode "exceeds FT" {
production fwrdDecSiteExceedsFT
top::RSExpr ::= e::RSExpr
{
  e.env1 = top.env2;
  forwards to @e;
}
}

warnCode "exceeds FT" {
production projExceedsFT
top::RSExpr ::= e::RSExpr
{
  local e1::RSExpr = copy12(@e);
  e1.env1 = top.env2;
  forwards to @e1;
}
}

