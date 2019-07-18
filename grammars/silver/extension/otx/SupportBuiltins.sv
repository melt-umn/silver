abstract production otxDebugImpl
top::Expr ::= arg::Expr
{
  top.unparse = "<otxDebugImpl>";

  top.errors := arg.errors;

  top.typerep = arg.typerep;

  top.flowDefs = arg.flowDefs;
  top.flowDeps = arg.flowDeps;

  arg.downSubst = top.downSubst;
  top.upSubst = arg.upSubst;

  top.translation = s"((${finalType(arg).transType})common.Origins.debug(${arg.translation}))";

  top.lazyTranslation = wrapThunk(top.translation, top.frame.lazyApplication);
}

abstract production otxDuplicateImpl
top::Expr ::= rule::Expr arg::Expr
{
  top.unparse = "<otxDuplicateImpl>";

  top.errors := rule.errors ++ arg.errors;

  top.typerep = arg.typerep;

  top.flowDefs = rule.flowDefs ++ arg.flowDefs;
  top.flowDeps = rule.flowDeps ++ arg.flowDeps;

  rule.downSubst = top.downSubst;
  arg.downSubst = rule.upSubst;
  top.upSubst = arg.upSubst;

  top.translation = s"/*otxDuplicateImpl*/((${finalType(arg).transType})common.Origins.duplicate((${rule.translation}).toString(), ${arg.translation}))";

  top.lazyTranslation = wrapThunk(top.translation, top.frame.lazyApplication);
}