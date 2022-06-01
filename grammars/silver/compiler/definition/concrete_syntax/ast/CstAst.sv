grammar silver:compiler:definition:concrete_syntax:ast;

imports silver:regex;
imports silver:compiler:definition:type;
imports silver:compiler:definition:env;

imports silver:compiler:translation:java:core only makeIdName, makeProdName, makeNTName;
imports silver:compiler:translation:java:type only transType;

import silver:compiler:definition:concrete_syntax:copper as copper;
import silver:util:graph as g;
import silver:util:treemap as tm;
import silver:util:treeset as s;

{--
 - Encapsulates transformations and analysis of Syntax
 -}
closed nonterminal SyntaxRoot with location, sourceGrammar, cstErrors, copperParser, allTerminals, allNonterminals, dominatingTerminals, compareTo, isEqual;
propagate compareTo, isEqual, allTerminals, allNonterminals on SyntaxRoot;

@{-- The Copper API object corresponding to the parser. -}
synthesized attribute copperParser::copper:ParserBean;

@{-- An environment containing all terminals that dominate any particular one. -}
synthesized attribute dominatingTerminals::EnvTree<Decorated SyntaxDcl>;

abstract production cstRoot
top::SyntaxRoot ::=
  parsername::String  startnt::String  s::Syntax
  customStartLayout::Maybe<[String]> terminalPrefixes::[Pair<String String>] componentGrammarMarkingTerminals::[Pair<String [String]>]
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
            g:empty()))));
  s.subClasses =
    directBuildTree(
      g:toList(
        g:transitiveClosure(
          g:add(
            map(\ p::Pair<String String> -> pair(p.snd, p.fst), s.superClassContribs),
            g:empty()))));
  s.parserAttributeAspects = directBuildTree(s.parserAttributeAspectContribs);
  s.layoutTerms =
    buildLayoutEnv(
      map((.fullName), s.allTerminals),
      map((.fullName), s.allProductions ++ s.allNonterminals),
      s.layoutContribs);
  s.prefixesForTerminals = directBuildTree(terminalPrefixes);
  s.componentGrammarMarkingTerminals = directBuildTree(componentGrammarMarkingTerminals);
  s.prettyNames = tm:add(s.prettyNamesAccum, tm:empty());
  
  top.dominatingTerminals = directBuildTree(s.dominatingTerminalContribs);
  
  -- Move productions under their nonterminal, and sort the declarations
  production s2 :: Syntax = foldr(consSyntax, nilSyntax(), sortBy(sortKeyLte, s.cstNormalize));
  s2.cstEnv = s.cstEnv;
  s2.containingGrammar = "host";
  s2.cstNTProds = error("TODO: make this environment not be decorated?"); -- TODO
  s2.classTerminals = s.classTerminals;
  s2.superClasses = s.superClasses;
  s2.subClasses = s.subClasses;
  s2.parserAttributeAspects = s.parserAttributeAspects;
  s2.layoutTerms = s.layoutTerms;
  s2.prefixesForTerminals = s.prefixesForTerminals;
  s2.componentGrammarMarkingTerminals = s.componentGrammarMarkingTerminals;
  s2.prettyNames = tm:add(s2.prettyNamesAccum, tm:empty());
  
  -- This should be on s1, because the s2 transform assumes everything is well formed.
  -- In particular, it drops productions it can't find an NT for.
  top.cstErrors := s.cstErrors;
  
  production startFound :: [Decorated SyntaxDcl] = searchEnvTree(startnt, s.cstEnv);

  top.cstErrors <- if !null(startFound) then []
                   else ["Nonterminal " ++ startnt ++ " was referenced but " ++
                         "this grammar was not included in this parser. (Referenced as parser's starting nonterminal)"];

  -- The layout before and after the root nonterminal. By default, the layout of the root nonterminal.
  local startLayout::[copper:ElementReference] =
    map((.copperElementReference),
      map(head,
        lookupStrings(
          fromMaybe(searchEnvTree(startnt, s.layoutTerms), customStartLayout),
          s.cstEnv)));

  local parserClassAuxCode::String =
    s"""
          protected List<common.Terminal> tokenList = null;

          public void reset() {
            tokenList = new ArrayList<common.Terminal>();
          }

          public List<common.Terminal> getTokens() {
            return tokenList; // The way we reset this iterator when parsing again is to create a new list, so this is defacto immutable
          }
${s2.lexerClassRefDcls}
    """;
  local parserInitCode::String = "reset();";
  local preambleCode::String = "import java.util.ArrayList;\nimport java.util.List;\n";

  local grammarElements::[copper:GrammarElement] = s2.copperGrammarElements
    ++ [ copper:parserAttribute(top.sourceGrammar, builtinLoc("silver:compiler:definition:concrete_syntax:ast"),
           "context", "common.DecoratedNode", "context = common.TopNode.singleton;")
       ]
    ++ flatMap((.copperGrammarElements), s2.disambiguationClasses);
  top.copperParser = copper:parserBean(top.sourceGrammar, top.location,
    makeCopperName(parsername), parsername,
    head(startFound).copperElementReference, startLayout, ["common.HasTokens"], parserClassAuxCode,
    parserInitCode, preambleCode,
    copper:grammar_(top.sourceGrammar, top.location, s2.containingGrammar,
      grammarElements));
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

-- Compute an environment containg the layout for a given list of items
function buildLayoutEnv
EnvTree<String> ::= allTerms::[String] layoutItems::[String] layoutContribs::[Pair<String String>]
{
  -- Build a set of all terminals, for faster lookup
  local terms::s:Set<String> = s:add(allTerms, s:empty());
  -- Build a graph of nonterminals, productions and layout terminals where there is an edge a -> b iff a inherits layout from b
  local transitiveLayout::g:Graph<String> = g:transitiveClosure(g:add(layoutContribs, g:empty()));
  -- For every item that we wish to compute layout (productions and nonterminals), find all inherited layout terminals
  local layoutTerms::[Pair<String [String]>] =
    map(
      \ item::String ->
        pair(item, s:toList(s:intersect(terms, g:edgesFrom(item, transitiveLayout)))),
      layoutItems);
  -- Build the layout EnvTree
  return
    directBuildTree(
      flatMap(
        \ item::Pair<String [String]> -> map(pair(item.fst, _), item.snd),
        layoutTerms));
}
