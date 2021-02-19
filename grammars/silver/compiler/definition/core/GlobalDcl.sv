grammar silver:compiler:definition:core;

import silver:compiler:definition:flow:driver only ProductionGraph, FlowType, constructAnonymousGraph;

concrete production globalValueDclConcrete
top::AGDcl ::= 'global' id::Name '::' t::TypeExpr '=' e::Expr ';'
{
  top.unparse = "global " ++ id.unparse ++ " :: " ++ t.unparse ++ " = " ++ e.unparse ++ ";\n";

  production attribute fName :: String;
  fName = top.grammarName ++ ":" ++ id.name;

  production attribute allLexicalTyVars :: [String];
  allLexicalTyVars = nub(t.lexicalTypeVariables);
  
  production attribute typeExprDefs :: [Def] with ++;
  typeExprDefs := addNewLexicalTyVars(top.grammarName, top.location, t.lexicalTyVarKinds, allLexicalTyVars);
  t.env = newScopeEnv(typeExprDefs, top.env);
  e.env = newScopeEnv(typeExprDefs, top.env);

  top.defs := [globalDef(top.grammarName, id.location, fName, t.typerep)];

  top.errors <-
    if length(getValueDclAll(fName, top.env)) > 1
    then [err(id.location, "Value '" ++ fName ++ "' is already bound.")]
    else [];

  e.originRules = [];
  e.isRoot = true;

  -- oh no again!
  local myFlow :: EnvTree<FlowType> = head(searchEnvTree(top.grammarName, top.compiledGrammars)).grammarFlowTypes;
  local myProds :: EnvTree<ProductionGraph> = head(searchEnvTree(top.grammarName, top.compiledGrammars)).productionFlowGraphs;

  local myFlowGraph :: ProductionGraph = 
    constructAnonymousGraph(e.flowDefs, top.env, myProds, myFlow);

  e.frame = globalExprContext(myFlowGraph, sourceGrammar=top.grammarName);
}
