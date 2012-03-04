grammar silver:definition:concrete_syntax:ast;

imports silver:definition:regex;
imports silver:definition:type;
imports silver:util:treemap;
imports silver:definition:env only typeName, unparse, unparseStrings, unparseNonStrings, quoteString, escapeString, unparseTyVars, unparseTypes;

imports silver:translation:java:core only makeIdName, makeClassName, makeNTClassName;
imports silver:translation:java:type only transType;

{--
 - Encapsulates transformations and analysis of Syntax
 -}
nonterminal SyntaxRoot with cstErrors, cstNormal, xmlCopper, {-TODO:debugging-}unparse;

synthesized attribute cstNormal :: SyntaxRoot; -- TODO basically just a debugging thing
synthesized attribute xmlCopper :: String;

{--
 - We need to give a name to the "grammar" all the syntax information goes
 - into on Copper's side. For now, just call it host, because we're not
 - taking advantage of multiple Copper grammars.
 -}
global copperGrammarId :: String = "host";

abstract production cstRoot
top::SyntaxRoot ::= parsername::String  startnt::String  s::Syntax
{
  s.cstEnv = treeConvert(s.cstDcls, treeNew(compareString));
  s.cstNTProds = treeConvert(s.cstProds, treeNew(compareString));
  
  -- Move productions under their nonterminal, and sort the declarations
  local attribute s2 :: Syntax;
  s2 = foldr_p(consSyntax, nilSyntax(), sortBy(syntaxDclLte, s.cstNormalize));
  s2.cstEnv = treeConvert(s.cstDcls, treeNew(compareString));
  
  -- This should be on s1, because the s2 transform assumes everything is well formed.
  -- In particular, it drops productions it can't find an NT for.
  top.cstErrors := s.cstErrors;
  top.cstNormal = cstRoot(parsername, startnt, s2);

  local attribute univLayout :: String;
  univLayout = implode("", map(xmlCopperRef, s2.allIgnoreTerminals));

  s2.univLayout = univLayout;
  top.xmlCopper =
"<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n\n" ++

"<CopperSpec xmlns=\"http://melt.cs.umn.edu/copper/xmlns\">\n" ++
"  <Parser id=\"" ++ makeCopperName(parsername) ++ "\" isUnitary=\"true\">\n" ++
"    <PP>" ++ parsername ++ "</PP>\n" ++
"    <Grammars><GrammarRef id=\"" ++ copperGrammarId ++ "\"/></Grammars>\n" ++
"    <StartSymbol>" ++ xmlCopperNontermRef(startnt) ++ "</StartSymbol>\n" ++
-- The layout before and after the root nonterminal. For now, universal layout.
"    <StartLayout>" ++ univLayout ++ "</StartLayout>\n" ++
-- TODO fix: ?
--"    <Package>parsers</Package>\n" ++
--"    <ClassName>SingleParser</ClassName>\n" ++
-- This stuff gets dumped onto the outer class:
--"    <ClassAuxiliaryCode><Code><![CDATA[  ]]></Code></ClassAuxiliaryCode>\n" ++
-- If not otherwise specified. We always specify.
--"    <DefaultProductionCode><Code><![CDATA[  ]]></Code></DefaultProductionCode>\n" ++
-- If not otherwise specified. We should do this, maybe...
--"    <DefaultTerminalCode><Code><![CDATA[  ]]></Code></DefaultTerminalCode>\n" ++
-- Call just before a parse:
--"    <ParserInitCode><Code><![CDATA[  ]]></Code></ParserInitCode>\n" ++
-- Ditto, after:
--"    <PostParseCode><Code><![CDATA[  ]]></Code></PostParseCode>\n" ++
-- Imports and whatnot:
--"    <Preamble><Code><![CDATA[  ]]></Code></Preamble>\n" ++
-- This stuff gets dumped onto the semantic action container class:
--"    <SemanticActionAuxiliaryCode><Code><![CDATA[  ]]></Code></SemanticActionAuxiliaryCode>\n" ++
"  </Parser>\n\n" ++

"  <Grammar id=\"" ++ copperGrammarId ++ "\">\n\n" ++
"    <PP>" ++ copperGrammarId ++ "</PP>\n\n" ++
-- Default layout for production, unless otherwise specified.
"    <Layout>" ++ univLayout ++ "</Layout>\n\n" ++
"    <Declarations>\n" ++
"      <ParserAttribute id=\"context\">\n" ++
"        <Type><![CDATA[common.DecoratedNode]]></Type>\n" ++
"        <Code><![CDATA[context = common.TopNode.singleton;]]></Code>\n" ++
"      </ParserAttribute>\n" ++
       s2.xmlCopper ++
"    </Declarations>\n" ++
"  </Grammar>\n" ++
"</CopperSpec>\n";

  top.unparse = implode(",\n ", s.unparses);
}


{-
Assumptions we make about initial Syntax:

1. All type parameter lists are the appropriate length. (Silver type checking)
-}

function makeCopperName
String ::= str::String
{
  return makeIdName(str);
}

