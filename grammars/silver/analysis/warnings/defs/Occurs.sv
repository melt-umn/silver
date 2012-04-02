grammar silver:analysis:warnings:defs;

imports silver:analysis:warnings;
imports silver:driver only run, RunUnit, computeDependencies;
imports silver:util:cmdargs;
imports silver:util;

imports silver:definition:core;
imports silver:definition:type;
imports silver:definition:type:syntax;
imports silver:definition:env;

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
aspect production run
top::RunUnit ::= iIn::IO args::[String]
{
  flags <- [pair("--warn-orphaned", flag(warnOrphanedFlag))];
}

aspect production attributionDcl
top::AGDcl ::= 'attribute' at::QName attl::BracketedOptTypeList 'occurs' 'on' nt::QName nttl::BracketedOptTypeList ';'
{
  top.errors <-
    if null(nt.lookupType.errors ++ at.lookupAttribute.errors)
    && (top.config.warnAll || top.config.warnOrphaned)
    --&& nt.lookupType.dcl.sourceGrammar != top.grammarName
    --&& at.lookupAttribute.dcl.sourceGrammar != top.grammarName
    && !contains(top.grammarName, computeDependencies([nt.lookupType.dcl.sourceGrammar, at.lookupAttribute.dcl.sourceGrammar], top.compiledGrammars))
    then [wrn(top.location, "Orphaned occurs declaration: " ++ at.lookupAttribute.fullName ++ " on " ++ nt.lookupType.fullName)]
    else [];
}
