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
  local attribute ntDefGram :: String;
  ntDefGram = substring(0, lastIndexOf(":", namedSig.outputElement.typerep.typeName), namedSig.outputElement.typerep.typeName);

  top.errors <-
    if null(body.errors ++ ns.errors{-TODO-})
    && (top.config.warnAll || top.config.warnFwd)
    && null(body.uniqueSignificantExpression) -- no forward
    -- We use compute OPTIONAL deps here to allow for non-forwarding production in certain grammars
    && !contains(top.grammarName, computeOptionalDeps([ntDefGram], top.compiledGrammars))
    then [wrn(top.location, "Orphaned production: " ++ id.pp ++ " on " ++ namedSig.outputElement.typerep.typeName)]
    else [];

}
