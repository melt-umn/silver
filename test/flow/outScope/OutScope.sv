grammar flow:outScope;

imports flow;
imports flow:outScope:mid;

-- Overrides whose paths enter productions and dispatch signatures that are not in scope here.  The
-- walk tells what an applied name is by its production graph, not by this grammar's environment
-- (see findDecSitePaths).

-- osHidden's equation for its child is the first equation on the path, and that equation's
-- consumers assume obM.
warnCode "Inherited override equation for flow:obK on child c may exceed a flow type with hidden transitive dependencies on flow:obK; on some reference to this tree, this attribute may be expected to depend only on flow:obM (from forward[flow:outScope:mid:osMid:z])" {
production osSite
top::OB ::= c::OB
{
  c.obK = top.obK;
  forwards to osMid(@c);
}
}
-- osHiddenNone neither gives its child an equation nor shares it, so no path reaches an equation.
noWarnCode "hidden transitive" {
production osSiteNone
top::OB ::= c::OB
{
  c.obK = top.obK;
  forwards to osMidNone(@c);
}
}
-- osHid's exclusive decoration sites meet: obH's consumers assume obK, and obJ's obM.
warnCode "Inherited override equation for flow:obK on child c may exceed a flow type with hidden transitive dependencies on flow:obM; on some reference to this tree, this attribute may be expected to depend on no left-side inherited attributes (from forward[flow:outScope:mid:osMidUBoth:z])" {
production osUnionBoth
top::OB ::= c::OB
{
  c.obK = top.obM;
  forwards to osMidUBoth(@c);
}
}

-- Into the implementations of a dispatch signature: their equations for its child are assumed as
-- both of the signature's graphs allow (see dispatchChildAllowedInhs), not as its tile flow graph
-- alone does.
warnCode "Inherited override equation for flow:gEnv on child a may exceed a flow type with hidden transitive dependencies on flow:gOther; on some reference to this tree, this attribute may be expected to depend only on flow:gCtx, flow:gEnv (from forward[flow:outScope:mid:osMidN:m])" {
production osSiteN
top::GExpr ::= a::GExpr
{
  a.gEnv = top.gOther;
  a.gCtx = top.gCtx;
  forwards to osMidN(@a);
}
}
-- An implementation of OsOuter that passes its child on to an application of GOpN: the walk enters
-- both.
warnCode "Inherited override equation for flow:gEnv on child x may exceed a flow type with hidden transitive dependencies on flow:gOther; on some reference to this tree, this attribute may be expected to depend only on flow:gCtx, flow:gEnv (from forward[flow:outScope:mid:osOuterMid:m]); flow:gOther is excluded by what flow:GOpN allows, through forward[flow:outScope:hidden:OsOuter:a], child qa of flow:outScope:hidden:osOuterImpl, forward[flow:GOpN:a]" {
production osOuterSite
top::GExpr ::= x::GExpr
{
  x.gEnv = top.gOther;
  x.gCtx = top.gCtx;
  forwards to osOuterMid(@x);
}
}
-- ...and where the inner dispatch signature, OsInner, is not in scope either.
warnCode "Inherited override equation for flow:gEnv on child x may exceed a flow type with hidden transitive dependencies on flow:gOther; on some reference to this tree, this attribute may be expected to depend only on flow:gCtx, flow:gEnv (from forward[flow:outScope:mid:osOuterNMid:m])" {
production osOuterNSite
top::GExpr ::= x::GExpr
{
  x.gEnv = top.gOther;
  x.gCtx = top.gCtx;
  forwards to osOuterNMid(@x);
}
}
-- Only implementations not known here supply the child of OsBare.
warnCode "Inherited override equation for flow:gEnv on child x may exceed a flow type with hidden transitive dependencies on flow:gOther; on some reference to this tree, this attribute may be expected to depend only on flow:gCtx, flow:gEnv (from forward[flow:outScope:mid:osBareMid:m])" {
production osBareSite
top::GExpr ::= x::GExpr
{
  x.gEnv = top.gOther;
  x.gCtx = top.gCtx;
  forwards to osBareMid(@x);
}
}
-- OsNoHost has no host implementations, so only its graph tells it from a production.
warnCode "Inherited override equation for flow:gEnv on child x may exceed a flow type with hidden transitive dependencies on flow:gOther; on some reference to this tree, this attribute may be expected to depend only on flow:gCtx, flow:gEnv (from forward[flow:outScope:mid:osNoHostMid:m])" {
production osNoHostSite
top::GExpr ::= x::GExpr
{
  x.gEnv = top.gOther;
  x.gCtx = top.gCtx;
  forwards to osNoHostMid(@x);
}
}

-- The route through the translation attribute of osTrHidden's child.
warnCode "Inherited override equation for flow:obTr.flow:obK on child c may exceed a flow type with hidden transitive dependencies on flow:obM; on some reference to this tree, this attribute may be expected to depend only on flow:obK (from forward[flow:outScope:mid:osTrMid:z])" {
production osTrSite
top::OB ::= c::OBT
{
  c.obTr.obK = top.obM;
  forwards to osTrMid(@c);
}
}

-- Vacuity is decided after the meet over osHid's own paths, which meet at no attribute, although
-- osMidU's y never gets obM, which the path through obJ needs (see mapApplied).
warnCode "Inherited override equation for flow:obK on child c may exceed a flow type with hidden transitive dependencies on flow:obF; on some reference to this tree, this attribute may be expected to depend on no left-side inherited attributes (from forward[flow:outScope:mid:osMidU:z])" {
production osUnion
top::OB ::= c::OB
{
  c.obK = top.obF;
  forwards to osMidU(@c);
}
}
-- The same in a parent walk: osParentMid's y gets obK along osHid's exclusive decoration sites.
warnCode "Inherited override equation for flow:obK on child c may exceed a flow type with hidden transitive dependencies on flow:obM; on some reference to this tree, this attribute may be expected to depend on no left-side inherited attributes (from forward[flow:outScope:mid:osParentMid:z])" {
production osParentSite
top::OB ::= c::OB
{
  c.obK = top.obM;
  forwards to osParentMid(@c);
}
}
