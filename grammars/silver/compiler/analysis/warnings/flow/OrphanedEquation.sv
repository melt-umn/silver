grammar silver:compiler:analysis:warnings:flow;

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
Either<String  Decorated CmdArgs> ::= args::[String]
{
  flags <- [pair("--warn-eqdef", flag(warnEqdefFlag))];
}

aspect production synthesizedAttributeDef
top::ProductionStmt ::= dl::Decorated DefLHS  attr::Decorated QNameAttrOccur e::Expr
{
  local exportedBy :: [String] = 
    if top.frame.hasPartialSignature
    then [top.frame.sourceGrammar, attr.dcl.sourceGrammar]
    else [attr.dcl.sourceGrammar]; -- defaults can only be listed together with occurs.

  -- Orphaned equation check
  top.errors <-
    if dl.found && attr.found
    && (top.config.warnAll || top.config.warnEqdef || top.config.runMwda)
    && !isExportedBy(top.grammarName, exportedBy, top.compiledGrammars)
    then [mwdaWrn(top.location, "Orphaned equation: " ++ attr.name ++ " (occurs from " ++ attr.dcl.sourceGrammar ++ ") in production " ++ top.frame.fullName, top.config.runMwda)]
    else [];
  
  -- Duplicate equation check
  top.errors <-
    if length(dl.lookupEqDefLHS) > 1
    then [mwdaWrn(top.location, "Duplicate equation for " ++ attr.name ++ " in production " ++ top.frame.fullName, top.config.runMwda)]
    else [];
}

aspect production inheritedAttributeDef
top::ProductionStmt ::= dl::Decorated DefLHS  attr::Decorated QNameAttrOccur  e::Expr
{
  local exportedBy :: [String] = 
    case dl of
    -- Exported by the declaration of the thing we're giving inh to, or to the occurs
    | localDefLHS(q) -> [q.lookupValue.dcl.sourceGrammar, attr.dcl.sourceGrammar]
    -- For rhs or forwards, that's the production.
    | _ -> [top.frame.sourceGrammar, attr.dcl.sourceGrammar]
    end;

  top.errors <-
    if dl.found && attr.found
    && (top.config.warnAll || top.config.warnEqdef || top.config.runMwda)
    && !isExportedBy(top.grammarName, exportedBy, top.compiledGrammars)
    then [mwdaWrn(top.location, "Orphaned equation: " ++ attr.name ++ " on " ++ dl.name ++ " (occurs from " ++ attr.dcl.sourceGrammar ++ ") in production " ++ top.frame.fullName, top.config.runMwda)]
    -- Now, check for duplicate equations!
    else [];
    
  top.errors <-
    if length(dl.lookupEqDefLHS) > 1
    then [mwdaWrn(top.location, "Duplicate equation for " ++ attr.name ++ " on " ++ dl.name ++ " in production " ++ top.frame.fullName, top.config.runMwda)]
    else [];
}


--- FROM COLLECTIONS

aspect production synBaseColAttributeDef
top::ProductionStmt ::= dl::Decorated DefLHS  attr::Decorated QNameAttrOccur  e::Expr
{
  local exportedBy :: [String] = 
    if top.frame.hasPartialSignature
    then [top.frame.sourceGrammar, attr.dcl.sourceGrammar]
    else [attr.dcl.sourceGrammar]; -- defaults can only be listed together with occurs.

  -- Orphaned equation check
  top.errors <-
    if dl.found && attr.found
    && (top.config.warnAll || top.config.warnEqdef || top.config.runMwda)
    && !isExportedBy(top.grammarName, exportedBy, top.compiledGrammars)
    then [mwdaWrn(top.location, "Orphaned equation: " ++ attr.name ++ " (occurs from " ++ attr.dcl.sourceGrammar ++ ") in production " ++ top.frame.fullName, top.config.runMwda)]
    else [];
  
  -- Duplicate equation check
  top.errors <-
    if length(dl.lookupEqDefLHS) > 1
    then [mwdaWrn(top.location, "Duplicate equation for " ++ attr.name ++ " in production " ++ top.frame.fullName, top.config.runMwda)]
    else [];
}
aspect production inhBaseColAttributeDef
top::ProductionStmt ::= dl::Decorated DefLHS  attr::Decorated QNameAttrOccur  e::Expr
{
  local exportedBy :: [String] = 
    case dl of
    -- Exported by the declaration of the thing we're giving inh to, or to the occurs
    | localDefLHS(q) -> [q.lookupValue.dcl.sourceGrammar, attr.dcl.sourceGrammar]
    -- For rhs or forwards, that's the production.
    | _ -> [top.frame.sourceGrammar, attr.dcl.sourceGrammar]
    end;

  top.errors <-
    if dl.found && attr.found
    && (top.config.warnAll || top.config.warnEqdef || top.config.runMwda)
    && !isExportedBy(top.grammarName, exportedBy, top.compiledGrammars)
    then [mwdaWrn(top.location, "Orphaned equation: " ++ attr.name ++ " on " ++ dl.name ++ " (occurs from " ++ attr.dcl.sourceGrammar ++ ") in production " ++ top.frame.fullName, top.config.runMwda)]
    -- Now, check for duplicate equations!
    else [];
    
  top.errors <-
    if length(dl.lookupEqDefLHS) > 1
    then [mwdaWrn(top.location, "Duplicate equation for " ++ attr.name ++ " on " ++ dl.name ++ " in production " ++ top.frame.fullName, top.config.runMwda)]
    else [];
}

aspect production exprLhsExpr
top::ExprLHSExpr ::= attr::QNameAttrOccur
{
  -- Duplicate equation check
  top.errors <-
    if attr.attrFound && length(filter(eq(attr.attrDcl.fullName, _), top.allSuppliedInhs)) > 1
    then [mwdaWrn(top.location, "Duplicate equation for " ++ attr.name, top.config.runMwda)]
    else [];
}


--- For our DefLHSs:

{--
 - A lookup for other instances of this equation on this DefLHS.
 -}
synthesized attribute lookupEqDefLHS :: [FlowDef] occurs on DefLHS;
flowtype lookupEqDefLHS {decorate} on DefLHS;

aspect lookupEqDefLHS on top::DefLHS of
  -- prod, child, attr
| childDefLHS(q) -> lookupInh(top.frame.fullName, q.lookupValue.fullName, top.defLHSattr.attrDcl.fullName, top.flowEnv)
  -- prod, attr
| lhsDefLHS(q) -> lookupSyn(top.frame.fullName, top.defLHSattr.attrDcl.fullName, top.flowEnv)
  -- prod, local, attr
| localDefLHS(q) -> lookupLocalInh(top.frame.fullName, q.lookupValue.fullName, top.defLHSattr.attrDcl.fullName, top.flowEnv)
  -- prod, attr
| forwardDefLHS(q) -> lookupFwdInh(top.frame.fullName, top.defLHSattr.attrDcl.fullName, top.flowEnv)
  -- nt, attr
| defaultLhsDefLHS(q) -> lookupDef(top.frame.lhsNtName, top.defLHSattr.attrDcl.fullName, top.flowEnv)

| errorDefLHS(q) -> []
| parserAttributeDefLHS(q) -> [] -- TODO: maybe error?
end;
