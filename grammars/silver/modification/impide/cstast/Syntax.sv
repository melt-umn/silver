grammar silver:modification:impide:cstast;

--import (see grammar-wide import in cstast.sv)

synthesized attribute fontList :: [Pair<String Font>];
attribute fontList occurs on Syntax, SyntaxDcl, SyntaxRoot;

synthesized attribute termFontPairList :: [Pair<String String>];
attribute termFontPairList occurs on Syntax, SyntaxDcl, SyntaxRoot;

aspect production nilSyntax
top::Syntax ::=
{
  top.nxmlCopper = "";
  top.fontList = [];
  top.termFontPairList = [];
}

aspect production consSyntax
top::Syntax ::= s1::SyntaxDcl s2::Syntax
{
  top.nxmlCopper = s1.nxmlCopper ++ s2.nxmlCopper;
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
  top.nxmlCopper = top.xmlCopper; -- NOTE: this is only safe because subdcls only contains productions, which also say = top.xmlCopper
}

aspect production syntaxTerminal
top::SyntaxDcl ::= n::String regex::Regex_R modifiers::SyntaxTerminalModifiers
{
  top.termFontPairList = [
    -- First element: full qualifier name. E.g. host$silver_definition_core_Ident_t
    -- Actually, when isUnitary=true, then we don't need the host$ bit...
    -- Second element: font name. Either from terminal, otherwise from *some* lexer class.
    pair(makeCopperName(n),
      if modifiers.fontAttr == "" then modifiers.fontAttrFromClass else modifiers.fontAttr)];
  
  top.nxmlCopper =
    "  <Terminal id=\"" ++ makeCopperName(n) ++ "\">\n" ++
    "    <PP>" ++ n ++ "</PP>\n" ++
    "    <Regex>" ++ regex_to_use.xmlCopper ++ "</Regex>\n" ++ 
    (if modifiers.opPrecedence.isJust || modifiers.opAssociation.isJust then
    "    <Operator>\n" ++
    "      <Class>main</Class>\n" ++
    "      <Precedence>" ++ toString(fromMaybe(0, modifiers.opPrecedence)) ++ "</Precedence>\n" ++
    "      " ++ convertAssocNXML(modifiers.opAssociation) ++ "\n" ++ -- TODO
    "    </Operator>\n"
    else "") ++
    "    <Type>common.TerminalRecord</Type>\n" ++ 
    "    <Code><![CDATA[\n" ++ 
    "RESULT = new common.TerminalRecord(" ++ lexeme_source ++ ",virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());\n" ++
    -- BEGIN DIFFERENCE FROM NORMAL xmlCopper ATTRIBUTE ************************
    "  addToken(_terminal, (int)getStartRealLocation().getPos(), (int)getEndRealLocation().getPos());\n" ++
    -- END DIFFERENCE FROM NORMAL *********************************************
      modifiers.acode ++
    "]]></Code>\n" ++ 
    "    <InClasses>" ++ modifiers.lexerclassesXML ++ "</InClasses>\n" ++ 
    -- TODO: prefix?
    "    <Submits>" ++ modifiers.submitsXML ++ "</Submits>\n" ++ 
    "    <Dominates>" ++ modifiers.dominatesXML ++ "</Dominates>\n" ++
    "  </Terminal>\n";
}

aspect production syntaxProduction
top::SyntaxDcl ::= ns::NamedSignature modifiers::SyntaxProductionModifiers
{
  top.nxmlCopper = top.xmlCopper; -- see note in syntaxNonterminal, if this changes...
}

aspect production syntaxLexerClass
top::SyntaxDcl ::= n::String modifiers::SyntaxLexerClassModifiers
{
  top.nxmlCopper = top.xmlCopper;
}

aspect production syntaxParserAttribute
top::SyntaxDcl ::= n::String ty::TypeExp acode::String
{
  top.nxmlCopper = top.xmlCopper;
}

aspect production syntaxDisambiguationGroup
top::SyntaxDcl ::= n::String terms::[String] acode::String
{
  top.nxmlCopper = top.xmlCopper;
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
  top.nxmlCopper = "";
  
  top.unparses = [];-- TODO builds won't work right unless you provide --clean
}

