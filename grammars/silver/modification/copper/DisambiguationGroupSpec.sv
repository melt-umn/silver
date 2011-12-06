grammar silver:modification:copper;

nonterminal DisambiguationGroupSpec with groupName,groupMembers,actionCode;

synthesized attribute groupName :: String;
synthesized attribute groupMembers :: [String];

function disambiguationGroupSpec
Decorated DisambiguationGroupSpec ::= members::[String] acode::String {
  return decorate i_disambiguationGroupSpec(members, acode) with {};
}

abstract production i_disambiguationGroupSpec
top::DisambiguationGroupSpec ::= members::[String] acode::String
{
  -- this genInt is okay because it literally doesn't matter at all.
  -- Copper demands a name, but it's basically unused.
  top.groupName = "D" ++ toString(genInt());
  top.groupMembers = members;
  top.actionCode = acode;
}

