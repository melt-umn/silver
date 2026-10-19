grammar flow;

-- Applications of a dispatch signature and its implementations in independent extensions
-- (see ext/DispatchGuarantee.sv)

inherited attribute gEnv::String;
inherited attribute gCtx::String;
inherited attribute gOther::String;
synthesized attribute gTy::String;
synthesized attribute gPP::String;
synthesized attribute gSmall::String;
synthesized attribute gNeedsOther::String;
nonterminal GExpr with gEnv, gCtx, gOther, gTy, gPP, gSmall, gNeedsOther;
flowtype GExpr = decorate {gEnv, gCtx}, forward {gEnv, gCtx},
  gTy {gEnv, gCtx}, gPP {}, gSmall {gEnv}, gNeedsOther {gOther};

production gLit
top::GExpr ::=
{
  top.gTy = top.gEnv ++ top.gCtx;
  top.gPP = "lit";
  top.gSmall = top.gEnv;
  top.gNeedsOther = top.gOther;
}

-- Supplies everything to a tree shared into it, as ableC's bindExprDecl does
production gWith
top::GExpr ::= a::GExpr
{
  a.gEnv = top.gEnv;
  a.gCtx = top.gCtx;
  a.gOther = "x";
  top.gTy = a.gTy;
  top.gPP = a.gPP;
  top.gSmall = a.gSmall;
  top.gNeedsOther = a.gNeedsOther;
}

dispatch GOp = GExpr ::= @a::GExpr;
production gImpl implements GOp
top::GExpr ::= @a::GExpr
{
  top.gTy = a.gTy;
  top.gPP = a.gPP;
  top.gSmall = a.gSmall;
  top.gNeedsOther = "";
}
-- An implementation with an extra child, as ableC's bindBinaryOp.
-- An implementation in an extension can supply a.gOther itself and then forward to gBind.  The extension's equation
-- for a.gOther may depend on anything in the forward flow type, and overrides gWith's.
warnCode "the implicit copy equation for flow:gNeedsOther (due to forwarding) would exceed the attribute's flow type with dependencies on flow:gCtx, flow:gEnv" {
production gBind implements GOp
top::GExpr ::= @a::GExpr r::GExpr
{
  r.gEnv = top.gEnv;
  r.gCtx = top.gCtx;
  r.gOther = top.gOther;
  top.gPP = r.gPP;
  forwards to gWith(@a);
}
}

-- The only application in the host language, which supplies gEnv and gCtx to the child it shares.
-- Copying gPP from an implementation not known here needs nothing, as gPP's flow type is empty.
noWarnCode "would exceed" {
production gSite
top::GExpr ::= a::GExpr
{
  a.gEnv = top.gEnv;
  a.gCtx = top.gCtx;
  top.gNeedsOther = "";
  local prod::GOp = gImpl;
  forwards to prod(a);
}
}
-- A forward production attribute already has this production as its forward parent, so the applied production's
-- equations for a child shared through its signature would never apply to it.
warnCode "Forward production attribute flow:gFwdAttrSigShared:local:flow:fp cannot be shared as child a of flow:GOp" {
production gFwdAttrSigShared
top::GExpr ::=
{
  forward production attribute fp = gLit();
  top.gNeedsOther = "";
  local prod::GOp = gImpl;
  forwards to prod(fp);
}
}
production gShareByName
top::GExpr ::= @a::GExpr
{
  top.gTy = "";
  top.gPP = "";
  top.gSmall = "";
  top.gNeedsOther = "";
}
warnCode "Forward production attribute flow:gFwdAttrByName:local:flow:fp cannot be shared as child a of flow:gShareByName" {
production gFwdAttrByName
top::GExpr ::=
{
  forward production attribute fp = gLit();
  top.gNeedsOther = "";
  forwards to gShareByName(fp);
}
}

-- A production may pass a tree with '@' to a child that a dispatch signature does not share.  The production's
-- equations for the tree may then depend only on what an implementation's equations for that child may depend on.
-- Here, that is the forward flow type or the same attribute.
dispatch GOpU = GExpr ::= a::GExpr;
production gImplU implements GOpU
top::GExpr ::= a::GExpr
{
  a.gEnv = top.gEnv;
  a.gCtx = top.gCtx;
  top.gTy = "";
  top.gPP = "";
  top.gSmall = "";
  top.gNeedsOther = "";
}
warnCode "which is shared with an application of flow:GOpU, depends on flow:gOther" {
production gSiteOther
top::GExpr ::= a::GExpr
{
  a.gEnv = top.gOther;
  a.gCtx = top.gCtx;
  top.gTy = "";
  top.gPP = "";
  top.gSmall = "";
  top.gNeedsOther = "";
  local prod::GOpU = gImplU;
  forwards to prod(@a);
}
}
noWarnCode "which is shared with an application of" {
production gSiteCrossed
top::GExpr ::= a::GExpr
{
  a.gEnv = top.gCtx;
  a.gCtx = top.gEnv;
  top.gTy = "";
  top.gPP = "";
  top.gSmall = "";
  top.gNeedsOther = "";
  local prod::GOpU = gImplU;
  forwards to prod(@a);
}
}
-- A forward production attribute gets every inherited attribute it has no equation for from the LHS, gOther included.
warnCode "which is shared with an application of flow:GOpU, depends on flow:gOther" {
production gSiteFwdAttr
top::GExpr ::=
{
  forward production attribute fp = gLit();
  top.gTy = "";
  top.gPP = "";
  top.gSmall = "";
  top.gNeedsOther = "";
  local prod::GOpU = gImplU;
  forwards to prod(@fp);
}
}
-- Where a host implementation's equation for the child accesses a synthesized attribute of its LHS, here gNeedsOther,
-- the dispatch signature's tile flow graph reaches that attribute's flow type through an implementation not known
-- here.  But implementations are checked assuming only the signature's normal graph, so gOther is still excess.
dispatch GOpN = GExpr ::= a::GExpr;
production gImplN implements GOpN
top::GExpr ::= a::GExpr
{
  a.gEnv = top.gNeedsOther;
  a.gCtx = top.gCtx;
  top.gTy = "";
  top.gPP = "";
  top.gSmall = "";
  top.gNeedsOther = "";
}
warnCode "which is shared with an application of flow:GOpN, depends on flow:gOther; implementations of flow:GOpN, including ones in other extensions, may supply it in its place depending only on flow:gCtx, flow:gEnv" {
production gSiteN
top::GExpr ::= a::GExpr
{
  a.gEnv = top.gOther;
  a.gCtx = top.gCtx;
  top.gTy = "";
  top.gPP = "";
  top.gSmall = "";
  top.gNeedsOther = "";
  local prod::GOpN = gImplN;
  forwards to prod(@a);
}
}
-- A production that passes a tree with '@' into a dispatch application held in a local, and accesses an attribute on
-- the tree, must supply the local what an implementation not known here may supply the tree from: the forward flow
-- type.  The host implementations here supply a.gEnv from the local's gEnv, but such an implementation may use gCtx.
warnCode "dependency flow:gCtx supplied to local" {
production gLocalDispatch
top::GExpr ::= x::GExpr
{
  local prod::GOpU = gImplU;
  local w::GExpr = prod(@x);
  w.gEnv = top.gEnv;
  top.gTy = "";
  top.gPP = "";
  top.gSmall = x.gSmall;
  top.gNeedsOther = "";
}
}

-- The translation attributes of an application may be those of an implementation not known here.
inherited attribute tEnv::String;
synthesized attribute tS::String;
synthesized attribute tOut::String;
nonterminal TTr with tEnv, tS;
flowtype tS {tEnv} on TTr;
production tConst
top::TTr ::=
{ top.tS = ""; }
translation attribute tTr::TTr;
nonterminal TExpr with tEnv, tTr, tOut;
dispatch TOp = TExpr ::= b::Boolean;
production tImpl implements TOp
top::TExpr ::= b::Boolean
{
  top.tTr = tConst();
  top.tOut = "";
}
warnCode "requires missing inherited attribute(s) flow:tEnv" {
production tUse
top::TExpr ::=
{
  local prod::TOp = tImpl;
  local w::TExpr = prod(true);
  w.tEnv = top.tEnv;
  top.tTr = tConst();
  top.tOut = w.tTr.tS;
}
}

-- An implementation in the host language may rely on what every application in the host language supplies to
-- a child shared through the signature.  This is checked where the implementation is compiled.
dispatch GOpS = GExpr ::= @a::GExpr;
production gSiteEnvOnly
top::GExpr ::= x::GExpr
{
  x.gEnv = top.gEnv;
  top.gTy = "";
  top.gPP = "";
  top.gSmall = "";
  top.gNeedsOther = "";
  local prod::GOpS = gImplReadsTy;
  forwards to prod(x);
}
warnCode "Access of synthesized attribute gTy on a requires missing inherited attribute(s) flow:gCtx" {
production gImplReadsTy implements GOpS
top::GExpr ::= @a::GExpr
{
  top.gTy = a.gTy;
  top.gPP = "";
  top.gSmall = "";
  top.gNeedsOther = "";
}
}

-- An application need not supply every inherited attribute to the tree it shares.  An implementation not known here
-- may supply the rest, with equations depending on anything in the forward flow type.  This holds even where every
-- implementation in the host language supplies them, as gImplW does through gWith (and ableC's bindBinaryOp through
-- bindExprDecl.)
dispatch GOpW = GExpr ::= @a::GExpr;
production gImplW implements GOpW
top::GExpr ::= @a::GExpr
{
  top.gNeedsOther = "";
  forwards to gWith(@a);
}
warnCode "Synthesized equation gSmall exceeds flow type with dependencies on flow:gCtx" {
production gSiteReads
top::GExpr ::= x::GExpr
{
  x.gEnv = top.gEnv;
  top.gSmall = x.gNeedsOther;
  local prod::GOpW = gImplW;
  forwards to prod(x);
}
}

-- An application in the host language supplying gOther, outside the forward flow type (see ext.)
dispatch GOpO = GExpr ::= @a::GExpr;
production gImplO implements GOpO
top::GExpr ::= @a::GExpr
{
  top.gTy = "";
  top.gPP = "";
  top.gSmall = "";
  top.gNeedsOther = "";
}
production gSiteSuppliesOther
top::GExpr ::= x::GExpr
{
  x.gEnv = top.gEnv;
  x.gCtx = top.gCtx;
  x.gOther = top.gOther;
  local prod::GOpO = gImplO;
  forwards to prod(x);
}

-- gImplT supplies gCtx to its child, but not gOther.  It is the only implementation that does not dispatch again.
-- gAgain dispatches again with its child, as Silver's transformExprAttributeDef does (see ext.)
dispatch GOpT = GExpr ::= @a::GExpr;
production gImplT implements GOpT
top::GExpr ::= @a::GExpr
{
  a.gCtx = top.gCtx;
  top.gTy = "";
  top.gPP = "";
  top.gSmall = "";
  top.gNeedsOther = "";
}
production gAgain implements GOpT
top::GExpr ::= @a::GExpr next::GOpT
{
  top.gTy = "";
  top.gPP = "";
  top.gSmall = "";
  top.gNeedsOther = "";
  forwards to next(a);
}
production gSiteT
top::GExpr ::= x::GExpr
{
  x.gEnv = top.gEnv;
  top.gTy = "";
  top.gPP = "";
  top.gSmall = "";
  top.gNeedsOther = "";
  local prod::GOpT = gImplT;
  forwards to prod(x);
}

-- An application of an implementation has nowhere to be recorded unless the application is at the root of a forward.
function gTakes
String ::= e::GExpr
{
  return "";
}
warnCode "Dispatch flow:GOp has shared children in its signature, and can only be applied in the root position of a forward or forward production attribute equation" {
production gArgApplication
top::GExpr ::= a::GExpr
{
  a.gEnv = top.gEnv;
  a.gCtx = top.gCtx;
  top.gTy = "";
  top.gPP = "";
  top.gSmall = "";
  top.gNeedsOther = "";
  local s::String = gTakes(gBind(a, gLit()));
}
}

-- An implementation may apply another by name, passing on its own child (gExtThenR, in ext).  The child was decorated
-- before the other implementation received it, so what that implementation accesses on the child depends on what was
-- supplied then.
dispatch GOpR = GExpr ::= @a::GExpr;
production gSiteR
top::GExpr ::= x::GExpr
{
  x.gEnv = top.gEnv;
  x.gCtx = top.gCtx;
  top.gNeedsOther = "";
  local prod::GOpR = gImplR;
  forwards to prod(x);
}
production gImplR implements GOpR
top::GExpr ::= @a::GExpr
{
  a.gOther = "";
  top.gTy = a.gOther;
  top.gPP = "";
  top.gSmall = "";
  top.gNeedsOther = "";
}

-- The same in the host language alone, where the only application supplies gOther from gCtx.
dispatch GOpB = GExpr ::= @a::GExpr;
production gSiteB
top::GExpr ::= x::GExpr
{
  x.gEnv = top.gEnv;
  x.gCtx = top.gCtx;
  x.gOther = top.gCtx;
  top.gSmall = "";
  top.gNeedsOther = "";
  local prod::GOpB = gThenB;
  forwards to prod(x);
}
production gReadsB implements GOpB
top::GExpr ::= @a::GExpr
{
  top.gTy = a.gOther;
  top.gPP = "";
  top.gSmall = "";
  top.gNeedsOther = "";
}
warnCode "Synthesized equation gSmall exceeds flow type with dependencies on flow:gCtx" {
production gThenB implements GOpB
top::GExpr ::= @a::GExpr
{
  top.gSmall = forward.gTy;
  top.gPP = "";
  top.gNeedsOther = "";
  forwards to gReadsB(a);
}
}

-- An implementation may access the attributes of its forward parent, the tree that forwarded to it.  A production
-- applying a dispatch signature as its forward is that forward parent, so what the implementation accesses depends on
-- that production's own inherited attributes, even ones it overrides on its forward.
dispatch GOpF = GExpr ::= @a::GExpr;
production gImplF implements GOpF
top::GExpr ::= @a::GExpr
{
  top.gTy = forwardParent.gSmall;
  top.gPP = "";
  top.gSmall = "";
  top.gNeedsOther = "";
}
warnCode "Synthesized equation gPP exceeds flow type with dependencies on flow:gCtx, flow:gEnv" {
production gSiteF
top::GExpr ::= x::GExpr
{
  x.gEnv = top.gEnv;
  x.gCtx = top.gCtx;
  forward.gEnv = "";
  top.gPP = forward.gTy;
  top.gSmall = top.gEnv;
  top.gNeedsOther = "";
  local prod::GOpF = gImplF;
  forwards to prod(x);
}
}

-- What a production supplies to a tree it passes with '@' to a child that a dispatch signature does not share is
-- bounded, even when it comes from the production's forward parent.
warnCode "which is shared with an application of flow:GOpU, depends on flow:gOther" {
production gSiteFPShare
top::GExpr ::= @y::GExpr x::GExpr
{
  x.gEnv = forwardParent.gNeedsOther;
  x.gCtx = top.gCtx;
  top.gTy = "";
  top.gPP = "";
  top.gSmall = "";
  top.gNeedsOther = "";
  local prod::GOpU = gImplU;
  forwards to prod(@x);
}
}

-- A production that overrides an inherited attribute on its forward keeps the copy where the forward applies by name an
-- implementation that dispatches again (see gExtOverrideV in ext/DispatchGuarantee.sv)...
dispatch GOpV = GExpr ::= a::GExpr;
production gImplV implements GOpV
top::GExpr ::= a::GExpr
{
  a.gEnv = top.gEnv;
  a.gCtx = top.gCtx;
  top.gTy = "";
  top.gPP = "";
  top.gSmall = "";
  top.gNeedsOther = "";
}
production gAgainV implements GOpV
top::GExpr ::= a::GExpr
{
  local prod::GOpV = gImplV;
  forwards to prod(@a);
}

-- ...or a production with a signature-shared child.  What this production supplies to the shared tree introduces
-- dependencies on that production's own inherited attributes, which are the forward's.  Accesses through a reference
-- to the forward are checked only against the reference set, so only the kept copy catches this.
inherited attribute rfK::String;
inherited attribute rfM::String;
synthesized attribute rfT::String;
synthesized attribute rfS::String;
synthesized attribute rfViaRef::String;
nonterminal RF with rfK, rfM, rfT, rfS, rfViaRef;
flowtype RF = decorate {}, forward {rfK}, rfT {rfK}, rfS {rfM}, rfViaRef {rfK};
production rfShares
top::RF ::= @a::RF
{
  top.rfT = "";
  top.rfS = a.rfT;
  top.rfViaRef = "";
}
warnCode "Synthesized equation rfViaRef exceeds flow type with dependencies on flow:rfM" {
production rfSite
top::RF ::= x::RF
{
  x.rfK = top.rfM;
  forward.rfM = top.rfK;
  local ref::Decorated RF with {rfM} = forward;
  top.rfViaRef = ref.rfS;
  forwards to rfShares(x);
}
}

-- An implementation copying an attribute of its forward parent depends only on that attribute's flow type, not on
-- what the other implementations access on their children.
dispatch GOpI = GExpr ::= @a::GExpr;
warnCode "Synthesized equation gPP exceeds flow type with dependencies on flow:gEnv" {
production gIReads implements GOpI
top::GExpr ::= @a::GExpr
{
  top.gTy = "";
  top.gPP = a.gNeedsOther;
  top.gSmall = "";
  top.gNeedsOther = "";
}
}
production gISupplies implements GOpI
top::GExpr ::= @a::GExpr
{
  a.gOther = top.gEnv;
  top.gTy = "";
  top.gPP = "";
  top.gSmall = "";
  top.gNeedsOther = "";
}
noWarnCode "exceeds flow type" {
production gICopies implements GOpI
top::GExpr ::= @a::GExpr
{
  top.gTy = "";
  top.gPP = forwardParent.gPP;
  top.gSmall = "";
  top.gNeedsOther = "";
}
}

-- Matching on the forward parent follows its forwards, so it may also evaluate this production's forward.
warnCode "Synthesized equation gPP exceeds flow type with dependencies on flow:gCtx, flow:gEnv" {
production gMatchFwdParent
top::GExpr ::= @a::GExpr
{
  top.gPP = case forwardParent of gLit() -> "lit" | _ -> "" end;
  top.gSmall = "";
  top.gNeedsOther = "";
  forwards to if top.gCtx == "" then gLit() else gWith(@a);
}
}

-- An implementation with a shared extra child can be applied only at the root of a forward, like a production with
-- shared children.  Applied anywhere else, the tree it shares there would have no decoration site.
production gImplSharesExtra implements GOpU
top::GExpr ::= a::GExpr @b::GExpr
{
  a.gEnv = top.gEnv;
  a.gCtx = top.gCtx;
  top.gTy = "";
  top.gPP = b.gPP;
  top.gSmall = "";
  top.gNeedsOther = "";
}
warnCode "has shared children in its signature beyond those of its dispatch signature" {
production gSiteSharesExtra
top::GExpr ::= x::GExpr y::GExpr
{
  x.gEnv = top.gEnv;
  x.gCtx = top.gCtx;
  y.gEnv = top.gEnv;
  y.gCtx = top.gCtx;
  top.gNeedsOther = "";
  local prod::GOpU = gImplSharesExtra(y);
  forwards to prod(@x);
}
}

-- An implementation may share a translation attribute of its child in turn.  What the application supplied to that
-- translation attribute takes precedence over the new decoration site's equations.
inherited attribute rtK::String;
synthesized attribute rtS::String;
nonterminal RT with rtK, rtS;
production rtReads
top::RT ::= b::RT
{
  b.rtK = "";
  top.rtS = b.rtK;
}
production rtLeaf
top::RT ::=
{ top.rtS = top.rtK; }
inherited attribute reM::String;
synthesized attribute reS::String;
translation attribute reTr::RT;
nonterminal RE with reM, reS, reTr;
flowtype RE = decorate {}, forward {}, reS {};
dispatch ROp = RE ::= @a::RE;
warnCode "Synthesized equation reS exceeds flow type with dependencies on flow:reM" {
production rImpl implements ROp
top::RE ::= @a::RE
{
  local l::RT = rtReads(@a.reTr);
  top.reS = l.rtS;
  top.reTr = rtLeaf();
}
}
warnCode "the implicit copy equation for flow:reS (due to forwarding) would exceed the attribute's flow type with dependencies on flow:reM" {
production rSite
top::RE ::= x::RE
{
  x.reTr.rtK = top.reM;
  local p::ROp = rImpl;
  forwards to p(x);
}
}

-- The same for a production that is not an implementation, sharing its own child in turn: what its application by
-- name supplied takes precedence.
inherited attribute qEnv::String;
inherited attribute qOther::String;
synthesized attribute qTy::String;
nonterminal QE with qEnv, qOther, qTy;
flowtype QE = decorate {qEnv}, forward {qEnv}, qTy {qEnv};
production qReadsOther
top::QE ::= b::QE
{
  b.qEnv = top.qEnv;
  b.qOther = "";
  top.qTy = b.qOther;
}
warnCode "Synthesized equation qTy exceeds flow type with dependencies on flow:qOther" {
production qShareOn
top::QE ::= @a::QE
{
  local l::QE = qReadsOther(@a);
  l.qEnv = top.qEnv;
  top.qTy = l.qTy;
}
}
warnCode "the implicit copy equation for flow:qTy (due to forwarding) would exceed the attribute's flow type with dependencies on flow:qOther" {
production qSite
top::QE ::= x::QE
{
  x.qEnv = top.qEnv;
  x.qOther = top.qOther;
  forwards to qShareOn(x);
}
}

-- Another application of qShareOn by name sees only what it supplies itself, not what qSite supplies.
noWarnCode "implicit copy equation" {
production qSiteEnv
top::QE ::= x::QE
{
  x.qEnv = top.qEnv;
  x.qOther = top.qEnv;
  forwards to qShareOn(x);
}
}

-- The same when the only application passes on, through a local, a tree it got decorated: what qChainSite supplied
-- reaches qShareOnChain through qChainSrc's local (see also ext).
warnCode "Synthesized equation qTy exceeds flow type with dependencies on flow:qOther" {
production qShareOnChain
top::QE ::= @a::QE
{
  local l::QE = qReadsOther(@a);
  l.qEnv = top.qEnv;
  top.qTy = l.qTy;
}
}
-- The copy of qTy in qChainSrc depends on qOther the same way: what qChainSite supplies for qOther
-- takes precedence over m.qOther, as the tree was decorated there first.
warnCode "In production flow:qChainSrc, the implicit copy equation for flow:qTy (due to forwarding) would exceed the attribute's flow type with dependencies on flow:qOther" {
production qChainSrc
top::QE ::= @a::QE
{
  local m::QE = @a;
  m.qOther = "";
  forwards to qShareOnChain(m);
}
}
warnCode "the implicit copy equation for flow:qTy (due to forwarding) would exceed the attribute's flow type with dependencies on flow:qOther" {
production qChainSite
top::QE ::= x::QE
{
  x.qEnv = top.qEnv;
  x.qOther = top.qOther;
  forwards to qChainSrc(x);
}
}

-- A host production supplying everything to a tree shared into it, for the hidden-dependency tests in ext.
inherited attribute hdEnv::String;
inherited attribute hdAlt::String;
synthesized attribute hdPP::String;
nonterminal HDExpr with hdEnv, hdAlt, hdPP;
flowtype HDExpr = forward {}, hdPP {};
production hdVar
top::HDExpr ::= n::String
{ top.hdPP = n; }
production hdWrap
top::HDExpr ::= c::HDExpr
{
  c.hdEnv = top.hdEnv;
  c.hdAlt = top.hdAlt;
  top.hdPP = c.hdPP;
}

-- The implicit copies of the forward are part of what it supplies itself, so they bound a contribution to a tree
-- shared there (see also ext.)
noWarnCode "hidden transitive" {
production udCollFwdCopy
top::UDExpr ::= e::UDExpr
{
  e.icoll := [];
  e.icoll <- top.icoll;
  forwards to @e;
}
}

-- A dispatch signature whose implementation in the host language has aspects in an extension (see ext.)
inherited attribute xEnv::String;
synthesized attribute xTy::String;
nonterminal XExpr with xEnv, xTy;
flowtype XExpr = decorate {xEnv}, forward {xEnv}, xTy {xEnv};
dispatch XOp = XExpr ::= @a::XExpr;
production xImpl implements XOp
top::XExpr ::= @a::XExpr
{
  top.xTy = "";
}
production xSite
top::XExpr ::= x::XExpr
{
  x.xEnv = top.xEnv;
  top.xTy = "";
  local prod::XOp = xImpl;
  forwards to prod(x);
}

-- An application by name of an implementation that passes its child on to another implementation (see transExt.)
inherited attribute sbA::String;
inherited attribute sbB::String;
nonterminal SBExpr with sbA, sbB;
flowtype SBExpr = decorate {}, forward {};
production sbLit
top::SBExpr ::=
{}
dispatch SBOp = SBExpr ::= @a::SBExpr;
production sbPass implements SBOp
top::SBExpr ::= @a::SBExpr
{
  local prod::SBOp = sbRead;
  forwards to prod(a);
}
production sbRead implements SBOp
top::SBExpr ::= @a::SBExpr
{}
production sbSite
top::SBExpr ::= x::SBExpr
{
  x.sbA = top.sbB;
  forwards to sbPass(x);
}

-- A tree passed to this production already decorated cannot be shared as a translation attribute
-- t of the left-hand side: it keeps the inherited attributes supplied to it before, not those
-- supplied to t, and the graph cannot show that t's inherited attributes are then the tree's (see
-- addDecSiteTreeEqs).  A plain child can be.
inherited attribute tdK::String;
synthesized attribute tdS::String;
nonterminal TDT with tdK, tdS;
flowtype TDT = decorate {}, forward {}, tdS {tdK};
translation attribute tdT::TDT;
nonterminal TD with tdK, tdS, tdT;
flowtype TD = decorate {}, forward {}, tdS {tdK}, tdT {};
production tdtLeaf
top::TDT ::=
{ top.tdS = top.tdK; }
production tdLeaf
top::TD ::=
{
  top.tdS = top.tdK;
  top.tdT = tdtLeaf();
}
warnCode "child a may be passed to this production already decorated, so it cannot be shared as flow:tdT of the left-hand side: it would keep the inherited attributes supplied to it before, and not receive those supplied to the left-hand side's flow:tdT" {
production tdShares
top::TD ::= @a::TDT
{
  top.tdT = @a;
  top.tdS = "";
}
}
noWarnCode "cannot be shared as" {
production tdPlain
top::TD ::= a::TDT
{
  top.tdT = @a;
  top.tdS = "";
}
}

-- An implementation may supply a child from an attribute of its forward parent: the production
-- applying the dispatch signature, or an earlier implementation in a chain of forwards, from any
-- extension.  So what gImplFP's a.gOther depends on is bounded only by gNeedsOther's flow type (see
-- implTileStitchPoint and ext).
dispatch GOpFP = GExpr ::= @a::GExpr;
production gImplFP implements GOpFP
top::GExpr ::= @a::GExpr
{
  a.gOther = forwardParent.gNeedsOther;
  top.gTy = "";
  top.gPP = "";
  top.gSmall = "";
  top.gNeedsOther = "";
}
production gSiteFP
top::GExpr ::= x::GExpr
{
  x.gEnv = top.gEnv;
  x.gCtx = top.gCtx;
  top.gNeedsOther = "";
  local prod::GOpFP = gImplFP;
  forwards to prod(x);
}

-- An implementation may supply a child from a second child's attributes, which depend on what it
-- supplies to the second child (gImplSB's a.gOther from b.gNeedsOther).  GOpSB's graph has the
-- children's flow types for this (see ext).
dispatch GOpSB = GExpr ::= @a::GExpr b::GExpr;
production gImplSB implements GOpSB
top::GExpr ::= @a::GExpr b::GExpr
{
  b.gEnv = top.gEnv;
  b.gCtx = top.gCtx;
  b.gOther = top.gOther;
  a.gOther = b.gNeedsOther;
  top.gTy = "";
  top.gPP = "";
  top.gSmall = "";
  top.gNeedsOther = "";
}
production gSiteSB
top::GExpr ::= x::GExpr
{
  x.gEnv = top.gEnv;
  x.gCtx = top.gCtx;
  top.gNeedsOther = "";
  local prod::GOpSB = gImplSB;
  forwards to prod(x, gLit());
}

-- top.gSmall accesses x.gNeedsOther, which needs x.gOther, which gImplFP supplies from its forward
-- parent's gNeedsOther: that forward parent may be an implementation in another extension, as
-- gExtImplFPOther (see ext), whose gNeedsOther depends on gOther.  (Also gCtx: this sharing site
-- leaves gOther to implementations.)
warnCode "Synthesized equation gSmall exceeds flow type with dependencies on flow:gCtx, flow:gOther" {
production gSiteFPAccesses
top::GExpr ::= x::GExpr
{
  x.gEnv = top.gEnv;
  x.gCtx = top.gCtx;
  top.gSmall = x.gNeedsOther;
  top.gNeedsOther = "";
  local prod::GOpFP = gImplFP;
  forwards to prod(x);
}
}

-- A host application may supply the tree it shares with GOpC through a local chain: x's equations
-- count as what the application supplies to y, in resolution (gImplC) and in the duplicate-equation
-- check (gImplCDup).
dispatch GOpC = GExpr ::= @a::GExpr;
production gChainSite
top::GExpr ::= x::GExpr
{
  x.gEnv = "";
  x.gCtx = "";
  local y::GExpr = @x;
  top.gTy = "";
  top.gPP = "";
  top.gSmall = "";
  top.gNeedsOther = "";
  local prod::GOpC = gImplC;
  forwards to prod(y);
}
noWarnCode "requires missing inherited attribute" {
production gImplC implements GOpC
top::GExpr ::= @a::GExpr
{
  top.gTy = a.gTy;
  top.gPP = "";
  top.gSmall = "";
  top.gNeedsOther = "";
}
}
warnCode "Duplicate equation for gEnv on a in production flow:gImplCDup" {
production gImplCDup implements GOpC
top::GExpr ::= @a::GExpr
{
  a.gEnv = top.gEnv;
  top.gTy = "";
  top.gPP = "";
  top.gSmall = "";
  top.gNeedsOther = "";
}
}

-- A host sharing site may supply the tree it shares through a local chain: what the dispatch
-- signature's graph projects for a.gOther is x's equation, which y's vertex reaches (see ext).
dispatch GOpQ = GExpr ::= @a::GExpr;
production gImplQ implements GOpQ
top::GExpr ::= @a::GExpr
{
  top.gTy = "";
  top.gPP = "";
  top.gSmall = "";
  top.gNeedsOther = "";
}
production gChainSiteQ
top::GExpr ::= x::GExpr
{
  x.gEnv = top.gEnv;
  x.gCtx = top.gCtx;
  x.gOther = top.gOther;
  local y::GExpr = @x;
  top.gNeedsOther = "";
  local prod::GOpQ = gImplQ;
  forwards to prod(y);
}

-- A host production may apply an implementation by name with a fresh tree, and that implementation
-- may dispatch again with it.  The tree then reaches every implementation, so what that application
-- supplies must suffice for each: gSiteYByName leaves out gCtx.
dispatch GOpY = GExpr ::= @a::GExpr;
warnCode "Access of synthesized attribute gTy on a requires missing inherited attribute(s) flow:gCtx to be supplied" {
production gReadsY implements GOpY
top::GExpr ::= @a::GExpr
{
  top.gTy = a.gTy;
  top.gPP = "";
  top.gSmall = "";
  top.gNeedsOther = "";
}
}
production gAgainY implements GOpY
top::GExpr ::= @a::GExpr
{
  local prod::GOpY = gReadsY;
  forwards to prod(a);
}
production gSiteY
top::GExpr ::= x::GExpr
{
  x.gEnv = top.gEnv;
  x.gCtx = top.gCtx;
  top.gTy = "";
  top.gPP = "";
  top.gSmall = "";
  top.gNeedsOther = "";
  local prod::GOpY = gReadsY;
  forwards to prod(x);
}
production gSiteYByName
top::GExpr ::= x::GExpr
{
  x.gEnv = top.gEnv;
  top.gTy = "";
  top.gPP = "";
  top.gSmall = "";
  top.gNeedsOther = "";
  forwards to gAgainY(x);
}

-- gBounce's shared child arrives only through gBouncePass passing on its own shared child, as
-- Silver's accessBouncer does: what gBouncePass's applications supply reaches gBounce through
-- gBouncePass.
warnCode "Access of synthesized attribute gTy on a requires missing inherited attribute(s) flow:gCtx" {
production gBounce
top::GExpr ::= @a::GExpr
{
  top.gTy = a.gTy;
  top.gPP = "";
  top.gSmall = "";
  top.gNeedsOther = "";
}
}
production gBouncePass
top::GExpr ::= @a::GExpr
{
  forwards to gBounce(a);
}
production gBounceSite
top::GExpr ::= x::GExpr
{
  x.gEnv = top.gEnv;
  top.gTy = "";
  top.gPP = "";
  top.gSmall = "";
  top.gNeedsOther = "";
  forwards to gBouncePass(x);
}

-- An implementation may pass its child to a production that applies the dispatch signature again
-- (Silver's accessBouncer).  Resolution follows the chain back to the signature's sharing sites,
-- which lead to the bouncer again: a cycle through sharing sites alone, which counts as supplied,
-- so only what gSiteZByName leaves out is reported, not gEnv as well.
dispatch GOpZ = GExpr ::= @a::GExpr;
production gBounceZ
top::GExpr ::= @a::GExpr next::GOpZ
{
  forwards to next(a);
}
production gViaBounceZ implements GOpZ
top::GExpr ::= @a::GExpr
{
  forwards to gBounceZ(a, gReadsZ);
}
warnCode "Access of synthesized attribute gTy on a requires missing inherited attribute(s) flow:gCtx to be supplied" {
production gReadsZ implements GOpZ
top::GExpr ::= @a::GExpr
{
  top.gTy = a.gTy;
  top.gPP = "";
  top.gSmall = "";
  top.gNeedsOther = "";
}
}
production gSiteZ
top::GExpr ::= x::GExpr
{
  x.gEnv = top.gEnv;
  x.gCtx = top.gCtx;
  top.gTy = "";
  top.gPP = "";
  top.gSmall = "";
  top.gNeedsOther = "";
  local prod::GOpZ = gViaBounceZ;
  forwards to prod(x);
}
production gSiteZByName
top::GExpr ::= x::GExpr
{
  x.gEnv = top.gEnv;
  top.gTy = "";
  top.gPP = "";
  top.gSmall = "";
  top.gNeedsOther = "";
  forwards to gViaBounceZ(x);
}

-- An implementation that supplies an attribute to the child it passes on supplies it to the trees
-- shared by its own applications by name, so those need not (gSitePByName leaves out gCtx).
dispatch GOpP = GExpr ::= @a::GExpr;
noWarnCode "requires missing inherited attribute" {
production gReadsP implements GOpP
top::GExpr ::= @a::GExpr
{
  top.gTy = a.gTy;
  top.gPP = "";
  top.gSmall = "";
  top.gNeedsOther = "";
}
}
production gSuppliesP implements GOpP
top::GExpr ::= @a::GExpr
{
  a.gCtx = top.gCtx;
  local prod::GOpP = gReadsP;
  forwards to prod(a);
}
production gSiteP
top::GExpr ::= x::GExpr
{
  x.gEnv = top.gEnv;
  x.gCtx = top.gCtx;
  top.gTy = "";
  top.gPP = "";
  top.gSmall = "";
  top.gNeedsOther = "";
  local prod::GOpP = gReadsP;
  forwards to prod(x);
}
production gSitePByName
top::GExpr ::= x::GExpr
{
  x.gEnv = top.gEnv;
  top.gTy = "";
  top.gPP = "";
  top.gSmall = "";
  top.gNeedsOther = "";
  forwards to gSuppliesP(x);
}

-- A production passing on a child shared through its signature may supply it (cpPass's a.cpK).  The
-- equation applies to the tree before the receiving production gets it, and reaches that
-- production's graph through the recorded sharing site (chain precedence, see ext).
inherited attribute cpEnv::String;
inherited attribute cpK::String;
inherited attribute cpOther::String;
synthesized attribute cpS::String;
nonterminal CP with cpEnv, cpK, cpOther, cpS;
flowtype CP = decorate {cpEnv}, forward {cpEnv}, cpS {cpEnv};
production cpLit
top::CP ::=
{ top.cpS = top.cpEnv; }
production cpReads
top::CP ::= @a::CP
{ top.cpS = ""; }
production cpPass
top::CP ::= @a::CP
{
  a.cpK = top.cpOther;
  forwards to cpReads(a);
}
production cpSite
top::CP ::= x::CP
{
  x.cpEnv = top.cpEnv;
  x.cpOther = top.cpOther;
  forwards to cpPass(x);
}

-- The same as gChainSiteQ through two locals: x's equations reach the implementations through y and
-- z, both for the projection (see ext) and for the duplicate-equation check.
dispatch GOpQ2 = GExpr ::= @a::GExpr;
production gImplQ2 implements GOpQ2
top::GExpr ::= @a::GExpr
{
  top.gTy = "";
  top.gPP = "";
  top.gSmall = "";
  top.gNeedsOther = "";
}
warnCode "Duplicate equation for gEnv on a in production flow:gImplQ2Dup" {
production gImplQ2Dup implements GOpQ2
top::GExpr ::= @a::GExpr
{
  a.gEnv = top.gEnv;
  top.gTy = "";
  top.gPP = "";
  top.gSmall = "";
  top.gNeedsOther = "";
}
}
production gChainSiteQ2
top::GExpr ::= x::GExpr
{
  x.gEnv = top.gEnv;
  x.gCtx = top.gCtx;
  x.gOther = top.gOther;
  local y::GExpr = @x;
  local z::GExpr = @y;
  top.gNeedsOther = "";
  local prod::GOpQ2 = gImplQ2;
  forwards to prod(z);
}

-- A chain with a conditional link: where r is a new tree, y's own equations apply, so the
-- projection reaches both y's and x's (see ext).
dispatch GOpQ3 = GExpr ::= @a::GExpr;
production gImplQ3 implements GOpQ3
top::GExpr ::= @a::GExpr
{
  top.gTy = "";
  top.gPP = "";
  top.gSmall = "";
  top.gNeedsOther = "";
}
production gChainSiteQ3
top::GExpr ::= x::GExpr
{
  x.gEnv = top.gEnv;
  x.gCtx = top.gCtx;
  x.gOther = top.gOther;
  local r::GExpr = if top.gEnv == "" then @x else gLit();
  local y::GExpr = @r;
  y.gEnv = top.gEnv;
  y.gCtx = top.gCtx;
  y.gOther = top.gCtx;
  top.gPP = "";
  top.gNeedsOther = "";
  local prod::GOpQ3 = gImplQ3;
  forwards to prod(y);
}

-- A tree shared into a local in one branch only guarantees nothing to the sharing site, so an
-- implementation's equation for the child is not a duplicate.
dispatch GOpC3 = GExpr ::= @a::GExpr;
production gChainSite3
top::GExpr ::= x::GExpr
{
  x.gEnv = "";
  x.gCtx = "";
  local r::GExpr = if top.gEnv == "" then @x else gLit();
  local y::GExpr = @r;
  top.gTy = "";
  top.gPP = "";
  top.gSmall = "";
  top.gNeedsOther = "";
  local prod::GOpC3 = gImplC3;
  forwards to prod(y);
}
noWarnCode "Duplicate equation" {
production gImplC3 implements GOpC3
top::GExpr ::= @a::GExpr
{
  a.gEnv = top.gEnv;
  top.gTy = "";
  top.gPP = "";
  top.gSmall = "";
  top.gNeedsOther = "";
}
}

-- As GOpZ, with a local in the bouncer: the cycle through the sharing sites passes through the tree
-- shared into that local, and still counts as supplied, so what gSiteZLByName supplies suffices for
-- gSmall.
dispatch GOpZL = GExpr ::= @a::GExpr;
production gBounceZL
top::GExpr ::= @a::GExpr next::GOpZL
{
  local y::GExpr = @a;
  forwards to next(y);
}
production gViaBounceZL implements GOpZL
top::GExpr ::= @a::GExpr
{
  forwards to gBounceZL(a, gReadsZL);
}
noWarnCode "requires missing inherited attribute" {
production gReadsZL implements GOpZL
top::GExpr ::= @a::GExpr
{
  top.gTy = "";
  top.gPP = "";
  top.gSmall = a.gSmall;
  top.gNeedsOther = "";
}
}
production gSiteZL
top::GExpr ::= x::GExpr
{
  x.gEnv = top.gEnv;
  x.gCtx = top.gCtx;
  top.gTy = "";
  top.gPP = "";
  top.gSmall = "";
  top.gNeedsOther = "";
  local prod::GOpZL = gViaBounceZL;
  forwards to prod(x);
}
production gSiteZLByName
top::GExpr ::= x::GExpr
{
  x.gEnv = top.gEnv;
  top.gTy = "";
  top.gPP = "";
  top.gSmall = "";
  top.gNeedsOther = "";
  forwards to gViaBounceZL(x);
}

-- gChooseA applies gReadsA by name or dispatches again with its child, as Silver's
-- decoratedAccessHandler does (unknownDclAccessHandler, or the attribute's own handler).  gReadsA's
-- child arrives from gChooseA's child, whose sharing sites lead back to gChooseA's child: a cycle
-- through sharing sites alone, which counts as supplied, so what gSiteA supplies suffices.
dispatch GOpA = GExpr ::= @a::GExpr;
production gChooseA implements GOpA
top::GExpr ::= @a::GExpr
{
  local next::GOpA = gChooseA;
  forwards to if a.gPP == "" then gReadsA(a) else next(a);
}
noWarnCode "requires missing inherited attribute" {
production gReadsA implements GOpA
top::GExpr ::= @a::GExpr
{
  top.gTy = a.gTy;
  top.gPP = "";
  top.gSmall = "";
  top.gNeedsOther = "";
}
}
production gSiteA
top::GExpr ::= x::GExpr
{
  x.gEnv = top.gEnv;
  x.gCtx = top.gCtx;
  top.gTy = "";
  top.gPP = "";
  top.gSmall = "";
  top.gNeedsOther = "";
  local prod::GOpA = gChooseA;
  forwards to prod(x);
}
