grammar silver:compiler:definition:flow:driver;

import silver:compiler:driver;
import silver:util:cmdargs;
import silver:util:treemap as rtm;

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
  forwards to @rest;
}
aspect function parseArgs
Either<String  Decorated CmdArgs> ::= args::[String]
{
  flags <- [ flagSpec(name="--dump-flow-graph", paramString=nothing(),
               help="write the flowtypes out to several Graphviz files",
               flagParser=flag(dumpFlowGraphFlag))
             -- Ted mistyped this a lot.
           , flagSpec(name="--dump-flow-graphs", paramString=nothing(),
               help="a typo of --dump-flow-graph",
               flagParser=flag(dumpFlowGraphFlag))
           ];
  -- not omitting descriptions deliberately!
}

aspect production compilation
top::Compilation ::= g::Grammars  _  _  a::Decorated CmdArgs  benv::BuildEnv
{
  top.postOps <-
    if a.dumpFlowGraph
    then [dumpFlowGraphAction(prodGraph, rtm:values(finalGraphEnv), unList(rtm:toList(flowTypes)))]
    else [];
}

-- Coalesce sequences of pairs with the same key
-- e.g. "ab,ac,ad,bc,bd -> a[bcd],b[cd]"
function unList
[Pair<String [b]>] ::= l::[Pair<String b>]
{
  local recurse :: [Pair<String [b]>] = unList(tail(l));
  
  return if null(l) then
    []
  else if !null(recurse) && head(recurse).fst == head(l).fst then
    (head(l).fst, head(l).snd :: head(recurse).snd) :: tail(recurse)
  else
    (head(l).fst, [head(l).snd]) :: recurse;
}



abstract production dumpFlowGraphAction
top::DriverAction ::= prodGraph::[ProductionGraph]  finalGraph::[ProductionGraph]  flowTypes::[Pair<String [FlowType]>]
{
  top.run = do {
    eprintln("Generating flow graphs");
    writeFile("stitch-points.txt", generateStitchPointsDump(prodGraph));
    writeDotGraphs("flow-deps-direct.dot", prodGraph);
    writeDotGraphs("flow-deps-transitive.dot", finalGraph);
    writeTileDotGraphs("flow-deps-tile.dot", prodGraph);
    writeFile("flow-types.dot", "digraph flow {\n" ++ generateFlowDotGraph(flowTypes) ++ "}");
    return 0;
  };

  top.order = 0;
}


function generateFlowDotGraph
String ::= flowTypes::[Pair<String [FlowType]>]
{
  local nt::String = head(flowTypes).fst;
  local edges::[Pair<String String>] = g:toList(head(head(flowTypes).snd));
  
  return if null(flowTypes) then ""
  else "subgraph \"cluster:" ++ nt ++ "\" {\nlabel=\"" ++ substring(lastIndexOf(":", nt) + 1, length(nt), nt) ++ "\";\n" ++ 
       implode("", map(makeLabelDcls(nt, _), nub(expandLabels(edges)))) ++
       implode("", map(makeNtFlow(nt, _), edges)) ++
       "}\n" ++
       generateFlowDotGraph(tail(flowTypes));
}

fun expandLabels [String] ::= l::[Pair<String String>] =
  if null(l) then [] else head(l).fst :: head(l).snd :: expandLabels(tail(l));
function makeLabelDcls
String ::= nt::String  attr::String
{
  local a :: String = substring(lastIndexOf(":", attr) + 1, length(attr), attr);
  return "\"" ++ nt ++ "/" ++ attr ++ "\"[label=\"" ++ a ++ "\"];\n";
}
fun makeNtFlow String ::= nt::String  e::Pair<String String> =
  "\"" ++ nt ++ "/" ++ e.fst ++ "\" -> \"" ++ nt ++ "/" ++ e.snd ++ "\";\n";

fun writeDotGraphs IO<Unit> ::= fileName::String specs::[ProductionGraph] = do {
  writeFile(fileName, "digraph flow {\n");
  traverse_(\ spec::ProductionGraph ->
    appendFile(fileName,
      "subgraph \"cluster:" ++ spec.prod ++ "\" {\n" ++ 
      implode("", map(makeDotArrow(spec.prod, _, ""), g:toList(spec.graph))) ++
      implode("", map(makeDotArrow(spec.prod, _, " [style=dotted]"), spec.suspectEdges)) ++
      "}\n"),
    specs);
  appendFile(fileName, "}\n");
};

fun writeTileDotGraphs IO<Unit> ::= fileName::String specs::[ProductionGraph] = do {
  writeFile(fileName, "digraph flow {\n");
  traverse_(\ spec::ProductionGraph ->
    appendFile(fileName,
      "subgraph \"cluster:" ++ spec.prod ++ "\" {\n" ++ 
      implode("", map(makeDotArrow(spec.prod, _, ""), g:toList(spec.tileGraph))) ++
      "}\n"),
    specs);
  appendFile(fileName, "}\n");
};

-- "production/flowvertex" -> "production/flowvertex"
fun makeDotArrow String ::= p::String e::(FlowVertex, FlowVertex) style::String =
  "\"" ++ p ++ "/" ++ e.fst.dotName ++ "\" -> \"" ++ p ++ "/" ++ e.snd.dotName ++ "\"" ++ style ++ ";\n";



{--
 - DOT graph names for vertices in the production flow graphs
 -}
synthesized attribute dotName :: String occurs on FlowVertex;

aspect dotName on FlowVertex of
| lhsSynVertex(attrName) -> attrName
| lhsInhVertex(attrName) -> attrName
| rhsEqVertex(sigName) -> sigName
| rhsSynVertex(sigName, attrName) -> sigName ++ "/" ++ attrName
| rhsInhVertex(sigName, attrName) -> sigName ++ "/" ++ attrName
| localEqVertex(fName) -> fName
| localSynVertex(fName, attrName) -> fName ++ "/" ++ attrName
| localInhVertex(fName, attrName) -> fName ++ "/" ++ attrName
| anonEqVertex(fName) -> fName
| anonSynVertex(fName, attrName) -> fName ++ "/" ++ attrName
| anonInhVertex(fName, attrName) -> fName ++ "/" ++ attrName
| subtermEqVertex(parent, prodName, sigName) ->
  parent.synVertex(prodName ++ "@" ++ sigName).dotName  -- Hack!
| subtermSynVertex(parent, prodName, sigName, attrName) ->
  parent.synVertex(prodName ++ "@" ++ sigName ++ "/" ++ attrName).dotName  -- Hack!
| subtermInhVertex(parent, prodName, sigName, attrName) ->
  parent.synVertex(prodName ++ "@" ++ sigName ++ "/" ++ attrName).dotName  -- Hack!
end;

fun generateStitchPointsDump String ::= specs::[ProductionGraph] =
  flatMap(dumpGraphStitchPoints, specs);

fun dumpGraphStitchPoints String ::= g::ProductionGraph =
  s"${g.prod}\n${flatMap((.showStitchPoint), g.stitchPoints)}" ++
  (if null(g.sigNtStitchPoints) then ""
   else s"from signature nts\n${flatMap((.showStitchPoint), g.sigNtStitchPoints)}") ++
  "\n";

synthesized attribute showStitchPoint :: String occurs on StitchPoint;
aspect showStitchPoint on StitchPoint of
| nonterminalStitchPoint(nt, vertexType) ->
  s"\tnonterminal ${nt} at ${vertexType.vertexName}\n"
| projectionStitchPoint(prod, sourceType, targetType, prodType, attrs) ->
  s"\tprojection ${prod}@${prodType.vertexName} at ${sourceType.vertexName}, ${targetType.vertexName}\n\t\tattrs ${implode(", ", attrs)}\n"
| tileStitchPoint(prod, parentType, childTypes, syns, childInhs) ->
  s"\ttile ${prod} at ${parentType.vertexName}\n" ++
  s"\t\tchildren ${implode(", ", map(\ c::(String, VertexType) -> s"${c.1} -> ${c.2.vertexName}", childTypes))}\n" ++
  s"\t\tsyns ${implode(", ", syns)}\n" ++
  flatMap(\ ci::(String, [String]) -> s"\t\t${ci.1} inhs ${implode(", ", ci.2)}\n", childInhs)
end;
