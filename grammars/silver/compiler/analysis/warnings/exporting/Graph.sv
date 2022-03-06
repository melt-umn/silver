grammar silver:compiler:analysis:warnings:exporting;

import silver:compiler:driver;
import silver:util:cmdargs;

import silver:compiler:definition:core;
import silver:compiler:definition:env;

-- This isn't exactly a warning, but it can live here for now...

synthesized attribute dumpDepGraph :: Boolean occurs on CmdArgs;

aspect production endCmdArgs
top::CmdArgs ::= _
{
  top.dumpDepGraph = false;
}
abstract production dumpDepGraphFlag
top::CmdArgs ::= rest::CmdArgs
{
  top.dumpDepGraph = true;
  forwards to rest;
}
aspect function parseArgs
Either<String  Decorated CmdArgs> ::= args::[String]
{
  flags <- [
    flagSpec(name="--dump-import-graph", paramString="",
      help="dump a graph of dependencies between grammars as a Graphviz file",
      flagParser=flag(dumpDepGraphFlag))];
  -- not omitting from descriptions deliberately!
}
aspect production compilation
top::Compilation ::= g::Grammars  _  _  benv::BuildEnv
{
  top.postOps <- if top.config.dumpDepGraph then [dumpDepGraphAction(g.grammarList)] else [];
}

abstract production dumpDepGraphAction
top::DriverAction ::= specs::[Decorated RootSpec]
{
  top.io = writeFileT("deps.dot", "digraph deps {\n" ++ generateDotGraph(specs) ++ "}",
    eprintlnT("Generating import graph", top.ioIn));

  top.code = 0;
  top.order = 0;
}

function generateDotGraph
String ::= specs::[Decorated RootSpec]
{
  return case specs of
  | [] -> ""
  | h::t ->
      "\"" ++ h.declaredName ++ "\"[label=\"" ++ h.declaredName ++ "\"];\n" ++
      implode("", map(makeDotArrow(h.declaredName, _), h.moduleNames)) ++
      generateDotGraph(t)
  end;
}

function makeDotArrow
String ::= f::String t::String
{
  -- A heuristic to try to make the graph more readable...
  return if t == "silver:core" then "" 
  else "\"" ++ f ++ "\" -> \"" ++ t ++ "\";\n";
}

