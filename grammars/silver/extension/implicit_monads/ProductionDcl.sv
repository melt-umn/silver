grammar silver:extension:implicit_monads;


terminal Monadic_kwd    'monadic'     lexer classes {KEYWORD,RESERVED};
concrete production productionMonadDcl
top::AGDcl ::= 'monadic' 'abstract' 'production' id::Name ns::ProductionSignature body::ProductionBody
{
  --body.downSubst = emptySubst();
  --ns.signatureName = fName;

  --The forward should not have any type errors in it; all the type
  --   errors should be in merrors and the translation using
  --   monadRewritten should be type correct
  top.errors := body.merrors ++ forward.errors;

--  ns.env = newScopeEnv(ns.defs, top.env);
--  production fName :: String = top.grammarName ++ ":" ++ id.name;
--  production namedSig :: NamedSignature = ns.namedSignature;

  --This block is copied from silver:definition:flow:env for a regular abstract production
--  local myGraphs :: EnvTree<ProductionGraph> = head(searchEnvTree(top.grammarName, top.compiledGrammars)).productionFlowGraphs;
  {-- Used by core to send down with .frame -}
--  production myFlowGraph :: ProductionGraph = findProductionGraph(fName, myGraphs);
  
--  body.frame = productionContext(namedSig, myFlowGraph);
  forwards to productionDcl('abstract', 'production', id, ns, body.monadRewritten, location=top.location);
  --Forward equation exceeds flow type with dependencies on silver:definition:env:compiledGrammars, silver:definition:env:config, silver:definition:flow:env:flowEnv
}

