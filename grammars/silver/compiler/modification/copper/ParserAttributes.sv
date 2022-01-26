grammar silver:compiler:modification:copper;

concrete production attributeDclParser
top::AGDcl ::= 'parser' 'attribute' a::Name '::' te::TypeExpr 'action' acode::ActionCode_c ';'
{
  top.unparse = "parser attribute " ++ a.name ++ " :: " ++ te.unparse ++ " action " ++ acode.unparse ++ " ;" ;

  production attribute fName :: String;
  fName = top.grammarName ++ ":" ++ a.name;

  top.defs := [parserAttrDef(top.grammarName, a.location, fName, te.typerep)];

  propagate errors;
  top.errors <- if length(getValueDclAll(fName, top.env)) > 1
                then [err(a.location, "Attribute '" ++ fName ++ "' is already bound.")]
                else [];
  top.errors <- te.errorsKindStar;
  
  -- oh no again!
  local myFlow :: EnvTree<FlowType> = head(searchEnvTree(top.grammarName, top.compiledGrammars)).grammarFlowTypes;
  local myProds :: EnvTree<ProductionGraph> = head(searchEnvTree(top.grammarName, top.compiledGrammars)).productionFlowGraphs;

  local myFlowGraph :: ProductionGraph = 
    constructAnonymousGraph(acode.flowDefs, top.env, myProds, myFlow);

  acode.frame = actionContext(myFlowGraph, sourceGrammar=top.grammarName);
  acode.env = newScopeEnv(acode.defs, top.env);
  
  top.syntaxAst := [syntaxParserAttribute(fName, te.typerep, acode.actionCode, location=top.location)];
}

concrete production attributeAspectParser
top::AGDcl ::= 'aspect' 'parser' 'attribute' a::QName 'action' acode::ActionCode_c ';'
{
  top.unparse = "aspect parser attribute " ++ a.name ++ " action " ++ acode.unparse ++ " ;" ;

  production attribute fName :: String;
  fName = a.lookupValue.dcl.fullName;

  top.defs := [];

  propagate errors;
  top.errors <- if null(a.lookupValue.dcls)
                then [err(a.location, "Undefined attribute '" ++ a.name ++ "'.")]
                else [];
  
  -- oh no again!
  local myFlow :: EnvTree<FlowType> = head(searchEnvTree(top.grammarName, top.compiledGrammars)).grammarFlowTypes;
  local myProds :: EnvTree<ProductionGraph> = head(searchEnvTree(top.grammarName, top.compiledGrammars)).productionFlowGraphs;

  local myFlowGraph :: ProductionGraph = 
    constructAnonymousGraph(acode.flowDefs, top.env, myProds, myFlow);

  acode.frame = actionContext(myFlowGraph, sourceGrammar=top.grammarName);
  acode.env = newScopeEnv(acode.defs, top.env);
  
  top.syntaxAst := [syntaxParserAttributeAspect(fName, acode.actionCode, location=top.location)];
}
