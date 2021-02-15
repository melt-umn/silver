grammar silver:compiler:definition:flow:env;

import silver:compiler:definition:type;
import silver:compiler:modification:defaultattr;
import silver:compiler:definition:flow:driver only ProductionGraph, findProductionGraph;
import silver:compiler:driver:util; -- only for productionFlowGraphs occurrence?

attribute flowEnv occurs on ProductionSignature, AspectProductionSignature;

aspect production productionDcl
top::AGDcl ::= 'abstract' 'production' id::Name ns::ProductionSignature body::ProductionBody
{
  -- TODO: bit of a hack, isn't it?
  local myGraphs :: EnvTree<ProductionGraph> = head(searchEnvTree(top.grammarName, top.compiledGrammars)).productionFlowGraphs;

  {-- Used by core to send down with .frame -}
  production myFlowGraph :: ProductionGraph = 
    findProductionGraph(fName, myGraphs);

  top.flowDefs <-
    if null(body.uniqueSignificantExpression)
    then [prodFlowDef(namedSig.outputElement.typerep.typeName, fName)]
    else [];
}

aspect production aspectProductionDcl
top::AGDcl ::= 'aspect' 'production' id::QName ns::AspectProductionSignature body::ProductionBody 
{
  -- TODO: bit of a hack, isn't it?
  local myGraphs :: EnvTree<ProductionGraph> = head(searchEnvTree(top.grammarName, top.compiledGrammars)).productionFlowGraphs;

  {-- Used by core to send down with .frame -}
  production myFlowGraph :: ProductionGraph = 
    findProductionGraph(id.lookupValue.fullName, myGraphs);
}
