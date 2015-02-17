grammar silver:analysis:warnings:exporting;

import silver:driver;
import silver:util:cmdargs;

import silver:definition:core;
import silver:definition:env;

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
  flags <- [pair("--dump-import-graph", flag(dumpDepGraphFlag))];
  -- omitting from descriptions deliberately!
}
aspect production compilation
top::Compilation ::= g::Grammars  _  buildGrammar::String  benv::BuildEnv
{
  top.postOps <- if top.config.dumpDepGraph then [dumpDepGraphAction(g.grammarList)] else [];
}

abstract production dumpDepGraphAction
top::Unit ::= specs::[Decorated RootSpec]
{
  top.io = writeFile("deps.dot", "digraph deps {\n" ++ generateDotGraph(specs) ++ "}",
    print("Generating import graph\n", top.ioIn));

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
  -- A few stupid heuristics to try to make the graphs more readable:
  return if t == "core" || startsWith("silver:util", t) || startsWith("silver:langutil", t) then "" 
  else "\"" ++ f ++ "\" -> \"" ++ t ++ "\";\n";
}

