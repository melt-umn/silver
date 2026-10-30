grammar silver:compiler:definition:flow:ast;

{--
 - The paths of decoration sites that a tree may take from a vertex type (see findDecSitePaths),
 - which the override check's walk follows (see pathBound).  Unlike a DecSiteTree it holds no
 - equations: the walk decides those at each vertex type from the flow environment (see
 - equationRule).
 -}
data DecSitePaths
  -- The tree at vt, and the steps a path may take from there.
  = pathVertex vt::VertexType  steps::[PathStep]
  -- The cut where a path comes back to vt, in the same instance of vt's production (entered at the
  -- same child).
  | pathCut vt::VertexType;

-- A step from a vertex type to the next decoration site.
data PathStep
  -- A decoration site in the same production, where the walk's attribute is renamed by rename.
  = localStep rename::AttrRename  dest::DecSitePaths
  -- The tree is the child sigName of an application of applied at parent: it continues as that
  -- child (into), and parentTree, the paths of the tree at parent, realizes applied's LHS.
  | appliedStep parent::VertexType  applied::String  sigName::String  into::AppliedChild  parentTree::DecSitePaths;

-- How a step in the same production renames the walk's attribute.
data AttrRename
  -- The tree itself is shared there.
  = sameAttr
  -- A translation attribute of the tree (of a translation attribute, and so on: outermost first) is
  -- shared there, so t1.t2.k becomes k.
  | onTransAttrs transAttrs::[String];

-- Where an applied child continues.
data AppliedChild
  -- The child of a production.
  = prodChild dest::DecSitePaths
  -- The child of a dispatch signature, as the child of each host-language implementation.  The walk
  -- also accounts for the implementations not known here (see unknownImplSets).
  | dispatchSigChild impls::[ImplChild];

-- The child implSigName of impl, at the position of the dispatch signature's child; names pairs
-- impl's children with the dispatch signature's.
data ImplChild = implChild impl::String  implSigName::String  names::[(String, String)]  dest::DecSitePaths;
