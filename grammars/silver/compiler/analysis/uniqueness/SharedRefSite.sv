grammar silver:compiler:analysis:uniqueness;

-- Shared references taken in this tree.
-- Combining the references of things that are evaluated together records which of them conflict,
-- so these must only be combined with appendSharedRefs, or unionMutuallyExclusiveRefs for
-- mutually exclusive alternatives - never with ++.
monoid attribute sharedRefs::[(String, SharedRefSite)] with [], appendSharedRefs;

attribute sharedRefs occurs on
  Grammar, File, AGDcls, AGDcl,
  ProductionBody, ProductionStmts, ProductionStmt;
propagate sharedRefs on
  Grammar, File, AGDcls, AGDcl,
  ProductionBody, ProductionStmts, ProductionStmt;

annotation sharedVertex::VertexType;
annotation conflictsWith::[(String, Location, VertexType)];

{--
 - Represents taking of a shared reference to a child or local/production attribute,
 - or to a translation attribute of one (of a translation attribute, and so on),
 - to catch a tree being shared in multiple places.
 - These are keyed by the production and the tree at the root of the shared vertex's
 - translation attributes, so the references that can conflict are found together.
 -}
data SharedRefSite = sharedRefSite with
  sourceGrammar,  -- The grammar of where the reference was taken
  sourceLocation, -- The location of where the reference was taken
  sharedVertex,   -- The shared vertex
  conflictsWith;  -- The references in this grammar that can be taken along with this one (possibly itself, twice)

-- We don't care about the locations or conflicts for correctness, don't compare them to avoid touching interface files.
instance Eq SharedRefSite {
  eq = \ s1::SharedRefSite s2::SharedRefSite ->
    s1.sourceGrammar == s2.sourceGrammar && s1.sharedVertex == s2.sharedVertex;
}

fun sharedRefId (String, Location, VertexType) ::= s::SharedRefSite =
  (s.sourceGrammar, s.sourceLocation, s.sharedVertex);

fun withConflicts SharedRefSite ::= s::SharedRefSite  cs::[(String, Location, VertexType)] =
  if null(cs) then s else s(conflictsWith=union(s.conflictsWith, cs));

{--
 - Combine references taken in mutually exclusive alternatives.
 - A reference can appear in more than one, as when it is taken in a let binding that is used in several,
 - so these are combined into one, with all of its conflicts.
 -}
fun unionMutuallyExclusiveRefs
[(String, SharedRefSite)] ::= rs1::[(String, SharedRefSite)]  rs2::[(String, SharedRefSite)] =
  foldr(
    \ r::(String, SharedRefSite)  rs::[(String, SharedRefSite)] ->
      let same::Pair<[(String, SharedRefSite)] [(String, SharedRefSite)]> =
        partition(\ s::(String, SharedRefSite) -> s.1 == r.1 && sharedRefId(s.2) == sharedRefId(r.2), rs)
      in (r.1, withConflicts(r.2, flatMap(\ s::(String, SharedRefSite) -> s.2.conflictsWith, same.fst))) :: same.snd
      end,
    rs2, rs1);

{--
 - Combine references taken in things that are evaluated together.
 - Sharing a tree also shares its translation attributes, so references to the same tree,
 - or to a tree and a translation attribute (of a translation attribute, and so on) of it, conflict.
 -}
fun appendSharedRefs
[(String, SharedRefSite)] ::= rs1::[(String, SharedRefSite)]  rs2::[(String, SharedRefSite)] =
  if null(rs1) then rs2 else if null(rs2) then rs1 else
  let markConflicts::([(String, SharedRefSite)] ::= [(String, SharedRefSite)] [(String, SharedRefSite)]) =
    \ rs::[(String, SharedRefSite)]  others::[(String, SharedRefSite)] ->
      map(
        \ r::(String, SharedRefSite) ->
          (r.1, withConflicts(r.2,
            map(\ o::(String, SharedRefSite) -> sharedRefId(o.2),
              filter(\ o::(String, SharedRefSite) -> o.1 == r.1 && sharedVerticesConflict(o.2.sharedVertex, r.2.sharedVertex), others)))),
        rs)
  in unionMutuallyExclusiveRefs(markConflicts(rs1, rs2), markConflicts(rs2, rs1))
  end;

-- Does sharing one of these vertices also share the other?
fun sharedVerticesConflict Boolean ::= v1::VertexType  v2::VertexType =
  isSameOrTransOf(v1, v2) || isSameOrTransOf(v2, v1);

-- The tree that v is a translation attribute (of a translation attribute, and so on) of, or v itself
fun transRootVertex VertexType ::= v::VertexType =
  case v of
  | transAttrVertexType(v1, _) -> transRootVertex(v1)
  | _ -> v
  end;

-- Is v1 the same as v2, or a translation attribute (of a translation attribute, and so on) of it?
fun isSameOrTransOf Boolean ::= v1::VertexType  v2::VertexType =
  v1 == v2 ||
  case v1 of
  | transAttrVertexType(v, _) -> isSameOrTransOf(v, v2)
  | _ -> false
  end;
