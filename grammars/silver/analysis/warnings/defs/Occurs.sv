grammar silver:analysis:warnings:defs;

import silver:analysis:warnings;
import silver:util:command hiding grammarName;

import silver:definition:core;
import silver:definition:type;
import silver:definition:type:syntax;
import silver:definition:env;

synthesized attribute warnOrphaned :: Boolean occurs on Command;

aspect production cRootAll
top::Command ::= c1::PieceList
{
  flagLookups <- [flagLookup("--warn-orphaned",false)];

  top.warnOrphaned = !null(findFlag("--warn-orphaned", top.flags));
}

aspect production attributionDcl
top::AGDcl ::= 'attribute' a::QName '<' tlat::TypeList '>' 'occurs' 'on' nt::QName '<' tlnt::TypeList '>' ';'
{
  top.warnings <-
    if (cmdLineArgs.warnAll || cmdLineArgs.warnOrphaned)
    && null(top.errors)
    && nt.lookupType.dcl.sourceGrammar != top.grammarName
    && a.lookupAttribute.dcl.sourceGrammar != top.grammarName
    then [wrn(top.location, "Orphaned occurs declaration: " ++ a.pp ++ " (from " ++ a.lookupAttribute.dcl.sourceGrammar ++ ") on " ++ nt.pp ++ "(from " ++ nt.lookupType.dcl.sourceGrammar ++ ")")]
    else [];
}
