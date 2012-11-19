grammar silver:driver:util;

import silver:driver only finalGraphs, flowTypes, grammarPairing;

nonterminal Compilation with config, grammarList, grammarEnv;

synthesized attribute grammarList :: [Decorated RootSpec];
synthesized attribute grammarEnv :: EnvTree<Decorated RootSpec>;

abstract production compilation
top::Compilation ::= g::Grammars
{
  g.config = top.config;
  g.compiledGrammars = directBuildTree(map(grammarPairing, g.grammarList));
  -- TODO: fix these:
  g.productionFlowGraphs = finalGraphs;
  g.grammarFlowTypes = flowTypes;
  
  top.grammarList = g.grammarList;
  top.grammarEnv = g.compiledGrammars;
}

nonterminal Grammars with config, compiledGrammars, productionFlowGraphs, grammarFlowTypes, grammarList;

abstract production consGrammars
top::Grammars ::= h::RootSpec  t::Grammars
{
  top.grammarList = h :: t.grammarList;
}

abstract production nilGrammars
top::Grammars ::=
{
  top.grammarList = [];
}

