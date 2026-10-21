grammar flow:transExt;

-- This extension's default production for DIExpr has the name of flow's (see defaultProdName), so both are in DIExpr's
-- default graph: the inferred flow type of diX has what diX's default needs through its anonymous scrutinee on diS.
synthesized attribute diX::String occurs on DIExpr;
aspect default production
top::DIExpr ::=
{ top.diX = case top.diS of "" -> "" | _ -> "x" end; }

synthesized attribute diXOut::String;
nonterminal DIXRoot with diXOut;
warnCode "Access of synthesized attribute diX on c requires missing inherited attribute(s) flow:diK to be supplied" {
production diXUse
top::DIXRoot ::= c::DIExpr
{ top.diXOut = c.diX; }
}
