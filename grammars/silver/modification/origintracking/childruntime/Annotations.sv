
annotation origininfo :: OriginInfo;

nonterminal OriginInfo;
nonterminal OriginNote;
nonterminal OriginLink;

synthesized attribute pp :: String occurs on OriginNote;
synthesized attribute nextOrigin :: Maybe<OriginInfo> occurs on OriginLink;

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
top::OriginNote ::= attributeName::String sourceLocation::Location
{
	
}

function getOriginLink
Maybe<OriginLink> ::= l::OriginInfo
{
	return case l of
		| originOriginInfo(o, _, _) -> just(o)
		| originAndRedexOriginInfo(o, _, _, _, _) -> just(o)
		| _ -> nothing()
	end;
}

function getOriginChain
[OriginInfo] ::= l::OriginInfo
{
	return case getOriginLink(l) of
		| just(o) -> case o.nextOrigin of
			| just(n) -> n :: getOriginChain(n)
			| nothing() -> []
		end
		| nothing() -> []
	end;
}