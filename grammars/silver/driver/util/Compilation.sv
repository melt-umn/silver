grammar silver:driver:util;

nonterminal Compilation with config, postOps, grammarList, recheckGrammars;

synthesized attribute postOps :: [Unit] with ++;
synthesized attribute grammarList :: [Decorated RootSpec];
synthesized attribute recheckGrammars :: [String];

{--
 - This abstractly represents a compilation.
 - Note, in particular, that this does not *DO* any IO at all itself.
 -
 - However, it does expect some "flow":
 -  * 'g' should be examined, and then 'top.recheckGrammars' used to provide 'r'
 -
 - @param g  A list of grammar initially read in
 - @param r  A list of grammars that we re-compiled, due to dirtiness in 'g'
 - @param buildGrammar  The initial grammar requested built
 - @param silverHome  The home location of Silver
 - @param silverGen  The directory to store generated files
 -}
abstract production compilation
top::Compilation ::= g::Grammars r::Grammars buildGrammar::String silverHome::String silverGen::String
{
  top.grammarList = g.grammarList; -- this is the stream from g ONLY

  production grammars :: [Decorated RootSpec] = g.grammarList;
  
  g.compiledGrammars = directBuildTree(map(grammarPairing, grammars));
  r.compiledGrammars = g.compiledGrammars;
  
  production attribute grammarsDependedUpon :: [String];
  grammarsDependedUpon = expandAllDeps([buildGrammar], [], g.compiledGrammars);
  
  production attribute grammarsToTranslate :: [Decorated RootSpec];
  grammarsToTranslate = keepGrammars(grammarsDependedUpon, g.translateGrammars);

  top.postOps := [];
  top.recheckGrammars = g.recheckGrammars;
}

nonterminal Grammars with config, compiledGrammars, productionFlowGraphs, grammarFlowTypes, grammarList, recheckGrammars, translateGrammars;

abstract production consGrammars
top::Grammars ::= h::RootSpec  t::Grammars
{
  top.grammarList = h :: t.grammarList;
  top.recheckGrammars = h.recheckGrammars ++ t.recheckGrammars;
  top.translateGrammars = h.translateGrammars ++ t.translateGrammars;
}

abstract production nilGrammars
top::Grammars ::=
{
  top.grammarList = [];
  top.recheckGrammars = [];
  top.translateGrammars = [];
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


