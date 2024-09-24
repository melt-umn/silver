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
  forwards to @rest;
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
top::AGDcl ::= 'abstract' 'production' id::Name d::ProductionImplements ns::ProductionSignature body::ProductionBody
{
  local ntDefGram :: String =
    substring(0, lastIndexOf(":", namedSig.outputElement.typerep.typeName), namedSig.outputElement.typerep.typeName);

  local isClosedNt :: Boolean =
    case getTypeDclAll(namedSig.outputElement.typerep.typeName, top.env) of
    | ntDcl(_, _, _, closed, _) :: _ -> closed
    | _ -> false -- default, if the lookup fails
    end;

  top.errors <-
    if null(body.errors ++ ns.errors)
    && top.config.warnFwd
    -- If this production does not forward
    && null(body.forwardExpr)
    -- AND this is not a closed nonterminal
    && !isClosedNt
    -- AND this production is not exported by the nonterminal definition grammar... even including options
    && !isExportedBy(top.grammarName, [ntDefGram], top.compiledGrammars)
    then [mwdaWrnFromOrigin(top, "Orphaned production: " ++ id.name ++ " on " ++ namedSig.outputElement.typerep.typeName)]
    else [];

  -- This check is about orphaned implementation prods that don't forward properly,
  -- this seems like a reasonablke place to put it.
  top.errors <-
    if null(body.errors ++ ns.errors)
    && top.config.warnFwd
    then
      case d.implementsSig of
      | just(dSig) when
          -- If this production implements a dispatch signature from a grammar that does not export this production
          !isExportedBy(top.grammarName, [implode(":", init(explode(":", dSig.fullName)))], top.compiledGrammars) &&
          -- AND this production does not forward to an application of the same dispatch signature with the same shared children
          !forwardsToImplementedSig
        -> [mwdaWrnFromOrigin(top, s"Orphaned implementation production ${id.name} for dispatch ${dSig.fullName}; this production must forward directly to an application of this dispatch signature with the same shared children.")]
      | _ -> []
      end
    else [];
}
