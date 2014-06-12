grammar silver:definition:flow:ast;

{--
 - A "classification" of FlowVertex that has ways to map attributes to vertexes.
 -
 - Quick reference: 
 - lhsVertexType, rhsVertexType(sigName), localVertexType(fName),
 - forwardVertexType, anonVertexType(x)
 -}
nonterminal VertexType with synVertex, inhVertex, fwdVertex, eqVertex;

{-- FlowVertex for a synthesized attribute for this FlowVertex -}
synthesized attribute synVertex :: (FlowVertex ::= String);
{-- FlowVertex for a inherited attribute for this FlowVertex -}
synthesized attribute inhVertex :: (FlowVertex ::= String);
{-- FlowVertex for the forward flow type for this FlowVertex -}
synthesized attribute fwdVertex :: FlowVertex;
{-- FlowVertex for the equation giving this FlowVertex (there may not be one!) -}
synthesized attribute eqVertex :: [FlowVertex];

global lhsVertexType :: VertexType = lhsVertexType_real();
global forwardVertexType :: VertexType = forwardVertexType_real();

-- implementation detail, do no use outside this file.
global forwardEqVertex_singleton :: FlowVertex = localEqVertex("forward");
-- forwardEqVertex() == localEqVertex("forward")
-- we consider lhsSynVertex("forward") also equivalent, actually.


{--
 - Represents the vertexes for a production lhs. You can use lhsVertexType instead of this production directly.
 -}
abstract production lhsVertexType_real
top::VertexType ::=
{
  top.synVertex = lhsSynVertex;
  top.inhVertex = lhsInhVertex;
  top.fwdVertex = forwardEqVertex_singleton;
  top.eqVertex = [];
}

{--
 - Represents the vertexes for each right-hand side of a production.
 -}
abstract production rhsVertexType
top::VertexType ::= sigName::String
{
  top.synVertex = rhsVertex(sigName, _);
  top.inhVertex = rhsVertex(sigName, _);
  top.fwdVertex = rhsVertex(sigName, "forward");
  top.eqVertex = [];
}

{--
 - Represents the vertexes for each local within a production.
 -}
abstract production localVertexType
top::VertexType ::= fName::String
{
  top.synVertex = localVertex(fName, _);
  top.inhVertex = localVertex(fName, _);
  top.fwdVertex = localVertex(fName, "forward");
  top.eqVertex = [localEqVertex(fName)];
}

{--
 - Represents the vertexes for the forward of a production. You can use forwardVertexType instead of this production directly.
 -}
abstract production forwardVertexType_real
top::VertexType ::=
{
  top.synVertex = localVertex("forward", _);
  top.inhVertex = localVertex("forward", _);
  top.fwdVertex = localVertex("forward", "forward");
  top.eqVertex = [forwardEqVertex_singleton];
}

{--
 - Represents the vertexes for anonymous vertex types somewhere within a production (e.g. 'decorate with' expressions).
 -}
abstract production anonVertexType
top::VertexType ::= x::String
{
  top.synVertex = anonVertex(x, _);
  top.inhVertex = anonVertex(x, _);
  top.fwdVertex = anonVertex(x, "forward");
  top.eqVertex = [anonEqVertex(x)];
}


