grammar flow;

-- Matching follows forwards, so a match depends on what the scrutinee's forward, and that forward's
-- forwards, need.

inherited attribute mfK2::String;
synthesized attribute mfS::String;
nonterminal MFExpr with mfK2, mfS;
flowtype MFExpr = decorate {}, forward {mfK2}, mfS {};

production mfH
top::MFExpr ::=
{ top.mfS = ""; }

-- The forward equation accesses mfK2.
production mfJ
top::MFExpr ::=
{ top.mfS = ""; forwards to if top.mfK2 == "" then mfH() else mfH(); }

production mfP
top::MFExpr ::=
{ top.mfS = ""; forwards to mfJ(); }

-- A match on the forward walks on to mfP's forward, mfJ, whose forward equation needs mfK2.
warnCode "Synthesized equation mfS exceeds flow type with dependencies on flow:mfK2" {
production mfMatchForward
top::MFExpr ::=
{
  top.mfS = case forward of mfH() -> "h" | _ -> "" end;
  forwards to mfP();
}
}

-- A match on a local must be supplied what the local's forwards need.
warnCode "Matching requires missing inherited attribute(s) flow:mfK2 to be supplied to local" {
production mfMatchLocal
top::MFExpr ::=
{
  local l::MFExpr = mfJ();
  top.mfS = case l of mfH() -> "h" | _ -> "" end;
}
}
noWarnCode "requires missing inherited attribute" {
production mfMatchLocalSupplied
top::MFExpr ::=
{
  local l::MFExpr = mfJ();
  l.mfK2 = "";
  top.mfS = case l of mfH() -> "h" | _ -> "" end;
}
}

-- A production forwarded through on the way to the matched production may supply that production's
-- inherited attributes from its own forward inherited equations, which depend on at most the
-- forward flow type (see matchThroughForwards).
inherited attribute mgK1::String;
inherited attribute mgK2::String;
synthesized attribute mgT::String;
synthesized attribute mgS::String;
nonterminal MGExpr with mgK1, mgK2, mgT, mgS;
flowtype MGExpr = decorate {}, forward {mgK2}, mgT {mgK1}, mgS {mgK1};

production mgLeaf
top::MGExpr ::=
{ top.mgT = top.mgK1; top.mgS = ""; }

production mgH
top::MGExpr ::= w::MGExpr
{ w.mgK1 = top.mgK1; top.mgT = ""; top.mgS = ""; }

production mgI
top::MGExpr ::=
{
  forward.mgK1 = top.mgK2;
  forwards to mgH(mgLeaf());
}

-- b's mgK1 is mgH's, which mgI supplies from mgK2.
warnCode "Synthesized equation mgS exceeds flow type with dependencies on flow:mgK2" {
production mgMatchForward
top::MGExpr ::=
{
  top.mgS = case forward of mgH(b) -> b.mgT | _ -> "" end;
  forwards to mgI();
}
}

-- The same through a local, and a local that is not supplied mgK2.
warnCode "Synthesized equation mgS exceeds flow type with dependencies on flow:mgK2" {
production mgMatchLocal
top::MGExpr ::=
{
  local l::MGExpr = mgI();
  l.mgK1 = top.mgK1;
  l.mgK2 = top.mgK2;
  top.mgT = "";
  top.mgS = case l of mgH(b) -> b.mgT | _ -> "" end;
}
}
warnCode "Access of synthesized attribute mgT on b requires missing inherited attribute(s) flow:mgK1 to be supplied to dependency flow:mgK2" {
production mgMatchLocalMissing
top::MGExpr ::=
{
  local l::MGExpr = mgI();
  l.mgK1 = top.mgK1;
  top.mgT = "";
  top.mgS = case l of mgH(b) -> b.mgT | _ -> "" end;
}
}

-- Matching the production applied at the forward follows no forward, but a match is assumed to
-- follow forwards wherever the matched production is in scope (see matchThroughForwards).
warnCode "Synthesized equation mgS exceeds flow type with dependencies on flow:mgK2" {
production mgMatchApplied
top::MGExpr ::=
{
  top.mgS = case forward of mgH(b) -> b.mgT | _ -> "" end;
  forwards to mgH(mgLeaf());
}
}

-- Only the attributes of a pattern variable that are used depend on the forward flow type.
noWarnCode "exceeds flow type" {
production mgMatchForwardUnused
top::MGExpr ::=
{
  top.mgS = case forward of mgH(b) -> "" | _ -> "" end;
  forwards to mgI();
}
}

-- mgConst gives its child a constant, which needs nothing of the tree matched, through forwards or
-- not.
production mgConst
top::MGExpr ::= w::MGExpr
{ w.mgK1 = ""; top.mgT = ""; top.mgS = ""; }
production mgToConst
top::MGExpr ::=
{
  forward.mgK1 = top.mgK2;
  forwards to mgConst(mgLeaf());
}
noWarnCode "requires missing inherited attribute" {
production mgMatchConst
top::MGExpr ::=
{
  local l::MGExpr = mgToConst();
  l.mgK1 = top.mgK1;
  top.mgT = "";
  top.mgS = case l of mgConst(b) -> b.mgT | _ -> "" end;
}
}

-- In a production other than mgH that forwards, a match on the LHS is a match on the forward.
warnCode "Synthesized equation mgS exceeds flow type with dependencies on flow:mgK2" {
production mgMatchTop
top::MGExpr ::=
{
  forward.mgK1 = top.mgK2;
  top.mgS = case top of mgH(b) -> b.mgT | _ -> "" end;
  forwards to mgH(mgLeaf());
}
}
-- A match follows the locals and forward production attributes shared at the tree matched...
warnCode "Synthesized equation mgS exceeds flow type with dependencies on flow:mgK2" {
production mgMatchSharedLocal
top::MGExpr ::=
{
  local q::MGExpr = mgI();
  top.mgS = case forward of mgH(b) -> b.mgT | _ -> "" end;
  forwards to @q;
}
}
warnCode "Synthesized equation mgS exceeds flow type with dependencies on flow:mgK2" {
production mgMatchFwdAttr
top::MGExpr ::=
{
  forward x = mgI();
  top.mgS = case forward of mgH(b) -> b.mgT | _ -> "" end;
  forwards to @x;
}
}
-- ...and applies to any tree matched, such as a decorate expression...
warnCode "Synthesized equation mgS exceeds flow type with dependencies on flow:mgK2" {
production mgMatchDecorate
top::MGExpr ::=
{
  top.mgT = "";
  top.mgS = case decorate mgI() with {mgK1 = top.mgK1; mgK2 = top.mgK2;} of mgH(b) -> b.mgT | _ -> "" end;
}
}
-- ...or a translation attribute of the LHS.  Only the graph shows the dependency on mgWt.mgK2, as
-- the inherited attributes on the LHS's translation attributes count as supplied.
translation attribute mgWt::MGExpr;
synthesized attribute mgWS::String;
nonterminal MGWrap with mgWt, mgWS;
flowtype MGWrap = mgWS {mgWt.mgK1};
warnCode "Synthesized equation mgWS exceeds flow type with dependencies on flow:mgWt.flow:mgK2" {
production mgMatchTrans
top::MGWrap ::=
{
  top.mgWt = mgI();
  top.mgWS = case top.mgWt of mgH(b) -> b.mgT | _ -> "" end;
}
}
