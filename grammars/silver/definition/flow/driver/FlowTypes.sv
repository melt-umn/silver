grammar silver:definition:flow:driver;

imports silver:definition:core;
imports silver:definition:env;
--import silver:definition:flow:env;
imports silver:definition:flow:ast;
imports silver:analysis:warnings:defs only isOccursSynthesized, isAutocopy;

imports silver:modification:autocopyattr;

imports silver:util:raw:treemap as rtm;

import silver:util only rem;


-- Help some type signatures suck a little less
type ProdName = String;
type NtName = String;

-- construct a production graph for each production
function computeAllProductionGraphs
[ProductionGraph] ::= prods::[String]  prodTree::EnvTree<FlowDef>  flowEnv::Decorated FlowEnv  realEnv::Decorated Env
{
  return if null(prods) then []
  else constructProductionGraph(head(prods), searchEnvTree(head(prods), prodTree), flowEnv, realEnv) ::
    computeAllProductionGraphs(tail(prods), prodTree, flowEnv, realEnv);
}


{--
 - Produces flow types for every nonterminal.
 - Iterates until convergence.
 -}
function fullySolveFlowTypes
Pair<[ProductionGraph] EnvTree<Pair<String String>>> ::= graphs::[ProductionGraph]
                                 realEnv::Decorated Env
                                 ntEnv::EnvTree<Pair<String String>>
{
  local iter :: Pair<Boolean Pair<[ProductionGraph] EnvTree<Pair<String String>>>> =
    solveFlowTypes(graphs, realEnv, ntEnv);
  
  -- Just iterate until no new edges are added
  return if !iter.fst then iter.snd
  else fullySolveFlowTypes(iter.snd.fst, realEnv, iter.snd.snd);
}

{--
 - One iteration of solving flow type equations. Goes through each production once.
 -}
function solveFlowTypes
Pair<Boolean
     Pair<[ProductionGraph]
          EnvTree<Pair<String String>>>> ::=
  graphs::[ProductionGraph]
  realEnv::Decorated Env
  ntEnv::EnvTree<Pair<String String>>
{
  local graph :: ProductionGraph = head(graphs);
  graph.flowTypes = ntEnv;
  local stitchedGraph :: ProductionGraph = graph.stitchedGraph;
  stitchedGraph.flowTypes = ntEnv;
  local updatedGraph :: ProductionGraph = stitchedGraph.cullSuspect;

  -- TODO it'd be nice if we didn't need to look this up every time we're called.
  -- IN PARTICULAR since it's the only use of realEnv here!
  local syns :: [String] =
    map((.attrOccurring),
      filter(isOccursSynthesized(_, realEnv),
        getAttrsOn(graph.lhsNt, realEnv)));
  
  local currentFlowType :: EnvTree<String> =
    directBuildTree(searchEnvTree(graph.lhsNt, ntEnv));
  
  -- The New Improved Flow Type
  local synExpansion :: [Pair<String [String]>] =
    map(expandVertexFilterTo(_, updatedGraph),
      forwardEqVertex() :: map(lhsSynVertex, syns));
  
  -- Find what edges are NEW NEW NEW
  local brandNewEdges :: [Pair<NtName Pair<String String>>] =
    map(pair(graph.lhsNt, _), findBrandNewEdges(synExpansion, currentFlowType));
  
  local recurse :: Pair<Boolean Pair<[ProductionGraph] EnvTree<Pair<String String>>>> =
    solveFlowTypes(tail(graphs), realEnv, rtm:add(brandNewEdges, ntEnv));
    
  return if null(graphs) then pair(false, pair([], ntEnv))
  else pair(!null(brandNewEdges) || recurse.fst, pair(updatedGraph :: recurse.snd.fst, recurse.snd.snd));
}


function findBrandNewEdges
[Pair<String String>] ::= candidates::[Pair<String [String]>]  currentFlowType::EnvTree<String>
{
  local syn :: String = head(candidates).fst;
  local inhs :: [String] = head(candidates).snd;
  
  local newinhs :: [String] = rem(inhs, searchEnvTree(syn, currentFlowType));
  
  local newEdges :: [Pair<String String>] = map(pair(syn, _), newinhs);
  
  return if null(candidates) then [] else newEdges ++ findBrandNewEdges(tail(candidates), currentFlowType);
}




-- Expand 'ver' using 'graph', then filter down to just those in 'inhs'
function expandVertexFilterTo
Pair<String [String]> ::= ver::FlowVertex  graph::ProductionGraph
{
  return pair(ver.flowTypeName, foldr(collectInhs, [], graph.edgeMap(ver)));
}


{--
 - Used to filter down to just the inherited attributes
 - 
 - @param f  The flow vertex in question
 - @param l  The current set of inherited attribute dependencies
 - @return  {l} with {f} added to it, IF it's in {inhs} and not already in {l}
 -}
function collectInhs
[String] ::= f::FlowVertex  l::[String]
{
  return case f of
  | lhsInhVertex(a) -> if containsBy(stringEq, a, l) then l else a::l
  | _ -> l
  end;
}

{--
 - Used to add the 'minimum' flow type for non-host synthesized attributes.
 - These attributes need to be able to evaluate the forwards of productions
 - to be able to be evaluated.
 - @param initial  the results from fullySolveFlowTypes
 - @param edits  the list of non-host synthesized attribute occurrences
 - @return the modified flow types
 -}
function patchFlowTypes
EnvTree<Pair<String String>> ::= initial::EnvTree<Pair<String String>>  edits::[FlowDef]
{
  return rtm:add(foldr(append, [], map(patchEditPair(_, initial), edits)), initial);
}
function patchEditPair
[Pair<String Pair<String String>>] ::= edit::FlowDef  current::EnvTree<Pair<String String>>
{
  return case edit of
  | nonHostSynDef(attr, nt) -> 
      let fwdInhs :: [String] = lookupAllBy(stringEq, "forward", searchEnvTree(nt, current)),
          alreadyInhs :: [String] = lookupAllBy(stringEq, attr, searchEnvTree(nt, current))
       in
          map(pair(nt, _), map(pair(attr, _), rem(fwdInhs, alreadyInhs)))
      end
  end; -- for everything found under nt->forward, add something under nt->attr, if it doesn't exist already
}







function flowVertexEq
Boolean ::= a::FlowVertex  b::FlowVertex
{
  -- eh, good enough TODO
  return a.dotName == b.dotName;
}




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
aspect production rhsVertex
top::FlowVertex ::= sigName::String  attrName::String
{
  top.flowTypeName = error("Internal compiler error: shouldn't be solving flow types for child attributes?");
}
aspect production localEqVertex
top::FlowVertex ::= fName::String
{
  top.flowTypeName = fName; -- secretly only ever "forward"
}
aspect production localVertex
top::FlowVertex ::= fName::String  attrName::String
{
  top.flowTypeName = error("Internal compiler error: shouldn't be solving flow types for local inherited attributes?");
}

