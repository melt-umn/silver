grammar silver:compiler:analysis:warnings:flow;

import silver:compiler:modification:let_fix only lexicalLocalReference;

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
    && top.config.warnEqdef
    && !isExportedBy(top.grammarName, exportedBy, top.compiledGrammars)
    then [mwdaWrn(top.config, top.location, "Orphaned equation: " ++ attr.name ++ " (occurs from " ++ attr.dcl.sourceGrammar ++ ") in production " ++ top.frame.fullName)]
    else [];
  
  -- Duplicate equation check
  top.errors <-
    if length(dl.lookupEqDefLHS) > 1
    then [mwdaWrn(top.config, top.location, "Duplicate equation for " ++ attr.name ++ " in production " ++ top.frame.fullName)]
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
    && top.config.warnEqdef
    && !isExportedBy(top.grammarName, exportedBy, top.compiledGrammars)
    then [mwdaWrn(top.config, top.location, "Orphaned equation: " ++ attr.name ++ " on " ++ dl.name ++ " (occurs from " ++ attr.dcl.sourceGrammar ++ ") in production " ++ top.frame.fullName)]
    -- Now, check for duplicate equations!
    else [];
    
  top.errors <-
    if length(dl.lookupEqDefLHS) > 1
    then [mwdaWrn(top.config, top.location, "Duplicate equation for " ++ attr.name ++ " on " ++ dl.name ++ " in production " ++ top.frame.fullName)]
    else [];

  -- Check that if there is a partially decorated reference taken to this decoration site,
  -- we aren't defining an equation that isn't in that reference type (Decorated Foo with only {...}).
  top.errors <-
    if dl.found && attr.found
    && top.config.warnEqdef
    then flatMap(
      \ refSite::(Location, [String]) ->
        if contains(attr.attrDcl.fullName, refSite.2) then []
        else [mwdaWrn(top.config, top.location, "Attribute " ++ attr.name ++ " with an equation on " ++ dl.name ++ " is not in the partially decorated reference taken at " ++ refSite.1.unparse ++ " with only " ++ implode(", ", refSite.2))],
      case dl of
      | childDefLHS(q) -> getPartialRefs(top.frame.fullName, q.lookupValue.fullName, top.flowEnv)
      | localDefLHS(q) -> getPartialRefs(top.frame.fullName, q.lookupValue.fullName, top.flowEnv)
      | _ -> []
      end)
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
    && top.config.warnEqdef
    && !isExportedBy(top.grammarName, exportedBy, top.compiledGrammars)
    then [mwdaWrn(top.config, top.location, "Orphaned equation: " ++ attr.name ++ " (occurs from " ++ attr.dcl.sourceGrammar ++ ") in production " ++ top.frame.fullName)]
    else [];
  
  -- Duplicate equation check
  top.errors <-
    if length(dl.lookupEqDefLHS) > 1
    then [mwdaWrn(top.config, top.location, "Duplicate equation for " ++ attr.name ++ " in production " ++ top.frame.fullName)]
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
    && top.config.warnEqdef
    && !isExportedBy(top.grammarName, exportedBy, top.compiledGrammars)
    then [mwdaWrn(top.config, top.location, "Orphaned equation: " ++ attr.name ++ " on " ++ dl.name ++ " (occurs from " ++ attr.dcl.sourceGrammar ++ ") in production " ++ top.frame.fullName)]
    -- Now, check for duplicate equations!
    else [];
    
  top.errors <-
    if length(dl.lookupEqDefLHS) > 1
    then [mwdaWrn(top.config, top.location, "Duplicate equation for " ++ attr.name ++ " on " ++ dl.name ++ " in production " ++ top.frame.fullName)]
    else [];

  -- Check that if there is a partially decorated reference taken to this decoration site,
  -- we aren't defining an equation that isn't in that reference type (Decorated Foo with only {...}).
  top.errors <-
    if dl.found && attr.found
    && top.config.warnEqdef
    then flatMap(
      \ refSite::(Location, [String]) ->
        if contains(attr.attrDcl.fullName, refSite.2) then []
        else [mwdaWrn(top.config, top.location, "Attribute " ++ attr.name ++ " with an equation for " ++ dl.name ++ " is not in the partially decorated reference taken at " ++ refSite.1.unparse ++ " with only " ++ implode(", ", refSite.2))],
      case dl of
      | childDefLHS(q) -> getPartialRefs(top.frame.fullName, q.lookupValue.fullName, top.flowEnv)
      | localDefLHS(q) -> getPartialRefs(top.frame.fullName, q.lookupValue.fullName, top.flowEnv)
      | _ -> []
      end)
    else [];
}

aspect production exprLhsExpr
top::ExprLHSExpr ::= attr::QNameAttrOccur
{
  -- Duplicate equation check
  top.errors <-
    if attr.attrFound && length(filter(eq(attr.attrDcl.fullName, _), top.allSuppliedInhs)) > 1
    then [mwdaWrn(top.config, top.location, "Duplicate equation for " ++ attr.name)]
    else [];
}

-- These checks live here for now, since they are related to duplicate equations:
aspect production childReference
top::Expr ::= q::Decorated QName
{
  local finalTy::Type = performSubstitution(top.typerep, top.finalSubst);
  local partialRefs::[(Location, [String])] = getPartialRefs(top.frame.fullName, q.lookupValue.fullName, top.flowEnv);
  top.errors <-
    case finalTy, refSet of
    | partiallyDecoratedType(_, _), just(inhs) when top.config.warnEqdef && q.lookupValue.found ->
      if !null(partialRefs)
      -- There is an exported partial reference taken to this decoration site (might be somewhere else!),
      -- check that we are consistent with any exported partial refs:
      then flatMap(
        \ refSite::(Location, [String]) ->
          if refSite.2 == inhs then []
          else [mwdaWrn(top.config, top.location, s"Conflicting partially decorated references to ${q.name}: the reference taken here has type ${prettyType(finalTy)}, while at ${refSite.1.unparse} a reference is taken with only ${implode(", ", refSite.2)}.")],
        partialRefs)
      -- No exported partial references for this decoration site, so we must be orphaned:
      else [mwdaWrn(top.config, top.location, s"Orphaned partially decorated reference to ${q.lookupValue.fullName} in production ${top.frame.fullName} (reference has type ${prettyType(finalTy)}).")]
    | _, _ -> []
    end;
}
aspect production localReference
top::Expr ::= q::Decorated QName
{
  local finalTy::Type = performSubstitution(top.typerep, top.finalSubst);
  local partialRefs::[(Location, [String])] = getPartialRefs(top.frame.fullName, q.lookupValue.fullName, top.flowEnv);
  top.errors <-
    case finalTy, refSet of
    | partiallyDecoratedType(_, _), just(inhs) when top.config.warnEqdef && q.lookupValue.found ->
      if !null(partialRefs)
      -- There is an exported partial reference taken to this decoration site (might be somewhere else!),
      -- check that we are consistent with any exported partial refs:
      then flatMap(
        \ refSite::(Location, [String]) ->
          if refSite.2 == inhs then []
          else [mwdaWrn(top.config, top.location, s"Conflicting partially decorated references to ${q.name}: the reference taken here has type ${prettyType(finalTy)}, while at ${refSite.1.unparse} a reference is taken with only ${implode(", ", refSite.2)}.")],
        partialRefs)
      -- No exported partial references for this decoration site, so we must be orphaned:
      else [mwdaWrn(top.config, top.location, s"Orphaned partially decorated reference to ${q.lookupValue.fullName} in production ${top.frame.fullName} (reference has type ${prettyType(finalTy)}).")]
    | _, _ -> []
    end;
}
aspect production lhsReference
top::Expr ::= q::Decorated QName
{
  local finalTy::Type = performSubstitution(top.typerep, top.finalSubst);
  top.errors <-
    case finalTy of
    | partiallyDecoratedType(_, _) when top.config.warnEqdef ->
      [mwdaWrn(top.config, top.location, s"Cannot take a partially decorated reference of type ${prettyType(finalTy)} to the lhs tree.")]
    | _ -> []
    end;
}
aspect production forwardReference
top::Expr ::= q::Decorated QName
{
  local finalTy::Type = performSubstitution(top.typerep, top.finalSubst);
  top.errors <-
    case finalTy of
    | partiallyDecoratedType(_, _) when top.config.warnEqdef ->
      [mwdaWrn(top.config, top.location, s"Cannot take a partially decorated reference of type ${prettyType(finalTy)} to the forward tree.")]
    | _ -> []
    end;
}
aspect production lexicalLocalReference
top::Expr ::= q::Decorated QName  fi::ExprVertexInfo  fd::[FlowVertex]
{
  local finalTy::Type = performSubstitution(top.typerep, top.finalSubst);
  top.errors <-
    case finalTy, q.lookupValue.typeScheme.monoType of
    | partiallyDecoratedType(_, _), partiallyDecoratedType(_, _) -> []
    | partiallyDecoratedType(_, _), _ when top.config.warnEqdef ->
      [mwdaWrn(top.config, top.location, s"${q.name} was not bound as a partially decorated reference, but here it is used with type ${prettyType(finalTy)}.")]
    | _, _ -> []
    end;
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
