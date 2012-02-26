grammar silver:analysis:warnings:defs;

imports silver:analysis:warnings;
imports silver:driver;
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
  -- The occurs declarations should be exported by either:
  -- 1. the grammar declaring the nonterminal
  -- 2. the grammar declaring the attribute.
  
  -- TODO: only checks grammar equals, does NOT pay attention to exports!
  
  top.errors <-
    if null(nt.lookupType.errors ++ at.lookupAttribute.errors)
    && (top.config.warnAll || top.config.warnOrphaned)
    && nt.lookupType.dcl.sourceGrammar != top.grammarName
    && at.lookupAttribute.dcl.sourceGrammar != top.grammarName
    then [wrn(top.location, "Orphaned occurs declaration: " ++ at.lookupAttribute.fullName ++ " on " ++ nt.lookupType.fullName)]
    else [];
}
