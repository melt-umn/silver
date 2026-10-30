grammar flow;

-- A default equation runs at a production that does not forward and has no equation of its own, and
-- a default contribution to a collection is assumed to apply at every production (see
-- defaultDepsAt).  An access through the tile flow graph of a production where a default applies
-- depends on what the default needs there, also through pattern variables, anonymous scrutinees and
-- forwards (see defaultStitchPoint).

synthesized attribute dftOut::String;
nonterminal DftRoot with dftOut;

-- dftI gives its forward dftH the attribute dftK1 from its own dftK2.  dftH has no equation for
-- dftG or dftM, so the defaults run at dftH, where the match binds dftH's own child.
inherited attribute dftK1::String;
inherited attribute dftK2::String;
synthesized attribute dftT::String;
synthesized attribute dftG::String;
synthesized attribute dftM::[String] with ++;
nonterminal DftExpr with dftK1, dftK2, dftT, dftG, dftM;
flowtype DftExpr = decorate {}, forward {dftK2}, dftT {dftK1}, dftG {dftK1, dftK2}, dftM {dftK1, dftK2};

production dftLeaf
top::DftExpr ::=
{ top.dftT = top.dftK1; top.dftG = ""; }
production dftH
top::DftExpr ::= w::DftExpr
{ w.dftK1 = top.dftK1; top.dftT = ""; }
production dftI
top::DftExpr ::=
{
  forward.dftK1 = top.dftK2;
  forwards to dftH(dftLeaf());
}
-- dftP forwards and has a collection of its own, so the default contribution is appended to it, and
-- the match goes through dftP's forwards to dftH.
production dftP
top::DftExpr ::=
{
  top.dftM := [];
  forwards to dftI();
}
aspect default production
top::DftExpr ::=
{
  top.dftG = case top of dftH(b) -> b.dftT | _ -> "" end;
  top.dftM := [];
  top.dftM <- [case top of dftH(b) -> b.dftT | _ -> "" end];
}

warnCode "Access of synthesized attribute dftG on l requires missing inherited attribute(s) flow:dftK2" {
production dftUseG
top::DftRoot ::=
{
  local l::DftExpr = dftI();
  l.dftK1 = "";
  top.dftOut = l.dftG;
}
}
-- Applied directly, dftH is matched itself, but the match is assumed to follow forwards (see
-- matchThroughForwards), so the default needs dftK2 of l as well.
warnCode "Access of synthesized attribute dftG on l requires missing inherited attribute(s) flow:dftK2" {
production dftUseH
top::DftRoot ::=
{
  local l::DftExpr = dftH(dftLeaf());
  l.dftK1 = "";
  top.dftOut = l.dftG;
}
}
warnCode "Access of synthesized attribute dftM on l requires missing inherited attribute(s) flow:dftK2" {
production dftUseM
top::DftRoot ::=
{
  local l::DftExpr = dftP();
  l.dftK1 = "";
  top.dftOut = implode(",", l.dftM);
}
}

-- dftCLeaf and dftCOwn have collections of their own, to which the default contribution is
-- appended; dftCNone has no equation, so it gets the default's whole value.  dftCFwd has no
-- collection of its own and gets its forward's at run time, but the default contribution is assumed
-- to apply there too (see defaultDepsAt).
inherited attribute dftCK::String;
synthesized attribute dftE::[String] with ++;
nonterminal DftColl with dftCK, dftE;
flowtype DftColl = decorate {}, forward {}, dftE {dftCK};

production dftCLeaf
top::DftColl ::=
{ top.dftE := []; }
production dftCNone
top::DftColl ::=
{}
production dftCOwn
top::DftColl ::=
{
  top.dftE := [];
  forward.dftCK = "";
  forwards to dftCLeaf();
}
production dftCFwd
top::DftColl ::=
{
  forward.dftCK = "";
  forwards to dftCLeaf();
}
aspect default production
top::DftColl ::=
{
  top.dftE := [];
  top.dftE <- [top.dftCK];
}

warnCode "Access of synthesized attribute dftE on l requires missing inherited attribute(s) flow:dftCK" {
production dftUseCLeaf
top::DftRoot ::=
{
  local l::DftColl = dftCLeaf();
  top.dftOut = implode(",", l.dftE);
}
}
warnCode "Access of synthesized attribute dftE on l requires missing inherited attribute(s) flow:dftCK" {
production dftUseCNone
top::DftRoot ::=
{
  local l::DftColl = dftCNone();
  top.dftOut = implode(",", l.dftE);
}
}
warnCode "Access of synthesized attribute dftE on l requires missing inherited attribute(s) flow:dftCK" {
production dftUseCOwn
top::DftRoot ::=
{
  local l::DftColl = dftCOwn();
  top.dftOut = implode(",", l.dftE);
}
}
warnCode "Access of synthesized attribute dftE on l requires missing inherited attribute(s) flow:dftCK" {
production dftUseCFwd
top::DftRoot ::=
{
  local l::DftColl = dftCFwd();
  top.dftOut = implode(",", l.dftE);
}
}

-- An inferred flow type: the default matches on top.dftS, a scrutinee with no vertex of its own
-- (see matchPrimitiveReal).
inherited attribute dftAK::String;
synthesized attribute dftS::String;
synthesized attribute dftA::String;
nonterminal DftInf with dftAK, dftS, dftA;
flowtype DftInf = decorate {}, forward {}, dftS {dftAK};

production dftALeaf
top::DftInf ::=
{ top.dftS = top.dftAK; }
aspect default production
top::DftInf ::=
{ top.dftA = case top.dftS of "" -> "empty" | _ -> "nonempty" end; }

warnCode "Access of synthesized attribute dftA on l requires missing inherited attribute(s) flow:dftAK" {
production dftUseA
top::DftRoot ::=
{
  local l::DftInf = dftALeaf();
  top.dftOut = l.dftA;
}
}

-- dftVNode uses the default for dftVS in its own equation for dftVU, whose flow type is inferred:
-- dftVU needs what the default needs at dftVNode, through the match on dftVNode's own child.
inherited attribute dftVK::String;
synthesized attribute dftVX::String;
synthesized attribute dftVS::String;
synthesized attribute dftVU::String;
nonterminal DftVia with dftVK, dftVX, dftVS, dftVU;
flowtype DftVia = decorate {}, forward {}, dftVX {dftVK};

production dftVLeaf
top::DftVia ::=
{ top.dftVX = top.dftVK; top.dftVU = ""; }
production dftVNode
top::DftVia ::= c::DftVia
{
  c.dftVK = top.dftVK;
  top.dftVX = "";
  top.dftVU = top.dftVS;
}
aspect default production
top::DftVia ::=
{ top.dftVS = case top of dftVNode(b) -> b.dftVX | _ -> "" end; }

warnCode "Access of synthesized attribute dftVU on x requires missing inherited attribute(s) flow:dftVK" {
production dftUseVU
top::DftRoot ::= x::DftVia
{ top.dftOut = x.dftVU; }
}

-- Default contributions from flow:ext, which flow (the grammar of these nonterminals and
-- attributes) does not export, may not affect the flow types of dftZ and dftY (see
-- ext/DefaultTiles.sv).
inherited attribute dftZK::String;
synthesized attribute dftZ::[String] with ++;
nonterminal DftSusp with dftZK, dftZ;

production dftZLeaf
top::DftSusp ::=
{ top.dftZ := []; }

inherited attribute dftYK::String;
synthesized attribute dftY::[String] with ++;
nonterminal DftSuspD with dftYK, dftY;
flowtype DftSuspD = decorate {}, forward {}, dftY {dftYK};

production dftYLeaf
top::DftSuspD ::=
{ top.dftY := []; }

warnCode "Access of synthesized attribute dftY on l requires missing inherited attribute(s) flow:dftYK" {
production dftUseY
top::DftRoot ::=
{
  local l::DftSuspD = dftYLeaf();
  top.dftOut = implode(",", l.dftY);
}
}
