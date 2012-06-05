grammar silver:analysis:warnings:defs;

imports silver:analysis:warnings;
imports silver:driver only run, RunUnit, computeDependencies, computeOptionalDeps;
imports silver:util:cmdargs;
imports silver:util;

imports silver:definition:core;
imports silver:definition:type;
imports silver:definition:type:syntax;
imports silver:definition:env;

--imports silver:definition:flow:env;
imports silver:definition:flow:ast;

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
  local attribute isClosedNt :: Boolean;
  isClosedNt = case nt.lookupType.dcls of
               | ntDcl(_, _, _, _, _, closed) :: _ -> closed
               | _ -> false -- default, if the lookup fails
               end;

  top.errors <-
    if null(nt.lookupType.errors ++ at.lookupAttribute.errors)
    && (top.config.warnAll || top.config.warnOrphaned)
    --&& nt.lookupType.dcl.sourceGrammar != top.grammarName
    --&& at.lookupAttribute.dcl.sourceGrammar != top.grammarName
    && !contains(top.grammarName, computeDependencies([nt.lookupType.dcl.sourceGrammar, at.lookupAttribute.dcl.sourceGrammar], top.compiledGrammars))
    then [wrn(top.location, "Orphaned occurs declaration: " ++ at.lookupAttribute.fullName ++ " on " ++ nt.lookupType.fullName)]
         -- If this is a non-closed NT, or not a synthesized attribute, then we're done. er TODO: only synthesized??
    else if !isClosedNt || (case at.lookupAttribute.dcls of synDcl(_,_,_,_,_) :: _ -> false | _ -> true end) then []
              -- For closed nt, either we're exported by only the nt, OR there MUST be a default!
         else if !contains(top.grammarName, computeDependencies([nt.lookupType.dcl.sourceGrammar], top.compiledGrammars))
              && null(lookupDef(nt.lookupType.fullName, at.lookupAttribute.fullName, top.flowEnv))
              then [wrn(top.location, at.lookupAttribute.fullName ++ " cannot occur on " ++ nt.lookupType.fullName ++ " because that nonterminal is closed, and this attribute does not have a default equation.")]
              else [];
}

