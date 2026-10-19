grammar flow;

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
