grammar silver:analysis:warnings:flow;

-- Flow type check: the implicitly generated copy equations for synthesized
-- attributes due to forwarding may exceed their flow type.

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
  
  local transitiveDeps :: [FlowVertex] = expandGraph([forwardEqVertex()], findProductionGraph(fName, myGraphs));
  local fwdFlowDeps :: set:Set<String> = onlyLhsInh(transitiveDeps);

  local lhsNt :: String = namedSig.outputElement.typerep.typeName;
  local hostSyns :: [String] = getHostSynsFor(lhsNt, top.flowEnv);

  -- Possible refactoring: Consider moving this check from this production to forwarding equation?

  top.errors <-
    if null(body.errors ++ ns.errors)
    && (top.config.warnAll || top.config.warnMissingInh)
    -- Must be a forwarding production
    && !null(body.uniqueSignificantExpression)
    then flatMap(raiseImplicitFwdEqFlowTypes(top.location, lhsNt, fName, _, top.flowEnv, fwdFlowDeps, myFlow), hostSyns)
    else [];
}


function raiseImplicitFwdEqFlowTypes
[Message] ::= l::Location  lhsNt::String  prod::String  attr::String  e::Decorated FlowEnv  fwdFlowDeps::set:Set<String>  myFlow::EnvTree<FlowType>
{
  -- The flow type for `attr` on `lhsNt`
  local depsForThisAttr :: set:Set<String> = inhDepsForSyn(attr, lhsNt, myFlow);
  -- Actual forwards equation deps not in the flow type for `attr`
  local diff :: [String] = set:toList(set:difference(fwdFlowDeps, depsForThisAttr));

  return case lookupSyn(prod, attr, e) of
  | eq :: _ -> []
  | [] ->
      if null(diff) then []
      else 
      [wrn(l, s"In production ${prod}, the implicit copy equation for ${attr} (due to forwarding) would exceed the attribute's flow type because the production forward equation depends on ${implode(", ", diff)}")]
  end;
}

