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
ParseResult<Decorated CmdArgs> ::= args::[String]
{
  flags <- [pair("--warn-eqdef", flag(warnEqdefFlag))];
}

aspect production synthesizedAttributeDef
top::ProductionStmt ::= dl::Decorated DefLHS  attr::Decorated QNameAttrOccur e::Expr
{
  local attribute prodDefGram :: String;
  prodDefGram = substring(0, lastIndexOf(":", top.signature.fullName), top.signature.fullName);

  local exportedBy :: [String] = 
    if top.blockContext.hasPartialSignature
    then [prodDefGram, attr.dcl.sourceGrammar]
    else [attr.dcl.sourceGrammar]; -- defaults can only be listed together with occurs.

  -- Orphaned equation check
  top.errors <-
    if null(attr.errors ++ attr.errors)
    && (top.config.warnAll || top.config.warnEqdef)
    && !isExportedBy(top.grammarName, exportedBy, top.compiledGrammars)
    then [wrn(top.location, "Orphaned equation: " ++ attr.pp ++ " (occurs from " ++ attr.dcl.sourceGrammar ++ ") in production " ++ top.signature.fullName)]
    else [];
  
  -- Duplicate equation check
  top.errors <-
    if length(dl.lookupEqDefLHS) > 1
    then [wrn(top.location, "Duplicate equation for " ++ attr.pp ++ " in production " ++ top.signature.fullName)]
    else [];
}

aspect production inheritedAttributeDef
top::ProductionStmt ::= dl::Decorated DefLHS  attr::Decorated QNameAttrOccur  e::Expr
{
  local attribute prodDefGram :: String;
  prodDefGram = substring(0, lastIndexOf(":", top.signature.fullName), top.signature.fullName);

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
    && !isExportedBy(top.grammarName, exportedBy, top.compiledGrammars)
    then [wrn(top.location, "Orphaned equation: " ++ attr.pp ++ " on " ++ dl.pp ++ " (occurs from " ++ attr.dcl.sourceGrammar ++ ") in production " ++ top.signature.fullName)]
    -- Now, check for duplicate equations!
    else [];
    
  top.errors <-
    if length(dl.lookupEqDefLHS) > 1
    then [wrn(top.location, "Duplicate equation for " ++ attr.pp ++ " on " ++ dl.pp ++ " in production " ++ top.signature.fullName)]
    else [];
}


--- FROM COLLECTIONS

aspect production synBaseColAttributeDef
top::ProductionStmt ::= dl::Decorated DefLHS  attr::Decorated QNameAttrOccur  e::Expr
{
  local attribute prodDefGram :: String;
  prodDefGram = substring(0, lastIndexOf(":", top.signature.fullName), top.signature.fullName);

  local exportedBy :: [String] = 
    if top.blockContext.hasPartialSignature
    then [prodDefGram, attr.dcl.sourceGrammar]
    else [attr.dcl.sourceGrammar]; -- defaults can only be listed together with occurs.

  -- Orphaned equation check
  top.errors <-
    if null(attr.errors ++ attr.errors)
    && (top.config.warnAll || top.config.warnEqdef)
    && !isExportedBy(top.grammarName, exportedBy, top.compiledGrammars)
    then [wrn(top.location, "Orphaned equation: " ++ attr.pp ++ " (occurs from " ++ attr.dcl.sourceGrammar ++ ") in production " ++ top.signature.fullName)]
    else [];
  
  -- Duplicate equation check
  top.errors <-
    if length(dl.lookupEqDefLHS) > 1
    then [wrn(top.location, "Duplicate equation for " ++ attr.pp ++ " in production " ++ top.signature.fullName)]
    else [];
}
aspect production inhBaseColAttributeDef
top::ProductionStmt ::= dl::Decorated DefLHS  attr::Decorated QNameAttrOccur  e::Expr
{
  local attribute prodDefGram :: String;
  prodDefGram = substring(0, lastIndexOf(":", top.signature.fullName), top.signature.fullName);

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
    && !isExportedBy(top.grammarName, exportedBy, top.compiledGrammars)
    then [wrn(top.location, "Orphaned equation: " ++ attr.pp ++ " on " ++ dl.pp ++ " (occurs from " ++ attr.dcl.sourceGrammar ++ ") in production " ++ top.signature.fullName)]
    -- Now, check for duplicate equations!
    else [];
    
  top.errors <-
    if length(dl.lookupEqDefLHS) > 1
    then [wrn(top.location, "Duplicate equation for " ++ attr.pp ++ " on " ++ dl.pp ++ " in production " ++ top.signature.fullName)]
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
  top.lookupEqDefLHS = lookupInh(top.signature.fullName, q.lookupValue.fullName, top.defLHSattr.attrDcl.fullName, top.flowEnv);
}
aspect production lhsDefLHS
top::DefLHS ::= q::Decorated QName
{
  top.lookupEqDefLHS = lookupSyn(top.signature.fullName, top.defLHSattr.attrDcl.fullName, top.flowEnv);
}
aspect production localDefLHS
top::DefLHS ::= q::Decorated QName
{
  top.lookupEqDefLHS = lookupLocalInh(top.signature.fullName, q.lookupValue.fullName, top.defLHSattr.attrDcl.fullName, top.flowEnv);
}
aspect production forwardDefLHS
top::DefLHS ::= q::Decorated QName
{
  top.lookupEqDefLHS = lookupFwdInh(top.signature.fullName, top.defLHSattr.attrDcl.fullName, top.flowEnv);
}
aspect production defaultLhsDefLHS
top::DefLHS ::= q::Decorated QName
{
  top.lookupEqDefLHS = lookupDef(top.signature.outputElement.typerep.typeName, top.defLHSattr.attrDcl.fullName, top.flowEnv);
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

