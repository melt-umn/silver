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
    && !contains(top.grammarName,
         computeDependencies(
           if top.blockContext.hasPartialSignature -- is not a default production
           then [prodDefGram, occursCheck.dcl.sourceGrammar]
           else [occursCheck.dcl.sourceGrammar],
           top.compiledGrammars))
    then [wrn(top.location, "Orphaned equation: " ++ attr.pp ++ " (occurs from " ++ occursCheck.dcl.sourceGrammar ++ ") on " ++ top.signature.fullName)]
    -- Now, check for duplicate equations!
    else if top.blockContext.hasPartialSignature
         then if length(lookupSyn(top.signature.fullName, attr.lookupAttribute.fullName, top.flowEnv)) > 1
              then [wrn(top.location, "Duplicate equation for " ++ attr.pp ++ " on " ++ top.signature.fullName)]
              else []
         else if length(lookupDef(top.signature.outputElement.typerep.typeName, attr.lookupAttribute.fullName, top.flowEnv)) > 1
              then [wrn(top.location, "Duplicate default equation for " ++ attr.pp ++ " on " ++ top.signature.outputElement.typerep.typeName)]
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
    && !contains(top.grammarName,
         computeDependencies(
           if top.blockContext.hasPartialSignature
           then [prodDefGram, occursCheck.dcl.sourceGrammar]
           else [occursCheck.dcl.sourceGrammar],
           top.compiledGrammars))
    then [wrn(top.location, "Orphaned inherited equation: " ++ attr.pp ++ " (occurs from " ++ occursCheck.dcl.sourceGrammar ++ ") on " ++ top.signature.fullName)]
    -- Now, check for duplicate equations!
    else case dl of
         | childDefLHS(q) ->
             if length(lookupInh(top.signature.fullName, q.lookupValue.fullName, attr.lookupAttribute.fullName, top.flowEnv)) > 1
             then [wrn(top.location, "Duplicate equation for " ++ attr.pp ++ " on " ++ q.lookupValue.fullName)]
             else []
         | localDefLHS(q) ->
             if length(lookupLocalInh(top.signature.fullName, q.lookupValue.fullName, attr.lookupAttribute.fullName, top.flowEnv)) > 1
             then [wrn(top.location, "Duplicate equation for " ++ attr.pp ++ " on " ++ q.lookupValue.fullName)]
             else []
         | forwardDefLHS(q) ->
             if length(lookupFwdInh(top.signature.fullName, attr.lookupAttribute.fullName, top.flowEnv)) > 1
             then [wrn(top.location, "Duplicate equation for " ++ attr.pp ++ " on " ++ q.lookupValue.fullName)]
             else []
         | _ -> [] -- TODO : this isn't quite extensible... more better way eventually, plz
         end;

}

