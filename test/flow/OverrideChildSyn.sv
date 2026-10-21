grammar flow;

-- An override equation on a tree shared into an application of a production is bounded by what the
-- applied production supplies for the same attribute, as this production's graph would have it
-- without its override equations (see overrideBounds).  Where the applied production's equation
-- accesses the tree's synthesized attributes, those depend on what the applied production supplies
-- for the tree's inherited attributes, not on the override.

inherited attribute vqA::String;
inherited attribute vqI::String;
inherited attribute vqM::String;
synthesized attribute vqS::String;
nonterminal VQ with vqA, vqI, vqM, vqS;
flowtype VQ = decorate {}, forward {vqI}, vqS {vqA};

production vqLeaf
top::VQ ::=
{ top.vqS = top.vqA; }

dispatch VQD = VQ ::= @a::VQ;

production vqImpl implements VQD
top::VQ ::= @a::VQ
{
  a.vqA = "";
  top.vqS = "";
}

-- vqSite applies VQD without supplying c.vqA, so an implementation in a grammar not known here may
-- supply it from the forward flow type, {vqI}, and vqSite supplies forward.vqI from c.vqS.  The
-- flow type of vqS is {vqA}, so in vqOverride, a bound that followed t.vqS to the override equation
-- for t.vqA would contain that equation's own dependencies.  The sharing-cycle check reports the
-- path through c.vqS back to c.vqA at vqSite.
warnCode "Potentially missing inherited override equation for flow:vqA on c; a cycle may exist via its sharing decoration site forward[flow:VQD:a]" {
production vqSite
top::VQ ::= c::VQ
{
  forward.vqI = c.vqS;
  local prod::VQD = vqImpl;
  forwards to prod(c);
}
}

warnCode "Inherited override equation for flow:vqA on child t may exceed a flow type with hidden transitive dependencies on flow:vqM; on some reference to this tree, this attribute may be expected to depend only on flow:vqI (from forward[flow:vqSite:c])" {
production vqOverride
top::VQ ::= t::VQ
{
  t.vqA = top.vqM;
  top.vqS = "";
  forwards to vqSite(@t);
}
}

-- The same where the path back to the override goes through the LHS of the production applied
-- around vqInner.
production vqInner
top::VQ ::= c::VQ
{
  c.vqA = top.vqA;
  top.vqS = c.vqS;
}
warnCode "Inherited override equation for flow:vqA on child t may exceed a flow type with hidden transitive dependencies on flow:vqM; on some reference to this tree, this attribute may be expected to depend only on flow:vqI (from forward[flow:vqSite:c][flow:vqInner:c])" {
production vqNested
top::VQ ::= t::VQ
{
  t.vqA = top.vqM;
  top.vqS = "";
  forwards to vqSite(vqInner(@t));
}
}

inherited attribute ozA::String;
inherited attribute ozI::String;
inherited attribute ozM::String;
synthesized attribute ozS1::String;
synthesized attribute ozS2::String;
nonterminal OZ with ozA, ozI, ozM, ozS1, ozS2;
flowtype OZ = decorate {}, forward {}, ozS1 {ozI}, ozS2 {ozA};

production ozLeaf
top::OZ ::=
{
  top.ozS1 = top.ozI;
  top.ozS2 = top.ozA;
}

production ozConst
top::OZ ::=
{
  top.ozS1 = "";
  top.ozS2 = "";
}

-- ozSite's c.ozA comes from d.ozS1, and its d.ozI from c.ozS2: by flow types a cycle through c.ozA,
-- which ozConst at d breaks.  Following d.ozI back to c.ozS2 would reach the override.
production ozSite
top::OZ ::= c::OZ  d::OZ
{
  c.ozA = d.ozS1;
  d.ozI = c.ozS2;
  top.ozS1 = "";
  top.ozS2 = "";
}
warnCode "Inherited override equation for flow:ozA on child t may exceed a flow type with hidden transitive dependencies on flow:ozM; on some reference to this tree, this attribute may be expected to depend on no left-side inherited attributes (from forward[flow:ozSite:c])" {
production ozOverride
top::OZ ::= t::OZ
{
  t.ozA = top.ozM;
  top.ozS1 = "";
  top.ozS2 = "";
  forwards to ozSite(@t, ozConst());
}
}

-- The same through ozLhsSite's own LHS synthesized attribute.
production ozLhsSite
top::OZ ::= c::OZ
{
  c.ozA = top.ozS1;
  top.ozS1 = c.ozS2;
  top.ozS2 = "";
}
warnCode "Inherited override equation for flow:ozA on child t may exceed a flow type with hidden transitive dependencies on flow:ozM; on some reference to this tree, this attribute may be expected to depend on no left-side inherited attributes (from forward[flow:ozLhsSite:c])" {
production ozLhsOverride
top::OZ ::= t::OZ
{
  t.ozA = top.ozM;
  top.ozS1 = "";
  top.ozS2 = "";
  forwards to ozLhsSite(@t);
}
}

inherited attribute ssK::String;
inherited attribute ssG::String;
inherited attribute ssM::String;
synthesized attribute ssD::String;
synthesized attribute ssS::String;
nonterminal SS with ssK, ssG, ssM, ssD, ssS;
flowtype SS = decorate {}, forward {}, ssD {ssG}, ssS {ssK, ssG};

production ssLeaf
top::SS ::=
{
  top.ssD = top.ssG;
  top.ssS = top.ssK;
}

-- ssSite's equation for its child's ssK accesses the child's ssD, which depends on the ssG that
-- ssSite supplies.
production ssSite
top::SS ::= c::SS
{
  c.ssK = top.ssK ++ c.ssD;
  c.ssG = top.ssG;
  top.ssD = c.ssD;
  top.ssS = c.ssS;
}

production ssWrap
top::SS ::= x::SS
{
  x.ssK = top.ssK;
  x.ssG = top.ssG;
  top.ssD = x.ssD;
  top.ssS = x.ssS;
}

-- An override that repeats ssSite's equation depends on ssG through c.ssD, as ssSite's own equation
-- does, also where ssSite is applied inside ssWrap.
noWarnCode "hidden transitive" {
production ssSame
top::SS ::= c::SS
{
  c.ssK = top.ssK ++ c.ssD;
  top.ssD = "";
  top.ssS = "";
  forwards to ssSite(@c);
}
production ssSameNested
top::SS ::= c::SS
{
  c.ssK = top.ssK ++ c.ssD;
  top.ssD = "";
  top.ssS = "";
  forwards to ssWrap(ssSite(@c));
}
}

-- An override that adds ssM to what ssSite's equation depends on is reported, with the bound ssK
-- and ssG.
warnCode "Inherited override equation for flow:ssK on child c may exceed a flow type with hidden transitive dependencies on flow:ssM; on some reference to this tree, this attribute may be expected to depend only on flow:ssG, flow:ssK (from forward[flow:ssSite:c])" {
production ssExceeds
top::SS ::= c::SS
{
  c.ssK = top.ssK ++ c.ssD ++ top.ssM;
  top.ssD = "";
  top.ssS = "";
  forwards to ssSite(@c);
}
}

-- The same where c also has an override for ssG: the bound has the ssG that ssSite supplies, not
-- ssTwo's.
warnCode "Inherited override equation for flow:ssK on child c may exceed a flow type with hidden transitive dependencies on flow:ssM; on some reference to this tree, this attribute may be expected to depend only on flow:ssG, flow:ssK (from forward[flow:ssSite:c])" {
production ssTwo
top::SS ::= c::SS
{
  c.ssK = top.ssK ++ top.ssM;
  c.ssG = top.ssM;
  top.ssD = "";
  top.ssS = "";
  forwards to ssSite(@c);
}
}

-- The same where the local y holds the application: y's equations give ssSite's ssK and ssG, both
-- from ssK here.
warnCode "may exceed a flow type with hidden transitive dependencies on flow:ssM; on some reference to this tree, this attribute may be expected to depend only on flow:ssK (from flow:ssLocalExceeds:local:" {
production ssLocalExceeds
top::SS ::= c::SS
{
  c.ssK = top.ssK ++ c.ssD ++ top.ssM;
  local y::SS = ssSite(@c);
  y.ssK = top.ssK;
  y.ssG = top.ssK;
  top.ssD = "";
  top.ssS = "";
}
}

-- The same where the applied production implements a dispatch signature and c is its child e, at no
-- position of the signature.
dispatch SSD = SS ::= a::SS;
production ssImpl implements SSD
top::SS ::= a::SS  e::SS
{
  e.ssK = top.ssK ++ e.ssD;
  e.ssG = top.ssG;
  top.ssD = e.ssD;
  top.ssS = e.ssS;
}
warnCode "Inherited override equation for flow:ssK on child c may exceed a flow type with hidden transitive dependencies on flow:ssM; on some reference to this tree, this attribute may be expected to depend only on flow:ssG, flow:ssK (from forward[flow:ssImpl:e])" {
production ssByImpl
top::SS ::= c::SS
{
  c.ssK = top.ssK ++ c.ssD ++ top.ssM;
  top.ssD = "";
  top.ssS = "";
  forwards to ssImpl(ssLeaf(), @c);
}
}

-- ssPair supplies c.ssK from d.ssD, and ssOther passes ssFrom(top.ssM) as d, so an override of
-- c.ssK from ssM depends on what ssPair's equation does.
production ssPair
top::SS ::= c::SS  d::SS
{
  c.ssK = d.ssD;
  c.ssG = top.ssG;
  d.ssG = top.ssG;
  top.ssD = c.ssD;
  top.ssS = c.ssS;
}
production ssFrom
top::SS ::= s::String
{
  top.ssD = s;
  top.ssS = s;
}
noWarnCode "hidden transitive" {
production ssOther
top::SS ::= c::SS
{
  c.ssK = top.ssM;
  local y::SS = ssPair(@c, ssFrom(top.ssM));
  y.ssG = top.ssG;
  top.ssD = y.ssD;
  top.ssS = "";
}
}

inherited attribute opA::String;
inherited attribute opK::String;
synthesized attribute opS::String;
nonterminal OP with opA, opK, opS;
flowtype OP = decorate {}, forward {opK}, opS {opA};

production opLeaf
top::OP ::=
{ top.opS = top.opA; }

-- opSite supplies c.opA from its other child n, which opSame gives from opK, so an override from
-- opK is allowed.
production opSite
top::OP ::= c::OP  n::String
{
  c.opA = n;
  top.opS = c.opS;
}
noWarnCode "hidden transitive" {
production opSame
top::OP ::= t::OP
{
  t.opA = top.opK;
  top.opS = "";
  forwards to opSite(@t, top.opK);
}
}

inherited attribute pdS::String;
synthesized attribute puS::String;
nonterminal PD with pdS, puS;
flowtype PD = decorate {}, forward {pdS}, puS {pdS};

production pdLeaf
top::PD ::=
{ top.puS = top.pdS; }

production pdSite
top::PD ::= c::PD
{
  c.pdS = top.pdS;
  top.puS = c.puS;
}

-- What this production supplies to the applied production's LHS is followed with this production's
-- own equations: pdSite supplies c.pdS from its own pdS, which pdOverride supplies from e.puS, and
-- so from the override itself.
noWarnCode "hidden transitive" {
production pdOverride
top::PD ::= e::PD
{
  e.pdS = top.pdS;
  forward.pdS = e.puS;
  top.puS = "";
  forwards to pdSite(@e);
}
}

inherited attribute shK::String;
inherited attribute shM::String;
synthesized attribute shT::String;
nonterminal SH with shK, shM, shT;
flowtype SH = decorate {}, forward {}, shT {shK};

production shLeaf
top::SH ::=
{ top.shT = top.shK; }

-- shDown supplies its child's shK from its own, and shMid shares its child into shDown.
production shDown
top::SH ::= w::SH
{
  w.shK = top.shK;
  top.shT = "";
}
production shMid
top::SH ::= z::SH
{ forwards to shDown(@z); }

-- shPass gets its child through its signature, and shares it into shMid.
production shPass
top::SH ::= @a::SH
{ forwards to shMid(@a); }

-- An equation on a tree passed to a child shared through the signature is the first equation the
-- tree gets, so, as at an applied production's child, it hides shDown's equation and is bounded by
-- what shDown supplies.
warnCode "Inherited override equation for flow:shK on child x may exceed a flow type with hidden transitive dependencies on flow:shM; on some reference to this tree, this attribute may be expected to depend only on flow:shK (from forward[flow:shPass:a])" {
production shSite
top::SH ::= x::SH
{
  x.shK = top.shM;
  forwards to shPass(x);
}
}
noWarnCode "hidden transitive" {
production shSame
top::SH ::= x::SH
{
  x.shK = top.shK;
  forwards to shPass(x);
}
}

-- The same through a production that passes its child on to shPass's.
production shOn
top::SH ::= @a::SH
{ forwards to shPass(a); }
warnCode "Inherited override equation for flow:shK on child x may exceed a flow type with hidden transitive dependencies on flow:shM; on some reference to this tree, this attribute may be expected to depend only on flow:shK (from forward[flow:shOn:a])" {
production shOnSite
top::SH ::= x::SH
{
  x.shK = top.shM;
  forwards to shOn(x);
}
}

-- The same where an implementation of a dispatch signature shares its child into shMid, applied by
-- name or through the signature.
dispatch SHD = SH ::= @a::SH;
production shImpl implements SHD
top::SH ::= @a::SH
{ forwards to shMid(@a); }
warnCode "Inherited override equation for flow:shK on child x may exceed a flow type with hidden transitive dependencies on flow:shM; on some reference to this tree, this attribute may be expected to depend only on flow:shK (from forward[flow:shImpl:a])" {
production shImplSite
top::SH ::= x::SH
{
  x.shK = top.shM;
  forwards to shImpl(x);
}
}
warnCode "Inherited override equation for flow:shK on child x may exceed a flow type with hidden transitive dependencies on flow:shM; on some reference to this tree, this attribute may be expected to depend only on flow:shK (from forward[flow:SHD:a])" {
production shDispatchSite
top::SH ::= x::SH
{
  x.shK = top.shM;
  local prod::SHD = shImpl;
  forwards to prod(x);
}
}
