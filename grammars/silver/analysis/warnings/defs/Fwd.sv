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
  -- The production declarations should be exported by:
  -- 1. the grammar declaring the nonterminal
  -- ALTERNATIVELY,
  -- 2. they must forward
  
  -- TODO: only checks grammar equals, does NOT pay attention to exports!

  local attribute ntDefGram :: String;
  ntDefGram = substring(0, lastIndexOf(":", namedSig.outputElement.typerep.typeName), namedSig.outputElement.typerep.typeName);

  top.errors <-
    if null(body.errors ++ ns.errors{-TODO-})
    && (top.config.warnAll || top.config.warnFwd)
    && null(body.uniqueSignificantExpression) -- no forward
    && ntDefGram != top.grammarName
    then [wrn(top.location, "Orphaned production: " ++ id.pp ++ " on " ++ namedSig.outputElement.typerep.typeName)]
    else [];

}
