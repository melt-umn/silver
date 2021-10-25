grammar silver:compiler:analysis:warnings:flow;

synthesized attribute warnInhSetRef :: Boolean occurs on CmdArgs;

aspect production endCmdArgs
top::CmdArgs ::= l::[String]
{
  top.warnInhSetRef = false;
}
abstract production warnInhSetRefFlag
top::CmdArgs ::= rest::CmdArgs
{
  top.warnInhSetRef = true;
  forwards to rest;
}
aspect function parseArgs
Either<String  Decorated CmdArgs> ::= args::[String]
{
  flags <- [pair("--warn-inhset-ref", flag(warnOrphanedFlag))];
}

function checkInhSetRef
[Message] ::= location::Location env::Decorated Env grammarName::String compiledGrammars::EnvTree<Decorated RootSpec> runMwda::Boolean finalTy::Type
{
  return
    case finalTy of
    | decoratedType(_, inhSetConstType(fn)) when getTypeDclAll(fn, env) matches dcl :: _ ->
      if !isExportedBy(grammarName, [dcl.sourceGrammar], compiledGrammars)
      then [mwdaWrn(location, "This taking of a reference of type " ++ prettyType(finalTy) ++ " is orphaned from the declaration of " ++ fn, runMwda)]
      else []
    | _ -> []
    end;
}

aspect production childReference
top::Expr ::= q::Decorated QName
{
  top.errors <-
    if q.lookupValue.found && isDecorable(q.lookupValue.typeScheme.typerep, top.env)
    && (top.config.warnAll || top.config.warnInhSetRef || top.config.runMwda)
    then checkInhSetRef(top.location, top.env, top.grammarName, top.compiledGrammars, top.config.runMwda, performSubstitution(top.typerep, top.finalSubst))
    else [];
}

aspect production lhsReference
top::Expr ::= q::Decorated QName
{
  top.errors <-
    if q.lookupValue.found && isDecorable(q.lookupValue.typeScheme.typerep, top.env)
    && (top.config.warnAll || top.config.warnInhSetRef || top.config.runMwda)
    then checkInhSetRef(top.location, top.env, top.grammarName, top.compiledGrammars, top.config.runMwda, performSubstitution(top.typerep, top.finalSubst))
    else [];
}

aspect production localReference
top::Expr ::= q::Decorated QName
{
  top.errors <-
    if q.lookupValue.found && isDecorable(q.lookupValue.typeScheme.typerep, top.env)
    && (top.config.warnAll || top.config.warnInhSetRef || top.config.runMwda)
    then checkInhSetRef(top.location, top.env, top.grammarName, top.compiledGrammars, top.config.runMwda, performSubstitution(top.typerep, top.finalSubst))
    else [];
}

aspect production forwardReference
top::Expr ::= q::Decorated QName
{
  top.errors <-
    if q.lookupValue.found && isDecorable(q.lookupValue.typeScheme.typerep, top.env)
    && (top.config.warnAll || top.config.warnInhSetRef || top.config.runMwda)
    then checkInhSetRef(top.location, top.env, top.grammarName, top.compiledGrammars, top.config.runMwda, performSubstitution(top.typerep, top.finalSubst))
    else [];
}

aspect production varVarBinder
top::VarBinder ::= n::Name
{
  top.errors <-
    case finalTy of
    | decoratedType(_, inhSetConstType(fn)) when isDecorable(top.bindingType, top.env) && (top.config.warnAll || top.config.warnInhSetRef || top.config.runMwda) ->
      [mwdaWrn(top.location, s"Taking a reference of type ${prettyType(finalTy)} via pattern matching is not yet supported.", top.config.runMwda)]
    | _ -> []
    end;
}
