grammar silver:modification:impide:cstast;

--import (see grammar-wide import in cstast.sv)

synthesized attribute fontList :: [Pair<String Font>];
attribute fontList occurs on Syntax, SyntaxDcl, SyntaxRoot;

synthesized attribute classFontList :: [Pair<String String>];
attribute classFontList occurs on Syntax, SyntaxDcl, SyntaxRoot;

aspect production nilSyntax
top::Syntax ::=
{
  top.fontList = [];
  top.classFontList = [];
}

aspect production consSyntax
top::Syntax ::= s1::SyntaxDcl s2::Syntax
{
  top.fontList = s1.fontList ++ s2.fontList;
  top.classFontList = s1.classFontList ++ s2.classFontList;
}

aspect default production
top::SyntaxDcl ::=
{
  top.fontList = [];
  top.classFontList = [];
}

aspect production syntaxNonterminal
top::SyntaxDcl ::= t::Type subdcls::Syntax --modifiers::SyntaxNonterminalModifiers
{
}

aspect production syntaxTerminal
top::SyntaxDcl ::= n::String regex::Regex modifiers::SyntaxTerminalModifiers
{
}

aspect production syntaxProduction
top::SyntaxDcl ::= ns::NamedSignature modifiers::SyntaxProductionModifiers
{
}

aspect production syntaxLexerClass
top::SyntaxDcl ::= n::String modifiers::SyntaxLexerClassModifiers
{
  top.classFontList =
    if modifiers.fontAttr == "" then []
    else [pair(n, modifiers.fontAttr)];
}

aspect production syntaxParserAttribute
top::SyntaxDcl ::= n::String ty::Type acode::String
{
}

aspect production syntaxDisambiguationGroup
top::SyntaxDcl ::= n::String terms::[String] acode::String
{
}

abstract production syntaxFont
top::SyntaxDcl ::= fontName::String fnt::Font -- TODO: we probably? need to factor out this data structure somehow?
{
  top.fontList = [pair(makeCopperName(fontName), fnt)];

  top.sortKey = "111"; -- Doesn't really matter, it doesn't show up in the copper XML
  top.cstDcls = [pair(fontName, top)];
  top.cstErrors := [];
  top.cstNormalize = [top];
  
  top.xmlCopper = "";
}

