grammar silver:definition:concrete_syntax:ast;

imports silver:definition:regex;
imports silver:definition:type;
imports silver:definition:env only typeName, unparse, unparseStrings, unparseNonStrings, quoteString, escapeString, unparseTyVars, unparseTypes, EnvTree, searchEnvTree, directBuildTree;

imports silver:translation:java:core only makeIdName, makeClassName, makeNTClassName;
imports silver:translation:java:type only transType;

{--
 - Encapsulates transformations and analysis of Syntax
 -}
closed nonterminal SyntaxRoot with cstErrors, cstNormal, xmlCopper, {-TODO:debugging-}unparse;

synthesized attribute cstNormal :: SyntaxRoot; -- TODO basically just a debugging thing
synthesized attribute xmlCopper :: String;

abstract production cstRoot
top::SyntaxRoot ::= parsername::String  startnt::String  s::Syntax
{
  s.cstEnv = directBuildTree(s.cstDcls);
  s.cstNTProds = directBuildTree(s.cstProds);
  s.containingGrammar = "host";
  
  -- Move productions under their nonterminal, and sort the declarations
  local attribute s2 :: Syntax;
  s2 = foldr(consSyntax, nilSyntax(), sortBy(syntaxDclLte, s.cstNormalize));
  s2.cstEnv = directBuildTree(s.cstDcls);
  s2.containingGrammar = "host";
  
  -- This should be on s1, because the s2 transform assumes everything is well formed.
  -- In particular, it drops productions it can't find an NT for.
  top.cstErrors := s.cstErrors;
  top.cstNormal = cstRoot(parsername, startnt, s2);
  
  local startFound :: [Decorated SyntaxDcl] = searchEnvTree(startnt, s2.cstEnv);
  -- TODO check if this is found!!

  local attribute univLayout :: String;
  univLayout = implode("", map(xmlCopperRef, s2.allIgnoreTerminals));

  s2.univLayout = univLayout;
  top.xmlCopper =
"<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n\n" ++

"<CopperSpec xmlns=\"http://melt.cs.umn.edu/copper/xmlns\">\n" ++
"  <Parser id=\"" ++ makeCopperName(parsername) ++ "\" isUnitary=\"true\">\n" ++
"    <PP>" ++ parsername ++ "</PP>\n" ++
"    <Grammars><GrammarRef id=\"" ++ s2.containingGrammar ++ "\"/></Grammars>\n" ++
"    <StartSymbol>" ++ xmlCopperRef(head(startFound)) ++ "</StartSymbol>\n" ++
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

"  <Grammar id=\"" ++ s2.containingGrammar ++ "\">\n\n" ++
"    <PP>" ++ s2.containingGrammar ++ "</PP>\n\n" ++
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

