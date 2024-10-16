grammar silver:compiler:definition:flow:ast;

{--
 - A "classification" of FlowVertex that has ways to map attributes to vertexes.
 -
 - Quick reference: 
 - lhsVertexType, rhsVertexType(sigName), localVertexType(fName),
 - forwardVertexType, anonVertexType(x)
 -}
data nonterminal VertexType with
  vertexName, vertexPP, isInhDefVertex,
  synVertex, inhVertex, fwdVertex, eqVertex;
derive Eq, Ord on VertexType;

synthesized attribute vertexName::String;
synthesized attribute vertexPP::String;
synthesized attribute isInhDefVertex::Boolean;

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
  top.vertexName = "top";
  top.vertexPP = "left-hand side";
  top.isInhDefVertex = false;
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
  top.vertexName = sigName;
  top.vertexPP = "child " ++ sigName;
  top.isInhDefVertex = true;
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
  top.vertexName = fName;
  top.vertexPP = "local " ++ fName;
  top.isInhDefVertex = true;
  top.synVertex = localSynVertex(fName, _);
  top.inhVertex = localInhVertex(fName, _);
  top.fwdVertex = localSynVertex(fName, "forward");
  top.eqVertex = [localEqVertex(fName)];
}

{--
 - Represents the vertexes for each translation attribute on a production lhs/rhs/local.
 -}
abstract production transAttrVertexType
top::VertexType ::= v::VertexType  transAttr::String
{
  top.vertexName = s"${v.vertexName}.${transAttr}";
  top.vertexPP = s"translation attribute ${transAttr} of ${v.vertexPP}";
  top.isInhDefVertex = v.isInhDefVertex;
  top.synVertex = \ attr::String -> v.synVertex(s"${transAttr}.${attr}");
  top.inhVertex = \ attr::String -> v.inhVertex(s"${transAttr}.${attr}");
  top.fwdVertex = v.synVertex(s"${transAttr}.forward");
  top.eqVertex = v.synVertex(transAttr) :: v.eqVertex;
}

{--
 - Represents the vertexes for the forward of a production. You can use forwardVertexType instead of this production directly.
 -}
abstract production forwardVertexType_real
top::VertexType ::=
{
  top.vertexName = "forward";
  top.vertexPP = "forward";
  top.isInhDefVertex = true;
  top.synVertex = forwardSynVertex;
  top.inhVertex = forwardInhVertex;
  top.fwdVertex = forwardSynVertex("forward");
  top.eqVertex = [forwardEqVertex_singleton];
}

abstract production forwardParentVertexType
top::VertexType ::=
{
  top.vertexName = "forwardParent";
  top.vertexPP = "forward parent";
  top.isInhDefVertex = false;
  top.synVertex = forwardParentSynVertex;
  top.inhVertex = lhsInhVertex;
  -- The forward of the forward parent is the LHS of this production, which doesn't have a vertex!
  -- This should never really be consulted in practice.
  top.fwdVertex = localEqVertex("__lhs");
  top.eqVertex = [];
}

{--
 - Represents the vertexes for anonymous vertex types somewhere within a production (e.g. 'decorate with' expressions).
 -}
abstract production anonVertexType
top::VertexType ::= x::String
{
  top.vertexName = x;
  top.vertexPP = s"anonymous decoration site ${x}";
  top.isInhDefVertex = true;
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
  top.vertexName = s"${parent.vertexName}[${prodName}:${sigName}]";
  top.vertexPP = top.vertexName;  -- Shouldn't appear in error messages?  Gets too long to spell out anyway.
  top.isInhDefVertex = false;
  top.synVertex = subtermSynVertex(parent, prodName, sigName, _);
  top.inhVertex = subtermInhVertex(parent, prodName, sigName, _);
  top.fwdVertex = subtermSynVertex(parent, prodName, sigName, "forward");
  top.eqVertex = [];
}
