grammar silver:compiler:definition:flow:env;

import silver:compiler:definition:type;
import silver:compiler:modification:defaultattr;
import silver:compiler:definition:flow:driver;
import silver:compiler:driver:util; -- only for productionFlowGraphs occurrence?

attribute flowEnv occurs on
  ProductionSignature, ProductionLHS, ProductionRHS, ProductionRHSElem,
  AspectProductionSignature, AspectProductionLHS, AspectFunctionSignature, AspectFunctionLHS,
  AspectRHS, AspectRHSElem;

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

  top.flowDefs <- flatMap(
    \ ie::NamedSignatureElement -> occursContextDeps(namedSig, body.env, ie.typerep, rhsVertexType(ie.elementName)),
    namedSig.inputElements);
}

aspect production aspectProductionDcl
top::AGDcl ::= 'aspect' 'production' id::QName ns::AspectProductionSignature body::ProductionBody 
{
  -- TODO: bit of a hack, isn't it?
  local myFlow :: EnvTree<FlowType> = head(searchEnvTree(top.grammarName, top.compiledGrammars)).grammarFlowTypes;
  local myGraphs :: EnvTree<ProductionGraph> = head(searchEnvTree(top.grammarName, top.compiledGrammars)).productionFlowGraphs;

  {-- Used by core to send down with .frame -}
  production myFlowGraph :: ProductionGraph =
    case searchEnvTree(id.lookupValue.fullName, myGraphs) of
    | g :: _ -> g
    -- In case we didn't find the flow graph (the aspected production doesn't exist?)
    -- build an anonymous flow graph to use instead as a "best effort".
    | [] -> constructAnonymousGraph(body.flowDefs, top.env, myGraphs, myFlow)
    end;
}
