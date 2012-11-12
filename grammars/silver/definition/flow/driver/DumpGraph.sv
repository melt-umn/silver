grammar silver:definition:flow:driver;

import silver:driver;
import silver:util:cmdargs;

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
ParseResult<Decorated CmdArgs> ::= args::[String]
{
  flags <- [pair("--dump-flow-graph", flag(dumpFlowGraphFlag))];
  -- omitting from descriptions deliberately!
}

aspect production run
top::RunUnit ::= iIn::IO args::[String]
{
  postOps <- if a.dumpFlowGraph then [dumpFlowGraphAction(findAllNts(allProds, allRealEnv), finalGraphs, flowTypes)] else [];
}
function findAllNts
[String] ::=  prods::[String]  realEnv::Decorated Env
{
  return nubBy(stringEq, map((.typeName), map((.typerep), map((.outputElement), map((.namedSignature), map(head, map(getValueDclAll(_, realEnv), prods)))))));
}


abstract production dumpFlowGraphAction
top::Unit ::= allNts::[String]  prodGraph::[ProductionGraph]  flowTypes::EnvTree<Pair<String String>>
{
  top.io = 
    writeFile("flow-types.dot", "digraph flow {\n" ++ generateFlowDotGraph(allNts, flowTypes) ++ "}", 
      writeFile("flow-deps.dot", "digraph flow {\n" ++ generateDotGraph(prodGraph) ++ "}",
        print("Generating flow graphs\n", top.ioIn)));

  top.code = 0;
  top.order = 0;
}


function generateFlowDotGraph
String ::= nts::[String]  ft::EnvTree<Pair<String String>>
{
  local nt::String = head(nts);
  local edges::[Pair<String String>] = searchEnvTree(nt, ft);
  
  return if null(nts) then ""
  else "subgraph \"cluster:" ++ nt ++ "\" {\nlabel=\"" ++ substring(lastIndexOf(":", nt) + 1, length(nt), nt) ++ "\";\n" ++ 
       implode("", map(makeLabelDcls(nt, _), nubBy(stringEq, expandLabels(edges)))) ++
       implode("", map(makeNtFlow(nt, _), edges)) ++
       "}\n" ++
       generateFlowDotGraph(tail(nts), ft);
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
  | productionGraph(prod, _, _, edges, _, _, _)::t ->
      "subgraph \"cluster:" ++ prod ++ "\" {\n" ++ 
      implode("", map(makeDotArrow(prod, _), edges)) ++
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

