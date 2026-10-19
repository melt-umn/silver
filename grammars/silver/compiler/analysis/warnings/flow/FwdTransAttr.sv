grammar silver:compiler:analysis:warnings:flow;

-- A forwarding production must take its translation attributes from its forward.  Then the
-- forward's translation attribute is the production's own, as the forward's copies of the
-- production's inherited attributes assume (see addFwdInhEqs).  This covers every form of equation,
-- in the production's body and in aspects of it.

aspect production synthesizedAttributeDef
top::ProductionStmt ::= @dl::DefLHS @attr::QNameAttrOccur e::Expr
{
  top.errors <-
    if top.config.warnMissingInh && attr.found && attr.attrDcl.isTranslation &&
       prodForwards(top.frame.fullName, top.env)
    then [mwdaWrnFromOrigin(top, fwdTransAttrDefMsg(attr.attrDcl.fullName, top.frame.fullName))]
    else [];
}
aspect production synBaseColAttributeDef
top::ProductionStmt ::= @dl::DefLHS @attr::QNameAttrOccur e::Expr
{
  top.errors <-
    if top.config.warnMissingInh && attr.found && attr.attrDcl.isTranslation &&
       prodForwards(top.frame.fullName, top.env)
    then [mwdaWrnFromOrigin(top, fwdTransAttrDefMsg(attr.attrDcl.fullName, top.frame.fullName))]
    else [];
}
aspect production synAppendColAttributeDef
top::ProductionStmt ::= @dl::DefLHS @attr::QNameAttrOccur e::Expr
{
  top.errors <-
    if top.config.warnMissingInh && attr.found && attr.attrDcl.isTranslation &&
       prodForwards(top.frame.fullName, top.env)
    then [mwdaWrnFromOrigin(top, fwdTransAttrDefMsg(attr.attrDcl.fullName, top.frame.fullName))]
    else [];
}

-- Does the production prod forward?
fun prodForwards Boolean ::= prod::String  env::Env =
  case getValueDcl(prod, env) of
  | d :: _ -> d.hasForward
  | [] -> false
  end;

fun fwdTransAttrDefMsg String ::= attr::String  prod::String =
  s"Translation attribute ${attr} is defined in production ${prod}, which forwards; a forwarding production must " ++
  "take its translation attributes from its forward";
