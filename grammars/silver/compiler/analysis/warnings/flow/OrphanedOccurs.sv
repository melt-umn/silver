grammar silver:compiler:analysis:warnings:flow;

synthesized attribute warnOrphaned :: Boolean occurs on CmdArgs;

aspect production endCmdArgs
top::CmdArgs ::= l::[String]
{
  top.warnOrphaned = false;
}
abstract production warnOrphanedFlag
top::CmdArgs ::= rest::CmdArgs
{
  top.warnOrphaned = true;
  forwards to rest;
}
aspect function parseArgs
Either<String  Decorated CmdArgs> ::= args::[String]
{
  flags <- [
    flagSpec(name="--warn-orphaned", paramString=nothing(),
      help="warn about orphaned attribute occurrences",
      flagParser=flag(warnOrphanedFlag))];
}

aspect production attributionDcl
top::AGDcl ::= 'attribute' at::QName attl::BracketedOptTypeExprs 'occurs' 'on' nt::QName nttl::BracketedOptTypeExprs ';'
{
  top.errors <-
    if nt.lookupType.found && at.lookupAttribute.found
    && top.config.warnOrphaned
    && !isExportedBy(top.grammarName, [nt.lookupType.dcl.sourceGrammar, at.lookupAttribute.dcl.sourceGrammar], top.compiledGrammars)
    then [mwdaWrnFromOrigin(top, "Orphaned occurs declaration: " ++ at.lookupAttribute.fullName ++ " on " ++ nt.lookupType.fullName)]
         -- If this is a non-closed NT, or not a synthesized attribute, then we're done.
    else [];
  
  top.errors <-
    if !nt.lookupType.found || !at.lookupAttribute.found || !nt.lookupType.dcl.isClosed || !at.lookupAttribute.dcl.isSynthesized then []
    -- For closed nt, either we're exported by only the nt, OR there MUST be a default!
    else if !isExportedBy(top.grammarName, [nt.lookupType.dcl.sourceGrammar], top.compiledGrammars)
         && null(lookupDef(nt.lookupType.fullName, at.lookupAttribute.fullName, top.flowEnv))
         then [mwdaWrnFromOrigin(top, at.lookupAttribute.fullName ++ " cannot occur on " ++ nt.lookupType.fullName ++ " because that nonterminal is closed, and this attribute does not have a default equation.")]
         else [];
}

