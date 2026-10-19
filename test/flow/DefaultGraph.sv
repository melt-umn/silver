grammar flow;

-- The flow-type checks of the equations of a default production, and flow type inference, use the
-- default graph of its nonterminal, which has the flow defs of the equations' expressions and the
-- flow types of the left-hand side.

-- A default contribution is appended to the collection of every production with its own equation
-- for that collection, dgJ included: at dgJ, a match on the left-hand side follows the forward, and
-- dgJ's forward equation needs dgK2.
inherited attribute dgK2::String;
synthesized attribute dgC::[String] with ++;
nonterminal DGExpr with dgK2, dgC;
flowtype DGExpr = decorate {}, forward {dgK2}, dgC {};

production dgLeaf
top::DGExpr ::=
{ top.dgC := []; }

production dgJ
top::DGExpr ::=
{
  top.dgC := [];
  forwards to if top.dgK2 == "" then dgLeaf() else dgLeaf();
}

warnCode "Synthesized equation dgC exceeds flow type with dependencies on flow:dgK2" {
aspect default production
top::DGExpr ::=
{ top.dgC <- [case top of dgLeaf() -> "leaf" | _ -> "" end]; }
}

-- dhD's default runs at dhH also where dhH is dhI's forward, and dhI gives dhH's dhK1 from dhI's
-- dhK2.  In the default graph, a match on the left-hand side depends on the forward flow type,
-- which has dhK2 (see dgC).  dhH's tile flow graph has what the default needs at dhH (see
-- defaultStitchPoint), so the implicit copy of dhD at dhI needs dhK2.
inherited attribute dhK1::String;
inherited attribute dhK2::String;
synthesized attribute dhT::String;
synthesized attribute dhD::String;
nonterminal DHExpr with dhK1, dhK2, dhT, dhD;
flowtype DHExpr = decorate {}, forward {dhK2}, dhT {dhK1}, dhD {dhK1};

production dhLeaf
top::DHExpr ::=
{ top.dhT = top.dhK1; }

production dhH
top::DHExpr ::= w::DHExpr
{ w.dhK1 = top.dhK1; top.dhT = ""; }

warnCode "flow:dhD (due to forwarding) would exceed the attribute's flow type with dependencies on flow:dhK2" {
production dhI
top::DHExpr ::=
{
  forward.dhK1 = top.dhK2;
  forwards to dhH(dhLeaf());
}
}

warnCode "Synthesized equation dhD exceeds flow type with dependencies on flow:dhK2" {
aspect default production
top::DHExpr ::=
{ top.dhD = case top of dhH(b) -> b.dhT | _ -> "" end; }
}

-- The other synthesized attributes of the left-hand side have their flow types in the default
-- graph.
inherited attribute dsK::String;
synthesized attribute dsT::String;
synthesized attribute dsD::String;
synthesized attribute dsB::[String] with ++;
synthesized attribute dsC::[String] with ++;
nonterminal DSExpr with dsK, dsT, dsD, dsB, dsC;
flowtype DSExpr = decorate {}, dsT {dsK}, dsD {}, dsB {}, dsC {};

production dsLeaf
top::DSExpr ::=
{ top.dsT = top.dsK; top.dsC := []; }

warnCode "Synthesized equation dsD exceeds flow type with dependencies on flow:dsK" {
aspect default production
top::DSExpr ::=
{ top.dsD = top.dsT; }
}
warnCode "Synthesized equation dsB exceeds flow type with dependencies on flow:dsK" {
aspect default production
top::DSExpr ::=
{ top.dsB := [top.dsT]; }
}
warnCode "Synthesized equation dsC exceeds flow type with dependencies on flow:dsK" {
aspect default production
top::DSExpr ::=
{ top.dsC <- [top.dsT]; }
}

-- An inferred flow type has what a default equation's expression needs, here through an anonymous
-- scrutinee.
inherited attribute diK::String;
synthesized attribute diS::String;
synthesized attribute diA::String;
nonterminal DIExpr with diK, diS, diA;

production diLeaf
top::DIExpr ::=
{ top.diS = top.diK; }

aspect default production
top::DIExpr ::=
{ top.diA = case top.diS of "" -> "empty" | _ -> "nonempty" end; }

synthesized attribute diOut::String;
nonterminal DIRoot with diOut;
warnCode "Access of synthesized attribute diA on c requires missing inherited attribute(s) flow:diK to be supplied" {
production diUse
top::DIRoot ::= c::DIExpr
{ top.diOut = c.diA; }
}

-- An inferred flow type also has what a default contribution needs, which is appended to deLeaf's
-- own collection.
inherited attribute deK::String;
synthesized attribute deE::[String] with ++;
nonterminal DEColl with deK, deE;

production deLeaf
top::DEColl ::=
{ top.deE := []; }

noWarnCode "exceeds flow type" {
aspect default production
top::DEColl ::=
{ top.deE <- [top.deK]; }
}

synthesized attribute deOut::String;
nonterminal DERoot with deOut;
warnCode "Access of synthesized attribute deE on c requires missing inherited attribute(s) flow:deK to be supplied" {
production deUse
top::DERoot ::= c::DEColl
{ top.deOut = implode(",", c.deE); }
}
