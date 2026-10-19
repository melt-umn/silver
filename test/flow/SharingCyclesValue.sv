grammar flow;

-- Sharing translations with flow types like toCore {toCore.env}: every path through the value of
-- such a translation reaches the inherited attributes on it.  So where the value of the shared tree
-- reaches the attribute checked, the sharing-cycle check counts the value of a translation
-- attribute in what the decoration site supplies only through the inherited attributes of the tree
-- that translation attribute is on (see valueFreeDeps).

nonterminal TVExpr;
nonterminal TVCore;
inherited attribute tvEnv::Integer occurs on TVExpr, TVCore;
synthesized attribute tvVal::Integer occurs on TVExpr, TVCore;
translation attribute tvCore::TVCore occurs on TVExpr;
flowtype tvVal {tvEnv} on TVExpr, TVCore;
flowtype tvCore {tvEnv, tvCore.tvEnv} on TVExpr;

abstract production tvCoreLit
top::TVCore ::= i::Integer
{
  top.tvVal = i + top.tvEnv;
}
abstract production tvCoreNeg
top::TVCore ::= e::TVCore
{
  e.tvEnv = top.tvEnv;
  top.tvVal = 1 - e.tvVal;
}
abstract production tvCoreSeq
top::TVCore ::= s1::TVCore  s2::TVCore
{
  s1.tvEnv = top.tvEnv;
  s2.tvEnv = s1.tvEnv;
  top.tvVal = s1.tvVal + s2.tvVal;
}
abstract production tvCoreAdd
top::TVCore ::= n::Integer  b::TVCore
{
  b.tvEnv = top.tvEnv + n;
  top.tvVal = b.tvVal;
}
abstract production tvLitIf
top::TVExpr ::= i::Integer
{
  top.tvVal = i + top.tvEnv;
  top.tvCore = if top.tvEnv == 0 then tvCoreLit(i) else tvCoreNeg(tvCoreLit(i));
}

dispatch TVImpl = TVCore ::= e::TVCore;
production tvImplNeg implements TVImpl
top::TVCore ::= e::TVCore
{
  forwards to tvCoreNeg(@e);
}

-- What the application of TVImpl supplies to e.tvCore depends on the value of e.tvCore, from which
-- an implementation not known here may construct its forward (see addDispatchUnknownImplSynEqs).
noWarnCode "a cycle may exist" {
abstract production tvNeg
top::TVExpr ::= e::TVExpr
{
  e.tvEnv = top.tvEnv;
  top.tvVal = 1 - e.tvVal;
  local prod::TVImpl = tvImplNeg;
  top.tvCore = prod(@e.tvCore);
}
}

-- The same for tvCore.tvEnv on e, a child of tvPrint shared into tvNeg: in the tile flow graph of
-- tvNeg, what tvNeg supplies for that attribute depends on the value of e.tvCore.
noWarnCode "a cycle may exist" {
abstract production tvPrint
top::TVExpr ::= e::TVExpr
{
  forwards to tvNeg(@e);
}
}

-- The same where e.tvCore is shared within the argument of TVImpl: what tvCoreSeq supplies to s2
-- for tvEnv is tvEnv on s1, which the application of TVImpl supplies to that argument.
noWarnCode "a cycle may exist" {
abstract production tvWrap
top::TVExpr ::= e::TVExpr
{
  e.tvEnv = top.tvEnv;
  top.tvVal = e.tvVal;
  local prod::TVImpl = tvImplNeg;
  top.tvCore = prod(tvCoreSeq(tvCoreLit(0), @e.tvCore));
}
}

-- The same through the value of y.tvCore, shared into TVPair beside x.tvCore: what the application
-- supplies to x.tvCore depends on the value of y.tvCore, which reaches tvEnv on y.tvCore, which
-- that application supplies.
dispatch TVPair = TVCore ::= a::TVCore  b::TVCore;
production tvPairImpl implements TVPair
top::TVCore ::= a::TVCore  b::TVCore
{
  forwards to tvCoreSeq(@a, @b);
}
noWarnCode "a cycle may exist" {
abstract production tvPairSite
top::TVExpr ::= x::TVExpr  y::TVExpr
{
  x.tvEnv = top.tvEnv;
  y.tvEnv = top.tvEnv;
  top.tvVal = x.tvVal + y.tvVal;
  local prod::TVPair = tvPairImpl;
  top.tvCore = prod(@x.tvCore, @y.tvCore);
}
}

-- Which implementation is applied depends on e.tvCore.tvVal, which needs tvEnv on e.tvCore: a real
-- cycle.
warnCode "Potentially missing inherited override equation for flow:tvEnv on e.flow:tvCore; a cycle may exist via its sharing decoration site top.flow:tvCore[flow:TVImpl:e]" {
abstract production tvChoice
top::TVExpr ::= e::TVExpr
{
  e.tvEnv = top.tvEnv;
  top.tvVal = e.tvVal;
  local prod::TVImpl = if e.tvCore.tvVal == 0 then tvImplNeg else tvImplNeg;
  top.tvCore = prod(@e.tvCore);
}
}

-- What tvImpl2Add supplies to e depends on the argument n, here e.tvCore.tvVal, which needs tvEnv
-- on e.tvCore: a real cycle.
dispatch TVImpl2 = TVCore ::= e::TVCore  n::Integer;
production tvImpl2Add implements TVImpl2
top::TVCore ::= e::TVCore  n::Integer
{
  forwards to tvCoreAdd(n, @e);
}
warnCode "Potentially missing inherited override equation for flow:tvEnv on e.flow:tvCore; a cycle may exist via its sharing decoration site top.flow:tvCore[flow:TVImpl2:e]" {
abstract production tvArg
top::TVExpr ::= e::TVExpr
{
  e.tvEnv = top.tvEnv;
  top.tvVal = e.tvVal;
  local prod::TVImpl2 = tvImpl2Add;
  top.tvCore = prod(@e.tvCore, e.tvCore.tvVal);
}
}

-- What tvPick supplies to a for tvEnv matches on b, here y.tvCore, whose root needs tvEnv on y,
-- which is x.tvCore.tvVal and needs tvEnv on x.tvCore: a real cycle, for y = tvLitIf(0).
abstract production tvPick
top::TVCore ::= a::TVCore  b::TVCore
{
  a.tvEnv = case b of tvCoreLit(_) -> top.tvEnv | _ -> top.tvEnv + 1 end;
  b.tvEnv = top.tvEnv;
  top.tvVal = a.tvVal + b.tvVal;
}
warnCode "Potentially missing inherited override equation for flow:tvEnv on x.flow:tvCore; a cycle may exist via its sharing decoration site top.flow:tvCore[flow:tvPick:a]" {
abstract production tvPairCycle
top::TVExpr ::= x::TVExpr  y::TVExpr
{
  x.tvEnv = top.tvEnv;
  y.tvEnv = x.tvCore.tvVal;
  top.tvVal = x.tvVal;
  top.tvCore = tvPick(@x.tvCore, @y.tvCore);
}
}

-- What tvSelf supplies to e.tvCore for tvEnv, at x, needs the value of top.tvCore, whose root needs
-- x.tvVal and so tvEnv on e.tvCore: a real cycle, through the equation of tvSelf for its own
-- translation attribute.
function tvValOf
Integer ::= c::Decorated TVCore
{
  return c.tvVal;
}
warnCode "Potentially missing inherited override equation for flow:tvEnv on e.flow:tvCore; a cycle may exist via its sharing decoration site flow:tvSelf:local:" {
abstract production tvSelf
top::TVExpr ::= e::TVExpr
{
  e.tvEnv = top.tvEnv;
  top.tvVal = e.tvVal;
  local x::TVCore = @e.tvCore;
  x.tvEnv = tvValOf(top.tvCore);
  top.tvCore = if x.tvVal == 0 then tvCoreLit(0) else tvCoreLit(1);
}
}

-- What tvCoreRoot supplies to c for tvEnv is tvVal on c, which needs tvEnv: a real cycle, through
-- an attribute of the shared translation rather than its value.
abstract production tvCoreRoot
top::TVCore ::= c::TVCore
{
  c.tvEnv = c.tvVal;
  top.tvVal = c.tvVal;
}
warnCode "Potentially missing inherited override equation for flow:tvEnv on e.flow:tvCore; a cycle may exist via its sharing decoration site top.flow:tvCore[flow:tvCoreRoot:c]" {
abstract production tvRoot
top::TVExpr ::= e::TVExpr
{
  e.tvEnv = top.tvEnv;
  top.tvVal = e.tvVal;
  top.tvCore = tvCoreRoot(@e.tvCore);
}
}

-- What otUsesK supplies to c for otI is otK on c, here the override a.otTrans.otK = a.otTrans.otS,
-- which needs otI on a.otTrans: a real cycle, through an equation of the production sharing
-- a.otTrans.
nonterminal OTExpr;
nonterminal OTTarget;
inherited attribute otI::String occurs on OTTarget;
inherited attribute otK::String occurs on OTTarget;
synthesized attribute otS::String occurs on OTTarget;
translation attribute otTrans::OTTarget occurs on OTExpr;
flowtype otS {otI, otK} on OTTarget;
flowtype otTrans {otTrans.otI, otTrans.otK} on OTExpr;
abstract production otUsesK
top::OTTarget ::= c::OTTarget
{
  c.otK = top.otK;
  c.otI = c.otK;
  top.otS = c.otS;
}
warnCode "Potentially missing inherited override equation for flow:otI on a.flow:otTrans; a cycle may exist via its sharing decoration site top.flow:otTrans[flow:otUsesK:c]" {
abstract production otCycle
top::OTExpr ::= a::OTExpr
{
  a.otTrans.otK = a.otTrans.otS;
  top.otTrans = otUsesK(@a.otTrans);
}
}
-- The same where a.otTrans is shared into the local y, at which the override is recorded: the
-- decoration site in otUsesK reaches it through y (see ownTreeEqDeps).
warnCode "Potentially missing inherited override equation for flow:otI on a.flow:otTrans; a cycle may exist via its sharing decoration site flow:otLocalCycle:local:" {
abstract production otLocalCycle
top::OTExpr ::= a::OTExpr
{
  a.otTrans.otK = a.otTrans.otS;
  local y::OTTarget = @a.otTrans;
  top.otTrans = otUsesK(@y);
}
}
