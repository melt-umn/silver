grammar silver:compiler:definition:flow:ast;

data nonterminal InhDep with vertexName, vertexOf, lhsVertex, forwardVertex;

synthesized attribute vertexOf::(FlowVertex ::= VertexType);

-- Utilities for common singleton vertex types:
synthesized attribute lhsVertex::FlowVertex;
synthesized attribute forwardVertex::FlowVertex;

production inhDep
top::InhDep ::= inhName::String
{
  top.vertexName = inhName;
  top.vertexOf = inhVertex(_, inhName);
  top.lhsVertex = inhVertex(lhsVertexType(), inhName);
  top.forwardVertex = inhVertex(forwardVertexType(), inhName);
}

production transInhDep
top::InhDep ::= transName::String inh::InhDep
{
  top.vertexName = transName ++ "." ++ inh.vertexName;
  top.vertexOf = \ vt -> inh.vertexOf(transAttrVertexType(vt, transName));
  top.lhsVertex = inhVertex(
    transAttrVertexType(lhsVertexType(), transName),
    inh.vertexName);
  top.forwardVertex = inhVertex(
    transAttrVertexType(forwardVertexType(), transName),
    inh.vertexName);
}

fun inhVertexOf FlowVertex ::= vt::VertexType inh::InhDep =
  inh.vertexOf(vt);

fun showInhDeps String ::= deps::[InhDep] =
  implode(", ", map((.vertexName), deps));

--derive Eq, Ord on InhDep;

-- More efficient equality and ordering by just comparing vertex names:
instance Eq InhDep {
  eq = \ d1::InhDep d2::InhDep -> d1.vertexName == d2.vertexName;
}

instance Ord InhDep {
  compare = \ d1::InhDep d2::InhDep -> compare(d1.vertexName, d2.vertexName);
}

