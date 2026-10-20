grammar flow:ext;

-- Only the host language can share a tree as a child shared through a dispatch signature.
warnCode "Orphaned application of flow:GOp" {
production gExtSiteMissing
top::GExpr ::= a::GExpr
{
  a.gEnv = top.gEnv;
  top.gNeedsOther = "";
  local prod::GOp = gImpl;
  forwards to prod(a);
}
}

-- An implementation may rely on what every application in the host language supplies to a child shared through
-- the signature...
noWarnCode "requires missing inherited attribute" {
production gExtImpl implements GOp
top::GExpr ::= @a::GExpr
{
  top.gPP = a.gPP;
  top.gSmall = "";
  top.gNeedsOther = "";
  local ty::String = a.gTy;
  local prod::GOp = gImpl;
  forwards to prod(a);
}
}

-- ...but not on gOther, which those applications do not supply.  Dispatching again does not supply gOther either,
-- since gImpl does not.
warnCode "Access of synthesized attribute gNeedsOther on a requires missing inherited attribute(s) flow:gOther" {
production gExtImplOther implements GOp
top::GExpr ::= @a::GExpr
{
  top.gPP = "";
  top.gSmall = "";
  top.gNeedsOther = "";
  local needsOther::String = a.gNeedsOther;
  local prod::GOp = gImpl;
  forwards to prod(a);
}
}

-- An implementation can supply gOther itself...
noWarnCode "requires missing inherited attribute" {
production gExtImplSupplies implements GOp
top::GExpr ::= @a::GExpr
{
  a.gOther = "";
  top.gPP = "";
  top.gSmall = "";
  top.gNeedsOther = "";
  local needsOther::String = a.gNeedsOther;
  local prod::GOp = gImpl;
  forwards to prod(a);
}
}

-- ...or rely on a host-language production that it shares the child with, as ableC's extensions do
-- through bindBinaryOp and bindExprDecl.
noWarnCode "requires missing inherited attribute" {
production gExtViaHelper implements GOp
top::GExpr ::= @a::GExpr
{
  top.gSmall = "";
  top.gNeedsOther = "";
  local needsOther::String = a.gNeedsOther;
  forwards to gBind(a, gLit());
}
}

-- The attributes of an implementation may depend on what every application in the host language supplies,
-- as only the host language can apply the implementation.
noWarnCode "exceeds flow type" {
production gExtImplSmall implements GOp
top::GExpr ::= @a::GExpr
{
  top.gSmall = a.gSmall;
  top.gPP = "";
  top.gNeedsOther = "";
  local prod::GOp = gImpl;
  forwards to prod(a);
}
}

-- An implementation passing on its own child is not a new application.
noWarnCode "Orphaned application" {
production gExtImplPasses implements GOp
top::GExpr ::= @a::GExpr
{
  top.gSmall = "";
  top.gNeedsOther = "";
  local prod::GOp = gImpl;
  forwards to prod(a);
}
}

-- Every application in the host language supplies gEnv, which overrides an implementation's equation.
warnCode "Duplicate equation for gEnv on a" {
production gExtImplDuplicate implements GOp
top::GExpr ::= @a::GExpr
{
  a.gEnv = top.gEnv;
  top.gSmall = "";
  top.gNeedsOther = "";
  local prod::GOp = gImpl;
  forwards to prod(a);
}
}

-- An implementation's forward may depend on an extra child, which may in turn depend on the signature-shared child.
-- So the forward may depend on what the applications in the host language supply to the signature-shared child.
-- Here the forward depends on gOther, which is outside the forward flow type.
warnCode "Forward equation exceeds flow type with dependencies on flow:gOther" {
production gExtImplFwdViaExtra implements GOpO
top::GExpr ::= @a::GExpr y::GExpr
{
  y.gEnv = a.gNeedsOther;
  y.gCtx = "";
  local prod::GOpO = if y.gSmall == "" then gImplO else gImplO;
  forwards to prod(a);
}
}

-- When an implementation shares its child with its forward, accessing an attribute on the forward can depend on what
-- the forward accesses on that child.  For example, ableC's extensions access forward.typerep through bindBinaryOp.
warnCode "Synthesized equation gPP exceeds flow type with dependencies on flow:gCtx, flow:gEnv" {
production gExtImplUsesFwd implements GOp
top::GExpr ::= @a::GExpr
{
  top.gPP = forward.gTy;
  top.gSmall = "";
  top.gNeedsOther = "";
  forwards to gBind(a, gLit());
}
}

-- An application in an extension does not widen what an implementation's equations for a child may depend on.
-- Here gExtSiteSuppliesOther supplies gOther, but no application in the host language does.
warnCode "Orphaned application of flow:GOp" {
production gExtSiteSuppliesOther
top::GExpr ::= x::GExpr
{
  x.gEnv = top.gEnv;
  x.gCtx = top.gCtx;
  x.gOther = top.gOther;
  top.gNeedsOther = "";
  local prod::GOp = gImpl;
  forwards to prod(x);
}
}
warnCode "Inherited override equation for flow:gOther on child a may exceed a flow type with hidden transitive dependencies on flow:gOther; on some reference to this tree, this attribute may be expected to depend on no left-side inherited attributes (from forward[flow:GOp:a], applications of dispatch flow:GOp); flow:gOther is excluded by what flow:GOp allows" {
production gExtImplOtherFromOther implements GOp
top::GExpr ::= @a::GExpr
{
  a.gOther = top.gOther;
  top.gPP = "";
  top.gSmall = "";
  top.gNeedsOther = "";
  local prod::GOp = gImpl;
  forwards to prod(a);
}
}
-- An implementation passing its child on may depend on gOther, as an application may (see gSiteN).
production gExtImplN implements GOpN
top::GExpr ::= a::GExpr
{
  a.gEnv = top.gOther;
  local prod::GOpN = gImplN;
  forwards to prod(@a);
}

-- When an implementation supplies an inherited attribute to its child itself, accessing the child's attributes does
-- not depend only on that equation.  An implementation earlier in a chain of forwards may supply the attribute
-- first.  The earlier implementation's equation may depend on anything in the forward flow type.
warnCode "Synthesized equation gNeedsOther exceeds flow type with dependencies on flow:gCtx, flow:gEnv" {
production gExtChainReads implements GOp
top::GExpr ::= @a::GExpr
{
  a.gOther = top.gEnv;
  top.gNeedsOther = a.gNeedsOther;
  top.gPP = "";
  top.gSmall = "";
  local prod::GOp = gImpl;
  forwards to prod(a);
}
}

-- An implementation forwarding to one that dispatches again may rely on what is supplied by every implementation
-- that does not dispatch again (gCtx)...
noWarnCode "requires missing inherited attribute" {
production gExtViaAgain implements GOpT
top::GExpr ::= @a::GExpr
{
  top.gTy = "";
  top.gPP = "";
  top.gSmall = "";
  top.gNeedsOther = "";
  local ty::String = a.gTy;
  forwards to gAgain(a, gImplT);
}
}
-- ...but not on gOther, which no application or implementation supplies.
warnCode "Access of synthesized attribute gNeedsOther on a requires missing inherited attribute(s) flow:gOther" {
production gExtViaAgainOther implements GOpT
top::GExpr ::= @a::GExpr
{
  top.gTy = "";
  top.gPP = "";
  top.gSmall = "";
  top.gNeedsOther = "";
  local needsOther::String = a.gNeedsOther;
  forwards to gAgain(a, gImplT);
}
}

-- Matching on a signature-shared child: the attributes of the pattern variables are resolved on that child.
noWarnCode "requires missing inherited attribute" {
production gExtMatchShared implements GOp
top::GExpr ::= @a::GExpr
{
  top.gPP = "";
  top.gSmall = "";
  top.gNeedsOther = "";
  local ty::String = case a of gWith(b) -> b.gTy | _ -> "" end;
  local prod::GOp = gImpl;
  forwards to prod(a);
}
}

-- An extension's aspect of a host-language implementation can rely on what the applications in the host language
-- supply, including through the extension's own aspects.  It cannot rely on applications in the extension.
inherited attribute xInh1::String occurs on XExpr;
inherited attribute xInh2::String occurs on XExpr;
synthesized attribute xSyn1::String occurs on XExpr;
synthesized attribute xSyn2::String occurs on XExpr;
flowtype xSyn1 {xEnv, xInh1} on XExpr;
flowtype xSyn2 {xEnv, xInh2} on XExpr;
aspect default production
top::XExpr ::=
{
  top.xSyn1 = "";
  top.xSyn2 = "";
}
aspect production xSite
top::XExpr ::= x::XExpr
{
  x.xInh1 = "";
}
warnCode "Orphaned application of flow:XOp" {
production xExtSite
top::XExpr ::= x::XExpr
{
  x.xEnv = top.xEnv;
  x.xInh2 = "";
  local prod::XOp = xImpl;
  forwards to prod(x);
}
}
noWarnCode "requires missing inherited attribute" {
aspect production xImpl
top::XExpr ::= @a::XExpr
{
  top.xSyn1 = a.xSyn1;
}
}
warnCode "Access of synthesized attribute xSyn2 on a requires missing inherited attribute(s) flow:ext:xInh2" {
aspect production xImpl
top::XExpr ::= @a::XExpr
{
  top.xSyn2 = a.xSyn2;
}
}

-- An implementation applying another by name with its own child (see DispatchGuarantee.sv).  gExtFirstR decorates the
-- child first, so the gOther that gImplR accesses is gExtFirstR's.
production gExtFirstR implements GOpR
top::GExpr ::= @a::GExpr
{
  a.gOther = top.gCtx;
  top.gPP = "";
  top.gNeedsOther = "";
  local prod::GOpR = gExtThenR;
  forwards to prod(a);
}
warnCode "Synthesized equation gSmall exceeds flow type with dependencies on flow:gCtx" {
production gExtThenR implements GOpR
top::GExpr ::= @a::GExpr
{
  top.gSmall = forward.gTy;
  top.gPP = "";
  top.gNeedsOther = "";
  forwards to gImplR(a);
}
}

-- An implementation that overrides an inherited attribute on its forward keeps the copy.  What was supplied to the
-- child before the next implementation got it introduces dependencies on that implementation's own inherited
-- attributes (see implementedSigStitchPoints), and those are the forward's.  This holds for dispatch signatures without
-- shared children too...
warnCode "Synthesized equation gSmall exceeds flow type with dependencies on flow:gCtx" {
production gExtOverrideU implements GOpU
top::GExpr ::= a::GExpr
{
  forward.gCtx = top.gEnv;
  top.gSmall = forward.gTy;
  local prod::GOpU = gImplU;
  forwards to prod(@a);
}
}
-- ...and where the forward applies by name an implementation that dispatches again.
warnCode "Synthesized equation gSmall exceeds flow type with dependencies on flow:gCtx" {
production gExtOverrideV implements GOpV
top::GExpr ::= a::GExpr
{
  forward.gCtx = top.gEnv;
  top.gSmall = forward.gTy;
  forwards to gAgainV(@a);
}
}

-- An implementation may use its forward parent, which is a tree of the same nonterminal: its attributes depend on
-- their flow types.
warnCode "Synthesized equation gPP exceeds flow type with dependencies on flow:gCtx, flow:gEnv" {
production gExtUsesFwdParent implements GOp
top::GExpr ::= @a::GExpr
{
  top.gPP = forwardParent.gTy;
  top.gSmall = "";
  top.gNeedsOther = "";
  local prod::GOp = gImpl;
  forwards to prod(a);
}
}

-- An override equation for a host attribute on a tree shared with an application of a host
-- production is bounded by that production's own equation, even when the tree was decorated before
-- this production got it.  Otherwise an independent extension accessing an attribute on hdWrap's
-- child could need hdAlt.
warnCode "may exceed a flow type with hidden transitive dependencies on flow:hdAlt" {
production hdReEnv
top::HDExpr ::= @a::HDExpr
{
  a.hdEnv = top.hdAlt;
  top.hdPP = "";
  forwards to hdWrap(@a);
}
}
production hdReEnvSite
top::HDExpr ::= x::HDExpr
{
  top.hdPP = "";
  forwards to hdReEnv(x);
}

-- The same for an override at a sharing site, on the tree it shares through a production's signature.
production hdReEnv2
top::HDExpr ::= @a::HDExpr
{
  top.hdPP = "";
  forwards to hdWrap(@a);
}
warnCode "may exceed a flow type with hidden transitive dependencies on flow:hdAlt" {
production hdReEnvSite2
top::HDExpr ::= x::HDExpr
{
  x.hdEnv = top.hdAlt;
  top.hdPP = "";
  forwards to hdReEnv2(x);
}
}

-- What a decoration site supplies itself includes the forward's implicit copies, which also reach a
-- local shared at the forward.  So none of these overrides hides anything.
noWarnCode "hidden transitive" {
production hdFwdCopy
top::HDExpr ::= a::HDExpr
{
  a.hdEnv = top.hdEnv;
  forwards to @a;
}
production hdFwdCopyShared
top::HDExpr ::= @a::HDExpr
{
  a.hdEnv = top.hdEnv;
  forwards to @a;
}
production hdLocalCopy
top::HDExpr ::= a::HDExpr
{
  a.hdEnv = top.hdEnv;
  local l::HDExpr = @a;
  forwards to @l;
}
}

-- An override on a tree passed along locals to the forward is bounded by the forward flow type: z's
-- own equation never applies, as the tree already has a's...
warnCode "Inherited override equation for flow:hdEnv on child a may exceed a flow type with hidden transitive dependencies on flow:hdAlt" {
production hdChainOverride
top::HDExpr ::= a::HDExpr
{
  a.hdEnv = top.hdAlt;
  local y::HDExpr = @a;
  local z::HDExpr = @y;
  z.hdEnv = top.hdEnv;
  forwards to @z;
}
}
-- ...also where a was passed to this production already decorated...
warnCode "Inherited override equation for flow:hdEnv on child a may exceed a flow type with hidden transitive dependencies on flow:hdAlt" {
production hdChainOverrideShared
top::HDExpr ::= @a::HDExpr
{
  a.hdEnv = top.hdAlt;
  local y::HDExpr = @a;
  local z::HDExpr = @y;
  z.hdEnv = top.hdEnv;
  forwards to @z;
}
}
-- ...while supplying it as z does hides nothing.
noWarnCode "hidden transitive" {
production hdChainCopy
top::HDExpr ::= a::HDExpr
{
  a.hdEnv = top.hdEnv;
  local y::HDExpr = @a;
  local z::HDExpr = @y;
  z.hdEnv = top.hdEnv;
  forwards to @z;
}
}

-- An equation of the applied production may access another inherited attribute of the tree, which this production
-- supplies: hdReadsK's c.hdEnv is e.hdK, which is top.hdAlt here.
inherited attribute hdK::String occurs on HDExpr;
production hdReadsK
top::HDExpr ::= c::HDExpr
{
  c.hdK = top.hdK;
  c.hdEnv = c.hdK;
  c.hdAlt = top.hdAlt;
  forwards to hdVar("");
}
-- Nothing projects what an application by name supplies onto hdReadsK's child, so an aspect of
-- hdReadsK in a grammar importing this one sees c.hdK depend only on hdReadsK's own hdK: an
-- override supplying it from hdAlt hides a dependency (see pathBound)...
warnCode "Inherited override equation for flow:ext:hdK on child e may exceed a flow type with hidden transitive dependencies on flow:hdAlt" {
production hdSuppliesKAlt
top::HDExpr ::= e::HDExpr
{
  e.hdK = top.hdAlt;
  e.hdEnv = top.hdAlt;
  forwards to hdReadsK(@e);
}
}
-- ...while supplying it as hdReadsK does hides nothing, and neither does hdEnv, which hdReadsK
-- takes from e.hdK.
noWarnCode "hidden transitive" {
production hdSuppliesK
top::HDExpr ::= e::HDExpr
{
  e.hdK = top.hdK;
  e.hdEnv = top.hdK;
  forwards to hdReadsK(@e);
}
}

-- An extension attribute's equation accessing qShareOnChain's child also sees what qChainSite supplied, through
-- qChainSrc's local.
synthesized attribute qExt::String occurs on QE;
flowtype qExt {qEnv} on QE;
aspect default production
top::QE ::=
{
  top.qExt = "";
}
warnCode "Synthesized equation qExt exceeds flow type with dependencies on flow:qOther" {
aspect production qShareOnChain
top::QE ::= @a::QE
{
  top.qExt = a.qOther;
}
}

-- An extension's contribution to gColl on the tree that gSiteK shares through a dispatch signature.
-- An implementation in an extension that does not import this grammar sees what gSiteK supplies
-- without the contribution: gColl depending only on gCtx.  So the contribution may not add gEnv...
warnCode "Inherited contribution equation for flow:gColl on child x has excess dependencies on flow:gEnv; a contribution from a grammar not exported by those of the attribute's other equations may depend only on flow:gCtx" {
aspect production gSiteK
top::GExpr ::= x::GExpr
{
  x.gColl <- [top.gEnv];
}
}
-- ...but may depend on what the base equation depends on.
noWarnCode "Inherited contribution equation" {
aspect production gSiteK
top::GExpr ::= x::GExpr
{
  x.gColl <- [top.gCtx];
}
}
-- The same for a host implementation's own signature child (see gImplL).  Every grammar allows for
-- an implementation it does not import supplying gColl from the forward flow type, so gEnv is not
-- excess here, but gOther is.
warnCode "Inherited contribution equation for flow:gColl on child a has excess dependencies on flow:gOther; a contribution from a grammar not exported by those of the attribute's other equations may depend only on flow:gCtx, flow:gEnv" {
aspect production gImplL
top::GExpr ::= @a::GExpr
{
  a.gColl <- [top.gOther];
}
}
noWarnCode "Inherited contribution equation" {
aspect production gImplL
top::GExpr ::= @a::GExpr
{
  a.gColl <- [top.gEnv];
}
}
-- The bound does not need dispatch: an aspect of gCollWrap in another extension, accessing an
-- attribute of x or l that depends on gColl, sees it depend only on gCtx.
warnCode "Inherited contribution equation for flow:gColl on child x has excess dependencies on flow:gEnv; a contribution from a grammar not exported by those of the attribute's other equations may depend only on flow:gCtx" {
aspect production gCollWrap
top::GExpr ::= x::GExpr
{
  x.gColl <- [top.gEnv];
}
}
warnCode "Inherited contribution equation for flow:gColl on local flow:gCollWrap:local:flow:l has excess dependencies on flow:gEnv; a contribution from a grammar not exported by those of the attribute's other equations may depend only on flow:gCtx" {
aspect production gCollWrap
top::GExpr ::= x::GExpr
{
  l.gColl <- [top.gEnv];
}
}

-- gImplFP supplies a.gOther from its forward parent's gNeedsOther.  The forward parent may be an
-- implementation in another extension, whose gNeedsOther may depend on anything in its flow type,
-- gOther; so may a.gOther.
warnCode "Synthesized equation gTy exceeds flow type with dependencies on flow:gOther" {
production gExtImplFP implements GOpFP
top::GExpr ::= @a::GExpr
{
  top.gTy = a.gNeedsOther;
  top.gPP = "";
  top.gSmall = "";
  top.gNeedsOther = "";
  local prod::GOpFP = gImplFP;
  forwards to prod(a);
}
}
-- gImplSB supplies a.gOther from b.gNeedsOther, which needs b.gOther, which gImplSB supplies from
-- top.gOther.
warnCode "Synthesized equation gTy exceeds flow type with dependencies on flow:gOther" {
production gExtImplSB implements GOpSB
top::GExpr ::= @a::GExpr b::GExpr
{
  top.gTy = a.gNeedsOther;
  top.gPP = "";
  top.gSmall = "";
  top.gNeedsOther = "";
  local prod::GOpSB = gImplSB;
  forwards to prod(a, @b);
}
}

-- The forward parent of gImplFP may be this implementation, whose gNeedsOther depends on gOther
-- (see gSiteFPAccesses).
production gExtImplFPOther implements GOpFP
top::GExpr ::= @a::GExpr
{
  top.gNeedsOther = top.gOther;
  top.gTy = "";
  top.gPP = "";
  top.gSmall = "";
  local prod::GOpFP = gImplFP;
  forwards to prod(a);
}

-- gSiteHNone leaves gOther to implementations and sees what they supply only through the forward
-- flow type, so an implementation may not supply it from gOther, although gSiteHOther does: the
-- bound is what GOpH's normal graph and its tile flow graph both allow (see
-- dispatchChildAllowedInhs).
warnCode "Inherited override equation for flow:gOther on child a may exceed a flow type with hidden transitive dependencies on flow:gOther; on some reference to this tree, this attribute may be expected to depend only on flow:gCtx, flow:gEnv (from forward[flow:GOpH:a], applications of dispatch flow:GOpH); flow:gOther is excluded by what flow:GOpH allows" {
production gExtImplHOther implements GOpH
top::GExpr ::= @a::GExpr
{
  a.gOther = top.gOther;
  top.gPP = "";
  top.gSmall = "";
  top.gNeedsOther = "";
  local prod::GOpH = gImplH;
  forwards to prod(a);
}
}

-- A tree shared through a local keeps the equations of the tree it was shared from, which this
-- production decorated first, and the local's vertex reaches them (see addDecSiteTreeEqs): x.gEnv
-- is checked through y.
warnCode "Inherited override equation for flow:gEnv on child x may exceed a flow type with hidden transitive dependencies on flow:gOther; on some reference to this tree, this attribute may be expected to depend only on flow:gCtx, flow:gEnv" {
production gExtChainLocal
top::GExpr ::= x::GExpr
{
  x.gEnv = top.gOther;
  x.gCtx = top.gCtx;
  local y::GExpr = @x;
  top.gNeedsOther = "";
  local prod::GOpU = gImplU;
  forwards to prod(@y);
}
}
-- When x, at the root of the chain, and y both supply gEnv, x's equation applies: y's own equation
-- never does, so the paths go on past y, to what GOpU allows.
warnCode "Inherited override equation for flow:gEnv on child x may exceed a flow type with hidden transitive dependencies on flow:gOther; on some reference to this tree, this attribute may be expected to depend only on flow:gCtx, flow:gEnv (from local flow:ext:gExtChainRoot:local:" {
production gExtChainRoot
top::GExpr ::= x::GExpr
{
  x.gEnv = top.gOther;
  local y::GExpr = @x;
  y.gEnv = top.gEnv;
  y.gCtx = top.gCtx;
  top.gNeedsOther = "";
  local prod::GOpU = gImplU;
  forwards to prod(@y);
}
}
-- A tree shared into a local only in one branch does not override the local's own equation, which
-- applies when the tree is not shared: y's vertex reaches both x.gEnv and y.gEnv.
warnCode "may exceed a flow type with hidden transitive dependencies on flow:gOther; on some reference to this tree, this attribute may be expected to depend only on flow:gCtx, flow:gEnv (from forward[flow:GOpU:a]); flow:gOther is excluded by what flow:GOpU allows" {
production gExtChainCond
top::GExpr ::= x::GExpr
{
  x.gEnv = top.gEnv;
  x.gCtx = top.gCtx;
  local y::GExpr = if top.gEnv == "" then @x else gLit();
  y.gEnv = top.gOther;
  y.gCtx = top.gCtx;
  top.gNeedsOther = "";
  local prod::GOpU = gImplU;
  forwards to prod(@y);
}
}
-- Shared into r in one branch only, x's equations do not override those of v, which r is shared
-- into: where r is a new tree, v's own equation applies.
warnCode "may exceed a flow type with hidden transitive dependencies on flow:gOther; on some reference to this tree, this attribute may be expected to depend only on flow:gCtx, flow:gEnv (from forward[flow:GOpU:a]); flow:gOther is excluded by what flow:GOpU allows" {
production gExtChainCondMid
top::GExpr ::= x::GExpr
{
  x.gEnv = top.gEnv;
  x.gCtx = top.gCtx;
  local r::GExpr = if top.gEnv == "" then @x else gLit();
  local v::GExpr = @r;
  v.gEnv = top.gOther;
  v.gCtx = top.gCtx;
  top.gNeedsOther = "";
  local prod::GOpU = gImplU;
  forwards to prod(@v);
}
}

-- An implementation accessing a.gNeedsOther sees gChainSiteQ's x.gOther = top.gOther through the
-- local y it shares: the projection from y reaches x's equation.  Projecting only y's (absent)
-- equations would leave the unknown-implementation edge, giving gEnv, gCtx instead.
warnCode "Synthesized equation gSmall exceeds flow type with dependencies on flow:gOther" {
production gExtChainReadsQ implements GOpQ
top::GExpr ::= @a::GExpr
{
  top.gSmall = a.gNeedsOther;
  top.gPP = "";
  top.gNeedsOther = "";
  local prod::GOpQ = gImplQ;
  forwards to prod(a);
}
}
-- The same through two locals (see gChainSiteQ2).
warnCode "Synthesized equation gSmall exceeds flow type with dependencies on flow:gOther" {
production gExtChainReadsQ2 implements GOpQ2
top::GExpr ::= @a::GExpr
{
  top.gSmall = a.gNeedsOther;
  top.gPP = "";
  top.gNeedsOther = "";
  local prod::GOpQ2 = gImplQ2;
  forwards to prod(a);
}
}
-- Through a conditional link, both x's equation and y's may apply (see gChainSiteQ3).
warnCode "Synthesized equation gSmall exceeds flow type with dependencies on flow:gCtx, flow:gOther" {
production gExtChainReadsQ3 implements GOpQ3
top::GExpr ::= @a::GExpr
{
  top.gSmall = a.gNeedsOther;
  top.gPP = "";
  top.gNeedsOther = "";
  local prod::GOpQ3 = gImplQ3;
  forwards to prod(a);
}
}

-- An extension attribute's equation accessing cpReads's child sees what cpPass supplies to it:
-- a.cpK from cpPass's cpOther, which is outside cpExt's flow type.
synthesized attribute cpExt::String occurs on CP;
flowtype cpExt {cpEnv} on CP;
aspect default production
top::CP ::=
{
  top.cpExt = "";
}
warnCode "Synthesized equation cpExt exceeds flow type with dependencies on flow:cpOther" {
aspect production cpReads
top::CP ::= @a::CP
{
  top.cpExt = a.cpK;
}
}

-- An implementation's own equation for a.gOther hides gWith's, which gBind's forward applies, and
-- gWith's consumers assume that a.gOther depends on nothing (see gBind).
warnCode "Inherited override equation for flow:gOther on child a may exceed a flow type with hidden transitive dependencies on flow:gEnv; on some reference to this tree, this attribute may be expected to depend on no left-side inherited attributes" {
production gExtImplSuppliesEnv implements GOp
top::GExpr ::= @a::GExpr
{
  a.gOther = top.gEnv;
  top.gPP = "";
  top.gSmall = "";
  top.gNeedsOther = "";
  forwards to gBind(a, gLit());
}
}

-- An extension implementation's own equation for its child is bounded by what applications of its
-- dispatch signature allow, whichever path the child takes next (see implCapInhs), as well as by
-- the paths from where the child is shared.
warnCode "Inherited override equation for flow:gOther on child a may exceed a flow type with hidden transitive dependencies on flow:gOther; on some reference to this tree, this attribute may be expected to depend only on flow:gCtx, flow:gEnv" {
production gExtImplUOther implements GOpU
top::GExpr ::= a::GExpr
{
  a.gOther = top.gOther;
  local prod::GOpU = gImplU;
  forwards to prod(@a);
}
}
-- Where the child is shared nowhere, only that bound applies (and the implementation is orphaned).
warnCode "Inherited equation for flow:gOther on child a has excess dependencies on flow:gOther; applications of dispatch flow:GOpU allow it to depend only on flow:gCtx, flow:gEnv" {
production gExtImplUNoFwd implements GOpU
top::GExpr ::= a::GExpr
{
  a.gOther = top.gOther;
  forwards to gLit();
}
}
-- An equation on a translation attribute of the child is reported once, as an equation on the
-- child.
warnCode "Inherited equation for flow:reTr.flow:rtK on child a has excess dependencies on flow:reM; applications of dispatch flow:ROpT allow it to depend on no left-side inherited attributes" {
production gExtImplTrans implements ROpT
top::RE ::= a::RE
{
  a.reTr.rtK = top.reM;
  forwards to rImplT(^a);
}
}

-- An implementation must pass its child on to an application of its dispatch signature in a match's
-- else branch too.
warnCode "Orphaned implementation production gExtImplElse for dispatch flow:GOp" {
production gExtImplElse implements GOp
top::GExpr ::= @a::GExpr
{
  top.gSmall = "";
  top.gNeedsOther = "";
  local prod::GOp = gImpl;
  local other::GOpU = gImplU;
  forwards to match a return GExpr with gWith(b) -> prod(a) else -> other(@a) end;
}
}
