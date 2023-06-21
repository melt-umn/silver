grammar silver:compiler:definition:flow:ast;

{--
 - A "classification" of FlowVertex that has ways to map attributes to vertexes.
 -
 - Quick reference: 
 - lhsVertexType, rhsVertexType(sigName), localVertexType(fName),
 - forwardVertexType, anonVertexType(x)
 -}
nonterminal VertexType with
  compareTo, isEqual, compareKey, compare,
  synVertex, inhVertex, fwdVertex, eqVertex;
propagate compareTo, isEqual, compareKey, compare on VertexType;

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
  top.synVertex = rhsSynVertex(sigName, _);
  top.inhVertex = rhsInhVertex(sigName, _);
  top.fwdVertex = rhsSynVertex(sigName, "forward");
  top.eqVertex = [];
}

{--
 - Represents the vertexes for each local within a production.
 -}
abstract production localVertexType
top::VertexType ::= fName::String
{
  top.synVertex = localSynVertex(fName, _);
  top.inhVertex = localInhVertex(fName, _);
  top.fwdVertex = localSynVertex(fName, "forward");
  top.eqVertex = [localEqVertex(fName)];
}

{--
 - Represents the vertexes for each synthesized translation attribute on a production lhs/rhs/local.
 -}
abstract production synTransAttrVertexType
top::VertexType ::= v::VertexType  attr::String
{
  top.synVertex = transAttrSynVertex(v.synVertex(attr), _);
  top.inhVertex = transAttrInhVertex(v.synVertex(attr), _);
  top.fwdVertex = transAttrSynVertex(v.synVertex(attr), "forward");
  top.eqVertex = [v.synVertex(attr)];
}

{--
 - Represents the vertexes for each inherited translation attribute on a production lhs/rhs/local.
 -}
abstract production inhTransAttrVertexType
top::VertexType ::= v::VertexType  attr::String
{
  top.synVertex = transAttrSynVertex(v.inhVertex(attr), _);
  top.inhVertex = transAttrInhVertex(v.inhVertex(attr), _);
  top.fwdVertex = transAttrSynVertex(v.inhVertex(attr), "forward");
  top.eqVertex = [v.inhVertex(attr)];
}

{--
 - Represents the vertexes for the forward of a production. You can use forwardVertexType instead of this production directly.
 -}
abstract production forwardVertexType_real
top::VertexType ::=
{
  top.synVertex = forwardSynVertex;
  top.inhVertex = forwardInhVertex;
  top.fwdVertex = forwardSynVertex("forward");
  top.eqVertex = [forwardEqVertex_singleton];
}

{--
 - Represents the vertexes for anonymous vertex types somewhere within a production (e.g. 'decorate with' expressions).
 -}
abstract production anonVertexType
top::VertexType ::= x::String
{
  top.synVertex = anonSynVertex(x, _);
  top.inhVertex = anonInhVertex(x, _);
  top.fwdVertex = anonSynVertex(x, "forward");
  top.eqVertex = [anonEqVertex(x)];
}

{--
 - Represents the vertexes corresponding to sub-terms of an expression with a known decoration site.
 -}
abstract production subtermVertexType
top::VertexType ::= parent::VertexType prodName::String sigName::String
{
  top.synVertex = subtermSynVertex(parent, prodName, sigName, _);
  top.inhVertex = subtermInhVertex(parent, prodName, sigName, _);
  top.fwdVertex = subtermSynVertex(parent, prodName, sigName, "forward");
  top.eqVertex = [];
}
