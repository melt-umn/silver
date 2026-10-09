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
