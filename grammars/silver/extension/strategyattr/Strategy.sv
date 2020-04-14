grammar silver:extension:strategyattr;

import silver:definition:flow:driver only ProductionGraph, FlowType, constructAnonymousGraph;
import silver:driver:util;

abstract production strategyAttributeDcl
top::AGDcl ::= a::Name recVarEnv::[Pair<String String>] e::StrategyExpr
{
  top.unparse = "strategy attribute " ++ a.unparse ++ "=" ++ e.unparse ++ ";";

  production attribute fName :: String;
  fName = top.grammarName ++ ":" ++ a.name;
  
  -- Define these directly to avoid circular dependencies,
  -- since the forward contributes to the env.
  propagate errors, moduleNames;
  
  top.errors <-
    if length(getAttrDclAll(fName, top.env)) > 1
    then [err(a.location, "Attribute '" ++ fName ++ "' is already bound.")]
    else [];
  top.errors <-
    if null(getValueDcl("core:monad:bindMaybe", top.env))
    then [err(top.location, "Strategy attributes require import of core:monad")]
    else [];
  
  -- Frame doesn't really matter, since we will re-check any expressions occuring in e when propagated.
  -- Need all this to construct a bogus frame...
  local myFlow :: EnvTree<FlowType> = head(searchEnvTree(top.grammarName, top.compiledGrammars)).grammarFlowTypes;
  local myProds :: EnvTree<ProductionGraph> = head(searchEnvTree(top.grammarName, top.compiledGrammars)).productionFlowGraphs;
  local myFlowGraph :: ProductionGraph = 
    constructAnonymousGraph(e.flowDefs, top.env, myProds, myFlow);
  e.frame = globalExprContext(myFlowGraph);
  
  e.recVarEnv = recVarEnv;
  e.outerAttr = just(a.name);
  
  local fwrd::AGDcl =
    foldr(
      appendAGDcl(_, _, location=top.location),
      defsAGDcl(
        [attrDef(
           defaultEnvItem(
             strategyDcl(
               top.grammarName, a.location, fName, freshTyVar(),
               !null(top.errors), e.isRecursive, map(fst, e.liftedStrategies), recVarEnv, e)))],
        location=top.location),
      map(
        \ d::Pair<String Decorated StrategyExpr> ->
          strategyAttributeDcl(name(d.fst, top.location), d.snd.recVarEnv, new(d.snd), location=top.location),
        e.liftedStrategies));
   
  --forwards to unsafeTrace(fwrd, print(a.name ++ " = " ++ e.unparse ++ "; lifted  " ++ implode(",  ", map(fst, e.liftedStrategies)) ++ "\n\n", unsafeIO()));
  forwards to fwrd;
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
        \ n::String ->
          attributionDcl(
            'attribute', qName(top.location, n), attl, 'occurs', 'on', nt, nttl, ';',
            location=top.location),
        at.lookupAttribute.dcl.liftedStrategyNames));
}

{--
 - Propagate a strategy attribute on the enclosing production
 - @param attr  The name of the attribute to propagate
 -}
abstract production propagateStrategy
top::ProductionStmt ::= attr::Decorated QName
{
  top.unparse = s"propagate ${attr.unparse}";
  
  production e::StrategyExpr = attr.lookupAttribute.dcl.strategyExpr;
  e.grammarName = top.grammarName;
  e.config = top.config;
  e.frame = top.frame;
  e.env = top.env;
  e.recVarEnv = attr.lookupAttribute.dcl.givenRecVarEnv;
  e.outerAttr = just(attr.lookupAttribute.fullName);
  
  production e2::StrategyExpr =
    case e.optimize of
    | just(e2) -> e2
    | nothing() -> error("optimize failed for " ++ e.unparse)
    end;
  e2.grammarName = e.grammarName;
  e2.config = e.config;
  e2.frame = e.frame;
  e2.env = e.env;
  e2.recVarEnv = e.recVarEnv;
  e2.outerAttr = e.outerAttr;
  
  -- Can't do this with forwarding to avoid circular dependency of
  -- forward -> dcl.containsErrors -> dcl.flowEnv -> forward.flowDefs
  top.errors :=
    if attr.lookupAttribute.dcl.containsErrors
    then []
    else forward.errors;
  
  local fwrd::ProductionStmt =
    foldr(
      productionStmtAppend(_, _, location=top.location),
      attributeDef(
        concreteDefLHS(qName(top.location, top.frame.signature.outputElement.elementName), location=top.location),
        '.',
        qNameAttrOccur(new(attr), location=top.location),
        '=',
        e2.translation,
        ';',
        location=top.location),
      map(
        \ n::String -> propagateOneAttr(qName(top.location, n), location=top.location),
        attr.lookupAttribute.dcl.liftedStrategyNames));
  
  --forwards to unsafeTrace(fwrd, print(attr.name ++ " on " ++ top.frame.fullName ++ " = " ++ e2.translation.unparse ++ ";\n\n", unsafeIO()));
  forwards to fwrd;
}
