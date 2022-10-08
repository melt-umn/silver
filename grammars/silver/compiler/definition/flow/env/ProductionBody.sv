grammar silver:compiler:definition:flow:env;

import silver:compiler:definition:type only isNonterminal, typerep;
import silver:compiler:definition:type:syntax;
import silver:compiler:modification:defaultattr;
import silver:compiler:modification:collection;
import silver:compiler:modification:copper;
import silver:compiler:driver:util only isExportedBy, RootSpec;

attribute flowDefs, flowEnv occurs on ProductionBody, ProductionStmts, ProductionStmt, ForwardInhs, ForwardInh;
attribute flowEnv occurs on DefLHS;

propagate flowDefs on ProductionBody, ProductionStmts, ProductionStmt, ForwardInhs, ForwardInh;

{- A short note on how flowDefs are generated:

  - We ALWAYS produce the flowDef itself. This is necessary to catch missing or duplicate equations.
  - We omit the dependencies if it appears in a location not permitted to affect the flow type.
    This is to allow us to just compute flow types once, globally.
-}

{--
 - An occurs dcl info 's flow type can be affected here
 -}
function isAffectable
Boolean ::= prodgram::String  ntgram::String  cg::EnvTree<Decorated RootSpec>  d::OccursDclInfo
{
  return isExportedBy(prodgram, [ntgram, d.sourceGrammar], cg);
}

aspect production forwardsTo
top::ProductionStmt ::= 'forwards' 'to' e::Expr ';'
{
  local ntDefGram :: String = hackGramFromFName(top.frame.lhsNtName);

  local mayAffectFlowType :: Boolean =
    isExportedBy(top.grammarName, [ntDefGram], top.compiledGrammars);
  
  top.flowDefs <- [
    fwdEq(top.frame.fullName, e.flowDeps, mayAffectFlowType),
    -- These are attributes that we know, here, occurs on this nonterminal.
    -- The point is, these are the implicit equations we KNOW get generated, so
    -- we regard these as non-suspect. That is, we implicitly insert these copy
    -- equations here.
    -- Currently, we don't bother to filter this to just synthesized, but we should?
    implicitFwdAffects(top.frame.fullName, map((.attrOccurring),
      filter(isAffectable(top.grammarName, ntDefGram, top.compiledGrammars, _),
        getAttrsOn(top.frame.lhsNtName, top.env))))];
}
aspect production forwardInh
top::ForwardInh ::= lhs::ForwardLHSExpr '=' e::Expr ';'
{
  -- TODO: we need to figure out how to introduce any new lhsinh deps to the
  -- forward flow type automatically.
  top.flowDefs <-
    case lhs of
    | forwardLhsExpr(q) -> [fwdInhEq(top.frame.fullName, q.attrDcl.fullName, e.flowDeps)]
    end;
}

aspect production synthesizedAttributeDef
top::ProductionStmt ::= dl::PartiallyDecorated DefLHS  attr::PartiallyDecorated QNameAttrOccur  e::Expr
{
  local ntDefGram :: String = hackGramFromFName(top.frame.lhsNtName);

  local srcGrams :: [String] = [ntDefGram, hackGramFromDcl(attr)];

  local mayAffectFlowType :: Boolean =
    isExportedBy(top.grammarName, srcGrams, top.compiledGrammars);
  
  top.flowDefs <-
    if top.frame.hasPartialSignature then 
      [synEq(top.frame.fullName, attr.attrDcl.fullName, e.flowDeps, mayAffectFlowType)]
    else
      [defaultSynEq(top.frame.lhsNtName, attr.attrDcl.fullName, e.flowDeps)];
}
aspect production inheritedAttributeDef
top::ProductionStmt ::= dl::PartiallyDecorated DefLHS  attr::PartiallyDecorated QNameAttrOccur  e::Expr
{
  top.flowDefs <-
    case dl of
    | childDefLHS(q) -> [inhEq(top.frame.fullName, q.lookupValue.fullName, attr.attrDcl.fullName, e.flowDeps)]
    | localDefLHS(q) -> [localInhEq(top.frame.fullName, q.lookupValue.fullName, attr.attrDcl.fullName, e.flowDeps)]
    | forwardDefLHS(q) -> [fwdInhEq(top.frame.fullName, attr.attrDcl.fullName, e.flowDeps)]
    | _ -> [] -- TODO: this isn't quite extensible... more better way eventually, plz
    end;
}

aspect production localValueDef
top::ProductionStmt ::= val::PartiallyDecorated QName  e::Expr
{
  -- TODO: So, I'm just going to assume for the moment that we're always allowed to define the eq for a local...
  -- technically, it's possible to break this if you declare it in one grammar, but define it in another, but
  -- I think we should forbid that syntactically, later on...
  top.flowDefs <-
    [localEq(top.frame.fullName, val.lookupValue.fullName, val.lookupValue.typeScheme.typeName, val.lookupValue.typeScheme.typerep.isNonterminal, e.flowDeps)];

  -- If we have a type var with occurs-on contexts, add the specified syn -> inh deps for the new vertex
  top.flowDefs <- occursContextDeps(top.frame.signature, top.env, val.lookupValue.typeScheme.typerep, localVertexType(val.lookupValue.fullName));
}

-- FROM COLLECTIONS TODO

aspect production synAppendColAttributeDef
top::ProductionStmt ::= dl::PartiallyDecorated DefLHS  attr::PartiallyDecorated QNameAttrOccur  {- <- -} e::Expr
{
  local ntDefGram :: String = hackGramFromFName(top.frame.lhsNtName);

  local mayAffectFlowType :: Boolean =
    isExportedBy(top.grammarName, [ntDefGram, hackGramFromDcl(attr)], top.compiledGrammars);

  top.flowDefs <-
    [extraEq(top.frame.fullName, lhsSynVertex(attr.attrDcl.fullName), e.flowDeps, mayAffectFlowType)];
}

aspect production inhAppendColAttributeDef
top::ProductionStmt ::= dl::PartiallyDecorated DefLHS  attr::PartiallyDecorated QNameAttrOccur  {- <- -} e::Expr
{
  local vertex :: FlowVertex =
    case dl of
    | childDefLHS(q) -> rhsVertex(q.lookupValue.fullName, attr.attrDcl.fullName)
    | localDefLHS(q) -> localVertex(q.lookupValue.fullName, attr.attrDcl.fullName)
    | forwardDefLHS(q) -> forwardVertex(attr.attrDcl.fullName)
    | _ -> localEqVertex("bogus:value:from:inhcontrib:flow")
    end;
  top.flowDefs <-
    [extraEq(top.frame.fullName, vertex, e.flowDeps, true)];
}
aspect production synBaseColAttributeDef
top::ProductionStmt ::= dl::PartiallyDecorated DefLHS  attr::PartiallyDecorated QNameAttrOccur  e::Expr
{
  local ntDefGram :: String = hackGramFromFName(top.frame.lhsNtName);

  local srcGrams :: [String] = [ntDefGram, hackGramFromDcl(attr)];

  local mayAffectFlowType :: Boolean =
    isExportedBy(top.grammarName, srcGrams, top.compiledGrammars);
  
  top.flowDefs <-
    if top.frame.hasPartialSignature then 
      [synEq(top.frame.fullName, attr.attrDcl.fullName, e.flowDeps, mayAffectFlowType)]
    else
      [defaultSynEq(top.frame.lhsNtName, attr.attrDcl.fullName, e.flowDeps)];
}
aspect production inhBaseColAttributeDef
top::ProductionStmt ::= dl::PartiallyDecorated DefLHS  attr::PartiallyDecorated QNameAttrOccur  e::Expr
{
  top.flowDefs <-
    case dl of
    | childDefLHS(q) -> [inhEq(top.frame.fullName, q.lookupValue.fullName, attr.attrDcl.fullName, e.flowDeps)]
    | localDefLHS(q) -> [localInhEq(top.frame.fullName, q.lookupValue.fullName, attr.attrDcl.fullName, e.flowDeps)]
    | forwardDefLHS(q) -> [fwdInhEq(top.frame.fullName, attr.attrDcl.fullName, e.flowDeps)]
    | _ -> [] -- TODO: this isn't quite extensible... more better way eventually, plz
    end;
}


aspect production appendCollectionValueDef
top::ProductionStmt ::= val::PartiallyDecorated QName  e::Expr
{
  local locDefGram :: String = hackGramFromQName(val.lookupValue);
  -- TODO: possible bug? this would include ":local" in the gram wouldn't it?

  local mayAffectFlowType :: Boolean =
    isExportedBy(top.grammarName, [locDefGram], top.compiledGrammars);

  -- TODO: So, locals that may affect flow types' suspect edges can NEVER have an effect
  -- so we don't bother to even emit the extra equations in that case.
  -- But, this means we might lose out on knowing there's a contribution here.
  -- If we ever start using this information to locate contributions.
  -- If we do, we'll have to come back here to add 'location' info anyway,
  -- so if we do that, uhhh... fix this! Because you're here! Reading this!

  top.flowDefs <-
    if mayAffectFlowType
    then [extraEq(top.frame.fullName, localEqVertex(val.lookupValue.fullName), e.flowDeps, true)]
    else [];
}

-- TODO: Copper ProductionStmts

-- We're in the unfortunate position of HAVING to compute values for 'flowDefs'
-- even if there are errors in the larger grammar, as remote errors in binding
-- cannot be observed to suppress the analysis of flow.

-- It's not clear how to ideally fix this problem. What we do here is just report
-- harmless junk if we can't determine a good value.

-- Source grammar of a nonterminal's fullName
function hackGramFromFName
String ::= s::String
{
  -- As a safety feature, rather than crash in this instance, report no known grammar
  local i :: Integer = lastIndexOf(":", s);
  return if i > 0 then substring(0, i, s) else "";
}
-- Source grammar of a lookup of an attribute occurrence dcl
function hackGramFromDcl
String ::= qn::Decorated QNameAttrOccur
{
  return if qn.found then qn.dcl.sourceGrammar else "";
}
-- Source grammar of a lookup of a local dcl
function hackGramFromQName
String ::= qn::Decorated QNameLookup<ValueDclInfo>
{
  return if qn.found then qn.dcl.sourceGrammar else "";
}

