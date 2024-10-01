grammar silver:compiler:definition:flow:env;

import silver:compiler:definition:type;
import silver:compiler:definition:type:syntax;
import silver:compiler:definition:concrete_syntax;
import silver:compiler:modification:defaultattr;
import silver:compiler:modification:copper;
import silver:compiler:definition:flow:driver;
import silver:compiler:driver:util; -- only for productionFlowGraphs occurrence?

attribute flowEnv occurs on
  ProductionImplements, ProductionSignature, ProductionLHS, ProductionRHS, ProductionRHSElem,
  AspectProductionSignature, AspectProductionLHS, AspectFunctionSignature, AspectFunctionLHS,
  AspectRHS, AspectRHSElem;
propagate flowEnv on
  ProductionImplements, ProductionSignature, ProductionLHS, ProductionRHS, ProductionRHSElem,
  AspectProductionSignature, AspectProductionLHS, AspectFunctionSignature, AspectFunctionLHS,
  AspectRHS, AspectRHSElem;

aspect production productionDcl
top::AGDcl ::= 'abstract' 'production' id::Name d::ProductionImplements ns::ProductionSignature body::ProductionBody
{
  -- TODO: bit of a hack, isn't it?
  local myGraphs :: EnvTree<ProductionGraph> = head(searchEnvTree(top.grammarName, top.compiledGrammars)).productionFlowGraphs;

  {-- Used by core to send down with .frame -}
  production myFlowGraph :: ProductionGraph = 
    findProductionGraph(fName, myGraphs);

  top.flowDefs <-
    if null(body.forwardExpr)
    then [prodFlowDef(namedSig.outputElement.typerep.typeName, fName)]
    else [];
  
  -- Does this production forward to an application of the same dispatch signature
  -- with the same shared children?
  -- Note that decorating the children under a forward production attribute is also sufficient.
  -- This is also used in checking for orphaned implementation productions.
  production forwardsToImplementedSig :: Boolean = 
    case d.implementsSig of
    | just(dSig) -> any(map(
        \ e::Decorated Expr -> e.isDispatchApplication(dSig),
        body.forwardExpr ++ body.forwardProdAttrExprs))
    | _ -> false
    end;

  top.flowDefs <-
    case d.implementsSig of
    | just(dSig) when
        isExportedBy(top.grammarName, [implode(":", init(explode(":", dSig.fullName)))], top.compiledGrammars) &&
        -- Productions that forward to the same dispatch signature with the same shared children
        -- cannot affect inherited completeness.
        -- This is a potential circularity, as the prod could in principle forward to itself,
        -- but not something we want to forbid.
        !forwardsToImplementedSig ->
      [implFlowDef(dSig.fullName, fName, namedSig.inputNames)]
    | _ -> []
    end;

  top.flowDefs <- flatMap(
    \ ie::NamedSignatureElement -> occursContextDeps(namedSig, body.env, ie.typerep, rhsVertexType(ie.elementName)),
    namedSig.inputElements);
}

aspect production concreteProductionDcl
top::AGDcl ::= 'concrete' 'production' id::Name ns::ProductionSignature pm::ProductionModifiers body::ProductionBody
{
  propagate flowEnv;
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

aspect production aspectProductionLHSTyped
top::AspectProductionLHS ::= id::Name '::' t::TypeExpr
{
  propagate flowEnv;
}

aspect production aspectRHSElemTyped
top::AspectRHSElem ::= id::Name '::' t::TypeExpr
{
  propagate flowEnv;
}

aspect production aspectRHSElemSharedTyped
top::AspectRHSElem ::= '@' id::Name '::' t::TypeExpr
{
  propagate flowEnv;
}
