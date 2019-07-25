
annotation otxinfo :: OtxInfo;

nonterminal OtxInfo;
nonterminal OtxNote;
nonterminal OtxLink;

synthesized attribute pp :: String occurs on OtxNote;

abstract production otherOtxInfo
top::OtxInfo ::= source::String notes::[OtxNote]
{
	
}

abstract production originOtxInfo
top::OtxInfo ::= origin :: OtxLink
				 originNotes :: [OtxNote]
				 newlyConstructed :: Boolean
{
	
}

abstract production originAndRedexOtxInfo
top::OtxInfo ::= origin :: OtxLink
				 originNotes :: [OtxNote]
				 redex :: OtxLink
				 redexNotes :: [OtxNote]
				 newlyConstructed :: Boolean
{
	
}

aspect default production
top::OtxNote ::=
{
	top.pp = "<<" ++ hackUnparse(top) ++ ">>";
}

abstract production otxDbgNote
top::OtxNote ::= string::String
{
	
}

abstract production dbgNote
top::OtxNote ::= string::String
{
	
}

abstract production ruleLocNote
top::OtxNote ::= exprLoc::String
{
	
}