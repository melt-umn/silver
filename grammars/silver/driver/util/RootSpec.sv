grammar silver:driver:util;

import silver:definition:core only Grammar, errors, grammarName, importedDefs, grammarDependencies, globalImports;
import silver:definition:env:env_parser only IRoot;
import silver:definition:flow:env only flowEnv, flowDefs, fromFlowDefs;
import silver:definition:flow:ast only nilFlow, consFlow, FlowDef;

{--
 - A representation of a grammar, from an unknown source.
 -}
nonterminal RootSpec with
  -- compiler-wide inherited attributes
  config, compiledGrammars, productionFlowGraphs, grammarFlowTypes,
  -- synthesized attributes
  declaredName, moduleNames, exportedGrammars, optionalGrammars, condBuild, allGrammarDependencies,
  defs, errors;


{--
 - Create a RootSpec from a real grammar, a set of .sv files.
 -}
abstract production grammarRootSpec
top::RootSpec ::= g::Grammar  grammarName::String
{
  g.grammarName = grammarName;
  
  -- Create the environments for this grammar
  g.env = toEnv(g.defs);
  g.globalImports = toEnv(g.importedDefs);
  
  -- This grammar, its direct imports, and only transitively close over exports and TRIGGERED conditional imports.
  -- i.e. these are the things that we really, truly depend upon.
  local actualDependencies :: [String] = makeSet(computeDependencies(grammarName :: top.moduleNames, top.compiledGrammars));

  -- Compute flow information for this grammar, (closing over imports and options, too:)
  local depsPlusOptions :: [String] = makeSet(completeDependencyClosure(actualDependencies, top.compiledGrammars));
  g.grammarDependencies = actualDependencies;
  g.flowEnv = fromFlowDefs(foldr(consFlow, nilFlow(), gatherFlowEnv(depsPlusOptions, top.compiledGrammars)));
  
  -- Echo down global compiler info
  g.config = top.config;
  g.compiledGrammars = top.compiledGrammars;
  
  top.declaredName = g.declaredName;
  top.moduleNames = makeSet(g.moduleNames);
  top.exportedGrammars = g.exportedGrammars;
  top.optionalGrammars = g.optionalGrammars;
  top.condBuild = g.condBuild;
  top.allGrammarDependencies = actualDependencies;
  
  top.defs = g.defs;
  top.errors := g.errors;
}

{--
 - Create a RootSpec from an interface file, representing a grammar.
 -}
abstract production interfaceRootSpec
top::RootSpec ::= p::IRoot
{
  p.grammarName = p.declaredName;

  top.declaredName = p.declaredName; 
  top.moduleNames = p.moduleNames;
  top.exportedGrammars = p.exportedGrammars;
  top.optionalGrammars = p.optionalGrammars;
  top.condBuild = p.condBuild;
  top.allGrammarDependencies = p.allGrammarDependencies;

  top.defs = p.defs;
  top.errors := []; -- TODO: consider getting grammarName and comparing against declaredName?
}

{--
 - How RootSpecs are turned into interface files shouldn't change
 - depending on what the source it, so we give this function externally
 - to the productions, instead of as an attribute.
 -}
function unparseRootSpec
String ::= r::Decorated RootSpec
{
  production attribute unparses :: [String] with ++;
  unparses := [
		"declaredName " ++ quoteString(r.declaredName),
		"moduleNames " ++ unparseStrings(r.moduleNames),
		"allDeps " ++ unparseStrings(r.allGrammarDependencies),
	       	"exportedGrammars " ++ unparseStrings(r.exportedGrammars),
	       	"optionalGrammars " ++ unparseStrings(r.optionalGrammars),
	       	"condBuild " ++ unparseStrings(foldr(append, [], r.condBuild)),
	       	"defs " ++ unparseDefs(r.defs, [])
	      ];

  return implode("\n", unparses);
}

{--
 - All grammar names mentioned by this root spec (not transitive!)
 -}
function mentionedGrammars
[String] ::= r::Decorated RootSpec
{
  return makeSet(r.moduleNames ++ foldr(append, [], r.condBuild) ++ r.optionalGrammars);
}

function gatherFlowEnv
[FlowDef] ::= deps::[String]  e::EnvTree<Decorated RootSpec>
{
  return if null(deps) then []
         else case searchEnvTree(head(deps), e) of
              | r :: _ -> r.flowDefs ++ gatherFlowEnv(tail(deps), e)
              | [] -> gatherFlowEnv(tail(deps), e)
              end;
}

