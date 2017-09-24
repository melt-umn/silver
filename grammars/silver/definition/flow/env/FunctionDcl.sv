grammar silver:definition:flow:env;

import silver:definition:flow:driver only ProductionGraph, constructFunctionGraph;

aspect production functionDcl
top::AGDcl ::= 'function' id::Name ns::FunctionSignature body::ProductionBody 
{
  {-- Used by core to send down with .frame -}
  production myFlowGraph :: ProductionGraph = 
    constructFunctionGraph(namedSig, top.flowEnv, top.env);

  top.flowDefs = body.flowDefs;
}

aspect production aspectFunctionDcl
top::AGDcl ::= 'aspect' 'function' id::QName ns::AspectFunctionSignature body::ProductionBody 
{
  {-- Used by core to send down with .frame -}
  production myFlowGraph :: ProductionGraph = 
    constructFunctionGraph(namedSig, top.flowEnv, top.env);

  top.flowDefs = body.flowDefs;
}

