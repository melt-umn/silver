abstract production copyFunctionImpl
top::Expr ::= redex::Expr node::Expr
{
  top.unparse = "<copy>";

  top.errors := redex.errors ++ node.errors;

  top.typerep = node.typerep;

  top.flowDefs = redex.flowDefs ++ node.flowDefs;
  top.flowDeps = redex.flowDeps ++ node.flowDeps;

  redex.downSubst = top.downSubst;
  node.downSubst = redex.upSubst;
  top.upSubst = node.upSubst;

  top.translation = s"((${finalType(node).transType})common.Origins.copy(${redex.translation}, ${node.translation}))";

  top.lazyTranslation = wrapThunk(top.translation, top.frame.lazyApplication);
}