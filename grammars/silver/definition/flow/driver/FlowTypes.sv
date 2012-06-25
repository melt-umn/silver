grammar silver:definition:flow:driver;

imports silver:definition:core;
imports silver:definition:env;
--import silver:definition:flow:env;
imports silver:definition:flow:ast;
imports silver:analysis:warnings:defs only isOccursSynthesized, isAutocopy;

imports silver:modification:autocopyattr;

imports silver:util:raw:treemap as rtm;

-- TODO: Ideally, we wouldn't have to do this, as these show up organically from the trees?
{--
 - Deal with the defaults/forwarding and HOA logic on production graphs.
 -
 - 1. All HOA synthesized attributes have a dep on their equation. 
 - 1b. Same for forwarding.
 - 2. All synthesized attributes missing equations have dep on their corresponding fwd.
 - 2b. OR use their default if not forwarding and it exists.
 - 3. All inherited attributes not supplied to forward have copies.
 - 4. All autocopy attributes not supplied to childred have copies.
 -}
function fixupGraphs
[Pair<String [Pair<FlowVertex FlowVertex>]>] ::= prods::[String]  prodTree::EnvTree<FlowDef>  flowEnv::Decorated FlowEnv  realEnv::Decorated Env
{
  local p :: String = head(prods);
  local dcl :: DclInfo = head(getValueDclAll(p, realEnv));
  local nt :: String = dcl.namedSignature.outputElement.typerep.typeName;
  local attrs :: Pair<[DclInfo] [DclInfo]> = partition(isOccursSynthesized(_, realEnv), getAttrsOn(nt, realEnv));
  local syns :: [String] = map((.attrOccurring), attrs.fst);
  local inhs :: [String] = map((.attrOccurring), attrs.snd);
  local autos :: [String] = filter(isAutocopy(_, realEnv), inhs);
  
  local normalEdges :: [Pair<FlowVertex FlowVertex>] =
    foldr(append, [], map((.flowEdges), searchEnvTree(p, prodTree)));
  
  local fixedEdges :: [Pair<FlowVertex FlowVertex>] =
    normalEdges ++
    (if null(lookupFwd(p, flowEnv))
     then addDefEqs(p, nt, syns, flowEnv)
     else addFwdEqs(p, syns, flowEnv) ++ addFwdInhEqs(p, inhs, flowEnv)) ++
    fixupAllHOAs(searchEnvTree(p, prodTree), flowEnv, realEnv) ++
    addAllAutoCopyEqs(p, dcl.namedSignature.inputElements, autos, flowEnv, realEnv);

  return if null(prods) then []
  else [pair(p, fixedEdges)] ++ fixupGraphs(tail(prods), prodTree, flowEnv, realEnv);
}

---- Begin helpers for fixing up graphs ----------------------------------------

function fixupAllHOAs
[Pair<FlowVertex FlowVertex>] ::= d::[FlowDef] flowEnv::Decorated FlowEnv realEnv::Decorated Env
{
  return case d of
  | [] -> []
  | localEq(_, fN, "", deps) :: rest -> fixupAllHOAs(rest, flowEnv, realEnv)
  | localEq(_, fN, tN, deps) :: rest -> 
      addHOASynDeps(map((.attrOccurring), filter(isOccursSynthesized(_, realEnv), getAttrsOn(tN, realEnv))), fN) ++
        fixupAllHOAs(rest, flowEnv, realEnv)
  | _ :: rest -> fixupAllHOAs(rest, flowEnv, realEnv)
  end;
}
function addHOASynDeps
[Pair<FlowVertex FlowVertex>] ::= synattrs::[String]  fName::String
{
  return if null(synattrs) then []
  else pair(localVertex(fName, head(synattrs)), localEqVertex(fName)) :: addHOASynDeps(tail(synattrs), fName);
}

function addFwdEqs
[Pair<FlowVertex FlowVertex>] ::= prod::String syns::[String] flowEnv::Decorated FlowEnv
{
  return if null(syns) then []
  else 
    [pair(forwardVertex(head(syns)), forwardEqVertex())] ++
    (if null(lookupSyn(prod, head(syns), flowEnv)) then [pair(lhsVertex(head(syns)), forwardVertex(head(syns)))] else []) ++
    addFwdEqs(prod, tail(syns), flowEnv);
}
function addFwdInhEqs
[Pair<FlowVertex FlowVertex>] ::= prod::String inhs::[String] flowEnv::Decorated FlowEnv
{
  return if null(inhs) then []
  else (if null(lookupFwdInh(prod, head(inhs), flowEnv)) then [pair(forwardVertex(head(inhs)), lhsVertex(head(inhs)))] else []) ++
    addFwdInhEqs(prod, tail(inhs), flowEnv);
}


function addDefEqs
[Pair<FlowVertex FlowVertex>] ::= prod::String nt::String syns::[String] flowEnv :: Decorated FlowEnv
{
  return if null(syns) then []
  else (if null(lookupSyn(prod, head(syns), flowEnv)) 
        then let x :: [FlowDef] = lookupDef(nt, head(syns), flowEnv)
              in if null(x) then [] else head(x).flowEdges 
             end
        else []) ++
    addDefEqs(prod, nt, tail(syns), flowEnv);
}

function addAllAutoCopyEqs
[Pair<FlowVertex FlowVertex>] ::= prod::String sigNames::[NamedSignatureElement] inhs::[String] flowEnv::Decorated FlowEnv realEnv::Decorated Env
{
  return if null(sigNames) then []
  else addAutocopyEqs(prod, head(sigNames), inhs, flowEnv, realEnv) ++ addAllAutoCopyEqs(prod, tail(sigNames), inhs, flowEnv, realEnv);
}
function addAutocopyEqs
[Pair<FlowVertex FlowVertex>] ::= prod::String sigName::NamedSignatureElement inhs::[String] flowEnv::Decorated FlowEnv realEnv::Decorated Env
{
  return if null(inhs) then []
  else (if null(lookupInh(prod, sigName.elementName, head(inhs), flowEnv))  -- no equation
        && !null(getOccursDcl(head(inhs), sigName.typerep.typeName, realEnv)) -- and it occurs on this type
        then [pair(rhsVertex(sigName.elementName, head(inhs)), lhsVertex(head(inhs)))]
        else []) ++
    addAutocopyEqs(prod, sigName, tail(inhs), flowEnv, realEnv);
}

---- End helpers for fixing up graphs ------------------------------------------






{--
 - This is a slightly bizarro data structure that lets us find out 
 - (fst) the signature
 - (snd) the type of each local attribute
 - for a production.
 -}
function makeProdLocalInfo
[Pair<String Pair<NamedSignature [Pair<String String>]>>] ::= prods::[String]  prodTree::EnvTree<FlowDef>  realEnv::Decorated Env
{
  return if null(prods) then []
  else pair(head(prods), 
         pair(
           head(getValueDclAll(head(prods), realEnv)).namedSignature,
           findAllLocals(searchEnvTree(head(prods), prodTree)))) ::
       makeProdLocalInfo(tail(prods), prodTree, realEnv);
}
function findAllLocals
[Pair<String String>] ::= d::[FlowDef]
{
  return case d of
  | [] -> []
  | localEq(_, fN, "", deps) :: rest -> findAllLocals(rest)
  | localEq(_, fN, tN, deps) :: rest -> pair(fN, tN) :: findAllLocals(rest)
  | _ :: rest -> findAllLocals(rest)
  end;
}




{--
 - Produces flow types for every nonterminal.
 - Iterates until convergence.
 -}
function fullySolveFlowTypes
EnvTree<Pair<String String>> ::= prodinfos::EnvTree<Pair<NamedSignature [Pair<String String>]>>
                                 graphs::[Pair<String [Pair<FlowVertex FlowVertex>]>]
                                 realEnv::Decorated Env
                                 ntEnv::EnvTree<Pair<String String>>
{
  local iter :: [Pair<String Pair<String String>>] =
    solveFlowTypes(prodinfos, graphs, realEnv, ntEnv);
  
  -- Just iterate until no new edges are added
  return if null(iter) then ntEnv
  else fullySolveFlowTypes(prodinfos, graphs, realEnv, 
         rtm:add(iter, ntEnv));
}

{--
 - One iteration of solving flow type equations. Goes through each production once.
 -}
function solveFlowTypes
[Pair<String Pair<String String>>] ::= prodinfos::EnvTree<Pair<NamedSignature [Pair<String String>]>>
                                       graphs::[Pair<String [Pair<FlowVertex FlowVertex>]>]
                                       realEnv::Decorated Env
                                       ntEnv::EnvTree<Pair<String String>>
{
  local graph :: Pair<String [Pair<FlowVertex FlowVertex>]> = head(graphs);
  local prodInfo :: Pair<NamedSignature [Pair<String String>]> = head(searchEnvTree(prod, prodinfos));

  local prod :: String = graph.fst;
  local nt :: String = sig.outputElement.typerep.typeName;
  local edges :: [Pair<FlowVertex FlowVertex>] = graph.snd;
  local sig :: NamedSignature = prodInfo.fst;
  local localTypes :: [Pair<String String>] = prodInfo.snd;
  
  local stitchedGraph :: [Pair<FlowVertex FlowVertex>] =
    edges ++
    stitchEdges(forwardVertex, searchEnvTree(nt, ntEnv)) ++
    stitchRhsEdges(sig.inputElements, ntEnv) ++
    stitchLocalEdges(localTypes, ntEnv);
  local stitchedGraphEnv :: EnvTree<FlowVertex> =
    directBuildTree(map(makeGraphEnv, stitchedGraph));
  
  local attrs :: Pair<[DclInfo] [DclInfo]> = partition(isOccursSynthesized(_, realEnv), getAttrsOn(nt, realEnv));
  local syns :: [String] = map((.attrOccurring), attrs.fst);
  local inhs :: [String] = map((.attrOccurring), attrs.snd);
  
  local currentFlowType :: EnvTree<String> =
    directBuildTree(searchEnvTree(nt, ntEnv));
  
  -- The New Improved Flow Type
  -- TODO: We also need to compute flow type information for locals... somehow. Maybe? Maybe not....
  local synExpansion :: [Pair<String [String]>] =
    expandForEach(map(lhsVertex, syns) ++ [forwardEqVertex()], inhs, stitchedGraphEnv);
  
  -- Find what edges are NEW NEW NEW
  local brandNewEdges :: [Pair<String Pair<String String>>] =
    map(pair(nt, _), findBrandNewEdges(synExpansion, currentFlowType));
  
  return if null(graphs) then []
  else brandNewEdges ++
         solveFlowTypes(prodinfos, tail(graphs), realEnv, rtm:add(brandNewEdges, ntEnv));
}

function makeGraphEnv
Pair<String FlowVertex> ::= p::Pair<FlowVertex FlowVertex>
{
  return pair(p.fst.dotName, p.snd);
}

function findBrandNewEdges
[Pair<String String>] ::= candidates::[Pair<String [String]>]  currentFlowType::EnvTree<String>
{
  local syn :: String = head(candidates).fst;
  local inhs :: [String] = head(candidates).snd;
  
  local newinhs :: [String] = removeAllBy(stringEq, searchEnvTree(syn, currentFlowType), inhs);
  
  local newEdges :: [Pair<String String>] = map(pair(syn, _), newinhs);
  
  return if null(candidates) then [] else newEdges ++ findBrandNewEdges(tail(candidates), currentFlowType);
}

function dualApply
Pair<b b> ::= f::(b ::= a)  x::Pair<a a>
{
  return pair(f(x.fst), f(x.snd));
}
function stitchEdges
[Pair<FlowVertex FlowVertex>] ::= obj::(FlowVertex ::= String) lst::[Pair<String String>]
{
  return map(dualApply(obj, _), lst);
}

-- Produces edges for each RHS element according to the current flow type
function stitchRhsEdges
[Pair<FlowVertex FlowVertex>] ::= rhs::[NamedSignatureElement]  ntEnv::EnvTree<Pair<String String>>
{
  return if null(rhs) then []
  else stitchEdges(rhsVertex(head(rhs).elementName, _), searchEnvTree(head(rhs).typerep.typeName, ntEnv)) ++ stitchRhsEdges(tail(rhs), ntEnv);
}
-- Produces edges for each local element according to the current flow type
function stitchLocalEdges
[Pair<FlowVertex FlowVertex>] ::= locals::[Pair<String String>]  ntEnv::EnvTree<Pair<String String>>
{
  return if null(locals) then []
  else stitchEdges(localVertex(head(locals).fst, _), searchEnvTree(head(locals).snd, ntEnv)) ++ stitchLocalEdges(tail(locals), ntEnv);
}

-- For each vertex, do a graph expansion and then filter down to just inherited attributes
function expandForEach
[Pair<String [String]>] ::= syns::[FlowVertex]  inhs::[String]  graph::EnvTree<FlowVertex>
{
  return if null(syns) then []
  else pair(head(syns).flowTypeName, foldr(collectInhs(inhs,_,_), [], expandGraph([head(syns)], graph))) ::
         expandForEach(tail(syns), inhs, graph);
}

-- Transitive closure under the graph
function expandGraph
[FlowVertex] ::= set::[FlowVertex]  graph::EnvTree<FlowVertex>
{
  local expanded :: [FlowVertex] =
    nubBy(flowVertexEq, foldr(append, [], map(searchEnvTree(_, graph), map((.dotName), set))));
  
  local newNodes :: [FlowVertex] =
    removeAllBy(flowVertexEq, set, expanded);
  
  return if null(newNodes) then set else expandGraph(set ++ newNodes, graph);
}

{--
 - Used to filter down to just the inherited attributes
 - @param inhs  All inherited attributes on the LHS
 - @param f  The flow vertex in question
 - @param l  The current set of inherited attribute dependencies
 - @return  {l} with {f} added to it, IF it's in {inhs} and not already in {l}
 -}
function collectInhs
[String] ::= inhs::[String]  f::FlowVertex  l::[String]
{
  return case f of
  | lhsVertex(a) -> if containsBy(stringEq, a, l) || !containsBy(stringEq, a, inhs) then l else a::l
  | _ -> l
  end;
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

aspect production lhsVertex
top::FlowVertex ::= attrName::String
{
  top.flowTypeName = attrName; -- for syn only, but no way to error on inh here
}
aspect production rhsVertex
top::FlowVertex ::= sigName::String  attrName::String
{
  top.flowTypeName = error("Internal compiler error: shouldn't be solving flow types for child inherited attributes?");
}
aspect production localEqVertex
top::FlowVertex ::= fName::String
{
  top.flowTypeName = fName;
}
aspect production localVertex
top::FlowVertex ::= fName::String  attrName::String
{
  top.flowTypeName = error("Internal compiler error: shouldn't be solving flow types for local inherited attributes?");
}

