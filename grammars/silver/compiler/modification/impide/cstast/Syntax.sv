grammar silver:compiler:modification:impide:cstast;

--import (see grammar-wide import in cstast.sv)

monoid attribute fontList :: [Pair<String Font>];
monoid attribute classFontList :: [Pair<String String>];

attribute fontList, classFontList occurs on Syntax, SyntaxDcl, SyntaxRoot;
propagate fontList, classFontList on Syntax, SyntaxDcl;

aspect production syntaxLexerClass
top::SyntaxDcl ::= n::String modifiers::SyntaxLexerClassModifiers
{
  top.classFontList <-
    if modifiers.fontAttr == "" then []
    else [pair(n, modifiers.fontAttr)];
}

abstract production syntaxFont
top::SyntaxDcl ::= fontName::String fnt::Font -- TODO: we probably? need to factor out this data structure somehow?
{
  top.fontList <- [pair(makeCopperName(fontName), fnt)];
  
  propagate cstErrors, prefixSeperator;

  top.fullName = fontName;
  top.sortKey = "111"; -- Doesn't really matter, it doesn't show up in the copper XML
  top.cstDcls := [pair(fontName, top)];
  top.cstNormalize := [top];
  
  top.xmlCopper = "";
}

