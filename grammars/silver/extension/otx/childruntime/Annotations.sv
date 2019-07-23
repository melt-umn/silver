
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

abstract production linkOtxInfo
top::OtxInfo ::= origin::Maybe<Pair<OtxLink [OtxRule]>>
                 redex::Maybe<Pair<OtxLink [OtxRule]>>
                 isContractum::Boolean
{
	
}

aspect default production
top::OtxRule ::=
{
	top.pp = "<<" ++ hackUnparse(top) ++ ">>";
}

abstract production debugStringRule
top::OtxRule ::= string::String
{
	
}

abstract production builtinNoOriginsConstructorRule
top::OtxRule ::= exprLoc::String
{
	
}