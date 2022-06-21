grammar silver:compiler:driver:util;

import silver:compiler:definition:core only jarName;

synthesized attribute initRecompiledGrammars::[Decorated RootSpec];

nonterminal Compilation with config, postOps, grammarList, initRecompiledGrammars, recompiledGrammars;

flowtype postOps {config} on Compilation;

synthesized attribute postOps :: [DriverAction] with ++;
synthesized attribute grammarList :: [Decorated RootSpec];

{--
 - This abstractly represents a compilation.
 - Note, in particular, that this does not *DO* any IO at all itself.
 -
 - However, it does expect some "flow":
 -  * 'g' should be examined, and then 'top.dirtyGrammars' used to provide 'r'
 -
 - @param g  A list of grammar initially read in
 - @param r  A list of grammars that we re-compiled, due to dirtiness in 'g'
 - @param buildGrammars  The initial grammars requested built
 - @param benv  The build configuration
 -}
abstract production compilation
top::Compilation ::= g::Grammars  r::Grammars  buildGrammars::[String]  benv::BuildEnv
{
  -- the list of rootspecs coming out of g
  top.grammarList = g.grammarList;
  -- the initial list of rootspecs from g that were re-compiled
  top.initRecompiledGrammars = keepGrammars(grammarsDependedUpon, g.recompiledGrammars);
  -- the list of re-compiled rootspecs from g and r
  top.recompiledGrammars := top.initRecompiledGrammars ++ r.grammarList;
  
  g.compiledGrammars = directBuildTree(map(\ r::Decorated RootSpec -> (r.declaredName, r), g.grammarList));
  -- However, we are then forced to use the interface files that we are going to
  -- recheck in the .compiledGrammars for the recheck.
  -- That means they don't see "themselves" but their previous interface file.
  r.compiledGrammars = g.compiledGrammars;
  -- This *should* be okay, because the information should be identical in both.

  g.dependentGrammars = flatMap(
    \ r::Decorated RootSpec -> map(\ g::String -> (g, r.declaredName), r.allGrammarDependencies),
    grammarsRelevant);
  r.dependentGrammars = g.dependentGrammars;
  
  -- This determines what is actually needed in this build.
  -- For example, it excludes "options" and conditional builds that aren't
  -- actually used / triggered.
  production grammarsDependedUpon :: [String] =
    expandAllDeps(buildGrammars, [], g.compiledGrammars);
  
  -- Ditto the above, but rootspecs
  production grammarsRelevant :: [Decorated RootSpec] =
    keepGrammars(grammarsDependedUpon, g.grammarList);
  
  -- The grammars that we have recompiled, that need to be translated
  production grammarsToTranslate :: [Decorated RootSpec] = top.recompiledGrammars;

  top.postOps := [];
}

nonterminal Grammars with config, compiledGrammars, productionFlowGraphs, grammarFlowTypes, dependentGrammars, grammarList, dirtyGrammars, recompiledGrammars, jarName;

propagate dirtyGrammars, recompiledGrammars, jarName, dependentGrammars on Grammars;

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
 - Keep only a selected set of grammars.
 - @param keep  The set of grammars to keep
 - @param d  The list of grammars to filter
 -}
function keepGrammars
[Decorated RootSpec] ::= keep::[String] d::[Decorated RootSpec]
{
  return filter(\ r::Decorated RootSpec -> contains(r.declaredName, keep), d);
}

