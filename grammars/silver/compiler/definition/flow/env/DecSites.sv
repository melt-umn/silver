grammar silver:compiler:definition:flow:env;

import silver:util:treeset as set;

-- places where this child was decorated in a production forwarding to this one,
-- or in a dispatch signature that this production implements
fun lookupAllSigShareSites [(String, VertexType)] ::= prod::String sigName::String e::FlowEnv realEnv::Env =
  lookupSigShareSites(prod, sigName, e) ++
  case getValueDcl(prod, realEnv) of
  | dcl :: _ when dcl.implementedSignature matches just(sig) ->
    lookupSigShareSites(
      sig.fullName,
      head(drop(positionOf(sigName, dcl.namedSignature.inputNames), sig.inputNames)),
      e)
  | _ -> []
  end;

{--
 - Represents a tree of decoration sites where an equation might need to be supplied
 - to ensure an inherited attribute is always present on some tree.
 -}
data nonterminal DecSite with decSitePP;

synthesized attribute decSitePP::String;

{--
 - An attribute is present if it is supplied by any of these sites.
 -}
production anyDecSite
top::DecSite ::= ds::[DecSite]
{
  top.decSitePP = implode(" | ", map((.decSitePP), ds));
}

{--
 - An attribute is present if it is supplied by all of these sites.
 -}
production allDecSite
top::DecSite ::= ds::[DecSite]
{
  top.decSitePP = s"{${implode(", ", map((.decSitePP), ds))}}";
}

production directDecSite
top::DecSite ::= prodName::String vt::VertexType
{
  -- TODO: What if vt is an anonVertexType?
  top.decSitePP = s"${vt.vertexPP} of production ${prodName}";
}

production forwardDecSite
top::DecSite ::= 
{
  top.decSitePP = "via forwarding";
}

production transAttrDecSite
top::DecSite ::= attrName::String d::DecSite
{
  top.decSitePP = s"via translation attribute ${attrName}: ${d.decSitePP}";
}

fun foldAnyDecSite DecSite ::= ds::[DecSite] =
  case ds of
  | [d] -> d
  | _ -> anyDecSite(flatMap(\ d -> case d of anyDecSite(ds1) -> ds1 | _ -> [d] end, ds))
  end;

fun foldAllDecSite DecSite ::= ds::[DecSite] =
  case ds of
  | [d] -> d
  | _ -> allDecSite(flatMap(\ d -> case d of allDecSite(ds1) -> ds1 | _ -> [d] end, ds))
  end;

fun prettyDecSites String ::= ds::[DecSite] =
  case ds of
  | [d] -> d.decSitePP
  | _ -> "any of\n\t" ++ implode("\n\t", map((.decSitePP), ds))
  end;

function findDecSites
[DecSite] ::= prodName::String vt::VertexType seen::[(String, VertexType)] flowEnv::FlowEnv realEnv::Env
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

  local recurse::([DecSite] ::= String VertexType) =
    findDecSites(_, _, (prodName, vt) :: seen, flowEnv, realEnv);

  return
    if contains((prodName, vt), seen)
    then []
    else
      directDecSite(prodName, vt) ::
      case vt of
      | forwardVertexType_real() -> [forwardDecSite()]
      | localVertexType("forward") -> [forwardDecSite()]  -- TODO: Not sure if this is actually possible?
      | localVertexType(fName) when isForwardProdAttr(fName, realEnv) -> [forwardDecSite()]
      | _ -> []
      end ++
      flatMap(recurse(prodName, _), lookupRefDecSite(prodName, vt, flowEnv)) ++
      case vt of
      | rhsVertexType(sigName) when lookupSignatureInputElem(sigName, ns).elementShared ->
        [foldAllDecSite(map(foldAnyDecSite, unzipWith(recurse, lookupAllSigShareSites(prodName, sigName, flowEnv, realEnv))))]
      | _ -> []
      end ++
      filterMap(
        \ attrName ->
          case getAttrDcl(attrName, realEnv) of
          | dcl :: _ when dcl.isTranslation ->
            just(transAttrDecSite(attrName, foldAnyDecSite(recurse(prodName, transAttrVertexType(vt, attrName)))))
          | _ -> nothing()
          end,
        getHostSynsFor(ntName, flowEnv));
}

function decSiteHasInhEq
Boolean ::= d::DecSite attrName::String flowEnv::FlowEnv
{
  local transInh::Maybe<(String, String)> = splitTransAttrInh(attrName);
  return
    case d of
    | anyDecSite(ds) -> any(map(decSiteHasInhEq(_, attrName, flowEnv), ds))
    | allDecSite(ds) -> all(map(decSiteHasInhEq(_, attrName, flowEnv), ds))
    | directDecSite(prodName, vt) -> vertexHasInhEq(prodName, vt, attrName, flowEnv)
    | forwardDecSite() -> !transInh.isJust
    | transAttrDecSite(attrName, d) when transInh matches just((transAttr, inhAttr)) ->
      decSiteHasInhEq(d, inhAttr, flowEnv)
    | _ -> false
    end;
}

-- Helper for filtering
fun decSitesMissingInhEq
Boolean ::= attrName::String decSites::[DecSite] flowEnv::FlowEnv =
  !any(map(decSiteHasInhEq(_, attrName, flowEnv), decSites));
