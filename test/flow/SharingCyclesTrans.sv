grammar flow;

-- Sharing a translation attribute whose flow type includes inherited attributes on its
-- own tree must not be reported as a potential cycle: supplying those attributes at the
-- site depends on the shared tree's value, and the value's flow type on those attributes,
-- but that path only restates the flow type.

nonterminal SCExpr;
nonterminal SCTarget;
inherited attribute scEnv::Integer occurs on SCExpr, SCTarget;
synthesized attribute scVal::Integer occurs on SCExpr, SCTarget;
translation attribute scTrans::SCTarget occurs on SCExpr;
flowtype scVal {scEnv} on SCExpr, SCTarget;
flowtype scTrans {scEnv, scTrans.scEnv} on SCExpr;

abstract production scTgtLit
top::SCTarget ::= i::Integer
{
  top.scVal = i + top.scEnv;
}

abstract production scTgtPair
top::SCTarget ::= a::SCTarget b::SCTarget
{
  a.scEnv = top.scEnv;
  b.scEnv = top.scEnv;
  top.scVal = a.scVal + b.scVal;
}

abstract production scLit
top::SCExpr ::= i::Integer
{
  top.scVal = i + top.scEnv;
  top.scTrans = scTgtLit(i);
}

noWarnCode "a cycle may exist" {
abstract production scPair
top::SCExpr ::= a::SCExpr b::SCExpr
{
  a.scEnv = top.scEnv;
  b.scEnv = top.scEnv;
  top.scVal = a.scVal + b.scVal;
  top.scTrans = scTgtPair(@a.scTrans, @b.scTrans);
}
}

-- Supplying one inherited attribute at a decoration site can depend on its others.  Here e.cnI is cnReadsK's
-- c.cnI = c.cnK, and e.cnK = e.cnS needs e.cnI: a real cycle.
nonterminal CNExpr;
inherited attribute cnI::String occurs on CNExpr;
inherited attribute cnK::String occurs on CNExpr;
synthesized attribute cnS::String occurs on CNExpr;
flowtype cnS {cnI, cnK} on CNExpr;

abstract production cnLeaf
top::CNExpr ::=
{
  top.cnS = top.cnI;
}

abstract production cnReadsK
top::CNExpr ::= c::CNExpr
{
  c.cnK = top.cnK;
  c.cnI = c.cnK;
  top.cnS = c.cnS;
}

warnCode "Potentially missing inherited override equation for flow:cnI on e" {
abstract production cnCycle
top::CNExpr ::= e::CNExpr
{
  e.cnK = e.cnS;
  forwards to cnReadsK(@e);
}
}

-- The same through a production's fallback equation for a child shared through its signature: x.cyI is cyFall's
-- a.cyI = a.cyJ, and x.cyJ = x.cyI.
nonterminal CYExpr;
inherited attribute cyI::String occurs on CYExpr;
inherited attribute cyJ::String occurs on CYExpr;
synthesized attribute cyS::String occurs on CYExpr;

abstract production cyFall
top::CYExpr ::= @a::CYExpr
{
  a.cyI = a.cyJ;
  top.cyS = a.cyI;
}

warnCode "Potentially missing inherited override equation for flow:cyI on x" {
abstract production cySite
top::CYExpr ::= x::CYExpr
{
  x.cyJ = x.cyI;
  forwards to cyFall(x);
}
}

-- The tree shared into y already has cyI from x's equation, so cyFall's a.cyI = a.cyJ never
-- applies, and y.cyJ = y.cyI makes no cycle (see hasInhBeforeSharing).
noWarnCode "a cycle may exist" {
abstract production cySiteChain
top::CYExpr ::= x::CYExpr
{
  x.cyI = "";
  local y::CYExpr = @x;
  y.cyJ = y.cyI;
  forwards to cyFall(y);
}
}

-- What a decoration site supplies is followed along the paths of decoration sites from it (see
-- suppliedInhDeps), and not along the edges from a decoration site back to the tree shared there
-- (see addDecSiteTreeEqs).
nonterminal SLX;
inherited attribute slI::String occurs on SLX;
synthesized attribute slS::String occurs on SLX;
flowtype slS {slI} on SLX;
abstract production slLeaf
top::SLX ::=
{
  top.slS = top.slI;
}
noWarnCode "a cycle may exist" {
abstract production slLocalNoCycle
top::SLX ::= x::SLX
{
  local y::SLX = @x;
  y.slI = top.slI;
  top.slS = y.slS;
}
}
-- Along a chain of locals to the first equation.
warnCode "Potentially missing inherited override equation for flow:slI on x" {
abstract production slChainCycle
top::SLX ::= x::SLX
{
  local y::SLX = @x;
  local z::SLX = @y;
  z.slI = x.slS;
  top.slS = z.slS;
}
}
-- Into a production that a child is shared with through its signature.
nonterminal SGX;
inherited attribute sgI::String occurs on SGX;
synthesized attribute sgS::String occurs on SGX;
flowtype sgS {sgI} on SGX;
abstract production sgApply
top::SGX ::= @a::SGX
{
  a.sgI = top.sgI;
  top.sgS = a.sgS;
}
noWarnCode "a cycle may exist" {
abstract production sgSigNoCycle
top::SGX ::= x::SGX
{
  forwards to sgApply(x);
}
}
-- What a production applied at a decoration site gives its child from its own LHS is followed to
-- what the parent supplies to the application's tree.
nonterminal SBX;
inherited attribute sbI::String occurs on SBX;
synthesized attribute sbS::String occurs on SBX;
flowtype sbS {sbI} on SBX;
abstract production sbCopy
top::SBX ::= c::SBX
{
  c.sbI = top.sbI;
  top.sbS = c.sbS;
}
noWarnCode "a cycle may exist" {
abstract production sbParentNoCycle
top::SBX ::= x::SBX  b::Boolean
{
  local y::SBX = if b then sbCopy(@x) else @x;
  y.sbI = top.sbI;
  top.sbS = y.sbS;
}
}
-- Each sharing site is checked on its own: soUsesJ gives its child w.soJ for soI, which uses x
-- through w.soS, while soUsesK gives its child w.soK, which does not.
nonterminal SOX;
inherited attribute soI::String occurs on SOX;
inherited attribute soJ::String occurs on SOX;
inherited attribute soK::String occurs on SOX;
synthesized attribute soS::String occurs on SOX;
flowtype soS {soI, soJ, soK} on SOX;
abstract production soUsesJ
top::SOX ::= c::SOX
{
  c.soI = top.soJ;
  c.soJ = "";
  c.soK = "";
  top.soS = c.soS;
}
abstract production soUsesK
top::SOX ::= c::SOX
{
  c.soI = top.soK;
  c.soJ = "";
  c.soK = "";
  top.soS = c.soS;
}
warnCode "Potentially missing inherited override equation for flow:soI on x" {
abstract production soBranchCycle
top::SOX ::= x::SOX  z::SOX  b::Boolean
{
  local w::SOX = if b then soUsesJ(@x) else soUsesK(@z);
  w.soI = top.soI;
  w.soJ = w.soS;
  w.soK = top.soK;
  top.soS = "";
}
}
