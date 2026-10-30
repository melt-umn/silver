grammar flow;

-- An override equation hides the first equation for its attribute on whichever path of decoration
-- sites its tree takes, and that equation's consumers assume its dependencies.  So the override may
-- depend only on what every path that reaches such an equation allows (see overrideBounds and
-- pathBound).

inherited attribute obK::String;
inherited attribute obM::String;
inherited attribute obF::String;
synthesized attribute obT::String;
synthesized attribute obS::String;
synthesized attribute obR::Decorated OB with {obK};
nonterminal OB with obK, obM, obF, obT, obS, obR;
flowtype OB = decorate {}, forward {obF}, obT {obK}, obS {obM}, obR {obK};

production obLeaf
top::OB ::=
{
  top.obT = top.obK;
  top.obS = top.obM;
  top.obR = top;
}
-- obH supplies its child's obK from its own obK, and hands out a reference to the child that
-- guarantees only obK.
production obH
top::OB ::= w::OB
{
  w.obK = top.obK;
  top.obT = "";
  top.obS = "";
  top.obR = w;
}
-- obJ supplies its child's obK from its own obM.
production obJ
top::OB ::= z::OB
{
  z.obK = top.obM;
  top.obT = "";
  top.obS = "";
  top.obR = top;
}
production obNone
top::OB ::= z::OB
{
  top.obT = "";
  top.obS = "";
  top.obR = top;
}
production obI
top::OB ::= z::OB
{
  forwards to obH(@z);
}

-- (a) Exclusive decoration sites of the overriding production: obI's path allows obK, obJ's obM.
warnCode "Inherited override equation for flow:obK on child c may exceed a flow type with hidden transitive dependencies on flow:obM; on some reference to this tree, this attribute may be expected to depend on no left-side inherited attributes" {
production obTwoPaths
top::OB ::= c::OB  flag::Boolean
{
  c.obK = top.obM;
  top.obT = "";
  top.obS = "";
  top.obR = top;
  forwards to if flag then obI(@c) else obJ(@c);
}
}

-- (b) The exclusive decoration sites are inside the applied production, or behind its local.
production obSel
top::OB ::= sel::Boolean  z::OB
{
  top.obT = "";
  top.obS = "";
  top.obR = top;
  forwards to if sel then obH(@z) else obJ(@z);
}
warnCode "Inherited override equation for flow:obK on child c may exceed a flow type with hidden transitive dependencies on flow:obM; on some reference to this tree, this attribute may be expected to depend on no left-side inherited attributes (from forward[flow:obSel:z]); flow:obM is excluded by the equation for flow:obK in flow:obH, through forward[flow:obH:w]" {
production obInside
top::OB ::= c::OB
{
  c.obK = top.obM;
  top.obT = "";
  top.obS = "";
  top.obR = top;
  forwards to obSel(true, @c);
}
}
production obLocalSel
top::OB ::= sel::Boolean  z::OB
{
  top.obT = "";
  top.obS = "";
  top.obR = top;
  local y::OB = @z;
  forwards to if sel then obH(@y) else obJ(@y);
}
warnCode "Inherited override equation for flow:obK on child c may exceed a flow type with hidden transitive dependencies on flow:obM; on some reference to this tree, this attribute may be expected to depend on no left-side inherited attributes (from forward[flow:obLocalSel:z])" {
production obInsideLocal
top::OB ::= c::OB
{
  c.obK = top.obM;
  top.obT = "";
  top.obS = "";
  top.obR = top;
  forwards to obLocalSel(true, @c);
}
}

-- (c) obH's equation uses its own obK, which obR2 supplies along exclusive decoration sites: obA
-- from obK, obB from obM, so they meet at no attribute.  obSelParent's other branch, through obHM,
-- allows both.
production obA
top::OB ::= y::OB
{
  y.obK = top.obK;
  top.obT = "";
  top.obS = "";
  top.obR = y.obR;
}
production obB
top::OB ::= y::OB
{
  y.obK = top.obM;
  top.obT = "";
  top.obS = "";
  top.obR = top;
}
production obR2
top::OB ::= flag::Boolean  x::OB
{
  top.obT = "";
  top.obS = "";
  top.obR = top;
  forwards to if flag then obA(@x) else obB(@x);
}
production obHM
top::OB ::= w::OB
{
  w.obK = top.obK ++ top.obM;
  top.obT = "";
  top.obS = "";
  top.obR = top;
}
production obSelParent
top::OB ::= sel::Boolean  z::OB
{
  top.obT = "";
  top.obS = "";
  top.obR = top;
  forwards to if sel then obHM(@z) else obR2(true, obH(@z));
}
warnCode "Inherited override equation for flow:obK on child c may exceed a flow type with hidden transitive dependencies on flow:obM; on some reference to this tree, this attribute may be expected to depend on no left-side inherited attributes (from forward[flow:obSelParent:z])" {
production obThroughParent
top::OB ::= c::OB
{
  c.obK = top.obM;
  top.obT = "";
  top.obS = "";
  top.obR = top;
  forwards to obSelParent(true, @c);
}
}

-- (d) The first equation accesses the tree's own obS, which needs obM: obDA supplies that from obK,
-- obDB from obM.
production obDA
top::OB ::= y::OB
{
  y.obK = y.obS;
  y.obM = top.obK;
  top.obT = "";
  top.obS = "";
  top.obR = y;
}
production obDB
top::OB ::= y::OB
{
  y.obK = y.obS;
  y.obM = top.obM;
  top.obT = "";
  top.obS = "";
  top.obR = top;
}
warnCode "Inherited override equation for flow:obK on child c may exceed a flow type with hidden transitive dependencies on flow:obM; on some reference to this tree, this attribute may be expected to depend on no left-side inherited attributes" {
production obOwnSyn
top::OB ::= c::OB  flag::Boolean
{
  c.obK = top.obM;
  top.obT = "";
  top.obS = "";
  top.obR = top;
  forwards to if flag then obDA(@c) else obDB(@c);
}
}

-- (L) A tree shared into a local: neither the copy that the override records at y nor this
-- production's own equation on y bounds it, as this production's graph shows the override to their
-- consumers.
warnCode "Inherited override equation for flow:obK on child x may exceed a flow type with hidden transitive dependencies on flow:obM; on some reference to this tree, this attribute may be expected to depend only on flow:obK (from local flow:obLocalShared:local:" {
production obLocalShared
top::OB ::= x::OB
{
  x.obK = top.obM;
  top.obT = "";
  top.obS = "";
  top.obR = top;
  local y::OB = @x;
  forwards to obH(@y);
}
}
warnCode "Inherited override equation for flow:obK on child x may exceed a flow type with hidden transitive dependencies on flow:obM; on some reference to this tree, this attribute may be expected to depend only on flow:obK (from local flow:obLocalOwnEq:local:" {
production obLocalOwnEq
top::OB ::= x::OB
{
  x.obK = top.obM;
  top.obT = "";
  top.obS = "";
  top.obR = top;
  local y::OB = @x;
  y.obK = top.obM;
  forwards to obH(@y);
}
}

-- Translation attributes: a tree shared as a translation attribute of the left-hand side, where the
-- applying production supplies obTr.obK alone; and an override on a translation attribute of a
-- child, bounded by where that translation attribute is shared (obTransTwo) and by where the child
-- is shared (obTransBase).
translation attribute obTr::OB;
nonterminal OBT with obK, obM, obTr;
production obtH
top::OBT ::= w::OBT
{
  w.obTr.obK = top.obK;
  top.obTr = obLeaf();
}
production obtJ
top::OBT ::= z::OBT
{
  z.obTr.obK = top.obM;
  top.obTr = obLeaf();
}
warnCode "Inherited override equation for flow:obK on child c may exceed a flow type with hidden transitive dependencies on flow:obM; on some reference to this tree, this attribute may be expected to depend only on flow:obTr.flow:obK (from translation attribute flow:obTr of left-hand side); flow:obM is excluded by what the left-hand side of flow:obLhsTrans gets for flow:obK" {
production obLhsTrans
top::OBT ::= c::OB
{
  c.obK = top.obM;
  top.obTr = @c;
}
}
warnCode "Inherited override equation for flow:obK on translation attribute flow:obTr of child c may exceed a flow type with hidden transitive dependencies on flow:obM; on some reference to this tree, this attribute may be expected to depend on no left-side inherited attributes" {
production obTransTwo
top::OB ::= c::OBT  flag::Boolean
{
  c.obTr.obK = top.obM;
  top.obT = "";
  top.obS = "";
  top.obR = top;
  forwards to if flag then obI(@c.obTr) else obJ(@c.obTr);
}
}
warnCode "Inherited override equation for flow:obTr.flow:obK on child c may exceed a flow type with hidden transitive dependencies on flow:obM; on some reference to this tree, this attribute may be expected to depend on no left-side inherited attributes" {
production obTransBase
top::OBT ::= c::OBT  flag::Boolean
{
  c.obTr.obK = top.obM;
  forwards to if flag then obtH(@c) else obtJ(@c);
}
}

-- A translation attribute of another production's local, shared there: its decoration sites are
-- listed from the flow environment, since the overriding grammar's environment has no locals of
-- other productions (see sharedTransAttrsOf).
production obtHold
top::OBT ::= z::OBT
{
  top.obTr = obLeaf();
  local m::OBT = @z;
  local q::OB = obH(@m.obTr);
  q.obK = top.obK;
}
warnCode "Inherited override equation for flow:obTr.flow:obK on child c may exceed a flow type with hidden transitive dependencies on flow:obM; on some reference to this tree, this attribute may be expected to depend only on flow:obK (from forward[flow:obtHold:z])" {
production obtHoldSite
top::OBT ::= c::OBT
{
  c.obTr.obK = top.obM;
  forwards to obtHold(@c);
}
}
noWarnCode "requires missing inherited attribute" {
production obtHoldAccess
top::OBT ::= c::OBT
{
  local u::String = c.obTr.obT;
  forwards to obtHold(@c);
}
}
-- The same for the main body's local, seen from an aspect, whose environment does not have it.
production obtAspectHost
top::OBT ::= c::OBT
{
  top.obTr = obLeaf();
  local m::OBT = @c;
  local q::OB = obH(@m.obTr);
  q.obK = top.obK;
}
warnCode "Inherited override equation for flow:obTr.flow:obK on child c may exceed a flow type with hidden transitive dependencies on flow:obM; on some reference to this tree, this attribute may be expected to depend only on flow:obK (from local flow:obtAspectHost:local:" {
aspect production obtAspectHost
top::OBT ::= c::OBT
{
  c.obTr.obK = top.obM;
}
}

-- A production that gets the tree through its signature from a production that shows the override
-- is covered (see enterLevel): its graph projects what the applying production supplies, so its own
-- equation hides nothing, the paths go on to where it shares the tree, and its forward is bounded
-- by the forward flow type.  Reached behind a child that is not shared through the signature
-- (obSigOwn, behind obSigMid's z), the production is not covered.
production obSigOwn
top::OB ::= @a::OB
{
  a.obK = top.obK;
  top.obT = "";
  top.obS = "";
  top.obR = top;
}
production obSigMid
top::OB ::= z::OB
{
  top.obT = "";
  top.obS = "";
  top.obR = top;
  forwards to obSigOwn(z);
}
warnCode "Inherited override equation for flow:obK on child x may exceed a flow type with hidden transitive dependencies on flow:obM; on some reference to this tree, this attribute may be expected to depend only on flow:obK (from forward[flow:obSigMid:z])" {
production obSigNested
top::OB ::= x::OB
{
  x.obK = top.obM;
  top.obT = "";
  top.obS = "";
  top.obR = top;
  forwards to obSigMid(@x);
}
}
-- obSigOn's own equation hides obJ's, and is not a duplicate equation, as obSigOnNone supplies
-- nothing for a.
warnCode "Inherited override equation for flow:obK on child a may exceed a flow type with hidden transitive dependencies on flow:obK; on some reference to this tree, this attribute may be expected to depend only on flow:obM (from forward[flow:obJ:z])" {
production obSigOn
top::OB ::= @a::OB
{
  a.obK = top.obK;
  top.obT = "";
  top.obS = "";
  top.obR = top;
  forwards to obJ(@a);
}
}
production obSigOnNone
top::OB ::= x::OB
{
  top.obT = "";
  top.obS = "";
  top.obR = top;
  forwards to obSigOn(x);
}
warnCode "Inherited override equation for flow:obK on child x may exceed a flow type with hidden transitive dependencies on flow:obK; on some reference to this tree, this attribute may be expected to depend only on flow:obM (from forward[flow:obSigOn:a])" {
production obSigOnSite
top::OB ::= x::OB
{
  x.obK = top.obK;
  top.obT = "";
  top.obS = "";
  top.obR = top;
  forwards to obSigOn(x);
}
}
production obSigFwd
top::OB ::= @a::OB
{
  top.obT = "";
  top.obS = "";
  top.obR = top;
  forwards to @a;
}
warnCode "Inherited override equation for flow:obK on child x may exceed a flow type with hidden transitive dependencies on flow:obM; on some reference to this tree, this attribute may be expected to depend only on flow:obF, flow:obK (from forward[flow:obSigFwd:a])" {
production obSigFwdSiteM
top::OB ::= x::OB
{
  x.obK = top.obM;
  top.obT = "";
  top.obS = "";
  top.obR = top;
  forwards to obSigFwd(x);
}
}

-- A plain child that becomes the applied production's forward: that production's graph does not
-- show the override, so its forward's copy is the first equation.
production obFwdTo
top::OB ::= c::OB
{
  top.obT = "";
  top.obS = "";
  top.obR = top;
  forwards to @c;
}
warnCode "Inherited override equation for flow:obK on child x may exceed a flow type with hidden transitive dependencies on flow:obF; on some reference to this tree, this attribute may be expected to depend only on flow:obK (from forward[flow:obFwdTo:c])" {
production obFwdToF
top::OB ::= x::OB
{
  x.obK = top.obF;
  top.obT = "";
  top.obS = "";
  top.obR = top;
  forwards to obFwdTo(@x);
}
}

-- What this production supplies to obI's left-hand side, its forward in that branch, leaves out the
-- copy that the override records at the forward, c's decoration site in the other branch.
warnCode "Inherited override equation for flow:obK on child c may exceed a flow type with hidden transitive dependencies on flow:obF; on some reference to this tree, this attribute may be expected to depend only on flow:obK" {
production obFwdOrI
top::OB ::= c::OB  flag::Boolean
{
  c.obK = top.obF;
  top.obT = "";
  top.obS = "";
  top.obR = top;
  forwards to if flag then @c else obI(@c);
}
}

-- An override on a tree shared with an application of a dispatch signature: what this production
-- supplies to the application's left-hand side, y, follows y's exclusive decoration sites.  An
-- implementation not known here may supply c.obK depending on y.obF, which is given.
dispatch OBD = OB ::= a::OB;
production obdImpl implements OBD
top::OB ::= a::OB
{
  a.obK = top.obK;
  top.obT = "";
  top.obS = "";
  top.obR = top;
}
warnCode "Inherited override equation for flow:obK on child c may exceed a flow type with hidden transitive dependencies on flow:obM; on some reference to this tree, this attribute may be expected to depend only on flow:obF" {
production obDispatchParent
top::OB ::= c::OB  flag::Boolean
{
  c.obK = top.obM;
  top.obT = "";
  top.obS = "";
  top.obR = top;
  local prod::OBD = obdImpl;
  local y::OB = prod(@c);
  y.obF = top.obF;
  forwards to if flag then obH(@y) else obJ(@y);
}
}

-- An implementation applied by name at a child at a position of its dispatch signature: its own
-- equation there is assumed as the signature allows, and the paths go on past it.
dispatch OBE = OB ::= a::OB;
production obeOwn implements OBE
top::OB ::= a::OB
{
  a.obK = top.obK;
  top.obT = "";
  top.obS = "";
  top.obR = top;
}
production obePass implements OBE
top::OB ::= a::OB
{
  a.obK = top.obM;
  top.obT = "";
  top.obS = "";
  top.obR = top;
  forwards to obJ(@a);
}
warnCode "Inherited override equation for flow:obK on child c may exceed a flow type with hidden transitive dependencies on flow:obF; on some reference to this tree, this attribute may be expected to depend only on flow:obM (from forward[flow:obePass:a])" {
production obByNamePass
top::OB ::= c::OB
{
  c.obK = top.obF;
  top.obT = "";
  top.obS = "";
  top.obR = top;
  forwards to obePass(@c);
}
}

-- A loop whose iterations swap obK and obM on the forward, and one whose iterations agree (see
-- solveLoop).
nonterminal OBL with obK, obM, obT;
flowtype OBL = decorate {}, forward {obK, obM}, obT {obK};
production oblH
top::OBL ::= w::OBL
{
  w.obK = top.obK;
  top.obT = "";
}
production oblSwap
top::OBL ::= z::OBL  b::Boolean
{
  top.obT = "";
  forward.obK = top.obM;
  forward.obM = top.obK;
  forwards to if b then oblSwap(@z, false) else oblH(@z);
}
warnCode "Inherited override equation for flow:obK on child c may exceed a flow type with hidden transitive dependencies on flow:obM; on some reference to this tree, this attribute may be expected to depend on no left-side inherited attributes (from forward[flow:oblSwap:z])" {
production oblViaSwap
top::OBL ::= c::OBL
{
  c.obK = top.obM;
  top.obT = "";
  forwards to oblSwap(@c, true);
}
}
production oblCopy
top::OBL ::= z::OBL  b::Boolean
{
  top.obT = "";
  forwards to if b then oblCopy(@z, false) else oblH(@z);
}

-- An application of a dispatch signature applies one of its implementations, so their paths meet:
-- obwH passes its child on to obH, which supplies the child's obK from obK, and obwJ passes it to
-- obJ, which supplies it from obM.
dispatch OBW = OB ::= a::OB;
production obwH implements OBW
top::OB ::= a::OB
{
  top.obT = "";
  top.obS = "";
  top.obR = top;
  forwards to obH(@a);
}
production obwJ implements OBW
top::OB ::= a::OB
{
  top.obT = "";
  top.obS = "";
  top.obR = top;
  forwards to obJ(@a);
}
warnCode "Inherited override equation for flow:obK on child c may exceed a flow type with hidden transitive dependencies on flow:obM; on some reference to this tree, this attribute may be expected to depend on no left-side inherited attributes (from forward[flow:OBW:a]); flow:obM is excluded by the equation for flow:obK in flow:obH, through child a of flow:obwH, forward[flow:obH:w]" {
production obDispatchTwo
top::OB ::= c::OB
{
  c.obK = top.obM;
  top.obT = "";
  top.obS = "";
  top.obR = top;
  local prod::OBW = obwH;
  forwards to prod(@c);
}
}
-- Implementations that agree, and implementations whose own equations for their child differ (see
-- ext).
dispatch OBQ = OB ::= a::OB;
production obqH implements OBQ
top::OB ::= a::OB
{
  top.obT = "";
  top.obS = "";
  top.obR = top;
  forwards to obH(@a);
}
production obqW implements OBQ
top::OB ::= a::OB
{
  top.obT = "";
  top.obS = "";
  top.obR = top;
  forwards to obWrap(@a);
}
dispatch OBC = OB ::= a::OB;
production obcK implements OBC
top::OB ::= a::OB
{
  a.obK = top.obK;
  top.obT = "";
  top.obS = "";
  top.obR = top;
}
production obcM implements OBC
top::OB ::= a::OB
{
  a.obK = top.obM;
  top.obT = "";
  top.obS = "";
  top.obR = top;
}

-- Paths that agree; a path without an equation; parents and the tree's own attributes given alike
-- on every path; nested applications of one production; productions covered through their signature
-- (own equation, forward, a local carrying the attribute); a forward reached through a local; an
-- implementation's own equation within what its dispatch signature allows; a loop whose iterations
-- agree; implementations of a dispatch signature that agree.
production obSelNone
top::OB ::= sel::Boolean  z::OB
{
  top.obT = "";
  top.obS = "";
  top.obR = top;
  forwards to if sel then obH(@z) else obNone(@z);
}
production obR3
top::OB ::= flag::Boolean  x::OB
{
  top.obT = "";
  top.obS = "";
  top.obR = top;
  forwards to if flag then obA(@x) else obH(@x);
}
production obSelParentSame
top::OB ::= sel::Boolean  z::OB
{
  top.obT = "";
  top.obS = "";
  top.obR = top;
  forwards to if sel then obH(@z) else obR3(true, obH(@z));
}
production obSelParentSwap
top::OB ::= sel::Boolean  z::OB
{
  top.obT = "";
  top.obS = "";
  top.obR = top;
  forwards to if sel then obR3(true, obH(@z)) else obH(@z);
}
production obWrap
top::OB ::= w::OB
{
  w.obK = top.obK;
  top.obT = w.obT;
  top.obS = "";
  top.obR = top;
}
production obTwice
top::OB ::= z::OB
{
  top.obT = "";
  top.obS = "";
  top.obR = top;
  forwards to obWrap(obWrap(@z));
}
-- obCarry's local m carries what its only sharing site supplies to a, so its tile flow graph stops
-- at m (see addSharingEqs); the paths go on to obH.
production obCarry
top::OB ::= @a::OB
{
  top.obT = "";
  top.obS = "";
  top.obR = top;
  local m::OB = @a;
  forwards to obH(@m);
}
noWarnCode "hidden transitive" {
production obSame
top::OB ::= c::OB  flag::Boolean
{
  c.obK = top.obK;
  top.obT = "";
  top.obS = "";
  top.obR = top;
  forwards to if flag then obI(@c) else obH(@c);
}
production obInsideNone
top::OB ::= c::OB
{
  c.obK = top.obK;
  top.obT = "";
  top.obS = "";
  top.obR = top;
  forwards to obSelNone(true, @c);
}
production obThroughParentSame
top::OB ::= c::OB
{
  c.obK = top.obK;
  top.obT = "";
  top.obS = "";
  top.obR = top;
  forwards to obSelParentSame(true, @c);
}
production obThroughParentSwap
top::OB ::= c::OB
{
  c.obK = top.obK;
  top.obT = "";
  top.obS = "";
  top.obR = top;
  forwards to obSelParentSwap(true, @c);
}
production obOwnSynSame
top::OB ::= c::OB  flag::Boolean
{
  c.obK = top.obK;
  top.obT = "";
  top.obS = "";
  top.obR = top;
  forwards to if flag then obDA(@c) else obH(@c);
}
production obViaTwice
top::OB ::= c::OB
{
  c.obK = top.obK;
  top.obT = "";
  top.obS = "";
  top.obR = top;
  forwards to obTwice(@c);
}
production obSigOwnSite
top::OB ::= x::OB
{
  x.obK = top.obM;
  top.obT = "";
  top.obS = "";
  top.obR = top;
  forwards to obSigOwn(x);
}
production obSigFwdSite
top::OB ::= x::OB
{
  x.obK = top.obF;
  top.obT = "";
  top.obS = "";
  top.obR = top;
  forwards to obSigFwd(x);
}
production obCarrySite
top::OB ::= x::OB
{
  x.obK = top.obK;
  top.obT = "";
  top.obS = "";
  top.obR = top;
  forwards to obCarry(x);
}
production obLocalFwd
top::OB ::= x::OB
{
  x.obK = top.obF;
  top.obT = "";
  top.obS = "";
  top.obR = top;
  local y::OB = @x;
  forwards to @y;
}
production obByNameOwn
top::OB ::= c::OB
{
  c.obK = top.obF;
  top.obT = "";
  top.obS = "";
  top.obR = top;
  forwards to obeOwn(@c);
}
production oblViaCopy
top::OBL ::= c::OBL
{
  c.obK = top.obK;
  top.obT = "";
  forwards to oblCopy(@c, true);
}
production obDispatchSame
top::OB ::= c::OB
{
  c.obK = top.obK;
  top.obT = "";
  top.obS = "";
  top.obR = top;
  local prod::OBQ = obqH;
  forwards to prod(@c);
}
}

-- Every path from a decoration site is bounded, whether or not it reaches an equation whose inputs
-- are given (see mapApplied).  obR2 supplies obH's obK along exclusive decoration sites, obA from
-- obK and obB from obM, which meet at no attribute: an empty bound, not a vacuous path that never
-- gets obK.
warnCode "Inherited override equation for flow:obK on child c may exceed a flow type with hidden transitive dependencies on flow:obM; on some reference to this tree, this attribute may be expected to depend on no left-side inherited attributes (from forward[flow:obR2:x][flow:obH:w])" {
production obParentDirect
top::OB ::= c::OB  flag::Boolean
{
  c.obK = top.obM;
  top.obT = "";
  top.obS = "";
  top.obR = top;
  forwards to obR2(flag, obH(@c));
}
}
-- The same where obY's equation for c accesses c2's obT, which depends on the obK that obY gives c2
-- from its own: obR2 supplies that obK from obK on one path and from obM on the other.
production obY
top::OB ::= c::OB  c2::OB
{
  c2.obK = top.obK;
  c.obK = c2.obT;
  top.obT = "";
  top.obS = "";
  top.obR = c;
}
warnCode "Inherited override equation for flow:obK on child c may exceed a flow type with hidden transitive dependencies on flow:obM; on some reference to this tree, this attribute may be expected to depend on no left-side inherited attributes (from forward[flow:obR2:x][flow:obY:c])" {
production obParentViaChild
top::OB ::= c::OB  flag::Boolean
{
  c.obK = top.obM;
  top.obT = "";
  top.obS = "";
  top.obR = top;
  forwards to obR2(flag, obY(@c, obLeaf()));
}
}
-- The same where c2 is the local d2, shared at the exclusive decoration sites obY2's c2 and obJ's
-- z: on the path that shares c, d2 is obY2's c2, which gets its obK from obK.
production obY2
top::OB ::= c::OB  c2::OB
{
  c2.obK = top.obK;
  c.obK = c2.obT;
  top.obT = "";
  top.obS = "";
  top.obR = c;
}
warnCode "Inherited override equation for flow:obK on child c may exceed a flow type with hidden transitive dependencies on flow:obM; on some reference to this tree, this attribute may be expected to depend only on flow:obK (from forward[flow:obY2:c])" {
production obOtherTree
top::OB ::= c::OB  flag::Boolean
{
  c.obK = top.obM;
  top.obT = "";
  top.obS = "";
  top.obR = top;
  local d2::OB = obLeaf();
  forwards to if flag then obY2(@c, @d2) else obJ(@d2);
}
}
-- obYS's equation for c accesses obYS's own obS, which depends on nothing; obLeaf, the forward on
-- the other path, has an obS that depends on obM.
production obYS
top::OB ::= c::OB
{
  c.obK = top.obS;
  top.obT = "";
  top.obS = "";
  top.obR = c;
}
warnCode "Inherited override equation for flow:obK on child c may exceed a flow type with hidden transitive dependencies on flow:obM; on some reference to this tree, this attribute may be expected to depend on no left-side inherited attributes (from forward[flow:obYS:c])" {
production obOtherBuilder
top::OB ::= c::OB  flag::Boolean
{
  c.obK = top.obM;
  top.obT = "";
  top.obS = "";
  top.obR = top;
  forwards to if flag then obYS(@c) else obLeaf();
}
}
-- obYC's c2 gets its obK from c's obT, and c its obK from c2's obT.  The override's dependency on
-- obM comes through the construction of l.  Mapping c2.obT back in obBuilt's graph with the
-- override would reach that construction through the override itself, which would then bound itself
-- (see pcOverrideFree and builtSets in mapAppliedWith).
production obMk
top::OB ::= s::String
{
  top.obT = s;
  top.obS = "";
  top.obR = top;
}
production obYC
top::OB ::= c::OB  c2::OB
{
  c2.obK = c.obT;
  c.obK = c2.obT;
  top.obT = "";
  top.obS = "";
  top.obR = c;
}
warnCode "Inherited override equation for flow:obK on child c may exceed a flow type with hidden transitive dependencies on flow:obM; on some reference to this tree, this attribute may be expected to depend on no left-side inherited attributes (from forward[flow:obYC:c])" {
production obBuilt
top::OB ::= c::OB
{
  local l::OB = obMk(top.obM);
  l.obK = "";
  c.obK = l.obT;
  top.obT = "";
  top.obS = "";
  top.obR = top;
  forwards to obYC(@c, obLeaf());
}
}
-- The order in which the paths are found does not matter.
warnCode "Inherited override equation for flow:obK on child c may exceed a flow type with hidden transitive dependencies on flow:obM; on some reference to this tree, this attribute may be expected to depend only on flow:obK (from forward[flow:obSelParentSame:z])" {
production obParentOrderM
top::OB ::= c::OB
{
  c.obK = top.obM;
  top.obT = "";
  top.obS = "";
  top.obR = top;
  forwards to obSelParentSame(true, @c);
}
}
warnCode "Inherited override equation for flow:obK on child c may exceed a flow type with hidden transitive dependencies on flow:obM; on some reference to this tree, this attribute may be expected to depend only on flow:obK (from forward[flow:obSelParentSwap:z])" {
production obParentSwapM
top::OB ::= c::OB
{
  c.obK = top.obM;
  top.obT = "";
  top.obS = "";
  top.obR = top;
  forwards to obSelParentSwap(true, @c);
}
}
-- An input that the parent gets on only some of its paths does not make the path vacuous.
warnCode "Inherited override equation for flow:obK on child c may exceed a flow type with hidden transitive dependencies on flow:obM; on some reference to this tree, this attribute may be expected to depend only on flow:obK (from flow:obSometimesK:local:" {
production obSometimesK
top::OB ::= c::OB  flag::Boolean
{
  c.obK = top.obM;
  top.obT = "";
  top.obS = "";
  top.obR = top;
  local y::OB = obH(@c);
  forwards to if flag then obA(@y) else obNone(@y);
}
}
-- A path is vacuous where the parent never gets an input that every consumer of the hidden equation
-- needs: the completeness checks report every demand of that equation.  obHM's equation needs obK
-- and obM, and y never gets obM.
production obNeverMid
top::OB ::= z::OB
{
  top.obT = "";
  top.obS = "";
  top.obR = top;
  local q::OB = obH(@z);
}
noWarnCode "hidden transitive" {
production obNeverK
top::OB ::= c::OB
{
  c.obK = top.obM;
  top.obT = "";
  top.obS = "";
  top.obR = top;
  local y::OB = obH(@c);
}
production obNeverNested
top::OB ::= c::OB
{
  c.obK = top.obM;
  top.obT = "";
  top.obS = "";
  top.obR = top;
  forwards to obNeverMid(@c);
}
production obOneNever
top::OB ::= c::OB
{
  c.obK = top.obM;
  top.obT = "";
  top.obS = "";
  top.obR = top;
  local y::OB = obHM(@c);
  y.obK = top.obK;
}
}
-- Vacuity is decided after the meet over an applied production's own paths: where obSel's paths
-- meet at no attribute, the bound stays so, although y never gets obM, which obJ's path needs.
warnCode "Inherited override equation for flow:obK on child c may exceed a flow type with hidden transitive dependencies on flow:obF; on some reference to this tree, this attribute may be expected to depend on no left-side inherited attributes" {
production obSelLocalK
top::OB ::= c::OB
{
  c.obK = top.obF;
  top.obT = "";
  top.obS = "";
  top.obR = top;
  local y::OB = obSel(true, @c);
  y.obK = top.obK;
}
}
-- In a loop, vacuity is decided per application: obRec's inner instance gets obK from obKConst, so
-- its path through obH is not vacuous, although y never gets obK...
production obKConst
top::OB ::= w::OB
{
  w.obK = "";
  top.obT = "";
  top.obS = "";
  top.obR = top;
}
production obRec
top::OB ::= flag::Boolean  z::OB
{
  top.obT = "";
  top.obS = "";
  top.obR = top;
  forwards to if flag then obH(@z) else obKConst(obRec(true, @z));
}
warnCode "Inherited override equation for flow:obK on child c may exceed a flow type with hidden transitive dependencies on flow:obM; on some reference to this tree, this attribute may be expected to depend on no left-side inherited attributes" {
production obRecSite
top::OB ::= c::OB
{
  c.obK = top.obM;
  top.obT = "";
  top.obS = "";
  top.obR = top;
  local y::OB = obRec(false, @c);
}
}
-- ...while obRecNone's inner instance, under obNone, never gets obK either.
production obRecNone
top::OB ::= flag::Boolean  z::OB
{
  top.obT = "";
  top.obS = "";
  top.obR = top;
  forwards to if flag then obH(@z) else obNone(obRecNone(true, @z));
}
noWarnCode "hidden transitive" {
production obRecNoneSite
top::OB ::= c::OB
{
  c.obK = top.obM;
  top.obT = "";
  top.obS = "";
  top.obR = top;
  local y::OB = obRecNone(false, @c);
}
}

-- A scrutinee is not a decoration site, so a tree cannot be shared there, and no path of decoration
-- sites reaches an anonymous scrutinee.
wrongFlowCode "Cannot share a tree here" {
production obMatchShared
top::OB ::= c::OB
{
  top.obT = case @c of obH(w) -> "h" | _ -> "" end;
  top.obS = "";
  top.obR = top;
}
}

-- An application of a dispatch signature at a parent that never gets what its implementations'
-- equations need: the path is vacuous (see mapApplied).
noWarnCode "hidden transitive" {
production obNeverDispatch
top::OB ::= c::OB
{
  c.obK = top.obM;
  top.obT = "";
  top.obS = "";
  top.obR = top;
  local prod::OBD = obdImpl;
  local y::OB = prod(@c);
}
}

-- The witness after "(from ...)" says why each attribute in excess is excluded.  Where no single
-- path excludes an attribute, the witness names where the paths meet: obSel's paths give obK and
-- obM, each from obF here, but meet at no attribute before they are mapped back.
warnCode "Inherited override equation for flow:obK on child c may exceed a flow type with hidden transitive dependencies on flow:obF; on some reference to this tree, this attribute may be expected to depend on no left-side inherited attributes (from forward[flow:obSel:z]); flow:obF is excluded where the paths from child z of flow:obSel meet" {
production obSelBothF
top::OB ::= c::OB
{
  c.obK = top.obF;
  top.obT = "";
  top.obS = "";
  top.obR = top;
  forward.obK = top.obF;
  forward.obM = top.obF;
  forwards to obSel(true, @c);
}
}
-- ...or where the implementations of a dispatch signature meet.
dispatch OBD2 = OB ::= a::OB;
production obd2K implements OBD2
top::OB ::= a::OB
{
  top.obR = top;
  forwards to obH(@a);
}
production obd2M implements OBD2
top::OB ::= a::OB
{
  top.obR = top;
  forwards to obJ(@a);
}
warnCode "Inherited override equation for flow:obK on child c may exceed a flow type with hidden transitive dependencies on flow:obF; on some reference to this tree, this attribute may be expected to depend on no left-side inherited attributes (from forward[flow:OBD2:a]); flow:obF is excluded where the implementations of flow:OBD2 meet" {
production obDispatchBothF
top::OB ::= c::OB
{
  c.obK = top.obF;
  top.obT = "";
  top.obS = "";
  top.obR = top;
  forward.obK = top.obF;
  forward.obM = top.obF;
  local prod::OBD2 = obd2K;
  forwards to prod(@c);
}
}
-- One clause per distinct witness.
warnCode "; flow:obK is excluded by the equation for flow:obK in flow:obJ, through forward[flow:obJ:z]; flow:obM is excluded by the equation for flow:obK in flow:obH, through forward[flow:obH:w]" {
production obTwoPathsKM
top::OB ::= c::OB
{
  c.obK = top.obK ++ top.obM;
  top.obT = "";
  top.obS = "";
  top.obR = top;
  forwards to obSel(true, @c);
}
}

-- An override in a function: the function's own graph stands in for the override-free graph (see
-- boundCtx), and a function has no LHS inherited attributes for the override to depend on.
noWarnCode "hidden transitive" {
function obFun
String ::= c::OB  d::OB
{
  c.obK = d.obT;
  d.obK = "";
  local y::OB = obH(@c);
  y.obK = "";
  return y.obT;
}
}

-- An override on a tree that the applied production passes on to an application of its own.  The
-- access of the attribute there sees the override, so a production that constructs the overriding
-- production must supply what the override depends on (see addDecSiteTreeEqs).
inherited attribute ondI::String;
inherited attribute ondJ::String;
inherited attribute ondK::String;
inherited attribute ondEnv::String;
synthesized attribute ondS::String;
synthesized attribute ondT::String;
synthesized attribute ondTy::String;
nonterminal OND with ondI, ondJ, ondK, ondEnv, ondS, ondT, ondTy;
flowtype OND = decorate {}, forward {}, ondS {ondK}, ondT {ondJ}, ondTy {ondEnv};

production ondLeaf
top::OND ::=
{
  top.ondS = "";
  top.ondT = "";
  top.ondTy = top.ondEnv;
}
-- ondInner accesses z.ondI, whose equation depends on ondJ through y's flow type.
production ondInner
top::OND ::= z::OND  y::OND  b::OND
{
  z.ondI = y.ondS;
  y.ondK = top.ondJ;
  b.ondEnv = z.ondI;
  top.ondS = "";
  top.ondT = b.ondTy;
  top.ondTy = "";
}
production ondPass
top::OND ::= x::OND  y::OND  b::OND
{
  forwards to ondInner(@x, @y, @b);
}
-- The override is within the bound, as ondInner's equation for z.ondI depends on ondJ.
production ondShare
top::OND ::= c::OND  b::OND
{
  c.ondI = top.ondJ;
  forwards to ondPass(@c, ondLeaf(), @b);
}
-- Through ondShare's graph, ondLeaf's ondS depends on nothing, but at run time ondInner's access of
-- z.ondI sees ondShare's override, which demands ondJ on l.
warnCode "Access of synthesized attribute ondT on l requires missing inherited attribute(s) flow:ondJ to be supplied to local" {
production ondLocal
top::OND ::=
{
  local l::OND = ondShare(ondLeaf(), ondLeaf());
  l.ondEnv = "";
  top.ondS = "";
  top.ondT = l.ondT;
  top.ondTy = "";
}
}
