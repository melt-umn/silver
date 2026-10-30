grammar flow;

-- A forwarding production may not define a translation attribute in any form (see also FwrdInh.sv,
-- TransSiteSupply.sv and transExt/Ext.sv).  A body-level propagate of a monoid translation
-- attribute is an equation for it...
nonterminal FBZ;
production fbNil
top::FBZ ::=
{}
production fbAppend
top::FBZ ::= l::FBZ  r::FBZ
{}
monoid translation attribute fbM::FBZ with fbNil(), fbAppend;
nonterminal FBX with fbM;
production fbBase
top::FBX ::=
{
  top.fbM = fbNil();
}
warnCode "Translation attribute flow:fbM is defined in production flow:fbPropagate, which forwards; a forwarding production must take its translation attributes from its forward" {
production fbPropagate
top::FBX ::=
{
  propagate fbM;
  forwards to fbBase();
}
}

-- ...and a dispatch implementation that forwards is a forwarding production like any other.
translation attribute fbT::FBZ;
nonterminal FBD with fbT;
dispatch FBOp = FBD ::= @a::FBD;
noWarnCode "which forwards; a forwarding production must take its translation attributes from its forward" {
production fbImpl implements FBOp
top::FBD ::= @a::FBD
{
  top.fbT = fbNil();
}
}
warnCode "Translation attribute flow:fbT is defined in production flow:fbImplFwd, which forwards; a forwarding production must take its translation attributes from its forward" {
production fbImplFwd implements FBOp
top::FBD ::= @a::FBD
{
  top.fbT = fbNil();
  local p::FBOp = fbImpl;
  forwards to p(a);
}
}
