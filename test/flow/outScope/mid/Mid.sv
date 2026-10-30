grammar flow:outScope:mid;

imports flow;
imports flow:outScope:hidden;

-- Productions in scope at flow:outScope that apply those of flow:outScope:hidden, which is not.

production osMid
top::OB ::= z::OB
{
  forwards to osHidden(@z);
}
production osMidNone
top::OB ::= z::OB
{
  forwards to osHiddenNone(@z);
}
production osMidU
top::OB ::= z::OB
{
  local y::OB = osHid(@z, true);
  y.obK = top.obK;
  top.obR = y.obR;
  forwards to obLeaf();
}
-- As in osHid (see Hidden.sv), y.obR seems to depend on obM.
warnCode "Synthesized equation obR exceeds flow type with dependencies on flow:obM" {
production osMidUBoth
top::OB ::= z::OB
{
  local y::OB = osHid(@z, true);
  y.obK = top.obK;
  y.obM = top.obM;
  top.obR = y.obR;
  forwards to obLeaf();
}
}

production osMidN
top::GExpr ::= m::GExpr
{
  local p::OsN = osImplN;
  forwards to p(@m);
}
production osOuterMid
top::GExpr ::= m::GExpr
{
  local p::OsOuter = osOuterImpl;
  forwards to p(@m);
}
production osOuterNMid
top::GExpr ::= m::GExpr
{
  local p::OsOuterN = osOuterNImpl;
  forwards to p(@m);
}
production osBareMid
top::GExpr ::= m::GExpr
{
  local p::OsBare = osBareImpl;
  forwards to p(@m);
}
-- OsNoHost has no host implementation, and osNoHostExt is not a host implementation either, as
-- OsNoHost's grammar does not export this grammar; osNoHostExt forwards to an application of
-- OsNoHost with its child.
production osNoHostExt implements OsNoHost
top::GExpr ::= a::GExpr
{
  local d::OsNoHost = osNoHostExt;
  forwards to d(@a);
}
production osNoHostMid
top::GExpr ::= m::GExpr
{
  local p::OsNoHost = osNoHostExt;
  forwards to p(@m);
}

production osTrMid
top::OB ::= z::OBT
{
  forwards to osTrHidden(@z);
}

-- As in osHid (see Hidden.sv), the forward's obR seems to depend on obM.
warnCode "In production flow:outScope:mid:osParentMid, the implicit copy equation for flow:obR (due to forwarding) would exceed the attribute's flow type with dependencies on flow:obM" {
production osParentMid
top::OB ::= z::OB
{
  local y::OB = obH(@z);
  forwards to osHid(@y, true);
}
}
