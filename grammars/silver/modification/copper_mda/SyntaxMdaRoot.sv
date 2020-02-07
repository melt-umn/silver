grammar silver:modification:copper_mda;

import silver:util:raw:graph as g;

abstract production cstCopperMdaRoot
top::SyntaxRoot ::= parsername::String  startnt::String  host::Syntax  ext::Syntax  terminalPrefixes::[Pair<String String>]
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
            g:empty(compareString)))));
  host.subClasses =
    directBuildTree(
      g:toList(
        g:transitiveClosure(
          g:add(
            map(
              \ p::Pair<String String> -> pair(p.snd, p.fst),
              host.superClassContribs ++ ext.superClassContribs),
            g:empty(compareString)))));
  host.parserAttributeAspects =
    directBuildTree(host.parserAttributeAspectContribs ++ ext.parserAttributeAspectContribs);
  host.layoutTerms =
    -- ext shouldn't affect host layout, but include both so we only have to build this once
    buildLayoutEnv(
      map((.fullName), host.allIgnoreTerminals ++ ext.allIgnoreTerminals),
      map((.fullName), host.allProductions ++ ext.allProductions),
      host.layoutContribs ++ ext.layoutContribs);
  host.prefixesForTerminals = directBuildTree(terminalPrefixes);
  ext.cstEnv = host.cstEnv;
  ext.containingGrammar = "ext";
  ext.cstNTProds = error("TODO: this should only be used by normalize"); -- TODO
  ext.classTerminals = host.classTerminals;
  ext.superClasses = host.superClasses;
  ext.subClasses = host.subClasses;
  ext.parserAttributeAspects = host.parserAttributeAspects;
  ext.layoutTerms = host.layoutTerms;
  ext.prefixesForTerminals = host.prefixesForTerminals;
  
  local startFound :: [Decorated SyntaxDcl] = searchEnvTree(startnt, host.cstEnv);

  top.cstErrors := host.cstErrors ++ ext.cstErrors;
  top.cstErrors <- if !null(startFound) then []
                   else ["Nonterminal " ++ startnt ++ " was referenced but " ++
                         "this grammar was not included in this parser. (Referenced as parser's starting nonterminal)"];

  production univLayout :: String = implode("", map(xmlCopperRef, host.allIgnoreTerminals)); -- er, we're ignoring ext here?

  top.xmlCopper = 
"<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n\n" ++

"<CopperSpec xmlns=\"http://melt.cs.umn.edu/copper/xmlns/skins/xml/0.9\">\n" ++

"  <ExtendedParser id=\"" ++ makeCopperName(parsername) ++ "\">\n" ++
"    <PP>" ++ parsername ++ "</PP>\n" ++
"    <HostGrammar>\n" ++
"      <GrammarRef id=\"" ++ host.containingGrammar ++ "\"/>\n" ++
"    </HostGrammar>\n" ++
"    <ExtensionGrammars>\n" ++
"      <GrammarRef id=\"" ++ ext.containingGrammar ++ "\"/>\n" ++
"    </ExtensionGrammars>\n" ++
"    <StartSymbol>" ++ xmlCopperRef(head(startFound)) ++ "</StartSymbol>\n" ++
"    <StartLayout>" ++ univLayout ++ "</StartLayout>\n" ++
"  </ExtendedParser>\n\n" ++

"  <Grammar id=\"" ++ host.containingGrammar ++ "\">\n\n" ++
"    <PP>" ++ host.containingGrammar ++ "</PP>\n\n" ++
-- Default layout for production, unless otherwise specified.
"    <Layout>" ++ univLayout ++ "</Layout>\n\n" ++
"    <Declarations>\n" ++
"      <ParserAttribute id=\"context\">\n" ++
"        <Type><![CDATA[common.DecoratedNode]]></Type>\n" ++
"        <Code><![CDATA[context = common.TopNode.singleton;]]></Code>\n" ++
"      </ParserAttribute>\n" ++
       host.xmlCopper ++
"    </Declarations>\n" ++
"  </Grammar>\n\n" ++

"  <ExtensionGrammar id=\"" ++ ext.containingGrammar ++ "\">\n" ++
"    <PP>" ++ host.containingGrammar ++ "</PP>\n\n" ++
"    <Layout>" ++ univLayout ++ "</Layout>\n\n" ++
"    <MarkingTerminals>\n" ++
  implode("", map(xmlCopperRef, ext.markingTokens)) ++
"    </MarkingTerminals>\n" ++
"    <BridgeProductions>\n" ++
  implode("", map(xmlCopperRef, ext.bridgeProductions)) ++
"    </BridgeProductions>\n" ++
"    <GlueDisambiguationFunctions>\n" ++
  -- TODO: Workaround hack since host disambiguation functions are moved
  -- to the extension grammar.
  implode("",
    map(xmlCopperRef,
      map(
        \ d::Decorated SyntaxDcl ->
          decorate new(d) with {
            containingGrammar = "ext";
            cstEnv = host.cstEnv;
            cstNTProds = error("TODO: this should only be used by normalize"); -- TODO
            classTerminals = host.classTerminals;
            superClasses = host.superClasses;
            subClasses = host.subClasses;
            parserAttributeAspects = host.parserAttributeAspects;
            layoutTerms = host.layoutTerms;
            prefixesForTerminals = host.prefixesForTerminals;
          },
        host.disambiguationClasses))) ++
  implode("", map(xmlCopperRef, ext.disambiguationClasses)) ++
"    </GlueDisambiguationFunctions>\n" ++
"    <Declarations>\n" ++
  ext.xmlCopper ++
-- All disambiguation classes go in the extension grammar for now,
-- since they reference extension terminals.
  implode("\n", map((.xmlCopper), host.disambiguationClasses)) ++
  implode("\n", map((.xmlCopper), ext.disambiguationClasses)) ++
"    </Declarations>\n" ++
"  </ExtensionGrammar>\n\n" ++

"</CopperSpec>\n";
}


