grammar silver:modification:impide:cstast;

--import (see grammar-wide import in cstast.sv)

synthesized attribute classDomContribsNXML :: String;
synthesized attribute classSubContribsNXML :: String;
attribute classDomContribsNXML occurs on SyntaxDcl;
attribute classSubContribsNXML occurs on SyntaxDcl;

synthesized attribute termFontPairList :: [Pair<String String>];
attribute termFontPairList occurs on Syntax, SyntaxDcl;

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
  top.classDomContribsNXML = error("Internal compiler error: should only ever be demanded of lexer classes");
  top.classSubContribsNXML = error("Internal compiler error: should only ever be demanded of lexer classes");
  top.fontList = [];
  top.termFontPairList = [];
}

aspect production syntaxNonterminal
top::SyntaxDcl ::= t::TypeExp subdcls::Syntax --modifiers::SyntaxNonterminalModifiers
{
  top.nxmlCopper =
    "\n\t\t\t<Nonterminal id=\"" ++ makeCopperName(t.typeName) ++ "\">\n" ++
      "\t\t\t\t<PP>" ++ makeCopperName(t.typeName) ++ "</PP>\n" ++
      "\t\t\t\t<Type><![CDATA[AdaptiveEnhancedParseTreeInnerNode<" ++ makeNTClassName(t.typeName) ++ ">]]></Type>\n" ++
    "\t\t\t</Nonterminal>\n" ++
    subdcls.nxmlCopper;
}

aspect production syntaxTerminal
top::SyntaxDcl ::= n::String regex::Regex_R modifiers::SyntaxTerminalModifiers
{
  top.termFontPairList = [ pair(makeCopperName(n), if modifiers.fontAttr == "" then modifiers.fontAttrFromClass else modifiers.fontAttr) ];
  top.nxmlCopper =
    "\n\t\t\t<Terminal id=\"" ++ makeCopperName(n) ++ "\">\n" ++
      "\t\t\t\t<PP>" ++ makeCopperName(n) ++ "</PP>\n" ++
      "\t\t\t\t<Regex>" ++
      regex.xmlCopper ++
      "\n\t\t\t\t</Regex>\n" ++ 

      (if modifiers.opPrecedence.isJust || modifiers.opAssociation.isJust then
      "\t\t\t\t<Operator>\n" ++
      "\t\t\t\t\t<Class>main</Class>\n" ++
      "\t\t\t\t\t<Precedence>" ++ toString(fromMaybe(0, modifiers.opPrecedence)) ++ "</Precedence>\n" ++
      "\t\t\t\t\t" ++ convertAssocNXML(modifiers.opAssociation) ++ "\n" ++
      "\t\t\t\t</Operator>\n"
      else "") ++

      "\t\t\t\t<Type>Object</Type>\n" ++ 
      "\t\t\t\t<Code><![CDATA[\n" ++ 
"            RESULT = new common.TerminalRecord(lexeme,virtualLocation,Integer.valueOf((int)getStartRealLocation().getPos()),Integer.valueOf((int)getEndRealLocation().getPos()));\n" ++
"            addToken(_terminal);\n" ++
(if modifiers.ignored then "" else "            shiftPTNode(RESULT, _terminal);\n") ++
      "]]></Code>\n" ++ 
      (if modifiers.lexerclassesNXML == "" then "" else 
      "\t\t\t\t<InClasses>\n" ++ 
      "\t\t\t\t\t<TerminalClassRef id=\"" ++ modifiers.lexerclassesNXML ++ "\"/>\n" ++ 
      "\t\t\t\t</InClasses>\n") ++ 
      "\t\t\t\t<Submits>\n\t\t\t\t\t" ++
      modifiers.submitsNXML ++
      "\n\t\t\t\t</Submits>\n" ++ 
      "\t\t\t\t<Dominates>\n\t\t\t\t\t" ++
      modifiers.dominatesNXML ++
      "\n\t\t\t\t</Dominates>\n" ++
    "\t\t\t</Terminal>\n";
}

function convertAssocNXML -- TODO remove, make attribute
String ::= opassoc::Maybe<String>
{ 
  local attribute assoc::String;
  assoc = fromMaybe("", opassoc);
  return if assoc=="left" then "<LeftAssociative/>" 
          else if assoc=="right" then "<RightAssociative/>" 
          else "<NonAssociative/>";
}

aspect production syntaxProduction
top::SyntaxDcl ::= n::String lhs::TypeExp rhs::[TypeExp] modifiers::SyntaxProductionModifiers
{
  local attribute rhses :: [Pair<Decorated SyntaxDcl Pair<String String>>];
  rhses = createNamedSyntaxDclsForRHSXml(map(head, rhsRefs), 0);
  top.nxmlCopper =
    "\t\t\t<Production id=\"" ++ makeCopperName(n) ++ "\">\n" ++
      "\t\t\t\t<Code><![CDATA[\n" ++ 
"            RESULT = createPTNode(new " ++ makeClassName(n) ++ "(new Object[]{" ++ implode(", ", map(getAccesserCodeForTermNonterm, rhses)) ++ "}));\n" ++
"]]></Code>\n" ++
      "\t\t\t\t<LHS>" ++ nxmlCopperElementRef(createPairForSyntaxDcl(head(lhsRef))) ++ "</LHS>\n" ++
      "\t\t\t\t<RHS>\n\t\t\t\t\t" ++
        implode("\n\t\t\t\t\t", map(nxmlCopperElementRef, rhses)) ++
      "\n\t\t\t\t</RHS>\n" ++
    "\t\t\t</Production>\n";
}

aspect production syntaxLexerClass
top::SyntaxDcl ::= n::String modifiers::SyntaxLexerClassModifiers
{
  -- Uhh, TODO. This code needs some clean up work. I think this ought to work fine, though.
  top.classDomContribsNXML = modifiers.dominatesXML;
  top.classSubContribsNXML = modifiers.submitsXML;

  top.nxmlCopper = "\t\t\t" ++ nxmlCopperElement(top) ++ "\n";
}

aspect production syntaxParserAttribute
top::SyntaxDcl ::= n::String ty::TypeExp acode::String
{
    top.nxmlCopper = 
    "  <ParserAttribute id=\"" ++ makeCopperName(n) ++ "\"\n" ++
    "    <Type>" ++ ty.transType ++ "</Type>\n" ++
    "    <Code><![CDATA[\n" ++
      acode ++
    "\n]]></Code>\n" ++
    "  </ParserAttribute>\n";
}

aspect production syntaxDisambiguationGroup
top::SyntaxDcl ::= n::String terms::[String] acode::String
{
  top.nxmlCopper = 
    "  <DisambiguationFunction id=\"" ++ makeCopperName(n) ++ "\">\n" ++
    "    <Members>\n      " ++
    implode("\n      ", map(createNxmlCopperTermElementRef, terms)) ++
    "\n    </Members>\n" ++
    "    <Code><![CDATA[\n" ++
    "return " ++ getGrammarId() ++ "$" ++ substring(7, length(acode), acode) ++  
    "\n]]></Code>\n" ++
    "  </DisambiguationFunction>\n";
}

abstract production syntaxFont
top::SyntaxDcl ::= fontName::String fnt::Font
{
  top.nxmlCopper = "";
  top.fontList = [pair(fontName, fnt)];

  top.sortKey = "111"; -- Doesn't really matter
  top.cstDcls = [pair(fontName, top)]; -- maybe this isn't needed.
  top.cstErrors := [];
  top.cstNormalize = [top];
  
  top.xmlCopper = "";
  top.unparses = [];-- TODO builds won't work right unless you provide --clean

}

function createNxmlCopperTermElementRef
String ::= term::String
{
  --return nxmlCopperTermElementRef(term, getGrammarId(), "");
  return "<TerminalRef id=\"" ++ makeCopperName(term) ++ "\"/>";
}

function nxmlCopperTermElement
String ::= s::String
{
  return "<Terminal id=\"" ++ makeCopperName(s) ++ "\" />";
}
function nxmlCopperClassElement
String ::= s::String
{
  return "<TerminalClass id=\"" ++ makeCopperName(s) ++ "\" />";
}
function nxmlCopperNontermElement
String ::= s::String
{
  return "<Nonterminal id=\"" ++ makeCopperName(s) ++ "\" />";
}
function nxmlCopperElement
String ::= d::Decorated SyntaxDcl
{
  return case d of
         | syntaxLexerClass(n, _) -> nxmlCopperClassElement(n)
         | syntaxTerminal(n, _, _)   -> nxmlCopperTermElement(n)
         | syntaxNonterminal(n, _)   -> nxmlCopperNontermElement(n.typeName)
         end;
}

function nxmlCopperTermElementRef
String ::= s::String gr::String name::String
{
  return "<TerminalRef id=\"" ++ makeCopperName(s) ++ "\" grammar=\"" ++ gr ++ "\"" 
    ++ (if name == "" then "" else " name=\"" ++ name ++ "\"") ++ "/>";
}
function nxmlCopperClassElementRef
String ::= s::String gr::String name::String
{
  return "<TerminalClassRef id=\"" ++ makeCopperName(s) ++ "\" grammar=\"" ++ gr ++ "\"" 
    ++ (if name == "" then "" else " name=\"" ++ name ++ "\"") ++ "/>";
}
function nxmlCopperNontermElementRef
String ::= s::String gr::String name::String
{
  return "<NonterminalRef id=\"" ++ makeCopperName(s) ++ "\" grammar=\"" ++ gr ++ "\"" 
    ++ (if name == "" then "" else " name=\"" ++ name ++ "\"") ++ "/>";
}
function nxmlCopperElementRef
String ::= p::Pair<Decorated SyntaxDcl Pair<String String>>
--d::Decorated SyntaxDcl gr::String name::String
{
  local attribute syn :: Decorated SyntaxDcl;
  local attribute gr :: String;
  local attribute name :: String;
  syn = p.fst;
  gr = p.snd.fst;
  name = p.snd.snd;
  return case syn of
         | syntaxLexerClass(n, _) -> nxmlCopperClassElementRef(n,gr,name)
         | syntaxTerminal(n, _, _)   -> nxmlCopperTermElementRef(n,gr,name)
         | syntaxNonterminal(n, _)   -> nxmlCopperNontermElementRef(n.typeName,gr,name)
         end;
}

function getclassDomContribsNXML
String ::= d::Decorated SyntaxDcl
{ 
  return d.classDomContribsNXML; 
}

function getclassSubContribsNXML
String ::= d::Decorated SyntaxDcl
{  
  return d.classSubContribsNXML; 
}

