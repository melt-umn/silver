grammar silver:analysis:warnings:defs;

import silver:modification:collection;
import silver:modification:copper;
import silver:modification:defaultattr;


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
    && (top.config.warnAll || top.config.warnEqdef)
    && !isExportedBy(top.grammarName, exportedBy, top.compiledGrammars)
    then [wrn(top.location, "Orphaned equation: " ++ attr.pp ++ " (occurs from " ++ attr.dcl.sourceGrammar ++ ") in production " ++ top.frame.fullName)]
    else [];
  
  -- Duplicate equation check
  top.errors <-
    if length(dl.lookupEqDefLHS) > 1
    then [wrn(top.location, "Duplicate equation for " ++ attr.pp ++ " in production " ++ top.frame.fullName)]
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
    && (top.config.warnAll || top.config.warnEqdef)
    && !isExportedBy(top.grammarName, exportedBy, top.compiledGrammars)
    then [wrn(top.location, "Orphaned equation: " ++ attr.pp ++ " on " ++ dl.pp ++ " (occurs from " ++ attr.dcl.sourceGrammar ++ ") in production " ++ top.frame.fullName)]
    -- Now, check for duplicate equations!
    else [];
    
  top.errors <-
    if length(dl.lookupEqDefLHS) > 1
    then [wrn(top.location, "Duplicate equation for " ++ attr.pp ++ " on " ++ dl.pp ++ " in production " ++ top.frame.fullName)]
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
    && (top.config.warnAll || top.config.warnEqdef)
    && !isExportedBy(top.grammarName, exportedBy, top.compiledGrammars)
    then [wrn(top.location, "Orphaned equation: " ++ attr.pp ++ " (occurs from " ++ attr.dcl.sourceGrammar ++ ") in production " ++ top.frame.fullName)]
    else [];
  
  -- Duplicate equation check
  top.errors <-
    if length(dl.lookupEqDefLHS) > 1
    then [wrn(top.location, "Duplicate equation for " ++ attr.pp ++ " in production " ++ top.frame.fullName)]
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
    && (top.config.warnAll || top.config.warnEqdef)
    && !isExportedBy(top.grammarName, exportedBy, top.compiledGrammars)
    then [wrn(top.location, "Orphaned equation: " ++ attr.pp ++ " on " ++ dl.pp ++ " (occurs from " ++ attr.dcl.sourceGrammar ++ ") in production " ++ top.frame.fullName)]
    -- Now, check for duplicate equations!
    else [];
    
  top.errors <-
    if length(dl.lookupEqDefLHS) > 1
    then [wrn(top.location, "Duplicate equation for " ++ attr.pp ++ " on " ++ dl.pp ++ " in production " ++ top.frame.fullName)]
    else [];
}


--- For our DefLHSs:

{--
 - A lookup for other instances of this equation on this DefLHS.
 -}
synthesized attribute lookupEqDefLHS :: [FlowDef] occurs on DefLHS;

aspect production childDefLHS
top::DefLHS ::= q::Decorated QName
{
  -- prod, child, attr
  top.lookupEqDefLHS = lookupInh(top.frame.fullName, q.lookupValue.fullName, top.defLHSattr.attrDcl.fullName, top.flowEnv);
}
aspect production lhsDefLHS
top::DefLHS ::= q::Decorated QName
{
  -- prod, attr
  top.lookupEqDefLHS = lookupSyn(top.frame.fullName, top.defLHSattr.attrDcl.fullName, top.flowEnv);
}
aspect production localDefLHS
top::DefLHS ::= q::Decorated QName
{
  -- prod, local, attr
  top.lookupEqDefLHS = lookupLocalInh(top.frame.fullName, q.lookupValue.fullName, top.defLHSattr.attrDcl.fullName, top.flowEnv);
}
aspect production forwardDefLHS
top::DefLHS ::= q::Decorated QName
{
  -- prod, attr
  top.lookupEqDefLHS = lookupFwdInh(top.frame.fullName, top.defLHSattr.attrDcl.fullName, top.flowEnv);
}
aspect production defaultLhsDefLHS
top::DefLHS ::= q::Decorated QName
{
  -- nt, attr
  top.lookupEqDefLHS = lookupDef(top.frame.lhsNtName, top.defLHSattr.attrDcl.fullName, top.flowEnv);
}
aspect production errorDefLHS
top::DefLHS ::= q::Decorated QName
{
  top.lookupEqDefLHS = [];
}
aspect production parserAttributeDefLHS
top::DefLHS ::= q::Decorated QName
{
  top.lookupEqDefLHS = []; -- TODO: maybe error?
}

