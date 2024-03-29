grammar silver:compiler:definition:flow:driver;

imports silver:compiler:definition:core;
imports silver:compiler:definition:env;
imports silver:compiler:definition:flow:env;
imports silver:compiler:definition:flow:ast;
imports silver:compiler:analysis:warnings:flow only isOccursSynthesized;
imports silver:compiler:analysis:uniqueness;

imports silver:util:treemap as rtm;
imports silver:util:graph as g;
imports silver:util:treeset as set;

-- Help some type signatures suck a little less
type ProdName = String;
type NtName = String;

-- from explicit specifications
function computeInitialFlowTypes
EnvTree<FlowType> ::= specDefs::[(String, String, [String], [String])]
{
  -- We don't care what flow specs reference what
  local dropRefs::[(String, String, [String])] =
    map(\ d::(String, String, [String], [String]) -> (d.1, d.2, d.3), specDefs);

  local specs :: [(NtName, [(String, [String])])] =
    ntListCoalesce(groupBy(ntListEq, sortBy(ntListLte, dropRefs)));
  
  return rtm:add(map(initialFlowType, specs), rtm:empty());
}
fun initialFlowType Pair<NtName FlowType> ::= x::(NtName, [(String, [String])]) =
  (x.fst, g:add(flatMap(toFlatEdges, x.snd), g:empty()));
fun ntListLte Boolean ::= a::Pair<NtName a>  b::Pair<NtName b> = a.fst <= b.fst;
fun ntListEq Boolean ::= a::Pair<NtName a>  b::Pair<NtName b> = a.fst == b.fst;
fun ntListCoalesce [(NtName, [(String, [String])])] ::= l::[[(NtName, String, [String])]] =
  if null(l) then []
  else (head(head(l)).fst, map(snd, head(l))) :: ntListCoalesce(tail(l));
fun toFlatEdges [Pair<String String>] ::= x::Pair<String [String]> =
  map(pair(fst=x.fst, snd=_), x.snd);


{--
 - Produces flow types for every nonterminal.
 - Iterates until convergence.
 -}
function fullySolveFlowTypes
([ProductionGraph], EnvTree<FlowType>) ::= 
  graphs::[ProductionGraph]
  ntEnv::EnvTree<FlowType>
{
  -- Each iteration, we rebuild this... :/
  -- TODO: consider a 'mapValuesWithMapState' function :: 'k1, v1, map<k1 v1>, map<k2 v2> -> v1, k2, v2'
  local prodEnv :: EnvTree<ProductionGraph> =
    directBuildTree(map(prodGraphToEnv, graphs));
  
  local iter :: (Boolean, [ProductionGraph], EnvTree<FlowType>) =
    solveFlowTypes(graphs, prodEnv, ntEnv);
  
  -- Just iterate until no new edges are added
  return if !iter.1 then iter.snd
  else fullySolveFlowTypes(iter.2, iter.3);
}

{--
 - One iteration of solving flow type equations. Goes through each production once.
 -}
function solveFlowTypes
(Boolean, [ProductionGraph], EnvTree<FlowType>) ::=
  graphs::[ProductionGraph]
  prodEnv::EnvTree<ProductionGraph>
  ntEnv::EnvTree<FlowType>
{
  local updatedGraph :: ProductionGraph = updateGraph(head(graphs), prodEnv, ntEnv);

  local currentFlowType :: FlowType = findFlowType(updatedGraph.lhsNt, ntEnv);
  
  -- The New Improved Flow Type
  local synExpansion :: [Pair<String [String]>] =
    map(expandVertexFilterTo(_, updatedGraph), updatedGraph.flowTypeVertexes);
  
  -- Find what edges are NEW NEW NEW
  local brandNewEdges :: [Pair<String String>] =
    findBrandNewEdges(synExpansion, currentFlowType);
    
  local newFlowType :: FlowType =
    g:add(brandNewEdges, currentFlowType);
  -- TODO: we could just always "add everything unconditionally" but we also need to know if there were
  -- any new additions... so we'd need something added to graph to support that.
  
  local recurse :: Pair<Boolean Pair<[ProductionGraph] EnvTree<FlowType>>> =
    solveFlowTypes(tail(graphs), prodEnv, rtm:update(updatedGraph.lhsNt, [newFlowType], ntEnv));
    
  return if null(graphs) then (false, ([], ntEnv))
  else (!null(brandNewEdges) || recurse.fst, updatedGraph :: recurse.snd.fst, recurse.snd.snd);
}


function findBrandNewEdges
[Pair<String String>] ::= candidates::[Pair<String [String]>]  currentFlowType::FlowType
{
  local syn :: String = head(candidates).fst;
  local inhs :: [String] = head(candidates).snd;
  
  -- TODO: we might take '[Pair<String Set<String>>]' insteadof [String] and gain speed?
  local newinhs :: [String] = removeAll(set:toList(g:edgesFrom(syn, currentFlowType)), inhs);
  
  local newEdges :: [Pair<String String>] = map(pair(fst=syn, snd=_), newinhs);
  
  return if null(candidates) then [] else newEdges ++ findBrandNewEdges(tail(candidates), currentFlowType);
}

-- Expand 'ver' using 'graph', then filter down to just those in 'inhs'
fun expandVertexFilterTo Pair<String [String]> ::= ver::FlowVertex  graph::ProductionGraph =
  (ver.flowTypeName, filterLhsInh(set:toList(graph.edgeMap(ver))));

{--
 - Filters vertexes down to just the names of inherited attributes on the LHS
 -}
fun filterLhsInh [String] ::= f::[FlowVertex] = foldr(collectInhs, [], f);

{--
 - Used to filter down to just the inherited attributes (on the LHS)
 - 
 - @param f  The flow vertex in question
 - @param l  The current set of inherited attribute dependencies
 - @return  {l} with {f} added to it
 -}
fun collectInhs [String] ::= f::FlowVertex  l::[String] =
  case f of
  | lhsInhVertex(a) -> a::l
  | _ -> l
  end;


{--
 - Flow type lookup names for vertices
 -}
synthesized attribute flowTypeName :: String occurs on FlowVertex;

aspect production lhsSynVertex
top::FlowVertex ::= attrName::String
{
  top.flowTypeName = attrName;
}
aspect production lhsInhVertex
top::FlowVertex ::= attrName::String
{
  top.flowTypeName = error("Internal compiler error: shouldn't be solving flow types for inherited attributes?");
}
aspect production rhsSynVertex
top::FlowVertex ::= sigName::String  attrName::String
{
  top.flowTypeName = error("Internal compiler error: shouldn't be solving flow types for child synthesized attributes?");
}
aspect production rhsInhVertex
top::FlowVertex ::= sigName::String  attrName::String
{
  top.flowTypeName = error("Internal compiler error: shouldn't be solving flow types for child inherited attributes?");
}
aspect production localEqVertex
top::FlowVertex ::= fName::String
{
  top.flowTypeName = fName; -- secretly only ever "forward" when we actually demand flowTypeName
}
aspect production localSynVertex
top::FlowVertex ::= fName::String  attrName::String
{
  top.flowTypeName = error("Internal compiler error: shouldn't be solving flow types for local synthesized attributes?");
}
aspect production localInhVertex
top::FlowVertex ::= fName::String  attrName::String
{
  top.flowTypeName = error("Internal compiler error: shouldn't be solving flow types for local inherited attributes?");
}
aspect production anonEqVertex
top::FlowVertex ::= fName::String
{
  top.flowTypeName = error("Internal compiler error: shouldn't be solving flow types for anon equations?");
}
aspect production anonSynVertex
top::FlowVertex ::= fName::String  attrName::String
{
  top.flowTypeName = error("Internal compiler error: shouldn't be solving flow types for anon synthesized attributes?");
}
aspect production anonInhVertex
top::FlowVertex ::= fName::String  attrName::String
{
  top.flowTypeName = error("Internal compiler error: shouldn't be solving flow types for anon inherited attributes?");
}
aspect production subtermSynVertex
top::FlowVertex ::= parent::VertexType prodName::String sigName::String  attrName::String
{
  top.flowTypeName = error("Internal compiler error: shouldn't be solving flow types for subterm synthesized attributes?");
}
aspect production subtermInhVertex
top::FlowVertex ::= parent::VertexType prodName::String sigName::String  attrName::String
{
  top.flowTypeName = error("Internal compiler error: shouldn't be solving flow types for subterm inherited attributes?");
}
