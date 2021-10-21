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
  flags <- [("--warn-orphaned", flag(warnOrphanedFlag))];
}

aspect production attributionDcl
top::AGDcl ::= 'attribute' at::QName attl::BracketedOptTypeExprs 'occurs' 'on' nt::QName nttl::BracketedOptTypeExprs ';'
{
  local isClosedNt :: Boolean =
    case nt.lookupType.dcls of
    | ntDcl(_, _, closed, _) :: _ -> closed
    | _ -> false -- default, if the lookup fails
    end;

  top.errors <-
    if nt.lookupType.found && at.lookupAttribute.found
    && (top.config.warnAll || top.config.warnOrphaned || top.config.runMwda)
    && !isExportedBy(top.grammarName, [nt.lookupType.dcl.sourceGrammar, at.lookupAttribute.dcl.sourceGrammar], top.compiledGrammars)
    then [mwdaWrn(top.location, "Orphaned occurs declaration: " ++ at.lookupAttribute.fullName ++ " on " ++ nt.lookupType.fullName, top.config.runMwda)]
         -- If this is a non-closed NT, or not a synthesized attribute, then we're done.
    else [];
  
  top.errors <-
    if !nt.lookupType.found || !at.lookupAttribute.found || !isClosedNt || !at.lookupAttribute.dcl.isSynthesized then []
    -- For closed nt, either we're exported by only the nt, OR there MUST be a default!
    else if !isExportedBy(top.grammarName, [nt.lookupType.dcl.sourceGrammar], top.compiledGrammars)
         && null(lookupDef(nt.lookupType.fullName, at.lookupAttribute.fullName, top.flowEnv))
         then [mwdaWrn(top.location, at.lookupAttribute.fullName ++ " cannot occur on " ++ nt.lookupType.fullName ++ " because that nonterminal is closed, and this attribute does not have a default equation.", top.config.runMwda)]
         else [];
}

