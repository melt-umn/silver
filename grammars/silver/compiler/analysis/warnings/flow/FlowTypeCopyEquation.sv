grammar silver:compiler:analysis:warnings:flow;

-- Flow type check: the implicitly generated copy equations for synthesized
-- attributes due to forwarding may exceed their flow type.

-- This can happen when the forward equation itself exceeds the flow type,
-- or when a forward inh equation for an inh that the syn depends on exceeds
-- the syn's flow type.

-- This can only occur with *host-language attributes* as extension
-- attribute are required to have ft(syn) > ft(fwd).

-- The flow environment can give us the authoritative list of those attributes to check.
-- These may be from `options` and so requires the flowEnv.

aspect production productionDcl
top::AGDcl ::= 'abstract' 'production' id::Name ns::ProductionSignature body::ProductionBody
{
  -- oh no again!
  local myFlow :: EnvTree<FlowType> = head(searchEnvTree(top.grammarName, top.compiledGrammars)).grammarFlowTypes;
  local myGraphs :: EnvTree<ProductionGraph> = head(searchEnvTree(top.grammarName, top.compiledGrammars)).productionFlowGraphs;
  local myGraph :: ProductionGraph = findProductionGraph(fName, myGraphs);

  local lhsNt :: String = namedSig.outputElement.typerep.typeName;
  local hostSyns :: [String] = getHostSynsFor(lhsNt, top.flowEnv);

  -- Possible refactoring: Consider moving this check from this production to forwarding equation?

  top.errors <-
    if null(body.errors ++ ns.errors)
    && top.config.warnMissingInh
    -- Must be a forwarding production
    && !null(body.forwardExpr)
    then flatMap(raiseImplicitFwdEqFlowTypes(top.config, top.location, lhsNt, fName, _, top.flowEnv, myGraph, myFlow), hostSyns)
    else [];
}


function raiseImplicitFwdEqFlowTypes
[Message] ::= config::Decorated CmdArgs  l::Location  lhsNt::String  prod::String  attr::String  e::FlowEnv  myGraph::ProductionGraph  myFlow::EnvTree<FlowType> 
{
  -- The actual dependencies for `forward.attr`
  local fwdFlowDeps :: set:Set<String> = onlyLhsInh(expandGraph([forwardVertex(attr)], myGraph));
  -- The flow type for `attr` on `lhsNt`
  local depsForThisAttr :: set:Set<String> = inhDepsForSyn(attr, lhsNt, myFlow);
  -- Actual forwards equation deps not in the flow type for `attr`
  local diff :: [String] = set:toList(set:difference(fwdFlowDeps, depsForThisAttr));

  return case lookupSyn(prod, attr, e) of
  | eq :: _ -> []
  | [] ->
      if null(diff) then []
      else [mwdaWrn(config, l, s"In production ${prod}, the implicit copy equation for ${attr} (due to forwarding) would exceed the attribute's flow type with dependencies on ${implode(", ", diff)}")]
  end;
}

