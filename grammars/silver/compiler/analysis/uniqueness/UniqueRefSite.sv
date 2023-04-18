grammar silver:compiler:analysis:uniqueness;

-- Unique references taken in this tree
monoid attribute uniqueRefs::[(String, UniqueRefSite)];

{--
 - Represents taking of a unique reference to a child or local/production attribute.
 - Since taking a unique reference means that inherited equations
 - on the decoration site for attributes not in the reference set are forbidden,
 - this info tracks what decoration sites have partial references taken.
 -}
nonterminal UniqueRefSite with refSet, refFlowDeps,
  sourceGrammar,  -- The grammar of where the reference was taken
  sourceLocation; -- The location of where the reference was taken

-- The attributes in the type of the taken reference
annotation refSet::[String];

-- The flow dependencies of taking this reference
annotation refFlowDeps::[FlowVertex];

abstract production uniqueRefSite
top::UniqueRefSite ::=
{}

-- Append lists of references, ignoring duplicate refs to the same ref site
function unionMutuallyExclusiveRefs
[(String, UniqueRefSite)] ::= rs1::[(String, UniqueRefSite)] rs2::[(String, UniqueRefSite)]
{
  return rs1 ++ filter(\ r::(String, UniqueRefSite) -> !lookup(r.1, rs1).isJust, rs2);
}

-- Compare unique ref sites based on ref set and decoration site.
-- Source location doesn't matter, and we should never be comparing unique ref sites from different grammars.
instance Eq UniqueRefSite {
  eq = \ r1::UniqueRefSite r2::UniqueRefSite -> r1.refSet == r2.refSet;
}

global uniqueContextErrors::([Message] ::= [(String, UniqueRefSite)]) =
  map(\ r::(String, UniqueRefSite) -> err(r.2.sourceLocation, s"Unique reference to ${r.1} taken outside of a unique context."), _);
