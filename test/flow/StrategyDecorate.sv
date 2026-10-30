grammar flow;

-- A strategy attribute rule is checked for missing inherited attributes in the productions that the strategy
-- is propagated into, where the equations of an anonymous decoration in the rule can be found.

inherited attribute sdI::String;
synthesized attribute sdS::String;
nonterminal SdA with sdI, sdS;
production sdA
top::SdA ::=
{ top.sdS = top.sdI; }

noWarnCode "requires missing inherited attribute" {
partial strategy attribute sdGood = rule on SdA of | x -> if decorate ^x with { sdI = "z"; }.sdS == "" then ^x else ^x end;
attribute sdGood occurs on SdA;
propagate sdGood on SdA;
}

warnCode "requires missing inherited attribute(s) flow:sdI to be supplied to anonymous decoration site" {
partial strategy attribute sdBad = rule on SdA of | x -> if decorate ^x with {}.sdS == "" then ^x else ^x end;
attribute sdBad occurs on SdA;
propagate sdBad on SdA;
}
