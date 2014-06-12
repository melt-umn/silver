grammar silver:definition:flow:driver;

nonterminal StitchPoint with prodGraphs, flowTypes, stitchEdges;

synthesized attribute stitchEdges :: [Pair<FlowVertex FlowVertex>];

{--
 - Introduces internal edges corresponding to the flow type of 'nt'
 - to the specified vertex type.
 -}
abstract production nonterminalStitchPoint
top::StitchPoint ::= nt::String  vertexType::(FlowVertex ::= String)
{
  top.stitchEdges = 
    map(dualApply(vertexType, _),
      g:toList(findFlowType(nt, top.flowTypes)));
}

-- TODO: turn the above to use VertexType
-- TODO: turn the below to use VertexType
-- TODO: use the below, somehow. it's for pattern matching, right?


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
  sourceType::(FlowVertex ::= String) -- the pattern Variable vertex type
  targetType::(FlowVertex ::= String) -- the scruntinee vertex type
  prodType::(FlowVertex ::= String) -- a rhsVertex of 'prod'
  attrs::[String] -- all inhs on the NT type of prodType/sourceType
{
  top.stitchEdges =
    foldr(append, [],
      map(projectAttribute(_, sourceType, targetType, prodType,
        findProductionGraph(prod, top.prodGraphs)), attrs));
}


function projectAttribute
[Pair<FlowVertex FlowVertex>] ::=
  attr::String
  sourceType::(FlowVertex ::= String)
  targetType::(FlowVertex ::= String)
  prodType::(FlowVertex ::= String)
  prod::ProductionGraph
{
  -- emit edges from the src vertex of this production
  return map(pair(sourceType(attr), _),
    -- Turn into dst vertexes (in this production)
    map(targetType,
      -- Filter down to just LHS Inh, (string names)
      foldr(collectInhs, [], 
        -- Deps of this vertex in that production
        set:toList(prod.edgeMap(prodType(attr))))));
}


-- Useful for mapping
function stitchEdgesFor
[Pair<FlowVertex FlowVertex>] ::= sp::StitchPoint  ntEnv::EnvTree<g:Graph<String>>  prodEnv::EnvTree<ProductionGraph>
{
  sp.prodGraphs = prodEnv;
  sp.flowTypes = ntEnv;
  return sp.stitchEdges;
}

function dualApply
Pair<b b> ::= f::(b ::= a)  x::Pair<a a>
{
  return pair(f(x.fst), f(x.snd));
}

function edgeIsNew
Boolean ::= edge::Pair<FlowVertex FlowVertex>  e::g:Graph<FlowVertex>
{
  return !g:contains(edge, e);
}

