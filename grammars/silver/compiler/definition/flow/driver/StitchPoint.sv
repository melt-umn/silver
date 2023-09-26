grammar silver:compiler:definition:flow:driver;

data nonterminal StitchPoint with stitchEdges;

synthesized attribute stitchEdges :: ([Pair<FlowVertex FlowVertex>] ::= EnvTree<FlowType> EnvTree<ProductionGraph>);

{--
 - Introduces internal edges corresponding to the flow type of 'nt'
 - to the specified vertex type.
 -}
abstract production nonterminalStitchPoint
top::StitchPoint ::= nt::String  vertexType::VertexType
{
  top.stitchEdges = \ flowTypes::EnvTree<FlowType> prodGraphs::EnvTree<ProductionGraph> ->
    map(flowTypeEdge(vertexType, _),
      g:toList(findFlowType(nt, flowTypes)));
}


{--
 - Given production 'prod :: LHS ::= rhs1::RHS1'
 - with all inherited attributes on 'RHS1' as 'attrs'.
 -
 - Finds all edges for each 'attrs' from 'prodType' (here, rhsVertex("rhs1",_))
 - to LHS INH in the production 'prod'.
 -
 - We emit edges for the local production from
 - 'sourceType' (e.g. localVertex("patvar23", _)) to 'targetType' (e.g. rhsVertex("e", _))
 - corresponding to these edges.
 -
 - For example, if 'prod' has (rhs1, env) -> (lhs, env),
 - then here we would emit (patvar23, env) -> (e, env).
 -
 - @param prod  The production we're projecting
 - @param sourceType  The "vertexType" of this stitchPoint
 - @param targetType  The "vertexType" of where this stitchPoint should proxy to
 - @param prodType  The "vertexType" in the other production to use
 - @param attrs  The attributes we want to project to LHS inhs
 -}
abstract production projectionStitchPoint
top::StitchPoint ::= 
  prod::String -- pattern match on this production
  sourceType::VertexType -- the pattern Variable vertex type
  targetType::VertexType -- the scruntinee vertex type
  prodType::VertexType -- a rhsVertex of 'prod'
  attrs::[String] -- all inhs on the NT type of prodType/sourceType
{
  top.stitchEdges = \ flowTypes::EnvTree<FlowType> prodGraphs::EnvTree<ProductionGraph> ->
    flatMap(
      projectAttribute(_, sourceType, targetType, prodType, findProductionGraph(prod, prodGraphs)),
      attrs);
}


{--
 - @param attr  An inherited attribute
 - @param sourceType  "pattern variable" vertex type
 - @param targetType  "scrutinee" vertex type
 - @param prodType  the "child" vertex type...
 - @param prod  ...of this production (prodType in here, others in original prod graph)
 - @return edges from 'sourceType.inhVertex(attr)' to 'targetType.inhVertex(??)'
 -}
function projectAttribute
[Pair<FlowVertex FlowVertex>] ::=
  attr::String
  sourceType::VertexType
  targetType::VertexType
  prodType::VertexType
  prod::ProductionGraph
{
  -- emit edges from the src vertex of this production
  return map(pair(fst=sourceType.inhVertex(attr), snd=_),
    -- Turn into inh vertexes (in this production) on targetType
    map(targetType.inhVertex,
      -- Filter down to just LHS Inh in that production, (string names)
      filterLhsInh(
        -- Deps of this vertex in that other production
        set:toList(prod.edgeMap(prodType.inhVertex(attr))))));
}


-- Useful for mapping
function stitchEdgesFor
[Pair<FlowVertex FlowVertex>] ::= sp::StitchPoint  ntEnv::EnvTree<FlowType>  prodEnv::EnvTree<ProductionGraph>
{
  return sp.stitchEdges(ntEnv, prodEnv);
}

function edgeIsNew
Boolean ::= edge::Pair<FlowVertex FlowVertex>  e::g:Graph<FlowVertex>
{
  return !g:contains(edge, e);
}

{--
 - Creates edges from a "flow type" source to a "flow type" sink.
 - Special case: have to spot forwards and handle them correctly. This stinks.
 -
 - @param vt  The vertex type we're creating edges within
 - @param edge  pair of syn/fwd and inh. The edge.
 -}
function flowTypeEdge
Pair<FlowVertex FlowVertex> ::= vt::VertexType  edge::Pair<String String>
{
  return if edge.fst == "forward" then
    (vt.fwdVertex, vt.inhVertex(edge.snd))
  else
    (vt.synVertex(edge.fst), vt.inhVertex(edge.snd));
}

