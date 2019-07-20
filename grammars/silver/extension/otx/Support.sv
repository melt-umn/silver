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

abstract production otxSexprifyImpl
top::Expr ::= arg::Expr
{
  top.unparse = "<otxSexprifyImpl>";

  top.errors := arg.errors;

  top.typerep = stringType();

  top.flowDefs = arg.flowDefs;
  top.flowDeps = arg.flowDeps;

  arg.downSubst = top.downSubst;
  top.upSubst = arg.upSubst;

  top.translation = s"(common.Origins.sexprify(${arg.translation}))";

  top.lazyTranslation = wrapThunk(top.translation, top.frame.lazyApplication);
}

abstract production otxShuckValueImpl
top::Expr ::= arg::Expr
{
  arg.downSubst = top.downSubst; --Needs to be provided here so we can ask it's typerep to forward to

  forwards to case arg.typerep of
    | decoratedType(_) -> newFunction('new', '(', arg, ')', location=top.location)
    | _ -> arg
  end;
}