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
  eqVertex, toLhsInhDep;
derive Eq, Ord on VertexType;

synthesized attribute vertexName::String;
synthesized attribute vertexPP::String;
synthesized attribute isInhDefVertex::Boolean;

{-- Flow vertex(es) for the equation giving this FlowVertex (there may not be one!) -}
synthesized attribute eqVertex :: [FlowVertex];

{-- What inh on the lhs does some inh on this vertex type correspond to? -}
synthesized attribute toLhsInhDep::(Maybe<InhDep> ::= InhDep);

aspect default production
top::VertexType ::=
{
  top.eqVertex = [eqVertex(top)];
  top.toLhsInhDep = \ _ -> nothing();
}

{--
 - Represents the vertexes for a production lhs. You can use lhsVertexType instead of this production directly.
 -}
abstract production lhsVertexType
top::VertexType ::=
{
  top.vertexName = "top";
  top.vertexPP = "left-hand side";
  top.isInhDefVertex = false;
  top.toLhsInhDep = just;
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
  top.eqVertex = synVertex(v, transAttr) :: v.eqVertex;
  top.toLhsInhDep = \ inh -> v.toLhsInhDep(transInhDep(transAttr, inh));
}

{--
 - Represents the vertexes for the forward of a production. You can use forwardVertexType instead of this production directly.
 -}
abstract production forwardVertexType
top::VertexType ::=
{
  top.vertexName = "forward";
  top.vertexPP = "forward";
  top.isInhDefVertex = true;
}

abstract production forwardParentVertexType
top::VertexType ::=
{
  top.vertexName = "forwardParent";
  top.vertexPP = "forward parent";
  top.isInhDefVertex = false;
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
}
