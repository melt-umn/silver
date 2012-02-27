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
aspect production run
top::RunUnit ::= iIn::IO args::[String]
{
  flags <- [pair("--dump-deps", flag(dumpDepGraphFlag))];
  -- omitting from descriptions deliberately!
  
  postOps <- if a.dumpDepGraph then [dumpDepGraphAction(grammars)] else [];
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

