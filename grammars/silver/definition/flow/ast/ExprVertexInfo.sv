grammar silver:definition:flow:ast;

{--
 - Information about an expression. Specifically, whether it corresponds to a
 - vertex in the graph, or not.
 -}
nonterminal ExprVertexInfo;


{--
 - The expression DOES have a corresponding vertex in the production graph.
 -
 - @param vertexType  How we should address its attributes
 - 
 - Do not forget we also need to address its equation (but vertexType takes care of that too)
 -
 - e.g. new(decorate rhs.a with {}) needs to emit 'rhs.a' still, even while
 -      ignoring the decorate vertex. That happens when we refer to the anonEq.
 -}
abstract production hasVertex
top::ExprVertexInfo ::=
  vertexType::VertexType
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


