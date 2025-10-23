grammar flow:ext;

imports flow;

warnCode "may exceed a flow type with hidden transitive dependencies" {
production remoteExceedsOverrideInExt
top::RSExpr ::= e::RSExpr
{
  e.env1 = top.env1;

  local e1::RSExpr = copy12(copy12From2(copy12(@e)));
  e1.env1 = top.env1;
  e1.env2 = top.env2;

  top.errors1 = e.errors1;
  top.errors2 = e.errors2;
}
}

warnCode "flow:env1 on child e may exceed a flow type with hidden transitive dependencies on flow:env1; on some reference to this tree, this attribute may be expected to depend only on flow:env2" {
production uselessOverrideHiddenTransDepInExt
top::RSExpr ::= e::RSExpr
{
  e.env1 = top.env1;  -- Hidden transitive dependency
  local e1::RSExpr = @e;
  e1.env1 = top.env2;

  top.errors1 = e1.errors1;
  top.errors2 = false;
}
}

warnCode "may exceed a flow type with hidden transitive dependencies" {
production fwrdDecSiteExceedsFTInExt
top::RSExpr ::= e::RSExpr
{
  e.env1 = top.env2;
  forwards to @e;
}
}

warnCode "may exceed a flow type with hidden transitive dependencies" {
production projExceedsFTInExt
top::RSExpr ::= e::RSExpr
{
  local e1::RSExpr = copy12(@e);
  e1.env1 = top.env2;
  forwards to @e1;
}
}

warnCode "may exceed a flow type with hidden transitive dependencies" {
production condDecExceedsFTInExt
top::RSExpr ::= e::RSExpr
{
  e.env1 = top.env2;
  forwards to
    if top.env1 == [] then copy12(@e) else base();
}
}

warnCode "may exceed a flow type with hidden transitive dependencies" {
production overrideHiddenTransitiveDepInExt
top::RSExpr ::= e::RSExpr
{
  e.env1 = top.env1;
  e.env2 = top.env2;
  local e1::RSExpr = @e;
  e1.env2 = [];
}
}

warnCode "may exceed a flow type with hidden transitive dependencies" {
production anonDecUselessOverrideExceedsFTInExt
top::RSExpr ::= e::RSExpr
{
  e.env1 = [];
  e.env2 = top.env1 ++ top.env2;
  local d::Decorated RSExpr with {env2} = decorate @e with {env2 = top.env2;};
  top.errors1 = e.errors1;
  top.errors2 = e.errors2;
}
}
