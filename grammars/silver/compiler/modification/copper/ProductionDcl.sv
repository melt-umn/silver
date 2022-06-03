grammar silver:compiler:modification:copper;

terminal Semantic_kwd 'semantic' lexer classes {MODIFIER};
terminal Token_kwd 'token' lexer classes {MODIFIER};
terminal At_kwd 'at' lexer classes {MODIFIER};
disambiguate Semantic_kwd, IdLower_t { pluck Semantic_kwd; }

concrete production productionModifierSemanticToken
top::ProductionModifier ::= 'semantic' 'token' n::QNameType 'at' l::Expr
{
  top.unparse = "semantic token " ++ n.unparse ++ " at " ++ l.unparse;

  top.productionModifiers := [prodSemanticToken(n.lookupType.fullName, l.translation)];

  -- oh no again!
  local myFlow :: EnvTree<FlowType> = head(searchEnvTree(top.grammarName, top.compiledGrammars)).grammarFlowTypes;
  local myProds :: EnvTree<ProductionGraph> = head(searchEnvTree(top.grammarName, top.compiledGrammars)).productionFlowGraphs;

  local myFlowGraph :: ProductionGraph = 
    constructAnonymousGraph(l.flowDefs, top.env, myProds, myFlow);

  l.frame = reduceActionContext(top.productionSig, myFlowGraph, sourceGrammar=top.grammarName);
  l.originRules = [];
  l.isRoot = true;

  local checkLoc :: TypeCheck = check(l.typerep, nonterminalType("silver:core:Location", [], false));
  l.downSubst = emptySubst();
  checkLoc.downSubst = l.upSubst;
  l.finalSubst = checkLoc.upSubst;
  checkLoc.finalSubst = checkLoc.upSubst;

  propagate errors;
  top.errors <- 
    n.lookupType.errors ++
    if !n.lookupType.typeScheme.isTerminal
    then [err(n.location, n.unparse ++ " is not a terminal.")]
    else [];

  top.errors <-
    if checkLoc.typeerror
    then [err(l.location, s"Semantic token position expected a ${checkLoc.rightpp}, but got ${checkLoc.leftpp}")]
    else [];
}
