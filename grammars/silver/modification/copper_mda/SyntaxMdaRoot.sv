grammar silver:modification:copper_mda;


abstract production cstCopperMdaRoot
top::SyntaxRoot ::= parsername::String  startnt::String  host::Syntax  ext::Syntax
{
  -- Because there may be references between the grammars, we cannot do the
  -- usualy normalization.
  
  -- TODO: we could consider making host host-only, and ext have both...
  host.cstEnv = directBuildTree(host.cstDcls ++ ext.cstDcls);
  host.containingGrammar = "host";
  host.cstNTProds = error("TODO: this should only be used by normalize"); -- TODO
  ext.cstEnv = host.cstEnv;
  ext.containingGrammar = "ext";
  ext.cstNTProds = error("TODO: this should only be used by normalize"); -- TODO
  
  top.cstErrors := host.cstErrors ++ ext.cstErrors;
  top.cstNormal = top; -- meh
  
  local startFound :: [Decorated SyntaxDcl] = searchEnvTree(startnt, host.cstEnv);
  -- TODO check if this is found!!

  local attribute univLayout :: String;
  univLayout = implode("", map(xmlCopperRef, host.allIgnoreTerminals)); -- er, we're ignoring ext here?
  host.univLayout = univLayout;
  ext.univLayout = univLayout;

  top.xmlCopper =
"<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n\n" ++

"<CopperSpec xmlns=\"http://melt.cs.umn.edu/copper/xmlns\">\n" ++
"  <Parser id=\"" ++ makeCopperName(parsername) ++ "\">\n" ++
"    <PP>" ++ parsername ++ "</PP>\n" ++
"    <Grammars><GrammarRef id=\"" ++ host.containingGrammar ++ "\"/><GrammarRef id=\"" ++ ext.containingGrammar ++ "\"/></Grammars>\n" ++
"    <StartSymbol>" ++ xmlCopperRef(head(startFound)) ++ "</StartSymbol>\n" ++
"    <StartLayout>" ++ univLayout ++ "</StartLayout>\n" ++
"  </Parser>\n\n" ++

"  <Grammar id=\"" ++ host.containingGrammar ++ "\">\n\n" ++
"    <PP>" ++ host.containingGrammar ++ "</PP>\n\n" ++
"    <Layout>" ++ univLayout ++ "</Layout>\n\n" ++
"    <Declarations>\n" ++
-- TODO: we should reconsider calling this a parser attribute, and maybe use the "put in semantic action container" thingy instead...
"      <ParserAttribute id=\"context\">\n" ++
"        <Type><![CDATA[common.DecoratedNode]]></Type>\n" ++
"        <Code><![CDATA[context = common.TopNode.singleton;]]></Code>\n" ++
"      </ParserAttribute>\n" ++
       host.xmlCopper ++
"    </Declarations>\n" ++
"  </Grammar>\n" ++

"  <Grammar id=\"" ++ ext.containingGrammar ++ "\">\n\n" ++
"    <PP>" ++ ext.containingGrammar ++ "</PP>\n\n" ++
"    <Layout>" ++ univLayout ++ "</Layout>\n\n" ++
"    <Declarations>\n" ++
       ext.xmlCopper ++
"    </Declarations>\n" ++
"  </Grammar>\n" ++

"</CopperSpec>\n";

  top.unparse = error("No notion of unparsing for mda root...");
}


