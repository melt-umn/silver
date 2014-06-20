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
  top.nxmlCopper = 
    "\n  <Nonterminal id=\"" ++ makeCopperName(t.typeName) ++ "\">\n" ++
      "    <PP>" ++ t.typeName ++ "</PP>\n" ++
      "    <Type><![CDATA[AdaptiveEnhancedParseTreeInnerNode<" ++ makeNTClassName(t.typeName) ++ ">]]></Type>\n" ++
      "  </Nonterminal>\n" ++
    subdcls.nxmlCopper;
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
    "  addToken(_terminal);\n" ++
    -- TODO: probable bug just spotted: more terminals than just those marked ignored can be layout and thus inappropriate to shift.
    (if modifiers.ignored then "" else "  shiftPTNode(RESULT, _terminal);\n") ++
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
  top.nxmlCopper =
    "  <Production id=\"" ++ makeCopperName(ns.fullName) ++ "\">\n" ++
    (if modifiers.productionPrecedence.isJust then
    "    <Class>main</Class>\n" ++
    "    <Precedence>" ++ toString(modifiers.productionPrecedence.fromJust) ++ "</Precedence>\n"
    else "") ++
    -- BEGIN DIFFERENCE *******************************************************
    "    <Code><![CDATA[\n" ++ 
    "      RESULT = createPTNode(new " ++ makeClassName(ns.fullName) ++ "(" ++ implode(", ", extractNonterminalsFromChildren(0, map(head, rhsRefs))) ++ insertLocationAnnotationAEPTIN(ns, map(head, rhsRefs)) ++ "));\n" ++
    -- TODO HACK: accesses to RESULT in production action code need to turn into RESULT.getLangSpecNode()
    -- because the nodes are wrapped in that instead.
      substitute("RESULT", "RESULT.getLangSpecNode()", modifiers.acode) ++
    -- END DIFFERENCE *********************************************************
    "]]></Code>\n" ++
    "    <LHS>" ++ xmlCopperRef(head(lhsRef)) ++ "</LHS>\n" ++
    "    <RHS>" ++ implode("", map(xmlCopperRef, map(head, rhsRefs))) ++ "</RHS>\n" ++
    (if modifiers.customLayout.isJust then
    "    <Layout>" ++ modifiers.customLayout.fromJust ++ "</Layout>\n"
    else "") ++
    (if modifiers.productionOperator.isJust then
    "    <Operator>" ++ modifiers.productionOperator.fromJust ++ "</Operator>\n"
    else "") ++
    "  </Production>\n";
}
-- The idea here seems to be that we have nonterminals wrapped in PTNode thingys,
-- so to construct our nonterminal, we have to unwrap them. Terminals are apparently a-okay though.
function extractNonterminalsFromChildren
[String] ::= index::Integer  from::[Decorated SyntaxDcl]
{
  local accessor :: String = "_children[" ++ toString(index) ++ "]";
  
  return if null(from) then []    -- "null": make the last argument to be null. An emergent fix to location
  else case head(from) of
       | syntaxTerminal(n, _, _)   -> [accessor]
       | syntaxNonterminal(n, _)   -> ["((AdaptiveEnhancedParseTreeInnerNode)" ++ accessor ++ ").getLangSpecNode()"]
       end ++ extractNonterminalsFromChildren(index + 1, tail(from));
}
function insertLocationAnnotationAEPTIN
String ::= ns::Decorated NamedSignature  from::[Decorated SyntaxDcl]
{
  local pfx :: String = if null(ns.inputElements) then "" else ", ";
  
  return if null(ns.namedInputElements) then ""
  else if length(ns.namedInputElements) > 1 then pfx ++ "multiple_annotation_problem" -- TODO
  else if head(ns.namedInputElements).elementName != "core:location" then pfx ++ "unknown_annotation_type_problem" -- TODO etc
  -- This code is awful:
  else pfx ++ "common.TerminalRecord.createSpan(new Object[] {" ++ implode(", ", extractNonterminalsFromChildren(0, from)) ++ "}, virtualLocation, (int)_pos.getPos())";
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

