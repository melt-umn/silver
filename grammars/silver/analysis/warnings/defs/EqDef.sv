grammar silver:analysis:warnings:defs;

synthesized attribute warnEqdef :: Boolean occurs on CmdArgs;

aspect production endCmdArgs
top::CmdArgs ::= l::[String]
{
  top.warnEqdef = false;
}
abstract production warnEqdefFlag
top::CmdArgs ::= rest::CmdArgs
{
  top.warnEqdef = true;
  forwards to rest;
}
aspect production run
top::RunUnit ::= iIn::IO args::[String]
{
  flags <- [pair("--warn-eqdef", flag(warnEqdefFlag))];
}

aspect production synthesizedAttributeDef
top::ProductionStmt ::= dl::DefLHS '.' attr::Decorated QName '=' e::Expr
{
  -- synthesized attribute equations should be exported by either:
  -- 1. the grammar declaring the production
  -- 2. the grammar declaring the occurrence
  
  -- TODO: only checks grammar equals, does NOT pay attention to exports!

  local attribute prodDefGram :: String;
  prodDefGram = substring(0, lastIndexOf(":", top.signature.fullName), top.signature.fullName);

  top.errors <-
    if null(occursCheck.errors ++ attr.lookupAttribute.errors)
    && (top.config.warnAll || top.config.warnEqdef)
    && $4.lexeme != "<-" -- hack to omit collections
    --&& occursCheck.dcl.sourceGrammar != top.grammarName
    --&& prodDefGram != top.grammarName
    && !contains(top.grammarName, computeDependencies([prodDefGram, occursCheck.dcl.sourceGrammar], top.compiledGrammars))
    then [wrn(top.location, "Orphaned equation: " ++ attr.pp ++ " (occurs from " ++ occursCheck.dcl.sourceGrammar ++ ") on " ++ top.signature.fullName)]
    else [];
}

{- This check isn't right. TODO figure out what the right check even is!
aspect production inheritedAttributeDef
top::ProductionStmt ::= dl::DefLHS '.' attr::Decorated QName '=' e::Expr
{
  local attribute prodDefGram :: String;
  prodDefGram = substring(0, lastIndexOf(":", top.signature.fullName), top.signature.fullName);

  top.errors <-
   if null(occursCheck.errors ++ attr.lookupAttribute.errors)
    && containsAny(["--warn-eqdef","--warn-all"], top.config.warningFlags)
    && $4.lexeme != "<-" -- hack to omit collections
    && occursCheck.dcl.sourceGrammar != top.grammarName
    && prodDefGram != top.grammarName
    then [wrn(top.location, "Orphaned equation: " ++ attr.pp ++ " (from " ++ attr.lookupAttribute.dcl.sourceGrammar ++ ") on " ++ occursCheck.dcl.fullName)]
    else [];
}
-}
