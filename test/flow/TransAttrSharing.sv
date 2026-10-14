grammar flow;

-- Translation attributes of shared trees, and of a translation taken from the forward.
-- A shared tree's translations are its decoration site's translations, and a production that
-- does not define a translation attribute has its forward's; the synthesized attributes of these
-- translations, at any depth, must depend on what the site or the production supplies to them.

inherited attribute tsJ::Integer;
synthesized attribute tsS::Integer;
inherited attribute tsK::Integer;
synthesized attribute tsOut::Integer;

nonterminal TSM with tsJ, tsS;
production tsM
top::TSM ::=
{ top.tsS = top.tsJ; }

translation attribute tsT::TSM;
nonterminal TSN with tsT;
production tsN
top::TSN ::=
{ top.tsT = tsM(); }

nonterminal TSP with tsK, tsOut;
flowtype tsOut {} on TSP;

-- Accessing a synthesized attribute of the site's translation reaches the shared tree's translation.
warnCode "Synthesized equation tsOut exceeds flow type with dependencies on flow:tsK" {
production tsShareSite
top::TSP ::= x::TSN
{
  local y::TSN = @x;
  y.tsT.tsJ = top.tsK;
  top.tsOut = y.tsT.tsS;
}
}

-- The same through a chain of translations shared into locals: z is y's translation, and y is x's.
nonterminal TSL with tsJ, tsS;
production tsL
top::TSL ::=
{ top.tsS = top.tsJ; }

translation attribute tsU::TSL;
nonterminal TSM2 with tsU;
production tsM2
top::TSM2 ::=
{ top.tsU = tsL(); }

translation attribute tsT2::TSM2;
nonterminal TSN2 with tsT2;
production tsN2
top::TSN2 ::=
{ top.tsT2 = tsM2(); }

warnCode "Synthesized equation tsOut exceeds flow type with dependencies on flow:tsK" {
production tsShareChain
top::TSP ::= x::TSN2
{
  local y::TSM2 = @x.tsT2;
  local z::TSL = @y.tsU;
  z.tsJ = top.tsK;
  top.tsOut = z.tsS;
}
}

-- A forwarding production that does not define tsT has its forward's translation.
synthesized attribute tsOutF::Integer;
nonterminal TSF with tsT, tsOutF;
flowtype tsOutF {} on TSF;
production tsFBase
top::TSF ::=
{
  top.tsT = tsM();
  top.tsOutF = 0;
}

warnCode "Synthesized equation tsOutF exceeds flow type with dependencies on flow:tsT.flow:tsJ" {
production tsFFwd
top::TSF ::=
{
  top.tsOutF = top.tsT.tsS;
  forwards to tsFBase();
}
}

-- A translation of a translation of a shared tree, two levels down on the sharing edge from y to x;
-- the inherited attribute is supplied by a local sharing y's translation.
warnCode "Synthesized equation tsOut exceeds flow type with dependencies on flow:tsK" {
production tsShareSiteDeep
top::TSP ::= x::TSN2
{
  local y::TSN2 = @x;
  local w::TSM2 = @y.tsT2;
  w.tsU.tsJ = top.tsK;
  top.tsOut = y.tsT2.tsU.tsS;
}
}

-- Sharing only translations of a tree, and not the tree itself, is fine.
production tsShareChainOk
top::TSP ::= x::TSN2
{
  local y::TSM2 = @x.tsT2;
  local z::TSL = @y.tsU;
  z.tsJ = 0;
  top.tsOut = z.tsS;
}

-- A translation of the forward's translation, taken from the forward.
synthesized attribute tsOutF2::Integer;
nonterminal TSF2 with tsT2, tsOutF2;
flowtype tsOutF2 {} on TSF2;
production tsF2Base
top::TSF2 ::=
{
  top.tsT2 = tsM2();
  top.tsOutF2 = 0;
}

warnCode "Synthesized equation tsOutF2 exceeds flow type with dependencies on flow:tsT2.flow:tsU.flow:tsJ" {
production tsF2Fwd
top::TSF2 ::=
{
  top.tsOutF2 = top.tsT2.tsU.tsS;
  forwards to tsF2Base();
}
}

-- The root of a translation taken from the forward is the forward's translation, whose construction
-- here depends on tsK.
nonterminal TSC with tsS;
production tsC
top::TSC ::=
{ top.tsS = 0; }

translation attribute tsTC::TSC;
synthesized attribute tsOutG::Integer;
nonterminal TSG with tsK, tsTC, tsOutG;
flowtype tsOutG {} on TSG;
production tsGBase
top::TSG ::=
{
  top.tsTC = if top.tsK > 0 then tsC() else tsC();
  top.tsOutG = 0;
}

warnCode "Synthesized equation tsOutG exceeds flow type with dependencies on flow:tsK" {
production tsGFwd
top::TSG ::=
{
  top.tsOutG = top.tsTC.tsS;
  forwards to tsGBase();
}
}

-- A polymorphic translation attribute, whose translation's type is given at its occurrence.
translation attribute tsP<a>::a;
nonterminal TSQ;
attribute tsP<TSM> occurs on TSQ;
production tsQ
top::TSQ ::=
{ top.tsP = tsM(); }

warnCode "Synthesized equation tsOut exceeds flow type with dependencies on flow:tsK" {
production tsSharePoly
top::TSP ::= x::TSQ
{
  local y::TSQ = @x;
  y.tsP.tsJ = top.tsK;
  top.tsOut = y.tsP.tsS;
}
}

-- Sharing a translation of a tree that is itself shared would decorate the translation twice,
-- at any depth, and also when the translation is shared through a production's signature.
wrongFlowCode "Cannot share x.flow:tsT in production flow:tsShareBoth, because child x is also shared" {
production tsShareBoth
top::TSP ::= x::TSN
{
  local y::TSN = @x;
  local z::TSM = @x.tsT;
}
}

wrongFlowCode "Cannot share x.flow:tsT2.flow:tsU in production flow:tsDoubleShare, because child x is also shared" {
production tsDoubleShare
top::TSP ::= x::TSN2
{
  local y::TSN2 = @x;
  local z::TSL = @x.tsT2.tsU;
}
}

production tsMShare
top::TSM ::= @e::TSM
{
  e.tsJ = top.tsJ;
  top.tsS = e.tsS;
}

wrongFlowCode "Cannot share x.flow:tsT in production flow:tsSigDoubleShare, because child x is also shared" {
production tsSigDoubleShare
top::TSM ::= x::TSN
{
  local y::TSN = @x;
  forwards to tsMShare(x.tsT);
}
}

-- Sharing a translation of a translation from the production's grammar is not orphaned
-- (see NestedTransSharing.sv.)
noWarnCode "Orphaned sharing" {
production tsShareNested
top::TSP ::= x::TSN2
{
  local z::TSL = @x.tsT2.tsU;
  z.tsJ = 0;
  top.tsOut = z.tsS;
}
}

-- A translation taken from a default equation is built elsewhere: its root depends on how the
-- default builds it, and its attributes on what is supplied to the translation.
translation attribute tsTD::TSM;
synthesized attribute tsOutD::Integer;
nonterminal TSD with tsK, tsTD, tsOutD;
flowtype tsOutD {} on TSD;
aspect default production
top::TSD ::=
{
  top.tsTD = if top.tsK > 0 then tsM() else tsM();
}

warnCode "Synthesized equation tsOutD exceeds flow type with dependencies on flow:tsK, flow:tsTD.flow:tsJ" {
production tsDUse
top::TSD ::=
{
  top.tsOutD = top.tsTD.tsS;
}
}

-- The same for a translation of a translation from a default equation.
translation attribute tsTD2::TSM2;
synthesized attribute tsOutD2::Integer;
nonterminal TSD2 with tsTD2, tsOutD2;
flowtype tsOutD2 {} on TSD2;
aspect default production
top::TSD2 ::=
{
  top.tsTD2 = tsM2();
}

warnCode "Synthesized equation tsOutD2 exceeds flow type with dependencies on flow:tsTD2.flow:tsU.flow:tsJ" {
production tsDUse2
top::TSD2 ::=
{
  top.tsOutD2 = top.tsTD2.tsU.tsS;
}
}
