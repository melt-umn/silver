grammar silver:definition:concrete_syntax:ast;

imports silver:definition:regex;
imports silver:definition:type;
imports silver:definition:env;

imports silver:translation:java:core only makeIdName, makeClassName, makeNTClassName;
imports silver:translation:java:type only transType;

import silver:util:raw:graph as g;
import silver:util:raw:treeset as s;

{--
 - Encapsulates transformations and analysis of Syntax
 -}
closed nonterminal SyntaxRoot with cstErrors, xmlCopper;

{--
 - Translation of a CST AST to Copper XML.
 -}
synthesized attribute xmlCopper :: String;

abstract production cstRoot
top::SyntaxRoot ::= parsername::String  startnt::String  s::Syntax  terminalPrefixes::[Pair<String String>]
{
  s.cstEnv = directBuildTree(s.cstDcls);
  s.cstNTProds = directBuildTree(s.cstProds);
  s.classTerminals = directBuildTree(s.classTerminalContribs);
  s.containingGrammar = "host";
  s.superClasses =
    directBuildTree(
      g:toList(
        g:transitiveClosure(
          g:add(
            s.superClassContribs,
            g:empty(compareString)))));
  s.subClasses =
    directBuildTree(
      g:toList(
        g:transitiveClosure(
          g:add(
            map(\ p::Pair<String String> -> pair(p.snd, p.fst), s.superClassContribs),
            g:empty(compareString)))));
  s.parserAttributeAspects = directBuildTree(s.parserAttributeAspectContribs);
  s.layoutTerms =
    buildLayoutEnv(
      map((.fullName), s.allIgnoreTerminals),
      map((.fullName), s.allProductions),
      s.layoutContribs);
  s.prefixesForTerminals = directBuildTree(terminalPrefixes);
  
  -- Move productions under their nonterminal, and sort the declarations
  production s2 :: Syntax =
    foldr(consSyntax, nilSyntax(), sortBy(syntaxDclLte, s.cstNormalize));
  s2.cstEnv = directBuildTree(s.cstDcls);
  s2.containingGrammar = "host";
  s2.cstNTProds = error("TODO: make this environment not be decorated?"); -- TODO
  s2.classTerminals = s.classTerminals;
  s2.superClasses = s.superClasses;
  s2.subClasses = s.subClasses;
  s2.parserAttributeAspects = s.parserAttributeAspects;
  s2.layoutTerms = s.layoutTerms;
  s2.prefixesForTerminals = s.prefixesForTerminals;
  
  -- This should be on s1, because the s2 transform assumes everything is well formed.
  -- In particular, it drops productions it can't find an NT for.
  top.cstErrors := s.cstErrors;
  
  production startFound :: [Decorated SyntaxDcl] = searchEnvTree(startnt, s2.cstEnv);

  top.cstErrors <- if !null(startFound) then []
                   else ["Nonterminal " ++ startnt ++ " was referenced but " ++
                         "this grammar was not included in this parser. (Referenced as parser's starting nonterminal)"];

  production univLayout :: String = implode("", map(xmlCopperRef, s2.allIgnoreTerminals));

  top.xmlCopper =
s"""<?xml version="1.0" encoding="UTF-8"?>

<CopperSpec xmlns="http://melt.cs.umn.edu/copper/xmlns/skins/xml/0.9">
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
          
${s2.lexerClassRefDcls}
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
""" ++
-- Disambiguation classes
implode("\n", map((.xmlCopper), s2.disambiguationClasses)) ++
s"""
    </Declarations>
  </Grammar>
</CopperSpec>""";
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

function buildLayoutEnv
EnvTree<String> ::= allIgnoreTerms::[String] allProds::[String] layoutContribs::[Pair<String String>]
{
  -- Build a set of all ignore terminals, for faster lookup
  local ignoreTerms::s:Set<String> = s:add(allIgnoreTerms, s:empty(compareString));
  -- Build a graph of nonterminals, productions and layout terminals where there is an edge a -> b iff a inherits layout from b
  local layoutSources::g:Graph<String> =
    g:transitiveClosure(g:add(layoutContribs, g:empty(compareString)));
  -- For every production, find all inherited layout terminals
  local layoutTerms::[Pair<String [String]>] =
    map(
      \ prod::String ->
        pair(prod, s:toList(s:intersect(ignoreTerms, g:edgesFrom(prod, layoutSources)))),
      allProds);
  -- Build the layout EnvTree
  return
    directBuildTree(
      flatMap(
        \ item::Pair<String [String]> -> map(pair(item.fst, _), item.snd),
        layoutTerms));
}

