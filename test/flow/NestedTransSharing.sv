grammar flow;

-- Sharing a translation attribute of a translation attribute (at any depth) of a child or local.
-- Only the decoration site supplies ntsJ to the shared tree. It can be shared by the production's grammar,
-- or by a grammar declaring an occurrence on the chain (see flow:transShare), and accesses on the shared
-- tree, and on the trees that it is a translation attribute of, find that decoration site.

inherited attribute ntsJ::Integer;
synthesized attribute ntsS::Integer;

nonterminal NtsZ with ntsJ, ntsS;
production ntsZ
top::NtsZ ::=
{ top.ntsS = top.ntsJ; }

translation attribute ntsA::NtsZ;
nonterminal NtsY with ntsA, ntsS;
production ntsY
top::NtsY ::=
{
  top.ntsA = ntsZ();
  top.ntsS = top.ntsA.ntsS;
}

translation attribute ntsB::NtsY;
nonterminal NtsX with ntsB, ntsS;
production ntsX
top::NtsX ::=
{
  top.ntsB = ntsY();
  top.ntsS = top.ntsB.ntsS;
}

translation attribute ntsC::NtsX;
nonterminal NtsV with ntsC, ntsS;
production ntsV
top::NtsV ::=
{
  top.ntsC = ntsX();
  top.ntsS = top.ntsC.ntsS;
}

synthesized attribute ntsOut::Integer;
nonterminal NtsP with ntsOut;

-- The decoration sites.
nonterminal NtsW with ntsS;
production ntsW
top::NtsW ::= c::NtsZ
{
  c.ntsJ = 0;
  top.ntsS = c.ntsS;
}
production ntsSite
top::NtsP ::= c::NtsZ
{
  c.ntsJ = 0;
  top.ntsOut = c.ntsS;
}
production ntsSigSite
top::NtsP ::= @c::NtsZ
{
  c.ntsJ = 0;
  top.ntsOut = c.ntsS;
}

-- Sharing from the production's grammar, at a local, the forward or through the forward's signature,
-- of a child or a local, at any depth.
noWarnCode "Orphaned sharing" {
production ntsShareNested
top::NtsP ::= x::NtsX
{
  local site::NtsW = ntsW(@x.ntsB.ntsA);
  top.ntsOut = site.ntsS;
}
}

noWarnCode "Orphaned sharing" {
production ntsShareNestedFwd
top::NtsP ::= x::NtsX
{
  forwards to ntsSite(@x.ntsB.ntsA);
}
}

noWarnCode "Orphaned sharing" {
production ntsShareNestedSig
top::NtsP ::= x::NtsX
{
  forwards to ntsSigSite(x.ntsB.ntsA);
}
}

noWarnCode "Orphaned sharing" {
production ntsShareNestedLocal
top::NtsP ::=
{
  local l::NtsX = ntsX();
  local site::NtsW = ntsW(@l.ntsB.ntsA);
  top.ntsOut = site.ntsS;
}
}

noWarnCode "Orphaned sharing" {
production ntsShareTriple
top::NtsP ::= v::NtsV
{
  local site::NtsW = ntsW(@v.ntsC.ntsB.ntsA);
  top.ntsOut = site.ntsS;
}
}

-- ntsS on the trees that the shared tree is a translation attribute of depends on ntsJ on the shared tree,
-- e.g. ntsS on x on ntsB.ntsA.ntsJ.
noWarnCode "requires missing inherited attribute" {
production ntsAccessNested
top::NtsP ::= x::NtsX
{
  local site::NtsW = ntsW(@x.ntsB.ntsA);
  top.ntsOut = site.ntsS + x.ntsB.ntsA.ntsS + x.ntsB.ntsS + x.ntsS;
}
}

noWarnCode "requires missing inherited attribute" {
production ntsAccessTriple
top::NtsP ::= v::NtsV
{
  local site::NtsW = ntsW(@v.ntsC.ntsB.ntsA);
  top.ntsOut = site.ntsS + v.ntsC.ntsB.ntsA.ntsS + v.ntsC.ntsB.ntsS + v.ntsC.ntsS + v.ntsS;
}
}

noWarnCode "requires missing inherited attribute" {
production ntsAccessSig
top::NtsP ::= x::NtsX
{
  top.ntsOut = x.ntsB.ntsA.ntsS + x.ntsB.ntsS + x.ntsS;
  forwards to ntsSigSite(x.ntsB.ntsA);
}
}

-- The same tree, where the tree that it is a translation attribute of is shared, and it is shared from there.
-- x.ntsB is yb, so x.ntsB.ntsA is yb.ntsA.
noWarnCode "requires missing inherited attribute" {
production ntsAccessChain
top::NtsP ::= x::NtsX
{
  local yb::NtsY = @x.ntsB;
  local site::NtsW = ntsW(@yb.ntsA);
  top.ntsOut = site.ntsS + x.ntsB.ntsA.ntsS + x.ntsB.ntsS + x.ntsS;
}
}

-- x is y, so x.ntsB.ntsA is y.ntsB.ntsA.
noWarnCode "requires missing inherited attribute" {
production ntsAccessRootShared
top::NtsP ::= x::NtsX
{
  local y::NtsX = @x;
  local site::NtsW = ntsW(@y.ntsB.ntsA);
  top.ntsOut = site.ntsS + x.ntsB.ntsA.ntsS + x.ntsB.ntsS + x.ntsS;
}
}

-- The same for a single translation attribute: x is y, so x.ntsA is y.ntsA.
noWarnCode "requires missing inherited attribute" {
production ntsAccessRootSharedSingle
top::NtsP ::= x::NtsY
{
  local y::NtsY = @x;
  local site::NtsW = ntsW(@y.ntsA);
  top.ntsOut = site.ntsS + x.ntsA.ntsS + x.ntsS;
}
}

-- Without a decoration site, ntsJ is still missing.
warnCode "Access of synthesized attribute ntsS on x.ntsB requires missing inherited attribute(s) flow:ntsA.flow:ntsJ" {
production ntsAccessUnshared
top::NtsP ::= x::NtsX
{
  top.ntsOut = x.ntsB.ntsS;
}
}

warnCode "Access of synthesized attribute ntsS on x requires missing inherited attribute(s) flow:ntsB.flow:ntsA.flow:ntsJ" {
production ntsAccessUnsharedRoot
top::NtsP ::= x::NtsX
{
  top.ntsOut = x.ntsS;
}
}

-- Host productions that extensions share translation attributes of translation attributes of x in.
production ntsExtHost
top::NtsP ::= x::NtsX
{ top.ntsOut = 0; }
production ntsExtHostInner
top::NtsP ::= x::NtsX
{ top.ntsOut = 0; }
production ntsOrphanHost
top::NtsP ::= x::NtsX
{ top.ntsOut = 0; }
production ntsOrphanExtHost
top::NtsP ::= x::NtsX
{ top.ntsOut = 0; }
