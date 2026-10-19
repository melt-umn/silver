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

-- lcReadsK's child keeps x's equation, as this production decorated x first, so the implicit copy
-- of lcS depends on x.lcK, from lcM.
warnCode "In production flow:lcChain, the implicit copy equation for flow:lcS (due to forwarding) would exceed the attribute's flow type with dependencies on flow:lcM" {
production lcChain
top::LC ::= x::LC
{
  x.lcK = top.lcM;
  local y::LC = @x;
  forwards to lcReadsK(@y);
}
}

-- The same through two locals.
warnCode "In production flow:lcChain2, the implicit copy equation for flow:lcS (due to forwarding) would exceed the attribute's flow type with dependencies on flow:lcM" {
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

