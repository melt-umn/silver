grammar silver:definition:flow:driver;

import silver:util only contains;
import silver:definition:type only isDecorable;

nonterminal ProductionGraph with flowTypes, stitchedGraph, prod, lhsNt, transitiveClosure, edgeMap, cullSuspect, flowTypeVertexes, prodGraphs;

inherited attribute flowTypes :: EnvTree<g:Graph<String>>;
inherited attribute prodGraphs :: EnvTree<ProductionGraph>;

-- TODO: future me note: these are good candidates to be "static attributes" maybe?
{--
 - Given a set of flow types, stitches those edges into the graph for
 - all stitch points (i.e. children, locals, forward)
 -}
synthesized attribute stitchedGraph :: ProductionGraph;
{--
 - Just compute the transitive closure of the edge set
 -}
synthesized attribute transitiveClosure :: ProductionGraph;
{--
 - Edge mapper
 -}
synthesized attribute edgeMap :: (set:Set<FlowVertex> ::= FlowVertex);

synthesized attribute cullSuspect :: ProductionGraph;

-- This is, apparently, only used to look up production by name
synthesized attribute prod::String;
-- Only used by solveFlowTypes()
synthesized attribute lhsNt::String;
-- Used in solveFlowTypes
synthesized attribute flowTypeVertexes::[FlowVertex];
-- I'd prefer this not exist, but...

{--
 - An object for representing a production's flow graph.
 - Should ALWAYS be a transitive closure over the edges for 'vertexes'.
 -
 - @param prod  The full name of this production
 - @param lhsNt  The full name of the nonterminal this production constructs
 - @param flowTypeVertexes  The vertexes that we track the flow types of. (Syns and optionally fwd)
 - @param graph  The edges within this production
 - @param suspectEdges  Edges that are not permitted to affect their OWN flow types (but perhaps some unknown other flowtypes)
 - @param stitchPoints  Places where current flow types need grafting to this graph to yield a full flow graph
 -
 - @see constructProductionGraph for how to go about getting an object of this type
 -}
abstract production productionGraph
top::ProductionGraph ::=
  prod::String
  lhsNt::String
  flowTypeVertexes::[FlowVertex]
  graph::g:Graph<FlowVertex>
  suspectEdges::[Pair<FlowVertex FlowVertex>]
  stitchPoints::[StitchPoint]
{
  top.prod = prod;
  top.lhsNt = lhsNt;
  top.flowTypeVertexes = flowTypeVertexes;
  
  top.stitchedGraph = 
    let newEdges :: [Pair<FlowVertex FlowVertex>] =
          filter(edgeIsNew(_, graph),
            foldr(append, [], map(stitchEdgesFor(_, top.flowTypes, top.prodGraphs), stitchPoints)))
    in let repaired :: g:Graph<FlowVertex> =
             repairClosure(newEdges, graph)
    in if null(newEdges) then top else
         productionGraph(prod, lhsNt, flowTypeVertexes, repaired, suspectEdges, stitchPoints)
    end end;
  
  top.transitiveClosure =
    let transitiveClosure :: g:Graph<FlowVertex> =
          transitiveClose(graph)
    in
      productionGraph(prod, lhsNt, flowTypeVertexes, transitiveClosure, suspectEdges, stitchPoints) end;
    
  top.edgeMap = g:edgesFrom(_, graph);
  
  top.cullSuspect = 
    -- this potentially introduces the same edge twice, but that's a nonissue
    let newEdges :: [Pair<FlowVertex FlowVertex>] =
          foldr(append, [], 
            map(findAdmissibleEdges(_, graph, findFlowType(lhsNt, top.flowTypes)), suspectEdges))
    in let repaired :: g:Graph<FlowVertex> =
             repairClosure(newEdges, graph)
    in if null(newEdges) then top else
         productionGraph(prod, lhsNt, flowTypeVertexes, repaired, suspectEdges, stitchPoints)
    end end;
}

{--
 - Produces a ProductionGraph in some special way. Fixes up implicit equations,
 - figures out stitch points, and so forth.
 -
 - 1. All HOA synthesized attributes have a dep on their equation. 
 - 1b. Same for forwarding.
 - 2. All synthesized attributes missing equations have dep on their corresponding fwd.
 - 2b. OR use their default if not forwarding and it exists.
 - 3. All inherited attributes not supplied to forward have copies.
 - 4. All autocopy attributes not supplied to childred have copies.
 -
 - @param prod  The full name of the production
 - @param defs  The set of defs from prodGraphContribs
 - @param flowEnv  A full flow environment
 - @param realEnv  A full real environment
 - @return A fixed up graph.
 -}
function constructProductionGraph
ProductionGraph ::= prod::String  defs::[FlowDef]  flowEnv::Decorated FlowEnv  realEnv::Decorated Env
{
  -- The dcl for this production
  local dcl :: DclInfo = head(getValueDclAll(prod, realEnv));
  -- The LHS nonterminal full name
  local nt :: NtName = dcl.namedSignature.outputElement.typerep.typeName;
  -- All attributes occurrences
  local attrs :: [DclInfo] = getAttrsOn(nt, realEnv);
  -- Just synthesized attributes.
  local syns :: [String] = map((.attrOccurring), filter(isOccursSynthesized(_, realEnv), attrs));
  -- Just inherited.
  local inhs :: [String] = map((.attrOccurring), filter(isOccursInherited(_, realEnv), attrs));
  -- Autocopy.
  local autos :: [String] = filter(isAutocopy(_, realEnv), inhs);
  
  -- Normal edges!
  local normalEdges :: [Pair<FlowVertex FlowVertex>] =
    foldr(append, [], map((.flowEdges), defs));
  
  local nonForwarding :: Boolean =
    null(lookupFwd(prod, flowEnv));
  
  -- Insert implicit equations.
  local fixedEdges :: [Pair<FlowVertex FlowVertex>] =
    normalEdges ++
    (if nonForwarding
     then addDefEqs(prod, nt, syns, flowEnv)
     else -- This first pair is used sometimes as an alias:
          pair(lhsSynVertex("forward"), forwardEqVertex()) ::
          addFwdEqs(syns) ++ 
          addFwdSynEqs(prod, synsBySuspicion.fst, flowEnv) ++ 
          addFwdInhEqs(prod, inhs, flowEnv)) ++
    fixupAllHOAs(defs, flowEnv, realEnv) ++
    addAllAutoCopyEqs(prod, dcl.namedSignature.inputElements, autos, flowEnv, realEnv);
  
  -- (safe, suspect)
  local synsBySuspicion :: Pair<[String] [String]> =
    partition(contains(_, getNonSuspectAttrsForProd(prod, flowEnv)), syns);
  
  -- No implicit equations here, just keep track.
  local suspectEdges :: [Pair<FlowVertex FlowVertex>] =
    foldr(append, [], map((.suspectFlowEdges), defs)) ++
    -- If it's forwarding .snd is attributes not known at forwarding time. If it's non, then actually .snd is all attributes. Ignore.
    if nonForwarding then [] else addFwdSynEqs(prod, synsBySuspicion.snd, flowEnv);

  -- RHS and locals and forward.
  local stitchPoints :: [StitchPoint] =
    rhsStitchPoints(dcl.namedSignature.inputElements) ++
    localStitchPoints(nt, defs);
  
  local flowTypeVertexes :: [FlowVertex] =
    (if nonForwarding then [] else [forwardEqVertex()]) ++
      map(lhsSynVertex, syns);
  
  local initialGraph :: g:Graph<FlowVertex> =
    createFlowGraph(fixedEdges);

  return productionGraph(prod, nt, flowTypeVertexes, initialGraph, suspectEdges, stitchPoints).transitiveClosure;
}

---- Begin helpers for fixing up graphs ----------------------------------------

{--
 - Introduces 'hoa.syn -> hoaeq' edges.
 - These are ALWAYS included in standard edges.
 - TODO: We actually turn 'x.a' into an access of v.for(a) and v.eq already. So we may not need to add these edges???
 -}
function fixupAllHOAs
[Pair<FlowVertex FlowVertex>] ::= d::[FlowDef] flowEnv::Decorated FlowEnv realEnv::Decorated Env
{
  return case d of
  | [] -> []
  | localEq(_, fN, "", deps) :: rest -> fixupAllHOAs(rest, flowEnv, realEnv)
  | localEq(_, fN, tN, deps) :: rest -> 
      let syns :: [String] = 
        map((.attrOccurring),
          filter(isOccursSynthesized(_, realEnv),
            getAttrsOn(tN, realEnv)))
      in addHOASynDeps(syns, fN) ++
        fixupAllHOAs(rest, flowEnv, realEnv) end
  | anonEq(_, fN, tN, _, deps) :: rest -> -- exact duplicate of the above. It'd be nice to have multiple patterns with one body...
      let syns :: [String] = 
        map((.attrOccurring),
          filter(isOccursSynthesized(_, realEnv),
            getAttrsOn(tN, realEnv)))
      in addAnonSynDeps(syns, fN) ++
        fixupAllHOAs(rest, flowEnv, realEnv) end
  | _ :: rest -> fixupAllHOAs(rest, flowEnv, realEnv)
  end;
}
-- Helper for above
function addHOASynDeps
[Pair<FlowVertex FlowVertex>] ::= synattrs::[String]  fName::String
{
  return if null(synattrs) then []
  else pair(localVertex(fName, head(synattrs)), localEqVertex(fName)) :: addHOASynDeps(tail(synattrs), fName);
}
function addAnonSynDeps
[Pair<FlowVertex FlowVertex>] ::= synattrs::[String]  fName::String
{
  return if null(synattrs) then []
  else pair(anonVertex(fName, head(synattrs)), anonEqVertex(fName)) :: addAnonSynDeps(tail(synattrs), fName);
}
{--
 - Introduces implicit 'forward.syn -> forward' equations.
 - TODO: We might be able to fold this into SynEqs. Because those should emit deps on BOTH syn and eq (this is what we emit for attribute access)
 -}
function addFwdEqs
[Pair<FlowVertex FlowVertex>] ::= syns::[String]
{
  return if null(syns) then []
  else 
    pair(forwardVertex(head(syns)), forwardEqVertex()) :: addFwdEqs(tail(syns));
}
{--
 - Introduces implicit 'lhs.syn -> forward.syn' equations.
 - Called twice: once for safe edges, later for SUSPECT edges!
 - TODO: to parallel attribute access, this should probably also emit 'lhs.syn -> forward.eq' edges too. Currently covered by addFwdEqs, though!
 -}
function addFwdSynEqs
[Pair<FlowVertex FlowVertex>] ::= prod::ProdName syns::[String] flowEnv::Decorated FlowEnv
{
  return if null(syns) then []
  else (if null(lookupSyn(prod, head(syns), flowEnv))
    then [pair(lhsSynVertex(head(syns)), forwardVertex(head(syns)))] else []) ++
    addFwdSynEqs(prod, tail(syns), flowEnv);
}
{--
 - Introduces implicit 'forward.inh = lhs.inh' equations.
 - Inherited equations are never suspect.
 -}
function addFwdInhEqs
[Pair<FlowVertex FlowVertex>] ::= prod::ProdName inhs::[String] flowEnv::Decorated FlowEnv
{
  return if null(inhs) then []
  else (if null(lookupFwdInh(prod, head(inhs), flowEnv)) then [pair(forwardVertex(head(inhs)), lhsInhVertex(head(inhs)))] else []) ++
    addFwdInhEqs(prod, tail(inhs), flowEnv);
}
{--
 - Introduces default equations deps. Realistically, should be empty, always.
 -}
function addDefEqs
[Pair<FlowVertex FlowVertex>] ::= prod::ProdName nt::NtName syns::[String] flowEnv :: Decorated FlowEnv
{
  return if null(syns) then []
  else (if null(lookupSyn(prod, head(syns), flowEnv)) 
        then let x :: [FlowDef] = lookupDef(nt, head(syns), flowEnv)
              in if null(x) then [] else head(x).flowEdges 
             end
        else []) ++
    addDefEqs(prod, nt, tail(syns), flowEnv);
}
{--
 - Introduces 'rhs.inh = lhs.inh' wherever not present.
 - Inherited equations are never suspect.
 -}
function addAllAutoCopyEqs
[Pair<FlowVertex FlowVertex>] ::= prod::ProdName sigNames::[NamedSignatureElement] inhs::[String] flowEnv::Decorated FlowEnv realEnv::Decorated Env
{
  return if null(sigNames) then []
  else addAutocopyEqs(prod, head(sigNames), inhs, flowEnv, realEnv) ++ addAllAutoCopyEqs(prod, tail(sigNames), inhs, flowEnv, realEnv);
}
-- Helper for above.
function addAutocopyEqs
[Pair<FlowVertex FlowVertex>] ::= prod::ProdName sigName::NamedSignatureElement inhs::[String] flowEnv::Decorated FlowEnv realEnv::Decorated Env
{
  return if null(inhs) then []
  else (if null(lookupInh(prod, sigName.elementName, head(inhs), flowEnv))  -- no equation
        && !null(getOccursDcl(head(inhs), sigName.typerep.typeName, realEnv)) -- and it occurs on this type
        then [pair(rhsVertex(sigName.elementName, head(inhs)), lhsInhVertex(head(inhs)))]
        else []) ++
    addAutocopyEqs(prod, sigName, tail(inhs), flowEnv, realEnv);
}

---- End helpers for fixing up graphs ------------------------------------------

---- Begin helpers for figuring out stitch points ------------------------------

function localStitchPoints
[StitchPoint] ::= nt::NtName  d::[FlowDef]
{
  return case d of
  | [] -> []
  -- We add the forward stitch point here, too!
  | fwdEq(_, _, _) :: rest -> nonterminalStitchPoint(nt, forwardVertex) :: localStitchPoints(nt, rest)
  -- Ignore locals that aren't nonterminal types!
  | localEq(_, fN, "", _) :: rest -> localStitchPoints(nt, rest)
  -- Add locals that are nonterminal types.
  | localEq(_, fN, tN, _) :: rest -> nonterminalStitchPoint(tN, localVertex(fN, _)) :: localStitchPoints(nt, rest)
  -- Add all anon decoration sites
  | anonEq(_, fN, tN, _, _) :: rest -> nonterminalStitchPoint(tN, anonVertex(fN, _)) :: localStitchPoints(nt, rest)
  -- Ignore all other flow def info
  | _ :: rest -> localStitchPoints(nt, rest)
  end;
}
function rhsStitchPoints
[StitchPoint] ::= rhs::[NamedSignatureElement]
{
  return if null(rhs) then []
  -- We want only NONTERMINAL stitch points!
  else if head(rhs).typerep.isDecorable
       then nonterminalStitchPoint(
              head(rhs).typerep.typeName,
              rhsVertex(head(rhs).elementName, _)) :: rhsStitchPoints(tail(rhs))
       else rhsStitchPoints(tail(rhs));
}

---- End helpers for figuring our stitch points --------------------------------

function getFst
a ::= v::Pair<a b>
{ return v.fst; }

function prodGraphToEnv
Pair<String ProductionGraph> ::= p::ProductionGraph
{
  return pair(p.prod, p);
}

---- Begin Suspect edge handling -----------------------------------------------

{--
 - This function finds edges that should be introduced from a suspect edge.
 -
 - Suspect edges themselves can never be introduced, because the interaction of
 - introducing two or more suspect edges can be undesirable.  (a,b) might be
 - introduced, followed by (b,c). But (b,c) might have prevented (a,b) from
 - appearing!
 -
 - Instead we introduce their ultimate dependencies of interest:
 - If (a,b) is introduced, we actually introduce (a, x) for x: an inherited
 - attribute that a does not already depend upon that is in a's flow type.
 - This way, after (b,c)'s edges are admitted, we come back to (a,b) and do not
 - admit the extra edges c introduced (through b) for a.
 -
 - A note on this being applied "in parallel:" it's okay not to update 'ft' and 'graph'
 - after each edge is introduced, as this is conservative: it just means we'll
 - potentially introduce an edge next iteration.
 -
 - The reason is that each edge is TO an lhsInh, which never gets edges from it.
 - So once valid that edge is valid, it is always valid. No additional edges or
 - flow type updates will change that.
 -
 - @param edge  A suspect edge. INVARIANT: edge.fst can always be looked up in the flow type.
 -              (currently, a syn or fwd)
 - @param graph  The current graph
 - @param ft  The current flow types for the nonterminal this graph belongs to.
 - @return  Edges to introduce. INVARIANT: .fst is always edge.fst, .snd is
 -          always an lhsInhVertex.
 -}
function findAdmissibleEdges
[Pair<FlowVertex FlowVertex>] ::= edge::Pair<FlowVertex FlowVertex>  graph::g:Graph<FlowVertex>  ft::g:Graph<String>
{
  -- The current flow type of the edge's source vertex (which is always a thing in the flow type)
  local currentDeps :: set:Set<String> =
    g:edgesFrom(edge.fst.flowTypeName, ft);
  
  local targetNotSource :: set:Set<FlowVertex> = 
    set:difference(
      g:edgesFrom(edge.snd, graph),
      g:edgesFrom(edge.fst, graph));
  
  -- ONLY those that ARE in current. i.e. dependencies that do not expand the flow type of this source vertex.
  local validDeps :: [FlowVertex] = 
    filter(isLhsInhSet(_, currentDeps), set:toList(targetNotSource));
  
  return if set:isEmpty(currentDeps) then [] -- just a quick optimization.
  else map(pair(edge.fst, _), validDeps);
}

