grammar flow:ext;

imports flow;

-- The innermost copy12 supplies e.env1 from env2, through copy12From2, which is all an aspect of
-- copy12 may assume; the forward makes e1 visible beyond this production, so the override from env1
-- hides a dependency.
warnCode "Inherited override equation for flow:env1 on child e may exceed a flow type with hidden transitive dependencies on flow:env1" {
production remoteExceedsOverrideInExt
top::RSExpr ::= e::RSExpr
{
  e.env1 = top.env1;

  local e1::RSExpr = copy12(copy12From2(copy12(@e)));
  e1.env1 = top.env1;
  e1.env2 = top.env2;

  top.errors1 = e.errors1;
  top.errors2 = e.errors2;
  forwards to @e1;
}
}

-- pmFromM supplies its child's pmK from pmM, so pmS's flow type has only pmM.  Supplying the
-- child's pmK from this production's own pmK hides that dependency, even though this production
-- forwards to the application (see pathBound).
warnCode "Inherited override equation for flow:pmK on child e may exceed a flow type with hidden transitive dependencies on flow:pmK" {
production pmRoot
top::PM ::= e::PM
{
  e.pmK = top.pmK;
  forwards to pmFromM(@e);
}
}

-- An override in this grammar's own production is recorded although flow does not export flow:ext
-- (see defLHSRecorded), so these behave as their twins in flow's RefSiteProj.sv.  Forwarding keeps
-- them from being orphaned.
noWarnCode "hidden transitive" {
production uselessOverrideHiddenTransDepInExt
top::RSExpr ::= e::RSExpr
{
  e.env1 = top.env1;
  local e1::RSExpr = @e;
  e1.env1 = top.env2;

  top.errors1 = e1.errors1;
  top.errors2 = false;
  forwards to base();
}
}

warnCode "the implicit copy equation for flow:errors1 (due to forwarding) would exceed the attribute's flow type with dependencies on flow:env2" {
production fwrdDecSiteExceedsFTInExt
top::RSExpr ::= e::RSExpr
{
  e.env1 = top.env2;
  forwards to @e;
}
}

warnCode "the implicit copy equation for flow:errors1 (due to forwarding) would exceed the attribute's flow type with dependencies on flow:env2" {
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

noWarnCode "hidden transitive" {
production overrideHiddenTransitiveDepInExt
top::RSExpr ::= e::RSExpr
{
  e.env1 = top.env1;
  e.env2 = top.env2;
  local e1::RSExpr = @e;
  e1.env2 = [];
  forwards to base();
}
}

noWarnCode "hidden transitive" {
production anonDecUselessOverrideExceedsFTInExt
top::RSExpr ::= e::RSExpr
{
  e.env1 = [];
  e.env2 = top.env1 ++ top.env2;
  local d::Decorated RSExpr with {env2} = decorate @e with {env2 = top.env2;};
  top.errors1 = e.errors1;
  top.errors2 = e.errors2;
  forwards to base();
}
}
