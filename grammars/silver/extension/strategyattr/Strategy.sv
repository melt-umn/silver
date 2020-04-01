grammar silver:extension:strategyattr;

import silver:definition:flow:driver only ProductionGraph, FlowType, constructAnonymousGraph;
import silver:driver:util;

concrete production strategyAttributeDcl
top::AGDcl ::= 'strategy' 'attribute' a::Name '=' e::StrategyExpr ';'
{
  top.unparse = "strategy attribute " ++ a.unparse ++ ";";

  production attribute fName :: String;
  fName = top.grammarName ++ ":" ++ a.name;
  
  -- Define these directly to avoid flow errors
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
  
  forwards to
    defsAGDcl(
      [attrDef(defaultEnvItem(strategyDcl(top.grammarName, a.location, fName, freshTyVar(), e)))],
      location=top.location);
}

abstract production strategyAttributionDcl
top::AGDcl ::= at::Decorated QName attl::BracketedOptTypeExprs nt::QName nttl::BracketedOptTypeExprs
{
  forwards to
    defaultAttributionDcl(
      at,
      if length(attl.types) > 0
      then attl
      else
        botlSome(
          '<',
          typeListSingle(
            nominalTypeExpr(nt.qNameType, nttl, location=top.location),
            location=top.location),
          '>', location=top.location),
      nt, nttl,
      location=top.location);
}

{--
 - Propagate a strategy attribute on the enclosing production
 - @param attr  The name of the attribute to propagate
 -}
abstract production propagateStrategy
top::ProductionStmt ::= attr::Decorated QName
{
  -- No explicit errors, for now.  The only conceivable issue is the attribute not
  -- occuring on the LHS but this should be caught by the forward errors.  
  
  -- Generate the arguments for the constructor
  local topName::QName = qName(top.location, top.frame.signature.outputElement.elementName);
  local prodName::QName = qName(top.location, top.frame.fullName);
  prodName.grammarName = top.grammarName;
  prodName.config = top.config;
  prodName.env = top.env;

  local inputs :: [Expr] = 
    map(makeArg(top.location, top.env, attr, _), top.frame.signature.inputElements);
  local annotations :: [Pair<String Expr>] = 
    map(makeAnnoArg(top.location, topName, _), top.frame.signature.namedInputElements);

  -- Construct an attribute def and call with the generated arguments
  forwards to 
    attributeDef(
      concreteDefLHS(topName, location=top.location),
      '.',
      qNameAttrOccur(new(attr), location=top.location),
      '=',
      mkFullFunctionInvocation(
        top.location,
        baseExpr(prodName, location=top.location),
        inputs,
        annotations),
      ';',
      location=top.location);
}
