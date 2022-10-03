grammar silver:compiler:driver:util;

import silver:compiler:definition:core only jarName, grammarErrors;
import silver:util:treemap as map;

synthesized attribute initRecompiledGrammars::[Decorated RootSpec];

nonterminal Compilation with config, postOps, grammarList, allGrammars, initRecompiledGrammars, recompiledGrammars;
propagate config on Compilation;

flowtype postOps {config} on Compilation;

synthesized attribute postOps :: [DriverAction] with ++;
synthesized attribute grammarList :: [Decorated RootSpec];
synthesized attribute allGrammars :: [Decorated RootSpec];

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
  -- all compiled rootspecs from g and r
  top.allGrammars = g.grammarList ++ r.grammarList;
  -- the initial list of rootspecs from g that were re-compiled
  top.initRecompiledGrammars = keepGrammars(grammarsDependedUpon, g.recompiledGrammars);
  -- the list of re-compiled rootspecs from g and r
  top.recompiledGrammars := top.initRecompiledGrammars ++ r.grammarList;
  
  -- All grammars that were compiled due to being dirty or dependencies of dirty grammars
  -- see all the other initially compiled grammars.
  g.compiledGrammars = directBuildTree(map(\ r::Decorated RootSpec -> (r.declaredName, r), g.grammarList));
  -- However, since we don't initially know all the grammars we are going to recheck,
  -- we are forced to start with the interface files that we are going to
  -- recheck in the .compiledGrammars for the recheck.
  r.compiledGrammars = g.compiledGrammars;
  -- Since we never compile a grammar more than once, this means in case of mutual dependencies
  -- between grammars, the initially-compiled grammar may see an outdated interface file.
  -- See https://github.com/melt-umn/silver/issues/673

  g.dependentGrammars = flatMap(
    \ r::Decorated RootSpec -> map(\ g::String -> (g, r.declaredName), r.allGrammarDependencies),
    grammarsRelevant);
  -- See above comments.
  -- Assumption: if a grammar has an up-to-date interface file, then its dependencies are unchanged.
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

  local rGrammarNames :: [String] = map((.declaredName), r.grammarList);
  -- All grammars from g and r, excluding interface files from r that were later recompiled
  production allLatestGrammars :: [Decorated RootSpec] =
    r.grammarList ++
    filter(\ rs::Decorated RootSpec -> !contains(rs.declaredName, rGrammarNames), g.grammarList);

  top.postOps := [];
}

nonterminal Grammars with config, compiledGrammars, productionFlowGraphs, grammarFlowTypes, dependentGrammars, grammarList, dirtyGrammars, recompiledGrammars, jarName;

propagate config, compiledGrammars, productionFlowGraphs, grammarFlowTypes, dirtyGrammars, recompiledGrammars, jarName, dependentGrammars on Grammars;

abstract production consGrammars
top::Grammars ::= h::RootSpec  t::Grammars
{
  top.grammarList = h :: t.grammarList;

  -- Once we have compiled a grammar, replace the interface file rootspec when compiling dependent grammars
  h.compiledGrammars = map:update(h.declaredName, [h], top.compiledGrammars);
  t.compiledGrammars = h.compiledGrammars;
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

