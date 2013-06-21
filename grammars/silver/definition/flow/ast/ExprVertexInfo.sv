grammar silver:definition:flow:ast;

{--
 - Information about an expression. Specifically, whether it corresponds to a
 - vertex in the graph, or not.
 -}
nonterminal ExprVertexInfo;


{--
 - The expression DOES have a corresponding vertex in the production graph.
 - @param synVertexType  Address its synthesized attributes
 - @param inhVertexType  Address its inherited attributes
 - @param keepDeps  Deps that must be emitted, even when taking advantage of the
 -          fact that this expression has a vertex.
 - N.B. new(decorate rhs.a with {}) needs to emit 'rhs.a' still, even while
 -      ignoring the decorate vertex, and that's the purpose of keepDeps.
 -}
abstract production hasVertex
top::ExprVertexInfo ::=
  synVertexType::(FlowVertex ::= String)
  inhVertexType::(FlowVertex ::= String)
  keepDeps::[FlowVertex]
{
}

{--
 - The expression does not have a corresponding vertex in the production graph
 - and so much be treated as any value.
 -}
abstract production noVertex
top::ExprVertexInfo ::=
{
}

{--
 - A simple utility for the trivial cases (e.g. childReference, etc)
 - where there are no extra deps, and both syn and inh use the same vertex type.
 -}
function simpleHasVertex
ExprVertexInfo ::= vertexType::(FlowVertex ::= String)
{
  return hasVertex(vertexType, vertexType, []);
}

