grammar silver:compiler:analysis:warnings:flow;

-- An inherited equation on the forward, where a forward production attribute is shared
-- unconditionally as the forward (forwards to @x), has no effect: x's tree already has this
-- production as its forward parent, which decorating it again does not change.  The flow graph
-- keeps such equations as extra dependencies, so this is a plain warning.

aspect production inheritedAttributeDef
top::ProductionStmt ::= @dl::DefLHS @attr::QNameAttrOccur e::Expr
{
  top.errors <-
    case noEffectFwdProdAttr(top.frame.fullName, dl.defLHSVertex, top.flowEnv) of
    | just(x) when top.config.warnMissingInh && dl.found && attr.found ->
      [wrnFromOrigin(top, noEffectInhEqMsg(attr.attrDcl.fullName, dl.unparse, x))]
    | _ -> []
    end;
}
aspect production inhBaseColAttributeDef
top::ProductionStmt ::= @dl::DefLHS @attr::QNameAttrOccur e::Expr
{
  top.errors <-
    case noEffectFwdProdAttr(top.frame.fullName, dl.defLHSVertex, top.flowEnv) of
    | just(x) when top.config.warnMissingInh && dl.found && attr.found ->
      [wrnFromOrigin(top, noEffectInhEqMsg(attr.attrDcl.fullName, dl.unparse, x))]
    | _ -> []
    end;
}
aspect production inhAppendColAttributeDef
top::ProductionStmt ::= @dl::DefLHS @attr::QNameAttrOccur e::Expr
{
  top.errors <-
    case noEffectFwdProdAttr(top.frame.fullName, dl.defLHSVertex, top.flowEnv) of
    | just(x) when top.config.warnMissingInh && dl.found && attr.found ->
      [wrnFromOrigin(top, noEffectInhEqMsg(attr.attrDcl.fullName, dl.unparse, x))]
    | _ -> []
    end;
}
aspect production forwardInh
top::ForwardInh ::= lhs::ForwardLHSExpr '=' e::Expr ';'
{
  top.errors <-
    case lhs, noEffectFwdProdAttr(top.frame.fullName, forwardVertexType(), top.flowEnv) of
    | forwardLhsExpr(q), just(x) when top.config.warnMissingInh && q.found ->
      [wrnFromOrigin(top, noEffectInhEqMsg(q.attrDcl.fullName, "forward", x))]
    | _, _ -> []
    end;
}

-- The forward production attribute shared unconditionally as the forward, where vt is rooted at the
-- forward in prod.
function noEffectFwdProdAttr
Maybe<String> ::= prod::String  vt::VertexType  flowEnv::FlowEnv
{
  local fwdProdAttrs::[VertexType] =
    filter(isFwdProdAttrVertex(prod, _, flowEnv), lookupSharedIntoAlways(prod, forwardVertexType(), flowEnv));
  return
    case transRootVertex(vt), fwdProdAttrs of
    | forwardVertexType(), localVertexType(fName) :: _ -> just(fName)
    | _, _ -> nothing()
    end;
}

fun noEffectInhEqMsg String ::= attr::String  tree::String  fwdProdAttr::String =
  s"Inherited equation for ${attr} on ${tree} has no effect: the tree is that of forward production attribute " ++
  s"${fwdProdAttr}, which already has this production as its forward parent";
