grammar silver:definition:flow:env;

import silver:definition:type:syntax;
import silver:modification:defaultattr;
import silver:modification:collection;
import silver:modification:copper;
import silver:util only contains;
import silver:driver:util only isExportedBy, RootSpec;

attribute flowDefs, flowEnv occurs on ProductionBody, ProductionStmts, ProductionStmt, ForwardInhs, ForwardInh;
attribute flowEnv occurs on DefLHS;

{- A short note on how flowDefs are generated:

  - We ALWAYS produce the flowDef itself. This is necessary to catch missing or duplicate equations.
  - We omit the dependencies if it appears in a location not permitted to affect the flow type.
    This is to allow us to just compute flow types once, globally.
-}

aspect production productionBody
top::ProductionBody ::= '{' stmts::ProductionStmts '}'
{
  top.flowDefs = stmts.flowDefs;
}

----

aspect production productionStmtsNil
top::ProductionStmts ::= 
{
  top.flowDefs = [];
}

aspect production productionStmtsSnoc
top::ProductionStmts ::= h::ProductionStmts  t::ProductionStmt
{
  top.flowDefs = h.flowDefs ++ t.flowDefs;
}

----

aspect production productionStmtAppend
top::ProductionStmt ::= h::ProductionStmt  t::ProductionStmt
{
  top.flowDefs = h.flowDefs ++ t.flowDefs;
}

aspect production errorProductionStmt
top::ProductionStmt ::= e::[Message]
{
  top.flowDefs = [];
}

{-
aspect default production
top::ProductionStmt ::=
{
  top.flowDefs = [];
}
-}

----

{--
 - An occurs dcl info 's flow type can be affected here
 -}
function isAffectable
Boolean ::= prodgram::String  ntgram::String  cg::EnvTree<Decorated RootSpec>  d::DclInfo
{
  return isExportedBy(prodgram, [ntgram, d.sourceGrammar], cg);
}

aspect production forwardsTo
top::ProductionStmt ::= 'forwards' 'to' e::Expr ';'
{
  local ntDefGram :: String = hackGramFromFName(top.signature.outputElement.typerep.typeName);

  local mayAffectFlowType :: Boolean =
    isExportedBy(top.grammarName, [ntDefGram], top.compiledGrammars);
  
  top.flowDefs = e.flowDefs ++ [
    fwdEq(top.signature.fullName, e.flowDeps, mayAffectFlowType),
    -- These are attributes that we know, here, occurs on this nonterminal.
    -- The point is, these are the implicit equations we KNOW get generated, so
    -- we regard these as non-suspect. That is, we implicitly insert these copy
    -- equations here.
    -- Currently, we don't bother to filter this to just synthesized, but we should?
    implicitFwdAffects(top.signature.fullName, map((.attrOccurring),
      filter(isAffectable(top.grammarName, ntDefGram, top.compiledGrammars, _),
        getAttrsOn(top.signature.outputElement.typerep.typeName, top.env))))];
}
aspect production forwardingWith
top::ProductionStmt ::= 'forwarding' 'with' '{' inh::ForwardInhs '}' ';'
{
  top.flowDefs = inh.flowDefs;
}

aspect production forwardInhsOne
top::ForwardInhs ::= lhs::ForwardInh
{
  top.flowDefs = lhs.flowDefs;
}
aspect production forwardInhsCons
top::ForwardInhs ::= lhs::ForwardInh rhs::ForwardInhs
{
  top.flowDefs = lhs.flowDefs ++ rhs.flowDefs;
}
aspect production forwardInh
top::ForwardInh ::= lhs::ForwardLHSExpr '=' e::Expr ';'
{
  -- TODO: we need to figure out how to introduce any new lhsinh deps to the
  -- forward flow type automatically.
  top.flowDefs = e.flowDefs ++ 
    case lhs of
    | forwardLhsExpr(q) -> [fwdInhEq(top.signature.fullName, q.attrDcl.fullName, e.flowDeps)]
    end;
}

aspect production localAttributeDcl
top::ProductionStmt ::= 'local' 'attribute' a::Name '::' te::Type ';'
{
  top.flowDefs = [];
  -- This is basically taken care of by the definition equation, rather than here.
}
aspect production returnDef
top::ProductionStmt ::= 'return' e::Expr ';'
{
  top.flowDefs = e.flowDefs;
}

aspect production errorAttributeDef
top::ProductionStmt ::= msg::[Message] dl::Decorated DefLHS  attr::Decorated QNameAttrOccur  e::Expr
{
  top.flowDefs = e.flowDefs;
}

aspect production synthesizedAttributeDef
top::ProductionStmt ::= dl::Decorated DefLHS  attr::Decorated QNameAttrOccur  e::Expr
{
  local ntDefGram :: String = hackGramFromFName(top.signature.outputElement.typerep.typeName);

  local srcGrams :: [String] = [ntDefGram, hackGramFromDcl(attr)];

  local mayAffectFlowType :: Boolean =
    isExportedBy(top.grammarName, srcGrams, top.compiledGrammars);
  
  top.flowDefs = e.flowDefs ++
    if top.blockContext.hasPartialSignature then 
      [synEq(top.signature.fullName, attr.attrDcl.fullName, e.flowDeps, mayAffectFlowType)]
    else
      [defaultSynEq(top.signature.outputElement.typerep.typeName, attr.attrDcl.fullName, e.flowDeps)];
}
aspect production inheritedAttributeDef
top::ProductionStmt ::= dl::Decorated DefLHS  attr::Decorated QNameAttrOccur  e::Expr
{
  top.flowDefs = e.flowDefs ++
    case dl of
    | childDefLHS(q) -> [inhEq(top.signature.fullName, q.lookupValue.fullName, attr.attrDcl.fullName, e.flowDeps)]
    | localDefLHS(q) -> [localInhEq(top.signature.fullName, q.lookupValue.fullName, attr.attrDcl.fullName, e.flowDeps)]
    | forwardDefLHS(q) -> [fwdInhEq(top.signature.fullName, attr.attrDcl.fullName, e.flowDeps)]
    | _ -> [] -- TODO : this isn't quite extensible... more better way eventually, plz
    end;
}

aspect production localValueDef
top::ProductionStmt ::= val::Decorated QName  e::Expr
{
  -- TODO: So, I'm just going to assume for the moment that we're always allowed to define the eq for a local...
  -- technically, it's possible to break this if you declare it in one grammar, but define it in another, but
  -- I think we should forbid that syntactically, later on...
  top.flowDefs = e.flowDefs ++
    [localEq(top.signature.fullName, val.lookupValue.fullName, val.lookupValue.typerep.typeName, e.flowDeps)];
}
aspect production errorValueDef
top::ProductionStmt ::= val::Decorated QName  e::Expr
{
  top.flowDefs = e.flowDefs;
}

-- FROM COLLECTIONS TODO

aspect production synAppendColAttributeDef
top::ProductionStmt ::= dl::Decorated DefLHS  attr::Decorated QNameAttrOccur  {- <- -} e::Expr
{
  local ntDefGram :: String = hackGramFromFName(top.signature.outputElement.typerep.typeName);

  local mayAffectFlowType :: Boolean =
    isExportedBy(top.grammarName, [ntDefGram, hackGramFromDcl(attr)], top.compiledGrammars);

  top.flowDefs = e.flowDefs ++
    [extraEq(top.signature.fullName, lhsSynVertex(attr.attrDcl.fullName), e.flowDeps, mayAffectFlowType)];
}

aspect production inhAppendColAttributeDef
top::ProductionStmt ::= dl::Decorated DefLHS  attr::Decorated QNameAttrOccur  {- <- -} e::Expr
{
  local vertex :: FlowVertex =
    case dl of
    | childDefLHS(q) -> rhsVertex(q.lookupValue.fullName, attr.attrDcl.fullName)
    | localDefLHS(q) -> localVertex(q.lookupValue.fullName, attr.attrDcl.fullName)
    | forwardDefLHS(q) -> forwardVertex(attr.attrDcl.fullName)
    | _ -> localEqVertex("bogus:value:from:inhcontrib:flow")
    end;
  top.flowDefs = e.flowDefs ++
    [extraEq(top.signature.fullName, vertex, e.flowDeps, true)];
}
aspect production synBaseColAttributeDef
top::ProductionStmt ::= dl::Decorated DefLHS  attr::Decorated QNameAttrOccur  e::Expr
{
  local ntDefGram :: String = hackGramFromFName(top.signature.outputElement.typerep.typeName);

  local srcGrams :: [String] = [ntDefGram, hackGramFromDcl(attr)];

  local mayAffectFlowType :: Boolean =
    isExportedBy(top.grammarName, srcGrams, top.compiledGrammars);
  
  top.flowDefs = e.flowDefs ++
    if top.blockContext.hasPartialSignature then 
      [synEq(top.signature.fullName, attr.attrDcl.fullName, e.flowDeps, mayAffectFlowType)]
    else
      [defaultSynEq(top.signature.outputElement.typerep.typeName, attr.attrDcl.fullName, e.flowDeps)];
}
aspect production inhBaseColAttributeDef
top::ProductionStmt ::= dl::Decorated DefLHS  attr::Decorated QNameAttrOccur  e::Expr
{
  top.flowDefs = e.flowDefs ++
    case dl of
    | childDefLHS(q) -> [inhEq(top.signature.fullName, q.lookupValue.fullName, attr.attrDcl.fullName, e.flowDeps)]
    | localDefLHS(q) -> [localInhEq(top.signature.fullName, q.lookupValue.fullName, attr.attrDcl.fullName, e.flowDeps)]
    | forwardDefLHS(q) -> [fwdInhEq(top.signature.fullName, attr.attrDcl.fullName, e.flowDeps)]
    | _ -> [] -- TODO : this isn't quite extensible... more better way eventually, plz
    end;
}


aspect production appendCollectionValueDef
top::ProductionStmt ::= val::Decorated QName  e::Expr
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

  top.flowDefs = e.flowDefs ++
    if mayAffectFlowType
    then [extraEq(top.signature.fullName, localEqVertex(val.lookupValue.fullName), e.flowDeps, true)]
    else [];
}
------ FROM COPPER TODO

aspect production pluckDef
top::ProductionStmt ::= 'pluck' e::Expr ';'
{
  top.flowDefs = e.flowDefs;
}

aspect production printStmt
top::ProductionStmt ::= 'print' e::Expr ';'
{
  top.flowDefs = e.flowDefs;
}

aspect production parserAttributeValueDef
top::ProductionStmt ::= val::Decorated QName  e::Expr
{
  top.flowDefs = e.flowDefs;
}

aspect production termAttrValueValueDef
top::ProductionStmt ::= val::Decorated QName  e::Expr
{
  top.flowDefs = e.flowDefs;
}
aspect production pushTokenIfStmt
top::ProductionStmt ::= 'pushToken' '(' val::QName ',' lexeme::Expr ')' 'if' condition::Expr ';'
{
  top.flowDefs = lexeme.flowDefs ++ condition.flowDefs;
}


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
  return if null(qn.errors) then qn.dcl.sourceGrammar else "";
}
-- Source grammar of a lookup of a local dcl
function hackGramFromQName
String ::= qn::Decorated QNameLookup
{
  return if null(qn.errors) then qn.dcl.sourceGrammar else "";
}

