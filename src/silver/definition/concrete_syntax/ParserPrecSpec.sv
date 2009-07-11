--grammar silver:definition:concrete_syntax;

nonterminal ParserPrecSpec with parserPrecedence, parserAssociation, precedenceName;

synthesized attribute precedenceName :: String;

function precSpec
Decorated ParserPrecSpec ::= n::String prec::Integer assoc::String
{
  return decorate i_precSpec(n, prec, assoc) with {};
}

abstract production i_precSpec
top::ParserPrecSpec ::= n::String prec::Integer assoc::String
{
  top.precedenceName = n;
  top.parserPrecedence = prec;
  top.parserAssociation = assoc;
}

function getParserPrecSpecTerminals
[Decorated ParserPrecSpec] ::= l::[Decorated TerminalSpec]
{
  return if null(l) 
	 then []
	 else [precSpec(head(l).terminalName, head(l).parserPrecedence, head(l).parserAssociation)] ++ getParserPrecSpecTerminals(tail(l));
}

function getParserPrecSpecRules
[Decorated ParserPrecSpec] ::= l::[Decorated RuleSpec]
{
  return if null(l) 
	 then []
	 else getParserPrecSpecRulesHelp(head(l).ruleRHSSpec) ++ getParserPrecSpecRules(tail(l));
}

function getParserPrecSpecRulesHelp
[Decorated ParserPrecSpec] ::= l::[Decorated RHSSpec]
{
  return if null(l) 
	 then []
	 else [precSpec(head(l).ruleName, head(l).parserPrecedence, "nonassoc")] ++ getParserPrecSpecRulesHelp(tail(l));
}