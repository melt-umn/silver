grammar silver:definition:env;
import silver:util;

synthesized attribute declaredName :: String;
synthesized attribute defs :: Decorated Defs;
synthesized attribute exportedDefs :: Decorated Defs;
synthesized attribute moduleNames :: [String];
closed nonterminal RootSpec with defs, declaredName, exportedDefs, moduleNames, unparse;

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
  top.exportedDefs = emptyDefs();  
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
	       	"exportedDefs " ++ r.exportedDefs.unparse	      	
	      ];

  top.unparse = folds("\n", unparses);
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