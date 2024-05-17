grammar silver:compiler:definition:core;

import silver:compiler:definition:flow:driver only ProductionGraph, FlowType, constructAnonymousGraph;

concrete production globalValueDclConcrete
top::AGDcl ::= 'global' id::Name '::' cl::ConstraintList '=>' t::TypeExpr '=' e::Expr ';'
{
  top.unparse = "global " ++ id.unparse ++ " :: " ++ cl.unparse ++ " => " ++ t.unparse ++ " = " ++ e.unparse ++ ";\n";

  production attribute fName :: String;
  fName = top.grammarName ++ ":" ++ id.name;

  production boundVars::[TyVar] = t.typerep.freeVariables;

  production attribute allLexicalTyVars :: [String];
  allLexicalTyVars = nub(t.lexicalTypeVariables);
  
  production attribute typeExprDefs :: [Def] with ++;
  typeExprDefs := addNewLexicalTyVars(top.grammarName, t.lexicalTyVarKinds, allLexicalTyVars);
  
  -- The following environments require the definitions from the type
  -- expression, as constructed above in typeExprDefs using its lexical
  -- type variables 
  cl.env = newScopeEnv(typeExprDefs, top.env);
  t.env = cl.env;
  -- The expression also requires the constraint list definitions in its env
  e.env = occursEnv(cl.occursDefs, newScopeEnv(cl.defs, cl.env));

  top.defs := [globalDef(top.grammarName, id.nameLoc, fName, boundVars, cl.contexts, t.typerep)];

  top.errors <-
    if length(getValueDclAll(fName, top.env)) > 1
    then [errFromOrigin(id, "Value '" ++ fName ++ "' is already bound.")]
    else [];

  e.originRules = [];
  e.isRoot = true;

  cl.constraintPos = globalPos(boundVars, sourceGrammar=top.grammarName);

  -- oh no again!
  local myFlow :: EnvTree<FlowType> = head(searchEnvTree(top.grammarName, top.compiledGrammars)).grammarFlowTypes;
  local myProds :: EnvTree<ProductionGraph> = head(searchEnvTree(top.grammarName, top.compiledGrammars)).productionFlowGraphs;

  local myFlowGraph :: ProductionGraph = constructAnonymousGraph(e.flowDefs, top.env, myProds, myFlow);

  e.frame = globalExprContext(fName, foldContexts(cl.contexts), t.typerep, myFlowGraph, sourceGrammar=top.grammarName);
}

-- If the global declaration does not have constraints, we unparse appropriately 
-- in the following production and forward to the generic globalValueDclConcrete
-- production with an empty constraint list
concrete production globalValueDclConcreteNoCL
top::AGDcl ::= 'global' id::Name '::' t::TypeExpr '=' e::Expr ';'
{
  top.unparse = "global " ++ id.unparse ++ " :: " ++ t.unparse ++ " = " ++ e.unparse ++ ";\n";

  forwards to globalValueDclConcrete($1, @id, $3, nilConstraint(), '=>', @t, $5, @e, $7);
}