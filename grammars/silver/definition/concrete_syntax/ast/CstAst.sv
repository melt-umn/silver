grammar silver:definition:concrete_syntax:ast;

imports silver:definition:regex;
imports silver:definition:type;
imports silver:definition:env;

imports silver:translation:java:core only makeIdName, makeClassName, makeNTClassName;
imports silver:translation:java:type only transType;

{--
 - Encapsulates transformations and analysis of Syntax
 -}
closed nonterminal SyntaxRoot with cstErrors, xmlCopper, {-TODO:debugging-}unparse;

{--
 - Translation of a CST AST to Copper XML.
 -}
synthesized attribute xmlCopper :: String;

abstract production cstRoot
top::SyntaxRoot ::= parsername::String  startnt::String  s::Syntax  terminalPrefixes::[Pair<String String>]
{
  s.cstEnv = directBuildTree(s.cstDcls);
  s.cstNTProds = directBuildTree(s.cstProds);
  s.containingGrammar = "host";
  s.univLayout = error("TODO: make this environment not be decorated?"); -- TODO
  s.prefixesForTerminals = error("TODO: shouldn't by necessary to normalize"); -- TODO
  
  -- Move productions under their nonterminal, and sort the declarations
  production s2 :: Syntax =
    foldr(consSyntax, nilSyntax(), sortBy(syntaxDclLte, s.cstNormalize));
  s2.cstEnv = directBuildTree(s.cstDcls);
  s2.containingGrammar = "host";
  s2.cstNTProds = error("TODO: make this environmnet not be decorated?"); -- TODO
  s2.prefixesForTerminals = directBuildTree(terminalPrefixes);
  
  -- This should be on s1, because the s2 transform assumes everything is well formed.
  -- In particular, it drops productions it can't find an NT for.
  top.cstErrors := s.cstErrors;
  
  -- TODO: better error message, test case 
  production startFound :: [Decorated SyntaxDcl] = searchEnvTree(startnt, s2.cstEnv);

  top.cstErrors <- if null(startFound) then 
                   ["Missing nonterminal " ++ startnt ++ " for parser "  ++ parsername] else [];

  production univLayout :: String = implode("", map(xmlCopperRef, s2.allIgnoreTerminals));

  s2.univLayout = univLayout;
  top.xmlCopper = if !null(top.cstErrors) then "" else 
s"""<?xml version="1.0" encoding="UTF-8"?>

<CopperSpec xmlns="http://melt.cs.umn.edu/copper/xmlns">
  <Parser id="${makeCopperName(parsername)}" isUnitary="true">
    <PP>${parsername}</PP>
    <Grammars><GrammarRef id="${s2.containingGrammar}"/></Grammars>
    <StartSymbol>${xmlCopperRef(head(startFound))}</StartSymbol>
""" ++
-- The layout before and after the root nonterminal. For now, universal layout.
s"""    <StartLayout>${univLayout}</StartLayout>
""" ++
-- TODO fix: ?
--"    <Package>parsers</Package>\n" ++
--"    <ClassName>SingleParser</ClassName>\n" ++
-- This stuff gets dumped onto the outer class:
--"    <ClassAuxiliaryCode><Code><![CDATA[  ]]></Code></ClassAuxiliaryCode>\n" ++

s"""    <ClassAuxiliaryCode><Code><![CDATA[
          protected List<common.Terminal> tokenList = null;

          public void reset() {
            tokenList = new ArrayList<common.Terminal>();
          }

          public List<common.Terminal> getTokens() {
            return tokenList; // The way we reset this iterator when parsing again is to create a new list, so this is defacto immutable
          }
        ]]></Code></ClassAuxiliaryCode>
""" ++
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

s"""    <ParserInitCode>
      <Code><![CDATA[
        reset();
      ]]></Code>
    </ParserInitCode>
    <Preamble>
<Code><![CDATA[
import java.util.ArrayList;
import java.util.List;
]]></Code>
    </Preamble>
""" ++

s"""  </Parser>

  <Grammar id="${s2.containingGrammar}">

    <PP>${s2.containingGrammar}</PP>

""" ++
-- Default layout for production, unless otherwise specified.
s"""    <Layout>${univLayout}</Layout>
    <Declarations>
      <ParserAttribute id="context">
        <Type><![CDATA[common.DecoratedNode]]></Type>
        <Code><![CDATA[context = common.TopNode.singleton;]]></Code>
      </ParserAttribute>
       ${s2.xmlCopper}
    </Declarations>
  </Grammar>
</CopperSpec>""";

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

