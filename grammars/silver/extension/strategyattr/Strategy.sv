grammar silver:extension:strategyattr;

import silver:definition:flow:driver only ProductionGraph, FlowType, constructAnonymousGraph;
import silver:driver:util;

concrete production strategyAttributeDcl
top::AGDcl ::= 'strategy' 'attribute' a::Name '=' e::StrategyExpr ';'
{
  top.unparse = "strategy attribute " ++ a.unparse ++ ";";

  production attribute fName :: String;
  fName = top.grammarName ++ ":" ++ a.name;
  
  -- Define these directly to avoid circular dependencies
  propagate errors, moduleNames;
  
  top.errors <-
    if length(getAttrDclAll(fName, top.env)) > 1
    then [err(a.location, "Attribute '" ++ fName ++ "' is already bound.")]
    else [];
  
  -- Frame doesn't really matter, since we will re-check any expressions occuring in e when propagated.
  -- Need all this to construct a bogus frame...
  local myFlow :: EnvTree<FlowType> = head(searchEnvTree(top.grammarName, top.compiledGrammars)).grammarFlowTypes;
  local myProds :: EnvTree<ProductionGraph> = head(searchEnvTree(top.grammarName, top.compiledGrammars)).productionFlowGraphs;
  local myFlowGraph :: ProductionGraph = 
    constructAnonymousGraph(e.flowDefs, top.env, myProds, myFlow);
  e.frame = globalExprContext(myFlowGraph);
  
  e.genName = a.name;
  
  forwards to
    defsAGDcl(
      [attrDef(defaultEnvItem(strategyDcl(top.grammarName, a.location, fName, freshTyVar(), e)))],
      location=top.location);
}

abstract production strategyAttributionDcl
top::AGDcl ::= at::Decorated QName attl::BracketedOptTypeExprs nt::QName nttl::BracketedOptTypeExprs
{
  local localErrors::[Message] =
    attl.errors ++ attl.errorsTyVars ++ nt.lookupType.errors ++ nttl.errors ++ nttl.errorsTyVars ++
    if length(attl.types) > 0
    then [err(attl.location, "Explicit type arguments are not allowed for strategy attributes")]
    else [];
  -- TODO: Check that the type parameters of any rules of type nt match nttl
  
  top.errors := if !null(localErrors) then localErrors else forward.errors;

  forwards to
    foldr(
      appendAGDcl(_, _, location=top.location),
      defaultAttributionDcl(
        at,
        botlSome(
          '<',
          typeListSingle(
            nominalTypeExpr(nt.qNameType, nttl, location=top.location),
            location=top.location),
          '>', location=top.location),
        nt, nttl,
        location=top.location),
      map(
        \ s::Pair<String StrategyExpr> ->
          attributionDcl(
            'attribute', qName(top.location, s.fst), attl, 'occurs', 'on', nt, nttl, ';',
            location=top.location),
        at.lookupAttribute.dcl.liftedStrategies));
}

{--
 - Propagate a strategy attribute on the enclosing production
 - @param attr  The name of the attribute to propagate
 -}
abstract production propagateStrategy
top::ProductionStmt ::= attr::Decorated QName
{

  -- TODO: Don't generate code if attr decl contains errors
  forwards to error("todo");
}
