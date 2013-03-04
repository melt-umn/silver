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
aspect function parseArgs
ParseResult<Decorated CmdArgs> ::= args::[String]
{
  flags <- [pair("--warn-eqdef", flag(warnEqdefFlag))];
}

aspect production synthesizedAttributeDef
top::ProductionStmt ::= dl::Decorated DefLHS  attr::Decorated QNameAttrOccur e::Expr
{
  local attribute prodDefGram :: String;
  prodDefGram = substring(0, lastIndexOf(":", top.signature.fullName), top.signature.fullName);

  -- Some facts to help understand the code.
  -- Synthesized attribute are always being defined on the LHS of a production.
  --  (i.e. no concerns about functions or local attributes or etc)
  -- Synthesized attributes MAY be contributions in a collection (<-)
  --  (we do not care about these! They only matter w.r.t flow types.)

  -- Synthesized attributes MAY appear in "default" productions.
  
  local exportedBy :: [String] = 
    if top.blockContext.hasPartialSignature
    then [prodDefGram, attr.dcl.sourceGrammar]
    else [attr.dcl.sourceGrammar]; -- defaults can only be listed together with occurs.

  top.errors <-
    if null(attr.errors ++ attr.errors)
    && (top.config.warnAll || top.config.warnEqdef)
    -- && $4.lexeme != "<-" -- hack to omit collections -- TODO BUG FIXME We need to aspect the new collections productions!!
    && !contains(top.grammarName, computeDependencies(exportedBy, top.compiledGrammars))
    then [wrn(top.location, "Orphaned equation: " ++ attr.pp ++ " (occurs from " ++ attr.dcl.sourceGrammar ++ ") on " ++ top.signature.fullName)]
    -- Now, check for duplicate equations!
    else if top.blockContext.hasPartialSignature
         then if length(lookupSyn(top.signature.fullName, attr.attrDcl.fullName, top.flowEnv)) > 1
              then [wrn(top.location, "Duplicate equation for " ++ attr.pp ++ " on " ++ top.signature.fullName)]
              else []
         else if length(lookupDef(top.signature.outputElement.typerep.typeName, attr.attrDcl.fullName, top.flowEnv)) > 1
              then [wrn(top.location, "Duplicate default equation for " ++ attr.pp ++ " on " ++ top.signature.outputElement.typerep.typeName)]
              else [];
}

aspect production inheritedAttributeDef
top::ProductionStmt ::= dl::Decorated DefLHS  attr::Decorated QNameAttrOccur  e::Expr
{
  local attribute prodDefGram :: String;
  prodDefGram = substring(0, lastIndexOf(":", top.signature.fullName), top.signature.fullName);

  -- Some facts to help understand the code.
  -- Inherited attributes may be given to RHS, locals, or forwards.
  -- Inherited equations may NOT appear in default productions.
  -- Inherited equations MAY be collection contributions!
  -- Equations can appear in functions or productions.

  local exportedBy :: [String] = 
    case dl of
    -- Exported by the declaration of the thing we're giving inh to, or to the occurs
    | localDefLHS(q) -> [q.lookupValue.dcl.sourceGrammar, attr.dcl.sourceGrammar]
    -- For rhs or forwards, that's the production.
    | _ -> [prodDefGram, attr.dcl.sourceGrammar]
    end;

  top.errors <-
    if null(attr.errors ++ attr.errors ++ dl.errors)
    && (top.config.warnAll || top.config.warnEqdef)
    -- && $4.lexeme != "<-" -- hack to omit collections -- TODO BUG FIXME ASPEC THE NEW COLLECTION PRODUCTION !!
    && !contains(top.grammarName, computeDependencies(exportedBy, top.compiledGrammars))
    then [wrn(top.location, "Orphaned inherited equation: " ++ attr.pp ++ " (occurs from " ++ attr.dcl.sourceGrammar ++ ") in " ++ top.signature.fullName ++ " on " ++ dl.pp)]
    -- Now, check for duplicate equations!
    else case dl of
         | childDefLHS(q) ->
             if length(lookupInh(top.signature.fullName, q.lookupValue.fullName, attr.attrDcl.fullName, top.flowEnv)) > 1
             then [wrn(top.location, "Duplicate equation for " ++ attr.pp ++ " on " ++ q.lookupValue.fullName)]
             else []
         | localDefLHS(q) ->
             if length(lookupLocalInh(top.signature.fullName, q.lookupValue.fullName, attr.attrDcl.fullName, top.flowEnv)) > 1
             then [wrn(top.location, "Duplicate equation for " ++ attr.pp ++ " on " ++ q.lookupValue.fullName)]
             else []
         | forwardDefLHS(q) ->
             if length(lookupFwdInh(top.signature.fullName, attr.attrDcl.fullName, top.flowEnv)) > 1
             then [wrn(top.location, "Duplicate equation for " ++ attr.pp ++ " on " ++ q.lookupValue.fullName)]
             else []
         | _ -> [] -- TODO : this isn't quite extensible... more better way eventually, plz
         -- Probably put a "lookupEq" on DefLHS?
         end;

}

