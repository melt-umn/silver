
annotation origininfo :: OriginInfo;

nonterminal OriginInfo;
nonterminal OriginNote;
nonterminal OriginLink;

synthesized attribute pp :: String occurs on OriginNote;

abstract production otherOriginInfo
top::OriginInfo ::= source::String notes::[OriginNote]
{
	
}

abstract production originOriginInfo
top::OriginInfo ::= origin :: OriginLink
				 originNotes :: [OriginNote]
				 newlyConstructed :: Boolean
{
	
}

abstract production originAndRedexOriginInfo
top::OriginInfo ::= origin :: OriginLink
				 originNotes :: [OriginNote]
				 redex :: OriginLink
				 redexNotes :: [OriginNote]
				 newlyConstructed :: Boolean
{
	
}

aspect default production
top::OriginNote ::=
{
	top.pp = "<<" ++ hackUnparse(top) ++ ">>";
}

abstract production originDbgNote
top::OriginNote ::= string::String
{
	
}

abstract production dbgNote
top::OriginNote ::= string::String
{
	
}

abstract production ruleLocNote
top::OriginNote ::= attributeName::String lhsDesc::String sourceLocation::Location
{
	
}