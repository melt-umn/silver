grammar flow;

-- An access of a synthesized attribute on a tree that the production constructs requires only the inherited
-- attributes of the flow type that the productions it is built from use, as the tile stitch points show.
-- A tree whose productions are not known here still requires the whole flow type.

inherited attribute caEnv1::String;
inherited attribute caEnv2::String;
inherited attribute caEnv3::String;
synthesized attribute caOut::String;

nonterminal CaExpr with caEnv1, caEnv2, caEnv3, caOut;
flowtype caOut {caEnv1, caEnv2} on CaExpr;

-- Uses both.
production caBoth
top::CaExpr ::=
{ top.caOut = top.caEnv1 ++ top.caEnv2; }
-- Uses only caEnv2.
production caTwo
top::CaExpr ::=
{ top.caOut = top.caEnv2; }
-- Passes both to its child, and uses only the child.
production caWrap
top::CaExpr ::= c::CaExpr
{
  c.caEnv1 = top.caEnv1;
  c.caEnv2 = top.caEnv2;
  top.caOut = c.caOut;
}

noWarnCode "requires missing inherited attribute" {
production caLocalTwo
top::CaExpr ::=
{
  local w::CaExpr = caTwo();
  w.caEnv2 = "2";
  top.caOut = w.caOut;
}
}
warnCode "Access of synthesized attribute caOut on w requires missing inherited attribute(s) flow:caEnv2 to be supplied" {
production caLocalTwoMissing
top::CaExpr ::=
{
  local w::CaExpr = caTwo();
  top.caOut = w.caOut;
}
}
warnCode "Access of synthesized attribute caOut on w requires missing inherited attribute(s) flow:caEnv1 to be supplied" {
production caLocalBoth
top::CaExpr ::=
{
  local w::CaExpr = caBoth();
  w.caEnv2 = "2";
  top.caOut = w.caOut;
}
}

-- A constructed tree nested in another, including a literal leaf.
noWarnCode "requires missing inherited attribute" {
production caNested
top::CaExpr ::=
{
  local w::CaExpr = caWrap(caWrap(caTwo()));
  w.caEnv2 = "2";
  top.caOut = w.caOut;
}
}
warnCode "Access of synthesized attribute caOut on w requires missing inherited attribute(s) flow:caEnv1 to be supplied" {
production caNestedBoth
top::CaExpr ::=
{
  local w::CaExpr = caWrap(caWrap(caBoth()));
  w.caEnv2 = "2";
  top.caOut = w.caOut;
}
}

-- The forward is constructed too.
noWarnCode "requires missing inherited attribute" {
production caForward
top::CaExpr ::=
{
  top.caOut = forward.caOut;
  forwards to caWrap(caTwo());
}
}

-- A child shared into a constructed tree is not known here, so the tree requires its whole flow type.
warnCode "Access of synthesized attribute caOut on w requires missing inherited attribute(s) flow:caEnv1 to be supplied" {
production caShared
top::CaExpr ::= x::CaExpr
{
  local w::CaExpr = caWrap(@x);
  w.caEnv2 = "2";
  top.caOut = w.caOut;
}
}

-- A tree shared from a constructed local requires only what the local's productions use.
noWarnCode "requires missing inherited attribute" {
production caSharedLocal
top::CaExpr ::=
{
  local a::CaExpr = caTwo();
  local b::CaExpr = @a;
  b.caEnv2 = "2";
  top.caOut = b.caOut;
}
}
warnCode "Access of synthesized attribute caOut on b requires missing inherited attribute(s) flow:caEnv2 to be supplied" {
production caSharedLocalMissing
top::CaExpr ::=
{
  local a::CaExpr = caTwo();
  local b::CaExpr = @a;
  top.caOut = b.caOut;
}
}

-- The same holds for a tree decorated in a global.
noWarnCode "requires missing inherited attribute" {
global caGlobalTwo::String = decorate caTwo() with { caEnv2 = "2"; }.caOut;
}
warnCode "requires missing inherited attribute(s) flow:caEnv2 to be supplied to anonymous decoration site" {
global caGlobalTwoMissing::String = decorate caTwo() with {}.caOut;
}

-- A constructed tree's translation attribute.
translation attribute caTr::CaExpr;
nonterminal CaHost with caTr, caOut;
production caHostTwo
top::CaHost ::=
{
  top.caTr = caTwo();
  top.caOut = "";
}
noWarnCode "requires missing inherited attribute" {
production caTransTwo
top::CaExpr ::=
{
  local h::CaHost = caHostTwo();
  h.caTr.caEnv2 = "2";
  top.caOut = h.caTr.caOut;
}
}
warnCode "Access of synthesized attribute caOut on h.caTr requires missing inherited attribute(s) flow:caEnv2 to be supplied" {
production caTransTwoMissing
top::CaExpr ::=
{
  local h::CaHost = caHostTwo();
  top.caOut = h.caTr.caOut;
}
}

-- The dependencies of a literal leaf of a constructed tree are not lost:
-- here caOut depends on caEnv3 through the leaf, beyond its flow type.
warnCode "Synthesized equation caOut exceeds flow type with dependencies on flow:caEnv3" {
production caLeafDeps
top::CaExpr ::=
{
  local w::CaExpr = caWrap(caTwo());
  w.caEnv1 = top.caEnv1;
  w.caEnv2 = top.caEnv3;
  top.caOut = w.caOut;
}
}

-- A child shared through the signature has no stitch point of its own here, so an access on it still
-- requires the whole flow type, from the productions that share the child into this one.
warnCode "Access of synthesized attribute caOut on c requires missing inherited attribute(s) flow:caEnv1" {
production caSigShare
top::CaExpr ::= @c::CaExpr
{
  top.caOut = c.caOut;
}
}
production caSigShareUser
top::CaExpr ::= x::CaExpr
{
  x.caEnv2 = "2";
  forwards to caSigShare(x);
}
