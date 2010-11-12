grammar silver:analysis:warnings:defs;

import silver:analysis:warnings;
import silver:util:command hiding grammarName;

import silver:definition:core;
import silver:definition:type;
import silver:definition:type:syntax;
import silver:definition:env;

synthesized attribute warnEqDef :: Boolean occurs on Command;

aspect production cRootAll
top::Command ::= c1::PieceList
{
  flagLookups <- [flagLookup("--warn-eqdef",false)];

  top.warnEqDef = !null(findFlag("--warn-eqdef", top.flags));
}

aspect production synthesizedAttributeDef
top::ProductionStmt ::= dl::DefLHS '.' attr::Decorated QName '=' e::Expr
{
  local attribute prodDefGram :: String;
  prodDefGram = substring(0, lastIndexOf(":", top.signature.fullName), top.signature.fullName);

  top.warnings <-
    if (cmdLineArgs.warnAll || cmdLineArgs.warnEqDef)
    && null(top.errors)
    && occursCheck.dcl.sourceGrammar != top.grammarName
    && prodDefGram != top.grammarName
    then [wrn(top.location, "Orphaned equation: " ++ attr.pp ++ " (from " ++ attr.lookupAttribute.dcl.sourceGrammar ++ ") on " ++ occursCheck.dcl.fullName)]
    else [];
}

aspect production inheritedAttributeDef
top::ProductionStmt ::= dl::DefLHS '.' attr::Decorated QName '=' e::Expr
{
  local attribute prodDefGram :: String;
  prodDefGram = substring(0, lastIndexOf(":", top.signature.fullName), top.signature.fullName);

  top.warnings <-
    if (cmdLineArgs.warnAll || cmdLineArgs.warnEqDef)
    && null(top.errors)
    && occursCheck.dcl.sourceGrammar != top.grammarName
    && prodDefGram != top.grammarName
    then [wrn(top.location, "Orphaned equation: " ++ attr.pp ++ " (from " ++ attr.lookupAttribute.dcl.sourceGrammar ++ ") on " ++ occursCheck.dcl.fullName)]
    else [];
}
