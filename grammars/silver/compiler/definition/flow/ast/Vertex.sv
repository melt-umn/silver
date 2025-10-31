grammar silver:compiler:definition:flow:ast;

{--
 - Data structure representing vertices in the flow graph within a single production.
 -}
data FlowVertex
  = eqVertex  vertexType::VertexType
  | synVertex vertexType::VertexType attrName::String
  | inhVertex vertexType::VertexType attrName::String
with vertexName, lhsInh;

aspect vertexName on v::FlowVertex of
| eqVertex(vt) -> vt.vertexName ++ "!"
| synVertex(vt, attrName) -> vt.vertexName ++ "/" ++ attrName
| inhVertex(vt, attrName) -> vt.vertexName ++ "/" ++ attrName
end;

{--
 - The lhs inherited attribute that this vertex corresponds to.
 -}
synthesized attribute lhsInh::Maybe<InhDep>;
aspect lhsInh on v::FlowVertex of
| inhVertex(vt, attrName) -> vt.toLhsInhDep(inhDep(attrName))
| _ -> nothing()
end;


--derive Eq, Ord on FlowVertex;

-- More efficient equality and ordering by just comparing vertex names:
instance Eq FlowVertex {
  eq = \ v1::FlowVertex v2::FlowVertex -> v1.vertexName == v2.vertexName;
}

instance Ord FlowVertex {
  compare = \ v1::FlowVertex v2::FlowVertex -> compare(v1.vertexName, v2.vertexName);
}

fun flatMapVertex [FlowVertex] ::= f::([VertexType] ::= VertexType) v::FlowVertex = 
  case v of
  | eqVertex(vt) -> map(eqVertex(_), f(vt))
  | synVertex(vt, attrName) -> map(synVertex(_, attrName), f(vt))
  | inhVertex(vt, attrName) -> map(inhVertex(_, attrName), f(vt))
  end;

-- Helpers for common vertex types:
global lhsSynVertex :: (FlowVertex ::= String) = synVertex(lhsVertexType(), _);

fun rhsEqVertex FlowVertex ::= sigName::String = eqVertex(rhsVertexType(sigName));
fun rhsSynVertex FlowVertex ::= sigName::String attrName::String =
  synVertex(rhsVertexType(sigName), attrName);
fun rhsInhVertex FlowVertex ::= sigName::String inh::InhDep =
  inh.vertexOf(rhsVertexType(sigName));

fun localEqVertex FlowVertex ::= fName::String = eqVertex(localVertexType(fName));
fun localSynVertex FlowVertex ::= fName::String attrName::String =
  synVertex(localVertexType(fName), attrName);
fun localInhVertex FlowVertex ::= fName::String inh::InhDep =
  inh.vertexOf(localVertexType(fName));

global forwardEqVertex :: FlowVertex = eqVertex(forwardVertexType());
global forwardSynVertex :: (FlowVertex ::= String) =
  synVertex(forwardVertexType(), _);

