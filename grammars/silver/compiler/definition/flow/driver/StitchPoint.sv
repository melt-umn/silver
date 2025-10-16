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
 - @param prod  The production (or dispatch signature) we're projecting
 - @param sourceType  The "vertexType" of this stitchPoint
 - @param targetType  The "vertexType" of where this stitchPoint should proxy to
 - @param prodType    The "vertexType" of 'prod' (e.g. rhsVertex("rhs1", _))
 - @param attrs  The attributes we want to project to LHS inhs
 -}
abstract production projectionStitchPoint
top::StitchPoint ::= 
  prod::String -- pattern match on this production
  sourceType::VertexType -- the pattern Variable vertex type
  targetType::VertexType -- the scruntinee vertex type
  prodType::VertexType -- a vertex type of 'prod'
  attrs::[String] -- all inhs on the NT type of sigName/sourceType
{
  top.stitchEdges = \ flowTypes::EnvTree<FlowType> prodGraphs::EnvTree<ProductionGraph> ->
    flatMap(
      projectInh(_, sourceType, targetType, prodType, findProductionGraph(prod, prodGraphs)),
      attrs);
}


{--
 - @param attr  An inherited attribute
 - @param sourceType  "pattern variable" vertex type
 - @param targetType  "scrutinee" vertex type
 - @param sigName     the child name...
 - @param prod  ...of this production (sigName in here, others in original prod graph)
 - @return edges from 'sourceType.inhVertex(attr)' to 'targetType.inhVertex(??)'
 -}
fun projectInh
[(FlowVertex, FlowVertex)] ::=
  attr::String
  sourceType::VertexType
  targetType::VertexType
  prodType::VertexType
  prod::ProductionGraph =
  map(pair(fst=sourceType.inhVertex(attr), snd=_),
    -- Turn into inh vertexes (in this production) on targetType
    map(targetType.inhVertex,
      -- Filter down to just LHS Inh in that production, (string names)
      filterLhsInh(
        -- Deps of this vertex in that other production
        set:toList(prod.edgeMap(prodType.inhVertex(attr))))));


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
    map(fromSigEdge(prod, parentType, _),
      findProductionGraph(prod, prodGraphs).tileEdges);
}

fun fromSigEdge
(FlowVertex, FlowVertex) ::= prodName::String parentType::VertexType e::(FlowVertex, FlowVertex) =
  (fromSigVertex(prodName, parentType, e.1), fromSigVertex(prodName, parentType, e.2));

fun fromSigVertex
FlowVertex ::= prodName::String parentType::VertexType v::FlowVertex =
  case v of
  | lhsSynVertex(attr) -> parentType.synVertex(attr)
  | lhsInhVertex(attr) -> parentType.inhVertex(attr)
  | rhsEqVertex(sigName) ->
    subtermEqVertex(parentType, prodName, sigName)
  | rhsSynVertex(sigName, attr) ->
    subtermSynVertex(parentType, prodName, sigName, attr)
  | rhsInhVertex(sigName, attr) ->
    subtermInhVertex(parentType, prodName, sigName, attr)
  | _ -> error("Unexpected non-signature vertex in tileEdges: " ++ v.dotName)
  end;


-- Useful for mapping
fun stitchEdgesFor
[(FlowVertex, FlowVertex)] ::= sp::StitchPoint  ntEnv::EnvTree<FlowType>  prodEnv::EnvTree<ProductionGraph> =
  sp.stitchEdges(ntEnv, prodEnv);

fun edgeIsNew Boolean ::= edge::(FlowVertex, FlowVertex)  e::g:Graph<FlowVertex> =
  !g:contains(edge, e);

{--
 - Creates edges from a "flow type" source to a "flow type" sink.
 - Special case: have to spot forwards and handle them correctly. This stinks.
 -
 - @param vt  The vertex type we're creating edges within
 - @param edge  pair of syn/fwd and inh. The edge.
 -}
fun flowTypeEdge (FlowVertex, FlowVertex) ::= vt::VertexType  edge::Pair<String String> =
  if edge.fst == "forward" then
    (vt.fwdVertex, vt.inhVertex(edge.snd))
  else
    (vt.synVertex(edge.fst), vt.inhVertex(edge.snd));

