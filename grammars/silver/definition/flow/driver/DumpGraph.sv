grammar silver:definition:flow:driver;

import silver:driver;
import silver:util:cmdargs;
import silver:util:raw:treemap as rtm;

-- This isn't exactly a warning, but it can live here for now...

synthesized attribute dumpFlowGraph :: Boolean occurs on CmdArgs;

aspect production endCmdArgs
top::CmdArgs ::= _
{
  top.dumpFlowGraph = false;
}
abstract production dumpFlowGraphFlag
top::CmdArgs ::= rest::CmdArgs
{
  top.dumpFlowGraph = true;
  forwards to rest;
}
aspect function parseArgs
Either<String  Decorated CmdArgs> ::= args::[String]
{
  flags <- [pair("--dump-flow-graph", flag(dumpFlowGraphFlag)),
            pair("--dump-flow-graphs", flag(dumpFlowGraphFlag))]; -- I mistype this a lot.
  -- omitting from descriptions deliberately!
}

aspect production compilation
top::Compilation ::= g::Grammars _ buildGrammar::String silverHome::String silverGen::String
{
  top.postOps <-
    if top.config.dumpFlowGraph
    then [dumpFlowGraphAction(prodGraph, finalGraphs, unList(rtm:toList(flowTypes)))]
    else [];
}

function unList
[Pair<String [b]>] ::= l::[Pair<String b>]
{
  local recurse :: [Pair<String [b]>] = unList(tail(l));
  
  return if null(l) then
    []
  else if !null(recurse) && head(recurse).fst == head(l).fst then
    pair(head(l).fst, head(l).snd :: head(recurse).snd) :: tail(recurse)
  else
    pair(head(l).fst, [head(l).snd]) :: recurse;
}



abstract production dumpFlowGraphAction
top::Unit ::= prodGraph::[ProductionGraph]  finalGraph::[ProductionGraph]  flowTypes::[Pair<String [g:Graph<String>]>]
{
  top.io = 
    writeFile("flow-types.dot", "digraph flow {\n" ++ generateFlowDotGraph(flowTypes) ++ "}", 
      writeFile("flow-deps-direct.dot", "digraph flow {\n" ++ generateDotGraph(prodGraph) ++ "}",
        writeFile("flow-deps-transitive.dot", "digraph flow {\n" ++ generateDotGraph(finalGraph) ++ "}",
          print("Generating flow graphs\n", top.ioIn))));

  top.code = 0;
  top.order = 0;
}


function generateFlowDotGraph
String ::= flowTypes::[Pair<String [g:Graph<String>]>]
{
  local nt::String = head(flowTypes).fst;
  local edges::[Pair<String String>] = g:toList(head(head(flowTypes).snd));
  
  return if null(flowTypes) then ""
  else "subgraph \"cluster:" ++ nt ++ "\" {\nlabel=\"" ++ substring(lastIndexOf(":", nt) + 1, length(nt), nt) ++ "\";\n" ++ 
       implode("", map(makeLabelDcls(nt, _), nubBy(stringEq, expandLabels(edges)))) ++
       implode("", map(makeNtFlow(nt, _), edges)) ++
       "}\n" ++
       generateFlowDotGraph(tail(flowTypes));
}

function expandLabels
[String] ::= l::[Pair<String String>]
{
  return if null(l) then [] else head(l).fst :: head(l).snd :: expandLabels(tail(l));
}
function makeLabelDcls
String ::= nt::String  attr::String
{
  local a :: String = substring(lastIndexOf(":", attr) + 1, length(attr), attr);
  return "\"" ++ nt ++ "/" ++ attr ++ "\"[label=\"" ++ a ++ "\"];\n";
}
function makeNtFlow
String ::= nt::String  e::Pair<String String>
{
  return "\"" ++ nt ++ "/" ++ e.fst ++ "\" -> \"" ++ nt ++ "/" ++ e.snd ++ "\";\n";
}

function generateDotGraph
String ::= specs::[ProductionGraph]
{
  return case specs of
  | [] -> ""
  | productionGraph(prod, _, _, graph, _, _)::t ->
      "subgraph \"cluster:" ++ prod ++ "\" {\n" ++ 
      implode("", map(makeDotArrow(prod, _), g:toList(graph))) ++
      "}\n" ++
      generateDotGraph(t)
  end;
}

function makeDotArrow
String ::= p::String e::Pair<FlowVertex FlowVertex>
{
  return "\"" ++ p ++ "/" ++ e.fst.dotName ++ "\" -> \"" ++ p ++ "/" ++ e.snd.dotName ++ "\";\n";
}



{--
 - DOT graph names for vertices in the production flow graphs
 -}
synthesized attribute dotName :: String occurs on FlowVertex;

aspect production lhsSynVertex
top::FlowVertex ::= attrName::String
{
  top.dotName = attrName;
}
aspect production lhsInhVertex
top::FlowVertex ::= attrName::String
{
  top.dotName = attrName;
}
aspect production rhsVertex
top::FlowVertex ::= sigName::String  attrName::String
{
  top.dotName = sigName ++ "/" ++ attrName;
}
aspect production localEqVertex
top::FlowVertex ::= fName::String
{
  top.dotName = fName;
}
aspect production localVertex
top::FlowVertex ::= fName::String  attrName::String
{
  top.dotName = fName ++ "/" ++ attrName;
}
aspect production anonEqVertex
top::FlowVertex ::= fName::String
{
  top.dotName = fName;
}
aspect production anonVertex
top::FlowVertex ::= fName::String  attrName::String
{
  top.dotName = fName ++ "/" ++ attrName;
}

