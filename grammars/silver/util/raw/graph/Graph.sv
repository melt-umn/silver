grammar silver:util:raw:graph;

import silver:util:raw:treeset as set;

{--
 - A primitive graph representation. Edges has no special value
 - they either exist or do not.
 -}
type Graph<a> foreign;


{--
 - Returns an empty graph using the specified vertex comparator.
 -}
function empty
Graph<a> ::= comparator::(Integer ::= a a)
{
  return error("NYI");
} foreign {
  "java" : return "common.rawlib.RawGraph.empty(%comparator%)";
}

{--
 - Adds a list of edges to the map
 -}
function add
Graph<a> ::= lst::[Pair<a a>] graph::Graph<a>
{
  return error("NYI");
} foreign {
  "java" : return "common.rawlib.RawGraph.add(%lst%, (java.util.TreeMap<Object,java.util.TreeSet<Object>>)%graph%)";
}

{--
 - Returns a set of edges FROM a particular vertex
 -}
function edgesFrom
set:Set<a> ::= vertex::a graph::Graph<a>
{
  return error("NYI");
} foreign {
  "java" : return "common.rawlib.RawGraph.edgesFrom(%vertex%, (java.util.TreeMap<Object,java.util.TreeSet<Object>>)%graph%)";
}

{--
 - Determines whether an edge already exists in the graph.
 -}
function contains
Boolean ::= edge::Pair<a a> graph::Graph<a>
{
  return error("NYI");
} foreign {
  "java" : return "common.rawlib.RawGraph.contains(%edge%, (java.util.TreeMap<Object,java.util.TreeSet<Object>>)%graph%)";
}

{--
 - Returns the list of edges that make up the graph.
 -}
function toList
[Pair<a a>] ::= graph::Graph<a>
{
  return error("NYI");
} foreign {
  "java" : return "common.rawlib.RawGraph.toList((java.util.TreeMap<Object,java.util.TreeSet<Object>>)%graph%)";
}

