grammar silver:analysis:warnings:defs;

synthesized attribute warnEqdef :: Boolean occurs on CmdArgs;
synthesized attribute warnEqdefInh :: Boolean occurs on CmdArgs;

aspect production endCmdArgs
top::CmdArgs ::= l::[String]
{
  top.warnEqdef = false;
  top.warnEqdefInh = false;
}
abstract production warnEqdefFlag
top::CmdArgs ::= rest::CmdArgs
{
  top.warnEqdef = true;
  forwards to rest;
}
abstract production warnEqdefInhFlag
top::CmdArgs ::= rest::CmdArgs
{
  top.warnEqdefInh = true;
  forwards to rest;
}
aspect production run
top::RunUnit ::= iIn::IO args::[String]
{
  flags <- [pair("--warn-eqdef", flag(warnEqdefFlag)), pair("--warn-eqdef-inh", flag(warnEqdefInhFlag))];
}

aspect production synthesizedAttributeDef
top::ProductionStmt ::= dl::DefLHS '.' attr::Decorated QName '=' e::Expr
{
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

aspect production inheritedAttributeDef
top::ProductionStmt ::= dl::DefLHS '.' attr::Decorated QName '=' e::Expr
{
  local attribute prodDefGram :: String;
  prodDefGram = substring(0, lastIndexOf(":", top.signature.fullName), top.signature.fullName);

  top.errors <-
   if null(occursCheck.errors ++ attr.lookupAttribute.errors)
    && (top.config.warnAll || top.config.warnEqdefInh)
    && $4.lexeme != "<-" -- hack to omit collections
    && !contains(top.grammarName, computeDependencies([prodDefGram, occursCheck.dcl.sourceGrammar], top.compiledGrammars))
    then [wrn(top.location, "Orphaned inherited equation: " ++ attr.pp ++ " (occurs from " ++ occursCheck.dcl.sourceGrammar ++ ") on " ++ top.signature.fullName)]
    else [];
}

aspect production localValueDef
top::ProductionStmt ::= val::Decorated QName '=' e::Expr
{
  
}


