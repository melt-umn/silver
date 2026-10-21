grammar flow;

-- A pattern variable bound by matching on the forward may be a tree that this production shared
-- into the forward: the tree keeps what this production supplied to it, not what the matched
-- production's equations supply.

inherited attribute pvK1::String;
inherited attribute pvK2::String;
synthesized attribute pvT::String;
synthesized attribute pvS::String;
nonterminal PVExpr with pvK1, pvK2, pvT, pvS;
flowtype PVExpr = decorate {}, forward {}, pvT {pvK1}, pvS {pvK1};

production pvLeaf
top::PVExpr ::=
{ top.pvT = top.pvK1; top.pvS = ""; }

production pvH
top::PVExpr ::= w::PVExpr
{ w.pvK1 = top.pvK1; top.pvT = ""; top.pvS = ""; }

production pvI
top::PVExpr ::= z::PVExpr
{ forwards to pvH(@z); }

-- b is c, shared into the forward: b.pvT needs what was supplied to c.
production pvQ
top::PVExpr ::= c::PVExpr
{
  top.pvS = case forward of pvH(b) -> b.pvT | _ -> "" end;
  forwards to pvI(@c);
}

-- pvQ's child c gets pvK1 from this production's override on x, which needs pvK2, while pvQ's
-- consumers assume pvK1 (see pathBound).  The override's warning suppresses the warning that the
-- implicit copy of pvS exceeds its flow type.
warnCode "Inherited override equation for flow:pvK1 on child x may exceed a flow type with hidden transitive dependencies on flow:pvK2" {
production pvSP
top::PVExpr ::= x::PVExpr
{
  x.pvK1 = top.pvK2;
  forwards to pvQ(@x);
}
}
