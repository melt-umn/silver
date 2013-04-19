grammar silver:modification:copper_mda;

synthesized attribute markingTokens :: [Decorated SyntaxDcl];
synthesized attribute bridgeProductions :: [Decorated SyntaxDcl];

attribute markingTokens, bridgeProductions occurs on Syntax, SyntaxDcl;

aspect production nilSyntax
top::Syntax ::=
{
  top.markingTokens = [];
  top.bridgeProductions = [];
}
aspect production consSyntax
top::Syntax ::= s1::SyntaxDcl s2::Syntax
{
  top.markingTokens = s1.markingTokens ++ s2.markingTokens;
  top.bridgeProductions = s1.bridgeProductions ++ s2.bridgeProductions;
}

aspect default production
top::SyntaxDcl ::=
{
  top.markingTokens = [];
  top.bridgeProductions = [];
}

aspect production syntaxTerminal
top::SyntaxDcl ::= n::String _ modifiers::SyntaxTerminalModifiers
{
  top.markingTokens = if modifiers.marking then [top] else [];
}

aspect production syntaxProduction
top::SyntaxDcl ::= ns::NamedSignature  modifiers::SyntaxProductionModifiers
{
  top.bridgeProductions =
    if !null(lhsRef) && top.containingGrammar != head(lhsRef).containingGrammar
    then [top]
    else [];
}

