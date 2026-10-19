grammar silver:compiler:definition:flow:env;

import silver:compiler:definition:flow:driver only ProductionGraph, findProductionGraph, dispatchInputs;

{--
 - Every path of decoration sites from vt in prodName, for bounding an override equation (see
 - pathBound).  A path that comes back to a vertex type already on it, in the same instance of that
 - vertex type's production, is cut there.  Applied productions and dispatch signatures are told
 - apart by their production graphs (prodGraphs), so a path also enters those not in scope here.
 -}
fun findDecSitePaths
DecSitePaths ::= prodName::String  vt::VertexType  flowEnv::FlowEnv  prodGraphs::EnvTree<ProductionGraph> =
  decSitePathsIn([], "", prodName, vt, flowEnv, prodGraphs);

-- findDecSitePaths, where path holds the vertex types already on the path, each with the production
-- instance it is in, and entered is the child at which the path entered prodName ("" where the path
-- starts).
function decSitePathsIn
DecSitePaths ::= path::[(String, VertexType)]  entered::String  prodName::String  vt::VertexType
  flowEnv::FlowEnv  prodGraphs::EnvTree<ProductionGraph>
{
  -- An inner instance of a production, entered at another child, is walked again, so a cut has a
  -- loop head of the same instance.
  local key::(String, VertexType) = (s"${prodName}@${entered}", vt);
  local next::(DecSitePaths ::= String String VertexType) =
    \ e::String p::String v::VertexType -> decSitePathsIn(key :: path, e, p, v, flowEnv, prodGraphs);

  -- Where the child sigName of an application of applied continues: into the production, or into
  -- each host-language implementation of a dispatch signature.
  local intoApplied::(AppliedChild ::= String String) =
    \ applied::String sigName::String ->
      case findProductionGraph(applied, prodGraphs).dispatchInputs of
      | nothing() -> prodChild(next(sigName, applied, rhsVertexType(sigName)))
      | just(inputNames) ->
        dispatchSigChild(flatMap(
          \ impl::(String, [String]) ->
            case drop(positionOf(sigName, inputNames), impl.2) of
            | sn :: _ -> [implChild(impl.1, sn, zip(impl.2, inputNames), next(sn, impl.1, rhsVertexType(sn)))]
            | [] -> []
            end,
          getImplementingProds(applied, flowEnv)))
      end;

  local steps::[PathStep] =
    (case vt of
     -- The tree at parent starts a path of its own.
     | subtermVertexType(parent, applied, sigName) ->
       [appliedStep(parent, applied, sigName, intoApplied(applied, sigName),
          decSitePathsIn([], entered, prodName, parent, flowEnv, prodGraphs))]
     | _ -> []
     end) ++
    -- Where this tree, or a tree that it is a translation attribute of, is shared
    map(
      \ d::VertexType -> localStep(sameAttr(), next(entered, prodName, d)),
      lookupAllRefPossibleDecSites(prodName, vt, flowEnv)) ++
    -- Where its translation attributes are shared
    flatMap(
      \ s::([String], VertexType) ->
        map(
          \ d::VertexType -> localStep(onTransAttrs(s.1), next(entered, prodName, d)),
          lookupRefPossibleDecSites(prodName, s.2, flowEnv)),
      sharedTransAttrsOf(prodName, vt, flowEnv));

  return if contains(key, path) then pathCut(vt) else pathVertex(vt, steps);
}

-- attr as named past a step in the same production, or nothing() where it is not an attribute of
-- the translation attribute shared there.
fun renameAttr Maybe<String> ::= rename::AttrRename  attr::String =
  case rename of
  | sameAttr() -> just(attr)
  | onTransAttrs(transAttrs) -> stripTransAttrs(transAttrs, attr)
  end;

-- attr without the translation attributes transAttrs in front of it, one at a time (see
-- splitTransAttrInh), so that t1.t2 itself is not an attribute of t1.t2.
fun stripTransAttrs Maybe<String> ::= transAttrs::[String]  attr::String =
  case transAttrs, splitTransAttrInh(attr) of
  | [], _ -> just(attr)
  | transAttr :: rest, just((t, k)) when t == transAttr -> stripTransAttrs(rest, k)
  | _, _ -> nothing()
  end;
