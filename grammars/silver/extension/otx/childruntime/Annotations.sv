
annotation otxinfo :: OtxInfo;

nonterminal OtxInfo;
nonterminal OtxRule;
nonterminal OtxLink;

synthesized attribute pp :: String occurs on OtxRule;

abstract production locOtxInfo
top::OtxInfo ::= loc::Location rules::[OtxRule]
{
	
}

abstract production otherOtxInfo
top::OtxInfo ::= source::String rules::[OtxRule]
{
	
}

abstract production originOtxInfo
top::OtxInfo ::= origin :: OtxLink
				 originRules :: [OtxRule]
				 newlyConstructed :: Boolean
{
	
}

abstract production originAndRedexOtxInfo
top::OtxInfo ::= origin :: OtxLink
				 originRules :: [OtxRule]
				 redex :: OtxLink
				 redexrules :: [OtxRule]
				 newlyConstructed :: Boolean
{
	
}

aspect default production
top::OtxRule ::=
{
	top.pp = "<<" ++ hackUnparse(top) ++ ">>";
}

abstract production noteRule
top::OtxRule ::= string::String
{
	
}

abstract production builtinNoOriginsConstructorRule
top::OtxRule ::= exprLoc::String
{
	
}