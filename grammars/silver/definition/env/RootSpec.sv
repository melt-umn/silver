grammar silver:definition:env;

{--
 - RootSpecs have two functions:
 - 1. Abstract away from whether the information comes from a grammar (Root)
 -    or from an interface file.
 - 2. Abstract away from the number of files that are in the grammar.
 -    (i.e. handle a list of Root nonterminals.)
 -}
nonterminal RootSpec with defs, declaredName, exportedGrammars, condBuild, moduleNames, unparse;

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
 - A list of triggered builds. Format is actually [ [build x, with gram], ... ]
 -}
synthesized attribute condBuild :: [[String]];
{--
 - A list of grammars that should be built along with this grammar.
 - e.g. all imports, exports, grammars included in parsers, etc.
 -}
synthesized attribute moduleNames :: [String];

function emptyRootSpec
Decorated RootSpec ::= 
{
  return decorate i_emptyRootSpec() with {};
}

abstract production i_emptyRootSpec
top::RootSpec ::= 
{
  top.unparse = unparseRootSpec(top).unparse;
  top.declaredName = "_NULL_";
  top.moduleNames = [];
  top.defs = emptyDefs();
  top.exportedGrammars = [];
  top.condBuild = [];
}

function getRootSpec
[Decorated RootSpec] ::= n::String rs::[Decorated RootSpec]
{
  return if null(rs) then [] else if head(rs).declaredName == n then [head(rs)] else getRootSpec(n, tail(rs));
}

-- TODO: eliminate this NT and fold this code into RootSpec.  Why is it separate?
nonterminal RootSpecUnparse with unparse;
abstract production unparseRootSpec
top::RootSpecUnparse ::= r::Decorated RootSpec
{
  production attribute unparses :: [String] with ++;
  unparses := [
		"declaredName " ++ quoteString(r.declaredName),
		"moduleNames [" ++ implode(", ", quoteStrings(r.moduleNames)) ++ "]",
	       	"defs [" ++ unparseDefs(r.defs, []) ++ "]",
	       	"exportedGrammars [" ++ implode(", ", quoteStrings(r.exportedGrammars)) ++ "]",
	       	"condBuild [" ++ foldCB(r.condBuild) ++ "]"
	      ];

  top.unparse = implode("\n", unparses);
}

function foldCB
String ::= inp::[[String]]
{ -- TODO: make this a real fold?
  return if null(inp) then "" else implode(", ", quoteStrings(head(inp))) ++ if null(tail(inp)) then "" else ", " ++ foldCB(tail(inp));
}

