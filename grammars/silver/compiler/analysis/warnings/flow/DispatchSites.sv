grammar silver:compiler:analysis:warnings:flow;

aspect production presentAppExpr
top::AppExpr ::= e::Expr
{
  -- A production may share a tree ('@x') in an application of a dispatch signature (not via signature sharing.)
  -- The production decorated the tree first, so its equations for the tree override
  -- the implementation's equations for that child.  Implementations are checked assuming that every equation for
  -- such a child depends only on what the dispatch signature allows (see dispatchChildAllowedInhs).  So the
  -- production's equations for the tree may depend only on that too.  The dispatch signature allows the
  -- dependencies of host-language implementations' equations for that child.  For an implementation in another
  -- extension, the signature also allows the forward flow type (see addDispatchUnknownImplInhEqs).
  local myGraphs::EnvTree<ProductionGraph> = head(searchEnvTree(top.grammarName, top.compiledGrammars)).productionFlowGraphs;

  -- The dispatch signature of what is applied here, and the signature's child at this position
  local dispatchChild::Maybe<(NamedSignature, NamedSignatureElement)> =
    case top.appProd of
    | just(ns) when null(getValueDcl(ns.fullName, top.env)) && sigIndex < length(ns.inputElements) ->
      just((ns, head(drop(sigIndex, ns.inputElements))))
    | just(ns) ->
      case getValueDcl(ns.fullName, top.env) of
      | dcl :: _ ->
        case dcl.implementedSignature of
        | just(sig) when sigIndex < length(sig.inputElements) -> just((sig, head(drop(sigIndex, sig.inputElements))))
        | _ -> nothing()
        end
      | [] -> nothing()
      end
    | nothing() -> nothing()
    end;
  -- The check below skips an implementation passing on its own child to the same position.  Its equations for
  -- that child are checked as those of an implementation (see HiddenTransitiveDeps.sv).
  local passedOn::Boolean =
    case getValueDcl(top.frame.fullName, top.env), dispatchChild, e.flowVertexInfo of
    | dcl :: _, just((sig, _)), just(rhsVertexType(fc)) ->
      case dcl.implementedSignature of
      | just(fSig) -> fSig.fullName == sig.fullName && positionOf(fc, dcl.namedSignature.inputNames) == sigIndex
      | nothing() -> false
      end
    | _, _, _ -> false
    end;

  top.errors <-
    case top.decSiteVertexInfo, e.flowVertexInfo, dispatchChild of
    | just(parent), just(v), just((sig, c))
        when top.config.warnMissingInh && !sigIsShared && isDecorable(top.appExprTyperep, top.env) && !passedOn ->
      let dispatchGraph::ProductionGraph = findProductionGraph(sig.fullName, myGraphs)
      in flatMap(
        \ attr::String ->
          let
            deps::set:Set<String> = onlyLhsInh(expandGraph([v.inhVertex(attr)], top.frame.flowGraph)),
            allowedInhs::set:Set<String> = dispatchChildAllowedInhs(dispatchGraph, rhsInhVertex(c.elementName, attr))
          in
            case set:toList(set:difference(deps,
                   onlyLhsInh(expandGraph(map(parent.inhVertex, set:toList(allowedInhs)), top.frame.flowGraph)))) of
            | [] -> []
            | excess -> [mwdaWrnFromOrigin(top,
                s"Inherited attribute ${attr} supplied to ${v.vertexPP}, which is shared with an application of " ++
                s"${sig.fullName}, depends on ${implode(", ", excess)}; implementations of ${sig.fullName}, " ++
                s"including ones in other extensions, may supply it in its place depending " ++ depListStr(allowedInhs))]
            end
          end,
        filter(vertexHasInhEq(top.frame.fullName, v, _, top.flowEnv),
          getInhAndInhOnTransAttrsOn(c.typerep.typeName, top.env)))
      end
    | _, _, _ -> []
    end;
}
