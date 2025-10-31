grammar silver:compiler:definition:flow:driver;

data nonterminal StitchPoint with stitchEdges;

synthesized attribute stitchEdges :: ([(FlowVertex, FlowVertex)] ::= EnvTree<FlowType> EnvTree<ProductionGraph>);

{--
 - Introduces internal edges corresponding to the flow type of 'nt'
 - to the specified vertex type.
 -}
abstract production nonterminalStitchPoint
top::StitchPoint ::= nt::String  vertexType::VertexType
{
  top.stitchEdges = \ flowTypes::EnvTree<FlowType> prodGraphs::EnvTree<ProductionGraph> ->
    map(flowTypeEdge(vertexType, _),
      rtm:toList(findFlowType(nt, flowTypes)));
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
 - @param prod  The production (or dispatch signature) we're projecting
 - @param sourceType  The "vertexType" of this stitchPoint
 - @param targetType  The "vertexType" of where this stitchPoint should proxy to
 - @param prodType    The "vertexType" of 'prod' (e.g. rhsVertex("rhs1", _))
 - @param inhs  The attributes we want to project to LHS inhs
 -}
abstract production projectionStitchPoint
top::StitchPoint ::= 
  prod::String -- pattern match on this production
  sourceType::VertexType -- the pattern Variable vertex type
  targetType::VertexType -- the scruntinee vertex type
  prodType::VertexType -- a vertex type of 'prod'
  inhs::[InhDep] -- all inhs on the NT type of sigName/sourceType
{
  top.stitchEdges = \ flowTypes::EnvTree<FlowType> prodGraphs::EnvTree<ProductionGraph> ->
    flatMap(
      projectInh(_, sourceType, targetType, prodType, findProductionGraph(prod, prodGraphs)),
      inhs);
}


{--
 - @param inh  An inherited attribute
 - @param sourceType  "pattern variable" vertex type
 - @param targetType  "scrutinee" vertex type
 - @param sigName     the child name...
 - @param prod  ...of this production (sigName in here, others in original prod graph)
 - @return edges from 'inhVertex(sourceType, attr)' to 'inhVertex(targetType, ??)'
 -}
fun projectInh
[(FlowVertex, FlowVertex)] ::=
  inh::InhDep
  sourceType::VertexType
  targetType::VertexType
  prodType::VertexType
  prod::ProductionGraph =
  map(pair(fst=inh.vertexOf(sourceType), snd=_),
    -- Turn into inh vertexes (in this production) on targetType
    map(inhVertexOf(targetType, _),
      -- Filter down to just LHS Inh in that production, (string names)
      filterMap((.lhsInh), 
        -- Deps of this vertex in that other production
        set:toList(prod.edgeMap(inh.vertexOf(prodType))))));


{--
 - Given production 'prod :: LHS ::= rhs1::RHS1'
 - with all synthesized attributes on 'LHS' as 'syns'
 - and all inherited attributes on 'RHS1' as 'inhs'.
 -
 - Finds all edges for inhs from RHS for each 'childInhs' (here, "rhs1") and 'syns' from LHS
 - to LHS INH/RHS SYN/RHS EQ in the production 'prod'.
 -
 - @param prod  The production (or dispatch signature) we're projecting
 - @param parentType The decoration site of the tree being constructed
 - @param childTypes  A map from children in prod to the corresponding vertex types in the current production
 -}
abstract production tileStitchPoint
top::StitchPoint ::= 
  prod::String -- production being constructed
  parentType::VertexType -- the parent tree vertex type in the current production
{
  top.stitchEdges = \ flowTypes::EnvTree<FlowType> prodGraphs::EnvTree<ProductionGraph> ->
    flatMap(fromSigEdge(prod, parentType, _),
      findProductionGraph(prod, prodGraphs).tileEdges);
}

fun fromSigEdge
[(FlowVertex, FlowVertex)] ::= prodName::String parentType::VertexType e::(FlowVertex, FlowVertex) =
  cartProd(fromSigVertex(prodName, parentType, e.1), fromSigVertex(prodName, parentType, e.2));

fun fromSigVertex
[FlowVertex] ::= prodName::String parentType::VertexType v::FlowVertex =
  flatMapVertex(\ vt ->
    case vt of
    | lhsVertexType() -> [parentType]
    | rhsVertexType(sigName) -> [subtermVertexType(parentType, prodName, sigName)]
    | _ -> []
    end, v);


-- Useful for mapping
fun stitchEdgesFor
[(FlowVertex, FlowVertex)] ::= sp::StitchPoint  ntEnv::EnvTree<FlowType>  prodEnv::EnvTree<ProductionGraph> =
  sp.stitchEdges(ntEnv, prodEnv);

fun edgeIsNew Boolean ::= edge::(FlowVertex, FlowVertex)  e::g:Graph<FlowVertex> =
  !g:contains(edge, e);

{--
 - Creates edges from a "flow type" source to a "flow type" sink.
 -
 - @param vt  The vertex type we're creating edges within
 - @param edge  pair of syn/fwd and inh. The edge.
 -}
fun flowTypeEdge (FlowVertex, FlowVertex) ::= vt::VertexType  edge::(String, InhDep) =
  (synVertex(vt, edge.1), edge.2.vertexOf(vt));

