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
  top.reTr = rtLeaf();
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
production qChainSrc
top::QE ::= @a::QE
{
  local m::QE = @a;
  m.qOther = "";
  forwards to qShareOnChain(m);
}
production qChainSite
top::QE ::= x::QE
{
  x.qEnv = top.qEnv;
  x.qOther = top.qOther;
  forwards to qChainSrc(x);
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
