grammar silver:modification:impide:cstast;

--import (see grammar-wide import in cstast.sv)

synthesized attribute fontList :: [Pair<String Font>];
attribute fontList occurs on Syntax, SyntaxDcl, SyntaxRoot;

synthesized attribute termFontPairList :: [Pair<String String>];
attribute termFontPairList occurs on Syntax, SyntaxDcl, SyntaxRoot;

aspect production nilSyntax
top::Syntax ::=
{
  top.fontList = [];
  top.termFontPairList = [];
}

aspect production consSyntax
top::Syntax ::= s1::SyntaxDcl s2::Syntax
{
  top.fontList = s1.fontList ++ s2.fontList;
  top.termFontPairList = s1.termFontPairList ++ s2.termFontPairList;
}

aspect default production
top::SyntaxDcl ::=
{
  top.fontList = [];
  top.termFontPairList = [];
}

aspect production syntaxNonterminal
top::SyntaxDcl ::= t::TypeExp subdcls::Syntax --modifiers::SyntaxNonterminalModifiers
{
}

aspect production syntaxTerminal
top::SyntaxDcl ::= n::String regex::Regex_R modifiers::SyntaxTerminalModifiers
{
  top.termFontPairList = [
    -- First element: full qualifier name. E.g. host$silver_definition_core_Ident_t
    -- Actually, when isUnitary=true, then we don't need the host$ bit...
    -- Second element: font name. Either from terminal, otherwise from *some* lexer class.
    pair(n,
      if modifiers.fontAttr == "" then modifiers.fontAttrFromClass else modifiers.fontAttr)];
}

aspect production syntaxProduction
top::SyntaxDcl ::= ns::NamedSignature modifiers::SyntaxProductionModifiers
{
}

aspect production syntaxLexerClass
top::SyntaxDcl ::= n::String modifiers::SyntaxLexerClassModifiers
{
}

aspect production syntaxParserAttribute
top::SyntaxDcl ::= n::String ty::TypeExp acode::String
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
  
  top.unparses = [];-- TODO builds won't work right unless you provide --clean
}

