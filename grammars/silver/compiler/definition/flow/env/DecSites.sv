grammar silver:compiler:definition:flow:env;

import silver:util:treemap as map;

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
 - Represents a decision tree of potential decoration sites to determine if an inherited attribute
 - has been supplied for some vertex type.
 -}
nonterminal DecSiteTree with decSitePP, decSiteAlts, decSiteReqs;
flowtype DecSiteTree = decorate {}, forward {};

synthesized attribute decSitePP::String;
synthesized attribute decSiteAlts::[DecSiteTree];
synthesized attribute decSiteReqs::[DecSiteTree];

aspect default production
top::DecSiteTree ::=
{
  top.decSiteAlts = [top];
  top.decSiteReqs = [top];
}

{--
 - No attributes can be known to be supplied.
 - Primarilly, denotes a cycle in decoration site resolution.
 -}
production neverDec
top::DecSiteTree ::= 
{
  top.decSitePP = "never";
  top.decSiteAlts = [];
}

{-
 - All attributes are known to be supplied.
 - Can occur if a dispatch signature has no implementing productions, or in the search reduction process.
 -}
production alwaysDec
top::DecSiteTree ::= 
{
  top.decSitePP = "always";
  top.decSiteReqs = [];
}

{--
 - An attribute can be supplied by either of these sites.
 -}
production altDec
top::DecSiteTree ::= d1::DecSiteTree d2::DecSiteTree
{
  top.decSitePP = implode(" | ", map((.decSitePP), top.decSiteAlts));
  top.decSiteAlts = d1.decSiteAlts ++ d2.decSiteAlts;
}

{--
 - An attribute is present if it is supplied by both of these sites.
 -}
production bothDec
top::DecSiteTree ::= d1::DecSiteTree d2::DecSiteTree
{
  top.decSitePP = s"{${implode(", ", map((.decSitePP), top.decSiteReqs))}}";
  top.decSiteReqs = d1.decSiteReqs ++ d2.decSiteReqs;
}

{--
 - An attribute can be supplied to a vertex type in some production.
 -}
production directDec
top::DecSiteTree ::= prodName::String vt::VertexType
{
  -- TODO: What if vt is an anonVertexType?
  top.decSitePP = s"${vt.vertexPP} of production ${prodName}";
}

{--
 - An attribute can be supplied via forwarding.
 - This does *not* include inherited attributes of translation attributes.
 -}
production forwardDec
top::DecSiteTree ::= 
{
  top.decSitePP = "via forwarding";
}

{--
 - An inherited attribute on a translation attribute can be supplied via a translation attribute.
 -}
production transAttrDec
top::DecSiteTree ::= attrName::String d::DecSiteTree
{
  top.decSitePP = s"via translation attribute ${attrName}: ${d.decSitePP}";
}

fun foldAnyDecSite DecSiteTree ::= ds::[DecSiteTree] =
  foldr(altDec, neverDec(), ds);

fun foldAllDecSite DecSiteTree ::= ds::[DecSiteTree] =
  foldr(bothDec, alwaysDec(), ds);

fun prettyDecSites String ::= d::DecSiteTree =
  case d.decSiteAlts of
  | [d] -> d.decSitePP
  | ds -> "any of\n\t" ++ implode("\n\t", map((.decSitePP), ds))
  end;

derive Eq, Ord on DecSiteTree;

-- Semigroup/monoid instances for composing alternatives, since that more common that conjunction
instance Semigroup DecSiteTree {
  append = altDec;
}

instance Monoid DecSiteTree {
  mempty = neverDec();
}

{--
 - Generate a decision tree to determine all decoration sites where an inherited equation could be supplied
 - for it to be available on some vertex type.
 -
 - @param prodName The name of the production containing the vertex type.
 - @param vt The vertex type to find decoration sites for.
 - @param seen A list of (production name, vertex type) pairs that have already been visited.
 - @param flowEnv The flow environment.
 - @param realEnv The regular environment.
 - @return A decision tree to determine if an inherited attributes has been supplied for vt.
 -}
function findDecSites
DecSiteTree ::= prodName::String vt::VertexType seen::[(String, VertexType)] flowEnv::FlowEnv realEnv::Env
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
    findDecSites(_, _, (prodName, vt) :: seen, flowEnv, realEnv);

  return
    if contains((prodName, vt), seen)
    then neverDec()
    else
      -- Direct inherited equation at a decoration site
      (if vt.isInhDefVertex
       then directDec(prodName, vt)
       else neverDec()) ++
      -- Via forwarding
      case vt of
      | forwardVertexType_real() -> forwardDec()
      | localVertexType("forward") -> forwardDec()  -- TODO: Not sure if this is actually possible?
      | localVertexType(fName) when isForwardProdAttr(fName, realEnv) -> forwardDec()
      | _ -> neverDec()
      end ++
      -- Via direct sharing
      foldAnyDecSite(map(recurse(prodName, _), lookupRefDecSite(prodName, vt, flowEnv))) ++
      case vt of
      | subtermVertexType(_, prodOrSig, sigName) ->
        foldAllDecSite(
          map(recurse(_, rhsVertexType(sigName)),
            if !null(getValueDcl(prodOrSig, realEnv))
            then [prodOrSig]
            else getImplementingProds(prodOrSig, flowEnv)))
      | _ -> neverDec()
      end ++
      -- Via signature/dispatch sharing
      case vt of
      | rhsVertexType(sigName) when lookupSignatureInputElem(sigName, ns).elementShared ->
        foldAllDecSite(unzipWith(recurse, lookupAllSigShareSites(prodName, sigName, flowEnv, realEnv)))
      | _ -> neverDec()
      end ++
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

strategy attribute reduceDecSite = outermost(  -- TODO: Is innermost more efficient here?
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

-- Helper for checking multiple inh attributes
fun decSitesMissingInhEqs
[(DecSiteTree, [String])] ::= attrNames::[String] d::DecSiteTree flowEnv::FlowEnv =
  let resolved::map:Map<DecSiteTree String> =
    map:add(map(\ a -> (resolveDecSiteInhEq(a, d, flowEnv), a), attrNames), map:empty())
  in flatMap(\ d -> 
    case map:lookup(d, resolved) of
    | [] -> []
    | missing -> [(d, missing)]
    end,
    remove(alwaysDec(), map:keys(resolved)))
  end;
