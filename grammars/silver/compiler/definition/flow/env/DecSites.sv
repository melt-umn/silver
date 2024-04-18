grammar silver:compiler:definition:flow:env;

import silver:util:treemap as map;
import silver:util:treeset as set;

{--
 - Generate a decision tree to determine all decoration sites where an inherited equation could be supplied
 - for it to be available on some vertex type.
 -
 - @param prodName The name of the production containing the vertex type.
 - @param vt The vertex type to find decoration sites for.
 - @param seen A set of (production name, vertex type) that have already been visited.
 - @param flowEnv The flow environment.
 - @param realEnv The regular environment.
 - @return A decision tree to determine if an inherited attributes has been supplied for vt.
 -}
function findDecSites
DecSiteTree ::= prodName::String vt::VertexType seen::set:Set<String> flowEnv::FlowEnv realEnv::Env
{
  local prodDcl :: [ValueDclInfo] = getValueDcl(prodName, realEnv);
  local ns :: NamedSignature =
    case prodDcl of
    | d :: _ -> d.namedSignature
    | [] -> bogusNamedSignature()
    end;
  local ntName::String =
    case vt of
    | forwardVertexType_real() -> ns.outputElement.typerep.typeName
    | localVertexType(fName) when getValueDcl(fName, realEnv) matches dcl :: _ -> dcl.typeScheme.typeName
    | rhsVertexType(sigName) -> lookupSignatureInputElem(sigName, ns).typerep.typeName
    | _ -> ""
    end;

  local recurse::(DecSiteTree ::= String VertexType) =
    findDecSites(_, _, set:add([crossnames(prodName, vt.vertexName)], seen), flowEnv, realEnv);

  return
    if set:contains(crossnames(prodName, vt.vertexName), seen)
    then neverDec()
    else
      -- Direct inherited equation at a decoration site
      (if vt.isInhDefVertex
       then directDec(prodName, vt)
       else neverDec()) ++
      case vt of
      -- Via forwarding
      | forwardVertexType_real() -> forwardDec()
      | localVertexType("forward") -> forwardDec()  -- TODO: Not sure if this is actually possible?
      | localVertexType(fName) when isForwardProdAttr(fName, realEnv) -> forwardDec()
      -- Via projected remote equation
      | subtermVertexType(_, prodOrSig, sigName) ->
        foldAllDecSite(
          map(recurse(_, rhsVertexType(sigName)),
            if !null(getValueDcl(prodOrSig, realEnv))
            then [prodOrSig]
            else getImplementingProds(prodOrSig, flowEnv)))
      -- Via signature/dispatch sharing
      | rhsVertexType(sigName) when lookupSignatureInputElem(sigName, ns).elementShared ->
        foldAllDecSite(unzipWith(recurse,
          -- places where this child was decorated in a production forwarding to this one
          lookupSigShareSites(prodName, sigName, flowEnv) ++
          -- or in a dispatch signature that this production implements
          case getValueDcl(prodName, realEnv) of
          | dcl :: _ when dcl.implementedSignature matches just(sig) ->
            lookupSigShareSites(
              sig.fullName,
              head(drop(positionOf(sigName, dcl.namedSignature.inputNames), sig.inputNames)),
              flowEnv)
          | _ -> []
          end))
      | _ -> neverDec()
      end ++
      -- Via direct sharing
      foldAnyDecSite(map(recurse(prodName, _), lookupRefDecSite(prodName, vt, flowEnv))) ++
      -- Via translation attributes
      foldAnyDecSite(
        filterMap(
          \ attrName ->
            case getAttrDcl(attrName, realEnv) of
            | dcl :: _ when dcl.isTranslation ->
              just(transAttrDec(attrName, recurse(prodName, transAttrVertexType(vt, attrName))))
            | _ -> nothing()
            end,
          getHostSynsFor(ntName, flowEnv)));
}

strategy attribute reduceDecSite = innermost(  -- TODO: Avoid forcing the entire tree if the first dec site is supplied?
  rule on DecSiteTree of
  | altDec(alwaysDec(), d) -> alwaysDec()
  | altDec(d, alwaysDec()) -> alwaysDec()
  | altDec(neverDec(), d) -> d
  | altDec(d, neverDec()) -> d
  | bothDec(alwaysDec(), d) -> d
  | bothDec(d, alwaysDec()) -> d
  | bothDec(neverDec(), _) -> neverDec()
  | bothDec(_, neverDec()) -> neverDec()
  | altDec(altDec(d1, d2), d3) -> altDec(d1, altDec(d2, d3))
  | bothDec(bothDec(d1, d2), d3) -> bothDec(d1, bothDec(d2, d3))
  | altDec(d1, d2) when contains(d1, d2.decSiteAlts) -> d2
  | bothDec(d1, d2) when contains(d1, d2.decSiteReqs) -> d2
  | transAttrDec(attrName, neverDec()) -> neverDec()
  -- This is assuming the we have already resolved for some inh-on-a-trans-attr that matches attrName.
  | transAttrDec(attrName, alwaysDec()) -> alwaysDec()
  end
) occurs on DecSiteTree;

inherited attribute attrToResolve::String occurs on DecSiteTree;
propagate attrToResolve on DecSiteTree excluding transAttrDec;
aspect production transAttrDec
top::DecSiteTree ::= _ d::DecSiteTree
{
  d.attrToResolve = splitTransAttrInh(top.attrToResolve).fromJust.2;
}

attribute flowEnv occurs on DecSiteTree;
strategy attribute resolveDecSite = allTopDown(
  rule on top::DecSiteTree of
  | directDec(prodName, vt)
      when vertexHasInhEq(prodName, vt, top.attrToResolve, top.flowEnv) ->
    alwaysDec()
  | forwardDec() ->
    if splitTransAttrInh(top.attrToResolve).isJust
    then neverDec()
    else alwaysDec()
  | transAttrDec(attrName, d) when
      case splitTransAttrInh(top.attrToResolve) of
      | just((transAttr, inhAttr)) -> transAttr != attrName
      | _ -> true
      end -> neverDec()
  end
) <* reduceDecSite
occurs on DecSiteTree;

propagate flowEnv, reduceDecSite, resolveDecSite on DecSiteTree;

{--
  - Determine if some decoration site has some inherited attribute supplied.
  -
  - @param d The decoration site to check.
  - @param attrName The name of the inherited attribute.
  - @param flowEnv The flow environment.
  - @return alwaysDec(), if the attribute is always present,
  - or else the places where it could be supplied.
  -}
function resolveDecSiteInhEq
DecSiteTree ::= attrName::String d::DecSiteTree flowEnv::FlowEnv
{
  d.attrToResolve = attrName;
  d.flowEnv = flowEnv;
  return d.resolveDecSite;
}

{--
  - Determine if some flow vertex type in a production has some inherited attribute supplied.
  -
  - @param prodName The name of the production containing the vertex.
  - @param vt The vertex type to check.
  - @param attrName The name of the inherited attribute.
  - @param flowEnv The flow environment.
  - @return alwaysDec(), if the attribute is always present,
  - or else the places where it could be supplied.
  -}
fun resolveInhEq
DecSiteTree ::= prodName::String vt::VertexType attrName::String flowEnv::FlowEnv realEnv::Env =
  resolveDecSiteInhEq(attrName, findDecSites(prodName, vt, set:empty(), flowEnv, realEnv), flowEnv);

-- Helper for checking multiple inh attributes
function decSitesMissingInhEqs
[(DecSiteTree, [String])] ::= prodName::String vt::VertexType attrNames::[String] flowEnv::FlowEnv realEnv::Env
{
  local d::DecSiteTree = findDecSites(prodName, vt, set:empty(), flowEnv, realEnv);
  local resolved::map:Map<DecSiteTree String> =
    map:add(map(\ a -> (resolveDecSiteInhEq(a, d, flowEnv), a), attrNames), map:empty());
  return flatMap(\ d -> 
    case map:lookup(d, resolved) of
    | [] -> []
    | missing -> [(d, missing)]
    end,
    remove(alwaysDec(), map:keys(resolved)));
}
