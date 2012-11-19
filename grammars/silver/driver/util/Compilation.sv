grammar silver:driver:util;

import silver:driver only finalGraphs, flowTypes, doInterfaces, noBindingChecking, printAllBindingErrors;

nonterminal Compilation with config, postOps, grammarList;

synthesized attribute postOps :: [Unit] with ++;
synthesized attribute grammarList :: [Decorated RootSpec];

abstract production compilation
top::Compilation ::= g::Grammars buildGrammar::String silverHome::String silverGen::String
{
  top.grammarList = g.grammarList;

  g.config = top.config;
  g.compiledGrammars = directBuildTree(map(grammarPairing, top.grammarList));
  -- TODO: fix these:
  g.productionFlowGraphs = finalGraphs;
  g.grammarFlowTypes = flowTypes;
  
  production attribute grammarsDependedUpon :: [String];
  grammarsDependedUpon = expandAllDeps([buildGrammar], [], g.compiledGrammars);
  
  production attribute grammarsToTranslate :: [Decorated RootSpec];
  grammarsToTranslate = keepGrammars(grammarsDependedUpon, top.grammarList);

  top.postOps := [doInterfaces(grammarsToTranslate, silverGen)];
  top.postOps <- if top.config.noBindingChecking then [] else [printAllBindingErrors(top.grammarList)]; 
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

{--
 - Returns a pair, suitable for building an environment
 -}
function grammarPairing
Pair<String Decorated RootSpec> ::= r::Decorated RootSpec
{
  return pair(r.declaredName, r);
}

{--
 - Keep only a selected set of grammars.
 - @param keep  The set of grammars to keep
 - @param d  The list of grammars to filter
 -}
function keepGrammars
[Decorated RootSpec] ::= keep::[String] d::[Decorated RootSpec]
{
  return if null(d) then [] else (if contains(head(d).declaredName, keep) then [head(d)] else []) ++ keepGrammars(keep, tail(d));
}


