grammar silver:modification:copper_mda;


abstract production cstCopperMdaRoot
top::SyntaxRoot ::= parsername::String  startnt::String  host::Syntax  ext::Syntax  terminalPrefixes::[Pair<String String>]
{
  -- Because there may be references between the grammars, we cannot do the
  -- usualy normalization.
  
  -- TODO: we could consider making host host-only, and ext have both...
  host.cstEnv = directBuildTree(host.cstDcls ++ ext.cstDcls);
  host.containingGrammar = "host";
  host.cstNTProds = error("TODO: this should only be used by normalize"); -- TODO
  host.prefixesForTerminals = directBuildTree(terminalPrefixes);
  ext.cstEnv = host.cstEnv;
  ext.containingGrammar = "ext";
  ext.cstNTProds = error("TODO: this should only be used by normalize"); -- TODO
  ext.prefixesForTerminals = host.prefixesForTerminals;
  
  top.cstErrors := host.cstErrors ++ ext.cstErrors;
  
  local startFound :: [Decorated SyntaxDcl] = searchEnvTree(startnt, host.cstEnv);
  -- TODO check if this is found!!

  local attribute univLayout :: String;
  univLayout = implode("", map(xmlCopperRef, host.allIgnoreTerminals)); -- er, we're ignoring ext here?
  host.univLayout = univLayout;
  ext.univLayout = univLayout;

  top.xmlCopper =
"<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n\n" ++

"<CopperSpec xmlns=\"http://melt.cs.umn.edu/copper/xmlns\">\n" ++

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
"    <Declarations>\n" ++
  ext.xmlCopper ++
"    </Declarations>\n" ++
"  </ExtensionGrammar>\n\n" ++

"</CopperSpec>\n";

  top.unparse = error("No notion of unparsing for mda root...");
}


