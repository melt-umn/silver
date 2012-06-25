grammar silver:analysis:warnings:defs;

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
aspect production run
top::RunUnit ::= iIn::IO args::[String]
{
  flags <- [pair("--warn-fwd", flag(warnFwdFlag))];
}

aspect production productionDcl
top::AGDcl ::= 'abstract' 'production' id::Name ns::ProductionSignature body::ProductionBody
{
  local ntDefGram :: String =
    substring(0, lastIndexOf(":", namedSig.outputElement.typerep.typeName), namedSig.outputElement.typerep.typeName);

  local isClosedNt :: Boolean =
    case getTypeDclAll(namedSig.outputElement.typerep.typeName, top.env) of
    | ntDcl(_, _, _, _, _, closed) :: _ -> closed
    | _ -> false -- default, if the lookup fails
    end;

  top.errors <-
    if null(body.errors ++ ns.errors{-TODO-})
    && (top.config.warnAll || top.config.warnFwd)
    -- If this production does not forward
    && null(body.uniqueSignificantExpression)
    -- AND this is not a closed nonterminal
    && !isClosedNt
    -- AND this production is not exported by the nonterminal definition grammar... even including options
    && !contains(top.grammarName, computeOptionalDeps([ntDefGram], top.compiledGrammars))
    then [wrn(top.location, "Orphaned production: " ++ id.pp ++ " on " ++ namedSig.outputElement.typerep.typeName)]
    else [];

}
