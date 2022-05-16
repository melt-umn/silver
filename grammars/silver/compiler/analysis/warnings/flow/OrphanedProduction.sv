grammar silver:compiler:analysis:warnings:flow;

synthesized attribute warnFwd :: Boolean occurs on CmdArgs;

aspect production endCmdArgs
top::CmdArgs ::= l::[String]
{
  top.warnFwd = false;
}
abstract production warnFwdFlag
top::CmdArgs ::= rest::CmdArgs
{
  top.warnFwd = true;
  forwards to rest;
}
aspect function parseArgs
Either<String  Decorated CmdArgs> ::= args::[String]
{
  flags <- [
    flagSpec(name="--warn-fwd", paramString=nothing(),
      help="warn about orphaned productions",
      flagParser=flag(warnFwdFlag))];
}

aspect production productionDcl
top::AGDcl ::= 'abstract' 'production' id::Name ns::ProductionSignature body::ProductionBody
{
  local ntDefGram :: String =
    substring(0, lastIndexOf(":", namedSig.outputElement.typerep.typeName), namedSig.outputElement.typerep.typeName);

  local isClosedNt :: Boolean =
    case getTypeDclAll(namedSig.outputElement.typerep.typeName, top.env) of
    | ntDcl(_, _, closed, _) :: _ -> closed
    | _ -> false -- default, if the lookup fails
    end;

  top.errors <-
    if null(body.errors ++ ns.errors)
    && top.config.warnFwd
    -- If this production does not forward
    && null(body.uniqueSignificantExpression)
    -- AND this is not a closed nonterminal
    && !isClosedNt
    -- AND this production is not exported by the nonterminal definition grammar... even including options
    && !isExportedBy(top.grammarName, [ntDefGram], top.compiledGrammars)
    then [mwdaWrn(top.config, top.location, "Orphaned production: " ++ id.name ++ " on " ++ namedSig.outputElement.typerep.typeName)]
    else [];
}

