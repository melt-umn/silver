grammar silver:compiler:definition:flow:ast;

imports silver:langutil only unparse;

{--
 - A "classification" of FlowVertex that has ways to map attributes to vertexes.
 -
 - Quick reference: 
 - lhsVertexType(), rhsVertexType(sigName), localVertexType(fName),
 - transAttrVertexType(v, transAttr), forwardVertexType(), anonVertexType(x),
 - anonScrutineeVertexType(x), subtermVertexType(parent, prodName, sigName)
 -}
data nonterminal VertexType with
  vertexName, vertexPP, isInhDefVertex,
  synVertex, inhVertex, eqVertex, outerEqVertex,
  eqDeps, outerEqDeps, synDeps, inhDeps, fwdDeps;
derive Eq, Ord on VertexType;

synthesized attribute vertexName::String;
synthesized attribute vertexPP::String;
synthesized attribute isInhDefVertex::Boolean;

{-- FlowVertex for a synthesized attribute for this VertexType -}
synthesized attribute synVertex :: (FlowVertex ::= String);
{-- FlowVertex for a inherited attribute for this VertexType -}
synthesized attribute inhVertex :: (FlowVertex ::= String);
{-- FlowVertex for the equation giving this VertexType -}
synthesized attribute eqVertex :: FlowVertex;
{-- FlowVertex for the outer dependencies of the equation giving this VertexType -}
synthesized attribute outerEqVertex :: FlowVertex;
{-- FlowVertex dependencies of taking a reference to this VertexType -}
synthesized attribute eqDeps :: [FlowVertex];
{-- FlowVertex dependencies of constructing the outer node of this VertexType -}
synthesized attribute outerEqDeps :: [FlowVertex];
{-- FlowVertex dependencies of a synthesized attribute access on this VertexType -}
synthesized attribute synDeps :: ([FlowVertex] ::= String);
{-- FlowVertex dependencies of an inherited attribute access on this VertexType -}
synthesized attribute inhDeps :: ([FlowVertex] ::= String);
{-- FlowVertex dependencies for the forward flow type for this VertexType -}
synthesized attribute fwdDeps :: [FlowVertex];

aspect default production
top::VertexType ::=
{
  top.eqDeps = [top.eqVertex];
  top.outerEqDeps = [top.outerEqVertex];
  top.synDeps = \ attr -> top.synVertex(attr) :: top.outerEqDeps;
  top.inhDeps = \ attr -> top.inhVertex(attr) :: top.outerEqDeps;
  top.fwdDeps = top.synDeps("forward");
}


{--
 - Represents the vertexes for a production lhs.
 -}
abstract production lhsVertexType
top::VertexType ::=
{
  top.vertexName = "top";
  top.vertexPP = "left-hand side";
  top.isInhDefVertex = false;
  top.synVertex = lhsSynVertex;
  top.inhVertex = lhsInhVertex;
  top.eqVertex = lhsEqVertex();
  top.outerEqVertex = error("Shouldn't ask for an outerEqVertex on lhsVertexType");
  top.outerEqDeps = [];
  top.fwdDeps = [forwardEqVertex];  -- override for better caching
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
  top.eqVertex = rhsEqVertex(sigName);
  top.outerEqVertex = rhsOuterEqVertex(sigName);
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
  top.eqVertex = localEqVertex(fName);
  top.outerEqVertex = localOuterEqVertex(fName);
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
  top.eqVertex = v.synVertex(transAttr);
  top.outerEqVertex = transAttrOuterEqVertex(v, transAttr);
  top.eqDeps = v.synDeps(transAttr);
  top.outerEqDeps = top.outerEqVertex :: v.outerEqDeps;
}

{--
 - Represents the vertexes for the forward of a production.
 -}
abstract production forwardVertexType
top::VertexType ::=
{
  top.vertexName = "forward";
  top.vertexPP = "forward";
  top.isInhDefVertex = true;
  top.synVertex = forwardSynVertex;
  top.inhVertex = forwardInhVertex;
  top.eqVertex = forwardEqVertex;
  top.outerEqVertex = forwardOuterEqVertex();
}

abstract production forwardParentVertexType
top::VertexType ::=
{
  -- TODO: Deps on these vertices need to introduce deps on the forward vertices of the remote prod
  -- that forwarded to this sig sharing prod, even when there are override eqs.
  top.vertexName = "forwardParent";
  top.vertexPP = "forward parent";
  top.isInhDefVertex = false;
  top.synVertex = forwardParentSynVertex;
  top.inhVertex = forwardParentInhVertex;
  top.eqVertex = forwardParentEqVertex();
  top.outerEqVertex = error("Shouldn't ask for an outerEqVertex on forwardParentVertexType");
  top.outerEqDeps = [];
  -- The forward of the forward parent is the LHS of this production
  top.fwdDeps = [lhsEqVertex()];
}

{--
 - Represents the vertexes for anonymous vertex types somewhere within a production (e.g. 'decorate with' expressions).
 -}
abstract production anonVertexType
top::VertexType ::= x::String grammarName::String loc::Location
{
  top.vertexName = s"${grammarName}:${loc.unparse}:${x}";
  top.vertexPP = s"anonymous decoration site at ${grammarName}:${loc.unparse}";
  top.isInhDefVertex = true;
  top.synVertex = anonSynVertex(x, _);
  top.inhVertex = anonInhVertex(x, _);
  top.eqVertex = anonEqVertex(x);
  top.outerEqVertex = anonEqVertex(x);  -- We don't distinguish the outer eq for anon vertexes
}

{--
 - Represents the vertexes for the scrutinee when pattern matching on a reference that lacks a vertex type.
 -}
abstract production anonScrutineeVertexType
top::VertexType ::= x::String grammarName::String loc::Location
{
  top.vertexName = s"${grammarName}:${loc.unparse}:${x}";
  top.vertexPP = s"anonymous scrutinee ${grammarName}:${loc.unparse}";
  top.isInhDefVertex = false;
  top.synVertex = anonSynVertex(top.vertexName, _);
  top.inhVertex = anonInhVertex(top.vertexName, _);
  top.eqVertex = anonEqVertex(top.vertexName);
  top.outerEqVertex = anonEqVertex(top.vertexName);  -- We don't distinguish the outer eq for anon vertexes
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
  top.eqVertex = subtermEqVertex(parent, prodName, sigName);
  top.outerEqVertex = subtermOuterEqVertex(parent, prodName, sigName);
}
