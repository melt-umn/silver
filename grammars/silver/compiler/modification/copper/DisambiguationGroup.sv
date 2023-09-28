grammar silver:compiler:modification:copper;

terminal Disambiguation_kwd 'disambiguate' lexer classes {KEYWORD};

concrete production disambiguationGroupDcl
top::AGDcl ::= 'disambiguate' terms::TermList acode::ActionCode_c
{
  top.unparse = "disambiguate " ++ terms.unparse ++ " " ++ acode.unparse;
  propagate config, grammarName, compiledGrammars, errors;

  terms.env = top.env;
  acode.env = newScopeEnv(disambiguationActionVars ++ acode.defs ++ terms.defs, top.env);

  -- Give the group a name, deterministically, based on line number
  local loc :: Location = getParsedOriginLocation(top).fromJust;
  production fName :: String = top.grammarName ++ ":__disam" ++ toString(loc.line);
  
  -- oh no again!
  local myFlow :: EnvTree<FlowType> = head(searchEnvTree(top.grammarName, top.compiledGrammars)).grammarFlowTypes;
  local myProds :: EnvTree<ProductionGraph> = head(searchEnvTree(top.grammarName, top.compiledGrammars)).productionFlowGraphs;

  local myFlowGraph :: ProductionGraph = 
    constructAnonymousGraph(acode.flowDefs, top.env, myProds, myFlow);

  acode.frame = disambiguationContext(myFlowGraph, sourceGrammar=top.grammarName);

  top.syntaxAst :=
    [ syntaxDisambiguationGroup(fName, terms.termList, false, acode.actionCode, sourceGrammar=top.grammarName, location=loc)
    ];
}
