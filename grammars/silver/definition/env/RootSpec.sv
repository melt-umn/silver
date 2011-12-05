grammar silver:definition:env;
import silver:util;

synthesized attribute declaredName :: String;
synthesized attribute defs :: Defs;
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

function quoteStrings
[String] ::= s::[String]
{
  return map(quoteString, s);
}

function quoteString
String ::= s::String{
  return "'" ++ s ++ "'";
}

function getDeclaredNames
[String] ::= r::[Decorated RootSpec]{
  return if null(r) then [] else [head(r).declaredName] ++ getDeclaredNames(tail(r)); 
}

function unparseStrings
String ::= s::[String]
{
  return "[" ++ implode(",", quoteStrings(s)) ++ "]";
}
function unparseNonStrings
String ::= s::[String]
{
  return "[" ++ implode(",", s) ++ "]";
}

