grammar silver:extension:implicit_monads;

{-
terminal Monadic_kwd    'monadic'     lexer classes {KEYWORD,RESERVED};
concrete production productionMonadDcl
top::AGDcl ::= 'monadic' 'abstract' 'production' id::Name ns::ProductionSignature body::ProductionBody
{
  top.unparse = "monadic abstract production " ++ id.unparse ++ "\n" ++ ns.unparse ++ "\n" ++ body.unparse;

  production fName :: String = top.grammarName ++ ":" ++ id.name;
  production namedSig :: NamedSignature = ns.namedSignature;

  top.defs := prodDef(top.grammarName, id.location, namedSig) ::
    if null(body.productionAttributes) then []
    else [prodOccursDef(top.grammarName, id.location, namedSig, body.productionAttributes)];

  --The forward should not have any type errors in it; all the type
  --   errors should be in merrors and the translation using
  --   monadRewritten should be type correct
  top.errors := body.merrors ++ forward.errors;

  production attribute sigDefs :: [Def] with ++;
  sigDefs := ns.defs;

  ns.signatureName = fName;
  ns.env = newScopeEnv(ns.defs, top.env);

  local attribute prodAtts :: [Def];
  prodAtts = defsFromPADcls(getProdAttrs(fName, top.env), namedSig);

  body.env = newScopeEnv(body.defs ++ sigDefs, newScopeEnv(prodAtts, top.env));
  body.frame = productionContext(namedSig, myFlowGraph);

  body.downSubst = emptySubst();
  body.mDownSubst = emptySubst();


  --This block is copied from silver:definition:flow:env for a regular abstract production
  local myGraphs :: EnvTree<ProductionGraph> = head(searchEnvTree(top.grammarName, top.compiledGrammars)).productionFlowGraphs;
  {-- Used by core to send down with .frame -}
  production myFlowGraph :: ProductionGraph = findProductionGraph(fName, myGraphs);
  top.flowDefs := body.flowDefs ++ 
    if null(body.uniqueSignificantExpression)
    then [prodFlowDef(namedSig.outputElement.typerep.typeName, fName)]
    else [];


  forwards to productionDcl('abstract', 'production', id, ns, body.monadRewritten, location=top.location);
  --Forward equation exceeds flow type with dependencies on silver:definition:env:compiledGrammars, silver:definition:env:config, silver:definition:flow:env:flowEnv
}
-}
