grammar silver:compiler:driver:util;

import silver:compiler:definition:core only jarName, grammarErrors;
import silver:util:treemap as map;
import silver:util:treeset as set;
import silver:util:graph as g;
import silver:util:cmdargs;
import silver:compiler:analysis:warnings:flow only warnMissingInh;

synthesized attribute initDirtyGrammars::[String];

data nonterminal Compilation with
  initialChecks, postOps, grammarList, reGrammarList, allGrammars, recompiledGrammars, initDirtyGrammars;

synthesized attribute initialChecks :: IOErrorable<()> with applyFirst;
synthesized attribute postOps :: [DriverAction] with ++;
synthesized attribute grammarList :: [Decorated RootSpec];
synthesized attribute reGrammarList :: [Decorated RootSpec];
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
 - @param a  The command line arguments
 - @param benv  The build configuration
 -}
abstract production compilation
top::Compilation ::= g::Grammars  r::Grammars  buildGrammars::[String]  a::Decorated CmdArgs  benv::BuildEnv
{
  -- the list of rootspecs coming out of g
  top.grammarList = g.grammarList;
  -- the list of rootspecs coming out of r
  top.reGrammarList = r.grammarList;
  -- the list of rootspecs from g and r, excluding grammars from g that were later recompiled in r
  top.allGrammars = r.grammarList ++ excludeGrammars(rGrammarNames, g.grammarList);
  -- the list of re-compiled rootspecs from g and r
  top.recompiledGrammars :=
    excludeGrammars(rGrammarNames, keepGrammars(grammarsDependedUpon, g.recompiledGrammars)) ++
    r.grammarList;
  -- the initial list of grammar names from g known to be in need of recompilation
  top.initDirtyGrammars = set:toList(set:intersect(
    -- grammars from g that we think are dirty
    flatMap(compose(set:fromList, (.dirtyGrammars)), grammarsRelevant),
    -- grammars that are from (or depend on) an interface file, and thus may be dirty
    flatMap(g:edgesFrom(_, g:transitiveClosure(g:add(g.ifcDependentGrammars, g:empty()))), g.cachedGrammars)));
  
  -- All grammars that were compiled due to being dirty or dependencies of dirty grammars
  -- see all the other initially compiled grammars.
  g.compiledGrammars = directBuildTree(map(\ r::Decorated RootSpec -> (r.declaredName, r), g.grammarList));
  -- However, since we don't initially know all the grammars we are going to recheck,
  -- we are forced to start with the interface files that we are going to
  -- recheck in the .compiledGrammars for the recheck.
  r.compiledGrammars = g.compiledGrammars;
  -- Note that if a grammar wasn't initially recompiled in g, then grammars depending on
  -- it in g will see its old interface file.
  -- If we later decide that the grammar needs to be recompiled in r,
  -- then those grammars depending on it may be re-compiled again, replacing those rootspecs
  -- prior to type checking and translation.

  -- All pairs of grammars (a, b) where b should be rebuilt if the interface of a changes.
  g.ifcDependentGrammars = flatMap(
    \ r::Decorated RootSpec -> map(\ g::String -> (g, r.declaredName), r.allGrammarDependencies),
    grammarsRelevant);
  -- See above comments.
  -- Assumption: if a grammar has an up-to-date interface file, then its dependencies are unchanged.
  r.ifcDependentGrammars = g.ifcDependentGrammars;

  -- All pairs of grammars (a, b) where b should be rebuilt if the AST of a changes in any way.
  production attribute astDependentGrammars :: [(String, String)] with ++;
  astDependentGrammars := [];
  g.astDependentGrammars = astDependentGrammars;
  r.astDependentGrammars = astDependentGrammars;

  -- If we are running the flow analysis, then we need to consider any change to a dependency
  -- as cause for a rebuild, since we ignore flow defs when comparing interface files.
  -- This also avoids a circularity issue: computing whether an interface file
  -- changed depends on error checking, which depends on computed flow types when
  -- the flow analysis is enabled, which depends on which grammars were compiled.
  astDependentGrammars <-
    if a.warnMissingInh then g.ifcDependentGrammars else [];

  g.config = a;
  r.config = a;
  
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

  -- Initial checks that should be performed on grammars from g, prior to computing defs/errors
  top.initialChecks := pure(());

  -- Actions to be performed after the initial build (check errors, generate translation, etc.)
  top.postOps := [];
}

nonterminal Grammars with
  config, compiledGrammars, productionFlowGraphs, grammarFlowTypes, ifcDependentGrammars, astDependentGrammars,
  grammarList, cachedGrammars, dirtyGrammars, recompiledGrammars, jarName, includedJars;

propagate
  config, productionFlowGraphs, grammarFlowTypes, cachedGrammars, dirtyGrammars, recompiledGrammars, jarName,
  ifcDependentGrammars, astDependentGrammars, includedJars
  on Grammars;

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
fun keepGrammars [Decorated RootSpec] ::= keep::[String] d::[Decorated RootSpec] =
  filter(\ r::Decorated RootSpec -> contains(r.declaredName, keep), d);

{--
 - Exclude only a selected set of grammars.
 - @param exclude  The set of grammars to exclude
 - @param d  The list of grammars to filter
 -}
fun excludeGrammars [Decorated RootSpec] ::= exclude::[String] d::[Decorated RootSpec] =
  filter(\ r::Decorated RootSpec -> !contains(r.declaredName, exclude), d);

