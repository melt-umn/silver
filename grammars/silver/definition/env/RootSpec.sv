grammar silver:definition:env;
import silver:util;

synthesized attribute declaredName :: String;
synthesized attribute defs :: Decorated Defs;
synthesized attribute exportedGrammars :: [String];
synthesized attribute condBuild :: [[String]];
synthesized attribute moduleNames :: [String];
nonterminal RootSpec with defs, declaredName, exportedGrammars, condBuild, moduleNames, unparse;

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
[Decorated RootSpec] ::= n::String rs::[Decorated RootSpec]{
  return if null(rs) then [] else if head(rs).declaredName == n then [head(rs)] else getRootSpec(n, tail(rs));
}

nonterminal RootSpecUnparse with unparse;
abstract production unparseRootSpec
top::RootSpecUnparse ::= r::Decorated RootSpec{

  production attribute unparses :: [String] with ++;
  unparses := [
		"declaredName " ++ quoteString(r.declaredName),
		"moduleNames [" ++ folds(",", quoteStrings(r.moduleNames)) ++ "]",
	       	"defs " ++ r.defs.unparse,
	       	"exportedGrammars [" ++ folds(",", quoteStrings(r.exportedGrammars)) ++ "]",
	       	"condBuild [" ++ foldCB(r.condBuild) ++ "]"
	      ];

  top.unparse = folds("\n", unparses);
}

function foldCB
String ::= inp::[[String]]
{
  return if null(inp) then "" else folds(",", quoteStrings(head(inp))) ++ if null(tail(inp)) then "" else foldCB(tail(inp));
}

function quoteStrings
[String] ::= s::[String]{
  return if null(s) then [] else [quoteString(head(s))] ++ quoteStrings(tail(s));
}

function quoteString
String ::= s::String{
  return "'" ++ s ++ "'";
}

function getDeclaredNames
[String] ::= r::[Decorated RootSpec]{
  return if null(r) then [] else [head(r).declaredName] ++ getDeclaredNames(tail(r)); 
}
