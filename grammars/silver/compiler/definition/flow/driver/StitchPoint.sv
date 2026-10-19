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
 - @param throughForwards  The nonterminal of 'prod', if a match may reach 'prod' through the
 -   forwards of other productions (see matchThroughForwards).  Their forward inherited equations
 -   may supply the LHS inherited attributes of 'prod' from the forward flow type, so an attribute
 -   that depends on the LHS of 'prod' also depends on that.
 -}
abstract production projectionStitchPoint
top::StitchPoint ::= 
  prod::String -- pattern match on this production
  sourceType::VertexType -- the pattern Variable vertex type
  targetType::VertexType -- the scrutinee vertex type
  prodType::VertexType -- a vertex type of 'prod'
  attrs::[String] -- all inhs on the NT type of sigName/sourceType
  throughForwards::Maybe<String> -- the NT of 'prod', if matching may reach 'prod' through forwards
{
  -- A function passing a tree with '@' to a signature-shared child has no graph here, but has no
  -- LHS inherited attributes to project either.
  top.stitchEdges = \ flowTypes::EnvTree<FlowType> prodGraphs::EnvTree<ProductionGraph> ->
    case searchEnvTree(prod, prodGraphs) of
    | g :: _ ->
      flatMap(
        \ attr::String ->
          map(\ a::String -> (sourceType.inhVertex(attr), targetType.inhVertex(a)),
            projectedLhsDeps(g, prodType, attr, throughForwards, flowTypes)),
        attrs)
    | [] -> []
    end;
}

-- The LHS inherited attributes that the inherited attribute attr of the tree at vt depends on in
-- graph.  A match that may reach graph's production through forwards (throughForwards, that
-- production's nonterminal) gets these from forward inherited equations, so where there are any,
-- the forward flow type is needed too (see matchThroughForwards).
function projectedLhsDeps
[String] ::= graph::ProductionGraph  vt::VertexType  attr::String  throughForwards::Maybe<String>
  flowTypes::EnvTree<FlowType>
{
  local lhsDeps :: [String] = filterLhsInh(set:toList(graph.edgeMap(vt.inhVertex(attr))));
  return
    case throughForwards of
    | just(nt) when !null(lhsDeps) -> union(lhsDeps, set:toList(inhDepsForSyn("forward", nt, flowTypes)))
    | _ -> lhsDeps
    end;
}


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
  -- Note that deps on lhsEqVertex are not included in tile stitch points,
  -- as it is only used to locally collect the deps for taking a reference to the LHS.
  | lhsSynVertex(attr) -> parentType.synVertex(attr)
  | lhsInhVertex(attr) -> parentType.inhVertex(attr)
  | rhsEqVertex(sigName) ->
    subtermEqVertex(parentType, prodName, sigName)
  | rhsOuterEqVertex(sigName) ->
    subtermOuterEqVertex(parentType, prodName, sigName)
  | rhsSynVertex(sigName, attr) ->
    subtermSynVertex(parentType, prodName, sigName, attr)
  | rhsInhVertex(sigName, attr) ->
    subtermInhVertex(parentType, prodName, sigName, attr)
  -- Deps on forward parent vertices are only allowed in sig sharing prods,
  -- which must be applied in the root of a forward or forward production attribute,
  -- thus forward parent vertices in the remote production always map to the LHS vertices.
  -- A dispatch signature's graph keeps them instead (see implTileStitchPoint).
  | forwardParentEqVertex() -> lhsEqVertex()
  | forwardParentSynVertex(attr) -> lhsSynVertex(attr)
  | forwardParentInhVertex(attr) -> lhsInhVertex(attr)
  | _ -> error("Unexpected non-signature vertex in tileEdges: " ++ v.dotName)
  end;

{--
 - The tile flow graph of the implementation impl in the graph of the dispatch signature that impl
 - implements, at the signature's LHS (see dispatchStitchPoints).  The forward parent of impl, the
 - applying production or an earlier implementation (from any extension) in a chain of forwards, has
 - the attributes of the signature's LHS.
 -}
abstract production implTileStitchPoint
top::StitchPoint ::= impl::String
{
  top.stitchEdges = \ flowTypes::EnvTree<FlowType> prodGraphs::EnvTree<ProductionGraph> ->
    flatMap(implTileEdges(impl, _), findProductionGraph(impl, prodGraphs).tileEdges);
}

-- The edge e of the tile flow graph of impl, in the dispatch signature's graph.  An edge into a
-- forward-parent vertex keeps that vertex, which an application maps to the applying production's
-- LHS vertex (see fromSigVertex), and also goes to the dispatch signature's LHS vertex, for an
-- earlier implementation in a chain of forwards.
function implTileEdges
[(FlowVertex, FlowVertex)] ::= impl::String  e::(FlowVertex, FlowVertex)
{
  local src :: FlowVertex = implTileVertex(impl, e.1);
  return
    (src, implTileVertex(impl, e.2)) ::
    case e.2 of
    | forwardParentSynVertex(attr) -> [(src, lhsSynVertex(attr))]
    | forwardParentInhVertex(attr) -> [(src, lhsInhVertex(attr))]
    | _ -> []
    end;
}

-- The vertex v of the tile flow graph of impl, in the dispatch signature's graph: a forward-parent
-- vertex is kept, and any other vertex is mapped as at an application of impl at the dispatch
-- signature's LHS.
fun implTileVertex FlowVertex ::= impl::String  v::FlowVertex =
  case v of
  | forwardParentEqVertex() -> v
  | forwardParentSynVertex(_) -> v
  | forwardParentInhVertex(_) -> v
  | _ -> fromSigVertex(impl, lhsVertexType(), v)
  end;

{--
 - Edges from the synthesized attribute attr to the LHS vertices that deps reach in the default
 - graph of nt (see constructDefaultProductionGraph), where deps are dependencies of a default
 - equation or contribution for attr that applies at a production (see defaultDepsAt and
 - expandsDefaultDep).  For a contribution that may not affect the flow type of attr, only edges to
 - the inherited attributes in that flow type.
 -}
abstract production defaultStitchPoint
top::StitchPoint ::= nt::String  attr::String  deps::[FlowVertex]  mayAffectFlowType::Boolean
{
  top.stitchEdges = \ flowTypes::EnvTree<FlowType> prodGraphs::EnvTree<ProductionGraph> ->
    case searchEnvTree(defaultProdName(nt), prodGraphs) of
    | g :: _ ->
      map(pair(fst=lhsSynVertex(attr), snd=_),
        filter(
          isDefaultDepTarget(attr, mayAffectFlowType, inhDepsForSyn(attr, nt, flowTypes), _),
          set:toList(foldr(set:union, set:emptyWith(compareVertexId), map(g.tileEdgeMap, deps)))))
    | [] -> []
    end;
}

-- Whether the edges of defaultStitchPoint for attr go to v, a vertex that a dependency of the
-- default reaches in the default graph: any LHS vertex other than attr's, and the outer equation
-- vertex of a translation attribute of the LHS (at any depth).  For a contribution that may not
-- affect the flow type of attr, only the inherited attributes in ft, the flow type of attr.
fun isDefaultDepTarget Boolean ::= attr::String  mayAffectFlowType::Boolean  ft::set:Set<String>  v::FlowVertex =
  case v of
  | lhsInhVertex(i) -> mayAffectFlowType || set:contains(i, ft)
  | lhsSynVertex(a) -> mayAffectFlowType && a != attr
  | lhsEqVertex() -> mayAffectFlowType
  | transAttrOuterEqVertex(vt, _) -> mayAffectFlowType && transRootVertex(vt) == lhsVertexType()
  | _ -> false
  end;


{--
 - The keys for what the edges of this stitch point are computed from: the flow types of
 - nonterminals, and the graphs of productions.
 -}
synthesized attribute stitchDeps :: [String] occurs on StitchPoint;
aspect stitchDeps on StitchPoint of
| nonterminalStitchPoint(nt, _) -> [ntDep(nt)]
| projectionStitchPoint(prod, _, _, _, _, just(nt)) -> [prodDep(prod), ntDep(nt)]
| projectionStitchPoint(prod, _, _, _, _, nothing()) -> [prodDep(prod)]
| tileStitchPoint(prod, _) -> [prodDep(prod)]
| implTileStitchPoint(impl) -> [prodDep(impl)]
| defaultStitchPoint(nt, _, _, mayAffectFlowType) ->
  prodDep(defaultProdName(nt)) :: (if mayAffectFlowType then [] else [ntDep(nt)])
end;

fun ntDep String ::= nt::String = "nt:" ++ nt;
fun prodDep String ::= prod::String = "prod:" ++ prod;

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
fun flowTypeEdge (FlowVertex, FlowVertex) ::= vt::VertexType  edge::Pair<String String> =
  (vt.synVertex(edge.fst), vt.inhVertex(edge.snd));

