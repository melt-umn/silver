
synthesized attribute canProduceEmptyString :: Boolean;
nonterminal TreesitterNonterminalModifiers with canProduceEmptyString;
nonterminal TreesitterNonterminalModifier with canProduceEmptyString;


abstract production nilTSNonterminalMod
top::TreesitterNonterminalModifiers ::= 
{
  top.canProduceEmptyString = false;
}

abstract production consTSNonterminalMod
top::TreesitterNonterminalModifiers ::= h::TreesitterNonterminalModifier t::TreesitterNonterminalModifiers
{
  top.canProduceEmptyString = h.canProduceEmptyString || t.canProduceEmptyString;
}

aspect default production
top::TreesitterNonterminalModifier ::=
{
  top.canProduceEmptyString = false;
}

abstract production ntProducesEmptyStringMod
top::TreesitterNonterminalModifier ::=
{
  top.canProduceEmptyString = true;
}
