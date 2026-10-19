grammar flow;

-- A tree shared into a local keeps the equations of the tree it was shared from, which was
-- decorated first.  So where the local is shared, the inherited attribute vertices at the
-- decoration site reach those equations, through any number of locals (see addDecSiteTreeEqs).

inherited attribute lcK::String;
inherited attribute lcM::String;
synthesized attribute lcS::String;
nonterminal LC with lcK, lcM, lcS;
flowtype LC = decorate {}, forward {}, lcS {lcK};

-- Accesses the inherited attribute lcK of its child, so lcS is what its applications supply to that
-- child.
production lcReadsK
top::LC ::= c::LC
{
  c.lcK = top.lcK;
  top.lcS = c.lcK;
}

-- lcReadsK's child keeps x's equation, as this production decorated x first, so x.lcK hides
-- lcReadsK's c.lcK, which lcReadsK's consumers assume depends on lcK.  This warning suppresses the
-- warning about the implicit copy of lcS (see FlowTypeCopyEquation.sv).
warnCode "Inherited override equation for flow:lcK on child x may exceed a flow type with hidden transitive dependencies on flow:lcM; on some reference to this tree, this attribute may be expected to depend only on flow:lcK (from local flow:lcChain:local:" {
production lcChain
top::LC ::= x::LC
{
  x.lcK = top.lcM;
  local y::LC = @x;
  forwards to lcReadsK(@y);
}
}

-- The same through two locals.
warnCode "Inherited override equation for flow:lcK on child x may exceed a flow type with hidden transitive dependencies on flow:lcM; on some reference to this tree, this attribute may be expected to depend only on flow:lcK (from local flow:lcChain2:local:" {
production lcChain2
top::LC ::= x::LC
{
  x.lcK = top.lcM;
  local y::LC = @x;
  local z::LC = @y;
  forwards to lcReadsK(@z);
}
}

-- Where a tree has no equation for an attribute, the local it is shared into supplies the
-- attribute: y.lcK is z's equation, so y.lcS depends on lcM (see beforeSharing).
warnCode "Synthesized equation lcS exceeds flow type with dependencies on flow:lcM" {
production lcLater
top::LC ::= x::LC
{
  local y::LC = @x;
  local z::LC = @y;
  z.lcK = top.lcM;
  top.lcS = y.lcS;
}
}

-- l holds the tree shared into it only where b is true; otherwise l is an application of ckP, whose
-- child e gets ckK from ckP's own ckK.  So the override of e.ckK from ckM hides a dependency (see
-- mapApplied), even though the tree shared into l has ckK from ckM.
inherited attribute ckK::String;
inherited attribute ckM::String;
synthesized attribute ckS::String;
nonterminal CK with ckK, ckM, ckS;
flowtype CK = decorate {}, forward {}, ckS {};
production ckQuiet
top::CK ::=
{ top.ckS = ""; }
production ckP
top::CK ::= c::CK
{
  c.ckK = top.ckK;
  c.ckM = top.ckM;
  top.ckS = "";
}
warnCode "Inherited override equation for flow:ckK on child e may exceed a flow type with hidden transitive dependencies on flow:ckM" {
production ckCond
top::CK ::= e::CK b::Boolean
{
  local r2::CK = ckQuiet();
  r2.ckK = top.ckM;
  r2.ckM = top.ckM;
  local r::CK = @r2;
  e.ckK = top.ckM;
  e.ckM = top.ckM;
  local l::CK = if b then @r else ckP(@e);
  l.ckK = top.ckK;
  l.ckM = top.ckM;
  forwards to @l;
}
}
