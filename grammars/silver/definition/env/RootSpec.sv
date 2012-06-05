grammar silver:definition:env;

import silver:util:raw:treemap as rtm; -- TODO 

{--
 - RootSpecs have two functions:
 - 1. Abstract away from whether the information comes from a grammar (Root)
 -    or from an interface file.
 - 2. Abstract away from the number of files that are in the grammar.
 -    (i.e. handle a list of Root nonterminals.)
 -}
nonterminal RootSpec with defs, declaredName, exportedGrammars, optionalGrammars, condBuild, moduleNames, allGrammarDependencies, flowTypes;

{--
 - The name of the grammar this RootSpec represents.
 -}
synthesized attribute declaredName :: String;
{--
 - A list of definitions exported.
 -}
synthesized attribute defs :: Defs;
{--
 - Grammars DIRECTLY exported by this grammar.
 -}
synthesized attribute exportedGrammars :: [String];
{--
 - Grammars this grammar specifies as optional modifications.
 - (i.e. grammars that introduce more productions that do no forward)
 -}
synthesized attribute optionalGrammars :: [String];
{--
 - A list of triggered builds. Format is actually [ [build x, with gram], ... ]
 -}
synthesized attribute condBuild :: [[String]];
{--
 - A list of grammars that should be built along with this grammar.
 - e.g. all imports, exports, grammars included in parsers, etc.
 -}
synthesized attribute moduleNames :: [String];
{--
 - Echos the grammar's dependencies back upwards, so it's available
 - on RootSpecs.  This is mostly necessary because RootSpec is badly
 - designed at the moment... TODO: eventually make RootSpec non-decorated.
 -}
synthesized attribute allGrammarDependencies :: [String];
{-- flow stuff -}
synthesized attribute flowTypes :: EnvTree<Pair<String String>>;

function emptyRootSpec
Decorated RootSpec ::= 
{
  return decorate i_emptyRootSpec() with {};
}

abstract production i_emptyRootSpec
top::RootSpec ::= 
{
  top.declaredName = "_NULL_";
  top.moduleNames = [];
  top.allGrammarDependencies = [];
  top.defs = emptyDefs();
  top.exportedGrammars = [];
  top.optionalGrammars = [];
  top.condBuild = [];
  top.flowTypes = rtm:empty(compareString);
}

function getRootSpec
[Decorated RootSpec] ::= n::String rs::[Decorated RootSpec]
{
  return if null(rs) then [] else if head(rs).declaredName == n then [head(rs)] else getRootSpec(n, tail(rs));
}

function unparseRootSpec
String ::= r::Decorated RootSpec
{
  production attribute unparses :: [String] with ++;
  unparses := [
		"declaredName " ++ quoteString(r.declaredName),
		"moduleNames " ++ unparseStrings(r.moduleNames),
		"allDeps " ++ unparseStrings(r.allGrammarDependencies),
	       	"defs [" ++ unparseDefs(r.defs, []) ++ "]",
	       	"exportedGrammars " ++ unparseStrings(r.exportedGrammars),
	       	"optionalGrammars " ++ unparseStrings(r.optionalGrammars),
	       	"condBuild " ++ unparseStrings(foldr(append, [], r.condBuild)) ++ ""
	      ];

  return implode("\n", unparses);
}

