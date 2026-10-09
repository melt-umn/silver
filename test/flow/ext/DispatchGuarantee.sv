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
  a.gOther = top.gEnv;
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
warnCode "Inherited override equation for flow:gOther on child a has excess dependencies on flow:gOther" {
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
-- An implementation passing its child on may no more depend on what only the tile flow graph allows than an
-- application may (see gSiteN).
warnCode "Inherited override equation for flow:gEnv on child a has excess dependencies on flow:gOther; applications of dispatch flow:GOpN allow it to depend only on flow:gCtx, flow:gEnv" {
production gExtImplN implements GOpN
top::GExpr ::= a::GExpr
{
  a.gEnv = top.gOther;
  local prod::GOpN = gImplN;
  forwards to prod(@a);
}
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

-- An override equation for a host attribute on a tree shared on to a host production is bounded by that production's
-- own equation, even when the tree was decorated before this production got it.  Otherwise an independent extension
-- accessing an attribute on hdWrap's child could need hdAlt.
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

-- What a decoration site supplies itself includes the forward's implicit copies, and what the forward supplies to a
-- local shared on to it.  So none of these overrides hides anything.
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
noWarnCode "hidden transitive" {
production hdSuppliesK
top::HDExpr ::= e::HDExpr
{
  e.hdK = top.hdAlt;
  e.hdEnv = top.hdAlt;
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
