grammar silver:compiler:definition:flow:env;

import silver:compiler:definition:type;
import silver:compiler:modification:defaultattr;
import silver:compiler:definition:flow:driver only ProductionGraph, findProductionGraph;
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
    \ ie::NamedSignatureElement -> occursContextDeps(namedSig, ie.typerep, rhsVertexType(ie.elementName)),
    namedSig.inputElements);
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

monoid attribute occursContextDeps::[(String, String, [String])]  -- (type name, syn, inhs)
  occurs on NamedSignature, Contexts, Context;
propagate occursContextDeps on NamedSignature, Contexts;
aspect occursContextDeps on Context using := of
| synOccursContext(syn, _, _, inhs, ntty) -> [(ntty.typeName, syn, inhs.inhSetMembers)]
| _ -> []
end;

function occursContextDeps
[FlowDef] ::= ns::NamedSignature t::Type vt::VertexType
{
  return map(
    \ synDeps::(String, [String]) -> synOccursContextEq(ns.fullName, vt, synDeps.fst, synDeps.snd),
    lookupAll(t.typeName, ns.occursContextDeps));
}
