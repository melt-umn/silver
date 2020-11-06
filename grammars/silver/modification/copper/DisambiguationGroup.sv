grammar silver:modification:copper;

terminal Disambiguation_kwd 'disambiguate' lexer classes {KEYWORD};

concrete production disambiguationGroupDcl
top::AGDcl ::= 'disambiguate' terms::TermList acode::ActionCode_c
{
  top.unparse = "disambiguate " ++ terms.unparse ++ " " ++ acode.unparse;

  top.errors := terms.errors ++ acode.errors;

  acode.env = newScopeEnv(disambiguationActionVars ++ acode.defs ++ terms.defs, top.env);

  -- Give the group a name, deterministically, based on line number
  production fName :: String = top.grammarName ++ ":__disam" ++ toString(top.location.line);
  
  -- oh no again!
  local myFlow :: EnvTree<FlowType> = head(searchEnvTree(top.grammarName, top.compiledGrammars)).grammarFlowTypes;
  local myProds :: EnvTree<ProductionGraph> = head(searchEnvTree(top.grammarName, top.compiledGrammars)).productionFlowGraphs;

  local myFlowGraph :: ProductionGraph = 
    constructAnonymousGraph(acode.flowDefs, top.env, myProds, myFlow);

  acode.frame = disambiguationContext(myFlowGraph);

  top.syntaxAst := [syntaxDisambiguationGroup(fName, terms.termList, false, acode.actionCode)];
}

