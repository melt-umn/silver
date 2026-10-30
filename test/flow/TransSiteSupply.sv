grammar flow;

-- The inherited attributes of a translation attribute of a tree are supplied wherever they are supplied to the tree
-- (as tssA.tssEnv): by a production that the tree is shared into, for its child's translation attribute,
-- or by forwarding, when the production does not define the translation attribute itself.

inherited attribute tssEnv::String;
synthesized attribute tssOut::String;

nonterminal TssZ with tssEnv, tssOut;
production tssZ
top::TssZ ::=
{ top.tssOut = top.tssEnv; }

translation attribute tssA::TssZ;
nonterminal TssX with tssA, tssOut;
production tssX
top::TssX ::=
{
  top.tssA = tssZ();
  top.tssOut = "x";
}

translation attribute tssB::TssZ;
nonterminal TssW with tssOut, tssB;
-- Supplies the translation attribute of its child by an equation.
production tssEqSite
top::TssW ::= c::TssX
{
  c.tssA.tssEnv = "eq";
  top.tssOut = c.tssA.tssOut;
  top.tssB = tssZ();
}
-- Supplies it by sharing it at a local.
production tssShareSite
top::TssW ::= c::TssX
{
  local y::TssZ = @c.tssA;
  y.tssEnv = "share";
  top.tssOut = y.tssOut;
  top.tssB = tssZ();
}
-- Shares it as its own translation attribute, so it is supplied by whatever decorates that.
production tssLhsSite
top::TssW ::= c::TssX
{
  top.tssB = @c.tssA;
  top.tssOut = "lhs";
}

-- x is shared into a production that supplies its translation attribute.
noWarnCode "requires missing inherited attribute" {
production tssViaEq
top::TssW ::= x::TssX
{
  local w::TssW = tssEqSite(@x);
  top.tssOut = x.tssA.tssOut ++ w.tssOut;
  top.tssB = tssZ();
}
}
noWarnCode "requires missing inherited attribute" {
production tssViaShare
top::TssW ::= x::TssX
{
  local w::TssW = tssShareSite(@x);
  top.tssOut = x.tssA.tssOut ++ w.tssOut;
  top.tssB = tssZ();
}
}
-- The production that x is shared into passes the translation attribute on as its own, which nothing supplies here.
warnCode "requires missing inherited attribute" {
production tssViaLhs
top::TssW ::= x::TssX
{
  local w::TssW = tssLhsSite(@x);
  top.tssOut = x.tssA.tssOut ++ w.tssOut;
  top.tssB = tssZ();
}
}
-- ... unless this production supplies it.
noWarnCode "requires missing inherited attribute" {
production tssViaLhsSupplied
top::TssW ::= x::TssX
{
  local w::TssW = tssLhsSite(@x);
  w.tssB.tssEnv = "lhs";
  top.tssOut = x.tssA.tssOut;
  top.tssB = tssZ();
}
}
-- The production that x is shared into forwards to one that supplies it.
production tssFwdEqSite
top::TssW ::= c::TssX
{
  top.tssB = tssZ();
  forwards to tssEqSite(@c);
}
noWarnCode "requires missing inherited attribute" {
production tssViaFwdEq
top::TssW ::= x::TssX
{
  local w::TssW = tssFwdEqSite(@x);
  top.tssOut = x.tssA.tssOut ++ w.tssOut;
  top.tssB = tssZ();
}
}

-- x is shared as the forward, so its translation attribute is the production's own,
-- unless the production defines the translation attribute.
noWarnCode "requires missing inherited attribute" {
production tssFwdShared
top::TssX ::= x::TssX
{
  top.tssOut = x.tssA.tssOut;
  forwards to @x;
}
}
warnCode "requires missing inherited attribute" {
production tssFwdSharedDefined
top::TssX ::= x::TssX
{
  top.tssA = tssZ();
  top.tssOut = x.tssA.tssOut;
  forwards to @x;
}
}

-- The translation attribute of the forward, likewise.
noWarnCode "requires missing inherited attribute" {
production tssFwd
top::TssX ::=
{
  top.tssOut = forward.tssA.tssOut;
  forwards to tssX();
}
}
warnCode "requires missing inherited attribute" {
production tssFwdDefined
top::TssX ::=
{
  top.tssA = tssZ();
  top.tssOut = forward.tssA.tssOut;
  forwards to tssX();
}
}
