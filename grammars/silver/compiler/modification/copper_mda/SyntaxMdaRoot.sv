grammar silver:compiler:modification:copper_mda;

import silver:compiler:definition:concrete_syntax:copper as copper;
import silver:util:graph as g;
import silver:util:treemap as tm;

abstract production cstCopperMdaRoot
top::SyntaxRoot ::= parsername::String  startnt::String  host::Syntax  ext::Syntax  customStartLayout::Maybe<[String]>
{
  -- Because there may be references between the grammars, we cannot do the
  -- usual normalization.
  
  -- TODO: we could consider making host host-only, and ext have both...
  host.cstEnv = directBuildTree(host.cstDcls ++ ext.cstDcls);
  host.containingGrammar = "host";
  host.cstNTProds = error("TODO: this should only be used by normalize"); -- TODO
  host.classTerminals = directBuildTree(host.classTerminalContribs ++ ext.classTerminalContribs);
  host.superClasses =
    directBuildTree(
      g:toList(
        g:transitiveClosure(
          g:add(
            host.superClassContribs ++ ext.superClassContribs,
            g:empty()))));
  host.subClasses =
    directBuildTree(
      g:toList(
        g:transitiveClosure(
          g:add(
            map(
              \ p::Pair<String String> -> pair(p.snd, p.fst),
              host.superClassContribs ++ ext.superClassContribs),
            g:empty()))));
  host.parserAttributeAspects =
    directBuildTree(host.parserAttributeAspectContribs ++ ext.parserAttributeAspectContribs);
  host.layoutTerms =
    -- ext shouldn't affect host layout, but include both so we only have to build this once
    buildLayoutEnv(
      map((.fullName), host.allTerminals ++ ext.allTerminals),
      map((.fullName), host.allProductions ++ ext.allProductions ++ host.allNonterminals ++ ext.allNonterminals),
      host.layoutContribs ++ ext.layoutContribs);
  host.prefixesForTerminals = directBuildTree([]);
  host.componentGrammarMarkingTerminals = directBuildTree([]);
  
  ext.cstEnv = host.cstEnv;
  ext.containingGrammar = "ext";
  ext.cstNTProds = error("TODO: this should only be used by normalize"); -- TODO
  ext.classTerminals = host.classTerminals;
  ext.superClasses = host.superClasses;
  ext.subClasses = host.subClasses;
  ext.parserAttributeAspects = host.parserAttributeAspects;
  ext.layoutTerms = host.layoutTerms;
  ext.prefixesForTerminals = host.prefixesForTerminals;
  ext.componentGrammarMarkingTerminals = host.componentGrammarMarkingTerminals;

  local prettyNames::tm:Map<String String> =
    tm:add(host.prettyNamesAccum, tm:add(ext.prettyNamesAccum, tm:empty()));
  host.prettyNames = prettyNames;
  ext.prettyNames = prettyNames;
  
  local startFound :: [Decorated SyntaxDcl] = searchEnvTree(startnt, host.cstEnv);

  top.cstErrors := host.cstErrors ++ ext.cstErrors;
  top.cstErrors <- if !null(startFound) then []
                   else ["Nonterminal " ++ startnt ++ " was referenced but " ++
                         "this grammar was not included in this parser. (Referenced as parser's starting nonterminal)"];

  -- The layout before and after the root nonterminal. By default, the layout of the root nonterminal.
  local startLayout::[copper:ElementReference] =
    map((.copperElementReference),
      map(head,
        lookupStrings(
          fromMaybe(searchEnvTree(startnt, host.layoutTerms), customStartLayout),
          host.cstEnv)));

  local hostGrammar::copper:Grammar =
    copper:grammar_(top.sourceGrammar, top.location, host.containingGrammar,
      host.copperGrammarElements);

  -- All disambiguation classes go in the extension grammar for now, since they
  -- reference extension terminals.
  local extGrammarElements::[copper:GrammarElement] = ext.copperGrammarElements
    ++ flatMap((.copperGrammarElements), host.disambiguationClasses)
    ++ flatMap((.copperGrammarElements), ext.disambiguationClasses);
  local extGrammar::copper:Grammar =
    copper:extensionGrammar(top.sourceGrammar, top.location,
      ext.containingGrammar, extGrammarElements,
      map((.copperElementReference), ext.markingTokens),
      map((.copperElementReference), ext.bridgeProductions),
      map((.copperElementReference), host.disambiguationClasses));

  top.copperParser = copper:extendedParserBean(top.sourceGrammar, top.location,
    makeCopperName(parsername), parsername,
    head(startFound).copperElementReference, startLayout, "", "", "",
    hostGrammar, extGrammar);
}
